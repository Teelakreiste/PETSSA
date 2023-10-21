import { Router } from "express";
import  allController  from '../controllers/allController';

class AllRoutes {

    public router: Router = Router();

    constructor() {
        this.config();
    }

    config(): void {
        this.router.get('/', allController.list);
        this.router.get('/:id', allController.getOne);
        this.router.get('/medications/:id', allController.getMedicationByClient);
    }
}

const allRoutes = new AllRoutes();
export default allRoutes.router;