import { Router } from "express";
import  detailsController  from '../controllers/detailsController';

class DetailsRoutes {

    public router: Router = Router();

    constructor() {
        this.config();
    }

    config(): void {
        this.router.get('/:id', detailsController.list);
        this.router.post('/', detailsController.create);
        this.router.put('/update/:masId/:medId', detailsController.update);
        this.router.delete('/delete/:masId/:medId', detailsController.delete);
    }
}

const detailsRoutes = new DetailsRoutes();
export default detailsRoutes.router;