import daisyui from "daisyui";
/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
  theme: {
    extend: {
      fontFamily: {
        "dm-sans": ['"DM Sans"', "sans-serif"],
      },
    },
  },
  plugins: [daisyui],
  daisyui: {

    prefix: "daisy-",

  },
  darkMode: "class",
};
