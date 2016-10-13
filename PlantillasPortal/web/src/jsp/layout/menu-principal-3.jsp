
<%@ taglib prefix="agrega" uri="/WEB-INF/tags/agregaProperties.tld" %>
<%@ include file="/taglib-imports.jspf" %>

<nav id="menu">
	<h1><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos2/Portada/Portada.do" title="Agrega"><strong>Agrega</strong></a></h1>
	<div id="nav_principal">
		<ul class="metanavegacion">
			<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos2/Portada/Portada.do"><bean:message key="menu.cabecera.buscador.agrega"/></a></li>
			<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos2/BusquedaGoogleCU/BusquedaGoogleCU.do"><bean:message key="menu.cabecera.buscador.externo"/></a></li>
			<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/buscador2/NavegarArbolCurricularCU/NavegarArbolCurricularCU.do" id="activo"><bean:message key="menu.cabecera.arbolcuricular"/></a></li>	
		</ul>
	</div>
</nav>