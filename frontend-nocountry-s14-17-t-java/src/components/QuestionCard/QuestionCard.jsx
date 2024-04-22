import { forwardRef, useState } from "react";
import PropTypes from "prop-types";
const QuestionCard = forwardRef(function QuestionCard(
  { description, name, field_id, ...rest },
  ref
) {
  const min = 0;
  const max = 10;
  const middle = Math.floor((min + max) / 2);
  const [value, setValue] = useState(1);
  const handleChange = (e) => {
    setValue(e.target.value);
  };

  return (
    <div className="md:w-[90%] h-[250px] p-5 rounded-3xl bg-gradient-to-b from-blue-400/10 to-#06071B	   flex-col flex mb-10  ">
      <div className="flex items-center justify-between mb-4 ">
        <h3 className="md:text-2xl text-1xl text-center font-bold text-white">
          Pregunta {field_id}
        </h3>
        <h3 className="md:text-2xl text-1xl text-center font-bold bg-gradient-to-r from-[#1d90fc] to-[#0cfca7] inline-block text-transparent bg-clip-text">
          {name}
        </h3>
      </div>

      <h1 className="md:text-3xl text-1xl text-left mb-4 font-semibold text-white">
        {description}
      </h1>

      <div className="w-full ">
        <input
          type="range"
          name={name}
          min={min}
          max={max}
          className="daisy-ui daisy-range"
          step="1"
          defaultValue={value}
          onChange={(e) => console.log(e.target.value)}
          onMouseUp={handleChange}
          {...rest}
          ref={ref}
        />
        <div className="daisy-ui w-full flex justify-between text-xs px-2 text-white font-semibold">
          {Array.from({ length: max + 1 }).map((_, index) => (
            <span key={index}>
              {index === min || index === middle || index === max ? index : "|"}
            </span>
          ))}
        </div>
      </div>
    </div>
  );
});

QuestionCard.propTypes = {
  field_id: PropTypes.number.isRequired,
  name: PropTypes.string.isRequired,
  description: PropTypes.string.isRequired,
};

export default QuestionCard;
