import { useState, useEffect } from 'react';
import groupsService from "../services/groupsService";

const useGetMembers = (idGrupo) => {
  const [members, setMembers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchMiembrosGrupo = async () => {
      try {
        setLoading(true);
        
        const miembrosData = await groupsService.member(idGrupo);
        setMembers(miembrosData);
        setLoading(false);
      } catch (error) {
        setError(error);
        setLoading(false);
      }
    };

    fetchMiembrosGrupo();

    // Limpiar efecto en caso de desmontar el componente o cambiar el ID del grupo
    return () => {
      // Aquí podrías realizar alguna limpieza si fuera necesario
    };
  }, [idGrupo]); // Ejecutar el efecto cada vez que cambie el ID del grupo

  return { members, loading, error };
};

export default useGetMembers;