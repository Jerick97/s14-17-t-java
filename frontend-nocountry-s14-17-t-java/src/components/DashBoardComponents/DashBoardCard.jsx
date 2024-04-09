const DashBoardCard = () => {
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
            4
          </div>
          <div className="daisy-stat-desc text-white lg:text-lg text-sm ">
            Fecha limite 01/06/2024
          </div>
        </div>

        <div className="daisy-stat  border-[1px] border-[#09FCA7] flex-col items-center p-0">
          <div className="daisy-stat-title  text-white lg:text-lg text-sm">
            Total participantes
          </div>
          <div className="daisy-stat-value text-warning lg:text-2xl text-xl">
            13
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
