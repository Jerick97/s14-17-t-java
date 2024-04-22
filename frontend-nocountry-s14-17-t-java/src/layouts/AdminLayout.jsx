import { Link, Outlet } from "react-router-dom";
import HeaderHome from "../components/HeaderDash/HeaderDash";
import MenuDashboard from "../components/MenuDashboard/MenuDashboard";
import Footer from "../components/Footer/Footer";

function AdminLayout() {
  return (
    <div className='bg-[#06071B]  min-h-screen flex flex-col justify-between'>
      <div id='dashboard' className=''>
        <div className='daisy-drawer h-full flex flex-col mb-10 '>
          <HeaderHome />

          <div className='daisy-drawer-content w-full   h-full flex flex-row md:flex-col items-center '>
            <input
              id='my-drawer'
              type='checkbox'
              className='daisy-drawer-toggle'
            />

            {/* Contenido de la pagina */}
            <div className='px-10 mt-5'>
              <MenuDashboard />

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
                    <a>Menu</a>
                  </Link>
                </li>
                <li>
                  <Link to={"/dashboard/excel"}>
                    <a>Importar excel</a>
                  </Link>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <div className=''>
        <Footer />
      </div>
    </div>
  );
}

export default AdminLayout;
