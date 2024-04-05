const TittleGradient = ({ user, voting }) => {
  return (
    <h1 className="text-5xl top-20 absolute text-gray-400 font-semibold text-center">
      <span
        className="font-bold bg-gradient-to-r from-blue-600 via-green-500 to-indigo-400 inline-block text-transparent bg-clip-text
"
      >
        {user}
      </span>{" "}
       estas votando a {" "}
      <br></br>
      <span
        className="text-gray-400"
      >
        {" "}
        {voting}{" "}
      </span>
    </h1>
  );
};

export default TittleGradient;
