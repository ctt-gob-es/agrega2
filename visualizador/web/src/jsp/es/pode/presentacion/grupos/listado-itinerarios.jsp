<%@ include file="/taglib-imports.jspf" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>

<tiles:insert definition="layout-principal">



<tiles:put name="title" type="string">
	<bean:message key="title.Comun"/> - ${fn:escapeXml(visualizadorSession.tituloOde)}
</tiles:put>

<tiles:put name="body" type="string">
<!-- CONTENIDO -->
					
<!-- Panel contenidos!! -->
<div id="capa_contenidos" name="capa_contenidos">
<div id="contenido" name="contenido" style="margin-left: 0pt; min-width: 520px; min-height: 440px; height: 223px; width: 530px;">


<jsp:include page="/layout/messages.jsp" flush="true" />
<!-- Inicio plantilla contenido  -->
<!-- Inicio plantilla contenido  -->
<div class="plantilla_contenido" id="contenido_it" style="position:relative;">
<div id="contenido_externo">


<h2 align="left"><bean:message key="itinerarios.listado.titulo"/></h2>

<form method="POST" action="<html:rewrite action="/Grupos/ListadoItinerariosAsociar"/>" >

<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_blanco uno_b">
	<div class="globo_blanco_01">
		<div class="globo_blanco_02">
			<div class="globo_blanco_03" style="padding:10px 0 !important">
			<!--  INICIO CAJA DE FORMULARIO   -->
<div id="formulario" >
<div class="caja_tabla " id="bordeada" style="padding:0 !important" >
					


	
					<display:table 
						name="${form.listadoGrupos}" 
						requestURI=""
						export="false" 
						uid="fila" 
						class="administracion_tareas" 
						style="width:100%;border:1;"
						cellpadding="0" 
						cellspacing="0" 
						sort="page" 
						pagesize="8">
					
						<display:setProperty name="css.tr.odd" value="tr_gris"/>
						<display:setProperty name="css.tr.even" value="tr_blanco"/>
						<display:setProperty name="basic.show.header" value="false"/>	
						<display:setProperty name="basic.msg.empty_list"><bean:message key="itinerarios.listado.vacio"/></display:setProperty>
						<display:setProperty name="basic.empty.showtable" value="false" />

						
						    <display:column style="valign:top;align:left;" class="sin_b">        	
								<label for="${fila.nombre}" class="oculto" ><bean:message key="itinerarios.listado.seleccione"/></label>
								<c:if test="${fila.asociado }">	
						            <input type="checkbox"  id="${fila.nombre}" value="${fila.nombre}" name="seleccionadoRowSelectionAsArray" class="rad_check" checked="checked" disabled="disabled"/>
						        </c:if>
								<c:if test="${!fila.asociado }">	
						            <input type="checkbox"  id="${fila.nombre}" value="${fila.nombre}" name="seleccionadoRowSelectionAsArray" class="rad_check"/>
						        </c:if>
					        </display:column>

					        <display:column style="valign:top;align:left;" class="tar4">
					        	 	${fila.nombre}
					        </display:column>
        			</display:table>

</div>
</div>
				</div>
			</div>
		</div>
</div>
<!--  FIN GLOBO Blanco   -->
<!--  FIN GLOBO Blanco   -->
<!--  FIN GLOBO Blanco   -->
		


			<br class="oculto">
			
		<c:if test='${!empty form.listadoGrupos }'>
			<!-- Inicio Botones  -->
			<!-- Inicio Botones  -->
			<fieldset class="tipo ft_centrada">
				<input class="boton_125" type="submit"  value="<bean:message key="itinerarios.listado.asociar"/>" />
			</fieldset>
			<!-- Fin Botones  -->
			<!-- Fin Botones  -->
				
		</c:if>
</form>
			
</div>
</div>


</div>

</div>
<script type="text/javascript">
//<![CDATA[
	$("#contenido_central_largo").contenido({
		ancho_menu:430,
		inicio_menu_desplegado:'false',
		boton_menu:'#enlace_menu',
		contenedor_menu:'#panel_menu',
		contenedor_frame:'#contenido',
		flechas:0
		}
	);
//]]>
</script>

</tiles:put>
</tiles:insert>
