import axiosInstance from "../api/axiosInstance";

const endDateService = {
  date: async () => {
    try {
      const response = await axiosInstance.get("/projects/1/limit-date");
      return response.data;
    } catch (error) {
      console.error(error);
    }
  },
};

export default endDateService;
