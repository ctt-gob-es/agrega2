<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
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
	<form method="post" action="<html:rewrite action="/DespublicarCargasEjecutadas/BrevesMomentosAceptar"/>"> 
		<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<input type="hidden" name="entrada" value="${form.entrada}"/>
					<input type="hidden" name="nombreTarea" value="${form.nombreTarea}"/>	
					<input type="hidden" name="nombreLote" value="${form.nombreLote}"/>
					<input type="hidden" name="fechaFin" value="${form.fechaFin}"/>
					<input type="hidden" name="id" value="${form.id}"/>
			<div id="formulario" class="ali_c">
			<br />
			 <p><em class="corrector"><bean:message key="despublicar.breves.momentos" /> <a href="<html:rewrite action="/ListarTareasEjecutadas/ListarTareasEjecutadas.do"/>"><bean:message key="despublicar.breves.momentos.aqui" />.</a></em></p>
				<br />
				</div>

				<!--  FIN CAJA DE FORMULARIO   -->
			</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->	
<input class="boton_125 tipo ft_centrada"  type="submit"  value="<bean:message key="usuarios.aceptar"/>" />	
	</form>
	</div>
</div>

</div>
<!--		Fin de la capa plantilla_contenido		-->

</tiles:put>


</tiles:insert>