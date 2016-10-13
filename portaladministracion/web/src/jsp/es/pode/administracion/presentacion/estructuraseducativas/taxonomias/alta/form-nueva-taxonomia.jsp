<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/tags/banderas.tld" prefix="flags" %>

<tiles:insert definition="layout-administrador">
<tiles:put name="title" type="string">
	<bean:message key="title.Comun"/>
</tiles:put>
<tiles:put name="body" type="string">
<%@ include file="/taglib-imports.jspf" %>

<!--************************          Inicio plantilla contenido       ****************************-->
<div class="plantilla_contenido">

<jsp:include page="/layout/messages.jsp" flush="true" />

 <h2> <bean:message key="estructuras.taxonomias.crear.titulo"/> </h2>
 
<form id="altaTaxonomiasFormNuevaTaxonomiaSubmitForm" 
	action="<html:rewrite action="/AltaTaxonomias/FormNuevaTaxonomiaSubmit"/>" method="post" enctype="multipart/form-data">


	<logic:iterate id="idioma" name="form" property="idiomasIso" indexId="index">


<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris uno_b" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
				<div class="caja_dinamica">
					<flags:banderas banderas="${idioma}" action="actionGloboEstructurasEducativas"/>
				</div>
				<br/>
				<div class="formu">
					<div class="fila_de_tabla" >
						<div class="contenedor_izquierda">
	  						<div class="text"><label for="fichero${index}"><bean:message key="estructuras.taxonomias.modi.nuevo"/></label></div>
	 					</div>
						<div class="contenedor_derecha">
							<div class="text">
								<input  name="fichero${index}"
										onfocus="limpiarTexto(this)"  
										value="&nbsp;"  
										onblur="this.style.backgroundColor='#e1e1e1'" 
										id="fichero${index}" 
										type="file" 
										style="width: 70%; background-color: rgb(225, 225, 225);"
										class="nombreGrupo" 
										title="<bean:message key="estructuras.taxonomias.seleccione"/>"  />
							</div>
						</div>
						<div class="linea_separadora"></div>
						<br class="oculto" />
					</div>	
				</div>
				<!--  FIN CAJA DE FORMULARIO   -->
			</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->
	
	
	
	</logic:iterate>





<!-- Inicio Botones  -->
<!-- Inicio Botones  -->
<input class="boton_125_de_2_izq"   type="submit" name="action"  value="<bean:message key="estructuras.cancelar"/>" />
<input class="boton_125_de_2"   type="submit" name="action"  value="<bean:message key="estructuras.aceptar"/>" />
<!-- Fin Botones  -->
<!-- Fin Botones  --> 

</form>


</div><!-- plantilla contenido -->
</tiles:put>
</tiles:insert>