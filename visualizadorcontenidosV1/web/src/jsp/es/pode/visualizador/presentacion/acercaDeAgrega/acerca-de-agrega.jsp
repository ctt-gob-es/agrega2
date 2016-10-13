<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib prefix="agrega" uri="/WEB-INF/tags/agregaProperties.tld" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib uri="/WEB-INF/tags/googleAnalytic.tld" prefix="analytic" %>


<tiles:insert definition="layout-usuario">
<tiles:put name="title" type="string">
	<bean:message key="portada.title"/>
</tiles:put>

    <tiles:put name="title" type="string">
        <bean:message key="title.Comun"/>
    </tiles:put>

    <tiles:put name="style" type="string">
      <link rel="stylesheet" type="text/css" media="screen" href="<html:rewrite page="/es/pode/visualizador/presentacion/acercaDeAgrega/acerca-de-agrega.css"/>"></link>
    </tiles:put>

    
    <tiles:put name="body" type="string">
     <%@ include file="/es/pode/visualizador/presentacion/acercaDeAgrega/acerca-de-agrega-javascript.jspf" %>

    <div class="plantilla_contenido">
    
    <analytic:googleAnalytic />

		<bean:define id="changeLog"><agrega:agregaProperties property="pathChangeLog"/></bean:define>
		<h2>
			<bean:message key="visualizador.acercaDeAgrega.titulo"/> 
			<!--a href="<rewrite:rewrite url="${changeLog}"/>"><agrega:agregaProperties property="version"/></a-->
			<agrega:agregaProperties property="version"/>
		</h2>
		
		
		<div id="contenido_noticia">
				<!-- 
				<p>	
					<bean:message key="visualizador.acercaDeAgrega.texto1"/>
					<b><bean:message key="visualizador.acercaDeAgrega.texto2"/></b>
					<bean:message key="visualizador.acercaDeAgrega.texto3"/>
					<b><bean:message key="visualizador.acercaDeAgrega.texto4"/></b>
				</p>
				-->
				<p><br/></p>
				
				<p>	
					<bean:message key="visualizador.acercaDeAgrega.texto5"/>
					<b><bean:message key="visualizador.acercaDeAgrega.texto6"/></b>
					<bean:message key="visualizador.acercaDeAgrega.texto7"/>
				</p>
			
			
			<!--
			<p>	
				<bean:message key="visualizador.acercaDeAgrega.texto1"/>
				<b><bean:message key="visualizador.acercaDeAgrega.texto2"/></b>
			</p>
			
			<p><br/></p>
			
			<p>	
				<bean:message key="visualizador.acercaDeAgrega.texto3"/>
				<b><bean:message key="visualizador.acercaDeAgrega.texto4"/></b>
				<bean:message key="visualizador.acercaDeAgrega.texto5"/>
			</p>
			
			<p><br/></p>
			
			<p>	
				<b><bean:message key="visualizador.acercaDeAgrega.texto6"/></b>
				<bean:message key="visualizador.acercaDeAgrega.texto7"/>
				<b><bean:message key="visualizador.acercaDeAgrega.texto8"/></b>
				<bean:message key="visualizador.acercaDeAgrega.texto9"/>
			</p>
			
			<p><br/></p>
			
			<p>	
				<bean:message key="visualizador.acercaDeAgrega.texto10"/>
				<b><bean:message key="visualizador.acercaDeAgrega.texto6"/></b>
				<bean:message key="visualizador.acercaDeAgrega.texto11"/>
				<b><bean:message key="visualizador.acercaDeAgrega.texto12"/></b>
				<bean:message key="visualizador.acercaDeAgrega.texto13"/>
			</p>
			
			<p><br/></p>
			
			<p>	
				<bean:message key="visualizador.acercaDeAgrega.texto14"/>
				<b><bean:message key="visualizador.acercaDeAgrega.texto6"/></b>
				<bean:message key="visualizador.acercaDeAgrega.texto15"/>
			</p>
			
			<p><br/></p>
			
			<p>	
				<bean:message key="visualizador.acercaDeAgrega.texto16"/>
				<b><bean:message key="visualizador.acercaDeAgrega.texto6"/></b>
				<bean:message key="visualizador.acercaDeAgrega.texto17"/>
			
				<a 	href='<bean:message key="visualizador.acercaDeAgrega.hipervinculo"/>' title='<bean:message key="visualizador.acercaDeAgrega.hipervinculoTexto"/>' >
					<bean:message key="visualizador.acercaDeAgrega.hipervinculoTexto"/>
				</a>
				
				<bean:message key="visualizador.acercaDeAgrega.texto18"/>
			</p>
			
			<p><br/></p>
			-->
		</div>

	</div>

    </tiles:put>

</tiles:insert>
