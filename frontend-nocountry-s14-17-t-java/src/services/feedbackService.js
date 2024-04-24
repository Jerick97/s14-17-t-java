import axiosInstance from "../api/axiosInstance";

const feedbackService = {
  feedback: async (feedbackJson) => {
    try {
      const response = await axiosInstance.post("/feedback", feedbackJson);
      return response.data;
    } catch (error) {
      console.error(error);
    }
  },
};

export default feedbackService;
