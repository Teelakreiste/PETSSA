import { Router } from "express";
import  petsController  from '../controllers/petsController';

class PetsRoutes {

    public router: Router = Router();

    constructor() {
        this.config();
    }

    config(): void {
        this.router.get('/', petsController.list);
        this.router.get('/:id', petsController.getOne);
        this.router.get('/owner/:id', petsController.getByOwner);
        this.router.get('/owner/:idCliente/:idMascota', petsController.getByOwnerAndPet);
        this.router.post('/', petsController.create);
        this.router.put('/:id', petsController.update);
        this.router.delete('/:id', petsController.delete);
        this.router.delete('/pet/:id', petsController.eliminarMascotaYDetalles)
    }
}

const petsRoutes = new PetsRoutes();
export default petsRoutes.router;