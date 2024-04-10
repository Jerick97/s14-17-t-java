import { Link } from "react-router-dom";
import DashBoardCard from "../../../components/DashBoardComponents/DashBoardCard";
import DashBoardGraphic from "../../../components/DashBoardComponents/DashBoardGraphic";
import DashboarTable from "../../../components/DashBoardComponents/DashboarTable";
import HeaderDash from "../../../components/HeaderDash/HeaderDash";
function Dashboard() {
  return (

          <div className='px-4 sm:px-6 lg:px-8  w-full  grid grid-rows-auto  gap-4'>
            <div className='row-span-auto sm:row-span-1 text-black min-h-full '>
              <DashBoardGraphic />
            </div>
            <div className='row-span-1 sm:col-span-2 sm:row-span-1 text-black  w-full  '>
              <DashBoardCard />
            </div>
            <div className='sm:row-span-auto sm:col-span-3 lg:w-full sm:w-auto justify-center text-black w-full '>
              <DashboarTable />
            </div>
          </div>
   

  );
}

export default Dashboard;
