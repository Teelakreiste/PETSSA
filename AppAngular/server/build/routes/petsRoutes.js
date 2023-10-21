"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = require("express");
const petsController_1 = __importDefault(require("../controllers/petsController"));
class PetsRoutes {
    constructor() {
        this.router = (0, express_1.Router)();
        this.config();
    }
    config() {
        this.router.get('/', petsController_1.default.list);
        this.router.get('/:id', petsController_1.default.getOne);
        this.router.get('/owner/:id', petsController_1.default.getByOwner);
        this.router.get('/owner/:idCliente/:idMascota', petsController_1.default.getByOwnerAndPet);
        this.router.post('/', petsController_1.default.create);
        this.router.put('/:id', petsController_1.default.update);
        this.router.delete('/:id', petsController_1.default.delete);
        this.router.delete('/pet/:id', petsController_1.default.eliminarMascotaYDetalles);
    }
}
const petsRoutes = new PetsRoutes();
exports.default = petsRoutes.router;
