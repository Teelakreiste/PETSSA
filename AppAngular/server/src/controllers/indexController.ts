import { Request, Response } from 'express';

class IndexController {

    public index(req: Request, res: Response) { 
        try {
            res.send('<p> Welcome to PETS S.A. API - 0.0.1' + '<br>' + 'Developed by: <b>Osmel David Zuñiga Contreras</b></p>');
        }
        catch (error) {
            console.log(error);
            res.status(500).json({ text: 'Internal Error', error: error });
        } 
    }

}

export const indexController = new IndexController();