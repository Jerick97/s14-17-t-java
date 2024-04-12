import { useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";
import LogoImg from "../../../assets/LogoNoCountry.svg";
import UserInput from "../../../components/UserInput/UserInput";
import PasswordInput from "../../../components/PasswordInput/PasswordInput";
import ButtonPrimary from "../../../components/ButtonPrimary/ButtonPrimary";



function Login() {
  const navigate = useNavigate();
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const users = {
    "admin@gmail.com": { password: "Administrador", isAdmin: true },
    "user@gmail.com": { password: "Usuario123", isAdmin: false },
  };

  const handleLogin = (data) => {
    const { email, password } = data;
    const user = users[email];

    if (!user || user.password !== password) {
      return alert("El correo electrónico o contraseña es incorrecta");
    }

    navigate(user.isAdmin ? "/dashboard" : "/", {
      replace: true,
      state: {
        logged: true,
        admin: user.isAdmin,
      },
    });
  };

  return (
    <div

      className="w-full h-screen text-white"

      style={{
        backgroundColor: "#06071b",
        backgroundImage:
          "radial-gradient(circle farthest-corner at 50% 0%, rgba(29, 144, 252, .29), #06071b)",
      }}
    >


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
          <div className="absolute inset-0 bg-gradient-to-br from-[#091A3A] from-[50%] to-[#0C274F] to-[50%] transform skew-y-45"></div>
          <form
            className="absolute inset-0 flex justify-around items-center flex-col sm:p-8 p-4"
            autoComplete="off"
            onSubmit={handleSubmit(handleLogin)}
          >
            <h4 className="text-white text-2xl text-center font-extrabold uppercase mb-5">
              User Login
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
                      value: 8,
                      message: "Minimum 8 characters",
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
