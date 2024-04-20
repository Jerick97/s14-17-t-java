import { Outlet } from "react-router-dom";
import HeaderHome from "../components/HeaderHome/HeaderHome";
import Footer from "../components/Footer/Footer";

const HomeLayout = () => {
  return (
    <>
      <HeaderHome />
      <Outlet />
      <Footer />
    </>
  );
};

export default HomeLayout;
