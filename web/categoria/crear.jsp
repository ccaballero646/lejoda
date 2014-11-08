<%-- 
    Document   : crear
    Created on : oct 14, 2014, 7:34:58 p.m.
    Author     : Cristhian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Usuario"%>
<%@page import="model.Categoria"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1"/>
	<title>parOnline </title>
        <link rel="stylesheet" href="/paronline/static/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="/paronline/static/css/bootstrap-theme.css"/>
        <link rel="stylesheet" href="/paronline/static/css/estilos.css" />
        <link rel="stylesheet" href="/paronline/static/css/jquery.dataTables.css" />
</head>
<body>
    <header>
            <figure id="logo">
                <a href="/paronline"><img src="/paronline/static/images/shield.jpg" /></a>
            </figure>
            <h1>
                ParOnline
            </h1>
            <div id="avatar">
                <%Usuario user = (Usuario) session.getAttribute("usuario"); %>
                <%if (user != null) {%> 
                <h5>Bienvenido, <%=user.getNombre()%>  (<a href='/paronline/perfil.jsp?username=<%=user.getLoginName()%>' >Perfil</a>) (<a id="link" href='/paronline/cerrar'>Cerrar Sesión</a>) </h5>
                
                <%} else {%>
                    <a id="link" href='/paronline/login.jsp'>Ingresar</a>
                <%}%>
            </div>
    </header>
    <nav>
        <ul>
            <li><a id="link" href='/paronline/productos/listar'>Lista de Productos</a></li>
            <%if (user != null && user.getTipo_usuario()== 0) {%>
                <li><a id="link" href='/paronline/categoria/listar'>Categorias</a></li>
            <%}%>
            <li><a id="link" href='/paronline/carrito/listar'>Carrito</a></li>
            <li class="link"><a id="link" href='/paronline/transaccion/historial'>Historial de Compra</a></li>
	</ul>
    </nav>
    <section id="contenido">

    
        <div class="col-md-3 ">
                
        </div>

        <div class="col-md-8">
            <form action="/paronline/categoria/crear" method="POST">
                <table>
                <tr>
                    <td>
                        <strong>Descripcion</strong>:
                    </td>
                    <td>
                        <input type="text" name="descripcion">
                    </td>
                </tr>
            </table>
            <input type="submit" value="Guardar"><br>
            </form>
         </div>

    </section>

    <footer>
        <p>
            <strong>Cristhian Caballero®</strong>
        </p>
        <p>
            Programacion de Aplicaciones en Redes-2014
        </p>
    </footer>

</body>
</html>