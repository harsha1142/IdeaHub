import { useEffect, useState } from "react";
import IdeaCard from "../components/IdeaCard";
import { getAllIdeas, deleteIdea, updateIdea } from "../api/ideaApi";
import { VoteIdea } from "../api/voteApi";
import { useLocation, useNavigate } from "react-router-dom";
import { logout } from "../api/authApi";

export default function ViewAllIdeaPage() {
  const [ideas, setIdeas] = useState([]);
  const [editingIdeaId, setEditingIdeaId] = useState(null);
  const [editTitle, setEditTitle] = useState("");
  const [editDescription, setEditDescription] = useState("");
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  const userId = Number(localStorage.getItem("userId"));
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    setLoading(true);
    setError("");

    getAllIdeas()
      .then(res => setIdeas(res.data))
      .catch(() => setError("Failed to load ideas"))
      .finally(() => setLoading(false));
  }, [location.pathname]);

  const handleUpvote = (ideaId) => {
    VoteIdea({ ideaId, userId }).then(() => {
      setIdeas(prev =>
        prev.map(i =>
          i.id === ideaId
            ? { ...i, voteCount: i.voteCount + 1 }
            : i
        )
      );
    });
  };

  const handleDelete = (ideaId) => {
    deleteIdea(ideaId)
      .then(() => getAllIdeas())
      .then(res => setIdeas(res.data))
      .catch(() => {
        alert("Failed to delete idea");
      });
  };

  const handleEdit = (idea) => {
    setEditingIdeaId(idea.id);
    setEditTitle(idea.title);
    setEditDescription(idea.description || "");
  };

  const handleSave = (ideaId) => {
    updateIdea(ideaId, userId, {
      title: editTitle,
      description: editDescription
    }).then(() => {
      setIdeas(prev =>
        prev.map(i =>
          i.id === ideaId
            ? { ...i, title: editTitle, description: editDescription }
            : i
        )
      );
      setEditingIdeaId(null);
    });
  };

  const handleLogout = () => {
    logout();
    navigate("/");
  };

  return (
    <div className="ideas-page">

      {/* HEADER */}
      <div className="ideas-header">
        <div>
          <h1>All Ideas</h1>
          <p className="muted">Explore and share innovative thoughts</p>
        </div>

        <div className="actions">
          <button className="btn primary" onClick={() => navigate("/add")}>
            + Add Idea
          </button>

          <button className="btn danger" onClick={handleLogout}>
            Logout
          </button>
        </div>
      </div>

      {/* CONTENT STATES */}
      {loading && <p className="muted">Loading ideas...</p>}

      {error && <div className="error-box">{error}</div>}

      {!loading && ideas.length === 0 && (
        <p className="muted">
          No ideas yet. Be the first to add one!
        </p>
      )}

      {/* IDEAS GRID */}
      <div className="ideas-grid">
        {ideas.map(idea => (
          <IdeaCard
            key={idea.id}
            idea={idea}
            isEditing={editingIdeaId === idea.id}
            editTitle={editTitle}
            editDescription={editDescription}
            setEditTitle={setEditTitle}
            setEditDescription={setEditDescription}
            onUpvote={() => handleUpvote(idea.id)}
            onEdit={() => handleEdit(idea)}
            onSave={() => handleSave(idea.id)}
            onCancel={() => setEditingIdeaId(null)}
            onDelete={() => handleDelete(idea.id)}
          />
        ))}
      </div>
    </div>
  );
}
