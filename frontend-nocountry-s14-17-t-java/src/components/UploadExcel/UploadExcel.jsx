import React, { useState } from "react";
import { useDropzone } from "react-dropzone";
import * as XLSX from "xlsx";

function FileUploader() {
  const [groupedData, setGroupedData] = useState({});

  const validateEmail = (email) => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  };

  const onDrop = (acceptedFiles) => {
    const reader = new FileReader();

    reader.onload = (event) => {
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
          apellidos: personData[0],
          nombres: personData[1],
          email: email,
          emailValido: validateEmail(email),
          rol: personData[3],
          staff: staff,
        };

        groupedData[groupName].push(personObj);
      });

      delete groupedData["grupo"];

      setGroupedData(groupedData);
      console.log(groupedData);
    };

    reader.readAsBinaryString(acceptedFiles[0]);
  };
  const { getRootProps, getInputProps } = useDropzone({ onDrop });

  return (
    <div>
      <div
        {...getRootProps()}
        style={{
          border: "1px solid #ccc",
          padding: "20px",
          textAlign: "center",
          cursor: "pointer",
        }}
      >
        <input {...getInputProps()} />
        <p>
          Arrastra y suelta un archivo Excel aquí, o haz clic para seleccionar
          uno
        </p>
      </div>

      <div>
        <h2>Datos del archivo Excel agrupados:</h2>
        <pre>{JSON.stringify(groupedData, null, 2)}</pre>
      </div>

      <div>
        <h2>Datos del archivo Excel agrupados:</h2>
        {Object.keys(groupedData).map((group) => (
          <div key={group}>
            <h3>{group}</h3>
            <ul>
              {groupedData[group].map((person, index) => (
                <li key={index}>
                  <strong>Apellidos:</strong> {person.apellidos},{" "}
                  <strong>Nombres:</strong> {person.nombres},{" "}
                  <strong>Email:</strong> {person.email},{" "}
                  <strong>Formato de Email válido:</strong>{" "}
                  {person.emailValido ? "Sí" : "No"}, <strong>Rol:</strong>{" "}
                  {person.rol}, <strong>Staff:</strong>{" "}
                  {person.staff ? "Sí" : "No"}
                </li>
              ))}
            </ul>
          </div>
        ))}
      </div>
    </div>
  );
}

export default FileUploader;
