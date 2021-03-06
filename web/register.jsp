<%-- 
    Document   : register
    Created on : sep 11, 2014, 6:06:44 p.m.
    Author     : Cristhian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Usuario"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1"/>
	<title>parOnline </title>
        <link rel="stylesheet" href="/paronline/static/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="/paronline/static/css/bootstrap-theme.css"/>
        <link rel="stylesheet" href="/paronline/static/css/estilos.css" />
</head>
<body>
    <header>
            <figure id="logo">
                <a href="/paronline"><img src="static/images/shield.jpg" /></a>
            </figure>
            <h1>
                ParOnline
            </h1>
            <div id="avatar">
                <%Usuario user = (Usuario) session.getAttribute("usuario"); %>
                <%if (user != null) {%> 
                <h5>Bienvenido, <%=user.getNombre()%>  (<a href='perfil.jsp?username=<%=user.getLoginName()%>' >Perfil</a>) (<a id="link" href='cerrar'>Cerrar Sesión</a>) </h5>
                
                <%} else {%>
                    <a id="link" href='login.jsp'>Ingresar</a>
                <%}%>
            </div>
    </header>
    <nav>
        <ul>
            <li><a id="link" href='productos/listar'>Lista de Productos</a></li>
            <%if (user != null && user.getTipo_usuario()== 0) {%>
                <li><a id="link" href='categoria/listar'>Categorías</a></li>
            <%}%>
            <li><a id="link" href='carrito/listar.jsp'>Carrito</a></li>
            <li class="link"><a id="link" href='transaccion/historial'>Historial de Venta</a></li>
	</ul>
    </nav>
    <section id="contenido">

    
        <div class="col-md-3 ">
                
        </div>

        <div class="col-md-8">
            <h3>Complete todos los campos.</h3>
        <form action="Register" method="post">
            <table>
                <tr>
                    <td>
                        <strong>Nombre y Apellido</strong>:
                    </td>
                    <td>
                        <input type="text" name="nombre">
                    </td>
                </tr>
                <tr>
                    <td>
                        <strong>Nombre de usuario</strong>:
                    </td>
                    <td>
                        <input type="text" name="login_name">
                    </td>
                </tr>
                <tr>
                    <td>
                        <strong>Password</strong>:
                    </td>
                    <td>
                        <input type="password" name="password">
                    </td>
                </tr>
                <tr>
                    <td>
                        <strong>Repetir Password</strong>:
                    </td>
                    <td>
                        <input type="password" name="password2">
                    </td>
                </tr>
                <tr>
                    <td>
                        <strong>Tipo de usuario</strong>:
                    </td>
                    <td>
                        <select name="tipo_usuario" size="1">
                            <%if (user != null && user.getTipo_usuario()== 0) {%>
                                <option value="0">Administrador</option>
                            <%}%>    
                            <option value="1">Usuario normal</option>
                        </select>
                    </td>
                </tr>
            </table>
            <input type="submit" value="Registrarse">
            <input type="reset" value="Limpiar datos">
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

    <script src="static/js/jquery-1.11.1.min.js"></script>


</body>
</html>