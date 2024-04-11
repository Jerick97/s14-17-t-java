import { Link, useRouteError } from "react-router-dom";

const Error = () => {
  const error = useRouteError();
  return (
    <div className="w-full min-h-screen flex flex-col items-center justify-center bg-[#06071B]">
      <div className="flex items-center justify-center gap-5">
        <h2 className="md:text-[5rem] text-[3rem] font-bold bg-gradient-to-r from-blue-600 via-green-500 to-indigo-400 inline-block text-transparent bg-clip-text">
          Algo salio mal
        </h2>
      </div>
      <div className="md:w-1200 w-[300px] h-350 rounded-3xl bg-gradient-to-b from-blue-400 to-#06071B p-20 flex-col flex items-center">
        <h3 className="md:text-2xl text-xl text-center font-medium mb-2 text-white ">
          {" "}
          {error.statusText || error.message}
        </h3>
        <h3 className="font-bold text-white md:text-[6rem] text-[4rem]">404</h3>
        <Link to={"/"}>
          <button className="w-[160px] h-[38px] border-[1px] border-[#09FCA7] bg-black rounded-[5px] px-[20px] py-[8px] text-[#09FCA7] hover:shadow-[0px_0px_10px_0px_#09FCA7] no-underline">
            Volver al inicio
          </button>
        </Link>
      </div>
    </div>
  );
};

export default Error;
