<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib uri="/WEB-INF/tags/serverProperties.tld" prefix="server" %>

<tiles:insert definition="layout-busqueda-sin-resultados">

	<tiles:put name="title" type="string">
	    <bean:message key="title.Comun"/>
	</tiles:put>

	<tiles:put name="body-principal" type="string">
		<%@ include file="/taglib-imports.jspf" %>
		
		<!-- 
		<h2><bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.resultadoBusqueda"/></h2>
		-->
		
	<!-- Inicio Error  -->
	<!-- <div class="parrafo_comun" id="separacion"> -->
	<div id="separacion" class="tipofieldset clearfix">
		<logic:notEmpty name="form"	property="quisoDecir">
			<bean:message key="listar.odecu.mostrar.resultados.quiso.quisoDecir"/>:  
				<logic:iterate name="form" property="quisoDecir" id="sugerencia">
					<a href="<html:rewrite action="/BuscarAvanzadoCU/MostrarQuisoDecirAvanzadoBuscarQuisoDecir.do"/>?buscContenido=${sugerencia}&idiomBusc=${form.idioma}&amp;formato=${form.formato}&amp;recurso=${form.recurso}&amp;procesoCognitivo=${form.procesoCognitivo}&amp;contexto=${form.contexto}&amp;edad=${form.edad}&amp;autor=${form.autor}&amp;diaPublic=${form.diaPublic}&amp;mesPublic=${form.mesPublic}&amp;anyoPublic=${form.anyoPublic}&amp;c_s_secuencia=${form.c_s_secuencia}&amp;valoracion=${form.valoracion}&amp;enlaceTaxSelec=${form.enlaceTaxSelec}&amp;enlaceComuSelec=${form.enlaceComuSelec}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;idTesauro=${form.idTesauro}&amp;nomTesauros=${form.nomTesauros}&amp;nivelAgreg=${form.nivelAgreg}&amp;tipoBusqueda=${form.tipoBusqueda}&amp;destinatarios=${form.destinatarios}">${sugerencia}</a> 
				</logic:iterate>
			.<br />
		</logic:notEmpty>
		
		<logic:notEmpty name="form" property="tesauros">
			<bean:message key="listar.odecu.mostrar.resultados.quiso.tesauros"/>
				<logic:iterate name="form" property="tesauros" id="taxon"> 
					<a href="<html:rewrite action="/BuscarAvanzadoCU/MostrarQuisoDecirAvanzadoQuisoDecir.do"/>?idTesauroSug=${taxon.identificador}&idiomBusc=${form.idioma}&amp;formato=${form.formato}&amp;recurso=${form.recurso}&amp;procesoCognitivo=${form.procesoCognitivo}&amp;contexto=${form.contexto}&amp;edad=${form.edad}&amp;autor=${form.autor}&amp;diaPublic=${form.diaPublic}&amp;mesPublic=${form.mesPublic}&amp;anyoPublic=${form.anyoPublic}&amp;c_s_secuencia=${form.c_s_secuencia}&amp;valoracion=${form.valoracion}&amp;enlaceTaxSelec=${form.enlaceTaxSelec}&amp;enlaceComuSelec=${form.enlaceComuSelec}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;idTesauro=${form.idTesauro}&amp;nomTesauros=${form.nomTesauros}&amp;nivelAgreg=${form.nivelAgreg}&amp;tipoBusqueda=${form.tipoBusqueda}&amp;destinatarios=${form.destinatarios}">${taxon.nombre}</a> 
				</logic:iterate>
			<br />
		</logic:notEmpty>
	
		<strong><bean:message key="listar.odecu.mostrar.resultados.quiso.sinResultados"/>:</strong>
		<ul id="error">
		<li><bean:message key="listar.odecu.mostrar.resultados.quiso.comprobarSintaxis"/></li>
		<li><bean:message key="listar.odecu.mostrar.resultados.quiso.otraPalabra"/></li>
		<li><bean:message key="listar.odecu.mostrar.resultados.quiso.sinonimo"/></li>
		</ul>
		
		</div>

<!-- </div> -->
<!-- Fin Error  -->

<!-- 
</form>
-->

<!-- 
</div>
</div>
fin plantilla contenido -->

    </tiles:put>

</tiles:insert>



<SCRIPT LANGUAGE="JavaScript">
function deshabilitarCombos(){
	var oComboIdioma = document.getElementById('idiomaBuscadorContenido');
	var oComboNumeroResultados = document.getElementById('numeroResultados');
	oComboIdioma.disabled=true;
	oComboNumeroResultados.disabled=true;
}

function habilitarCombos(){
	var oComboIdioma = document.getElementById('idiomaBuscadorContenido');
	var oComboNumeroResultados = document.getElementById('numeroResultados');
	oComboIdioma.disabled=false;
	oComboNumeroResultados.disabled=false;
}
</SCRIPT>