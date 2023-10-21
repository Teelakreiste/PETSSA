"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = require("express");
const medicationsController_1 = __importDefault(require("../controllers/medicationsController"));
class MedicationsRoutes {
    constructor() {
        this.router = (0, express_1.Router)();
        this.config();
    }
    config() {
        this.router.get('/', medicationsController_1.default.list);
        this.router.get('/:id', medicationsController_1.default.getOne);
        this.router.post('/', medicationsController_1.default.create);
        this.router.put('/:id', medicationsController_1.default.update);
        this.router.delete('/:id', medicationsController_1.default.delete);
    }
}
const medicationsRoutes = new MedicationsRoutes();
exports.default = medicationsRoutes.router;
