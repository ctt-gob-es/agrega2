<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tags/valoracion.tld" prefix="estrellas" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tags/social.tld" prefix="social" %>
<%@ taglib uri="/WEB-INF/tags/formato.tld" prefix="format" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib uri="/WEB-INF/tags/googleAnalytic.tld" prefix="analytic" %>
<%@ taglib uri="/WEB-INF/tags/linkOaiOre.tld" prefix="linkOaiOre" %>
<%@ page import="es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils"%>

<script type="text/javascript" src="/static/js/addthis_widget.js"></script>
<script type="text/javascript">
	var addthis_config = {
           services_compact: 'facebook, twitter, google_plusone_share, pinterest_share, linkedin, more',
           services_exclude: 'print, email'
}
</script>


<tiles:insert definition="layout-detalleODE">

    <tiles:put name="title" type="string">
        <bean:message key="title.Comun"/>
    </tiles:put>
    
	<tiles:put name="codigo-head" type="String">
		<logic:notEmpty name="form" property="datosOaiOre">
			<linkOaiOre:linkOaiOre metadatoOaiOre="${form.datosOaiOre}"/>			
		</logic:notEmpty>		
	</tiles:put>
	
	<tiles:put name="body-principal" type="string">
	<%@ include file="/taglib-imports.jspf" %>

	<analytic:googleAnalytic />
		<jsp:include page="/layout/messages.jsp" flush="true" />


		<!-- estrellas (para puntuar al ode) -->
		<c:if test="${form.usuarioLogado || form.tieneIdentidadFederada}">
			<logic:notEqual name="form" property="estaValorado" value="true">
				<div class="valoracion" >
	        		<div>
					<ol>
						<!-- 
						<li class="rating-actual" style="width:60%;"><bean:message key="valorar.estrellas0"/></li>
						-->
						<li>
						<!-- 
							<a href="<html:rewrite action="/DetallarODECU/MostrarDetalleODEValorarODE.do"/>?identificadorODE=${form.identificadorODE}&amp;idioma=${form.idioma}&amp;mostrarVuelta=${form.mostrarVuelta}&amp;valoracion=1&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;nodoOrigen=${form.nodoOrigen}&amp;tieneIdentidadFederada=${form.tieneIdentidadFederada}" title="<bean:message key="valorar.estrellas1"/>" class="uno-estrella">1</a>
							<a href="<html:rewrite action="/DetallarODECU/MostrarDetalleODEValorarODE.do"/>?identificadorODE=${form.identificadorODE}&amp;idioma=${form.idioma}&amp;mostrarVuelta=${form.mostrarVuelta}&amp;valoracion=2&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;nodoOrigen=${form.nodoOrigen}&amp;tieneIdentidadFederada=${form.tieneIdentidadFederada}" title="<bean:message key="valorar.estrellas2"/>" class="dos-estrellas">2</a>
							<a href="<html:rewrite action="/DetallarODECU/MostrarDetalleODEValorarODE.do"/>?identificadorODE=${form.identificadorODE}&amp;idioma=${form.idioma}&amp;mostrarVuelta=${form.mostrarVuelta}&amp;valoracion=3&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;nodoOrigen=${form.nodoOrigen}&amp;tieneIdentidadFederada=${form.tieneIdentidadFederada}" title="<bean:message key="valorar.estrellas3"/>" class="tres-estrellas">3</a>
							<a href="<html:rewrite action="/DetallarODECU/MostrarDetalleODEValorarODE.do"/>?identificadorODE=${form.identificadorODE}&amp;idioma=${form.idioma}&amp;mostrarVuelta=${form.mostrarVuelta}&amp;valoracion=4&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;nodoOrigen=${form.nodoOrigen}&amp;tieneIdentidadFederada=${form.tieneIdentidadFederada}" title="<bean:message key="valorar.estrellas4"/>" class="cuatro-estrellas">4</a>
							<a href="<html:rewrite action="/DetallarODECU/MostrarDetalleODEValorarODE.do"/>?identificadorODE=${form.identificadorODE}&amp;idioma=${form.idioma}&amp;mostrarVuelta=${form.mostrarVuelta}&amp;valoracion=5&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;nodoOrigen=${form.nodoOrigen}&amp;tieneIdentidadFederada=${form.tieneIdentidadFederada}" title="<bean:message key="valorar.estrellas5"/>" class="cinco-estrellas">5</a>
						-->
						<estrellas:valoracion valoracion="${form.valoracion}" pagina="detallar" urlBaseParaVotar="/buscador2/DetallarODECU/MostrarDetalleODEValorarODE.do?identificadorODE=${form.identificadorODE}&amp;idioma=${form.idioma}&amp;mostrarVuelta=${form.mostrarVuelta}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;nodoOrigen=${form.nodoOrigen}&amp;tieneIdentidadFederada=${form.tieneIdentidadFederada}" mensaje="listar.odecu.mostrar.resultados.detalles.valoracion"/>
						</li>
					</ol>
					</div>
					<strong><bean:message key="valorar"/></strong>
				</div>
			</logic:notEqual>
		</c:if>
					
		
		<header>
			<c:if test="${form.licencias!=null&&form.licencias!=''}"> 
				<div class="licencias">						  
						<c:choose> 
							<c:when test="${form.idLicencia=='6.2.1'}">
								<span class="licencias_int" id="copyright" title="${form.licencias}" alt="${form.licencias}"></span>		
							</c:when>
							<c:when test="${form.idLicencia=='6.2.2'}">
								<span class="licencias_int" id="eupl" title="${form.licencias}" alt="${form.licencias}"></span>		
							</c:when>
							<c:when test="${form.idLicencia=='6.2.3'}">
								<span class="licencias_int" id="gpl" title="${form.licencias}" alt="${form.licencias}"></span>		
							</c:when>
							<c:when test="${form.idLicencia=='6.2.4'}">
								<span class="licencias_int" id="gpl_eupl" title="${form.licencias}" alt="${form.licencias}"></span>		
							</c:when>
							<c:when test="${form.idLicencia=='6.2.5'}">
								<span class="licencias_int" id="otras_lic_libres" title="${form.licencias}" alt="${form.licencias}"></span>		
							</c:when>
							<c:when test="${form.idLicencia=='6.2.6'}">
								<span class="licencias_int" id="cc_pd" title="${form.licencias}" alt="${form.licencias}"></span>		
							</c:when>
							<c:when test="${form.idLicencia=='6.2.7'}">
								<span class="licencias_int" id="no_corresponde" title="${form.licencias}" alt="${form.licencias}"></span>		
							</c:when>
							<c:when test="${form.idLicencia=='6.2.8'}">
								<span class="licencias_int" id="lic_prop_intelectual" title="${form.licencias}" alt="${form.licencias}"></span>		
							</c:when>
							<c:when test="${form.idLicencia=='6.2.9'}">
								<span class="licencias_int" id="cc_by" title="${form.licencias}" alt="${form.licencias}"></span>					
							</c:when>
							<c:when test="${form.idLicencia=='6.2.10'}">
								<span class="licencias_int" id="cc_by_nd" title="${form.licencias}" alt="${form.licencias}"></span>		
							</c:when>
							<c:when test="${form.idLicencia=='6.2.11'}">
								<span class="licencias_int" id="cc_by_nc_nd" title="${form.licencias}" alt="${form.licencias}"></span>			
							</c:when>
							<c:when test="${form.idLicencia=='6.2.12'}">
								<span class="licencias_int" id="cc_by_nc" title="${form.licencias}" alt="${form.licencias}"></span>			
							</c:when>
							<c:when test="${form.idLicencia=='6.2.13'}">
								<span class="licencias_int" id="cc_by_nc_sa" title="${form.licencias}" alt="${form.licencias}"></span>			
							</c:when>
							<c:when test="${form.idLicencia=='6.2.14'}">
								<span class="licencias_int" id="cc_by_sa" title="${form.licencias}" alt="${form.licencias}"></span>			
							</c:when>
							<c:when test="${form.idLicencia=='6.2.15'}">
								<span class="licencias_int" id="gfdl" title="${form.licencias}" alt="${form.licencias}"></span>			
							</c:when>
						</c:choose>
					<span class="cat">
					<strong >
					<c:if test="${form.gruposLicencia!=null}">   
						<c:forEach items="${form.gruposLicencia}" var="grupo" begin="0">
							<c:choose> 
								<c:when test="${grupo==1}">
									<em id="licencia_b" title="<bean:message key="grupoLicenciasA"/>" alt="<bean:message key="grupoLicenciasA"/>">&nbsp;<span>Categor&iacute;a A</span></em>					
								</c:when>	
								<c:when test="${grupo==2}">
									<em id="licencia_a" title="<bean:message key="grupoLicenciasB"/>" alt="<bean:message key="grupoLicenciasB"/>">&nbsp;<span>Categor&iacute;a B</span></em>				
								</c:when>	
								<c:when test="${grupo==3}">
									<em id="licencia_c" title="<bean:message key="grupoLicenciasC"/>" alt="<bean:message key="grupoLicenciasC"/>">&nbsp;<span>Categor&iacute;a C</span></em>				
								</c:when>
								<c:otherwise>
									<em>&nbsp;-<span >-</span></em>	
								</c:otherwise>
							</c:choose>				
						</c:forEach>
					</c:if>	
					</strong></span>
				</div>	
			</c:if>	
			<div id="categorias">
				<h2>${form.titulo}</h2>
				<!-- estrellas (puntuacion del ode) -->	
			    <div class="valoracion">
			        <div>
		       		<ol>
		    		<li>
			   		<estrellas:valoracion valoracion="${form.valoracion}" pagina="" urlBaseParaVotar="" mensaje="listar.odecu.mostrar.resultados.detalles.valoracion"/>
			   		</li>
		       		</ol>
			        </div>
			   		<strong><bean:message key="listar.odecu.mostrar.resultados.detalles.valoracion"/>:</strong>
			   	</div>	
		   	</div>	
		</header>
		
		<section class="clearfix salto">
		<p>
			<logic:notEmpty name="form" property="imagen">
				<a href="${form.imagenAmpliada}" target="_blank" class="imagenflotante" id="imt"><img  src="${form.imagen}" class="imagenflotante" alt="Imagen 02"/></a>
			</logic:notEmpty>
			<logic:empty name="form" property="imagen">
				<img  src="${form.imagen}" alt="Imagen 02" class="imagenflotante" id="imt" />
			</logic:empty>
			${form.descripcion}  
		</p>
		</section>
		
				
		<section class="valorame clearfix" >
		<ul id="res_com">
			<!-- 
			<li class="comentarios_fav"><a href="#" title="Destacados" ><span>Destacados</span></a></li>
			-->	
			
			<%
			String url = "http://" + LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio();
			%>	
											
			<SCRIPT LANGUAGE="JavaScript">	
									
			var direccion= '<%=url%>/${initParam.url_buscadorDetalleCorta}/${form.idioma}/${form.identificadorODE}'; 
			var titulo='<bean:message key="listar.odecu.mostrar.resultados.detalles.tituloFavoritos"/>';											
			if (navigator.userAgent.indexOf("Opera")!=-1) document.write("")
			else{ 
				if (navigator.appName == "Netscape") document.write("") 
			    else{											       
			        var titleLink='<bean:message key="listar.odecu.mostrar.resultados.detalles.datos.favoritos"/>';
			        var literalLink='<bean:message key="listar.odecu.mostrar.resultados.detalles.datos.favoritos"/>';
			       	document.write(' <li class="comentarios_fav">  ')													
						document.write(' <a href="javascript:window.external.AddFavorite(direccion, titulo)"');														
							document.write(' title="'+titleLink+'">  ');															
							document.write(' <span>'+literalLink+'</span>');															
						document.write(' </a>  ')														
					document.write(' </li>  ')														
				}
			}											
			</SCRIPT>	
			
			<!-- 
			<li class="comentarios_en"><a href="#" title="Comentarios" ><span>00 Comentarios</span></a></li>
			<li class="comentarios_an"><a href="#" title="Anadir" ><span>Comentarios</span></a></li>
			-->
			
			<logic:notEqual name="form" property="tipoLayoutBuscador" value="BUSCADOR">
				<c:choose> 
					<c:when test="${form.tipoLayoutBuscador=='FEDERADO' && (form.tieneIdentidadFederada || form.usuarioLogado)}">
						<li class="comentarios_an"><a href="<html:rewrite action="/IntroducirComentariosCU/IntroducirComentariosCU.do"/>?idODE=${form.identificadorODE}&amp;tituloODE=${form.titulo}&amp;imagen=${form.imagen}&amp;idiomaODE=${form.idioma}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;busquedaSimpleAvanzada=${form.busquedaSimpleAvanzada}&amp;mostrarVuelta=${form.mostrarVuelta}&amp;urlODE=${initParam.url_buscadorDetalleCorta}&amp;valoracion=${form.valoracion}&amp;nodoOrigen=${form.nodoOrigen}&amp;tieneIdentidadFederada=${form.tieneIdentidadFederada}"  title="<bean:message key="listar.odecu.mostrar.resultados.detalles.comentarios"/>"><span>${form.nrComentarios} <bean:message key="listar.odecu.mostrar.resultados.detalles.comentarios"/></span></a></li>													
					</c:when>	
					<c:otherwise>
						<logic:equal name="form" property="nrComentarios" value="0">															
							<li class="comentarios_an"><a title="<bean:message key="listar.odecu.mostrar.resultados.detalles.no.comentarios"/>"><span>${form.nrComentarios}<bean:message key="listar.odecu.mostrar.resultados.detalles.comentarios"/></span></a></li>													
						</logic:equal>
						<logic:notEqual name="form" property="nrComentarios" value="0">																			
							<li class="comentarios_an"><a href="<html:rewrite action="/ListarComentariosCU/ListarComentariosCU.do"/>?idODE=${form.identificadorODE}&amp;tituloODE=${form.titulo}&amp;imagen=${form.imagen}&amp;idiomaODE=${form.idioma}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;busquedaSimpleAvanzada=${form.busquedaSimpleAvanzada}&amp;mostrarVuelta=${form.mostrarVuelta}"  title="<bean:message key="listar.odecu.mostrar.resultados.detalles.comentarios"/>" ><span>${form.nrComentarios} <bean:message key="listar.odecu.mostrar.resultados.detalles.comentarios"/></span></a></li>													
						</logic:notEqual>
					</c:otherwise>
				</c:choose>
			</logic:notEqual>		
			
			<logic:equal name="form" property="tipoLayoutBuscador" value="BUSCADOR">														
				<logic:equal name="form" property="usuarioLogado" value="true">																				
					<li class="comentarios_an"><a href="<html:rewrite action="/IntroducirComentariosCU/IntroducirComentariosCU.do"/>?idODE=${form.identificadorODE}&amp;tituloODE=${form.titulo}&amp;imagen=${form.imagen}&amp;idiomaODE=${form.idioma}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;busquedaSimpleAvanzada=${form.busquedaSimpleAvanzada}&amp;mostrarVuelta=${form.mostrarVuelta}&amp;urlODE=${initParam.url_buscadorDetalleCorta}&amp;valoracion=${form.valoracion}"  title="<bean:message key="listar.odecu.mostrar.resultados.detalles.comentarios"/>"><span>${form.nrComentarios} <bean:message key="listar.odecu.mostrar.resultados.detalles.comentarios"/></span></a></li>													
				</logic:equal>
				<logic:equal name="form" property="usuarioLogado" value="false">	
					<logic:equal name="form" property="nrComentarios" value="0">															
						<li class="comentarios_an"><a title="<bean:message key="listar.odecu.mostrar.resultados.detalles.no.comentarios"/>"><span>${form.nrComentarios} <bean:message key="listar.odecu.mostrar.resultados.detalles.comentarios"/></span></a></li>													
					</logic:equal>
					<logic:notEqual name="form" property="nrComentarios" value="0">																			
						<li class="comentarios_an"><a href="<html:rewrite action="/ListarComentariosCU/ListarComentariosCU.do"/>?idODE=${form.identificadorODE}&amp;tituloODE=${form.titulo}&amp;imagen=${form.imagen}&amp;idiomaODE=${form.idioma}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;busquedaSimpleAvanzada=${form.busquedaSimpleAvanzada}&amp;mostrarVuelta=${form.mostrarVuelta}"  title="<bean:message key="listar.odecu.mostrar.resultados.detalles.comentarios"/>" ><span>${form.nrComentarios} <bean:message key="listar.odecu.mostrar.resultados.detalles.comentarios"/></span></a></li>													
					</logic:notEqual>
				</logic:equal>
			</logic:equal>
		
			<social:social url="/${initParam.url_buscadorDetalleCorta2}/${form.idioma}/${form.identificadorODE}" mostrarVuelta="${form.mostrarVuelta}" busquedaSimpleAvanzada="${form.busquedaSimpleAvanzada}" buscadorEmpaquetador="${form.tipoLayoutBuscador}" identificadorODE="${form.identificadorODE}" idioma="${form.idioma}" titulo="${form.titulo}" urlImagen="${form.imagen}" nodoOrigen="${form.nodoOrigen}" tieneIdentidadFederada="${form.tieneIdentidadFederada}"/>
			
			<!-- 
			<li class="enviar_objeto"><a href="#" title="Enviar" ><span>Enviar</span></a></li>
			-->
		</ul>
		</section>

		<section class="seccion clearfix" id="datos_basicos">
			<h3><bean:message key="listar.odecu.mostrar.resultados.detalles.datos.basicos"/></h3>  	
			<ul>
				<!-- FORMATO -->
				<li class="clearfix">
					<p id="formato">
						<strong class="tipo_label"><bean:message key="listar.odecu.mostrar.resultados.detalles.formato"/>:</strong>   
						<c:if test="${form.formato!=null && form.formato!=''}">
							<span><format:formato formato="${form.formato}" literalApli="listar.odecu.mostrar.resultados.consulta.vo.aplicacion" literalText="listar.odecu.mostrar.resultados.consulta.vo.texto" literalImag="listar.odecu.mostrar.resultados.consulta.vo.imagen" literalAud="listar.odecu.mostrar.resultados.consulta.vo.audio" literalVid="listar.odecu.mostrar.resultados.consulta.vo.video" /></span>  
						</c:if>
					</p>
					<!-- TAMANO  -->
					<p id="tamanio" class="lc">
						<strong class="tipo_label"><bean:message key="listar.odecu.mostrar.resultados.detalles.tamanio"/>:</strong>   
						<c:if test="${form.tamanio!=null && form.tamanio!=''}">
							<span>${form.tamanio} <bean:message key="listar.odecu.mostrar.resultados.detalles.tamanio.mb"/></span>  
						</c:if>		
					</p>
				</li>
				
				<!-- IDIOMA -->
				<li class="clearfix">
					<p id="idioma">
						<strong class="tipo_label"><bean:message key="listar.odecu.mostrar.resultados.detalles.idioma"/>:</strong>   
						<c:if test="${form.idiomaODE!=null && form.idiomaODE!=''}"> 
							<span>${form.idiomaODE}</span>  
						</c:if>		
					</p>							
					<!-- AMBITO -->
					<p class="lc" id="ambito">
						<strong class="tipo_label"><bean:message key="listar.odecu.mostrar.resultados.detalles.ambito"/>:</strong>   
						<c:if test="${form.ambito!=null && form.ambito!=''}">
							<span>${form.ambito}</span>  
						</c:if>		
					</p>
				</li>
				
				<!-- LICENCIAS -->							
				<li class="clearfix" >
					<p id="licencias">
						<strong class="tipo_label"><bean:message key="listar.odecu.mostrar.resultados.detalles.licencias"/>:</strong> 
						<c:if test="${form.licencias!=null&&form.licencias!=''}">   
							<span>${form.licencias}</span>  
						</c:if>	
					</p>
					<!-- DESTINATARIOS -->
					<p class="lc" id="destinatarios">
						<strong class="tipo_label"><bean:message key="listar.odecu.mostrar.resultados.detalles.destinatarios"/>:</strong> 
						<c:if test="${form.destinatarios!=null && form.destinatarios!=''}">
							<span>${form.destinatarios}</span>  
						</c:if>		
					</p>
				</li>
				
				
				<!-- TIPO RECURSO -->
				<li class="clearfix largo" >
					<p id="tipo">
						<strong class="tipo_label"><bean:message key="listar.odecu.mostrar.resultados.detalles.tipoRecurso"/>:</strong>  
						<c:if test="${form.tipoRecursos!=null&&form.tipoRecursos!=''}">
							<span>${form.tipoRecursos}</span>
						</c:if>		  
					</p>
				</li>
				</li>
				<!-- ORIENTACION DIDACTICA -->
				<li class="clearfix largo" >
					<p id="orientacion">
						<strong class="tipo_label"><bean:message key="listar.odecu.mostrar.resultados.detalles.orientacionDidactica"/>:</strong>
						<c:if test="${form.orientacionDidactica!=null&&form.orientacionDidactica!=''}">  
							<span>
								<!-- <fmt:formatNumber var="contItem" value="0" type="number"/> -->
								<c:forEach items="${form.orientacionDidactica}" var="orientacion" varStatus="status">
									<span>${orientacion}</span>
								</c:forEach>
							</span>  
						</c:if>		  
					</p>
				</li>
			
			</ul>
		</section>
				<!-- PALABRAS CLAVE -->
		<section class="seccion clearfix" id="keywords">
			<h3><bean:message key="listar.odecu.mostrar.resultados.detalles.palabrasClave"/></h3>
			<ul>
				<li class="clearfix largo" >
					<p id="p_clave">   <span>			
						<fmt:formatNumber var="contItem" value="1" type="number"/>
						<fmt:formatNumber var="total" value="${fn:length(form.palabrasClave)}" type="number"/>
						<c:forEach items="${form.palabrasClave}" var="palabrasClave" varStatus="status">
							<c:if test="${contItem<total}">	
								${palabrasClave},
							</c:if>
							<c:if test="${contItem==total}">	
								${palabrasClave}
							</c:if>
							<c:set var="contItem" value="${contItem+1}"/>
						</c:forEach>
					</span> </p>
				</li>
			</ul>
		</section>
		<!-- CONTRIBUCIONES -->
		<section class="seccion clearfix" id="niveles_agregacion">
			<a href="javascript:void(0)" class="a_search showhide2" id="toggleh1"><bean:message key="listar.odecu.mostrar.resultados.detalles.mostrarCampos"/></a> 
			<a href="javascript:void(0)" class="a_search showhide"  id="toggleh2"><bean:message key="listar.odecu.mostrar.resultados.detalles.ocultarCampos"/></a>  
			<h3 class="ieespecial"><bean:message key="listar.odecu.mostrar.resultados.detalles.datos.contribuciones"/></h3>
			<c:if test="${form.contribucion!=null}">
				<ul class="showhide">
					<c:forEach items="${form.contribucion}" var="contribucion" varStatus="status">
						<li class="clearfix">
							<p id="rol">
								<strong class="tipo_label"><bean:message key="listar.odecu.mostrar.resultados.detalles.datos.contribuciones.tipo"/>:</strong>    
								<c:if test="${contribucion.tipoContribucion!=null&&contribucion.tipoContribucion!=''}">
									<span>${contribucion.tipoContribucion}</span> 
								</c:if> 
							</p>
							<p class="lc" id="fecha">
								<strong class="tipo_label"><bean:message key="listar.odecu.mostrar.resultados.detalles.datos.contribuciones.fecha"/>:</strong>   
								<c:if test="${contribucion.fecha!=null && contribucion.fecha!=''}">
									<span>${contribucion.fecha}</span>  
								</c:if>
							</p>
						</li>

						<c:if test="${contribucion.entidades!=null}">
							<c:forEach items="${contribucion.entidades}" var="entidad" varStatus="status">
								<li class="clearfix largo" >
									<p id="nombre">
										<strong class="tipo_label"><bean:message key="listar.odecu.mostrar.resultados.detalles.datos.entidad.nombre"/>:</strong>    
										<c:if test="${entidad.nombre!=null && entidad.nombre!=''}">
											<span>${entidad.nombre}</span>  
										</c:if>
									</p>
								</li>
								<li class="clearfix largo" >
									<p id="org">
										<strong class="tipo_label"><bean:message key="listar.odecu.mostrar.resultados.detalles.datos.entidad.organizacion"/>:</strong>    
										<c:if test="${entidad.organizacion!=null && entidad.organizacion!=''}">
											<span>${entidad.organizacion}</span>  
										</c:if>
									</p>
								</li>
							</c:forEach>
						</c:if>
					</c:forEach>
				</ul>
			</c:if>
		</section>

		<!-- ETIQUETAS (PALABRAS CLAVE) -->
		<section class="seccion clearfix" id="keywords">
			<h3><bean:message key="listar.odecu.mostrar.resultados.detalles.datos.etiquetas"/></h3>
			<ul>
				<li class="clearfix largo" >
				<p id="f_etiquetas"><span>
				<c:forEach items="${form.etiquetas}" var="etiqueta" varStatus="status">
						<bean:define id="tag" ><c:out value="${etiqueta}" escapeXml="true" default="" /></bean:define>
							<a href="<rewrite:rewrite url="TaggingWeb/ListarAgregaTags/ListadoMostrarOdesAgrega.do"/>?tag=${tag }">${etiqueta }</a>
				</c:forEach>
				</span></p>
				</li>
			</ul>
		</section>
								
		<!-- ESTADISTICAS -->
		<section class="seccion clearfix" id="f_estadisticas">
		<h3><bean:message key="listar.odecu.mostrar.resultados.detalles.datos.estadisticas"/></h3>
		<ul>
			<!-- -->
			<li class="clearfix" >
			<p id="previsualizado"> 
				<strong class="tipo_label"><bean:message key="listar.odecu.mostrar.resultados.detalles.previsualizado"/>:</strong>    
				<c:if test="${form.numVecesPrevisualizado!=null && form.numVecesPrevisualizado!=''}">  
					<span>${form.numVecesPrevisualizado}</span> 
				</c:if>
			</p>
			<p id="consultado"> 
				<strong class="tipo_label"><bean:message key="listar.odecu.mostrar.resultados.detalles.visto"/>:</strong>    
				<c:if test="${form.numVecesVisto!=null && form.numVecesVisto!=''}">   
					<span>${form.numVecesVisto}</span> 
				</c:if>
			</p>
			<p id="descargado"> 
				<strong class="tipo_label"><bean:message key="listar.odecu.mostrar.resultados.detalles.descargado"/>:</strong>   
				<c:if test="${form.numVecesDescargado!=null && form.numVecesDescargado!=null}">   
					<span>${form.numVecesDescargado}</span> 
				</c:if>
			</p>
			<p id="enviado"> 
				<strong class="tipo_label"><bean:message key="listar.odecu.mostrar.resultados.detalles.enviado"/>:</strong>   
				<c:if test="${form.numVecesEnviado!=null && form.numVecesEnviado!=null}">    
					<span>${form.numVecesEnviado}</span> 
				</c:if>
			</p>
			<p id="embed" class="lc"> 
				<strong class="tipo_label"><bean:message key="listar.odecu.mostrar.resultados.detalles.embed"/>:</strong>     
				<c:if test="${form.numVecesEmbed!=null && form.numVecesEmbed!=null}">  
					<span>${form.numVecesEmbed}</span>
				</c:if>
			</p>
			</li>
			<!-- -->
		</ul>
		</section>

		<!-- OBJETOS RELACIONADOS -->
		<logic:equal name="form" property="existenOdesRelacionados" value="true">
			<section class="seccion clearfix" id="relacionados">
				<h3><bean:message key="listar.odecu.mostrar.resultados.detalles.odesRelacionados"/></h3>
				<ul>
					<li class="clearfix" >
						<fmt:formatNumber var="contItem" value="1" type="number"/>
						<logic:iterate name="form" property="odesRelacionados" id="resultados">
						
							<c:choose> 
								<c:when test="${contItem mod 3 == 0}">	
									<p class="lc">
								</c:when>	
								<c:otherwise>
									<p>
								</c:otherwise>
							</c:choose>
							
							<!-- 
								<a href="<html:rewrite action="/DetallarODECU/MostrarDetalleODEDetalleOdeRelacionado"/>?idioma=${form.idioma}&amp;identificadorODE=${resultados.id}&amp;tieneIdentidadFederada=${form.tieneIdentidadFederada}&amp;nodoOrigen=${form.nodoOrigen}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;posicionamientoAnterior=&amp;posicionamientoSiguiente="/>
							-->
									<img  src="${resultados.urlImagen}" class="imagenflotante" alt="Imagen 02"/>
							<!-- 
								</a>
							-->
								<span>
									<a href="<html:rewrite action="/DetallarODECU/MostrarDetalleODEDetalleOdeRelacionado"/>?idioma=${form.idioma}&amp;identificadorODE=${resultados.id}&amp;tieneIdentidadFederada=${form.tieneIdentidadFederada}&amp;nodoOrigen=${form.nodoOrigen}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;posicionamientoAnterior=&amp;posicionamientoSiguiente=">
										<c:choose>
											<c:when test="${(fn:length(resultados.titulo)) < 17}">
												${resultados.titulo}
											</c:when>
											<c:otherwise>
												${fn:substring(resultados.titulo,0,12)}...
											</c:otherwise>
										</c:choose>
									</a>
									<!-- <br />00 Mb <em> Autor: XYZ</em><br />-->
									<a target="_blank" class="peq" href="<html:rewrite action="/BuscarAvanzadoCU/MostrarResultadosImagenesPrevisualizarODE"/>?idioma=${form.idioma}&amp;identificadorODE=${resultados.id}&amp;nodoDestino=${resultados.nodo}&amp;titulo=${resultados.titulo}&amp;seleccionarSecuencia=${resultados.conSecuencia}&amp;tieneIdentidadFederada=${form.tieneIdentidadFederada}&amp;nodoOrigen=${form.nodoOrigen}"><bean:message key="listar.odecu.mostrar.resultados.detalles.previsualizar"/></a>					
									<span class="valoracion_sola">			
										<estrellas:valoracion valoracion="${resultados.valoracion}" pagina="" urlBaseParaVotar="" mensaje="listar.odecu.mostrar.resultados.detalles.valoracion"/>
									</span>
								</span>
							</p>
							
							<c:choose> 
								<c:when test="${contItem mod 3 == 0}">	
									<br>
								</c:when>	
							</c:choose>
							
							<c:set var="contItem" value="${contItem+1}"/>
						</logic:iterate>
					</li>	
				</ul>
			</section>
		</logic:equal>
							 															
<!-- Nuevos Botones -->
<!-- 
<div>	
<a class="boton" style="float: center ! important; margin-right: 15px; ; width: 160px;" id="b_previsualizar" href="<html:rewrite action="/DescargarODECU/DescargarODECU.do"/>?identificadorODE=${form.identificadorODE}&amp;idioma=${form.idioma}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;mostrarVuelta=${form.mostrarVuelta}&amp;areaCurricularBusqueda=&amp;tieneIdentidadFederada=${form.tieneIdentidadFederada}&amp;nodoOrigen=${form.nodoOrigen}">
<bean:message key="listar.odecu.mostrar.resultados.detalles.descargar"/></a>					
<a class="boton fl" style="float: right ! important; margin-right: 15px; ; width: 160px;" id="b_previsualizar" target="_blank" href="<html:rewrite action="/DetallarODECU/MostrarDetalleODEPrevisualizar.do"/>?identificadorODE=${form.identificadorODE}&amp;seleccionarSecuencia=${form.seleccionarSecuencia}&amp;idioma=${form.idioma}&amp;tieneIdentidadFederada=${form.tieneIdentidadFederada}&amp;nodoOrigen=${form.nodoOrigen}">
<bean:message key="listar.odecu.mostrar.resultados.detalles.previsualizar"/></a>
<a class="boton" style="float: left ! important; margin-right: 15px; width: 160px;" id="b_previsualizar" href="javascript:history.back(1)">
<bean:message key="listar.odecu.mostrar.resultados.detalles.volver"/></a>		
</div>
 -->
<!-- Fin Nuevos Botones -->

<!--  -->
	<form>
		<fieldset class="botonera_de_tres clearfix">
			<a class="boton " href="<html:rewrite action="/DescargarODECU/DescargarODECU.do"/>?identificadorODE=${form.identificadorODE}&amp;idioma=${form.idioma}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;mostrarVuelta=${form.mostrarVuelta}&amp;areaCurricularBusqueda=&amp;tieneIdentidadFederada=${form.tieneIdentidadFederada}&amp;nodoOrigen=${form.nodoOrigen}&amp;idLicencia=${form.idLicencia}"><bean:message key="listar.odecu.mostrar.resultados.detalles.descargar"/></a>
			<a class="boton" href="<html:rewrite action="/DetallarODECU/MostrarDetalleODEPrevisualizar.do"/>?identificadorODE=${form.identificadorODE}&amp;seleccionarSecuencia=${form.seleccionarSecuencia}&amp;idioma=${form.idioma}&amp;tieneIdentidadFederada=${form.tieneIdentidadFederada}&amp;nodoOrigen=${form.nodoOrigen}" target="_blank"><bean:message key="listar.odecu.mostrar.resultados.detalles.previsualizar"/></a>
			<a id="primero" class="boton " href="javascript:history.back(1)"><bean:message key="listar.odecu.mostrar.resultados.detalles.volver"/></a>
		</fieldset>
	</form>

	<SCRIPT LANGUAGE="JavaScript">
	var ocultar="<bean:message key='configurar.avanzado.ocultar2'/>";
	var ver="<bean:message key='configurar.avanzado.ver2'/>";
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
	
	<!-- Script para agrega 2 -->
	<script>
	 $(document).ready(function() {
	   $('#hideh1').click( function() {
	    $('.showhide').hide();
	    
	   });
	   $('#toggleh1').click( function() {
	    $('.showhide').toggle();
	    $('.showhide2').hide();
	   });
	   
	   $('#toggleh2').click( function() {
	    $('.showhide2').toggle();
	    $('.showhide').hide();
	   });
	
	});
	</script>


  </tiles:put>
</tiles:insert>