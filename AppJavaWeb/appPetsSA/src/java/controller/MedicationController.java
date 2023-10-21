/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.MedicationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Medication;

/**
 *
 * @author osmel
 */
public class MedicationController extends HttpServlet {

    private Medication medication = new Medication();
    private MedicationDAO medicationDAO = new MedicationDAO();
    private final String viewMedication = "webs/medications/medication.jsp";

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
            out.println("<title>Servlet MedicationController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MedicationController at " + request.getContextPath() + "</h1>");
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
        if (action.equalsIgnoreCase("medication")) {
            access = view(request, response);
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
            request.setAttribute("flag", "0");
        } else if (action.equalsIgnoreCase("discard")) {
            isRequest = false;
            request.setAttribute("flag", "0");
        }

        if (isRequest) {
            RequestDispatcher view = request.getRequestDispatcher(access);
            view.forward(request, response);
        } else {
            String baseUrl = request.getRequestURI() + "?action=medication"; // Obtiene la URL base sin parámetros
            response.sendRedirect(baseUrl);
        }
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

    private String view(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("flag", "0");
        return viewMedication;
    }

    private String add(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("flag", "0");
        String name = request.getParameter("name"),
                description = request.getParameter("description");
        if (!name.equals("") && !name.equals("")) {
            medication.setName(name);
            medication.setDescription(description);
            medicationDAO.add(medication);
        }
        return viewMedication;
    }

    private String isEdit(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("flag", "1");
        request.setAttribute("idMed", request.getParameter("id"));
        return viewMedication;
    }

    private String edit(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt((String) request.getParameter("id"));
        String name = request.getParameter("name"),
                description = request.getParameter("description");

        medication.setIdentification(id);
        medication.setName(name);
        medication.setDescription(description);
        medicationDAO.edit(medication);
        return viewMedication;
    }

    private String remove(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt((String) request.getParameter("id"));
        medicationDAO.delete(id);
        return viewMedication;
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
