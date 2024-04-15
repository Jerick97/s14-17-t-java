import axiosInstance from "../api/axiosInstance";

export function initAxiosInterceptors() {
  const token = localStorage.getItem("jwt-token");
  axiosInstance.interceptors.request.use(function (config) {
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  });
  axiosInstance.interceptors.response.use(
    function (response) {
      return response;
    },
    function (error) {
      if (error.response.status === 401) {
        return Promise.reject(error);
      }
    }
  );
}
