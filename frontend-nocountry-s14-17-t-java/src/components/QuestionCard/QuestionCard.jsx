import React from "react";

const QuestionCard = ({ children }) => {
  return (
    <div className="w-1000 h-350 rounded-3xl bg-gradient-to-b from-blue-400 to-#06071B p-20 flex-col flex mt-40">
      {children}
    </div>
  );
};

export default QuestionCard;