<%@ include file="/taglib-imports.jspf" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<tiles:insert definition="layout-principal">



<tiles:put name="title" type="string">
	<bean:message key="title.Comun"/> - ${fn:escapeXml(visualizadorSession.tituloOde)}
</tiles:put>



<tiles:put name="body" type="string">
<!-- CONTENIDO -->
					
<!-- Panel contenidos!! -->
<div id="capa_contenidos" name="capa_contenidos">
<div id="contenido" name="contenido" style="min-width: 520px; min-height: 440px; height: 223px; width: 530px;">


<jsp:include page="/layout/messages.jsp" flush="true" />

<!-- Inicio plantilla contenido  -->
<!-- Inicio plantilla contenido  -->
<div id="contenido_externo" class="plantilla_contenido">
<h2 align="left"><bean:message key="comentarios.ode.comentarios"/></h2>

<form method="post" action="<html:rewrite action="/Comentarios/ListadoComentariosEliminar"/>" >
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_blanco uno_b">
	<div class="globo_blanco_01">
		<div class="globo_blanco_02">
			<div class="globo_blanco_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
										<div id="formulario" align="left">
<logic:notEmpty name="form" property="comentarios">
<c:if test="${visualizadorSession.verComentariosAlta }">
												<a href="#marcaEnlace"><bean:message key="comentarios.ode.aniadir.comentarios"/></a>
</c:if>												
														<logic:iterate name="form" property="comentarios" id="comentario">						
															<div id="lista_de_comen">
																<div>
																	<c:if test="${visualizadorSession.verComentariosBaja }">
																		<input type="checkbox" id="${comentario.id}" name="seleccionadoRowSelectionAsArray" value="${comentario.id}" class="boton_check" />
																	</c:if>
																	<em>${comentario.usuario}</em>											
																	<strong>
																		<fmt:formatDate value="${comentario.fecha.time}" pattern="dd/MM/yyyy HH:mm:ss"/>&nbsp;
																	</strong>																			
																	<p>${comentario.comentario}<br /></p>
																</div>
															</div>
														</logic:iterate>
												
<c:if test="${visualizadorSession.verComentariosBaja }">
									<fieldset class="tipo ft_centrada">
											<!-- Inicio Botones  -->
											<!-- Inicio Botones  -->					
												<input class="boton_125"  type="submit"  value="<bean:message key="comentarios.eliminar"/>" />
											<!-- Fin Botones  -->
											<!-- Fin Botones  -->
									</fieldset>
</c:if>
									
</logic:notEmpty>
<logic:empty name="form" property="comentarios">
									<em><bean:message key="comentarios.ode.sin.comentarios"/></em>
</logic:empty>
										
										</div>
										<!--  FIN CAJA DE FORMULARIO   -->
									</div>
								</div>
							</div>
	
						</div>
					<!--  FIN GLOBO GRIS   -->
					<!--  FIN GLOBO GRIS   -->
</form>
<br/>





<form method="post" action="<html:rewrite action="/Comentarios/ListadoComentariosSubmit"/>" >
				
				
<c:if test="${visualizadorSession.verComentariosAlta }">

<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_blanco" >
	<div class="globo_blanco_01">
		<div class="globo_blanco_02">
			<div class="globo_blanco_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario_02" align="left">
					<a name="marcaEnlace"></a>

					<h3 class="grande_h2"><bean:message key="comentarios.ode.aniadir.comentarios"/></h3>
						<!-- -->	
					<div class="fila_de_tabla">
		  				<div class="text">
						  	<label for="comentario" ><bean:message key="comentarios.ode.comentario"/>:</label>
						</div>
		 			</div>
					<div class="text">
						<textarea name="comentario" rows="10" cols="40" class="ta_minimo_ancho" onblur="this.style.backgroundColor='#e1e1e1'" id="comentario" style="width:100%" title="<bean:message key="comentarios.ode.introduzca.comentario"/>" >${form.comentario}</textarea>
					</div>						
							
			</div>
			<div class="linea_separadora"></div>
			<br class="oculto" />
	<!-- Inicio Botones  -->
	<!-- Inicio Botones  -->
<fieldset class="tipo ft_centrada">				
	<input class="boton_125"  type="submit" name="action"  value="<bean:message key="comentarios.guardar"/>"/>
</fieldset>
	<!-- Fin Botones  -->
	<!-- Fin Botones  -->

					
				</div>
				<!--  FIN CAJA DE FORMULARIO   -->
			</div>
		</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->

</c:if>
<input type="hidden" name="identificador" value="${form.identificador}" />
			<!-- Fin plantilla contenido  -->	
</form>
			<div class="linea_separadora"></div>
			<br class="oculto"></div>
				
</div>




</div>
	<script type="text/javascript">
	//<![CDATA[
		$("#contenido_central_largo").contenido({
			ancho_menu:430,
			inicio_menu_desplegado:'false',
			boton_menu:'#enlace_menu',
			contenedor_menu:'#panel_menu',
			contenedor_frame:'#contenido'
			}
		);
	//]]>
	</script>




</tiles:put>
</tiles:insert>
