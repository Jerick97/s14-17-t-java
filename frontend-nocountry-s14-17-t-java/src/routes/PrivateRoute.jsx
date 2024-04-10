import { Navigate, Outlet } from "react-router-dom";
import PropTypes from "prop-types";
import { useContext } from "react";
import { AuthContext } from "../context/AuthContext";

const isObjectEmpty = (objectName) => {
  return Object.keys(objectName).length === 0;
};

export const PrivateRoute = ({ allowed, redirectPath, children }) => {
  const { auth } = useContext(AuthContext);
  if (isObjectEmpty(auth)) {
    return <Navigate to="/login" replace />; // Redirecciona al login si no inicia sesión
  }

  if (auth.roles !== allowed) {
    return <Navigate to={redirectPath} replace />; // Redirecciona sino si no tiene el rol permitido
  }

  return children ? children : <Outlet />;
};

PrivateRoute.propTypes = {
  allowed: PropTypes.string.isRequired,
  children: PropTypes.node.isRequired,
  redirectPath: PropTypes.string,
};
