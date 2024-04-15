import { useContext } from "react";
import { AuthContext } from "../context/AuthContext";
import { useLocalStorage } from "./useLocalStorage";

export const useUser = () => {
  const { auth, setAuth } = useContext(AuthContext);
  const { setItem } = useLocalStorage();

  const addUser = (user) => {
    setAuth(user);
    setItem("user", JSON.stringify(user));
  };

  const removeUser = () => {
    setAuth(null);
    setItem("user", "");
  };

  return { auth, addUser, removeUser, setAuth };
};
