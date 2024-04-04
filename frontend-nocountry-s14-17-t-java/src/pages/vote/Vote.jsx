import React from 'react'
import TittleGradient from '../../components/TittleGradient/TittleGradient'

//Varianles para usuario que vota y a quien esta votando//
const user = "Franco"
const voting = "Rodrigo"


const Vote = () => {
  return (

<div className="w-full h-screen flex items-center justify-center bg-[#06071B]">
  <TittleGradient user={user} voting={voting}/>

  <h2 className='text-3xl'>Pregunta 1.</h2>



</div>    
  )
}

export default Vote