import ButtonNeon from "../ButtonNeon/ButtonNeon";
const DashBoardNavbar = () => {
  return (
    <div className="daisy-drawer">
      <input id="my-drawer" type="checkbox" className="daisy-drawer-toggle" />
      <div className="daisy-drawer-content w-full md:h-[200px] h-auto flex flex-row md:flex-col items-center justify-around">
        {/* Page content here */}
        <label
          htmlFor="my-drawer"
          className=" daisy-drawer-button w-[160px] h-[38px] border-[1px] border-[#09FCA7] bg-black rounded-[5px] px-[20px] py-[8px] text-center text-[#09FCA7] hover:shadow-[0px_0px_10px_0px_#09FCA7] no-underline"
        >
          Menu
        </label>
      </div>
      <div className="daisy-drawer-side ">
        <label
          htmlFor="my-drawer"
          aria-label="close sidebar"
          className="daisy-drawer-overlay"
        ></label>
        <ul className="daisy-menu p-4 w-80 min-h-full bg-black/30 backdrop-filter backdrop-blur-sm  text-lg text-white font-bold ">
          {/* Sidebar content here */}
          <li>
            <a>Sidebar Item 1</a>
          </li>
          <li>
            <a>Sidebar Item 2</a>
          </li>
        </ul>
      </div>
    </div>
  );
};

export default DashBoardNavbar;
