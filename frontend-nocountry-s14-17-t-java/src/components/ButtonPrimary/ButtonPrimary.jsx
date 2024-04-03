import PropTypes from "prop-types";

function ButtonPrimary({ text, onClick }) {
  return (
    <button
      className="bg-white hover:bg-gray-100 text-[#01212E] font-bold py-2 px-6 w-full border border-gray-400 rounded-full shadow"
      onClick={onClick}
    >
      {text}
    </button>
  );
}

ButtonPrimary.propTypes = {
  text: PropTypes.string.isRequired,
  onClick: PropTypes.func.isRequired,
};

export default ButtonPrimary;
