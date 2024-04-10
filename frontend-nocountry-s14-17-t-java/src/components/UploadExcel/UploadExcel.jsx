import React, { useState } from "react";
import { useDropzone } from "react-dropzone";
import * as XLSX from "xlsx";
import { sendDataToBackend } from "./excelHook";
import { sendDataToBackendPrueba } from "./pruebaHook";
import Partners from "../Partners/Partners";
import Groups from "../Partners/Groups";

function FileUploader() {
  const [groupedData, setGroupedData] = useState({});
  const [errorMessage, setErrorMessage] = useState(null);

  const validateEmail = (email) => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  };

  const onDrop = (acceptedFiles) => {
    const reader = new FileReader();

    reader.onload = (event) => {
      try {
        const binaryString = event.target.result;
        const workbook = XLSX.read(binaryString, { type: "binary" });
        const sheetName = workbook.SheetNames[0];
        const worksheet = workbook.Sheets[sheetName];
        const excelData = XLSX.utils.sheet_to_json(worksheet, { header: 1 });

        const groupedData = {};
        excelData.forEach((row) => {
          const [group, ...personData] = row;
          const groupName = group.trim();

          if (!groupedData[groupName]) {
            groupedData[groupName] = [];
          }

          const email = personData[2];
          const staff = personData[4] === 1;

          const personObj = {
            surname: personData[0],
            name: personData[1],
            username: email,
            emailValido: validateEmail(email),
            role: personData[3],
            staff: staff,
          };

          groupedData[groupName].push(personObj);
        });

        delete groupedData["grupo"];

        setGroupedData(groupedData);
        setErrorMessage(null);
      } catch (error) {
        console.error("Error al leer el archivo Excel:", error);
        setErrorMessage(
          "Error al leer el archivo Excel. Por favor, asegúrate de que sea un archivo válido."
        );
      }
    };

    reader.readAsBinaryString(acceptedFiles[0]);
  };

  const handleSendDataToBackend = () => {
    console.log("Enviando datos al servidor:", groupedData);
    sendDataToBackend(groupedData)
      .then((data) => {
        console.log("Datos enviados exitosamente:", data);
      })
      .catch((error) => {
        console.error("Error al enviar datos al servidor:", error);
      });
  };

  const prueba = {
    username: "pepim2",
    name: "nombre2",
  };

  const handleSendDataToBackendPrueba = () => {
    console.log("Enviando datos al servidor:", prueba);
    sendDataToBackendPrueba(prueba)
      .then((data) => {
        console.log("Datos enviados exitosamente:", data);
      })
      .catch((error) => {
        console.error("Error al enviar datos al servidor:", error);
      });
  };

  const { getRootProps, getInputProps } = useDropzone({ onDrop });

  return (
    <div>
      <div
        {...getRootProps()}
        className='border-[#0CFCA7] border rounded-lg mb-3 p-5 cursor-pointer'
      >
        <input {...getInputProps()} />
        <p>
          Arrastra y suelta un archivo Excel aquí, o haz clic para seleccionar
          uno
        </p>
      </div>
      <button className='btn btn-primary' onClick={handleSendDataToBackend}>
        Enviar datos al servidor
      </button>

      <button
        className='btn btn-primary'
        onClick={handleSendDataToBackendPrueba}
      >
        Enviar datos al servidor
      </button>
      <div>
        {errorMessage ? (
          <div>
            <h2>Error:</h2>
            <p>{errorMessage}</p>
          </div>
        ) : (
          <div>
            <h2>Importaste los siguientes equipos:</h2>

            <ul className='flex flex-wrap gap-5 my-5'>
              {Object.keys(groupedData).map((groupName) => (
                <Groups key={groupName} name={groupName} />
              ))}
            </ul>

            <div>
              <h2>Datos del archivo Excel agrupados:</h2>
              <pre className='overflow-hidden'>
                {JSON.stringify(groupedData, null, 2)}
              </pre>
            </div>
          </div>
        )}
      </div>
    </div>
  );
}

export default FileUploader;
