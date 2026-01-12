import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { getIdeaById } from "../api/ideaApi";
import { VoteIdea } from "../api/voteApi";
import {
  getCommentsByIdea,
  addComment,
  updateComment,
  deleteComment
} from "../api/commentApi";
import { logout } from "../api/authApi";

export default function IdeaDetailPage() {
  const { id } = useParams();
  const navigate = useNavigate();
  const userId = Number(localStorage.getItem("userId"));

  const [idea, setIdea] = useState(null);
  const [comments, setComments] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  const [commentText, setCommentText] = useState("");
  const [editingCommentId, setEditingCommentId] = useState(null);
  const [editCommentText, setEditCommentText] = useState("");

  /* LOAD IDEA + COMMENTS */
  useEffect(() => {
    setLoading(true);
    setError("");

    Promise.all([getIdeaById(id), getCommentsByIdea(id)])
      .then(([ideaRes, commentsRes]) => {
        setIdea(ideaRes.data);
        setComments(commentsRes.data);
      })
      .catch(() => setError("Failed to load idea"))
      .finally(() => setLoading(false));
  }, [id]);

  /* LOGOUT */
  const handleLogout = () => {
    logout();
    navigate("/");
  };

  /* UPVOTE */
  const handleUpvote = () => {
    VoteIdea({ ideaId: id, userId })
      .then(() => {
        setIdea(prev => ({
          ...prev,
          voteCount: prev.voteCount + 1
        }));
      })
      .catch(() => alert("You have already voted"));
  };

  /* ADD COMMENT */
  const handleAddComment = () => {
    if (!commentText.trim()) return;

    addComment({
      ideaId: id,
      userId,
      content: commentText
    })
      .then(() => getCommentsByIdea(id))
      .then(res => {
        setComments(res.data);
        setCommentText("");
      })
      .catch(() => alert("Failed to add comment"));
  };

  /* EDIT COMMENT */
  const startEditComment = (comment) => {
    setEditingCommentId(comment.id);
    setEditCommentText(comment.content);
  };

  const saveEditedComment = (commentId) => {
    updateComment(commentId, userId, { content: editCommentText })
      .then(() => {
        setComments(prev =>
          prev.map(c =>
            c.id === commentId
              ? { ...c, content: editCommentText }
              : c
          )
        );
        setEditingCommentId(null);
      })
      .catch(() => alert("Failed to update comment"));
  };

  /* DELETE COMMENT */
  const handleDeleteComment = (commentId) => {
    deleteComment(commentId)
      .then(() => getCommentsByIdea(id))
      .then(res => setComments(res.data))
      .catch(() => alert("Failed to delete comment"));
  };

  /* STATES */
  if (loading) return <p className="muted">Loading idea...</p>;
  if (error) return <div className="error-box">{error}</div>;
  if (!idea) return <p className="muted">Idea not found.</p>;

  return (
    <div className="ideas-page">

      {/* TOP NAVIGATION */}
      <div className="ideas-header">
        <button
          className="btn"
          onClick={() => navigate("/ideas")}
        >
          ← View all ideas
        </button>

        <button
          className="btn danger"
          onClick={handleLogout}
        >
          Logout
        </button>
      </div>

      {/* IDEA CARD */}
      <div className="card">
        <h2 className="card-title">{idea.title}</h2>
        <p>{idea.description || "No description provided."}</p>

        <p className="muted">
          Status: {idea.status} · Votes: {idea.voteCount}
        </p>

        <div className="actions">
          <button className="btn primary" onClick={handleUpvote}>
            Upvote
          </button>
        </div>
      </div>

      {/* COMMENTS */}
      <div className="card" style={{ marginTop: "20px" }}>
        <h3>Comments</h3>

        {/* ADD COMMENT */}
        <textarea
          rows={3}
          placeholder="Write a thoughtful comment..."
          value={commentText}
          onChange={e => setCommentText(e.target.value)}
        />

        <div className="actions">
          <button
            className="btn primary"
            onClick={handleAddComment}
            disabled={!commentText.trim()}
          >
            Submit
          </button>
        </div>

        {/* COMMENT LIST */}
        <div style={{ marginTop: "20px" }}>
          {comments.length === 0 && (
            <p className="muted">No comments yet. Start the discussion.</p>
          )}

          {comments.map(c => {
            const isOwner = c.userId === userId;

            return (
              <div key={c.id} className="comment-card">
                {editingCommentId === c.id ? (
                  <>
                    <textarea
                      value={editCommentText}
                      onChange={e => setEditCommentText(e.target.value)}
                    />

                    <div className="actions">
                      <button
                        className="btn primary small"
                        onClick={() => saveEditedComment(c.id)}
                      >
                        Save
                      </button>

                      <button
                        className="btn small"
                        onClick={() => setEditingCommentId(null)}
                      >
                        Cancel
                      </button>
                    </div>
                  </>
                ) : (
                  <>
                    <p>{c.content}</p>

                    <small className="muted">
                      By {c.userName} ·{" "}
                      {c.createdAt
                        ? new Date(c.createdAt).toLocaleString()
                        : ""}
                    </small>

                    {isOwner && (
                      <div className="actions" style={{ marginTop: "6px" }}>
                        <button
                          className="btn small"
                          onClick={() => startEditComment(c)}
                        >
                          Edit
                        </button>

                        <button
                          className="btn small danger"
                          onClick={() => handleDeleteComment(c.id)}
                        >
                          Delete
                        </button>
                      </div>
                    )}
                  </>
                )}
              </div>
            );
          })}
        </div>
      </div>
    </div>
  );
}
