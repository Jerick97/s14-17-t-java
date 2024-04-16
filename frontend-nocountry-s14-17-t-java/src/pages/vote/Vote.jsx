import React, { useContext } from "react";
import { Link, useLocation } from "react-router-dom";
import TittleGradient from "../../components/TittleGradient/TittleGradient";
import { AuthContext } from "../../context/AuthContext";

const Vote = () => {
  const { auth, updateUserStaff } = useContext(AuthContext);

  // Obtener la ubicación actual

  const location = useLocation();
  // Obtener el parámetro 'user' de la URL
  const userVoting = new URLSearchParams(location.search).get("user");
  const index = new URLSearchParams(location.search).get("index");

  const handleSubmit = () => {
    // Lógica para actualizar el staff del socio correspondiente (usando el índice)
    updateUserStaff(index);
    // Navegar de regreso a la página de inicio
  };

  const preguntas = ["¿Se sienten seguros y apoyados por sus compañeros?"];

  return (
    <div className='w-full h-screen flex flex-col items-center justify-center bg-[#06071B]'>
      <TittleGradient user={auth.name} voting={userVoting} />
      <div className='w-1200 h-350 rounded-3xl bg-gradient-to-b from-blue-400 to-#06071B opacity-30 p-20 flex-col flex mt-40'>
        {preguntas.map((pregunta, index) => (
          <div key={index} className=''>
            <h3 className='text-2xl text-center font-medium mb-2 text-white'>
              Pregunta {index + 1}:
            </h3>
            <h1 className='text-3xl text-center font-bold text-white'>
              {pregunta}
            </h1>
          </div>
        ))}
      </div>
      <Link to={"/"}>
        <button className='bg-white text-black' onClick={handleSubmit}>
          Submit
        </button>
      </Link>
    </div>
  );
};

export default Vote;
