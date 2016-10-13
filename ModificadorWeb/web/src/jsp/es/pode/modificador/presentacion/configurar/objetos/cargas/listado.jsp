<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<tiles:insert definition="${sessionScope.offline == true ? 'layout-offline' : 'layout-administrador'}">

<tiles:put name="title" type="string">
      <bean:message key="title.Comun"/>
</tiles:put>

<tiles:put name="body" type="string">

<!-- Inicio plantilla contenido  -->
<div class="plantilla_contenido">
<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />
<h2><bean:message key="configurarObjeto.anyadirRuta"/></h2>
<p class="parrafo_comun" id="separacion2"><bean:message key="configurarObjeto.seleccione"/></p>
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario" >
				<form method="post" action="<html:rewrite action="/ListadoCargas/ListadoCancelar"/>" id="listarOdesCargaSubmitForm">

	<!-- ******************************      CAJA TABLA     ************************************ -->
					<div class="caja_tabla bordeada" id="ficha_dentro_gris">
		
						<display:table name="${form.cargas}" requestURI=""
						export="false" id="carga" class="administracion_tareas" 
						style="border:1;width:100%;" cellpadding="0" 
						cellspacing="0" sort="list" pagesize="8">
				
							<display:setProperty name="css.tr.odd" value="tr_gris"/>
							<display:setProperty name="css.tr.even" value="tr_blanco"/>
							<display:setProperty name="basic.show.header" value="false"/>	
	
	<!--  ******************** COLUMNA QUE MUESTRA LA CARGA **********************-->						
							<display:column style="valign:top;" class="" >		
	                				<div align="left"><html:link action="/ListadoCargas/ListadoSeleccionar?id=${carga.id}">${carga.descripcionTarea}</html:link></div>
	            			</display:column>	
	 					</display:table>
	 				</div>
	 			</div>
	 		</div>
	 	</div>
	 </div>
</div>
<!-- Inicio Botones  -->
<fieldset class="tipo ft_centrada">

<input class="boton_125_de_2_izq"  name="action" type="submit"  value="<bean:message key="comun.cancelar"/>" />

</fieldset>
<!-- Fin Botones  -->
</form>
</div>

</tiles:put>
</tiles:insert>