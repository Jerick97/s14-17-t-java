import { Navigate } from "react-router-dom";
import PropTypes from "prop-types";
import { useContext } from "react";
import { AuthContext } from "../context/AuthContext";
import Loading from "../components/Loading/Loading";

export const PrivateRoute = ({ allowed, redirectPath, children }) => {
  const { auth, isLoggedIn, isLoading } = useContext(AuthContext);
  console.log(isLoggedIn);
  // Mientras se trae la data del usuario con el token
  if (isLoading) {
    return (
      <div className="w-full h-screen flex items-center justify-center">
        <Loading />
      </div>
    );
  }

  if (!isLoggedIn) {
    return <Navigate to="/login" replace />; // Redirecciona al login si no inicia sesión
  }

  if (auth.role !== allowed) {
    return <Navigate to={redirectPath} replace />; // Redirecciona sino si no tiene el rol permitido
  }

  return children;
};

PrivateRoute.propTypes = {
  allowed: PropTypes.string.isRequired,
  children: PropTypes.node.isRequired,
  redirectPath: PropTypes.string,
};
