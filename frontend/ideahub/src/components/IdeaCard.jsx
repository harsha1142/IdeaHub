import { useNavigate } from "react-router-dom";

export default function IdeaCard({
  idea,
  isEditing,
  editTitle,
  editDescription,
  setEditTitle,
  setEditDescription,
  onUpvote,
  onEdit,
  onSave,
  onCancel,
  onDelete
}) {
  const navigate = useNavigate();

  const userId = Number(localStorage.getItem("userId"));
  const isOwner = userId === idea.createdById;

  return (
    <div className="card">
      {isEditing ? (
        /*  EDIT MODE  */
        <>
          <input
            className="input"
            type="text"
            value={editTitle}
            onChange={e => setEditTitle(e.target.value)}
            placeholder="Idea title"
          />

          <textarea
            className="textarea"
            rows={4}
            value={editDescription}
            onChange={e => setEditDescription(e.target.value)}
            placeholder="Idea description"
          />

          <div className="actions">
            <button className="btn primary" onClick={onSave}>
              Save
            </button>

            <button className="btn" onClick={onCancel}>
              Cancel
            </button>
          </div>
        </>
      ) : (
        /*  VIEW MODE */
        <>
          <h3 className="card-title">{idea.title}</h3>

          <p className="muted">By {idea.createdByName}</p>

          <p className="muted">
            Status: {idea.status}
          </p>

          <p className="muted">
            Votes: {idea.voteCount}
          </p>

          <div className="actions">
            <button
              className="btn"
              onClick={() => navigate(`/ideas/${idea.id}`)}
            >
              View
            </button>

            <button
              className="btn"
              onClick={onUpvote}
            >
              Upvote 
            </button>

            {isOwner && (
              <>
                <button
                  className="btn"
                  onClick={onEdit}
                >
                  Edit
                </button>

                <button
                  className="btn danger"
                  onClick={onDelete}
                >
                  Delete
                </button>
              </>
            )}
          </div>
        </>
      )}
    </div>
  );
}
