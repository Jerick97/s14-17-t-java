export default function Groups({ name }) {
  return (
    <div className='border-[#1D90FC] border-2 cursor-pointer rounded-lg p-3 '>
      <h3 className='text-xl font-bold overflow-hidden whitespace-nowrap overflow-ellipsis'>
        {name}
      </h3>
    </div>
  );
}
