<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html:xhtml/>

<tiles:insert definition="layout-administrador">

    <tiles:put name="title" type="string">
      <bean:message key="title.Comun"/>
	</tiles:put>


    <tiles:put name="body" type="string">
    
		<%@ include file="/taglib-imports.jspf" %>

		 
		<!-- ********************       INICIO PLANTILLA CONTENIDOS     ***********************  -->
		<div class="plantilla_contenido">
		
			<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
			<jsp:include page="/layout/messages.jsp" flush="true" />
		  
			<h2><bean:message key="modificarTarea.cabecera2"/></h2>
			
			<html:form styleId="obtenerTCargaODEsFormularioInformeCatalogoIIAceptarForm" action="/ObtenerTCargaODEs/FormularioInformeCatalogoIIAceptar" method="post" enctype="multipart/form-data">
			 
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
									
									<input type="hidden" name="grupoTrabajo" value="${form.grupoTrabajo}"/>
									
				                    <input type="hidden" name="tipoTarea" value="${form.tipoTarea}" />
				                    	
									<input type="hidden" name="dia" value="${form.dia}" />
							   
									<input type="hidden" name="mes" value="${form.mes}" />
							  
									<input type="hidden" name="anio" value="${form.anio}" />
							 
									<input type="hidden" name="hora" value="${form.hora}" />
							 
									<input type="hidden" name="minutos" value="${form.minutos}" />
							 
									<input type="hidden" name="periodicidad" value="${form.periodicidad}" />
									
									<input type="hidden" name="msgDescTrabajo" value="${form.msgDescTrabajo}" />
									
									<input type="hidden" name="msgNoInforme" value="${form.msgNoInforme}" />
									
									<input type="hidden" name="msgInforme" value="${form.msgInforme}" />
									
									
									
									
					                

									<!--   ********************       SELECT PARA INDICAR INDICE DE REINDEXADO         ***********************-->										
									<div class="fila_de_tabla">
									
										<div class="contenedor_izquierda2">
											<div class="text">
												<label for="idiomas"><bean:message key="crearTarea.idiomasCatalogo"/></label>
											</div>
										</div>
										
										<div class="contenedor_derecha2">
										
										
										
										<bean:define id="idiomaAlt"><bean:message key="crearTarea.seleccioneIdiomaCatalogo"/></bean:define>
											<c:choose>
						                        <c:when test="${!empty form.repositorioBackingList}">
						                           <html:select name="form" property="repositorio" styleId="Idioma" title="${idiomaAlt}">
						                               <html:optionsCollection name="form" property="repositorioBackingList" label="label" value="value"/>
						                           </html:select>
						                        </c:when>
						                        <c:otherwise>
						                            <html:select name="form" property="repositorio" styleId="Idioma" title="${idiomaAlt}"/>
						                        </c:otherwise>
						                    </c:choose>
											
										</div>
										
										<div class="linea_separadora"></div>
										<br class="oculto" />
									</div>
									
									<bean:define id="nomMeses"><bean:message key="catalogadorAvanzado.nombresMeses"/></bean:define>
		                            <bean:define id="nomDias"><bean:message key="catalogadorAvanzado.nombresDias"/></bean:define>
		                            <bean:define id="offSet"><bean:message key="offset"/></bean:define>
									<!--  *********************       Cajas de Texto de FechaActualizacion    *******************  -->

							<div class="fila_de_tabla" style="">
								<div class="contenedor_izquierda">
									<div class="text">
										<label for="repetir_tipoTarea"><bean:message key="crearTarea.distinguirODEs"/></label>
									</div>
								</div>
								<div class="contenedor_derecha"  id="cont_esp_fech">
								<div class="text" style="white-space:nowrap">
									<label class="oculto" for="day2"><bean:message key="crearTarea.fechaDia"/></label>
									<input  class="fechazo_02" id="day2"  value="${form.diaActualizacion}" onblur="this.style.backgroundColor='#e1e1e1'"  name="diaActualizacion" type="text" onfocus="limpiarTexto(this)" title="<bean:message key="crearTarea.introduzcaAnio"/> "/>
									<label class="oculto" for="month1"><bean:message key="crearTarea.mes"/></label>
										<select  id="month1"  name="mesActualizacion" title="<bean:message key="crearTarea.introduzcaMes"/> " style="width:100px;" >
											<c:if test="${form.mesActualizacion == '1'}">
												<option selected="selected"><bean:message key="crearTarea.Enero"/></option>
											</c:if>
											<c:if test="${form.mesActualizacion == '2'}">
												<option selected="selected"><bean:message key="crearTarea.Febrero"/></option>
											</c:if>
											<c:if test="${form.mesActualizacion == '3'}">
												<option selected="selected"><bean:message key="crearTarea.Marzo"/></option>
											</c:if>
											<c:if test="${form.mesActualizacion == '4'}">
												<option selected="selected"><bean:message key="crearTarea.Abril"/></option>
											</c:if>
											<c:if test="${form.mesActualizacion == '5'}">
												<option selected="selected"><bean:message key="crearTarea.Mayo"/></option>
											</c:if>
											<c:if test="${form.mesActualizacion == '6'}">
												<option selected="selected"><bean:message key="crearTarea.Junio"/></option>
											</c:if>
											<c:if test="${form.mesActualizacion == '7'}">
												<option selected="selected"><bean:message key="crearTarea.Julio"/></option>
											</c:if>
											<c:if test="${form.mesActualizacion == '8'}">
												<option selected="selected"><bean:message key="crearTarea.Agosto"/></option>
											</c:if>
											<c:if test="${form.mesActualizacion == '9'}">
												<option selected="selected"><bean:message key="crearTarea.Septiembre"/></option>
											</c:if>
											<c:if test="${form.mesActualizacion == '10'}">
												<option selected="selected"><bean:message key="crearTarea.Octubre"/></option>
											</c:if>
											<c:if test="${form.mesActualizacion == '11'}">
												<option selected="selected"><bean:message key="crearTarea.Noviembre"/></option>
											</c:if>
											<c:if test="${form.mesActualizacion == '12'}">
												<option selected="selected"><bean:message key="crearTarea.Diciembre"/></option>
											</c:if>
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
									<label class="oculto"  for="anio" ><bean:message key="crearTarea.fechaAnio"/></label>
									<input  class="fechazo_02" id="fecha_anio"  value="${form.anoActualizacion}" onblur="this.style.backgroundColor='#e1e1e1'"  name="anoActualizacion" type="text" onfocus="limpiarTexto(this)" title="<bean:message key="crearTarea.introduzcaAnio"/> "/>
								</div>
								</div>
								<div class="linea_separadora"></div>
								<br class="oculto" />
							</div>
							
							
							
							
							
							
								   
								</div>
							   	<!--  ********************      FIN CAJA DE FORMULARIO      ************************  -->
							</div>
						</div>
					</div>
				</div>
				<!-- *********************         FIN GLOBO GRIS        *************************  -->
			 
				<!--  ********************          Boton Aceptar       ************************ -->
					
					<bean:define id="aceptValue"><bean:message key="crearTarea.aceptar"/></bean:define>	
					<html:submit styleClass="boton_125_de_2 tipo" value="${aceptValue}"/>
				
			</html:form>

			<!-- ********************     Fin Formulario    **************** -->
			
			
			<!-- ********************     Inicio Formulario secundario    **************** -->
			
			<form id="obtenerTCargaODEsFormularioInformeCatalogoIICancelarForm" action="<html:rewrite action="/ObtenerTCargaODEs/FormularioInformeCatalogoIICancelar"/>" method="post" >
			
			
				<!-- ********************     Boton Cancelar    **************** -->
				
				<bean:define id="cancelValue"><bean:message key="crearTarea.cancelar"/></bean:define>
				<html:submit styleClass="boton_125_de_2_izq tipo" value="${cancelValue}"/>
				
			</form> 
			
			<!--  ********************          Fin formulario secundario        ***********************  -->
				
			
		</div>
		<!-- *********************         FIN PLANTILLA CONTENIDOS        ************************-->


    </tiles:put>

</tiles:insert>