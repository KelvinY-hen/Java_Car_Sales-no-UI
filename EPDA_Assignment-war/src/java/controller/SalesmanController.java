package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
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
import model.ProductClassFacade;
import model.ProductFacade;
import model.SaleRecords;
import model.SaleRecordsFacade;
import model.Salesman;
import model.SalesmanFacade;

/**
 *
 * @author yelia
 */
@WebServlet(name = "SalesmanController", urlPatterns = {"/SalesmanController"})
public class SalesmanController extends HttpServlet {

    @EJB
    private SaleRecordsFacade saleRecordsFacade;

    @EJB
    private ProductClassFacade productClassFacade;

    @EJB
    private CustomerFacade customerFacade;

    @EJB
    private ProductFacade productFacade;

    @EJB
    private SalesmanFacade salesmanFacade;

    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            String idTemp = null;
            try{
                String action = request.getParameter("action");
                if(action != null){
                    if (action.equals("postSalesmanEdit")){
                        postSalesmanEdit(request, response);
                    }
                    else if (action.equals("postSalesmanAdd")){
                        postSalesmanAdd(request, response);
                    }
                    else if (action.equals("postSalesmanSearch")){
                        postSalesmanSearch(request, response);
                    }
                    else if (action.equals("postSalesmanLogin")){
                        postSalesmanLogin(request, response);
                    }
                    else if (action.equals("postProductEdit")){
                        postProductEdit(request, response);
                    }
                    else if (action.equals("postProductSearch")){
                        postProductSearch(request, response);
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
                    if(action.equals("getSalesmanManage")){
                        getSalesmanManage(request, response);
                    }
                    else if (action.equals("getSalesmanEdit")){
                        getSalesmanEdit(request, response);
                    }
                    else if (action.equals("getSalesmanDelete")){
                        getSalesmanDelete(request, response);
                    }
                    else if (action.equals("getProductEdit")){
                        getProductEdit(request, response);
                    }
                    else if (action.equals("getProductManage")){
                        getProductManage(request, response);
                    }
                    else if (action.equals("getSalesRecord")){
                        getSalesRecord(request, response);
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

    private void getSalesmanManage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession(false);
        
        try (PrintWriter out = response.getWriter()) {
            String idTemp = null;
            try{
                List<Salesman> salesmanList = new ArrayList<>();
                Salesman search = null;
                search = (Salesman)request.getAttribute("salesmanSearch");
                if (search != null)
                {
                    salesmanList.add(search);
                    request.removeAttribute("salesmanSearch");
                }
                else{
                    salesmanList=salesmanFacade.findAll();
                }
                request.setAttribute("salesmanList", salesmanList);
                
                request.getRequestDispatcher("salesManage.jsp").forward(request, response);
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
    private void getSalesmanEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession();
        
        Salesman salesman;
        String salesmanIdParam = request.getParameter("id");
        
        if (salesmanIdParam != null) {
            String idTemp = request.getParameter("id");
            salesman = salesmanFacade.find(idTemp);
            
        } 
        else {
            salesman = (Salesman) s.getAttribute("active");
        }
        
        if (salesman != null) {
            request.setAttribute("salesman", salesman);
            request.getRequestDispatcher("salesEdit.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.html");
        }
    }
    
    
    private void postSalesmanEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession(false);
        Salesman profile= null;
        Manager Mprofile = null;
        try (PrintWriter out = response.getWriter()) {
            String idTemp = null;
            String role = (String)s.getAttribute("role");
            try{
                System.out.println(role);
                if (role=="manager"){
                   Mprofile =  (Manager)s.getAttribute("active");
                }
                else {
                    profile = (Salesman)s.getAttribute("active");
                }
                idTemp = request.getParameter("idSales");
                String nameTemp = request.getParameter("nameSales");
                String passTemp = request.getParameter("passSales");
                
                Salesman found = salesmanFacade.find(idTemp);
                
                found.setName(nameTemp);
                found.setPassword(passTemp);
                
                salesmanFacade.edit(found);
                System.out.println("5");
                
                if (Mprofile == null){
                    if (profile.getId().equals(found.getId()))
                            {
                                s.setAttribute("active", found);
                            }
                }
                
                
                
                request.getRequestDispatcher("salesEdit.jsp").include(request, response);
                out.println("<br><br><br>"+idTemp+", salesman profile is updated!");
            }catch(Exception e){
                   request.getRequestDispatcher("salesEdit.jsp").include(request, response);
                if(e instanceof IllegalArgumentException){
                    out.println("<br><br><br>Sorry "+idTemp+", your username has been used!");
                } else{
                    out.println("<br><br><br>Sorry "+e+", Invalid Password");
                }
            }
        }
    }
    
    private void postSalesmanAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession(false);
        try (PrintWriter out = response.getWriter()) {
            String idTemp = null;
            try{
                idTemp = request.getParameter("idSales");
                Salesman found = salesmanFacade.find(idTemp);
                if(found!=null){
                    System.out.println(found.getId());
                    throw new IllegalArgumentException();
                }
                String nameTemp = request.getParameter("nameSales");
                String passTemp = request.getParameter("passSales");

                Salesman temp = new Salesman(idTemp, nameTemp , passTemp);
                salesmanFacade.create(temp);
                if (s != null)
                {
                    request.getRequestDispatcher("salesRegister.jsp").include(request, response);
                    out.println("<br><br><br>"+idTemp+", your registration is done!");
                }
                else 
                {
                    getSalesmanManage(request, response);
                }
            }catch(Exception e){
                request.getRequestDispatcher("salesRegister.jsp").include(request, response);
                if(e instanceof IllegalArgumentException){
                    out.println("<br><br><br>Sorry "+idTemp+", your username has been used!");
                } else{
                    out.println("<br><br><br>Sorry "+idTemp+", Invalid Password");
                }
            }
        }
    }
    
    private void getSalesmanDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession(false);
        try (PrintWriter out = response.getWriter()) {
            String salesmanIdParam = request.getParameter("id");
            try{
                Salesman found = salesmanFacade.find(salesmanIdParam);
                salesmanFacade.remove(found);
                
                getSalesmanManage(request, response);
                out.println("<br><br><br>"+salesmanIdParam+", Salesman is deleted!");
            }catch(Exception e){
                getSalesmanManage(request, response);
                out.println("<br><br><br>Sorry "+e+", Invalid");
                
            }
        }
    }
    
    private void postSalesmanSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try (PrintWriter out = response.getWriter()) {
            String idSearch = null;
            try{
                idSearch = request.getParameter("idSearch");
                Salesman found = salesmanFacade.find(idSearch);
                
                if (found == null)
                {
                    getSalesmanManage(request,response);
                    throw new IllegalArgumentException();
                }
                else{
                    request.setAttribute("salesmanSearch", found);
                }
                
                getSalesmanManage(request,response);
                out.println("<br><br><br> Id Found!");
            }catch(Exception e){
                   request.getRequestDispatcher("salesManage.jsp").include(request, response);
                if(e instanceof IllegalArgumentException){
                    out.println("<br><br><br>"+idSearch+", not Found!");
                } else{
                    out.println("<br><br><br>Sorry Invalid");
                }
            }
        }
    }
    
    private void postSalesmanLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try (PrintWriter out = response.getWriter()) {
            String idTemp = null;
            try{
                idTemp = request.getParameter("idSales");
                Salesman found = salesmanFacade.find(idTemp);
                if(found == null){
                    throw new Exception();
                }
                String passTemp = request.getParameter("passSales");
                if(!(passTemp.equals(""+found.getPassword()))){
                    throw new Exception();
                }
                HttpSession s = request.getSession();
                s.setAttribute("active", found);
                request.getRequestDispatcher("salesMenu.jsp").forward(request, response);
                out.println("<br><br><br>Welcome "+idTemp+"!");
            }catch(Exception e){
                request.getRequestDispatcher("salesLogin.jsp").include(request, response);
                out.println("<br><br><br>Invalid Id or Password!");
            }            
        }
    }
    
    private void getProductManage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession(false);
        Salesman profile = (Salesman)s.getAttribute("active");
        
        try (PrintWriter out = response.getWriter()) {
            String idTemp = null;
            try{
                List<Product> productList = new ArrayList<>();
                Product search = null;
                search = (Product)request.getAttribute("productSearch");
                if (search != null)
                {
                    productList.add(search);
                    request.removeAttribute("productSearch");
                }
                else{
                    productList= profile.getProducts();
                }
                
                request.setAttribute("productList", productList);
                request.getRequestDispatcher("productSalesManage.jsp").forward(request, response);
                out.println("<br><br><br> success");
            }catch(Exception e){
                request.getRequestDispatcher("salesMenu.jsp").include(request, response);
                if(e instanceof IllegalArgumentException){
                    out.println("<br><br><br>Sorry "+idTemp+", your username has been used!");
                }else{
                    out.println("<br><br><br>Sorry "+e+", Invalid Password");
                }
            }
        }
}
    private void getSalesRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession(false);
        Salesman profile = (Salesman)s.getAttribute("active");
        
        try (PrintWriter out = response.getWriter()) {
            String idTemp = null;
            try{
                
                List<SaleRecords> saleRecordsList = new ArrayList<>();
                List<SaleRecords> paidRecordsList = new ArrayList<>();
                saleRecordsList = profile.getSalesHistory();
                for(SaleRecords Record : saleRecordsList)
                {
                    if ("paid".equals(Record.getId().getStatus())){
                        paidRecordsList.add(Record);
                    }
                }
                request.setAttribute("saleRecordsList", paidRecordsList);
                request.getRequestDispatcher("salesRecord.jsp").forward(request, response);
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
    private void getProductEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession();
        String err = (String)request.getAttribute("exception");
        String nstatus= (String)request.getParameter("nstatus");
        Product product = null;
        Long productIdParam = Long.parseLong(request.getParameter("id"));
        product = productFacade.find(productIdParam); 
        product.setStatus(nstatus);
        System.out.println("status:" + nstatus);
        
        if ("booked".equals(nstatus)){
            System.out.println("tempe1");
            List<Customer> customerList = new ArrayList<>();
            customerList= customerFacade.findAll();
            request.setAttribute("customerList",customerList);
        }else {
            System.out.println("tempe2");
            SaleRecords productRecords = saleRecordsFacade.find(product.getId());
            System.out.println(productRecords.getCustomer());
            request.setAttribute("customer",productRecords.getCustomer());
        }
        
        
        if (product != null) {
            request.setAttribute("product", product);
            request.getRequestDispatcher("productSalesEdit.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.html");
        }
    }
    
    private void postProductEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession(false);
        
        try (PrintWriter out = response.getWriter()) {
            Long idTemp = null;
            String idSales = null;
            String idPC = null;
            String idCust = null;
            ZonedDateTime dt = ZonedDateTime.now();
            try{
                idTemp = Long.parseLong(request.getParameter("idProd"));
                String statusTemp = request.getParameter("statusProd");
                Product found = productFacade.find(idTemp);
                
                System.out.println("Product id: " + found);
                
                idSales = request.getParameter("idSales");
                Salesman foundSales = salesmanFacade.find(idSales);
                
                System.out.println("Product id: " + foundSales);
                
                idPC = request.getParameter("idProductClass");
                ProductClass foundPC = productClassFacade.find(idPC);
                
                System.out.println("Product id: " + foundPC);
                
                idCust = request.getParameter("idCust");
                Customer foundCust = customerFacade.find(idCust);
                System.out.println("tahu1");
                if ("booked".equals(statusTemp)){
                    System.out.println("tahu buku");
                    found.setStatus(statusTemp);
                    SaleRecords saleRecord = new SaleRecords(found, foundCust, foundSales,found.getProductClass(), dt);
                    saleRecordsFacade.create(saleRecord);
                }else if ("paid".equals(statusTemp)){
                    System.out.println("tahu bayar");
                    String salesReview = request.getParameter("salesReview");
                    SaleRecords foundSR = saleRecordsFacade.find(found.getId());
                    foundSR.setSalesReview(salesReview);
                    foundSR.setDt(dt);
                    saleRecordsFacade.edit(foundSR);
                    found.setStatus(statusTemp);
                }else if ("cancel".equals(statusTemp)){
                    System.out.println("tidak tahu");
                    found.setStatus("available");
                    SaleRecords foundSR = saleRecordsFacade.find(found.getId());
                    System.out.println("tahu jam:"+ foundSR.getDt());
                    saleRecordsFacade.remove(foundSR);
                }
                System.out.println("tahu habis");
                productFacade.edit(found);
                foundSales = salesmanFacade.find(idSales);
                s.setAttribute("active", foundSales);
                getProductManage(request,response);
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
    
   private void postProductSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try (PrintWriter out = response.getWriter()) {
            HttpSession s = request.getSession();
            Salesman profile = (Salesman)s.getAttribute("active");
            Long idSearch = null;
            Product found = null;
            try{
                idSearch = Long.parseLong(request.getParameter("idProductSearch"));
                if (idSearch==null){
                    getProductManage(request,response);
                }
                List<Product> productList = profile.getProducts();
                for(Product product : productList)
                {
                    if (product.getId().equals(idSearch)){
                        found = product;
                    }
                }
                out.println("<br><br><br>Sorry"+found);
                if (found == null)
                {
                    getProductManage(request,response);
                    throw new IllegalArgumentException();
                }
                else{
                    request.setAttribute("productSearch", found);
                }
                
                getProductManage(request,response);
                out.println("<br><br><br> Id Found!");
            }catch(Exception e){
                   request.getRequestDispatcher("productSalesManage.jsp").include(request, response);
                if(e instanceof IllegalArgumentException){
                    out.println("<br><br><br>"+idSearch+", not Found!");
                } else{
                    out.println("<br><br><br>Sorry Invalid");
                }
            }
        }
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    

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

}
