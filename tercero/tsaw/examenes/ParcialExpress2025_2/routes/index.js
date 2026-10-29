const express = require("express");
const router = express.Router();

const planetasController = require('../controllers/planetas');
const especiesController = require("../controllers/especies");

router.get("/", planetasController.listarPlanetas);
router.get("/:diametroMinimo/:diametroMaximo", planetasController.filtrarPlanetas);
router.get("/especies/:id", especiesController.infoEspecie);
router.post("/especies/guardar", especiesController.guardarEspecie)

module.exports = router;
