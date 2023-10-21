import { Router } from "express";

import  medicationsController  from '../controllers/medicationsController';

class MedicationsRoutes {

    public router: Router = Router();

    constructor() {
        this.config();
    }

    config(): void {
        this.router.get('/', medicationsController.list);
        this.router.get('/:id', medicationsController.getOne);
        this.router.post('/', medicationsController.create);
        this.router.put('/:id', medicationsController.update);
        this.router.delete('/:id', medicationsController.delete);
    }
}

const medicationsRoutes = new MedicationsRoutes();
export default medicationsRoutes.router;