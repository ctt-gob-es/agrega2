<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<tiles:insert definition="layout-menu-0">

	<!-- TODO mjb. no se con cual de estos dos titulos quedarme... -->

	<tiles:put name="title" type="string">
	    <bean:message key="title.Comun"/>
	</tiles:put>
	
	<tiles:put name="body-principal" type="string">
		 <%@ include file="/es/pode/visualizador/presentacion/accesibilidad/accesibilidad-javascript.jspf" %>
		<div class="col_der">
			<section >
				<header>
					<h2><bean:message key="visualizador.accesibilidad.titulo"/></h2>
				</header>
				<p>
					<bean:message key="visualizador.accesibilidad.web"/><strong><bean:message key="visualizador.accesibilidad.red"/></strong> <bean:message key="visualizador.accesibilidad.texto1"/><br /><br />
					<bean:message key="visualizador.accesibilidad.texto2"/><br /><br /> 
				</p>
			</section>
		</div>
	</tiles:put>
	
</tiles:insert>
