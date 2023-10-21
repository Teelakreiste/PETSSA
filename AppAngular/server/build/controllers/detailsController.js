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
class DetailsController {
    list(req, res) {
        return __awaiter(this, void 0, void 0, function* () {
            try {
                const { id } = req.params;
                const details = yield database_1.default.query('SELECT * FROM tdetalles INNER JOIN tmedicamentos ON tdetalles.tmedicamentos_identificacion = tmedicamentos.identificacion WHERE tmascotas_identificacion = ?', [id]);
                res.json(details);
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
                const { tmascotas_identificacion, tmedicamentos_identificacion, dosis } = req.body;
                yield database_1.default.query('CALL InsertarActualizarDetalle(?, ?, ?)', [tmascotas_identificacion, tmedicamentos_identificacion, dosis]);
                res.json({ message: 'Detail saved' });
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
                const { masId, medId } = req.params;
                yield database_1.default.query('UPDATE tdetalles set ? WHERE tmascotas_identificacion = ? and tmedicamentos_identificacion = ?', [req.body, masId, medId]);
                res.json({ message: 'The detail was updated' });
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
                const { masId, medId } = req.params;
                yield database_1.default.query('DELETE FROM tdetalles WHERE tmascotas_identificacion = ? and tmedicamentos_identificacion = ?', [masId, medId]);
                res.json({ message: 'The detail was deleted' });
            }
            catch (error) {
                console.log(error);
                res.status(500).json({ text: 'Internal Error', error: error });
            }
        });
    }
}
const detailsController = new DetailsController();
exports.default = detailsController;
