const TittleGradient = ({ user, voting }) => {
  return (
    <h1 className="md:text-5xl text-2xl top-20 absolute text-gray-400 font-semibold text-center">
      <span
        className="font-bold bg-gradient-to-r from-[#1d90fc] to-[#0cfca7] inline-block text-transparent bg-clip-text
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
