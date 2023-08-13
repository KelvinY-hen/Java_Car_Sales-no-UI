/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Product;
import model.ProductClass;
import model.ProductClassFacade;
import model.ProductFacade;
import model.Salesman;
import model.SalesmanFacade;

/**
 *
 * @author yelia
 */
@WebServlet(name = "ProductController", urlPatterns = {"/ProductController"})
public class ProductController extends HttpServlet {

    @EJB
    private SalesmanFacade salesmanFacade;

    @EJB
    private ProductClassFacade productClassFacade;
    
    @EJB
    private ProductFacade productFacade;

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
            out.println("<title>Servlet productController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet productController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            String idTemp = null;
            try{
                String action = request.getParameter("action");
                if(action != null){
                    if (action.equals("postProductEdit")){
                        postProductEdit(request, response);
                    }
                    if (action.equals("postProductClassEdit")){
                        postProductClassEdit(request, response);
                    }
                    else if (action.equals("postProductAdd")){
                        postProductAdd(request, response);
                    }
                    else if (action.equals("postProductClassAdd")){
                        postProductClassAdd(request, response);
                    }
                    else if (action.equals("postProductClassSearch")){
                        postProductClassSearch(request, response);
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
                    if(action.equals("getProductManage")){
                        getProductManage(request, response);
                    }
                    else if (action.equals("getProductEdit")){
                        getProductEdit(request, response);
                    }
                    else if (action.equals("getProductClassEdit")){
                        getProductClassEdit(request, response);
                    }
                    else if (action.equals("getProductAdd")){
                        getProductAdd(request, response);
                    }
                    else if (action.equals("getProductDelete")){
                        getProductDelete(request, response);
                    }
                    else if (action.equals("getProductClassDelete")){
                        getProductClassDelete(request, response);
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

    private void getProductManage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession(false);
        
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
                    productList=productFacade.findAll();
                    for(Product product : productList)
                    {
                        System.out.println("tempe1: "+ product);
                    }
                }
                
                for(Product product : productList)
                {
                    System.out.println("tempe1: "+ product);
                }
                List<ProductClass> productClassList = new ArrayList<>();
                ProductClass search2 = null;
                search2 = (ProductClass)request.getAttribute("productClassSearch");
                if (search2 != null)
                {
                    productClassList.add(search2);
                    request.removeAttribute("productClassSearch");
                }
                else{
                    productClassList=productClassFacade.findAll();
                }
                
                request.setAttribute("productClassList",productClassList);
                request.setAttribute("productList", productList);
                request.getRequestDispatcher("productManage.jsp").forward(request, response);
                out.println("<br><br><br> success");
            }catch(Exception e){
                request.getRequestDispatcher("managerMenu.jsp").include(request, response);
                if(e instanceof IllegalArgumentException){
                    out.println("<br><br><br>Sorry "+idTemp+", your username has been used!");
                } else if(e instanceof EJBException){
                   request.getRequestDispatcher("productManage.jsp").forward(request, response);
                   out.println("<br><br><br> Empty"); 
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
        Product product = null;
        Long productIdParam = Long.parseLong(request.getParameter("id"));
        product = productFacade.find(productIdParam); 
        
        List<Product> productList = new ArrayList<>();
                Product search = null;
                search = (Product)request.getAttribute("ProductSearch");
                if (search != null)
                {
                    productList.add(search);
                    request.removeAttribute("ProductSearch");
                }
                else{
                    productList=productFacade.findAll();
                }
                
        List<Salesman> salesmanList = new ArrayList<>();
        salesmanList= salesmanFacade.findAll();

        List<ProductClass> productClassList = new ArrayList<>();
        productClassList=productClassFacade.findAll();

        request.setAttribute("salesmanList",salesmanList);
        request.setAttribute("productClassList",productClassList);
        
        if (product != null) {
            request.setAttribute("product", product);
            request.getRequestDispatcher("productEdit.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.html");
        }
    }
    private void getProductStatusEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession();
        String err = (String)request.getAttribute("exception");
        Product product = null;
        Long productIdParam = Long.parseLong(request.getParameter("id"));
        product = productFacade.find(productIdParam); 
        
        List<Product> productList = new ArrayList<>();
                Product search = null;
                search = (Product)request.getAttribute("ProductSearch");
                if (search != null)
                {
                    productList.add(search);
                    request.removeAttribute("ProductSearch");
                }
                else{
                    productList=productFacade.findAll();
                }
                
        List<Salesman> salesmanList = new ArrayList<>();
        salesmanList= salesmanFacade.findAll();

        List<ProductClass> productClassList = new ArrayList<>();
        productClassList=productClassFacade.findAll();

        request.setAttribute("salesmanList",salesmanList);
        request.setAttribute("productClassList",productClassList);
        
        if (product != null) {
            request.setAttribute("product", product);
            request.getRequestDispatcher("productEdit.jsp").forward(request, response);
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
            try{
                idTemp = Long.parseLong(request.getParameter("idProd"));
                String statusTemp = request.getParameter("statusProd");
                Double priceTemp = Double.parseDouble(request.getParameter("priceProd"));
                Product found = productFacade.find(idTemp);
                
                System.out.println("Product id: " + found);
                
                idSales = request.getParameter("idSales");
                Salesman foundSales = salesmanFacade.find(idSales);
                if(foundSales==null){
                    throw new IllegalArgumentException();
                }
                
                System.out.println("Product id: " + foundSales);
                
                idPC = request.getParameter("idProductClass");
                ProductClass foundPC = productClassFacade.find(idPC);
                if(foundPC==null){
                    throw new IllegalArgumentException();
                }
                
                System.out.println("Product id: " + foundPC);
                
                
                found.setStatus(statusTemp);
                found.setPrice(priceTemp);
                found.setSalesman(foundSales);
                found.setProductClass(foundPC);
                
                productFacade.edit(found);
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
    
    private void getProductClassEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession();
        ProductClass productClass = null;
        String productClassIdParam = request.getParameter("id");
        productClass = productClassFacade.find(productClassIdParam); 
        
        
        if (productClass != null) {
            request.setAttribute("productClass",productClass);
            request.getRequestDispatcher("productClassEdit.jsp").forward(request, response);
        } else {
            getProductManage(request,response);
        }
    }
    
    
    private void postProductClassEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession(false);
        
        try (PrintWriter out = response.getWriter()) {
            String idTemp = null;
            try{
                idTemp = request.getParameter("idProductClass");
                String nameTemp = request.getParameter("nameProductClass");
                ProductClass found = productClassFacade.find(idTemp);
                
                found.setName(nameTemp);
                System.out.println("Product id: " + found);
                productClassFacade.edit(found);
                getProductManage(request,response);
                out.println("<br><br><br>"+idTemp+", product profile is updated!");
            }catch(Exception e){
                   request.getRequestDispatcher("productClassEdit.jsp").include(request, response);
                if(e instanceof IllegalArgumentException){
                    out.println("<br><br><br>Sorry "+idTemp+", Invalid ID!");
                } else{
                    out.println("<br><br><br>Sorry "+e+", Error");
                }
            }
        }
    }
    
    private void postProductClassAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession(false);
        try (PrintWriter out = response.getWriter()) {
            try{
                String idTemp = request.getParameter("idProdClass");
                ProductClass found = productClassFacade.find(idTemp);
                if(found!=null){
                    System.out.println(found.getId());
                    throw new IllegalArgumentException();
                }
                String nameTemp = request.getParameter("nameProdClass");

                ProductClass temp = new ProductClass(idTemp, nameTemp);
                productClassFacade.create(temp);
                getProductManage(request, response);
            }catch(Exception e){
                request.getRequestDispatcher("productClassRegister.jsp").include(request, response);
                if(e instanceof IllegalArgumentException){
                    out.println("<br><br><br>Sorry Invalid ID");
                } else{
                    out.println("<br><br><br>Sorry "+e+", Error");
                }
            }
        }
    }
    
    private void getProductAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession(false);
        String test = null;
        try (PrintWriter out = response.getWriter()) {
            try{
                test="1";
                List<Salesman> salesmanList = new ArrayList<>();
                salesmanList= salesmanFacade.findAll();
                
                test="2";
                List<ProductClass> productClassList = new ArrayList<>();
                productClassList=productClassFacade.findAll();
                
                request.setAttribute("salesmanList",salesmanList);
                request.setAttribute("productClassList",productClassList);
                request.getRequestDispatcher("productRegister.jsp").forward(request, response);
            }catch(Exception e){
                if (e instanceof EJBException){
                    if (test == "1"){
                        request.getRequestDispatcher("salesRegister.jsp").forward(request, response);
                        out.println("<br><br><br> Empty Sales"); 
                    }
                    else if (test == "2"){
                        request.getRequestDispatcher("productClassRegister.jsp").forward(request, response);
                        out.println("<br><br><br> Empty Product Class"); 
                    }
                }
                else {
                request.getRequestDispatcher("productManage.jsp").include(request, response);
                out.println("<br><br><br>Sorry "+e+", Error");
            }
                
            }
        }
    }
    
    
    
    private void postProductAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession(false);
        try (PrintWriter out = response.getWriter()) {
            Long idProd = null;
            String idSales = null;
            String idPC = null;
            try{
                idProd =Long.parseLong(request.getParameter("idProd"));
                Product foundProd = productFacade.find(idProd);
                System.out.println("Product id: " + foundProd);
                if(foundProd!=null){
                    System.out.println(foundProd.getId());
                    throw new IllegalArgumentException();
                }
                idSales = request.getParameter("idSales");
                Salesman foundSales = salesmanFacade.find(idSales);
                System.out.println("Product id: " + foundSales);
                if(foundSales==null){
                    System.out.println(foundSales.getId());
                    throw new IllegalArgumentException();
                }
                idPC = request.getParameter("idProductClass");
                ProductClass foundPC = productClassFacade.find(idPC);
                System.out.println("Product id: " + foundPC);
                if(foundPC==null){
                    System.out.println(foundPC.getId());
                    throw new IllegalArgumentException();
                }
                String statusTemp = request.getParameter("statusProd");
                double priceTemp = Double.parseDouble(request.getParameter("priceProd"));
                
                System.out.println("Product id: final");

                Product newProduct = new Product(idProd, statusTemp , priceTemp, foundSales, foundPC);
                
                System.out.println("Product id: final 2");
                System.out.println("Product id: " + newProduct.getId());
                System.out.println("Product status: " + newProduct.getStatus());
                System.out.println("Product price: " + newProduct.getPrice());
                System.out.println("Product salesman: " + newProduct.getSalesman().getName());
                System.out.println("Product product class: " + newProduct.getProductClass().getName());

                productFacade.create(newProduct);
                out.println("<br><br><br> "+idProd+", Succesfully Added");
                getProductManage(request, response);
            }catch(Exception e){
                request.getRequestDispatcher("productRegister.jsp").include(request, response);
                if(e instanceof IllegalArgumentException){
                    out.println("<br><br><br>Sorry Invalid ID");
                } else{
                    out.println("<br><br><br>Sorry "+e+", Error");
                    
                    
                }
            }
        }
        
    }
    private void getProductClassDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession(false);
        try (PrintWriter out = response.getWriter()) {
            String ProductClassIdParam = request.getParameter("id");
            try{
                ProductClass found = productClassFacade.find(ProductClassIdParam);
                out.println("<br><br><br>Sorry 1"+found);
                productClassFacade.remove(found);
                out.println("<br><br><br>Sorry 2"+found);
                getProductManage(request, response);
                out.println("<br><br><br>"+ProductClassIdParam+", Customer is deleted!");
            }catch(Exception e){
                getProductManage(request, response);
                out.println("<br><br><br>Sorry "+e+", Invalid");
                
            }
        }
    }
    private void getProductDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession(false);
        try (PrintWriter out = response.getWriter()) {
            Long ProductIdParam = Long.parseLong(request.getParameter("id"));
            try{
                Product found = productFacade.find(ProductIdParam);
                productFacade.remove(found);
                
                getProductManage(request, response);
                out.println("<br><br><br>"+ProductIdParam+", Customer is deleted!");
            }catch(Exception e){
                getProductManage(request, response);
                out.println("<br><br><br>Sorry "+e+", Invalid");
                
            }
        }
    }
    
    private void postProductSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try (PrintWriter out = response.getWriter()) {
            Long idSearch = null;
            try{
                idSearch = Long.parseLong(request.getParameter("idProductSearch"));
                if (idSearch==null){
                    getProductManage(request,response);
                }
                Product found = productFacade.find(idSearch);
                out.println("<br><br><br>Sorry"+found);
                if (found == null)
                {
                    getProductManage(request,response);
                    throw new IllegalArgumentException();
                }
                else{
                    request.setAttribute("ProductSearch", found);
                }
                
                getProductManage(request,response);
                out.println("<br><br><br> Id Found!");
            }catch(Exception e){
                   request.getRequestDispatcher("productManage.jsp").include(request, response);
                if(e instanceof IllegalArgumentException){
                    out.println("<br><br><br>"+idSearch+", not Found!");
                } else{
                    out.println("<br><br><br>Sorry Invalid");
                }
            }
        }
    }
   
        
    private void postProductClassSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try (PrintWriter out = response.getWriter()) {
            String idSearch = null;
            try{
                idSearch = request.getParameter("idProductClassSearch");
                if (idSearch==null){
                    getProductManage(request,response);
                }
                ProductClass found = productClassFacade.find(idSearch);
                
                if (found == null)
                {
                    getProductManage(request,response);
                    throw new IllegalArgumentException();
                }
                else{
                    request.setAttribute("productClassSearch", found);
                }
                getProductManage(request,response);
                out.println("<br><br><br> Id Found!");
            }catch(Exception e){
                   request.getRequestDispatcher("productManage.jsp").include(request, response);
                if(e instanceof IllegalArgumentException){
                    out.println("<br><br><br>"+idSearch+", not Found!");
                } else{
                    out.println("<br><br><br>Sorry Invalid");
                }
            }
        }
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
