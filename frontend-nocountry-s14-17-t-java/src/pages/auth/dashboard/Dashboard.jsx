import { useEffect, useState } from "react";
import DashBoardCard from "../../../components/DashBoardComponents/DashBoardCard";
import DashBoardGraphic from "../../../components/DashBoardComponents/DashBoardGraphic";
import DashboarTable from "../../../components/DashBoardComponents/DashboarTable";
import groupsService from "../../../services/groupsService";
// import users from "../../../data/users.json";

function Dashboard() {
  const [users, setUsers] = useState([]);
  console.log(users);

  useEffect(() => {
    const fetchGroups = async () => {
      try {
        const groupsData =
          await groupsService.member(1);
        setUsers(groupsData);
      } catch (error) {
        console.error("Error fetching groups:", error);
      }
    };
    fetchGroups();
  }, []);

  return (
    <div className="w-full grid grid-rows-auto md:gap-4">
      <div className="row-span-auto sm:row-span-1 text-black">
        <DashBoardGraphic users={users} />
      </div>
      <div className="row-span-1 sm:col-span-2 sm:row-span-1 text-black  w-full">
        <DashBoardCard users={users} />
      </div>
      <div className="sm:row-span-auto sm:col-span-3 lg:w-full sm:w-auto justify-center text-black w-full ">
        <DashboarTable users={users} />
      </div>
    </div>
  );
}

export default Dashboard;
