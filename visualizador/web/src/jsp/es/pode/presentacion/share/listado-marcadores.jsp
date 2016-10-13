<%@ include file="/taglib-imports.jspf" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<tiles:insert definition="layout-principal">



<tiles:put name="title" type="string">
	<bean:message key="title.Comun"/> - ${fn:escapeXml(visualizadorSession.tituloOde)}
</tiles:put>



<tiles:put name="body" type="string">
<!-- CONTENIDO -->
					
<!-- Panel contenidos!! -->
<div id="capa_contenidos" name="capa_contenidos">
<iframe name="contenido" id="contenido" frameborder="0" scrolling="auto" style="min-width: 520px; min-height: 440px; height: 107px; width: 494px;" marginwidth="0" marginheight="0" src="http://www.addthis.com/bookmark.php?v=20&amp;url=${form.url}" onload="redim();" >
[Su agente de usuario no soporta marcos o está actualmente configurado para no mostrar marcos. Sin embargo, puede visitar <A href='http://www.addthis.com/bookmark.php?v=20&amp;url=${form.url}'>Documento relacionado.</A>]
</iframe>
<hr/>
</div>
	<script type="text/javascript">
	//<![CDATA[
		$("#contenido_central_largo").contenido({
			ancho_menu:430,
			inicio_menu_desplegado:'false',
			boton_menu:'#enlace_menu',
			contenedor_menu:'#panel_menu',
			contenedor_frame:'#contenido'
			}
		);
	//]]>
	</script>


</tiles:put>
</tiles:insert>
