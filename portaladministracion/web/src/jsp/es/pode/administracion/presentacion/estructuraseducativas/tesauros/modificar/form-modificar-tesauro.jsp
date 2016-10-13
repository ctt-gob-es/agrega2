<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/tags/banderas.tld" prefix="flags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tiles:insert definition="layout-administrador">
<tiles:put name="title" type="string">
	<bean:message key="title.Comun"/>
</tiles:put>
<tiles:put name="body" type="string">
<%@ include file="/taglib-imports.jspf" %>

<!--************************          Inicio plantilla contenido       ****************************-->
<div class="plantilla_contenido">

<jsp:include page="/layout/messages.jsp" flush="true" />

 
 
<form id="formModificarTesauroSubmit" 
	action="<html:rewrite action="/ModificarTesauros/FormModificarTesauroSubmit"/>" method="post" enctype="multipart/form-data">
<h2><bean:message key="estructuras.modificar"/>: ${form.nombre}</h2>

	<html:hidden name="form" property="tipo" />
	<html:hidden name="form" property="identificadorVdex" />
	<html:hidden name="form" property="nombre" />

<c:if test="${form.tipo=='TESAURO'}">
	
	
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris uno_b" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">

		   <!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario">
			 	<div class="fila_de_tabla">
  					<div class="text ft_lateral">
  						<html:checkbox name="form" property="actualizarVigente" styleClass="boton_radio"></html:checkbox>
  						<label for="actualizarVigente" class="alineada"><bean:message key="estructuras.tesauros.modi.actualizar"/></label>
  					</div>
					<div class="linea_separadora"></div>
					<br class="oculto">
				</div>
			</div>
			<!--  FIN CAJA DE FORMULARIO   -->

			</div>
		</div>
	</div>
</div>

<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->
</c:if>

	<logic:iterate id="idioma" name="form" property="idiomasIso" indexId="index">
	

<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<c:set var="nombre" value=""/>
<logic:iterate id="fichero" name="form" property="listaFicheros">
	<c:set var="terminacion" value="${idioma}.xml"/>
	<c:if test="${fn:endsWith(fichero, terminacion)}">
		<c:set var="nombre" value="${fichero}"/>
	</c:if>
</logic:iterate>
	
<div class="globo_gris uno_b" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
				<div class="caja_dinamica">
					<a class="caja_abierta" id="p${idioma}" href="#" onclick="expandirCaja('${idioma}', '<bean:message key="estructuras.vermas"/>', '<bean:message key="estructuras.ocultar"/>');return false;" onkeypress="expandirCaja('${idioma}', '<bean:message key="estructuras.vermas"/>', '<bean:message key="estructuras.ocultar"/>');return false;">
						<br class="falso" /><strong id="d${idioma}" ><bean:message key="estructuras.ocultar"/></strong>
						</a>
					<flags:banderas banderas="${idioma}" action="actionGloboEstructurasEducativas"/></h3>
				</div>
<div id="${idioma}" class="caja_abierta"  >
<br />
<!--  INICIO CAJA DE FORMULARIO   -->
			<div class="formu" >
<c:if test="${nombre!=''}">
  				<div class="fila_de_tabla">
  					<div class="contenedor_izquierda">
  						<div class="text"><label for="Seleccione"><bean:message key="estructuras.tesauros.modi.actual"/></label></div>
 					</div>
					<div class="contenedor_derecha">
						<div class="text"><span class="sp_titulo">${nombre}</span></div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>
</c:if>
				<!--    -->
				<div class="fila_de_tabla">
  					<div class="contenedor_izquierda">
  						<div class="text"><label for="fichero"><bean:message key="estructuras.tesauros.modi.nuevo"/></label></div>
 					</div>
					<div class="contenedor_derecha">
						<div class="text">
							<input  name="fichero${index}"
									onfocus="limpiarTexto(this)"  
									value="&nbsp;"  
									onblur="this.style.backgroundColor='#e1e1e1'" 
									id="fichero${index}" 
									type="file" 
									style="width: 70%; background-color: rgb(225, 225, 225);"
									class="nombreGrupo" 
									title="<bean:message key="estructuras.tesauros.seleccione"/>"/>
						</div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />

				</div>
				</div>
				<!--  FIN CAJA DE FORMULARIO   -->
</div>
			</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->
	
	
	</logic:iterate>
	
	


<!-- Inicio Botones  -->
<!-- Inicio Botones  -->
<input class="boton_125_de_2_izq"   type="submit" name="action"  value="<bean:message key="estructuras.cancelar"/>" />
<input class="boton_125_de_2"   type="submit" name="action"  value="<bean:message key="estructuras.aceptar"/>" />
<!-- Fin Botones  -->
<!-- Fin Botones  --> 

</form>


</div><!-- plantilla contenido -->
</tiles:put>
</tiles:insert>