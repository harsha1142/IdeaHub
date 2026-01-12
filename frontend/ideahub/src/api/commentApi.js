import api from "./axios";


export const getCommentsByIdea = (ideaId) => {
  return api.get(`/comments/idea/${ideaId}`);
};


export const addComment = (data) => {
  return api.post("/comments", data);
};


export const updateComment = (commentId, userId, data) => {
  return api.put(`/comments/${commentId}?userId=${userId}`, data);
};


export const deleteComment = (commentId) => {
  const userId = Number(localStorage.getItem("userId"));

  if (!userId) {
    return Promise.reject("User not logged in");
  }

  return api.delete(`/comments/${commentId}`, {
    params: { userId }
  });
};
