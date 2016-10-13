<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html:xhtml/>
<tiles:insert definition="layout-administrador">

	<tiles:put name="title" type="string">
		<bean:message key="title.Admnistracion" />
	</tiles:put>
	<tiles:put name="body" type="string">
		<!-- Inicio plantilla contenido  -->
<div class="plantilla_contenido">


<h2>Crear/Modificar Colección</h2>
<form method="post" action="" >


<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" id="ficha">
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">

			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario" >
			<p class="parrafo_comun" >Introduzca un nombre para la colección que desea crear/modificar:</p>

			
			<!--  INICIO GLOBO AZUL   -->
			<!--  INICIO GLOBO AZUL   -->
			<div class="globo_blanco gb_ie" >
				<div class="globo_blanco_01">
					<div class="globo_blanco_02">

						<div class="globo_blanco_03">
						
					<div class="fila_de_tabla">
  					<div class="contenedor_izquierda">
  						<div class="text"><label for="nombreTarea">Nombre de Colección:</label></div>
 					</div>
					<div class="contenedor_derecha">
						<div class="text"><input name="nombreTarea" onfocus="limpiarTexto(this)" value="&nbsp;"  onblur="this.style.backgroundColor='#e1e1e1'" id="nombreTarea" type="text" title="Introduzca Nombre "  /></div>
					</div>

					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>
				<!-- -->

						</div>
					</div>
				</div>
		</div>

		<!--  FIN GLOBO AZUL   -->
		<!--  FIN GLOBO AZUL   -->

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
<fieldset class="tipo">
<input class="boton_125_de_2_izq"  type="button"  value="Cancelar" />
<input class="boton_125_de_2"  type="button"  value="Aceptar" />
</fieldset>
<!-- Fin Botones  -->
<!-- Fin Botones  -->
</form>
</div>
<!-- Fin plantilla contenido  -->
	</tiles:put>

</tiles:insert>
