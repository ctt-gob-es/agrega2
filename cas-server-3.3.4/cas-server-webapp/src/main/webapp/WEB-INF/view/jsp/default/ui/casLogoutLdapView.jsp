<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="server" uri="/WEB-INF/tags/serverProperties.tld" %>
<%@ taglib prefix="agrega" uri="/WEB-INF/tags/agregaProperties.tld" %>
<%@ taglib prefix="i18n" uri="/WEB-INF/tags/i18nProperties.tld" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@page import="es.agrega.soporte.agregaProperties.AgregaProperties"%>
<%@page import="es.agrega.soporte.agregaProperties.AgregaPropertiesImpl"%>
<?xml version="1.0" encoding="iso-8859-1" ?> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">

<%
	
AgregaProperties properties = AgregaPropertiesImpl.getInstance();
String entorno = properties.getProperty(AgregaProperties.HOST);
String subdominio = properties.getProperty(AgregaProperties.SUBDOMINIO); 
String urlLogout = request.getParameter("urlLogout");
	response.sendRedirect("http://" +  entorno + subdominio+ "/visualizadorcontenidos/");
	
%>
	
<head lang="en" dir="ltr">

<title>
	
	Agrega - Portada

</title>
<link rel="shortcut icon" href="<rewrite:rewrite url="static/img/favicon.ico"/>" />
<link rel="stylesheet" media="screen"  type="text/css" href="<rewrite:rewrite url="static/css/red.css"/>" />
<script type="text/javascript" src="<rewrite:rewrite url="static/js/plantilla.js"/>"></script>
<script type="text/javascript" src="<rewrite:rewrite url='static/js/jquery-1.7.2.min.js'/>"></script>

<!--[if lte IE 6]>
<style title="Hoja de estilo oculta para Internet Explorer">
@import url("/static/css/ancho_maximo.css"); 
</style>
<![endif]-->

</head>

<body>

</body>
</html>
