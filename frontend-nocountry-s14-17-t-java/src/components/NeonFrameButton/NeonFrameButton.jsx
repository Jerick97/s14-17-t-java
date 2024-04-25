import PropTypes from "prop-types";

const NeonFrameButton = ({ id, text, onClick, disabled }) => {


  const disabledClass = "border-[#A8A8A8] text-[#A8A8A8] cursor-not-allowed";
  return (
    <div className="md:col-span-4 sm:col-span-6 col-span-12">
      <button
        onClick={() => onClick({ id, text })}
        className={`w-full h-20 text-xl border-solid border-2 border-[#09FCA7] bg-transparent rounded-[5px] px-[20px] py-[8px] text-[#09FCA7] no-underline ${
          disabled ? disabledClass : "hover:shadow-[0px_0px_10px_0px_#09FCA7]"
        }`}
        disabled={disabled}
      >
        {text}
      </button>
    </div>
  );
};

NeonFrameButton.propTypes = {
  id: PropTypes.string.isRequired,
  text: PropTypes.string.isRequired,
  onClick: PropTypes.func.isRequired,
  disabled: PropTypes.bool,
};

export default NeonFrameButton;
