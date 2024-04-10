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
        <ButtonNeon text="S14-17-t-Java" />
      </div>
    </div>
  );
};

export default HeaderHome;
