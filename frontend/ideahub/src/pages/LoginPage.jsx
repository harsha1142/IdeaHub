import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { login } from "../api/authApi";

export default function LoginPage() {
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleLogin = () => {
    setError("");

    if (!email || !password) {
      setError("Email and password are required");
      return;
    }

    setLoading(true);

    login({ email, password })
      .then((res) => {
        console.log("LOGIN PAGE RECIVED:", res);

        if(!res || !res.token){
          throw new Error("Invalid login response");
        }
        //jwt 
        localStorage.setItem("token", res.token);
        localStorage.setItem("userId", res.userId);
        localStorage.setItem("email", res.email);
        localStorage.setItem("username", res.username);

        //login
        navigate("/ideas");
      })
      .catch((err) => {
        console.log("LOGIN ERROR:", err);
        if (err.response?.status === 401) {
          setError("Invalid email or password");
        } else {
          setError("Something went wrong. Please try again.");
        }
      })
      .finally(() => {
        setLoading(false);
      });
  };

  return (
    <div className="auth-page">
      <div className="auth-card">
        <h1>Welcome</h1>
        <p className="muted" style={{ textAlign: "center" }}>
          Sign in to continue to IdeaHub
        </p>

        {error && (
          <div className="error-box">
            {error}
          </div>
        )}

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
          onClick={handleLogin}
          disabled={loading}
        >
          {loading ? "Logging in..." : "Login"}
        </button>

        <p className="muted" style={{ textAlign: "center", marginTop: "12px" }}>
          Don’t have an account?
          <button
            className="link-btn"
            onClick={() => navigate("/register")}
          >
            Register
          </button>
        </p>
      </div>
    </div>
  );
}
