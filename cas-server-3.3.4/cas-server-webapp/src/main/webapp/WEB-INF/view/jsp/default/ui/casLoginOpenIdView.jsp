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
@import url("//css/ancho_maximo.css"); 
</style>
<![endif]-->

</head>

<body>


<script type="text/javascript">

function cargaProveedor(s,start,size) {
	var o = document.getElementById('username');
	o.value = s;
	o.focus();

	if (window.ActiveXObject) {
		try {
			var tr = o.createTextRange();    
			tr.collapse(true);
			tr.moveStart('character', start);
			tr.moveEnd('character', size);
			tr.select();
		}
			catch (e) {
		}
	} else {
		o.setSelectionRange(start,start+size);
	}

}

function cargaUrl(s) {
	var o = document.getElementById('username');
	o.value = s;
	o.focus();

}

</script>


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
			<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos/AcercaDeAgrega/AcercaDeAgrega.do" title="<spring:message code="acercaAgrega"/>" ><spring:message code="acercaAgrega"/></a></li>
		<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos/Accesibilidad/Accesibilidad.do" title="<spring:message code="accesibilidad"/>"><spring:message code="accesibilidad"/></a></li>
		<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos/Faqs/Faqs.do" title="<spring:message code="faqs"/>"><spring:message code="faqs"/></a></li>
		<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos/Mapa/Mapa.do" title="<spring:message code="mapa"/>"><spring:message code="mapa"/></a></li>
		<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos/Contacto/Contacto.do" title="<spring:message code="contacto"/>" id="contacto"><spring:message code="contacto"/></a></li>
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



<div id="madre_barra">
<div class="fondolat"></div>
</div>
<!-- Fin MENU PRINCIPAL -->
<!-- Fin MENU PRINCIPAL -->

<!-- Menu lateral -->
<!--AJAX 20660905-->
<!--FIN AJAX 20660905-->




<!--AJAX 20660905-->


<!--FIN AJAX 20660905-->


<c:if test="${not empty param.code}">
	<div class="error">
	<spring:message code="${param.code}" text="${param.description}" />
	</div>
</c:if>


<div class="plantilla_contenido" id="loguearse">
<form method="post" action="<%=response.encodeRedirectURL("authenticationOpenId" + (request.getQueryString() != null && request.getQueryString().length() > 0 ? "?" + request.getQueryString() : ""))%>">
<c:if test="${!el:esRegistro()}">
	<div class="acceso_filtro" id="acceso_filtro_sin"> 
	<div class="a_f_i" >
		<div class="rm3">
			<div style="float:left">
				<img src="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/imagenesPortada/imagen.jpg" />
			
			</div>
		<div id="rm4">
			<h2><spring:message code="acceso"/></h2>
			<p><spring:message code="introducirDato"/>:<br /></p>
			
			<label for="username" ><spring:message code="acceso_OpenId"/></label>
			<input type="text"  name="username" id="username" class="bot_fil" value="" title="<spring:message code="titleNombre"/>"  /><br />
			<input type="hidden" name="lt" value="${flowExecutionKey}" />
			<input type="hidden" name="_eventId" value="submit" />
			<input type="hidden" name ="openid.return_to" value="<%=request.getParameter("service")%>"/>
			<input type="hidden" name ="return_to" value="<%=request.getParameter("service")%>"/>
			<div class="autenticacion_externa">
				<label class="auten"><spring:message code="accesoUsuarioPass"/>:</label>
				<label class="auten"><spring:message code="accesoUsuarioPass"/>:</label>
				<img src="<rewrite:rewrite url="static/img/icono_yahoo.gif"/>" alt="Autenticación Yahoo" title="Autenticación Yahoo" onclick="cargaProveedor('https://me.yahoo.com/username',21,8);return false;" onkeypress="cargaProveedor('https://me.yahoo.com/username',21,8);return false;"  />
				<img src="<rewrite:rewrite url="static/img/rs_google.gif"/>" alt="Google" title="Autenticación Google" onclick="cargaProveedor('https://www.google.com/accounts/o8/id');return false;" onkeypress="cargaProveedor('https://www.google.com/accounts/o8/id');return false;"  />
			</div>
			<input class="boton_125"  type="submit"  value="<spring:message code="acceder"/>" />
			<span class="oculto">-</span><a href="<%=response.encodeRedirectURL("login" + (request.getQueryString() != null && request.getQueryString().length() > 0 ? "?" + request.getQueryString() : ""))%>" ><spring:message code="accesoUsuarioPass"/></a>
		</div>
	</div>
</div>
</div>
</c:if>
<c:if test="${el:esRegistro()}">
	<div class="acceso_filtro"> 
		<div class="a_f_i" >
			<div class="rm">
				<img src="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/imagenesPortada/imagen.jpg" />
			</div>
		</div>	
		<div  class="a_f_1">
	<div class="rm2">
		<h2><spring:message code="acceso"/></h2>
		<p><spring:message code="introducirDato"/>:<br /></p>
		
		<label for="username" ><spring:message code="acceso_OpenId"/></label>
		<input type="text"  name="username" id="username" class="bot_fil" value="" title="<spring:message code="titleNombre"/>"  /><br />
		<input type="hidden" name="lt" value="${flowExecutionKey}" />
		<input type="hidden" name="_eventId" value="submit" />
		<input type="hidden" name ="openid.return_to" value="<%=request.getParameter("service")%>"/>
		<input type="hidden" name ="return_to" value="<%=request.getParameter("service")%>"/>
		<div class="autenticacion_externa">
			<label class="auten"><spring:message code="accesoUsuarioPass"/>:</label>
			<!-- img src="<rewrite:rewrite url="static/img/icono_aim.gif"/>" alt="Autenticación AOL " title="Autenticación AOL " onclick="cargaProveedor('http://openid.aol.com/screenname',22,10);return false;" onkeypress="cargaProveedor('http://openid.aol.com/screenname',22,10);return false;" /-->
			<img src="<rewrite:rewrite url="static/img/icono_yahoo.gif"/>" alt="Autenticación Yahoo" title="Autenticación Yahoo" onclick="cargaProveedor('https://me.yahoo.com/username',21,8);return false;" onkeypress="cargaProveedor('https://me.yahoo.com/username',21,8);return false;"  />
			<!-- img src="<rewrite:rewrite url="static/img/icono_blogger.gif"/>" alt="Autenticación Blogger" title="Autenticación Blogger"   onclick="cargaProveedor('http://blogname.blogspot.com/',7,8);return false;" onkeypress="cargaProveedor('http://blogname.blogspot.com/',7,8);return false;"  /-->
			<img src="<rewrite:rewrite url="static/img/rs_google.gif"/>" alt="Google" title="Autenticación Google" onclick="cargaUrl('https://www.google.com/accounts/o8/id');return false;" onkeypress="cargaUrl('https://www.google.com/accounts/o8/id');return false;"  />
		</div>
		<input class="boton_125"  type="submit"  value="<spring:message code="acceder"/>" />
		<span class="oculto">-</span><a href="<%=response.encodeRedirectURL("login" + (request.getQueryString() != null && request.getQueryString().length() > 0 ? "?" + request.getQueryString() : ""))%>" ><spring:message code="accesoUsuarioPass"/></a>
	</div>
</div>
</c:if>


<!-- <c:if test="${el:esContrasena()}">
	<span class="oculto">-</span><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos/RecuerdoUsuario/RecuerdoUsuario.do" ><spring:message code="olvidoContrasenia"/></a>
	<span class="oculto">.</span><br class="oculto"/>
</c:if>-->

<!-- Capa Registro  -->

<c:if test="${el:esRegistro()}">
<div class="a_f_1" style="text-align:left">
	<div class="rm2">
		<h2><spring:message code="registrese"/></h2>
		<p><spring:message code="registreseAhora"/>&nbsp;<i><agrega:agregaProperties property="correoCas"/></i></p>
		<br class="limpiar" />
	</div>
</div>
</c:if>
</form>
<!-- /div-->
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
<a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos/InformacionLegal/InformacionLegal.do"  id="info" title="<spring:message code="informacionLegal"/>" ><span><spring:message code="informacionLegal"/></span></a> 
<a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos/PoliticaPrivacidad/PoliticaPrivacidad.do" id="politica" title="<spring:message code="politicaPrivacidad"/>"><span><spring:message code="politicaPrivacidad"/></span></a> <br class="oculto" />
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
<!--  FIN CUERPO GENERAL   -->

</div>
<!--  FIN CAPA MADRE   -->
</body>
</html>
