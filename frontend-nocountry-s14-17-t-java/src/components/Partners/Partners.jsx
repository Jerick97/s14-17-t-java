import React, { useContext } from "react";
import { Link } from "react-router-dom";
import check from "@assets/check.svg";
export default function Partners({
  name,
  surname,
  role,
  staff,
  index,
  activo,
}) {
  console.log(activo);
  const fullName = `${name} ${surname}`;
  return (
    <Link to={`/vote?user=${encodeURIComponent(fullName)}&index=${index}`}>
      <button
        className={`${staff ? "border-[#0CFCA7]" : "border-[#1D90FC]"} ${
          activo ? "cursor-pointer" : "cursor-not-allowed opacity-50"
        } flex flex-col justify-start hover:scale-105 border-2 pb-8 relative rounded-lg p-3 w-80`}
        disabled={!activo}
      >
        <h3 className='text-xl font-bold overflow-hidden whitespace-nowrap overflow-ellipsis'>
          {name} {surname}
        </h3>
        <p className='text-lg italic'>{role}</p>
        {staff && (
          <img className='absolute bottom-1 right-1' src={check} alt='' />
        )}
      </button>
    </Link>
  );
}
