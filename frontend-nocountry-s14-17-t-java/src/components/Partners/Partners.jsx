import { Link } from "react-router-dom";
import check from "@assets/check.svg";
export default function Partners({ id, name, surname, role, staff, activo, idUser }) {
  const fullName = `${name} ${surname}`;
  // Determinar la clase de borde basada en las condiciones
  const state = "A" == activo;

  const getBorderClass = () => {
    if (!staff && state) {
      return "border-none"; // Clase para estado inicial
    } else if (!state && staff) {
      return "border-[#A8A8A8]"; // Clase para estado activo
    } else if (state) {
      return "border-[#0CFCA7]";
    }
  };
  // Obtener la clase de borde
  const borderClass = getBorderClass();
  return (
    <Link
      to={`/vote?user=${encodeURIComponent(
        fullName
      )}&index=${id}&idUser=${idUser}`}
    >
      <button
        className={`${
          state ? "cursor-pointer" : "cursor-not-allowed opacity-50"
        } flex flex-col justify-start hover:scale-105 border-2 pb-8 relative rounded-lg p-3 w-80 bg-gradient-to-b from-blue-400/10 to-[#06071B] ${borderClass}`}
        disabled={!state}
      >
        <h3 className="text-xl font-bold overflow-hidden whitespace-nowrap overflow-ellipsis">
          {name} {surname}
        </h3>
        <p className="text-lg italic">{role}</p>
        {staff && (
          <img
            className="absolute bottom-3 right-3 h-8 w-8"
            src={check}
            alt=""
          />
        )}
      </button>
    </Link>
  );
}
