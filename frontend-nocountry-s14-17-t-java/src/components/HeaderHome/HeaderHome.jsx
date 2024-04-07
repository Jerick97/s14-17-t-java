import Logo from "../Logo/Logo";
import ButtonNeon from "../ButtonNeon/ButtonNeon";

const HeaderHome = () => {
  return (
    <div className="w-screen">
      <div className=" w-full h-[58px] flex justify-around items-center bg-black">
        <Logo />
        <ButtonNeon text="S14-17-t-Java" />
      </div>
    </div>
  );
};

export default HeaderHome;
