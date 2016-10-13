<%@ include file="/taglib-imports.jspf"%>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib uri="/WEB-INF/tags/logos.tld" prefix="logos" %>

<!-- INICIO PIE DE PAGINA -->
<!-- INICIO PIE DE PAGINA -->
<div id="pie_pagina">
<hr />
<a href="<rewrite:rewrite url="${initParam.url_informacionLegal}"/>"  id="info" title="<bean:message key="pie.informacionLegal"/>" ><span><bean:message key="pie.informacionLegal"/></span></a> 
<a href="<rewrite:rewrite url="${initParam.url_politicaPrivacidad }"/>" id="politica" title="<bean:message key="pie.politicaPrivacidad"/>"><span><bean:message key="pie.politicaPrivacidad"/></span></a> <br class="oculto" />
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- INCLUDE DE LOS LOGOS DE LOS COLABORADORES Y PATROCINADORES  -->
<div id="patros_con_ccaa">
<logos:renderLogoSet htmlFile="uploads/logos/logos-pie_actualizado.html"/>
</div>
<!-- FIN DEL INCLUDE DE LOS LOGOS DE LOS COLABORADORES Y PATROCINADORES  -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->

<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->

</div>
