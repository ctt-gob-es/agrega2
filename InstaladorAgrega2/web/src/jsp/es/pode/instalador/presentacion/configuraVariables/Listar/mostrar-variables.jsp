<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<?xml version="1.0" encoding="iso-8859-1" ?> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="es" xml:lang="es">


<head lang="es" dir="ltr">
	<meta name="description" content="Repositorio Educativo de la Comunidad Educativa Española" />
	<meta name="keywords" content="Agrega,Educación,Repositorio,Contenidos,SCORM2004,LOM" />
	<title>
		Agrega 2 - Instalaci&oacute;n
	</title>
	<link rel="shortcut icon" href="/static/img/favicon.ico" />
	<link rel="stylesheet" media="screen" href="/InstaladorAgrega2-1.0/layout/red.css" type="text/css" />
</head>

<body>

<!--  CAPA QUE CONTIENE TODAS LAS DEMAS  -->
<!--  INICIO CAPA MADRE   -->
<div id="capa_madre">
<!-- AÑADIR MOTOR DE BUSQUEDA -->
		
<!--  INICIO CUERPO GENERAL   -->
<div id="cuerpo">
<div class="minwidth">
<div class="contenido_general">
	<!-- Aqui va la cabecera -->
	<div id="cabecera">
		<h1>
			<span>
				Red.es
			</span>
		</h1>
		<hr />
	</div>
	<hr />
	<!-- FIN BUSCADOR  -->
	<br class="oculto" />
	<a href="/visualizadorcontenidos/Portada/Portada.do" title="P&aacute;gina de Inicio" id="logo"><span>P&aacute;gina de Inicio</span></a>
	<br class="oculto" />
</div>

<!-- Inicio cuerpo central de pagina con Menu Principal, capa Lateral y capa Central  -->
<div class="tronco_de_contenido">
<!-- Menu de cabecera -->


<!-- Inicio MENU PRINCIPAL -->
<!-- Inicio MENU PRINCIPAL -->
<div id="madre_menu_horizontal">
	<div id="cabecera_menu">
		<div id="menu_principal0">
			<div id="menu_principal">
				<div id="menu_principal2">
				<ul>
					<li id="portada">
						<a href="/InstaladorAgrega2-1.0/ListarVariablesCU/ListarVariablesCU.do"  >
						<span><span>INICIO</span></span>
						</a>
					</li>
					<li id="arbol">
						<div>
						<span><span>CONSULTAR CONFIGURACIÓN</span></span>
						</div>
					</li>
				</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Fin MENU PRINCIPAL -->
<!-- Fin MENU PRINCIPAL -->



<!-- Inicio Contenido ESPECÍFICO  -->
<div id="contenido_central">

<!--  INICIO CAPA MADRE   -->
<div id="capa_madre">

<!-- Inicio plantilla contenido  -->
<div class="plantilla_contenido">
<!-- ## Para pintar los mensajes de error ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />
	

    
<h2>Configuración</h2>

<form method="post" action="/InstaladorAgrega2-1.0/ListarVariablesCU/MostrarVariablesModificar.do" >


<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			
				<!--  INICIO CAJA DE FORMULARIO   -->
				<div class="formu">
					<h3>Variables a configurar del JBoss ${form.tipoJboss}</h3>

					<c:if test="${fn:length(form.listaVariables) < 1}" > 
						<p>No se han recibido variables que mostrar. Esto puede deberese a que no 
						está correctamente configurada la conexión con la base de datos, o que en la base de datos
						no hay ninguna variable registrada.</p><br>
					</c:if> 	
					<c:if test="${fn:length(form.listaVariables) > 0}" >
					
						<input type="hidden" name="tipoJboss" value="${form.tipoJboss}">
						<input type="hidden" name="numJbossDisponibles" value="${form.numJbossDisponibles}">
						
						<c:forEach items="${form.listaVariables}" var="variable" varStatus="status">	
						
							<!--  INICIO GLOBO BLANCO   -->
							<!--  INICIO GLOBO BLANCO   -->
							<c:if test="${variable.nombre!='server_path'}">
							
							<div class="globo_blanco" >
								<div class="globo_blanco_01">
									<div class="globo_blanco_02">
										<div class="globo_blanco_03 tab_min">
											<div class="formu" >
												<table border="0" class="tab_miniaturas" cellpadding="0" cellspacing="0" >
												<tr >
													<td valign="top" class="opcion_tab_01">
													<h3>${variable.nombre}</h3>
													<c:if test="${variable.descripcion!=''}">	
														${variable.descripcion}.<br>
													</c:if> 
													<br>
													<c:choose> 
													
														<c:when test="${variable.nombre=='casurl'}">			

																<c:if test="${variable.ejemplo!=''}">	
																	<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
																</c:if> 
																<br>
																<label for="casurl" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
																<input type="text" name="casurl" value="${form.casurl}" id="casurl" style="margin-bottom:0 !important;" title="${variable.nombre}" size="150"><br />

														</c:when>	
														
														<c:when test="${variable.nombre=='ccaa'}">
															
																<c:if test="${variable.ejemplo!=''}">	
																	<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
																</c:if> 
																<br>
																<label for="ccaa" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
																<input type="text" name="ccaa" value="${form.ccaa}" id="ccaa" style="margin-bottom:0 !important;" title="${variable.nombre}" size="150"><br />
															
														</c:when>	
														
														<c:when test="${variable.nombre=='server_id'}">
															
																<c:if test="${variable.ejemplo!=''}">	
																	<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
																</c:if> 
																<br>
																<label for="idnodo" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
																<input type="text" name="idnodo" value="${form.idnodo}" id="idnodo" style="margin-bottom:0 !important;" title="${variable.nombre}" size="150"><br />
															
														</c:when>	
														
														<c:when test="${variable.nombre=='imgserverhost'}">
															<c:if test="${variable.ejemplo!=''}">	
																<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
															</c:if> 
															<br>
															<label for="imgserverhost" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
															<input type="text" name="imgserverhost" value="${form.imgserverhost}" id="imgserverhost" style="margin-bottom:0 !important;" title="${variable.nombre}" size="150"><br />
														</c:when>	
														
														<c:when test="${variable.nombre=='imgserverport'}">
															<c:if test="${variable.ejemplo!=''}">	
																<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
															</c:if> 
															<br>
															<label for="imgserverport" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
															<input type="text" name="imgserverport" value="${form.imgserverport}" id="imgserverport" style="margin-bottom:0 !important;" title="${variable.nombre}" size="6" maxlength="6"><br />
														</c:when>	
														
														<c:when test="${variable.nombre=='JBOSS_HOME'}">
															<c:if test="${variable.ejemplo!=''}">	
																<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
															</c:if> 
															<br>
															<label for="JBOSS_HOME" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
															<input type="text" name="JBOSS_HOME" value="${form.JBOSS_HOME}" id="JBOSS_HOME" style="margin-bottom:0 !important;" title="${variable.nombre}" size="150"><br />
														</c:when>	
														
														<c:when test="${variable.nombre=='ldap_external_admin'}">
															
																<c:if test="${variable.ejemplo!=''}">	
																	<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
																</c:if> 
																<br>
																<label for="ldapadmin" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
																<input type="text" name="ldapadmin" value="${form.ldapadmin}" id="ldapadmin" style="margin-bottom:0 !important;" title="${variable.nombre}" size="150"><br />
															
														</c:when>	
														
														<c:when test="${variable.nombre=='ldap_external'}">		
<tr >
	<td valign="top" class="opcion_tab_01">
		<c:choose> 
			<c:when test="${form.ldapexternal=='true'}">	
				<input type="radio" name="ldapexternal" value="true" checked="checked" id="ldapexternal">
				<label for="ldapexternal">Si</label>
				<input type="radio" name="ldapexternal" value="false" id="ldapexternal" style="margin-left:15px;">
				<label for="ldapexternal">No</label>
			</c:when>	
			<c:when test="${form.ldapexternal=='false'}">	
				<input type="radio" name="ldapexternal" value="true" id="ldapexternal">
				<label for="ldapexternal">Si</label>
				<input type="radio" name="ldapexternal" value="false" checked="checked" id="ldapexternal" style="margin-left:15px;">
				<label for="ldapexternal">No</label>
			</c:when>	
			<c:otherwise>
				<input type="radio" name="ldapexternal" value="true" id="ldapexternal">
				<label for="ldapexternal">Si</label>
				<input type="radio" name="ldapexternal" value="false" id="ldapexternal" style="margin-left:15px;">
				<label for="ldapexternal">No</label>
			</c:otherwise>
		</c:choose>
	</td>
</tr>
														</c:when>	
														
														<c:when test="${variable.nombre=='ldapmanagerDN'}">
															
																<c:if test="${variable.ejemplo!=''}">
																	<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
																</c:if> 
																<br>
																<label for="ldapmanagerDN" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
																<input type="text" name="ldapmanagerDN" value="${form.ldapmanagerDN}" id="ldapmanagerDN" style="margin-bottom:0 !important;" title="${variable.nombre}" size="150"><br />
															
														</c:when>	
														
														<c:when test="${variable.nombre=='ldapmanagerPasswd'}">
															
																<label for="ldapmanagerPasswd" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
																<input type="text" name="ldapmanagerPasswd" value="${form.ldapmanagerPasswd}" id="ldapmanagerPasswd" style="margin-bottom:0 !important;" title="${variable.nombre}" size="150"><br />
															
														</c:when>	
														
														<c:when test="${variable.nombre=='ldappath'}">
															
																<c:if test="${variable.ejemplo!=''}">	
																	<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
																</c:if> 
																<br>
																<label for="ldappath" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
																<input type="text" name="ldappath" value="${form.ldappath}" id="ldappath" style="margin-bottom:0 !important;" title="${variable.nombre}" size="150"><br />
															
														</c:when>	
														
														<c:when test="${variable.nombre=='ldapurl'}">
															
																<c:if test="${variable.ejemplo!=''}">	
																	<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
																</c:if> 
																<br>
																<label for="ldapurl" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
																<input type="text" name="ldapurl" value="${form.ldapurl}" id="ldapurl" style="margin-bottom:0 !important;" title="${variable.nombre}" size="150"><br />
															
														</c:when>	
														
														<c:when test="${variable.nombre=='ldapusuariosbindsubpath'}">
															
																<c:if test="${variable.ejemplo!=''}">	
																	<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
																</c:if> 
																<br>
																<label for="ldapusuariosbindsubpath" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
																<input type="text" name="ldapusuariosbindsubpath" value="${form.ldapusuariosbindsubpath}" id="ldapusuariosbindsubpath" style="margin-bottom:0 !important;" title="${variable.nombre}" size="150"><br />
															
														</c:when>	
														
														<c:when test="${variable.nombre=='contacto_mail'}">
															
																<c:if test="${variable.ejemplo!=''}">
																	<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
																</c:if> 
																<br>
																<label for="mailContacto" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
																<input type="text" name="mailContacto" value="${form.mailContacto}" id="mailContacto" style="margin-bottom:0 !important;" title="${variable.nombre}" size="150"><br />
															
														</c:when>	
														
														<c:when test="${variable.nombre=='host'}">
															<c:if test="${variable.ejemplo!=''}">
																<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
															</c:if> 
															<br>
															<label for="nodo" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
															<input type="text" name="nodo" value="${form.nodo}" id="nodo" style="margin-bottom:0 !important;" title="${variable.nombre}" size="150"><br />
														</c:when>	
														
														<c:when test="${variable.nombre=='nodo_jboss'}">
															<c:if test="${variable.ejemplo!=''}">
																<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
															</c:if> 
															<br>
															<label for="nodo_jboss" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
															<input type="text" name="nodo_jboss" value="${form.nodo_jboss}" id="nodo_jboss" style="margin-bottom:0 !important;" title="${variable.nombre}" size="150"><br />
														</c:when>	
														
														<c:when test="${variable.nombre=='puerto'}">
															<c:if test="${variable.ejemplo!=''}">	
																<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
															</c:if> 
															<br>
															<label for="port" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
															<input type="text" name="port" value="${form.port}" id="port" style="margin-bottom:0 !important;" title="${variable.nombre}" size="6" maxlength="6"><br />
														</c:when>	
														
														<c:when test="${variable.nombre=='puerto_jboss'}">
															<c:if test="${variable.ejemplo!=''}">
																<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
															</c:if> 
															<br>
															<label for="port_jboss" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
															<input type="text" name="port_jboss" value="${form.port_jboss}" id="port_jboss" style="margin-bottom:0 !important;" title="${variable.nombre}" size="6" maxlength="6"><br />
														</c:when>	
														
														<c:when test="${variable.nombre=='nodo'}">
															<select name="prefijo_nodo" id="prefijo_nodo">
															<c:choose> 
																<c:when test="${form.prefijo_nodo=='MEC'}">
																	<option value="MEC" selected>Nodos estatales, Ceuta o Melilla</option>
																	<option value="AN">Nodo de Andalucia</option>
																	<option value="AR">Nodo de Aragon</option>
																	<option value="AS">Nodos de Asturias</option>
																	<option value="IB">Nodo de Baleares</option>
																	<option value="IC">Nodo de Canarias</option>
																	<option value="CB">Nodo de Cantabria</option>
																	<option value="CM">Nodo de Castilla la Mancha</option>
																	<option value="CL">Nodo de Castilla y Leon</option>
																	<option value="CT">Nodo de Cataluña</option>
																	<option value="EU">Nodo de Pais Vasco</option>
																	<option value="EX">Nodo de Extremadura</option>
																	<option value="GA">Nodo de Galicia</option>
																	<option value="LR">Nodo de La Rioja</option>
																	<option value="MA">Nodo de Madrid</option>
																	<option value="MU">Nodo de Murcia</option>
																	<option value="NA">Nodo de Navarra</option>
																	<option value="CV">Nodo de Valencia</option>
																</c:when>
																<c:when test="${form.prefijo_nodo=='AN'}">
																	<option value="MEC">Nodos estatales, Ceuta o Melilla</option>
																	<option value="AN" selected>Nodo de Andalucia</option>
																	<option value="AR">Nodo de Aragon</option>
																	<option value="AS">Nodos de Asturias</option>
																	<option value="IB">Nodo de Baleares</option>
																	<option value="IC">Nodo de Canarias</option>
																	<option value="CB">Nodo de Cantabria</option>
																	<option value="CM">Nodo de Castilla la Mancha</option>
																	<option value="CL">Nodo de Castilla y Leon</option>
																	<option value="CT">Nodo de Cataluña</option>
																	<option value="EU">Nodo de Pais Vasco</option>
																	<option value="EX">Nodo de Extremadura</option>
																	<option value="GA">Nodo de Galicia</option>
																	<option value="LR">Nodo de La Rioja</option>
																	<option value="MA">Nodo de Madrid</option>
																	<option value="MU">Nodo de Murcia</option>
																	<option value="NA">Nodo de Navarra</option>
																	<option value="CV">Nodo de Valencia</option>
																</c:when>
																<c:when test="${form.prefijo_nodo=='AR'}">
																	<option value="MEC">Nodos estatales, Ceuta o Melilla</option>
																	<option value="AN">Nodo de Andalucia</option>
																	<option value="AR" selected>Nodo de Aragon</option>
																	<option value="AS">Nodos de Asturias</option>
																	<option value="IB">Nodo de Baleares</option>
																	<option value="IC">Nodo de Canarias</option>
																	<option value="CB">Nodo de Cantabria</option>
																	<option value="CM">Nodo de Castilla la Mancha</option>
																	<option value="CL">Nodo de Castilla y Leon</option>
																	<option value="CT">Nodo de Cataluña</option>
																	<option value="EU">Nodo de Pais Vasco</option>
																	<option value="EX">Nodo de Extremadura</option>
																	<option value="GA">Nodo de Galicia</option>
																	<option value="LR">Nodo de La Rioja</option>
																	<option value="MA">Nodo de Madrid</option>
																	<option value="MU">Nodo de Murcia</option>
																	<option value="NA">Nodo de Navarra</option>
																	<option value="CV">Nodo de Valencia</option>
																</c:when>
																<c:when test="${form.prefijo_nodo=='AS'}">
																	<option value="MEC">Nodos estatales, Ceuta o Melilla</option>
																	<option value="AN">Nodo de Andalucia</option>
																	<option value="AR">Nodo de Aragon</option>
																	<option value="AS" selected>Nodos de Asturias</option>
																	<option value="IB">Nodo de Baleares</option>
																	<option value="IC">Nodo de Canarias</option>
																	<option value="CB">Nodo de Cantabria</option>
																	<option value="CM">Nodo de Castilla la Mancha</option>
																	<option value="CL">Nodo de Castilla y Leon</option>
																	<option value="CT">Nodo de Cataluña</option>
																	<option value="EU">Nodo de Pais Vasco</option>
																	<option value="EX">Nodo de Extremadura</option>
																	<option value="GA">Nodo de Galicia</option>
																	<option value="LR">Nodo de La Rioja</option>
																	<option value="MA">Nodo de Madrid</option>
																	<option value="MU">Nodo de Murcia</option>
																	<option value="NA">Nodo de Navarra</option>
																	<option value="CV">Nodo de Valencia</option>
																</c:when>
																<c:when test="${form.prefijo_nodo=='IB'}">
																	<option value="MEC">Nodos estatales, Ceuta o Melilla</option>
																	<option value="AN">Nodo de Andalucia</option>
																	<option value="AR">Nodo de Aragon</option>
																	<option value="AS">Nodos de Asturias</option>
																	<option value="IB" selected>Nodo de Baleares</option>
																	<option value="IC">Nodo de Canarias</option>
																	<option value="CB">Nodo de Cantabria</option>
																	<option value="CM">Nodo de Castilla la Mancha</option>
																	<option value="CL">Nodo de Castilla y Leon</option>
																	<option value="CT">Nodo de Cataluña</option>
																	<option value="EU">Nodo de Pais Vasco</option>
																	<option value="EX">Nodo de Extremadura</option>
																	<option value="GA">Nodo de Galicia</option>
																	<option value="LR">Nodo de La Rioja</option>
																	<option value="MA">Nodo de Madrid</option>
																	<option value="MU">Nodo de Murcia</option>
																	<option value="NA">Nodo de Navarra</option>
																	<option value="CV">Nodo de Valencia</option>
																</c:when>
																<c:when test="${form.prefijo_nodo=='IC'}">
																	<option value="MEC">Nodos estatales, Ceuta o Melilla</option>
																	<option value="AN">Nodo de Andalucia</option>
																	<option value="AR">Nodo de Aragon</option>
																	<option value="AS">Nodos de Asturias</option>
																	<option value="IB">Nodo de Baleares</option>
																	<option value="IC" selected>Nodo de Canarias</option>
																	<option value="CB">Nodo de Cantabria</option>
																	<option value="CM">Nodo de Castilla la Mancha</option>
																	<option value="CL">Nodo de Castilla y Leon</option>
																	<option value="CT">Nodo de Cataluña</option>
																	<option value="EU">Nodo de Pais Vasco</option>
																	<option value="EX">Nodo de Extremadura</option>
																	<option value="GA">Nodo de Galicia</option>
																	<option value="LR">Nodo de La Rioja</option>
																	<option value="MA">Nodo de Madrid</option>
																	<option value="MU">Nodo de Murcia</option>
																	<option value="NA">Nodo de Navarra</option>
																	<option value="CV">Nodo de Valencia</option>
																</c:when>
																<c:when test="${form.prefijo_nodo=='CB'}">
																	<option value="MEC">Nodos estatales, Ceuta o Melilla</option>
																	<option value="AN">Nodo de Andalucia</option>
																	<option value="AR">Nodo de Aragon</option>
																	<option value="AS">Nodos de Asturias</option>
																	<option value="IB">Nodo de Baleares</option>
																	<option value="IC">Nodo de Canarias</option>
																	<option value="CB" selected>Nodo de Cantabria</option>
																	<option value="CM">Nodo de Castilla la Mancha</option>
																	<option value="CL">Nodo de Castilla y Leon</option>
																	<option value="CT">Nodo de Cataluña</option>
																	<option value="EU">Nodo de Pais Vasco</option>
																	<option value="EX">Nodo de Extremadura</option>
																	<option value="GA">Nodo de Galicia</option>
																	<option value="LR">Nodo de La Rioja</option>
																	<option value="MA">Nodo de Madrid</option>
																	<option value="MU">Nodo de Murcia</option>
																	<option value="NA">Nodo de Navarra</option>
																	<option value="CV">Nodo de Valencia</option>
																</c:when>
																<c:when test="${form.prefijo_nodo=='CM'}">
																	<option value="MEC">Nodos estatales, Ceuta o Melilla</option>
																	<option value="AN">Nodo de Andalucia</option>
																	<option value="AR">Nodo de Aragon</option>
																	<option value="AS">Nodos de Asturias</option>
																	<option value="IB">Nodo de Baleares</option>
																	<option value="IC">Nodo de Canarias</option>
																	<option value="CB">Nodo de Cantabria</option>
																	<option value="CM" selected>Nodo de Castilla la Mancha</option>
																	<option value="CL">Nodo de Castilla y Leon</option>
																	<option value="CT">Nodo de Cataluña</option>
																	<option value="EU">Nodo de Pais Vasco</option>
																	<option value="EX">Nodo de Extremadura</option>
																	<option value="GA">Nodo de Galicia</option>
																	<option value="LR">Nodo de La Rioja</option>
																	<option value="MA">Nodo de Madrid</option>
																	<option value="MU">Nodo de Murcia</option>
																	<option value="NA">Nodo de Navarra</option>
																	<option value="CV">Nodo de Valencia</option>
																</c:when>
																<c:when test="${form.prefijo_nodo=='CL'}">
																	<option value="MEC">Nodos estatales, Ceuta o Melilla</option>
																	<option value="AN">Nodo de Andalucia</option>
																	<option value="AR">Nodo de Aragon</option>
																	<option value="AS">Nodos de Asturias</option>
																	<option value="IB">Nodo de Baleares</option>
																	<option value="IC">Nodo de Canarias</option>
																	<option value="CB">Nodo de Cantabria</option>
																	<option value="CM">Nodo de Castilla la Mancha</option>
																	<option value="CL" selected>Nodo de Castilla y Leon</option>
																	<option value="CT">Nodo de Cataluña</option>
																	<option value="EU">Nodo de Pais Vasco</option>
																	<option value="EX">Nodo de Extremadura</option>
																	<option value="GA">Nodo de Galicia</option>
																	<option value="LR">Nodo de La Rioja</option>
																	<option value="MA">Nodo de Madrid</option>
																	<option value="MU">Nodo de Murcia</option>
																	<option value="NA">Nodo de Navarra</option>
																	<option value="CV">Nodo de Valencia</option>
																</c:when>
																<c:when test="${form.prefijo_nodo=='CT'}">
																	<option value="MEC">Nodos estatales, Ceuta o Melilla</option>
																	<option value="AN">Nodo de Andalucia</option>
																	<option value="AR">Nodo de Aragon</option>
																	<option value="AS">Nodos de Asturias</option>
																	<option value="IB">Nodo de Baleares</option>
																	<option value="IC">Nodo de Canarias</option>
																	<option value="CB">Nodo de Cantabria</option>
																	<option value="CM">Nodo de Castilla la Mancha</option>
																	<option value="CL">Nodo de Castilla y Leon</option>
																	<option value="CT" selected>Nodo de Cataluña</option>
																	<option value="EU">Nodo de Pais Vasco</option>
																	<option value="EX">Nodo de Extremadura</option>
																	<option value="GA">Nodo de Galicia</option>
																	<option value="LR">Nodo de La Rioja</option>
																	<option value="MA">Nodo de Madrid</option>
																	<option value="MU">Nodo de Murcia</option>
																	<option value="NA">Nodo de Navarra</option>
																	<option value="CV">Nodo de Valencia</option>
																</c:when>
																<c:when test="${form.prefijo_nodo=='EU'}">
																	<option value="MEC">Nodos estatales, Ceuta o Melilla</option>
																	<option value="AN">Nodo de Andalucia</option>
																	<option value="AR">Nodo de Aragon</option>
																	<option value="AS">Nodos de Asturias</option>
																	<option value="IB">Nodo de Baleares</option>
																	<option value="IC">Nodo de Canarias</option>
																	<option value="CB">Nodo de Cantabria</option>
																	<option value="CM">Nodo de Castilla la Mancha</option>
																	<option value="CL">Nodo de Castilla y Leon</option>
																	<option value="CT">Nodo de Cataluña</option>
																	<option value="EU" selected>Nodo de Pais Vasco</option>
																	<option value="EX">Nodo de Extremadura</option>
																	<option value="GA">Nodo de Galicia</option>
																	<option value="LR">Nodo de La Rioja</option>
																	<option value="MA">Nodo de Madrid</option>
																	<option value="MU">Nodo de Murcia</option>
																	<option value="NA">Nodo de Navarra</option>
																	<option value="CV">Nodo de Valencia</option>
																</c:when>
																<c:when test="${form.prefijo_nodo=='EX'}">
																	<option value="MEC">Nodos estatales, Ceuta o Melilla</option>
																	<option value="AN">Nodo de Andalucia</option>
																	<option value="AR">Nodo de Aragon</option>
																	<option value="AS">Nodos de Asturias</option>
																	<option value="IB">Nodo de Baleares</option>
																	<option value="IC">Nodo de Canarias</option>
																	<option value="CB">Nodo de Cantabria</option>
																	<option value="CM">Nodo de Castilla la Mancha</option>
																	<option value="CL">Nodo de Castilla y Leon</option>
																	<option value="CT">Nodo de Cataluña</option>
																	<option value="EU">Nodo de Pais Vasco</option>
																	<option value="EX" selected>Nodo de Extremadura</option>
																	<option value="GA">Nodo de Galicia</option>
																	<option value="LR">Nodo de La Rioja</option>
																	<option value="MA">Nodo de Madrid</option>
																	<option value="MU">Nodo de Murcia</option>
																	<option value="NA">Nodo de Navarra</option>
																	<option value="CV">Nodo de Valencia</option>
																</c:when>
																<c:when test="${form.prefijo_nodo=='GA'}">
																	<option value="MEC">Nodos estatales, Ceuta o Melilla</option>
																	<option value="AN">Nodo de Andalucia</option>
																	<option value="AR">Nodo de Aragon</option>
																	<option value="AS">Nodos de Asturias</option>
																	<option value="IB">Nodo de Baleares</option>
																	<option value="IC">Nodo de Canarias</option>
																	<option value="CB">Nodo de Cantabria</option>
																	<option value="CM">Nodo de Castilla la Mancha</option>
																	<option value="CL">Nodo de Castilla y Leon</option>
																	<option value="CT">Nodo de Cataluña</option>
																	<option value="EU">Nodo de Pais Vasco</option>
																	<option value="EX">Nodo de Extremadura</option>
																	<option value="GA" selected>Nodo de Galicia</option>
																	<option value="LR">Nodo de La Rioja</option>
																	<option value="MA">Nodo de Madrid</option>
																	<option value="MU">Nodo de Murcia</option>
																	<option value="NA">Nodo de Navarra</option>
																	<option value="CV">Nodo de Valencia</option>
																</c:when>
																<c:when test="${form.prefijo_nodo=='LR'}">
																	<option value="MEC">Nodos estatales, Ceuta o Melilla</option>
																	<option value="AN">Nodo de Andalucia</option>
																	<option value="AR">Nodo de Aragon</option>
																	<option value="AS">Nodos de Asturias</option>
																	<option value="IB">Nodo de Baleares</option>
																	<option value="IC">Nodo de Canarias</option>
																	<option value="CB">Nodo de Cantabria</option>
																	<option value="CM">Nodo de Castilla la Mancha</option>
																	<option value="CL">Nodo de Castilla y Leon</option>
																	<option value="CT">Nodo de Cataluña</option>
																	<option value="EU">Nodo de Pais Vasco</option>
																	<option value="EX">Nodo de Extremadura</option>
																	<option value="GA">Nodo de Galicia</option>
																	<option value="LR" selected>Nodo de La Rioja</option>
																	<option value="MA">Nodo de Madrid</option>
																	<option value="MU">Nodo de Murcia</option>
																	<option value="NA">Nodo de Navarra</option>
																	<option value="CV">Nodo de Valencia</option>
																</c:when>
																<c:when test="${form.prefijo_nodo=='MA'}">
																	<option value="MEC">Nodos estatales, Ceuta o Melilla</option>
																	<option value="AN">Nodo de Andalucia</option>
																	<option value="AR">Nodo de Aragon</option>
																	<option value="AS">Nodos de Asturias</option>
																	<option value="IB">Nodo de Baleares</option>
																	<option value="IC">Nodo de Canarias</option>
																	<option value="CB">Nodo de Cantabria</option>
																	<option value="CM">Nodo de Castilla la Mancha</option>
																	<option value="CL">Nodo de Castilla y Leon</option>
																	<option value="CT">Nodo de Cataluña</option>
																	<option value="EU">Nodo de Pais Vasco</option>
																	<option value="EX">Nodo de Extremadura</option>
																	<option value="GA">Nodo de Galicia</option>
																	<option value="LR">Nodo de La Rioja</option>
																	<option value="MA" selected>Nodo de Madrid</option>
																	<option value="MU">Nodo de Murcia</option>
																	<option value="NA">Nodo de Navarra</option>
																	<option value="CV">Nodo de Valencia</option>
																</c:when>
																<c:when test="${form.prefijo_nodo=='MU'}">
																	<option value="MEC">Nodos estatales, Ceuta o Melilla</option>
																	<option value="AN">Nodo de Andalucia</option>
																	<option value="AR">Nodo de Aragon</option>
																	<option value="AS">Nodos de Asturias</option>
																	<option value="IB">Nodo de Baleares</option>
																	<option value="IC">Nodo de Canarias</option>
																	<option value="CB">Nodo de Cantabria</option>
																	<option value="CM">Nodo de Castilla la Mancha</option>
																	<option value="CL">Nodo de Castilla y Leon</option>
																	<option value="CT">Nodo de Cataluña</option>
																	<option value="EU">Nodo de Pais Vasco</option>
																	<option value="EX">Nodo de Extremadura</option>
																	<option value="GA">Nodo de Galicia</option>
																	<option value="LR">Nodo de La Rioja</option>
																	<option value="MA">Nodo de Madrid</option>
																	<option value="MU" selected>Nodo de Murcia</option>
																	<option value="NA">Nodo de Navarra</option>
																	<option value="CV">Nodo de Valencia</option>
																</c:when>
																<c:when test="${form.prefijo_nodo=='NA'}">
																	<option value="MEC">Nodos estatales, Ceuta o Melilla</option>
																	<option value="AN">Nodo de Andalucia</option>
																	<option value="AR">Nodo de Aragon</option>
																	<option value="AS">Nodos de Asturias</option>
																	<option value="IB">Nodo de Baleares</option>
																	<option value="IC">Nodo de Canarias</option>
																	<option value="CB">Nodo de Cantabria</option>
																	<option value="CM">Nodo de Castilla la Mancha</option>
																	<option value="CL">Nodo de Castilla y Leon</option>
																	<option value="CT">Nodo de Cataluña</option>
																	<option value="EU">Nodo de Pais Vasco</option>
																	<option value="EX">Nodo de Extremadura</option>
																	<option value="GA">Nodo de Galicia</option>
																	<option value="LR">Nodo de La Rioja</option>
																	<option value="MA">Nodo de Madrid</option>
																	<option value="MU">Nodo de Murcia</option>
																	<option value="NA" selected>Nodo de Navarra</option>
																	<option value="CV">Nodo de Valencia</option>
																</c:when>
																<c:when test="${form.prefijo_nodo=='CV'}">
																	<option value="MEC">Nodos estatales, Ceuta o Melilla</option>
																	<option value="AN">Nodo de Andalucia</option>
																	<option value="AR">Nodo de Aragon</option>
																	<option value="AS">Nodos de Asturias</option>
																	<option value="IB">Nodo de Baleares</option>
																	<option value="IC">Nodo de Canarias</option>
																	<option value="CB">Nodo de Cantabria</option>
																	<option value="CM">Nodo de Castilla la Mancha</option>
																	<option value="CL">Nodo de Castilla y Leon</option>
																	<option value="CT">Nodo de Cataluña</option>
																	<option value="EU">Nodo de Pais Vasco</option>
																	<option value="EX">Nodo de Extremadura</option>
																	<option value="GA">Nodo de Galicia</option>
																	<option value="LR">Nodo de La Rioja</option>
																	<option value="MA">Nodo de Madrid</option>
																	<option value="MU">Nodo de Murcia</option>
																	<option value="NA">Nodo de Navarra</option>
																	<option value="CV" selected>Nodo de Valencia</option>
																</c:when>
																<c:otherwise>
																	<option value="" selected>Seleccionar...</option>
																	<option value="MEC">Nodos estatales, Ceuta o Melilla</option>
																	<option value="AN">Nodo de Andalucia</option>
																	<option value="AR">Nodo de Aragon</option>
																	<option value="AS">Nodos de Asturias</option>
																	<option value="IB">Nodo de Baleares</option>
																	<option value="IC">Nodo de Canarias</option>
																	<option value="CB">Nodo de Cantabria</option>
																	<option value="CM">Nodo de Castilla la Mancha</option>
																	<option value="CL">Nodo de Castilla y Leon</option>
																	<option value="CT">Nodo de Cataluña</option>
																	<option value="EU">Nodo de Pais Vasco</option>
																	<option value="EX">Nodo de Extremadura</option>
																	<option value="GA">Nodo de Galicia</option>
																	<option value="LR">Nodo de La Rioja</option>
																	<option value="MA">Nodo de Madrid</option>
																	<option value="MU">Nodo de Murcia</option>
																	<option value="NA">Nodo de Navarra</option>
																	<option value="CV">Nodo de Valencia</option>
																</c:otherwise>
															</c:choose>
															</select>
														</c:when>	
														
														<c:when test="${variable.nombre=='proxy'}">														
<tr >
	<td valign="top" class="opcion_tab_01">
		<c:choose> 
			<c:when test="${form.proxy=='true'}">	
				<input type="radio" name="proxy" value="true" checked="checked" id="proxy">
				<label for="proxy">Si</label>
				<input type="radio" name="proxy" value="false" id="proxy" style="margin-left:15px;">
				<label for="proxy">No</label>
			</c:when>	
			<c:when test="${form.proxy=='false'}">	
				<input type="radio" name="proxy" value="true" id="proxy">
				<label for="proxy">Si</label>
				<input type="radio" name="proxy" value="false" checked="checked" id="proxy" style="margin-left:15px;">
				<label for="proxy">No</label>
			</c:when>	
			<c:otherwise>
				<input type="radio" name="proxy" value="true" id="proxy">
				<label for="proxy">Si</label>
				<input type="radio" name="proxy" value="false" id="proxy" style="margin-left:15px;">
				<label for="proxy">No</label>
			</c:otherwise>
		</c:choose>
	</td>
</tr>
														</c:when>	
														
														<c:when test="${variable.nombre=='proxy_host'}">
															
																<c:if test="${variable.ejemplo!=''}">	
																	<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
																</c:if> 
																<br>
																<label for="proxyhost" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
																<input type="text" name="proxyhost" value="${form.proxyhost}" id="proxyhost" style="margin-bottom:0 !important;" title="${variable.nombre}" size="150"><br />
															
														</c:when>	
														
														<c:when test="${variable.nombre=='proxy_passwd'}">
															
																<label for="proxypasswd" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
																<input type="text" name="proxypasswd" value="${form.proxypasswd}" id="proxypasswd" style="margin-bottom:0 !important;" title="${variable.nombre}" size="150"><br />
															
														</c:when>	
														
														<c:when test="${variable.nombre=='proxy_port'}">
															
																<c:if test="${variable.ejemplo!=''}">	
																	<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
																</c:if> 
																<br>
																<label for="proxyport" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
																<input type="text" name="proxyport" value="${form.proxyport}" id="proxyport" style="margin-bottom:0 !important;" title="${variable.nombre}" size="6" maxlength="6"><br />
															
														</c:when>	
														
														<c:when test="${variable.nombre=='proxy_user'}">
															
																<c:if test="${variable.ejemplo!=''}">	
																	<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
																</c:if> 
																<br>
																<label for="proxyuser" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
																<input type="text" name="proxyuser" value="${form.proxyuser}" id="proxyuser" style="margin-bottom:0 !important;" title="${variable.nombre}" size="150"><br />
															
														</c:when>	
														
														<c:when test="${variable.nombre=='rolebindsubpath'}">
															
																<c:if test="${variable.ejemplo!=''}">	
																	<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
																</c:if> 
																<br>
																<label for="rolebindsubpath" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
																<input type="text" name="rolebindsubpath" value="${form.rolebindsubpath}" id="rolebindsubpath" style="margin-bottom:0 !important;" title="${variable.nombre}" size="150"><br />
															
														</c:when>	
														
														<c:when test="${variable.nombre=='path_logs'}">
															<c:if test="${variable.ejemplo!=''}">
																<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
															</c:if> 
															<br>
															<label for="rutalogs" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
															<input type="text" name="rutalogs" value="${form.rutalogs}" id="rutalogs" style="margin-bottom:0 !important;" title="${variable.nombre}" size="150"><br />
														</c:when>	
														
														<c:when test="${variable.nombre=='server_on'}">
															
																<c:if test="${variable.ejemplo!=''}">	
																	<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
																</c:if> 
																<br>
																<label for="serveron" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
																<input type="text" name="serveron" value="${form.serveron}" id="serveron" style="margin-bottom:0 !important;" title="${variable.nombre}" size="150"><br />
															
														</c:when>	
														
														<c:when test="${variable.nombre=='smtp_authentication'}">
<tr >
	<td valign="top" class="opcion_tab_01">
		<c:choose> 
			<c:when test="${form.smtpauthentication=='true'}">	
				<input type="radio" name="smtpauthentication" value="true" checked="checked" id="smtpauthentication">
				<label for="smtpauthentication">Si</label>
				<input type="radio" name="smtpauthentication" value="false" id="smtpauthentication" style="margin-left:15px;">
				<label for="smtpauthentication">No</label>
			</c:when>	
			<c:when test="${form.smtpauthentication=='false'}">	
				<input type="radio" name="smtpauthentication" value="true" id="smtpauthentication">
				<label for="smtpauthentication">Si</label>
				<input type="radio" name="smtpauthentication" value="false" checked="checked" id="smtpauthentication" style="margin-left:15px;">
				<label for="smtpauthentication">No</label>
			</c:when>	
			<c:otherwise>
				<input type="radio" name="smtpauthentication" value="true" id="smtpauthentication">
				<label for="smtpauthentication">Si</label>
				<input type="radio" name="smtpauthentication" value="false" id="smtpauthentication" style="margin-left:15px;">
				<label for="smtpauthentication">No</label>
			</c:otherwise>
		</c:choose>
	</td>
</tr>
														</c:when>	
														
														<c:when test="${variable.nombre=='smtp_host'}">
															
																<c:if test="${variable.ejemplo!=''}">	
																	<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
																</c:if> 
																<br>
																<label for="smtphost" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
																<input type="text" name="smtphost" value="${form.smtphost}" id="smtphost" style="margin-bottom:0 !important;" title="${variable.nombre}" size="150"><br />
															
														</c:when>	
														
														<c:when test="${variable.nombre=='smtp_passwd'}">
															
																<c:if test="${variable.ejemplo!=''}">	
																	<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
																</c:if> 
																<br>
																<label for="smtppasswd" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
																<input type="text" name="smtppasswd" value="${form.smtppasswd}" id="smtppasswd" style="margin-bottom:0 !important;" title="${variable.nombre}" size="150"><br />
															
														</c:when>	
														
														<c:when test="${variable.nombre=='smtp_sender'}">
															
																<c:if test="${variable.ejemplo!=''}">	
																	<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
																</c:if> 
																<br>
																<label for="smtpsender" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
																<input type="text" name="smtpsender" value="${form.smtpsender}" id="smtpsender" style="margin-bottom:0 !important;" title="${variable.nombre}" size="150"><br />
															
														</c:when>	
														
														<c:when test="${variable.nombre=='smtp_user'}">
															
																<c:if test="${variable.ejemplo!=''}">
																	<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
																</c:if> 
																<br>
																<label for="smtpuser" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
																<input type="text" name="smtpuser" value="${form.smtpuser}" id="smtpuser" style="margin-bottom:0 !important;" title="${variable.nombre}" size="150"><br />
															
														</c:when>	
														
														<c:when test="${variable.nombre=='subdominio'}">
															<c:if test="${variable.ejemplo!=''}">
																<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
															</c:if> 
															<br>
															<label for="subdomain" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
															<input type="text" name="subdomain" value="${form.subdomain}" id="subdomain" style="margin-bottom:0 !important;" title="${variable.nombre}" size="150"><br />
														</c:when>	
														
														<c:when test="${variable.nombre=='subdominio_jboss'}">
															<c:if test="${variable.ejemplo!=''}">
																<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
															</c:if> 
															<br>
															<label for="subdomain_jboss" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
															<input type="text" name="subdomain_jboss" value="${form.subdomain_jboss}" id="subdomain_jboss" style="margin-bottom:0 !important;" title="${variable.nombre}" size="150"><br />
														</c:when>	
														
														<c:when test="${variable.nombre=='contacto_telefono'}">
																												
																<c:if test="${variable.ejemplo!=''}">	
																	<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
																</c:if> 
																<br>
																<label for="telefonoContacto" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
																<input type="text" name="telefonoContacto" value="${form.telefonoContacto}" id="telefonoContacto" style="margin-bottom:0 !important;" title="${variable.nombre}" size="15" maxlength="15"><br />
															
														</c:when>	
														
														<c:when test="${variable.nombre=='UPLOADS'}">
															<c:if test="${variable.ejemplo!=''}">	
																<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
															</c:if> 
															<br>
															<label for="UPLOADS" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
															<input type="text" name="UPLOADS" value="${form.UPLOADS}" id="UPLOADS" style="margin-bottom:0 !important;" title="${variable.nombre}" size="150"><br />
														</c:when>	
														
														<c:when test="${variable.nombre=='urlConsejeriaNodo'}">
															
																<c:if test="${variable.ejemplo!=''}">	
																	<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
																</c:if> 
																<br>
																<label for="urlConsejeria" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
																<input type="text" name="urlConsejeria" value="${form.urlConsejeria}" id="urlConsejeria" style="margin-bottom:0 !important;" title="${variable.nombre}" size="150"><br />
															
														</c:when>	
														
														<c:when test="${variable.nombre=='usercnprefix'}">
															
																<c:if test="${variable.ejemplo!=''}">
																	<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
																</c:if> 
																<br>
																<label for="usercnprefix" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
																<input type="text" name="usercnprefix" value="${form.usercnprefix}" id="usercnprefix" style="margin-bottom:0 !important;" title="${variable.nombre}" size="150"><br />
															
														</c:when>
															
													</c:choose>			
													
													</td>
												</tr>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
							</c:if> 	
							<!--  FIN GLOBO BLANCO   -->
							<!--  FIN GLOBO BLANCO   -->

						</c:forEach>
					</c:if> 	
					
				</div>
				<!--  FIN CAJA DE FORMULARIO   -->

			</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->
<br>




<!-- Inicio Botones  -->
<!-- Inicio Botones  -->
<c:if test="${fn:length(form.listaVariables) > 0}" >
	<fieldset class="tipo ft_centrada">
	<input class="boton_125"  type="submit"  value="Guardar" />
	</fieldset>
</c:if>
<!-- Fin Botones  -->
<br>
</form>
</div>
<!-- Fin plantilla contenido  -->
<!-- Fin plantilla contenido  -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->


</div>


<!--  FIN CAPA MADRE   -->
</div>
</div>
<!-- Fin cuerpo central de pagina con Menu Principal, capa Lateral y capa Central  -->



<!-- Pie de pagina -->
</div>
</div>
</div>
<!--  FIN CUERPO GENERAL   -->

</div>
<!--  FIN CAPA MADRE   -->

</body>
</html>
