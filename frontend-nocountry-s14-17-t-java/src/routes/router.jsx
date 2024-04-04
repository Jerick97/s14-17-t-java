import { createBrowserRouter } from "react-router-dom";
import Login from "../pages/auth/login/Login";
import { PrivateRoute } from "./PrivateRoute";
import Vote from "../pages/vote/Vote";

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
    path: "/vote",
    element: <Vote />,
  },
]);

export default router;
