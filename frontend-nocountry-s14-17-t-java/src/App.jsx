import { RouterProvider } from "react-router-dom";
import router from "./routes/router.jsx";
import { initAxiosInterceptors } from "./helpers/auth-helpers.js";
import { AuthProvider } from "./context/AuthProvider.jsx";

initAxiosInterceptors();

function App() {
  return (
    <AuthProvider>
      <RouterProvider router={router} />
    </AuthProvider>
  );
}

export default App;
