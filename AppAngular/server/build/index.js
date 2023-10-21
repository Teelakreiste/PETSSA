"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = __importDefault(require("express"));
const morgan_1 = __importDefault(require("morgan"));
const cors_1 = __importDefault(require("cors"));
const indexRoutes_1 = __importDefault(require("./routes/indexRoutes"));
const medicationsRoutes_1 = __importDefault(require("./routes/medicationsRoutes"));
const clientsRoutes_1 = __importDefault(require("./routes/clientsRoutes"));
const petsRoutes_1 = __importDefault(require("./routes/petsRoutes"));
const detailsRoutes_1 = __importDefault(require("./routes/detailsRoutes"));
const allRoutes_1 = __importDefault(require("./routes/allRoutes"));
class Server {
    constructor() {
        this.app = (0, express_1.default)();
        this.config();
        this.routes();
    }
    config() {
        this.app.set('port', process.env.PORT || 3000);
        this.app.use((0, morgan_1.default)('dev'));
        this.app.use((0, cors_1.default)());
        this.app.use(express_1.default.json());
        this.app.use(express_1.default.urlencoded({ extended: false }));
    }
    routes() {
        this.app.use('/', indexRoutes_1.default);
        this.app.use('/api/petssa/medications', medicationsRoutes_1.default);
        this.app.use('/api/petssa/clients', clientsRoutes_1.default);
        this.app.use('/api/petssa/pets', petsRoutes_1.default);
        this.app.use('/api/petssa/details', detailsRoutes_1.default);
        this.app.use('/api/petssa/all', allRoutes_1.default);
    }
    start() {
        this.app.listen(this.app.get('port'), () => {
            console.log('Server on port', this.app.get('port'));
        });
    }
}
const server = new Server();
server.start();
