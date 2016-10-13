<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ include file="/taglib-imports.jspf" %>

<html:xhtml/>
<tiles:insert definition="layout-gestor-flujo-con-style">
<tiles:put name="title" type="string">
	<bean:message key="title.Comun"/>
</tiles:put>


<tiles:put name="body" type="string">




<!--		Inicio plantilla contenido		-->

<div class="plantilla_contenido">


<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />


<h2><bean:message key="odes.desasociarOdeGrupo.cabecera"/></h2>


<!--		Inicio del formulario principal		-->

<form id="desasociarFavoritoDesasociarFavoritoVolverForm" action='<html:rewrite action="/DesasociarOdeDeGrupo/ResultadoDesasociacionVolver"/>' method="post"  >

	<!--		INICIO GLOBO GRIS		-->
	
	<div class="globo_gris" >
		<div class="globo_gris_01">
			<div class="globo_gris_02">
				<div class="globo_gris_03">
					
					<input type="hidden" name="nombre" value="${form.nombre}"/>
					<!--		INICIO CAJA DE FORMULARIO		-->
					
					<div id="formulario" class="ali_c">
						<br />
						<logic:equal name="form" property="resultado" value="OK" >
							<p><em class="correcto"><bean:message key="odes.desasociarOdeGrupo.confirmacion2"/></em></p>
							<p>${form.titulo}</p>
							<p>${form.nombre}</p>
	                    </logic:equal>
						<logic:equal name="form" property="resultado" value="FALLO" >
                        	<p><em class="incorrecto"><bean:message key="odes.desasociarOdeGrupo.confirmacion22"/></em></p>
                        	<p>${form.titulo}</p>
							<p>${form.nombre}</p>
	                    </logic:equal>
						<br />
					</div>

					<!--		FIN CAJA DE FORMULARIO		-->
					
					
				</div>
			</div>
		</div>
	</div>
	
	<!--		FIN GLOBO GRIS		-->


	<!--		Boton aceptar		-->

	<input class="boton_125 tipo ft_centrada"  type="submit"  value="<bean:message key="usuarios.aceptar"/>" />

</form>

<!--		Fin formulario principal		-->


</div>

<!--		Fin de la capa plantilla_contenido		-->


</tiles:put>


</tiles:insert>