import { useNavigate, Link } from "react-router-dom";
import Logo from "../Logo/Logo";
import ButtonNeon from "../ButtonNeon/ButtonNeon";
import { useContext } from "react";
import { AuthContext } from "../../context/AuthContext";

const HeaderHome = () => {
  const { logout } = useContext(AuthContext);
  const navigate = useNavigate();

  const handlerlogout = () => {
    logout();
    navigate("/login");
  };

  return (
    <div className="">
      <div className=" w-full h-[58px] flex justify-between pl-10 pr-10 pt-10 items-center">
        <Link to="/">
          <Logo />
        </Link>
        <div className="w-auto h-auto flex items-center gap-2">
          <ButtonNeon text="Log Out" onClick={handlerlogout} />
          <label
        htmlFor='my-drawer'
        className='m-4 cursor-pointer daisy-drawer-button w-[160px] h-[38px] border-[1px] border-[#09FCA7] rounded-[5px] px-[20px] py-[8px] text-center text-[#09FCA7] no-underline'
      >
        Menu
      </label>
        </div>
        
      
      </div>
    </div>
  );
};

export default HeaderHome;
