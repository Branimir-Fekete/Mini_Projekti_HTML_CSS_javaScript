const counters = document.querySelectorAll(".counter");

counters.forEach((counter) => {
  counter.innerText = "0";

  const target = +counter.getAttribute("data-target");
  const duration = 1000;
  const steps = 200;
  const increment = target / steps;
  let currentValue = 0;

  const updateCounter = () => {
    currentValue += increment;

    if (currentValue < target) {
      counter.innerText = Math.ceil(currentValue);
      setTimeout(updateCounter, duration / steps);
    } else {
      counter.innerText = target;
    }
  };

  updateCounter();
});
