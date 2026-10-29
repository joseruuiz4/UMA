const initModels = require("../models/init-models");
const sequelize = require("sequelize");
const models = initModels(sequelize);

const controller = {};

// Listar frases de pelicula /////////////////////////////////////////////////////////////////////////////////////////////////////
controller.listarFrasesDePelicula = async function (req, res, next) {
    try {
        const frases = await models.Frase_Celebre.findAll({
            where: {
                pelicula: req.params.id
            }
        });

        const pelicula = await models.Pelicula.findOne({
            where: {
                pelicula_id: req.params.id
            }
        })

        let personajes = [];

        for (let i = 0; i < frases.length; i++) {
            const personaje = await models.Personaje.findOne({
                where: {
                    personaje_id: frases[i].personaje
                }
            })
            personajes.push(personaje.nombre);
        }


        res.render("frasesPelicula", {frases: frases, pelicula: pelicula, personajes: personajes});

    } catch (error) {
        res.send("Se ha producido un error " + error);
    }
};

controller.crearFrase = async function(req, res, next){
    const idPelicula = req.params.idPelicula;

    const personajesPelicula = await models.Pelicula_Personaje.findAll({
        attributes: ["personaje"],
        where:{
            pelicula: idPelicula
        }
    });


    let idPersonajesPeliculas = [];

    personajesPelicula.forEach((personaje) => {
        idPersonajesPeliculas.push(personaje.dataValues.personaje);
    })

    const todosPersonajes = await models.Personaje.findAll();

    console.log(idPersonajesPeliculas);



    res.render("fraseCrear", {idPelicula: idPelicula, idPersonajesPeliculas: idPersonajesPeliculas, todosPersonajes: todosPersonajes})

}

controller.guardar = async function (req, res, next) {
    try {

            await models.Frase_Celebre.create(
                {pelicula: req.body.idPelicula,
                    frase: req.body.frase,
                    personaje: req.body.personaje}
            );

        res.redirect('/frases/' + req.body.idPelicula);

    } catch (error) {
        res.send("Se ha producido un error " + error);
    }
};


module.exports = controller;