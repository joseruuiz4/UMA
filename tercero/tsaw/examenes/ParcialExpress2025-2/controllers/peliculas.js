const initModels = require("../models/init-models");
const sequelize = require("sequelize");
const models = initModels(sequelize);

const controller = {};

// Listar peliculas /////////////////////////////////////////////////////////////////////////////////////////////////////
controller.listarPeliculas = async function (req, res, next) {
    try {
        const param = req.query.orden;
        let peliculas = null;
        if(param != null && param === "titulo"){
             peliculas = await models.Pelicula
                .findAll({
                    order: [["titulo", "DESC"]]
                })
        }else if(param != null && param === "anyo"){
             peliculas = await models.Pelicula
                .findAll({
                    order: [["anyo", "ASC"]]
                })
        }else{
            peliculas = await models.Pelicula.findAll()
        }
                res.render("index", {peliculas: peliculas, title: "Lista de peliculas"});
    } catch (error) {
        res.send("Se ha producido un error " + error);
    }
};

module.exports = controller;