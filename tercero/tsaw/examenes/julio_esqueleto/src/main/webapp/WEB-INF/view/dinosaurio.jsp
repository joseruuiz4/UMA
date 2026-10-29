<%@ page import="es.taw.julio2025.entity.Dinosaurio" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.julio2025.entity.Habitat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%
    List<Dinosaurio> dinos = (List<Dinosaurio>) request.getAttribute("dinos");
    List<Habitat> habitats = (List<Habitat>) request.getAttribute("habitats");


%>

<body>
<h1>Dinosaurios:</h1>

<form action="/" method="get">
    <div>
        <label>Hábitat:</label>
        <select name="habitat">
            <option value="">---</option>

            <%for(Habitat habitat : habitats){%>
            <option value="<%=habitat.getId()%>"><%=habitat.getNombre()%></option>
            <%}%>
        </select>
    </div>
    <div>
        <label>Tamaño ></label>
        <input type="text" size="2" name="tamaño">
    </div>
    <div>
        <label>Peso < </label>
        <input type="text" size="2" name="peso">
    </div>



    <button type="submit">Filtrar</button>

</form>


<table>
    <tr>
        <th>NOMBRE</th>
        <th>TAMAÑO</th>
        <th>PESO</th>
        <th>PERIODO</th>
        <th>DIETA</th>
        <th>HABITATS</th>
    </tr>


<%for(Dinosaurio dino : dinos){%>
    <tr>
        <td><%=dino.getNombre()%></td>
        <td><%=dino.getTamaño_metros()%></td>
        <td><%=dino.getPeso_toneladas()%></td>
        <td><%=dino.getPeriodo().getNombre()%></td>
        <td><%=dino.getDieta().getTipo()%></td>
        <td><%=dino.getHabitatsString()%></td>
        <td><a href="/duplicar/<%=dino.getId()%>">Duplicar</a></td>
        <td><a href="/borrar/<%=dino.getId()%>">Borrar</a></td>

    </tr>



<%}%>

</table>

<a href="/"><--Atras</a>

</body>
</html>
