<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="agrega" uri="/WEB-INF/tags/agregaProperties.tld" %>

<tiles:insert definition="layout-menu-0">

	<tiles:put name="title" type="string">
	    <bean:message key="title.Comun"/>
	</tiles:put>
	
	<tiles:put name="body-principal" type="string">
		<%@ include file="/es/pode/visualizador/presentacion/politicaPrivacidad/politica-privacidad-javascript.jspf" %>
		<div class="col_der">
			<section>
				<header>
					<h2><bean:message key="visualizador.contacto.titulo"/></h2>
				</header>
				<br />
				<p>
					<span class="oculto" >
						-
					</span>
					<span id="cont_tel">
						<bean:message key="visualizador.contacto.telefono"/><agrega:agregaProperties property="contacto_telefono"/>
					</span>
				</p>
				<br />
				<br />
				<p>
					<span class="oculto" >
						-
					</span>
					<a href="mailto:<agrega:agregaProperties property="contacto_mail"/>" id="cont_mail">
						<bean:message key="visualizador.contacto.mail"/>
					</a>
				</p>
				<br />
			</section>
			
			<bean:define id="activarIncidencias"><agrega:agregaProperties property="contacto_incidencias_activar"/></bean:define>
			<c:if test="${activarIncidencias}">
				<br />
				<p>
					<span class="oculto" >
						-
					</span>
					<a href="/incidencias" id="cont_mantis">
						<bean:message key="visualizador.contacto.herramienta"/>
					</a>
				</p>
				<br />
			</c:if>
				
		</div>
	</tiles:put>
	
</tiles:insert>
