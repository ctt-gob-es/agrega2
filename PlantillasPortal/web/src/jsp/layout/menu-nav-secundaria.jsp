
<%@ taglib prefix="agrega" uri="/WEB-INF/tags/agregaProperties.tld" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<div class="col_izq">
	<nav>
		<ul id="nav_secundaria">
			<!-- <li ><a href="/visualizadorcontenidos/Noticias/Noticias.do" id="current">Noticias</a></li>
			<li><a href="/visualizadorcontenidos/Descargas/Descargas.do">Descargas</a></li>
			<li><a href="/visualizadorcontenidos/EstadisticasPortada/EstadisticasPortada.do">Estad&iacute;sticas</a></li>
			<li id="seleccionado"><a href="/visualizadorcontenidos/Utilidades/Utilidades.do">Agrega en tu web</a></li>
			<li><a href="/visualizadorcontenidos/Faqs/Faqs.do">Preguntas frecuentes</a></li>
			 -->
			<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos2/Noticias/Noticias.do"><bean:message key='menu.lateral.generico.noticias'/></a></li>
			<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos2/Descargas/Descargas.do"><bean:message key='menu.lateral.generico.descargas'/></a></li>
			<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos2/EstadisticasPortada/EstadisticasPortada.do"><bean:message key='menu.lateral.generico.estadisticas'/></a></li>
			<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos2/Utilidades/Utilidades.do"><bean:message key='menu.lateral.generico.utilidades'/></a></li>
			<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos2/Faqs/Faqs.do"><bean:message key='menu.lateral.generico.FAQs'/></a></li>
		</ul>
		<strong class="oculto">Destacados:</strong>
		<ul class="listadebanners_peq"  id="sec_clientes">
			<!-- <li><a href="/visualizadorcontenidos/Descargas/Descargas.do" class="dest_descargas"  title="DESCARGAS"><span>DESCARGAS</span></a></li>-->
			<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos2/Descargas/Descargas.do" class="dest_descargas" title="<bean:message key='menu.lateral.generico.descargasMay'/>"><span><bean:message key='menu.lateral.generico.descargasMay'/></span></a></li>
			<!--<li><a href="#" class="dest_comunidad"  title="COMUNIDAD"><span>COMUNIDAD</span></a></li>-->
		</ul>
	</nav>
</div>