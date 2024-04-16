import React from "react";

const QuestionCard = ({ children , pregunta, index }) => {
  return (
    <div className="md:w-[90%] h-[250px] p-5 rounded-3xl bg-gradient-to-b from-blue-400 to-#06071B flex-col flex mt-40">
      {children}
      <h3 className='md:text-2xl text-1xl text-left font-bold text-white'>
              Pregunta {index + 1}:
            </h3>
            <h1 className='md:text-3xl text-1xl text-left font-semibold text-white'>
              {pregunta}
            </h1>

            <div className="w-full ">
            <div className="daisy-rating">
            <input type="radio" name="rating-1" className="daisy-mask daisy-mask-star" />
            <input type="radio" name="rating-1" className="daisy-mask daisy-mask-star" checked />
            <input type="radio" name="rating-1" className="daisy-mask daisy-mask-star" />
            <input type="radio" name="rating-1" className="daisy-mask daisy-mask-star" />
            <input type="radio" name="rating-1" className="daisy-mask daisy-mask-star" />
          </div>
          </div>
    </div>
  );
};

export default QuestionCard;