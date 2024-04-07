const DashBoardGraphic = () => {
  return (
    <div className="w-full h-full bg-black border-[1px] border-[#09FCA7] rounded-[10px] flex flex-col items-center justify-around">
      <h3 className="text-warning text-2xl font-bold">Votacion total</h3>
      <div
        className="daisy-radial-progress text-warning text-xl font-bold border-4 border-warning"
        style={{ "--value": "70", "--size": "8rem", "--thickness": "10px" }}
        role="progressbar"
      >
        70%
      </div>
    </div>
  );
};

export default DashBoardGraphic;
