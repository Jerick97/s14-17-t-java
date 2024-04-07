import DashBoardCard from "../../../components/DashBoardComponents/DashBoardCard";
import DashBoardGraphic from "../../../components/DashBoardComponents/DashBoardGraphic";
import DashboarTable from "../../../components/DashBoardComponents/DashboarTable";
import HeaderDash from "../../../components/HeaderDash/HeaderDash";
function Dashboard() {
  return (
    <div id="dashboard" className="w-screen bg-[#06071B] h-auto">
      <HeaderDash />
      <div className="px-4 sm:px-6 lg:px-8  w-full h-screen  grid grid-rows-3  gap-4">
        <div className="row-span-auto sm:row-span-1 text-black  "><DashBoardGraphic /></div>
        <div className="row-span-1 sm:col-span-2 sm:row-span-1 text-black  w-full  "><DashBoardCard /></div>
        <div className="sm:row-span-auto sm:col-span-3 text-black w-full "><DashboarTable /></div>
      </div>
    </div>
  );
}

export default Dashboard;
