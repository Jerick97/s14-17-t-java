import React from 'react'


const Range = (min, max) => {
  return (
    < >
      <input type="range" min={0} max="10" className="daisy-ui daisy-range" step="1" />
              <div className="daisy-ui w-full flex justify-between text-xs px-2">
                <span>0</span>
                <span>|</span>
                <span>|</span>
                <span>|</span>
                <span>|</span>
                <span>5</span>
                <span>|</span>
                <span>|</span>
                <span>|</span>
                <span>|</span>
                <span>10</span>
              </div>
    </>
  )
}

export default Range
