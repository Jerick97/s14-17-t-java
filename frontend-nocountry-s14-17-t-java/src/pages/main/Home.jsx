import { useContext, useEffect, useState } from "react";
import Partners from "../../components/Partners/Partners";
import { AuthContext } from "../../context/AuthContext";
import UserGroups from "../../components/UserGroups/UserGroups";
import ButtonNeon from "../../components/ButtonNeon/ButtonNeon";
import HomeCard from "../../components/HomeCard/HomeCard";
import groupsService from "../../services/groupsService";
import Loading from "../../components/Loading/Loading";

export default function Home() {
  return <HomeContent />;
}

function HomeContent() {
  const { users, auth, group, setGroup, groups } = useContext(AuthContext);

  const [members, setMembers] = useState([]);
  const [loading, setLoading] = useState(true);

  const resetDataGroups = () => {
    setGroup(null);
    localStorage.removeItem("selectedGroup");
    localStorage.removeItem("users");
  };

  const usersFiltered = members
    .filter((user) => user.email !== auth.email)
    .sort((a, b) => a.id - b.id);
  const userDisabled = usersFiltered.filter((user) => user.state !== "P");
  const usersTotalVote = usersFiltered.filter(
    (user) => userDisabled.includes(user) && user.staff !== true
  );
  const usersVoted = usersFiltered.filter((user) => user.staff === true);
  console.log(usersFiltered);
  useEffect(() => {
    const storedSelectedGroup = localStorage.getItem("selectedGroup");

    // Verificar si existe el elemento "selectedGroup" en el localStorage
    if (storedSelectedGroup) {
      // Convertir el valor almacenado de "selectedGroup" de nuevo a un objeto
      const parsedSelectedGroup = JSON.parse(storedSelectedGroup);
      // Establecer el estado de group con el valor del objeto almacenado,
      // solo si es diferente al valor actual de group
      if (JSON.stringify(parsedSelectedGroup) !== JSON.stringify(group)) {
        setGroup(parsedSelectedGroup);
      }
    }

    if (groups.length === 1 && !group) {
      const newData = {
        id: groups[0].group_id,
        text: groups[0].group_name,
      };
      setGroup(newData);
    }

    const fetchMembers = async () => {
      try {
        setLoading(true);

        if (group) {
          const miembrosData = await groupsService.member(group.id);
          const filteredMembers = miembrosData
            .filter((member) => member.email !== auth.email)
            .sort((a, b) => a.id - b.id);
          const storedUsers = JSON.parse(localStorage.getItem("users"));

          if (storedUsers) {
            setMembers(storedUsers);
          } else {
            setMembers(filteredMembers);
          }
        }
      } catch (error) {
        console.error("Error al obtener los miembros del grupo:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchMembers();
  }, [groups, group, setGroup]);

  return (
    <div className="w-full bg-[#06071B] min-h-screen text-white">
      {/* Verificar qué componente mostrar */}
      {!group && groups.length > 1 ? (
        // Si no se ha seleccionado ningún grupo, mostrar UserGroups
        <UserGroups name={auth.name} groups={groups} />
      ) : groups.length >= 1 ? (
        // Si se ha seleccionado un grupo, mostrar Partners
        <div className="p-10">
          <div className="flex md:flex-row flex-col items-center ">
            <div className="md:w-1/2 w-full flex justify-between items-center">
              <div>
                <h1 className="text-2xl font-bold text-white">
                  Bienvenido {auth.name} a
                </h1>
                <span className="bg-gradient-to-r font-extrabold text-3xl from-[#1d90fc] to-[#0cfca7] inline-block text-transparent bg-clip-text">
                  Team Score
                </span>
                <h3>Tus compañeros de equipo son:</h3>
              </div>
              <div className="flex flex-wrap items-center justify-center h-24">
                {groups.length > 1 ? (
                  <ButtonNeon
                    text="Volver a grupos"
                    onClick={resetDataGroups}
                  />
                ) : null}
              </div>
            </div>
            <div className="ml-auto">
              <HomeCard
                users={members}
                usersTotalVote={usersTotalVote}
                usersVoted={usersVoted}
              />
            </div>
          </div>
          <div className="w-full bg-[#06071B] min-h-screen text-white">
            {/* Aquí verificamos si un grupo ha sido seleccionado */}
            {loading ? (
              <div className="w-full h-56 flex items-center justify-center">
                <Loading />
              </div>
            ) : (
              group && (
                <div className="flex flex-wrap gap-10 mt-8">
                  {/* Filter users based on the selected group */}
                  {usersFiltered.map((member, index) => (
                    <Partners
                      key={index}
                      id={index}
                      idUser={member.id}
                      name={member.name}
                      surname={member.surname}
                      role={member.rol}
                      staff={member.staff}
                      activo={member.state}
                    />
                  ))}
                </div>
              )
            )}
          </div>
        </div>
      ) : groups.length <= 0 ? (
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
      ) : (
        ""
      )}
    </div>
  );
}
