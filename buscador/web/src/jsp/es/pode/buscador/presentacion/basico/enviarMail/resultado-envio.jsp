<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<tiles:insert definition="layout-sinlateral">

    <tiles:put name="title" type="string">
        <bean:message key="title.Comun"/>
    </tiles:put>

    <tiles:put name="body" type="string">
<%@ include file="/taglib-imports.jspf" %>

<section id="principal" class="resultados_busqueda">
	<article id="buscador">
		<article id="buscador_tipo">
			<section id="buscador_avanzado">
				<div id="separacion" class="tipofieldset clearfix">
				
					<form method="post" action="<html:rewrite action="/EnviarMailCU/ResultadoEnvioAceptar.do" />">
						<div class="botonera_especial">
							<strong><bean:message key="enviar.email.amigo.envio.correcto"/></strong>
							<input class="boton"  type="submit"  value="<bean:message key="previsualizar.conSinSecuencia.botonAceptar"/>" />
							<input name="idioma" value="${form.idioma}" type="hidden">					
							<input name="identificadorODE" value="${form.identificadorODE}" type="hidden">
							<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
							<input name="mostrarVuelta" value="${form.mostrarVuelta}" type="hidden">
							<input type="hidden" name="nodoOrigen" value="${form.nodoOrigen}"/>
							<input type="hidden" name="tieneIdentidadFederada" value="${form.tieneIdentidadFederada}"/>
						</div>
					</form>
		
				</div>		
			</section>
		</article>
	</article>
</section>




<!-- 
<div class="plantilla_contenido" >
	<jsp:include page="/layout/messages.jsp" flush="true" />
	<h2><bean:message key="enviar.email.amigo.boton.enviar"/></h2>
		<form method="post" action="<html:rewrite action="/EnviarMailCU/ResultadoEnvioAceptar.do" />">	

		<div class="globo_gris" >
			<div class="globo_gris_01">
				<div class="globo_gris_02">
					<div class="globo_gris_03">

					<div id="formulario" class="ali_c">
					<br />
					 <p><em class="correcto">
					    <bean:message key="enviar.email.amigo.envio.correcto"/>
					 </em></p>
					  
						<br />
						</div>

					</div>
				</div>
			</div>
		</div>


			<fieldset class="tipo ft_centrada">	
				<input class="boton_125"  type="submit"  value="<bean:message key="previsualizar.conSinSecuencia.botonAceptar"/>" />
				<input name="idioma" value="${form.idioma}" type="hidden">					
				<input name="identificadorODE" value="${form.identificadorODE}" type="hidden">
				<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
				<input name="mostrarVuelta" value="${form.mostrarVuelta}" type="hidden">
				<input type="hidden" name="nodoOrigen" value="${form.nodoOrigen}"/>
				<input type="hidden" name="tieneIdentidadFederada" value="${form.tieneIdentidadFederada}"/>
			</fieldset>

		</form>
</div>
 -->

</tiles:put>
</tiles:insert>
