<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/WEB-INF/tlds/tags-empaquetador.tld" prefix="emp" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>

<!-- 
Los valores que se dan en las lineas de debajo a los action estan en el fichero web/target/src/WEB-INF/struts-config.xml del proyecto. 
El fichero /WEB-INF/tlds/tags-empaquetador.tld hace referencia al web/src/jsp/WEB-INF/tags/layout.tag en el tag layout. El archivo
layout.tag sera necesario editarlo en caso que se quieran añadis mas beans como los de las lineas de abajo.
-->
<bean:define id="urlGuardar"><html:rewrite action="/AsociacionArchivos/ArchivosEstructura"/></bean:define>
<bean:define id="urlAsistente"><html:rewrite action="/AsociacionArchivos/ArchivosEstructura"/></bean:define>
<bean:define id="urlEstructura"><html:rewrite action="/AsociacionArchivos/ArchivosEstructura"/></bean:define>
<bean:define id="urlArchivos"><html:rewrite action="/AsociacionArchivos/ArchivosArchivos"/></bean:define>
<bean:define id="selected" value="estructura"/>

<emp:layout selected="${selected}" urlArchivos="${urlArchivos}" urlEstructura="${urlEstructura}" urlGuardar="${urlGuardar}" urlAsistente="${urlAsistente}" titleKey="title.Comun">
	<!-- Inicio plantilla contenido  -->
	<!-- Inicio plantilla contenido  -->
	<div class="plantilla_contenido">
		<%@ include file="/layout/messages.jsp" %>
			<h2><bean:message key="portalempaquetado.basico.asociar.archivos.titulo"/></h2>
			<form id="asociacionArchivosArchivosSubmitForm" method="post" action="<html:rewrite action="/AsociacionArchivos/ArchivosSubmit"/>" >
			<p class="parrafo_comun" id="separacion2"><bean:message key="portalempaquetado.basico.asociar.archivos.seleccioneArch"/>:</p>
			<div class="interno_ficha">
			<div class="plantilla_contenido_pestanias">
			<!--  INICIO GLOBO GRIS   -->
			
			<!--  INICIO GLOBO GRIS   -->

			<div class="globo_gris" >
				<div class="globo_gris_01">
					<div class="globo_gris_02">
						<div class="globo_gris_03">
							<!--  INICIO CAJA DE FORMULARIO   -->
							<div id="formulario" >							
								<div class="breadcrumb_arbol">
									<logic:iterate id="archivo" name="form" property="path">	
										<strong class="oculto">-</strong>
										<a class="br_carpeta_abierta" href="<html:rewrite action="/AsociacionArchivos/ArchivosNavegar"/>?href=${archivo.href}">${archivo.nombre}</a>
									</logic:iterate>
							 	</div>
							
							 <div class="caja_tabla limpiar_breadcrumb" >
								<table border="1" class="generica_02" cellpadding="0" cellspacing="0" width="100%" summary="Tabla de Vista por Carpeta">
									<caption>
										<strong>
											<bean:message key="portalempaquetado.basico.asociar.archivos.tablaCarpeta"/>
										</strong>
									</caption>
								 
									<bean:define id="navegarUrl"><html:rewrite action="/AsociacionArchivos/ArchivosNavegar"/></bean:define>
									<bean:define id="asociarUrl"><html:rewrite action="/AsociacionArchivos/ArchivosAsociar"/></bean:define>
									<bean:define id="desasociarUrl"><html:rewrite action="/AsociacionArchivos/ArchivosDesasociar"/></bean:define>
								 
								 	<c:set var="contador" value="true"/>
									 <logic:iterate id="archivo" name="form" property="ficheros">
									 <c:if test="${!archivo.esProtegido}">
										 <logic:equal name="contador" value="true">
											<tr class="tr_gris">
										 </logic:equal>
										 <logic:equal name="contador" value="false">
											<tr class="tr_blanco">
										 </logic:equal>
										 <c:set var="contador" value="${!contador}"/>
										 <emp:asociar 	seleccionados="${form.ficherosSeleccionados}" 
														archivo="${archivo}"
														keyAso="portalempaquetado.basico.asociar"
														keyDes="portalempaquetado.basico.desasociar"
														urlAso="${asociarUrl}"
														urlDes="${desasociarUrl}"
														urlArc="${navegarUrl}" />										
										</tr>
									</c:if>
									</logic:iterate>
								 </table>
							</div>
					<!--  FIN CAPA TABLA   -->		
					<!--  FIN CAPA TABLA   -->		
						</div>
		
						<!--  FIN CAJA DE FORMULARIO   -->
</div>	
					</div>
				</div>
			</div>
			<!--  FIN GLOBO GRIS   -->
			<!--  FIN GLOBO GRIS   -->
			<div class="leyendas">
			<br/>
			<h3><bean:message key='portalempaquetado.basico.asociar.archivos.leyenda.simbologia'/>:</h3>
			<br/>
			<span class="carpeta_vacio"><bean:message key='portalempaquetado.basico.asociar.archivos.leyenda.vacia'/></span>&nbsp;&nbsp;
			<span class="carpeta_parcial"><bean:message key='portalempaquetado.basico.asociar.archivos.leyenda.parcial'/></span>&nbsp;&nbsp;
			<span class="carpeta_completa"><bean:message key='portalempaquetado.basico.asociar.archivos.leyenda.completa'/></span>
		</div>		
			</div>
			<!-- Inicio Botones  -->
		<!-- Inicio Botones  -->
		<fieldset class="tipo">
			<input class="boton_125_de_2"  type="submit"  name="action" value="<bean:message key='portalempaquetado.basico.asociar.archivos.aceptar'/>" />
			<input class="boton_125_de_2_izq"  type="submit"  name="action" value="<bean:message key='portalempaquetado.basico.asociar.archivos.cancelar'/>" />
		</fieldset>
		<!-- Fin Botones  -->
		<!-- Fin Botones  -->
			</div>		
		</form>
	</div>
	<!-- Fin plantilla contenido  -->
	<!-- Fin plantilla contenido  -->

</emp:layout>