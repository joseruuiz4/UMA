<%@ page import="es.taw.movies.entity.People" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.movies.entity.MovieCast" %>
<%@ page import="es.taw.movies.entity.MovieCrew" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<html>
<head>
    <title>Title</title>
</head>

<%
    List<People> peopleList = (List<People>) request.getAttribute("people");

%>

<body>
<h1>Lista de personas</h1>

<form action="/movies/filter" method="get">
    <div>
        <input type="radio" name="tipo" value="Reparto"> Reparto
        <input type="radio" name="tipo" value="Trabajadores"> Trabajadores
    </div>
    <div>
        <label>y contiene</label>
        <input type="text" name="filter" value="">
    </div>



    <button type="submit">Filtrar</button>
    <a href="/">Borrar Filtro</a>

</form>


<table>
    <tr>
        <th>Nombre</th>
        <th>PELICULAS-PERSONAJES</th>
        <th>PELICULAS-CARGOS</th>

    </tr>

    <%
        for(People people : peopleList){

    %>
    <tr>
        <td><%=people.getName()%></td>
        <td>
            <%
                for(MovieCast movieCast : people.getMovieCastList()){
            %>

            <a href="/movies/edit/<%=movieCast.getMovieId().getId()%>"> <%=movieCast.getMovieId().getTitle()%> </a> - <%=movieCast.getCharacterName()%>
            <%
                }
            %>
        </td>
        <td>
            <%
                for(MovieCrew movieCrew : people.getMovieCrewList()){
            %>
            <a href="/movies/edit/<%=movieCrew.getMovieId().getId()%>"> <%=movieCrew.getMovieId().getTitle()%> </a> - <%=movieCrew.getJob()%><br>
            <%
                }
            %>
        </td>

    </tr>
    <%
        }
    %>


</table>



</body>
</html>
