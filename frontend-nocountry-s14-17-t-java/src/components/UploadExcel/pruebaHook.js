export const sendDataToBackendPrueba = async (groupedData) => {
  try {
    const response = await fetch('http://localhost:8080/user', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(groupedData)
    });

    if (!response.ok) {
      throw new Error('Error al enviar datos al servidor');
    }

    // Manejar la respuesta del servidor
    const data = await response.json();
    console.log('Datos enviados exitosamente:', data);
    return data; // Puedes retornar los datos si los necesitas en el componente
  } catch (error) {
    console.error('Error al enviar datos al servidor:', error);
    throw error; // Puedes lanzar nuevamente el error para que sea manejado en el componente
  }
};
