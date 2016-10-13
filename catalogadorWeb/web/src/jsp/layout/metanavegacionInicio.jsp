<%@ include file="/taglib-imports.jspf" %>
<%@ taglib uri="/WEB-INF/tags/link.tld" prefix="link" %>

<!-- INICIO METANAVEGACION   metanavegacionInicio.jsp -->
<div id="metanavegacion">
<ol>
		<li class="oculto"><a href="#contenido_central" title="Contenido Principal"><strong>Contenido</strong></a></li>
		
		<li>
			<link:link attrProperty="HELP_URL" title="${ayuda }" id="li_inicial" target="blank" i18nMessage="catalogadorBasico.Ayuda"/>
		</li>
		<li id="titulo_empa"><span><bean:message key="catalogadorBasico.Catalogador"/></span></li> 
	<script>
		//document.write('<li><a href="#" onclick="document.forms[0].submit();return false;" id="contacto"><bean:message key="catalogadorBasico.Exportar" /></a></li>');
	//-->
	</script>
</ol>
</div>
<!-- FIN METANAVEGACION   -->