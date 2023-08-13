/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Color;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;
import model.Manager;
import model.ManagerFacade;
import model.Product;
import model.ProductClass;
import model.ProductClassFacade;
import model.SaleRecords;
import model.SaleRecordsFacade;

/**
 *
 * @author yelia
 */
@WebServlet(name = "ManagerController", urlPatterns = {"/ManagerController"})
public class ManagerController extends HttpServlet {

    @EJB
    private ProductClassFacade productClassFacade;

    @EJB
    private SaleRecordsFacade saleRecordsFacade;

    @EJB
    private ManagerFacade managerFacade;
    
    
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            String idTemp = null;
            try{
                String action = request.getParameter("action");
                if(action != null){
                    if (action.equals("postManagerEdit")){
                        postManagerEdit(request, response);
                    }
                    else if (action.equals("postManagerAdd")){
                        postManagerAdd(request, response);
                    }
                    else if (action.equals("postManagerSearch")){
                        postManagerSearch(request, response);
                    }
                    else if (action.equals("postManagerLogin")){
                        postManagerLogin(request, response);
                    }
                    else if (action.equals("postReport")){
                        postProductReporttest(request, response);
                    }
                    else if (action.equals("postDailyAnalysis")){
                        postDailyAnalysis(request, response);
                    }
                    else if (action.equals("postProductAnalysis")){
                        postProductAnalysis(request, response);
                    }
                    else if (action.equals("postMonthlyAnalysis")){
                        postMonthlyAnalysis(request, response);
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
                    if(action.equals("getManagerManage")){
                        getManagerManage(request, response);
                    }
                    else if (action.equals("getManagerEdit")){
                        getManagerEdit(request, response);
                    }
                    else if (action.equals("getManagerDelete")){
                        getManagerDelete(request, response);
                    }
                    else if (action.equals("getProductPayment")){
                        getProductPayment(request, response);
                    }
                    else if (action.equals("getProductAnalysis")){
                        getProductAnalysis(request, response);
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
    
    private void getManagerManage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession s = request.getSession(false);
        Manager profile= (Manager)s.getAttribute("active");
        
        try (PrintWriter out = response.getWriter()) {
            String idTemp = null;
            try{
                List<Manager> managerList = new ArrayList<>();
                Manager search = null;
                search = (Manager)request.getAttribute("managerSearch");
                if (search != null)
                {
                    managerList.add(search);
                    request.removeAttribute("managerSearch");
                }
                else{
                    managerList=managerFacade.findAll();
                }
                request.setAttribute("managerList", managerList);
                request.getRequestDispatcher("managerManage.jsp").forward(request, response);
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

    private void getManagerEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession();
        Manager manager;
        String customerIdParam = request.getParameter("id");
        
        if (customerIdParam != null) {
            String idTemp = request.getParameter("id");
            manager = managerFacade.find(idTemp);
            
        } 
        else {
            manager = (Manager) s.getAttribute("active");
        }
        
        if (manager != null) {
            request.setAttribute("manager", manager);
            request.getRequestDispatcher("managerEdit.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.html");
        }
    }
    
    
    private void postManagerEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession(false);
        Manager profile= (Manager)s.getAttribute("active");
        
        try (PrintWriter out = response.getWriter()) {
            String idTemp = null;
            try{
                
                
                idTemp = request.getParameter("idManager");
                String nameTemp = request.getParameter("nameManager");
                String passTemp = request.getParameter("passManager");
                Manager found = managerFacade.find(idTemp);
                
                System.out.println(found);
                found.setName(nameTemp);
                found.setPassword(passTemp);
                
                System.out.println("1");
                System.out.println(profile);
                
                System.out.println("2");
                
                managerFacade.edit(found);
                if (profile.getId().equals(found.getId()))
                    {
                        s.setAttribute("active", found);
                    }
                request.getRequestDispatcher("managerEdit.jsp").include(request, response);
                out.println("<br><br><br>"+idTemp+", manager profile is updated!");
            }catch(Exception e){
                   request.getRequestDispatcher("managerEdit.jsp").include(request, response);
                if(e instanceof IllegalArgumentException){
                    out.println("<br><br><br>Sorry "+idTemp+", your username has been used!");
                } else{
                    out.println("<br><br><br>Sorry "+e+", Invalid Password");
                }
            }
        }
    }
    
    private void postManagerAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession(false);
        try (PrintWriter out = response.getWriter()) {
            String idTemp = null;
            try{
                idTemp = request.getParameter("idManager");
                Manager found = managerFacade.find(idTemp);
                if(found!=null){
                    System.out.println(found.getId());
                    throw new IllegalArgumentException();
                }
                String nameTemp = request.getParameter("nameManager");
                String passTemp = request.getParameter("passManager");

                Manager temp = new Manager(idTemp, nameTemp , passTemp);
                managerFacade.create(temp);
                if (s != null)
                {
                    request.getRequestDispatcher("managerLogin.jsp").include(request, response);
                    out.println("<br><br><br>"+idTemp+", your registration is done!");
                }
                else 
                {
                    getManagerManage(request, response);
                }
            }catch(Exception e){
                request.getRequestDispatcher("managerRegister.jsp").include(request, response);
                if(e instanceof IllegalArgumentException){
                    out.println("<br><br><br>Sorry "+idTemp+", your username has been used!");
                } else{
                    out.println("<br><br><br>Sorry "+idTemp+", Invalid Password");
                }
            }
        }
    }
    
    private void getManagerDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession(false);
        try (PrintWriter out = response.getWriter()) {
            String managerIdParam = request.getParameter("id");
            try{
                List<Manager> managerList= managerFacade.findAll();
                Manager found = managerFacade.find(managerIdParam);
                if(managerList.size() == 1){
                    out.println("<br><br><br>Cannot delete the last manager");
                }else{
                    managerFacade.remove(found);
                    getManagerManage(request, response);
                    out.println("<br><br><br>"+managerIdParam+", Manager is deleted!");
                }
            }catch(Exception e){
                getManagerManage(request, response);
                out.println("<br><br><br>Sorry "+e+", Invalid");
                
            }
        }
    }
    
    private void postManagerSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try (PrintWriter out = response.getWriter()) {
            String idSearch = null;
            try{
                idSearch = request.getParameter("idSearch");
                Manager found = managerFacade.find(idSearch);
                
                if (found == null)
                {
                    getManagerManage(request,response);
                    throw new IllegalArgumentException();
                }
                else{
                    request.setAttribute("managerSearch", found);
                }
                getManagerManage(request,response);
                out.println("<br><br><br> Id Found!");
            }catch(Exception e){
                   request.getRequestDispatcher("managerManage.jsp").include(request, response);
                if(e instanceof IllegalArgumentException){
                    out.println("<br><br><br>"+idSearch+", not Found!");
                } else{
                    out.println("<br><br><br>Sorry Invalid");
                }
            }
        }
    }
    
    private void postManagerLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try (PrintWriter out = response.getWriter()) {
            String idTemp = null;
            try{
                idTemp = request.getParameter("idManager");
                Manager found = managerFacade.find(idTemp);
                if(found == null){
                    throw new Exception();
                }
                String passTemp = request.getParameter("passManager");
                if(!(passTemp.equals(""+found.getPassword()))){
                    throw new Exception();
                }
                HttpSession s = request.getSession();
                s.setAttribute("active", found);
                request.getRequestDispatcher("managerMenu.jsp").forward(request, response);
                out.println("<br><br><br>Welcome "+idTemp+"!");
            }catch(Exception e){
                request.getRequestDispatcher("managerLogin.jsp").include(request, response);
                out.println("<br><br><br>Invalid Id or Password!");
            }            
        }
    }
    
    private void getProductPayment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession(false);
        
        try (PrintWriter out = response.getWriter()) {
            String idTemp = null;
            try{
                
                List<SaleRecords> Temp = new ArrayList<>();
                List<SaleRecords> paidRecords = new ArrayList<>();
                List<SaleRecords> bookedRecords = new ArrayList<>();
                Temp = saleRecordsFacade.findAll();
                int total = Temp.size();
                int paid = 0;
                int booked = 0;
                for(SaleRecords record : Temp)
                {
                    if("paid".equals(record.getId().getStatus())){
                        paidRecords.add(record);
                        paid++;
                    }else if("booked".equals(record.getId().getStatus())){
                        bookedRecords.add(record);
                        booked++;
                    }
                }
                double paidPercentage = ((double)paid/total)*100;
                double bookedPercentage = ((double)booked/total)*100;
                
                DecimalFormat df = new DecimalFormat("#.##");
                String fpaidPercentage = df.format(paidPercentage);
                String fbookedPercentage = df.format(bookedPercentage);
                
                request.setAttribute("paidRecords", paidRecords);
                request.setAttribute("bookedRecords", bookedRecords);
                request.setAttribute("paidPercentage", fpaidPercentage);
                request.setAttribute("bookedPercentage", fbookedPercentage);
                request.getRequestDispatcher("managerProductPayment.jsp").forward(request, response);
                out.println("<br><br><br> success");
            }catch(Exception e){
                request.getRequestDispatcher("managerMenu.jsp").include(request, response);
                if(e instanceof IllegalArgumentException){
                    out.println("<br><br><br>Sorry "+idTemp+", your username has been used!");
                } 
                else{
                    out.println("<br><br><br>Sorry "+e+", Invalid Password");
                }
            }
        }
}
    private void getProductAnalysis(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession(false);
        
        try (PrintWriter out = response.getWriter()) {
            String idTemp = null;
            try{
                
                List<ProductClass> productClassList = new ArrayList<>();
                productClassList = productClassFacade.findAll();
                request.setAttribute("productClassList", productClassList);
                request.getRequestDispatcher("productAnalysis.jsp").forward(request, response);
                out.println("<br><br><br> success");
            }catch(Exception e){
                request.getRequestDispatcher("managerMenu.jsp").include(request, response);
                if(e instanceof IllegalArgumentException){
                    out.println("<br><br><br>Sorry "+idTemp+", your username has been used!");
                } 
                else{
                    out.println("<br><br><br>Sorry "+e+", Invalid Password");
                }
            }
        }
}

 
//    private void postProductReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession s = request.getSession(false);
//        
//        try (PrintWriter out = response.getWriter()) {
//            Long idTemp = null;
//            LocalDate today = LocalDate.now();
//            LocalDate oneWeekAgo = today.minusWeeks(1);
//            List<ProductClass> productClassList = new ArrayList<>();
//            List<String> classNames = new ArrayList<>();
//            List<Integer> productCounts = new ArrayList<>();
//            List<LocalDate> paidDate = new ArrayList<>();
//            try{
//                List<LocalDate> dates = new ArrayList<>();
//                LocalDate startDate = LocalDate.now().minusWeeks(2);
//                LocalDate currentDate = LocalDate.now();
//                while (startDate.isBefore(currentDate) || startDate.isEqual(currentDate)) {
//                    dates.add(startDate);
//                    startDate = startDate.plusDays(1);
//                }
//                String[] selectedClassIds = request.getParameterValues("selectedClasses");
//                
//                Map<String, Map<LocalDate, Integer>> countsMap = new HashMap<>();
//                for(String id : selectedClassIds)
//                {
//                    ProductClass temp = productClassFacade.find(id);
//                    Map<LocalDate, Integer> classCounts = new HashMap<>();
//                    for (LocalDate date : dates) {
//                        int count = 0;
//                        for (Product product : temp.getProducts()) {
//                            if ("paid".equals(product.getStatus()) && product.getSaleRecords().getDt().toLocalDate().isEqual(date)) {
//                                count++;
//                            }
//                        }
//                        classCounts.put(date, count);
//                    }
//                    countsMap.put(temp.getId(), classCounts);
//                }
//                String[] xValues = dates.stream().map(Object::toString).toArray(String[]::new);
//                List<Integer[]> yValuesList = new ArrayList<>();
//                for (ProductClass productClass : productClassList) {
//                    Map<LocalDate, Integer> classCounts = countsMap.get(productClass.getId());
//                    Integer[] yValues = dates.stream().map(classCounts::get).toArray(Integer[]::new);
//                    yValuesList.add(yValues);
//                }
//                
//                
//                request.setAttribute("lineChart", lineChart);
//                request.getRequestDispatcher("productReport.jsp").forward(request, response);
//                out.println("<br><br><br>"+idTemp+", product profile is updated!");
//            }catch(Exception e){
//                    request.setAttribute("index.html", e);
//                
//            }
//        }
//    }
    private void postProductReporttest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession(false);  
        try (PrintWriter out = response.getWriter()) {
            Long idTemp = null;
            LocalDate today = LocalDate.now();
            try{
                List<LocalDate> dates = new ArrayList<>();
                LocalDate startDate = LocalDate.now().minusWeeks(2);
                LocalDate currentDate = LocalDate.now();
                while (startDate.isBefore(currentDate) || startDate.isEqual(currentDate)) {
                    dates.add(startDate);
                    startDate = startDate.plusDays(1);
                }
                System.out.println("charting2");
                String[] selectedClassIds = request.getParameterValues("selectedClasses");
                ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
                for(String id : selectedClassIds)
                {
                    int n = 0;
                    ProductClass temp = productClassFacade.find(id);

                    // Initialize a count variable for this class
                    int count = 0;

                    // Loop through the class's products
                    for (Product product : temp.getProducts()) {
                        // If the product is paid and was sold today, increment the count
                        if ("paid".equals(product.getStatus()) && product.getSaleRecords().getDt().toLocalDate().isEqual(today)) {
                            count++;
                        }
                    }
                    System.out.println("charting3");
                    Label label = new Label(temp.getName());
                    label.setText(temp.getName());
                    PieChart.Data data = new PieChart.Data(temp.getName(), count);
                    pieChartData.add(data);
                    n++;
                }
                // Create a PieChart object with the paid counts for each class
                                    System.out.println(pieChartData);
                PieChart pieChart = new PieChart(pieChartData);
                System.out.println("charting");
                // Set the request attribute to the PieChart object
                request.setAttribute("pieChart", pieChart);
                request.getRequestDispatcher("productChart.jsp").forward(request, response);
                out.println("<br><br><br>"+idTemp+", product profile is updated!");
            }catch(Exception e){
                System.out.println("<br><br><br>Sorry "+e+", Invalid Password");
                request.setAttribute("index.html", e);
                
            }
        }
    }
    
    private void postDailyAnalysis(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession(false);  
        System.out.println("START");
        try (PrintWriter out = response.getWriter()) {
            Long idTemp = null;
            try{
                List<LocalDate> dates = new ArrayList<>();
                System.out.println("check1");
                String tempDate = (String)request.getParameter("date");
                System.out.println(tempDate);
                LocalDate localDate = LocalDate.parse(tempDate);
                System.out.println("check3");
                List<String> productClassName = new ArrayList<>();
                List<Integer> productClassCount = new ArrayList<>();
                double revenue = 0;
                System.out.println("charting2");
                List<ProductClass> productClassList = null;
                productClassList = productClassFacade.findAll();
                System.out.println(productClassList);
                for(ProductClass productClass : productClassList)
                {
                    System.out.println(productClass);
                    String tempName = productClass.getName();
                    System.out.println(tempName);
                    productClassName.add(tempName);
                    int count = 0;
                    System.out.println(tempName);
                    // Loop through the class's products
                    for (SaleRecords saleRecords : productClass.getSaleRecords()) {
                        // If the product is paid and was sold today, increment the count
                        if (saleRecords.getDt().toLocalDate().isEqual(localDate)) {
                            count++;
                            revenue = revenue + saleRecords.getId().getPrice();
                        }
                    }
                    productClassCount.add(count);
                    System.out.println(productClassCount);
                }
                System.out.println("tempeayam2");
                Set<Color> colorSet = new HashSet<>();
                Random random = new Random();
                System.out.println("tempeayam3");
                while (colorSet.size() < productClassName.size()) {
                    int r = random.nextInt(256);
                    int g = random.nextInt(256);
                    int b = random.nextInt(256);
                    Color color = new Color(r, g, b);
                    colorSet.add(color);
                }
                System.out.println("tempeayam4");
                List<Color> colors = new ArrayList<>(colorSet);
                
                request.setAttribute("colors", colors);
                System.out.println(colors);
                request.setAttribute("productClassName", productClassName);
                request.setAttribute("productClassCount", productClassCount);
                request.setAttribute("date", tempDate);
                request.setAttribute("revenue", revenue);
                
                // Set the request attribute to the PieChart object
//                request.setAttribute("pieChart", pieChart);
                request.getRequestDispatcher("dailyAnalysisChart.jsp").forward(request, response);
//                out.println("<br><br><br>"+idTemp+", product profile is updated!");
            }catch(Exception e){
                System.out.println("<br><br><br>Sorry "+e+", Invalid Password");
                request.setAttribute("index.html", e);
                
            }
        }
    }
    private void postMonthlyAnalysis(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession(false);  
        System.out.println("START");
        try (PrintWriter out = response.getWriter()) {
            Long idTemp = null;
            try{
                List<LocalDate> dates = new ArrayList<>();
                System.out.println("check1");
                String tempDate = (String)request.getParameter("date")+"-01";
                System.out.println(tempDate);
                LocalDate localDate = LocalDate.parse(tempDate);
                System.out.println("check3");
                List<String> productClassName = new ArrayList<>();
                List<Integer> productClassCount = new ArrayList<>();
                List<Color> colorCollection = new ArrayList<>();
                double revenue = 0;
                
                System.out.println("charting2");
                
                List<ProductClass> productClassList = null;
                productClassList = productClassFacade.findAll();
                System.out.println(productClassList);
                for(ProductClass productClass : productClassList)
                {
                    System.out.println(productClass);
                    String tempName = productClass.getName();
                    System.out.println(tempName);
                    productClassName.add(tempName);
                    int count = 0;
                    System.out.println(tempName);
                    // Loop through the class's products
                    for (SaleRecords saleRecords : productClass.getSaleRecords()) {
                        // If the product is paid and was sold today, increment the count
                        if (saleRecords.getDt().getMonth().equals(localDate.getMonth())) {
                            count++;
                            revenue = revenue + saleRecords.getId().getPrice();
                        }
                    }
                    System.out.println(count);
                    productClassCount.add(count);
                }
                
                Set<Color> colorSet = new HashSet<>();
                Random random = new Random();

                while (colorSet.size() < productClassName.size()) {
                    int r = random.nextInt(256);
                    int g = random.nextInt(256);
                    int b = random.nextInt(256);
                    Color color = new Color(r, g, b);
                    colorSet.add(color);
                }

                List<Color> colors = new ArrayList<>(colorSet);
                
                request.setAttribute("colors", colors);
                request.setAttribute("productClassName", productClassName);
                request.setAttribute("productClassCount", productClassCount);
                request.setAttribute("month", localDate.getMonth());
                request.setAttribute("revenue", revenue);
                
                // Set the request attribute to the PieChart object
//                request.setAttribute("pieChart", pieChart);
                request.getRequestDispatcher("monthlySalesChart.jsp").forward(request, response);
//                out.println("<br><br><br>"+idTemp+", product profile is updated!");
            }catch(Exception e){
                System.out.println("<br><br><br>Sorry "+e+", Invalid Password");
                request.setAttribute("index.html", e);
                
            }
        }
    }
    
    private void postProductAnalysis(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession(false);  
        System.out.println("START");
        try (PrintWriter out = response.getWriter()) {
            Long idTemp = null;
            try{
                String id = (String)request.getParameter("selectedClasses");
                ProductClass productClass = productClassFacade.find(id);
                double revenue = 0;
                int male = 0;
                int female =  0;
                int count = productClass.getSaleRecords().size();
                // Loop through the class's products
                for (SaleRecords saleRecords : productClass.getSaleRecords()) {
                    revenue = revenue + saleRecords.getId().getPrice();
                    if ("Male".equals(saleRecords.getCustomer().getGender())){
                        male++;
                    }else{
                        female++;
                    }
                }
                List<Integer> MFCount = new ArrayList<>();
                MFCount.add(male);
                MFCount.add(female);
                System.out.println(male);
                System.out.println(female);
                System.out.println(MFCount);
                
                request.setAttribute("MFCount", MFCount);
                request.setAttribute("count", count);
                request.setAttribute("revenue", revenue);
                
                // Set the request attribute to the PieChart object
//                request.setAttribute("pieChart", pieChart);
                request.getRequestDispatcher("productAnalysisChart.jsp").forward(request, response);
//                out.println("<br><br><br>"+idTemp+", product profile is updated!");
            }catch(Exception e){
                System.out.println("<br><br><br>Sorry "+e+", Invalid Password");
                request.setAttribute("index.html", e);
                
            }
        }
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

}
