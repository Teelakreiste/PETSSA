"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = require("express");
const logController_1 = __importDefault(require("../controllers/logController"));
class DetailsRoutes {
    constructor() {
        this.router = (0, express_1.Router)();
        this.config();
    }
    // Esta funcion configura las rutas de la clase DetailsRoutes donde se unian las rutas de los clientes y las mascotas con los detalles de las mascotas
    config() {
        this.router.get('/', logController_1.default.list);
        this.router.post('/', logController_1.default.create);
        this.router.put('/:id', logController_1.default.update);
        this.router.delete('/:id', logController_1.default.delete);
    }
}
const detailsRoutes = new DetailsRoutes();
exports.default = detailsRoutes.router;
