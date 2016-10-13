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
   
    
	<%@ include file="/taglib-imports.jspf" %>

		 
		<!-- ********************       INICIO PLANTILLA CONTENIDOS     ***********************  -->
		<div class="plantilla_contenido">
		
			<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
			<jsp:include page="/layout/messages.jsp" flush="true" />
		  
			<h2><bean:message key="crearTarea.cabecera2"/></h2>
			
			<html:form styleId="crearTareaFormularioCatalogoAceptarForm" action="/CrearTarea/FormularioCatalogoAceptar" method="post" enctype="multipart/form-data">
			 
				<!--  *******************        INICIO GLOBO GRIS     ***********************  -->
				<div class="globo_gris" >
					<div class="globo_gris_01">
						<div class="globo_gris_02">
							<div class="globo_gris_03">
						   
								<!--   *****************       INICIO CAJA DE FORMULARIO     ************************** -->
								<div id="formulario" >
									<h3><bean:message key="crearTarea.catalogo"/></h3>
								
									<!--  **********************         CAMPOS OCULTOS RECOGIDOS EN LA PANTALLA ANTERIOR      ******************-->
										
									<input type="hidden" name="trabajo" value="${form.trabajo}" />
									
				                    <input type="hidden" name="tipoTarea" value="${form.tipoTarea}" />
				                    	
									<input type="hidden" name="dia" value="${form.dia}" />
							   
									<input type="hidden" name="mes" value="${form.mes}" />
							  
									<input type="hidden" name="anio" value="${form.anio}" />
							 
									<input type="hidden" name="hora" value="${form.hora}" />
							 
									<input type="hidden" name="minutos" value="${form.minutos}" />
							 
									<input type="hidden" name="periodicidad" value="${form.periodicidad}" />
									
									<input type="hidden" name="informe" value="${form.informe}" />
					                

									<!--   ********************       SELECT PARA INDICAR INDICE DE REINDEXADO         ***********************-->										
									<div class="fila_de_tabla">
									
										<div class="contenedor_izquierda">
											<div class="text">
												<label for="indices"><bean:message key="crearTarea.idiomasCatalogo"/></label>
											</div>
										</div>
										
										<div class="contenedor_derecha">
											<div class="text">
												<bean:define id="idiomaAlt"><bean:message key="crearTarea.seleccioneIndices"/></bean:define>
													<c:choose>
								                        <c:when test="${!empty form.idiomaBackingList}">
								                           <html:select name="form" property="idioma" styleId="Idioma" title="${idiomaAlt}">
								                               <html:optionsCollection name="form" property="idiomaBackingList" label="label" value="value"/>
								                           </html:select>
								                        </c:when>
								                        <c:otherwise>
								                            <html:select name="form" property="idioma" styleId="Idioma" title="${idiomaAlt}"/>
								                        </c:otherwise>
								                    </c:choose>
							                  </div>
											
										</div>
										
										<div class="linea_separadora"></div>
										<br class="oculto" />
									</div>
									
														
								
						 			<!--  *********************       Cajas de Texto de Fecha     *******************  -->
									<div class="fila_de_tabla" style="">
										<div class="contenedor_izquierda">
											<div class="text">
												<label for="repetir_tipoTarea"><bean:message key="crearTarea.distinguirODEs"/></label>
											</div>
										</div>
										<div class="contenedor_derecha"  id="cont_esp_fech">
											<div class="text" style="white-space:nowrap">
												<label class="oculto" for="day2"><bean:message key="crearTarea.fechaDia"/></label>
													<input class="fechazo_02" value="<bean:message key="crearTarea.Dia"/>" onfocus="limpiarTexto(this)" onblur="this.style.backgroundColor='#e1e1e1'" id="day2" name="diaActualizacion" type="text" title="<bean:message key="crearTarea.introduzcaDia"/> "/>
												<label class="oculto" for="month2"><bean:message key="crearTarea.mes"/></label>
													<select class="fechazo_02" id="month2" name="mesActualizacion" title="<bean:message key="crearTarea.introduzcaMes"/> ">
														<option selected="selected"><bean:message key="crearTarea.Mes"/></option>
														<option value="1"><bean:message key="crearTarea.Enero"/></option>
														<option value="2"><bean:message key="crearTarea.Febrero"/></option>
														<option value="3"><bean:message key="crearTarea.Marzo"/></option>
														<option value="4"><bean:message key="crearTarea.Abril"/></option>
														<option value="5"><bean:message key="crearTarea.Mayo"/></option>
														<option value="6"><bean:message key="crearTarea.Junio"/></option>
														<option value="7"><bean:message key="crearTarea.Julio"/></option>
														<option value="8"><bean:message key="crearTarea.Agosto"/></option>
														<option value="9"><bean:message key="crearTarea.Septiembre"/></option>
														<option value="10"><bean:message key="crearTarea.Octubre"/></option>
														<option value="11"><bean:message key="crearTarea.Noviembre"/></option>
														<option value="12"><bean:message key="crearTarea.Diciembre"/></option>
													</select>
												<label class="oculto"  for="year2" ><bean:message key="crearTarea.fechaAnio"/></label>
												<input class="fechazo_02" id="year2"  value="<bean:message key="crearTarea.Año"/>" onfocus="limpiarTexto(this)" onblur="this.style.backgroundColor='#e1e1e1'"  name="anoActualizacion" type="text" title="<bean:message key="crearTarea.introduzcaAnio"/> "/>
											</div>
									</div>
									<div class="linea_separadora"></div>
									<br class="oculto" />
							</div>

				<!-- -->
				</div>

								
								
									
									
							   	<!--  ********************      FIN CAJA DE FORMULARIO      ************************  -->
							</div>
						</div>
					</div>
				</div>
				<!-- *********************         FIN GLOBO GRIS        *************************  -->
			 
				<!--  ********************         Boton Aceptar       ************************ -->
				<fieldset class="tipo">
					<bean:define id="aceptValue"><bean:message key="crearTarea.aceptar"/></bean:define>	
					<html:submit styleClass="boton_125_de_2" value="${aceptValue}" ></html:submit>
				
				</html:form>
				<!-- ********************     Fin Formulario    **************** -->
			
			
				<!-- ********************     Inicio Formulario secundario    **************** -->
				<form id="crearTareaFormularioCatalogoCancelarForm" action="<html:rewrite action="/CrearTarea/FormularioCatalogoCancelar"/>" method="post">
			
				<!-- ********************     Boton cancelar    **************** -->
					<bean:define id="cancelValue"><bean:message key="crearTarea.cancelar"/></bean:define>
					<html:submit styleClass="boton_125_de_2_izq" value="${cancelValue}"/>
				</fieldset>

				
			</form> 
			<!--  ********************          Fin formulario secundario        ***********************  -->
				
			
		</div>
		<!-- *********************         FIN PLANTILLA CONTENIDOS        ************************-->


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


