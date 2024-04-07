const DashboarTable = () => {
  return (
    <div className="w-full bg-black text-white ">
      <div className="overflow-x-auto">
        <table className="daisy-table table-auto">
          {/* head */}
          <thead>
            <tr className="  ">
              <th className="text-white font-bold md:text-lg text-[8px] border-[1px] border-[#09FCA7] ">
                Nombre y apellido
              </th>
              <th className="text-white font-bold  md:text-lg text-[8px] border-[1px] border-[#09FCA7]">
                Rol
              </th>
              <th className="text-white font-bold  md:text-lg text-[8px] border-[1px] border-[#09FCA7]">
                Estado votacion
              </th>
              <th className="text-white font-bold  md:text-lg text-[8px] border-[1px] border-[#09FCA7]">
                Progreso
              </th>
            </tr>
          </thead>
          <tbody>
            {/* row 1 */}
            <tr className=" border-[1px] border-[#09FCA7] ">
              <td>
                <div className="flex items-center gap-3">
                  <div>
                    <div className="font-bold md:text-lg text-[10px]">
                      German Ravarotto
                    </div>
                    <div className="text-sm opacity-50 md:text-lg text-[8px]">
                      Argentina
                    </div>
                  </div>
                </div>
              </td>
              <td>
                <div className="w-auto daisy-badge daisy-badge-success  whitespace-nowrap md:text-lg text-xs">
                  Back-End
                </div>
              </td>
              <td>
                <div className="daisy-badge daisy-badge-error badge-outline w-auto  whitespace-nowrap md:text-lg text-[7px]">
                  No Realizada
                </div>
              </td>
              <th>
                <div className="flex items-center gap-3">
                  <progress
                    className="md:block hidden daisy-progress daisy-progress-warning w-56"
                    value="80"
                    max="100"
                  ></progress>
                  <span>80%</span>
                </div>
              </th>
            </tr>
            {/* row 2 */}

            <tr className=" border-[1px] border-[#09FCA7]  ">
              <td>
                <div className="flex items-center gap-3">
                  <div>
                    <div className="font-bold md:text-lg text-[10px]">
                      Pepe Argento
                    </div>
                    <div className="text-sm opacity-50 md:text-lg text-[8px]">
                      Argentina
                    </div>
                  </div>
                </div>
              </td>
              <td>
                <div className="w-auto daisy-badge daisy-badge-success  whitespace-nowrap md:text-lg text-xs">
                  Front-End
                </div>
              </td>
              <td>
                <div className="daisy-badge daisy-badge-error badge-outline w-auto  whitespace-nowrap md:text-lg text-[7px]">
                  Realizada
                </div>
              </td>
              <th>
                <div className="flex items-center gap-3">
                  <progress
                    className="md:block hidden daisy-progress daisy-progress-warning w-56"
                    value="100"
                    max="100"
                  ></progress>
                  <span>100%</span>
                </div>
              </th>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default DashboarTable;
