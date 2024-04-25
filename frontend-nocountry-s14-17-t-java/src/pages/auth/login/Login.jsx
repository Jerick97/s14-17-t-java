import { useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";
import LogoImg from "../../../assets/LogoNoCountry.svg";
import UserInput from "../../../components/UserInput/UserInput";
import PasswordInput from "../../../components/PasswordInput/PasswordInput";
import ButtonPrimary from "../../../components/ButtonPrimary/ButtonPrimary";
import { useContext } from "react";
import { AuthContext } from "../../../context/AuthContext";
import authService from "../../../services/authService";
import Swal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";
import Count from "../../../components/CountLogin/Countlogin";

function Login() {
  const MySwal = withReactContent(Swal);
  const navigate = useNavigate();
  const { setAuth, login } = useContext(AuthContext);
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  /* const users = {
    "mikhail@gmail.com": {
      password: "12345678",
      operador: "0",
      name: "Mikhail",
    },
    "vero@gmail.com": {
      password: "12345678",
      operador: "1",
      name: "Vero",
    },
    "romina@gmail.com": {
      password: "12345678",
      operador: "1",
      name: "Romina",
    },
  }; */

  const handleLogin = async (data) => {
    const { email, password } = data;
    /* const user = users[email]; */

    try {
      // Llama al método login del servicio de autenticación para iniciar sesión
      const response = await authService.login(email, password);

      // Extrae el token del objeto de respuesta
      const { token, groups, operador } = response;
      // Guarda el token en el localStorage
      localStorage.setItem("jwt-token", token);
      localStorage.setItem("groups", JSON.stringify(groups));
      //Almacenamos en el contexto los datos del usuario
      setAuth({
        email,
        operador: operador, //admin es 1 user es 0
        ...response,
      });

      login(token);
      navigate(operador == "1" ? "/dashboard" : "/");
    } catch (error) {
      // Manejo de errores en caso de que falle el inicio de sesión
      MySwal.fire({
        icon: "error",
        title: "Error",
        text: "El correo electrónico o la contraseña son incorrectos",
      });
    }
  };

  return (
    <div
      className="w-full h-screen text-white overflow-hidden"
      style={{
        backgroundColor: "#06071b",
        backgroundImage:
          "radial-gradient(circle farthest-corner at 50% 0%, rgba(29, 144, 252, .29), #06071b)",
      }}
    >
      <Count />
      <div className="container mx-auto px-4 sm:px-6 lg:px-8 max-w-screen-lg h-full flex items-center justify-center md:flex-row flex-col">
        <div className="w-full flex items-center justify-center flex-col">
          <img src={LogoImg} alt="logo" className="w-80" />
          <h1 className="my-4 md:text-6xl text-3xl text-white font-medium text-center">
            Feedback{" "}
            <span className="bg-gradient-to-r from-[#1d90fc] to-[#0cfca7] inline-block text-transparent bg-clip-text">
              a tus compañeros
            </span>
          </h1>
        </div>
        <div className="h-96 w-full max-w-sm relative overflow-hidden mt-6 z-10 rounded-2xl">
          <div className="absolute inset-0 bg-gradient-to-b from-blue-400/10 to-#06071B	 transform skew-y-45"></div>
          <form
            className="absolute inset-0 flex justify-around items-center flex-col sm:p-8 p-4"
            autoComplete="off"
            onSubmit={handleSubmit(handleLogin)}
          >
            <h4 className="text-white text-2xl text-center font-extrabold uppercase mb-5">
              Inicia Sesion
            </h4>
            <div className="my-2 w-full">
              <div className="mb-4">
                <UserInput
                  {...register("email", {
                    required: {
                      value: true,
                      message: "Email is required",
                    },
                    pattern: {
                      value: /^[\w-ñÑ]+(\.[\w-ñÑ]+)*@[\w-ñÑ]+(\.[\w-ñÑ]+)+$/,
                      message: "Please enter a valid email",
                    },
                  })}
                  name="email"
                  placeholder="Email"
                  customStyle={
                    errors.email
                      ? "border-2 border-solid border-red-500 placeholder:text-red-400 text-red-400"
                      : "text-[#acacb5]"
                  }
                />
                {errors.email && (
                  <p className={`ml-12 text-red-500 text-sm`}>
                    {errors.email?.message}
                  </p>
                )}
              </div>
              <div className="mb-4">
                <PasswordInput
                  {...register("password", {
                    required: {
                      value: true,
                      message: "Password is required",
                    },
                    minLength: {
                      value: 6,
                      message: "Minimum 6 characters",
                    },
                  })}
                  name="password"
                  placeholder="Password"
                  customStyle={
                    errors.password
                      ? "border-2 border-solid border-red-500 placeholder:text-red-400 text-red-400"
                      : "text-[#acacb5]"
                  }
                />
                {errors.password && (
                  <p className={`ml-12 text-red-500 text-sm`}>
                    {errors.password?.message}
                  </p>
                )}
              </div>
            </div>
            <ButtonPrimary text="Login" type="submit" />
          </form>
        </div>
      </div>
    </div>
  );
}

export default Login;
