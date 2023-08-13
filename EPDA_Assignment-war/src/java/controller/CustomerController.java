/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.console;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;
import model.CustomerFacade;
import model.Manager;
import model.Product;
import model.ProductClass;
import model.SaleRecords;
import model.SaleRecordsFacade;
import model.Salesman;

/**
 *
 * @author yelia
 */
@WebServlet(name = "CustomerController", urlPatterns = {"/CustomerController"})
public class CustomerController extends HttpServlet {

    @EJB
    private SaleRecordsFacade saleRecordsFacade;

    @EJB
    private CustomerFacade customerFacade;

    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            String idTemp = null;
            try{
                String action = request.getParameter("action");
                if(action != null){
                    if (action.equals("postCustomerEdit")){
                        postCustomerEdit(request, response);
                    }
                    else if (action.equals("postCustomerAdd")){
                        postCustomerAdd(request, response);
                    }
                    else if (action.equals("postCustomerSearch")){
                        postCustomerSearch(request, response);
                    }
                    else if (action.equals("postCustomerLogin")){
                        postCustomerLogin(request, response);
                    }
                    else if (action.equals("postProductEdit")){
                        postProductEdit(request, response);
                    }
                }
                else{
                    response.sendRedirect("index.html");
                }
            }catch(Exception e){
                request.getRequestDispatcher("index.html").include(request, response);
                if(e instanceof IllegalArgumentException){
                    out.println("<br><br><br>Sorry "+idTemp+", your username has been used!");
                } else{
                    out.println("<br><br><br>Sorry "+e+", Invalid Password");
                }
            }
        }
        
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            String idTemp = null;
            try{
                String action = request.getParameter("action");
                if(action != null){
                    if(action.equals("getCustomerManage")){
                        getCustomerManage(request, response);
                    }
                    else if (action.equals("getCustomerEdit")){
                        getCustomerEdit(request, response);
                    }
                    else if (action.equals("getCustomerDelete")){
                        getCustomerDelete(request, response);
                    }
                    else if (action.equals("getProductEdit")){
                        getProductEdit(request, response);
                    }
                    else if (action.equals("getPurchaseHistory")){
                        getPurchaseHistory(request, response);
                    }
                }
                else{
                    response.sendRedirect("index.html");
                }
            }catch(Exception e){
                request.getRequestDispatcher("index.html").include(request, response);
                if(e instanceof IllegalArgumentException){
                    out.println("<br><br><br>Sorry "+idTemp+", your username has been used!");
                } else{
                    out.println("<br><br><br>Sorry "+e+", Invalid Password");
                }
            }
        }
    }

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
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
   

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void getCustomerManage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           HttpSession s = request.getSession(false);
        
        try (PrintWriter out = response.getWriter()) {
            String idTemp = null;
            try{
                List<Customer> customerList = new ArrayList<>();
                Customer search = null;
                search = (Customer)request.getAttribute("customerSearch");
                if (search != null)
                {
                    customerList.add(search);
                    request.removeAttribute("customerSearch");
                }
                else{
                    customerList=customerFacade.findAll();
                }
                request.setAttribute("customerList", customerList);
                request.getRequestDispatcher("customerManage.jsp").forward(request, response);
                out.println("<br><br><br> success");
            }catch(Exception e){
                request.getRequestDispatcher("managerMenu.jsp").include(request, response);
                if(e instanceof IllegalArgumentException){
                    out.println("<br><br><br>Sorry "+idTemp+", your username has been used!");
                } else{
                    out.println("<br><br><br>Sorry "+e+", Invalid Password");
                }
            }
        }
}

    private void getCustomerEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession();
        Customer customer;
        String customerIdParam = request.getParameter("id");
        
        if (customerIdParam != null) {
            String idTemp = request.getParameter("id");
            customer = customerFacade.find(idTemp);
            
        } 
        else {
            customer = (Customer) s.getAttribute("active");
        }
        
        if (customer != null) {
            request.setAttribute("customer", customer);
            request.getRequestDispatcher("customerEdit.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.html");
        }
    }
    
    
    private void postCustomerEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession(false);
        Customer profile= null;
        Manager Mprofile = null;
        
        try (PrintWriter out = response.getWriter()) {
            String idTemp = null;
            String role = (String)s.getAttribute("role");
            try{
                if (role=="manager"){
                   Mprofile =  (Manager)s.getAttribute("active");
                }
                else {
                    profile = (Customer)s.getAttribute("active");
                }
                idTemp = request.getParameter("idCust");
                String nameTemp = request.getParameter("nameCust");
                String genderTemp = request.getParameter("genderCust");
                String passTemp = request.getParameter("passCust");
                Customer found = customerFacade.find(idTemp);
                
                found.setName(nameTemp);
                found.setGender(genderTemp);
                found.setPassword(passTemp);
                
                customerFacade.edit(found);
                if (Mprofile == null){
                    if (profile.getId().equals(found.getId()))
                            {
                                s.setAttribute("active", found);
                            }
                }
                request.getRequestDispatcher("customerEdit.jsp").include(request, response);
                out.println("<br><br><br>"+idTemp+", customer profile is updated!");
            }catch(Exception e){
                   request.getRequestDispatcher("customerEdit.jsp").include(request, response);
                if(e instanceof IllegalArgumentException){
                    out.println("<br><br><br>Sorry "+idTemp+", your username has been used!");
                } else{
                    out.println("<br><br><br>Sorry "+e+", Invalid Password");
                }
            }
        }
    }
    
    private void postCustomerAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession(false);
        try (PrintWriter out = response.getWriter()) {
            String idTemp = null;
            try{
                idTemp = request.getParameter("idCust");
                Customer found = customerFacade.find(idTemp);
                if(found!=null){
                    System.out.println(found.getId());
                    throw new IllegalArgumentException();
                }
                String nameTemp = request.getParameter("nameCust");
                String genderTemp = request.getParameter("genderCust");
                String passTemp = request.getParameter("passCust");

                Customer temp = new Customer(idTemp, nameTemp ,genderTemp, passTemp);
                customerFacade.create(temp);
                if (s != null)
                {
                    request.getRequestDispatcher("customerRegister.jsp").include(request, response);
                    out.println("<br><br><br>"+idTemp+", your registration is done!");
                }
                else 
                {
                    getCustomerManage(request, response);
                }
            }catch(Exception e){
                request.getRequestDispatcher("customerRegister.jsp").include(request, response);
                if(e instanceof IllegalArgumentException){
                    out.println("<br><br><br>Sorry "+idTemp+", your username has been used!");
                } else{
                    out.println("<br><br><br>Sorry "+idTemp+", Invalid Password");
                }
            }
        }
    }
    
    private void getCustomerDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession(false);
        try (PrintWriter out = response.getWriter()) {
            String customerIdParam = request.getParameter("id");
            try{
                Customer found = customerFacade.find(customerIdParam);
                customerFacade.remove(found);
                
                getCustomerManage(request, response);
                out.println("<br><br><br>"+customerIdParam+", Customer is deleted!");
            }catch(Exception e){
                getCustomerManage(request, response);
                out.println("<br><br><br>Sorry "+e+", Invalid");
                
            }
        }
    }
    
    private void postCustomerSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try (PrintWriter out = response.getWriter()) {
            String idSearch = null;
            try{
                idSearch = request.getParameter("idSearch");
                Customer found = customerFacade.find(idSearch);
                
                if (found == null)
                {
                    getCustomerManage(request,response);
                    throw new IllegalArgumentException();
                }
                else{
                    request.setAttribute("customerSearch", found);
                }
                getCustomerManage(request,response);
                out.println("<br><br><br> Id Found!");
            }catch(Exception e){
                   request.getRequestDispatcher("customerManage.jsp").include(request, response);
                if(e instanceof IllegalArgumentException){
                    out.println("<br><br><br>"+idSearch+", not Found!");
                } else{
                    out.println("<br><br><br>Sorry Invalid");
                }
            }
        }
    }
    
    private void postCustomerLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try (PrintWriter out = response.getWriter()) {
            String idTemp = null;
            try{
                idTemp = request.getParameter("idCust");
                Customer found = customerFacade.find(idTemp);
                if(found == null){
                    throw new Exception();
                }
                String passTemp = request.getParameter("passCust");
                if(!(passTemp.equals(""+found.getPassword()))){
                    throw new Exception();
                }
                HttpSession s = request.getSession();
                s.setAttribute("active", found);
                request.getRequestDispatcher("customerMenu.jsp").forward(request, response);
                out.println("<br><br><br>Welcome "+idTemp+"!");
            }catch(Exception e){
                request.getRequestDispatcher("customerLogin.jsp").include(request, response);
                out.println("<br><br><br>Invalid Id or Password!");
            }            
        }
    }
    private void getProductEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession();
        String err = (String)request.getAttribute("exception");
        SaleRecords saleRecord = null;
        Long productIdParam = Long.parseLong(request.getParameter("id"));
        saleRecord = saleRecordsFacade.find(productIdParam);
        
        
        if (saleRecord != null) {
            request.setAttribute("saleRecord", saleRecord);
            request.getRequestDispatcher("customerProductEdit.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.html");
        }
    }
    
    private void getPurchaseHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession(false);
        Customer profile = (Customer)s.getAttribute("active");
        
        try (PrintWriter out = response.getWriter()) {
            String idTemp = null;
            try{
                
                List<SaleRecords> customerPurchaseList = new ArrayList<>();
                customerPurchaseList = profile.getPurchaseHistory();
                request.setAttribute("customerPurchaseList", customerPurchaseList);
                request.getRequestDispatcher("customerPurchase.jsp").forward(request, response);
                out.println("<br><br><br> success");
            }catch(Exception e){
                request.getRequestDispatcher("salesMenu.jsp").include(request, response);
                if(e instanceof IllegalArgumentException){
                    out.println("<br><br><br>Sorry "+idTemp+", your username has been used!");
                } 
                else{
                    out.println("<br><br><br>Sorry "+e+", Invalid Password");
                }
            }
        }
}
    
    private void postProductEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession(false);
        Customer profile = (Customer)s.getAttribute("active");
        
        try (PrintWriter out = response.getWriter()) {
            Long idTemp = null;
            String reviewTemp = null;
            try{
                System.out.println("Tempe0:"+ idTemp);
                idTemp = Long.parseLong(request.getParameter("idProd"));
                System.out.println("Tempe1:"+ idTemp);
                int ratingTemp = Integer.parseInt(request.getParameter("customerRating"));
                reviewTemp = request.getParameter("customerReview");
                SaleRecords saleRecord = saleRecordsFacade.find(idTemp);
                
                saleRecord.setCustomerFeedback(reviewTemp);
                saleRecord.setCustomerRating(ratingTemp);
                System.out.println("Tempe2:"+ idTemp);
                saleRecordsFacade.edit(saleRecord);
                String idCust = profile.getId();
                System.out.println("Tempe3:"+ idCust);
                Customer foundCustomer = customerFacade.find(idCust);
                System.out.println("Tempe4:"+ idCust);
                s.setAttribute("active", foundCustomer);
                System.out.println("Tempe5:"+ idCust);
                getPurchaseHistory(request,response);
                out.println("<br><br><br>"+idTemp+", product profile is updated!");
            }catch(Exception e){
                    request.setAttribute("exception", e);
                if(e instanceof IllegalArgumentException){
                    getProductEdit(request,response);
                    out.println("<br><br><br>Sorry "+idTemp+", Invalid ID!");
                } else{
                    getProductEdit(request,response);
                    out.println("<br><br><br>Sorry "+e+", Error");
                }
            }
        }
    }
}
