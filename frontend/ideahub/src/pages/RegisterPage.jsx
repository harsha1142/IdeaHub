import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { register } from "../api/authApi";

export default function RegisterPage() {
  const navigate = useNavigate();

  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleRegister = () => {
    setError("");

    if (!firstName || !lastName || !email || !password) {
      setError("All fields are required");
      return;
    }

    setLoading(true);

    register({ firstName, lastName, email, password })
      .then((res) => {
        localStorage.setItem("userId", res.data.userId);
        localStorage.setItem("email", res.data.email);
        localStorage.setItem("fullName", res.data.fullName);

        navigate("/ideas");
      })
      .catch((err) => {
        if (err.response?.status === 400) {
          setError("Email already registered");
        } else {
          setError("Registration failed. Please try again.");
        }
      })
      .finally(() => setLoading(false));
  };

  return (
    <div className="auth-page">
      <div className="auth-card">
        <h1>Create your account</h1>
        <p className="muted" style={{ textAlign: "center" }}>
          Join IdeaHub and start sharing ideas
        </p>

        {error && <div className="error-box">{error}</div>}

        {/* FIRST + LAST NAME */}
        <div className="name-row">
          <input
            placeholder="First name"
            value={firstName}
            onChange={(e) => setFirstName(e.target.value)}
          />

          <input
            placeholder="Last name"
            value={lastName}
            onChange={(e) => setLastName(e.target.value)}
          />
        </div>

        <input
          type="email"
          placeholder="Email address"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />

        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />

        <button
          className="btn primary"
          onClick={handleRegister}
          disabled={loading}
        >
          {loading ? "Creating account..." : "Create account"}
        </button>

        <p className="muted" style={{ textAlign: "center", marginTop: "12px" }}>
          Already have an account?
          <button className="link-btn" onClick={() => navigate("/")}>
            Sign in
          </button>
        </p>
      </div>
    </div>
  );
}
