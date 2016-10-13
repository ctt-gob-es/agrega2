<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="server" uri="/WEB-INF/tags/serverProperties.tld" %>
<%@ taglib prefix="agrega" uri="/WEB-INF/tags/agregaProperties.tld" %>
<%@ taglib prefix="el" uri="/WEB-INF/tags/configurarPortalTag.tld" %>
<%@ taglib prefix="i18n" uri="/WEB-INF/tags/i18nProperties.tld" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib uri="/WEB-INF/tags/logos.tld" prefix="logos" %>

<?xml version="1.0" encoding="iso-8859-1" ?> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">

<head lang="en" dir="ltr">
<script>
function validaMailLogin(formulario){
          var usuario = formulario.username.value;
          document.location.href='http://www.proyectoagrega.es/default/login/validarUser.php?accion=recordar_pass&usuario='+usuario;
			
          }

</script>
<title>
	
	Agrega - Portada

</title>
<link rel="shortcut icon" href="<rewrite:rewrite url="static/img/favicon.ico"/>" />
<link rel="stylesheet" media="screen"  type="text/css" href="<rewrite:rewrite url="static/css/red.css"/>" />
<link rel="stylesheet" media="screen" href="<rewrite:rewrite url="logos/css/logos_patrocinadores.css"/>" type="text/css" />
<script type="text/javascript" src="<rewrite:rewrite url="static/js/plantilla.js"/>"></script>
<script type="text/javascript" src="<rewrite:rewrite url="static/js/curvas.js"/>"></script>


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
		<c:if test="${param.locale == 'en'}">
		<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/wiki-en/index.php/visualizadorcontenidos/Login/Login.do" title="<spring:message code="ayuda"/>" id="ayuda" target="blank"><spring:message code="ayuda"/></a></li>
		</c:if>
		<c:if test="${param.locale == 'es'}">
		<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/wiki/index.php/visualizadorcontenidos/Login/Login.do" title="<spring:message code="ayuda"/>" id="ayuda" target="blank"><spring:message code="ayuda"/></a></li>
		</c:if>
		<c:if test="${param.locale == 'ca'}">
		<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/wiki-ca/index.php/visualizadorcontenidos/Login/Login.do" title="<spring:message code="ayuda"/>" id="ayuda" target="blank"><spring:message code="ayuda"/></a></li>
		</c:if>
		<c:if test="${param.locale == 'gl'}">
		<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/wiki-gl/index.php/visualizadorcontenidos/Login/Login.do" title="<spring:message code="ayuda"/>" id="ayuda" target="blank"><spring:message code="ayuda"/></a></li>
		</c:if>
		<c:if test="${param.locale == 'va'}">
		<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/wiki-va/index.php/visualizadorcontenidos/Login/Login.do" title="<spring:message code="ayuda"/>" id="ayuda" target="blank"><spring:message code="ayuda"/></a></li>
		</c:if>
		<c:if test="${param.locale == 'eu'}">
		<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/wiki-eu/index.php/visualizadorcontenidos/Login/Login.do" title="<spring:message code="ayuda"/>" id="ayuda" target="blank"><spring:message code="ayuda"/></a></li>
		</c:if>
		</c:if>
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
<c:if test="${!el:esTaller()}">
<a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos/Portada/Portada.do" title="<spring:message code="paginaInicio"/>" id="logo"><span><spring:message code="paginaInicio"/></span></a>
</c:if>
<c:if test="${el:esTaller()}">
<a href="http://www.proyectoagrega.es/default/Inicio" title="<spring:message code="paginaInicio"/>" id="logo"><span><spring:message code="paginaInicio"/></span></a>
</c:if>
<br class="oculto" />
</div>

<!-- Inicio cuerpo central de pagina con Menu Principal, capa Lateral y capa Central  -->
<div class="tronco_de_contenido">
<!-- Menu de cabecera -->

<!--AJAX 20660905-->


<!--FIN AJAX 20660905-->



<div id="madre_barra">
<div class="fondolat"></div>
</div>
<!-- Fin MENU PRINCIPAL -->
<!-- Fin MENU PRINCIPAL -->

<!-- Menu lateral -->
<!--AJAX 20660905-->
<!--FIN AJAX 20660905-->

<!-- Inicio Contenido ESPECÍFICO  -->
<div id="contenido_central">















<!--AJAX 20660905-->


<!--FIN AJAX 20660905-->


<spring:hasBindErrors name="credentials">
			<ul>
		  <c:forEach var="error" items="${errors.allErrors}">
		   <html:messages id="error" message="true" property="org.andromda.bpm4struts.errormessages">
                <div class="error"><spring:message code="${error.code}" text="${error.defaultMessage}" /></div>
       </html:messages>
		   </c:forEach>
		  </ul>
			</spring:hasBindErrors>
	
<div class="plantilla_contenido" id="loguearse">
<c:if test="${!el:esRegistro()}">
	<div class="acceso_filtro" id="acceso_filtro_sin"> 
	<div class="a_f_i" ><img src="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/imagenesPortada/imagen.jpg" /></div>
	<div  class="a_f_1" id="a_f_1_sin">	
</c:if>
<c:if test="${el:esRegistro()}">
	<div class="acceso_filtro"> 
	<div class="a_f_i" ><img src="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/imagenesPortada/imagen.jpg" /></div>
	<div  class="a_f_1" >	
</c:if>
<h2><spring:message code="acceso"/></h2>
<form name="formLogin" method="post" action="<%=response.encodeRedirectURL("login" + (request.getQueryString() != null && request.getQueryString().length() > 0 ? "?" + request.getQueryString() : ""))%>" >
<p><spring:message code="introducirDatos"/>:<br /></p>
<label for="username" >

<c:if test="${!el:esTaller()}">
<spring:message code="nombre"/>
</c:if>
<c:if test="${el:esTaller()}">
<spring:message code="email"/>
</c:if>
</label> 
<input type="text"  name="username" id="username" class="bot_fil" value="" title="<spring:message code="titleNombre"/>"  /><br />
<label for="password"><spring:message code="contrasenia"/></label> 
<input  type="password" name="password" class="bot_fil" id="password" value="" title="<spring:message code="titleContraseña"/>"  maxlength="200" /><br /><br class="oculto" />
<input type="hidden" name="lt" value="${flowExecutionKey}" />
<input type="hidden" name="_eventId" value="submit" />
<input class="boton_125"  type="submit"  value="<spring:message code="acceder"/>" />
<br/>
</form>
<c:if test="${el:esOpenId()}">
	<span class="oculto">-</span><a href="<%=response.encodeRedirectURL("openid" + (request.getQueryString() != null && request.getQueryString().length() > 0 ? "?" + request.getQueryString() : ""))%>"><spring:message code="accesoOpenId"/></a><br />
</c:if>

<c:if test="${el:esContrasena() && el:esTaller()}">
<span class="oculto">-</span><a href="javascript:validaMailLogin(document.formLogin)"><spring:message code="olvidoContrasenia"/></a>
</c:if>
<c:if test="${el:esContrasena() && (!el:esTaller())}">
<span class="oculto">-</span><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos/RecuerdoUsuario/RecuerdoUsuario.do" ><spring:message code="olvidoContrasenia"/></a>
</c:if>


<c:if test="${el:esOpenId() || el:esContrasena()}">
	<span class="oculto">.</span><br class="oculto"/>
</c:if>

</div>
<hr />

<!-- Capa Registro  -->
<c:if test="${el:esRegistro()}">
<div class="a_f_2"><h2><spring:message code="registrese"/></h2>
<p><spring:message code="registreseAhora"/>&nbsp;<i><agrega:agregaProperties property="correoCas"/></i></p>
<br class="limpiar" />
</div>
</c:if>
</div>
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


<!-- FIN PIE DE PAGINA -->
<!-- FIN PIE DE PAGINA -->




</div>
</div>
</div>
<!--  FIN CUERPO GENERAL   -->
<!--  FIN CAPA MADRE   -->
</body>
</html>
