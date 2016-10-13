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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<tiles:insert definition="layout-sinlateral">
<tiles:put name="title" type="string">
	<bean:message key="title.Comun"/>
</tiles:put>

<tiles:put name="body" type="string">
<%@ include file="/taglib-imports.jspf" %>
<!-- Inicio plantilla contenido  -->
<!-- Pintar mensajes de error  -->





<analytic:googleAnalytic />

<!-- Inicio caja buscador -->
<!-- Inicio caja buscador -->


<!-- NAVEGACIÓN POR TESAURO EN BÚSQUEDA TAXONÓMICA -->
<c:if test="${form.tipoBusqueda=='07'||form.tipoBusqueda=='08'}">
	<form method="post" action="<html:rewrite action=""/>" >
	<!-- AQUI NUEVO -->
	<section id="arbol_res" class="bloque_resultados clearfix">
	<c:if test="${!empty form.rutaTesauro}">
		<strong><c:out value="${form.numMaxResultados}"/> 
			<c:if test="${form.numMaxResultados==1 }">
				<bean:message key="listarODE.arbolCurricular.objeto"/>
			</c:if>
			<c:if test="${form.numMaxResultados!=1 }">
				<bean:message key="listarODE.arbolCurricular.objetos"/>
			</c:if>
		</strong>
	</c:if>
	<h3>${form.tituloTesauro}</h3>
	<p><bean:message key="navegar.tesauro.explicacion.taxonomia"/></p>
	<div class="arbol_ruta">
	<div>
		<html:link action="/NavegarTesauroCU/NavegarTesauroCU.do?tipoBusqueda=${form.tipoBusqueda}&tituloTesauro=${form.tituloTesauro}&tipoNavegacion=${form.tipoNavegacion}">
			<bean:message key="navegar.tesauro.nombre.inicio"/>
		</html:link>
 		<c:if test="${!empty form.rutaTesauro}">
	 		<c:set var="longitud" value="${ fn:length(form.rutaTesauro)}"/>
			<fmt:formatNumber var="nombre" value="${longitud -1}" type="number"/>
			<fmt:formatNumber var="nombre2" value="${longitud -2}" type="number"/>
	 		<c:if test="${longitud>0 }">
	 			<c:if test="${nombre2>=0 }">
		   			<c:forEach items="${form.rutaTesauro}" var="ruta" begin="0" end="${nombre2}">
		    			<em class="oculto">-</em>
				    	<html:link action="/NavegarTesauroCU/NavegarTesauroSeleccionarPadre.do?idTesauro=${ruta.id}&tipoBusqueda=${form.tipoBusqueda}&tituloTesauro=${form.tituloTesauro}&tipoNavegacion=${form.tipoNavegacion}">
		        			<c:out value="${ruta.valorTax}"/>
		        		</html:link>
		    		</c:forEach>
				</c:if>
	    		<c:set var="ultimo" value="${ form.rutaTesauro[nombre]}"/>
	    		<logic:equal name="form" property="esHoja" value="false">
			    	<html:link  action="/NavegarTesauroCU/NavegarTesauroSeleccionarPadre.do?idTesauro=${form.rutaTesauro[nombre].id}&tipoBusqueda=${form.tipoBusqueda}&tituloTesauro=${form.tituloTesauro}&tipoNavegacion=${form.tipoNavegacion}">
	        			<c:out value="${ultimo.valorTax}"/>
	        		</html:link>
				</logic:equal>
				<logic:equal name="form" property="esHoja" value="true">
					<span class="lc">
						<c:out value="${ultimo.valorTax}"/>
					</span>
				</logic:equal>
			</c:if>
		</c:if>
	</div>
	</div>
	</section>
	<!--  HASTA AQUI -->
	</form>
</c:if>


<form name="Registration" method="post" action="<html:rewrite action="/BuscarAvanzadoCU/MostrarResultadosImagenesPaginarImagenes.do"/>" >

	<article id="buscador">
		<header>
			<h2 class="oculto">Resultados de B&uacute;squeda</h2>
		</header>
		<!-- INICIO BUSCADOR AGREGA 2 -->
		
		<jsp:include page="/layout/messages.jsp" flush="true" />
		
		<section>
			<span class="enlace_f"><a href="<rewrite:rewrite url="${initParam.url_formularioAvanzado}"/>"><bean:message key="buscador.avanzado" /></a></span>
			<fieldset id="caja_buscador">
    		<div>
				<input type="submit" alt="<bean:message key="buscador.boton.buscar.alt"/>" class="boton_submit" value="<bean:message key="buscador.buscador.boton"/>" name="buscar" />		
	    		<label for="busc_contenidos">Buscar Contenidos Agrega :</label>
				<c:choose>
				<c:when test="${!empty form.buscContenido}">
					<input type="text" id="buscadorGeneral" autocomplete="off"  onkeyup="buscadorGeneral_bis.value=this.value" class="buscador" value="${form.buscContenido}" title="Buscador" name="buscContenido"  />
				</c:when>
				<c:when test="${!empty form.keyword}">
					<input type="text" id="buscadorGeneral" autocomplete="off"  onkeyup="buscadorGeneral_bis.value=this.value" class="buscador" value="${form.keyword}" title="Buscador" name="buscContenido"  />
				</c:when>
				<c:otherwise>
					<!-- 
					<input type="text" id="buscadorGeneral" autocomplete="off" onfocus="clearText(this)" onkeyup="buscadorGeneral_bis.value=this.value" class="buscador" value="<bean:message key="buscador.textinput"/>" title="Buscador" name="buscContenido"  />
				     value=""
					-->
					<input type="text" id="buscadorGeneral" autocomplete="off"  onkeyup="buscadorGeneral_bis.value=this.value" class="buscador" value=""  title="Buscador" name="buscContenido"  />
				</c:otherwise>
				</c:choose>
				<bean:define id="i18nValue"><bean:message key="buscador.idioma.por.defecto"/></bean:define>
				<label for="idioma"><bean:message key="buscador.idioma.del.contenido"/>:&nbsp;</label>
				<span class="caja_de_boton">
					<c:choose>
						<c:when test="${!empty form.idiomBuscBackingList}">
						   <html:select onchange="aplicarIdioma();" name="form" property="idiomBusc" styleId="Idioma" title="${i18nValue }" styleClass="select">
							 <html:optionsCollection name="form" property="idiomBuscBackingList" label="label" value="value"/>
						   </html:select>
						</c:when>
						<c:otherwise>
							<html:select name="form" property="idiomBusc" styleId="Idioma" title="${i18nValue }" styleClass="select"/>
						</c:otherwise>
					</c:choose>
    			</span>
			</div>
			</fieldset>
		
			<!--  tiles:insert definition="buscador" flush="false"/ -->
			<fieldset class="radios">
				<c:choose>
				<c:when test="${(empty form.tipoBusqueda) or (form.tipoBusqueda!='01' and form.tipoBusqueda!='02' and form.tipoBusqueda!='03' and form.tipoBusqueda!='05' and form.tipoBusqueda!='06')}">
					<label for="todo_agrega">
						<input type="radio" onclick="aplicarCambiosBusqueda('01');" id="tipoBusqueda" class="botonradio" value="01" name="tipoBusqueda" checked="checked" />
					<bean:message key="buscador.radio.buscar.en.red" /></label>
					<label for="nodo_cca_aa">
						<input type="radio" onlick="aplicarCambiosBusqueda('02');" id="tipoBusqueda" class="botonradio" value="02" name="tipoBusqueda" />
					<server:serverProperties property="ccaa"/></label>					
				</c:when>
				<c:otherwise>
					<label for="todo_agrega">
						<c:choose>
						<c:when test="${(!empty form.tipoBusqueda) and (form.tipoBusqueda=='01' or form.tipoBusqueda=='05')}">
							<input type="radio" onclick="aplicarCambiosBusqueda('01');" id="tipoBusqueda" class="botonradio" value="01" name="tipoBusqueda" checked="checked" />	
						</c:when>
						<c:otherwise>
							<input type="radio" onclick="aplicarCambiosBusqueda('01');" id="tipoBusqueda" class="botonradio" value="01" name="tipoBusqueda" />			
						</c:otherwise>
						</c:choose>
					<bean:message key="buscador.radio.buscar.en.red" /></label>
					<label for="nodo_cca_aa">
						<c:choose>
						<c:when test="${(!empty form.tipoBusqueda) and (form.tipoBusqueda=='02' or form.tipoBusqueda=='06')}">
							<input type="radio" onclick="aplicarCambiosBusqueda('02');" id="tipoBusqueda" class="botonradio" value="02" name="tipoBusqueda" checked="checked" />			
						</c:when>
						<c:otherwise>
							<input type="radio" onlick="aplicarCambiosBusqueda('02');" id="tipoBusqueda" class="botonradio" value="02" name="tipoBusqueda" />			
						</c:otherwise>
						</c:choose>
					<server:serverProperties property="ccaa"/></label>
					<c:choose>
					<c:when test="${(!empty form.tipoBusqueda) and (form.tipoBusqueda=='03')}">
						<label for="personalizada">
							<input type="radio" onclick="aplicarCambiosBusqueda('03');" id="tipoBusqueda" class="botonradio" value="03" name="tipoBusqueda" checked="checked" />
						<bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.personalizada" /></label>
						
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
					</c:when>
					</c:choose>
				</c:otherwise>
				</c:choose>
			</fieldset>			
	    </section>
	</article>
	<!-- FIN BUSCADOR AGREGA 2 -->
	
	
	<article id="main" class="clearfix">
	
		<c:if test="${form.tipoBusqueda!='07' && form.tipoBusqueda!='08'}">
		
			<div class="col_izq">
				<div class="fieldset">
					<header>
						<strong class="legend oculto_imp"><bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.resultadoBusqueda"/> (${form.numMaxResultados})</strong>
					</header>

					<!-- SOLO SE MUESTRA EL FILTRO EN DETERMINADOS TIPOS DE BUSQUEDA -->
					
					<c:choose>
					<c:when test="${(!empty form.tipoBusqueda) and (form.tipoBusqueda=='01' or form.tipoBusqueda=='02' or form.tipoBusqueda=='05' or form.tipoBusqueda=='06')}">
					
						<!-- INICIO FILTRO -->
						<ul>
							<li>  
							<input onclick="syncCheckboxsAndInput('todo'); this.form.submit();" id="todo" type="checkbox" name="box1" value="1"/>              
							<label for="todo"><bean:message key="listar.odecu.mostrar.resultados.consulta.vo.todo"/></label>     
							</li>  
							<li>  
								<input onclick="syncCheckboxsAndInput('1'); this.form.submit();" id="Level01" type="checkbox" name="filtroNivelAgreg"/> <!-- value="${valor.value}" /> -->  
								<label for="Level01"><bean:message key="listar.odecu.mostrar.resultados.consulta.vo.obj.basicos"/></label>
							</li>
							<fmt:formatNumber var="contIndices" value="1" type="number"/>							 				
							<logic:iterate id="valor" name="form" property="nivelAgregBackingList">					
								<c:if test="${contIndices=='1'}">  
									<input type="hidden" id="Nivel0${valor.value}" name="filtroNivelAgregacion"/> <!-- value="${valor.value}" /> -->  
								</c:if>
								<c:set var="contIndices" value="${contIndices+1}"/>
							</logic:iterate>
							<ul>   
								<!-- Solo imprimimos los formatos de "imagen", "sonido" y "video"-->
								<!-- Si eliminaramos el if de abajo tendriamos mas opciones de filtrar las busquedas; 
								"aplicacion", "imagen", "sonido", "texto" y "video" -->
								<fmt:formatNumber var="contIndices" value="1" type="number"/>
								<logic:iterate id="valor" name="form" property="formatoBackingList">		
									<c:if test="${contIndices!='1' && contIndices!='4'}">
										<li>  
											<input onclick="syncCheckboxsAndInput('imagen_sonido_video'); this.form.submit();" id="Formato0${contIndices}" type="checkbox" name="filtroFormato" value="${valor.value}" />  
											<label for="Formato0${contIndices}">${valor.label}</label>
										</li>
									</c:if>
									<c:set var="contIndices" value="${contIndices+1}"/>
								</logic:iterate>
								
								<!-- opcion "otros" donde se unen las opciones de "texto" (4) y "aplicacion" (1) que no se mostraron antes -->
								<li>  
									<input onclick="syncCheckboxsAndInput('otros'); this.form.submit();" id="otros" type="checkbox" name="filtroFormato" value="" />  
									<label for="otros"><bean:message key="listar.odecu.mostrar.resultados.consulta.vo.otros"/></label>
								</li>	
								<fmt:formatNumber var="contIndices" value="1" type="number"/>
								<logic:iterate id="valor" name="form" property="formatoBackingList">		
									<c:if test="${contIndices=='1' || contIndices=='4'}">
										<input type="hidden" id="Formato0${contIndices}" name="filtroFormato"/> <!-- value="${valor.value}" /> -->
									</c:if>
									<c:set var="contIndices" value="${contIndices+1}"/>
								</logic:iterate>
							</ul>      
							</li>							
							<!-- Solo imprimimos la opcion de "Secuencias didácticas" -->
							<!-- Si eliminaramos el if de abajo tendriamos mas opciones de filtrar las busquedas; 
							"Medias y medias integrados", "Objetos de aprendizaje", y "Cursos, planes y programas de formación" -->
							<fmt:formatNumber var="contIndices" value="1" type="number"/>							 				
							<logic:iterate id="valor" name="form" property="nivelAgregBackingList">					
								<c:if test="${contIndices=='3'}">
									<li>  
										<input onclick="syncCheckboxsAndInput('3'); this.form.submit();" id="Nivel0${valor.value}" type="checkbox" name="filtroNivelAgregacion" value="${valor.value}" />  
										<!-- 
										<label for="Nivel0${valor.value}">Objetos de aprendizaje y ${valor.label}</label>
										-->
										<label for="Nivel0${valor.value}"><bean:message key="listar.odecu.mostrar.resultados.consulta.vo.obj.aprendizaje"/></label>
									</li>
								</c:if>
								<c:set var="contIndices" value="${contIndices+1}"/>
							</logic:iterate>	
							
							<!-- opcion "Secuencias didácticas" donde se los niveles de agregacion superiores al 2 iunclusive -->
							<fmt:formatNumber var="contIndices" value="1" type="number"/>							 				
							<logic:iterate id="valor" name="form" property="nivelAgregBackingList">					
								<c:if test="${contIndices=='2' || contIndices=='4'}">
									<input type="hidden" id="Nivel0${valor.value}" name="filtroNivelAgregacion"/> <!-- value="${valor.value}" /> -->
								</c:if>
								<c:set var="contIndices" value="${contIndices+1}"/>
							</logic:iterate>	
						</ul> 
		
						<!-- FIN FILTRO -->
					</c:when>
					<c:otherwise>	
					
						<!-- estos espacios en blanco son un truco para hacer que 
						aparezca un hueco en blanco donde en ocasiones se muestra 
						el filtro. De esta forma mantenemos la colocacion de los 
						elementos en la pagina -->
						<ul>
						<li>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</li>
						</ul> 
						
					</c:otherwise>
					</c:choose>
				</div>
			</div>

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
			<!-- FIN SUGERENCIAS-->
		</c:if>

		<div class="col_der">
		
			<header id="resultados" class="clearfix">
				<a class="rss_feed2" target="_blank" href="<rewrite:rewrite url="feed-agrega2/20/${form.buscContenEnlace}/${form.idioma}/${form.anterior}/${form.formatoURL}/${form.idODE}/${form.recurso}/${form.procesoCognitivo}/${form.contexto}/${form.edad}/${form.autor}/${form.diaPublic}/${form.mesPublic}/${form.anyoPublic}/${form.c_s_secuencia}/${form.valoracion}/${form.enlaceTaxSelec}/${form.enlaceComuSelec}/${form.tipoLayoutBuscador}/${form.idTesauro}/${form.nomTesauros}/${form.nivelAgreg}/${form.destinatarios}/${form.keyword}/${form.tipoBusqueda}/RSS"/>" ><bean:message key="listar.odecu.mostrar.resultados.rss"/></a>
		    	<span class="resultados"><bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.desdeHasta" arg0="${form.resultadosDesde}" arg1="${form.resultadosHasta}" arg2="${form.numMaxResultados}"/></span>
		    	<h3 class="res_b"><bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.resultadoBusqueda"/></h3>
		    </header>
		    
		    
		    <!-- NAVEGACIÓN POR TAXONOMIA EN BÚSQUEDA TAXONÓMICA -->
			<c:if test="${form.tipoBusqueda=='05'||form.tipoBusqueda=='06'}">
				<form method="post" action="<html:rewrite action=""/>" >
				<!-- AQUI NUEVO -->
				<section id="arbol_res" class="bloque_resultados clearfix">
				<c:if test="${!empty form.rutaArbol}">
					<!-- 
					<strong><c:out value="${form.numMaxResultados}"/> Objetos</strong>
					-->
					<h3>${form.taxNombre}
						<c:if test="${form.numMaxResultados==1 }">
				 			<bean:message key="listarODE.arbolCurricular.objeto"/>
				 		</c:if>
				  		<c:if test="${form.numMaxResultados!=1 }">
				  			<bean:message key="listarODE.arbolCurricular.objetos"/>
						</c:if>
					</h3>
				</c:if>
				<p><bean:message key="listarODE.arbolCurricular.explicacion.taxonomia"/></p>
				<div class="arbol_ruta">
				<div>
					<html:link action="/NavegarArbolCurricularCU/NavegarArbolCurricularCU.do?tipoBusqueda=${form.tipoBusqueda}&tipoNavegacion=${form.tipoNavegacion}&idiomBusc=${form.idiomBusc}">
						<bean:message key="listarODE.taxonomia.inicio"/>
					</html:link>
					<c:if test="${!empty form.rutaArbol}">
						<c:set var="longitud" value="${fn:length(form.rutaArbol)}"/>
						<fmt:formatNumber var="nombre" value="${longitud -1}" type="number"/>
						<fmt:formatNumber var="nombre2" value="${longitud -2}" type="number"/>
						<c:if test="${longitud>0 }">
							<c:if test="${nombre2>=0 }">
							
								<c:forEach items="${form.rutaArbol}" var="ruta" begin="0" end="${nombre2}">
									<em class="oculto">-</em>
									<c:choose>
									<c:when test="${(!empty form.consultaRealizadaDesdeCajaArriba) and (form.consultaRealizadaDesdeCajaArriba==false)}">
										<html:link action="/NavegarArbolCurricularCU/ArbolCurricularSeleccionarHijo.do?areaCurricularBusqueda=${ruta.id}&tipoBusqueda=${form.tipoBusqueda}&tipoNavegacion=${form.tipoNavegacion}&idiomBusc=${form.idiomBusc}&cajaArribaAbierta=${form.cajaArribaAbierta}&cajaAbajoAbierta=${form.cajaAbajoAbierta}">
											<c:out value="${ruta.valorTax}"/>
										</html:link>
									</c:when>
									<c:otherwise>
										<html:link action="/NavegarArbolCurricularCU/ArbolCurricularSeleccionarHijo.do?areaCurricularBotones=${ruta.id}&tipoBusqueda=${form.tipoBusqueda}&ambitoBusquedaCombos=${form.tipoBusqueda}&tipoNavegacion=${form.tipoNavegacion}&idiomBusc=${form.idiomBusc}&lenguajeBusquedaCombos=${form.idiomBusc}&cajaArribaAbierta=${form.cajaArribaAbierta}&cajaAbajoAbierta=${form.cajaAbajoAbierta}">
											<c:out value="${ruta.valorTax}"/>
										</html:link>
									</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:if>
							
							<c:set var="ultimo" value="${form.rutaArbol[nombre]}"/>
							<logic:equal name="form" property="esHoja" value="false">
								<c:choose>
								<c:when test="${(!empty form.consultaRealizadaDesdeCajaArriba) and (form.consultaRealizadaDesdeCajaArriba==false)}">
									<html:link action="/NavegarArbolCurricularCU/ArbolCurricularSeleccionarHijo.do?areaCurricularBusqueda=${form.rutaArbol[nombre].id}&tipoBusqueda=${form.tipoBusqueda}&tipoNavegacion=${form.tipoNavegacion}&idiomBusc=${form.idiomBusc}&cajaArribaAbierta=${form.cajaArribaAbierta}&cajaAbajoAbierta=${form.cajaAbajoAbierta}">
										<c:out value="${ultimo.valorTax}"/>
							     	</html:link>
								</c:when>
								<c:otherwise>
									<html:link action="/NavegarArbolCurricularCU/ArbolCurricularSeleccionarHijo.do?areaCurricularBotones=${form.rutaArbol[nombre].id}&tipoBusqueda=${form.tipoBusqueda}&ambitoBusquedaCombos=${form.tipoBusqueda}&tipoNavegacion=${form.tipoNavegacion}&idiomBusc=${form.idiomBusc}&lenguajeBusquedaCombos=${form.idiomBusc}&cajaArribaAbierta=${form.cajaArribaAbierta}&cajaAbajoAbierta=${form.cajaAbajoAbierta}">
										<c:out value="${ultimo.valorTax}"/>
							     	</html:link>
								</c:otherwise>
								</c:choose>
							</logic:equal>
							<logic:equal name="form" property="esHoja" value="true">
						   		<span class="lc">
									<c:out value="${ultimo.valorTax}"/>
								</span>
							</logic:equal>
						</c:if>
					</c:if>
				</div>
				</div>
				</section>
				<!--  HASTA AQUI -->
				</form>
			</c:if>
        
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
				numValoraciones="${resultados.numValoraciones}" 
				tipoRecurso="${resultados.tipoRecurso}" 
				areaCurricular="${resultados.areaCurricular}" 
				nodo="${resultados.nodo}" 
				urlCambiarImagen="/buscador2/BuscarAvanzadoCU/MostrarResultadosImagenesCambiarImagenODE.do?idioma=${form.idioma}&amp;buscContenido=${form.buscContenEnlace}&amp;pagina=${form.pagina}&amp;formato=${form.formato}&amp;idODE=${form.idODE}&amp;recurso=${form.recurso}&amp;procesoCognitivo=${form.procesoCognitivo}&amp;contexto=${form.contexto}&amp;edad=${form.edad}&amp;autor=${form.autor}&amp;diaPublic=${form.diaPublic}&amp;mesPublic=${form.mesPublic}&amp;anyoPublic=${form.anyoPublic}&amp;c_s_secuencia=${form.c_s_secuencia}&amp;valoracion=${form.valoracion}&amp;enlaceTaxSelec=${form.enlaceTaxSelec}&amp;identificadorODE=${resultados.id}&amp;tipoVisualiz=${form.tipoVisualiz}&amp;nodoDestino=${resultados.nodo}&amp;enlaceComuSelec=${form.enlaceComuSelec}&amp;tipoBusqueda=${form.tipoBusqueda}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;idTesauro=${form.idTesauro}&amp;nomTesauros=${form.nomTesauros}&amp;idTesauroSug=${form.idTesauroSug}&amp;nivelAgreg=${form.nivelAgreg}&amp;destinatarios=${form.destinatarios}&amp;keyword=${form.keyword}&amp;numeroResultados=${form.numeroResultados}"
				urlPrevisualizar="/buscador2/BuscarAvanzadoCU/MostrarResultadosImagenesPrevisualizarODE.do?idioma=${form.idioma}&amp;identificadorODE=${resultados.id}&amp;nodoDestino=${resultados.nodo}&amp;seleccionarSecuencia=${resultados.conSecuencia}"
				url="/buscador2/BuscarAvanzadoCU/MostrarResultadosImagenesPrepararRetornoDetalleImagenes.do?idioma=${form.idioma}&amp;buscContenido=${form.buscContenEnlace}&amp;pagina=${form.pagina}&amp;formato=${form.formato}&amp;idODE=${form.idODE}&amp;recurso=${form.recurso}&amp;procesoCognitivo=${form.procesoCognitivo}&amp;contexto=${form.contexto}&amp;edad=${form.edad}&amp;autor=${form.autor}&amp;diaPublic=${form.diaPublic}&amp;mesPublic=${form.mesPublic}&amp;anyoPublic=${form.anyoPublic}&amp;c_s_secuencia=${form.c_s_secuencia}&amp;valoracion=${form.valoracion}&amp;enlaceTaxSelec=${form.enlaceTaxSelec}&amp;identificadorODE=${resultados.id}&amp;tipoVisualiz=${form.tipoVisualiz}&amp;nodoDestino=${resultados.nodo}&amp;enlaceComuSelec=${form.enlaceComuSelec}&amp;tipoBusqueda=${form.tipoBusqueda}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;idTesauro=${form.idTesauro}&amp;nomTesauros=${form.nomTesauros}&amp;idTesauroSug=${form.idTesauroSug}&amp;nivelAgreg=${form.nivelAgreg}&amp;destinatarios=${form.destinatarios}&amp;keyword=${form.keyword}&amp;numeroResultados=${form.numeroResultados}"/>						
				<%cont = cont +1; %>
			</logic:iterate>	


			<!-- Fin Resultados de Búsqueda -->
			<!-- Fin Resultados de Búsqueda -->
			<!-- Paginacion -->
			<c:if test="${form.tipoBusqueda!='05' && form.tipoBusqueda!='06'&& form.tipoBusqueda!='07' && form.tipoBusqueda!='08'}">
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
			</c:if>
<!-- 			
			<c:if test="${form.tipoBusqueda=='05' || form.tipoBusqueda=='06'}">
				<div class="paginacion">
					<ul id="navlist">				
					
						<c:choose>
						<c:when test="${(!empty form.consultaRealizadaDesdeCajaArriba) and (form.consultaRealizadaDesdeCajaArriba==false)}">
						
							<logic:notEmpty name="form" property="anterior">									
								<li><a href="<html:rewrite action="/NavegarArbolCurricularCU/ArbolCurricularBuscarAvanzado.do"/>?areaCurricularBusqueda=${ultimo.id}&tipoBusqueda=${form.tipoBusqueda}&tipoNavegacion=${form.tipoNavegacion}&busquedaArbol=true&amp;pagina=${pagina}&amp;formato=${form.formato}&amp;nivelAgreg=${form.nivelAgreg}&idiomBusc=${form.idiomBusc}&cajaArribaAbierta=${form.cajaArribaAbierta}&cajaAbajoAbierta=${form.cajaAbajoAbierta}&consultaRealizadaDesdeCajaArriba=${form.consultaRealizadaDesdeCajaArriba}&numMaxResultados=${form.numMaxResultados}"><bean:message key="listarODE.arbolCurricular.anterior"/></a></li>
							</logic:notEmpty>
			
							<logic:iterate name="form" property="paginas" id="pagina">									
								<logic:equal name="form" property="pagina" value="${pagina}">
									<li>${pagina}</li>
								</logic:equal>
								<logic:notEqual name="form" property="pagina" value="${pagina}">
									<li><a href="<html:rewrite action="/NavegarArbolCurricularCU/ArbolCurricularBuscarAvanzado.do"/>?areaCurricularBusqueda=${ultimo.id}&tipoBusqueda=${form.tipoBusqueda}&tipoNavegacion=${form.tipoNavegacion}&busquedaArbol=true&amp;pagina=${pagina}&amp;formato=${form.formato}&amp;nivelAgreg=${form.nivelAgreg}&idiomBusc=${form.idiomBusc}&cajaArribaAbierta=${form.cajaArribaAbierta}&cajaAbajoAbierta=${form.cajaAbajoAbierta}&consultaRealizadaDesdeCajaArriba=${form.consultaRealizadaDesdeCajaArriba}&numMaxResultados=${form.numMaxResultados}">${pagina}</a></li>
								</logic:notEqual>
							</logic:iterate>
				
							<logic:notEmpty name="form" property="siguiente">
								<li><a href="<html:rewrite action="/NavegarArbolCurricularCU/ArbolCurricularBuscarAvanzado.do"/>?areaCurricularBusqueda=${ultimo.id}&tipoBusqueda=${form.tipoBusqueda}&tipoNavegacion=${form.tipoNavegacion}&busquedaArbol=true&amp;pagina=${pagina}&amp;formato=${form.formato}&amp;nivelAgreg=${form.nivelAgreg}&idiomBusc=${form.idiomBusc}&cajaArribaAbierta=${form.cajaArribaAbierta}&cajaAbajoAbierta=${form.cajaAbajoAbierta}&consultaRealizadaDesdeCajaArriba=${form.consultaRealizadaDesdeCajaArriba}&numMaxResultados=${form.numMaxResultados}"><bean:message key="listarODE.arbolCurricular.siguiente"/></a></li>
							</logic:notEmpty>
							
						</c:when>
						<c:otherwise>
							
							<logic:notEmpty name="form" property="anterior">									
								<li><a href="<html:rewrite action="/NavegarArbolCurricularCU/ArbolCurricularBuscarAvanzado.do"/>?areaCurricularBusqueda=${ultimo.id}&tipoBusqueda=${form.tipoBusqueda}&tipoNavegacion=${form.tipoNavegacion}&busquedaArbol=true&amp;pagina=${pagina}&amp;formato=${form.formato}&amp;nivelAgreg=${form.nivelAgreg}&ambitoBusquedaCombos=${form.tipoBusqueda}&idiomBusc=${form.idiomBusc}&lenguajeBusquedaCombos=${form.idiomBusc}&cajaArribaAbierta=${form.cajaArribaAbierta}&cajaAbajoAbierta=${form.cajaAbajoAbierta}&consultaRealizadaDesdeCajaArriba=${form.consultaRealizadaDesdeCajaArriba}&numMaxResultados=${form.numMaxResultados}"><bean:message key="listarODE.arbolCurricular.anterior"/></a></li>
							</logic:notEmpty>
			
							<logic:iterate name="form" property="paginas" id="pagina">									
								<logic:equal name="form" property="pagina" value="${pagina}">
									<li>${pagina}</li>
								</logic:equal>
								<logic:notEqual name="form" property="pagina" value="${pagina}">
									<li><a href="<html:rewrite action="/NavegarArbolCurricularCU/ArbolCurricularBuscarAvanzado.do"/>?areaCurricularBusqueda=${ultimo.id}&tipoBusqueda=${form.tipoBusqueda}&tipoNavegacion=${form.tipoNavegacion}&busquedaArbol=true&amp;pagina=${pagina}&amp;formato=${form.formato}&amp;nivelAgreg=${form.nivelAgreg}&ambitoBusquedaCombos=${form.tipoBusqueda}&idiomBusc=${form.idiomBusc}&lenguajeBusquedaCombos=${form.idiomBusc}&cajaArribaAbierta=${form.cajaArribaAbierta}&cajaAbajoAbierta=${form.cajaAbajoAbierta}&consultaRealizadaDesdeCajaArriba=${form.consultaRealizadaDesdeCajaArriba}&numMaxResultados=${form.numMaxResultados}">${pagina}</a></li>
								</logic:notEqual>
							</logic:iterate>
				
							<logic:notEmpty name="form" property="siguiente">
								<li><a href="<html:rewrite action="/NavegarArbolCurricularCU/ArbolCurricularBuscarAvanzado.do"/>?areaCurricularBusqueda=${ultimo.id}&tipoBusqueda=${form.tipoBusqueda}&tipoNavegacion=${form.tipoNavegacion}&busquedaArbol=true&amp;pagina=${pagina}&amp;formato=${form.formato}&amp;nivelAgreg=${form.nivelAgreg}&ambitoBusquedaCombos=${form.tipoBusqueda}&idiomBusc=${form.idiomBusc}&lenguajeBusquedaCombos=${form.idiomBusc}&cajaArribaAbierta=${form.cajaArribaAbierta}&cajaAbajoAbierta=${form.cajaAbajoAbierta}&consultaRealizadaDesdeCajaArriba=${form.consultaRealizadaDesdeCajaArriba}&numMaxResultados=${form.numMaxResultados}"><bean:message key="listarODE.arbolCurricular.siguiente"/></a></li>
							</logic:notEmpty>
							
						</c:otherwise>
						</c:choose>
							
					</ul>
				</div>
			</c:if>
-->			<c:if test="${form.tipoBusqueda=='05' || form.tipoBusqueda=='06'}">
				<div class="paginacion">
					<ul id="navlist">				
					
						<c:choose>
						<c:when test="${(!empty form.consultaRealizadaDesdeCajaArriba) and (form.consultaRealizadaDesdeCajaArriba==false)}">
						
							<logic:notEmpty name="form" property="anterior">	
								<li>
								<a href="" onclick="setHref_pagesCajaArriba('anterior', '${ultimo.id}', '${form.tipoBusqueda}', '${form.tipoNavegacion}', '${form.anterior}', '${form.idiomBusc}', '${form.cajaArribaAbierta}', '${form.cajaAbajoAbierta}', '${form.consultaRealizadaDesdeCajaArriba}')" id="dinamycLink_anterior"/><bean:message key="listarODE.arbolCurricular.anterior"/></a>
								<!-- Si descomentamos el enlace de abajo obtendremos un enlace de salto hacia atras (un rw) al grupo anterior de paginas -->
								<!--  
								<a href="" onclick="setHref_pagesCajaArriba('anterior', '${ultimo.id}', '${form.tipoBusqueda}', '${form.tipoNavegacion}', '${pagina}', '${form.idiomBusc}', '${form.cajaArribaAbierta}', '${form.cajaAbajoAbierta}', '${form.consultaRealizadaDesdeCajaArriba}')" id="dinamycLink_anterior"/><bean:message key="listarODE.arbolCurricular.anterior"/></a>
								-->
								</li>
							</logic:notEmpty>
			
							<logic:iterate name="form" property="paginas" id="pagina">									
								<logic:equal name="form" property="pagina" value="${pagina}">
									<li>${pagina}</li>
								</logic:equal>
								<logic:notEqual name="form" property="pagina" value="${pagina}">
									<li>
									<a href="" onclick="setHref_pagesCajaArriba('${pagina}', '${ultimo.id}', '${form.tipoBusqueda}', '${form.tipoNavegacion}', '${pagina}', '${form.idiomBusc}', '${form.cajaArribaAbierta}', '${form.cajaAbajoAbierta}', '${form.consultaRealizadaDesdeCajaArriba}')" id="dinamycLink_${pagina}"/>${pagina}</a>
									</li>
								</logic:notEqual>
							</logic:iterate>
				
							<logic:notEmpty name="form" property="siguiente">
								<li>
								<a href="" onclick="setHref_pagesCajaArriba('siguiente', '${ultimo.id}', '${form.tipoBusqueda}', '${form.tipoNavegacion}', '${form.siguiente}', '${form.idiomBusc}', '${form.cajaArribaAbierta}', '${form.cajaAbajoAbierta}', '${form.consultaRealizadaDesdeCajaArriba}')" id="dinamycLink_siguiente"/><bean:message key="listarODE.arbolCurricular.siguiente"/></a>
								<!-- Si descomentamos el enlace de abajo obtendremos un enlace de salto hacia delante (un fw) al grupo posterior de pagina -->
								<!-- 
								<a href="" onclick="setHref_pagesCajaArriba('siguiente', '${ultimo.id}', '${form.tipoBusqueda}', '${form.tipoNavegacion}', '${pagina}', '${form.idiomBusc}', '${form.cajaArribaAbierta}', '${form.cajaAbajoAbierta}', '${form.consultaRealizadaDesdeCajaArriba}')" id="dinamycLink_siguiente"/><bean:message key="listarODE.arbolCurricular.siguiente"/></a>
								-->
								</li>
							</logic:notEmpty>
							
						</c:when>
						<c:otherwise>
							
							<logic:notEmpty name="form" property="anterior">									
								<li>
								<a href="" onclick="setHref_pages('anterior', '${ultimo.id}', '${form.tipoBusqueda}', '${form.tipoNavegacion}', '${form.anterior}', '${form.idiomBusc}', '${form.cajaArribaAbierta}', '${form.cajaAbajoAbierta}', '${form.consultaRealizadaDesdeCajaArriba}')" id="dinamycLink_anterior"/><bean:message key="listarODE.arbolCurricular.anterior"/></a>
								<!-- Si descomentamos el enlace de abajo obtendremos un enlace de salto hacia atras (un rw) al grupo anterior de paginas -->
								<!-- 
								<a href="" onclick="setHref_pages('anterior', '${ultimo.id}', '${form.tipoBusqueda}', '${form.tipoNavegacion}', '${pagina}', '${form.idiomBusc}', '${form.cajaArribaAbierta}', '${form.cajaAbajoAbierta}', '${form.consultaRealizadaDesdeCajaArriba}')" id="dinamycLink_anterior"/><bean:message key="listarODE.arbolCurricular.anterior"/></a>
								-->
								</li>
							</logic:notEmpty>
			
							<logic:iterate name="form" property="paginas" id="pagina">									
								<logic:equal name="form" property="pagina" value="${pagina}">
									<li>${pagina}</li>
								</logic:equal>
								<logic:notEqual name="form" property="pagina" value="${pagina}">
									<li>
									<a href="" onclick="setHref_pages('${pagina}', '${ultimo.id}', '${form.tipoBusqueda}', '${form.tipoNavegacion}', '${pagina}', '${form.idiomBusc}', '${form.cajaArribaAbierta}', '${form.cajaAbajoAbierta}', '${form.consultaRealizadaDesdeCajaArriba}')" id="dinamycLink_${pagina}"/>${pagina}</a>
									</li>
								</logic:notEqual>
							</logic:iterate>
				
							<logic:notEmpty name="form" property="siguiente">
								<li>
								<a href="" onclick="setHref_pages('siguiente', '${ultimo.id}', '${form.tipoBusqueda}', '${form.tipoNavegacion}', '${form.siguiente}', '${form.idiomBusc}', '${form.cajaArribaAbierta}', '${form.cajaAbajoAbierta}', '${form.consultaRealizadaDesdeCajaArriba}')" id="dinamycLink_siguiente"/><bean:message key="listarODE.arbolCurricular.siguiente"/></a>
								<!-- Si descomentamos el enlace de abajo obtendremos un enlace de salto hacia delante (un fw) al grupo posterior de pagina -->
								<!-- 
								<a href="" onclick="setHref_pages('siguiente', '${ultimo.id}', '${form.tipoBusqueda}', '${form.tipoNavegacion}', '${pagina}', '${form.idiomBusc}', '${form.cajaArribaAbierta}', '${form.cajaAbajoAbierta}', '${form.consultaRealizadaDesdeCajaArriba}')" id="dinamycLink_siguiente"/><bean:message key="listarODE.arbolCurricular.siguiente"/></a>
								-->
								</li>
							</logic:notEmpty>
							
						</c:otherwise>
						</c:choose>
							
					</ul>
				</div>
			</c:if>
			<c:if test="${form.tipoBusqueda=='07' || form.tipoBusqueda=='08'}">
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
			</c:if>
			<!-- Fin CONTENIDO PESTANIAS -->
			<!-- Fin CONTENIDO PESTANIAS -->
			
			<!-- Inicio Boton de Eliminar  -->
			<!-- Inicio Boton de Eliminar  -->
			<!-- 
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
					<input type="hidden" name="areaCurricular" value="${form.areaCurricular}"/>
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
			-->
		</div>
	</article>


	<!-- INICIO BUSCADOR AGREGA 2 -->
	<article id="buscador_bottom">
		<section>
			<span class="enlace_f"><a href="<rewrite:rewrite url="${initParam.url_formularioAvanzado}"/>"><bean:message key="buscador.avanzado" /></a></span>
			<fieldset id="caja_buscador">
		  	<div>
				<input type="submit" alt="<bean:message key="buscador.boton.buscar.alt"/>" class="boton_submit" value="<bean:message key="buscador.buscador.boton"/>" name="buscar" />		
		   		<label for="busc_contenidos">Buscar Contenidos Agrega :</label>
				<c:choose>
				<c:when test="${!empty form.buscContenido}">
					<input type="text" id="buscadorGeneral_bis" autocomplete="off"  onkeyup="buscadorGeneral.value=this.value" class="buscador" value="${form.buscContenido}" title="Buscador" name="buscContenido"  />
				</c:when>
				<c:when test="${!empty form.keyword}">
					<input type="text" id="buscadorGeneral_bis" autocomplete="off"  onkeyup="buscadorGeneral.value=this.value" class="buscador" value="${form.keyword}" title="Buscador" name="buscContenido"  />
				</c:when>
				<c:otherwise>
					<!-- 
					<input type="text" id="buscadorGeneral_bis" autocomplete="off" onfocus="clearText(this)" onkeyup="buscadorGeneral.value=this.value" class="buscador" value="<bean:message key="buscador.textinput"/>" title="Buscador" name="buscContenido"  />
					-->
					<input type="text" id="buscadorGeneral_bis" autocomplete="off"  onkeyup="buscadorGeneral.value=this.value" class="buscador" value="" title="Buscador" name="buscContenido"  />
				</c:otherwise>
				</c:choose>
				<bean:define id="i18nValue"><bean:message key="buscador.idioma.por.defecto"/></bean:define>
				<label for="idioma"><bean:message key="buscador.idioma.del.contenido"/>:&nbsp;</label>
				<span class="caja_de_boton">
					<c:choose>
						<c:when test="${!empty form.idiomBuscBackingList}">
						   <html:select onchange="aplicarIdioma_bis();" name="form" property="idiomBusc" styleId="Idioma_bis" title="${i18nValue }" styleClass="select">
							 <html:optionsCollection name="form" property="idiomBuscBackingList" label="label" value="value"/>
						   </html:select>
						</c:when>
						<c:otherwise>
							<html:select name="form" property="idiomBusc" styleId="Idioma_bis" title="${i18nValue }" styleClass="select"/>
						</c:otherwise>
					</c:choose>
		  			</span>
			</div>
			</fieldset>
		
			<!--  tiles:insert definition="buscador-button" flush="false"/ -->
			</fieldset>
			<fieldset class="radios">
				<c:choose>
				<c:when test="${(empty form.tipoBusqueda) or (form.tipoBusqueda!='01' and form.tipoBusqueda!='02' and form.tipoBusqueda!='03' and form.tipoBusqueda!='05' and form.tipoBusqueda!='06')}">
					<label for="todo_agrega">
						<input type="radio" onclick="aplicarCambiosBusqueda_bis('01');" id="tipoBusqueda_bis" class="botonradio" value="01" name="tipoBusqueda_bis" checked="checked" />
					<bean:message key="buscador.radio.buscar.en.red" /></label>
					<label for="nodo_cca_aa">
						<input type="radio" onlick="aplicarCambiosBusqueda_bis('02');" id="tipoBusqueda_bis" class="botonradio" value="02" name="tipoBusqueda_bis" />
					<server:serverProperties property="ccaa"/></label>					
				</c:when>
				<c:otherwise>
					<label for="todo_agrega">
						<c:choose>
						<c:when test="${(!empty form.tipoBusqueda) and (form.tipoBusqueda=='01' or form.tipoBusqueda=='05')}">
							<input type="radio" onclick="aplicarCambiosBusqueda_bis('01');" id="tipoBusqueda_bis" class="botonradio" value="01" name="tipoBusqueda_bis" checked="checked" />	
						</c:when>
						<c:otherwise>
							<input type="radio" onclick="aplicarCambiosBusqueda_bis('01');" id="tipoBusqueda_bis" class="botonradio" value="01" name="tipoBusqueda_bis" />			
						</c:otherwise>
						</c:choose>
					<bean:message key="buscador.radio.buscar.en.red" /></label>
					<label for="nodo_cca_aa">
						<c:choose>
						<c:when test="${(!empty form.tipoBusqueda) and (form.tipoBusqueda=='02' or form.tipoBusqueda=='06')}">
							<input type="radio" onclick="aplicarCambiosBusqueda_bis('02');" id="tipoBusqueda_bis" class="botonradio" value="02" name="tipoBusqueda_bis" checked="checked" />			
						</c:when>
						<c:otherwise>
							<input type="radio" onclick="aplicarCambiosBusqueda_bis('02');" id="tipoBusqueda_bis" class="botonradio" value="02" name="tipoBusqueda_bis" />			
						</c:otherwise>
						</c:choose>
					<server:serverProperties property="ccaa"/></label>
					<c:choose>
					<c:when test="${(!empty form.tipoBusqueda) and (form.tipoBusqueda=='03')}">
						<label for="personalizada">
							<input type="radio" onclick="aplicarCambiosBusqueda_bis('03');" id="tipoBusqueda_bis" class="botonradio" value="03" name="tipoBusqueda_bis" checked="checked" />
						<bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.personalizada" /></label>		
					</c:when>
					</c:choose>
				</c:otherwise>
				</c:choose>
			</fieldset>				
	    </section>
	</article>
	<!-- FIN BUSCADOR AGREGA 2 -->

</form>


<br />
<br />





<SCRIPT LANGUAGE="JavaScript">
var host_and_subdomain="<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>";

//Parametros de una busqueda de tipo 01 y 02
var tipoBusqueda="${form.tipoBusqueda}";
var formato="${form.formato}";
var nivelAgreg="${form.nivelAgreg}";
var numeroResultados="${form.numeroResultados}";
var ocultar="<bean:message key='configurar.avanzado.ocultar2'/>";
var ver="<bean:message key='configurar.avanzado.ver2'/>";

//Parametros de una busqueda de tipo 05 y 06
var areaCurricularBusqueda="${form.areaCurricularBusqueda}";
var tipoNavegacion="${form.tipoNavegacion}";
var idiomBusc="${form.idiomBusc}";
var cajaArribaAbierta="${form.cajaArribaAbierta}";
var cajaAbajoAbierta="${form.cajaAbajoAbierta}";
var consultaRealizadaDesdeCajaArriba="${form.consultaRealizadaDesdeCajaArriba}";

//Lista de idiomas; util para sincronizar los dos selectores de idiomas		
<c:forEach items="${form.idiomBuscBackingList}" var="idioma">
	<fmt:formatNumber var="cont" value="1" type="number"/>
	var idioma${cont} = "${idioma.label}";
	<c:set var="cont" value="${cont+1}"/>
</c:forEach>




/////////////////////////////////////////////////////////////////////////
//Inicio del codigo que determina el modo de funcionamiento del filtro
//Si se marcan unas opciones se desmarcan otras y viceversa
//Dependiendo del tipo de busqueda se pasaran unos parametros u otros
//Algunos checkbox activan uno o varios input hidden
/////////////////////////////////////////////////////////////////////////

function selectAllCheckbox () {
	document.forms['Registration'].elements['todo'].checked = true;
	document.forms['Registration'].elements['Level01'].checked = true;
	document.forms['Registration'].elements['Nivel01'].value = "1";
	document.forms['Registration'].elements['Nivel02'].value = "2";
	document.forms['Registration'].elements['Nivel03'].checked = true;
	document.forms['Registration'].elements['Nivel04'].value = "4";
	document.forms['Registration'].elements['Formato01'].value = "application/*";
	document.forms['Registration'].elements['Formato02'].checked = true;
	document.forms['Registration'].elements['Formato03'].checked = true;
	document.forms['Registration'].elements['Formato04'].value = "text/*";
	document.forms['Registration'].elements['Formato05'].checked = true;
	document.forms['Registration'].elements['otros'].checked = true;
}


function initCheckboxs (tipoBusqueda) {

	if(tipoBusqueda=='01' || tipoBusqueda=='02' || tipoBusqueda=='05' || tipoBusqueda=='06') {

		var numFiltrosFormatosSeleccionados = 0;

		if ( (formato.indexOf("application") != -1) || (formato.indexOf("text") != -1) ) {
			document.forms['Registration'].elements['Formato01'].value = "application/*";
			document.forms['Registration'].elements['Formato04'].value = "text/*";
			document.forms['Registration'].elements['otros'].checked = true;
			numFiltrosFormatosSeleccionados++;
		} else {
			document.forms['Registration'].elements['Formato01'].value = "";	
			document.forms['Registration'].elements['Formato04'].value = "";
		}
		
		if (formato.indexOf("image") != -1) {
			document.forms['Registration'].elements['Formato02'].checked = true;
			numFiltrosFormatosSeleccionados++;
		}
		 
		if (formato.indexOf("audio") != -1) {
			document.forms['Registration'].elements['Formato03'].checked = true;
			numFiltrosFormatosSeleccionados++;
		} 
		
		if (formato.indexOf("video") != -1) {
			document.forms['Registration'].elements['Formato05'].checked = true;
			numFiltrosFormatosSeleccionados++;
		}
		
		if (nivelAgreg.indexOf("3") != -1) {
			document.forms['Registration'].elements['Nivel03'].checked = true;
			document.forms['Registration'].elements['Nivel02'].value = "2";
			document.forms['Registration'].elements['Nivel04'].value = "4";
		} 
		
		//primera carga de la pagina
		if (nivelAgreg=='' && numFiltrosFormatosSeleccionados==0) {
			selectAllCheckbox();
			return;
		}
		
		//Si hay activado algun filtro de formato pasamos el valor 1 del nivel de agregacion
		if (numFiltrosFormatosSeleccionados > 0) // (nivelAgreg.indexOf("1") != -1)
			document.forms['Registration'].elements['Nivel01'].value = "1";
		else
			document.forms['Registration'].elements['Nivel01'].value = "";
		
		//Si estan seleccionados todos los formatos de fichero, activamos el chebox 
		//del nivel de agregacion 1
		if (numFiltrosFormatosSeleccionados == 4) 
			document.forms['Registration'].elements['Level01'].checked = true;
		
		if (numFiltrosFormatosSeleccionados == 4 && nivelAgreg.indexOf("3") != -1)
			document.forms['Registration'].elements['todo'].checked = true;		
	}
}


initCheckboxs(tipoBusqueda);


function allCheckboxSelected () {
	if (document.forms['Registration'].elements['Level01'].checked == true &&
		document.forms['Registration'].elements['Nivel03'].checked == true &&
		document.forms['Registration'].elements['Formato02'].checked == true &&
		document.forms['Registration'].elements['Formato03'].checked == true &&
		document.forms['Registration'].elements['Formato05'].checked == true &&
		document.forms['Registration'].elements['otros'].checked == true)
		return true;
	else
		return false;
}


function allCheckboxDeselected () {
	if (document.forms['Registration'].elements['Level01'].checked == false &&
		document.forms['Registration'].elements['Nivel03'].checked == false &&
		document.forms['Registration'].elements['Formato02'].checked == false &&
		document.forms['Registration'].elements['Formato03'].checked == false &&
		document.forms['Registration'].elements['Formato05'].checked == false &&
		document.forms['Registration'].elements['otros'].checked == false)
		return true;
	else
		return false;
}


function allFormatCheckboxSelected () {
	if (document.forms['Registration'].elements['Formato02'].checked == true &&
		document.forms['Registration'].elements['Formato03'].checked == true &&
		document.forms['Registration'].elements['Formato05'].checked == true &&
		document.forms['Registration'].elements['otros'].checked == true) 
		return true;
	else 
		return false;
}


function anyFormatCheckboxSelected () {
	if (document.forms['Registration'].elements['Formato02'].checked == true ||
		document.forms['Registration'].elements['Formato03'].checked == true ||
		document.forms['Registration'].elements['Formato05'].checked == true ||
		document.forms['Registration'].elements['otros'].checked == true) 
		return true;
	else 
		return false;
}


//Checkea (o no) el checkbox de nivel de agregacion 1 segun el caso
function doAgregationLevel1Selection () {
	//Si algun formato de imagen, sonido, video y/u otros esta desmarcado el nivel de agregacion 1 lo desmarcamos
	if (allFormatCheckboxSelected() == true) 
		document.forms['Registration'].elements['Level01'].checked = true;
	else
		document.forms['Registration'].elements['Level01'].checked = false;
		
	if (anyFormatCheckboxSelected() == true) 
		document.forms['Registration'].elements['Nivel01'].value = "1";
	else
		document.forms['Registration'].elements['Nivel01'].value = "";
}


//Checkea (o no) todos los checkbox de formato segun el caso
function doAllFormatSelection () {

	//Caso de haber desmarcado nivel agregacion 1
	if(document.forms['Registration'].elements['Level01'].checked == false) {
		document.forms['Registration'].elements['Nivel01'].value = "";
		document.forms['Registration'].elements['Formato01'].value = "";
		document.forms['Registration'].elements['Formato02'].checked = false;
		document.forms['Registration'].elements['Formato03'].checked = false;
		document.forms['Registration'].elements['Formato04'].value = "";
		document.forms['Registration'].elements['Formato05'].checked = false;
		document.forms['Registration'].elements['otros'].checked = false;
		
	} else if(document.forms['Registration'].elements['Level01'].checked == true) {
		document.forms['Registration'].elements['Nivel01'].value = "1";
		document.forms['Registration'].elements['Formato01'].value = "application/*";
		document.forms['Registration'].elements['Formato02'].checked = true;
		document.forms['Registration'].elements['Formato03'].checked = true;
		document.forms['Registration'].elements['Formato04'].value = "text/*";
		document.forms['Registration'].elements['Formato05'].checked = true;
		document.forms['Registration'].elements['otros'].checked = true;
	}
}


function getSelectedCheckboxInputString () {
	var ret_string = "";
	
	if(document.forms['Registration'].elements['todo'].checked == true) {
		string = "&filtroFormato=image/*" +
				"&filtroFormato=audio/*" +
				"&filtroFormato=video/*" +
				"&filtroFormato=application/*" +
				"&filtroFormato=text/*" +
				"&filtroNivelAgregacion=1" +
				"&filtroNivelAgregacion=2" +
				"&filtroNivelAgregacion=3" +
				"&filtroNivelAgregacion=4";
		return ret_string;
	}
	
	if (document.forms['Registration'].elements['Level01'].checked == true)
		ret_string = ret_string + 
				"&filtroFormato=image/*" +
				"&filtroFormato=audio/*" +
				"&filtroFormato=video/*" +
				"&filtroFormato=application/*" +
				"&filtroFormato=text/*" +
				"&filtroNivelAgregacion=1";
				
	else if (anyFormatCheckboxSelected() == true) 
		ret_string = ret_string + "&filtroNivelAgregacion=1";
	
	if (document.forms['Registration'].elements['Nivel03'].checked == true)
		ret_string = ret_string + 
				"&filtroNivelAgregacion=2" +
				"&filtroNivelAgregacion=3" +
				"&filtroNivelAgregacion=4";
	
	if (document.forms['Registration'].elements['Formato02'].checked == true)
		ret_string = ret_string + "&filtroFormato=image/*";
	
	if (document.forms['Registration'].elements['Formato03'].checked == true)
		ret_string = ret_string + "&filtroFormato=audio/*";
		
	if (document.forms['Registration'].elements['Formato05'].checked == true)
		ret_string = ret_string + "&filtroFormato=video/*";
	
	if (document.forms['Registration'].elements['otros'].checked == true)
		ret_string = ret_string + "&filtroFormato=application/*" + "&filtroFormato=text/*";
	
	return ret_string;
}


function syncCheckboxsAndInput(checkboxMarcado){

	document.forms['Registration'].elements['todo'].checked = false;

	switch(checkboxMarcado) {
	
		case 'todo':
			selectAllCheckbox();
			break;
		
		case 'imagen_sonido_video':
			doAgregationLevel1Selection();
			break;
		
		case 'otros':
			doAgregationLevel1Selection();
			
			if (document.forms['Registration'].elements['Formato01'].value == "application/*")
				document.forms['Registration'].elements['Formato01'].value = "";
			else
				document.forms['Registration'].elements['Formato01'].value = "application/*";
			
			if (document.forms['Registration'].elements['Formato04'].value == "text/*")
				document.forms['Registration'].elements['Formato04'].value = "";
			else
				document.forms['Registration'].elements['Formato04'].value = "text/*";
			
			break;
			
		case '1':
			doAllFormatSelection();
			break;
			
		case '3':				
			if (document.forms['Registration'].elements['Nivel02'].value == "2")
				document.forms['Registration'].elements['Nivel02'].value = "";
			else 
				document.forms['Registration'].elements['Nivel02'].value = "2";
				
			if (document.forms['Registration'].elements['Nivel04'].value == "4")
				document.forms['Registration'].elements['Nivel04'].value = "";
			else 
				document.forms['Registration'].elements['Nivel04'].value = "4";
				
			break;
	}
	
	//Si esta todo desmarcado marcamos todo
	if(allCheckboxDeselected() == true) 
		selectAllCheckbox();
	else 
		//Si esta todo marcado marcamos opcion "todo"
		if(allCheckboxSelected() == true)
			document.forms['Registration'].elements['todo'].checked = true;
			
	//En caso de que la busqueda venga de arbol curricular (global o local)
	//sera necesario modificar los parametros de la url del submit para tener en cuenta los parametros
	//de arbol curricular en vez de los de una busqueda normal
	if(tipoBusqueda == '05' || tipoBusqueda == '06') {
		objF = document.forms['Registration'];
		objF.action = "<html:rewrite action="/BuscarAvanzadoCU/MostrarResultadosImagenesPaginarImagenes.do"/>?areaCurricularBusqueda=" + areaCurricularBusqueda + "&tipoBusqueda=" +tipoBusqueda + "&tipoNavegacion=" + tipoNavegacion + "&idiomBusc=" + idiomBusc + getSelectedCheckboxInputString() + "&cajaArribaAbierta=" + cajaArribaAbierta + "&cajaAbajoAbierta=" + cajaAbajoAbierta + "&consultaRealizadaDesdeCajaArriba=" + consultaRealizadaDesdeCajaArriba;
		objF.submit();
	}
}

/////////////////////////////////////////////////////////////////////////
//Fin del codigo que determina el modo de funcionamiento del filtro
/////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////
/// Funciones que controlan los hrefs
/// Estos hrefs son necesarios generarlos dinamica-
/// mente debido a que necesitan contener el valor
/// actual de depliegue de las cajas de la seccion
/// del arbol curricular; si se muestran
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

function getURL_pagesCajaArriba(ultimoID, tipoBusqueda, tipoNavegacion, pagina, idiomaBusc, cajaArribaAbierta, cajaAbajoAbierta, consultaRealizadaDesdeCajaArriba) {
	return "http://"+host_and_subdomain+"/buscador2/BuscarAvanzadoCU/MostrarResultadosImagenesPaginarImagenes.do?areaCurricularBusqueda="+ultimoID+"&tipoBusqueda="+tipoBusqueda+"&tipoNavegacion="+tipoNavegacion+"&busquedaArbol=true&pagina="+pagina+"&idiomBusc="+idiomBusc+"&cajaArribaAbierta="+cajaArribaAbierta+"&cajaAbajoAbierta="+cajaAbajoAbierta+"&consultaRealizadaDesdeCajaArriba="+consultaRealizadaDesdeCajaArriba+getSelectedCheckboxInputString();
}

function setHref_pagesCajaArriba(identificador, ultimoID, tipoBusqueda, tipoNavegacion, pagina, idiomaBusc, cajaArribaAbierta, cajaAbajoAbierta, consultaRealizadaDesdeCajaArriba) {
	var url = getURL_pagesCajaArriba(ultimoID, tipoBusqueda, tipoNavegacion, pagina, idiomaBusc, cajaArribaAbierta, cajaAbajoAbierta, consultaRealizadaDesdeCajaArriba);	
	changeLinkHref('dinamycLink_'+identificador, url);
}

function getURL_pages(ultimoID, tipoBusqueda, tipoNavegacion, pagina, idiomBusc, cajaArribaAbierta, cajaAbajoAbierta, consultaRealizadaDesdeCajaArriba) {
	return "http://"+host_and_subdomain+"/buscador2/BuscarAvanzadoCU/MostrarResultadosImagenesPaginarImagenes.do?areaCurricularBusqueda="+ultimoID+"&tipoBusqueda="+tipoBusqueda+"&tipoNavegacion="+tipoNavegacion+"&busquedaArbol=true&pagina="+pagina+"&ambitoBusquedaCombos="+tipoBusqueda+"&idiomBusc="+idiomBusc+"&lenguajeBusquedaCombos="+idiomBusc+"&cajaArribaAbierta="+cajaArribaAbierta+"&cajaAbajoAbierta="+cajaAbajoAbierta+"&consultaRealizadaDesdeCajaArriba="+consultaRealizadaDesdeCajaArriba+getSelectedCheckboxInputString();
}

function setHref_pages(identificador, ultimoID, tipoBusqueda, tipoNavegacion, pagina, idiomBusc, cajaArribaAbierta, cajaAbajoAbierta, consultaRealizadaDesdeCajaArriba) {
	var url = getURL_pages(ultimoID, tipoBusqueda, tipoNavegacion, pagina, idiomBusc, cajaArribaAbierta, cajaAbajoAbierta, consultaRealizadaDesdeCajaArriba);
	changeLinkHref('dinamycLink_'+identificador, url);
}

	
/////////////////////////////////////////////////////
/// Fin funciones que controlan los hrefs
/////////////////////////////////////////////////////	





if(tipoBusqueda=="04"){
	deshabilitarCombos();
}

/*
if(formato!='') {
	expandirCaja ('m4');
}
if(nivelAgreg!='') {
	expandirCaja ('m3');
}
*/

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


/*
function expandirCaja (i) {
	if (document.getElementById(i).className=='caja_abierta') {
		document.getElementById('p' + i).className = 'caja_cerrada';
		document.getElementById('d'+i).innerHTML=ver;
		document.getElementById(i).className = 'caja_cerrada';
	} else {
		document.getElementById('p' + i).className = 'caja_abierta';
		document.getElementById('d'+i).innerHTML=ocultar;
		document.getElementById(i).className = 'caja_abierta';
	}
}
*/




/////////////////////////////////////////////////////////////////////////
//Inicio del codigo que sirve para mantener sincronizadas las dos 
//cajas de busqueda (arriba y abajo) con sus preferencias
/////////////////////////////////////////////////////////////////////////

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

	
function aplicarCambiosBusqueda(tipoSearch){
/*		Comentado por que ya no se usa en el buscador de Agrega2

		if(tipoBusqueda == '04'){//busqueda en repositorios externos
			document.getElementById('Idioma').disabled=true;//desactivar combo idioma
		}
		else{
*/			document.getElementById('Idioma').disabled=false;//activar combo idioma
//		}
		document.getElementById('tipoBusqueda_bis').value=tipoSearch;
		setCheckedValue(document.forms['Registration'].elements['tipoBusqueda_bis'], tipoSearch);	
}

	
function aplicarCambiosBusqueda_bis(tipoSearch){
/*		Comentado por que ya no se usa en el buscador de Agrega2

		if(tipoBusqueda == '04'){//busqueda en repositorios externos
			document.getElementById('Idioma').disabled=true;//desactivar combo idioma
		}
		else{
*/			document.getElementById('Idioma').disabled=false;//activar combo idioma
//		}
		document.getElementById('tipoBusqueda').value=tipoSearch;
		setCheckedValue(document.forms['Registration'].elements['tipoBusqueda'], tipoSearch);	
}


function aplicarIdioma() {
	//Datos de la opcion seleccionada
	var indice = document.getElementById('Idioma').selectedIndex;
	var valor = document.getElementById('Idioma').options[indice].value;

	//Ajustamos el otro select de idioma  
	document.getElementById('Idioma_bis').selectedIndex=indice;
	document.getElementById('Idioma_bis').options[indice].value=valor;
	
	//var aa="${form.idiomBuscBackingList[indice]}"
	//alert("Has seleccionado el idioma "+aa);
}


function aplicarIdioma_bis() {
	//Datos de la opcion seleccionada
	var indice = document.getElementById('Idioma_bis').selectedIndex;
	var valor = document.getElementById('Idioma_bis').options[indice].value;

	//Ajustamos el otro select de idioma  
	document.getElementById('Idioma').selectedIndex=indice;
	document.getElementById('Idioma').options[indice].value=valor;
	
	//var aa="${form.idiomBuscBackingList[indice]}"
	//alert("Has seleccionado el idioma "+aa);
}

/////////////////////////////////////////////////////////////////////////
//Fin del codigo que sirve para mantener sincronizadas las dos 
//cajas de busqueda (arriba y abajo) con sus preferencias
/////////////////////////////////////////////////////////////////////////

//como cambiar el texto encerrado en un span
//http://www.webdeveloper.com/forum/showthread.php?t=84892


//		var num = "${ fn:length(form.resultadosVO)}";
		
//		alert("Se han obtenido -"+num+"- resultados");


</SCRIPT>

</tiles:put>
</tiles:insert>
