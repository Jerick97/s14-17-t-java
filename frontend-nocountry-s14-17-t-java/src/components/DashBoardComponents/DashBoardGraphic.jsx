const DashBoardGraphic = ({ users }) => {
  // Filtrar los usuarios donde progress es 0 y obtener la longitud del resultado
  const countProgressZero = users.filter((user) => user.progress === 0).length;

  // Calcular el porcentaje
  const totalUsers = users.length;
  const percentageProgressZero = (countProgressZero / totalUsers) * 100;
  const percentageProgressOne = 100 - percentageProgressZero;

  return (
    <div className="w-full h-full bg-black border-[1px] border-[#09FCA7] rounded-[10px] flex flex-col items-center justify-around p-2">
      <h3 className="text-warning text-2xl font-bold">Votación total</h3>
      <div
        className="daisy-radial-progress text-warning text-xl font-bold"
        style={{
          "--value": `${percentageProgressOne}`,
          "--size": "7rem",
          "--thickness": "7px",
        }}
        role="progressbar"
      >
        {percentageProgressOne}%
      </div>
    </div>
  );
};

export default DashBoardGraphic;
