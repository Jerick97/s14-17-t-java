import { Link, Outlet } from "react-router-dom";
import HeaderHome from "../components/HeaderDash/HeaderDash";
import MenuDashboard from "../components/MenuDashboard/MenuDashboard";

function AdminLayout() {
  return (
    <div className='bg-[#06071B]  min-h-screen'>
      <div id='dashboard' className=''>
        <div className='daisy-drawer h-full flex flex-col'>
          <HeaderHome />
          <MenuDashboard />
          <div className='daisy-drawer-content w-full   h-full flex flex-row md:flex-col items-center '>
            <input
              id='my-drawer'
              type='checkbox'
              className='daisy-drawer-toggle'
            />

            {/* Contenido de la pagina */}
            <div className='px-10'>
              <Outlet />
            </div>

            <div className='daisy-drawer-side '>
              <label
                htmlFor='my-drawer'
                aria-label='close sidebar'
                className='daisy-drawer-overlay'
              ></label>
              <ul className='daisy-menu p-4 w-80 min-h-full bg-black/30 backdrop-filter backdrop-blur-sm  text-lg text-white font-bold '>
                {/* Sidebar content here */}
                <li>
                  <Link to={"/dashboard"}>
                    <p>Menu</p>
                  </Link>
                </li>
                <li>
                  <Link to={"/dashboard/excel"}>
                    <p>Importar excel</p>
                  </Link>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default AdminLayout;
