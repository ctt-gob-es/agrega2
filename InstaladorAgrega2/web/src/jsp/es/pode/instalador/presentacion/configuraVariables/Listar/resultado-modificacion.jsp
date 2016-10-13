<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>

<?xml version="1.0" encoding="iso-8859-1" ?> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="es" xml:lang="es">


<head lang="es" dir="ltr">
	<meta name="description" content="Repositorio Educativo de la Comunidad Educativa Espa�ola" />
	<meta name="keywords" content="Agrega,Educaci�n,Repositorio,Contenidos,SCORM2004,LOM" />
	<title>
		Agrega 2 - Instalaci&oacute;n
	</title>
	<link rel="shortcut icon" href="/static/img/favicon.ico" />
	<link rel="stylesheet" media="screen" href="/InstaladorAgrega2-1.0/layout/red.css" type="text/css" />
</head>

<body>

<!--  CAPA QUE CONTIENE TODAS LAS DEMAS  -->
<!--  INICIO CAPA MADRE   -->
<div id="capa_madre">
<!-- A�ADIR MOTOR DE BUSQUEDA -->
		
<!--  INICIO CUERPO GENERAL   -->
<div id="cuerpo">
<div class="minwidth">
<div class="contenido_general">
	<!-- Aqui va la cabecera -->
	<div id="cabecera">
		<h1>
			<span>
				Red.es
			</span>
		</h1>
		<hr />
	</div>
	<hr />
	<!-- FIN BUSCADOR  -->
	<br class="oculto" />
	<a href="/visualizadorcontenidos/Portada/Portada.do" title="P&aacute;gina de Inicio" id="logo"><span>P&aacute;gina de Inicio</span></a>
	<br class="oculto" />
</div>

<!-- Inicio cuerpo central de pagina con Menu Principal, capa Lateral y capa Central  -->
<div class="tronco_de_contenido">
<!-- Menu de cabecera -->


<!-- Inicio MENU PRINCIPAL -->
<!-- Inicio MENU PRINCIPAL -->
<div id="madre_menu_horizontal">
	<div id="cabecera_menu">
		<div id="menu_principal0">
			<div id="menu_principal">
				<div id="menu_principal2">
				<ul>
					<li id="portada">
						<a href="/InstaladorAgrega2-1.0/ListarVariablesCU/ListarVariablesCU.do"  >
						<span><span>INICIO</span></span>
						</a>
					</li>
					<li id="arbol">
						<div>
						<span><span>CONSULTAR CONFIGURACI�N</span></span>
						</div>
					</li>
				</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Fin MENU PRINCIPAL -->
<!-- Fin MENU PRINCIPAL -->



<!-- Inicio Contenido ESPEC�FICO  -->
<div id="contenido_central">

<!--  INICIO CAPA MADRE   -->
<div id="capa_madre">

<!-- Inicio plantilla contenido  -->
<div class="plantilla_contenido">
<!-- ## Para pintar los mensajes de error ## -->
	

    
<h2>Configuraci�n</h2>

<form method="post" action="/InstaladorAgrega2-1.0/ListarVariablesCU/ResultadoModificacionMostrarCambios.do" >


<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			
				<!--  INICIO CAJA DE FORMULARIO   -->
				<div class="formu">
					<h3>Resultado de la operaci�n</h3>
					
					<c:if test="${form.operacionSatisfactoria == 'true'}">
						La instalaci�n se realiz� satisfactoriamente.
					</c:if>	
					<c:if test="${form.operacionSatisfactoria == 'false'}">
						Hubo algun problema durante la instalaci�n.
					</c:if>	
					<input type="hidden" name="tipoJboss" value="${form.tipoJboss}">
					<input type="hidden" name="numJbossDisponibles" value="${form.numJbossDisponibles}">
				</div>
				<!--  FIN CAJA DE FORMULARIO   -->

			</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->
<br>




<!-- Inicio Botones  -->
<!-- Inicio Botones  -->
<fieldset class="tipo ft_centrada">
<input class="boton_125"  type="submit"  value="Continuar" />
</fieldset>
<!-- Fin Botones  -->
<br>
</form>
</div>
<!-- Fin plantilla contenido  -->
<!-- Fin plantilla contenido  -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->


</div>


<!--  FIN CAPA MADRE   -->
</div>
</div>
<!-- Fin cuerpo central de pagina con Menu Principal, capa Lateral y capa Central  -->



<!-- Pie de pagina -->
</div>
</div>
</div>
<!--  FIN CUERPO GENERAL   -->

</div>
<!--  FIN CAPA MADRE   -->

</body>
</html>
