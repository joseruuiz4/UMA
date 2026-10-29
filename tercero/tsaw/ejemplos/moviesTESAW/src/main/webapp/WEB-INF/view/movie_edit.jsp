<%@ page import="es.tesaw.movies.entity.MovieEntity" %>
<%@ page import="es.tesaw.movies.entity.SpokenLanguageEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.tesaw.movies.entity.GenreEntity" %><%--
  Created by IntelliJ IDEA.
  User: guzman
  Date: 18/3/26
  Time: 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar película</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<%
    MovieEntity movie = (MovieEntity) request.getAttribute("movie");
    List<SpokenLanguageEntity> languages = (List<SpokenLanguageEntity>) request.getAttribute("languages");
    List<GenreEntity> genres = (List<GenreEntity>) request.getAttribute("genres");
%>
<body>
<div class="container mt-5">
    <h2 class="mb-4">Editar Película</h2>

<form action="/movies/save" method="post">
    <% if(movie.getId() != null){%>
    <input type="hidden" name="id" value="<%= movie.getId() %>">
    <% }%>

    <div class="mb-3">
        <label class="form-label">Título:</label>
        <input class="form-control" type="text" size="100" maxlength="100" name="titulo" value="<%=movie.getId() != null ? movie.getTitle() : "" %>"/><br/>
    </div>
    <div class="mb-3">
        <label class="form-label">Título original:</label>
            <input class="form-control" type="text" name="titulo_orig" value="<%=movie.getId() != null ? movie.getOriginalTitle() : ""%>"/><br/>
    </div>
    <div class="mb-3">
        <label class="form-label">Sinopsis: </label>
        <textarea class="form-control" rows="10" cols="50" name="sinopsis"><%=movie.getId() != null ? movie.getOverview() : ""%></textarea><br/>
    </div>

    Idioma<br>
    <select name="language">
        <%
            for(SpokenLanguageEntity language : languages){
                String selected = "";
                if(movie.getId() != null && movie.getOriginalLanguage().getId().equals(language.getId())) selected = "selected";
        %>
        <option value="<%=language.getId()%>" <%= selected %> ><%= language.getName()%></option>
        <%
            }
        %>
    </select>




    <div>
        <label>Generos:</label>
        <br>
        <%
            for(GenreEntity genre : genres){
                String checked = "";
                if(movie.getId() != null && movie.getGenres().contains(genre))checked = "checked";
        %>
        <input type="checkbox" <%=checked%> name="genres" value="<%=genre.getId()%>"> <%=genre.getName()%>

        <%
            }
        %>

    </div>



    <div class="mb-3">
        <label class="form-label">Fecha de estreno: </label>
        <input class="form-control" type="date" name="fecha" value="<%= movie.getReleaseDate() %>"/><br/>
    </div>
    <div class="mb-3">
        <label class="form-label">Duración: </label>
        <input class="form-control" type="number" name="runtime" value="<%= movie.getRuntime() %>"/><br/>
    </div>
    <div class="mb-3">
        <label class="form-label">Presupuesto: </label>
        <input class="form-control" type="number" name="budget" value="<%= movie.getBudget() %>" /><br/>
    </div>
    <div class="mb-3">
        <label class="form-label">Ingresos: </label>
        <input class="form-control" type="number" name="revenue" value="<%= movie.getRevenue() %>" /><br/>
    </div>
    <div class="mb-3">
        <label class="form-label">Estado: </label>
        <input class="form-control" type="number" name="status" value="<%=movie.getStatus() %>" /><br/>
    </div>
    <div class="mb-3">
        <label class="form-label">Tagline: </label>
        <input class="form-control" type="text" size="100" maxlength="100" name="tagline" value="<%=movie.getId() != null ? movie.getTagline() : ""%>"/><br/>
    </div>
    <div class="mb-3">
        <label class="form-label">Popularidad: </label>
        <input class="form-control" type="number" name="popularity" value="<%=movie.getPopularity() %>" /><br/>
    </div>
    <div class="mb-3">
        <label class="form-label">Calificación promedio: </label>
        <input class="form-control" type="number" name="voteAverage" value="<%=movie.getVoteAverage() %>" /><br/>
    </div>
    <div class="mb-3">
        <label class="form-label">Número de votos: </label>
        <input class="form-control" type="number" name="voteCount" value="<%=movie.getVoteCount() %>" /><br/>
    </div>
    <div class="mb-3">
        <label class="form-label">Sitio web: </label>
        <input class="form-control" type="text" size="100" maxlength="100" name="homepage" value="<%=movie.getId() != null ? movie.getHomepage() : ""%>"/><br/>
    </div>

    <button type="submit">Guardar</button>
</form>
</div>
</body>
</html>
