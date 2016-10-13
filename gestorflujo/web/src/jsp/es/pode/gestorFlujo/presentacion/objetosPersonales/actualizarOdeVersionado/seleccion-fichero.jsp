<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>
	<tiles:insert definition="layout-gestor-flujo-con-style">

    <tiles:put name="title" type="string">
        <bean:message key="title.Comun"/>
    </tiles:put>

    <tiles:put name="body" type="string">

<div class="plantilla_contenido">
<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />


	<h2><bean:message key="gestorFlujo.mostrarOdes.actualizar.ode"/></h2>
	
	<form method="post" action= "<html:rewrite action="/ActualizarOdeVersionadoCU/SeleccionFicheroSelectAction"/>?progressMonitor=myProgressMonitor" enctype="multipart/form-data" onsubmit="startLoading()">
	<input type="hidden" name="espacioLibre" value="${form.espacioLibre}"/>
	<input type="hidden" name="idODE" value="${form.idODE}"/>
	<p class="parrafo_comun" id="separacion2"><bean:message key = "gestorFlujo.actualizar.seleccione"/></p>
	<!--  INICIO GLOBO GRIS   -->
	<!--  INICIO GLOBO GRIS   -->
	<!--  INICIO GLOBO GRIS   -->
	<div class="globo_gris" >
		<div class="globo_gris_01">
			<div class="globo_gris_02">
				<div class="globo_gris_03">
				    <!--  INICIO CAJA DE FORMULARIO   -->
			
				<!--  INICIO CAJA DE FORMULARIO   -->
	
				<div class="formu" >
		
		
		<!--  INICIO ITEMS DE FORMULARIO   -->			
					<div class="fila_de_tabla">
	  					<div class="text">
	  					<label for="importarA01" class="red_cc">
	  					<html:file name="form" property="ficheroODE" onfocus="limpiarTexto(this)"style="width:50%" value="&nbsp;"  onblur="this.style.backgroundColor='#e1e1e1'" styleClass="nombreGrupo" titleKey= "gestorFlujo.importar.objeto"  />
	  				</div>
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
	<!-- Cargando  -->
	<fieldset class="tipo" id="con_loading">
	
	<input class="boton_125_de_2_izq" name="action"  type="submit"  value="<bean:message key="gestorFlujo.cancelar"/>" />

	
	<input class="boton_125_de_2" name="action"  type="submit"  value="<bean:message key ="gestorFlujo.aceptar"/>" />
	
	<!-- Fin Botones  -->
	<!-- Fin Botones  -->
	</fieldset>
	<div id="loading"  style="z-index:100 !important">
		<%@ include file="/progressBar/barra.jsp"%>
	</div>
	<%@ include file="/progressBar/cabecera-barra.jsp" %>
	</form>
	
    </div>

	</div>    <!-- Fin plantilla CONTENIDO -->
    

  
   </tiles:put>

</tiles:insert>



