
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="server" uri="/WEB-INF/tags/serverProperties.tld" %>
<%@ taglib prefix="agrega" uri="/WEB-INF/tags/agregaProperties.tld" %>
<%@ taglib prefix="i18n" uri="/WEB-INF/tags/i18nProperties.tld" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib prefix="buscadorCas" uri="/WEB-INF/tags/buscadorCasTag.tld" %>
<%@ taglib uri="/WEB-INF/tags/logos.tld" prefix="logos" %>
<%@ page import="java.io.InputStream"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ taglib prefix="el" uri="/WEB-INF/tags/configurarPortalTag.tld" %>

<?xml version="1.0" encoding="iso-8859-1" ?> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="es" xml:lang="es">
 
<% 
String locale = request.getParameter("locale");
String urlLogout = request.getParameter("urlLogout");
String urlVuelta = "";
if(!(locale == null))
{
	
	Cookie cookie1 = new Cookie("org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE",request.getParameter("locale"));
    cookie1.setMaxAge(-1);
    cookie1.setPath("/");
    response.addCookie(cookie1); 
    
    try
    {
    	InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/authbackend.properties");
    	java.util.Properties pSpringPropertiesAuth = new java.util.Properties();
		pSpringPropertiesAuth.load(fIsSpringProperties);
		urlVuelta = pSpringPropertiesAuth.getProperty("cas.http.url")+"/logout?idioma="+locale+"&urlLogout="+urlLogout;
		
    }catch(Exception e)
    {
    	urlVuelta = "/logout&urlLogout="+urlLogout;
    }
  	
    response.sendRedirect(urlVuelta);
}
		
 %>

<head lang="en" dir="ltr">

<title>
	
	Agrega - Portada

</title>
<link rel="shortcut icon" href="<rewrite:rewrite url="static/img/favicon.ico"/>" />
<link rel="stylesheet" media="screen"  type="text/css" href="<rewrite:rewrite url="static/css/red.css"/>" />
<link rel="stylesheet" media="screen" href="<rewrite:rewrite url="logos/css/logos_patrocinadores.css"/>" type="text/css" />
<script type="text/javascript" src="<rewrite:rewrite url="static/js/plantilla.js"/>"></script>
<script type="text/javascript" src="<rewrite:rewrite url="static/js/curvas.js"/>"></script>
<script type="text/javascript" src="<rewrite:rewrite url='static/js/jquery-1.7.2.min.js'/>"></script>

<!--[if lte IE 6]>
<style title="Hoja de estilo oculta para Internet Explorer">
@import url("/static/css/ancho_maximo.css"); 
</style>
<![endif]-->

</head>

<body>

<!--  CAPA QUE CONTIENE TODAS LAS DEMAS  -->
<!--  INICIO CAPA MADRE   -->
<div id="capa_madre">
<!--  INICIO CUERPO GENERAL   -->
<div id="cuerpo">
<div class="minwidth">
<div class="contenido_general">

<!-- Aqui va la cabecera -->
<div id="cabecera">
<h1><span><agrega:agregaProperties property="ccaa"/></span></h1>

<!--AJAX 20660905-->


<!--FIN AJAX 20660905-->
<!-- INICIO METANAVEGACION   -->
<div id="metanavegacion">
<ol>
		<li class="oculto"><a href="#contenido_central" title="Contenido Principal"><strong><spring:message code="contenido"/></strong></a></li>
		<!-- Estos dos links cambian cuando el usuario se ha autenticado -->
		<!-- TODO: Cambiar en fase II cuando se use autenticacion -->
		<!-- <li><a href="#" title="Accesibilidad" id="registro">Accesibilidad</a></li>
		<li><a href="#" title="Acceso Zona Restringida" id="restringida">Acceso Zona Restringida</a></li> -->
		<!-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
			
		<c:if test="${!el:esTaller()}">
		<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos/AcercaDeAgrega/AcercaDeAgrega.do" title="<spring:message code="acercaAgrega"/>" ><spring:message code="acercaAgrega"/></a></li>
		<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos/Accesibilidad/Accesibilidad.do" title="<spring:message code="accesibilidad"/>"><spring:message code="accesibilidad"/></a></li>
		<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos/Faqs/Faqs.do" title="<spring:message code="faqs"/>"><spring:message code="faqs"/></a></li>
		<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos/Mapa/Mapa.do" title="<spring:message code="mapa"/>"><spring:message code="mapa"/></a></li>
		<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos/Contacto/Contacto.do" title="<spring:message code="contacto"/>" id="contacto"><spring:message code="contacto"/></a></li>
		</c:if>
</ol>
</div>
<!-- FIN METANAVEGACION   -->
<!--AJAX 20660905-->
<!--FIN AJAX 20660905-->

<br class="oculto" />
<a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos/Portada/Portada.do" title="<spring:message code="paginaInicio"/>" id="logo"><span><spring:message code="paginaInicio"/></span></a>
<br class="oculto" />
</div>

<!-- Inicio cuerpo central de pagina con Menu Principal, capa Lateral y capa Central  -->
<div class="tronco_de_contenido">
<!-- Menu de cabecera -->
<!--AJAX 20660905-->
<!--FIN AJAX 20660905-->
<!-- Inicio MENU PRINCIPAL -->
<!-- Inicio MENU PRINCIPAL -->
<div id="madre_barra">
<div class="fondolat"></div>
</div>
<!-- Fin MENU PRINCIPAL -->
<!-- Fin MENU PRINCIPAL -->



<!-- Inicio Contenido ESPECÍFICO  -->



<!--AJAX 20660905-->


<!--FIN AJAX 20660905-->


<div id="contenido_central_largo">
	
<div class="plantilla_contenido">
<form method="post" action="${param.urlLogout}">
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario" class="ali_c">

			<br/>
			 <p><img src="<rewrite:rewrite url="static/img/v.gif"/>"/><spring:message code="sesionExpirada"/>.</p>
			 <br /><br />
				<br />
				</div>
				<!--  FIN CAJA DE FORMULARIO   -->
			</div>
		</div>

	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->

<!-- Inicio Botones  -->
<!-- Inicio Botones  -->
<fieldset class="tipo ft_centrada">
<input class="boton_125"  type="submit"  value="<spring:message code="aceptar"/>" />
</fieldset>
<!-- Fin Botones  -->
<!-- Fin Botones  -->
</form>
</div>
</div>


<!-- Fin cuerpo central de pagina con Menu Principal, capa Lateral y capa Central  -->

<!-- Pie de pagina -->
<!--AJAX 20660905-->


<!--FIN AJAX 20660905-->
<!-- INICIO PIE DE PAGINA -->
<!-- INICIO PIE DE PAGINA -->
<div id="pie_pagina">
<hr />

<iframe style="display:none" src="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/PortalEmpaquetador/invalidarsesion.jsp"></iframe>
<iframe style="display:none" src="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/gestorFlujo/invalidarsesion.jsp"></iframe>
<iframe style="display:none" src="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/portaladministracion/invalidarsesion.jsp"></iframe>
<iframe style="display:none" src="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos/invalidarsesion.jsp"></iframe>
<iframe style="display:none" src="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/TaggingWeb/invalidarsesion.jsp"></iframe>
<iframe style="display:none" src="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/buscador/invalidarsesion.jsp"></iframe>
<iframe style="display:none" src="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizador-1/invalidarsesion.jsp"></iframe>
<iframe style="display:none" src="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/catalogadorWeb/invalidarsesion.jsp"></iframe>
<iframe style="display:none" src="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos2/invalidarsesion.jsp"></iframe>
<iframe style="display:none" src="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/buscador2/invalidarsesion.jsp"></iframe>
<iframe style="display:none" src="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/ModificadorWeb/invalidarsesion.jsp"></iframe>

<c:if test="${!el:esTaller()}">
<a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos/InformacionLegal/InformacionLegal.do"  id="info" title="<spring:message code="informacionLegal"/>" ><span><spring:message code="informacionLegal"/></span></a> 
<a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos/PoliticaPrivacidad/PoliticaPrivacidad.do" id="politica" title="<spring:message code="politicaPrivacidad"/>"><span><spring:message code="politicaPrivacidad"/></span></a> <br class="oculto" />
</c:if>
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- INCLUDE DE LOS LOGOS DE LOS COLABORADORES Y PATROCINADORES  -->
<div id="patros_con_ccaa">
<logos:renderLogoSet htmlFile="uploads/logos/logos-pie_actualizado.html" language="${param.locale}"/>
</div>
<!-- FIN DEL INCLUDE DE LOS LOGOS DE LOS COLABORADORES Y PATROCINADORES  -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->

<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
</div>


</div>
</div>
</div>
</div>
<!--  FIN CUERPO GENERAL   -->

</div>
<!--  FIN CAPA MADRE   -->
</body>
</html>
