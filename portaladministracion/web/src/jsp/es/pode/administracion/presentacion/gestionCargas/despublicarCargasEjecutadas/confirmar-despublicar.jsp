<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html:xhtml/>

<tiles:insert definition="layout-administrador">

<tiles:put name="title" type="string">
      <bean:message key="title.Comun"/>
</tiles:put>


<tiles:put name="body" type="string">



<!--		Inicio plantilla contenido		-->

<div class="plantilla_contenido">


<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />

<!--		Inicio  PESTANIAS de FICHA		 -->
	<div id="ficha_pestanias">
		<div><h2><bean:message key="cabecera.cargas.contenidos"/></h2></div>
		<ul>
			<li id="pest_activa"><a href="<html:rewrite action="/ListarCargasEjecutadas/ListarCargasEjecutadas.do"/>" id="seleccionada"><bean:message key="cargas.pestania.listado"/></a></li>
			<li><a href="<html:rewrite action="/ListarInformesCarga/ListarInformesCarga.do"/>"><bean:message key="informes.pestania.listado" /></a></li>
			
		</ul>
	</div>
	<!--		Fin  PESTANIAS de FICHA		-->
	<!--		Inicio CONTENIDO PESTANIAS		-->

	<div class="interno_ficha">
	<div class="plantilla_contenido_pestanias">
	<form method="post" name="despublicarCargasEjecutadasConfirmarDespublicarDecidirForm" action="<html:rewrite action="/DespublicarCargasEjecutadas/ConfirmarDespublicarDecidir"/>"> 
		<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
					
					
					<input type="hidden" name="entrada" value="${form.entrada}"/>
					<input type="hidden" name="nombreTarea" value="${form.nombreTarea}"/>	
					<input type="hidden" name="nombreCarpeta" value="${form.nombreCarpeta}"/>
					<input type="hidden" name="nombreLote" value="${form.nombreLote}"/>
					<input type="hidden" name="fechaFin" value="${form.fechaFin}"/>
					<input type="hidden" name="id" value="${form.id}"/>
					<input type="hidden" name="id_mec" value="${form.id_mec}"/>
					<input type="hidden" name="nombreZip" value="${form.nombreZip}"/>
					
					<div id="formulario" class="ali_c">
					
					<br />
						<c:choose>
					 	 <c:when test="${!(form.nombreCarpeta eq null) && (form.nombreCarpeta !='') }">
					 	 <p><em class="corrector"><bean:message key="despublicar.eliminar.carpeta.confirmacion"/></em></p>
							
							<p>${form.nombreCarpeta}</p>
					 	 </c:when>
					 	 <c:when test="${!(form.nombreZip eq null) && (form.nombreZip !='') }">
					 	 <p><em class="corrector"><bean:message key="despublicar.eliminar.zip.confirmacion"/></em></p>
							
							<p>${form.nombreZip}</p>
					 	 </c:when>
					 	 <c:otherwise>
					 	 <p><em class="corrector"><bean:message key="despublicar.eliminar.lote.confirmacion"/></em></p>
							
							<p>${form.nombreTarea}</p>
					 	 </c:otherwise>
					</c:choose>
					<br />
				</div>

				<!--  FIN CAJA DE FORMULARIO   -->		
						
						
						
				</div>
			</div>
		</div>
	</div>
	
	<!--		FIN GLOBO GRIS		-->



	<!-- Inicio Botones  -->

	<input class="boton_125_de_2 tipo" name="accionAceptar" type="submit"  value="<bean:message key='usuarios.aceptar'/>" />
	<input class="boton_125_de_2_izq tipo" name="accionCancelar" type="submit"  value="<bean:message key='usuarios.cancelar'/>" />
    	
	</form>
	</div>
</div>

<!--		Fin de la capa plantilla_contenido		-->
</div>
</tiles:put>


</tiles:insert>