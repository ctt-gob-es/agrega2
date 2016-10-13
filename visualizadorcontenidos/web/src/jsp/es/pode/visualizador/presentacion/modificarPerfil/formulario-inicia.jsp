<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tags/tags-visualizadorContenidos.tld" prefix="el"%>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<html:xhtml/>


<tiles:insert definition="layout-usuario">

<tiles:put name="title" type="string">
      <bean:message key="title.Comun"/>
</tiles:put>

<tiles:put name="codigo-head" type="string">
<script type="text/javascript" src="<rewrite:rewrite url="static/js/prototype.js"/>"></script>
</tiles:put>

<tiles:put name="body" type="string">

<%@ include file="/taglib-imports.jspf" %>


<!--		Inicio plantilla contenido		-->

<div class="plantilla_contenido">


<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />
<span class="resultados_b" id="conmargen02"><bean:message key="usuarios.solicitarBaja1" /> <a href="<html:rewrite action='/SolicitarBajaUsuario/SolicitarBajaUsuario'/>"><bean:message key="usuarios.solicitarBaja2" /></a></span>
<h2><bean:message key="usuarios.modificarPerfil.cabecera"/></h2>


<!--		Inicio del formulario principal		-->

<form id="modificarPerfilFormularioIniciaAceptarForm" action='<html:rewrite action="/ModificarPerfil/FormularioIniciaAceptar"/>?progressMonitor=myProgressMonitor' method="post" enctype="multipart/form-data" onsubmit="startLoading()">
	
	
	<!--		INICIO GLOBO GRIS		-->
	
	<div class="globo_gris" >
		<div class="globo_gris_01">
			<div class="globo_gris_02">
				<div class="globo_gris_03">
					
					
					<!-- 		CAMPO DE ID (OCULTO)		-->
										
					<input name="id" value="${form.id}" type="hidden" />
					<input name="usuario" value="${form.usuario}" type="hidden" />
										
										
					<!--		INICIO CAJA DE FORMULARIO (Datos personales)		-->
					
					<div class="formu" >

						<h3><bean:message key="usuarios.datosPersonales"/></h3>
				
						<!--  INICIO GLOBO Blanco   -->
			<!--  INICIO GLOBO Blanco   -->
			<div class="globo_blanco conmargen02" >
				<div class="globo_blanco_01">
					<div class="globo_blanco_02">
						<div class="globo_blanco_03">

									
									

										
			  							<!-- 		CAMPO NOMBRE		-->
			  							
										<div class="fila_de_tabla">
			  								<div class="contenedor_izquierda">
			  									<div class="text"><label for="Nombre"><bean:message key="usuarios.nombre"/></label></div>
			 								</div>
											<div class="contenedor_derecha">
												<div class="text"><input name="nombre" value="${form.nombre}" onblur="this.style.backgroundColor='#e1e1e1'" id="Nombre" maxlength="50" type="text" title="<bean:message key='usuarios.introduzcaNombre' />" /></div>
											</div>
											<div class="linea_separadora"></div>
											<br class="oculto" />
										</div>

										
										<!-- 		CAMPO PRIMER APELLIDO		-->
										
										<div class="fila_de_tabla">
			  								<div class="contenedor_izquierda">
			  									<div class="text"><label for="Apellido1"><bean:message key="usuarios.apellido1"/></label></div>
			 								</div>
											<div class="contenedor_derecha">
												<div class="text"><input name="apellido1" onblur="this.style.backgroundColor='#e1e1e1'" value="${form.apellido1}" id="Apellido1" maxlength="50" type="text" title="<bean:message key='usuarios.introduzcaApellido1'/>"  /></div>
											</div>
											<div class="linea_separadora"></div>
											<br class="oculto" />
										</div>
										
										
										<!-- 		CAMPO SEGUNDO APELLIDO		-->
										
										<div class="fila_de_tabla">
			  								<div class="contenedor_izquierda">
			  									<div class="text"><label for="Apellido2"><bean:message key="usuarios.apellido2"/></label></div>
			 								</div>
											<div class="contenedor_derecha">
												<div class="text"><input name="apellido2" onblur="this.style.backgroundColor='#e1e1e1'" value="${form.apellido2}" id="Apellido2" maxlength="50" type="text" title="<bean:message key='usuarios.introduzcaApellido2'/>"  /></div>
											</div>
											<div class="linea_separadora"></div>
											<br class="oculto" />
										</div>

										
										<!-- 		CAMPO NIF		-->
										
										<div class="fila_de_tabla">
						  					<div class="contenedor_izquierda">
					  							<div class="text"><label for="NIF" class="label_nif"><bean:message key="usuarios.NIF"/></label></div>
					 						</div>
											<div class="contenedor_derecha">
												<div class="text"><input readonly="readonly" name="NIF" onblur="this.style.backgroundColor='#e1e1e1'" value="${form.NIF}" id="NIF" style="background-color: #e1e1e1"  type="text" title="<bean:message key="usuarios.introduzcaNIF"/>"  /></div>
											</div>
											<div class="linea_separadora"></div>
											<br class="oculto" />
										</div>

										
										<!--		CAMPO EMAIL		 -->
										
										<div class="fila_de_tabla">
					  						<div class="contenedor_izquierda">
					  							<div class="text"><label for="Email"><bean:message key="usuarios.mail"/></label></div>
					 						</div>
											<div class="contenedor_derecha">
												<div class="text"><input name="email" onblur="this.style.backgroundColor='#e1e1e1'"  value="${form.email}" id="Email"  type="text" title="<bean:message key="usuarios.introduzcaMail"/>"  /></div>
											</div>
											<div class="linea_separadora"></div>
											<br class="oculto" />
										</div>
										<!-- -->
										

									</div>
								</div>
							</div>
						</div>
						
						<!--  Fin GLOBO Blanco   -->
<!--  Fin GLOBO Blanco   -->

						
						
					</div>
					<!--  FIN CAJA DE FORMULARIO   -->
					</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->
<c:if test="${el:habilitadoPerfilPublico()}">
	<!--  INICIO GLOBO GRIS   -->

<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris conmargen" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div class="formu" >
			<h3><bean:message key="usuariosPublicos.comunidad"/></h3>
			
			
			<!--  INICIO GLOBO Blanco   -->

			<!--  INICIO GLOBO Blanco   -->
			<div class="globo_blanco conmargen02" id="check_but" >
				<div class="globo_blanco_01">
					<div class="globo_blanco_02">
						<div class="globo_blanco_03">
						<h3 class="in_h3"><bean:message key="usuariosPublicos.privacidad"/></h3>
  					
				<!-- -->
				<!-- -->
					<div class="fila_de_tabla">
  					<div class="contenedor_izquierda_esp">
  					<div class="text"><input type="checkbox" class="input_pqn"  id="perfilPublico"  name="perfilPublico" <c:if test="${form.perfilPublico==true}"> checked="checked"  </c:if>  onclick="disableCheck(id)"/><label class="enlinea" for="perfilPublico"><bean:message key="usuariosPublicos.activar.perfil"/></label></div>

					<div class="linea_separadora"></div>
				
				</div>
				</div>
				<!-- -->

				<br class="limpiar" />
				<!-- -->
					<div class="fila_de_tabla">
  					<div class="contenedor_izquierda_esp">
  					<c:if test="${form.perfilPublico==true}">
  						<div class="text"><input type="checkbox" class="input_pqn_02"  name="mostrarObjetos"  id="mostrarObjetos" <c:if test="${form.mostrarObjetos==true}"> checked="checked"  </c:if>/><label class="enlinea" for="mostrarObjetos"><bean:message key="usuarioPublico.mostrarObjetos"/></label></div>
 					</c:if>
 					<c:if test="${form.perfilPublico==false}">
  						<div class="text"><input type="checkbox" class="input_pqn_02"  name="mostrarObjetos"  id="mostrarObjetos"  disabled="disabled" <c:if test="${form.mostrarObjetos==true}"> checked="checked"  </c:if>/><label class="enlinea" for="mostrarObjetos"><bean:message key="usuarioPublico.mostrarObjetos"/></label></div>
 					</c:if>
 					
					<div class="linea_separadora"></div>
				
				</div>
				</div>

				<!-- -->

				<!-- -->		<br class="limpiar" />
				<!-- -->
					<div class="fila_de_tabla">
  					<div class="contenedor_izquierda_esp">
  					<c:if test="${form.perfilPublico==true}">
	  					<div class="text"><input type="checkbox" class="input_pqn_02"   name="mostrarFavoritos"   id="mostrarFavoritos" <c:if test="${form.mostrarFavoritos==true}"> checked="checked"  </c:if>/><label class="enlinea" for="mostrarFavoritos"><bean:message key="usuarioPublico.mostrarFavoritos"/></label></div>
					</c:if>
					<c:if test="${form.perfilPublico==false}">
	  					<div class="text"><input type="checkbox" class="input_pqn_02"   name="mostrarFavoritos"   id="mostrarFavoritos" disabled="disabled" <c:if test="${form.mostrarFavoritos==true}"> checked="checked"  </c:if>/><label class="enlinea" for="mostrarFavoritos"><bean:message key="usuarioPublico.mostrarFavoritos"/></label></div>
					</c:if>
					
					
					<div class="linea_separadora"></div>

				
				</div>
				</div>
				<!-- -->

				<!-- -->		<br class="limpiar" />
				<!-- -->
					<div class="fila_de_tabla">
  					<div class="contenedor_izquierda_esp">
  					<c:if test="${form.perfilPublico==true}">
  						<div class="text"><input type="checkbox" class="input_pqn_02"   name="mostrarGrupos"   id="mostrarGrupos" <c:if test="${form.mostrarGrupos==true}"> checked="checked"  </c:if>/><label class="enlinea" for="mostrarGrupos" ><bean:message key="usuarioPublico.mostrarGrupos"/></label></div>
					</c:if>
					<c:if test="${form.perfilPublico==false}">
  						<div class="text"><input type="checkbox" class="input_pqn_02"   name="mostrarGrupos"   id="mostrarGrupos" disabled="disabled" <c:if test="${form.mostrarGrupos==true}"> checked="checked"  </c:if>/><label class="enlinea" for="mostrarGrupos" ><bean:message key="usuarioPublico.mostrarGrupos"/></label></div>
					</c:if>
					<div class="linea_separadora"></div>
				
				</div>
				</div>
				<!-- -->

				<!-- -->		<br class="limpiar" />
				</div>
			</div>

		</div>
	</div>
<!--  Fin GLOBO Blanco   -->
<!--  Fin GLOBO Blanco   -->

	<!--  INICIO GLOBO Blanco   -->
			<!--  INICIO GLOBO Blanco   -->
			<div class="globo_blanco conmargen02" >
				<div class="globo_blanco_01">
					<div class="globo_blanco_02">

						<div class="globo_blanco_03" style="min-height:140px;">
						
						<h3 class="in_h3"><bean:message key="usuarios.ImagenUsuarioPublicoNuevo"/></h3>
						<c:if test="${!(form.foto eq null)}">
						<div class="_izq_usuario" style="position:relative">
						<div class="globo_gris conmargen02" >
						<div class="globo_gris_01">
						<div class="globo_gris_02">
						<div class="globo_gris_03 usuario" >
						
						<img src="${form.foto}" alt="${form.foto}" width="80" height="80"/>
						
						<c:if test="${form.eliminarImagen }">
						<a href="<html:rewrite action="/EliminarImagenPerfil/EliminarImagenPerfil.do"/>"><bean:message key="usuarioPublico.grupoPublico.eliminar"/></a></c:if>
						</div>
						</div>
					</div>
				</div>
						</div>
						</c:if>
						
						<p><bean:message key="texto.subir.imagen"/> </p>
						<html:file name="form" accept="image/jpeg" styleId="selecImagen" property="fotoNueva" onfocus="limpiarTexto(this)" style="width:50%" value="&nbsp;"  onblur="this.style.backgroundColor='#e1e1e1'" styleClass="nombreGrupo" titleKey="cambiar.imagen.seleccione.titleKey"  />
				</div>

			</div><br class="limpiar" />
			
		</div>
	</div>
<!--  Fin GLOBO Blanco   -->
<!--  Fin GLOBO Blanco   -->

<!--  INICIO GLOBO Blanco   -->
			<!--  INICIO GLOBO Blanco   -->
			<div class="globo_blanco conmargen02" >
				<div class="globo_blanco_01">
					<div class="globo_blanco_02">
 
						<div class="globo_blanco_03">
						<h3 class="in_h3"><bean:message key="usuariosPublicos.perfil.publico"/></h3>
  					
				<!-- -->
				
									<!--		CAMPO DESCRIPCION		 -->
					<div class="fila_de_tabla" id="solo_">
						<div class="contenedor_izquierda">
							<div class="text"><label for="descripcionUsuario"><bean:message key="grupoPublico.descripcion"/></label></div>
						</div>
						<div class="contenedor_derecha">
							<div class="text">
								<textarea id="descripcionUsuario" name="descripcionUsuario" rows="10" cols="40" class="ta_minimo_ancho"  title="<bean:message key="usuariosPublicos.descripcion.introducir"/>">${form.descripcionUsuario }</textarea></div>
						</div>
						<div class="linea_separadora"></div>
						<br class="oculto"/>
					</div>	<!-- -->
									
				<!-- -->
				<!--		CAMPO CENTRO		 -->
					<div class="fila_de_tabla">
  					<div class="contenedor_izquierda">
  						<div class="text"><label for="centroEducativo"><bean:message key="usuariosPublicos.centroEducativo"/></label></div>
 					</div>
					<div class="contenedor_derecha">
						<div class="text"><input name="centroEducativo"  value="${form.centroEducativo }"  onblur="this.style.backgroundColor='#e1e1e1'" id="centroEducativo" type="text" maxlength="250" title="<bean:message key="usuarios.introduzcaCentroEducativo"/>"  /></div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>
				
				<!-- -->			
				
				<!-- -->			
				<!-- -->		
				</div>
			</div>
		</div>
	</div>
<!--  Fin GLOBO Blanco   -->

<!--  Fin GLOBO Blanco   -->

</div>
<!--  FIN CAJA DE FORMULARIO   -->

			</div>
		</div>

	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->
</c:if>
			<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
	<div class="globo_gris conmargen" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div class="formu" >
			
				<h3><bean:message key="usuariosPublicos.notificaciones"/></h3>
			
			
			<!--  INICIO GLOBO Blanco   -->
			<!--  INICIO GLOBO Blanco   -->
			<div class="globo_blanco conmargen02" >
				<div class="globo_blanco_01">
					<div class="globo_blanco_02">
						<div class="globo_blanco_03">

										
				<!-- -->
				<!-- -->
					
					<div class="fila_de_tabla">
  					<div class="contenedor_izquierda_esp">
  					<div class="text"><input type="checkbox" class="input_pqn" name="recibirCorreo" <c:if test="${form.recibirCorreo==true}"> checked="checked"  </c:if>   id="recibirCorreo"/><label class="enlinea" for="recibirCorreo"><bean:message key="usuario.recibirCorreoPublicacion"/>.</label></div>

					<div class="linea_separadora"></div>
				
				</div>
				</div>

				<!-- -->
				<br class="limpiar" />
				<!-- -->
				<c:if test="${el:habilitadoPerfilPublico()}">
					<div class="fila_de_tabla">
  					<div class="contenedor_izquierda_esp">
  					<div class="text"><input type="checkbox" class="input_pqn" id="recibirCorreoItinerarios" <c:if test="${form.recibirCorreoItinerarios==true}"> checked="checked"  </c:if> name="recibirCorreoItinerarios"  /><label class="enlinea" for="recibirCorreoItinerarios"><bean:message key="usuario.recibirCorreoItinerarios"/></label></div>

					<div class="linea_separadora"></div>
				
				</div>

				</div>
				<!-- -->
	
				<!-- -->		<br class="limpiar" />
				</c:if>
				</div>
			</div>
		</div>
	</div>
<!--  Fin GLOBO Blanco   -->

<!--  Fin GLOBO Blanco   -->

	
</div>
<!--  FIN CAJA DE FORMULARIO   -->

			</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->

<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris conmargen" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div class="formu" >
					<h3><bean:message key="usuarios.datosAcceso"/></h3>
					
					<!--  INICIO GLOBO Blanco   -->
			<!--  INICIO GLOBO Blanco   -->
			<div class="globo_blanco conmargen02" >
				<div class="globo_blanco_01">
					<div class="globo_blanco_02">
						<div class="globo_blanco_03">
								
	  									<!-- 		CAMPO DE USUARIO		-->
	  									
										<div class="fila_de_tabla">
	  										<div class="contenedor_izquierda">
	  											<div class="text"><label for="Usuario"><bean:message key="usuarios.usuario"/></label></div>
	 										</div>
											<div class="contenedor_derecha">
												<div class="text"><input readonly="readonly" name="usuario" onblur="this.style.backgroundColor='#e1e1e1'" value="${form.usuario}" id="Usuario" style="background-color: #e1e1e1" maxlength="25" type="text" title="<bean:message key="usuarios.introduzcaUsuario"/> "  /></div>
											</div>
											<div class="linea_separadora"></div>
											<br class="oculto" />
										</div>
										
										<c:if test="${!el:esLdapExternal()}">	
											<!-- 		CAMPO DE CONTRASEÑA			-->
											
											<div class="fila_de_tabla">
							  					<div class="contenedor_izquierda">
							  						<div class="text"><label for="Contrasenia"><bean:message key="usuarios.clave"/></label></div>
							 					</div>
												<div class="contenedor_derecha">
													<div class="text"><input type="password" name="clave" value="" onblur="this.style.backgroundColor='#e1e1e1'" id="Contrasenia" maxlength="20" title="<bean:message key="usuarios.introduzcaClave"/>"  /></div>
												</div>
												<div class="linea_separadora"></div>
												<br class="oculto" />
											</div>
											
											
											<!-- 		CAMPO DE CONFIRMACION CONTRASEÑA		-->
											
											<div class="fila_de_tabla">
							  					<div class="contenedor_izquierda">
							  						<div class="text"><label for="ConfContrasenia"><bean:message key="usuarios.confirmarClave"/></label></div>
							 					</div>
												<div class="contenedor_derecha">
													<div class="text"><input type="password" name="repitaClave" onblur="this.style.backgroundColor='#e1e1e1'" value="" id="ConfContrasenia" maxlength="20" title="<bean:message key="usuarios.introduzcaClave"/> "  /></div>
												</div>
												<div class="linea_separadora"></div>
												<br class="oculto" />
											</div>
										</c:if>
										
										<!-- 		CAMPO DE IDENTIFICADOR OPENID		-->
										
										<div class="fila_de_tabla">
						  					<div class="contenedor_izquierda">
						  						<div class="text"><label for="openIdUrl"><bean:message key="usuarios.openIdUrl"/></label></div>
						 					</div>
											<div class="contenedor_derecha">
												<div class="text">
												<c:if test="${form.rolAdministrador == 'S'}">
													<input type="text" name="openIdUrl" value="${form.openIdUrl}" onblur="this.style.backgroundColor='#e1e1e1'" id="openIdUrl" maxlength="100" title="<bean:message key="usuarios.introduzcaOpenIdUrl"/> "  />
												</c:if>
												<c:if test="${form.rolAdministrador == 'N'}">
													<input readonly="readonly" type="text" name="openIdUrl" style="background-color:#e1e1e1"  onblur="this.style.backgroundColor='#e1e1e1'" value="${form.openIdUrl}" id="openIdUrl" maxlength="100" title="<bean:message key="usuarios.introduzcaOpenIdUrl"/> "  />
													</c:if>
												</div>
											</div>
											<div class="linea_separadora"></div>
											<br class="oculto" />
										</div>	
										
										
									</div>
								</div>
							</div>
						</div>
				
						
						<!--  Fin GLOBO Blanco   -->
<!--  Fin GLOBO Blanco   -->

					
						<p>
						<bean:message key="usuarios.mensajeClave" />
						</p>

					
					</div>
					<!--  FIN CAJA DE FORMULARIO   -->
			</div>
		</div>

	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->
	<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris conmargen" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->

			<div class="formu" >
				<h3><bean:message key="usuarios.preferencias"/></h3>	
				<div class="globo_blanco conmargen02" >
				<div class="globo_blanco_01">
					<div class="globo_blanco_02">
						<div class="globo_blanco_03">

					  					<!--		CAMPO DE TIPO DE EMPAQUETADOR		 -->
					  					
										<div class="fila_de_tabla">
						  					<div class="contenedor_izquierda">
						  						<div class="text"><label for="Editor"><bean:message key="usuarios.tipoDeEmpaquetador"/></label></div>
						 					</div>
											<div class="contenedor_derecha">
												<div class="text">
												<c:if test="${form.rolEmpaquetador == 'S'}">
													<input readonly="readonly" name="tipoEmpaquetador" value="${form.tipoEmpaquetador}" id="TipoEmpaquetador" style="background-color: #e1e1e1" maxlength="25" type="text" title="<bean:message key="usuarios.seleccioneEmpaquetador"/> "  />
												</c:if> 
												<c:if test="${form.rolEmpaquetador == 'N'}">
													
													<html:select name="form" property="tipoEmpaquetador" titleKey="usuarios.seleccioneEmpaquetador" styleId="Editor">
														<html:option key="usuarios.basico" value="basico" />
														<html:option key="usuarios.avanzado" value="avanzado" styleClass="oscura" />
													</html:select>
												</c:if>
												</div>
											</div>
											<div class="linea_separadora"></div>
											<br class="oculto" />
										</div>
										
										
										<!--		CAMPO DE TIPO DE CATALOGADOR		 -->
					  					
										<div class="fila_de_tabla">
						  					<div class="contenedor_izquierda">
						  						<div class="text"><label for="TipoCatalogador">*<bean:message key="usuarios.tipoCatalogador"/></label></div>
						 					</div>
											<div class="contenedor_derecha">
												<div class="text">
												
													<c:if test="${form.rolCatalogador == 'S'}">
														<input readonly="readonly"  name="tipoCatalogador" value="${form.tipoCatalogador}" id="TipoCatalogador" style="background-color: #e1e1e1" maxlength="25" type="text" title="<bean:message key="usuarios.seleccioneCatalogador"/> "  />
													</c:if> 
													<c:if test="${form.rolCatalogador == 'N'}">

														<html:select name="form" property="tipoCatalogador" titleKey="usuarios.seleccioneCatalogador" styleId="TipoCatalogador">
															<html:option key="usuarios.basico" value="basico" />
															<html:option key="usuarios.avanzado" value="avanzado" styleClass="oscura" />
														</html:select>
													
													</c:if>
											
												</div>
											</div>
											<div class="linea_separadora"></div>
											<br class="oculto" />
										</div>
										
										
										<!--		CAMPO DE TIPO DE VISUALIZADOR		 -->
					  					
										<div class="fila_de_tabla">
						  					<div class="contenedor_izquierda">
						  						<div class="text"><label for="TipoVisualizador">*<bean:message key="usuarios.tipoVisualizador"/></label></div>
						 					</div>
											<div class="contenedor_derecha">
												<div class="text">
												
													<html:select name="form" property="tipoVisualizador" titleKey="usuarios.seleccioneVisualizador" styleId="TipoVisualizador">
														<html:option key="usuarios.visualizador.agrega" value="agrega" />
														<html:option key="usuarios.visualizador.adl" value="adl" styleClass="oscura" />
													</html:select>
											
												</div>
											</div>
											<div class="linea_separadora"></div>
											<br class="oculto" />
										</div>
										
										
										<!--		CAMPO DE CUOTA		 -->
					  					
										<div class="fila_de_tabla">
						  					<div class="contenedor_izquierda">
						  						<div class="text"><label for="cuota"><bean:message key="usuarios.cuota"/></label></div>
						 					</div>
											<div class="contenedor_derecha">
												<div class="text">
													<c:if test="${form.rolAdministrador == 'S'}">
														<input gtbfieldid="7" name="cuota" type="text"  style="width:40px" value="${form.cuota}" onblur="this.style.backgroundColor='#e1e1e1'" id="cuota" maxlength="5" title="<bean:message key="usuarios.IntroduzcaCuota"/> "  />
													</c:if>
													<c:if test="${form.rolAdministrador == 'N'}">
														<input readonly="readonly" gtbfieldid="7" name="cuota" type="text"  style="width:40px;background-color: #e1e1e1" value="${form.cuota}"  onblur="this.style.backgroundColor='#e1e1e1'"  id="cuota" maxlength="5" title="<bean:message key="usuarios.IntroduzcaCuota"/> "  />
													</c:if>
													<label class="lb_long">&nbsp;<bean:message key="usuarios.Mb"/></label>
												</div>
											</div>
											<div class="linea_separadora"></div>
											<br class="oculto" />
										</div>
							
											
										<!--		CAMPO DE IDIOMA DE APLICACION		 -->
										
										<div class="fila_de_tabla">
						  					<div class="contenedor_izquierda">
						  						<div class="text"><label for="Idiomaaplicaciones"><bean:message key="usuarios.idiomaAplicacion"/></label></div>
						 					</div>
											<div class="contenedor_derecha">
												<div class="text">
												
													<c:choose>
						                               <c:when test="${!empty form.idiomaBackingList}">
						                                  <html:select name="form" property="idioma"  titleKey="usuarios.seleccioneIdiomaAplicacion" styleId="Idiomaaplicaciones">
						                                      <html:optionsCollection name="form" property="idiomaBackingList" label="label" value="value"/>
						                                  </html:select>
						                               </c:when>
						                               <c:otherwise>
						                                   <html:select name="form" property="idioma" styleId="Idiomaaplicaciones"/>
						                               </c:otherwise>
						                           </c:choose>
												
												</div>
											</div>
											<div class="linea_separadora"></div>
											<br class="oculto" />
										</div>	
										
										<!-- 		CAMPO DE IDIOMA DE CONSULTA			-->
										
										<div class="fila_de_tabla">
						  					<div class="contenedor_izquierda">
						  						<div class="text"><label for="Idiomaconsultas"><bean:message key="usuarios.idiomaConsultas"/></label></div>
						 					</div>
											<div class="contenedor_derecha">
												<div class="text">
												
													<c:choose>
						                               <c:when test="${!empty form.idiomaBusquedaBackingList}">
						                                  <html:select name="form" property="idiomaBusqueda"  titleKey="usuarios.seleccioneIdiomaConsultas" styleId="Idiomaconsultas">
						                                      <html:optionsCollection name="form" property="idiomaBusquedaBackingList" label="label" value="value"/>
						                                  </html:select>
						                               </c:when>
						                               <c:otherwise>
						                                   <html:select name="form" property="idiomaBusqueda"  styleId="Idiomaconsultas"/>
						                               </c:otherwise>
						                           </c:choose>
												
												</div>
											</div>
											<div class="linea_separadora"></div>
											<br class="oculto" />
										</div>
										
									
										
										
									</div>
								</div>
							</div>
						</div>
						
						<!--  Fin GLOBO Blanco   -->
<!--  Fin GLOBO Blanco   -->

            		
					</div>
					
					<!--		FIN CAJA DE FORMULARIO 3 (Preferencias)		-->
					
					
					
					
				</div>
			</div>
		</div>
	</div>
	
	<!--		FIN GLOBO GRIS		-->
	
	
	<!--		Boton Siguiente		-->
	<fieldset class="tipo ft_centrada" id="con_loading">
	<input class="boton_125_de_2 tipo"  type="submit"  value="<bean:message key='usuarios.aceptar'/>" />
	
	<script type="text/javascript">
	function disableCheck(id) {
		if (document.getElementById(id).checked==true){
			document.getElementById("mostrarObjetos").checked=false;
			document.getElementById("mostrarObjetos").disabled=false;
			document.getElementById("mostrarFavoritos").checked=false;
			document.getElementById("mostrarFavoritos").disabled=false;
			document.getElementById("mostrarGrupos").checked=false;
			document.getElementById("mostrarGrupos").disabled=false;
		}else{
			document.getElementById("mostrarObjetos").checked=false;
			document.getElementById("mostrarFavoritos").checked=false;
			document.getElementById("mostrarGrupos").checked=false;
			document.getElementById("mostrarObjetos").disabled=true;
			document.getElementById("mostrarFavoritos").disabled=true;
			document.getElementById("mostrarGrupos").disabled=true;
		}
	}
</script>
</form>
<form id="modificarPerfilFormularioIniciaCancelarForm" action='<html:rewrite action="/ModificarPerfil/FormularioIniciaCancelar.do"/>' method="post" >
	<input class="boton_125_de_2_izq tipo"  type="submit"  value="Cancelar" />
</form>
</fieldset>
	


<!--		Fin del Formulario principal		-->


	<div id="loading"  style="z-index:100 !important">
<%@ include file="/progressBar/barra.jsp"%>
</div>
<%@ include file="/progressBar/cabecera-barra.jsp" %>
<!--		Fin formulario secundario		-->
	

</div>
<!--		Fin de la capa plantilla_contenido		-->


</tiles:put>


</tiles:insert>