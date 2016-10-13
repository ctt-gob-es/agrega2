<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<tiles:insert definition="${sessionScope.offline == true ? 'layout-offline' : 'layout-administrador'}">
<tiles:put name="title" type="string">
      <bean:message key="title.Comun"/>
</tiles:put>
<tiles:put name="body" type="string">

   <div class="plantilla_contenido">
<%@ include file="/layout/messages.jsp" %>

<h2><bean:message key="ErrorModificador.errorGeneral"/></h2>

<%
   // Set error code and reason.
   response.setStatus(500);
%>
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario" class="ali_c">
			<br /><br />

			 <p><bean:message key="ErrorModificador.seHaProducidoUnError"/> <a href="#"><bean:message key="ErrorModificador.contacteConAdministrador"/></a>.</p>
				<br /><br />
				</div>
				<!--  FIN CAJA DE FORMULARIO   -->
			</div>
		</div>
	</div>

</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->


</div>
</tiles:put>
</tiles:insert>