let mayormenor = document.getElementById("mayormenor");
let stat = document.getElementById("stat");
let resultado = document.getElementById("resultado");
let submit = document.getElementById("submit");

submit.addEventListener("click", fetchPokemons);
async function fetchPokemons() {
  resultado.innerText = "hola";
}
