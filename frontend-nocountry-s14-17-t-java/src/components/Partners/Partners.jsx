import React from "react";
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

  const fullName = `${name} ${surname}`;
  return (
    <Link to={`/vote?user=${encodeURIComponent(fullName)}&index=${index}`}>
      <button
        className={`${staff ? "border-[#0CFCA7]" : "border-none"} ${
          activo ? "cursor-pointer" : "cursor-not-allowed opacity-50"
        } flex flex-col justify-start hover:scale-105 border-2 pb-8 relative rounded-lg p-3 w-80 bg-gradient-to-b from-blue-400/10 to-#06071B	`}
        disabled={!activo}
      >
        <h3 className='text-xl font-bold overflow-hidden whitespace-nowrap overflow-ellipsis'>
          {name} {surname}
        </h3>
        <p className='text-lg italic'>{role}</p>
        {staff && (
          <img className='absolute bottom-3 right-3 h-8 w-8' src={check} alt='' />
        )}
      </button>
    </Link>
  );
}
