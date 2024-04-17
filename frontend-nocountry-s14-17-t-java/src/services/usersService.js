import axiosInstance from "../api/axiosInstance";

const usersService = {
  users: async () => {
    try {
      const response = await axiosInstance.get("/");

      return response.data;
    } catch (error) {
      console.error("Error fetching users:", error);
      throw new Error("Failed to fetch users");
    }
  },
};

export default usersService;
