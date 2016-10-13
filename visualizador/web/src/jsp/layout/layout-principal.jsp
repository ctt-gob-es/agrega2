<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<?xml version="1.0" encoding="iso-8859-1" ?>
<html xmlns="http://www.w3.org/1999/xhtml" lang="es" xml:lang="es">

<head lang="es" dir="ltr">
 <%@ include file="/taglib-imports.jspf" %>
 <%@ taglib uri="/WEB-INF/tlds/rewriteTag.tld" prefix="rewrite" %> 
 <%@ taglib uri="/WEB-INF/tlds/agregaProperties.tld" prefix="agrega" %>
 <%@ taglib uri="/WEB-INF/tlds/tags-visualizador.tld" prefix="vis" %>
 <%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
 <%@ taglib uri="/WEB-INF/tlds/googleAnalytic.tld" prefix="analytic" %>
 <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
 <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
 
 <html:xhtml/>

		 <title>
			<tiles:insert attribute="title" flush="true"/>
		</title>

		<link rel="shortcut icon" href="<rewrite:rewrite url='static/img/favicon.ico'/>" />

<script type="text/javascript" src="/static/js/addthis_widget.js"></script>
<script type="text/javascript">
	var addthis_config = {
           services_compact: 'facebook, twitter, google_plusone_share, pinterest_share, linkedin, more',
           services_exclude: 'print, email'
}
</script>

 		
		<link rel="stylesheet" media="screen" href="<rewrite:rewrite url='static/css/red.css'/>" type="text/css"  />
		<link rel="stylesheet" media="screen" href="<rewrite:rewrite url='static/css/globales/red_previsualizador.css'/>" type="text/css"  />
		<script type="text/javascript" src="<rewrite:rewrite url='static/js/plantilla.js'/>"></script>
		<script type="text/javascript" src="<rewrite:rewrite url='static/js/curvas.js'/>"></script>
		<script type="text/javascript" src="<rewrite:rewrite url='static/js/menu.js'/>"></script>
		
		<script type="text/javascript" src="<rewrite:rewrite url='static/js/jquery-1.3.2.js'/>"></script>
		<script type="text/javascript" src="<rewrite:rewrite url='static/js/ui.core.js'/>"></script>
		<script type="text/javascript" src="<rewrite:rewrite url='static/js/ui.tabs.js'/>"></script>
		<script type="text/javascript" src="<rewrite:rewrite url='static/js/jquery.form.js'/>"></script>
		<script type="text/javascript" src="<rewrite:rewrite url='static/js/jquery.history.js'/>"></script>
		<script type="text/javascript" src="<rewrite:rewrite url='static/js/jquery.validate.js'/>"></script>
		<script type="text/javascript" src="<rewrite:rewrite url='static/js/jquery.clipboard.min.js'/>"></script>
		<script type="text/javascript" src="<rewrite:rewrite url='static/js/jquery.autocomplete.js'/>"></script>

		<script type="text/javascript" src="<rewrite:rewrite url='visualizador-1/layout/visualizador.js'/>"></script>
		<script type="text/javascript" src="<rewrite:rewrite url='visualizador-1/layout/jquery.agrega.contenido.js'/>"></script>
		<script type="text/javascript" src="<rewrite:rewrite url='visualizador-1/layout/jquery.agrega.embed.js'/>"></script>
		<script type="text/javascript" src="<rewrite:rewrite url='visualizador-1/layout/jquery.agrega.formularios.js'/>"></script>
		
		<link href="" id="aepref-keyboardnav.enabled"  />

<script type="text/javascript">
//<![CDATA[

	$(document).ready(function() {
		$("#tabs").tabs({
			   select: function(event, ui)
			   {
			 		$('#Limpiar', ui.panel).click();
			 		return true;
			   }
		});
		
		$("#tags").autocomplete("<html:rewrite action="/SugerirTags/SugerirTags"/>", {
			max: 10,
			highlight: false,
			multiple: true,
			multipleSeparator: " ",
			scroll: true,
			autoFill:true,
			minChars: 1
		});

	});


	
	
	function redim()
	{
		$("#contenido_central_largo").trigger('redimensionar');
	}

	
    /*
     * SCORM API
     */
     function GenericAPIAdaptor(){
     	this.LMSInitialize = LMSInitializeMethod;
     	this.LMSGetValue = LMSGetValueMethod;
     	this.LMSSetValue = LMSSetValueMethod;
     	this.LMSCommit = LMSCommitMethod;
     	this.LMSFinish = LMSFinishMethod;
     	this.LMSGetLastError = LMSGetLastErrorMethod;
     	this.LMSGetErrorString = LMSGetErrorStringMethod;
     	this.LMSGetDiagnostic = LMSGetDiagnosticMethod;
     }
     
     function GenericAPIAdaptor_2004(){
     	this.Initialize = LMSInitializeMethod;
     	this.Terminate = LMSFinishMethod;	
     	this.GetValue = LMSGetValueMethod;
     	this.SetValue = LMSSetValueMethod;
     	this.Commit = LMSCommitMethod;	
     	this.GetLastError = LMSGetLastErrorMethod;
     	this.GetErrorString = LMSGetErrorStringMethod;
     	this.GetDiagnostic = LMSGetDiagnosticMethod;
     }
     
     /*
     * LMSInitialize.
     */
     function LMSInitializeMethod(parameter){return "true";}
     /*
     * LMSFinish.
     */
     function LMSFinishMethod(parameter){return "true";}
     /*
     * LMSCommit.
     */
     function LMSCommitMethod(parameter){return "true";}
     /*
     * LMSGetValue.
     */
     function LMSGetValueMethod(element){return "";}
     /*
     * LMSSetValue.
     */
     function LMSSetValueMethod(element, value){return "true";}
     /*
     * LMSGetLastErrorString
     */
     function LMSGetErrorStringMethod(errorCode){return "No error";}
     /*
     * LMSGetLastError
     */
     function LMSGetLastErrorMethod(){return "0";}
     /*
     * LMSGetDiagnostic
     */
     function LMSGetDiagnosticMethod(errorCode){return "No error. No errors were encountered. Successful API call.";}
     
     var API = new GenericAPIAdaptor;
     var API_1484_11 = new GenericAPIAdaptor_2004;
     
//]]>
</script>
	
</head>
<body id="pseudo_iframe" onresize="redim();">
<div id="capa_madre">

<!-- panel superior!!  -->
	<div id="panel_superior">
			
			
			<div id="cabecera"  >
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
						<bean:define id="cerrarBoton"><html:rewrite action="/Cerrar/Cerrar"/></bean:define>
						<bean:define id="cerrar"><bean:message key="cabecera.Cerrar"/></bean:define>
						<html:link href="${cerrarBoton}?identificador=${form.identificador}" title="${cerrar}" styleId="li_inicial" ><bean:message key="metanavegacion.Cerrar"/></html:link>
					</li>
					<li>
						<bean:define id="ayuda"><bean:message key="cabecera.Ayuda"/></bean:define>
						<vis:link attrProperty="HELP_URL" title="${ayuda }" id="ayuda" target="blank" i18nMessage="cabecera.Ayuda"></vis:link>
					</li>
					
		<c:if test="${visualizadorSession.verFavorito}">
			<c:if test="${visualizadorSession.favorito}">
					<li id="cab_fav">
						<span id="msg_destacados">
							<span class="cab_fav"><bean:message key="menu.destacado"/></span>
						</span>
					</li>
			</c:if>
			<c:if test="${!visualizadorSession.favorito}">
					<li id="cab_fav">
						<span id="msg_destacados">
							<a href="<html:rewrite action="/Favoritos/Favoritos"/>?retorno=${form.retorno}&identificador=${form.identificador}" class="cab_fav" title="<bean:message key="menu.destacar.title"/>"><bean:message key="menu.destacar"/></a>
						</span>
					</li>
			</c:if>
		</c:if>

		<c:if test="${visualizadorSession.verItinerarios}">
					<li id="cab_itinerario">
						<a title="<bean:message key="menu.itinerarios.title"/>" href="<html:rewrite action="/Grupos/Grupos"/>?identificador=${form.identificador}">
							<span><bean:message key="menu.itinerarios"/></span>
						</a>
					</li>
		</c:if>
		
		<c:if test="${visualizadorSession.verComentarios}">
					<li id="cab_comentarios"><a href="<html:rewrite action="/Comentarios/Comentarios"/>?identificador=${form.identificador}" title="<bean:message key="menu.comentarios.title"/>" ><span><bean:message key="menu.comentarios"/></span></a></li>
		</c:if>

		<c:if test="${visualizadorSession.verExportar}">
		<bean:define id="exportarBoton"><html:rewrite action="/Exportar/Exportar"/></bean:define>
			<bean:define id="menuExportarTitle"><bean:message key="menu.exportar.title"/></bean:define>
					<li id="cab_exportar"><html:link href="${exportarBoton}?identificador=${form.identificador}" title="${menuExportarTitle}"><span><bean:message key="menu.exportar"/></span></html:link></li>
		</c:if>

		<c:if test="${visualizadorSession.verValorar}">
			<bean:define id="action"><html:rewrite action="/Valorar/Valorar"/></bean:define>
			<vis:valoracion 
				idioma="${form.idiomaCat}"
				valorado="${form.estaValorado}"
				valoracion="${form.valoracion}"
				action="${action}"
				retorno="${form.retorno}"
				identificador="${form.identificador}"
				/>
		</c:if>

		<c:if test="${visualizadorSession.verShare}">
			<bean:define id="shareBoton"><html:rewrite action="/Share/Share"/></bean:define>
			<bean:define id="menuShareTitle"><bean:message key="menu.share.title"/></bean:define>
			<!-- 
					<li id="cab_addthis"><html:link href="${shareBoton}?identificador=${form.identificador}" title="${menuShareTitle}"><span><bean:message key="menu.share"/></span></html:link></li>
			-->
					<li id="cab_addthis"><a class="addthis_button"><span>AddThis</span></a></li>
		</c:if>
		<c:if test="${visualizadorSession.verEstadisticas}">
					<li id="cab_estadisticas"><a href="<html:rewrite action="/Estadisticas/Estadisticas"/>" title="<bean:message key="menu.estadisticas.title"/>"><span><bean:message key="menu.estadisticas"/></span></a></li>
		</c:if>
					

			</ol>
			</div>
			
			<br class="oculto" />
			<h2 style="margin-bottom:0;">${fn:escapeXml(form.tituloOde) }</h2>
			
			</div>


					
	</div>
<!-- panel superior!!  -->	




<!-- menu y tabs -->

<div id="contenido_central_largo">
<analytic:googleAnalytic />

	<div id="contenido_externo" class="plantilla_contenido">

			<div id="panel_contenidos" >
					<!--  panel menu!! -->
					<div id="panel_menu" >
<!-- MENU PESTAÑAS  -->
<!-- MENU PESTAÑAS  -->

<div id="tabs">
<ul>
	<li><a href="#tabs-1" title="<bean:message key="menu.arbol.title"/>"><bean:message key="menu.arbol"/></a></li>
<c:if test="${visualizadorSession.verTagging}">
	<li><a href="#tabs-2" title="<bean:message key="menu.etiquetas.title"/>"><bean:message key="menu.etiquetas"/></a></li>
</c:if>
<c:if test="${visualizadorSession.verEmbebido}">
	<li><a href="#tabs-3" title="<bean:message key="menu.embebido.title"/>"><bean:message key="menu.embebido"/></a></li>
</c:if>
<c:if test="${visualizadorSession.verRecomendar}">
	<li><a href="#tabs-4" title="<bean:message key="menu.enviar.title"/>"><bean:message key="menu.enviar"/></a></li>
</c:if>
<c:if test="${visualizadorSession.verContenidoInapropiado}">
	<li><a href="#tabs-5" title="<bean:message key="menu.inapropiado.title"/>"><bean:message key="menu.inapropiado"/></a></li>
</c:if>
</ul>

<!-- xxxxx 01 Arbol xxxx -->
<!-- xxxxx 01 Arbol xxxx -->
<div id="tabs-1" >

<div class="contenido_tab">
			<div id="menu_frame" >
					<c:if test="${form.secuencia }">
							<bean:define id="action"><html:rewrite action="/VisualizadorCS/VisualizarDatosNavSecuenciaNodo"/></bean:define>
							 <vis:visualizarConSec 	
							 	organizaciones="${form.datosSalidaAsArray}" 
							 	action="${action}"
								localizacion="${form.localizacion}" 
								identificador="${form.identificador}" 
								idSeleccionado="${form.idSeleccionado}"
								rutaSeleccionado="${form.rutaSeleccionado}"/>
					</c:if>
					<c:if test="${!form.secuencia }">
							<bean:define id="action"><html:rewrite action="/VisualizadorSS/VisualizarDatosSeleccionar"/></bean:define>
							 <vis:visualizarSinSec 	
							 	organizaciones="${form.datosSalidaAsArray}" 
								action="${action}"
								localizacion="${form.localizacion}"
								idSeleccionado="${form.idSeleccionado}"
								rutaSeleccionado="${form.rutaSeleccionado}" />
					</c:if>	

			</div>
</div>
</div>						
<!-- xxxxx FIN Arbol xxxx -->		
<!-- xxxxx FIN Arbol xxxx -->
				
<c:if test="${visualizadorSession.verTagging}">
<!-- xxxxx 02 TAGS xxxx -->
<!-- xxxxx 02 TAGS xxxx -->		
	<div id="tabs-2">
		<form  action="<html:rewrite action="/Tagging/Tagging"/>" method="post" id="formTagging" name="formTagging">
		<html:hidden styleId="taggTitulo" property="titulo" value="${fn:escapeXml(form.tituloOde)}"/>
		<html:hidden styleId="taggIdOde" property="idOde" value="${fn:escapeXml(form.identificador)}" />
		<html:hidden styleId="taggIdUsuario" property="idUsuario" value="${fn:escapeXml(visualizadorSession.usuario)}" />
		<html:hidden styleId="taggIdiomaCat" property="idiomaCat" value="${fn:escapeXml(form.idiomaCat)}" />
			<div id="panel_error" name="panel_error" style="display:none" >
				<div class='globo_gris'>
					<div class='globo_gris_01'>
						<div class='globo_gris_02'>
							<div class='globo_gris_03'>
								<div id="formulario">
									<div id="ContenedorErrores" name="ContenedorErrores"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="panel_exito" name="panel_exito" style="display:none" >
				<div class='globo_gris'>
					<div class='globo_gris_01'>
						<div class='globo_gris_02'>
							<div class='globo_gris_03'>
								<div id="formulario">
									<p><bean:message key="tagging.popup.mensaje.exito"/></p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="contenido_tab" id="contenedor_tab_2">
				<h3 class="oculto"><bean:message key="tagging.titulo"/></h3>
				<label for="tags"><bean:message key="tagging.popup.mensaje"/></label>

				<textarea id="tags" name="tags" cols="4" rows="6" class="in_box_" title="<bean:message key="tagging.popup.titulo"/>"></textarea>
				<br  class="oculto" />

				<fieldset class="tipo">
					<input id="TaggingAceptar" name="TaggingAceptar" class="boton_90" style="float:right;" type="button" value="<bean:message key="tagging.popup.aceptar"/>"/>
					<input id="Limpiar" name="Limpiar" class="boton_90" type="button" value="Limpiar"/>
				</fieldset>
			</div>

		</form>
	</div>
	
	
	<bean:define id="requerido"><bean:message key="tagging.popup.error.comentario.vacio"/></bean:define>
	<bean:define id="max_length"><bean:message key="tagging.popup.error.longitud.tag"/></bean:define>
	<bean:define id="error"><bean:message key="tagging.popup.error.servidor.mensaje"/></bean:define>
	<script type="text/javascript">
	//<![CDATA[
	
		$("#formTagging").tabform({
				validationEnabled : true,
				formPluginEnabled: true,
				aceptar: "#TaggingAceptar",
				limpiar: "#Limpiar"
			},
			{
				separadorTags: ' ',
				maxLengthTags: '50',
				rules: { tags: 'required max_length_tags'},
				messages: {
					tags:	{
							required: '${fn:escapeXml(requerido)}',
							max_length_tags: '${fn:escapeXml(max_length)}'
							}
				},
				onfocusout: false,
				onkeyup: false
			},
			{
				contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
				mensajeError: '${fn:escapeXml(error)}'
			}
		);
		
	//]]>
	</script>


<!-- xxxxx 02 Fin TAGS xxxx -->
<!-- xxxxx 02 Fin TAGS xxxx -->	
</c:if>						

<c:if test="${visualizadorSession.verEmbebido}">
<!-- xxxxx 03 Embed xxxx -->
<!-- xxxxx 03 Embed xxxx -->	
	<div id="tabs-3">
		<form action="" id="formulario_embed" name="formulario_embed">
			<div class="contenido_tab" id="contenedor_tab_3">
				<h3 class="oculto"><bean:message key="embebido.titulo"/></h3>
				<label for="embed_caja"><bean:message key="embebido.popup.mensaje"/></label>
				<textarea cols="4" rows="6" name="caja_embed" id="caja_embed" readonly="readonly" class="in_box_" title="<bean:message key="embebido.popup.tootltip"/>">${fn:escapeXml(visualizadorSession.contenidoEmbebido)}</textarea>
				<fieldset class="tipo ft_centrada">
					<input class="boton_90" type="button" id="boton_embed" name="boton_embed" value="<bean:message key="embebido.popup.copiar"/>" />
				</fieldset>
			</div> 
		</form>
	</div>

<script type="text/javascript">
//<![CDATA[
	$("#formulario_embed").embebido({
			copiar: "#boton_embed",
			cajaTextoACopiar: "#caja_embed"
		}
	);

//]]>
</script>
	
	
<!-- xxxxx 03 Fin Embed xxxx -->
<!-- xxxxx 03 Fin Embed xxxx -->	
</c:if>


<c:if test="${visualizadorSession.verRecomendar}">
<!-- xxxxx 04 ENVIAR xxxx -->
<!-- xxxxx 04 ENVIAR xxxx -->
	<div id="tabs-4">
		<form action="<html:rewrite action="/Recomendar/Recomendar"/>" method="post"  id="formEnviarAmigo" name="formEnviarAmigo">
			<div id="panel_error" name="panel_error" style="display:none" >
				<div class='globo_gris'>
					<div class='globo_gris_01'>
						<div class='globo_gris_02'>
							<div class='globo_gris_03'>
								<div id="formulario">
									<div id="ContenedorErrores" name="ContenedorErrores"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="panel_exito" name="panel_exito" style="display:none" >
				<div class='globo_gris'>
					<div class='globo_gris_01'>
						<div class='globo_gris_02'>
							<div class='globo_gris_03'>
								<div id="formulario">
									<p><bean:message key="contenidoInapropiado.popup.mensaje.exito"/></p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="contenido_tab" id="contenedor_tab_4">
				<h3 class="oculto"><bean:message key="enviar.amigo.titulo"/></h3>
				<p align="left"><bean:message key="enviar.amigo.titulo"/></p>
				
				<div class="cpa_sup" ><label for="nombreDestinatario"><bean:message key="enviar.amigo.destinatario.nombre"/></label></div>
				<input type="text" value="" name="nombreDestinatario" class="in_box_"  onblur="this.style.backgroundColor='#e1e1e1'" id="nombreDestinatario"  title="<bean:message key="enviar.amigo.destinatario.nombre.titulo"/>" /><br />
				
				<div class="cpa_sup"><label  for="direccionDestinatario"><bean:message key="enviar.amigo.destinatario.email"/></label></div>
				<input type="text" value="" class="in_box_" name="direccionDestinatario" onblur="this.style.backgroundColor='#e1e1e1'" id="direccionDestinatario"  title="<bean:message key="enviar.amigo.destinatario.email.titulo"/>" /><br />
				
				<div class="cpa_sup"><label  for="nombreRemitente"><bean:message key="enviar.amigo.remitente.nombre"/></label></div>
				<input type="text" value="" class="in_box_" name="nombreRemitente" onblur="this.style.backgroundColor='#e1e1e1'" id="nombreRemitente"  title="<bean:message key="enviar.amigo.remitente.nombre.titulo"/>" /><br />
				
				<div class="cpa_sup"><label  for="direccionRemitente"><bean:message key="enviar.amigo.remitente.email"/></label></div>
				<input type="text" value="" class="in_box_" name="direccionRemitente" onblur="this.style.backgroundColor='#e1e1e1'" id="direccionRemitente"  title="<bean:message key="enviar.amigo.remitente.email.titulo"/>" /><br />
				
				<div class="cpa_sup"><label  for="mensaje"><bean:message key="enviar.amigo.mensaje"/></label></div>
				<textarea id="mensaje" name="mensaje" class="in_box_ enviar_am" cols="4" rows="6"></textarea><br />
				<br />
				
				
				<div class="cpa_sup"><label for="captchaInput" >*Escribe el texto de la imagen</label></div>
				<input type="text" value="" class="in_box_" id="captchaInput" name="captchaInput" style="width: 200px !important;"/>
				<br />

				<div class="cpa_sup"><label for="captchaImage" ></label></div>
				<img name="Refrescar" id="Refrescar" src="/static/img/reload.jpg" title="Cambiar imagen" alt="Cambiar imagen" style="height: 23px; width: 23px;"/>&nbsp;
				<img id="captchaImage" src="jcaptcha.jcaptcha" title="captcha" alt="captcha" class="in_box_" style="border: 1px solid #585858; height: 70px; width: 200px;"/><br />
				
				
				<input type="hidden" name="identificador" value="${form.identificador}" />
				<br />
				
				<fieldset class="tipo">
					<input name="Limpiar" id="Limpiar" class="boton_90" type="button" value="Limpiar"/>
					<input name="EnviarAmigoAceptar" id="EnviarAmigoAceptar" class="boton_90" type="button" style="float:right;" value="<bean:message key="enviar.amigo.enviar"/>"/>
				</fieldset>

			</div> 

			

		</form>

		<!-- Normas de uso -->
		<div class="contenido_tab">
			<div class="cpa_sup"><em ><bean:message key="enviar.amigo.normas.de.uso.titulo"/></em></div><br />
			<div class="cpa_sup"><p><bean:message key="enviar.amigo.normas.de.uso.cuerpo"/></p></div>
			<div class="cpa_sup"><p><bean:message key="enviar.amigo.normas.de.uso.cuerpo2"/></p></div>
		</div>

	</div>
	
	<bean:define id="requerido1"><bean:message key="enviar.amigo.error.mail.destinatario.vacio"/></bean:define>
	<bean:define id="lista_mails1"><bean:message key="enviar.amigo.error.mail.destinatario.novalido"/></bean:define>
	<bean:define id="lista_mails_nombres"><bean:message key="enviar.amigo.error.mail.destinatario.no.corresponde.nombres"/></bean:define>
	<bean:define id="nombredestinatario"><bean:message key="enviar.amigo.error.nombre.destinatario.vacio"/></bean:define>
	<bean:define id="requerido2"><bean:message key="enviar.amigo.error.mail.remitente.vacio"/></bean:define>
	<bean:define id="lista_mails2"><bean:message key="enviar.amigo.error.mail.remitente.novalido"/></bean:define>
	<bean:define id="nombreremitente"><bean:message key="enviar.amigo.error.nombre.remitente.vacio"/></bean:define>
	<bean:define id="mensaje"><bean:message key="enviar.amigo.error.mensaje"/></bean:define>
	<bean:define id="mailsnombres"><bean:message key="enviar.amigo.error.emails.nombres"/></bean:define>
	<bean:define id="error"><bean:message key="enviar.amigo.error.servidor"/></bean:define>
	<bean:define id="captchaRequerido">Es obligatorio introducir el texto de la imagen</bean:define>
	
	<script type="text/javascript">
	//<![CDATA[
		$("#formEnviarAmigo").tabform({ 
				validationEnabled : true,
				formPluginEnabled: true,
				aceptar: "#EnviarAmigoAceptar",
				limpiar: "#Limpiar",
				recargar:'#Refrescar'
			},
			{
				separadorMails: ';',
				idEmails:'#direccionDestinatario',
				idNombres:'#nombreDestinatario',
				rules: {
					direccionDestinatario:	'required lista_emails_nombres listaEmails',
					nombreDestinatario: 	'required',
					direccionRemitente: 	'required listaEmails',
					nombreRemitente:		'required',
					mensaje:'required',
					captchaInput: 'required'
					},
				messages: {
					direccionDestinatario:{
								required: '${requerido1}',
								listaEmails: '${lista_mails1}',
								lista_emails_nombres: '${lista_mails_nombres}'
								},
					nombreDestinatario: 	'${nombredestinatario}',
					direccionRemitente: {
								required: '${requerido2}',
								listaEmails: '${lista_mails2}'
								},
					nombreRemitente:'${nombreremitente}',
					mensaje:'${mensaje}',
					captchaInput: '${captchaRequerido}',
					emails_nombres: '${mailsnombres}'
					},
				onfocusout: false,
				onkeyup: false
			},
			{
				contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
				mensajeError: '${error}'
			}
		);
	
	//]]>
	</script>	
	
<!-- xxxxx 04 Fin ENVIAR xxxx -->
<!-- xxxxx 04 Fin ENVIAR xxxx -->	
</c:if>


<c:if test="${visualizadorSession.verContenidoInapropiado}">
<!-- xxxxx 05 Inapropiado xxxx -->
<!-- xxxxx 05 Inapropiado xxxx -->
	<div id="tabs-5">
		<form action="<html:rewrite action='/ContenidoInapropiado/ContenidoInapropiado'/>" method="post" id="formContenidoInapropiado" name="formContenidoInapropiado">
			<div id="panel_error" name="panel_error" style="display:none" >
				<div class='globo_gris'>
					<div class='globo_gris_01'>
						<div class='globo_gris_02'>
							<div class='globo_gris_03'>
								<div id="formulario">
									<div id="ContenedorErrores" name="ContenedorErrores">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="panel_exito" name="panel_exito" style="display:none" >
				<div class='globo_gris'>
					<div class='globo_gris_01'>
						<div class='globo_gris_02'>
							<div class='globo_gris_03'>
								<div id="formulario">
									<p ><bean:message key="contenidoInapropiado.popup.mensaje.exito"/></p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="contenido_tab" id="contenedor_tab_5">
				<h3 class="oculto"><bean:message key="contenidoInapropiado.popup.titulo"/></h3>
				
				<label for="comentario" ><bean:message key="contenidoInapropiado.popup.titulo"/></label>
				<textarea cols="4" rows="6" name="comentario" id="comentario" class="in_box_"  onblur="this.style.backgroundColor='#e1e1e1'"   title="<bean:message key="contenidoInapropiado.popup.titulo"/>"></textarea>
				<fieldset class="tipo">
					<input id="ContenidoInapropiadoAceptar" name="ContenidoInapropiadoAceptar" class="boton_90" style="float:right;" type="button" value="<bean:message key="contenidoInapropiado.popup.aceptar"/>"  />
					<input id="Limpiar" name="Limpiar" class="boton_90" type="button" value="Limpiar"/>
					<input type="hidden" name="identificador" value="${form.identificador}" />
				</fieldset>
			</div> 

			
		</form>
	</div>

	<bean:define id="comentario"><bean:message key="contenidoInapropiado.popup.error.mensaje.vacio"/></bean:define>
	<bean:define id="error"><bean:message key="contenidoInapropiado.popup.error.servidor.mensaje"/></bean:define>
	<script type="text/javascript">
	//<![CDATA[
		$("#formContenidoInapropiado").tabform({
				validationEnabled : true,
				formPluginEnabled: true,
				aceptar: "#ContenidoInapropiadoAceptar",
				limpiar: "#Limpiar"
			},
			{
				rules: { comentario: 'required'},
				messages: {comentario: '${fn:escapeXml(comentario)}'},
				onfocusout: false,
				onkeyup: false
			},
			{
				contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
				mensajeError: '${fn:escapeXml(error)}'
			}
		);
	
	//]]>
	</script>
	
	
<!-- xxxxx 05 Fin Inapropiado xxxx -->
<!-- xxxxx 05 Fin Inapropiado xxxx -->
</c:if>


		
	</div>


<br class="limpiar" />

</div>
<!-- FIN LATERAL DESPLEGABLE  -->
<!-- FIN LATERAL DESPLEGABLE  -->




			<!--  SOLAPA  -->
					<div class="capa_con_solapa" style="height:0px;">
						<a href="#" title="<bean:message key="contenido.menu.title"/>"  id="enlace_menu" name="enlace_menu" class="solapa_frame"><span><bean:message key="contenido.menu.enlace"/></span></a>
 					</div>


<!-- fin menu y tabs -->


<!-- Inicio Contenido ESPECÍFICO  -->

	<tiles:insert attribute="body" flush="true"/>

<!-- Fin Contenido ESPECÍFICO  -->

	</div>

			
</div> 

</div>

</div>

</body>
</html>

