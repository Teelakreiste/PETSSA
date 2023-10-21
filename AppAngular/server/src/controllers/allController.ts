import { Request, Response } from 'express';
import pool from '../database';

class AllController {

    public async list(req: Request, res: Response) {
        try {
            const all = await pool.query('SELECT tclientes.cedula, tclientes.nombre, COALESCE(tclientes.segundo_nombre, "") AS segundo_nombre, tclientes.apellido_paterno, tclientes.apellido_materno, tclientes.telefono, tclientes.direccion, tmascotas.nombre AS mascota_nombre, tmascotas.edad, tmascotas.raza, tmascotas.peso, GROUP_CONCAT(tmedicamentos.nombre) AS medicamentos, GROUP_CONCAT(tmedicamentos.descripcion) AS descripciones, GROUP_CONCAT(tdetalles.dosis) AS dosis FROM tclientes INNER JOIN tmascotas ON tclientes.cedula = tmascotas.tclientes_cedula LEFT JOIN tdetalles ON tmascotas.identificacion = tdetalles.tmascotas_identificacion LEFT JOIN tmedicamentos ON tdetalles.tmedicamentos_identificacion = tmedicamentos.identificacion GROUP BY tclientes.cedula, tmascotas.nombre');
            res.json(all);
        } catch (error) {
            console.log(error);
            res.status(500).json({ text: 'Internal Error', error: error });
        }
    }

    public async getOne(req: Request, res: Response) {
        try {
            const { id } = req.params;
            const all = await pool.query('SELECT tclientes.cedula, tclientes.nombre, COALESCE(tclientes.segundo_nombre, "") AS segundo_nombre, tclientes.apellido_paterno, tclientes.apellido_materno, tclientes.telefono, tclientes.direccion, tmascotas.nombre AS mascota_nombre, tmascotas.edad, tmascotas.raza, tmascotas.peso, GROUP_CONCAT(tmedicamentos.nombre) AS medicamentos, GROUP_CONCAT(tmedicamentos.descripcion) AS descripciones, GROUP_CONCAT(tdetalles.dosis) AS dosis FROM tclientes INNER JOIN tmascotas ON tclientes.cedula = tmascotas.tclientes_cedula LEFT JOIN tdetalles ON tmascotas.identificacion = tdetalles.tmascotas_identificacion LEFT JOIN tmedicamentos ON tdetalles.tmedicamentos_identificacion = tmedicamentos.identificacion WHERE tclientes.cedula = ? GROUP BY tclientes.cedula, tmascotas.nombre', [id]);
            res.json(all);
        } catch (error) {
            console.log(error);
            res.status(500).json({ text: 'Internal Error', error: error });
        }
    }

    public async getMedicationByClient(req: Request, res: Response) {
        try {
            const { id } = req.params;
            const medications = await pool.query('SELECT tc.cedula AS Cedula_Cliente, tc.nombre AS Nombre_Cliente, tmed.nombre AS Nombre_Medicamento, tmed.descripcion AS Descripcion_Medicamento FROM tclientes tc JOIN tmascotas tm ON tc.cedula = tm.tclientes_cedula JOIN tdetalles td ON tm.identificacion = td.tmascotas_identificacion JOIN tmedicamentos tmed ON td.tmedicamentos_identificacion = tmed.identificacion WHERE tc.cedula = ?', [id]);
            res.json(medications);
        } catch (error) {
            console.log(error);
            res.status(500).json({ text: 'Internal Error', error: error });
        }
    }
}

const allController = new AllController();
export default allController;