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


<logic:notEqual name="form" property="sinResult" value="true">
	<tiles:insert definition="layout-menu-1-enriquecido">
	    
	    <tiles:put name="title" type="string">
	        <bean:message key="title.Comun"/>
	    </tiles:put>
	    
		<tiles:put name="body-principal" type="string">
			<%@ include file="/taglib-imports.jspf" %>
	
			<!-- PRINCIPAL   -->

				<analytic:googleAnalytic />
				
				<article id="buscador_tipo">
					<header>
						<h2 class="titulo"><bean:message key="encuentra.los.mejores.contenidos.educativos"/></h2>
					</header>
					
					<section id="buscador_avanzado">			
				
						<!-- CAPA NUEVA DE ERROR -->    
						<jsp:include page="/layout/messages.jsp" flush="true" />	
						<logic:equal name="form" property="sinResult" value="true">
							
							<logic:notEmpty name="form"	property="quisoDecir">
								<bean:message key="listar.odecu.mostrar.resultados.quiso.quisoDecir"/>:  
								<logic:iterate name="form" property="quisoDecir" id="sugerencia">
									<a href="<html:rewrite action="/BuscarAvanzadoCU/MostrarQuisoDecirAvanzadoBuscarQuisoDecir.do"/>?buscContenido=${sugerencia}&idiomBusc=${form.idioma}&amp;formato=${form.formato}&amp;recurso=${form.recurso}&amp;procesoCognitivo=${form.procesoCognitivo}&amp;contexto=${form.contexto}&amp;edad=${form.edad}&amp;autor=${form.autor}&amp;diaPublic=${form.diaPublic}&amp;mesPublic=${form.mesPublic}&amp;anyoPublic=${form.anyoPublic}&amp;c_s_secuencia=${form.c_s_secuencia}&amp;valoracion=${form.valoracion}&amp;enlaceTaxSelec=${form.enlaceTaxSelec}&amp;enlaceComuSelec=${form.enlaceComuSelec}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;idTesauro=${form.idTesauro}&amp;nomTesauros=${form.nomTesauros}&amp;nivelAgreg=${form.nivelAgreg}&amp;tipoBusqueda=${form.tipoBusqueda}&amp;destinatarios=${form.destinatarios}">${sugerencia}</a> 
								</logic:iterate>
								.<br />
							</logic:notEmpty>
							
							<logic:notEmpty name="form" property="tesauros">
								<bean:message key="listar.odecu.mostrar.resultados.quiso.tesauros"/>
								<logic:iterate name="form" property="tesauros" id="taxon"> 
									<a href="<html:rewrite action="/BuscarAvanzadoCU/MostrarQuisoDecirAvanzadoQuisoDecir.do"/>?idTesauroSug=${taxon.identificador}&idiomBusc=${form.idioma}&amp;formato=${form.formato}&amp;recurso=${form.recurso}&amp;procesoCognitivo=${form.procesoCognitivo}&amp;contexto=${form.contexto}&amp;edad=${form.edad}&amp;autor=${form.autor}&amp;diaPublic=${form.diaPublic}&amp;mesPublic=${form.mesPublic}&amp;anyoPublic=${form.anyoPublic}&amp;c_s_secuencia=${form.c_s_secuencia}&amp;valoracion=${form.valoracion}&amp;enlaceTaxSelec=${form.enlaceTaxSelec}&amp;enlaceComuSelec=${form.enlaceComuSelec}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;idTesauro=${form.idTesauro}&amp;nomTesauros=${form.nomTesauros}&amp;nivelAgreg=${form.nivelAgreg}&amp;tipoBusqueda=${form.tipoBusqueda}&amp;destinatarios=${form.destinatarios}">${taxon.nombre}</a> 
								</logic:iterate>
								<br />
							</logic:notEmpty>							
							
							<div class="tipofieldset clearfix" id="separacion">
								<strong><bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.resultadoBusqueda"/></strong>
								<div class="clearfix" id="separacion">
									<bean:message key="listar.odecu.mostrar.resultados.quiso.sinResultados"/>:
									<ul id="error">
										<li><bean:message key="listar.odecu.mostrar.resultados.quiso.comprobarSintaxis"/></li>
										<li><bean:message key="listar.odecu.mostrar.resultados.quiso.otraPalabra"/></li>
										<li><bean:message key="listar.odecu.mostrar.resultados.quiso.sinonimo"/></li>
									</ul>
									<br class="oculto"/>
								</div>
							</div>   
						</logic:equal>							
												
						<!-- FIN CAPA NUEVA DE ERROR -->    	
				
						<form method="post" name="formularioAvanzado" action="<html:rewrite action="/BuscarAvanzadoCU/MostrarFormularioAvanzadoAnalizaPulsacion"/>" >
			
							<input type="hidden" name="contPropsContenido" value="${form.contPropsContenido}"/>
							<input type="hidden" name="contAreasCurric" value="${form.contAreasCurric}"/>
							<input type="hidden" name="contAmbito" value="${form.contAmbito}"/>
							<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
							<input type="hidden" name="tipoBusqueda" value="${form.tipoBusqueda}"/>
							<input type="hidden" name="enlaceTaxSelec" value="${form.enlaceTaxSelec}">
							<input type="hidden" name="formularioInicial" value="true">
							
							<logic:equal name="form" property="sinResult" value="false">
								<p class="clearfix" id="separacion3"><bean:message key="configurar.avanzado.acotarBusqueda"/></p>
							</logic:equal>		   	
							<div class="tipofieldset clearfix" id="primer_fs"  >
	
								<h3><bean:message key="configurar.avanzado.cabecera"/></h3>
								<fieldset class="clearfix">
									<label for="<bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.buscar"/>"><bean:message key="configurar.avanzado.buscar.buscarDosPuntos"/></label>
									<input name="buscContenido" value="${form.buscContenido}" class="de_texto" onkeypress="return focusSubmit(event)" id="nombreGrupo" type="text" title="<bean:message key="configurar.avanzado.introduzcaTextoBuscar"/>"  />
								</fieldset>																		
	
								<fieldset class="clearfix">
									<label for="Taxonomia01"><bean:message key="taxonomias.inicio.taxonomia"/>:</label>
									<span class="caja_de_boton">
										<html:select name="form" property="taxonomias" titleKey="taxonomias.inicio.taxonomia" styleId="Taxonomia01" styleClass="select">
											<option value="" class="oscura"><bean:message key="configurar.avanzado.todas"/></option>
											<html:optionsCollection name="form" property="taxonomiasBackingList" label="label" value="value"/>
										</html:select>
    								</span>
									<input id="flotante_fs2" class="boton" style="display:inline-block !important;width: 105px;" type="submit" title="<bean:message key="configurar.avanzado.taxonomias.aniadir"/>" name="pulsacion" value="<bean:message key="configurar.avanzado.taxonomias.aniadir"/>" />
								</fieldset>
								
								
								<logic:notEmpty name="form" property="rutasTax">
									<fmt:formatNumber var="contIndices" value="0" type="number"/>
									
										<table border="0" cellpadding="0" cellspacing="0" width="100%" class="inv_taxo">
											<logic:iterate id="ruta"  name="form" property="rutasTax">
												<c:if test="${contIndices%2==0}">
						 		    				<tr class="gris_01">
   														<td>
															<html:multibox name="form" styleId="taxSelec" property="taxSelec" value="${contIndices}" styleClass="check03"/>
														</td>	
						        			  			<td class="taxo">
						        			  				<span class="sola_rea2" title="${ruta.vocabName}->${ruta.valorTax}" >${ruta.valorTax}</span>
						        			  			</td>
						        			  			<td>
															<input class="boton flotante_izquierda"  id="Eliminar" type="submit" name="pulsacion" style="top:0;height:22px;margin:0" alt="<bean:message key="configurar.avanzado.taxonomias.eliminar"/>" value="<bean:message key="configurar.avanzado.taxonomias.eliminar"/>" />		
														</td>
									 				</tr>
												</c:if>
												<c:if test="${contIndices%2!=0}">
						        					<tr>
														<td>
															<html:multibox name="form" property="taxSelec" styleClass="check03" value="${contIndices}"/>
														</td>	
						        			  			<td class="taxo">
						        			  				<span class="sola_rea2" title="${ruta.vocabName}->${ruta.valorTax}" >${ruta.valorTax}</span>
					        			  				</td>
					        			  				<td>
															<input class="boton flotante_izquierda"  id="Eliminar" type="submit" name="pulsacion" style="top:0;height:22px;margin:0" alt="<bean:message key="configurar.avanzado.taxonomias.eliminar"/>" value="<bean:message key="configurar.avanzado.taxonomias.eliminar"/>" />		
														</td>
							 				 		</tr> 	
							    				</c:if>
												<c:set var="contIndices" value="${contIndices+1}"/>
											</logic:iterate>
										</table>
									
																          	          
								</logic:notEmpty>
								
								<fieldset class="clearfix">
									<label for="nivel_agregacion"><bean:message key="listar.odecu.mostrar.resultados.detalles.nivelAgregacion"/>:</label>
									<span class="caja_de_boton">
										<!--
										<select class="select" name="tipoRecurso" title="Seleccione Tipo de Recurso" id="tipoRecurso">
											<option value="#" selected="selected">Seleccione</option>
											<option value="#" class="oscura">&nbsp;</option>
											<option value="#">Recurso XYZ</option>
											<option value="#" class="oscura">Recurso XYZ</option>
										</select>
										 
										-->
										<bean:define id="tituloSeleccioneNivelAgregacion"><bean:message key="configurar.avanzado.atributo.nivelAgregacion"/></bean:define>
										<html:select name="form" property="nivelAgreg" title="${tituloSeleccioneNivelAgregacion}" styleId="Taxonomia01" styleClass="select">
											<option value="" class="oscura"><bean:message key="configurar.avanzado.todos"/></option>
											<html:optionsCollection name="form" property="nivelAgregBackingList" label="label" value="value"/>
										</html:select>
									
									</span>
								</fieldset>
					
								<fieldset class="clearfix">
									<label for="tipoFormato"><bean:message key="configurar.avanzado.atributo.tipoFormato"/>:</label>
									<span class="caja_de_boton">
										<bean:define id="tituloSeleccioneTipoFormato"><bean:message key="configurar.avanzado.atributo.seleccionar.tipoFormato"/></bean:define>
										<html:select name="form" property="formato" title="${tituloSeleccioneTipoFormato}" styleId="select" styleClass="select">
											<option value="" class="oscura"><bean:message key="configurar.avanzado.todos"/></option>
											<html:optionsCollection name="form"  property="formatoBackingList" label="label" value="value"/>
										</html:select>
									</span>
								</fieldset>		
								
								<fieldset class="clearfix">
									<label for="tipoFormato"><bean:message key="configurar.avanzado.atributo.licencia"/>:</label>
									<span class="caja_de_boton">
										<bean:define id="tituloSeleccioneLicencia"><bean:message key="configurar.avanzado.atributo.seleccionar.licencia"/></bean:define>
										<html:select name="form" property="licencia" title="${tituloSeleccioneLicencia}" styleId="select" styleClass="select">
											<option value="" class="oscura"><bean:message key="configurar.avanzado.todos"/></option>
											<html:optionsCollection name="form"  property="licenciaBackingList" label="label" value="value"/>
										</html:select>
									</span>
								</fieldset>	
							</div>
			
							<!-- POR PROPIEDADES DE CONTENIDO   -->
							<div class="tipofieldset clearfix"  >
								<div class="caja_dinamica">
									<a class="desplegado" id="pm1" href="#" onclick="expandirCaja('m1', '<bean:message key='configurar.avanzado.ver'/>', '<bean:message key='configurar.avanzado.ocultar'/>');return false;"><br class="falso" /><strong id="dm1" ><bean:message key='configurar.avanzado.ver'/></strong></a>
									<h3><bean:message key="configurar.avanzado.texto.porPropiedadesContenido"/></h3>
									<em>(<bean:message key="configurar.avanzado.numeroFiltrosSeleccionadosTiene"/>&nbsp;
									<label id="contadorPropiedades" style="display:inline-block; float:none; width:auto;">${form.contPropsContenido}</label>&nbsp;
									<c:if test="${form.contPropsContenido==1 }">
							    		<bean:message key="configurar.avanzado.numeroFiltroSeleccionado"/>)</em>
									</c:if>
								    <c:if test="${form.contPropsContenido!=1 }">
								      	<bean:message key="configurar.avanzado.numeroFiltrosSeleccionados"/>)</em>
								    </c:if>
								
									<div name="propiedades" id="m1" class="caja_cerrada" >
										<div class="campos clearfix">
										
											<fieldset class="clearfix">
												<label for="idODE"><bean:message key="configurar.avanzado.atributo.identificador"/>:</label>
												<input name="idODE" class="de_texto" value="${form.idODE}"  onchange="calcularFiltroPropiedades()" onkeypress="return focusSubmit(event)" id="idODE" type="text" title="<bean:message key="configurar.avanzado.atributo.introducir.titulo"/>"  />
											</fieldset>
					
											<fieldset class="clearfix">
												<label for="tipoRecurso"><bean:message key="configurar.avanzado.atributo.recurso"/>:</label>
												<span class="caja_de_boton">
													<bean:define id="tituloSeleccioneTipoRecurso"><bean:message key="configurar.avanzado.atributo.seleccionar.tipoRecurso"/></bean:define>
													<html:select onchange="calcularFiltroPropiedades()" name="form" property="recurso" title="${tituloSeleccioneTipoRecurso}" styleId="tipoRecurso" styleClass="select">
														<option value="" class="oscura"><bean:message key="configurar.avanzado.todos"/></option>
														<html:optionsCollection name="form"  property="recursoBackingList" label="label" value="value"/>
													</html:select>
												</span>
											</fieldset>
											
											<fieldset class="clearfix">
												<label for="Idioma"><bean:message key="listar.odecu.mostrar.resultados.detalles.idioma"/>:</label>
												<span class="caja_de_boton">
													<bean:define id="tituloSeleccioneTipoIdioma"><bean:message key="configurar.avanzado.atributo.seleccionar.tipoIdioma"/></bean:define>
													<html:select onchange="calcularFiltroPropiedades()" name="form" property="idiomBusc" title="${tituloSeleccioneTipoIdioma}" styleId="tipoIdioma" styleClass="select">
														<html:optionsCollection name="form"  property="idiomBuscBackingList" label="label" value="value"/>
													</html:select>
												</span>
											</fieldset>
											
											<fieldset class="clearfix">
												<label for="tipoProceso"><bean:message key="configurar.avanzado.atributo.procesoCognitivo"/>:</label>
												<span class="caja_de_boton">
														<bean:define id="tituloSeleccioneTipoProcesoCognitivo"><bean:message key="configurar.avanzado.atributo.seleccionar.tipoProcesoCognitivo"/></bean:define>
														<html:select onchange="calcularFiltroPropiedades()" name="form" property="procesoCognitivo" title="${tituloSeleccioneTipoProcesoCognitivo}" styleId="procesoCognitivo" styleClass="select">
															<option value="" class="oscura"><bean:message key="configurar.avanzado.todos"/></option>
															<html:optionsCollection name="form"  property="procesoCognitivoBackingList" label="label" value="value"/>
														</html:select>
									    		</span>
											</fieldset>
																					
											<fieldset class="clearfix">
												<label for="tipoContexto"><bean:message key="configurar.avanzado.atributo.contexto"/>:</label>
												<span class="caja_de_boton">
													<bean:define id="tituloSeleccioneTipoContexto"><bean:message key="configurar.avanzado.atributo.seleccionar.tipoContexto"/></bean:define>
													<html:select onchange="calcularFiltroPropiedades()" name="form" property="contexto" title="${tituloSeleccioneTipoContexto}" styleId="tipoContexto" styleClass="select">
														<option value="" class="oscura"><bean:message key="configurar.avanzado.todos"/></option>
														<html:optionsCollection name="form"  property="contextoBackingList" label="label" value="value"/>
													</html:select>
									    		</span>
											</fieldset>
											
											<fieldset class="clearfix">
												<label for="edad"><bean:message key="configurar.avanzado.atributo.edad"/>:</label>
												<input onchange="calcularFiltroPropiedades()" name="edad" class="de_texto" value="${form.edad}" style="width:40px;" onkeypress="return focusSubmit(event)" id="edad" type="text" title="<bean:message key="configurar.avanzado.atributo.introducir.edad"/>"  />
											</fieldset>
	
											<fieldset class="clearfix">
												<label for="autor"><bean:message key="configurar.avanzado.atributo.autor"/>:</label>
												<input  onchange="calcularFiltroPropiedades()" name="autor" class="de_texto" value="${form.autor}" onkeypress="return focusSubmit(event)" id="autor" type="text" title="<bean:message key="configurar.avanzado.atributo.introducir.autor"/>"  />
											</fieldset>
																							
									  		<bean:define id="nomMeses"><bean:message key="configurar.avanzado.atributo.nombresMeses"/></bean:define>
											<bean:define id="nomDias"><bean:message key="configurar.avanzado.atributo.nombresDias"/></bean:define>
											<bean:define id="offSet"><bean:message key="offset"/></bean:define>
											<fieldset class="clearfix relativo">
												<!--
												<label class="oculto" for="fecha_publi"><bean:message key="configurar.avanzado.atributo.fechaPublicacionDia"/>:</label>
												<input onkeypress="return focusSubmit(event)" name="diaPublic" value="${form.diaPublic}" class="fechazo_02" maxlength="2" id="diaPublic" type="text" title="<bean:message key="configurar.avanzado.atributo.introducir.diaFechaPublicacion"/>"  />
												<label class="oculto" for="fecha_mes_fin"><bean:message key="configurar.avanzado.atributo.fechaPublicacionMes"/>:</label>
												<input name="mesPublic" maxlength="2" onkeypress="return focusSubmit(event)" value="${form.mesPublic}" class="fechazo_02" id="mesPublic" type="text" title="<bean:message key="configurar.avanzado.atributo.introducir.mesFechaPublicacion"/>"  />
												<label class="oculto" for="fecha_anio_fin"><bean:message key="configurar.avanzado.atributo.fechaPublicacionAnyo"/>:</label>	
												<input name="anyoPublic" maxlength="4" onkeypress="return focusSubmit(event)" value="${form.anyoPublic}" class="fechazo_02" id="anyoPublic" type="text" title="<bean:message key="configurar.avanzado.atributo.introducir.anyoFechaPublicacion"/>"  />
												-->
												<!-- 
												<label for="fecha_publi"><bean:message key="configurar.avanzado.atributo.fechaPublicacion"/>:</label>
												<input name="diaPublic" id="diaPublic" class="de_texto  date-pick" onfocus="limpiarTexto(this)" style="width:108px;"  title="Introduce Fecha" type="text" value="DD/MM/AAAA" />
												<input name="mesPublic" maxlength="2" value="${form.mesPublic}" id="mesPublic" type="hidden" />
												<input name="anyoPublic" maxlength="4" value="${form.anyoPublic}" id="anyoPublic" type="text" style="visibility: hidden;display: none;" />
												-->
												<label for="fecha_publi"><bean:message key="configurar.avanzado.atributo.fechaPublicacion"/>:</label>
												<c:choose>
												<c:when test="${!empty form.fechaPublicacionFormatoHumano}">
													<input  onchange="calcularFiltroPropiedades()"  id="fecha_publi" name="fechaPublicacionFormatoHumano" value="${form.fechaPublicacionFormatoHumano}" class="de_texto date-pick" onfocus="limpiarTexto(this)" style="width:108px;"  title="Introduce Fecha" type="text" />
												</c:when>
												<c:otherwise>
													<input id="fecha_publi" name="fechaPublicacionFormatoHumano" value="" class="de_texto date-pick" onfocus="limpiarTexto(this)" style="width:108px;" title="Introduce Fecha" type="text" /><em class="etiqueta_fecha">(DD/MM/AAAA)</em>
													<!-- 
													<input id="fecha_publi" name="fechaPublicacionFormatoHumano" value="DD/MM/AAAA" class="de_texto date-pick" onfocus="limpiarTexto(this)" style="width:108px;" title="Introduce Fecha" type="text" />
													-->
													<!-- 
													Si usamos esto de abajo funciona bien el date picker pero dejan de funcionar los select
													<input id="fecha_publi" name="fechaPublicacionFormatoHumano" class="de_texto date-pick" onfocus="limpiarTexto(this)" style="width:108px;" title="Introduce Fecha" type="text" />
													-->
												</c:otherwise>
												</c:choose>
											</fieldset>
														
											<fieldset class="clearfix">
											<label for="secuencia"><bean:message key="configurar.avanzado.atributo.conSinSecuencia"/>:</label>
												<span class="caja_de_boton">
													<bean:define id="tituloSeleccioneTipoSecuencia"><bean:message key="configurar.avanzado.atributo.introducir.secuencia"/></bean:define>
													<html:select onchange="calcularFiltroPropiedades()"  name="form" property="c_s_secuencia" title="${tituloSeleccioneTipoSecuencia}" styleId="tipoSecuencia" styleClass="select">
														<option value="" class="oscura"><bean:message key="configurar.avanzado.todas"/></option>
														<html:optionsCollection name="form"  property="c_s_secuenciaBackingList" label="label" value="value"/>
													</html:select>
									    		</span>
											</fieldset>
											
											<fieldset class="clearfix">
											<label for="valor"><bean:message key="listar.odecu.mostrar.resultados.detalles.valoracion"/>:</label>
												<span class="caja_de_boton">
														<bean:define id="tituloSeleccioneTipoValoracion"><bean:message key="configurar.avanzado.atributo.valoracion"/></bean:define>
														<html:select onchange="calcularFiltroPropiedades()" name="form" property="valoracion" title="${tituloSeleccioneTipoValoracion}" styleId="tipoValoracion" styleClass="select">
															<option value="" class="oscura"><bean:message key="configurar.avanzado.todas"/></option>
															<html:optionsCollection name="form"  property="valoracionBackingList" label="label" value="value"/>
														</html:select>
									    		</span>
											</fieldset>
	
											<fieldset class="clearfix">
												<label for="valor"><bean:message key="listar.odecu.mostrar.resultados.detalles.destinatarios"/>:</label>
												<span class="caja_de_boton">
														<bean:define id="tituloSeleccioneDestinatarios"><bean:message key="configurar.avanzado.atributo.destinatarios"/></bean:define>
														<html:select onchange="calcularFiltroPropiedades()" name="form" property="destinatarios" title="${tituloSeleccioneDestinatarios}" styleId="destinatarios" styleClass="select">
															<option value="" class="oscura"><bean:message key="configurar.avanzado.todos"/></option>
															<html:optionsCollection name="form"  property="destinatariosBackingList" label="label" value="value"/>
														</html:select>
									    		</span>
											</fieldset>
					
										</div>
									</div>
								</div>
							</div>	
							
							<!-- POR NIVEL EDUCATIVO  -->
							<div class="tipofieldset clearfix"  >
								<div class="caja_dinamica"><a class="desplegado" id="pm2" href="#" onclick="expandirCaja('m2', '<bean:message key='configurar.avanzado.ver'/>', '<bean:message key='configurar.avanzado.ocultar'/>');return false;" onkeypress="expandirCaja('m2', '<bean:message key='configurar.avanzado.ver'/>', '<bean:message key='configurar.avanzado.ocultar'/>');return false;"><br class="falso" /><strong id="dm2" ><bean:message key='configurar.avanzado.ver'/></strong></a>
									<input type="hidden" name="idTesauro" value="${form.idTesauro}"/>
									<h3><bean:message key="configurar.avanzado.texto.porTesauro"/></h3> 
									<em>(<bean:message key="configurar.avanzado.numeroFiltrosSeleccionadosTiene"/>&nbsp;
									<label id="contadorTesauros" style="display:inline-block; float:none; width:auto;">${form.contTesauros}</label>&nbsp; 
									<c:if test="${form.contTesauros==1 }">
								     	<bean:message key="configurar.avanzado.numeroFiltroSeleccionado"/>)</em>
								    </c:if>
			    					<c:if test="${form.contTesauros!=1 }">
								      	<bean:message key="configurar.avanzado.numeroFiltrosSeleccionados"/>)</em>
								    </c:if>
								</div>
							
								<div id="m2" class="caja_cerrada" >
									<div class="campos clearfix">
							 			<p><bean:message key="configurar.avanzado.texto.tesauro.aniadir"/>:</p>
									   
									   	<div id="portapapeles">
										   	<span>${form.nomTesauros}</span>
									   	</div>
									   	
										<fieldset class="botonera  clearfix" id="portapapeles_bot">
											<logic:empty name="form" property="nomTesauros">
												<input class="boton"  type="submit"  name="pulsacion" value="<bean:message key="configurar.avanzado.tesauros.aniadir"/>" />
												<input type="hidden" id="nomTesauros" name="nomTesauros" value="${form.nomTesauros}"/>
											</logic:empty>
											<logic:notEmpty name="form" property="nomTesauros">
												<input class="boton"  type="submit"  name="pulsacion" value="<bean:message key="configurar.avanzado.tesauros.eliminar"/>" />
												<input type="hidden" id="nomTesauros" name="nomTesauros" value="${form.nomTesauros}"/>
											</logic:notEmpty>
										</fieldset>
									</div>
								</div>
							</div>	
							
							<!-- POR AMBITO  -->
							<div class="tipofieldset clearfix"  >
								<div class="caja_dinamica"><a class="desplegado" id="pm3" href="#" onclick="expandirCaja('m3', '<bean:message key='configurar.avanzado.ver'/>', '<bean:message key='configurar.avanzado.ocultar'/>');return false;" onkeypress="expandirCaja('m3', '<bean:message key='configurar.avanzado.ver'/>', '<bean:message key='configurar.avanzado.ocultar'/>');return false;"><br class="falso" /><strong id="dm3" ><bean:message key="configurar.avanzado.texto.verCamposbusqueda"/></strong></a>
									<h3><bean:message key="configurar.avanzado.texto.porAmbito"/></h3> 
									<em>(<bean:message key="configurar.avanzado.numeroFiltrosSeleccionadosTiene"/>&nbsp;
									<label id="contadorComunidades" style="display:inline-block; float:none; width:auto;">${form.contAmbito}</label>&nbsp; 
									<c:if test="${form.contAmbito==1 }">
								     	<bean:message key="configurar.avanzado.numeroFiltroSeleccionado"/>)</em>
								    </c:if>
			    					<c:if test="${form.contAmbito!=1 }">
								      	<bean:message key="configurar.avanzado.numeroFiltrosSeleccionados"/>)</em>
								    </c:if>
								</div>
								<div id="m3" class="caja_cerrada" >
									<div class="campos clearfix">
								 	<!-- INICIO TABLA  -->
										<div class="caja_de_tabla_invisible">
											<table>
											<tr>
												<td>
													<!--impares y 0-->
													<table><!--  cellspacing="0" cellpadding="0" border="1" width="50%"  summary="Asignacion Comunidades">-->
											    		<fmt:formatNumber var="contIndices" value="0" type="number"/>
														<logic:iterate id="comunidad"   name="form" property="comunidades">
															<!-- el primer elemento -->
															<c:if test="${contIndices==0}">
			 													<tr height="30">
			  														<td>
			  															<html:multibox name="form" styleId="comunidad${contIndices}" styleClass="check" property="comuSelec" value="${comunidad.id}" onclick="marcarTodas();clickEnCheck();"/>
											         			 	</td>	
											           			  	<td class="td_largo_09" valign="top"><label for="${comunidad.id}">${comunidad.nodo}</label></td>
			 													</tr>
										 					</c:if>
										 					<c:if test="${contIndices==1}">
																<tr height="30">
			  														<td>
			  															<html:multibox name="form" styleId="comunidad${contIndices}" styleClass="check" property="comuSelec" value="${comunidad.id}" onclick="comprobarTodas();clickEnCheck();"/>
											         			 	</td>	
											           			  	<td class="td_largo_09" valign="top"><label for="${comunidad.id}">${comunidad.nodo}</label></td>
			  													</tr>
										 					</c:if>
										 					<!-- fin primer elemento -->
										 					<c:if test="${contIndices>1}">
															<!--  impar   -->		
																<c:if test="${contIndices%2!=0}">
										        					<tr height="30">
																		<td>
			 																<html:multibox name="form" styleId="comunidad${contIndices}" styleClass="check" property="comuSelec" value="${comunidad.id}" onclick="comprobarTodas();clickEnCheck();"/></td>					
										        			  			<td class="td_largo_09" valign="top"><label for="${comunidad.id}">${comunidad.nodo}</label></td>
											 				 		</tr> 	
											    				</c:if>
											    			</c:if>
															<c:set var="contIndices" value="${contIndices+1}"/>
														</logic:iterate>
													</table>
												</td>
												<td>		
													<table>
														<!-- cellspacing="0" cellpadding="0" border="1" width="50%"  summary="Asignacion Comunidades">-->
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
			 																<html:multibox name="form" styleId="comunidad${contIndices}" styleClass="check" property="comuSelec" value="${comunidad.id}" onclick="comprobarTodas();clickEnCheck();"/>									          	          
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
							</div>
	
							<fieldset style="background:#fff !Important" class="botonera botonera_de_dos clearfix">
								<input class="boton" type="submit" name="pulsacion" value="<bean:message key="configurar.avanzado.buscar.buscar"/>"  />
								<input class="boton boton_flot"  name="pulsacion" type="submit"  value="<bean:message key="configurar.avanzado.buscar.limpiar"/>" /><br />
							</fieldset>
					<!-- Fin Botones  -->
					<!-- Fin Botones  -->
					
						</form>
	    			</section>
				</article>
	  
		
			<!-- Aqui van javascripts propios (DIRECTION) -->
		
			<script type="text/javascript">
				Date.firstDayOfWeek = 0;
				Date.format = 'dd/mm/yyyy';
				// $( function(){ $('.date-pick').datePicker() });
				// $( function(){ $('.date-pick').datePicker({startDate:'01/01/1990'}).val(new Date().asString()).trigger('change'); });
				//$( function(){ $('.date-pick').datePicker({startDate:'01/01/1990'}); });
				$( function(){ $('.date-pick').datePicker({startDate:'00/00/1000'}); });
			</script>
		
			<!-- Aqui van javascripts propios  -->
			
			
			
			<SCRIPT LANGUAGE="JavaScript">
				
				// al cargar la pagina marcamos todos los check
				if (document.addEventListener) {
				   document.addEventListener("DOMContentLoaded", inicializarAmbito, false);
				   document.addEventListener("DOMContentLoaded", inicializarPropiedadesContenido, false);
				}
				

				function inicializarPropiedadesContenido(){
					
			    	var htmlCont = document.getElementById("contadorPropiedades");
					var selects = document.propiedades.getElementsByName("form");
					var cont = 0;
					
					for(var i=0; i < selects.length; i++){
						if (selects[i].selectedIndex != -1)
							cont++;
					}
					htmlCont.innerHTML = cont;
				}   
				
				///////////////////////////////////////////
				//INICIO FUNCIONES AMBITO
				///////////////////////////////////////////
				function inicializarAmbito(){
					
			    	var htmlCont = document.getElementById("contadorComunidades");
					var checkboxes = document.getElementsByName("comuSelec");
					var cont = 0;
					
					for(var i=0; i < checkboxes.length; i++){
						checkboxes[i].checked = true;
						cont++;
					}
					if(document.getElementById("comunidad0").checked)
						cont--;
					htmlCont.innerHTML = cont;
				}   
				
				
				function clickEnCheck() {
					
					var htmlCont =  document.getElementById("contadorComunidades");
					var checkboxes = document.getElementsByName("comuSelec");
					var cont = 0;
					
					for(var i=0; i < checkboxes.length;i++)
						if(checkboxes[i].checked) 
							cont++;
					
					if(document.getElementById("comunidad0").checked)
						cont--;
					
					htmlCont.innerHTML = cont;
				}
		
				
				function marcarTodas() {
					
				 	var checkboxes= document.getElementsByName("comuSelec");
					var totalComunidades = "${ fn:length(form.comunidades)}";
					
					if(document.getElementById("comunidad"+0).checked) {
						for (var x=0; x < totalComunidades; x++)
							document.getElementById("comunidad"+x).checked=true;
					} else {
						for (var x=1; x < totalComunidades; x++)
				   	 		document.getElementById("comunidad"+x).checked=false;
					}
				}
			
				
				function comprobarTodas() {
					
					var vacio=false;
					var checkboxes= document.getElementsByTagName("input");
					var totalComunidades = "${ fn:length(form.comunidades)}";
					
					for (var x=0; x < totalComunidades; x++)
						if(document.getElementById("comunidad"+x).checked==true)
					   	 	vacio=true;
					
					if(vacio==true && document.getElementById("comunidad"+0).checked == true) 
						document.getElementById("comunidad"+0).checked = false;
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

               function calcularFiltroPropiedades(){
                   var contPropiedades=0;
                   var htmlCont = document.getElementById("contadorPropiedades");

                   // Verificamos campo a campo los 11 campos del apartado "Propiedades del contenido"
                   // Identificador

                   var txtIdentificador = document.getElementById("idODE");                                                                                                                                                   
                   if (txtIdentificador.value != "")                                                                                                                              
                       contPropiedades++;

                   // Tipo de recurso
                   var selectTipoRecurso = document.getElementById("tipoRecurso");                                                                   
                   if (selectTipoRecurso.selectedIndex > 0)                                                                                                                                       
                        contPropiedades++;

                   // Idioma. Está siempre activo
                   var selectTipoIdioma = document.getElementById("tipoIdioma");                                                                       
                   if (selectTipoIdioma.selectedIndex > -1)                                                                                                                                                          
                       contPropiedades++;
                   
                   // Proceso congnitivo
                   var selectProcesoCognitivo = document.getElementById("procesoCognitivo");
                   if (selectProcesoCognitivo.selectedIndex > 0)                                                                                                                                
                       contPropiedades++;


                   // Contexto
                   var selectTipoContexto = document.getElementById("tipoContexto");
                   if (selectTipoContexto.selectedIndex > 0)                                                                                                                                        
                       contPropiedades++;


                   // Edad
                   var txtEdad = document.getElementById("edad");                                                                                                                                     
                   if (txtEdad.value != "")                                                                                                                               
                        contPropiedades++;



                   // Autor
                   var txtAutor = document.getElementById("autor");                                                                                                                                                   
                   if (txtAutor.value != "")                                                                                                                                             
                       contPropiedades++;



                   // Fecha Puvblicacion
                   var txtFechaPublicacion = document.getElementById("fecha_publi");                                                                                                                                              
                   if (txtFechaPublicacion.value != "")                                                                                                                                      
                       contPropiedades++;



                   // Secuenciacion
                   var selectTipoSecuencia = document.getElementById("tipoSecuencia");
                   if (selectTipoSecuencia.selectedIndex > 0)                                                                                                                                      
                       contPropiedades++;



                   // Valoracion
                   var selectTipoValoracion = document.getElementById("tipoValoracion");
                   if (selectTipoValoracion.selectedIndex > 0)                                                                                                                                     
                        contPropiedades++;



                   // Contexto
                   var selectDestinatarios = document.getElementById("destinatarios");
                   if (selectDestinatarios.selectedIndex > 0)                                                                                                                                         
                       contPropiedades++;

                
                   htmlCont.innerHTML = contPropiedades;

                   }
            




				

				///////////////////////////////////////////
				//FIN FUNCIONES AMBITO
				///////////////////////////////////////////
				
			</SCRIPT>
	
	
		</tiles:put>
	</tiles:insert>
</logic:notEqual>	

<logic:equal name="form" property="sinResult" value="true">
	<tiles:insert definition="layout-busqueda-sin-resultados">
		<tiles:put name="title" type="string">
		    <bean:message key="title.Comun"/>
		</tiles:put>
		
		<tiles:put name="body-principal" type="string">
			<%@ include file="/taglib-imports.jspf" %>
			<!-- 
			<h2><bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.resultadoBusqueda"/></h2>
			-->
			<!-- CAPA NUEVA DE ERROR -->    
			<jsp:include page="/layout/messages.jsp" flush="true" />	
				
			<logic:notEmpty name="form"	property="quisoDecir">
				<bean:message key="listar.odecu.mostrar.resultados.quiso.quisoDecir"/>:  
				<logic:iterate name="form" property="quisoDecir" id="sugerencia">
					<a href="<html:rewrite action="/BuscarAvanzadoCU/MostrarQuisoDecirAvanzadoBuscarQuisoDecir.do"/>?buscContenido=${sugerencia}&idiomBusc=${form.idioma}&amp;formato=${form.formato}&amp;recurso=${form.recurso}&amp;procesoCognitivo=${form.procesoCognitivo}&amp;contexto=${form.contexto}&amp;edad=${form.edad}&amp;autor=${form.autor}&amp;diaPublic=${form.diaPublic}&amp;mesPublic=${form.mesPublic}&amp;anyoPublic=${form.anyoPublic}&amp;c_s_secuencia=${form.c_s_secuencia}&amp;valoracion=${form.valoracion}&amp;enlaceTaxSelec=${form.enlaceTaxSelec}&amp;enlaceComuSelec=${form.enlaceComuSelec}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;idTesauro=${form.idTesauro}&amp;nomTesauros=${form.nomTesauros}&amp;nivelAgreg=${form.nivelAgreg}&amp;tipoBusqueda=${form.tipoBusqueda}&amp;destinatarios=${form.destinatarios}">${sugerencia}</a> 
				</logic:iterate>
				.<br />
			</logic:notEmpty>
			
			<logic:notEmpty name="form" property="tesauros">
				<bean:message key="listar.odecu.mostrar.resultados.quiso.tesauros"/>
				<logic:iterate name="form" property="tesauros" id="taxon"> 
					<a href="<html:rewrite action="/BuscarAvanzadoCU/MostrarQuisoDecirAvanzadoQuisoDecir.do"/>?idTesauroSug=${taxon.identificador}&idiomBusc=${form.idioma}&amp;formato=${form.formato}&amp;recurso=${form.recurso}&amp;procesoCognitivo=${form.procesoCognitivo}&amp;contexto=${form.contexto}&amp;edad=${form.edad}&amp;autor=${form.autor}&amp;diaPublic=${form.diaPublic}&amp;mesPublic=${form.mesPublic}&amp;anyoPublic=${form.anyoPublic}&amp;c_s_secuencia=${form.c_s_secuencia}&amp;valoracion=${form.valoracion}&amp;enlaceTaxSelec=${form.enlaceTaxSelec}&amp;enlaceComuSelec=${form.enlaceComuSelec}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;idTesauro=${form.idTesauro}&amp;nomTesauros=${form.nomTesauros}&amp;nivelAgreg=${form.nivelAgreg}&amp;tipoBusqueda=${form.tipoBusqueda}&amp;destinatarios=${form.destinatarios}">${taxon.nombre}</a> 
				</logic:iterate>
				<br />
			</logic:notEmpty>							
			
			<div class="tipofieldset clearfix" id="separacion">
				<strong><bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.resultadoBusqueda"/></strong>
				<div class="clearfix" id="separacion">
					<bean:message key="listar.odecu.mostrar.resultados.quiso.sinResultados"/>:
					<ul id="error">
						<li><bean:message key="listar.odecu.mostrar.resultados.quiso.comprobarSintaxis"/></li>
						<li><bean:message key="listar.odecu.mostrar.resultados.quiso.otraPalabra"/></li>
						<li><bean:message key="listar.odecu.mostrar.resultados.quiso.sinonimo"/></li>
					</ul>
					<br class="oculto"/>
				</div>
			</div>   
			
			
			<!-- 
			<div class="clearfix" id="separacion">
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
			-->
		</tiles:put>
	</tiles:insert>
</logic:equal>