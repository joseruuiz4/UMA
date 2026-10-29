<%@ page import="java.util.List" %>
<%@ page import="es.tesaw.movies.entity.MovieEntity" %>
<%@ page import="es.tesaw.movies.entity.GenreEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista de películas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<%
    List<GenreEntity> genres =  (List<GenreEntity>) request.getAttribute("genres");
    List<GenreEntity> markedGenres =  (List<GenreEntity>) request.getAttribute("markedGenres");
%>
<body>
<h1>Lista de películas</h1>
<form action="/movies/create" method="post">
    <button type="submit">Crear</button>
</form>

<form id="filter_form" action="/movies" method="get">
    <input type="text" name="filter">
    <%
        for(GenreEntity genre : genres){
            String checked = "";
            if(markedGenres != null && markedGenres.contains(genre.getId())) checked = "checked";
    %>
    <input type="checkbox" <%=checked%> name="genres" value="<%=genre.getId()%>"> <%=genre.getName()%>
    <%
        }
    %>
    <input type="submit" value="Filter">

</form>

<div id="table">
    <jsp:include page="movies_table.jsp"/>
</div>


</body>
</html>
