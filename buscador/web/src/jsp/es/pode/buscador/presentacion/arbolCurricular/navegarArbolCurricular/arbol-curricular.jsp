<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tags/serverProperties.tld" prefix="server" %>
<%@ taglib prefix="agrega" uri="/WEB-INF/tags/agregaProperties.tld" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>


<tiles:insert definition="layout-menu-3-enriquecido">

    <tiles:put name="title" type="string">
        <bean:message key="title.Comun"/>
    </tiles:put>

	<tiles:put name="body-principal" type="string">
		<article id="buscador_tipo">
			<header>
			<!-- ojo, quitar uno de los dos -->
				<h2 class="titulo"><bean:message key="encuentra.los.mejores.contenidos.educativos"/></h2>
				<!--<h2><bean:message key="navegar.arbolCurricular.nombre"/></h2> --> <!-- esto escribe Árbol curricular -->
				<!--<h2>${form.taxNombre}</h2>--> <!-- esto escribe Árbol curricular LOE 2006 -->
			</header>
			<section id="buscador_avanzado">
				<!-- 
				<form id="f" method="post" action="<html:rewrite action="/NavegarArbolCurricularCU/ArbolCurricularBuscarAvanzado.do?areaCurricularBusqueda=${form.areaCurricularBotones}&tipoBusqueda=${form.tipoBusqueda}&tipoNavegacion=${form.tipoNavegacion}&busquedaArbol=true&idiomBusc=${form.idiomBusc}&ambitoBusquedaCombos=${form.ambitoBusquedaCombos}&lenguajeBusquedaCombos=${form.lenguajeBusquedaCombos}"/>" >
				-->				
				<form id="f" method="post" action="" >
				
					<!--  -->
					<input type="hidden" name="cajaArribaAbierta" class="cajaArribaAbierta" id="cajaArribaAbierta"/>
					<c:choose>
					<c:when test="${!empty form.cajaArribaAbierta and form.cajaArribaAbierta==true}">
						<h3 class="especial_n clearfix"><a href="#" title="<bean:message key='arbol.busquedarapida'/>" onclick="toggleCajaArriba()" class="set_table menos_img" id="set_table"><em><bean:message key="arbol.busquedarapida"/></em></a></h3>
						<div id="nuevo_buscador" class="campos clearfix tabla_oculta" style="display:block">
					</c:when>
					<c:otherwise>
						<h3 class="especial_n clearfix"><a href="#" title="<bean:message key='arbol.busquedarapida'/>" onclick="toggleCajaArriba()" class="set_table" id="set_table"><em><bean:message key="arbol.busquedarapida"/></em></a></h3>
						<div id="nuevo_buscador" class="campos clearfix tabla_oculta" style="display:none">
					</c:otherwise>
					</c:choose>
					
						<fieldset class="clearfix" id="idiomas">
							<span class="caja_de_boton">
							<label class="oculto" for="idioma">Idioma :</label>	
							<c:choose>
			                    <c:when test="${!empty form.idiomBuscBackingList}">
			                       <html:select onchange="" name="form" property="lenguajeBusquedaCombos" styleId="IdiomaCombo" title="${idiomaAlt}" styleClass="select">
			                           <html:optionsCollection name="form" property="idiomBuscBackingList" label="label" value="value"/>
			                       </html:select>
			                    </c:when>
			                    <c:otherwise>
			                        <html:select name="form" property="lenguajeBusquedaCombos" styleId="IdiomaBuscador" title="${idiomaAlt}"/>
			                    </c:otherwise>
					        </c:choose>
							</span>
						</fieldset>
						<!--  -->
						
						
						<fieldset class="clearfix ficha">

						<!-- en funcion de la longitud del breadcrumb se carga un combo u otro -->
						<!--<c:set var="longitud" value="${ fn:length(form.rutaArbol)}"/>-->
						<!--<c:out value="${ruta3.valorTax}"/>-->
						
							<span class="caja_de_boton">
								<label for="etapa"><bean:message key="arbol.busquedarapida.etapa"/>:</label>
<!-- 
				//opcionesRutaArbolNivel -> donde el JSP recibe el collection con el contenido de los combos
				//areaCurricularBotones -> donde el JSP envia la rutaArbol	
-->	
								<c:choose>
									<c:when test="${!empty form.opcionesRutaArbolNivel1}">
									   <html:select onchange="dameCiclo()" onclick="return false;" name="form" property="rutaArbol1Niveles" styleId="etapa" styleClass="select" titleKey="arbol.busquedarapida.select.etapa">
									     <option value=""></option>											 
										 <html:optionsCollection name="form" property="opcionesRutaArbolNivel1" label="nombre" value="identificador"/>
									   </html:select>
									</c:when>
									<c:otherwise>		
										<select name="etapa" id="etapa" class="select" title="<bean:message key='arbol.busquedarapida.select.etapa'/>">
											<option value=""></option>
										</select>
									</c:otherwise>
								</c:choose>
							</span>
							</fieldset>
										<!--  -->
							<fieldset class="clearfix ficha">
							<span class="caja_de_boton">
								<label for="etapa"><bean:message key="arbol.busquedarapida.ciclocurso"/>:</label>
								<c:choose>
									<c:when test="${!empty form.opcionesRutaArbolNivel2}">		
									   <html:select onchange="dameCurso()" onclick="return false;" name="form" property="rutaArbol2Niveles" styleId="curso" titleKey="arbol.busquedarapida.select.ciclocurso" styleClass="select">
									     <option value=""></option>											 
										 <html:optionsCollection name="form" property="opcionesRutaArbolNivel2" label="nombre" value="identificador"/>
									   </html:select>
									</c:when>
									<c:otherwise>
										<select id="curso" name="curso" class="select" title="<bean:message key='arbol.busquedarapida.select.ciclocurso'/>">
											<option value=""></option>
										</select>
									</c:otherwise>
								</c:choose>
							</span>
							</fieldset>
							 	<!--  -->
							<fieldset class="clearfix ficha">
							<span class="caja_de_boton">
								<label for="asignatura"><bean:message key="arbol.busquedarapida.areaasignatura"/>:</label>
								<c:choose>
									<c:when test="${!empty form.opcionesRutaArbolNivel3}">		
									   <html:select onchange="dameAsignatura()" onclick="return false;" name="form" property="rutaArbol3Niveles" styleId="asignatura" titleKey="arbol.busquedarapida.select.areaasignatura" styleClass="select">
									     <option value=""></option>											 
										 <html:optionsCollection name="form" property="opcionesRutaArbolNivel3" label="nombre" value="identificador"/>
									   </html:select>
									</c:when>
									<c:otherwise>
										<select id="curso" name="curso" class="select" title="<bean:message key='arbol.busquedarapida.select.areaasignatura'/>">
											<option value=""></option>
										</select>
									</c:otherwise>
								</c:choose>
							</span>
							</fieldset>
								<!--  -->
							<fieldset class="clearfix ficha">
							<span class="caja_de_boton">
								<label for="bloque_t"><bean:message key="arbol.busquedarapida.bloquetematico"/>:</label>
								<c:choose>
									<c:when test="${!empty form.opcionesRutaArbolNivel4}">		
									   <html:select onchange="dameBloque_t()" onclick="return false;" name="form" property="rutaArbol4Niveles" styleId="bloque_t" titleKey="arbol.busquedarapida.select.bloquetematico" styleClass="select">
									     <option value=""></option>
										 <html:optionsCollection name="form" property="opcionesRutaArbolNivel4" label="nombre" value="identificador"/>
									   </html:select>
									</c:when>
									<c:otherwise>
										<select id="curso" name="curso" class="select" title="<bean:message key='arbol.busquedarapida.select.bloquetematico'/>">
											<option value=""></option>
										</select>
									</c:otherwise>
								</c:choose>
							</span>
					</fieldset>	
					<div class="botonera_especial">
						<c:choose>
						<c:when test="${(empty form.ambitoBusquedaCombos) or (form.ambitoBusquedaCombos!='05' and form.ambitoBusquedaCombos!='06')}">
							<label for="todo_agrega">
								<input type="radio" onclick="" id="ambitoBusquedaCombos" class="botonradio" value="05" name="ambitoBusquedaCombos" checked="checked" />
							<bean:message	key="buscador.radio.buscar.en.red" /></label>			
							<label for="nodo_cca_aa">
								<input type="radio" onclick="" id="ambitoBusquedaCombos" class="botonradio" value="06" name="ambitoBusquedaCombos" />
							<server:serverProperties property="ccaa"/></label>
							<input type="hidden" name="tipoBusqueda" value="05" />	
						</c:when>
						<c:otherwise>	
							<input type="hidden" name="tipoBusqueda" />					
							<label for="todo_agrega">
								<c:choose>
								<c:when test="${(!empty form.ambitoBusquedaCombos) and (form.ambitoBusquedaCombos=='05')}">
									<input type="radio" onclick="" id="ambitoBusquedaCombos" class="botonradio" value="05" name="ambitoBusquedaCombos" checked="checked" />	
								</c:when>
								<c:otherwise>
									<input type="radio" onclick="" id="ambitoBusquedaCombos" class="botonradio" value="05" name="ambitoBusquedaCombos" />			
								</c:otherwise>
								</c:choose>
							<bean:message key="buscador.radio.buscar.en.red" /></label>
						
							<label for="nodo_cca_aa">
								<c:choose>
								<c:when test="${(!empty form.ambitoBusquedaCombos) and (form.ambitoBusquedaCombos=='06')}">
									<input type="radio" onclick="" id="ambitoBusquedaCombos" class="botonradio" value="06" name="ambitoBusquedaCombos" checked="checked" />			
								</c:when>
								<c:otherwise>
									<input type="radio" onclick="" id="ambitoBusquedaCombos" class="botonradio" value="06" name="ambitoBusquedaCombos" />			
								</c:otherwise>
								</c:choose>
							<server:serverProperties property="ccaa"/></label>
						</c:otherwise>
						</c:choose>
					<br />
					<input type="submit" value="<bean:message key="arbol.busquedarapida.button.buscar"/>" class="boton" onclick="doSubmit_form1()"/>
					</div>	
						</div>
					<!--  -->
							
				</form>
				
				<form id="f2" method="post" action="<html:rewrite action="/NavegarArbolCurricularCU/ArbolCurricularSeleccionarHijo.do?areaCurricularBusqueda=${ruta.id}&tipoBusqueda=${form.tipoBusqueda}&tipoNavegacion=${form.tipoNavegacion}&idiomBusc=${form.idiomBusc}"/>" >
					<!--  -->
					<input type="hidden" name="cajaAbajoAbierta" class="cajaAbajoAbierta" id="cajaAbajoAbierta"/>
					<c:choose>
					<c:when test="${!empty form.cajaAbajoAbierta and form.cajaAbajoAbierta==true}">
						<h3 class="especial_n clearfix" id="de_nueva_ruta"><a href="#" title="<bean:message key='arbol.rutacurricular'/>" onclick="toggleCajaAbajo()" class="set_table2 menos_img" id="set_table2"><em><bean:message key="arbol.rutacurricular"/></em></a></h3>
						<div id="nueva_ruta" class="campos clearfix tabla_oculta2" style="display:block">
					</c:when>
					<c:otherwise>
						<h3 class="especial_n clearfix" id="de_nueva_ruta"><a href="#" title="<bean:message key='arbol.rutacurricular'/>" onclick="toggleCajaAbajo()" class="set_table2" id="set_table2"><em><bean:message key="arbol.rutacurricular"/></em></a></h3>
						<div id="nueva_ruta" class="campos clearfix tabla_oculta2" style="display:none">
					</c:otherwise>
					</c:choose>
					
					<!--  -->
					<fieldset class="clearfix" id="idiomas2">
							<label class="oculto" for="idioma2">Idioma :</label>
							<span class="caja_de_boton">
								<c:choose>
				                    <c:when test="${!empty form.idiomBuscBackingList}">
				                       <html:select  onchange="selectHijo()" name="form" property="idiomBusc" styleId="IdiomaCombo" title="${idiomaAlt}" styleClass="select">
				                           <html:optionsCollection name="form" property="idiomBuscBackingList" label="label" value="value"/>
				                       </html:select>
				                    </c:when>
				                    <c:otherwise>
				                        <html:select name="form" property="idiomBusc" styleId="IdiomaBuscador" title="${idiomaAlt}"/>
				                    </c:otherwise>
					           </c:choose>
							</span>
					</fieldset>
					
					<!--  -->
					<div id="ruta_curricular">
							<!--  TABLA  -->
							<div class="tabla">
								<div>
									<!-- breadcrumb -->
									<table class="tabla_ruta_curricular">
										<c:set var="longitud" value="${ fn:length(form.rutaArbol)}"/>
										<fmt:formatNumber var="nombre" value="${longitud -1}" type="number"/>
										<fmt:formatNumber var="nombre2" value="${longitud -2}" type="number"/>
										<c:set var="ultimo" value="${ form.rutaArbol[nombre]}"/>
										<tr>
											<th class="name">
												<!-- yo creo que esta url esta mal puesta. Nodo CC.AA no deberia tener link -->
												<a href="/buscador2/NavegarArbolCurricularCU/ArbolCurricularSeleccionarHijo.do?tipoBusqueda=${form.tipoBusqueda}&tipoNavegacion=${form.tipoNavegacion}&idiomBusc=${form.idiomBusc}">
													<c:choose>
														<c:when test="${(!empty form.tipoBusqueda) and (form.tipoBusqueda=='06')}">
															<agrega:agregaProperties property="ccaa"/>
														</c:when>
														<c:otherwise>
															<bean:message key="buscador.radio.buscar.en.red" />
														</c:otherwise>
													</c:choose>
												</a>
												<c:if test="${!empty form.rutaArbol}">
													<c:if test="${longitud>1 }">
													   <c:forEach items="${form.rutaArbol}" var="ruta" begin="0" end="${nombre2}">
															<em class="oculto">-</em>
															<!--<a href="/buscador2/NavegarArbolCurricularCU/ArbolCurricularSeleccionarPadre.do?areaCurricularBusqueda=${ruta.id}&tipoBusqueda=${form.tipoBusqueda}&tipoNavegacion=${form.tipoNavegacion}&idiomBusc=${form.idiomBusc}">-->
															<!-- 
															<a href="/buscador2/NavegarArbolCurricularCU/ArbolCurricularSeleccionarHijo.do?areaCurricularBusqueda=${ruta.id}&tipoBusqueda=${form.tipoBusqueda}&tipoNavegacion=${form.tipoNavegacion}&idiomBusc=${form.idiomBusc}&cajaArribaAbierta=${form.cajaArribaAbierta}&cajaAbajoAbierta=${form.cajaAbajoAbierta}">
															-->
															<a href="" onclick="setHref_breadcrumb('${ruta.id}','${form.tipoBusqueda}','${form.tipoNavegacion}','${form.idiomBusc}','${taxonesvoact.numeroOdesAsociados}')" id="dinamycLink_bis_${ruta.id}"/>
																<c:out value="${ruta.valorTax}"/>
															</a>
														</c:forEach>
													</c:if>
													<c:forEach items="${form.rutaArbol}" var="ruta3" begin="${nombre}" end="${nombre}">
														<em class="oculto">-</em>
														<a href="#">
															<c:out value="${ruta3.valorTax}"/>
														</a>
													</c:forEach>
												</c:if>
											</th>
										</tr>
									</table>
									
									<display:table name="${form.nodos}" id="taxonesvoact" 
										class="tabla_ruta_curricular" cellpadding="0" cellspacing="0">
										
										<display:setProperty name="css.tr.even" value="pijama"/>
										<display:setProperty name="basic.show.header" value="false"/>
										<c:choose>
											<c:when test="${taxonesvoact.esHoja}">
												<display:column autolink="true">
													<c:if test="${taxonesvoact.numeroOdesAsociados==0}">
														<p>
															<strong><c:out value="${taxonesvoact.nombre}"/></strong>
														</p>
													</c:if>
													<c:if test="${taxonesvoact.numeroOdesAsociados!=0}">
														<p>
															<span class="fl">
																<a href="" onclick="setHref_viewODES('${taxonesvoact.identificador}','${form.tipoBusqueda}','${form.tipoNavegacion}','${form.idiomBusc}')" id="dinamycLink_${taxonesvoact.identificador}_viewODES"/><bean:message key="arbol.rutacurricular.vercontenidos"/></a>
																<!-- 
																<a href="/buscador2/NavegarArbolCurricularCU/ArbolCurricularBuscarAvanzado.do?areaCurricularBusqueda=${taxonesvoact.identificador}&tipoBusqueda=${form.tipoBusqueda}&tipoNavegacion=${form.tipoNavegacion}&busquedaArbol=true&idiomBusc=${form.idiomBusc}"/><bean:message key="arbol.rutacurricular.vercontenidos"/></a>
																-->
															</span>
															<strong><c:out value="${taxonesvoact.nombre}"/> (<c:out value="${taxonesvoact.numeroOdesAsociados}"/>)</strong>
														</p>
													</c:if>
												</display:column>	
											</c:when>
											<c:otherwise>
												<display:column autolink="true">
													<c:if test="${taxonesvoact.numeroOdesAsociados==0}">
														<p>
															<a href="" onclick="setHref_nextLevelTree('${taxonesvoact.identificador}','${form.tipoBusqueda}','${form.tipoNavegacion}','${form.idiomBusc}','${taxonesvoact.numeroOdesAsociados}')" id="dinamycLink_${taxonesvoact.identificador}"/>${taxonesvoact.nombre}</a>
														</p>
													</c:if>
													<c:if test="${taxonesvoact.numeroOdesAsociados!=0}">
														<p>
															<span class="fl">
																<a href="" onclick="setHref_viewODES('${taxonesvoact.identificador}','${form.tipoBusqueda}','${form.tipoNavegacion}','${form.idiomBusc}')" id="dinamycLink_${taxonesvoact.identificador}_viewODES"/><bean:message key="arbol.rutacurricular.vercontenidos"/></a>
																<!-- 
																<a href="/buscador2/NavegarArbolCurricularCU/ArbolCurricularBuscarAvanzado.do?areaCurricularBusqueda=${taxonesvoact.identificador}&tipoBusqueda=${form.tipoBusqueda}&tipoNavegacion=${form.tipoNavegacion}&busquedaArbol=true&idiomBusc=${form.idiomBusc}"/><bean:message key="arbol.rutacurricular.vercontenidos"/></a>
																-->
															</span>
															<a href="" onclick="setHref_nextLevelTree('${taxonesvoact.identificador}','${form.tipoBusqueda}','${form.tipoNavegacion}','${form.idiomBusc}','${taxonesvoact.numeroOdesAsociados}')" id="dinamycLink_${taxonesvoact.identificador}"/>${taxonesvoact.nombre}</a><strong> (<c:out value="${taxonesvoact.numeroOdesAsociados}"/>) </strong>
														</p>
													</c:if>
												</display:column>
											</c:otherwise>
										</c:choose>	      
									</display:table>
								</div>
							<!--  FIN TABLA  -->
						</div>
						<!--  FIN TABLA  -->
							
						<div class="botonera_especial"> 
						<c:choose>
						<c:when test="${(!empty form.tipoBusqueda) and (form.tipoBusqueda=='05' or form.tipoBusqueda=='06')}">
							<label for="todo_agrega">
								<c:choose>
								<c:when test="${(form.tipoBusqueda=='05')}">
									<input type="radio" onclick="selectHijo()" id="tipoBusqueda" class="botonradio" value="05" name="tipoBusqueda" checked="checked" />
								</c:when>
								<c:otherwise>
									<input type="radio" onclick="selectHijo()" id="tipoBusqueda" class="botonradio" value="05" name="tipoBusqueda" />			
								</c:otherwise>
								</c:choose>
							<bean:message key="buscador.radio.buscar.en.red" /></label>
					
							<label for="nodo_cca_aa">
								<c:choose>
								<c:when test="${(form.tipoBusqueda=='06')}">
									<input type="radio" onclick="selectHijo()" id="tipoBusqueda" class="botonradio" value="06" name="tipoBusqueda" checked="checked" />			
								</c:when>
								<c:otherwise>
									<input type="radio" onclick="selectHijo()" id="tipoBusqueda" class="botonradio" value="06" name="tipoBusqueda" />			
								</c:otherwise>
								</c:choose>
							<server:serverProperties property="ccaa"/></label>
						</c:when>
						<c:otherwise>
							<label for="todo_agrega">	
								<input type="radio" onclick="selectHijo()" id="tipoBusqueda" class="botonradio" value="05" name="tipoBusqueda" checked="checked" />
							<bean:message	key="buscador.radio.buscar.en.red" /></label>				
							<label for="nodo_cca_aa">
								<input type="radio" onclick="selectHijo()" id="tipoBusqueda" class="botonradio" value="06" name="tipoBusqueda" />
							<server:serverProperties property="ccaa"/></label>	
						</c:otherwise>
						</c:choose>
						<br />
							</div>	
						</div>
						</div>
					<!--  -->	
					
				</form>
				<c:choose>	
					<c:when test="${form.mostrarNoResultados==true}">
						<br/>					
						<fieldset class="clearfix">
							<span class="caja_de_boton">
								<div class="tipofieldset clearfix" id="separacion">
									<!--strong><bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.resultadoBusqueda"/></strong-->
									<div class="clearfix" id="separacion">
										<bean:message key="listar.odecu.mostrar.resultados.quiso.sinResultados.arbol.curricular"/>
										<br class="oculto"/>
									</div>
								</div>  				
							</span>
						</fieldset>	
					</c:when>
					<c:otherwise/>
				</c:choose>					
			</section>
		</article>
		
		<!-- Aqui van javascripts propios  -->
		<script language="javascript">
			var host_and_subdomain="<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>";
		
			///NOTA IMPORTANTE PARA FUTURAS ADAPTACIONES DEL CODIGO
			/* Tal y como esta hecho el codigo y las URLs de los forms no es posible usar los dos forms a la vez.
			Esto quiere decir que si el usuario interacciona con uno de los dos forms el otro se reseteara a su estado por defecto.
			Para cambiar esta caracteristica y que aun asi sigan siendo de funcionamiento independiente seria necesario hacer que
			las variables abajo definidas y exclusivas de un solo form, se usen en ambos forms.
			Tambien es posible que se necesiten añadir mas variables aunque esto no es seguro*/
		
			/////////////////////////////////////////////////////
			/// Varibles usadas en las URLs de los dos forms
			/////////////////////////////////////////////////////
			var tipoBusqueda ="${form.tipoBusqueda}";
			var tipoNavegacion ="${form.tipoNavegacion}";
			var areaCurricularBotones = "${form.areaCurricularBotones}";
			var idiomBusc = "${form.idiomBusc}";
			
			//¿?
			var areaCurricularBusqueda = "${ruta.id}";
			
			/////////////////////////////////////////////////////
			/// Variables exclusivas de las URLs formadas en el primer form
			/////////////////////////////////////////////////////
			var ultimoId = "${ultimo.id}";
			
			/////////////////////////////////////////////////////
			/// Variables exclusivas de las URLs formadas en el primer form
			/////////////////////////////////////////////////////
			var ambitoBusquedaCombos = "${form.ambitoBusquedaCombos}";
			var lenguajeBusquedaCombos = "${form.lenguajeBusquedaCombos}";
			
			// return the value of the radio button that is checked
			// return an empty string if none are checked, or
			// there are no radio buttons
			function getCheckedValue(radioObj) {
				if(!radioObj)
					return "";
				var radioLength = radioObj.length;
				if(radioLength == undefined)
					if(radioObj.checked)
						return radioObj.value;
					else
						return "";
				for(var i = 0; i < radioLength; i++) {
					if(radioObj[i].checked) {
						return radioObj[i].value;
					}
				}
				return "";
			}
			
			// set the radio button with the given value as being checked
			// do nothing if there are no radio buttons
			// if the given value does not exist, all the radio buttons
			// are reset to unchecked
			function setCheckedValue(radioObj, newValue) {
				if(!radioObj)
					return;
				var radioLength = radioObj.length;
				if(radioLength == undefined) {
					radioObj.checked = (radioObj.value == newValue.toString());
					return;
				}
				for(var i = 0; i < radioLength; i++) {
					radioObj[i].checked = false;
					if(radioObj[i].value == newValue.toString()) {
						radioObj[i].checked = true;
					}
				}
			}
			
			/////////////////////////////////////////////////////
			/// Funciones que controlan el estado de las cajas:
			/// si se muestran o no
			/////////////////////////////////////////////////////	
			
			function toggle(obj) {
				var el = document.getElementById(obj);
			
				if ( el.style.display != 'none' ) 
					el.style.display = 'none';
				else 	
					el.style.display = 'block';
			}
		
		    function toggleClass(obj, className1,className2) {
		    	var el = document.getElementById(obj);
		    
		    	el.className = (el.className == className1)?className2:className1;
		    }
		
			function toggleCajaArriba() {
				toggle('nuevo_buscador');
				toggleClass('set_table', 'set_table', 'set_table menos_img');
				
				if (document.getElementById('nuevo_buscador').style.display == 'none')
					document.forms['f'].elements['cajaArribaAbierta'].value = false;
				else
					document.forms['f'].elements['cajaArribaAbierta'].value = true;
			}
		
			function toggleCajaAbajo() {
				toggle('nueva_ruta');
				toggleClass('set_table2', 'set_table2', 'set_table2 menos_img');
				
				if (document.getElementById('nueva_ruta').style.display == 'none')
					document.forms['f2'].elements['cajaAbajoAbierta'].value = false;
				else
					document.forms['f2'].elements['cajaAbajoAbierta'].value = true;
			}	
		
			function getAbiertaCajaArriba() {
				if (document.getElementById('nuevo_buscador').style.display == 'none')
					return false;
				else
					return true;
			}
		
			function getAbiertaCajaAbajo() {		
				if (document.getElementById('nueva_ruta').style.display == 'none')
					return false;
				else
					return true;
			}	
			
			
			/////////////////////////////////////////////////////
			/// Funciones que controlan los hrefs
			/// Estos hrefs son necesarios generarlos dinamica-
			/// mente debido a que necesitan contener el valor
			/// actual de depliegue de las cajas; si se muestran
			/// o no.
			/////////////////////////////////////////////////////	
				
			function changeLinkHref(id,newHref) {
				if (document.links.length > 0) {
					if (document.getElementById) {
						document.getElementById(id).href = newHref;
					}
					else if (document.all) {
						document.all[id].href = newHref;
					}
				}
			}
			
			function getURL_nextLevelTree(identificador,tipoBusqueda,tipoNavegacion,idiomBusc,numeroOdesAsociados) {
				var cajaArribaAbierta = getAbiertaCajaArriba();
				var cajaAbajoAbierta = getAbiertaCajaAbajo();
				return "http://"+host_and_subdomain+"/buscador2/NavegarArbolCurricularCU/ArbolCurricularSeleccionarHijo.do?areaCurricularBusqueda="+identificador+"&tipoBusqueda="+tipoBusqueda+"&tipoNavegacion="+tipoNavegacion+"&idiomBusc="+idiomBusc+"&numeroResultados="+numeroOdesAsociados+"&busquedaArbol=true&cajaArribaAbierta=" + cajaArribaAbierta + "&cajaAbajoAbierta=" + cajaAbajoAbierta;
			}
			
			function setHref_nextLevelTree(identificador,tipoBusqueda,tipoNavegacion,idiomBusc,numeroOdesAsociados) {
				changeLinkHref('dinamycLink_'+identificador, getURL_nextLevelTree(identificador,tipoBusqueda,tipoNavegacion,idiomBusc,numeroOdesAsociados));
			}
				
			function getURL_breadcrumb(identificador,tipoBusqueda,tipoNavegacion,idiomBusc) {
				var cajaArribaAbierta = getAbiertaCajaArriba();
				var cajaAbajoAbierta = getAbiertaCajaAbajo();
				//Esto habrá que cambiarlo como otra función
				return "http://"+host_and_subdomain+"/buscador2/NavegarArbolCurricularCU/ArbolCurricularSeleccionarHijo.do?areaCurricularBusqueda="+identificador+"&tipoBusqueda="+tipoBusqueda+"&tipoNavegacion="+tipoNavegacion+"&idiomBusc="+idiomBusc+"&cajaArribaAbierta=" + cajaArribaAbierta + "&cajaAbajoAbierta=" + cajaAbajoAbierta;														
			}
			
			function setHref_breadcrumb(identificador,tipoBusqueda,tipoNavegacion,idiomBusc) {
				changeLinkHref('dinamycLink_bis_'+identificador, getURL_breadcrumb(identificador,tipoBusqueda,tipoNavegacion,idiomBusc));
			}
				
			function getURL_viewODES(identificador,tipoBusqueda,tipoNavegacion,idiomBusc) {
				var cajaArribaAbierta = getAbiertaCajaArriba();
				var cajaAbajoAbierta = getAbiertaCajaAbajo();
				//Esto habrá que cambiarlo como otra función
				return "http://"+host_and_subdomain+"/buscador2/NavegarArbolCurricularCU/ArbolCurricularBuscarAvanzado.do?areaCurricularBusqueda="+identificador+"&tipoBusqueda="+tipoBusqueda+"&tipoNavegacion="+tipoNavegacion+"&idiomBusc="+idiomBusc+"&cajaArribaAbierta=" + cajaArribaAbierta + "&cajaAbajoAbierta=" + cajaAbajoAbierta +"&consultaRealizadaDesdeCajaArriba=false";														
			}
			
			function setHref_viewODES(identificador,tipoBusqueda,tipoNavegacion,idiomBusc) {
				changeLinkHref('dinamycLink_'+identificador+'_viewODES', getURL_viewODES(identificador,tipoBusqueda,tipoNavegacion,idiomBusc));
			}
				
			/////////////////////////////////////////////////////
			/// Funciones del primer form
			/////////////////////////////////////////////////////
				
			function getIdioma_form1(){
				var indiceIdioma = document.forms['f'].elements['IdiomaCombo'].selectedIndex;
				var idioma = document.forms['f'].elements['IdiomaCombo'].options[indiceIdioma].value;	
				return idioma;
			}			
			
			function getAmbito_form1(){
				var ambito = getCheckedValue(document.forms['f'].elements['ambitoBusquedaCombos']);
				return ambito;
			}
			
			function getTipoBusqueda_form1(){
				var tipoBusqueda;
				if (getAmbito_form1() == '05') 
					tipoBusqueda = "05"; 			
				else 
					tipoBusqueda = "06";
				return tipoBusqueda; 
			}	
		
			function dameCiclo(){	
				var ambito = getAmbito_form1();		
				var valorIdioma = getIdioma_form1();	
				
				var cajaArribaAbierta = getAbiertaCajaArriba();
				var cajaAbajoAbierta = getAbiertaCajaAbajo();
		
				etapa = document.getElementById("etapa");
				objF = document.getElementById("f");
				identificador = etapa.value;
				objF.action = "http://"+host_and_subdomain+"/buscador2/NavegarArbolCurricularCU/ArbolCurricularSeleccionarHijo.do?areaCurricularBotones=" + identificador + "&tipoBusqueda=" + tipoBusqueda + "&tipoNavegacion=" + tipoNavegacion + "&idiomBusc=" + idiomBusc + "&ambitoBusquedaCombos=" + ambito + "&lenguajeBusquedaCombos=" + valorIdioma + "&cajaArribaAbierta=" + cajaArribaAbierta + "&cajaAbajoAbierta=" + cajaAbajoAbierta;
				objF.submit();
			}
			
			function dameCurso(){
				var ambito = getAmbito_form1();		
				var valorIdioma = getIdioma_form1();	
					
				var cajaArribaAbierta = getAbiertaCajaArriba();
				var cajaAbajoAbierta = getAbiertaCajaAbajo();
				
				curso = document.getElementById("curso");
				objF = document.getElementById("f");
				identificador = curso.value;
				objF.action = "http://"+host_and_subdomain+"/buscador2/NavegarArbolCurricularCU/ArbolCurricularSeleccionarHijo.do?areaCurricularBotones=" + identificador + "&tipoBusqueda=" +tipoBusqueda + "&tipoNavegacion=" + tipoNavegacion + "&idiomBusc=" + idiomBusc + "&ambitoBusquedaCombos=" + ambito + "&lenguajeBusquedaCombos=" + valorIdioma + "&cajaArribaAbierta=" + cajaArribaAbierta + "&cajaAbajoAbierta=" + cajaAbajoAbierta;
				objF.submit();
			}
			
			function dameAsignatura(){
				var ambito = getAmbito_form1();		
				var valorIdioma = getIdioma_form1();	
				
				var cajaArribaAbierta = getAbiertaCajaArriba();
				var cajaAbajoAbierta = getAbiertaCajaAbajo();
				
				asignatura = document.getElementById("asignatura");
				objF = document.getElementById("f");
				identificador = asignatura.value;
				objF.action ="http://"+host_and_subdomain+ "/buscador2/NavegarArbolCurricularCU/ArbolCurricularSeleccionarHijo.do?areaCurricularBotones=" + identificador + "&tipoBusqueda=" + tipoBusqueda + "&tipoNavegacion=" + tipoNavegacion + "&idiomBusc=" + idiomBusc + "&ambitoBusquedaCombos=" + ambito + "&lenguajeBusquedaCombos=" + valorIdioma + "&cajaArribaAbierta=" + cajaArribaAbierta + "&cajaAbajoAbierta=" + cajaAbajoAbierta;
				objF.submit();
			}
			
			function dameBloque_t(){
				var ambito = getAmbito_form1();		
				var valorIdioma = getIdioma_form1();	
				
				var cajaArribaAbierta = getAbiertaCajaArriba();
				var cajaAbajoAbierta = getAbiertaCajaAbajo();
				
				bloque_tematico = document.getElementById("bloque_t");
				objF = document.getElementById("f");
				identificador = bloque_tematico.value;
				objF.action = "http://"+host_and_subdomain+"/buscador2/NavegarArbolCurricularCU/ArbolCurricularSeleccionarHijo.do?areaCurricularBotones=" + identificador + "&tipoBusqueda=" + tipoBusqueda + "&tipoNavegacion=" + tipoNavegacion + "&idiomBusc=" + idiomBusc + "&ambitoBusquedaCombos=" + ambito + "&lenguajeBusquedaCombos=" + valorIdioma + "&cajaArribaAbierta=" + cajaArribaAbierta + "&cajaAbajoAbierta=" + cajaAbajoAbierta;
				objF.submit();
			}
		
			function doSubmit_form1() {		
				var ambitoBusc = getTipoBusqueda_form1();
				var valorIdioma = getIdioma_form1();	
				
				var cajaArribaAbierta = getAbiertaCajaArriba();
				var cajaAbajoAbierta = getAbiertaCajaAbajo();
				
				objF = document.getElementById("f");
				objF.action = "http://"+host_and_subdomain+"/buscador2/NavegarArbolCurricularCU/ArbolCurricularBuscarAvanzado.do?areaCurricularBusqueda=" + areaCurricularBotones + "&tipoBusqueda=" + ambitoBusc + "&tipoNavegacion=" + tipoNavegacion + "&idiomBusc=" + valorIdioma + "&busquedaArbol=true&consultaRealizadaDesdeCajaArriba=true" + "&cajaArribaAbierta=" + cajaArribaAbierta + "&cajaAbajoAbierta=" + cajaAbajoAbierta;
				objF.submit();
			}
					
			
			/////////////////////////////////////////////////////
			/// Funciones del segundo form
			/////////////////////////////////////////////////////	
				
			function getAmbito_form2(){
				var ambito = getCheckedValue(document.forms['f2'].elements['tipoBusqueda']);
				return ambito;
			}
			
			function selectHijo(){			
				var indice = document.forms['f2'].elements['IdiomaCombo'].selectedIndex;
				var valor = document.forms['f2'].elements['IdiomaCombo'].options[indice].value;	
				
				var busqueda = getAmbito_form2();	
				
				var cajaArribaAbierta = getAbiertaCajaArriba();
				var cajaAbajoAbierta = getAbiertaCajaAbajo();
				
				objF = document.getElementById("f2");
				objF.action = "http://"+host_and_subdomain+"/buscador2/NavegarArbolCurricularCU/ArbolCurricularSeleccionarHijo.do?areaCurricularBotones=" + ultimoId + "&tipoBusqueda=" + busqueda + "&tipoNavegacion=" + tipoNavegacion + "&idiomBusc=" + valor + "&ambitoBusquedaCombos=" + ambitoBusquedaCombos + "&cajaArribaAbierta=" + cajaArribaAbierta + "&cajaAbajoAbierta=" + cajaAbajoAbierta;
				objF.submit();
			}
			
			
		</script>
		<!-- 
		codigo_combos.js ya esta incluido en codigo.js y si incluyo los dos dejan de funcionar los select de buscador avanzado
		<script type="text/javascript" src="http://www.therightdirection.biz/clientes/indra/red_2011/js/codigo_combos.js"></script>
		 
		<script type="text/javascript" src="http://www.pengoworks.com/workshop/jquery/field/jquery.field.min.js"></script>
		
		<script type="text/javascript">
		$(document).ready(function() {
			$("a.set_table").click(function(event) {
				$("div.tabla_oculta").toggle();
				$("a.set_table").toggleClass("menos_img");
				if ($("input[name='cajaArribaAbierta']").val()==true) {
					$("input[name='cajaArribaAbierta']").val(false);
				} else {
					$("input[name='cajaArribaAbierta']").val(true);
				}
				event.preventDefault();
			})
			$("a.set_table2").click(function(event) {
				$("div.tabla_oculta2").toggle();
				$("a.set_table2").toggleClass("menos_img");
				if ($("input[name='cajaAbajoAbierta']").val()==true)
					$("input[name='cajaAbajoAbierta']").val(false);
				else
					$("input[name='cajaAbajoAbierta']").val(true);
				event.preventDefault();
			})
		})
		</script>
		-->


    </tiles:put>

</tiles:insert>