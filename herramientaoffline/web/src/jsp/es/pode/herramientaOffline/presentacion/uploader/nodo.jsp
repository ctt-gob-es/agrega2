<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<tiles:insert definition="layout-offline">

	<tiles:put name="title" type="string">
		<bean:message key="title.Comun" />
	</tiles:put>

	<tiles:put name="body" type="string">

<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- ATENCION!! RREMPLAZAR ESTA CAPA DE LA  PLANTILLA DE CONTENIDO ("plantilla_contenido") POR LA QUE SE DESEE EN LA PLANTILLA BASE  -->
<!-- Inicio plantilla contenido  -->



<div class="plantilla_contenido">
<jsp:include page="/layout/messages.jsp" flush="true" /> 


<h2><bean:message key="subir.odes"/></h2>

<form method="post" action="<html:rewrite action="/Uploader/NodoSubmit"/>" >


<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario" >
			<!--  INICIO GLOBO Blanco   -->

			<!--  INICIO GLOBO Blanco   -->
			<div class="globo_blanco" >
				<div class="globo_blanco_01">
					<div class="globo_blanco_02">
						<div class="globo_blanco_03">
						
					<h3><bean:message key="subir.datos"/></h3>
  					<!-- -->
  					<div class="fila_de_tabla">

  					<div class="contenedor_izquierda">
  						<div class="text"><label for="Usuario"><bean:message key="subir.datos.usuario"/></label></div>
 					</div>
					<div class="contenedor_derecha">
						<div class="text"><input name="usuario" value="${fn:escapeXml(form.usuario) }"  onblur="this.style.backgroundColor='#e1e1e1'" id="Usuario" type="text" title="<bean:message key="subir.usuario.tooltip"/>"  /></div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />

				</div>
				
					<div class="fila_de_tabla">

  					<div class="contenedor_izquierda">
  						<div class="text"><label for="Password"><bean:message key="subir.datos.clave"/></label></div>
 					</div>
					<div class="contenedor_derecha">
						<div class="text"><input name="passwd" value="${fn:escapeXml(form.passwd) }"  onblur="this.style.backgroundColor='#e1e1e1'" id="Password" type="password" title="<bean:message key="subir.clave.tooltip"/>"  /></div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />

				</div>
				<!-- -->
				<!-- -->
					
				
				<!-- -->
				</div>

		</div>
	</div>
</div>
<!--  Fin GLOBO Blanco   -->
<!--  Fin GLOBO Blanco   -->
</div>
				<!--  FIN CAJA DE FORMULARIO   -->
				
<!--  FIN CAJA DE FORMULARIO   -->
<div id="formulario" >
			<!--  INICIO GLOBO Blanco   -->

			<!--  INICIO GLOBO Blanco   -->
			<div class="globo_blanco" >
				<div class="globo_blanco_01">
					<div class="globo_blanco_02">
						<div class="globo_blanco_03">
						
					<h3><bean:message key="subir.urlOdes"/></h3>
  					<!-- -->
  					<div class="fila_de_tabla">

  					<div class="contenedor_izquierda">
  						<div class="text"><label for="Url"><bean:message key="subir.datos.url"/></label></div>
 					</div>
					<div class="contenedor_derecha">
						<div class="text"><input name="url" value="${fn:escapeXml(form.url) }"  onblur="this.style.backgroundColor='#e1e1e1'" id="Url" type="text" title="<bean:message key="subir.url.tooltip"/>"  /></div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />

				</div>
				<div class="fila_de_tabla">

  					<div class="contenedor_izquierda">
  						<div class="text"><label for="Puerto"><bean:message key="subir.datos.puerto"/></label></div>
 					</div>
					<div class="contenedor_derecha">
						<div class="text"><input name="puerto" value="${fn:escapeXml(form.puerto) }"  onblur="this.style.backgroundColor='#e1e1e1'" id="Puerto" type="text" title="<bean:message key="subir.puerto.tooltip"/>"  /></div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />

				</div>
					
				
				<!-- -->
				</div>

		</div>
	</div>
</div>
<!--  Fin GLOBO Blanco   -->
<!--  Fin GLOBO Blanco   -->
</div>
<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario_03" >
			<!--  INICIO GLOBO Blanco   -->

			<!--  INICIO GLOBO Blanco   -->
			<div class="globo_blanco" >
				<div class="globo_blanco_01">
					<div class="globo_blanco_02">
						<div class="globo_blanco_03">
						
					<h3><bean:message key="subir.tipoUpload"/></h3>
  					<!-- -->
				<div class="fila_de_tabla">

			
  				<!-- 	<div class="contenedor_izquierda">
  						<div class="text"><label for="TipoUpload"><bean:message key="subir.datos.tipoUpload"/></label></div>
 					</div> -->
					<div class="contenedor_derecha">
						<div class="text">
						
						<bean:message key="uploader.catalogador"/>
						<html:hidden property="tipoUpload" value="CATALOGADOR" />
						<!--<html:select  name="form" property="tipoUpload" titleKey="subir.tipo.tooltip" styleId="Editor">-->

						<!--<html:option value="CARPETA PERSONAL" styleClass="oscura"><bean:message key="uploader.carpetaPersonal"/></html:option>-->
						<!--<html:option value="CATALOGADOR" ><bean:message key="uploader.catalogador"/></html:option></html:select>-->
						</div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>	
				<!-- -->
				
				<!-- -->

				</div>
			</div>
		</div>
	</div>
<!--  Fin GLOBO Blanco   -->
<!--  Fin GLOBO Blanco   -->
</div>
<!--  FIN CAJA DE FORMULARIO   -->
			</div>
		</div>
	</div>

</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->


<!-- Inicio Botones  -->

<fieldset >
<input class="boton_125_de_2 tipo"  name="action" type="submit"  value="<bean:message key ="subir.Aceptar"/>" />
	


<input class="boton_125_de_2_izq tipo" name="action" type="submit"  value="<bean:message key="subir.Cancelar"/>" />

</fieldset>
	

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
		<!-- Fin plantilla CONTENIDO -->
	</tiles:put>

</tiles:insert>