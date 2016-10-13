<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<tiles:insert definition="layout-menu-0">

	<!-- TODO mjb. no se con cual de estos dos titulos quedarme... -->

	<tiles:put name="title" type="string">
	    <bean:message key="title.Comun"/>
	</tiles:put>
	
	<tiles:put name="body-principal" type="string">
		<%@ include file="/es/pode/visualizador/presentacion/politicaPrivacidad/politica-privacidad-javascript.jspf" %>
		<div class="col_der">
			<section >
				<header>
					<h2><bean:message key="visualizador.politica.titulo"/></h2>
				</header>
				<p><br/><br/></p>
				<p><strong><bean:message key="visualizador.politica.texto1"/></strong></p>
				<p><bean:message key="visualizador.politica.texto2"/></p>
				<p><bean:message key="visualizador.politica.texto3"/></p>
				<p><bean:message key="visualizador.politica.texto4"/></p>
				<p><bean:message key="visualizador.politica.texto5"/></p>
				<p><br/><br/></p>
				<p><strong><bean:message key="visualizador.politica.texto6"/></strong></p>
				<p><bean:message key="visualizador.politica.texto7"/> <b><bean:message key="visualizador.politica.texto6"/></b> <bean:message key="visualizador.politica.texto8"/></p>
				<p><bean:message key="visualizador.politica.texto9"/> <b><bean:message key="visualizador.politica.texto6"/></b> <bean:message key="visualizador.politica.texto10"/> <b><bean:message key="visualizador.politica.texto6"/></b> <bean:message key="visualizador.politica.texto11"/></p>
			</section>
		</div>
	</tiles:put>
	
</tiles:insert>
