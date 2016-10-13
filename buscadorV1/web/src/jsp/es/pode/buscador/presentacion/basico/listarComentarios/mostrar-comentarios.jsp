<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tags/valoracion.tld" prefix="estrellas" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tags/googleAnalytic.tld" prefix="analytic" %>


<tiles:insert definition="layout-sinlateral">

    <tiles:put name="title" type="string">
        <bean:message key="title.comun.comentarios"/>
    </tiles:put>

    <tiles:put name="body" type="string">
	<%@ include file="/taglib-imports.jspf" %>						
				<!-- Inicio plantilla contenido  -->
				<div class="plantilla_contenido" id="ventana_flotante">
				<analytic:googleAnalytic />
					<jsp:include page="/layout/messages.jsp" flush="true" />						
						<div class="globo_gris" >
							<div class="globo_gris_01">
								<div class="globo_gris_02">
									<div class="globo_gris_03">							
										<!--  INICIO CAJA DE FORMULARIO   -->
										<div id="formulario" >										
											<logic:notEmpty name="form" property="imagen">
												<a href="#" class="imagen_tab" id="imt"><img  src="${form.imagen}" alt="Imagen 02"/></a>
											</logic:notEmpty>
											<logic:empty name="form" property="imagen">
												<img  src="${form.imagen}" alt="Imagen 02" class="imagen_tab" id="imt" />
											</logic:empty>	
								
											<div class="valoracion"  id="valor_b">
												<span ><h2><bean:message key="introducir.comentarios.ode.titulo.sin.asteriscos"/>:</h2><h2><b>${form.tituloODE}</b></h2>&nbsp;</span>
											</div>
											<br /><br /><br /><br />
										</div>
										<!--  FIN CAJA DE FORMULARIO   -->
									<br class="limpiar" />
									</div>
								</div>
							</div>
						</div>						
						
							<div class="globo_gris uno_b">
								<div class="globo_gris_01">
									<div class="globo_gris_02">
										<div class="globo_gris_03">
											<!--  INICIO CAJA DE FORMULARIO   -->
											<div id="formulario" >				
												<h3 class="grande_h2"><bean:message key="introducir.comentarios.ode.comentarios"/></h3>													
													<logic:notEmpty name="form" property="comentarios">	
														<logic:iterate name="form" property="comentarios" id="comentario">						
															<div id="lista_de_comen">
																<div>
																	<label for="Comen01" class="etiq_invisible" ><bean:message key="introducir.comentarios.ode.seleccione"/></label>																		
																	<em>${comentario.usuario}</em>											
																	<strong>
																		<fmt:formatDate value="${comentario.fecha}" pattern="dd/MM/yyyy HH:mm:ss"/>&nbsp;
																	</strong>																			
																	<p>${comentario.comentario}<br /></p>
																</div>																			
															</div>
														</logic:iterate>
													</logic:notEmpty>													
												<br />
												<br />
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
						<logic:equal name="form" property="mostrarVuelta" value="true">
							<logic:equal name="form" property="busquedaSimpleAvanzada" value="BUSQUEDA_ARBOL">
								<form action="<html:rewrite action="/DetallarODECU/DetallarODECU.do"/>?identificadorODE=${form.idODE}&amp;idioma=${form.idiomaODE}&amp;mostrarVuelta=${form.mostrarVuelta}&amp;areaCurricularBusqueda=" method="post">
									<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
									<input class="boton_125_de_2_izq tipo"  type="submit"  value="<bean:message key="descargar.boton.volver"/>"/>
								</form>
							</logic:equal>
							<logic:notEqual name="form" property="busquedaSimpleAvanzada" value="BUSQUEDA_ARBOL">
								<form action="<html:rewrite action="/DetallarODECU/DetallarODECU.do"/>?identificadorODE=${form.idODE}&amp;idioma=${form.idiomaODE}&amp;mostrarVuelta=${form.mostrarVuelta}" method="post">
									<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
									<input class="boton_125_de_2_izq tipo"  type="submit"  value="<bean:message key="descargar.boton.volver"/>"/>
								</form>
							</logic:notEqual>
						</logic:equal>
						<logic:notEqual name="form" property="mostrarVuelta" value="true">
							<form action="<html:rewrite action="/DetallarODECU/DetallarODECU.do"/>?identificadorODE=${form.idODE}&amp;idioma=${form.idiomaODE}&amp;mostrarVuelta=${form.mostrarVuelta}" method="post">
								<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
								<input class="boton_125_de_2_izq tipo"  type="submit"  value="<bean:message key="descargar.boton.volver"/>"/>
							</form>
						</logic:notEqual>
						<!-- Fin Botones  -->							
						<!-- Fin Botones  -->					
				</div>					

			<!-- Fin plantilla contenido  -->	
	</tiles:put>
</tiles:insert>
	
	
		
