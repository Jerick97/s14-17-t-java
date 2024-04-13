import React, { useContext } from "react";
import { Link, useLocation } from "react-router-dom";
import TittleGradient from "../../components/TittleGradient/TittleGradient";
import { AuthContext } from "../../context/AuthContext";
import question from "../../data/question.json";
import QuestionCard from "../../components/QuestionCard/QuestionCard";


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


  return (
    <div className='w-full h-screen flex items-center justify-center bg-[#06071B] flex-col'> 
      <TittleGradient user={auth.name} voting={userVoting} />
      <div className='flex flex-col'> 
        {question.map((pregunta, index) => (
          <QuestionCard key={index}>
            <h3 className='text-2xl text-center font-medium mb-2 text-white'>
              Pregunta {index + 1}:
            </h3>
            <h1 className='text-3xl text-center font-bold text-white'>
              {pregunta}
            </h1>
          </QuestionCard>
        ))}
      </div>
  
      <Link to={"/"}>
        <button className='bg-white hover:bg-gray-100 text-gray-800 font-semibold py-2 px-4 border border-gray-400 rounded shadow' onClick={handleSubmit}>
          Enviar
        </button>
      </Link>
    </div>
  );


};

export default Vote;
