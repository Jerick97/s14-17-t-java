import axiosInstance from "../api/axiosInstance";

const groupsService = {
  member: async (id) => {
    try {
      const response = await axiosInstance.get(`/users/usersInGroup/${id}`);

      return response.data;
    } catch (error) {
      console.error("Error fetching groups:", error);
      throw new Error("Failed to fetch groups");
    }
  },
};

export default groupsService;
