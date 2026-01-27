import api from "./axios";

export const login = async (data) => {
  const response = await api.post("/auth/login", data);

  console.log("LOGIN API RESPONSE:", response.data);

  localStorage.setItem("token", response.data.token);

  localStorage.setItem("user", JSON.stringify({
    userId: response.data.userId,
    email: response.data.email,
    firstname: response.data.firstname
})
  );
  return response.data;
};


export const register = async (data) => {
  const response = await api.post("/auth/register", data);

  localStorage.setItem("token", response.data.token);

  localStorage.setItem("user", JSON.stringify({
    userId: response.data.userId,
    email: response.data.email,
    firstname: response.data.firstname
  }));

  return response.data;

};
export const logout = () => {
  localStorage.removeItem("userId");
  localStorage.removeItem("token");
};