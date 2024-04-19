import React, { useContext, useEffect, useState} from "react";
import { Link, useLocation } from "react-router-dom";
import TittleGradient from "../../components/TittleGradient/TittleGradient";
import { AuthContext } from "../../context/AuthContext";
import questionService from "../../services/questionService";
//import question from "../../data/question.json";
import QuestionCard from "../../components/QuestionCard/QuestionCard";
import ButtonNeon from "../../components/ButtonNeon/ButtonNeon";
// import HeaderDash from "../../components/HeaderDash/HeaderDash"

const Vote = () => {
  const { auth, updateUserStaff } = useContext(AuthContext);


  const [question, setQuestion] = useState([]); //Hacer lo mismo para Question
  useEffect(() => {
    questionService
      .users()
      .then((data) => {
        // Hacer algo con los datos recibidos
        setQuestion(data);
      })
      .catch((error) => {
        // Manejar cualquier error que ocurra durante la solicitud
        console.error("Error fetching question:", error);
      });
  }, []);


  // Obtener la ubicación actual

  const location = useLocation();
  // Obtener el parámetro 'user' de la URL
  const userVoting = new URLSearchParams(location.search).get("user");
  const index = new URLSearchParams(location.search).get("index");

  const handleSubmit = () => {
    // Lógica para actualizar el staff del socio correspondiente (usando el índice)
    updateUserStaff(index);
    // Navegar de regreso a la página de inicio
  };



  return (
    <div className='w-full min-h-screen flex items-center justify-center pt-8  flex-col'
    style={{
      backgroundColor: "#06071b",
      backgroundImage:
        "radial-gradient(circle farthest-corner at 50% 0%, rgba(29, 144, 252, .29), #06071b)",
    }}> 
    
      <div className="mb-40 w-full text-center flex items-center justify-center">
      <TittleGradient user={auth.name} voting={userVoting} />
      </div>
      <form >

      <div className='flex flex-col h-auto pt-8 container items-center mx-auto w-3/4 justify-around '> 
        {question.map((question) => (
          <QuestionCard description={question.description} key={question.field_id} name={question.name} field_id={question.field_id}>
          </QuestionCard>
        ))}
      </div>
        

       <div className="flex items-center justify-center my-10">
      <Link to={"/"}>
        <ButtonNeon onClick={handleSubmit} text={"Enviar Votacion"}>
          
        </ButtonNeon>
      </Link>
      </div>
      </form>

    </div>
  );


};

export default Vote;
