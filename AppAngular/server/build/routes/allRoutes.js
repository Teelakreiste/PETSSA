"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = require("express");
const allController_1 = __importDefault(require("../controllers/allController"));
class AllRoutes {
    constructor() {
        this.router = (0, express_1.Router)();
        this.config();
    }
    config() {
        this.router.get('/', allController_1.default.list);
        this.router.get('/:id', allController_1.default.getOne);
        this.router.get('/medications/:id', allController_1.default.getMedicationByClient);
    }
}
const allRoutes = new AllRoutes();
exports.default = allRoutes.router;
