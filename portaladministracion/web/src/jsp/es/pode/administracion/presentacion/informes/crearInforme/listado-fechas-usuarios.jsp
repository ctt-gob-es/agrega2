<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html:xhtml/>
<?xml version="1.0" encoding="iso-8859-1" ?> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="es" xml:lang="es">

<head lang="es" dir="ltr">

	<title><bean:message key ="title.Comun"/></title>
	
	
	
	
		<script type="text/javascript" src="<rewrite:rewrite url="static/js/curvas.js"/>"></script>
		<link rel="stylesheet" media="screen" href="<rewrite:rewrite url="static/css/red.css"/>" type="text/css" />
		<script type="text/javascript" src="<rewrite:rewrite url="static/js/plantilla.js"/>"></script>
		<script type="text/javascript" src="<rewrite:rewrite url="static/js/mootools.js"/>"></script>
		<script type="text/javascript" src="<rewrite:rewrite url="static/js/calendar.rc4.js"/>"></script>
		<style type="text/css">
			.plantilla_contenido {height:auto !important;height:30em}
			#capa_madre {max-width:800px !important;}
			body {background:#D6E6F6}
		</style>

</head>

<div id="capa_madre">
<!-- ************************     INICIO PLANTILLA CONTENIDO     *************************  -->
    <div class="plantilla_contenido">
    
    <!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
	<jsp:include page="/layout/messages.jsp" flush="true" />

	<h2><bean:message key="informes.crearInforme.cabecera"/></h2>
	
	<html:form styleId="crearInformeListadoFechasUsuariosAceptarForm" action="/CrearInforme/ListadoFechasUsuariosAceptar" method="post" enctype="multipart/form-data">
	
		<!--  ********************     INICIO GLOBO GRIS   *********************  -->
		<div class="globo_gris" >
			<div class="globo_gris_01">
				<div class="globo_gris_02">
					<div class="globo_gris_03">
					<!--  *********************       INICIO CAJA DE FORMULARIO     *******************  -->
						<div id="formulario" >
							
							<input name="informe" value="${form.informe}" type="hidden" />
							<!--  *********************       Caja de Texto de Nombre de Informe     *******************  -->
							<div class="fila_de_tabla">
								<div class="contenedor_izquierda">
									<div class="text">
										<label for="informe" ><bean:message key="informes.nombreInforme"/></label>
									</div>
								</div>
								<div class="contenedor_derecha">
									<div class="text">
										<div><p style="display:inline;"><bean:message key="informes.${form.informe}"/></p></div>
										<br/>
									</div>
								</div>
								<div class="linea_separadora"></div>
								<br class="oculto" />
							</div>
							
							
							<bean:define id="nomMeses"><bean:message key="catalogadorAvanzado.nombresMeses"/></bean:define>
							<bean:define id="nomDias"><bean:message key="catalogadorAvanzado.nombresDias"/></bean:define>
							<bean:define id="offSet"><bean:message key="offset"/></bean:define>
							<!--  *********************       Cajas de Texto de Fecha Desde     *******************  -->
							<div class="fila_de_tabla">
								<div class="contenedor_izquierda">
									<div class="text">
										<label for="fecha_inicio"><bean:message key="informes.crearInforme.fechaDesde"/></label>
									</div>
								</div>
								<div class="contenedor_derecha">
									<div class="text">
									
										<label class="oculto" for="day1"><bean:message key="informes.crearInforme.fechaDia"/></label>
										<input name="diaDesde" value="${form.diaDesde}" class="fechazo" maxlength="2" id="day1" onblur="this.style.backgroundColor='#e1e1e1'" type="text" title="<bean:message key="informes.crearInforme.introduzcaDia"/> "  />
										
										<label class="oculto" for="month1"><bean:message key="informes.crearInforme.fechaMes"/></label>
            							<input class="fechazo" value="${form.mesDesde}" maxlength="2" onblur="this.style.backgroundColor='#e1e1e1'" id="month1" name="mesDesde" type="text" title="<bean:message key="informes.crearInforme.introduzcaMes"/> "/>
										<label class="oculto" for="year1"><bean:message key="informes.crearInforme.fechaAnio"/></label>	
										<input name="anioDesde" value="${form.anioDesde}"  class="fechazo" maxlength="4" id="year1" onblur="this.style.backgroundColor='#e1e1e1'" type="text" title="<bean:message key="informes.crearInforme.introduzcaAnio"/> "  />

									</div>
								</div>
								<div class="linea_separadora"></div>
								<br class="oculto" />
							</div>
							
							
							<!--  *********************       Cajas de Texto de Fecha Hasta    *******************  -->
							<div class="fila_de_tabla">
								<div class="contenedor_izquierda">
									<div class="text">
										<label for="fecha_inicio"><bean:message key="informes.crearInforme.fechaHasta"/></label>
									</div>
								</div>
								<div class="contenedor_derecha" id="cont_esp_fech">
									<div class="text">
									
										<label class="oculto" for="day2"><bean:message key="informes.crearInforme.fechaDia"/></label>
										<input name="diaHasta" value="${form.diaHasta}" maxlength="2" class="fechazo"  id="day2" onblur="this.style.backgroundColor='#e1e1e1'" type="text" title="<bean:message key="informes.crearInforme.introduzcaDia"/> "  />
										
										<label class="oculto" for="month2"><bean:message key="informes.crearInforme.fechaMes"/></label>
            							<input class="fechazo" value="${form.mesHasta}" maxlength="2" onblur="this.style.backgroundColor='#e1e1e1'" id="month2" name="mesHasta" type="text" title="<bean:message key="informes.crearInforme.introduzcaMes"/> "/>
										<label class="oculto" for="year2"><bean:message key="informes.crearInforme.fechaAnio"/></label>	
										<input name="anioHasta" value="${form.anioHasta}" maxlength="4" class="fechazo" id="year2" onblur="this.style.backgroundColor='#e1e1e1'" type="text" title="<bean:message key="informes.crearInforme.introduzcaAnio"/> "  />

									</div>
								</div>
								<div class="linea_separadora"></div>
								<br class="oculto" />
							</div>
				 
				 		
				 			<!--		CAMPO usuario (desplegable)		-->
						
							<div class="fila_de_tabla">
			  					<div class="contenedor_izquierda">
			  						<div class="text"><label for="usuario" ><bean:message key="informes.crearInformes.nombreUsuario"/></label></div>
			 					</div>
								<div class="contenedor_derecha">
									<div class="text">
										<bean:define id="introUsuario" ><bean:message key="informes.crearInformes.introduzcaUsuario"/></bean:define>	
										<c:choose>
				                        	<c:when test="${!empty form.usuariosBackingList}">
				                        		<html:select style="width:200px" name="form" property="usuarios" styleId="usuarios" title="${introUsuario} " >
				                        			<option value="#" selected="selected" ><bean:message key="informe.crearInforme.seleccioneUsuario"/></option>
				                    				<html:optionsCollection name="form" property="usuariosBackingList"  label="label" value="value"/>
				                        		</html:select>
				                        	</c:when>
				                        	<c:otherwise>
				                        		<html:select name="form" property="usuarios" styleId="usuarios" title="${introUsuario}"/>
				                        	</c:otherwise>
				                        </c:choose>
									</div>
								</div>
								<div class="linea_separadora"></div>
								<br class="oculto" />
							</div>	
							
				 		
				 			<!-- *********************		CAMPO formato (desplegable)		********************* -->
						
							<div class="fila_de_tabla">
								<div class="contenedor_izquierda">
									<div class="text">
										<label for="formato"><bean:message key="informes.crearInforme.formato"/></label>
									</div>
								</div>
								<div class="contenedor_derecha">
									<div class="text">
										<html:select  style="width:107px" property="formato" titleKey="informes.crearInforme.seleccioneFormato" styleId="formato">
											<html:option key="informes.crearInforme.formato.html" 		value="html" />
											<html:option key="informes.crearInforme.formato.pdf" 		value="pdf" styleClass="oscura"/>
											<html:option key="informes.crearInforme.formato.excel"	 	value="excel"  />
										</html:select>
									</div>
								</div>
								<div class="linea_separadora"></div>
								
								<br class="oculto" />
							</div>	
				 		
						</div >
						<!--  ********************     FIN CAJA FORMULARIO   *********************  -->
					</div>	
				</div>		
			</div>			
		</div>			
		<!--  ********************     FIN GLOBO GRIS   *********************  -->
		
		<!--********************     Boton Continuar      *********************  -->
		
		<bean:define id="aceptValue"><bean:message key="informes.crearInforme.verInforme"/></bean:define>	
		<html:submit styleClass="boton_125_de_2" value="${aceptValue}" ></html:submit>
		<input type="button" value="<bean:message key="informe.cerrarVentana"/>" class="boton_125_de_2_izq" onclick="javascript:window.close();">
			 
	</html:form>	


	</div>
	<!-- ************************     FIN  PLANTILLA  CONTENIDO   *************************  -->

	
<script type="text/javascript">		
//<![CDATA[
	window.addEvent('domready', function() { 
		jsMeses = "${nomMeses}"; arrayMeses = jsMeses.split(",");
		jsDias = "${nomDias}"; arrayDias = jsDias.split(",");
			myCal1 = new Calendar({ year1: { day1: 'd', month1: 'm', year1: 'Y' }}, { direction: 0, tweak: {x: 6, y: 0} ,offset: ${offSet},months: arrayMeses ,days: arrayDias });
			myCal2 = new Calendar({ year2: { day2: 'd', month2: 'm', year2: 'Y' }}, { direction: 0, tweak: {x: 6, y: 0} ,offset: ${offSet},months: arrayMeses ,days: arrayDias });
			});
	//]]>
</script>	
</body>
</html>
