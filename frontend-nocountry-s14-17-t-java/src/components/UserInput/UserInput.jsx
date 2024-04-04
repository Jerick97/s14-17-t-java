import user from "@assets/Inputs/user.png";
export default function UserInput() {
  return (
    <div className="flex relative items-center mb-5">
      <div className="bg-white absolute w-12 h-12 rounded-full">
        <img className="p-2" src={user} alt="user" />
      </div>
      <input
        type="text"
        className="w-full rounded-full  pl-16 pr-5 h-9 focus:outline-none text-[#acacb5] font-normal bg-[#476468]"
        placeholder="Username"
      />
    </div>
  );
}
