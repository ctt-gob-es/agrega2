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
	<meta name="description" content="Repositorio Educativo de la Comunidad Educativa Española" />
	<meta name="keywords" content="Agrega,Educación,Repositorio,Contenidos,SCORM2004,LOM" />
	<title>
  		<bean:message key="tittle.comun" />
	</title>
	<link rel="shortcut icon" href="/static/img/favicon.ico" />
	<link rel="stylesheet" media="screen" href="/InstaladorAgrega2-1.0/layout/red.css" type="text/css" />
</head>

<body>

<!--  CAPA QUE CONTIENE TODAS LAS DEMAS  -->
<!--  INICIO CAPA MADRE   -->
<div id="capa_madre" style="font-size: 100%;
    						max-width: 850px;">
<!-- AÑADIR MOTOR DE BUSQUEDA -->
		
<!--  INICIO CUERPO GENERAL   -->
<div id="cuerpo" style="
						display: block !important;
					    margin: 0 auto;
					    min-height: 10em;
					    padding: 0 !important;
					    text-align: left;
					    width: 800px !important;">
<div class="minwidth">
<div class="contenido_general">
	<!-- Aqui va la cabecera -->
	<div id="cabecera" style="    
								background: none repeat scroll 0 0 #fff;
							    height: 5.5em;
							    margin: 0 auto;
							    position: relative;
							    width: 960px !important;">
		<h1>
			<span>
				Red.es
			</span>
		</h1>
		<hr />
	</div>
	<hr />
	<br class="oculto" />
	<a href="/visualizadorcontenidos2/Portada/Portada.do" 
		title="P&aacute;gina de Inicio" 
		id="logo"
		style="  
			background-image: url('../img/logo_agrega_red.gif');
			background-repeat: no-repeat;
			background-attachment: scroll 0% 0% transparent;
			display: block;
		    height: 50px;
		    left: 26px;
		    position: absolute;
		    text-decoration: none;
		    top: 20px;
		    width: 131px;">
    	<span>P&aacute;gina de Inicio</span>
    </a>
	<br class="oculto" />
</div>

<!-- Inicio cuerpo central de pagina con Menu Principal, capa Lateral y capa Central  -->
<div class="tronco_de_contenido">
















<!-- Inicio MENU PRINCIPAL -->
<!-- Inicio MENU PRINCIPAL -->
<div id="madre_menu_horizontal">

<div id="cabecera_menu">
<div id="menu_principal0">
<div id="menu_principal">
<div id="menu_principal2">
	<ul>
		<li>
			<div>
			<span><span>Inicio</span></span>
			</div>
		</li>
		<li>
			<a href="/InstaladorAgrega2-1.0/ConfiguracionPlataformaCU/ConfiguracionPlataformaCU.do"  >
			<span><span><bean:message key="modificar.config.formulario.cabecera"/></span></span>
			</a>
		</li>
		<li>
			<a href="/InstaladorAgrega2-1.0/ActualizarPlataformaCU/ActualizarPlataformaCU.do"  >
			<span><span>Actualización</span></span>
			</a>
		</li>
	</ul>
</div>
</div>
</div>
</div>
</div>
<!-- Fin MENU PRINCIPAL -->
<!-- Fin MENU PRINCIPAL -->











<!-- FIN LATERAL IZQUIERDO -->


<!-- Inicio Contenido ESPECÍFICO  -->
<div id="contenido_central" style="
									margin-left: 25px;
								    margin-right: 1px;
								    padding: 0;
								    position: relative;">
















<!--  INICIO CAPA MADRE   -->
<div id="capa_madre">


<!-- Inicio plantilla contenido  -->
<div class="plantilla_contenido" style="
										margin-right: 30px;
									    padding-bottom: 6px;
									    padding-right: 0;">
									    
<!-- ## Para pintar los mensajes de error ## -->
	<jsp:include page="/layout/messages.jsp" flush="true" />



<div class="interno_ficha">
<div class="plantilla_contenido_pestanias" style="
												width:716px !important;     
												border-bottom-left-radius: 5px;
											    border-bottom-right-radius: 5px;
											    padding: 16px 21px 10px 7px !important;">
<fieldset>
	
<form method="post" action="<html:rewrite action="/InicioCU/SelectorAccionSubmit.do"/>">	

	<!--  INICIO CAJA DE FORMULARIO   -->
	<div id="formulario" >
	 	<div class="fila_de_tabla">
			<div class="text" style="text-align:center">
				<br>
				<bean:message key="inicio.selectorAccion" />
				</br>  						
				</br>
			</div>
			<br class="oculto" />
		</div>
		<br />
	</div>
	<!--  FIN CAJA DE FORMULARIO   -->
	
	<!-- Inicio Botones  -->
	<!-- Inicio Botones  -->

	<fieldset class="tipo">
		<input class="boton_125_de_2_izq" name="action" type="submit"  value="<bean:message key="inicio.actualizar" />" />
		<input class="boton_125_de_2"  name="action" type="submit"  value="<bean:message key="inicio.configurar"/>"  />
	</fieldset>
	
</form>

</fieldset>
</div>
</div>


</div>
<!-- Fin plantilla contenido  -->
<!-- Fin plantilla contenido  -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->


</div>
</div>
</div>
</div>
</div>
</div>

</body>
</html>
