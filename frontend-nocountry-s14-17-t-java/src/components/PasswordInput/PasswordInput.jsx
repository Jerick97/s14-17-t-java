import lock from "@assets/Inputs/lock.png";
export default function PasswordInput() {
  return (
    <div className='flex relative items-center '>
      <input
        type='password'
        className='w-full rounded-full  pl-5 pr-16 h-9 focus:outline-none'
        placeholder='Password'
      />
      <div className='bg-white absolute right-0   w-12 h-12 rounded-full'>
        <img className='p-3' src={lock} alt='user' srcset='' />
      </div>
    </div>
  );
}
