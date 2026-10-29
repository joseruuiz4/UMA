<%@ page import="es.taw.movies.entity.Movies" %>
<%@ page import="es.taw.movies.entity.SpokenLanguages" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%
    Movies movie = (Movies) request.getAttribute("movie");
    List<SpokenLanguages> languagesList = (List<SpokenLanguages>) request.getAttribute("languages");
%>

<body>

<table>

    <tr>
        <td>
<form action="/movie/save" method="post">
    <input type="hidden" name="id" value="<%=movie.getId()%>">


<div>
    <label>Titulo:</label>
    <input type="text" name="title" value="<%=movie.getTitle()%>">
</div>
<div>
    <label>Fecha de publicacion:</label>
    <input type="date" name="date" value="<%=movie.getReleaseDate()%>">
</div>
<div>
    <label>Presupuesto:</label>
    <input type="number" name="budget" value="<%=movie.getBudget()%>">
</div>
<div>
    <label>Idiomas disponibles:</label>
    <%
        for(SpokenLanguages idioma : languagesList){
            String checked = "";
            if(movie.getSpokenLanguagesList() != null && movie.getSpokenLanguagesList().contains(idioma)) checked = "checked";
    %>
    <input type="checkbox" <%=checked%> name="languages" value="<%=idioma.getIso6391()%>"> <%=idioma.getName()%>
    <%
        }
    %>
</div>
<div>
    <label>Sinopsis:</label>
    <textarea name="overview" rows="5" cols="100"> <%=movie.getOverview()%></textarea>
</div>
<button type="submit">Guardar</button>
</form>

        </td>
        <td>

            <a href="/">Reparto</a>
            |
            <a href="/">Trabajadores</a>


        </td>


    </tr>

</table>



<a href="/"><-- Atrás</a>
</body>
</html>
