import axiosInstance from "../api/axiosInstance";

const authService = {
  login: async (email, password) => {
    try {
      const response = await axiosInstance.post("/auth/authenticate", { email, password });
      return response.data;
    } catch (error) {
      console.error(error);
    }
  },
};

export default authService;
