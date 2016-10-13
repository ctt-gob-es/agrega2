<%@ include file="/taglib-imports.jspf"%>
<%@ taglib uri="/WEB-INF/tags/logos.tld" prefix="logos" %>
<html:xhtml/>
<!-- INICIO PIE DE PAGINA -->
<!-- INICIO PIE DE PAGINA -->
<!-- <div id="pie_pagina"> -->
<!-- <hr /> -->
<!--<a class="oculto" href="/${initParam.url_informacionLegal}"  id="info" title="<bean:message key="pie.informacionLegal"/>" ><span><bean:message key="pie.informacionLegal"/></span></a> -->
<!--<a class="oculto" href="/${initParam.url_politicaPrivacidad }" id="politica" title="<bean:message key="pie.politicaPrivacidad"/>"><span><bean:message key="pie.politicaPrivacidad"/></span></a> <br class="oculto" />-->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- INCLUDE DE LOS LOGOS DE LOS COLABORADORES Y PATROCINADORES  -->
<!-- Aqui iria el logos:renderLogoSet .... -->
<!-- FIN DEL INCLUDE DE LOS LOGOS DE LOS COLABORADORES Y PATROCINADORES  -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->

<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->

<!-- </div> -->




<!-- ASIDE -->
<aside id="patros_con_ccaa">
<logos:renderLogoSet htmlFile="uploads/logos/logos-pie.html"/>
</aside>
<!-- FIN ASIDE -->

<!-- FOOTER -->
<footer id="pie_pagina">
	<ul>
		<li><a href="/${initParam.url_politicaPrivacidad}" id="politica" title="<bean:message key="pie.politicaPrivacidad"/>"><span><bean:message key="pie.politicaPrivacidad"/></span></a></li>
		<li><a href="/${initParam.url_informacionLegal}" id="info_legal" title="<bean:message key="pie.informacionLegal"/>" ><span><bean:message key="pie.informacionLegal"/></span></a></li>
		<li><a href="/${initParam.url_accesibilidad}" id="accesibilidad" title="<bean:message key="metanavegacion.accesibilidad"/>" id="registro"><bean:message key="metanavegacion.accesibilidad"/></a></li>
		<li><a href="<rewrite:rewrite url="${initParam.url_contacto}"/>" id="contacto" title="<bean:message key="metanavegacion.contacto"/>" id="contacto"><bean:message key="metanavegacion.contacto"/></a></li>
		<%@ include file="/layout/pie-ayuda.jsp"%>
		<li><a href="<rewrite:rewrite url="${initParam.url_mapa}"/>" title="<bean:message key="metanavegacion.mapa"/>"><bean:message key="metanavegacion.mapa"/></a></li>
		<li><a href="/visualizadorcontenidos2/AgregadorFeedsCU/AgregadorFeedsCU.do" title="RSS">RSS</a></li>
	</ul>
</footer>
<!-- FIN FOOTER -->


