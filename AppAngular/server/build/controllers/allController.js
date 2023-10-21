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
class AllController {
    list(req, res) {
        return __awaiter(this, void 0, void 0, function* () {
            try {
                const all = yield database_1.default.query('SELECT tclientes.cedula, tclientes.nombre, COALESCE(tclientes.segundo_nombre, "") AS segundo_nombre, tclientes.apellido_paterno, tclientes.apellido_materno, tclientes.telefono, tclientes.direccion, tmascotas.nombre AS mascota_nombre, tmascotas.edad, tmascotas.raza, tmascotas.peso, GROUP_CONCAT(tmedicamentos.nombre) AS medicamentos, GROUP_CONCAT(tmedicamentos.descripcion) AS descripciones, GROUP_CONCAT(tdetalles.dosis) AS dosis FROM tclientes INNER JOIN tmascotas ON tclientes.cedula = tmascotas.tclientes_cedula LEFT JOIN tdetalles ON tmascotas.identificacion = tdetalles.tmascotas_identificacion LEFT JOIN tmedicamentos ON tdetalles.tmedicamentos_identificacion = tmedicamentos.identificacion GROUP BY tclientes.cedula, tmascotas.nombre');
                res.json(all);
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
                const all = yield database_1.default.query('SELECT tclientes.cedula, tclientes.nombre, COALESCE(tclientes.segundo_nombre, "") AS segundo_nombre, tclientes.apellido_paterno, tclientes.apellido_materno, tclientes.telefono, tclientes.direccion, tmascotas.nombre AS mascota_nombre, tmascotas.edad, tmascotas.raza, tmascotas.peso, GROUP_CONCAT(tmedicamentos.nombre) AS medicamentos, GROUP_CONCAT(tmedicamentos.descripcion) AS descripciones, GROUP_CONCAT(tdetalles.dosis) AS dosis FROM tclientes INNER JOIN tmascotas ON tclientes.cedula = tmascotas.tclientes_cedula LEFT JOIN tdetalles ON tmascotas.identificacion = tdetalles.tmascotas_identificacion LEFT JOIN tmedicamentos ON tdetalles.tmedicamentos_identificacion = tmedicamentos.identificacion WHERE tclientes.cedula = ? GROUP BY tclientes.cedula, tmascotas.nombre', [id]);
                res.json(all);
            }
            catch (error) {
                console.log(error);
                res.status(500).json({ text: 'Internal Error', error: error });
            }
        });
    }
    getMedicationByClient(req, res) {
        return __awaiter(this, void 0, void 0, function* () {
            try {
                const { id } = req.params;
                const medications = yield database_1.default.query('SELECT tc.cedula AS Cedula_Cliente, tc.nombre AS Nombre_Cliente, tmed.nombre AS Nombre_Medicamento, tmed.descripcion AS Descripcion_Medicamento FROM tclientes tc JOIN tmascotas tm ON tc.cedula = tm.tclientes_cedula JOIN tdetalles td ON tm.identificacion = td.tmascotas_identificacion JOIN tmedicamentos tmed ON td.tmedicamentos_identificacion = tmed.identificacion WHERE tc.cedula = ?', [id]);
                res.json(medications);
            }
            catch (error) {
                console.log(error);
                res.status(500).json({ text: 'Internal Error', error: error });
            }
        });
    }
}
const allController = new AllController();
exports.default = allController;
