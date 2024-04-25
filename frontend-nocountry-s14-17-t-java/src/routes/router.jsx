import { createBrowserRouter } from "react-router-dom";
import Login from "../pages/auth/login/Login";
import { PrivateRoute } from "./PrivateRoute";
import UploadExcel from "../components/UploadExcel/UploadExcel";
import Vote from "../pages/vote/Vote";
import AdminLayout from "../layouts/AdminLayout";
import Dashboard from "../pages/auth/dashboard/Dashboard";
import Home from "../pages/main/Home";
import Error from "../pages/error/Error";
import HomeLayout from "../layouts/HomeLayout";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <HomeLayout />,
    children: [
      {
        index: true,
        element: (
          <PrivateRoute allowed="0" redirectPath="/dashboard">
            {/* Necesita Iniciar Sesión como user para acceder*/}
            <Home />
          </PrivateRoute>
        ),
      },
      {
        path: "excel",
        element: <UploadExcel />,
      },
    ],
    errorElement: <Error />,
  },
  {
    path: "/login",
    element: <Login />,
  },
  {
    path: "/vote",
    element: (
      <PrivateRoute allowed="0" redirectPath="/dashboard">
        {/* Necesita Iniciar Sesión como user para acceder*/}
        <Vote />
      </PrivateRoute>
    ),
    // errorElement:<h1>error</h1>,
  },
  {
    path: "/dashboard",
    element: <AdminLayout />,
    children: [
      {
        index: true,
        element: (
          <PrivateRoute allowed="1" redirectPath="/">
            {/* Necesita Iniciar Sesión como admin para acceder*/}
            <Dashboard />
          </PrivateRoute>
        ),
      },
      {
        path: "excel",
        element: <UploadExcel />,
      },
    ],
  },
]);

export default router;
