<%-- 
    Document   : detalle
    Created on : oct 19, 2014, 6:18:35 p.m.
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
            Detalles del Producto
            <form action="/paronline/producto/detalle" method="POST">
                <table>
                <tr>
                    <td>
                        <strong>Descripcion</strong>:
                    </td>
                    <td>
                        <input type="text" name="descripcion" value=${requestScope.producto.descripcion}>
                        <input type="hidden" name="id" value=${requestScope.producto.id_producto}>
                    </td>
                </tr>
                <tr>
                    <td>
                        <strong>Categoria</strong>:
                    </td>
                    <td>
                        <input type="text" name="categoria" value=${requestScope.producto.cat.descripcion} readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <td>
                        <strong>Precio</strong>:
                    </td>
                    <td>
                        <input type="text" name="precio" value=${requestScope.producto.precio}>
                    </td>
                </tr>
                <tr>
                    <td>
                        <strong>Cantidad</strong>:
                    </td>
                    <td>
                        <input type="text" name="cantidad" value=${requestScope.producto.cantidad}>
                    </td>
                </tr>
                <tr>
                    <td>
                        <strong>Cantidad en Deposito</strong>:
                    </td>
                    <td>
                        <input type="text" name="cantidadDeposito" value=${requestScope.producto.cantidadDeposito}>
                    </td>
                </tr>
            </table>
            <input type="submit" value="Guardar"><br>
            </form>
            <br>
            <form action="/paronline/carrito/agregar" method="POST">
                <table>
                    <tr>
                        <td>
                            <strong>Cantidad</strong>:
                        </td>
                        <td>
                            <input type="text" name="cantidad_carrito" >
                            <input type="hidden" name="id_carrito" value=${requestScope.producto.id_producto}>
                        </td>
                    </tr>
                </table>
                <input type="submit" name ="form_carrito" value="Agregar al carrito">
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