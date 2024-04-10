import flags from "../../data/flags.json";
import users from "../../data/users.json";
const DashboarTable = () => {


  const rolColors = {
    PM: "text-[#DB540D]",
    QA: "text-[#DB2D4B]",
    "UX/UI": "text-[#3DDB0D]",
    Backend: "text-[#400DDB]",
    Frontend: "text-[#830DDB]",
  };


  console.log(flags);
  return (
    <div className=" min-w-full bg-black text-white  ">
      <div className="overflow-x-auto">
        <table className="daisy-table table-auto">
          {/* head */}
          <thead>
            <tr className="  ">
              <th className="text-white font-bold md:text-lg text-[8px] border-[1px] border-[#09FCA7] ">
                Nombre y apellido
              </th>
              <th className="text-white font-bold md:text-lg text-[8px] border-[1px] border-[#09FCA7] ">
                Pais
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
            {/* Fila*/}
            {users.map((user, index) => (
              <tr key={index} className=" border-[1px] border-[#09FCA7]  ">
                <td>
                  <div className="flex items-center gap-3">
                    <div>
                      <div className="font-bold md:text-lg text-[10px] flex gap-12">
                        {user.nombres} {user.apellidos}
                      </div>
                      <div className="text-sm opacity-50 md:text-lg text-[8px]">
                        {user.email}
                      </div>
                    </div>
                  </div>
                </td>
                <td>
                  <div className="w-8 h-8"><img src={flags[user.country]} alt="Pais" /></div>
                </td>
                <td>
                  <div
                    className={`font-bold md:text-lg text-[10px] ${
                      rolColors[user.rol]
                    }`}
                  >
                    {user.rol}
                  </div>
                </td>
                <td>
                  {user.state ? (
                    <div className="daisy-badge daisy-badge-success badge-outline w-auto  whitespace-nowrap md:text-lg text-[7px]">
                      Activo
                    </div>
                  ) : (
                    <div className="daisy-badge daisy-badge-error badge-outline w-auto  whitespace-nowrap md:text-lg text-[7px]">
                      Inactivo
                    </div>
                  )}
                </td>
                <th>
                  <div className="flex items-center gap-3">
                    <progress
                      className="lg:block hidden daisy-progress daisy-progress-warning w-56"
                      value={user.progress}
                      max="100"
                    ></progress>
                    <span>{user.progress}%</span>
                  </div>
                </th>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default DashboarTable;
