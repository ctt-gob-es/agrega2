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


<div class="plantilla_contenido">
<html:xhtml/>
<jsp:include page="/layout/messages.jsp" flush="true" />

<!-- *****************************   Inicio  PESTANIAS de FICHA      ********************************** -->

	<div id="ficha_pestanias">
		<div><h2> <bean:message key="tareas.cabeceraEjecutadas"/> </h2></div>
		<ul>
		<li id="pest_activa"><a href="<html:rewrite action="/ModificacionesEjecutadas/ModificacionesEjecutadas"/>"id="seleccionada"> <bean:message key="tareas.Ejecutadas"/></a></li>
			<c:if test="${not offline }"><li><a href="<html:rewrite action="/ModificacionesEjecutadas/ListadoEjecutandose"/>"> <bean:message key="tareas.Ejecutandose"/></a></li></c:if>
			<li><a href="<html:rewrite action="/ModificacionesEjecutadas/ListadoPendientes"/>" ><bean:message key="tareas.Pendientes"/></a></li>
		</ul>
	</div>

<!-- *****************************   Fin  PESTANIAS de FICHA      ********************************** -->

<!-- ***************************** Inicio CONTENIDO PESTANIAS ****************************** -->


<div class="interno_ficha">
	<div class="plantilla_contenido_pestanias">
			
			<!-- ******************************      Inicio del formulario    ************************************ -->
		<form id="modificacionesPendientesListadoEliminarForm" action="<html:rewrite action='/ModificacionesEjecutadas/ListadoSubmitPendientes'/>" method="post" >
				<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
			<div class="globo_gris" >
				<div class="globo_gris_01">
					<div class="globo_gris_02">
						<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
							<div class="formu" >
			<h3 class="h3_generico"><bean:message key="tareasEjecutadas.pendientesAprobacion"/></h3>
			<p class="parrafo_comun_int"><bean:message key="tareasEjecutadas.pendientesAprobacion.texto"/></p>

			<!-- CAJA TABLA -->
<!-- CAJA TABLA -->
				
																			
				<!-- ******************************      CAJA TABLA     ************************************ -->
								<div class="caja_tabla bordeada" id="ficha_dentro_gris">
					
									<display:table name="${form.pendientes}" requestURI=""
									export="false" id="filaPendientes" class="administracion_tareas" 
									style="border:1;width:100%;" cellpadding="0" 
									cellspacing="0" summary="${summary}" sort="list" pagesize="8">
							
										<display:setProperty name="css.tr.odd" value="tr_gris"/>
										<display:setProperty name="css.tr.even" value="tr_blanco"/>
										<display:setProperty name="basic.show.header" value="false"/>	
										
										<!--  ******************** COLUMNA DE CHECKBOX ***********************-->
									    
										<display:column style="valign:top;" class="sin_b" >  
											<input type="checkbox" name="idModificacionRowSelectionAsArray" value="${filaPendientes.idModificacion}" title='<bean:message key="tareasEjecutadas.Seleccione"/>'/>
										</display:column>
				
									
										<!--  ******************** COLUMNA QUE MUESTRA LA TAREA **********************-->
										
										
										<display:column style="valign:top;" class="tar" >		
				                				${filaPendientes.nombre}      				
				            			</display:column>						
									
							
								        <!--  ******************** COLUMNA QUE MUESTRA ESTADO *******************-->
								        
									
										<display:column  style="valign:top;" class="alinea_a_izq">
											<bean:message key="estado.${filaPendientes.resultado}"/>
										</display:column>
										
										
										<!--  ******************** COLUMNA QUE MUESTRA LA OPCION "VER INFORME" *******************-->
									
										<display:column style="valign:top;" class="ejec">  
								            <html:link action="/ModificacionesEjecutadas/ListadoInformePendientes?idModificacion=${filaPendientes.idModificacion}"><bean:message key="tareasEjecutadas.VerInforme"/></html:link>
								        </display:column>
								       
									</display:table>
								</div>
					<!-- No se cierra el div aqui porque se hace en el archivo displaytag.properties al paginar -->
				<!-- en caso de no tener registros de usuarios se tiene que poner el div		-->
			
							<c:if test="${fn:length(form.pendientes) < 1}" >
		            			</div>     
								<!--		Fin caja tabla		-->
		            		</c:if>
       		     <!--  ****** Fin caja tabla *******-->
       		     <hr />
				<!-- ******************************      FIN CAJA TABLA     ************************************ -->
							<c:if test="${fn:length(form.pendientes) > 0}" >
				
							<fieldset class="tipo">	
								<input class="boton_125_de_2_izq bot_mar_der" name="action" type="submit" value="<bean:message key="tareasEjecutadas.aprobar"/>"  />
								<input class="boton_125_de_2" name="action"  type="submit" value="<bean:message key="tareasPendientes.Eliminar"/>" />
							</fieldset>	
							</c:if>

<!-- FIN CAJA TABLA -->
<!-- FIN CAJA TABLA -->
						</div>
				
				<!--  FIN CAJA DE FORMULARIO   -->
			</div>
		</div>
<br />

</form>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->

<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<form id="modificacionesEjecutadasListadoEliminarForm" action="<html:rewrite action='/ModificacionesEjecutadas/ListadoSubmitEjecutadas'/>" method="post" >

<div class="globo_gris" id="con_margen" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
				<div class="formu" >
			<h3 class="h3_generico"><bean:message key="tareasEjecutadas.aprobadas"/></h3>

			<!-- CAJA TABLA -->
<!-- CAJA TABLA -->
					<div class="caja_tabla bordeada" id="ficha_dentro_gris" >
						<display:table name="${form.ejecutadas}" requestURI=""
										export="false" id="fila" class="administracion_tareas" 
										style="border:1;width:100%;" cellpadding="0" 
										cellspacing="0" summary="${summary}" sort="list" pagesize="8">
								
											<display:setProperty name="css.tr.odd" value="tr_gris"/>
											<display:setProperty name="css.tr.even" value="tr_blanco"/>
											<display:setProperty name="basic.show.header" value="false"/>	
											
											<!--  ******************** COLUMNA DE CHECKBOX ***********************-->
										    
											<display:column style="valign:top;" class="sin_b" >  
												<input type="checkbox" name="idModificacionRowSelectionAsArray" value="${fila.idModificacion}" title='<bean:message key="tareasEjecutadas.Seleccione"/>'/>
											</display:column>
					
										
											<!--  ******************** COLUMNA QUE MUESTRA LA TAREA **********************-->
											
											
											<display:column style="valign:top;" class="tar" >		
					                				${fila.nombre}      				
					            			</display:column>						
										
								
									        <!--  ******************** COLUMNA QUE MUESTRA ESTADO *******************-->
									        
										
											<display:column  style="valign:top;" class="alinea_a_izq">
												<bean:message key="estado.${fila.resultado}"/>
											</display:column>
											
											
											<!--  ******************** COLUMNA QUE MUESTRA LA OPCION "VER INFORME" *******************-->
										
											<display:column style="valign:top;" class="ejec">  
									            <html:link action="/ModificacionesEjecutadas/ListadoInformeEjecutadas?idModificacion=${fila.idModificacion}"><bean:message key="tareasEjecutadas.VerInforme"/></html:link>
									        </display:column>
									       
										</display:table>
					
								
						
				<!-- No se cierra el div aqui porque se hace en el archivo displaytag.properties al paginar -->
				<!-- en caso de no tener registros de usuarios se tiene que poner el div		-->
			
								<c:if test="${fn:length(form.ejecutadas) < 1}" >
			            			</div>     
									<!--		Fin caja tabla		-->
			            		</c:if>
       		     <!--  ****** Fin caja tabla *******-->
           						<hr />
				<!-- ******************************      FIN CAJA TABLA     ************************************ -->
								<c:if test="${fn:length(form.ejecutadas) > 0}" >
								<!-- ******************************      Boton Ejecutar    ********************************** -->
									<fieldset class="tipo">	
										<input class="boton_125_de_2" name="action" type="submit" value="<bean:message key="tareasPendientes.Eliminar"/>" />
										<input class="boton_125_de_2_izq bot_mar_der" name="action" type="submit" value="<bean:message key="tareasEjecutadas.replanificar"/>" />	
									</fieldset>	
									<br/>	
								</c:if>
						</div>
						<hr />

				<!--  FIN CAJA DE FORMULARIO   -->
				</div>
		</div>
	</div>
	</div>
	
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->
				
			</form>
			<!-- ******************************      Fin del formulario    ***************************************** -->
		</div>		
	</div>	<!-- ************     plantilla contenido pestania  ************ -->	

	<!-- ************     fin <div class="interno_ficha">  ************ -->	
	
	<!-- ***************************** FIN  CONTENIDO PESTANIAS ****************************** -->

</div>
</tiles:put>
</tiles:insert>

