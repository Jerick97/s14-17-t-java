import { useContext, useEffect, useState } from "react";
import Partners from "../../components/Partners/Partners";
import { AuthContext } from "../../context/AuthContext";
import UserGroups from "../../components/UserGroups/UserGroups";
import ButtonNeon from "../../components/ButtonNeon/ButtonNeon";
import HomeCard from "../../components/HomeCard/HomeCard";
import useGetMembers from "../../hooks/useGetMembers";

//import groups from "../../data/userGroups.json";

export default function Home() {
  return <HomeContent />;
}

function HomeContent() {
  const { users, auth, group, setGroup } = useContext(AuthContext);
  const {groups} = auth;
  const [members, setMembers] = useState([])
// Función para obtener usuarios en un grupo específico
const getUsersInGroup = async (id) => {
  try {
    // Hacer la solicitud GET al endpoint con el ID del grupo
    const response = await axiosInstance.get(`/users/usersInGroup/${id}`);

    // Devolver los datos de la respuesta
    return setMembers(response);
  } catch (error) {
    // Manejar cualquier error que ocurra durante la solicitud
    console.error('Error fetching users in group:', error);
    throw new Error('Failed to fetch users in group');
  }
};


  
  const [selectedGroup, setSelectedGroup] = useState(null); // Estado para controlar qué componente mostrar

  const user = "Camilo";

  const resetData = () => {
    localStorage.removeItem("users");
    window.location.reload();
  };
  const resetDataGroups = () => {
    setGroup(null);
  };
  const usersFiltered = users.filter((usuario) => usuario.nombres !== user);
  const userDisabled = usersFiltered.filter((user) => user.state !== false);
  const usersTotalVote = usersFiltered.filter(
    (user) => userDisabled.includes(user) && user.staff !== true
  );
  const usersVoted = usersFiltered.filter((user) => user.staff === true);



  useEffect(() => {
    if (groups.length === 1) {
      
      const newData = {
        id: groups_id,
        text: groups_name,
      }
      /* useGetMembers(selectedGroup.group_id) */
      setGroup(newData);
      
    }
    if (group) {
/* 
      getUsersInGroup(group.id)
        .then((users) => {
          console.log('Usuarios en el grupo:', users);
          // Hacer algo con los usuarios obtenidos
        })
        .catch((error) => {
          console.error('Error obteniendo usuarios en el grupo:', error);
         
          // Manejar el error de manera apropiada
        }); */
        console.log(group.id)
    
    } 
  


  /*   return() => {
      setMembers(useGetMembers(group.id));
    } */
   }, [groups, group]);



   
/* 
  useEffect(() => {
    setMembers(useGetMembers(group.id));

  }, [group])
 */

  // Función para manejar el clic en un grupo en UserGroups
  const handleSelectGroup = (group) => {
    setSelectedGroup(group); // Set the selected group
  };

  return (
    <div className='w-full bg-[#06071B] min-h-screen text-white'>
    {/* Verificar qué componente mostrar */}
    {!group && groups.length > 1 ? (
        // Si no se ha seleccionado ningún grupo, mostrar UserGroups
        <UserGroups
          name={auth.name}
          groups={groups}

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
          {group && (
            <div className='flex flex-wrap gap-10 mt-8'>
              {/* Filter users based on the selected group */}
     {/*            {members.map((member, index) => (
                <Partners
                  key={index}
                  id={index}
                  name={member.name}
                  surname={member.surname}
                  role={member.rol}
                  staff={member.staff}
                  activo={member.state}
                />
              ))}   */}
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
