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


<h2><bean:message key="configurar.configurar"/></h2>

<form method="post" action="<html:rewrite action="/ConfigurarLdapCU/ConfigurarLdapAceptar"/>" >

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
						
					<h3><bean:message key="configurar.datosp"/></h3>
  					<!-- -->
  					<div class="fila_de_tabla">

  					<div class="contenedor_izquierda">
  						<div class="text"><label for="Login"><bean:message key="configurar.login"/></label></div>
 					</div>
					<div class="contenedor_derecha">
						<div class="text"><input name="login" value="${fn:escapeXml(form.login) }"  onblur="this.style.backgroundColor='#e1e1e1'" id="Login" type="text" title="<bean:message key="configurar.login.tooltip"/>"  /></div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />

				</div>
				
					<div class="fila_de_tabla">

  					<div class="contenedor_izquierda">
  						<div class="text"><label for="Nombre"><bean:message key="configurar.nombre"/></label></div>
 					</div>
					<div class="contenedor_derecha">
						<div class="text"><input name="nombre" value="${fn:escapeXml(form.nombre) }"  onblur="this.style.backgroundColor='#e1e1e1'" id="Nombre" type="text" title="<bean:message key="configurar.nombre.tooltip"/>"  /></div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />

				</div>
				<!-- -->
				<!-- -->
					<div class="fila_de_tabla">
  					<div class="contenedor_izquierda">
  						<div class="text"><label for="Apellidos"><bean:message key="configurar.apellidos"/></label></div>
 					</div>
					<div class="contenedor_derecha">

						<div class="text"><input name="apellidos"  value="${fn:escapeXml(form.apellidos) }"  onblur="this.style.backgroundColor='#e1e1e1'" id="Apellidos" type="text" title="<bean:message key="configurar.apellidos.tooltip"/>"  /></div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>
				<!-- -->
					<div class="fila_de_tabla">
  					<div class="contenedor_izquierda">
  						<div class="text"><label for="Email"><bean:message key="configurar.mail"/></label></div>

 					</div>
					<div class="contenedor_derecha">
						<div class="text"><input name="mail"  value="${fn:escapeXml(form.mail) }"  onblur="this.style.backgroundColor='#e1e1e1'" id="Email" type="text" title="<bean:message key="configurar.mail.tooltip"/>"  /></div>
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
				<!--  FIN CAJA DE FORMULARIO   -->
				
<!--  FIN CAJA DE FORMULARIO   -->
<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario_03" >
			<!--  INICIO GLOBO Blanco   -->

			<!--  INICIO GLOBO Blanco   -->
			<div class="globo_blanco" >
				<div class="globo_blanco_01">
					<div class="globo_blanco_02">
						<div class="globo_blanco_03">
						
					<h3><bean:message key="configurar.preferencias"/></h3>
  					<!-- -->
				<div class="fila_de_tabla">

  					<div class="contenedor_izquierda">
  						<div class="text"><label for="Editor"><bean:message key="configurar.editor"/></label></div>
 					</div>
					<div class="contenedor_derecha">
						<div class="text">
						<html:select  name="form" property="empaquetador" titleKey="configurar.editor.tooltip" styleId="Editor">
						<html:option value="avanzado" ><bean:message key="configurar.avanzado"/></html:option>
						<html:option value="basico" styleClass="oscura"><bean:message key="configurar.basico"/></html:option></html:select>
						</div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>					
				<!-- -->
				<div class="fila_de_tabla">
  					<div class="contenedor_izquierda">
  						<div class="text"><label for="Idiomadefecto"><bean:message key="configurar.idioma"/></label></div>
 					</div>
					<div class="contenedor_derecha">
						<div class="text">
						<html:select  name="form" property="idioma" titleKey="configurar.idioma.tooltip" styleId="Idiomadefecto">
						<html:optionsCollection name="form" property="idiomas" value="idLocalizacion" label="name"/>
						</html:select>
						</div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>
				<!-- -->
				<div class="fila_de_tabla">

  					<div class="contenedor_izquierda">
  						<div class="text"><label for="Catalogador"><bean:message key="configurar.catalogador"/></label></div>
 					</div>
					<div class="contenedor_derecha">
						<div class="text">
						<html:select  name="form" property="catalogador" titleKey="configurar.catalogador.tooltip" styleId="Catalogador">
						<html:option value="avanzado" ><bean:message key="configurar.avanzado"/></html:option>
						<html:option value="basico" styleClass="oscura"><bean:message key="configurar.basico"/></html:option></html:select>
						</div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>		
				<!-- -->
				<div class="fila_de_tabla">
  					<div class="contenedor_izquierda">
  						<div class="text"><label for="Visualizador"><bean:message key="configurar.visualizador"/></label></div>
 					</div>
					<div class="contenedor_derecha">
						<html:select name="form" property="visualizador" titleKey="configurar.visualizador.tooltip" styleId="Editor">
							<html:option key="usuarios.visualizador.agrega" value="agrega" />
							<html:option key="usuarios.visualizador.adl" value="adl" styleClass="oscura" />
						</html:select>
					</div>
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
<!--  FIN CAJA DE FORMULARIO   -->
			</div>
		</div>
	</div>

</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->


<!-- Inicio Botones  -->
<input class="boton_125_de_2"  type="submit"  value="<bean:message key ="comun.Aceptar"/>" />
	

	</form>
	<form method="post" action="<html:rewrite action="/ConfigurarLdapCU/ConfigurarLdapCancelar"/>">
		<input class="boton_125_de_2_izq"  type="submit"  value="<bean:message key="comun.Cancelar"/>" />
	</form>

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