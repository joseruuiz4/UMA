const initModels = require("../models/init-models");
const sequelize = require("sequelize");
const models = initModels(sequelize);

const controller = {};

controller.infoEspecie = async function (req, res, next){
    try {
        const especie = await models.Especie.findOne({
            where: {
                especie_id: req.params.id
            }
        });

        const personajes = await models.Personaje.findAll({
            where:{
                especie: req.params.id
            }
        })

        const familias = await models.Familia_Especie.findAll();


        res.render("especie", {especie: especie, personajes: personajes, familias: familias, title: "Edicion de especie"});

    }catch (error){
        res.send("Se ha producido un error" + error);
    }
}

controller.guardarEspecie = async function(req, res, next){
    try {
        if (typeof req.body.especie_id != "undefined") {
            const especie = await models.Especie.findOne({
                where: {
                    especie_id: req.body.especie_id
                }
            });

            if (especie) {
                await especie.update(
                    {
                        peso_medio: req.body.peso_medio,
                        esperanza_vida: req.body.esperanza_vida,
                        clasificacion: req.body.clasificacion,
                    }
                );
            }

        }
        res.redirect('/');

    }catch (error){
        res.send("Se ha producido un error" + error);
    }

}



module.exports = controller;