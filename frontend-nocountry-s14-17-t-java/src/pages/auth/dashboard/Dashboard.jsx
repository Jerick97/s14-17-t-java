import { useEffect, useState } from "react";
import DashBoardCard from "../../../components/DashBoardComponents/DashBoardCard";
import DashBoardGraphic from "../../../components/DashBoardComponents/DashBoardGraphic";
import DashboarTable from "../../../components/DashBoardComponents/DashboarTable";
import usersService from "../../../services/usersService";

function Dashboard() {
const [users, setUsers] = useState([]);
useEffect(() => {
  usersService
    .users()
    .then((data) => {
      // Hacer algo con los datos recibidos
      setUsers(data);
    })
    .catch((error) => {
      // Manejar cualquier error que ocurra durante la solicitud
      console.error("Error fetching users:", error);
    });
}, []);


return (
  <div className="w-full grid grid-rows-auto md:gap-4">
    <div className="row-span-auto sm:row-span-1 text-black">
      <DashBoardGraphic />
    </div>
    <div className="row-span-1 sm:col-span-2 sm:row-span-1 text-black  w-full">
      <DashBoardCard />
    </div>
    <div className="sm:row-span-auto sm:col-span-3 lg:w-full sm:w-auto justify-center text-black w-full ">
      <DashboarTable users={users} />
    </div>
  </div>
);
}

export default Dashboard;
