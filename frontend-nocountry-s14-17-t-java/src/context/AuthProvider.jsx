import { useState, useEffect, useContext } from "react";
import PropTypes from "prop-types";
import { AuthContext } from "./AuthContext";
import axiosInstance from "../api/axiosInstance";
import usersService from "../services/usersService";

export const AuthProvider = ({ children }) => {
  // Estado y efecto para el contexto de autenticación
  const [auth, setAuth] = useState({});
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [jwt, setJwt] = useState(null);
  const [isLoading, setIsLoading] = useState(true); // Set initial loading state
  const [group, setGroup] = useState(null); //Grupo Seleccionado Actual
  const [groups, setGroups] = useState([]); //Almacenamos todos los grupos disponibles del usuario

  useEffect(() => {
    const storedJwt = localStorage.getItem("jwt-token");
    if (storedJwt) {
      setIsLoggedIn(true);
      setJwt(storedJwt);

      (async () => {
        try {
          const response = await axiosInstance.get("/users/me");

          if (response.status === 200) {
            // Convertir la propiedad operador de número a cadena
            const operadorString = response.data.operador.toString();

            // Crear un nuevo objeto con la propiedad operador convertida a cadena
            const responseDataWithOperadorString = {
              ...response.data,
              operador: operadorString,
            };

            // Establecer el estado con el nuevo objeto modificado
            setAuth(responseDataWithOperadorString);
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
      setGroup(null);
      setGroups([]);
    }
  }, []);

  useEffect(() => {
    const storedGroups = JSON.parse(localStorage.getItem("groups"));
    if (storedGroups !== null && storedGroups.length > 0) {
      setGroups(storedGroups);
    }
  }, [localStorage.getItem("groups")]);

  function login(token) {
    setIsLoggedIn(true);
    setJwt(token);
    localStorage.setItem("jwt-token", token);
  }

  function logout() {
    setIsLoggedIn(false);
    setJwt(null);
    localStorage.removeItem("jwt-token");
    localStorage.removeItem("selectedGroup");
    localStorage.removeItem("groups");
    setAuth({});
    setGroup(null);
    setGroups([]);
  }

  // Estado y efecto para el contexto de usuarios
  const [users, setUsers] = useState([]);
  useEffect(() => {
    const storedUsers = localStorage.getItem("users");

    if (storedUsers) {
      setUsers(JSON.parse(storedUsers)); // Establecer usuarios desde el almacenamiento local al cargar la página
    } else {
      // Si no hay datos en el almacenamiento local, solicita los datos de la API
      usersService
        .users()
        .then((data) => {
          // Agregar el atributo "staff:false" a cada usuario
          const usersWithStaff = data.map((user) => ({
            ...user,
            staff: false, // Inicializar staff en false
          }));
          setUsers(usersWithStaff);
          localStorage.setItem("users", JSON.stringify(usersWithStaff)); // Guardar en el almacenamiento local
        })
        .catch((error) => {
          console.error("Error fetching users:", error);
        })
        .finally(() => {
          setIsLoading(false);
        });
    }
  }, []);

  useEffect(() => {
    //console.log(users); // Este console.log reflejará los datos actualizados después de establecer el estado
  }, [users]);

  // Función para actualizar el estado del usuario en el contexto de usuarios
  const updateUserStaff = (id) => {
    const userIdx = id - 1; // Ajustar el ID al índice del arreglo
    const updatedUsers = [...users];
    const currentStaffStatus = updatedUsers[userIdx].staff;
    updatedUsers[userIdx].staff = !currentStaffStatus;
    setUsers(updatedUsers);
    localStorage.setItem("users", JSON.stringify(updatedUsers)); // Guardar en el almacenamiento local
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
    group,
    setGroup,
    groups,
    setGroups,
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
