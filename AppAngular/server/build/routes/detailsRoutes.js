"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = require("express");
const detailsController_1 = __importDefault(require("../controllers/detailsController"));
class DetailsRoutes {
    constructor() {
        this.router = (0, express_1.Router)();
        this.config();
    }
    config() {
        this.router.get('/:id', detailsController_1.default.list);
        this.router.post('/', detailsController_1.default.create);
        this.router.put('/update/:masId/:medId', detailsController_1.default.update);
        this.router.delete('/delete/:masId/:medId', detailsController_1.default.delete);
    }
}
const detailsRoutes = new DetailsRoutes();
exports.default = detailsRoutes.router;
