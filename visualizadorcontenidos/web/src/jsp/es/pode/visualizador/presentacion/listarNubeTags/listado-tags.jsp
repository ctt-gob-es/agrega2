<!-- *****************************************************************************************************
*********************************       listado-tag.jsp    ** ***********************************
******************************************************************************************************-->
  
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib uri="/WEB-INF/tags/listadoNubeTag.tld" prefix="listadoNubeTag" %>
<%@ include file="/taglib-imports.jspf" %>
<%@ taglib uri="/WEB-INF/tags/googleAnalytic.tld" prefix="analytic" %>


<tiles:insert definition="layout-usuario">


<tiles:put name="title" type="string">
      <bean:message key="title.Comun"/>
</tiles:put>

<tiles:put name="body" type="string">

<!-- *****************************   Inicio  Plantilla contenido      ********************************** -->
<div class="plantilla_contenido">
<analytic:googleAnalytic />

<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />

			<div></div>

			<!-- ******************************      Inicio del formulario    ************************************ -->
			<!-- <form id="" action="" method="post" enctype="multipart/form-data">-->
			<form action="" method="post">
				<h2><bean:message key="listarNubeTags.palabrasClave"/></h2>
				<bean:define id="url" >
						<rewrite:rewrite url="${initParam.url_buscador}"/>
				</bean:define>
				<listadoNubeTag:listadoNubeTag tags="${form.tagsAsArray}" url="${url}"/>
			</form>
	
</div>
</tiles:put>
</tiles:insert>