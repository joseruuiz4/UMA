const express = require("express");
const router = express.Router();

const peliculasController = require('../controllers/peliculas');
const frasesController = require('../controllers/frases');


router.get("/", peliculasController.listarPeliculas);
router.get("/frases/:id", frasesController.listarFrasesDePelicula);
router.get("/frase/crear/:idPelicula", frasesController.crearFrase);
router.post("/frase/guardar", frasesController.guardar);

module.exports = router;
