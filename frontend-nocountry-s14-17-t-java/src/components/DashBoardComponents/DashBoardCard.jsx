import React from "react";

const DashBoardCard = () => {
  return (
    <div className="w-full h-full flex justify-center items-center  border-[1px] border-[#09FCA7] rounded-[10px]">
      <div className="w-full h-full  daisy-stats shadow  text-center bg-black">
        <div className="daisy-stat flex-col items-center">
          <div className="daisy-stat-title  text-white">
          Nombre del grupo
          </div>
          <div className="daisy-stat-value text-2xl text-warning">S14-17-t-Java</div>
          <div className="daisy-stat-desc text-white">
            Seleccionado Web-App
          </div>
        </div>

        <div className="daisy-stat border-[1px] border-[#09FCA7] flex-col items-center">
          <div className="daisy-stat-title text-white">Usuarios sin votar</div>
          <div className="daisy-stat-value text-secondary">4</div>
          <div className="daisy-stat-desc text-white">
            Fecha limite 01/06/2024
          </div>
        </div>

        <div className="daisy-stat  border-[1px] border-[#09FCA7] flex-col items-center">
          <div className="daisy-stat-title  text-white">
            Total participantes
          </div>
          <div className="daisy-stat-value text-warning">13</div>
          <div className="daisy-stat-desc text-white">
            De la simulacion Fecha Abril
          </div>
        </div>
      </div>
    </div>
  );
};

export default DashBoardCard;
