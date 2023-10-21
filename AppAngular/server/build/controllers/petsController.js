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
class PetsController {
    list(req, res) {
        return __awaiter(this, void 0, void 0, function* () {
            try {
                const pets = yield database_1.default.query('SELECT tmascotas.identificacion, tmascotas.nombre, tmascotas.raza, tmascotas.edad, tmascotas.peso, tclientes.nombre as clienteNombre, tclientes.segundo_nombre, tclientes.apellido_paterno, tclientes.apellido_materno FROM tmascotas INNER JOIN tclientes ON tmascotas.tclientes_cedula = tclientes.cedula');
                res.json(pets);
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
                const pet = yield database_1.default.query('SELECT * FROM tmascotas WHERE identificacion = ?', [id]);
                if (pet.length > 0) {
                    return res.json(pet[0]);
                }
                res.status(404).json({ text: "The pet doesn't exists" });
            }
            catch (error) {
                console.log(error);
                res.status(500).json({ text: 'Internal Error', error: error });
            }
        });
    }
    getByOwner(req, res) {
        return __awaiter(this, void 0, void 0, function* () {
            try {
                const { id } = req.params;
                const pet = yield database_1.default.query('SELECT tmascotas.identificacion, tmascotas.nombre, tmascotas.raza, tmascotas.edad, tmascotas.peso, tmascotas.tclientes_identificacion as idCliente, tmascotas.tclientes_cedula as cedula, tclientes.nombre AS clienteNombre, tclientes.segundo_nombre, tclientes.apellido_paterno, tclientes.apellido_materno FROM tmascotas INNER JOIN tclientes ON tmascotas.tclientes_cedula = tclientes.cedula WHERE tmascotas.tclientes_cedula = ?', [id]);
                if (pet.length > 0) {
                    return res.json(pet);
                }
                res.status(404).json({ text: "The pet doesn't exists" });
            }
            catch (error) {
                console.log(error);
                res.status(500).json({ text: 'Internal Error', error: error });
            }
        });
    }
    getByOwnerAndPet(req, res) {
        return __awaiter(this, void 0, void 0, function* () {
            try {
                const { idCliente, idMascota } = req.params;
                const pet = yield database_1.default.query('SELECT tmascotas.identificacion, tmascotas.nombre, tmascotas.raza, tmascotas.edad, tmascotas.peso, tclientes.nombre as clienteNombre, tclientes.segundo_nombre, tclientes.apellido_paterno, tclientes.apellido_materno FROM tmascotas INNER JOIN tclientes ON tmascotas.tclientes_cedula = tclientes.cedula WHERE tmascotas.tclientes_cedula = ? AND tmascotas.identificacion = ?', [idCliente, idMascota]);
                if (pet.length > 0) {
                    return res.json(pet[0]);
                }
                res.status(404).json({ text: "The pet doesn't exists" });
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
                yield database_1.default.query('INSERT INTO tmascotas set ?', [req.body]);
                res.json({ message: 'Pet saved' });
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
                yield database_1.default.query('UPDATE tmascotas set ? WHERE identificacion = ?', [req.body, id]);
                res.json({ message: 'The pet was updated' });
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
                yield database_1.default.query('DELETE FROM tmascotas WHERE identificacion = ?', [id]);
                res.json({ message: 'The pet was deleted' });
            }
            catch (error) {
                console.log(error);
                res.status(500).json({ text: 'Internal Error', error: error });
            }
        });
    }
    eliminarMascotaYDetalles(req, res) {
        return __awaiter(this, void 0, void 0, function* () {
            try {
                const { id } = req.params;
                yield database_1.default.query('CALL EliminarMascotaYDetalles(?)', [id]);
                res.json({ message: 'Mascota y detalles eliminados con éxito' });
            }
            catch (error) {
                console.log(error);
                res.status(500).json({ text: 'Error interno', error });
            }
        });
    }
}
const petsController = new PetsController();
exports.default = petsController;
