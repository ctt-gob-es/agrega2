<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<tiles:insert definition="layout-sinlateral">

    <tiles:put name="title" type="string">
        <bean:message key="title.Comun"/>
    </tiles:put>

    <tiles:put name="body" type="string">
<%@ include file="/taglib-imports.jspf" %>



<!-- Inicio plantilla contenido  -->
<div class="plantilla_contenido" >
<!-- ## Para pintar los mensajes de error ## -->
	<jsp:include page="/layout/messages.jsp" flush="true" />

	<h2><bean:message key="anadir.ode.carpeta.personal.titulo"/></h2>		
		<!--  INICIO GLOBO GRIS   -->
		<!--  INICIO GLOBO GRIS   -->
		
		<!-- 
		<div class="globo_gris" >
			<div class="globo_gris_01">
				<div class="globo_gris_02">
					<div class="globo_gris_03">
					<logic:equal name="form" property="resultadoOperacion" value="true">	
					<div id="formulario" class="ali_c">
					<br />
					 <p><em class="correcto">
					    <bean:message key="anadir.ode.carpeta.peronal.resultado.correcto"/>
					 </em></p>
					  
						<br />
					</div>
					</logic:equal>					
					<logic:equal name="form" property="resultadoOperacion" value="false">	
					<div id="formulario" class="ali_c">
					<br />
					 <p><em class="incorrecto">
					    <bean:message key="anadir.ode.carpeta.peronal.resultado.incorrecto"/> ${form.descripcionCodigoErroneo}
					 </em></p>					 
						<br />
					</div>
						</logic:equal>
					</div>
				</div>
			</div>
		</div>
		-->
		
		<!--  FIN GLOBO GRIS   -->
<input type="hidden" name="busquedaSimpleAvanzada" value="${form.busquedaSimpleAvanzada}"/>
			<!-- Inicio Botones  -->
			
			<logic:equal name="form" property="resultadoOperacion" value="false">	
				
				<div class="globo_gris" >
					<div class="globo_gris_01">
						<div class="globo_gris_02">
							<div class="globo_gris_03">			
							<!--  INICIO CAJA DE FORMULARIO   -->
							<div id="formulario" class="ali_c">
							<br />
							 <p><em class="incorrecto">
							    <bean:message key="anadir.ode.carpeta.peronal.resultado.incorrecto"/> ${form.descripcionCodigoErroneo}
							 </em></p>					 
								<br />
							</div>
							</div>
						</div>
					</div>
				</div>
			
				<fieldset class="tipo ft_centrada">	
					<logic:equal name="form" property="mostrarVuelta" value="true">
						<input type="hidden" name="mostVuelta" value="${form.mostrarVuelta}"/>								
						
						<logic:equal name="form" property="busquedaSimpleAvanzada" value="CARPETA_PERSONAL">
						<!-- Aqui se llega desde el enlace crear_version de la lista de publicados de la carpeta_personal-->		
							<form action="<html:rewrite action="../gestorFlujo/ObjetosPersonalesCU/ObjetosPersonalesCU.do"/>" method="post">
								<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
								<input class="boton_125"  type="submit"  value="<bean:message key="previsualizar.conSinSecuencia.botonAceptar"/>"/>
							</form>
						</logic:equal>
						
						<logic:notEqual name="form" property="busquedaSimpleAvanzada" value="CARPETA_PERSONAL">
							<input type="hidden" name="mostVuelta2" value="2"/>		
							<logic:equal name="form" property="busquedaSimpleAvanzada" value="BUSQUEDA_ARBOL">
								<input type="hidden" name="mostVuelta3" value="3"/>		
								<form action="<html:rewrite action="/DetallarODECU/DetallarODECU.do"/>?identificadorODE=${form.identificadorODE}&amp;idioma=${form.idiomaODE}&amp;mostrarVuelta=${form.mostrarVuelta}&amp;areaCurricularBusqueda=" method="post">
									<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
									<input class="boton_125"  type="submit"  value="<bean:message key="previsualizar.conSinSecuencia.botonAceptar"/>"/>
								</form>
							</logic:equal>
							<logic:notEqual name="form" property="busquedaSimpleAvanzada" value="BUSQUEDA_ARBOL">
								<input type="hidden" name="mostVuelta4" value="4"/>		
								<form action="<html:rewrite action="/DetallarODECU/DetallarODECU.do"/>?identificadorODE=${form.identificadorODE}&amp;idioma=${form.idiomaODE}&amp;mostrarVuelta=${form.mostrarVuelta}" method="post">
									<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
									<input class="boton_125"  type="submit"  value="<bean:message key="previsualizar.conSinSecuencia.botonAceptar"/>"/>
								</form>
							</logic:notEqual>
						</logic:notEqual>						
					</logic:equal>
					
					<logic:notEqual name="form" property="mostrarVuelta" value="true">
					<!-- Aqui se llega desde el enlace crear_version de la ficha del ODE y se vuelve a el-->
						<input type="hidden" name="mostVuelta5" value="5"/>		
						<form action="<html:rewrite action="/DetallarODECU/DetallarODECU.do"/>?identificadorODE=${form.identificadorODE}&amp;idioma=${form.idiomaODE}&amp;mostrarVuelta=${form.mostrarVuelta}" method="post">
							<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
							<input class="boton_125"  type="submit"  value="<bean:message key="previsualizar.conSinSecuencia.botonAceptar"/>"/>
						</form>
					</logic:notEqual>
				</fieldset>
			</logic:equal>	
			
			
			
			<logic:equal name="form" property="resultadoOperacion" value="true">	
					<logic:equal name="form" property="mostrarVuelta" value="true">
						<input type="hidden" name="mostVuelta" value="${form.mostrarVuelta}"/>	
													
						<logic:equal name="form" property="busquedaSimpleAvanzada" value="CARPETA_PERSONAL">
						<!-- Aqui se llega desde el enlace crear_version de la lista de publicados de la carpeta_personal-->
				<div class="globo_gris" >
					<div class="globo_gris_01">
						<div class="globo_gris_02">
							<div class="globo_gris_03">			
							<!--  INICIO CAJA DE FORMULARIO   -->
							<div id="formulario" class="ali_c">
							<br />
							 <p><em class="correcto">
							    <bean:message key="anadir.ode.para.versionar.carpeta.peronal.resultado.correcto"/>
							 </em></p>				 
								<br />
							</div>
							</div>
						</div>
					</div>
				</div>								
				<fieldset class="tipo">	
							<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>	
							<form action="<html:rewrite action="../gestorFlujo/ObjetosPersonalesCU/ObjetosPersonalesCU.do"/>" method="post">
								<input class="boton_125_de_2_izq" style="width:155px !important;" type="submit" name="action" value="<bean:message key="ir.a.carpeta.personal"/>" />
							</form>
							<form action="<html:rewrite action="../gestorFlujo/ObjetosPublicadosCU/ObjetosPublicadosCU.do"/>" method="post">
								<input class="boton_125_de_2" style="width:155px !important;" type="submit" name="action" value="<bean:message key="ir.a.carpeta.personal.mas.tarde"/>" />
							</form>
						</logic:equal>
						
						<!--  -->
						<logic:notEqual name="form" property="busquedaSimpleAvanzada" value="CARPETA_PERSONAL">
				<div class="globo_gris" >
					<div class="globo_gris_01">
						<div class="globo_gris_02">
							<div class="globo_gris_03">			
							<!--  INICIO CAJA DE FORMULARIO   -->
							<div id="formulario" class="ali_c">
							<br />
							 <p><em class="correcto">
							    <bean:message key="anadir.ode.carpeta.peronal.resultado.correcto"/>
							 </em></p>					 
								<br />
							</div>
							</div>
						</div>
					</div>
				</div>
				<fieldset class="tipo ft_centrada">	
							<input type="hidden" name="mostVuelta2" value="2"/>		
							<logic:equal name="form" property="busquedaSimpleAvanzada" value="BUSQUEDA_ARBOL">
								<input type="hidden" name="mostVuelta3" value="3"/>		
								<form action="<html:rewrite action="/DetallarODECU/DetallarODECU.do"/>?identificadorODE=${form.identificadorODE}&amp;idioma=${form.idiomaODE}&amp;mostrarVuelta=${form.mostrarVuelta}&amp;areaCurricularBusqueda=" method="post">
									<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
									<input class="boton_125"  type="submit"  value="<bean:message key="previsualizar.conSinSecuencia.botonAceptar"/>"/>				
								</form>
							</logic:equal>
							<logic:notEqual name="form" property="busquedaSimpleAvanzada" value="BUSQUEDA_ARBOL">
								<input type="hidden" name="mostVuelta4" value="4"/>		
								<form action="<html:rewrite action="/DetallarODECU/DetallarODECU.do"/>?identificadorODE=${form.identificadorODE}&amp;idioma=${form.idiomaODE}&amp;mostrarVuelta=${form.mostrarVuelta}" method="post">
									<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
									<input class="boton_125"  type="submit"  value="<bean:message key="previsualizar.conSinSecuencia.botonAceptar"/>"/>									
								</form>
							</logic:notEqual>
						</logic:notEqual>						
					</logic:equal>
					
					<logic:notEqual name="form" property="mostrarVuelta" value="true">
					<!-- Aqui se llega desde el enlace crear_version de la ficha del ODE y se vuelve a el-->
				<div class="globo_gris" >
					<div class="globo_gris_01">
						<div class="globo_gris_02">
							<div class="globo_gris_03">			
							<!--  INICIO CAJA DE FORMULARIO   -->
							<div id="formulario" class="ali_c">
							<br />
							 <p><em class="correcto">
							    <bean:message key="anadir.ode.para.versionar.carpeta.peronal.resultado.correcto"/>
							 </em></p>				 
								<br />
							</div>
							</div>
						</div>
					</div>
				</div>
				<fieldset class="tipo">	
						<input type="hidden" name="mostVuelta5" value="5"/>	
						<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>	
						<form action="<html:rewrite action="../gestorFlujo/ObjetosPersonalesCU/ObjetosPersonalesCU.do"/>" method="post">
							<input class="boton_125_de_2_izq" style="width:155px !important;" type="submit" name="action" value="<bean:message key="ir.a.carpeta.personal"/>" />
						</form>
						<form action="<html:rewrite action="/DetallarODECU/DetallarODECU.do"/>?identificadorODE=${form.identificadorODE}&amp;idioma=${form.idiomaODE}&amp;mostrarVuelta=${form.mostrarVuelta}" method="post">
							<input class="boton_125_de_2" style="width:155px !important;" type="submit" name="action" value="<bean:message key="ir.a.carpeta.personal.mas.tarde"/>" />
						</form>
					</logic:notEqual>
				</fieldset>
			</logic:equal>	
					
			<!-- Fin Botones  -->			
			
</div>
<!-- Fin plantilla contenido  -->

</tiles:put>
</tiles:insert>

