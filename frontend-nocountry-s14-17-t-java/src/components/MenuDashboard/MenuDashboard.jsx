
export default function MenuDashboard() {
  return (
    <div className='w-full h-[58px] flex justify-around items-center mb-4'>
      <h2 className='text-3xl text-white font-semibold'>
        Panel del administrador
      </h2>
      <label
        htmlFor='my-drawer'
        className='m-4 cursor-pointer daisy-drawer-button w-[160px] h-[38px] border-[1px] border-[#09FCA7] bg-black rounded-[5px] px-[20px] py-[8px] text-center text-[#09FCA7] no-underline'
      >
        Menu
      </label>
    </div>
  );
}
