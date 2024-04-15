import { useState, useEffect, useContext } from "react";
import PropTypes from "prop-types";
import { AuthContext } from "./AuthContext";
import { usuarios } from "./usuarios";
import axiosInstance from "../api/axiosInstance";
export const AuthProvider = ({ children }) => {
  // Estado y efecto para el contexto de autenticación
  const [auth, setAuth] = useState({});
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [jwt, setJwt] = useState(null);
  const [isLoading, setIsLoading] = useState(true); // Set initial loading state

  useEffect(() => {
    const storedJwt = localStorage.getItem("jwt-token");
    if (storedJwt) {
      setIsLoggedIn(true);
      setJwt(storedJwt);

      (async () => {
        try {
          const response = await axiosInstance.get("/user"); // Replace with your user data endpoint

          if (response.status === 200) {
            // Handle successful response
            setAuth(response.data);
            setIsLoading(false);
          } else {
            console.error(
              "Error fetching user data:",
              response.status,
              response.data
            );
          }
        } catch (error) {
          console.error("Error fetching user data:", error);
        } finally {
          setIsLoading(false); // Always set loading to false after fetching
        }
      })();
    } else {
      setIsLoggedIn(false);
      setIsLoading(false);
      setAuth({}); // Set auth to empty object if no token
    }
  }, []);

  function login(token) {
    setIsLoggedIn(true);
    setJwt(token);
    localStorage.setItem("jwt-token", token);
  }

  function logout() {
    setIsLoggedIn(false);
    setJwt(null);
    localStorage.removeItem("jwt-token");
    setAuth({});
  }

  // Estado y efecto para el contexto de usuarios
  const [users, setUsers] = useState(() => {
    const storedUsers = localStorage.getItem("users");
    return storedUsers ? JSON.parse(storedUsers) : usuarios;
  });

  useEffect(() => {
    localStorage.setItem("users", JSON.stringify(users));
  }, [users]);

  // Función para actualizar el estado del usuario en el contexto de usuarios
  const updateUserStaff = (index) => {
    const updatedUsers = [...users];
    const currentStaffStatus = updatedUsers[index].staff;
    updatedUsers[index].staff = !currentStaffStatus;
    setUsers(updatedUsers);
  };

  const contextValue = {
    auth,
    setAuth,
    users,
    updateUserStaff,
    isLoggedIn,
    isLoading,
    login,
    logout,
    jwt,
  };

  return (
    <AuthContext.Provider value={contextValue}>{children}</AuthContext.Provider>
  );
};

AuthProvider.propTypes = {
  children: PropTypes.node.isRequired,
};

// Hook para usar el contexto de autenticación
export const useAuthContext = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error(
      "useAuthContext debe ser utilizado dentro de un AuthProvider"
    );
  }
  return context;
};
