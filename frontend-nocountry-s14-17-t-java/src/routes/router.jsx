import { createBrowserRouter } from "react-router-dom";
import Login from "../pages/auth/login/Login";
import { PrivateRoute } from "./PrivateRoute";
import UploadExcel from "../components/UploadExcel/UploadExcel";
import Vote from "../pages/vote/Vote";
import AdminLayout from "../layouts/AdminLayout";
import Dashboard from "../pages/auth/dashboard/Dashboard";
import Home from "../pages/main/Home";

export const router = createBrowserRouter([
  {
    path: "/",
    element: (
      <PrivateRoute allowed="user" redirectPath="/dashboard">
        {/* Necesita Iniciar Sesión como user para acceder*/}
        <Home />
      </PrivateRoute>
    ),
  },
  {
    path: "/excel",
    element: <UploadExcel />,
  },
  {
    path: "/login",
    element: <Login />,
  },
  {
    path: "/vote",
    element: (
      <PrivateRoute allowed="user" redirectPath="/dashboard">
        {/* Necesita Iniciar Sesión como user para acceder*/}
        <Vote />
      </PrivateRoute>
    ),
  },
  {
    path: "/dashboard",
    element: <AdminLayout />,
    children: [
      {
        index: true,
        element: (
          <PrivateRoute allowed="admin" redirectPath="/">
            {/* Necesita Iniciar Sesión como admin para acceder*/}
            <Dashboard />
          </PrivateRoute>
        ),
      },
    ],
  },
]);

export default router;
