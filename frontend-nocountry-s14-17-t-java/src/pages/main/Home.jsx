import { useContext } from "react";
import Partners from "../../components/Partners/Partners";
import { AuthContext } from "../../context/AuthContext";
import UserGroups from "../../components/UserGroups/UserGroups";
import ButtonNeon from "../../components/ButtonNeon/ButtonNeon";
import HomeCard from "../../components/HomeCard/HomeCard";

export default function Home() {
  return <HomeContent />;
}

function HomeContent() {
  const { users } = useContext(AuthContext);

  const user = "Camilo";
  const groups = [
    { id: "1", name: "S14-17-t-Java" },
    // { id: "2", name: "S14-Java-tt" },
    // { id: "3", name: "S14-Node-tt" },
    //Si existen más de 1 grupo te pedira seleccionar tu grupo
  ];

  const resetData = () => {
    localStorage.removeItem("users");
    window.location.reload();
  };

  // Metodo elimina el propio usuario
  const usersFiltered = users.filter((usuario) => usuario.nombres !== user);
  // Metodo elimina el usuario que no participo
  const userDisabled = usersFiltered.filter((user) => user.state !== false);
  // Nuevo Array de usuarios sin el usuario que no participo
  const usersTotalVote = usersFiltered.filter(
    (user) => userDisabled.includes(user) && user.staff !== true
  );

  const usersVoted = usersFiltered.filter((user) => user.staff === true);

  return (
    <div className="w-full bg-[#06071B] min-h-screen text-white">
      {/** Si existe más de 1 grupo */}
      {groups && groups.length > 1 ? (
        <UserGroups name={user} groups={groups} />
      ) : groups && groups.length === 1 ? (
        <div className="p-10">
          <div className="flex md:flex-row flex-col items-center ">
            <div className="md:w-1/2 w-full flex justify-between items-center">
              <div>
                <h1 className="text-2xl font-bold text-white">
                  Bienvenido {user} a
                </h1>
                <span className="bg-gradient-to-r font-extrabold text-3xl from-[#1d90fc] to-[#0cfca7] inline-block text-transparent bg-clip-text">
                  Team Score
                </span>
                <h3>Tus compañeros de cohorte son:</h3>
              </div>
              <ButtonNeon text="Refrescar" onClick={resetData} />
            </div>
            <div className="ml-auto">
              <HomeCard
                users={users}
                usersTotalVote={usersTotalVote}
                usersVoted={usersVoted}
              />
            </div>
          </div>
          <div className="flex flex-wrap gap-x-10 gap-y-5 mt-5">
            {usersFiltered.map((user) => (
              <Partners
                key={user.id}
                id={user.id}
                name={user.nombres}
                surname={user.apellidos}
                role={user.rol}
                staff={user.staff}
                activo={user.state}
              />
            ))}
          </div>
        </div>
      ) : (
        <div className="p-10 w-full h-96 flex flex-col items-center justify-center">
          <h3 className="text-white text-xl font-bold text-center">
            No estás asignado a ningún grupo en este momento.
          </h3>
          <h3 className="text-white text-lg text-center">
            {" "}
            Por favor, ponte en contacto con el soporte técnico para obtener
            asistencia:
          </h3>
          <a
            href="mailto:contacto@nocountry.io"
            className="text-blue-300 hover:text-blue-400 block"
          >
            contacto@nocountry.io
          </a>
        </div>
      )}
    </div>
  );
}
