import { Request, Response } from 'express';
import pool from '../database';

class DetailsController {

    public async list(req: Request, res: Response) {
        try {
            const { id } = req.params;
            const details = await pool.query('SELECT * FROM tdetalles INNER JOIN tmedicamentos ON tdetalles.tmedicamentos_identificacion = tmedicamentos.identificacion WHERE tmascotas_identificacion = ?', [id]);
            res.json(details);
        } catch (error) {
            console.log(error);
            res.status(500).json({ text: 'Internal Error', error: error });
        }
    }

    public async create(req: Request, res: Response): Promise<void> {
        try {
            const { tmascotas_identificacion, tmedicamentos_identificacion, dosis } = req.body;
            await pool.query(
                'CALL InsertarActualizarDetalle(?, ?, ?)',
                [tmascotas_identificacion, tmedicamentos_identificacion, dosis]
            );
            res.json({ message: 'Detail saved' });
        } catch (error) {
            console.log(error);
            res.status(500).json({ text: 'Internal Error', error: error });
        }
    }


    public async update(req: Request, res: Response): Promise<void> {
        try {
            const { masId, medId } = req.params;
            await pool.query('UPDATE tdetalles set ? WHERE tmascotas_identificacion = ? and tmedicamentos_identificacion = ?', [req.body, masId, medId]);
            res.json({ message: 'The detail was updated' });
        } catch (error) {
            console.log(error);
            res.status(500).json({ text: 'Internal Error', error: error });
        }
    }

    public async delete(req: Request, res: Response): Promise<void> {
        try {
            const { masId, medId } = req.params;
            await pool.query('DELETE FROM tdetalles WHERE tmascotas_identificacion = ? and tmedicamentos_identificacion = ?', [masId, medId]);
            res.json({ message: 'The detail was deleted' });
        } catch (error) {
            console.log(error);
            res.status(500).json({ text: 'Internal Error', error: error });
        }
    }
}

const detailsController = new DetailsController();
export default detailsController;