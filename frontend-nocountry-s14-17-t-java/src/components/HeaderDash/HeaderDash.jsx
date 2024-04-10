import Logo from "../Logo/Logo";
import ButtonNeon from "../ButtonNeon/ButtonNeon";
import { Link } from "react-router-dom";
const HeaderHome = () => {
  return (
    <div className="w-screen">
      <div className=" w-full h-[58px] flex justify-around items-center bg-black">
        <Link to="/">
          <Logo />
        </Link>
        <div className="w-auto h-auto flex items-center gap-2">
          <ButtonNeon text="Log Out" />
        </div>
      </div>
    </div>
  );
};

export default HeaderHome;
