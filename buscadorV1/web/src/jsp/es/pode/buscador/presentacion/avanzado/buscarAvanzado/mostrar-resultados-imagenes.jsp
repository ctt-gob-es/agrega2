<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/WEB-INF/tags/serverProperties.tld" prefix="server" %>
<%@ taglib uri="/WEB-INF/tags/tablaImagenes.tld" prefix="imagenes" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib uri="/WEB-INF/tags/googleAnalytic.tld" prefix="analytic" %>
<%@ page import="es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils"%>
<%@ taglib prefix="agrega" uri="/WEB-INF/tags/agregaProperties.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<tiles:insert definition="layout-sinlateralYsinbuscadorSuperior">
    <tiles:put name="title" type="string">
        <bean:message key="title.Comun"/>
    </tiles:put>
   
    <tiles:put name="body" type="string">
		<%@ include file="/taglib-imports.jspf" %>
		<!-- Inicio plantilla contenido  -->
			<!-- Pintar mensajes de error  -->
			<jsp:include page="/layout/messages.jsp" flush="true" />

<!-- Inicio plantilla contenido  -->
 <div class="plantilla_contenido">


<analytic:googleAnalytic />

<!-- Inicio caja buscador -->
<!-- Inicio caja buscador -->
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->

<!-- NAVEGACIÓN POR TAXONOMIA EN BÚSQUEDA TAXONÓMICA -->
<c:if test="${form.tipoBusqueda=='05'||form.tipoBusqueda=='06'}">

			<form method="post" action="<html:rewrite action=""/>" >
			<div class="globo_gris" >
			<div class="globo_gris_01">
				<div class="globo_gris_02">
					<div class="globo_gris_03">
						<!--  INICIO CAJA DE FORMULARIO   -->
						<div id="formulario" >
				<!--<h2><bean:message key="listarODE.arbolCurricular.nombre"/></h2>-->
				<h2>${form.taxNombre}</h2>
				 <div class="fila_de_tabla">
					<p><bean:message key="listarODE.arbolCurricular.explicacion.taxonomia"/></p>
					<div class="breadcrumb_curricular">
						<html:link styleClass="br_carpeta_abierta" action="/NavegarArbolCurricularCU/NavegarArbolCurricularCU.do?tipoBusqueda=${form.tipoBusqueda}&tipoNavegacion=${form.tipoNavegacion}">
    						<bean:message key="listarODE.taxonomia.inicio"/><br/>
      					</html:link>
						 <c:if test="${!empty form.rutaArbol}">
							 <c:set var="longitud" value="${ fn:length(form.rutaArbol)}"/>
							 <fmt:formatNumber var="nombre" value="${longitud -1}" type="number"/>
							 <fmt:formatNumber var="nombre2" value="${longitud -2}" type="number"/>
							 <c:if test="${longitud>0 }">
							 	<c:if test="${nombre2>=0 }">
								   <c:forEach items="${form.rutaArbol}" var="ruta" begin="0" end="${nombre2}">
								    	<em class="oculto">-</em>
								    	<html:link styleClass="br_carpeta_abierta" action="/NavegarArbolCurricularCU/ArbolCurricularSeleccionarPadre.do?areaCurricularBusqueda=${ruta.id}&tipoBusqueda=${form.tipoBusqueda}&tipoNavegacion=${form.tipoNavegacion}">
						        			<c:out value="${ruta.valorTax}"/><br/>
						        		</html:link>
								    </c:forEach>
								</c:if>
							    <c:set var="ultimo" value="${ form.rutaArbol[nombre]}"/>
							    <logic:equal name="form" property="esHoja" value="false">
							    	<html:link styleClass="br_carpeta_abierta" action="/NavegarArbolCurricularCU/ArbolCurricularSeleccionarPadre.do?areaCurricularBusqueda=${form.rutaArbol[nombre].id}&tipoBusqueda=${form.tipoBusqueda}&tipoNavegacion=${form.tipoNavegacion}">
					        			<c:out value="${ultimo.valorTax}"/><br/>
					        		</html:link>
								</logic:equal>
								<logic:equal name="form" property="esHoja" value="true">
							    	<span class="br_carpeta_abierta">
								    	 <c:out value="${ultimo.valorTax}"/><br/>
								     </span>
								</logic:equal>
							</c:if>
							
						     <em>(<c:out value="${form.numMaxResultados}"/>
							     <c:if test="${form.numMaxResultados==1 }">
							     	<bean:message key="listarODE.arbolCurricular.objeto"/>
							     </c:if>
							      <c:if test="${form.numMaxResultados!=1 }">
							      	<bean:message key="listarODE.arbolCurricular.objetos"/>
							 </c:if>)</em>
				 		</c:if>
					</div>
				</div>
				</div>
				</div>
				</div>
				</div>
				</div>
				</form>
				
</c:if>
<!-- NAVEGACIÓN POR TESAURO EN BÚSQUEDA TAXONÓMICA -->
<c:if test="${form.tipoBusqueda=='07'||form.tipoBusqueda=='08'}">

<form method="post" action="<html:rewrite action=""/>" >
<div class="globo_gris" >
			<div class="globo_gris_01">
				<div class="globo_gris_02">
					<div class="globo_gris_03">
						<!--  INICIO CAJA DE FORMULARIO   -->
						<div id="formulario" >
				<h2>${form.tituloTesauro}</h2>
				<div class="fila_de_tabla">
					<p><bean:message key="navegar.tesauro.explicacion.taxonomia"/></p>
					<div class="breadcrumb_curricular">
						<html:link styleClass="br_carpeta_abierta" action="/NavegarTesauroCU/NavegarTesauroCU.do?tipoBusqueda=${form.tipoBusqueda}&tituloTesauro=${form.tituloTesauro}&tipoNavegacion=${form.tipoNavegacion}">
    						<bean:message key="navegar.tesauro.nombre.inicio"/><br/>
      					</html:link>
						 <c:if test="${!empty form.rutaTesauro}">
							 <c:set var="longitud" value="${ fn:length(form.rutaTesauro)}"/>
							 <fmt:formatNumber var="nombre" value="${longitud -1}" type="number"/>
							 <fmt:formatNumber var="nombre2" value="${longitud -2}" type="number"/>
							 <c:if test="${longitud>0 }">
							 	<c:if test="${nombre2>=0 }">
								   <c:forEach items="${form.rutaTesauro}" var="ruta" begin="0" end="${nombre2}">
								    	<em class="oculto">-</em>
								    	<html:link styleClass="br_carpeta_abierta" action="/NavegarTesauroCU/NavegarTesauroSeleccionarPadre.do?idTesauro=${ruta.id}&tipoBusqueda=${form.tipoBusqueda}&tituloTesauro=${form.tituloTesauro}&tipoNavegacion=${form.tipoNavegacion}">
						        			<c:out value="${ruta.valorTax}"/><br/>
						        		</html:link>
								    </c:forEach>
								</c:if>
							    <c:set var="ultimo" value="${ form.rutaTesauro[nombre]}"/>
							    <logic:equal name="form" property="esHoja" value="false">
							    	<html:link styleClass="br_carpeta_abierta" action="/NavegarTesauroCU/NavegarTesauroSeleccionarPadre.do?idTesauro=${form.rutaTesauro[nombre].id}&tipoBusqueda=${form.tipoBusqueda}&tituloTesauro=${form.tituloTesauro}&tipoNavegacion=${form.tipoNavegacion}">
					        			<c:out value="${ultimo.valorTax}"/><br/>
					        		</html:link>
								</logic:equal>
								<logic:equal name="form" property="esHoja" value="true">
							    	<span class="br_carpeta_abierta">
								    	 <c:out value="${ultimo.valorTax}"/><br/>
								     </span>
								</logic:equal>
							</c:if>
							
						     <em>(<c:out value="${form.numMaxResultados}"/>
							     <c:if test="${form.numMaxResultados==1 }">
							     	<bean:message key="listarODE.arbolCurricular.objeto"/>
							     </c:if>
							      <c:if test="${form.numMaxResultados!=1 }">
							      	<bean:message key="listarODE.arbolCurricular.objetos"/>
							 </c:if>)</em>
				 		</c:if>
					</div>
				</div>
				</div>
				</div>
				</div>
				</div>
				</div>
				
				

</form>

</c:if>

<!-- CAJA DE BÚSQUEDA NORMAL -->
<c:if test="${form.tipoBusqueda!='05' && form.tipoBusqueda!='06'&&form.tipoBusqueda!='07' && form.tipoBusqueda!='08'}">

	<div class="resultados_b">
				<bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.desdeHasta" arg0="${form.resultadosDesde}" arg1="${form.resultadosHasta}" arg2="${form.numMaxResultados}"/>
			</div>
			<h2><bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.resultadoBusqueda"/></h2>		



			<!--  INICIO CAJA DE FORMULARIO   -->
								
								<form method="post" action="<html:rewrite action="/BuscarAvanzadoCU/MostrarResultadosImagenesPaginarImagenes.do"/>" name="formularioBusqueda">
									<!-- Inicio caja buscador -->
									<!-- Inicio caja buscador -->
									<!--  INICIO GLOBO GRIS   -->
									<!--  INICIO GLOBO GRIS   -->

								<div class="globo_gris" >
									<div class="globo_gris_01">
										<div class="globo_gris_02">
											<div class="globo_gris_03">
											<!--  INICIO CAJA DE FORMULARIO   -->
												<div id="formulario" >	
					  				<div class="fila_de_tabla especial_sangrado" >
  										<div class="contenedor_izquierda_00" >
											&nbsp;<label class="oculto" for="buscContenido"><bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.buscador"/>:</label>
										</div>
										<div class="contenedor_derecha_00">
											<div class="text"><input name="buscContenido" value="${form.buscContenido}"  onblur="this.style.backgroundColor='#e1e1e1'" id="buscadorContenido" type="text" title="<bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.descripcionTextoBusqueda"/>"  /><br class="oculto" />
												<label class="oculto" for="idiomaBuscadorContenido">
													<bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.elijaIdioma"/>:
												</label>
												<bean:define id="tituloSeleccioneTipoIdioma"><bean:message key="configurar.avanzado.atributo.seleccionar.tipoIdioma"/></bean:define>
												<html:select name="form" property="idiomBusc" styleId="idiomaBuscadorContenido" title="${tituloSeleccioneTipoIdioma}">
													<html:optionsCollection name="form"  property="idiomBuscBackingList" label="label" value="value"/>
												</html:select>
												<br class="oculto" /><input type="submit" alt="<bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.botonBuscar"/>"  class="buscar"  value="<bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.buscar"/>" name="buscar" /><br class="oculto" /><a href="<html:rewrite action="/BuscarAvanzadoCU/MostrarResultadosImagenesMostrarAvanzadoResultados.do"/>?buscContenido=${form.buscContenEnlace}&amp;idiomBusc=${form.idioma}&amp;pagina=${form.anterior}&amp;formato=${form.formato}&amp;idODE=${form.idODE}&amp;recurso=${form.recurso}&amp;procesoCognitivo=${form.procesoCognitivo}&amp;contexto=${form.contexto}&amp;edad=${form.edad}&amp;autor=${form.autor}&amp;diaPublic=${form.diaPublic}&amp;mesPublic=${form.mesPublic}&amp;anyoPublic=${form.anyoPublic}&amp;c_s_secuencia=${form.c_s_secuencia}&amp;valoracion=${form.valoracion}&amp;enlaceTaxSelec=${form.enlaceTaxSelec}&amp;enlaceComuSelec=${form.enlaceComuSelec}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;idTesauro=${form.idTesauro}&amp;nomTesauros=${form.nomTesauros}&amp;nivelAgreg=${form.nivelAgreg}&amp;destinatarios=${form.destinatarios}&amp;keyword=${form.keyword}&amp;tipoBusqueda=${form.tipoBusqueda}" class="avanzado"><bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.avanzado"/></a><logic:equal name="form" property="tipoLayoutBuscador" value="NEUTRO"><a href="<rewrite:rewrite url="TaggingWeb/InicioTagging/InicioTagging.do"/>" class="avanzado"><bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.etiquetadoSocial"/></a></logic:equal>
												<input type="hidden" name="tipoVisualiz" value="${form.tipoVisualiz}"/>
												<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
												<input type="hidden" name="busqSimpleAvanz" value="${form.busqSimpleAvanz}"/>
												
												<logic:equal name="form" property="tipoBusqueda" value="03">
													<input type="hidden" name="keyword" value="${form.keyword}"/>
													<input type="hidden" name="formato" value="${form.formato}"/>
													<input type="hidden" name="recurso" value="${form.recurso}"/>
													<input type="hidden" name="procesoCognitivo" value="${form.procesoCognitivo}"/>
													<input type="hidden" name="contexto" value="${form.contexto}"/>
													<input type="hidden" name="edad" value="${form.edad}"/>
													<input type="hidden" name="autor" value="${form.autor}"/>
													<input type="hidden" name="diaPublic" value="${form.diaPublic}"/>
													<input type="hidden" name="mesPublic" value="${form.mesPublic}"/>
													<input type="hidden" name="anyoPublic" value="${form.anyoPublic}"/>
													<input type="hidden" name="c_s_secuencia" value="${form.c_s_secuencia}"/>
													<input type="hidden" name="valoracion" value="${form.valoracion}"/>
													<input type="hidden" name="nivelAgreg" value="${form.nivelAgreg}"/>
													<!--<input type="hidden" name="areaCurricular" value="${form.areaCurricular}"/>-->
													<input type="hidden" name="enlaceTaxSelec" value="${form.enlaceTaxSelec}"/>
													<input type="hidden" name="destinatarios" value="${form.destinatarios}"/>
													<input type="hidden" name="keyword" value="${form.keyword}"/>
													<input type="hidden" name="idTesauro" value="${form.idTesauro}"/>
													<input type="hidden" name="" value="${form.usuarioAdmin}"/>
													<input type="hidden" name="nomTesauros" value="${form.nomTesauros}"/>
													<input type="hidden" name="enlaceComuSelec" value="${form.enlaceComuSelec}"/>	
													<input type="hidden" name="idODE" value="${form.idODE}"/>	
												</logic:equal>
											</div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>

				<!--HASTA AQUI -->
				<!-- -->


												
							<div class="fila_de_tabla especial_sangrado">

								  		<div class="contenedor_izquierda_00">
											&nbsp;<label class="oculto" for="todosNodos">Nodos:</label>
										</div>
										<div class="contenedor_derecha_00">
											<div class="text"><span class="vert"><bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.numeroResultados"/></span>&nbsp;&nbsp;
											<html:select name="form" property="numeroResultados" styleId="numeroResultados" onchange="enviarFormulario();" title="${tituloSeleccioneNumeroResultados}" style="width:126px">
													<html:optionsCollection name="form"  property="numeroResultadosBackingList" label="label" value="value"/>
												</html:select>
											
										</div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>


				<!-- -->	

											
									<logic:equal name="form" property="mostrarAmbito" value="true">	
									
										<div class="fila_de_tabla especial_sangrado">

						  					<div class="contenedor_izquierda_00">
												&nbsp;<label class="oculto" for="todosNodos"><bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.nodos"/>:</label>
											</div>
											<div class="contenedor_derecha_00">
	   										 <div class="text">
	   										 	<html:radio property="tipoBusqueda" onclick="habilitarCombos();" styleClass="boton_radio" styleId="todosNodos" name="form" value="01" />
	           									 <span class="vert">
	                  								<bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.nodosTodos"/>
	            								 </span>
	            								<html:radio property="tipoBusqueda" onclick="habilitarCombos();" styleClass="boton_radio marginadito" styleId="todosNodos" name="form" value="02" />
	                 							 <span class="vert">
	                        						<server:serverProperties property="ccaa"/>
	                  							 </span>
												<logic:equal name="form" property="nodosSQI" value="true">
		                  							 <html:radio property="tipoBusqueda" onclick="deshabilitarCombos();" styleClass="boton_radio marginadito" styleId="todosNodos" name="form" value="04" />
			                 							 <span class="vert">
	        		                						<bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.otros"/>
	                		  							 </span>
	                		  					</logic:equal>
	                  							 <logic:equal name="form" property="tipoBusqueda" value="03">
	                  							 &nbsp;&nbsp;&nbsp;
		                  							 <html:radio property="tipoBusqueda" onclick="habilitarCombos();" styleClass="boton_radio" styleId="Personalizada" name="form" value="03" />
		                  							 <span class="vert">
														<bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.personalizada"/>&nbsp;&nbsp;
													 </span>
												 </logic:equal>
												</div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />

				</div>
				<!-- -->
				<!-- </div>-->

				

								
						
						<!--  FIN CAJA DE FORMULARIO   -->

							</logic:equal>
							</div>
							<div id="niveles">				


						<!--  INICIO GLOBO GRIS   -->
							<!--  INICIO GLOBO GRIS   -->
							<div class="globo_blanco" >
								<div class="globo_blanco_01">
									<div class="globo_blanco_02">

									<div class="globo_blanco_03">
								
							
							<div class="caja_dinamica" ><a class="desplegado" id="pm3" href="#" onclick="expandirCaja('m3');return false;"><br class="falso" /><strong id="dm3" ><bean:message key="configurar.avanzado.ver2"/></strong></a>
							<h3>
							<bean:message key="configurar.avanzado.atributo.nivelAgregacion"/>:
							</h3>
							</div>
							<div id="m3" class="caja_cerrada" >
								  <div class="formu conmargen">
								<!-- INICIO TABLA  -->
								<div class="caja_de_tabla_invisible">

									<table cellspacing="0" cellpadding="0" border="1" width="100%"  summary="Nivel Agregacion">
											 <fmt:formatNumber var="contIndices" value="1" type="number"/>
	   										 	<logic:iterate id="valor"   name="form" property="nivelAgregBackingList">
	   										 	<c:if test="${contIndices%2!=0}">
	            								<tr>
	            								</c:if>
												
	   											<td>
	   												<html:multibox styleClass="check" name="form" styleId="Nivel0${valor.value}" property="filtroNivelAgregacion" value="${valor.value}"/>	
	   											</td>	
	   									
	   											<td class="td_largo_09" valign="top"><label for="Nivel0${valor.value}">${valor.label}</label></td>
	   											<c:if test="${contIndices%2==0}">
	   											</tr>
	   											</c:if>
	   											<c:set var="contIndices" value="${contIndices+1}"/>
   												</logic:iterate>	
	      								
									</table>
</div>
</div>
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

<div class="globo_blanco" >
	<div class="globo_blanco_01">
		<div class="globo_blanco_02">
			<div class="globo_blanco_03">
				<!--  INICIO CAJA DE FORMULARIO   -->

								
								  <!-- INICIO TABLA  -->
						<div class="caja_dinamica" ><a class="desplegado" id="pm4" href="#" onclick="expandirCaja('m4');return false;"><br class="falso" /><strong id="dm4" ><bean:message key="configurar.avanzado.ver2"/></strong></a>
						<h3>
						<bean:message key="listar.odecu.mostrar.resultados.detalles.formato"/>:
						</h3>
							</div>
							<div id="m4" class="caja_cerrada" >
							<div class="formu conmargen">
						<!-- INICIO TABLA  -->
							<div class="caja_de_tabla_invisible">
							
									<table cellspacing="0" cellpadding="0" border="1" width="100%"  summary="Formato">
									  <fmt:formatNumber var="contIndices" value="1" type="number"/>
												<!--impares y 0-->
											 	<logic:iterate id="valor"   name="form" property="formatoBackingList">
											 	<c:if test="${contIndices%2!=0}">
	            								<tr>
	            								</c:if>
										
	   											<td>
	   												<html:multibox styleClass="check" name="form" styleId="Formato0${contIndices}" property="filtroFormato" value="${valor.value}"/>	
	   											</td>	
	   									
	   											<td class="td_largo_09" valign="top"><label for="Formato0${contIndices}">${valor.label}</label></td>
	   											<c:if test="${contIndices%2==0}">
	   											</tr>
	   											</c:if>
	   											<c:set var="contIndices" value="${contIndices+1}"/>
   												</logic:iterate>	
	      									
										
									</table>
							</div>
</div>
</div>
				<!--  FIN CAJA DE FORMULARIO   -->
			</div>
		</div>

	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->


</div>
			</div>
		</div>
	</div>
</div>
</form>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->
<!-- Fin caja buscador -->

<!-- Fin caja buscador -->
<br />




<!-- INICIO SUGERENCIAS -->

<div class="parrafo_comun">

		<logic:notEmpty name="form"	property="quisoDecir">
			<bean:message key="listar.odecu.mostrar.resultados.quiso.quisoDecir"/>:  
				<logic:iterate name="form" property="quisoDecir" id="sugerencia">
					<a href="<html:rewrite action="/BuscarAvanzadoCU/MostrarQuisoDecirAvanzadoBuscarQuisoDecir.do"/>?buscContenido=${sugerencia}&idiomBusc=${form.idioma}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;enlaceComuSelec=${form.enlaceComuSelec}&amp;tipoBusqueda=${form.tipoBusqueda}">${sugerencia}</a> 
				</logic:iterate>
			<br />
		</logic:notEmpty>
		
		<logic:notEmpty name="form" property="tesauros">
			<bean:message key="listar.odecu.mostrar.resultados.quiso.tesauros"/>
				<logic:iterate name="form" property="tesauros" id="taxon"> 
					<a href="<html:rewrite action="/BuscarAvanzadoCU/MostrarQuisoDecirAvanzadoQuisoDecir.do"/>?idTesauroSug=${taxon.identificador}&idiomBusc=${form.idioma}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;enlaceComuSelec=${form.enlaceComuSelec}&amp;tipoBusqueda=${form.tipoBusqueda}">${taxon.nombre}</a> 
				</logic:iterate>
			<br />
		</logic:notEmpty>
		
</div>

<!--  FIN SUGERENCIAS-->

</c:if>




<!-- Inicio  PESTANIAS de FICHA -->
<!-- Inicio  PESTANIAS de FICHA -->
<!--  <div id="ficha_pestanias">
	<ul> -->
		<!-- <li ><a href="/buscador/BuscarAvanzadoCU/MostrarResultadosAvanzadoBuscarResultados.do?buscContenido=${form.buscContenEnlace}&amp;idiomBusc=${form.idioma}&amp;pagina=${form.pagina}&amp;formato=${form.formato}&amp;recurso=${form.recurso}&amp;procesoCognitivo=${form.procesoCognitivo}&amp;contexto=${form.contexto}&amp;edad=${form.edad}&amp;autor=${form.autor}&amp;diaPublic=${form.diaPublic}&amp;mesPublic=${form.mesPublic}&amp;anyoPublic=${form.anyoPublic}&amp;c_s_secuencia=${form.c_s_secuencia}&amp;valoracion=${form.valoracion}&amp;areaCurricular=${form.areaCurricular}&amp;enlaceComuSelec=${form.enlaceComuSelec}&amp;tipoBusqueda=${form.tipoBusqueda}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;busqSimpleAvanz=${form.busqSimpleAvanz}&amp;tipoVisualiz=SIN_IMAGENES&amp;idTesauro=${form.idTesauro}&amp;nomTesauros=${form.nomTesauros}&amp;nivelAgreg=${form.nivelAgreg}&amp;destinatarios=${form.destinatarios}"><bean:message key="listar.odecu.mostrar.resultados.listado"/></a></li>
		<li id="pest_activa"><a  id="seleccionada" href="#"><bean:message key="listar.odecu.mostrar.resultados.imagenes"/></a></li> -->
<!-- 	</ul>
</div>  -->


<!-- Fin PESTANIAS de FICHA -->

<!-- Inicio CONTENIDO PESTANIAS -->
<!-- Inicio CONTENIDO PESTANIAS -->
<!-- <div class="interno_ficha"> -->


<!-- CAJA TABLA -->
<!-- CAJA TABLA -->

<form method="post" action="<html:rewrite action="/BuscarAvanzadoCU/MostrarResultadosImagenesEliminarODE.do"/>">
<div  id="nueva_cap_int_02"  style="width:956px;width:916px !important">

<!-- Inicio Resultados de Búsqueda -->
<!-- Inicio Resultados de Búsqueda -->
<div class="caja_tabla" id="marg_resultados_lis" style="margin-top:2px;" > 
	<table border="1" class="resultados_listados" cellpadding="0" cellspacing="0" width="100%" summary="Tabla de Resultado de Búsqueda:Imagenes">
		<caption><strong><bean:message key="listar.odecu.mostrar.resultados.listado"/></strong></caption>
			
								<tr>
					<th colspan="2" id="rev">
						<bean:define id="feedDefault"><agrega:agregaProperties property="feed_default"/></bean:define>
					 	<a href="<rewrite:rewrite url="feed/${feedDefault}/${form.buscContenEnlace}/${form.idioma}/${form.anterior}/${form.formatoURL}/${form.idODE}/${form.recurso}/${form.procesoCognitivo}/${form.contexto}/${form.edad}/${form.autor}/${form.diaPublic}/${form.mesPublic}/${form.anyoPublic}/${form.c_s_secuencia}/${form.valoracion}/${form.enlaceTaxSelec}/${form.enlaceComuSelec}/${form.tipoLayoutBuscador}/${form.idTesauro}/${form.nomTesauros}/${form.nivelAgreg}/${form.destinatarios}/${form.keyword}/${form.tipoBusqueda}/RSS"/>"><bean:message key="listar.odecu.mostrar.resultados.rss"/></a>   |&nbsp;&nbsp;<a href="<rewrite:rewrite url="/feed/${feedDefault}/${form.buscContenEnlace}/${form.idioma}/${form.anterior}/${form.formatoURL}/${form.idODE}/${form.recurso}/${form.procesoCognitivo}/${form.contexto}/${form.edad}/${form.autor}/${form.diaPublic}/${form.mesPublic}/${form.anyoPublic}/${form.c_s_secuencia}/${form.valoracion}/${form.enlaceTaxSelec}/${form.enlaceComuSelec}/${form.tipoLayoutBuscador}/${form.idTesauro}/${form.nomTesauros}/${form.nivelAgreg}/${form.destinatarios}/${form.keyword}/${form.tipoBusqueda}/ATOM"/>"><bean:message key="listar.odecu.mostrar.resultados.atom"/></a>&nbsp;
			
					</th>			
				</tr>	
	

				<%int cont=1; %>					
				<logic:iterate name="form" property="resultadosVO" id="resultados">
					<imagenes:imagenes 
					usuarioAdministrador="${form.usuarioAdmin}" 
					usuarioPublicador="${form.usuarioPublicador}" 
					identificadorODE="${resultados.id}" 
					titulo="${resultados.titulo}" 
					esVisualizable ="${resultados.esVisualizable}" 
					numeroODE="${resultados.numeroODE}" 
					formato="${resultados.formato}" 
					nivelAgregacionTexto="${resultados.nivelAgregacionTexto}" 
					urlImagen="${resultados.urlImagen}" contador="<%=cont%>" 
					valoracion="${resultados.valoracion}" 
					tipoRecurso="${resultados.tipoRecurso}" 
					areaCurricular="${resultados.areaCurricular}" 
					nodo="${resultados.nodo}" 
					urlCambiarImagen="/buscador/BuscarAvanzadoCU/MostrarResultadosImagenesCambiarImagenODE.do?idioma=${form.idioma}&amp;buscContenido=${form.buscContenEnlace}&amp;pagina=${form.pagina}&amp;formato=${form.formato}&amp;idODE=${form.idODE}&amp;recurso=${form.recurso}&amp;procesoCognitivo=${form.procesoCognitivo}&amp;contexto=${form.contexto}&amp;edad=${form.edad}&amp;autor=${form.autor}&amp;diaPublic=${form.diaPublic}&amp;mesPublic=${form.mesPublic}&amp;anyoPublic=${form.anyoPublic}&amp;c_s_secuencia=${form.c_s_secuencia}&amp;valoracion=${form.valoracion}&amp;enlaceTaxSelec=${form.enlaceTaxSelec}&amp;identificadorODE=${resultados.id}&amp;tipoVisualiz=${form.tipoVisualiz}&amp;nodoDestino=${resultados.nodo}&amp;enlaceComuSelec=${form.enlaceComuSelec}&amp;tipoBusqueda=${form.tipoBusqueda}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;idTesauro=${form.idTesauro}&amp;nomTesauros=${form.nomTesauros}&amp;idTesauroSug=${form.idTesauroSug}&amp;nivelAgreg=${form.nivelAgreg}&amp;destinatarios=${form.destinatarios}&amp;keyword=${form.keyword}&amp;numeroResultados=${form.numeroResultados}"
					urlPrevisualizar="/buscador/BuscarAvanzadoCU/MostrarResultadosImagenesPrevisualizarODE.do?idioma=${form.idioma}&amp;identificadorODE=${resultados.id}&amp;nodoDestino=${resultados.nodo}&amp;seleccionarSecuencia=${resultados.conSecuencia}"
					url="/buscador/BuscarAvanzadoCU/MostrarResultadosImagenesPrepararRetornoDetalleImagenes.do?idioma=${form.idioma}&amp;buscContenido=${form.buscContenEnlace}&amp;pagina=${form.pagina}&amp;formato=${form.formato}&amp;idODE=${form.idODE}&amp;recurso=${form.recurso}&amp;procesoCognitivo=${form.procesoCognitivo}&amp;contexto=${form.contexto}&amp;edad=${form.edad}&amp;autor=${form.autor}&amp;diaPublic=${form.diaPublic}&amp;mesPublic=${form.mesPublic}&amp;anyoPublic=${form.anyoPublic}&amp;c_s_secuencia=${form.c_s_secuencia}&amp;valoracion=${form.valoracion}&amp;enlaceTaxSelec=${form.enlaceTaxSelec}&amp;identificadorODE=${resultados.id}&amp;tipoVisualiz=${form.tipoVisualiz}&amp;nodoDestino=${resultados.nodo}&amp;enlaceComuSelec=${form.enlaceComuSelec}&amp;tipoBusqueda=${form.tipoBusqueda}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;idTesauro=${form.idTesauro}&amp;nomTesauros=${form.nomTesauros}&amp;idTesauroSug=${form.idTesauroSug}&amp;nivelAgreg=${form.nivelAgreg}&amp;destinatarios=${form.destinatarios}&amp;keyword=${form.keyword}&amp;numeroResultados=${form.numeroResultados}"/>						
					<%cont = cont +1; %>
				</logic:iterate>						
		<!--  -->
		<!--  -->
		</table>
	</div>
	<!-- Fin Resultados de Búsqueda -->

	<!-- Fin Resultados de Búsqueda -->
	<!-- Paginacion -->
	<c:if test="${form.tipoBusqueda!='05' && form.tipoBusqueda!='06'&& form.tipoBusqueda!='07' && form.tipoBusqueda!='08'}">
				<hr />
							<div class="paginacion">
								<ul id="navlist">								
									<logic:notEmpty name="form" property="anterior">									
										<li><a href="<html:rewrite action="/BuscarAvanzadoCU/MostrarResultadosImagenesPaginarImagenes.do"/>?buscContenido=${form.buscContenEnlace}&amp;idiomBusc=${form.idioma}&amp;pagina=${form.anterior}&amp;formato=${form.formato}&amp;idODE=${form.idODE}&amp;recurso=${form.recurso}&amp;procesoCognitivo=${form.procesoCognitivo}&amp;contexto=${form.contexto}&amp;edad=${form.edad}&amp;autor=${form.autor}&amp;diaPublic=${form.diaPublic}&amp;mesPublic=${form.mesPublic}&amp;anyoPublic=${form.anyoPublic}&amp;c_s_secuencia=${form.c_s_secuencia}&amp;valoracion=${form.valoracion}&amp;enlaceTaxSelec=${form.enlaceTaxSelec}&amp;enlaceComuSelec=${form.enlaceComuSelec}&amp;tipoBusqueda=${form.tipoBusqueda}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;nivelAgreg=${form.nivelAgreg}&amp;destinatarios=${form.destinatarios}&amp;keyword=${form.keyword}&amp;idTesauro=${form.idTesauro}&amp;nomTesauros=${form.nomTesauros}&amp;idTesauroSug=${form.idTesauroSug}&amp;numeroResultados=${form.numeroResultados}&amp;tipoVisualiz=CON_IMAGENES&amp;licencia=${form.licencia}"><bean:message key="listarODE.arbolCurricular.anterior"/></a></li>
									</logic:notEmpty>
					
									<logic:iterate name="form" property="paginas" id="pagina">									
										<logic:equal name="form" property="pagina" value="${pagina}">
											<li>${pagina}</li>
										</logic:equal>
										<logic:notEqual name="form" property="pagina" value="${pagina}">
											<li><a href="<html:rewrite action="/BuscarAvanzadoCU/MostrarResultadosImagenesPaginarImagenes.do"/>?buscContenido=${form.buscContenEnlace}&amp;idiomBusc=${form.idioma}&amp;pagina=${pagina}&amp;formato=${form.formato}&amp;idODE=${form.idODE}&amp;recurso=${form.recurso}&amp;procesoCognitivo=${form.procesoCognitivo}&amp;contexto=${form.contexto}&amp;edad=${form.edad}&amp;autor=${form.autor}&amp;diaPublic=${form.diaPublic}&amp;mesPublic=${form.mesPublic}&amp;anyoPublic=${form.anyoPublic}&amp;c_s_secuencia=${form.c_s_secuencia}&amp;valoracion=${form.valoracion}&amp;enlaceTaxSelec=${form.enlaceTaxSelec}&amp;enlaceComuSelec=${form.enlaceComuSelec}&amp;tipoBusqueda=${form.tipoBusqueda}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;nivelAgreg=${form.nivelAgreg}&amp;destinatarios=${form.destinatarios}&amp;keyword=${form.keyword}&amp;idTesauro=${form.idTesauro}&amp;nomTesauros=${form.nomTesauros}&amp;idTesauroSug=${form.idTesauroSug}&amp;numeroResultados=${form.numeroResultados}&amp;tipoVisualiz=CON_IMAGENES&amp;licencia=${form.licencia}">${pagina}</a></li>
										</logic:notEqual>
									</logic:iterate>
						
									<logic:notEmpty name="form" property="siguiente">
										<li><a href="<html:rewrite action="/BuscarAvanzadoCU/MostrarResultadosImagenesPaginarImagenes.do"/>?buscContenido=${form.buscContenEnlace}&amp;idiomBusc=${form.idioma}&amp;pagina=${form.siguiente}&amp;formato=${form.formato}&amp;idODE=${form.idODE}&amp;recurso=${form.recurso}&amp;procesoCognitivo=${form.procesoCognitivo}&amp;contexto=${form.contexto}&amp;edad=${form.edad}&amp;autor=${form.autor}&amp;diaPublic=${form.diaPublic}&amp;mesPublic=${form.mesPublic}&amp;anyoPublic=${form.anyoPublic}&amp;c_s_secuencia=${form.c_s_secuencia}&amp;valoracion=${form.valoracion}&amp;enlaceTaxSelec=${form.enlaceTaxSelec}&amp;enlaceComuSelec=${form.enlaceComuSelec}&amp;tipoBusqueda=${form.tipoBusqueda}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;nivelAgreg=${form.nivelAgreg}&amp;destinatarios=${form.destinatarios}&amp;keyword=${form.keyword}&amp;idTesauro=${form.idTesauro}&amp;nomTesauros=${form.nomTesauros}&amp;idTesauroSug=${form.idTesauroSug}&amp;numeroResultados=${form.numeroResultados}&amp;tipoVisualiz=CON_IMAGENES&amp;licencia=${form.licencia}"><bean:message key="listarODE.arbolCurricular.siguiente"/></a></li>
									</logic:notEmpty>
									</ul>
						</div>
				</div>	
				


		</c:if>
		<c:if test="${form.tipoBusqueda=='05' || form.tipoBusqueda=='06'}">
				<hr />
							<div class="paginacion">
								<ul id="navlist">								
									<logic:notEmpty name="form" property="anterior">									
										<li><a href="<html:rewrite action="/NavegarArbolCurricularCU/ArbolCurricularBuscarAvanzado.do"/>?areaCurricularBusqueda=${ultimo.id}&tipoBusqueda=${form.tipoBusqueda}&tipoNavegacion=${form.tipoNavegacion}&busquedaArbol=true&amp;pagina=${pagina}"><bean:message key="listarODE.arbolCurricular.anterior"/></a></li>
									</logic:notEmpty>
					
									<logic:iterate name="form" property="paginas" id="pagina">									
										<logic:equal name="form" property="pagina" value="${pagina}">
											<li>${pagina}</li>
										</logic:equal>
										<logic:notEqual name="form" property="pagina" value="${pagina}">
											<li><a href="<html:rewrite action="/NavegarArbolCurricularCU/ArbolCurricularBuscarAvanzado.do"/>?areaCurricularBusqueda=${ultimo.id}&tipoBusqueda=${form.tipoBusqueda}&tipoNavegacion=${form.tipoNavegacion}&busquedaArbol=true&amp;pagina=${pagina}">${pagina}</a></li>
										</logic:notEqual>
									</logic:iterate>
						
									<logic:notEmpty name="form" property="siguiente">
										<li><a href="<html:rewrite action="/NavegarArbolCurricularCU/ArbolCurricularBuscarAvanzado.do"/>?areaCurricularBusqueda=${ultimo.id}&tipoBusqueda=${form.tipoBusqueda}&tipoNavegacion=${form.tipoNavegacion}&busquedaArbol=true&amp;pagina=${pagina}"><bean:message key="listarODE.arbolCurricular.siguiente"/></a></li>
									</logic:notEmpty>
								</ul>
							</div>
</div>
					<hr />

</div>
		</c:if>
		<c:if test="${form.tipoBusqueda=='07' || form.tipoBusqueda=='08'}">
	
		<hr />
			<div class="paginacion">
				<ul id="navlist">

						
						<logic:notEmpty name="form" property="anterior">
							<li><a href="<html:rewrite action="/NavegarTesauroCU/NavegarTesauroBuscarAvanzado.do"/>?idiomaBuscador=${form.idiomaBuscador}&amp;pagina=${form.anterior}&amp;tesauroBusqueda=${form.tesauroBusqueda}&tipoBusqueda=${form.tipoBusqueda}&tituloTesauro=${form.tituloTesauro}&tipoNavegacion=${form.tipoNavegacion}&busquedaTesauro=true&amp;pagina=${pagina}">
								<bean:message key="listarODE.arbolCurricular.anterior"/>
							</a></li>
						</logic:notEmpty>
						
						<logic:iterate name="form" property="paginas" id="pagina">
							<logic:equal name="form" property="pagina" value="${pagina}">
								<li>${pagina}</li>
							</logic:equal>
							<logic:notEqual name="form" property="pagina" value="${pagina}">
								<li><a href="<html:rewrite action="/NavegarTesauroCU/NavegarTesauroBuscarAvanzado.do"/>?idiomaBuscador=${form.idiomaBuscador}&amp;pagina=${pagina}&amp;tesauroBusqueda=${form.tesauroBusqueda}&tipoBusqueda=${form.tipoBusqueda}&tituloTesauro=${form.tituloTesauro}&tipoNavegacion=${form.tipoNavegacion}&busquedaTesauro=true&amp;pagina=${pagina}">${pagina}</a></li>
							</logic:notEqual>
						</logic:iterate>
			
						<logic:notEmpty name="form" property="siguiente">
							<li><a href="<html:rewrite action="/NavegarTesauroCU/NavegarTesauroBuscarAvanzado.do"/>?idiomaBuscador=${form.idiomaBuscador}&amp;pagina=${form.siguiente}&amp;tesauroBusqueda=${form.tesauroBusqueda}&tipoBusqueda=${form.tipoBusqueda}&tituloTesauro=${form.tituloTesauro}&tipoNavegacion=${form.tipoNavegacion}&busquedaTesauro=true&amp;pagina=${pagina}">
							<bean:message key="listarODE.arbolCurricular.siguiente"/>
							</a></li>
						</logic:notEmpty>
					</ul>
							</div>
</div>
					<hr />

</div>

		</c:if>


<!-- Fin CONTENIDO PESTANIAS -->
<!-- Fin CONTENIDO PESTANIAS -->


<!-- Inicio Boton de Eliminar  -->
<!-- Inicio Boton de Eliminar  -->


		
	
	<c:if test="${form.usuarioAdmin|| form.usuarioPublicador}">
		<fieldset class="tipo ft_centrada">
				<input type="hidden" name="idioma" value="${form.idioma}"/>
				<input type="hidden" name="buscContenido" value="${form.buscContenido}"/>
				<input type="hidden" name="formato" value="${form.formato}"/>
				<input type="hidden" name="recurso" value="${form.recurso}"/>
				<input type="hidden" name="procesoCognitivo" value="${form.procesoCognitivo}"/>
				<input type="hidden" name="contexto" value="${form.contexto}"/>
				<input type="hidden" name="edad" value="${form.edad}"/>
				<input type="hidden" name="autor" value="${form.autor}"/>
				<input type="hidden" name="diaPublic" value="${form.diaPublic}"/>
				<input type="hidden" name="mesPublic" value="${form.mesPublic}"/>
				<input type="hidden" name="anyoPublic" value="${form.anyoPublic}"/>
				<input type="hidden" name="c_s_secuencia" value="${form.c_s_secuencia}"/>
				<input type="hidden" name="valoracion" value="${form.valoracion}"/>
				<input type="hidden" name="nivelAgreg" value="${form.nivelAgreg}"/>
				<!--<input type="hidden" name="areaCurricular" value="${form.areaCurricular}"/>-->
				<input type="hidden" name="enlaceTaxSelec" value="${form.enlaceTaxSelec}"/>				
				<input type="hidden" name="destinatarios" value="${form.destinatarios}"/>
				<input type="hidden" name="keyword" value="${form.keyword}"/>
				<input type="hidden" name="idTesauro" value="${form.idTesauro}"/>
				<input type="hidden" name="nomTesauros" value="${form.nomTesauros}"/>
				<input type="hidden" name="usuarioAdmin" value="${form.usuarioAdmin}"/>
				<input type="hidden" name="usuarioPublicador" value="${form.usuarioPublicador}"/>
				<input type="hidden" name="enlaceComuSelec" value="${form.enlaceComuSelec}"/>	
				<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>	
				<input type="hidden" name="tipoBusqueda" value="${form.tipoBusqueda}"/>
				<input type="hidden" name="pagina" value="${form.pagina}"/>	
				<input type="hidden" name="numeroResultados" value="${form.numeroResultados}"/>
				<input type="hidden" name="idODE" value="${form.idODE}"/>
			<input class="boton_125"  type="submit"  value="<bean:message key='listar.odecu.mostrar.resultados.consulta.cabecera.eliminar'/>" />
		</fieldset> 
	</c:if>
</form>

<br />
<!-- Fin Boton de Eliminar  -->
<!-- Fin Boton de Eliminar  -->


<br />
<c:if test="${form.tipoBusqueda!='05' && form.tipoBusqueda!='06'&& form.tipoBusqueda!='07' && form.tipoBusqueda!='08'}">

<!-- Inicio caja buscador -->
<!-- Inicio caja buscador -->
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario_02" >
  				<div class="fila_de_tabla">
  					<form method="post" action="<html:rewrite action="/BuscarAvanzadoCU/MostrarResultadosImagenesPaginarImagenes.do"/>" name="formularioBusqueda">
								<div class="contenedor_izquierda_00">
	  						<div class="text">
	  							<label class="oculto" for="buscContenSolo"><bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.buscador"/>:</label>
	  						</div>
						</div>
						<div class="contenedor_derecha_00" >
							<div class="text"><input name="buscContenSolo" value="${form.buscContenido}"  onblur="this.style.backgroundColor='#e1e1e1'" id="buscadorContenidoSolo" type="text" title="<bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.descripcionTextoBusqueda"/>"  /><br class="oculto" />
								<input type="hidden" name="idioma" value="${form.idioma}"/>
														
								<input type="hidden" name="tipoVisualiz" value="${form.tipoVisualiz}"/>
								<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
								<input type="hidden" name="busqSimpleAvanz" value="${form.busqSimpleAvanz}"/>
								<input type="hidden" name="tipoBusqueda" value="${form.tipoBusqueda}"/>
								<logic:equal name="form" property="tipoBusqueda" value="03">
													<input type="hidden" name="keyword" value="${form.keyword}"/>
													<input type="hidden" name="formato" value="${form.formato}"/>
													<input type="hidden" name="recurso" value="${form.recurso}"/>
													<input type="hidden" name="procesoCognitivo" value="${form.procesoCognitivo}"/>
													<input type="hidden" name="contexto" value="${form.contexto}"/>
													<input type="hidden" name="edad" value="${form.edad}"/>
													<input type="hidden" name="autor" value="${form.autor}"/>
													<input type="hidden" name="diaPublic" value="${form.diaPublic}"/>
													<input type="hidden" name="mesPublic" value="${form.mesPublic}"/>
													<input type="hidden" name="anyoPublic" value="${form.anyoPublic}"/>
													<input type="hidden" name="c_s_secuencia" value="${form.c_s_secuencia}"/>
													<input type="hidden" name="valoracion" value="${form.valoracion}"/>
													<input type="hidden" name="nivelAgreg" value="${form.nivelAgreg}"/>
													<!--<input type="hidden" name="areaCurricular" value="${form.areaCurricular}"/>-->
													<input type="hidden" name="enlaceTaxSelec" value="${form.enlaceTaxSelec}"/>
													<input type="hidden" name="destinatarios" value="${form.destinatarios}"/>
													<input type="hidden" name="keyword" value="${form.keyword}"/>
													<input type="hidden" name="idTesauro" value="${form.idTesauro}"/>
													<input type="hidden" name="usuarioAdmin" value="${form.usuarioAdmin}"/>
													<input type="hidden" name="nomTesauros" value="${form.nomTesauros}"/>
													<input type="hidden" name="enlaceComuSelec" value="${form.enlaceComuSelec}"/>	
													<input type="hidden" name="idODE" value="${form.idODE}"/>	
								
								</logic:equal>
								<input type="submit" alt="<bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.botonBuscar"/>"  class="buscar"  value="<bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.buscar"/>" name="buscar2" /><br class="oculto" />
								<a href="<html:rewrite action="/BuscarAvanzadoCU/MostrarResultadosImagenesMostrarAvanzadoResultados.do"/>?buscContenido=${form.buscContenEnlace}&amp;idiomBusc=${form.idioma}&amp;pagina=${form.anterior}&amp;formato=${form.formato}&amp;idODE=${form.idODE}&amp;recurso=${form.recurso}&amp;procesoCognitivo=${form.procesoCognitivo}&amp;contexto=${form.contexto}&amp;edad=${form.edad}&amp;autor=${form.autor}&amp;diaPublic=${form.diaPublic}&amp;mesPublic=${form.mesPublic}&amp;anyoPublic=${form.anyoPublic}&amp;c_s_secuencia=${form.c_s_secuencia}&amp;valoracion=${form.valoracion}&amp;enlaceTaxSelec=${form.enlaceTaxSelec}&amp;enlaceComuSelec=${form.enlaceComuSelec}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;idTesauro=${form.idTesauro}&amp;nomTesauros=${form.nomTesauros}&amp;nivelAgreg=${form.nivelAgreg}&amp;destinatarios=${form.destinatarios}&amp;keyword=${form.keyword}&amp;tipoBusqueda=${form.tipoBusqueda}" class="avanzado"><bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.avanzado"/></a>
										
							</div>
					</div>
					<div class="linea_separadora"></div>
					<br />
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
<!-- Fin caja buscador -->
<!-- Fin caja buscador -->


</c:if>



<!-- Fin plantilla contenido  -->
<!-- Fin plantilla contenido  -->


    </tiles:put>

</tiles:insert>
<SCRIPT LANGUAGE="JavaScript">
var tipoBusqueda="${form.tipoBusqueda}";
var formato="${form.formato}";
var nivelAgreg="${form.nivelAgreg}";
var numeroResultados="${form.numeroResultados}";
var ocultar="<bean:message key='configurar.avanzado.ocultar2'/>";
var ver="<bean:message key='configurar.avanzado.ver2'/>";
if(tipoBusqueda=="04"){
	deshabilitarCombos();
}
if(formato!='')
{
expandirCaja ('m4');
}
if(nivelAgreg!='')
{

expandirCaja ('m3');
}


function deshabilitarCombos(){
	var oComboIdioma = document.getElementById('idiomaBuscadorContenido');
	var oComboNumeroResultados = document.getElementById('numeroResultados');
	oComboIdioma.disabled=true;
	oComboNumeroResultados.disabled=true;
}

function habilitarCombos(){
	var oComboIdioma = document.getElementById('idiomaBuscadorContenido');
	var oComboNumeroResultados = document.getElementById('numeroResultados');
	oComboIdioma.disabled=false;
	oComboNumeroResultados.disabled=false;
}

function enviarFormulario(){
	if(numeroResultados!=document.getElementById('numeroResultados').value){
		document.formularioBusqueda.submit();
	}
}
function expandirCaja (i) 
	{
		if (document.getElementById(i).className=='caja_abierta') 
		{
			document.getElementById('p' + i).className = 'caja_cerrada';
			document.getElementById('d'+i).innerHTML=ver;
			document.getElementById(i).className = 'caja_cerrada';
		}
		else 
		{
			document.getElementById('p' + i).className = 'caja_abierta';
			document.getElementById('d'+i).innerHTML=ocultar;
			document.getElementById(i).className = 'caja_abierta';
		}
	}
</SCRIPT>
