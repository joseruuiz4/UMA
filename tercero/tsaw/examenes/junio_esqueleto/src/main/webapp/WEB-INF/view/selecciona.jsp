<%@ page import="es.taw.junio2025.entity.Marca" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.junio2025.entity.Vehiculo" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<%
    List<Marca> marcas = (List<Marca>) request.getAttribute("marcas");
    List<Vehiculo> vehiculos = (List<Vehiculo>) request.getAttribute("vehiculos");
    Integer marcaID = (Integer) request.getAttribute("marcaID");

%>

<body>
<h1>Selecciona:</h1>
<form action="/<%=marcaID == null ? "" : "servicios"%>" method="get">


Marca:
<br>
<%
    for(Marca marca : marcas){
        String checked="";
        if(marca.getIdMarca().equals(marcaID)) checked = "checked";

%>
<input type="radio" <%=checked%> <%=marcaID==null?"":"disabled"%> name="marca" value="<%=marca.getIdMarca()%>"> <%=marca.getNombre()%> <br>
<%
    }
%>
    <%=vehiculos ==null ? "" : "Vehiculos"%>

    <%
        if(vehiculos!= null){
        for(Vehiculo vehiculo : vehiculos){
    %>
    <input type="checkbox" value="<%=vehiculo.getModelo()%>" name="vehiculos"> <%=vehiculo.getModelo()%>



    <%
        }
        }
    %>


    <br>
    <button type="submit">Enviar</button>
</form>

<a href="/"><-- Atras</a>


</body>
</html>
