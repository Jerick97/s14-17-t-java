import { forwardRef } from "react";
import PropTypes from "prop-types";
import lock from "@assets/Inputs/lock.png";
const PasswordInput = forwardRef(function PasswordInput(
  { name, placeholder, customStyle, ...rest },
  ref
) {
  return (
    <div className="flex relative items-center mb-2">
      <input
        type="password"
        name={name}
        className={`w-full rounded-full pl-5 pr-16 h-9 focus:outline-none text-[#acacb5] font-normal bg-[#476468] ${customStyle}`}
        placeholder={placeholder}
        {...rest}
        ref={ref}
      />
      <div className="bg-white absolute right-0  w-12 h-12 rounded-full">
        <img className="p-3" src={lock} alt="user" />
      </div>
    </div>
  );
});

PasswordInput.propTypes = {
  name: PropTypes.string.isRequired,
  placeholder: PropTypes.string.isRequired,
  customStyle: PropTypes.string.isRequired,
};

export default PasswordInput;
