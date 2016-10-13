<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/tags-empaquetador.tld" prefix="emp" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<bean:define id="urlArchivos"><html:rewrite action="/GestorArchivos/GestorArchivos"/></bean:define>
<bean:define id="urlRecursos"><html:rewrite action="/GestorRecursos/GestorRecursos"/></bean:define>
<bean:define id="urlOrganizaciones"><html:rewrite action="/GestorOrganizaciones/GestorOrganizaciones"/></bean:define>
<bean:define id="urlSubmanifiestos"><html:rewrite action="/GestorSubmanifiestos/GestorSubmanifiestos"/></bean:define>
<bean:define id="urlGuardar"><html:rewrite action="/GestorArchivos/GestorArchivos"/></bean:define>
<bean:define id="urlAsistente"><html:rewrite action="/GestorArchivos/GestorArchivos"/></bean:define>
<bean:define id="urlEstructura"><html:rewrite action="/GestorBasico/GestorBasico"/></bean:define>
<bean:define id="selected" value="archivos"/>
<emp:layout selected="${selected}" urlArchivos="${urlArchivos}" urlRecursos="${urlRecursos}" urlSubmanifiestos="${urlSubmanifiestos}" urlOrganizaciones="${urlOrganizaciones}" urlEstructura="${urlEstructura}" urlGuardar="${urlGuardar}" urlAsistente="${urlAsistente}" titleKey="title.Comun">
<bean:define id="empaquetadorSesion" name="<%=es.pode.empaquetador.presentacion.EmpaquetadorSession.SESSION_KEY%>" scope="session"/>
<bean:define id="gestorArchivosSesion" name="<%=es.pode.empaquetador.presentacion.archivos.GestorArchivosSession.SESSION_KEY%>" scope="session"/>
<%@ include file="/es/pode/empaquetador/presentacion/archivos/gestor/gestor-archivos-vars.jspf" %>

<c:if test="${!empty empaquetadorSesion.mensajeAsistente}">
	<div class="bocadillo">
	<div class="bocadillo_int">
	<div>
	<strong class="titulo_boc"><bean:message key="empaquetador.asistente.Recomendacion"/></strong>
	<p>${empaquetadorSesion.mensajeAsistente}</p>
	</div>
	</div>
	</div>
</c:if>

<!-- Inicio plantilla contenido  -->
<div class="plantilla_contenido">
<%@ include file="/layout/messages.jsp" %>

	<bean:define id="vistaCarpeta" value="${empaquetadorSesion.vistaCarpeta}"></bean:define>
<!-- Inicio  PESTANIAS de FICHA -->
<!-- Inicio  PESTANIAS de FICHA -->
	<div id="ficha_pestanias">
	<div><h2><bean:message key="presentacion.archivos.gestor.cabecera.h2"/></h2></div>
		<ul>
			<logic:equal name="vistaCarpeta" value="true">
				<li><a href="<html:rewrite action="/GestorArchivos/GestorArchivosVista"/>?vistaCarpeta=false"><bean:message key="presentacion.archivos.gestor.pestanias.vista.por.arbol"/></a></li>
				<li id="pest_activa"><a href="<html:rewrite action="/GestorArchivos/GestorArchivosVista"/>?vistaCarpeta=true" id="seleccionada"><bean:message key="presentacion.archivos.gestor.pestanias.vista.por.carpeta"/></a></li>
			</logic:equal>
			<logic:equal name="vistaCarpeta" value="false">
				<li id="pest_activa"><a href="<html:rewrite action="/GestorArchivos/GestorArchivosVista"/>?vistaCarpeta=false" id="seleccionada"><bean:message key="presentacion.archivos.gestor.pestanias.vista.por.arbol"/></a></li>
				<li><a href="<html:rewrite action="/GestorArchivos/GestorArchivosVista"/>?vistaCarpeta=true"><bean:message key="presentacion.archivos.gestor.pestanias.vista.por.carpeta"/></a></li>
			</logic:equal>
			
		</ul>
	</div>

<!-- Fin PESTANIAS de FICHA -->
<!-- Fin PESTANIAS de FICHA -->

<!-- Inicio CONTENIDO PESTANIAS -->
<!-- Inicio CONTENIDO PESTANIAS -->
<div class="interno_ficha">

<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->

<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- ATENCION!! E3TA ES LA CAPA QUE DEBE REEMPLAZARSE POR LA DEL MISMO NOMBRE (plantilla contenido pestanias ) EN LA PLANTILLA DE  CONTENIDO  CON EL CONTENIDO DE FICHA -->

<div class="plantilla_contenido_pestanias">

<form id="gestorArchivosGestorArchivosSubmitForm" method="post" action="<html:rewrite action="/GestorArchivos/GestorArchivosSubmit"/>">
	<!-- Aviso de licencias -->
	<c:if test="${form.mostrarAvisoLicencia == true}">
		<div class="error">
		<!-- Poner otro mensaje -->
		<bean:message key="comunes.licenciaNoPermiteObraDerivada" />
		</div>
	</c:if>
	<!-- Aviso de cambio de licencias -->
	<c:if test="${form.mostradoMensajeCompatibilidadLicencia == false}">
	<!-- Class exito se pinta en verde, luego deber�a pasar var para pintar como error o como exito -->
		<div class="error">
		<!-- Mostramos mensaje -->
		<p>${empaquetadorSesion.mensajeCompatibilidadLicencias }</p>
		</div>
	</c:if>
<logic:notEmpty name="portapapeles">
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris">
  <div class="globo_gris_01">
	<div class="globo_gris_02">
 		<div class="globo_gris_03">
 		
<!-- ******************************************* INICIO CAJA DE FORMULARIO   *****************************-->
				<div id="formulario_02">
				<a href="<html:rewrite action="/GestorArchivos/GestorArchivosVaciarPortapapeles"/>" class="flotder_enlace"><bean:message key="presentacion.archivos.gestor.portapapeles.link.vaciar"/></a>
				<h3><bean:message key="presentacion.archivos.gestor.portapapeles.h3"/></h3>
				
				<!--  INICIO CAPA TABLA PORTAPAPELES   -->
			
				<c:set var="portapapeles" value="${portapapeles}"/>				
 				<div class="caja_tabla bordeada">
					<table border="1" class="generica_02" id="portapapeles" cellpadding="0" cellspacing="0" width="100%" summary="<bean:message key="presentacion.archivos.gestor.portapapeles.table.summary"/>">
					<caption><strong><bean:message key="presentacion.archivos.gestor.portapapeles.table.caption"/></strong></caption>
						<logic:iterate id="idportapapeles" name="portapapeles">
							
							<tr class="tr_beige">
								<td valign="top" class="td_larga"><a href="#" class="recurso">${idportapapeles.nombre}</a></td>
								<td class="td_corta02"><a href="<html:rewrite action="/GestorArchivos/GestorArchivosPortapapelesEliminar"/>?nombre=${idportapapeles.nombre}"><bean:message key="presentacion.archivos.gestor.portapapeles.link.eliminar"/></a></td>
							</tr>
						</logic:iterate>
					</table>
				</div>
			
			<!-- ***************************** FIN CAPA TABLA PORTAPAPELES *****************************  -->
				
			</div>
			
				<!-- ***************************** FIN CAJA DE FORMULARIO   *****************************-->
		</div>
	</div>
  </div>
</div>
<!--  FIN GLOBO GRIS   -->
 <br/>
</logic:notEmpty>

<!-- *****************************INICIO ARBOL VERTICAL *****************************-->
<logic:equal name="vistaCarpeta" value="false">
<div id="menu_arbol">
<ul>
	<bean:define id="actionNavegar"><html:rewrite action="/GestorArchivos/GestorArchivosNavegar"/></bean:define>
	<emp:archivos arbol="${form.arbol}" niveles="1" actionNavegar="${actionNavegar}" path="${gestorArchivosSession.path}"/>
</ul>
</div>
</logic:equal>


<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris">
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">

	        <!--  *****************************INICIO CAJA DE FORMULARIO  ***************************** -->
			<div id="formulario">		  	
<!--  *****************************bucle coleccion  ***************************** -->	
<!--  Breadcrumb   -->
		<div class="breadcrumb_arbol"><em class="oculto">-</em>
			<logic:iterate id="archivo" name="path">	
				<a class="br_carpeta_abierta" href="<html:rewrite action="/GestorArchivos/GestorArchivosNavegar"/>">${archivo.nombre}</a>
			</logic:iterate>
		 </div>
<!--  *****************************INICIO CAPA TABLA   ***************************** -->
					
             <div class="caja_tabla bordeada limpiar_breadcrumb">
				<table border="1" class="generica_02" cellpadding="0" cellspacing="0" width="100%" summary="<bean:message key="presentacion.archivos.gestor.archivos.table.summary"/>">
					<caption><strong><bean:message key="presentacion.archivos.gestor.archivos.table.caption"/></strong></caption>
<!--  *****************************PARA QUE HAGA CAMPO GRIS,CAMPO BLANCO  *****************************-->
					<c:set var="contador" value="true"/>
					<c:set var="contflechas" value="0"/>
					<c:set var="sinArchivos" value="true"/>
					<logic:iterate id="archivo" name="listado">
						<bean:define id="esProtegido" name="archivo" property="esProtegido"/>
						<logic:equal name="esProtegido" value="false">
						<logic:equal name="contador" value="true">
							<tr class="tr_gris">
						</logic:equal>
						<logic:equal name="contador" value="false">
							<tr class="tr_blanco">
						</logic:equal>
						<c:set var="contador" value="${!contador}"/>
<!--  *****************************FIN PARA QUE HAGA CAMPO GRIS,CAMPO BLANCO ***************************** -->
<!--  *****************************dibujo: grupo,recurso o Submanifiesto ***************************** -->
						<emp:checkbox portapapeles="${portapapeles}" grupo="${archivo}" name="nombreRowSelectionAsArray" accion="${gestorArchivosSession.accion}"/>
 						<bean:define id="esFichero" value="${archivo.esFichero}"/>
 						<logic:equal name="esFichero" value="false">
   							<td valign="top" class="td_larga"><a href="<html:rewrite action="/GestorArchivos/GestorArchivosNavegar"/>?href=${archivo.href}" class="carpeta_cerrada"> ${archivo.nombre} </a></td>
   							<td valign="top" class="td_corta03" align="center"><span class="oculto">-</span><a href="<html:rewrite action="/GestorArchivos/GestorArchivosModificar"/>?nombre=${archivo.nombre}"><bean:message key="presentacion.archivos.gestor.archivos.link.modificar"/></a></td>
						</logic:equal>
 						<logic:equal name="esFichero" value="true">
 							<bean:define id="recursoURL"><rewrite:rewrite url="${archivo.href}"/></bean:define>
  							<td valign="top" class="td_larga"><a  target="_blank" href="<emp:encodeURL url="${recursoURL}"/>" class="archivo"> ${archivo.nombre} </a></td>
  							<td valign="top" class="td_corta03" align="center"><span class="oculto">-</span><a href="<html:rewrite action="/GestorArchivos/GestorArchivosModificar"/>?nombre=${archivo.nombre}"><bean:message key="presentacion.archivos.gestor.archivos.link.modificar"/></a></td>
 						</logic:equal>
					</tr>
					<c:set var="sinArchivos" value="false"/>
					</logic:equal>
  					</logic:iterate>
<logic:equal name="sinArchivos" value="true">
<tr class="tr_blanco">
<td><bean:message key="presentacion.archivos.gestor.table.empty.message"/></td>
</tr>
</logic:equal>
 				</table>
			</div>
	<!-- ***************************** FIN CAPA TABLA   *****************************-->
		</div>

<!-- Inicio Botones  -->
<!-- Inicio Botones  -->
<br class="oculto"/>
<fieldset class="tipo_interior_02">
   <logic:notEmpty name="gestorArchivosSesion" property="portapapeles">
    <logic:equal name="gestorArchivosSesion" property="modoPegar" value="true">	
        <logic:notEqual name="gestorArchivosSesion" property="accion" value="Normal">	
	 	    <input type="submit" class="boton_65_de_3_der bot_mar_izq" name="action" value="<bean:message key='presentacion.archivos.gestor.archivos.submit.pegar'/>" />
	    </logic:notEqual>
    </logic:equal>
   </logic:notEmpty>
	

	 <logic:equal name="gestorArchivosSesion" property="modoPegar" value="false">	
	   <logic:equal name="gestorArchivosSesion" property="accion" value="Normal">	
	   	<input type="submit" class="boton_65_de_3_der bot_mar_izq" name="action" value="<bean:message key='presentacion.archivos.gestor.archivos.submit.cortar'/>" />
	   </logic:equal>
	   <logic:equal name="gestorArchivosSesion" property="accion" value="Cortar">	
	   	<input type="submit" class="boton_65_de_3_der bot_mar_izq" name="action" value="<bean:message key='presentacion.archivos.gestor.archivos.submit.cortar'/>" />
	   </logic:equal>
	</logic:equal>
	
	<logic:equal name="gestorArchivosSesion" property="modoPegar" value="false">	
	   <logic:equal name="gestorArchivosSesion" property="accion" value="Normal">	
	   	       		<input type="submit" class="boton_65_de_3_der bot_mar_izq" name="action" value="<bean:message key='presentacion.archivos.gestor.archivos.submit.copiar'/>" />
	   </logic:equal>
	   <logic:equal name="gestorArchivosSesion" property="accion" value="Copiar">	
       		<input type="submit" class="boton_65_de_3_der bot_mar_izq" name="action" value="<bean:message key='presentacion.archivos.gestor.archivos.submit.copiar'/>" />
	   </logic:equal>
	</logic:equal>
	
	<logic:equal name="gestorArchivosSesion" property="accion" value="Normal">	
	      <input type="submit" class="boton_65_de_3_der  bot_mar_izq" name="action" value="<bean:message key='presentacion.archivos.gestor.archivos.submit.eliminar'/>" />
	</logic:equal>

	<br class="oculto" /><br class="oculto" />
	<input class="boton_125_de_2_izq bot_mar_der"  type="submit"  name="action" value="<bean:message key='presentacion.archivos.gestor.archivos.submit.crear.carpeta'/>" />
	<input class="boton_125_de_2_izq bot_mar_der"  type="submit"  name="action" value="<bean:message key='presentacion.archivos.gestor.archivos.submit.crear.archivo'/>" />
			
</fieldset>
		<!-- Fin Botones  -->

			<!--  FIN CAJA DE FORMULARIO   -->
	  </div>
    </div>
   </div>
  </div>
<!--  FIN GLOBO GRIS   -->
</form>
</div>
<!-- Fin CONTENIDO PESTANIAS -->
<!-- Fin CONTENIDO PESTANIAS -->

</div>
</div>
<!-- Fin plantilla contenido  -->
<!-- Fin plantilla contenido  -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->



<!-- HASTA AQUI EL REEEMPLAZO -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
</emp:layout>
