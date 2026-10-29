<%@ page import="es.taw.movies.entity.Movies" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.movies.entity.ProductionCompanies" %>
<%@ page import="es.taw.movies.entity.SpokenLanguages" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<%
    List<Movies> movies = (List<Movies>) request.getAttribute("movies");
    List<ProductionCompanies> productionCompanies = (List<ProductionCompanies>) request.getAttribute("productionCompanies");
    List<Integer> checkedProductionCompanies = (List<Integer>) request.getAttribute("checkedProductionCompanies");
%>

<body>

<form action="/" method="get">
<table>

    <tr>
        <th>Productoras</th>
        <th>PELICULAS</th>
    </tr>

    <tr>
        <td>
        <%
            for (ProductionCompanies productionCompanie : productionCompanies){
                String checked = "";
                if(checkedProductionCompanies != null && checkedProductionCompanies.contains(productionCompanie.getId())) checked = "checked";
        %>
            <input type="checkbox" <%=checked%> value="<%=productionCompanie.getId()%>" name="productionCompanies" > <%=productionCompanie.getName()%><br>

        <%
            }
        %>
        </td>

        <td>
            <%
                for (Movies movie : movies){
            %>
            <a href="/movie/<%=movie.getId()%>"> <%=movie.getTitle()%></a> (

            <%
                for(SpokenLanguages spokenLanguages : movie.getSpokenLanguagesList()){

            %>
                <%=spokenLanguages.getName() + " "%>
            <%
                }
            %>)

            <br>

            <%
                }
            %>
        </td>


    </tr>




</table>
<button type="submit">Filtrar</button>
</form>


</body>
</html>
