<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri="/WEB-INF/tlds/tags-modificadorWeb.tld"  prefix="mod" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<tiles:insert definition="${sessionScope.offline == true ? 'layout-offline' : 'layout-administrador'}">

<tiles:put name="title" type="string">
      <bean:message key="title.Comun"/>
</tiles:put>

<tiles:put name="body" type="string">

 
<!-- *****************************   Inicio  Plantilla contenido      ********************************** -->
<div class="plantilla_contenido">

<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />


<h2><bean:message key="tipoAnadir.titulo"/></h2>
<form id="tipoAnadirTipoAnadirSelectActionForm" action="<html:rewrite action="/TipoAnadir/TipoAnadirSelectAction"/>"  method="post">

<p class="parrafo_comun" id="separacion2"><bean:message key="tipoAnadir.instrucciones"/>.</p>
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario" >

			 <div class="fila_de_tabla">
  				<div class="text"><span class="displayed"><bean:message key="tipoAnadir.alcanceMetadatos"/>:</span>						
  					<html:radio styleClass="boton_radio" styleId="CambioMetadato01" name="form" property="alcance" value="true" /><label for="CambioMetadato01" class="alineada"><bean:message key="tipoAnadir.soloCatalogacionPrincipal"/></label>
  				</div>
				<div class="linea_separadora"></div>
					<br class="oculto" />
			</div>
			<!--     -->
			<div class="fila_de_tabla">
  				<div class="text"><span class="displayed">&nbsp;</span>
  					<html:radio styleClass="boton_radio" styleId="CambioMetadato02" name="form" property="alcance" value="false" /><label for="CambioMetadato02"  class="alineada2"><bean:message key="tipoAnadir.todosLosMetadaros"/></label>
  				</div>
				<div class="linea_separadora"></div>
					<br class="oculto" />
			</div>
			<!--     -->
						
			<div class="fila_de_tabla">
  				<div class="text"><span class="displayed"><bean:message key="tipoAnadir.tipo"/>:</span>
  					<html:radio styleClass="boton_radio" styleId="taxon" name="form" property="cambiotipos" value="taxonomia" /><label for="taxon"  class="alineada" ><bean:message key="tipoAnadir.taxonomia"/>:</label></div>
				<div class="linea_separadora"></div>
				<br class="oculto" />
			</div>
			<!--     -->
			
			<div class="fila_de_tabla">
  						<div class="text"><span class="displayed">&nbsp;</span><label for="Fuente" class="alineada2 lbl_sin" style="vertical-align:bottom;padding-left:20px" id="fuent"><bean:message key="tipoAnadir.fuente"/>:&nbsp;</label>
  						<html:select name="form"  property="tipoAniadir" titleKey="tipoAnadir.tipo" styleId="taxonomia" styleClass="slct_medio" >

								<html:optionsCollection name="form"  property="taxonomiaBackingList" label="label" value="value"/>
						</html:select>
						</div>
				<div class="linea_separadora"></div>
				<br class="oculto" />
				</div>
					
				<div class="fila_de_tabla">
  						<div class="text"><span class="displayed">&nbsp;</span><label for="Proposito"  class="lbl_sin_02"><bean:message key="tipoAnadir.proposito"/>:</label>
  						<html:select name="form"  property="proposito" titleKey="tipoAnadir.proposito" styleId="proposito" styleClass="slct_medio">
								<html:optionsCollection name="form"  property="propositoBackingList" label="label" value="value"/>
						</html:select></div>
				<div class="linea_separadora"></div>
				<br class="oculto" />
				
			<!--     -->
			<div class="fila_de_tabla">
				<div class="text"><span class="displayed">&nbsp;</span>
				<html:radio styleClass="boton_radio" styleId="otro" name="form" property="cambiotipos" value="otro" /><label for="Otros"  class="alineada2" ><bean:message key="tipoAnadir.otro"/>:</label>
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
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->



<!-- Inicio Botones  -->
<!-- Inicio Botones  -->


<fieldset class="tipo ft_centrada">
<input class="boton_125_de_2" name="action" type="submit"  value="<bean:message key="comun.continuar"/>" />
<input class="boton_125_de_2_izq"  type="submit"  name="action" value="<bean:message key="comun.volver"/>" />
<input class="boton_125"  type="submit" name="action" value="<bean:message key="export.cancelar"/>" />
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



    </tiles:put>

</tiles:insert>
