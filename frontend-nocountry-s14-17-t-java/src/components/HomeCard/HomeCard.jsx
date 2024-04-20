const HomeCard = ({ users, usersVoted, usersTotalVote  }) => {
  return (
    <div className="w-full  h-full flex justify-center items-center  border-[1px] border-[#09FCA7] rounded-[10px]">
      <div className="w-full h-full  daisy-stats shadow  text-center bg-black">
        <div className="daisy-stat flex-col items-center p-2">
          <div className="daisy-stat-title lg:text-lg text-sm  text-white">
            Participantes
          </div>
          <div className="daisy-stat-value lg:text-2xl text-xl text-warning w-[100px]">
            {users.length}
          </div>
        </div>
        <div className="daisy-stat border-[1px] border-[#09FCA7] flex-col items-center p-2">
          <div className="daisy-stat-title text-white lg:text-lg text-sm">
            Por votar
          </div>
          <div className="daisy-stat-value text-secondary lg:text-1xl text-2xl w-[100px]">
            {usersTotalVote.length}
          </div>
        </div>

        <div className="daisy-stat  border-[1px] border-[#09FCA7] flex-col items-center p-2">
          <div className="daisy-stat-title  text-white lg:text-lg text-sm">
            Votados
          </div>
          <div className="daisy-stat-value text-warning lg:text-2xl text-xl w-[100px]">
            {usersVoted.length}
          </div>
        </div>
      </div>
    </div>
  );
};

export default HomeCard;
