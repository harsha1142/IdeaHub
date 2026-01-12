import api from "./axios";

export const getAllIdeas = () => {
  return api.get("/ideas");
};

export const getIdeaById = (id) => {
  return api.get(`/ideas/${id}`);
};

export const createIdea = (data) => {
  return api.post("/ideas", data);
};

export const deleteIdea = (ideaId) => {
  const userId = Number(localStorage.getItem("userId"));
  if (!userId){
    return Promise.reject("User not logged in!");
  }
  return api.delete(`/ideas/${ideaId}`, {
    params: { userId }
  });
};

export const updateIdea = (ideaId, userId, data) => {
  return api.put(`/ideas/${ideaId}?userId=${userId}`, data);
};
