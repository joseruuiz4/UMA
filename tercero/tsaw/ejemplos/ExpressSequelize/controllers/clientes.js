const initModels = require("../models/init-models");
const sequelize = require("sequelize")
const models = initModels(sequelize)

const controller={};


//Listar todos
controller.listarClientes = async function(req, res, next){
    try{
        await models.CUSTOMER
            .findAll()
            .then(async (data) => {

                //res.json(data);
                res.render("index", {clientes: data});
            });

    }catch (error){
        res.send("Se ha producido un error");
    }


}


controller.editarCliente = async function (req, res, next) {
    try {
        const cliente = await models.CUSTOMER.findOne({
            where: {
                CUSTOMER_ID: req.params.id
            }
        });
        const supermercados = await models.MICRO_MARKET.findAll();
        const descuentos = await  models.DISCOUNT_CODE.findAll();
        res.render("cliente", {cliente: cliente, supermercados: supermercados, descuentos: descuentos});

    } catch (error) {
        res.send("Se ha producido un error " + error);
    }
};

module.exports = controller
