import { useState, useEffect, useContext } from "react";
import PropTypes from "prop-types";
import { AuthContext } from "./AuthContext";
import {
  saveLocalStorage,
  getLocalStorage,
} from "../services/localStorageService";
import { usuarios } from "./usuarios";

export const AuthProvider = ({ children }) => {
  // Estado y efecto para el contexto de autenticación
  const [auth, setAuth] = useState(
    JSON.parse(localStorage.getItem("auth")) || {}
  );

  useEffect(() => {
    saveLocalStorage("user", auth);
  }, [auth]);

  useEffect(() => {
    const savedUser = getLocalStorage("user");
    if (savedUser) {
      setAuth(savedUser);
    }
  }, []);

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

  // Valor combinado para el contexto de autenticación
  const contextValue = {
    auth,
    setAuth,
    users,
    updateUserStaff,
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
