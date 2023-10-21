package controller;

import dao.ClientDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Client;

/**
 *
 * @author osmel
 */
public class ClientController extends HttpServlet {

    private Client client = new Client();
    private ClientDAO clientDAO = new ClientDAO();
    private final String viewClient = "webs/clients/client.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ClientController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClientController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String access = "";
        String action = request.getParameter("action");
        boolean isRequest = true;

        if (action.equalsIgnoreCase("clients")) {
            request.setAttribute("flag", "0");
            access = viewClient;
        } else if (action.equalsIgnoreCase("add")) {
            access = add(request, response);
            isRequest = false;
        } else if (action.equalsIgnoreCase("edit")) {
            access = isEdit(request, response);
        } else if (action.equalsIgnoreCase("save")) {
            access = edit(request, response);
            isRequest = false;
            request.setAttribute("flag", "0");
        } else if (action.equalsIgnoreCase("remove")) {
            access = remove(request, response);
            isRequest = false;
        } else if (action.equalsIgnoreCase("discard")) {
            isRequest = false;
            request.setAttribute("flag", "0");
        }

        if (isRequest) {
            RequestDispatcher view = request.getRequestDispatcher(access);
            view.forward(request, response);
        } else {
            String baseUrl = request.getRequestURI() + "?action=clients"; // Obtiene la URL base sin parámetros
            response.sendRedirect(baseUrl);
        }
    }

    private String view(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("flag", "0");
        return viewClient;
    }

    private String add(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("flag", "0");
        String cedula = request.getParameter("cedula"),
                name = request.getParameter("name"),
                second_name = request.getParameter("second_name"),
                surname = request.getParameter("surname"),
                secondSurname = request.getParameter("second_surname"),
                address = request.getParameter("address"),
                phone = request.getParameter("phone");

        if (!cedula.equals("") && !name.equals("") && !second_name.equals("") && !surname.equals("")
                && !secondSurname.equals("") && !address.equals("") && !phone.equals("")) {
            client.setCedula(cedula);
            client.setName(name);
            client.setSecondName(second_name);
            client.setSurname(surname);
            client.setSecondSurname(secondSurname);
            client.setAddress(address);
            client.setPhone(phone);
            clientDAO.add(client);
        }
        return viewClient;
    }

    private String isEdit(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("flag", "1");
        request.setAttribute("idCli", request.getParameter("id"));
        return viewClient;
    }

    private String edit(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt((String) request.getParameter("id"));
        String cedula = request.getParameter("cedula"),
                name = request.getParameter("name"),
                second_name = request.getParameter("second_name"),
                surname = request.getParameter("surname"),
                secondSurname = request.getParameter("second_surname"),
                address = request.getParameter("address"),
                phone = request.getParameter("phone");

        client.setIdentification(id);
        client.setCedula(cedula);
        client.setName(name);
        client.setSecondName(second_name);
        client.setSurname(surname);
        client.setSecondSurname(secondSurname);
        client.setAddress(address);
        client.setPhone(phone);
        clientDAO.edit(client);
        return viewClient;
    }

    private String remove(HttpServletRequest request, HttpServletResponse response) {
        String id = (String) request.getParameter("id");
        if (clientDAO.delete(id)) {

        }
        return viewClient;
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
