<%@ page import="java.util.List" %>
<%@ page import="es.taw.junio2025.entity.Servicio" %>
<%@ page import="es.taw.junio2025.entity.Propietario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<%
    List<Servicio> servicios = (List<Servicio>) request.getAttribute("servicios");

%>

<body>



<table>
    <tr>
        <th>IDSERVICIO</th>
        <th>FECHA</th>
        <th>VEHICULO</th>
        <th>MARCA</th>
        <th>PROPIETARIOS DEL VEHICULO</th>
        <th>DESCRIPCION</th>

    </tr>


<%
    for(Servicio servicio : servicios){

%>
    <tr>
        <td><%=servicio.getIdServicio()%></td>
        <td><%=servicio.getFecha()%></td>
        <td><%=servicio.getVehiculo().getModelo()%></td>
        <td><%=servicio.getVehiculo().getMarca().getNombre()%></td>

        <td><%for(Propietario p : servicio.getVehiculo().getPropietarios()){%>
            <%=p.getNombre()%> |
        <%}%>
        </td>
        <td><%=servicio.getDescripcion()%></td>

    </tr>



<%}%>
</table>

</body>
</html>
