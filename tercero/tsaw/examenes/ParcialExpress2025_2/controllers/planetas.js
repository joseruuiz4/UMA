const initModels = require("../models/init-models");
const sequelize = require("sequelize");
const { Op } = require("sequelize")
const models = initModels(sequelize);

const controller = {};

controller.listarPlanetas = async function (req, res, next){
    try {
        const planetas = await models.Planeta.findAll();
        const especies = await  models.Especie.findAll();


        res.render("index", {planetas: planetas, especies: especies, title: "Lista de Planetas"});

    }catch (error){
        res.send("Se ha producido un error" + error);
    }

}

controller.filtrarPlanetas = async function(req, res, next){
    try {

        const planetas = await models.Planeta.findAll({
            where: {
                diametro: {
                    [Op.gt]: req.params.diametroMinimo,
                    [Op.lt]: req.params.diametroMaximo

                }
            }
        });
        const especies = await  models.Especie.findAll();


        res.render("index", {planetas: planetas, especies: especies, title: "Lista de Planetas filtrados"});

    }catch (error){
        res.send("Se ha producido un error" + error);
    }



}


module.exports = controller;