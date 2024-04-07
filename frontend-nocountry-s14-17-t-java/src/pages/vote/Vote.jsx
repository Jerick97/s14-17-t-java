import React from 'react'
import TittleGradient from '../../components/TittleGradient/TittleGradient'

//Varianles para usuario que vota y a quien esta votando//
const user = "Franco"
const voting = "Rodrigo"


const Vote = () => {
  const preguntas = ["¿Se sienten seguros y apoyados por sus compañeros?"]


  return (

<div className="w-full h-screen flex items-center justify-center bg-[#06071B]">
  <TittleGradient user={user} voting={voting}/>
  <div className="w-1200 h-350 rounded-3xl		 bg-gradient-to-b from-blue-400 to-#06071B  opacity-30    p-20         flex-col flex mt-40 ">
  {preguntas.map((pregunta, index) => (
        <div key={index} className=''>
          
          <h3 className=" text-2xl text-center font-medium mb-2 text-white " >Pregunta {index + 1}:</h3>
          <h1 className="text-3xl text-center font-bold text-white " >{pregunta}</h1>
        </div>
      ))}
  </div>





</div>    
  )
}

export default Vote