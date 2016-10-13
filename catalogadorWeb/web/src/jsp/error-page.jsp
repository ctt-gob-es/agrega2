<%@ include file="/taglib-imports.jspf" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ page session="true" isErrorPage="true" %>
<%@ taglib uri="/WEB-INF/tlds/tags-catalogador.tld" prefix="cat" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>


<link rel="stylesheet" media="screen" href="<rewrite:rewrite url="static/css/red.css"/>" type="text/css" />
<script type="text/javascript" src="<rewrite:rewrite url="static/js/plantilla.js"/>"></script>

<html>
<title>
      <bean:message key="title.Comun"/>
</title>
<!-- 		INICIO DEL BODY		  -->
<body>
<!--		Inicio plantilla contenido		-->
<div class="plantilla_contenido">
<h2><bean:message key="error.${param.code}.title"/></h2>
<!--		Inicio del formulario principal		-->

<cat:redirige_error/>

<!--		Fin del Formulario principal		-->
</div>
<!--		Fin de la capa plantilla_contenido		-->
</body>
<!--		FIN DEL BODY		-->
</html>