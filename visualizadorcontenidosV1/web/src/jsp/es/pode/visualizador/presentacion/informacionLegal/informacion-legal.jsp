<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<tiles:insert definition="layout-usuario">

<tiles:put name="title" type="string">
	<bean:message key="title.Comun"/>
</tiles:put>

    <tiles:put name="style" type="string">
        <link rel="stylesheet" type="text/css" media="screen" href="<html:rewrite page="/es/pode/visualizador/presentacion/informacionLegal/informacion-legal.css"/>"></link>
    </tiles:put>

    
    <tiles:put name="body" type="string">
    <%@ include file="/es/pode/visualizador/presentacion/informacionLegal/informacion-legal-javascript.jspf" %>

    <div class="plantilla_contenido">

<h2><bean:message key="visualizador.informacionLegal.titulo"/></h2>

<div id="contenido_noticia">
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
								
</div>

</div>

    </tiles:put>

</tiles:insert>
