<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<?xml version="1.0" encoding="iso-8859-1" ?>
<html xmlns="http://www.w3.org/1999/xhtml" lang="es" xml:lang="es">

<head lang="es" dir="ltr">
 <%@ taglib uri="/WEB-INF/tlds/rewriteTag.tld" prefix="rewrite" %> 
 <%@ taglib uri="/WEB-INF/tlds/agregaProperties.tld" prefix="agrega" %>
 <%@ taglib uri="/WEB-INF/tlds/tags-visualizador.tld" prefix="vis" %>
 <%@ include file="/taglib-imports.jspf" %>
 
 <html:xhtml/>
	
		
		 <title>
			<bean:message key="title.Comun"/> - ${fn:escapeXml(visualizadorSession.tituloOde)}
		</title>

		<link rel="shortcut icon" href="<rewrite:rewrite url='static/img/favicon.ico'/>" />
		<link rel="stylesheet" media="screen" href="http://andreslopezjosenge.com/clientes/indra/plataforma/css/red.css" type="text/css"  />
		<link rel="stylesheet" media="screen" href="http://andreslopezjosenge.com/clientes/indra/plataforma/css/globales/red_previsualizador.css" type="text/css"  />
		<script type="text/javascript" src="http://andreslopezjosenge.com/clientes/indra/plataforma/js/plantilla.js"></script>
		<script type="text/javascript" src="http://andreslopezjosenge.com/clientes/indra/plataforma/js/menu.js"></script>
		<script type="text/javascript" src="http://andreslopezjosenge.com/clientes/indra/plataforma/js/curvas.js"></script>
		
		<script type="text/javascript" src="../layout/visualizador.js"></script>
		
		<link href="" id="aepref-keyboardnav.enabled"  />


	
</head>

<body id="pseudo_iframe" >

<div id="capa_madre">


<!-- INICIO PANEL SUPERIOR   -->
<div id="panel_superior">


<!-- INICIO CABECERA   -->

<div id="cabecera">

			<h1><span><agrega:agregaProperties property="ccaa"/></span></h1>
			<h2 id="agrega_logo">&nbsp;<span class="oculto">AGREGA</span></h2>
			<!-- INICIO METANAVEGACION   -->
			<!-- INICIO METANAVEGACION   -->
			<div id="metanavegacion"  class="metanavegacion">
			
			<ol id="container">
					<li class="oculto">
						<html:link href="#contenido_central" title="Contenido Principal">
						   <strong><bean:message key="cabecera.Contenido"/></strong>
						</html:link>
					</li>
					<li>
						<bean:define id="cerrar"><bean:message key="cabecera.Cerrar"/></bean:define>
						<html:link href="#" title="${cerrar}" styleId="li_inicial" onclick="window.parent.close()"><bean:message key="metanavegacion.Cerrar"/></html:link>
					</li>
					<li>
						<bean:define id="ayuda"><bean:message key="cabecera.Ayuda"/></bean:define>
						<vis:link attrProperty="HELP_URL" title="${ayuda }" id="ayuda" target="blank" i18nMessage="cabecera.Ayuda"></vis:link>
					</li>
					

			</ol>
			</div>
			
			<br class="oculto" />
			<h2 style="margin-bottom:0;">${fn:escapeXml(visualizadorSession.tituloOde)}</h2>
			
</div>
</div>



<div id="contenido_central_largo">


	<div id="contenido_externo"  class="plantilla_contenido">

<div id="panel_contenidos"  style="width:100%;">

<!-- LATERAL DESPLEGABLE  -->
<!-- LATERAL DESPLEGABLE  -->
<!-- FIN LATERAL DESPLEGABLE  -->
<!-- FIN LATERAL DESPLEGABLE  -->




		 
<!-- CONTENIDO -->
<div id="contenido_it" class="plantilla_contenido" style="position:relative;" >
	<br/><br/>
	<h2><bean:message key="comentarios.ode.advertencia.eliminar.titulo.cabecera"/></h2>
	<div id="formu">
	<br/>
						<form method="post" action="<html:rewrite action="/Comentarios/AdvertenciaSubmit"/>" >	
							<!--  INICIO GLOBO GRIS   -->
							<!--  INICIO GLOBO GRIS   -->
	<div class="globo_blanco">
	<div class="globo_blanco_01">
		<div class="globo_blanco_02">
			<div style="padding: 10px 0pt ! important;" class="globo_blanco_03">

										<!--  INICIO CAJA DE FORMULARIO   -->
										<div id="formulario" class="ali_c">
							
										<br />
										<p><bean:message key="comentarios.ode.advertencia.eliminar.texto"/></p>
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
							<fieldset class="tipo">
								<input class="boton_125_de_2_izq"  type="submit" name="action" value="<bean:message key="comentarios.cancelar"/>" />
								<input class="boton_125_de_2"  type="submit" name="action" value="<bean:message key="comentarios.aceptar"/>" />
							</fieldset>
							<!-- Fin Botones  -->
							<!-- Fin Botones  -->
						</form>

					<div class="linea_separadora">
					<br class="oculto"/>
					</div>
	</div>
</div>

</div>


</div>

</div>
</div>
	

</body>
</html>