import Logo from "../Logo/Logo";
import HeaderButton from "../HeaderHome/HeaderButton";

const HeaderHome = () => {
  return (
    <div className="w-screen">
      <div className=" w-full h-[58px] flex justify-around items-center bg-black">
        <Logo />
        <HeaderButton />
      </div>
    </div>
  );
};

export default HeaderHome;
