import React, { useState } from "react";
import { useDropzone } from "react-dropzone";
import * as XLSX from "xlsx";
import { sendDataToBackend } from "./excelHook";
import { sendDataToBackendPrueba } from "./pruebaHook";
import Partners from "../Partners/Partners";
import Groups from "../Partners/Groups";

function FileUploader() {

  let [errorMessage, setErrorMessage] = useState(null);

  const [excelData, setExcelData] = useState([]);
  const handleEmailChange = (index, newValue) => {
    const newExcelData = [...excelData];
    newExcelData[index].email = newValue;
    setExcelData(newExcelData);
  };

  let validateEmail = (email) => {
    let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  };

  let { getRootProps, getInputProps } = useDropzone({ onDrop });

  function onDrop(acceptedFiles) {
    const reader = new FileReader();
    reader.readAsBinaryString(acceptedFiles[0]);

    reader.onload = (event) => {
      try {
        const binaryString = event.target.result;
        const workbook = XLSX.read(binaryString, { type: "binary" });
        const sheetName = workbook.SheetNames[0];
        const worksheet = workbook.Sheets[sheetName];
        const data = XLSX.utils.sheet_to_json(worksheet, { header: 1, range: 1 });

        const excelRows = data.map(row => { 
          const group = row[0];
          const name = row[1];
          const surname = row[2];
          const email = (row[3]);
          const role = row[4];
          const staff = row[5];

          return { group, name, surname, email, role, staff };
        });

        setExcelData(excelRows);
        setErrorMessage(null);
      } catch (error) {
        console.error("Error al leer el archivo Excel:", error);
        setErrorMessage(
          "Error al leer el archivo Excel. Por favor, asegúrate de que sea un archivo válido."
        );
      }
    };
  }

  function handleSendDataToBackend() {
    
    // Objeto para almacenar las personas agrupadas por grupo
    const groupedData = [];

    // Recorrer excelData y agrupar por grupo
    excelData.forEach(person => {
      // Buscar si el grupo ya existe en groupedData
      const existingGroupIndex = groupedData.findIndex(group => group.nameGroup === person.group);

      if (existingGroupIndex !== -1) {
        // Si el grupo ya existe, agregar la persona al array de usuarios correspondiente
        groupedData[existingGroupIndex].users.push({
          username: person.email,
          // Puedes asignar los otros campos según sea necesario
          name: person.name,
          surname: person.surname,
          role: person.role
          // Agrega los otros campos según sea necesario para que coincidan con UserModel
        });
      } else {
        // Si el grupo no existe, crear un nuevo objeto LoadBulkModel
        const newGroup = {
          nameGroup: person.group,
          users: [{
            username: person.email,
            // Puedes asignar los otros campos según sea necesario
            name: person.name,
            surname: person.surname,
            role: person.role
            // Agrega los otros campos según sea necesario para que coincidan con UserModel
          }]
        };
        groupedData.push(newGroup);
      }
    });

    console.log(groupedData);
  }

  return (
    <div>
      <div {...getRootProps()} className="border-[#0CFCA7] border rounded-lg mb-3 p-5 cursor-pointer">
        <input {...getInputProps()} />
        <p>Arrastra y suelta un archivo Excel aquí, o haz clic para seleccionar uno</p>
      </div>

      {/* Mostrar el mensaje de error si existe */}
      {errorMessage && (
        <div className="text-red-500">
          <h2>Error:</h2>
          <p>{errorMessage}</p>
        </div>
      )}

      <table className="table-auto">
        <thead>
          <tr>
            <th className="text-white px-4 py-2">Group</th>
            <th className="text-white px-4 py-2">Name</th>
            <th className="text-white px-4 py-2">Surname</th>
            <th className="text-white px-4 py-2">Email</th>
            <th className="text-white px-4 py-2">Role</th>
            <th className="text-white px-4 py-2">Staff</th>
          </tr>
        </thead>
        <tbody>
          {excelData.map((row, index) => (
            <tr key={index}>
              <td className="text-white px-4 py-2">{row.group}</td>
              <td className="text-white px-4 py-2">{row.name}</td>
              <td className="text-white px-4 py-2">{row.surname}</td>
              <td className="text-white px-4 py-2">
                <input
                  type="text"
                  className="w-full bg-transparent text-black text-white"
                  value={row.email}
                  onChange={(e) => handleEmailChange(index, e.target.value)}
                />
              </td>
              <td className="text-white px-4 py-2">{row.role}</td>
              <td className="text-white px-4 py-2">{row.staff}</td>
            </tr>
          ))}
        </tbody>
      </table>

      <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded" onClick={handleSendDataToBackend}>
        Guardar
      </button>
    </div>
  );
}

export default FileUploader;
