<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tags/googleAnalytic.tld" prefix="analytic" %>

<tiles:insert definition="layout-comentariosODE">

    <tiles:put name="title" type="string">
        <bean:message key="title.Comun"/>
    </tiles:put>

<tiles:put name="body-principal" type="string">
	<%@ include file="/taglib-imports.jspf" %>


	<analytic:googleAnalytic />
	<!-- ## Para pintar los mensajes de error ## -->
	<jsp:include page="/layout/messages.jsp" flush="true" />


	<header>
	<h2>${form.titulo}</h2>	
	</header>
<!-- 
<h2><bean:message key="enviar.email.amigo.boton.enviar"/></h2>
-->	
	<form method="post" action="<html:rewrite action="/EnviarMailCU/EnvioMailAceptar.do"/>" >	
		
		<section>
			<p>
			<img src="${form.urlImagen}" class="imagenflotante"/> 
			${form.resumen}
			<br><br><br>
			</p>
		</section>
		<br>
		
		<section class="seccion clearfix" id="enviar_ode">
		
			<fieldset class="clearfix" id="flotante_fs">
			<html:checkbox styleId="Enviarcorreo" styleClass="boton_check" name="form" property="enviarRemitente" value="true"/>												
			<label for="Enviarcorreo" class="lb_long" ><bean:message key="enviar.email.amigo.enviar.tu.correo"/></label>
			</fieldset>
			
			<h3><bean:message key="enviar.email.amigo.boton.enviar"/></h3>
			
			<fieldset class="clearfix">
			<label for="CorreoE" ><bean:message key="enviar.email.amigo.correos.electronicos"/></label>
			<input name="correosHasta" value="${form.correosHasta}" class="de_texto"  id="CorreoE"  title="<bean:message key="enviar.email.amigo.correos.electronicos.tooltip"/>" type="text"/>
			</fieldset>
			
			<fieldset class="clearfix">
			<label for="Nombre"><bean:message key="enviar.email.amigo.nombres"/></label>
			<input name="nombresHasta" value="${form.nombresHasta}" class="de_texto"  id="Nombre"  title="<bean:message key="enviar.email.amigo.nombres.tooltip"/>" type="text"/>
			</fieldset>
			
			<fieldset class="clearfix">
			<label for="Tucorreo"><bean:message key="enviar.email.amigo.tu.correo"/></label>										
			<input name="correoDesde" value="${form.correoDesde}" class="de_texto"  id="Tucorreo"  title="<bean:message key="enviar.email.amigo.tu.correo.tooltip"/>" type="text" />
			</fieldset>
			
			<fieldset class="clearfix">
			<label for="Tunombre"><bean:message key="enviar.email.amigo.tu.nombre"/></label>
			<input name="nombreDesde" value="${form.nombreDesde}" class="de_texto"  id="Tunombre"  title="<bean:message key="enviar.email.amigo.tu.nombre.tooltip"/>" type="text"/>
			</fieldset>
			
			<fieldset class="clearfix">
			<label for="comentario" ><bean:message key="enviar.email.amigo.comentarios"/></label>
			<textarea name="resumen" id="comentario"  title="<bean:message key="enviar.email.amigo.resumen.tooltip"/>">${form.resumen}</textarea>
			</fieldset>
						
			<fieldset class="clearfix">
			<label for="captchaInput" >*Introduce el texto de la imagen</label>
		    <input id="captchaInput" name="captchaInput" type="text" class="de_texto" style="width: 200px;"/>
			</fieldset>
			<fieldset class="clearfix">
			<label for="captchaImage" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
			<img id="captchaImage" src="jcaptcha.jcaptcha" title="captcha" alt="captcha" class="input.de_texto" style="border: 1px solid #676767;"/>
			</fieldset>
			
			<!-- 
			</fieldset>
			<fieldset class="clearfix">
			<label for="captchaImage" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
			<img id="captchaImage" src="jcaptcha.jcaptcha" title="captcha" alt="captcha" style="
				background: none repeat scroll 0 0 #F9F9F9;
			    border: 1px solid #676767;
			    color: #151515;
			    font-size: 13px;
			    height: 70px;
			    margin-bottom: 4px;
			    padding: 0;
			    text-indent: 10px;
			    width: 200px;"/>
			</fieldset>
			-->
			
			<p style="font-size:90% !important">
				<em><bean:message key="enviar.email.amigo.normas.de.uso.titulo"/></em>
				<br />								
				<span>
					- <bean:message key="enviar.email.amigo.general.texto.obligacion"/>			
					<br />
					<bean:message key="enviar.email.amigo.normas.de.uso.cuerpo"/>
					<br />
					<bean:message key="enviar.email.amigo.normas.de.uso.cuerpo2"/>
				</span>
			</p>
		</section>
					
		
		<fieldset class="botonera botonera_de_dos clearfix">
			<input class="boton"  type="submit"  value="<bean:message key="enviar.email.amigo.boton.enviar"/>" />
			<input name="identificadorODE" value="${form.identificadorODE}" type="hidden">
			<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
			<input name="idioma" value="${form.idioma}" type="hidden">
			<input name="titulo" value="${form.titulo}" type="hidden">
			<input name="urlODE" value="${form.urlODE}" type="hidden">
			<input name="mostrarVuelta" value="${form.mostrarVuelta}" type="hidden">
			<input name="urlImagen" value="${form.urlImagen}" type="hidden">
			<input type="hidden" name="nodoOrigen" value="${form.nodoOrigen}"/>
			<input type="hidden" name="tieneIdentidadFederada" value="${form.tieneIdentidadFederada}"/>
			    
	</form>	
	
	<form method="post" action="<html:rewrite action="/EnviarMailCU/EnvioMailCancelar.do" />">	
			<input class="boton boton_flot"  type="submit"  value="<bean:message key="taxonomias.cancelar.seleccion"/>" />
			<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
			<input name="identificadorODE" value="${form.identificadorODE}" type="hidden">
			<input name="idioma" value="${form.idioma}" type="hidden">
			<input name="idiomaBuscador" value="${form.idiomaBuscador}" type="hidden">
			<input name="mostrarVuelta" value="${form.mostrarVuelta}" type="hidden">
			<input type="hidden" name="nodoOrigen" value="${form.nodoOrigen}"/>
			<input type="hidden" name="tieneIdentidadFederada" value="${form.tieneIdentidadFederada}"/>
		</fieldset>
	</form>

</tiles:put>
</tiles:insert>
