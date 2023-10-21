import { Request, Response } from 'express';
import pool from '../database';

class PetsController {
    public async list(req: Request, res: Response) {
        try {
            const pets = await pool.query('SELECT tmascotas.identificacion, tmascotas.nombre, tmascotas.raza, tmascotas.edad, tmascotas.peso, tclientes.nombre as clienteNombre, tclientes.segundo_nombre, tclientes.apellido_paterno, tclientes.apellido_materno FROM tmascotas INNER JOIN tclientes ON tmascotas.tclientes_cedula = tclientes.cedula');
            res.json(pets);
        } catch (error) {
            console.log(error);
            res.status(500).json({ text: 'Internal Error', error: error });
        }
    }

    public async getOne(req: Request, res: Response): Promise<any> {
        try {
            const { id } = req.params;
            const pet = await pool.query('SELECT * FROM tmascotas WHERE identificacion = ?', [id]);
            if (pet.length > 0) {
                return res.json(pet[0]);
            }
            res.status(404).json({ text: "The pet doesn't exists" });
        } catch (error) {
            console.log(error);
            res.status(500).json({ text: 'Internal Error', error: error });
        }
    }

    public async getByOwner(req: Request, res: Response): Promise<any> {
        try {
            const { id } = req.params;
            const pet = await pool.query('SELECT tmascotas.identificacion, tmascotas.nombre, tmascotas.raza, tmascotas.edad, tmascotas.peso, tmascotas.tclientes_identificacion as idCliente, tmascotas.tclientes_cedula as cedula, tclientes.nombre AS clienteNombre, tclientes.segundo_nombre, tclientes.apellido_paterno, tclientes.apellido_materno FROM tmascotas INNER JOIN tclientes ON tmascotas.tclientes_cedula = tclientes.cedula WHERE tmascotas.tclientes_cedula = ?', [id]);
            if (pet.length > 0) {
                return res.json(pet);
            }
            res.status(404).json({ text: "The pet doesn't exists" });
        } catch (error) {
            console.log(error);
            res.status(500).json({ text: 'Internal Error', error: error });
        }
    }

    public async getByOwnerAndPet(req: Request, res: Response): Promise<any> {
        try {
            const { idCliente, idMascota } = req.params;
            const pet = await pool.query('SELECT tmascotas.identificacion, tmascotas.nombre, tmascotas.raza, tmascotas.edad, tmascotas.peso, tclientes.nombre as clienteNombre, tclientes.segundo_nombre, tclientes.apellido_paterno, tclientes.apellido_materno FROM tmascotas INNER JOIN tclientes ON tmascotas.tclientes_cedula = tclientes.cedula WHERE tmascotas.tclientes_cedula = ? AND tmascotas.identificacion = ?', [idCliente, idMascota]);
            if (pet.length > 0) {
                return res.json(pet[0]);
            }
            res.status(404).json({ text: "The pet doesn't exists" });
        } catch (error) {
            console.log(error);
            res.status(500).json({ text: 'Internal Error', error: error });
        }
    }

    public async create(req: Request, res: Response): Promise<void> {
        try {
            await pool.query('INSERT INTO tmascotas set ?', [req.body]);
            res.json({ message: 'Pet saved' });
        } catch (error) {
            console.log(error);
            res.status(500).json({ text: 'Internal Error', error: error });
        }
    }

    public async update(req: Request, res: Response): Promise<void> {
        try {
            const { id } = req.params;
            await pool.query('UPDATE tmascotas set ? WHERE identificacion = ?', [req.body, id]);
            res.json({ message: 'The pet was updated' });
        } catch (error) {
            console.log(error);
            res.status(500).json({ text: 'Internal Error', error: error });
        }
    }

    public async delete(req: Request, res: Response): Promise<void> {
        try {
            const { id } = req.params;
            await pool.query('DELETE FROM tmascotas WHERE identificacion = ?', [id]);
            res.json({ message: 'The pet was deleted' });
        } catch (error) {
            console.log(error);
            res.status(500).json({ text: 'Internal Error', error: error });
        }
    }

    public async eliminarMascotaYDetalles(req: Request, res: Response): Promise<void> {
        try {
            const { id } = req.params;
            await pool.query('CALL EliminarMascotaYDetalles(?)', [id]);
            res.json({ message: 'Mascota y detalles eliminados con éxito' });
        } catch (error) {
            console.log(error);
            res.status(500).json({ text: 'Error interno', error });
        }
    }
}

const petsController = new PetsController();
export default petsController;