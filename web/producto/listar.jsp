<%-- 
    Document   : listar
    Created on : oct 6, 2014, 5:46:11 p.m.
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
                
                <%if (user != null && user.getTipo_usuario()== 0) {%>
                    <a href="/paronline/register.jsp">Crear cuenta nueva</a>
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
    <nav class="menu1">
        <ul>
            <%if (user != null && user.getTipo_usuario()== 0) {%>
                <li><a id="link" href='/paronline/producto/crear'>Crear Producto</a></li>
            <%}%>
        </ul>
    </nav>
    <section id="contenido">

    
        <div class="col-md-3 ">
                
        </div>

        <div class="col-md-8">
            
            <table id="lista" class="display" width="100%" cellspacing="0">
                <thead>
                    <tr style="color: #ffffff">
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Categoria</th>
                        <th>Precio</th>
                        <th>Cantidad</th>
                        <th>Deposito</th>
                        <th>Acciones</th>
                    </tr>
                </thead>

                <tfoot>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Categoria</th>
                        <th>Precio</th>
                        <th>Cantidad</th>
                        <th>Deposito</th>
                        <th>Acciones</th>
                    </tr>
                </tfoot>
                <tbody>
                   
                    <c:forEach items="${requestScope.lista}" var="producto">
                        <tr>
                            <td><c:out value="${producto.id_producto}" /></td>
                            <td><c:out value="${producto.descripcion}" /></td>
                            <td><c:out value="${producto.cat.descripcion}" /></td>
                            <td><c:out value="${producto.precio}" /></td>
                            <td><c:out value="${producto.cantidad}" /></td>
                            <td><c:out value="${producto.cantidadDeposito}" /></td>
                            <td><a href="/paronline/producto/detalle?id=${producto.id_producto}">Mas Info</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
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

    <script src="/paronline/static/js/jquery-1.11.1.min.js"></script>
    <script src="/paronline/static/js/jquery.js"></script>
    <script src="/paronline/static/js/jquery.dataTables.js"></script>
    
<script>
        $(document).ready(function() {
        $('#lista').dataTable({
            "language":{
                "url":"/paronline/static/lang/dataTables.spanish.lang.json"
            }
        });
        } );
</script>

</body>
</html>