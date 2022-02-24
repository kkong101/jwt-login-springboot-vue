export const debounce = (func, wait, instance) => {
  let inDebounce;
  return () => {
    console.log("gdgdgd");
    const context = instance;
    const args = arguments;
    clearTimeout(inDebounce);
    inDebounce = setTimeout(() => func.apply(context, args), wait);
  };
};
