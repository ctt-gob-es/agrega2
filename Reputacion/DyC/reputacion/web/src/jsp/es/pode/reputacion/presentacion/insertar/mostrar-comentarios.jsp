<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tags/valoracion.tld" prefix="estrellas" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<tiles:insert definition="layout-comentarios">

    <tiles:put name="title" type="string">
        <bean:message key="title.comun.comentarios"/>
    </tiles:put>

    <tiles:put name="body" type="string">
	<%@ include file="/taglib-imports.jspf" %>
						
				<!-- Inicio plantilla contenido  -->
				<div class="plantilla_contenido" id="ventana_flotante">
					<jsp:include page="/layout/messages.jsp" flush="true" />
					<form method="post" action="<html:rewrite action="/InsertarComentarioCU/MostrarComentariosEliminar"/>" >				
					
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
												<a href="#<bean:message key="introducir.comentarios.ode.marca.enlece.interno"/>"><bean:message key="introducir.comentarios.ode.aniadir.comentarios"/></a>
													<logic:notEmpty name="form" property="comentarios">	
														<logic:iterate name="form" property="comentarios" id="comentario">						
															<div id="lista_de_comen">
																<div>
																	<label for="Comen01" class="etiq_invisible" ><bean:message key="introducir.comentarios.ode.seleccione"/></label>	
																	<logic:equal name="form" property="usuarioAdministrador" value="true">										
																		<input type="checkbox" id="${comentario.id}" name="idRowSelectionAsArray" value="${comentario.id}" class="boton_check" />
																	</logic:equal>
																	<em>${comentario.titulo}</em>											
																	<strong>
																		<fmt:formatDate value="${comentario.fecha}" pattern="dd/MM/yyyy HH:mm:ss"/>&nbsp;
																	</strong>												
																	<estrellas:valoracion valoracion="${comentario.valoracion}" mensaje="introducir.comentarios.ode.valoracion"/>																					
																	<p>${comentario.comentario}<br /></p>
																</div>																			
															</div>
														</logic:iterate>
													</logic:notEmpty>
													<!-- Inicio Botones  -->
													<!-- Inicio Botones  -->
													<fieldset class="tipo ft_centrada">
														<logic:equal name="form" property="usuarioAdministrador" value="true">
															<logic:equal name="form" property="listaVacia" value="false">
																<input class="boton_125"  type="submit"  value="<bean:message key="introducir.comentarios.ode.eliminar"/>" />
																<input type="hidden" name="idODE" value="${form.idODE}"/>
																<input type="hidden" name="tituloODE" value="${form.tituloODE}"/>
																<input type="hidden" name="imagen" value="${form.imagen}"/>		
																<input type="hidden" name="usuarioLogado" value="${form.usuarioLogado}"/>	
																<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>																			
															</logic:equal>	
														</logic:equal>																	
													</fieldset>								
													<!-- Fin Botones  -->
													<!-- Fin Botones  -->
												<br />
												<br />
										</div>
										<!--  FIN CAJA DE FORMULARIO   -->
									</div>		
								</div>
							</div>
						</div>
					</form>		
					<!--  FIN GLOBO GRIS   -->
					<!--  FIN GLOBO GRIS   -->				
				
				<!--  INICIO GLOBO GRIS   -->
				<!--  INICIO GLOBO GRIS   -->
				<form method="post" action="<html:rewrite action="/InsertarComentarioCU/MostrarComentariosInsertarComentario"/>" >
				<logic:equal name="form" property="usuarioLogado" value="true">	
					<p class="parrafo_comun" id="separacion2"><bean:message key="introducir.comentarios.ode.texto.obligacion"/></p>																									
					<div class="globo_gris" >
						<div class="globo_gris_01">
							<div class="globo_gris_02">
								<div class="globo_gris_03">
								<!--  INICIO CAJA DE FORMULARIO   -->
										<div id="formulario_02" >
											<a name="<bean:message key="introducir.comentarios.ode.marca.enlece.interno"/>"></a>
											<h3 class="grande_h2"><bean:message key="introducir.comentarios.ode.aniadir.comentarios"/></h3>
											<!-- -->
												<div class="fila_de_tabla">
					  								<div class="text">
					  									<label for="valorar_combo">
															<bean:message key="introducir.comentarios.ode.valorar"/>:&nbsp;
														</label>
					  								</div>
					  							</div>								 				
												<div class="text">
													<bean:define id="tituloSeleccioneValoracion"><bean:message key="introducir.comentarios.ode.seleccione.valor"/></bean:define>
														<html:select name="form" property="valoracion" title="${tituloSeleccioneValoracion}" styleId="tipoValoracion" style="width:200px">															
															<html:optionsCollection name="form"  property="valoracionBackingList" label="label" value="value"/>
														</html:select>	 
												</div>												
												<br class="oculto" />
												<!-- -->
												<div class="fila_de_tabla">
									  				<div class="text">
									  					<label for="titulocomen"><bean:message key="introducir.comentarios.ode.titulo"/>:</label>
									  				</div>
									 				<div class="text">
														<input name="titulo" value="${form.titulo}"  onblur="this.style.backgroundColor='#e1e1e1'" id="titulocomen" type="text" title="<bean:message key="introducir.comentarios.ode.introduzca.titulo"/>" />
													</div>
													<br class="oculto" />
												</div>
												<!-- -->	
												<div class="fila_de_tabla">
									  				<div class="text">
													  	<label for="comentario" ><bean:message key="introducir.comentarios.ode.comentario"/>:</label>
													</div>
									 			</div>
												<div class="text">
													<textarea name="comentario" rows="10" cols="40" class="ta_minimo_ancho" onblur="this.style.backgroundColor='#e1e1e1'" id="comentario"  title="<bean:message key="introducir.comentarios.ode.introduzca.comentario"/>" >${form.comentario}</textarea>
												</div>						
												<div class="fila_de_tabla">
													<div class="text" >
														<label for="conInaprop" class="oculto" ><bean:message key="introducir.comentarios.ode.inapropiado"/>:</label>																
															<html:checkbox styleId="conInaprop" styleClass="boton_radio" name="form" property="conInaprop" value="true"/>																												
																<span class="vert"><bean:message key="introducir.comentarios.ode.contenido.inapropiado"/></span>
													</div>
														<div class="linea_separadora"></div>
														<br class="oculto" />
												</div>								
													<!-- -->	
												</div>		
										</div>
										<!--  FIN CAJA DE FORMULARIO   -->
									</div>
								</div>
							</div>
				</logic:equal>
				<!--  FIN GLOBO GRIS   -->
				<!--  FIN GLOBO GRIS   -->	
				
				<!-- Inicio Botones  -->
						<!-- Inicio Botones  -->			
						<fieldset class="tipo">
							<input class="boton_125_de_2_izq" type="button" value="<bean:message key="introducir.comentarios.ode.cerrar"/>" alt="<bean:message key="introducir.comentarios.ode.cerrar.y.volver.al.detalle"/>" onClick="window.close()">
							<logic:equal name="form" property="usuarioLogado" value="true">	
								<input class="boton_125_de_2"  type="submit"  value="<bean:message key="introducir.comentarios.ode.guardar"/>"/>
									<input type="hidden" name="idODE" value="${form.idODE}"/>	
									<input type="hidden" name="tituloODE" value="${form.tituloODE}"/>	
									<input type="hidden" name="imagen" value="${form.imagen}"/>	
									<input type="hidden" name="usuarioLogado" value="${form.usuarioLogado}"/>	
									<input type="hidden" name="idiomaODE" value="${form.idiomaODE}"/>
									<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>	
							</logic:equal>								
						</fieldset>				
						<!-- Fin Botones  -->
						<!-- Fin Botones  -->
					</form>	
				</div>					

			<!-- Fin plantilla contenido  -->	
	</tiles:put>
</tiles:insert>
	
	
		
	
	




