import express, { Application } from 'express';
import morgan from 'morgan';
import cors from 'cors';

import indexRoutes from './routes/indexRoutes';
import medicationsRoutes from './routes/medicationsRoutes';
import clientsRoutes from './routes/clientsRoutes';
import petsRoutes from './routes/petsRoutes';
import detailsRoutes from './routes/detailsRoutes';
import allRoutes from './routes/allRoutes';

class Server {
    public app: Application;

    constructor() {
        this.app = express();
        this.config();
        this.routes();
    }

    config(): void {
        this.app.set('port', process.env.PORT || 3000);
        this.app.use(morgan('dev'));
        this.app.use(cors());
        this.app.use(express.json());
        this.app.use(express.urlencoded({extended: false}));
    }

    routes(): void {
        this.app.use('/', indexRoutes);
        this.app.use('/api/petssa/medications', medicationsRoutes);
        this.app.use('/api/petssa/clients', clientsRoutes);
        this.app.use('/api/petssa/pets', petsRoutes);
        this.app.use('/api/petssa/details', detailsRoutes);
        this.app.use('/api/petssa/all', allRoutes);
    }

    start(): void {
        this.app.listen(this.app.get('port'), () => {
            console.log('Server on port', this.app.get('port'));
        });
    }
}
const server = new Server();
server.start();