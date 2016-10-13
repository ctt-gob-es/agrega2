<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tags/valoracion.tld" prefix="estrellas" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tags/googleAnalytic.tld" prefix="analytic" %>


<tiles:insert definition="layout-comentariosODE">

    <tiles:put name="title" type="string">
        <bean:message key="title.comun.comentarios"/>
    </tiles:put>

	<tiles:put name="body-principal" type="string">
		<%@ include file="/taglib-imports.jspf" %>
						
		<analytic:googleAnalytic />
		<jsp:include page="/layout/messages.jsp" flush="true" />
		
		<form method="post" action="<html:rewrite action="/IntroducirComentariosCU/MostrarComentariosEliminar"/>" >				
			<!-- 
			<div class="globo_gris" >
				<div class="globo_gris_01">
					<div class="globo_gris_02">
						<div class="globo_gris_03">					
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
						<br class="limpiar" />
						</div>
					</div>
				</div>
			</div>
			-->
					
			<header>
			<h2>${form.tituloODE}</h2>
			</header>		
			<logic:notEmpty name="form" property="imagen">
				<section>
					<p><img src="${form.imagen}" class="imagenflotante" alt="Aplicaci&oacute;n" title="Aplicaci&oacute;n" />
					<br><br><br>
					</p>
				</section>
			</logic:notEmpty>
		
			<section class="seccion clearfix" id="dacomentarios">
				<a class="flotante" href="#<bean:message key="introducir.comentarios.ode.marca.enlece.interno"/>"><bean:message key="introducir.comentarios.ode.aniadir.comentarios"/></a>
				<h2><bean:message key="introducir.comentarios.ode.comentarios"/></h2>
				<div id="lista_de_comen">
					<!--  -->
					<logic:notEmpty name="form" property="comentarios">
					<logic:iterate name="form" property="comentarios" id="comentario">
						<logic:equal name="form" property="usuarioAdministrador" value="true">										
							<input type="checkbox" id="${comentario.id}" name="idRowSelectionAsArray" value="${comentario.id}" class="boton_check" />
						</logic:equal>
						<div><em>${comentario.usuario}</em> <strong><fmt:formatDate value="${comentario.fecha}" pattern="dd/MM/yyyy HH:mm:ss"/>&nbsp;</strong>
						<p>${comentario.comentario}<br /></p></div>
					</logic:iterate>
					</logic:notEmpty>
					<!--  -->
				</div>
			</section>	
			
			<logic:equal name="form" property="usuarioAdministrador" value="true">
				<logic:equal name="form" property="listaVacia" value="false">
					<input class="boton"  type="submit"  value="<bean:message key="introducir.comentarios.ode.eliminar"/>" />
					<input type="hidden" name="idODE" value="${form.idODE}"/>
					<input type="hidden" name="tituloODE" value="${form.tituloODE}"/>
					<input type="hidden" name="imagen" value="${form.imagen}"/>	
					<input type="hidden" name="idiomaODE" value="${form.idiomaODE}"/>	
					<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
					<input type="hidden" name="mostrarVuelta" value="${form.mostrarVuelta}"/>	
					<input type="hidden" name="busquedaSimpleAvanzada" value="${form.busquedaSimpleAvanzada}"/>	
					<input type="hidden" name="urlODE" value="${form.urlODE}"/>	
					<input type="hidden" name="valoracion" value="${form.valoracion}"/>																																							
				</logic:equal>	
			</logic:equal>
		</form>
				<!-- 
				<div class="globo_gris uno_b">
					<div class="globo_gris_01">
						<div class="globo_gris_02">
							<div class="globo_gris_03">
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
														<em>${comentario.usuario}</em>											
														<strong>
															<fmt:formatDate value="${comentario.fecha}" pattern="dd/MM/yyyy HH:mm:ss"/>&nbsp;
														</strong>																																			
														<p>${comentario.comentario}<br /></p>
													</div>																			
												</div>
											</logic:iterate>
										</logic:notEmpty>
										<fieldset class="tipo ft_centrada">
											<logic:equal name="form" property="usuarioAdministrador" value="true">
												<logic:equal name="form" property="listaVacia" value="false">
													<input class="boton_125"  type="submit"  value="<bean:message key="introducir.comentarios.ode.eliminar"/>" />
													<input type="hidden" name="idODE" value="${form.idODE}"/>
													<input type="hidden" name="tituloODE" value="${form.tituloODE}"/>
													<input type="hidden" name="imagen" value="${form.imagen}"/>	
													<input type="hidden" name="idiomaODE" value="${form.idiomaODE}"/>	
													<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
													<input type="hidden" name="mostrarVuelta" value="${form.mostrarVuelta}"/>	
													<input type="hidden" name="busquedaSimpleAvanzada" value="${form.busquedaSimpleAvanzada}"/>	
													<input type="hidden" name="urlODE" value="${form.urlODE}"/>	
													<input type="hidden" name="valoracion" value="${form.valoracion}"/>																																							
												</logic:equal>	
											</logic:equal>																	
										</fieldset>				
									<br />
									<br />
							</div>
						</div>		
					</div>
				</div>
			</div>
		</form>		
		-->			
			
		<p><bean:message key="introducir.comentarios.ode.texto.obligacion"/></p>

		<section class="seccion clearfix" id="dcomentario">
			<a name="<bean:message key="introducir.comentarios.ode.marca.enlece.interno"/>"></a>
			<h3><bean:message key="introducir.comentarios.ode.aniadir.comentarios"/></h3>
			<label for="comentario" ><bean:message key="introducir.comentarios.ode.comentario"/>:</label>
			<textarea name="comentario" id="comentario" title="<bean:message key="introducir.comentarios.ode.introduzca.comentario"/>">${form.comentario}</textarea>
		</section>
		
		<fieldset class="botonera botonera_de_dos clearfix">
			<form method="post" action="<html:rewrite action="/IntroducirComentariosCU/MostrarComentariosInsertarComentario"/>" >
				<input type="hidden" name="idODE" value="${form.idODE}"/>	
				<input type="hidden" name="tituloODE" value="${form.tituloODE}"/>	
				<input type="hidden" name="imagen" value="${form.imagen}"/>	
				<input type="hidden" name="idiomaODE" value="${form.idiomaODE}"/>
				<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>	
				<input type="hidden" name="mostrarVuelta" value="${form.mostrarVuelta}"/>	
				<input type="hidden" name="busquedaSimpleAvanzada" value="${form.busquedaSimpleAvanzada}"/>	
				<input type="hidden" name="urlODE" value="${form.urlODE}"/>	
				<input type="hidden" name="valoracion" value="${form.valoracion}"/>	
				<input type="hidden" name="nodoOrigen" value="${form.nodoOrigen}"/>
				<input type="hidden" name="tieneIdentidadFederada" value="${form.tieneIdentidadFederada}"/>
				<input type="submit"  title="<bean:message key="introducir.comentarios.ode.guardar"/>" value="<bean:message key="introducir.comentarios.ode.guardar"/>" class="boton" />
			</form>
			<logic:equal name="form" property="mostrarVuelta" value="true">
				<logic:equal name="form" property="busquedaSimpleAvanzada" value="BUSQUEDA_ARBOL">
					<form action="<html:rewrite action="/DetallarODECU/DetallarODECU.do"/>?identificadorODE=${form.idODE}&amp;idioma=${form.idiomaODE}&amp;mostrarVuelta=${form.mostrarVuelta}&amp;areaCurricularBusqueda=&nodoOrigen=${form.nodoOrigen}&tieneIdentidadFederada=${form.tieneIdentidadFederada}" method="post">
						<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
						<input class="boton boton_flot"  type="submit"  value="<bean:message key="descargar.boton.volver"/>"/>
					</form>
				</logic:equal>
				<logic:notEqual name="form" property="busquedaSimpleAvanzada" value="BUSQUEDA_ARBOL">
					<form action="<html:rewrite action="/DetallarODECU/DetallarODECU.do"/>?identificadorODE=${form.idODE}&amp;idioma=${form.idiomaODE}&amp;mostrarVuelta=${form.mostrarVuelta}&nodoOrigen=${form.nodoOrigen}&tieneIdentidadFederada=${form.tieneIdentidadFederada}" method="post">
						<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
						<input class="boton boton_flot"  type="submit"  value="<bean:message key="descargar.boton.volver"/>"/>
					</form>
				</logic:notEqual>
			</logic:equal>
			<logic:notEqual name="form" property="mostrarVuelta" value="true">
				<form action="<html:rewrite action="/DetallarODECU/DetallarODECU.do"/>?identificadorODE=${form.idODE}&amp;idioma=${form.idiomaODE}&amp;mostrarVuelta=${form.mostrarVuelta}&nodoOrigen=${form.nodoOrigen}&tieneIdentidadFederada=${form.tieneIdentidadFederada}" method="post">
					<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
					<input class="boton boton_flot"  type="submit"  value="<bean:message key="descargar.boton.volver"/>"/>
				</form>
			</logic:notEqual>
		</fieldset>	
			
				<!--
				<form method="post" action="<html:rewrite action="/IntroducirComentariosCU/MostrarComentariosInsertarComentario"/>" >				
					<p class="parrafo_comun" id="separacion2"><bean:message key="introducir.comentarios.ode.texto.obligacion"/></p>																									
					<div class="globo_gris" >
						<div class="globo_gris_01">
							<div class="globo_gris_02">
								<div class="globo_gris_03">
										<div id="formulario_02" >
											<a name="<bean:message key="introducir.comentarios.ode.marca.enlece.interno"/>"></a>
											<h3 class="grande_h2"><bean:message key="introducir.comentarios.ode.aniadir.comentarios"/></h3>													

												<div class="fila_de_tabla">
									  				<div class="text">
													  	<label for="comentario" ><bean:message key="introducir.comentarios.ode.comentario"/>:</label>
													</div>
									 			</div>
												<div class="text">
													<textarea name="comentario" rows="10" cols="40" class="ta_minimo_ancho" onblur="this.style.backgroundColor='#e1e1e1'" id="comentario"  title="<bean:message key="introducir.comentarios.ode.introduzca.comentario"/>" >${form.comentario}</textarea>
												</div>						
																	
												</div>		
										</div>
									</div>
								</div>
							</div>			
												
						<input class="boton_125_de_2 tipo"  type="submit"  value="<bean:message key="introducir.comentarios.ode.guardar"/>"/>
								<input type="hidden" name="idODE" value="${form.idODE}"/>	
								<input type="hidden" name="tituloODE" value="${form.tituloODE}"/>	
								<input type="hidden" name="imagen" value="${form.imagen}"/>	
								<input type="hidden" name="idiomaODE" value="${form.idiomaODE}"/>
								<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>	
								<input type="hidden" name="mostrarVuelta" value="${form.mostrarVuelta}"/>	
								<input type="hidden" name="busquedaSimpleAvanzada" value="${form.busquedaSimpleAvanzada}"/>	
								<input type="hidden" name="urlODE" value="${form.urlODE}"/>	
								<input type="hidden" name="valoracion" value="${form.valoracion}"/>	
								<input type="hidden" name="nodoOrigen" value="${form.nodoOrigen}"/>
								<input type="hidden" name="tieneIdentidadFederada" value="${form.tieneIdentidadFederada}"/>
																																														
								
						</form>	
						<logic:equal name="form" property="mostrarVuelta" value="true">
							<logic:equal name="form" property="busquedaSimpleAvanzada" value="BUSQUEDA_ARBOL">
								<form action="<html:rewrite action="/DetallarODECU/DetallarODECU.do"/>?identificadorODE=${form.idODE}&amp;idioma=${form.idiomaODE}&amp;mostrarVuelta=${form.mostrarVuelta}&amp;areaCurricularBusqueda=&nodoOrigen=${form.nodoOrigen}&tieneIdentidadFederada=${form.tieneIdentidadFederada}" method="post">
									<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
									<input class="boton_125_de_2_izq tipo"  type="submit"  value="<bean:message key="descargar.boton.volver"/>"/>
								</form>
							</logic:equal>
							<logic:notEqual name="form" property="busquedaSimpleAvanzada" value="BUSQUEDA_ARBOL">
								<form action="<html:rewrite action="/DetallarODECU/DetallarODECU.do"/>?identificadorODE=${form.idODE}&amp;idioma=${form.idiomaODE}&amp;mostrarVuelta=${form.mostrarVuelta}&nodoOrigen=${form.nodoOrigen}&tieneIdentidadFederada=${form.tieneIdentidadFederada}" method="post">
									<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
									<input class="boton_125_de_2_izq tipo"  type="submit"  value="<bean:message key="descargar.boton.volver"/>"/>
								</form>
							</logic:notEqual>
						</logic:equal>
						<logic:notEqual name="form" property="mostrarVuelta" value="true">
							<form action="<html:rewrite action="/DetallarODECU/DetallarODECU.do"/>?identificadorODE=${form.idODE}&amp;idioma=${form.idiomaODE}&amp;mostrarVuelta=${form.mostrarVuelta}&nodoOrigen=${form.nodoOrigen}&tieneIdentidadFederada=${form.tieneIdentidadFederada}" method="post">
								<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
								<input class="boton_125_de_2_izq tipo"  type="submit"  value="<bean:message key="descargar.boton.volver"/>"/>
							</form>
						</logic:notEqual>
				</div>		
			 -->
	</tiles:put>
</tiles:insert>
	
	
		
	