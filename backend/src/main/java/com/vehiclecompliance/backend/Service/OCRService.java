package com.vehiclecompliance.backend.Service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
public class OCRService {

    public String extractText(MultipartFile image) throws IOException {

        File inputFile = File.createTempFile("vehicle-input-", ".png");
        File croppedFile = File.createTempFile("vehicle-cropped-", ".png");

        try {
            image.transferTo(inputFile);

            // Read image
            BufferedImage original = ImageIO.read(inputFile);

            if (original == null) {
                throw new RuntimeException("Could not read uploaded image");
            }

            /*
             * Crop the region containing the vehicle number.
             *
             * x = 330
             * y = 180
             * width = 1620
             * height = 300
             */
            int imageWidth = original.getWidth();
                int imageHeight = original.getHeight();

                BufferedImage cropped;

                double aspectRatio = (double) imageWidth / imageHeight;

                /*
                * If the uploaded image is already a close-up
                * number plate, use the entire image.
                */
                if (aspectRatio >= 4.0) {

                cropped = original;

                } else {

                /*
                * For larger vehicle images, crop the
                * approximate number-plate region.
                */
                int cropX = (int) (imageWidth * 0.16);
                int cropY = (int) (imageHeight * 0.26);

                int cropWidth = (int) (imageWidth * 0.79);
                int cropHeight = (int) (imageHeight * 0.44);

                cropX = Math.max(0, Math.min(cropX, imageWidth - 1));
                cropY = Math.max(0, Math.min(cropY, imageHeight - 1));

                cropWidth = Math.min(cropWidth, imageWidth - cropX);
                cropHeight = Math.min(cropHeight, imageHeight - cropY);

                cropped = original.getSubimage(
                        cropX,
                        cropY,
                        cropWidth,
                        cropHeight
                );
                }

            // Convert to grayscale
            BufferedImage gray = new BufferedImage(
                    cropped.getWidth(),
                    cropped.getHeight(),
                    BufferedImage.TYPE_BYTE_GRAY
            );

            Graphics2D graphics = gray.createGraphics();
            graphics.drawImage(cropped, 0, 0, null);
            graphics.dispose();

            // Upscale image 2x
            int newWidth = gray.getWidth() * 2;
            int newHeight = gray.getHeight() * 2;

            Image scaled = gray.getScaledInstance(
                    newWidth,
                    newHeight,
                    Image.SCALE_SMOOTH
            );

            BufferedImage finalImage = new BufferedImage(
                    newWidth,
                    newHeight,
                    BufferedImage.TYPE_BYTE_GRAY
            );

            Graphics2D scaledGraphics = finalImage.createGraphics();
            scaledGraphics.drawImage(scaled, 0, 0, null);
            scaledGraphics.dispose();

            ImageIO.write(finalImage, "png", croppedFile);

            // Tesseract
            Tesseract tesseract = new Tesseract();

            String tessDataPath =
                    new File("src/main/resources/tessdata").getAbsolutePath();

            tesseract.setDatapath(tessDataPath);
            tesseract.setLanguage("eng");

            // Single line of text
            tesseract.setPageSegMode(7);

            // Only letters and numbers
            tesseract.setVariable(
                    "tessedit_char_whitelist",
                    "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
            );

            String text = tesseract.doOCR(croppedFile);

            // Clean result
            return text
                    .toUpperCase()
                    .replaceAll("[^A-Z0-9]", "");

        } catch (TesseractException e) {

            throw new RuntimeException("OCR failed", e);

        } finally {

            inputFile.delete();
            croppedFile.delete();
        }
    }
}