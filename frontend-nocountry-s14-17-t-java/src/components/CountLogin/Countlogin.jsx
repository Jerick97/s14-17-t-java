import { useEffect } from "react";
import { useState } from "react";

function Count() {
  const [startDate] = useState(new Date("2024-04-10"));
  const [endDate] = useState(new Date("2024-05-10"));
  const [currentDate, setCurrentDate] = useState(new Date());

  /* ACTUALIZAMOS LA FECHA ACTUAL (CURRENTDATE) CADA SEGUNDO SETINTERVAL( () => {}, 1000 ) */
  useEffect(() => {
    /* LA FUNCION SETINTERVAL SE EJECUTA CADA SEGUNDO, ACTUALIZANDO EL ESTADO DE CURRENTDATE CON LA FECHA ACTUAL */
    const interval = setInterval(() => {
      setCurrentDate(new Date());
    }, 1000);

    /* LA FUNCION QUE RETORNAMOS SE EJECUTA AL DESMONTAR EL COMPONENTE, POR ESO LIMPIAMOS EL INTERVALO PARA LIBERAR LA MEMORIA */
    return () => clearInterval(interval);
  }, []);

  const calculateDaysRemaining = () => {
    /* UTILIZAMOS "getTime()" PARA OBTENER EL TIEMPO EN MILISEGUNDOS (desde 1/1/70) DE AMBAS FECHAS Y LAS RESTAMOS, NOS DA LA DIFERENCIA ENTRE AMBAS FECHAS */
    const daysDifference = endDate.getTime() - currentDate.getTime();
    /* HACEMOS LA CONVERCION DE MILISEGUNDOS A DIAS, DIVIDIMOS LA DIFERENCIA POR LOS MILISEGUNDOS EN UN DIA Y OBTENEMOS LOS DIAS RESTANTES. UTILIZAMOS "Math.ceil()" PARA REDONDEAR PARA ARRIBA Y DARNOS UN NUMERO ENTERO EN DIAS. */
    const daysRemaining = Math.ceil(daysDifference / (1000 * 3600 * 24));
    return daysRemaining;
  };

  const daysRemainingDate = calculateDaysRemaining();

  /* RENDERIZAMOS CON ".toLocaleDateString()" PARA TRANSFORMAR LAS FECHAS EN CADENAS DE TEXTO Y DE ACUERDO A LA REGION EN LA QUE NOS ENCONTREMOS */
  return (
    <div>
      <h1 className="text-center" style={{ backgroundColor: "#06071b" }}>
        {/* Fecha de inicio de la calificacion a compañeros:{" "}
        <span style={{ color: "#1d90fc" }}>
          {startDate.toLocaleDateString()}
        </span>.
        <br /> */}
        Faltan <span style={{ color: "#1d90fc" }}>{daysRemainingDate}</span>{" "}
        dias para que finalice el periodo de calificacion.{" "}
        {/*<br />
        Fecha de finalizacion de la calificacion:{" "}
        <span style={{ color: "#1d90fc" }}>{endDate.toLocaleDateString()}</span> */}
      </h1>
    </div>
  );
}

export default Count;
