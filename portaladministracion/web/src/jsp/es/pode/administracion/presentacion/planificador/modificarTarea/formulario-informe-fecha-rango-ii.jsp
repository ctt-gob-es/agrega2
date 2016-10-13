<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
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
	<!-- ************************     INICIO PLANTILLA CONTENIDO     *************************  -->
    <div class="plantilla_contenido">
    
    <!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
	<jsp:include page="/layout/messages.jsp" flush="true" />

	<h2><bean:message key="modificarTarea.cabecera2"/></h2>
	<html:form styleId="obtenerTCargaODEsFormularioInformeFechaRangoIIAceptarForm" action="/ObtenerTCargaODEs/FormularioInformeFechaRangoIIAceptar" method="post" >
	
		<!--  ********************     INICIO GLOBO GRIS   *********************  -->
		<div class="globo_gris" >
			<div class="globo_gris_01">
				<div class="globo_gris_02">
					<div class="globo_gris_03">
					<!--  *********************       INICIO CAJA DE FORMULARIO     *******************  -->
						<div id="formulario" >
							
							
							<!--  *********************       Campos ocultos    *******************  -->
							
							<input type="hidden" name="trabajo" value="${form.trabajo}" />
							
							<input type="hidden" name="informe" value="${form.informe}" />
							
							<input type="hidden" name="grupoTrabajo" value="${form.grupoTrabajo}"/>
									
		                    <input type="hidden" name="tipoTarea" value="${form.tipoTarea}" />
		                    	
							<input type="hidden" name="dia" value="${form.dia}" />
					   
							<input type="hidden" name="mes" value="${form.mes}" />
					  
							<input type="hidden" name="anio" value="${form.anio}" />
					 
							<input type="hidden" name="hora" value="${form.hora}" />
					 
							<input type="hidden" name="minutos" value="${form.minutos}" />
					 
							<input type="hidden" name="periodicidad" value="${form.periodicidad}" />
					
							
							<!--  *********************       Caja de Texto de Nombre de Tarea     *******************  -->
							<div class="fila_de_tabla">
								<div class="contenedor_izquierda">
									<div class="text">
										<label for="informe"><bean:message key="informes.nombreInforme"/></label>
									</div>
								</div>
								<div class="contenedor_derecha">
									<div class="text">
										<label id="informe" style="width:400px;"><bean:message key="informes.${form.informe}"/></label>
										<br/>
									</div>
								</div>
								<div class="linea_separadora"></div>
								<br class="oculto" />
							</div>
							
							
							<c:if test="${form.periodicidad == 'N'}" >
						
							<!--  *********************       Cajas de Texto de Fecha Desde    *******************  -->
							<div class="fila_de_tabla">
								<div class="contenedor_izquierda">
									<div class="text">
										<label for="fecha_inicio"><bean:message key="crearTarea.fechaDesde"/></label>
									</div>
								</div>
								<div class="contenedor_derecha"  id="cont_esp_fech">
						<div class="text"><label class="oculto" for="day1"><bean:message key="crearTarea.fechaDia"/></label><input  class="fechazo_02" value="${form.diaDesde}" onblur="this.style.backgroundColor='#e1e1e1'" id="day1" name="diaDesde" type="text" title="<bean:message key="crearTarea.introduzcaDia"/> "/>
						<label class="oculto" for="month1"><bean:message key="crearTarea.fechaMes"/></label><input class="fechazo_02" value="${form.mesDesde}" onblur="this.style.backgroundColor='#e1e1e1'" id="month1" name="mesDesde" type="text" title="<bean:message key="crearTarea.introduzcaMes"/> "/>
						
						
			<label class="oculto"  for="year1" ><bean:message key="crearTarea.fechaAnio"/></label>
			<input class="fechazo_02" id="year1"  value="${form.anioDesde}" onblur="this.style.backgroundColor='#e1e1e1'"  name="anioDesde" type="text" title="<bean:message key="crearTarea.introduzcaAnio"/> "/>
		</div>
				</div>
								<div class="linea_separadora"></div>
								<br class="oculto" />
							</div>
							
							
							<!--  *********************       Cajas de Texto de Fecha Hasta     *******************  -->
							<div class="fila_de_tabla">
								<div class="contenedor_izquierda">
									<div class="text">
										<label for="fecha_inicio"><bean:message key="crearTarea.fechaHasta"/></label>
									</div>
								</div>
								<div class="contenedor_derecha"  id="cont_esp_fech">
						<div class="text"><label class="oculto" for="day2"><bean:message key="crearTarea.fechaDia"/></label><input  class="fechazo_02" value="${form.diaHasta}" onblur="this.style.backgroundColor='#e1e1e1'" id="day2" name="diaHasta" type="text" title="<bean:message key="crearTarea.introduzcaDia"/> "/>
						<label class="oculto" for="month2"><bean:message key="crearTarea.fechaMes"/></label><input class="fechazo_02" value="${form.mesHasta}" onblur="this.style.backgroundColor='#e1e1e1'" id="month2" name="mesHasta" type="text" title="<bean:message key="crearTarea.introduzcaMes"/> "/>
						
						
			<label class="oculto"  for="year2" ><bean:message key="crearTarea.fechaAnio"/></label>
			<input class="fechazo_02" id="year2"  value="${form.anioHasta}" onblur="this.style.backgroundColor='#e1e1e1'"  name="anioHasta" type="text" title="<bean:message key="crearTarea.introduzcaAnio"/> "/>
			
		</div>
		
				</div>
								<div class="linea_separadora"></div>
								<br class="oculto" />
							</div>
							
							</c:if>
							
							<!--		CAMPO rango (desplegable)		-->
							<div class="fila_de_tabla">
								<div class="contenedor_izquierda">
									<div class="text">
										<label for="rango"><bean:message key="informes.crearInforme.rango"/></label>
									</div>
								</div>
								<div class="contenedor_derecha">
									<div class="text">
										<html:select property="rango" value="${form.rango}" style="width:107px" titleKey="informes.crearInforme.seleccioneRango" styleId="rango">
											<html:option key="informes.crearInforme.rango.5" 		value="5" />
											<html:option key="informes.crearInforme.rango.10"	 	value="10" styleClass="oscura" />
											<html:option key="informes.crearInforme.rango.15"	 	value="15" />
											<html:option key="informes.crearInforme.rango.20"	 	value="20" styleClass="oscura" />
											<html:option key="informes.crearInforme.rango.30"	 	value="30" />
											<html:option key="informes.crearInforme.rango.40"	 	value="40" styleClass="oscura" />
											<html:option key="informes.crearInforme.rango.50"	 	value="50" />
											<html:option key="informes.crearInforme.rango.100"	 	value="100" styleClass="oscura" />
										</html:select>
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
										<html:select property="formato" value="${form.formato}" style="width:107px" titleKey="informes.crearInforme.seleccioneFormato" styleId="formato">
											<html:option key="informes.crearInforme.formato.html" 		value="html" />
											<html:option key="informes.crearInforme.formato.pdf" 		value="pdf" styleClass="oscura"/>
											<html:option key="informes.crearInforme.formato.excel"	 	value="excel" />
										</html:select>
									</div>
								</div>
								<div class="linea_separadora"></div>
								
								<br class="oculto" />
							</div>	
							
				 
						</div >
						<!--  ********************     FIN CAJA FORMULARIO   *********************  -->
					</div>	
				</div>		
			</div>			
		</div>			
		<!--  ********************     FIN GLOBO GRIS   *********************  -->
		
		<!--********************     Boton aceptar      *********************  -->
		
		<bean:define id="aceptValue"><bean:message key="crearTarea.continuar"/></bean:define>	
		<html:submit styleClass="boton_125_de_2 tipo" value="${aceptValue}" ></html:submit>

	</html:form>
	
	<!-- ********************     Inicio Formulario secundario    **************** -->
	
	<form id="obtenerTCargaODEsFormularioInformeFechaRangoIICancelarForm" action='<html:rewrite action="/ObtenerTCargaODEs/FormularioInformeFechaRangoIICancelar"/>' method="post" >
	
	
		<!-- ********************     Boton Cancelar    **************** -->
		
		<bean:define id="cancelValue"><bean:message key="crearTarea.cancelar"/></bean:define>
		<html:submit styleClass="boton_125_de_2_izq tipo" value="${cancelValue}"/>
		
	</form> 
	<!-- ********************     FIN formulario secundario      *********************  -->
	
	
	</div>
	<!-- ************************     FIN  PLANTILLA  CONTENIDO   *************************  -->
	
	

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
			myCal2 = new Calendar({ year2: { day2: 'd', month2: 'm', year2: 'Y' }}, { direction: 0, tweak: {x: 6, y: 0} ,offset: 1,months: arrayMeses ,days: arrayDias });
			});
	</script>
   </tiles:put> 
</tiles:insert>