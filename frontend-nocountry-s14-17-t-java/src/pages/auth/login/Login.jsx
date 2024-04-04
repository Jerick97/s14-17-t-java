import { useNavigate } from "react-router-dom";
import LogoImg from "../../../assets/Logo.svg";
import UserInput from "../../../components/UserInput/UserInput";
import PasswordInput from "../../../components/PasswordInput/PasswordInput";
import ButtonPrimary from "../../../components/ButtonPrimary/ButtonPrimary";

function Login() {
  const navigate = useNavigate();
  const handleLogin = () => {
    // Falta capturar el usuario y contraseña
    navigate("/", {
      replace: true,
      state: {
        logged: true,
      },
    });
  };

  return (
    <div className="w-full bg-[#06071B] h-screen text-white">
      <div className="container mx-auto px-4 sm:px-6 lg:px-8 max-w-screen-lg h-full flex items-center justify-center flex-col">
        <img src={LogoImg} alt="logo" className="w-80" />
        <h1 className="my-4 md:text-5xl text-3xl text-white font-medium text-center">
          Feedback a tus compañeros
        </h1>
        <div className="h-96 w-full max-w-sm relative overflow-hidden mt-6 z-10">
          <div className="absolute inset-0 bg-gradient-to-br from-[#00292D] from-[50%] to-[#01212E] to-[50%] transform skew-y-45"></div>
          <form
            className="absolute inset-0 flex justify-around items-center flex-col sm:p-8 p-4"
            autoComplete="off"
          >
            <h4 className="text-white text-2xl text-center font-extrabold uppercase mb-5">
              User Login
            </h4>
            <div className="my-2 w-full">
              <div className="mb-4">
                <UserInput />
              </div>
              <div className="mb-4">
                <PasswordInput />
              </div>
            </div>
            <ButtonPrimary text="Login" onClick={handleLogin} />
          </form>
        </div>
      </div>
    </div>
  );
}

export default Login;
