import DashBoardCard from "../../../components/DashBoardComponents/DashBoardCard";
import DashBoardGraphic from "../../../components/DashBoardComponents/DashBoardGraphic";
import DashboarTable from "../../../components/DashBoardComponents/DashboarTable";
import HeaderDash from "../../../components/HeaderDash/HeaderDash";
function Dashboard() {
  return (
    <div id="dashboard" className="w-screen bg-[#06071B] h-auto">
      <HeaderDash />
      <div className="w-full h-[58px] flex justify-around items-center">
        <h2 className="text-3xl text-white font-semibold">
          Panel del administrador
        </h2>
        <label
          htmlFor="my-drawer"
          className="m-4 daisy-drawer-button w-[160px] h-[38px] border-[1px] border-[#09FCA7] bg-black rounded-[5px] px-[20px] py-[8px] text-center text-[#09FCA7] hover:shadow-[0px_0px_10px_0px_#09FCA7] no-underline"
        >
          Menu
        </label>
      </div>
      <div className="daisy-drawer h-screen">
        <input id="my-drawer" type="checkbox" className="daisy-drawer-toggle" />
        <div className="daisy-drawer-content w-full  md:h-[200px] h-auto flex flex-row md:flex-col items-center ">
          {/* Page content here */}

          <div className="px-4 sm:px-6 lg:px-8  w-full h-screen  grid grid-rows-3  gap-4">
            <div className="row-span-auto sm:row-span-1 text-black  ">
              <DashBoardGraphic />
            </div>
            <div className="row-span-1 sm:col-span-2 sm:row-span-1 text-black  w-full  ">
              <DashBoardCard />
            </div>
            <div className="sm:row-span-auto sm:col-span-3 text-black w-full ">
              <DashboarTable />
            </div>
          </div>
        </div>
        <div className="daisy-drawer-side ">
          <label
            htmlFor="my-drawer"
            aria-label="close sidebar"
            className="daisy-drawer-overlay"
          ></label>
          <ul className="daisy-menu p-4 w-80 min-h-full bg-black/30 backdrop-filter backdrop-blur-sm  text-lg text-white font-bold ">
            {/* Sidebar content here */}
            <li>
              <a>Sidebar Item 1</a>
            </li>
            <li>
              <a>Sidebar Item 2</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  );
}

export default Dashboard;
