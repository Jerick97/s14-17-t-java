import { Link, Outlet } from "react-router-dom";
import HeaderHome from "../components/HeaderDash/HeaderDash";
import MenuDashboard from "../components/MenuDashboard/MenuDashboard";
import Footer from "../components/Footer/Footer";
import groups from "../data/groups.json";
import ButtonNeon from "../components/ButtonNeon/ButtonNeon";

function AdminLayout() {
  return (
    <div className="bg-[#06071B]  min-h-screen flex flex-col justify-between">
      <div id="dashboard" className="">
        <div className="daisy-drawer h-full flex flex-col mb-10 ">
          <HeaderHome />

          <div className="daisy-drawer-content w-full   h-full flex flex-row md:flex-col items-center ">
            <input
              id="my-drawer"
              type="checkbox"
              className="daisy-drawer-toggle"
            />

            {/* Contenido de la pagina */}
            <div className="px-10 mt-5">
              <MenuDashboard />

              <Outlet />
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
                  <Link to={"/dashboard"}>
                    <a>Menu</a>
                  </Link>
                </li>
                <li>
                  <Link to={"/dashboard/excel"}>
                    <a>Importar excel</a>
                  </Link>
                </li>
                <div className="daisy-dropdown">
                  <div
                    tabIndex={0}
                    role="button"
                    className="daisy-btn m-1 bg-black w-full  border-[1px] border-[#09FCA7]   rounded-[5px] px-[20px] py-[8px] text-[#09FCA7] hover:shadow-[0px_0px_10px_0px_#09FCA7] no-underline"
                  >
                    Grupos
                  </div>
                  <ul
                    tabIndex={0}
                    className="daisy-dropdown-content w-full z-[1] daisy-menu p-2 shadow rounded-box bg-black "
                  >
                    {groups.map((group) => (
                      <li key={group.id}>
                        <ButtonNeon text={group.name}  />
                      </li>
                    ))}
                  </ul>
                </div>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <div className="">
        <Footer />
      </div>
    </div>
  );
}

export default AdminLayout;
