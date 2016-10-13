<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<tiles:insert definition="layout-offline">

	<tiles:put name="title" type="string">
		<bean:message key="title.Comun" />
	</tiles:put>

	<tiles:put name="body" type="string">

<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- ATENCION!! RREMPLAZAR ESTA CAPA DE LA  PLANTILLA DE CONTENIDO ("plantilla_contenido") POR LA QUE SE DESEE EN LA PLANTILLA BASE  -->
<!-- Inicio plantilla contenido  -->



<div class="plantilla_contenido">
<jsp:include page="/layout/messages.jsp" flush="true" />
<h2><bean:message key="infomes.cabeceraDescripcion"/></h2>

<form method="post"  action="<html:rewrite action="/Sincronizar/InformeAceptar"/>" >
	
	
	 <div class="caja_tabla bordeada limpiar"  >
					<display:table name="${form.resultados}" requestURI=""
					export="false" id="fila" class="administracion_tareas" 
					style="border:0;width:100%;" cellpadding="0" cellspacing="0" 
					summary="${summary}" sort="list" pagesize="8">
			
						<display:setProperty name="css.tr.odd" value="tr_gris"/>
						<display:setProperty name="css.tr.even" value="tr_blanco"/>
						<display:setProperty name="basic.show.header" value="false"/>	
						<display:setProperty name="basic.empty.showtable" value="true"/>
						<display:setProperty name="basic.msg.empty_list_row"><tr><td valign="top"  class="tar4" ><bean:message key="subir.sinOdes"/></td></tr></display:setProperty>
            			
            			<!--  ******************** COLUMNA Titulo ODE **********************-->
						<display:column style="valign:top;" class="tar4">  
							
								${fila.titulo}
							
            			</display:column>
            			
            			<!--  ******************** COLUMNA QUE MUESTRA Estado *******************-->
						
						<display:column style="valign:top;" class="ejec"> 
							<c:forEach var="mensaje" items="${fila.mensajesError}">
 
				 			${fn:escapeXml(mensaje)} 
						 </c:forEach>
				        </display:column>
				        
					</display:table>			

</div>

		<!-- ******************************      Boton Volver     ********************************** -->
		<input type="submit" class="boton_125_de_2"	value="<bean:message key="comun.Aceptar"/>" />

	</form>

</div>





<!-- Fin plantilla contenido  -->
<!-- Fin plantilla contenido  -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->



		<!-- Fin plantilla CONTENIDO -->
	</tiles:put>

</tiles:insert>