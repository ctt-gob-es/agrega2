<%@ include file="/taglib-imports.jspf" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<tiles:insert definition="layout-principal">



<tiles:put name="title" type="string">
	<bean:message key="title.Comun"/> - ${fn:escapeXml(visualizadorSession.tituloOde)}
</tiles:put>



<tiles:put name="body" type="string">
<!-- CONTENIDO -->
					
<!-- Panel contenidos!! -->
<div id="capa_contenidos" name="capa_contenidos">
<div id="contenido" name="contenido" style="margin-left: 0pt; min-width: 520px; min-height: 440px; height: 223px; width: 530px;">


<!-- Inicio plantilla contenido  -->
<!-- Inicio plantilla contenido  -->
<div class="plantilla_contenido" id="contenido_it" style="position:relative;">
<div id="contenido_externo">

<jsp:include page="/layout/messages.jsp" flush="true" />


<h2 align="left"><bean:message key="estadisticas.titulo"/></h2>
<form method="post" action="<html:rewrite action="/Estadisticas/EstadisticasSubmit"/>" >
			
										
										
										
										
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_blanco" >
	<div class="globo_blanco_01">
		<div class="globo_blanco_02">
			<div class="globo_blanco_03" >
			
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario" style="padding-bottom:5px;"   >
			 <!--  INICIO GLOBO Blanco   -->
			<!--  INICIO GLOBO Blanco   -->
			
			
			
			
			<!--*******************  NUMERO VECES PREVISUALIZADO  *************************-->
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris_bis" >
	<div class="globo_gris_01_bis">
		<div class="globo_gris_02_bis">
			<div class="globo_gris_03_bis">
			<table border="0" class="res_list_inv" cellpadding="0" cellspacing="0" summary="Previsualizado">
																	<tr class="tr_blanco">
																		<td valign="top" class="dere"><span><bean:message key="estadisticas.previsualizado"/>:</span></td>	
																		<td valign="top" align="right" class="dere1 alineado_der"><em>${form.estVecesPrevisualizado}</em></td>
																	</tr>
			</table>
			</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->

			<!--*******************  NUMERO VECES CONSULTADO  *************************-->
												<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris_bis conmargen" >
	<div class="globo_gris_01_bis">
		<div class="globo_gris_02_bis">
			<div class="globo_gris_03_bis">
			<table border="0" class="res_list_inv" cellpadding="0" cellspacing="0" summary="Consultado">
																	<tr class="tr_blanco">
																		<td valign="top" class="dere"><span><bean:message key="estadisticas.consultado"/>:</span></td>	
																		<td valign="top" align="right" class="dere1 alineado_der"><em>${form.estVecesConsultado}</em></td>
																	</tr>
			</table>
			</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->

<!--*******************  NUMERO VECES DESCARGADO  *********************-->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris_bis conmargen" >
	<div class="globo_gris_01_bis">
		<div class="globo_gris_02_bis">
			<div class="globo_gris_03_bis">
			<table border="0" class="res_list_inv" cellpadding="0" cellspacing="0" summary="Descargado">
																	<tr class="tr_blanco">
																		<td valign="top" class="dere"><span><bean:message key="estadisticas.descargado"/>:</span></td>	
																		<td valign="top" align="right" class="dere1 alineado_der"><em>${form.estVecesDescargado}</em></td>
																	</tr>
			</table>
			</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->

<!--**********************  NUMERO VECES ENVIADO  *********************-->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris_bis conmargen" >
	<div class="globo_gris_01_bis">
		<div class="globo_gris_02_bis">
			<div class="globo_gris_03_bis">
			<table border="0" class="res_list_inv" cellpadding="0" cellspacing="0" summary="Enviado">
																	<tr class="tr_blanco">
																		<td valign="top" class="dere"><span><bean:message key="estadisticas.enviado"/>:</span></td>	
																		<td valign="top" align="right" class="dere1 alineado_der"><em>${form.estVecesEnviado}</em></td>
																	</tr>
			</table>
			</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->


<!--**********************  NUMERO VECES EMBED  *********************-->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris_bis conmargen" >
	<div class="globo_gris_01_bis">
		<div class="globo_gris_02_bis">
			<div class="globo_gris_03_bis">
			<table border="0" class="res_list_inv" cellpadding="0" cellspacing="0" summary="Embed">
																	<tr class="tr_blanco">
																		<td valign="top" class="dere"><span><bean:message key="estadisticas.embed"/>:</span></td>	
																		<td valign="top" align="right" class="dere1 alineado_der"><em>${form.estVecesEmbed}</em></td>
																	</tr>
			</table>
			</div>
		</div>
	</div>
</div>

<!--  FIN GLOBO GRIS   -->




											</div>
										</div>
									</div>
								</div>
								<!--  Fin GLOBO Blanco   -->
	</div>
										
										
										
										
										
										

</form>
			
			<div class="linea_separadora">
				<br class="oculto">
			</div>
</div>


</div>

</div>
</div>

<script type="text/javascript">
//<![CDATA[
	$("#contenido_central_largo").contenido({
		ancho_menu:430,
		inicio_menu_desplegado:'false',
		boton_menu:'#enlace_menu',
		contenedor_menu:'#panel_menu',
		contenedor_frame:'#contenido'
		}
	);
//]]>
</script>


</tiles:put>
</tiles:insert>
