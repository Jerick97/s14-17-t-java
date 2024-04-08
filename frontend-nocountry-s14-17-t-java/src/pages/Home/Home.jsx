import React from "react";
import HeaderHome from "../../components/HeaderHome/HeaderHome";
import Partners from "../../components/Partners/Partners";

export default function Home() {
  const users = [
    {
      apellidos: "Nicolas Raffagnini",
      nombres: "Nicolas Raffagnini",
      email: "nicolas.raffagnini95@gmail.com",
      emailValido: true,
      rol: "PM",
      staff: true,
    },
    {
      apellidos: "Mc Glory Tovar",
      nombres: "a",
      email: "mcglorytovar@gmail.com",
      emailValido: true,
      rol: "QA",
      staff: true,
    },
    {
      apellidos: "Marcelo Alejandro",
      nombres: "Marcelo Saiz",
      email: "kaosinc@gmail.com",
      emailValido: true,
      rol: "QA",
      staff: true,
    },
    {
      apellidos: "David Gauthier",
      nombres: "David Gauthier",
      email: "deberia dar error al no tener mail",
      emailValido: false,
      rol: "UX/UI",
      staff: false,
    },
    {
      apellidos: "Alejandro Dominguez",
      nombres: "Alejandro Dominguez",
      email: "alejodi@gmail.com",
      emailValido: true,
      rol: "Backend",
      staff: false,
    },
    {
      apellidos: "Ana Merlo",
      nombres: "Ana Merlo",
      email: "anamerlo_me@hotmail.com",
      emailValido: true,
      rol: "Backend",
      staff: false,
    },
    {
      apellidos: "Efrén Morales",
      nombres: "Efrén Morales",
      email: "efren.morales.flores@gmail.com",
      emailValido: true,
      rol: "Backend",
      staff: false,
    },
    {
      apellidos: "Francisco Sierra",
      nombres: "Francisco Sierra",
      email: "fandres6262@gmail.com",
      emailValido: true,
      rol: "Backend",
      staff: false,
    },
    {
      apellidos: "Mario Calderon",
      nombres: "Mario Calderon",
      email: "mario.2893@hotmail.com",
      emailValido: true,
      rol: "Backend",
      staff: false,
    },
    {
      apellidos: "Sturniolo",
      nombres: "Bruno",
      email: "brunosebastian.sturniolo@gmail.com",
      emailValido: true,
      rol: "Backend",
      staff: false,
    },
    {
      apellidos: "Martinez",
      nombres: "Camilo",
      email: "camilom200107@gmail.com",
      emailValido: true,
      rol: "Frontend",
      staff: false,
    },
    {
      apellidos: "Suarez",
      nombres: "Emerson",
      email: "emersonsuarez2904@gmail.com",
      emailValido: true,
      rol: "Frontend",
      staff: false,
    },
    {
      apellidos: "Amicone",
      nombres: "Amicone",
      email: "francoamicone1@gmail.com",
      emailValido: true,
      rol: "Frontend",
      staff: false,
    },
    {
      apellidos: "Ravarotto ",
      nombres: "Germán",
      email: "g44ravarotto@gmail.com",
      emailValido: true,
      rol: "Frontend",
      staff: false,
    },
    {
      apellidos: "Contreras",
      nombres: "Salomón Contreras",
      email: "contrerassalomon6@gmail.com",
      emailValido: true,
      rol: "Frontend",
      staff: false,
    },
    {
      apellidos: "Hurtado Testa",
      nombres: "Alejo Hurtado Testa",
      email: "alejohurtadotesta@gmail.com",
      emailValido: true,
      rol: "Frontend",
      staff: false,
    },
  ];

  const user = "Camilo";
  return (
    <>
      <HeaderHome />
      <div className='p-10'>
        <h1 className='text-2xl font-bold text-white'>Bienvenido {user} a </h1>
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
            />
          ))}
        </div>
      </div>
    </>
  );
}
