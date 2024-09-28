/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ahmed abbas
 */
@WebServlet(name = "DynamicImageServlet", urlPatterns = {"/images/dynamic/*"})
public class DynamicImageServlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DynamicImageServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DynamicImageServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = null;
        try {
            // Get image file.
            param = request.getParameter("param");
            String fundBalance = request.getParameter("fundBalance");
            BufferedInputStream in=null;
            if (param != null && !param.isEmpty()) {
                in = new BufferedInputStream(new FileInputStream("/opt/web/Hr/emp_images/emp_image/" + param + ".jpg"));
            } else if (fundBalance != null && !fundBalance.isEmpty()) {
                System.out.println("fundbalance");
                in = new BufferedInputStream(new FileInputStream("/opt/web/Hr/fund_balance/" + fundBalance));
            }
            // Get image contents.
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            in.close();
            response.getOutputStream().write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("<<<<<<<<<< Image Not Fount For "+ param +" >>>>>>>>>>>");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
