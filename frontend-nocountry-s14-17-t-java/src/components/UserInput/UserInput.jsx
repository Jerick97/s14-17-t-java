import { forwardRef } from "react";
import PropTypes from "prop-types";
import user from "@assets/Inputs/user.png";
const UserInput = forwardRef(function UserInput(
  { name, placeholder, customStyle, ...rest },
  ref
) {
  return (
    <div className="flex relative items-center mb-2">
      <div className="bg-white absolute w-12 h-12 rounded-full">
        <img className="p-2" src={user} alt="user" />
      </div>
      <input
        type="text"
        name={name}
        className={`w-full rounded-full pl-16 pr-5 h-9 bg-[#476468] focus:outline-none font-normal ${customStyle}`}
        placeholder={placeholder}
        {...rest}
        ref={ref}
      />
    </div>
  );
});

UserInput.propTypes = {
  name: PropTypes.string.isRequired,
  placeholder: PropTypes.string.isRequired,
  customStyle: PropTypes.string.isRequired,
};

export default UserInput;
