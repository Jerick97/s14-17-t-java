import { createBrowserRouter } from "react-router-dom";
import Login from "../pages/auth/login/Login";
import { PrivateRoute } from "./PrivateRoute";
import UploadExcel from "../components/UploadExcel/UploadExcel";

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
    path: "/login",
    element: <Login />,
  },
  {
    path: "/excel",
    element: <UploadExcel />,
  },
]);

export default router;
