import api from "./axios";

export const VoteIdea = (data) => {
    return api.post("/votes", data);
};