import { createBrowserRouter } from "react-router-dom";
import Login from "../pages/auth/login/Login";
import { PrivateRoute } from "./PrivateRoute";

import UploadExcel from "../components/UploadExcel/UploadExcel";

import Vote from "../pages/vote/Vote";
import AdminLayout from "../layouts/AdminLayout";
import Dashboard from "../pages/auth/dashboard/Dashboard";


export const router = createBrowserRouter([
  {
    path: "/",
    element: (
      <PrivateRoute>
        <div>Home</div>
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
      <PrivateRoute>
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
        element: <Dashboard />,
      },
    ],

  },
]);

export default router;
