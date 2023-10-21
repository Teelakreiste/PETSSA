import { Request, Response } from 'express';
import pool from '../database';

class ClientsController {

    public async list(req: Request, res: Response) {
        try {
            const clients = await pool.query('SELECT * FROM tclientes');
            res.json(clients);
        } catch (error) {
            console.log(error);
            res.status(500).json({ text: 'Internal Error', error: error });
        }
    }

    public async getOne(req: Request, res: Response): Promise<any> {
        try {
            const { id } = req.params;
            const client = await pool.query('SELECT * FROM tclientes WHERE cedula = ?', [id]);
            if (client.length > 0) {
                return res.json(client[0]);
            }
            res.status(404).json({ text: "The client doesn't exists" });
        } catch (error) {
            console.log(error);
            res.status(500).json({ text: 'Internal Error', error: error });
        }
    }

    public async create(req: Request, res: Response): Promise<void> {
        try {
            await pool.query('INSERT INTO tclientes set ?', [req.body]);
            res.json({ message: 'Client saved' });
        } catch (error) {
            console.log(error);
            res.status(500).json({ text: 'Internal Error', error: error });
        }
    }

    public async update(req: Request, res: Response): Promise<void> {
        try {
            const { id } = req.params;
            await pool.query('UPDATE tclientes set ? WHERE cedula = ?', [req.body, id]);
            res.json({ message: 'The client was updated' });
        } catch (error) {
            console.log(error);
            res.status(500).json({ text: 'Internal Error', error: error });
        }
    }

    public async delete(req: Request, res: Response): Promise<void> {
        try {
            const { id } = req.params;
            await pool.query('DELETE FROM tclientes WHERE cedula = ?', [id]);
            res.json({ message: 'The client was deleted' });
        } catch (error) {
            console.log(error);
            res.status(500).json({ text: 'Internal Error', error: error });
        }
    }

}

const clientsController = new ClientsController();
export default clientsController;