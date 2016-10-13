<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<html:xhtml/>


<tiles:insert definition="layout-administrador">

<tiles:put name="title" type="string">
      <bean:message key="title.Comun"/>
</tiles:put>

<tiles:put name="codigo-head" type="string">
<script type="text/javascript" src="<rewrite:rewrite url="static/js/mootools.js"/>"></script>
<script type="text/javascript" src="<rewrite:rewrite url="static/js/calendar.rc4.js"/>"></script>
</tiles:put>

    <tiles:put name="body" type="string">
   
    
	<!-- ************************     INICIO PLANTILLA CONTENIDO     *************************  -->
    <div class="plantilla_contenido">
    
    <!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
	<jsp:include page="/layout/messages.jsp" flush="true" />

	<h2><bean:message key="crearTarea.cabecera"/></h2>
	<html:form styleId="crearTareaFormularioInicioCrearTareaForm" action='/CrearTarea/FormularioInicioCrearTarea' method="post" >
	
		<!--  ********************     INICIO GLOBO GRIS   *********************  -->
		<div class="globo_gris" id="ficha">
			<div class="globo_gris_01">
				<div class="globo_gris_02">
					<div class="globo_gris_03">
					<!--  *********************       INICIO CAJA DE FORMULARIO     *******************  -->
						<div id="formulario" >
						
							<!--  *********************       Caja de Texto de Nombre de Tarea     *******************  -->
							<div class="fila_de_tabla">
								<div class="contenedor_izquierda">
									<div class="text">
										<label for="nombreTarea">* <bean:message key="crearTarea.nombreTarea"/></label>
									</div>
								</div>
								<div class="contenedor_derecha">
									<div class="text">
										<input name="trabajo" value="" maxlength="40" id="nombreTarea" type="text" title="<bean:message key='crearTarea.introduzcaNombre'/>" />
									</div>
								</div>
								<div class="linea_separadora"></div>
								<br class="oculto" />
							</div>
							
							
							<!--  *********************       Desplegable de Tipo de Tarea     *******************  -->
							<div class="fila_de_tabla">
								<div class="contenedor_izquierda">
									<div class="text">
										<label for="tipoTarea"><bean:message key="crearTarea.tipoTarea"/></label>
									</div>
								</div>
								<div class="contenedor_derecha">
									<div class="text">
										<select  name="tipoTarea" title="<bean:message key="crearTarea.seleccioneTipoTarea"/>" id="tipoTarea" onchange="ajustarPeriodicidad();">
											<optgroup label="<bean:message key="crearTarea.mantenimiento"/>">	
												<option value="CargaODEs" class="oscura"><bean:message key="crearTarea.tareaCargaODEs" /></option>
												<option value="Reindexado" ><bean:message key="crearTarea.tareaReindexada"/></option>
												<option value="EliminarNoDisponibles" class="oscura"><bean:message key="crearTarea.tareaEliminarODEs"/></option>
												<!-- Se elimina la tarea de Regenerar Imagenes ya que el usuario puede seleccionar la imagen por defecto -->
												<!-- option value="RegenerarImagenes" ><bean:message key="crearTarea.tareaRegenerarImagenes"/></option-->
												<option value="GenerarSitemaps"><bean:message key="crearTarea.generarSitemaps"/></option>
												<option value="LanzarRSS"><bean:message key="crearTarea.lanzarRSS"/></option>
												<option value="DespublicarODEs"><bean:message key="crearTarea.despublicacionMasiva"/></option>
												<option value="GenerarOaiOre"><bean:message key="crearTarea.generarOaiOre"/></option>
												<option value="ActualizarIndicesRemotos"><bean:message key="crearTarea.actualizarIndicesRemotos"/></option>
												<option value="SubirIndices"><bean:message key="crearTarea.subirIndices"/></option>
												<option value="EstadisticasLocales">Generar estadísticas locales</option>
												<option value="EstadisticasTotales">Generar estadísticas totales</option>	
												<option value="ObtenerMetadatosODESFederados">Obtener Metadatos ODEs Federados</option>
												<option value="ObtenerODESDespublicadosFederados">Obtener ODEs Despublicados Federados</option>	
												<option value="ObtenerMetadatosODESFederadosFaltantes">Obtener Metadatos ODEs Federados Faltantes</option>
												<option value="ObtenerODESDespublicadosFederadosFaltantes">Obtener ODEs Despublicados Federados Faltantes</option>	
											</optgroup>
											<optgroup label="<bean:message key="crearTarea.informes"/>">
												<option value="estadoOdes" class="oscura"><bean:message key="crearTarea.estadoOdes"/></option>
												<option value="operacionesRealizadas"><bean:message key="crearTarea.operacionesRealizadas"/></option>
												<option value="nivelAgregacion" class="oscura"><bean:message key="crearTarea.nivelAgregacion"/></option>
												<option value="coberturaCurricular" ><bean:message key="crearTarea.coberturaCurricular"/></option>
												<option value="odesLicencias" class="oscura" ><bean:message key="crearTarea.odesLicencias"/></option>
												<option value="procesosPlanificados"><bean:message key="crearTarea.procesosPlanificados"/></option>
												<option value="usuarios" class="oscura" ><bean:message key="crearTarea.usuarios"/></option>
												<option value="odesUsuario" /><bean:message key="crearTarea.odesUsuario" /></option>
												<option value="terminosBusqueda" class="oscura" /><bean:message key="crearTarea.terminosBusqueda"/></option>
												<option value="masValorado"  /><bean:message key="crearTarea.masValorado"/></option>
												<option value="masMostrado" class="oscura"  /><bean:message key="crearTarea.masMostrado"/></option>
												<option value="masPrevisualizado" /><bean:message key="crearTarea.masPrevisualizado"/></option>
												<option value="masDescargado" class="oscura" /><bean:message key="crearTarea.masDescargado"/></option>
												<option value="tamanio" /><bean:message key="crearTarea.tamanio"/></option>
											</optgroup>
											<optgroup label="<bean:message key="crearTarea.informesFederado"/>" style="oscura">
												<option value="odesIdiomaFederada" /><bean:message key="crearTarea.odesIdiomaFederada"/></option>
												<option value="odesPublicadosFederada" class="oscura" /><bean:message key="crearTarea.odesPublicadosFederada"/></option>
												<option value="nivelAgregacionFederada" /><bean:message key="crearTarea.nivelAgregacionFederada"/></option>
												<option value="coberturaCurricularFederada" class="oscura"/> <bean:message key="crearTarea.coberturaCurricularFederada"/></option>
											</optgroup>
											<optgroup label="<bean:message key="crearTarea.catalogo"/>">
												<option value="repositorio" class="oscura"/><bean:message key="crearTarea.repositorio"/></option>
											</optgroup>
									</select>
									</div>										
								</div>
								<div class="linea_separadora"></div>
								<br class="oculto" />
							</div>
							
							
							<bean:define id="nomMeses"><bean:message key="catalogadorAvanzado.nombresMeses"/></bean:define>
                            <bean:define id="nomDias"><bean:message key="catalogadorAvanzado.nombresDias"/></bean:define>
                            <bean:define id="offSet"><bean:message key="offset"/></bean:define>
							<!--  *********************       Cajas de Texto de Fecha     *******************  -->
							<div class="fila_de_tabla">
								<div class="contenedor_izquierda">
									<div class="text">
										<label for="fecha_inicio">* <bean:message key="crearTarea.fechaInicio"/></label>
									</div>
								</div>
								<div class="contenedor_derecha"  id="cont_esp_fech">
						<div class="text"><label class="oculto" for="day2"><bean:message key="crearTarea.fechaDia"/></label><input class="fechazo_02" value="" onblur="this.style.backgroundColor='#e1e1e1'" id="day2" name="dia" type="text" title="<bean:message key="crearTarea.introduzcaDia"/> "/>
						<label class="oculto" for="month2"><bean:message key="crearTarea.mes"/></label><input class="fechazo_02" value="" onblur="this.style.backgroundColor='#e1e1e1'" id="month2" name="mes" type="text" title="<bean:message key="crearTarea.introduzcaMes"/> "/>
						
						
			<label class="oculto"  for="year2" ><bean:message key="crearTarea.fechaAnio"/></label>
			<input class="fechazo_02" id="year2"  value="" onblur="this.style.backgroundColor='#e1e1e1'"  name="anio" type="text" title="<bean:message key="crearTarea.introduzcaAnio"/> "/>
		<span class="vert"><bean:message key="crearTarea.aLas"/></span>
										
										<label class="oculto" for="fecha_hora"><bean:message key="crearTarea.fechaHora"/></label>
										<input name="hora"  value="${form.hora}" maxlength="2" class="fecha_horas" id="fecha_hora" type="text" title="<bean:message key="crearTarea.introduzcaHora"/> "  />
										
										<label class="oculto"  for="fecha_minutos"><bean:message key="crearTarea.fechaMinuto"/></label>
										<input name="minutos" value="${form.minutos}" maxlength="2" class="fecha_minutos" id="fecha_minutos" type="text" title="<bean:message key="crearTarea.introduzcaMinuto"/> "  />
</div>
					</div>
								<div class="linea_separadora"></div>
								<br class="oculto" />
							</div>
							
							
							<!--  *********************       Desplegable de Periodicidad     *******************  -->
							<div class="fila_de_tabla">
								<div class="contenedor_izquierda">
									<div class="text">
										<label for="repetir_tipoTarea"><bean:message key="crearTarea.repetir"/></label>
									</div>
								</div>
								<div class="contenedor_derecha">
									<div class="text">
										<html:select  property="periodicidad" titleKey="crearTarea.repitaTipoTarea" styleId="repetir_tipoTarea">
											<html:option key="crearTarea.N" 		value="N" />
											<html:option key="crearTarea.D" 		value="D" styleClass="oscura" />
											<html:option key="crearTarea.S" 		value="S" />
											<html:option key="crearTarea.M" 		value="M" styleClass="oscura"/>
											<html:option key="crearTarea.A" 		value="A" />
										</html:select>
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
		
		<!--********************     Boton aceptar      *********************  -->
		
		<bean:define id="aceptValue"><bean:message key="crearTarea.aceptar"/></bean:define>	
		<html:submit styleClass="boton_125_de_2 tipo" value="${aceptValue}" ></html:submit>

	</html:form>
	
	<!-- ********************     Inicio Formulario secundario    **************** -->
	
	<form id="crearTareaFormularioInicioCancelarForm" action="<html:rewrite action="/CrearTarea/FormularioInicioCancelar"/>" method="post" >
	
	
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
	
	
	
   <script type="text/javascript">
		
		function ajustarPeriodicidad() {

			// Get all options within <select id='foo'>...</select>
			var op = document.getElementById("repetir_tipoTarea").getElementsByTagName("option");
			var tipoTarea = document.getElementById('tipoTarea').value;
			
			//Si la tarea es de alguno de estos tipos tiene que ser diaria
			if(tipoTarea=="ObtenerMetadatosODESFederados" || tipoTarea=="ObtenerODESDespublicadosFederados") {
				
				document.getElementById('repetir_tipoTarea').value="D";
				for (var i = 0; i < op.length; i++) {  
					if (op[i].value != "D") 
						op[i].disabled = true;
					else 
						op[i].disabled = false;
				}
			} else {
				for (var i = 0; i < op.length; i++) 
					op[i].disabled = false;
			}
		}
		
	</script>
   
   </tiles:put>
   
</tiles:insert>