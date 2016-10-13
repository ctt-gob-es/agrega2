<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tags/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html:xhtml/> 
<tiles:insert definition="layout-gestor-flujo-con-style">
<tiles:put name="title" type="string">
	<bean:message key="title.Comun"/>
</tiles:put>
<tiles:put name="body" type="string">
<%@ include file="/taglib-imports.jspf" %>

<div class="plantilla_contenido">


<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />
<form id="listarGruposAsociadosListarGruposAsociadosAsociarForm" action='<html:rewrite action="/ListarGruposAsociados/ListarGruposAsociadosAsociar"/>' method="post" >
<h2><bean:message key='odes.asociarOde.cabecera'/></h2>

<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div class="formu" >
			<p class="parrafo_comun" id="separacion06" ><bean:message key="grupos.odesAsociar.seleccione.grupos"/></p>
			<div class="caja_tabla bordeada" >

	<display:table name="${form.gruposAsociados}" uid="fila" requestURI=""
			        export="false" pagesize="8" sort="list" style="border:1;width:100%;" class="administracion_tareas"
					cellpadding="0" cellspacing="0" summary="${summary}">
					
					<display:setProperty name="css.tr.odd" value="tr_gris"/>
					<display:setProperty name="css.tr.even" value="tr_blanco"/>
					<display:setProperty name="basic.show.header" value="true"/>
	
					<!--		Columna de checkbox		-->
				
				<display:column media="html" style="valign:top;" class="sin_b">
				<c:if test="${!fila.contieneOde}">
		            <input type="checkbox" name="idRowSelectionAsArray" value="${fila.id}" title="<bean:message key='usuarioPublico.itinerarios.aprendizaje.seleccione'/>"/>
		        </c:if>
		       <c:if test="${fila.contieneOde}">
		       	<input type="checkbox"  checked="checked" disabled="disabled" name="idRowSelectionAsArray" value="${fila.id}"/>
		       </c:if>
		        </display:column>	
		         
				<display:column sortable="true" sortProperty="nombre" style="valign:top;" class="tar">

					<a href="<html:rewrite action="/GestionGrupoPublico/GestionGrupoPublico.do?nombre=${fila.nombre}"/>" id="${fila.nombre}" >${fila.nombre}
					</a>
				</display:column>
				<display:column sortable="true" sortProperty="administrador" style="valign:top;" class="tar">
					${fila.administrador}
				</display:column>
			</display:table>
			</div>

</div>
			</div>
		</div>
	</div>


<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->
			
			
			<!-- </div> Fin de la caja tabla (SE CIERRA EN displaytag.properties) -->
			<!-- en caso de no tener registros de usuarios se tiene que poner el div		-->
				
			<c:if test="${fn:length(form.gruposAsociados) < 1 || !(form.todosAsociados)}" >
	
	            </div>
	                  
				<!--		Fin caja tabla		-->
		
			</c:if>
		<input type="hidden" id="id_mec" name="id_mec" value="${form.id_mec}"/>
		<input type="hidden" id="titulo" name="titulo" value="${form.titulo}"/>
		<input type="hidden" id="idioma" name="idioma" value="${form.idioma}"/>
		<input type="hidden" id="vuelta" name="vuelta" value="${form.vuelta}"/>
		<input type="hidden" id="nombre" name="nombre" value="${form.nombre}"/>
		
		<c:if test="${fn:length(form.gruposAsociados) > 0 && !(form.todosAsociados)}" >
		<input class="boton_125_de_2 tipo"  type="submit" action="accion" name="actionAceptar"  value="<bean:message key='usuarioPublico.itinerarios.aprendizaje.asociar'/>" />
		</c:if>
		<input class="boton_125_de_2_izq tipo"  type="submit"  name="actionCancelar" value="<bean:message key='usuarios.cancelar'/>" />

</form>
<!--		Formulario secundario y boton cancelar		-->


	</div>

</tiles:put>
</tiles:insert>

