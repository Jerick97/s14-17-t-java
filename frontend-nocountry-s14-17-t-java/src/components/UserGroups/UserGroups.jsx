import PropTypes from "prop-types";
import NeonFrameButton from "../NeonFrameButton/NeonFrameButton";
import { useContext, useEffect } from "react";
import { AuthContext } from "../../context/AuthContext";

function UserGroups({ groups, name }) {
  const { group, setGroup } = useContext(AuthContext);
  const handlerSelectGroup = (name) => {
    setGroup(name); //guardamos el nombre del grupo en el Contexto, debe usarse en el Header
  };

  useEffect(() => {
    console.log(group); //mostramos el name del grupo seleccionado
  }, [group]);

  return (
    <div className="container max-w-screen-lg mx-auto w-full sm:p-10 p-4">
      <div className="flex flex-col md:items-start items-center justify-center mb-14">
        <h1 className="sm:text-6xl text-5xl font-bold text-white text-center md:text-start">
          Bienvenido {name} a
        </h1>
        <span className="bg-gradient-to-r font-extrabold sm:text-6xl text-5xl from-[#1d90fc] to-[#0cfca7] inline-block text-transparent bg-clip-text">
          Team Score
        </span>
        <h3 className="text-[#A8A8A8] text-xl">Tus grupos de cohorte son:</h3>
      </div>
      <div className="grid grid-cols-12 md:gap-8 gap-4 mb-4">
        {groups.map((group) => (
          <NeonFrameButton
            key={group.id}
            id={group.id}
            text={group.name}
            onClick={handlerSelectGroup}
            disabled={false} //si es true, se mostrara en gris
          />
        ))}
      </div>
    </div>
  );
}

UserGroups.propTypes = {
  name: PropTypes.string.isRequired,
  groups: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.string.isRequired,
      name: PropTypes.string.isRequired,
    })
  ).isRequired,
};

export default UserGroups;
