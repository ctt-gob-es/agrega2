<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<html:xhtml/>


<tiles:insert definition="layout-administrador">

<tiles:put name="title" type="string">
      <bean:message key="title.Comun"/>
</tiles:put>

<tiles:put name="codigo-head" type="string">
<script type="text/javascript" src="<rewrite:rewrite url="static/js/prototype.js"/>"></script>
</tiles:put>

    <tiles:put name="body" type="string">
    
	<%@ include file="/taglib-imports.jspf" %>
	

		<!--************************             Inicio plantilla contenido         **************************  -->
		<div class="plantilla_contenido">
		
			<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
			<jsp:include page="/layout/messages.jsp" flush="true" />
 
	        <h2><bean:message key="crearTarea.cabecera2"/></h2>
	        
	        

	        <html:form styleId="crearTareaFormularioTareaDespublicarAceptarForm" action="/CrearTarea/FormularioTareaDespublicarAceptar?progressMonitor=myProgressMonitor" method="post" enctype="multipart/form-data" onsubmit="startLoading()">
				
				<!-- **********************         INICIO GLOBO GRIS        ***********************  -->
				<div class="globo_gris" >
					<div class="globo_gris_01">
						<div class="globo_gris_02">
							<div class="globo_gris_03">
								<!--  **********************      INICIO CAJA DE FORMULARIO         ***********************   -->
								<div id="formulario" >
								<h3><bean:message key="crearTarea.despublicacionMasiva"/></h3>
								<p class="parrafo_comun_02" id="sinmargen"><bean:message key="crearTarea.despublicacionMasiva.seleccionarArchivos"/></p>
								
									<!--  **********************         CAMPOS OCULTOS RECOGIDOS EN LA PANTALLA ANTERIOR      ******************-->
									
				                    <input type="hidden" name="trabajo" value="${form.trabajo}" />
									
				                    <input type="hidden" name="tipoTarea" value="${form.tipoTarea}" />
				                    	
									<input type="hidden" name="dia" value="${form.dia}" />
							   
									<input type="hidden" name="mes" value="${form.mes}" />
							  
									<input type="hidden" name="anio" value="${form.anio}" />
							 
									<input type="hidden" name="hora" value="${form.hora}" />
							 
									<input type="hidden" name="minutos" value="${form.minutos}" />
							 
									<input type="hidden" name="periodicidad" value="${form.periodicidad}" />
									
									<input type="hidden" name="msgPublicado" value="<bean:message key="crearTarea.msgPublicado"/>" />
									
									<input type="hidden" name="msgNoPublicado" value="<bean:message key="crearTarea.msgNoPublicado"/>" />
									
									<input type="hidden" name="msgDescCargaODEs" value="<bean:message key="crearTarea.msgDescCargaODEs"/>" />
									
									
									<!--		CAMPO IMAGEN		-->
											
									<div class="fila_de_tabla">
						  				<div class="text">
						  					<html:file name="archivo" accept="application/xls" styleId="selecImagen" property="archivo" onfocus="limpiarTexto(this)" style="width:50%" value="&nbsp;"  onblur="this.style.backgroundColor='#e1e1e1'" styleClass="nombreGrupo" titleKey="cambiar.imagen.seleccione.titleKey"  />
						  				</div>
									<br class="oculto" />
									</div>	
									
			
								</div>
								
								<!--  **********************      FIN CAJA DE FORMULARIO         ***********************   -->
								
							</div>
						</div>
					</div>
				</div>		
				<!-- **********************         FIN GLOBO GRIS        ***********************  -->
          
				<!--********************     Boton Aceptar      *********************  -->
					<fieldset class="tipo" id="con_loading">
					
				<bean:define id="aceptValue"><bean:message key="crearTarea.aceptar"/></bean:define>	
				<html:submit styleClass="boton_125_de_2 tipo" value="${aceptValue}" ></html:submit>
					
					
				</html:form>
				
				<!-- ********************     Fin Formulario     **************** -->
				
				
				<!-- ********************     Inicio Formulario secundario    **************** -->
				
				<form id="crearTareaFormularioTareaDespublicarCancelarForm" action="<html:rewrite action="/CrearTarea/FormularioTareaDespublicarCancelar"/>" method="post" >
				
				
					<!-- ********************     Boton cancelar    **************** -->
					
					<bean:define id="cancelValue"><bean:message key="crearTarea.cancelar"/></bean:define>
					<html:submit styleClass="boton_125_de_2_izq tipo" value="${cancelValue}"/>
					
				</form> 
				</fieldset>
	<div id="loading"  style="z-index:100 !important">
<%@ include file="/progressBar/barra.jsp"%>
</div>
<%@ include file="/progressBar/cabecera-barra.jsp" %>
				
				<!--********************     Fin formulario secundario      *********************  -->
		  
			


		</div>
		<!--************************             Fin plantilla contenido         **************************  -->
		
				
    </tiles:put>

</tiles:insert>














