<%@ page import="es.taw.movies.entity.Movies" %>
<%@ page import="es.taw.movies.entity.Genres" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<%
    Movies movie = (Movies) request.getAttribute("movie");
    List<Genres> genres = (List<Genres>) request.getAttribute("genres");
%>


<body>
<h1>Ficha de la pelicula</h1>
<form action="/movies/save" method="post">
    <input type="hidden" name="id" value="<%=movie.getId()%>">

    <div>
        <label>Título: </label>
        <input type="text" name="title" value="<%=movie.getTitle() == null ? "" : movie.getTitle()%>">
    </div>
    <div>
        <label>Duración: </label>
        <input type="number" name="runtime" value="<%=movie.getRuntime() == null ? "" : movie.getRuntime()%>">
    </div>
    <div>
        <label>Recaudación: </label>
        <input type="number" name="revenue" value="<%=movie.getRevenue() == null ? "" : movie.getRevenue()%>">
    </div>
    <div>
        <label>Generos: </label>
        <%
            for(Genres genre : genres){
                String checked = "";
                if(movie.getGenresList().contains(genre)) checked = "checked";
        %>
        <input type="checkbox" <%=checked%> name="genres" value="<%=genre.getId()%>"> <%=genre.getName()%>
        <%
            }
        %>
    </div>

    <button type="submit">Guardar</button>


</form>
<a href="/"><-- Atras</a>


</body>
</html>
