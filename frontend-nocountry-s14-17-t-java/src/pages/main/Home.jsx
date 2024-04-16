import { useContext, useEffect, useState } from "react";
import HeaderHome from "../../components/HeaderHome/HeaderHome";
import Partners from "../../components/Partners/Partners";
import { AuthContext } from "../../context/AuthContext";

export default function Home() {
  return <HomeContent />;
}

function HomeContent() {
  const { users } = useContext(AuthContext);
  // Usar los datos iniciales si están disponibles, de lo contrario, inicializar como un array vacío

  const user = "Camilo";

  const resetData = () => {
    localStorage.removeItem("users");
    window.location.reload();
  };

  return (
    <div className='w-full bg-[#06071B] min-h-screen text-white'>
      <HeaderHome />
      <div className='p-10'>
        <div className='flex items-center'>
          <div>
            <h1 className='text-2xl font-bold text-white'>
              Bienvenido {user} a
            </h1>
            <span className='bg-gradient-to-r font-extrabold text-3xl from-[#1d90fc] to-[#0cfca7] inline-block text-transparent bg-clip-text'>
              Team Score
            </span>
            <h3>Tus compañeros de cohorte son:</h3>
          </div>
          <button
            onClick={resetData}
            className='bg-gray-600 ml-32 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline'
          >
            Restablecer Datos
          </button>
        </div>
        <div className='flex flex-wrap gap-x-10 gap-y-5 mt-5'>
          {users.map((user, index) => (
            <Partners
              key={index}
              name={user.nombres}
              surname={user.apellidos}
              role={user.rol}
              staff={user.staff}
              index={index}
              activo={user.state}
            />
          ))}
        </div>
      </div>
    </div>
  );
}
