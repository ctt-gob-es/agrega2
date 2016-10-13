<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>

<tiles:insert definition="layout-administrador">

<tiles:put name="title" type="string">
      <bean:message key="title.Comun"/>
</tiles:put>
<tiles:put name="codigo-head" type="string">
<script type="text/javascript" src="<rewrite:rewrite url="static/js/mootools.js"/>"></script>
<script type="text/javascript" src="<rewrite:rewrite url="static/js/calendar.rc4.js"/>"></script>
</tiles:put>
    <tiles:put name="body" type="string">
     
    
	<%@ include file="/taglib-imports.jspf" %>
	

		<!--************************             Inicio plantilla contenido         **************************  -->
		<div class="plantilla_contenido">
		
			<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
			<jsp:include page="/layout/messages.jsp" flush="true" />
 
	        <h2><bean:message key="crearTarea.cabecera2"/></h2>

			
			<html:form  styleId="crearTareaFormularioInformeFederadoNivelAgregacionAceptarForm" action="/CrearTarea/FormularioInformeFederadoNivelAgregacionAceptar" method="post" enctype="multipart/form-data" >
	        
				<!--  ********************     INICIO GLOBO GRIS   *********************  -->
				<div class="globo_gris" >
					<div class="globo_gris_01">
						<div class="globo_gris_02">
							<div class="globo_gris_03">
							<!--  *********************       INICIO CAJA DE FORMULARIO     *******************  -->
								<div id="formulario" >
									
									
									
									<!--  **********************         CAMPOS OCULTOS RECOGIDOS EN LA PANTALLA ANTERIOR      ******************-->
									
				                    <input type="hidden" name="trabajo" value="${form.trabajo}" />
									
				                    <input type="hidden" name="tipoTarea" value="${form.tipoTarea}" />
				                    	
									<input type="hidden" name="dia" value="${form.dia}" />
							   
									<input type="hidden" name="mes" value="${form.mes}" />
							  
									<input type="hidden" name="anio" value="${form.anio}" />
							 
									<input type="hidden" name="hora" value="${form.hora}" />
							 
									<input type="hidden" name="minutos" value="${form.minutos}" />
							 
									<input type="hidden" name="periodicidad" value="${form.periodicidad}" />
									
									<input type="hidden" name="msgInforme" value="<bean:message key="informe.crearInforme.msgInforme"/>" />
									
									<input type="hidden" name="msgNoInforme" value="<bean:message key="informe.crearInforme.msgNoInforme"/>" />
									
									<input type="hidden" name="msgDescTrabajo" value="<bean:message key="informe.crearInforme.msgDescTrabajo"/>" />
									
									<input type="hidden" name="informe" value="${form.informe}" />
									
									
									<!--  *********************       Caja de Texto de Nombre de Tarea     *******************  -->
									<div class="fila_de_tabla">
										<div class="contenedor_izquierda">
											<div class="text">
												<label for="informe"><bean:message key="informes.nombreInforme"/></label>
											</div>
										</div>
										<div class="contenedor_derecha">
											<div class="text">
												<label id="informe" style="width:400px;"><bean:message key="informes.${form.tipoTarea}"/></label>
												<br/>
											</div>
										</div>
										<div class="linea_separadora"></div>
										<br class="oculto" />
									</div>
									
									
									<!-- *********************		CAMPO formato (desplegable)		********************* -->
								
									<div class="fila_de_tabla">
										<div class="contenedor_izquierda">
											<div class="text">
												<label for="formato"><bean:message key="informes.crearInforme.formato"/></label>
											</div>
										</div>
										<div class="contenedor_derecha">
											<div class="text">
												<html:select  property="formato" titleKey="informes.crearInforme.seleccioneFormato" >
													<html:option key="informes.crearInforme.formato.html" 		value="html" />
													<html:option key="informes.crearInforme.formato.pdf" 		value="pdf" styleClass="oscura"/>
													<html:option key="informes.crearInforme.formato.excel"	 	value="excel" />
												</html:select>
											</div>
										</div>
										<div class="linea_separadora"></div>
										
										<br class="oculto" />
									</div>	
									
															 			<!--  *********************       Cajas de Texto de Fecha     *******************  -->
							<div class="fila_de_tabla" style="">
								<div class="contenedor_izquierda">
									<div class="text">
										<label for="repetir_tipoTarea"><bean:message key="tareas.tituloFechaInicio"/></label>
									</div>
								</div>
								<div class="contenedor_derecha"  id="cont_esp_fech">
								<div class="text" style="white-space:nowrap">
									<label class="oculto" for="day1"><bean:message key="crearTarea.fechaDia"/></label><input class="fechazo_02" value="" onblur="this.style.backgroundColor='#e1e1e1'" id="day1" name="dia" type="hidden" title="<bean:message key="crearTarea.introduzcaDia"/> "/>
									
									<label class="oculto" for="month1"><bean:message key="crearTarea.mes"/></label>
										
										<input class="fechazo_02" value="" onblur="this.style.backgroundColor='#e1e1e1'" id="month1" name="mesInicioInforme" type="text" title="<bean:message key="crearTarea.introduzcaMes"/> "/>
												
									<label class="oculto"  for="year1" ><bean:message key="crearTarea.fechaAnio"/></label>
									<input class="fechazo_02" id="year1"  value="" onblur="this.style.backgroundColor='#e1e1e1'"  name="anioInicioInforme" type="text" onfocus="limpiarTexto(this)" title="<bean:message key="crearTarea.introduzcaAnio"/> "/>
								</div>
								</div>
								<div class="linea_separadora"></div>
								<br class="oculto" />
							</div>
							
							
							
							
							
							</div>
							
								<!--  ********************     FIN CAJA FORMULARIO   *********************  -->
							</div>	
						</div>		
					</div>			
				</div>			
				<!--  ********************     FIN GLOBO GRIS   *********************  -->
				
				         
				<!--********************     Boton Aceptar      *********************  -->
					
				<bean:define id="aceptValue"><bean:message key="crearTarea.aceptar"/></bean:define>	
				<fieldset class="tipo">
						<html:submit styleClass="boton_125_de_2" value="${aceptValue}" ></html:submit>

					
				</html:form>
				
				<!-- ********************     Fin Formulario     **************** -->
				
				
				<!-- ********************     Inicio Formulario secundario    **************** -->
				
				<form id="crearTareaFormularioInformeFederadoNivelAgregacionCancelarForm" action="<html:rewrite action="/CrearTarea/FormularioInformeFederadoNivelAgregacionCancelar"/>" method="post" >
				
				
					<!-- ********************     Boton cancelar    **************** -->
					
					<bean:define id="cancelValue"><bean:message key="crearTarea.cancelar"/></bean:define>
					
						<html:submit styleClass="boton_125_de_2_izq" value="${cancelValue}"/>
					</fieldset>
					
				</form> 
				
				<!--********************     Fin formulario secundario      *********************  -->
		  
			


		</div>
		<!--************************             Fin plantilla contenido         **************************  -->
		
				
    </tiles:put>
 <bean:define id="nomMeses"><bean:message key="catalogadorAvanzado.nombresMeses"/></bean:define>
	<bean:define id="nomDias"><bean:message key="catalogadorAvanzado.nombresDias"/></bean:define>
	<bean:define id="offSet"><bean:message key="offset"/></bean:define>
   <tiles:put name="end-body" type="string">
  <script type="text/javascript">
	window.addEvent('domready', function() {
			jsMeses = "${nomMeses}"; arrayMeses = jsMeses.split(",");
			jsDias = "${nomDias}"; arrayDias = jsDias.split(",");
			myCal1 = new Calendar({ year1: { day1: 'd', month1: 'm', year1: 'Y' }}, { direction: 0, tweak: {x: 6, y: 0} ,offset: 1,months: arrayMeses ,days: arrayDias });
			});
	</script>
   </tiles:put>
</tiles:insert>

