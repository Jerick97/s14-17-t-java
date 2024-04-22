import { useContext, useEffect, useState } from "react";
import Partners from "../../components/Partners/Partners";
import { AuthContext } from "../../context/AuthContext";
import UserGroups from "../../components/UserGroups/UserGroups";
import ButtonNeon from "../../components/ButtonNeon/ButtonNeon";
import HomeCard from "../../components/HomeCard/HomeCard";
import groups from "../../data/userGroups.json";
export default function Home() {
  return <HomeContent />;
}

function HomeContent() {
  const { users, group } = useContext(AuthContext);
  const [selectedGroup, setSelectedGroup] = useState(null); // Estado para controlar qué componente mostrar

  const user = "Camilo";

  console.log(users);
  const resetData = () => {
    localStorage.removeItem("users");
    window.location.reload();
  };
  const resetDataGroups = () => {
    setSelectedGroup(null);
  };
  const usersFiltered = users.filter((usuario) => usuario.nombres !== user);
  const userDisabled = usersFiltered.filter((user) => user.state !== false);
  const usersTotalVote = usersFiltered.filter(
    (user) => userDisabled.includes(user) && user.staff !== true
  );
  const usersVoted = usersFiltered.filter((user) => user.staff === true);
  const { auth } = useContext(AuthContext);
  useEffect(() => {
    if (groups.length === 1) {
      setSelectedGroup(groups[0]);
    }
  }, [groups]);
  // Función para manejar el clic en un grupo en UserGroups
  const handleSelectGroup = (group) => {
    setSelectedGroup(group); // Set the selected group
  };

  return (
    <div className='w-full bg-[#06071B] min-h-screen text-white'>
      {/* Verificar qué componente mostrar */}
      {!selectedGroup && groups.length > 1 ? (
        // Si no se ha seleccionado ningún grupo, mostrar UserGroups
        <UserGroups
          name={auth.name}
          groups={groups}
          onSelectGroup={handleSelectGroup}
        />
      ) : groups.length >= 1 ? (
        // Si se ha seleccionado un grupo, mostrar Partners
        <div className='p-10'>
          <div className='flex md:flex-row flex-col items-center '>
            <div className='md:w-1/2 w-full flex justify-between items-center'>
              <div>
                <h1 className='text-2xl font-bold text-white'>
                  Bienvenido {auth.name} a
                </h1>
                <span className='bg-gradient-to-r font-extrabold text-3xl from-[#1d90fc] to-[#0cfca7] inline-block text-transparent bg-clip-text'>
                  Team Score
                </span>
                <h3>Tus compañeros de cohorte son:</h3>
              </div>
              <ButtonNeon text='Refrescar' onClick={resetData} />
              <ButtonNeon text='Volver a grupos' onClick={resetDataGroups} />
            </div>
            <div className='ml-auto'>
              <HomeCard
                users={users}
                usersTotalVote={usersTotalVote}
                usersVoted={usersVoted}
              />
            </div>
          </div>
          <div className='w-full bg-[#06071B] min-h-screen text-white'>
            {/** Resto del código... */}
            {/* Aquí verificamos si un grupo ha sido seleccionado */}
            {selectedGroup && (
              <div className='flex flex-wrap gap-10 mt-8'>
                {/* Filter users based on the selected group */}
                {selectedGroup.members.map((member) => (
                  <Partners
                    key={member.id}
                    id={member.id}
                    name={member.nombres}
                    surname={member.apellidos}
                    role={member.rol}
                    staff={member.staff}
                    activo={member.state}
                  />
                ))}
              </div>
            )}
          </div>
        </div>
      ) : groups.length <= 0 ? (
        <div className='p-10 w-full h-96 flex flex-col items-center justify-center'>
          <h3 className='text-white text-xl font-bold text-center'>
            No estás asignado a ningún grupo en este momento.
          </h3>
          <h3 className='text-white text-lg text-center'>
            {" "}
            Por favor, ponte en contacto con el soporte técnico para obtener
            asistencia:
          </h3>
          <a
            href='mailto:contacto@nocountry.io'
            className='text-blue-300 hover:text-blue-400 block'
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
