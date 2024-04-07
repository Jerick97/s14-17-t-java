import Logo from "../Logo/Logo";
import ButtonNeon from "../ButtonNeon/ButtonNeon";
import DashBoardNavbar from "../DashBoardComponents/DashBoardNavbar";

const HeaderHome = () => {
  return (
    <div className="w-screen">
      <div className=" w-full h-[58px] flex justify-around items-center bg-black">
        <Logo />
        <div className="w-auto h-auto flex items-center gap-2">
          <DashBoardNavbar />
          <ButtonNeon text="Log Out" />
        </div>
      </div>
    </div>
  );
};

export default HeaderHome;
