import { useContext, useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { useLocation, useNavigate } from "react-router-dom";
import TittleGradient from "../../components/TittleGradient/TittleGradient";
import { AuthContext } from "../../context/AuthContext";
import questionService from "../../services/questionService";
import QuestionCard from "../../components/QuestionCard/QuestionCard";
import ButtonNeon from "../../components/ButtonNeon/ButtonNeon";
import feedbackService from "../../services/feedbackService";

const Vote = () => {
  // Obtener la ubicación actual
  const location = useLocation();
  const navigate = useNavigate();
  // Obtener el parámetro 'user' de la URL
  const userVoting = new URLSearchParams(location.search).get("user");
  const id = new URLSearchParams(location.search).get("index");
  const idUser = new URLSearchParams(location.search).get("idUser");
  const { auth, updateUserStaff, groups } = useContext(AuthContext);
  const { register, handleSubmit } = useForm();

  const onSubmit = async (data) => {
    // Construye el JSON final
    const jsonToSend = [
      {
        projectId: groups[0].projectId,
        idUsuarioQueEvalua: auth.id,
        idUsuarioEvaluado: idUser,
        valorDelFeedback: Object.entries(data).map(([field_id, score]) => ({
          field_id: parseInt(field_id),
          score: parseInt(score),
          comments: "",
        })),
      },
    ];

    // Muestra el JSON en la consola
    console.log(jsonToSend);
    try {
      await feedbackService.feedback(jsonToSend);
      console.log("Feedback enviado correctamente"); // Acceso al estado de la respuesta
    } catch (error) {
      console.error("Feedback no enviado", error);
    }
    // Lógica para actualizar el staff del socio correspondiente (usando el índice)
    updateUserStaff(id);

    // Navegar de regreso a la página de inicio
    navigate("/");
  };

  const [question, setQuestion] = useState([]); //Hacer lo mismo para Question
  useEffect(() => {
    questionService
      .users()
      .then((data) => {
        // Hacer algo con los datos recibidos
        const filteredData = data.filter((item) => item.status !== "false");
        setQuestion(filteredData);
        console.log(filteredData);
      })
      .catch((error) => {
        // Manejar cualquier error que ocurra durante la solicitud
        console.error("Error fetching question:", error);
      });
  }, []);

  return (
    <div className="w-full min-h-screen flex items-center justify-center pt-8 bg-[#06071B] flex-col">
      <div className="mb-40 w-full text-center flex items-center justify-center">
        <TittleGradient user={auth.name} voting={userVoting} />
      </div>
      <form autoComplete="off" onSubmit={handleSubmit(onSubmit)}>
        <div className="flex flex-col h-auto pt-8 container items-center mx-auto w-3/4 justify-around ">
          {question.map((habilidad, index) => (
            <QuestionCard
              description={habilidad.description}
              key={habilidad.id + 1}
              title={habilidad.name}
              field_id={habilidad.id}
              number={index + 1}
              {...register(`${habilidad.id}`, {
                required: {
                  value: true,
                  message: "Question is required",
                },
              })}
            />
          ))}
        </div>

        <div className="flex items-center justify-center my-10">
          <ButtonNeon text="Enviar" />
        </div>
      </form>
    </div>
  );
};

export default Vote;
