import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { createIdea } from "../api/ideaApi";

export default function AddIdeaPage() {
  const navigate = useNavigate();

  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const submitIdea = async () => {
    const userId = Number(localStorage.getItem("userId"));
    setError("");

    if (!title.trim()) {
      setError("Title is required");
      return;
    }

    try {
      setLoading(true);

      await createIdea({
        title,
        description,
        userId,
      });

      navigate("/ideas", {
        state: { submitted: true }
      });

    } catch (err) {
      console.error("Failed to create idea", err);
      setError("Idea submission failed. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="page">
      <div className="card" style={{ maxWidth: "600px", margin: "0 auto" }}>
        <h1 className="card-title">Share a new idea</h1>
        <p className="muted">
          Describe your idea clearly so others can understand and vote on it.
        </p>

        {error && <div className="error-box">{error}</div>}

        <input
          placeholder="Idea title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          disabled={loading}
        />

        <textarea
          rows={5}
          placeholder="Describe your idea (optional)"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          disabled={loading}
        />

        <div className="actions">
          <button
            className="btn"
            onClick={() => navigate("/ideas")}
            disabled={loading}
          >
            Cancel
          </button>

          <button
            className="btn primary"
            onClick={submitIdea}
            disabled={loading}
          >
            {loading ? "Submitting..." : "Submit idea"}
          </button>
        </div>
      </div>
    </div>
  );
}
