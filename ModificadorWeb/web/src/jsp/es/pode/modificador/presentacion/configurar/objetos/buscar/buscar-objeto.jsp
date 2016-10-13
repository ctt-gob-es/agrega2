<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html:xhtml/>
<tiles:insert definition="${sessionScope.offline == true ? 'layout-offline' : 'layout-administrador'}">

<tiles:put name="title" type="string">
      <bean:message key="title.Comun"/>
</tiles:put>

<tiles:put name="body" type="string">

<!--  -->
<!-- Inicio plantilla contenido  -->
<div class="plantilla_contenido">
<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />

<h2><bean:message key="buscarObjeto.titulo"/></h2>
<form method="post" action="<html:rewrite action="/BuscarObjetos/BuscarObjetoSelectAction"/>" id="buscarObjetosForm">
<p class="parrafo_comun" id="separacion2"><bean:message key="resultadosObjetos.introduzca"/></p>

<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" id="ficha">
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->

			<div id="formulario" >
				<!-- -->
				<div class="fila_de_tabla">
  					<div class="contenedor_izquierda">
  						<div class="text"><label for="iden"><bean:message key="buscarObjeto.identificador"/>:</label></div>
 					</div>
					<div class="contenedor_derecha">
						<div class="text"><input name="identificador" onfocus="limpiarTexto(this)" value="${form.identificador}"  onblur="this.style.backgroundColor='#e1e1e1'" id="identificador" type="text" title="<bean:message key="buscarObjeto.identificador.introduzca"/>"  /></div>

					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>
				<!-- -->
				<div class="fila_de_tabla">
  					<div class="contenedor_izquierda">
  						<div class="text"><label for="titulo"><bean:message key="buscarObjeto.titulo"/>:</label></div>

 					</div>
					<div class="contenedor_derecha">
						<div class="text"><input name="titulo" onfocus="limpiarTexto(this)" value="${form.titulo}"  onblur="this.style.backgroundColor='#e1e1e1'" id="titulo" type="text" title="<bean:message key="buscarObjeto.titulo.introduzca"/>"  /></div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>
				<!-- -->
				<div class="fila_de_tabla">

  					<div class="contenedor_izquierda">
  						<div class="text"><label for="autor"><bean:message key="buscarObjeto.autor"/>:</label></div>
 					</div>
					<div class="contenedor_derecha">
						<div class="text"><input name="autor" onfocus="limpiarTexto(this)" value="${form.autor}"  onblur="this.style.backgroundColor='#e1e1e1'" id="autor" type="text" title="<bean:message key="buscarObjeto.autor.introduzca"/>"  /></div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />

				</div>
				<input type="hidden" name="enlaceTaxSelec" value="${form.enlaceTaxSelec}">
				<!-- ******************************************************************* -->
				<!-- ************************* TAXONOMIAS******************************* -->
				<!-- ******************************************************************* -->
				<div class="fila_de_tabla">
  					<div class="contenedor_izquierda">					  					
  						<div class="text"><label for="areaC"><bean:message key="buscarObjeto.taxonomia"/>:</label></div>
 					</div>
 
					<div class="contenedor_derecha" >
						<div class="text">
							<table border="1" cellpadding="0" cellspacing="0" style="font-size:100%" width="90%">
								<tr>
									<td align="left">
										<table border="0" cellpadding="0" cellspacing="0" width="100%">
											<tr>
												<td colspan="2"><label for="Taxonomia01" class="oculto"><bean:message key="buscarObjeto.taxonomia"/>:</label>
													<html:select name="form" property="taxonomias" titleKey="buscarObjeto.taxonomia" styleId="taxonomia" styleClass="slct_medio">
															<html:optionsCollection name="form" property="taxonomiasBackingList" label="label" value="value"/>
													</html:select>
												</td>
												<td>&nbsp;</td>
												<td valign="top" align="right"><input class="boton_125" type="submit" style="top:0;height:22px;margin:0" name="action" value="<bean:message key="buscarObjeto.taxonomia.aniadir"/>" /></td>		
											</tr>
										</table>	
									</td>
								</tr>
								<logic:notEmpty name="form" property="taxonesSeleccionados">
									<fmt:formatNumber var="contIndices" value="0" type="number"/>
									<tr>
										<td colspan="2">
											<table border="0" cellpadding="0" cellspacing="0" width="100%" class="inv_taxo">
												<logic:iterate id="ruta"  name="form" property="taxSelec" indexId="i">
													<c:if test="${contIndices%2==0}">
							 		    				<tr class="gris_01">
       														<td>
  																	<html:multibox name="form" styleId="taxSelec" property="taxSelec" value="${contIndices}" styleClass="check03"/>
  																</td>	
							        			  			<td class="taxo"><span class="sola_rea2" title="${form.fuentes[i]}->${ruta}" >${ruta}</span></td>
     										 				</tr>
     														</c:if>
													<c:if test="${contIndices%2!=0}">
							        					<tr>
															<td>
   																<html:multibox name="form" property="taxSelec" styleClass="check03" value="${contIndices}"/>
  																</td>	
							        			  			<td class="taxo"><span class="sola_rea2" title="${form.fuentes[i]}->${ruta}" >${ruta}</span></td>
								 				 		</tr> 	
								    				</c:if>
     														<c:set var="contIndices" value="${contIndices+1}"/>
												</logic:iterate>
											</table>
										</td>
									</tr>		
									<tr>
										<td>&nbsp;</td>
										<td valign="top" align="right"><input class="boton_125"  id="Eliminar" type="submit" name="action" style="top:0;height:22px;margin:0"  value="<bean:message key="buscarObjeto.taxonomia.eliminar"/>" /></td>		
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
				<bean:define id="nomMeses"><bean:message key="catalogadorAvanzado.nombresMeses"/></bean:define>
				<bean:define id="nomDias"><bean:message key="catalogadorAvanzado.nombresDias"/></bean:define>
				<bean:define id="offSet"><bean:message key="offset"/></bean:define>
				<div class="fila_de_tabla">
  					<div class="contenedor_izquierda">
  						<div class="text"><label for="fecha_inicio"><bean:message key="buscarObjeto.fechaPublicacion"/>:</label></div>
 					</div>
					<div class="contenedor_derecha" id="cont_esp_fech">
					
						<div class="text">

						<span class="vert2"><bean:message key="buscarObjeto.entre"/>:&nbsp;&nbsp;</span>
						<label class="oculto" for="day1"><bean:message key="buscarObjeto.fechaInicio"/>:</label>
						<input name="diaDesde"  value="${form.diaDesde}" class="fechazo" onblur="this.style.backgroundColor='#e1e1e1'" maxlength="2" id="day1" type="text" title="<bean:message key="buscarObjeto.entre.introduzca"/>"  />
						<label class="oculto" for="month1"><bean:message key="buscarObjeto.fechaMes"/>:</label>
						<input name="mesDesde"  maxlength="2" value="${form.mesDesde }" class="fechazo" onblur="this.style.backgroundColor='#e1e1e1'" id="month1" type="text" title="<bean:message key="buscarObjeto.fechaMes.introduzca"/> "  />
						<label class="oculto" for="year1"><bean:message key="buscarObjeto.fechaAnio"/>:</label>	
						<input name="anyoDesde"  maxlength="4" value="${form.anyoDesde}" class="fechazo" onblur="this.style.backgroundColor='#e1e1e1'" id="year1" type="text" title="<bean:message key="buscarObjeto.fechaAnio.introduzca"/>"  />
						</div>
						<div class="text">
						<span class="vert2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="comun.and"/>&nbsp;&nbsp;</span>
						<label class="oculto" for="day2"><bean:message key="buscarObjeto.fechaFin"/>:</label>
						<input name="diaHasta"  value="${form.diaHasta}" class="fechazo" onblur="this.style.backgroundColor='#e1e1e1'" maxlength="2" id="day2" type="text" title="<bean:message key="buscarObjeto.entre.introduzca2"/>"  />
						<label class="oculto" for="month2"><bean:message key="buscarObjeto.fechaMes"/>:</label>
						<input name="mesHasta" maxlength="2" value="${form.mesHasta}" class="fechazo" onblur="this.style.backgroundColor='#e1e1e1'" id="month2" type="text" title="<bean:message key="buscarObjeto.fechaMes.introduzca2"/>"  />
						<label class="oculto" for="year2"><bean:message key="buscarObjeto.fechaAnio"/>:</label>	
						<input name="anyoHasta" onfocus="limpiarTexto(this)" maxlength="4" value="${form.anyoHasta}" class="fechazo" onblur="this.style.backgroundColor='#e1e1e1'" id="year2" type="text" title="<bean:message key="buscarObjeto.fechaAnio.introduzca2"/>"  />

</div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>
				<!-- Idioma de busqueda -->
				<div class="fila_de_tabla">
  					<div class="contenedor_izquierda">
  						<div class="text"><label for="idiomaBusqueda"><bean:message key="buscarObjeto.idioma"/></label></div>
 					</div>
					<div class="contenedor_derecha">
						<div class="text"><html:select name="form" property="idioma" titleKey="buscarObjeto.seleccioneIdioma" styleId="idiomaBusqueda"  styleClass="caja_buscador_selector">
							<html:optionsCollection name="form" property="idiomaBackingList" label="label" value="value"/>
						</html:select></div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />

				</div>
				<!-- -->
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
<fieldset class="tipo ft_centrada">
<input class="boton_125_de_2"  name="action" type="submit"  value=<bean:message key="buscarObjeto.Buscar"/> />
<input class="boton_125_de_2_izq"  name="action" type="submit"  value=<bean:message key="comun.volver"/> />

</fieldset>
<!-- Fin Botones  -->
<!-- Fin Botones  -->

</form>
</div>
<!-- Fin plantilla contenido  -->


    </tiles:put>

</tiles:insert>
<script type="text/javascript">		
//<![CDATA[
	window.addEvent('domready', function() { 
		jsMeses = "${nomMeses}"; arrayMeses = jsMeses.split(",");
		jsDias = "${nomDias}"; arrayDias = jsDias.split(",");
		myCal1 = new Calendar({ year1: { day1: 'd', month1: 'm', year1: 'Y' }}, { direction: 0, tweak: {x: 6, y: 0},offset: ${offSet},months: arrayMeses ,days: arrayDias });
			myCal2 = new Calendar({ year2: { day2: 'd', month2: 'm', year2: 'Y' }}, { direction: 0, tweak: {x: 6, y: 0},offset: ${offSet},months: arrayMeses ,days: arrayDias });
			});
	//]]>
</script>
