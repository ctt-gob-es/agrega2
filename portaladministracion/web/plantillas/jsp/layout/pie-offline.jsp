<%@ include file="/taglib-imports.jspf"%>
<%@ taglib uri="/WEB-INF/tags/logos.tld" prefix="logos" %>
<html:xhtml/>
<!-- INICIO PIE DE PAGINA -->
<!-- INICIO PIE DE PAGINA -->
<div id="pie_pagina">
<hr />
<a class="oculto" href="/${initParam.url_informacionLegal}"  id="info" title="<bean:message key="pie.informacionLegal"/>" ><span><bean:message key="pie.informacionLegal"/></span></a> 
<a class="oculto" href="/${initParam.url_politicaPrivacidad }" id="politica" title="<bean:message key="pie.politicaPrivacidad"/>"><span><bean:message key="pie.politicaPrivacidad"/></span></a> <br class="oculto" />
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- INCLUDE DE LOS LOGOS DE LOS COLABORADORES Y PATROCINADORES  -->
<logos:renderLogoSet htmlFile="uploads/logos/logos-pie-agrega-1.html"/>
<!-- FIN DEL INCLUDE DE LOS LOGOS DE LOS COLABORADORES Y PATROCINADORES  -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->

<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
</div>
