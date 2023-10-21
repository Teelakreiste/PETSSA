/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DetailDAO;
import dao.PetDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Detail;
import model.Pet;

/**
 *
 * @author osmel
 */
public class PetController extends HttpServlet {

    private Pet pet = new Pet();
    private PetDAO petDAO = new PetDAO();
    private final String viewPets = "webs/pets/viewPets.jsp";
    private final String formPets = "webs/pets/new-pet.jsp";

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
            out.println("<title>Servlet PetController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PetController at " + request.getContextPath() + "</h1>");
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
        String partUrl = "?action=viewPets";
        boolean isRequest = true;
        if (action.equalsIgnoreCase("viewPets")) {
            access = viewPets(request, response);
        } else if (action.equalsIgnoreCase("medication-remove")) {
            partUrl = remove(request, response);
            isRequest = false;
        } else if (action.equalsIgnoreCase("new-pet")) {
            access = newPet(request, response);
        } else if (action.equalsIgnoreCase("edit-pet")) {
            access = editPet(request, response);
        } else if (action.equalsIgnoreCase("pet-remove")) {
            int id = Integer.parseInt((String) request.getParameter("id"));
            petDAO.delete(id);
            isRequest = false;
        } else if (action.equalsIgnoreCase("pet-add")) {
            partUrl = add(request, response);
            isRequest = false;
        } else if (action.equalsIgnoreCase("pet-save")) {
            partUrl = petSave(request, response);
            isRequest = false;
        } else if (action.equalsIgnoreCase("discard")) {
            partUrl = discard(request, response);
            isRequest = false;
        } else if (action.equalsIgnoreCase("add-dosage")) {
            request.setAttribute("flag", "1");
            partUrl = addDosage(request, response);
            isRequest = false;
        }

        if (isRequest) {
            RequestDispatcher view = request.getRequestDispatcher(access);
            view.forward(request, response);
        } else {
            String baseUrl = request.getRequestURI() + partUrl; // Obtiene la URL base sin parámetros
            response.sendRedirect(baseUrl);
        }
    }

    private String viewPets(HttpServletRequest request, HttpServletResponse response) {
        String access;
        access = viewPets;
        try {
            String id = (String) request.getParameter("ic");
            if (!id.isEmpty()) {
                if (petDAO.qtyPet(id) <= 0) {
                    access = formPets;
                    request.setAttribute("flag", "0");
                }
            }
        } catch (Exception e) {
            access = viewPets;
        }
        request.setAttribute("id", request.getParameter("id"));
        request.setAttribute("ic", request.getParameter("ic"));
        return access;
    }

    private String newPet(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("id", request.getParameter("id"));
        request.setAttribute("ic", request.getParameter("ic"));
        request.setAttribute("flag", "0");
        return formPets;
    }

    private String editPet(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("id", request.getParameter("id"));
        request.setAttribute("ic", request.getParameter("ic"));
        request.setAttribute("ip", request.getParameter("ip"));
        request.setAttribute("flag", "1");
        return formPets;
    }

    private String add(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("id", request.getParameter("id"));
        request.setAttribute("ic", request.getParameter("ic"));
        request.setAttribute("flag", "0");
        int id = Integer.parseInt((String) request.getParameter("id")),
                age = Integer.parseInt((String) request.getParameter("age"));
        String cedula = request.getParameter("ic"),
                name = request.getParameter("name"),
                breed = request.getParameter("breed");
        float weight = Float.valueOf(((String) request.getParameter("weight")));

        pet.setName(name);
        pet.setBreed(breed);
        pet.setAge(age);
        pet.setWeight(weight);
        pet.setOwnerId(id);
        pet.setOwnerCedula(cedula);
        petDAO.add(pet);
        return "?action=viewPets&id=" + request.getParameter("id") + "&ic=" + request.getParameter("ic");
    }

    private String remove(HttpServletRequest request, HttpServletResponse response) {
        int petId = Integer.parseInt((String) request.getParameter("petId"));
        int medId = Integer.parseInt((String) request.getParameter("medId"));

        DetailDAO detailDAO = new DetailDAO();
        detailDAO.delete(petId, medId);
        return "?action=edit-pet&id=" + (String) request.getParameter("id")
                + "&ic=" + (String) request.getParameter("ic") + "&ip=" + (String) request.getParameter("petId");
    }

    private String petSave(HttpServletRequest request, HttpServletResponse response) {
        int ip = Integer.parseInt((String) request.getParameter("ip"));
        int id = Integer.parseInt((String) request.getParameter("id"));
        String ic = (String) request.getParameter("ic");

        String name = (String) request.getParameter("name");
        String breed = (String) request.getParameter("breed");
        int age = Integer.parseInt((String) request.getParameter("age"));
        float weight = Float.valueOf((String) request.getParameter("weight"));

        pet.setIdentification(ip);
        pet.setName(name);
        pet.setBreed(breed);
        pet.setAge(age);
        pet.setWeight(weight);
        pet.setOwnerId(id);
        pet.setOwnerCedula(ic);
        petDAO.edit(pet);

        return "?action=viewPets&id=" + id + "&ic=" + ic;
    }

    private String addDosage(HttpServletRequest request, HttpServletResponse response) {
        int ip = Integer.parseInt((String) request.getParameter("ipD"));
        int id = Integer.parseInt((String) request.getParameter("idD"));
        int ic = Integer.parseInt((String) request.getParameter("icD"));

        int idM = Integer.parseInt((String) request.getParameter("medicamentos"));
        String dosage = request.getParameter("dosis");

        Detail detail = new Detail();
        detail.setPetIdentification(ip);
        detail.setMedicationIdentification(idM);
        detail.setDosage(dosage);
        DetailDAO detailDAO = new DetailDAO();
        detailDAO.add(detail);

        return "?action=edit-pet&id=" + id + "&ic=" + ic + "&ip=" + ip;
    }

    private String discard(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("id", request.getParameter("id"));
        request.setAttribute("ic", request.getParameter("ic"));
        request.setAttribute("flag", "0");
        return "?action=viewPets&id=" + request.getParameter("id") + "&ic=" + request.getParameter("ic");
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
