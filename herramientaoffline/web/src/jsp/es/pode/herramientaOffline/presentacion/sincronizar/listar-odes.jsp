<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<html:xhtml/>
<tiles:insert definition="layout-offline">

<tiles:put name="title" type="string">
      <bean:message key="title.Comun"/>
</tiles:put>

<tiles:put name="body" type="string">

<%@ include file="/taglib-imports.jspf" %>

<!-- *****************************   Inicio  Plantilla contenido      ********************************** -->
<div class="plantilla_contenido">
<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />

<!-- Inicio plantilla contenido  -->
<div class="interno_ficha">
<div class="plantilla_contenido_pestanias">
<form method="post" id="sincronizarListarODEsSubmitForm" action="<html:rewrite action="/Sincronizar/ListarODEsSubmit"/>"  enctype="multipart/form-data">

<h2><bean:message key="sincronizarOdes.titulo" /></h2>


<div class="caja_tabla" >
	
<display:table name="${form.odes}" requestURI=""
					export="false" id="fila" class="administracion_tareas" 
					style="width:100%;" cellpadding="0" cellspacing="0" 
					summary="${sincronizarOdes.tabla.summary}" sort="list" pagesize="8"
					decorator="es.pode.herramientaOffline.presentacion.MarcarModificado">
						<display:setProperty name="css.tr.odd" value="tr_gris"/>
						<display:setProperty name="css.tr.even" value="tr_blanco"/>
						<display:setProperty name="basic.show.header" value="false"/>
						
						<!--  ******************** COLUMNA DE CHECKBOX ***********************-->
					    
						<display:column style="valign:top;" class="sin_b">  
						
							<input type="checkbox" name="identificadorRowSelectionAsArray" value="${fila.idODE}" title='<bean:message key="sincronizarOdes.seleccione"/>'/>
						</display:column>
						
						<!--  ******************** COLUMNA QUE MUESTRA LA DESCARGA  ********************-->
						
						<display:column style="valign:top;" class="tar4">							
                				${fila.titulo}
            			</display:column>
            			
            			
</display:table>


<!-- Inicio Botones  -->
<!-- Inicio Botones  -->
<fieldset>
<input class="boton_125_de_2_izq" name="action" value="<bean:message key="sincronizarOdes.Cancelar"/>" type="submit">
<input class="boton_125_de_2" name="action" value="<bean:message key="sincronizarOdes.Aceptar"/>" type="submit">
</fieldset>
<!-- Fin Botones  -->
<!-- Fin Botones  -->

</div>
</form>
</div>
</div>
<!-- Fin plantilla contenido  -->

</div>
</tiles:put>

</tiles:insert>