"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.indexController = void 0;
class IndexController {
    index(req, res) {
        try {
            res.send('<p> Welcome to PETS S.A. API - 0.0.1' + '<br>' + 'Developed by: <b>Osmel David Zuñiga Contreras</b></p>');
        }
        catch (error) {
            console.log(error);
            res.status(500).json({ text: 'Internal Error', error: error });
        }
    }
}
exports.indexController = new IndexController();
