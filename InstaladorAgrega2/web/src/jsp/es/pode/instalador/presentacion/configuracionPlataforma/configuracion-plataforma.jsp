<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>

<?xml version="1.0" encoding="iso-8859-1" ?> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="es" xml:lang="es">


<head lang="es" dir="ltr">
	<meta name="description" content="Repositorio Educativo de la Comunidad Educativa Española" />
	<meta name="keywords" content="Agrega,Educación,Repositorio,Contenidos,SCORM2004,LOM" />
	<title>
  		<bean:message key="tittle.comun" />
	</title>
	<link rel="shortcut icon" href="/static/img/favicon.ico" />
	<link rel="stylesheet" media="screen" href="/InstaladorAgrega2-1.0/layout/red.css" type="text/css" />
</head>

<body>

<!--  CAPA QUE CONTIENE TODAS LAS DEMAS  -->
<!--  INICIO CAPA MADRE   -->
<div id="capa_madre" style="font-size: 100%;
    						max-width: 850px;">
<!-- AÑADIR MOTOR DE BUSQUEDA -->
		
<!--  INICIO CUERPO GENERAL   -->
<div id="cuerpo" style="
						display: block !important;
					    margin: 0 auto;
					    min-height: 10em;
					    padding: 0 !important;
					    text-align: left;
					    width: 800px !important;">
<div class="minwidth">
<div class="contenido_general">
	<!-- Aqui va la cabecera -->
	<div id="cabecera" style="    
								background: none repeat scroll 0 0 #fff;
							    height: 5.5em;
							    margin: 0 auto;
							    position: relative;
							    width: 960px !important;">
		<h1>
			<span>
				Red.es
			</span>
		</h1>
		<hr />
	</div>
	<hr />
	<br class="oculto" />
	<a href="/visualizadorcontenidos2/Portada/Portada.do" 
		title="P&aacute;gina de Inicio" 
		id="logo"
		style="  
			background-image: url('../img/logo_agrega_red.gif');
			background-repeat: no-repeat;
			background-attachment: scroll 0% 0% transparent;
			display: block;
		    height: 50px;
		    left: 26px;
		    position: absolute;
		    text-decoration: none;
		    top: 20px;
		    width: 131px;">
    	<span>P&aacute;gina de Inicio</span>
    </a>
	<br class="oculto" />
</div>

<!-- Inicio cuerpo central de pagina con Menu Principal, capa Lateral y capa Central  -->
<div class="tronco_de_contenido">
















<!-- Inicio MENU PRINCIPAL -->
<!-- Inicio MENU PRINCIPAL -->
<div id="madre_menu_horizontal">

<div id="cabecera_menu">
<div id="menu_principal0">
<div id="menu_principal">
<div id="menu_principal2">
	<ul>
		<li>
			<a href="/InstaladorAgrega2-1.0/InicioCU/InicioCU.do"  >
			<span><span>Inicio</span></span>
			</a>
		</li>
		<li>
			<div>
			<span><span><bean:message key="modificar.config.formulario.cabecera"/></span></span>
			</div>
		</li>
		<li>
			<a href="/InstaladorAgrega2-1.0/ActualizarPlataformaCU/ActualizarPlataformaCU.do"  >
			<span><span>Actualización</span></span>
			</a>
		</li>
	</ul>
</div>
</div>
</div>
</div>
</div>
<!-- Fin MENU PRINCIPAL -->
<!-- Fin MENU PRINCIPAL -->











<!-- FIN LATERAL IZQUIERDO -->


<!-- Inicio Contenido ESPECÍFICO  -->
<div id="contenido_central" style="
									margin-left: 25px;
								    margin-right: 1px;
								    padding: 0;
								    position: relative;">
















<!--  INICIO CAPA MADRE   -->
<div id="capa_madre">


<!-- Inicio plantilla contenido  -->
<div class="plantilla_contenido" style="
										margin-right: 30px;
									    padding-bottom: 6px;
									    padding-right: 0;">
									    
<!-- ## Para pintar los mensajes de error ## -->
	<jsp:include page="/layout/messages.jsp" flush="true" />
	
<div id="ficha_pestanias">
<div><h2><bean:message key="modificar.config.formulario.cabecera"/></h2></div>
	<ul>
		<c:forEach items="${form.listaCategorias}" var="categoria" varStatus="status">	
			<c:if test="${categoria==form.categoria}">
				<li id="pest_activa"><a id="seleccionada" href="/InstaladorAgrega2-1.0/ConfiguracionPlataformaCU/ConfiguracionPlataformaCU.do?categoria=${categoria}">${categoria}</a></li>
			</c:if>
			<c:if test="${categoria!=form.categoria}">
				<li><a href="/InstaladorAgrega2-1.0/ConfiguracionPlataformaCU/ConfiguracionPlataformaCU.do?categoria=${categoria}">${categoria}</a></li>
			</c:if>
		</c:forEach>
		
	</ul>
</div>

<div class="interno_ficha">
<div class="plantilla_contenido_pestanias" style="
												width:716px !important;     
												border-bottom-left-radius: 5px;
											    border-bottom-right-radius: 5px;
											    padding: 16px 21px 10px 7px !important;">
<fieldset>

<form method="post" action="<html:rewrite action="/ConfiguracionPlataformaCU/ConfiguracionPlataformaSubmit.do"/>">	


<input type="hidden" name="categoria" value="${form.categoria}"/>


<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03" style="
											padding:12px 0px 8px 0px !important;
											background: url('../img/azul/esq_blanco_bot_der.gif');
											background-image: url('../img/azul/esq_blanco_bot_der.gif');
											background-repeat: no-repeat;
											background-attachment: scroll 100% 100% transparent;">
				<!--  INICIO CAJA DE FORMULARIO   -->
				<div class="formu" style="
										margin: 0px 8px !important;
										display: block;
 										font-size: 70%;
 										width: auto;">
					
					<c:if test="${fn:length(form.listaPropiedades) < 1}" > 
						<p>No se han recibido propiedades que mostrar. Esto puede deberese a que no 
						está correctamente configurada la conexión con la base de datos, o que en la base de datos
						no hay ninguna propiedad registrada.</p><br>
					</c:if> 	
					<c:if test="${fn:length(form.listaPropiedades) > 0}" >
										
						<c:forEach items="${form.listaPropiedades}" var="propiedad" varStatus="status">
							
							<c:if test="${status.index==0}">
													<h3>${propiedad.subcategoria}</h3>
							</c:if>
							<c:if test="${status.index>0}">
								<fmt:formatNumber var="lastIndex" value="${status.index-1}" type="number"/>
								<c:if test="${form.listaPropiedades[lastIndex].subcategoria!=propiedad.subcategoria}">	
									
													<!--  FIN CAJA DE FORMULARIO   -->
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="globo_gris conmargen" style="margin-top:17px !important">
										<div class="globo_gris_01">
											<div class="globo_gris_02">
												<div class="globo_gris_03" style="
																				padding:12px 0px 8px 0px !important;
																				background: url('../img/azul/esq_blanco_bot_der.gif');
																				background-image: url('../img/azul/esq_blanco_bot_der.gif');
																				background-repeat: no-repeat;
																				background-attachment: scroll 100% 100% transparent;">
													<!--  INICIO CAJA DE FORMULARIO   -->
													<div class="formu" style="
																			margin: 0px 8px !important;
																			display: block;
						    												font-size: 70%;
						    												width: auto;">
														<h3>${propiedad.subcategoria}</h3>
								</c:if> 	
							</c:if>
							
							
							<!--  INICIO GLOBO BLANCO   -->
							<!--  INICIO GLOBO BLANCO   -->
							<div class="globo_blanco" >
								<div class="globo_blanco_01">
									<div class="globo_blanco_02">
										<div class="globo_blanco_03 tab_min" style="
																				padding:12px 0px 8px 0px !important; 
																				background-color:white !important;
																				background:#ffffff !important">
											<div class="formu" style="
																	margin: 0px 8px !important;
																	display: block;
				    												font-size: 70%;
				    												width: auto;">
												<table border="0" class="tab_miniaturas" cellpadding="0" cellspacing="0" >
												<tr >
												
													<td valign="top" class="opcion_tab_01" style="border:0px !important; padding:0px 0px 0px 0px !important;">
													
													<input type="hidden" name="nombresPropiedades" value="${propiedad.nombre}"/>
													<input type="hidden" name="instanciaJboss" value="${propiedad.instanciaJboss}"/>
													
													<c:if test="${propiedad.instanciaJboss!='global'}">	
														<strong>${propiedad.nombre} (${propiedad.instanciaJboss}):</strong>
													</c:if>
													<c:if test="${propiedad.instanciaJboss=='global'}">	
														<strong>${propiedad.nombre}:</strong>
													</c:if>
													<c:if test="${propiedad.descripcion!=''}">	
														${propiedad.descripcion}<br>
													</c:if> 
													<br>
													
													<c:if test="${propiedad.tipologia=='boolean'}">	
														<tr >
															<td valign="top" class="opcion_tab_01" style="border:0px !important; padding:0px 0px 0px 0px !important;">
															
																<c:choose> 
																	<c:when test="${form.listaNuevosValores[status.index].valor=='true' || form.listaNuevosValores[status.index].valor=='false'}">
																		<c:choose> 
																			<c:when test="${form.listaNuevosValores[status.index].valor=='true'}">	
																				<input type="radio" name="nuevosValores${status.index}" value="true" onclick="setCampoHidden('true', ${status.index});" checked="checked" id="nuevosValores${status.index}" style="border:0px !important;">
																				<label for="nuevosValores">Si (true)</label>
																				<input type="radio" name="nuevosValores${status.index}" value="false" onclick="setCampoHidden('false', ${status.index});" id="nuevosValores${status.index}" style="margin-left:15px; border:0px !important;">
																				<label for="nuevosValores">No (false)</label>
																				
																				<input type="hidden" name="nuevosValores" value="true"/>
																			</c:when>	
																			<c:when test="${form.listaNuevosValores[status.index].valor=='false'}">	
																				<input type="radio" name="nuevosValores${status.index}" value="true" onclick="setCampoHidden('true', ${status.index});" id="nuevosValores${status.index}" style="border:0px !important;">
																				<label for="nuevosValores">Si (true)</label>
																				<input type="radio" name="nuevosValores${status.index}" value="false" onclick="setCampoHidden('false', ${status.index});" checked="checked" id="nuevosValores${status.index}" style="margin-left:15px; border:0px !important;">
																				<label for="nuevosValores">No (false)</label>
																				
																				<input type="hidden" name="nuevosValores" value="false"/>
																			</c:when>	
																		</c:choose>
																	</c:when>	
																	<c:when test="${form.listaNuevosValores[status.index].valor=='SI' || form.listaNuevosValores[status.index].valor=='NO'}">
																		<c:choose> 
																			<c:when test="${form.listaNuevosValores[status.index].valor=='SI'}">	
																				<input type="radio" name="nuevosValores${status.index}" value="SI" onclick="setCampoHidden('SI', ${status.index});" checked="checked" id="nuevosValores${status.index}" style="border:0px !important;">
																				<label for="nuevosValores">Si (true)</label>
																				<input type="radio" name="nuevosValores${status.index}" value="NO" onclick="setCampoHidden('NO', ${status.index});" id="nuevosValores${status.index}" style="margin-left:15px; border:0px !important;">
																				<label for="nuevosValores">No (false)</label>
																				
																				<input type="hidden" name="nuevosValores" value="SI"/>
																			</c:when>	
																			<c:when test="${form.listaNuevosValores[status.index].valor=='NO'}">	
																				<input type="radio" name="nuevosValores${status.index}" value="SI" onclick="setCampoHidden('SI', ${status.index});" id="nuevosValores${status.index}" style="border:0px !important;">
																				<label for="nuevosValores">Si (true)</label>
																				<input type="radio" name="nuevosValores${status.index}" value="NO" onclick="setCampoHidden('NO', ${status.index});" checked="checked" id="nuevosValores${status.index}" style="margin-left:15px; border:0px !important;">
																				<label for="nuevosValores">No (false)</label>
																				
																				<input type="hidden" name="nuevosValores" value="NO"/>
																			</c:when>	
																		</c:choose>
																	</c:when>	
																	<c:when test="${form.listaNuevosValores[status.index].valor=='Si' || form.listaNuevosValores[status.index].valor=='No'}">
																		<c:choose> 
																			<c:when test="${form.listaNuevosValores[status.index].valor=='Si'}">	
																				<input type="radio" name="nuevosValores${status.index}" value="Si" onclick="setCampoHidden('Si', ${status.index});" checked="checked" id="nuevosValores${status.index}" style="border:0px !important;">
																				<label for="nuevosValores">Si (true)</label>
																				<input type="radio" name="nuevosValores${status.index}" value="No" onclick="setCampoHidden('No', ${status.index});" id="nuevosValores${status.index}" style="margin-left:15px; border:0px !important;">
																				<label for="nuevosValores">No (false)</label>
																				
																				<input type="hidden" name="nuevosValores" value="Si"/>
																			</c:when>	
																			<c:when test="${form.listaNuevosValores[status.index].valor=='No'}">	
																				<input type="radio" name="nuevosValores${status.index}" value="Si" onclick="setCampoHidden('Si', ${status.index});" id="nuevosValores${status.index}" style="border:0px !important;">
																				<label for="nuevosValores">Si (true)</label>
																				<input type="radio" name="nuevosValores${status.index}" value="No" onclick="setCampoHidden('No', ${status.index});" checked="checked" id="nuevosValores${status.index}" style="margin-left:15px; border:0px !important;">
																				<label for="nuevosValores">No (false)</label>
																				
																				<input type="hidden" name="nuevosValores" value="No"/>
																			</c:when>	
																		</c:choose>
																	</c:when>
																	<c:when test="${form.listaNuevosValores[status.index].valor=='si' || form.listaNuevosValores[status.index].valor=='no'}">
																		<c:choose> 
																			<c:when test="${form.listaNuevosValores[status.index].valor=='si'}">	
																				<input type="radio" name="nuevosValores${status.index}" value="si" onclick="setCampoHidden('si', ${status.index});" checked="checked" id="nuevosValores${status.index}" style="border:0px !important;">
																				<label for="nuevosValores">Si (true)</label>
																				<input type="radio" name="nuevosValores${status.index}" value="no" onclick="setCampoHidden('no', ${status.index});" id="nuevosValores${status.index}" style="margin-left:15px; border:0px !important;">
																				<label for="nuevosValores">No (false)</label>
																				
																				<input type="hidden" name="nuevosValores" value="si"/>
																			</c:when>	
																			<c:when test="${form.listaNuevosValores[status.index].valor=='no'}">	
																				<input type="radio" name="nuevosValores${status.index}" value="si" onclick="setCampoHidden('si', ${status.index});" id="nuevosValores${status.index}" style="border:0px !important;">
																				<label for="nuevosValores">Si (true)</label>
																				<input type="radio" name="nuevosValores${status.index}" value="no" onclick="setCampoHidden('no', ${status.index});" checked="checked" id="nuevosValores${status.index}" style="margin-left:15px; border:0px !important;">
																				<label for="nuevosValores">No (false)</label>
																				
																				<input type="hidden" name="nuevosValores" value="no"/>
																			</c:when>	
																		</c:choose>
																	</c:when>	
																	<c:otherwise>
																		<input type="radio" name="nuevosValores${status.index}" value="true" onclick="setCampoHidden('true', ${status.index});" id="nuevosValores${status.index}" style="border:0px !important;">
																		<label for="nuevosValores">Si (true)</label>
																		<input type="radio" name="nuevosValores${status.index}" value="false" onclick="setCampoHidden('false', ${status.index});" id="nuevosValores${status.index}" style="margin-left:15px; border:0px !important;">
																		<label for="nuevosValores">No (false)</label>
																				
																		<input type="hidden" name="nuevosValores" value=""/>
																	</c:otherwise>
																</c:choose>
																<br />	
																<c:if test="${propiedad.requiereReinicioJboss=='true'}">
															<br />	
															<strong><bean:message key="mensaje.requiereReinicio"/></strong><br>														
														<br>
													</c:if> 
															</td>
														</tr>
													</c:if> 
													<c:if test="${propiedad.tipologia!='boolean'}">	
														<c:if test="${propiedad.ejemplo!=''}">
															<strong>Ejemplo/s:</strong> <em>${propiedad.ejemplo}</em><br>
														</c:if> 
														<br>
														<label for="nuevosValores" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
														<input type="text" name="nuevosValores" value="${form.listaNuevosValores[status.index].valor}" id="nuevosValores" style="margin-bottom:0 !important;" title="${propiedad.nombre}" size="75">
														<br />	
																											<c:if test="${propiedad.requiereReinicioJboss=='true'}">
															<br />	
															<strong><bean:message key="mensaje.requiereReinicio"/></strong><br>														
														<br>
													</c:if>				
													</c:if> 
 
												</tr>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!--  FIN GLOBO BLANCO   -->
							<!--  FIN GLOBO BLANCO   -->
						
						
						</c:forEach>
					</c:if> 	
					
					
				<!--  FIN CAJA DE FORMULARIO   -->
				</div>
			</div>
		</div>
	</div>
</div>



<script languaje="javascript">
function setCampoHidden(valor, numeroCampo) {
    var vectorNuevosValores = document.getElementsByName('nuevosValores');
    var elem = vectorNuevosValores[numeroCampo];
    elem.value=valor;
}
</script>



<!-- Inicio Botones  -->
<!-- Inicio Botones  -->
<fieldset class="tipo ft_centrada">
<input class="boton_125"  type="submit"  value="<bean:message key="modificar.config.formulario.guardar"/>" />
</fieldset>
<!-- Fin Botones  -->
	
</form>

</fieldset>
</div>
</div>


</div>
<!-- Fin plantilla contenido  -->
<!-- Fin plantilla contenido  -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->


</div>
</div>
</div>
</div>
</div>
</div>

</body>
</html>
