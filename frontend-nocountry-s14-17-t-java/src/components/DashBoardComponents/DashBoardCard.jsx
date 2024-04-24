import { useState, useEffect } from "react";
import endDateService from "../../services/endDateService";

const DashBoardCard = ({ users }) => {
  const [dateEnd, setDateEnd] = useState(null);

  useEffect(() => {
    const endDate = async () => {
      try {
        const response = await endDateService.date();
        setDateEnd(response);
      } catch (error) {
        console.error("Error fetching groups:", error);
      }
    };
    endDate();
  }, []);

  return (
    <div className="w-full  h-full flex justify-center items-center  border-[1px] border-[#09FCA7] rounded-[10px]">
      <div className="w-full h-full  daisy-stats shadow  text-center bg-black">
        <div className="daisy-stat flex-col items-center p-0">
          <div className="daisy-stat-title lg:text-lg text-sm  text-white">
            Nombre del grupo
          </div>
          <div className="daisy-stat-value lg:text-2xl text-lg text-warning">
            S14-17-t-Java
          </div>
          <div className="daisy-stat-desc lg:text-lg text-sm  text-white">
            Seleccionado Web-App
          </div>
        </div>

        <div className="daisy-stat border-[1px] border-[#09FCA7] flex-col items-center p-0">
          <div className="daisy-stat-title text-white lg:text-lg text-sm">
            Usuarios sin votar
          </div>
          <div className="daisy-stat-value text-secondary lg:text-1xl text-2xl">
            {users.filter((user) => user.progress === 0).length}
          </div>
          <div className="daisy-stat-desc text-white lg:text-lg text-sm ">
            Fecha limite {dateEnd}
          </div>
        </div>

        <div className="daisy-stat  border-[1px] border-[#09FCA7] flex-col items-center p-0">
          <div className="daisy-stat-title  text-white lg:text-lg text-sm">
            Total participantes
          </div>
          <div className="daisy-stat-value text-warning lg:text-2xl text-xl">
            {users.length}
          </div>
          <div className="daisy-stat-desc text-white lg:text-lg text-sm">
            De la simulacion Fecha Abril
          </div>
        </div>
      </div>
    </div>
  );
};

export default DashBoardCard;
