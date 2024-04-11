// Función para guardar los votos en el localStorage
export const saveLocalStorage = (name, data) => {
  // Convertir la data a formato JSON
  const dataJSON = JSON.stringify(data);
  // Guardar en el localStorage
  localStorage.setItem(name, dataJSON);
};

// Función para recuperar los data del localStorage
export const getLocalStorage = (name) => {
  // Recuperar del localStorage
  const dataJSON = localStorage.getItem(name);
  // Convertir de JSON a un array de objetos JavaScript
  return JSON.parse(dataJSON) || []; // Si no hay data guardados, retorna un array vacío
};
