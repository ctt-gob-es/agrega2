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
<form id="modificarGrupoPublicoVerGrupoPublicoObtenerAccionForm" action='<html:rewrite action="/ModificarGrupoPublico/VerGrupoPublicoObtenerAccion"/>' method="post" >
<h2><bean:message key="grupoPublico.modificar.cabecera"/></h2>


<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">

		<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario" >
			<input type="hidden" id="id" name="id" value="${form.id}" />
			<input type="hidden" id="nombre" name="nombre" value="${form.nombre}" />
			<input type="hidden" id="administrador" name="administrador" value="${form.administrador}" />
				<!-- -->
					<div class="fila_de_tabla">
  					<div class="contenedor_izquierda">
  						<div class="text"><label for="nombre">* <bean:message key="grupoPublico.nombre"/></label></div>
 					</div>
					<div class="contenedor_derecha">
						<div class="text"><input name="nombre" value="${form.nombre}" readonly="readonly"  style="background-color: #e1e1e1" id="nombre" type="text" maxlength="70" title="<bean:message key="gruposPublicos.introduzca.nombre"/> "  /></div>
					</div>

					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>
				<!-- -->
				<div class="fila_de_tabla">
										<div class="contenedor_izquierda">
											<div class="text"><label for="descripcion"><bean:message key="grupoPublico.descripcion"/></label>
											</div>

										</div>
										<div class="contenedor_derecha">
											<div class="text">
												<textarea name="descripcion" rows="5" cols="40" class="ta_minimo_ancho"  onblur="this.style.backgroundColor='#e1e1e1'"  id="descripcion"  title="<bean:message key="gruposPublicos.introduzca.descripcion"/>" > ${form.descripcion}</textarea>
											</div>
										</div>
										<div class="linea_separadora">
										</div>

									</div>
			<!-- -->
		<!-- -->
				<div class="fila_de_tabla">
  					<div class="contenedor_izquierda">
  						<div class="text"><span class="tipo_label"><bean:message key="grupoPublico.imagen"/></span></div>
 					</div>
					<div class="contenedor_derecha">

					<div class="text"><c:forEach items="${form.imagenes}" var="imagen"><img src="${imagen[0] }" alt="${imagen[1]}" class="imagen_radio_sin" width="50" height="41" /><input type="radio" class="boton_radio vert" value="${imagen[1]}" id="${imagen[1]}" name="imagen" <c:if test="${form.imagen==imagen[1]}"> checked="checked"  </c:if>  /><label for="${imagen[1]}" class="oculto"></label></c:forEach></div>
					</div>
					<div class="linea_separadora"></div>
					<br  />
				</div>	
				<!-- -->
		

</div>
				<!--  FIN CAJA DE FORMULARIO   -->
				
			</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->

<input class="boton_125_de_2 tipo"  type="submit" name="accionAceptar"  value="<bean:message key='usuarios.aceptar'/>" />
<input class="boton_125_de_2_izq tipo"  type="submit" name="accionCancelar"  value="<bean:message key='usuarios.cancelar'/>" />

</form>



</div>

</tiles:put>
</tiles:insert>

