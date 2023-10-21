import { Request, Response } from 'express';
import pool from '../database';

class MedicationsController {

    public async list(req: Request, res: Response) {
        try {
            const medications = await pool.query('SELECT * FROM tmedicamentos');
            res.json(medications);
        } catch (error) {
            console.log(error);
            res.status(500).json({ text: 'Internal Error', error: error });
        }
    }
    
    public async getOne(req: Request, res: Response): Promise<any> {
        try {
            const { id } = req.params;
            const game = await pool.query('SELECT * FROM tmedicamentos WHERE identificacion = ?', [id]);
            if (game.length > 0) {
                return res.json(game[0]);
            }
            res.status(404).json({ text: "The album doesn't exists" });
        } catch (error) {
            console.log(error);
            res.status(500).json({ text: 'Internal Error', error: error });
        }
    }

    public async create(req: Request, res: Response): Promise<void> {
        try {
            await pool.query('INSERT INTO tmedicamentos set ?', [req.body]);
            res.json({ message: 'Album saved' });
        } catch (error) {
            console.log(error);
            res.status(500).json({ text: 'Internal Error', error: error });
        }
    }

    public async update(req: Request, res: Response): Promise<void> {
        try {
            const { id } = req.params;
            await pool.query('UPDATE tmedicamentos set ? WHERE identificacion = ?', [req.body, id]);
            res.json({ message: 'The album was updated' });
        } catch (error) {
            console.log(error);
            res.status(500).json({ text: 'Internal Error', error: error });
        }
    }

    public async delete(req: Request, res: Response): Promise<void> {
        try {
            const { id } = req.params;
            await pool.query('DELETE FROM tmedicamentos WHERE identificacion = ?', [id]);
            res.json({ message: 'The album was deleted' });
        } catch (error) {
            console.log(error);
            res.status(500).json({ text: 'Internal Error', error: error });
        }
    }

}

const medicationsController = new MedicationsController();
export default medicationsController;