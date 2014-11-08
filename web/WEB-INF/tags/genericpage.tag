<%-- 
    Document   : genericpage
    Created on : sep 10, 2014, 6:08:19 p.m.
    Author     : Cristhian
--%>

<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<head>
    <meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1"/>
	<title>parOnline </title>
        <link rel="stylesheet" href="static/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="static/css/bootstrap-theme.css"/>
        <link rel="stylesheet" href="static/css/estilos.css" />
</head>
<html>
  <body>
    <div id="header">
      <figure id="logo">
                <a href="/"><img src="/static/img/wpm_logo.jpg" /></a>
            </figure>
            <h1>
                ParOnline
            </h1>
            <div id="avatar">
                <h5>Bienvenido, usuario (<a id="link" href='/cerrar/'>Cerrar Sesión</a>) </h5>
            </div>
      <jsp:invoke fragment="header"/>
    </div>
    <div id="body">
      <script src="static/js/jquery-1.11.1.min.js"></script>
      <jsp:doBody/>
    </div>
    <div id="footer">
        <p>
            <strong>Cristhian Caballero®</strong>
        </p>
        <p>
            Programacion de Aplicaciones en Redes-2014
        </p>
      <jsp:invoke fragment="footer"/>
    </div>
  </body>
</html>