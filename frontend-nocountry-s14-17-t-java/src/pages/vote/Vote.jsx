import React from 'react'


//Varianles para usuario que vota y a quien esta votando//
const user = "Franco"
const voting = "Rodrigo"


const Vote = () => {
  return (

<div className="w-full h-screen flex items-center justify-center bg-[#06071B]">
  <h1 className="text-5xl font-bold top-20 absolute"> 
  <span className="">{user}</span>, estas votando a <span className=''> {voting} </span>
  </h1>

  <h2 className='text-3xl'>Pregunta 1.</h2>



</div>    
  )
}

export default Vote