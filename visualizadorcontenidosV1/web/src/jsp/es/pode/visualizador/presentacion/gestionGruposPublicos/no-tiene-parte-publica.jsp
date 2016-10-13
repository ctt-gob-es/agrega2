<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html:xhtml/> 
<tiles:insert definition="layout-gestor-flujo-con-style">
<tiles:put name="title" type="string">
	<bean:message key="title.Comun"/>
</tiles:put>



<tiles:put name="body" type="string">




<div class="plantilla_contenido">

<%@ include file="/taglib-imports.jspf" %>
<%@ include file="/es/pode/visualizador/presentacion/gestionGruposPublicos/no-tiene-parte-publica-javascript.jspf" %>
<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />


<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->

			<div id="formulario" class="ali_c">
			<br />
			 <p><em class="corrector"><bean:message key="texto.no.parte.publica.usuario" /> <a href="<html:rewrite action="/ModificarPerfil/ModificarPerfil.do"/>"><bean:message key="texto.no.parte.publica.aqui" />.</a></em></p>
				<br />
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

