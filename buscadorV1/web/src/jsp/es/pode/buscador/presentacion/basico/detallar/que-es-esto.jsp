<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>



<tiles:insert definition="layout-sinlateralYsinbuscadorSuperior">
    <tiles:put name="title" type="string">
        <bean:message key="title.Comun"/>
    </tiles:put>
    <tiles:put name="body" type="string">
		<%@ include file="/taglib-imports.jspf" %>


<div class="plantilla_contenido" >
<form method="post" action="<html:rewrite action="/DetallarODECU/QueEsEstoVolverDetalle"/>" id="popup">
<h2><bean:message key='listar.odecu.mostrar.resultados.detalles.queEsEsto'/></h2>

<div class="parrafo_comun" id="separacion"><bean:message key='listar.odecu.mostrar.resultados.detalles.explicacion'/>
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris conmargen" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
				<div><h3 class="icono_rss" id="ico_delicious"><bean:message key='listar.odecu.mostrar.resultados.detalles.del.iciou.us'/></h3>
				<p><bean:message key='listar.odecu.mostrar.resultados.detalles.explicacion.del.iciou.us'/></p>
				</div>
			<!--  FIN CAJA DE FORMULARIO   -->
				</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris conmargen" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
				<div><h3 class="icono_rss" id="ico_yahoo"><bean:message key='listar.odecu.mostrar.resultados.detalles.yahoo'/></h3>
				<p><bean:message key='listar.odecu.mostrar.resultados.detalles.explicacion.yahoo'/></p>
				</div>
			<!--  FIN CAJA DE FORMULARIO   -->
				</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris conmargen" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
				<div><h3 class="icono_rss" id="ico_digg"><bean:message key='listar.odecu.mostrar.resultados.detalles.digg'/></h3>
				<p><bean:message key='listar.odecu.mostrar.resultados.detalles.explicacion.digg'/></p>
				</div>
			<!--  FIN CAJA DE FORMULARIO   -->
				</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->

<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris conmargen" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
				<div><h3 class="icono_rss" id="ico_meneame"><bean:message key='listar.odecu.mostrar.resultados.detalles.meneame'/></h3>
				<p><bean:message key='listar.odecu.mostrar.resultados.detalles.explicacion.meneame'/></p>
				</div>
			<!--  FIN CAJA DE FORMULARIO   -->
				</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->

<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris conmargen" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
				<div><h3 class="icono_rss" id="ico_technorati"><bean:message key='listar.odecu.mostrar.resultados.detalles.technorati'/></h3>
				<p><bean:message key='listar.odecu.mostrar.resultados.detalles.explicacion.technorati'/></p>
				</div>
			<!--  FIN CAJA DE FORMULARIO   -->
				</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->
</div>

<!-- Inicio Botones  -->
<!-- Inicio Botones  -->

<logic:equal name="form" property="busquedaSimpleAvanzada" value="BUSQUEDA_ARBOL">
	<form action="/${initParam.url_detalle}?identificadorODE=${form.identificadorODE}&amp;idioma=${form.idioma}&amp;areaCurricularBusqueda=" method="post">
		<fieldset class="tipo ft_centrada">
			<input name="identificadorODE" value="${form.identificadorODE}" type="hidden">
			<input name="idioma" value="${form.idioma}" type="hidden">
			<input name="mostrarVuelta" value="${form.mostrarVuelta}" type="hidden">
			<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
			<input class="boton_125"  type="submit"  value="<bean:message key='listar.odecu.mostrar.resultados.detalles.volver'/>" />
		</fieldset>
	</form>
</logic:equal>
<logic:notEqual name="form" property="busquedaSimpleAvanzada" value="BUSQUEDA_ARBOL">
	<fieldset class="tipo ft_centrada">
		<input name="identificadorODE" value="${form.identificadorODE}" type="hidden">
		<input name="idioma" value="${form.idioma}" type="hidden">
		<input name="mostrarVuelta" value="${form.mostrarVuelta}" type="hidden">
		<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
		<input class="boton_125"  type="submit"  value="<bean:message key='listar.odecu.mostrar.resultados.detalles.volver'/>" />
	</fieldset>
</logic:notEqual>



<!-- Fin Botones  -->
<!-- Fin Botones  -->

</form>

</div>
<!-- Fin plantilla contenido  -->
<!-- Fin plantilla contenido  -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->


</div>
<!--  FIN CAPA MADRE   -->
<!--  FIN CAPA MADRE   -->
</tiles:put>
</tiles:insert>
