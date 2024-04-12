import React, { useContext, useRef } from "react";
import HeaderHome from "../../components/HeaderHome/HeaderHome";
import Partners from "../../components/Partners/Partners";
import { useAuthContext } from "../../context/AuthProvider";
import { AuthContext } from "../../context/AuthContext";

export default function Home() {
  return <HomeContent />;
}

function HomeContent() {
  const user = "Camilo";
  const { users, updateUserStaff } = useContext(AuthContext);

  const handleUserClick = (index) => {
    updateUserStaff(index);
  };

  return (
    <div className='w-full bg-[#06071B] min-h-screen text-white'>
      <HeaderHome />
      <div className='p-10'>
        <h1 className='text-2xl font-bold text-white'>Bienvenido {user} a</h1>
        <span className='bg-gradient-to-r font-extrabold text-3xl from-[#1d90fc] to-[#0cfca7] inline-block text-transparent bg-clip-text'>
          Team Score
        </span>
        <h3>Tus compañeros de cohorte son:</h3>
        <div className='flex flex-wrap gap-x-10 gap-y-5 mt-5'>
          {users.map((user, index) => (
            <Partners
              key={index}
              name={user.nombres}
              surname={user.apellidos}
              role={user.rol}
              staff={user.staff}
              index={index}
            />
          ))}
        </div>
      </div>
    </div>
  );
}
