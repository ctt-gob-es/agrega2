<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://www.andromda.org/tags-breadcrumbs" prefix="breadcrumbs" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib uri="/WEB-INF/tags/googleAnalytic.tld" prefix="analytic" %>

	<tiles:insert definition="layout-sinlateralYsinbuscadorSuperior">
    <tiles:put name="title" type="string">
        <bean:message key="title.Comun"/>
    </tiles:put>
    	<tiles:put name="codigo-head" type="string">
    		<script type="text/javascript" src="<rewrite:rewrite url="static/js/mootools.js"/>"></script>
			<script type="text/javascript" src="<rewrite:rewrite url="static/js/calendar.rc4.js"/>"></script>
			<script type="text/javascript" src="<rewrite:rewrite url="static/js/busqueda_avanzada.js"/>"></script>
		</tiles:put>
    
    <tiles:put name="body" type="string">
		<%@ include file="/taglib-imports.jspf" %>
		<!-- ATENCION!! RREMPLAZAR ESTA CAPA DE LA  PLANTILLA DE CONTENIDO ("plantilla_contenido") POR LA QUE SE DESEE EN LA PLANTILLA BASE  -->
		<!-- Inicio plantilla contenido  -->
		<div class="plantilla_contenido">
		<analytic:googleAnalytic />
			<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
			<jsp:include page="/layout/messages.jsp" flush="true" />
			<h2><bean:message key="configurar.avanzado.cabecera"/></h2>
			<form method="post" name="formularioAvanzado" action="<html:rewrite action="/BuscarAvanzadoCU/MostrarFormularioAvanzadoAnalizaPulsacion"/>" >
				<!--  INICIO GLOBO GRIS   -->
				<input type="hidden" name="contPropsContenido" value="${form.contPropsContenido}"/>
				<input type="hidden" name="contAreasCurric" value="${form.contAreasCurric}"/>
				<input type="hidden" name="contAmbito" value="${form.contAmbito}"/>
				<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
				<input type="hidden" name="tipoBusqueda" value="${form.tipoBusqueda}"/>
				<input type="hidden" name="enlaceTaxSelec" value="${form.enlaceTaxSelec}">
				<input type="hidden" name="formularioInicial" value="true">
				<div class="globo_gris" >
					<div class="globo_gris_01">
						<div class="globo_gris_02">
							<div class="globo_gris_03">
								<!--  INICIO CAJA DE FORMULARIO   -->
								<div id="formulario" >
							 		<div class="fila_de_tabla">
			  							<div class="contenedor_izquierda">			  							
			  								<div class="text"><label for="<bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.buscar"/>"><bean:message key="configurar.avanzado.buscar.buscarDosPuntos"/></label></div>
											</div>
											<div class="contenedor_derecha">
											<div class="text"><input name="buscContenido" value="${form.buscContenido}" onkeypress="return focusSubmit(event)" onblur="this.style.backgroundColor='#e1e1e1'" id="nombreGrupo" type="text" title="<bean:message key="configurar.avanzado.introduzcaTextoBuscar"/>"  /></div>
										</div>
										<div class="linea_separadora"></div>
									</div>
									<!-- ******************************************************************* -->
									<!-- ************************* TAXONOMIAS******************************* -->
									<!-- ******************************************************************* -->
									<div class="fila_de_tabla">
					  					<div class="contenedor_izquierda">					  					
					  						<div class="text"><label for="areaC"><bean:message key="taxonomias.inicio.taxonomia"/>:</label></div>
					 					</div>
										<div class="contenedor_derecha" >
											<div class="text">
												<table border="1" cellpadding="0" cellspacing="0" style="font-size:100%" width="90%">
													<tr>
														<td align="left">
															<table border="0" cellpadding="0" cellspacing="0" width="100%">
																<tr>
																	<td colspan="2"><label for="Taxonomia01" class="oculto"><bean:message key="taxonomias.inicio.taxonomia"/>:</label>
																		<html:select name="form" property="taxonomias" titleKey="taxonomias.inicio.taxonomia" styleId="Taxonomia01">
																				<option value="" class="oscura"><bean:message key="configurar.avanzado.todas"/></option>
																				<html:optionsCollection name="form" property="taxonomiasBackingList" label="label" value="value"/>
																		</html:select>
																	</td>
																	<td valign="top" align="right"><input class="boton_125" type="submit" title="<bean:message key="configurar.avanzado.taxonomias.aniadir"/>" name="pulsacion" value="<bean:message key="configurar.avanzado.taxonomias.aniadir"/>" style="top:0;height:22px;margin:0"/></td>		
																</tr>
															</table>	
														</td>
													</tr>
													<logic:notEmpty name="form" property="rutasTax">
														<fmt:formatNumber var="contIndices" value="0" type="number"/>
														<tr>
															<td colspan="2">
																<table border="0" cellpadding="0" cellspacing="0" width="100%" class="inv_taxo">
																	<logic:iterate id="ruta"  name="form" property="rutasTax">
																		<c:if test="${contIndices%2==0}">
												 		    				<tr class="gris_01">
				        														<td>
				   																	<html:multibox name="form" styleId="taxSelec" property="taxSelec" value="${contIndices}" styleClass="check03"/>
				   																</td>	
												        			  			<td class="taxo"><span class="sola_rea2" title="${ruta.vocabName}->${ruta.valorTax}" >${ruta.valorTax}</span></td>
				      										 				</tr>
			       														</c:if>
																		<c:if test="${contIndices%2!=0}">
												        					<tr>
																				<td>
					   																<html:multibox name="form" property="taxSelec" styleClass="check03" value="${contIndices}"/>
				   																</td>	
												        			  			<td class="taxo"><span class="sola_rea2" title="${ruta.vocabName}->${ruta.valorTax}" >${ruta.valorTax}</span></td>
													 				 		</tr> 	
													    				</c:if>
			       														<c:set var="contIndices" value="${contIndices+1}"/>
																	</logic:iterate>
																</table>
															</td>
														</tr>		
														<tr>
															<td>&nbsp;</td>
															<td valign="top" align="right"><input class="boton_125"  id="Eliminar" type="submit" name="pulsacion" style="top:0;height:22px;margin:0" alt="<bean:message key="configurar.avanzado.taxonomias.eliminar"/>" value="<bean:message key="configurar.avanzado.taxonomias.eliminar"/>" /></td>		
														</tr>							          	          
													</logic:notEmpty>
												</table>
											</div>
										</div>
										<div class="linea_separadora"></div>
										<br class="oculto" />
									</div>
									<!-- ******************************************************************* -->
									<!-- ********************* FIN TAXONOMIAS******************************* -->
									<!-- ******************************************************************* -->
									<!-- -->
									<!-- -->
									<div class="fila_de_tabla" id="solo">
										<div class="contenedor_izquierda">
											<div class="text"><label for="nivel_agregacion"><bean:message key="listar.odecu.mostrar.resultados.detalles.nivelAgregacion"/>:</label>
											</div>
										</div>
										<div class="contenedor_derecha">
											<div class="text">
												<bean:define id="tituloSeleccioneNivelAgregacion"><bean:message key="configurar.avanzado.atributo.nivelAgregacion"/></bean:define>
												<html:select name="form" property="nivelAgreg" title="${tituloSeleccioneNivelAgregacion}" styleId="nivelAgreg">
													<option value="" class="oscura"><bean:message key="configurar.avanzado.todos"/></option>
													<html:optionsCollection name="form"  property="nivelAgregBackingList" label="label" value="value"/>
												</html:select>
											</div>
										</div>
										<div class="linea_separadora"></div>
										<br class="oculto" />
									</div>
									<!-- -->
									<div class="fila_de_tabla">
										<div class="contenedor_izquierda">
											<div class="text">
												<label for="tipoFormato"><bean:message key="configurar.avanzado.atributo.tipoFormato"/>:</label>
											</div>
										</div>
										<div class="contenedor_derecha">
											<div class="text">
												<bean:define id="tituloSeleccioneTipoFormato"><bean:message key="configurar.avanzado.atributo.seleccionar.tipoFormato"/></bean:define>
												<html:select name="form" property="formato" title="${tituloSeleccioneTipoFormato}" styleId="tipoFormato">
													<option value="" class="oscura"><bean:message key="configurar.avanzado.todos"/></option>
													<html:optionsCollection name="form"  property="formatoBackingList" label="label" value="value"/>
												</html:select>
											</div>
										</div>
										<div class="linea_separadora">
										</div><br class="oculto"/>
									</div>
									<!-- -->
									<div class="fila_de_tabla">
										<div class="contenedor_izquierda">
											<div class="text">
												<label for="tipoLicencia"><bean:message key="configurar.avanzado.atributo.licencia"/>:</label>
											</div>
										</div>
										<div class="contenedor_derecha">
											<div class="text">
												<bean:define id="tituloSeleccioneLicencia"><bean:message key="configurar.avanzado.atributo.seleccionar.tipoFormato"/></bean:define>
												<html:select name="form" property="licencia" title="${tituloSeleccioneLicencia}" styleId="tipoLicencia">
													<option value="" class="oscura"><bean:message key="configurar.avanzado.todos"/></option>
													<html:optionsCollection name="form"  property="licenciaBackingList" label="label" value="value"/>
												</html:select>
											</div>
										</div>
										<div class="linea_separadora">
										</div><br class="oculto"/>
									</div>
									<!--  -->
									<fieldset class="tipo ft_centrada">
										<input class="boton_125" style="height:20px;" type="submit" id="pulsacion" name="pulsacion" value="<bean:message key="configurar.avanzado.buscar.buscar"/>" />
									</fieldset>
									<br />
								</div>
								<!--  FIN CAJA DE FORMULARIO   -->
							</div>
						</div>
					</div>
				</div>
				<!--  FIN GLOBO GRIS   -->
				<logic:equal name="form" property="sinResult" value="true">
					<div class="parrafo_comun" id="separacion">
						<bean:message key="listar.odecu.mostrar.resultados.quiso.sinSugerencias"/><br />
						<bean:message key="listar.odecu.mostrar.resultados.quiso.sinTesauros"/><br />
						<bean:message key="listar.odecu.mostrar.resultados.quiso.sinResultados"/>:
						<ul id="error">
							<li><bean:message key="listar.odecu.mostrar.resultados.quiso.comprobarSintaxis"/></li>
							<li><bean:message key="listar.odecu.mostrar.resultados.quiso.otraPalabra"/></li>
							<li><bean:message key="listar.odecu.mostrar.resultados.quiso.sinonimo"/></li>
						</ul>
						<br class="oculto"/>
					</div>
				</logic:equal>
				<logic:equal name="form" property="sinResult" value="false">
					<p class="parrafo_comun" id="separacion3"><bean:message key="configurar.avanzado.acotarBusqueda"/></p>
				</logic:equal>	
				
				<!--  INICIO GLOBO GRIS   -->
				<div class="globo_gris uno_b" >
					<div class="globo_gris_01">
						<div class="globo_gris_02">
							<div class="globo_gris_03">
								<!--  INICIO CAJA DE FORMULARIO   -->
								<div class="caja_dinamica" ><a class="desplegado" id="pm1" href="#" onclick="expandirCaja('m1');return false;"><br class="falso" /><strong id="dm1" ><bean:message key="configurar.avanzado.texto.verCamposbusqueda"/></strong></a><h3><bean:message key="configurar.avanzado.texto.porPropiedadesContenido"/></h3><em>(<bean:message key="configurar.avanzado.numeroFiltrosSeleccionadosTiene"/>&nbsp;<label id="contadorPropiedades" >${form.contPropsContenido}</label>&nbsp;
									<c:if test="${form.contPropsContenido==1 }">
							    		<bean:message key="configurar.avanzado.numeroFiltroSeleccionado"/>)</em>
									</c:if>
								    <c:if test="${form.contPropsContenido!=1 }">
								      	<bean:message key="configurar.avanzado.numeroFiltrosSeleccionados"/>)</em>
								    </c:if>
								</div>
								<div id="m1" class="caja_cerrada" >
									<br />
									<div class="formu" >
										<div class="fila_de_tabla">
											<div class="contenedor_izquierda">
												<div class="text"><label for="idODE"><bean:message key="configurar.avanzado.atributo.identificador"/>:</label>
												</div>
											</div>
											<div class="contenedor_derecha">
												<div class="text">
													<input name="idODE" value="${form.idODE}" onkeypress="return focusSubmit(event)" onblur="this.style.backgroundColor='#e1e1e1'" id="idODE" type="text" title="<bean:message key="configurar.avanzado.atributo.introducir.edad"/>"  />
												</div>
											</div>
											<div class="linea_separadora"></div>
											<br class="oculto" />
										</div>
										<div class="fila_de_tabla">
											<div class="contenedor_izquierda">
												<div class="text"><label for="tipoRecurso"><bean:message key="configurar.avanzado.atributo.recurso"/>:</label>
												</div>
											</div>
											<div class="contenedor_derecha">
												<div class="text">
													<bean:define id="tituloSeleccioneTipoRecurso"><bean:message key="configurar.avanzado.atributo.seleccionar.tipoRecurso"/></bean:define>
													<html:select name="form" property="recurso" title="${tituloSeleccioneTipoRecurso}" styleId="tipoRecurso">
														<option value="" class="oscura"><bean:message key="configurar.avanzado.todos"/></option>
														<html:optionsCollection name="form"  property="recursoBackingList" label="label" value="value"/>
													</html:select>
												</div>
											</div>
											<div class="linea_separadora"></div>
											<br class="oculto" />
										</div>
										<div class="fila_de_tabla">
											<div class="contenedor_izquierda">
												<div class="text"><label for="Idioma"><bean:message key="listar.odecu.mostrar.resultados.detalles.idioma"/>:</label>
												</div>
											</div>
											<div class="contenedor_derecha">
												<div class="text">
													<bean:define id="tituloSeleccioneTipoIdioma"><bean:message key="configurar.avanzado.atributo.seleccionar.tipoIdioma"/></bean:define>
													<html:select name="form" property="idiomBusc" title="${tituloSeleccioneTipoIdioma}">
														<html:optionsCollection name="form"  property="idiomBuscBackingList" label="label" value="value"/>
													</html:select>
												</div>
											</div>
											<div class="linea_separadora">
											</div><br class="oculto"/>
										</div>
										<!-- -->
										<div class="fila_de_tabla">
											<div class="contenedor_izquierda">
												<div class="text"><label for="tipoProceso"><bean:message key="configurar.avanzado.atributo.procesoCognitivo"/>:</label>
												</div>
											</div>
											<div class="contenedor_derecha">
												<div class="text">
													<bean:define id="tituloSeleccioneTipoProcesoCognitivo"><bean:message key="configurar.avanzado.atributo.seleccionar.tipoProcesoCognitivo"/></bean:define>
													<html:select name="form" property="procesoCognitivo" title="${tituloSeleccioneTipoProcesoCognitivo}" styleId="procesoCognitivo">
														<option value="" class="oscura"><bean:message key="configurar.avanzado.todos"/></option>
														<html:optionsCollection name="form"  property="procesoCognitivoBackingList" label="label" value="value"/>
													</html:select>
												</div>
											</div>
											<div class="linea_separadora">
											</div><br class="oculto"/>
										</div>
										<div class="fila_de_tabla">
											<div class="contenedor_izquierda">
												<div class="text"><label for="tipoContexto"><bean:message key="configurar.avanzado.atributo.contexto"/>:</label>
												</div>
											</div>
											<div class="contenedor_derecha">
												<div class="text">
													<bean:define id="tituloSeleccioneTipoContexto"><bean:message key="configurar.avanzado.atributo.seleccionar.tipoContexto"/></bean:define>
													<html:select name="form" property="contexto" title="${tituloSeleccioneTipoContexto}" styleId="tipoContexto">
														<option value="" class="oscura"><bean:message key="configurar.avanzado.todos"/></option>
														<html:optionsCollection name="form"  property="contextoBackingList" label="label" value="value"/>
													</html:select>
												</div>
											</div>
											<div class="linea_separadora">
											</div><br class="oculto"/>
										</div>
										<div class="fila_de_tabla">
											<div class="contenedor_izquierda">
												<div class="text"><label for="edad"><bean:message key="configurar.avanzado.atributo.edad"/>:</label>
												</div>
											</div>
											<div class="contenedor_derecha">
												<div class="text">
													<input name="edad" value="${form.edad}" onkeypress="return focusSubmit(event)" onblur="this.style.backgroundColor='#e1e1e1'" id="edad" type="text" title="<bean:message key="configurar.avanzado.atributo.introducir.edad"/>"  />
												</div>
											</div>
											<div class="linea_separadora">
											</div><br class="oculto"/>
										</div>
										<div class="fila_de_tabla">
											<div class="contenedor_izquierda">
												<div class="text"><label for="autor"><bean:message key="configurar.avanzado.atributo.autor"/>:</label>
												</div>
											</div>
											<div class="contenedor_derecha">
												<div class="text">
													<input name="autor" value="${form.autor}" onkeypress="return focusSubmit(event)" onblur="this.style.backgroundColor='#e1e1e1'" id="autor" type="text" title="<bean:message key="configurar.avanzado.atributo.introducir.autor"/>"  />
												</div>
	
											</div>
											<div class="linea_separadora">
											</div><br/>
										</div>
										
							
										
								  		<bean:define id="nomMeses"><bean:message key="configurar.avanzado.atributo.nombresMeses"/></bean:define>
										<bean:define id="nomDias"><bean:message key="configurar.avanzado.atributo.nombresDias"/></bean:define>
										<bean:define id="offSet"><bean:message key="offset"/></bean:define>
				
										<div class="fila_de_tabla">
					  						<div class="contenedor_izquierda">
 													<div class="text"><label for="fecha_publi"><bean:message key="configurar.avanzado.atributo.fechaPublicacion"/>:</label></div>
						 					</div>
											<div class="contenedor_derecha" id="cont_esp_fech">
												<div class="text">
													<label class="oculto" for="fecha_publi"><bean:message key="configurar.avanzado.atributo.fechaPublicacionDia"/>:</label>
													<input onkeypress="return focusSubmit(event)" name="diaPublic" value="${form.diaPublic}" class="fechazo_02" onblur="this.style.backgroundColor='#e1e1e1'" maxlength="2" id="diaPublic" type="text" title="<bean:message key="configurar.avanzado.atributo.introducir.diaFechaPublicacion"/>"  />
													<label class="oculto" for="fecha_mes_fin"><bean:message key="configurar.avanzado.atributo.fechaPublicacionMes"/>:</label>
													<input name="mesPublic" maxlength="2" onkeypress="return focusSubmit(event)" value="${form.mesPublic}" class="fechazo_02" onblur="this.style.backgroundColor='#e1e1e1'" id="mesPublic" type="text" title="<bean:message key="configurar.avanzado.atributo.introducir.mesFechaPublicacion"/>"  />
													<label class="oculto" for="fecha_anio_fin"><bean:message key="configurar.avanzado.atributo.fechaPublicacionAnyo"/>:</label>	
													<input name="anyoPublic" maxlength="4" onkeypress="return focusSubmit(event)" value="${form.anyoPublic}" class="fechazo_02" onblur="this.style.backgroundColor='#e1e1e1'" id="anyoPublic" type="text" title="<bean:message key="configurar.avanzado.atributo.introducir.anyoFechaPublicacion"/>"  />
												</div>
											</div>
											<div class="linea_separadora">
											</div>
											<br class="oculto" />
										</div>
										<!-- -->
										<!-- -->	
										<div class="fila_de_tabla">
											<div class="contenedor_izquierda">
												<div class="text"><label for="secuencia"><bean:message key="configurar.avanzado.atributo.conSinSecuencia"/>:</label>
												</div>
											</div>
											<div class="contenedor_derecha">
												<div class="text">
													<bean:define id="tituloSeleccioneTipoSecuencia"><bean:message key="configurar.avanzado.atributo.introducir.secuencia"/></bean:define>
													<html:select name="form" property="c_s_secuencia" title="${tituloSeleccioneTipoSecuencia}" styleId="tipoSecuencia">
														<option value="" class="oscura"><bean:message key="configurar.avanzado.todas"/></option>
														<html:optionsCollection name="form"  property="c_s_secuenciaBackingList" label="label" value="value"/>
													</html:select>
												</div>
											</div>
											<div class="linea_separadora">
											</div><br class="oculto"/>
										</div>
										<!-- -->
										<div class="fila_de_tabla">
											<div class="contenedor_izquierda">
												<div class="text"><label for="valor"><bean:message key="listar.odecu.mostrar.resultados.detalles.valoracion"/>:</label>
												</div>
											</div>
											<div class="contenedor_derecha">
												<div class="text">
													<bean:define id="tituloSeleccioneTipoValoracion"><bean:message key="configurar.avanzado.atributo.valoracion"/></bean:define>
													<html:select name="form" property="valoracion" title="${tituloSeleccioneTipoValoracion}" styleId="tipoValoracion">
														<option value="" class="oscura"><bean:message key="configurar.avanzado.todas"/></option>
														<html:optionsCollection name="form"  property="valoracionBackingList" label="label" value="value"/>
													</html:select>
												</div>
											</div>
											<div class="linea_separadora">
											</div><br class="oculto"/>
										</div>
										<div class="fila_de_tabla">
											<div class="contenedor_izquierda">
												<div class="text"><label for="valor"><bean:message key="listar.odecu.mostrar.resultados.detalles.destinatarios"/>:</label>
												</div>
											</div>
											<div class="contenedor_derecha">
												<div class="text">
													<bean:define id="tituloSeleccioneDestinatarios"><bean:message key="configurar.avanzado.atributo.destinatarios"/></bean:define>
													<html:select name="form" property="destinatarios" title="${tituloSeleccioneDestinatarios}" styleId="destinatarios">
														<option value="" class="oscura"><bean:message key="configurar.avanzado.todos"/></option>
														<html:optionsCollection name="form"  property="destinatariosBackingList" label="label" value="value"/>
													</html:select>
												</div>
											</div>
											<div class="linea_separadora">
											</div><br class="oculto"/>
										</div>
									</div>
								</div>
								<br />
								<!--  FIN CAJA DE FORMULARIO   -->
							</div>
						</div>
					</div>
				</div>
				<!--  FIN GLOBO GRIS   -->
				<!--  INICIO GLOBO GRIS   -->
				<div class="globo_gris uno_b" >
					<div class="globo_gris_01">
						<div class="globo_gris_02">
							<div class="globo_gris_03">
								<!--  INICIO CAJA DE FORMULARIO   -->
								<div class="caja_dinamica" ><a class="desplegado" id="pm4" href="#" onclick="expandirCaja('m4');return false;"><br class="falso" /><strong id="dm4" ><bean:message key="configurar.avanzado.texto.verCamposbusqueda"/></strong></a><h3><bean:message key="configurar.avanzado.texto.porTesauro"/> </h3> <em>(<bean:message key="configurar.avanzado.numeroFiltrosSeleccionadosTiene"/> ${form.contTesauros} 
									<c:if test="${form.contTesauros==1 }">
								     	<bean:message key="configurar.avanzado.numeroFiltroSeleccionado"/>)</em>
								    </c:if>
								    <c:if test="${form.contTesauros!=1 }">
							    	  	<bean:message key="configurar.avanzado.numeroFiltrosSeleccionados"/>)</em>
								    </c:if>
								</div>
								<div id="m4" class="caja_cerrada" ><br />
									<!--  INICIO CAJA DE FORMULARIO   -->
									<div id="formulario_02" >
									<!--  INICIO GLOBO Blanco   -->
										<div class="globo_blanco" >
											<div class="globo_blanco_01">
												<div class="globo_blanco_02">
													<div class="globo_blanco_03">
														<input type="hidden" name="idTesauro" value="${form.idTesauro}"/>
														<logic:empty name="form" property="nomTesauros">
															<p class="parrafo_comun" ><bean:message key="configurar.avanzado.texto.tesauro.aniadir"/>:</p>
														</logic:empty>
														<logic:notEmpty name="form" property="nomTesauros">
															<p class="parrafo_comun" ><bean:message key="configurar.avanzado.texto.tesauro.eliminar"/>:</p>
														</logic:notEmpty>
														<!--  INICIO Criterios Nivel Educativo   -->						
										 				<div class="caja_tabla bordeada"  >
															<table border="1" class="generica_02" id="portapapeles" cellpadding="0" cellspacing="0" width="100%" summary="Tabla de Criterios Nivel Educativo">
																<caption><strong><bean:message key="configurar.avanzado.texto.tesauro.tabla"/></strong></caption>
																<tr class="tr_beige">
																	<td valign="top" class="td_larga">${form.nomTesauros}</td>
																</tr>
															</table>
														</div>
														<fieldset class="tipo ft_centrada">
															<logic:empty name="form" property="nomTesauros">
																<input class="boton_125"  type="submit"  name="pulsacion" value="<bean:message key="configurar.avanzado.tesauros.aniadir"/>" />
																<input type="hidden" id="nomTesauros" name="nomTesauros" value="${form.nomTesauros}"/>
															</logic:empty>
															<logic:notEmpty name="form" property="nomTesauros">
																<input class="boton_125"  type="submit"  name="pulsacion" value="<bean:message key="configurar.avanzado.tesauros.eliminar"/>" />
																<input type="hidden" id="nomTesauros" name="nomTesauros" value="${form.nomTesauros}"/>
															</logic:notEmpty>
														</fieldset>
														<br class="limpiar" />
														<!--  FIN Criterios Nivel Educativo   -->	
													</div>
												</div>
											</div>
										</div>
										<!--  Fin GLOBO Blanco   -->
									</div>
									<!--  FIN CAJA DE FORMULARIO   -->
								</div>
								<br />
							</div>
						</div>
					</div>
				</div>
				<!--  INICIO GLOBO GRIS   -->
				<logic:equal name="form" property="mostrarAmbito" value="true">
				<div class="globo_gris" >
					<div class="globo_gris_01">
						<div class="globo_gris_02">
							<div class="globo_gris_03">
								<!--  INICIO CAJA DE FORMULARIO   -->
								<div class="caja_dinamica" ><a class="desplegado" id="pm3" href="#" onclick="expandirCaja('m3');return false;"><br class="falso" /><strong id="dm3" ><bean:message key="configurar.avanzado.texto.verCamposbusqueda"/></strong></a><h3><bean:message key="configurar.avanzado.texto.porAmbito"/></h3> <em>(<bean:message key="configurar.avanzado.numeroFiltrosSeleccionadosTiene"/>&nbsp; <label id="contadorComunidades">${form.contAmbito}</label>&nbsp; 
									<c:if test="${form.contAmbito==1 }">
								     	<bean:message key="configurar.avanzado.numeroFiltroSeleccionado"/>)</em>
								    </c:if>
			    					<c:if test="${form.contAmbito!=1 }">
								      	<bean:message key="configurar.avanzado.numeroFiltrosSeleccionados"/>)</em>
								    </c:if>
								</div>
								<div id="m3" class="caja_cerrada" ><br />
								 <div class="formu" >
								  <!-- INICIO TABLA  -->
									<div class="caja_de_tabla_invisible">
									<table cellspacing="0" cellpadding="0" border="1" width="100%"  summary="Asignacion Comunidades">
										<tr>
											<td>
												<!--impares y 0-->
												<table cellspacing="0" cellpadding="0" border="1" width="50%"  summary="Asignacion Comunidades">
										    		<fmt:formatNumber var="contIndices" value="0" type="number"/>
													<logic:iterate id="comunidad"   name="form" property="comunidades">
														<!-- el primer elemento -->
														<c:if test="${contIndices==0}">
	  														<tr height="30">
	   															<td>
	   																<html:multibox name="form" styleId="comunidad${contIndices}" property="comuSelec" value="${comunidad.id}" onclick="marcarTodas();"/>
										         			 	</td>	
										           			  	<td class="td_largo_09" valign="top"><label for="${comunidad.id}">${comunidad.nodo}</label></td>
   															</tr>
									 					</c:if>
									 					<c:if test="${contIndices==1}">
  															<tr height="30">
	   															<td>
	   																<html:multibox name="form" styleId="comunidad${contIndices}"  property="comuSelec" value="${comunidad.id}" onclick="comprobarTodas();clickEnCheck();"/>
										         			 	</td>	
										           			  	<td class="td_largo_09" valign="top"><label for="${comunidad.id}">${comunidad.nodo}</label></td>
	   														</tr>
									 					</c:if>
									 					<!-- fin primer elemento -->
									 					<!--  mayor de 0   -->		
									 					<c:if test="${contIndices>1}">
														<!--  impar   -->		
															<c:if test="${contIndices%2!=0}">
									        					<tr height="30">
																	<td>
   																		<html:multibox name="form" styleId="comunidad${contIndices}" property="comuSelec" value="${comunidad.id}" onclick="comprobarTodas();"/>								          	          											         			 			</td>					
									        			  			<td class="td_largo_09" valign="top"><label for="${comunidad.id}">${comunidad.nodo}</label></td>
										 				 		</tr> 	
										    				</c:if>
										    			</c:if>
														<c:set var="contIndices" value="${contIndices+1}"/>
													</logic:iterate>
												</table>
											</td>
											<td>		
												<table cellspacing="0" cellpadding="0" border="1" width="50%"  summary="Asignacion Comunidades">
													<fmt:formatNumber var="contIndices" value="0" type="number"/>
													<!--  par -->
													<tr height="30">
   														<td>&nbsp;</td>
  											 			<td>&nbsp;</td>
 										 			</tr>
									 		    	<logic:iterate id="comunidad"   name="form" property="comunidades">
									 		    		<c:if test="${contIndices!=0}">
									 		    			<c:if test="${contIndices%2==0}">
									 		    				<tr height="30">
	        														<td>
   																		<html:multibox name="form" styleId="comunidad${contIndices}" property="comuSelec" value="${comunidad.id}" onclick="comprobarTodas();"/>									          	          
									         			 			</td>					
									        			  			<td class="td_largo_09" valign="top"><label for="${comunidad.id}">${comunidad.nodo}</label></td>
	      										 				</tr>
       														</c:if>
								  						</c:if>
 														<!--  end mayor 0 -->										
														<c:set var="contIndices" value="${contIndices+1}"/>
													</logic:iterate>
												</table>
											</td>
										</tr>
									</table>
										</div>
									</div>
								</div>
								<br /><!--  FIN CAJA DE FORMULARIO   -->
							</div>
						</div>
					</div>
				</div>
				</logic:equal>
				<!-- Inicio Botones  -->
				<fieldset class="tipo">
					<input class="boton_125_de_2_izq" name="pulsacion" type="submit"  value="<bean:message key="configurar.avanzado.buscar.limpiar"/>" />
					<input type="submit" class="boton_125_de_2" name="pulsacion" value="<bean:message key="configurar.avanzado.buscar.buscar"/>"  /><br />
				</fieldset>
				<!-- Fin Botones  -->
				<!-- Fin Botones  -->
			</form>
			
			<!--  Establecer el cursor por defecto en la caja del buscador   -->
		</div>
		<div id="m6" class="caja_cerrada" ></div>
	<!-- Fin plantilla contenido  -->
	</tiles:put>
	<tiles:put name="end-body" type="string">

   <script type="text/javascript">

      window.addEvent('domready', function() {

                  jsMeses = "${nomMeses}"; arrayMeses = jsMeses.split(",");

                  jsDias = "${nomDias}"; arrayDias = jsDias.split(",");

            		myCal1 = new Calendar({ anyoPublic: { diaPublic: 'd', mesPublic: 'm', anyoPublic: 'Y' }}, { direction: 0, tweak: {x: 6, y: 0}, offset: ${offSet},months: arrayMeses ,days: arrayDias });
	
         
                  });

      </script>

   </tiles:put>

	
</tiles:insert>

<SCRIPT LANGUAGE="JavaScript">
	var ocultar="<bean:message key='configurar.avanzado.ocultar'/>";
	var ver="<bean:message key='configurar.avanzado.ver'/>";
	var propiedades = getChildsCount();
	var checkboxes= document.getElementsByTagName("input");
	var mostrarAmbito="${form.mostrarAmbito}";
	if(mostrarAmbito=="true"){
		var comunidades = 0;
		var totalComunidades = "${ fn:length(form.comunidades)}";
		comunidades = contarComunidades();
		document.getElementById("contadorComunidades").innerHTML=comunidades;

		if(comunidades<(parseInt(totalComunidades)-parseInt(2))){
			expandirCaja('m3');
		}
	}
	if(propiedades>1){
		expandirCaja('m1');
	}
	
	if(trim(document.getElementById("nomTesauros").value)!=""){
		expandirCaja('m4');
	}
	document.getElementById("contadorPropiedades").innerHTML=propiedades;
	
	document.getElementById("m6").className = 'caja_abierta';
	document.forms[0].buscContenido.focus();
	
	function contarComunidades(){
		var cont = 0;
			if(parseInt(totalComunidades)>2){
			for (var x=2; x < parseInt(totalComunidades); x++){
				if(document.getElementById("comunidad"+x).checked){
					cont=cont+1;
			    }
			}
		}
		return cont;
	} 
	
	function expandirCaja (i) 
	{
		if (document.getElementById(i).className=='caja_abierta') 
		{
			document.getElementById('p' + i).className = 'caja_cerrada';
			document.getElementById('d'+i).innerHTML=ver;
			document.getElementById(i).className = 'caja_cerrada';
			document.getElementById("contadorPropiedades").innerHTML=getChildsCount();
			document.getElementById("contadorComunidades").innerHTML=contarComunidades();
		}
		else 
		{
			document.getElementById('p' + i).className = 'caja_abierta';
			document.getElementById('d'+i).innerHTML=ocultar;
			document.getElementById(i).className = 'caja_abierta';
		}
	}
	
	function marcarTodas()
	{
	 	var checkboxes= document.getElementsByTagName("input");
		var totalComunidades = "${ fn:length(form.comunidades)}";
		

		if(document.getElementById("comunidad"+0).checked){
			for (var x=0; x < totalComunidades; x++){
				document.getElementById("comunidad"+x).checked=true;
      		 }
		}else{
				for (var x=1; x < totalComunidades; x++){
			   	 document.getElementById("comunidad"+x).checked=false;
			   	 }
			
		}
	}

	function comprobarTodas(){
		var vacio=false;
		var checkboxes= document.getElementsByTagName("input");
		var totalComunidades = "${ fn:length(form.comunidades)}";
		for (var x=0; x < totalComunidades; x++){
				if(document.getElementById("comunidad"+x).checked==true)
				{
			   	 	vacio=true;
				}
		}
		if(vacio==true && document.getElementById("comunidad"+0).checked == true) document.getElementById("comunidad"+0).checked = false;
	}
	
	function focusSubmit(evt) {
		evt = (evt) ? evt : event;
		var charCode = (evt.charCode) ? evt.charCode :
		((evt.which) ? evt.which : evt.keyCode);
		if (charCode == 13 || charCode == 3) {
			document.getElementById("pulsacion").focus();
			document.formularioAvanzado.submit();
			return false;
		}
		return true;
	}
</SCRIPT>