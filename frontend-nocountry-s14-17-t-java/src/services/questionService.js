import axiosInstance from "../api/axiosInstance";

const questionService = {
  users: async () => {
    try {
      const response = await axiosInstance.get("/questions");

      return response.data;
    } catch (error) {
      console.error("Error fetching users:", error);
      throw new Error("Failed to fetch users");
    }
  },
};

export default questionService;
