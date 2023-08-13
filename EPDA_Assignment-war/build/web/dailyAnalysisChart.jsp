<%-- 
    Document   : productChart
    Created on : Mar 2, 2023, 3:41:17 PM
    Author     : yelia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script
            src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js">
        </script>
        <title>JSP Page</title>
    </head>
        <body>
            <input type="hidden" id="productClassName" value="${productClassName}" />
            <input type="hidden" id="productClassCount" value="${productClassCount}" />
            <input type="hidden" id="colors" value="${colors}" />
            <input type="hidden" id="date" value="${date}" />
            
            <canvas id="myChart" style="width:100%;max-width:700px"></canvas>
            
            Total Revenue = RM${revenue};
        <script>
            var xValues = document.getElementById("productClassName").value.split(",");
            for (var i = 0; i < xValues.length; i++) 
                {xValues[i] = xValues[i].substring(1, xValues[i].length - 1);}
            console.log(xValues);
            var yValues = document.getElementById("productClassCount").value.split(",");
            for (var i = 0; i < yValues.length; i++) {
                if (i === 0){
                    yValues[i] = parseInt(yValues[i].substring(1));
                }else{
                    yValues[i] = parseInt(yValues[i]);
                }
  }        var barColors = [
                <c:forEach items="${colors}" var="color">
                    "rgb(${color.red}, ${color.green}, ${color.blue})",
                </c:forEach>
    ];  
            new Chart("myChart", {
              type: "pie",
              data: {
                labels: xValues,
                datasets: [{
                  backgroundColor: barColors,
                  data: yValues
                }]
              },
              options: {
                title: {
                  display: true,
                  text: "Revenue for "+document.getElementById("date").value
                }
              }
            });
         </script>
    </body>
</html>
