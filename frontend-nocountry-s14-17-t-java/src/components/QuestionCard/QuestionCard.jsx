import { useState } from "react";
import Range from "../Range/Range";



const QuestionCard = ({ description, name, field_id }) => {


  const [value, setValue] = useState(5);

  const handleChange = (event) => {
    setValue(parseInt(event.target.value));
  }; 

  return (
    <div className="md:w-[90%] h-[250px] p-5 rounded-3xl bg-gradient-to-b from-blue-400/10 to-#06071B	   flex-col flex mb-10  ">  
      
      <div className="flex items-center justify-between mb-4 ">
           <h3 className='md:text-2xl text-1xl text-center font-bold text-white'>
              Pregunta {field_id}
            </h3>
            <h3 className='md:text-2xl text-1xl text-center font-bold bg-gradient-to-r from-[#1d90fc] to-[#0cfca7] inline-block text-transparent bg-clip-text'>
               {name}
            </h3>
        </div>

            <h1 className='md:text-3xl text-1xl text-left mb-4 font-semibold text-white'>
              {description}
            </h1>

            <div className="w-full ">
            <Range min={0} max={10}></Range>



            </div>
    
    </div>
  );
};

export default QuestionCard;