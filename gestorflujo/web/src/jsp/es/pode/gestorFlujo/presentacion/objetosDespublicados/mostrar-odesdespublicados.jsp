<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib prefix="agrega" uri="/WEB-INF/tlds/agregaProperties.tld" %>
<html:xhtml/>
<tiles:insert definition="layout-administrador">

<tiles:put name="title" type="string">
	<bean:message key="title.Admnistracion"/>
</tiles:put>

<tiles:put name="body" type="string">
<script type="text/javascript">
var checkTodas=1;
function marcarTodas()
	{	
	 	var checkboxes= document.getElementsByTagName("input");
		if(checkTodas==1)
		{			
			for (var x=0; x < checkboxes.length; x++) 
			{
				
				if(checkboxes[x].type=="checkbox")
				{
			   	 	checkboxes[x].checked=true;
				}
      		 }
      		 checkTodas=0;
		}
		else
		{
		for (var x=0; x < checkboxes.length; x++) 
			{
				if(checkboxes[x].type=="checkbox")
				{
			   	 	checkboxes[x].checked=false;
				}
      		 }
      		 checkTodas=1;
		}
	}	
</script>
<!-- *****************************   Inicio  Plantilla contenido      ********************************** -->
		<div class="plantilla_contenido"><!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
		<jsp:include page="/layout/messages.jsp" flush="true" /> <!-- Inicio  PESTANIAS de FICHA -->
		<!-- Inicio  PESTANIAS de FICHA -->

		<div id="ficha_pestanias">
		<div>
		<h2><bean:message key="gestorFlujo.mostrarOdes.despublicados.title" /></h2>
		</div>
			<ul>
			<li id="pest_activa" ><a
				href="<html:rewrite action="${initParam.url_despublicados}"/> "
				id="seleccionada"><bean:message
				key="gestorFlujo.mostrarOdes.despublicados" /></a></li>	
			<li ><a
				href="<html:rewrite action="${initParam.url_pendientes} "/>"
				><bean:message
				key="gestorFlujo.mostrarOdes.pendientes" /></a></li>		
			</ul>
		</div>
		<!-- Fin PESTANIAS de FICHA --> <!-- Fin PESTANIAS de FICHA --> <!-- Inicio CONTENIDO PESTANIAS -->

		<!-- Inicio CONTENIDO PESTANIAS -->
		<div class="interno_ficha"><!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
		<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
		<!-- ATENCION!! E3TA ES LA CAPA QUE DEBE REEMPLAZARSE POR LA DEL MISMO NOMBRE (plantilla contenido pestanias ) EN LA PLANTILLA DE  CONTENIDO  CON EL CONTENIDO DE FICHA -->
		<div class="plantilla_contenido_pestanias">
		<!-- CAJA TABLA --> <!-- CAJA TABLA -->
		
<form method="post"	action="<html:rewrite action="${initParam.url_despublicados}"/>"> 
				<!-- Inicio caja buscador -->
<!-- Inicio caja buscador -->
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris"  >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03" style="padding-bottom:0 !important">
			<!--  INICIO CAJA DE FORMULARIO   -->		
			
			<div id="formulario_02" >
			<!-- 
			<h3 class="h3_generico_small"><bean:message key="usuarios.busqueda.usuarios" /></h3>
			-->
			<h3 class="h3_generico_small">Busqueda de ODEs despublicados</h3>
			
  				<div class="fila_de_tabla" style="border:1px solid #D6E6F6">
					<div class="text">
						<label class="oculto" for="buscarUsuario"></label>
						
							<table border="0" class="res_list_inv" style="border-bottom:0;" cellpadding="0" cellspacing="0" >
							<tr>
							<td valign="top" class="dere00 td_short">
								<!--bean:message key="estadisticas.estadisticas.cambiar.fechas.param.fecha.desde.actividad"/>-->
								Despublicado entre el
							</td>
							<td valign="top" class="dere00 td_large">
								<input name="fechaInicioBusqueda" class="fechazo calendario" style="left:0 !important;" onfocus="limpiarTexto(this)" value="${form.fechaInicioBusqueda}"  onblur="this.style.backgroundColor='#e1e1e1'" id="fechaInicioBusqueda" type="text" title="<bean:message key="buscador.buscador"/>"  />
								<div class="autocomplete" id="list" style="display:none"></div>
								&nbsp;&nbsp;&nbsp; y el &nbsp;&nbsp;&nbsp;
								<input name="fechaFinBusqueda" class="fechazo calendario" style="left:0 !important;" onfocus="limpiarTexto(this)" value="${form.fechaFinBusqueda}"  onblur="this.style.backgroundColor='#e1e1e1'" id="fechaFinBusqueda" type="text" title="<bean:message key="buscador.buscador"/>"  />
								<div class="autocomplete" id="list" style="display:none"></div>
							</td>
							</tr>
							
							<tr>
							<td valign="top" class="dere00 td_short">
								Usuario despublicador
							</td>
							<td valign="top" class="dere00 td_large">
								<input name="idUsuarioBusqueda" style="width:500px;margin-right:10px;" onfocus="limpiarTexto(this)" value="${form.idUsuarioBusqueda}"  onblur="this.style.backgroundColor='#e1e1e1'" id="idUsuarioBusqueda" type="text" title="<bean:message key="buscador.buscador"/>"  />
								<div class="autocomplete" id="list" style="display:none"></div>
							</td>
							</tr>
							
							<tr>
							<td valign="top" class="dere00 td_short">
								Titulo ODE despublicado
							</td>
							<td valign="top" class="dere00 td_large">
								<input name="tituloBusqueda" style="width:500px;margin-right:10px;" onfocus="limpiarTexto(this)" value="${form.tituloBusqueda}"  onblur="this.style.backgroundColor='#e1e1e1'" id="tituloBusqueda" type="text" title="<bean:message key="buscador.buscador"/>"  />
								<div class="autocomplete" id="list" style="display:none"></div>
							</td>	
							</tr>
							
							</table>
								
						</div>
				</div>
				
				<div style="text-align: center;">
				<input class="boton_90" id="bus_solo" type="submit" value="<bean:message key="buscador.buscador.boton"/>" />
				</div>
				
				<!-- -->
			</div>
			
				<!--  FIN CAJA DE FORMULARIO   -->
			</div>
		</div>

	</div>
</div>
</form>		
		

<link rel="stylesheet" href="/static/css/jquery-ui-1.8.23.custom.css" type="text/css" />
<script type="text/javascript" src="/static/js/jquery-ui-1.8.23.custom.min.js"></script>		
<script type="text/javascript">		
var host_and_subdomain="<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>";

$( ".calendario").datepicker({
				showOn: "button",
				buttonImage: "http://"+host_and_subdomain+"/static/img/azul/calendar.png",
				buttonText: "Seleccionar Fecha",
				buttonImageOnly: true
		});
		$( ".calendario").click( function(){ 
			$(this).datepicker("show");
		}).attr("readonly",true);
		
//$(".ui-datepicker-trigger").removeAttr('style').css("margin-left","0px !important");
$(".ui-datepicker-trigger").css("margin-left", "0px !important;");
$(".ui-datepicker-trigger").attr("style", "margin-left: 0px !important");
</script>

		
		
		<fieldset>
		
		
		<form method="post"
			action="<html:rewrite action="${initParam.url_despublicados_eliminar}"/>">
		
		<div class="caja_tabla">

		<h3 class="h3_generico"><bean:message
			key="gestorFlujo.mostrarOdes.despublicados" /></h3>
		<input type="hidden" name="esDespublicadoValueList" value="true" /> 
		<input type="hidden" name="retorno" value="${form.retorno}" />
		<c:if test="${fn:length(form.listaODES) > 1}">
			<h5 class="h5_generico"><a href="#" onclick="marcarTodas()"><bean:message
				key="gestorFlujo.mostrarOdes.selDesel" /></a></h5>
			<br/>
		</c:if> 
		
		<bean:define id="textoMod"><bean:message key="gestorFlujo.mostrarOdes.modificar" /></bean:define>
		<bean:define id="idUsuarioLocal">${form.idUsuario}</bean:define>
		<bean:define id="summary"><bean:message key="noticias.tablaAdministracionNoticias"/></bean:define>
		<bean:define id="esDespublicado">${form.esDespublicado}</bean:define>
		<bean:define id="urlCerrar">/${initParam.url_gestorFlujo_init}/${initParam.url_despublicados}</bean:define>
		<display:table name="${form.listaODES}"
			requestURI="" export="false"
			id="fila" class="administracion_tareas" style="border:1;width:100%;"
			cellpadding="0" cellspacing="0" summary="${summary}" sort="list"
			partialList="False" pagesize="20">

			<display:caption>
				<bean:message key="gestorFlujo.mostrarOdes.tablaDespublicados" />
			</display:caption>

			<display:setProperty name="css.tr.odd" value="tr_gris" />
			<display:setProperty name="css.tr.even" value="tr_blanco" />
			<display:setProperty name="basic.show.header" value="true" />
			
			<!--  ******************** COLUMNA DE CHECKBOX ***********************-->

			<display:column style="valign:top;" class="sin_b">
				<input type="checkbox" name="idODERowSelectionAsArray"
					value="${fila.idODE},${fila.titulo}"
					title="<bean:message key="gestorFlujo.mostrarOdes.seleccione"/>" />
			</display:column>

			<!--  ******************** COLUMNA DE TITULO ***********************-->
			<bean:define id="tituloValue"><b><bean:message key="titulo"/></b></bean:define>
			<bean:define id="comentario"><bean:message key="COM_OK_CI"/></bean:define>
			<c:choose>
				<c:when test="${fila.comentarios != comentario}">
					<display:column sortable="true" sortProperty="titulo" style="valign:top;" class="tar10" title="${tituloValue}" >
						<html:link action="${initParam.url_visualizar_despublicados}?idOde=${fila.idODE}" 
						styleClass = "paquete" target="blank">
							${fn:escapeXml(fila.titulo)}
						</html:link>
					</display:column>
				</c:when>
				<c:otherwise>
					<display:column sortable="true" sortProperty="titulo" style="valign:top;" class="tar10" title="${tituloValue}" >
						<html:link action="${initParam.url_visualizar_despublicados}?idOde=${fila.idODE}" 
						styleClass = "paquete_inapropiado" target="blank">
							${fn:escapeXml(fila.titulo)}
						</html:link>
					</display:column>
				</c:otherwise>
			</c:choose>
			
			<td valign="top"></td>

			<!--  ******************** COLUMNA DE HISTORIAL ***********************-->
			<display:column style="valign:top;" class="ejec2">
				<span class="oculto">-</span>
				<html:link target="blank"
					action="${initParam.url_despublicados_historial }?idODE=${fila.idODE}&titulo=${fila.titulo}">
					<bean:message key="gestorFlujo.mostrarOdes.verHistorial" />
				</html:link>
			</display:column>
			
			<!--  ******************** COLUMNA DE MODIFICAR ***********************-->
			<display:column style="valign:top;" class="tar11">
				<span class="oculto">-</span>
					<a href="<rewrite:rewrite url="${initParam.url_objetos}?identificador=${fila.idODE}&urlCerrar=${urlCerrar}"/>">${textoMod}</a>
			</display:column>	

				<!--  ******************** COLUMNA DE PUBLICAR ***********************-->
			<display:column style="valign:top;" class="ejec">
				<span class="oculto">-</span>	
					<html:link
						action="${initParam.url_despublicados_mostrar}?idODE=${fila.idODE}&idUsuario=${idUsuarioLocal}&esDespublicado=${form.esDespublicado}&retorno=${form.retorno }">
					<bean:message key="gestorFlujo.mostrarOdes.publicar" />
					</html:link>					
				
			</display:column>
			
			
				<!--  ******************** COLUMNA DE EXPORTAR ***********************-->
			<display:column style="valign:top;" class="ejec">
				<span class="oculto">-</span>	
					<html:link
						action="${initParam.url_despublicados_exportar}?idODE=${fila.idODE}&idUsuario=${idUsuarioLocal}&titulo=${fila.titulo}&esDespublicado=${form.esDespublicado}&retorno=objetosDespublicados">
					<bean:message key="gestorFlujo.mostrarOdes.exportar" />
					</html:link>					
				
			</display:column>
			
			
		</display:table>
		<!-- No se cierra el div aqui porque se hace en el archivo displaytag.properties al paginar -->
		<c:if test="${fn:length(form.listaODES) < 1}" >
  			</div>
		</c:if>
		<!-- No se cierra el div aqui porque se hace en el archivo displaytag.properties al paginar -->
		<!-- FIN CAJA TABLA --> <!-- FIN CAJA TABLA --> <!-- Inicio Botones  -->
		<!-- Inicio Botones  --> 
		<c:if test="${fn:length(form.listaODES) > 0}" >		
			<input type="submit" class="boton_125_de_2 tipo"
				value="<bean:message key="gestorFlujo.mostrarOdes.eliminar"/>" />
		</c:if>
			</form>
			</fieldset>
		
		</div>
		<!-- HASTA AQUI EL REEEMPLAZO --> <!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
		<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->


		</div>
		<!-- Fin CONTENIDO PESTANIAS --> <!-- Fin CONTENIDO PESTANIAS --> <!-- HASTA AQUI EL REEEMPLAZO -->
		<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
		<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->

		</div>
		<!-- Fin plantilla CONTENIDO -->
	</tiles:put>

</tiles:insert>

