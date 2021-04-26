const selected = document.querySelector(".selected");
const optionsContainer = document.querySelector(".option-container");

 const optionsList = document.querySelectorAll(".option");

selected.addEventListener("click", () => {
    optionsContainer.classList.toggle("active");
})

