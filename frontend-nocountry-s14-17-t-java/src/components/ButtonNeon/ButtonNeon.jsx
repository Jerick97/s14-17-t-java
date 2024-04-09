const ButtonNeon = ({ text, onClick }) => {
  return (
    <div>
      <button
        onClick={onClick}
        className="w-[160px] h-[38px] border-[1px] border-[#09FCA7] bg-black rounded-[5px] px-[20px] py-[8px] text-[#09FCA7] hover:shadow-[0px_0px_10px_0px_#09FCA7] no-underline"
      >
        {text}
      </button>
    </div>
  );
};

export default ButtonNeon;
