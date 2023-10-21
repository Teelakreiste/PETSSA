package org.apache.jsp.webs.pets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.Detail;
import dao.MedicationDAO;
import java.util.Iterator;
import java.util.List;
import model.Pet;
import dao.PetDAO;

public final class viewPets_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Mascotas</title>\n");
      out.write("        <link rel=\"shortcut icon\" href=\"assets/petssa.png\" type=\"image/x-icon\">\n");
      out.write("        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\n");
      out.write("              integrity=\"sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN\" crossorigin=\"anonymous\">\n");
      out.write("        <script src=\"https://kit.fontawesome.com/9fc76cebfc.js\" crossorigin=\"anonymous\"></script>\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/style.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/pets.css\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <header>\n");
      out.write("            <nav class=\"nav\">\n");
      out.write("                <ul class=\"nav-list\">\n");
      out.write("                    <li class=\"nav-item\">\n");
      out.write("                        <a href=\"/appPetsSA\" class=\"btn btn-header text-logo\" routerLink=\"/\">\n");
      out.write("                            <img src=\"assets/petssa.png\" alt=\"\" class=\"img-logo\">PETS S.A.\n");
      out.write("                        </a>\n");
      out.write("                    </li>\n");
      out.write("                    <li class=\"nav-item\">\n");
      out.write("                        <a class=\"btn btn-header\" href=\"ClientController?action=clients\">Clientes\n");
      out.write("                        </a>\n");
      out.write("                    </li>\n");
      out.write("                    <li class=\"nav-item\">\n");
      out.write("                        <a class=\"btn btn-header\" href=\"PetController?action=viewPets\" >Mascotas\n");
      out.write("                        </a>\n");
      out.write("                    </li>\n");
      out.write("                    <li class=\"nav-item\">\n");
      out.write("                        <a class=\"btn btn-header\" href=\"MedicationController?action=medication\" >Medicamentos\n");
      out.write("                        </a>\n");
      out.write("                    </li>\n");
      out.write("                </ul>\n");
      out.write("            </nav>\n");
      out.write("        </header>\n");
      out.write("\n");
      out.write("        <main>\n");
      out.write("            <div class=\"medicamentos\">\n");
      out.write("                <h1 class=\"text-center\">Todas las mascotas</h1>\n");
      out.write("                ");

                    PetDAO objPetDAO = new PetDAO();
                    List<Pet> objListPet = objPetDAO.list();
                    Iterator<Pet> iter = objListPet.iterator();
                    Pet pet = null;

                    MedicationDAO objMedicationDAO = new MedicationDAO();
                    while (iter.hasNext()) {
                        pet = iter.next();
                        List<Detail> objListMedications = objMedicationDAO.listByPet(pet.getIdentification());
                        Iterator<Detail> iterDetail = objListMedications.iterator();
                        Detail detail = null;
                
      out.write("\n");
      out.write("                <div class=\"contenedor\">\n");
      out.write("                    <div class=\"formulario\">\n");
      out.write("                        <h3>Información de la mascota</h3>\n");
      out.write("                        <div class=\"formulario f2\">\n");
      out.write("                            <p class=\"title\">Nombre: <span class=\"text\">");
      out.print( pet.getName());
      out.write("</span></p>\n");
      out.write("                            <p class=\"title\">Raza: <span class=\"text\">");
      out.print( pet.getBreed());
      out.write("</span></p>\n");
      out.write("                            <p class=\"title\">Edad: <span class=\"text\">");
      out.print( pet.getAge());
      out.write("</span></p>\n");
      out.write("                            <p class=\"title\">Peso: <span class=\"text\">");
      out.print( pet.getWeight());
      out.write("</span></p>\n");
      out.write("                            <p class=\"title\">Propietario: <span class=\"text\">");
      out.print( pet.getOwnerCedula());
      out.write("</span></p>\n");
      out.write("                            <div class=\"text-center\">\n");
      out.write("                                <a class=\"btn btn-secondary\">\n");
      out.write("                                    <i class=\"fa-solid fa-pen-to-square\" style=\"color: #ffffff;\"></i>\n");
      out.write("                                    Modificar\n");
      out.write("                                </a>\n");
      out.write("                                <a class=\"btn btn-danger\">\n");
      out.write("                                    <i class=\"fa-solid fa-pen-to-trash\" style=\"color: #ffffff;\"></i>\n");
      out.write("                                    Eliminar\n");
      out.write("                                </a>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"tabla-medicamentos\">\n");
      out.write("                        <h3>Lista de medicamentos</h3>\n");
      out.write("                        <div class=\"table-responsive\">\n");
      out.write("                            <table class=\"table table-borderless\">\n");
      out.write("                                <thead>\n");
      out.write("                                    <tr>\n");
      out.write("                                        <th>Nombre</th>\n");
      out.write("                                        <th>Dosis</th>\n");
      out.write("                                        <th>Acciones</th>\n");
      out.write("                                    </tr>\n");
      out.write("                                </thead>\n");
      out.write("                                <tbody>\n");
      out.write("                                    ");

                                        while (iterDetail.hasNext()) {
                                            detail = iterDetail.next();
                                    
      out.write("\n");
      out.write("                                    <tr>\n");
      out.write("                                        <td>Paracetamol</td>\n");
      out.write("                                        <td>Hasta que se muera.</td>\n");
      out.write("                                        <td class=\"td-acciones\">\n");
      out.write("                                            <a class=\"btn btn-danger\">\n");
      out.write("                                                <i class=\"fa-solid fa-trash\"></i>\n");
      out.write("                                            </a>\n");
      out.write("                                        </td>\n");
      out.write("                                    </tr>\n");
      out.write("                                    <\n");
      out.write("                                </tbody>\n");
      out.write("                            </table>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                ");
}
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </main>\n");
      out.write("\n");
      out.write("        <footer>\n");
      out.write("            <!-- <hr> -->\n");
      out.write("            <div class=\"container\">\n");
      out.write("                <div class=\"row\">\n");
      out.write("                    <div class=\"col-md-12\">\n");
      out.write("                        <p class=\"text-footer\">\n");
      out.write("                            <img class=\"img-logo-footer\" src=\"assets/petssa.png\" alt=\"\">\n");
      out.write("                            <strong>PETS S.A.</strong> © 2023 - Todos los derechos reservados.\n");
      out.write("                        </p>\n");
      out.write("                        <p class=\"text-footer\">\n");
      out.write("                            Desarrollado por: Teelakreiste | <a href=\"https://github.com/Teelakreiste\" target=\"_blank\"><i\n");
      out.write("                                    class=\"fa-brands fa-github\"></i></a>\n");
      out.write("                        </p>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </footer>\n");
      out.write("        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js\"\n");
      out.write("                integrity=\"sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL\"\n");
      out.write("        crossorigin=\"anonymous\"></script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
