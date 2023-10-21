"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const database_1 = __importDefault(require("../database"));
class MedicationsController {
    list(req, res) {
        return __awaiter(this, void 0, void 0, function* () {
            try {
                const medications = yield database_1.default.query('SELECT * FROM tmedicamentos');
                res.json(medications);
            }
            catch (error) {
                console.log(error);
                res.status(500).json({ text: 'Internal Error', error: error });
            }
        });
    }
    getOne(req, res) {
        return __awaiter(this, void 0, void 0, function* () {
            try {
                const { id } = req.params;
                const game = yield database_1.default.query('SELECT * FROM tmedicamentos WHERE identificacion = ?', [id]);
                if (game.length > 0) {
                    return res.json(game[0]);
                }
                res.status(404).json({ text: "The album doesn't exists" });
            }
            catch (error) {
                console.log(error);
                res.status(500).json({ text: 'Internal Error', error: error });
            }
        });
    }
    create(req, res) {
        return __awaiter(this, void 0, void 0, function* () {
            try {
                yield database_1.default.query('INSERT INTO tmedicamentos set ?', [req.body]);
                res.json({ message: 'Album saved' });
            }
            catch (error) {
                console.log(error);
                res.status(500).json({ text: 'Internal Error', error: error });
            }
        });
    }
    update(req, res) {
        return __awaiter(this, void 0, void 0, function* () {
            try {
                const { id } = req.params;
                yield database_1.default.query('UPDATE tmedicamentos set ? WHERE identificacion = ?', [req.body, id]);
                res.json({ message: 'The album was updated' });
            }
            catch (error) {
                console.log(error);
                res.status(500).json({ text: 'Internal Error', error: error });
            }
        });
    }
    delete(req, res) {
        return __awaiter(this, void 0, void 0, function* () {
            try {
                const { id } = req.params;
                yield database_1.default.query('DELETE FROM tmedicamentos WHERE identificacion = ?', [id]);
                res.json({ message: 'The album was deleted' });
            }
            catch (error) {
                console.log(error);
                res.status(500).json({ text: 'Internal Error', error: error });
            }
        });
    }
}
const medicationsController = new MedicationsController();
exports.default = medicationsController;
