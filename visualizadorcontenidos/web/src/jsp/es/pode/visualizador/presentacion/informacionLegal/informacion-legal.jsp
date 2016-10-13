<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib uri="/WEB-INF/tags/serverProperties.tld" prefix="server" %>


<tiles:insert definition="layout-menu-0">

	<tiles:put name="title" type="string">
        <bean:message key="title.Comun"/>
    </tiles:put>
	
	<tiles:put name="body-principal" type="string">
		<%@ include file="/es/pode/visualizador/presentacion/informacionLegal/informacion-legal-javascript.jspf" %>
		<div class="col_der">
			<section >
				<header>
					<h2><bean:message key="visualizador.informacionLegal.titulo"/></h2>
				</header>
				<p><br/><br/></p>
				<p><strong><bean:message key="visualizador.informacionLegal.texto1"/></strong></p>
				<p><bean:message key="visualizador.informacionLegal.texto2"/>
				<bean:message key="visualizador.informacionLegal.texto3"/></p>
				<p><br/><br/></p>
				<p><strong><bean:message key="visualizador.informacionLegal.texto4"/></strong></p>
				<p><bean:message key="visualizador.informacionLegal.texto5"/>
				<bean:message key="visualizador.informacionLegal.texto6"/>
				<bean:message key="visualizador.informacionLegal.texto7"/>
				<bean:message key="visualizador.informacionLegal.texto8"/>
				<bean:message key="visualizador.informacionLegal.texto9"/></p>
				<p><br/><br/></p>
				<p><strong><bean:message key="visualizador.informacionLegal.texto10"/></strong></p>
				<p><bean:message key="visualizador.informacionLegal.texto11"/>
				<bean:message key="visualizador.informacionLegal.texto12"/>
				<bean:message key="visualizador.informacionLegal.texto13"/>
				<bean:message key="visualizador.informacionLegal.texto14"/></p>
				<p><br/><br/></p>
				<p><strong><bean:message key="visualizador.informacionLegal.cookies"/></strong></p>
				<p><bean:message key="visualizador.informacionLegal.texto15"/><b><bean:message key="visualizador.informacionLegal.cookies"/></b> <bean:message key="visualizador.informacionLegal.texto16"/>
				<bean:message key="visualizador.informacionLegal.las"/> <b><bean:message key="visualizador.informacionLegal.cookies"/></b> <bean:message key="visualizador.informacionLegal.texto17"/><b><bean:message key="visualizador.informacionLegal.cookies"/></b> <bean:message key="visualizador.informacionLegal.texto18"/></p>
				<p><br/><br/></p>
				<p><strong><bean:message key="visualizador.informacionLegal.modificaciones"/></strong></p>
				<p><bean:message key="visualizador.informacionLegal.texto19"/>
				<bean:message key="visualizador.informacionLegal.texto20"/>
				<bean:message key="visualizador.informacionLegal.texto21"/>
				<bean:message key="visualizador.informacionLegal.texto22"/></p>
				<p><br/><br/></p>
				<p><strong><bean:message key="visualizador.informacionLegal.hiperenlaces"/></strong></p>
				<p><bean:message key="visualizador.informacionLegal.texto23"/></p>
			</section>
		</div>
	</tiles:put>

</tiles:insert>
<!-- Aqui van javascripts propios  -->
<script type='text/javascript' src="<rewrite:rewrite url="static/Agrega2/js/codigo.js"/>"></script>
<!-- Aqui van javascripts propios  -->