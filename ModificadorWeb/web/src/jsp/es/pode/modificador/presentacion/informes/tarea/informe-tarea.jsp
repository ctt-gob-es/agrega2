<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>


<tiles:insert definition="${sessionScope.offline == true ? 'layout-offline' : 'layout-administrador'}">

<tiles:put name="title" type="string">
      <bean:message key="title.Comun"/>
</tiles:put>
<tiles:put name="body" type="string">

<%@ include file="/taglib-imports.jspf" %>


<!-- *****************************   Inicio  Plantilla contenido      ********************************** -->
<div class="plantilla_contenido">

<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />

<h2><bean:message key="tareas.cabeceraInforme"/> </h2>
<form method="post" action="<html:rewrite action="/InformeTarea/InformeTareaSelectOrigin"/>" enctype="multipart/form-data">
<!--  INICIO CAJA DE FORMULARIO   -->
<div class="caja_de_tabla_invisible" >
<table cellspacing="0" cellpadding="0"  border="0" width="100%"  summary="Asignacion Comunidades">
<tr>
	<td class="nt" valign="top"  ><bean:message key="tareas.nombreTarea"/>:&nbsp;</td>
	<td  class="nt2" valign="top"><label>${form.nombreTarea}</label></td>
</tr>
<tr>
	<td class="nt" valign="top" ><bean:message key="tareas.Resultado"/>:</td>

	<td class="nt2" valign="top" ><label><bean:message key="tareas.${form.resultadoTarea}"/>
	<logic:notEmpty name="form" property="descResultado">
		- <bean:message key="${form.descResultado}"/>
	</logic:notEmpty>
	</label></td>
</tr>
</table>
</div>

<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" id="conmargen">
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">

			
			<!--  INICIO CAJA DE FORMULARIO   -->

			<div class="formu" >
			<!-- -->
			<c:choose>
				<c:when test="${(!empty sessionScope.informeSession.odes) and (fn:length(sessionScope.informeSession.odes)>0) and (sessionScope.informeSession.origen!='Configurar')}">
					<div class="fila_de_tabla">
					
					<div class="text"><label for="Estado" id="filtrar_estado"><bean:message key="filtrar.estado"/>:</label>
														
						<html:select name="form" property="estados" titleKey="filtrar.seleccioneEstado" styleId="Estado" styleClass="filtrar_estado">
								<html:optionsCollection name="form" property="estadosBackingList" label="label" value="value"/>
						</html:select><input class="boton_90" name="action" style="height:1.8em;"  type="submit"  value="<bean:message key="filtrar"/>" />								</div>
						<div class="linea_separadora"></div>
						<br class="oculto"/>
						
					</div>	<!-- -->
					</c:when>
				</c:choose>

<!--  INICIO CAPA TABLA   -->
<!--  INICIO CAPA TABLA   -->	
<c:choose>
<c:when test="${(!empty form.odes) and (fn:length(form.odes)>0)}">				
 <div class="caja_tabla bordeada"   >
 
 		<display:table name="${form.odes}" requestURI=""
					export="false" id="fila" class="administracion_tareas" 
					style="border:0;width:100%;" cellpadding="0" cellspacing="0" 
					summary="${summary}" sort="list" pagesize="8">
			
						<display:setProperty name="css.tr.odd" value="tr_gris"/>
						<display:setProperty name="css.tr.even" value="tr_blanco"/>
						<display:setProperty name="basic.show.header" value="true"/>		
						<display:setProperty name="basic.empty.showtable" value="true"/>
            			
            			<!--  ******************** COLUMNA Titulo ODE **********************-->
            			<bean:define id="tituloValue"><b><bean:message key="buscarObjeto.titulo"/></b></bean:define>
						<display:column sortable="true" sortProperty="titulo" style="valign:top;" class="tar" title="${tituloValue}">  
							<span class="paquete">
								${fila.titulo}
							</span>
            			</display:column>
            			<td valign="top"></td>
            			<!--  ******************** COLUMNA QUE MUESTRA Estado *******************-->
						<bean:define id="estadoValue"><b><bean:message key="tareas.cabeceraEstado"/></b></bean:define>
						<display:column sortable="true" sortProperty="status" style="valign:top;" class="alinea_a_izq" title="${estadoValue}"> 
							<bean:message key="tareas.${fila.status}"/>
				        </display:column>
            			
            			<!--  ******************** COLUMNA MAS INFORMACION **********************-->
            			<display:column style="valign:top;" class="ejec">
							<html:link  target="_blank" action="/InformeTarea/InformeTareaTraza?id=${fila.id}&amp;nombreTarea=${form.nombreTarea}&amp;resultadoTarea=${form.resultadoTarea}">
								<bean:message key="tareas.masInformacion"/>
							</html:link>
				        </display:column>
				        <td valign="top"></td>
				        <!--  ******************** COLUMNA  DESCARGAR **********************-->
				        <c:if test="${fila.esDescargable}">
            			<display:column style="valign:top;">
							<html:link action="/InformeTarea/InformeTareaDescargar?id=${fila.id}&amp;titulo=${fila.titulo}">
								<bean:message key="tareas.descargar"/>
							</html:link>
				        </display:column>
				        <td valign="top"></td>
				        </c:if>
				        <!--  ******************** COLUMNA QUE MUESTRA Deshacer *******************-->
				        <c:if test="${(!empty informeSession.idModificacion) and (!empty fila.pathBackup)}">
				        <display:column style="valign:top;" class="ejec">
				        <c:if test="${(fila.status == 'FINALIZADA')}">
							<html:link action="/InformeTarea/InformeTareaRestaurar?id=${fila.id}">
								<bean:message key="tareas.deshacer"/>
							</html:link>
						</c:if>
				        </display:column>
				        </c:if>
				        
				        
				        
					</display:table>
					
	</c:when>
	<c:otherwise>
					
						<div class="caja_tabla bordeada"  >
								<display:table name="${form.odes}" requestURI=""
					export="false" id="fila" class="administracion_tareas" 
					style="border:0;width:100%;" cellpadding="0" cellspacing="0" 
					summary="${summary}" sort="list" pagesize="8">
			
						<display:setProperty name="css.tr.odd" value="tr_gris"/>
						<display:setProperty name="css.tr.even" value="tr_blanco"/>	
						<display:setProperty name="basic.show.header" value="false"/>	
						<display:setProperty name="basic.empty.showtable" value="true"/>
						<display:setProperty name="basic.msg.empty_list_row"><tr><td valign="top"  class="tar" ><bean:message key="${form.textoError}"/></td></tr></display:setProperty>
            			
            			<!--  ******************** COLUMNA Titulo ODE **********************-->
            			
						<display:column   style="valign:top;" class="tar" >  
							<span class="paquete">
								${fila.titulo}
							</span>
            			</display:column>
            			<td valign="top"></td>
            			<!--  ******************** COLUMNA QUE MUESTRA Estado *******************-->
						
						<display:column  style="valign:top;" class="alinea_a_izq" > 
							<bean:message key="tareas.${fila.status}"/>
				        </display:column>
            			
            			<!--  ******************** COLUMNA MAS INFORMACION **********************-->
            			<display:column style="valign:top;" class="ejec">
							<html:link  target="_blank" action="/InformeTarea/InformeTareaTraza?id=${fila.id}&amp;nombreTarea=${form.nombreTarea}&amp;resultadoTarea=${form.resultadoTarea}">
								<bean:message key="tareas.masInformacion"/>
							</html:link>
				        </display:column>
				        <td valign="top"></td>
				        <!--  ******************** COLUMNA QUE MUESTRA Deshacer *******************-->
				        <c:if test="${(!empty informeSession.idModificacion) and (!empty fila.pathBackup)}">
				        <display:column style="valign:top;" class="ejec">
				        <c:if test="${(fila.status == 'FINALIZADA')}">
							<html:link action="/InformeTarea/InformeTareaRestaurar?id=${fila.id}">
								<bean:message key="tareas.deshacer"/>
							</html:link>
						</c:if>
				        </display:column>
				        </c:if>
				        
					</display:table>
					
	</c:otherwise>
</c:choose>
				

</div>
<!--  FIN CAPA TABLA   -->		
<!--  FIN CAPA TABLA   -->	
</div>
<!--  FIN CAJA DE FORMULARIO   -->
			</div>
		</div>
	</div>

</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->

<!-- Inicio Botones  -->
<!-- Inicio Botones  -->
<fieldset class="tipo ft_centrada" >
<input class="boton_125" name="action" type="submit"  value="<bean:message key="tareas.volver"/>"  />
</fieldset>
<!-- Fin Botones  -->
<!-- Fin Botones  -->

</form>
</div>



    </tiles:put>

</tiles:insert>
