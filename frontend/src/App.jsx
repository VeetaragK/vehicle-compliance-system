import { useState } from "react";
import {
  Search,
  Car,
  User,
  Fuel,
  MapPin,
  ShieldCheck,
  FileCheck2,
  Gauge,
  CheckCircle2,
  XCircle,
  AlertTriangle,
  Loader2,
} from "lucide-react";
import "./App.css";

const STATUS_META = {
  VALID: {
    color: "#0a8f3d",
    bg: "#e7f6ec",
    icon: CheckCircle2,
    label: "Valid",
  },
  EXPIRED: {
    color: "#d32f2f",
    bg: "#fdebeb",
    icon: XCircle,
    label: "Expired",
  },
  EXPIRING_SOON: {
    color: "#b8720a",
    bg: "#fdf1e0",
    icon: AlertTriangle,
    label: "Expiring soon",
  },
};

function StatusBadge({ status }) {
  const meta = STATUS_META[status] || STATUS_META.EXPIRING_SOON;
  const Icon = meta.icon;

  return (
    <span
      className="status-badge"
      style={{
        color: meta.color,
        background: meta.bg,
      }}
    >
      <Icon size={14} strokeWidth={2.5} />
      {meta.label}
    </span>
  );
}

function DocCard({ icon: Icon, title, number, validTill, status }) {
  return (
    <div className="card">
      <div className="card-head">
        <div className="card-icon">
          <Icon size={18} strokeWidth={2} />
        </div>

        <h3>{title}</h3>
      </div>

      <p className="card-number">{number}</p>

      <p className="card-valid">
        Valid till {validTill}
      </p>

      <StatusBadge status={status} />
    </div>
  );
}

function App() {
  const [vehicleNumber, setVehicleNumber] = useState("");
  const [vehicle, setVehicle] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [selectedImage, setSelectedImage] = useState(null);
  const [imageLoading, setImageLoading] = useState(false);

  const searchVehicle = async () => {
    if (!vehicleNumber.trim()) {
      setVehicle(null);
      setError("Enter a vehicle number to search.");
      return;
    }

    setError("");
    setVehicle(null);
    setLoading(true);

    try {
      const number = vehicleNumber.trim().toUpperCase();

      const response = await fetch(
        `http://localhost:8080/api/vehicles/${number}`
      );

      const data = await response.json();

      if (!response.ok) {
        throw new Error(data.message || "Vehicle not found.");
      }

      setVehicle(data);
    } catch (error) {
      setVehicle(null);
      setError(error.message);
    } finally {
      setLoading(false);
    }
  };

  const handleKeyDown = (event) => {
    if (event.key === "Enter") {
      searchVehicle();
    }
  };

  const searchByImage = async () => {
    if (!selectedImage) {
      setError("Please select a vehicle image.");
      return;
    }

    setError("");
    setVehicle(null);
    setImageLoading(true);

    try {
      const formData = new FormData();
      formData.append("image", selectedImage);

      const response = await fetch(
        "http://localhost:8080/api/ocr/vehicle",
        {
          method: "POST",
          body: formData,
        }
      );

      const data = await response.json();

      if (!response.ok) {
        throw new Error(data.message || "Could not identify vehicle.");
      }

      setVehicle(data);
    } catch (error) {
      setVehicle(null);
      setError(error.message);
    } finally {
      setImageLoading(false);
    }
  };

  return (
    <div className="page">
      <div className="container">

        {/* Header */}
        <div className="header">
          <div className="header-icon">
            <Car size={24} strokeWidth={2} />
          </div>

          <div>
            <h1>Vehicle Compliance</h1>
            <p className="subtitle">
              Check RC, insurance and PUC status in one search.
            </p>
          </div>
        </div>

        {/* Search */}
        <div className="search-box">
          <div className="input-wrap">
            <Search size={18} className="input-icon" />

            <input
              className="vehicle-input"
              type="text"
              placeholder="e.g. KA03EF9012"
              value={vehicleNumber}
              onChange={(event) =>
                setVehicleNumber(event.target.value)
              }
              onKeyDown={handleKeyDown}
            />
          </div>

          <button
            className="search-btn"
            onClick={searchVehicle}
            disabled={loading}
          >
            {loading ? (
              <Loader2
                size={16}
                className="spinner"
              />
            ) : (
              "Search"
            )}
          </button>
        </div>
        
        <div className="or-divider">
          <span></span>
          <div className="or-text">OR</div>
          <span></span>
        </div>

        <div className="image-search">
          <label className="upload-area">
            <input
              type="file"
              accept="image/*"
              onChange={(event) => setSelectedImage(event.target.files[0])}
            />

            <span className="upload-title">
              {selectedImage
                ? selectedImage.name
                : "Upload vehicle image"}
            </span>

            <span className="upload-subtitle">
              Upload a vehicle number plate photo (JPG, PNG or JPEG)
            </span>
          </label>

          <button
            className="search-btn image-btn"
            onClick={searchByImage}
            disabled={imageLoading || !selectedImage}
          >
            {imageLoading ? "Reading plate..." : "Search"}
          </button>
        </div>

        {/* Error */}
        {error && (
          <p className="error">
            {error}
          </p>
        )}

        {/* Empty state */}
        {!vehicle && !loading && !error && (
          <div className="empty-state">
            <Gauge size={28} />

            <p>
              Enter a registration number to see its
              compliance status.
            </p>
          </div>
        )}

        {/* Vehicle result */}
        {vehicle && (
          <>
            <div className="vehicle-card">

              <div className="vehicle-top">
                <h2>{vehicle.vehicleNumber}</h2>
              </div>

              <div className="vehicle-grid">

                <div className="vehicle-field">
                  <User size={15} />
                  <span>{vehicle.ownerName}</span>
                </div>

                <div className="vehicle-field">
                  <Car size={15} />
                  <span>
                    {vehicle.manufacturer} {vehicle.model}
                  </span>
                </div>

                <div className="vehicle-field">
                  <Fuel size={15} />
                  <span>{vehicle.fuelType}</span>
                </div>

                <div className="vehicle-field">
                  <MapPin size={15} />
                  <span>{vehicle.rto}</span>
                </div>

              </div>
            </div>

            {/* Documents */}
            <div className="cards">

              <DocCard
                icon={FileCheck2}
                title="Registration Certificate"
                number={vehicle.rc?.rcNumber}
                validTill={vehicle.rc?.validTill}
                status={vehicle.rc?.status}
              />

              <DocCard
                icon={ShieldCheck}
                title="Insurance"
                number={vehicle.insurance?.policyNumber}
                validTill={vehicle.insurance?.validTill}
                status={vehicle.insurance?.status}
              />

              <DocCard
                icon={Gauge}
                title="Pollution Certificate"
                number={vehicle.puc?.pucNumber}
                validTill={vehicle.puc?.validTill}
                status={vehicle.puc?.status}
              />

            </div>
          </>
        )}

      </div>
    </div>
  );
}

export default App;