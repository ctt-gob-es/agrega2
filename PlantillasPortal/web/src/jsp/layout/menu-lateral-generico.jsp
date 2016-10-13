<%@ include file="/taglib-imports.jspf"%>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<html:xhtml/>
<!-- INICIO LATERAL IZQUIERDO  -->


<!-- INICIO NAVEGACIÓN VERTICAL NUEVO -->
<!-- INICIO NAVEGACIÓN VERTICAL -->
<div id="menu_vertical_02" >

<!--
<br class="oculto" />
<strong class="oculto"><bean:message key="menu.lateral.generico.menuVertical" /></strong>
<ul>
<tiles:importAttribute name="MENU_LATERAL" scope="page"/>
<logic:iterate name="MENU_LATERAL" id="menuItem">
	<li>
		<logic:equal name="menuItem" property="selected" value="false">
			<a href="<bean:write name="menuItem" property="link"/>" <logic:notEqual name="menuItem" property="target" value="">target="<bean:write name="menuItem" property="target"/>"</logic:notEqual>>
		</logic:equal>
		<logic:equal name="menuItem" property="selected" value="true">
			<div>
		</logic:equal>
			<span><bean:write name="menuItem" property="i18nKey"/></span>
		<logic:equal name="menuItem" property="selected" value="false">
			</a>
		</logic:equal>
		<logic:equal name="menuItem" property="selected" value="true">
			</div>
		</logic:equal>
	</li>
</logic:iterate>
</ul>
-->
</div>

<!-- FIN NAVEGACIÓN VERTICAL -->
<!-- FIN NAVEGACIÓN VERTICAL -->

<!-- <logic:notEqual name="RSS" value="0">-->
<!--
<tiles:importAttribute name="RSS" scope="page"/>
<c:if test="${!empty RSS}">
<strong class="oculto"><bean:message key="menu.lateral.generico.rssFeeds" /></strong>
<ul class="destacados">
<li><a href="<rewrite:rewrite url="${initParam.url_feeds}"/>" class="des_01" title="<bean:message key="menu.lateral.generico.rssFeeds" />"><span><bean:message key="menu.lateral.generico.rssFeeds" /></span></a></li>
</ul>
</c:if>
-->
<!-- </logic:notEqual>-->


<!-- NUBE TAGS -->
<tiles:importAttribute name="NUBE_TAGS" scope="page"/>
<logic:notEmpty name="NUBE_TAGS">
<!--
<div class="nube_destacada_blanca">
	<div class="nub_d etiquetas">
		<h3><bean:message key="menu.lateral.generico.etiquetas" /></h3>
		<ul>
			
			<logic:iterate name="NUBE_TAGS" id="palabrasClave">
				<li><a title="<bean:write name="palabrasClave" property="palabraClave"/> <bean:write name="palabrasClave" property="ranking"/>" href="<rewrite:rewrite url="buscador/ListarODECU/ListarODECU.do"/>?keyword=<bean:write name="palabrasClave" property="palabraClave"/>&tipoBusqueda=03" class="t<bean:write name="palabrasClave" property="tamanio"/>"><bean:write name="palabrasClave" property="palabraClave"/></a></li>
			</logic:iterate>
			<li id="enlace_especial"><a href="<rewrite:rewrite url="visualizadorcontenidos/ListarNubeTagsCU/ListarNubeTagsCU.do"/>" title="<bean:message key="menu.lateral.generico.nubeTags.verMas"/>"><span><bean:message key="menu.lateral.generico.nubeTags.verMas" /></span></a><br class="limpiar" /></li>
		</ul>
	</div>	
	</div>
-->

    <div class="col_der">	
    <section class="bloque etiquetas">
     	<header>
    		<h2 class="etiquetas" title="<bean:message key="menu.lateral.generico.palabrasClave" />"><bean:message key="menu.lateral.generico.palabrasClave" /></h2>
   	 	</header>
   	 	<p class="clearfix">
		<logic:iterate name="NUBE_TAGS" id="palabrasClave">
			<!-- Esto lazaria una busqueda por keyword -->
			<!--
			<a title="<bean:write name="palabrasClave" property="palabraClave"/> <bean:write name="palabrasClave" property="ranking"/>" href="<rewrite:rewrite url="buscador/ListarODECU/ListarODECU.do"/>?keyword=<bean:write name="palabrasClave" property="palabraClave"/>&tipoBusqueda=03" class="t<bean:write name="palabrasClave" property="tamanio"/>"><bean:write name="palabrasClave" property="palabraClave"/></a>
			-->
			
			<!-- Esto lanza una busqueda normal -->
			<a title="<bean:write name="palabrasClave" property="palabraClave"/> <bean:write name="palabrasClave" property="ranking"/>" href="<rewrite:rewrite url="buscador2/ListarODECU/ListarODECU.do"/>?buscar=Buscar&buscadorGeneral=<bean:write name="palabrasClave" property="palabraClave"/>&tipoBusqueda=02" class="t<bean:write name="palabrasClave" property="tamanio"/>"><bean:write name="palabrasClave" property="palabraClave"/></a>
		</logic:iterate>
		</p>
	</section>
    </div>

</logic:notEmpty>
<!-- FIN NUBE TAGS -->
<!--
<hr/>
-->

<!-- FIN LATERAL IZQUIERDO -->
<!-- FIN LATERAL IZQUIERDO -->