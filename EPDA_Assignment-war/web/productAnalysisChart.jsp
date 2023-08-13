<%-- 
    Document   : productAnalysisChart
    Created on : Mar 6, 2023, 7:25:49 PM
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
            <input type="hidden" id="MFCount" value="${MFCount}" />
            
            <canvas id="myChart" style="width:100%;max-width:700px"></canvas>
            
            Total Number of Sale = ${count};
        <br/><br/>Total Revenue       = RM${revenue};
        <script>
            var xValues = ["Male", "Female"];
            console.log(xValues);
            var yValues = document.getElementById("MFCount").value.split(",");
            for (var i = 0; i < yValues.length; i++) {
                if (i === 0){
                    yValues[i] = parseInt(yValues[i].substring(1));
                }else{
                    yValues[i] = parseInt(yValues[i]);
                }
  }
            console.log(yValues);
            var barColors = [
              "#b91d47",
              "#00aba9",
              "#2b5797",
              "#e8c3b9",
              "#1e7145"
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
                  text: "Product Sales Information"
                }
              }
            });
         </script>
    </body>
</html>
