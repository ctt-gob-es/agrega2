<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib uri="/WEB-INF/tags/serverProperties.tld" prefix="server" %>

<tiles:insert definition="layout-sinlateralYsinbuscadorSuperior">

    <tiles:put name="title" type="string">
        <bean:message key="title.Comun"/>
    </tiles:put>

    <tiles:put name="body" type="string">
    <%@ include file="/taglib-imports.jspf" %>
<div id="capa_madre">
<!-- Inicio plantilla contenido  -->
<div class="plantilla_contenido">

<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />

<h2><bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.resultadoBusqueda"/></h2>

<form method="post" action="<html:rewrite action="/BuscarAvanzadoCU/MostrarQuisoDecirAvanzadoBuscarQuisoDecir.do" />">

<!-- Inicio caja buscador -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario" >

  				<div class="fila_de_tabla">
  					<div class="contenedor_izquierda_00">
					&nbsp;<label class="oculto" for="buscContenido"><bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.buscador"/>:</label>
					</div>
					<div class="contenedor_derecha_00">
						<div class="text"><input name="buscContenido" value="${form.buscContenido}"  onblur="this.style.backgroundColor='#e1e1e1'" id="buscadorContenido" type="text" title="<bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.descripcionTextoBusqueda"/>"  /><br class="oculto" /><label class="oculto" for="idiomaBuscadorContenido"><bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.elijaIdioma"/>:</label>
						
						<html:select name="form" property="idiomBusc" styleId="idiomaBuscadorContenido" titleKey="listar.odecu.mostrar.resultados.consulta.cabecera.seleccionIdioma">
							<html:optionsCollection name="form"  property="idiomBuscBackingList" label="label" value="value"/>
						</html:select>
						<bean:define id="tituloSeleccioneNumeroResultados"><bean:message key="configurar.avanzado.atributo.seleccionar.numeroResultados"/></bean:define>
						<input type="hidden" name="busqSimpleAvanz" value="${form.busqSimpleAvanz}"/>
						<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
						<logic:equal name="form" property="tipoBusqueda" value="03">
							<input type="hidden" name="formato" value="${form.formato}"/>
							<input type="hidden" name="recurso" value="${form.recurso}"/>
							<input type="hidden" name="procesoCognitivo" value="${form.procesoCognitivo}"/>
							<input type="hidden" name="contexto" value="${form.contexto}"/>
							<input type="hidden" name="edad" value="${form.edad}"/>
							<input type="hidden" name="autor" value="${form.autor}"/>
							<input type="hidden" name="diaPublic" value="${form.diaPublic}"/>
							<input type="hidden" name="mesPublic" value="${form.mesPublic}"/>
							<input type="hidden" name="anyoPublic" value="${form.anyoPublic}"/>
							<input type="hidden" name="c_s_secuencia" value="${form.c_s_secuencia}"/>
							<input type="hidden" name="valoracion" value="${form.valoracion}"/>
							<input type="hidden" name="nivelAgreg" value="${form.nivelAgreg}"/>
							<input type="hidden" name="destinatarios" value="${form.destinatarios}"/>
							<!--<input type="hidden" name="areaCurricular" value="${form.areaCurricular}"/>-->
							<input type="hidden" name="enlaceTaxSelec" value="${form.enlaceTaxSelec}"/>
							<input type="hidden" name="idTesauro" value="${form.idTesauro}"/>
							<input type="hidden" name="nomTesauros" value="${form.nomTesauros}"/>
							<input type="hidden" name="enlaceComuSelec" value="${form.enlaceComuSelec}"/>	
						</logic:equal>
						<br class="oculto" /><input type="submit" alt="<bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.botonBuscar"/>"  class="buscar"  value="<bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.buscar"/>" name="buscar" /><br class="oculto" />
						<a href="<html:rewrite action="/BuscarAvanzadoCU/MostrarQuisoDecirAvanzadoMostrarAvanzadoQuisoDecir.do"/>?buscContenido=${form.buscContenEnlace}&amp;idiomBusc=${form.idioma}&amp;pagina=${form.anterior}&amp;formato=${form.formato}&amp;recurso=${form.recurso}&amp;procesoCognitivo=${form.procesoCognitivo}&amp;contexto=${form.contexto}&amp;edad=${form.edad}&amp;autor=${form.autor}&amp;diaPublic=${form.diaPublic}&amp;mesPublic=${form.mesPublic}&amp;anyoPublic=${form.anyoPublic}&amp;c_s_secuencia=${form.c_s_secuencia}&amp;valoracion=${form.valoracion}&amp;enlaceTaxSelec${form.enlaceTaxSelec}&amp;enlaceComuSelec=${form.enlaceComuSelec}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;idTesauro=${form.idTesauro}&amp;nomTesauros=${form.nomTesauros}&amp;nivelAgreg=${form.nivelAgreg}&amp;tipoBusqueda=${form.tipoBusqueda}&amp;destinatarios=${form.destinatarios}" class="avanzado"><bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.avanzado"/></a>
						<logic:equal name="form" property="tipoLayoutBuscador" value="NEUTRO">
							<a href="<rewrite:rewrite url="/TaggingWeb/InicioTagging/InicioTagging.do"/>" class="avanzado"><bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.etiquetadoSocial"/></a>
						</logic:equal>
						</div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>

				<!-- -->
				
					<div class="fila_de_tabla">
						<div class="contenedor_izquierda_00">
							&nbsp;<label class="oculto" for="todosNodos">Nodos:</label>
						</div>
						<div class="contenedor_derecha_00">
							<div class="text"><span class="vert"><bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.numeroResultados"/></span>&nbsp;&nbsp;
								<html:select name="form" property="numeroResultados" styleId="numeroResultados" title="${tituloSeleccioneNumeroResultados}" style="width:126px">
									<html:optionsCollection name="form"  property="numeroResultadosBackingList" label="label" value="value"/>
								</html:select>
							</div>
						</div>
						<div class="linea_separadora"></div>
							<br class="oculto" />
						</div>
						<logic:equal name="form" property="mostrarAmbito" value="true">	
		  					<div class="contenedor_izquierda_00">
							&nbsp;<label class="oculto" for="todosNodos"><bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.nodos"/>:</label>
							</div>
							<div class="contenedor_derecha_00">
								<span class="vert">
									<bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.buscarEn"/>
								</span>
								<html:radio property="tipoBusqueda" onclick="habilitarCombos();" styleClass="boton_radio" styleId="todosNodos" name="form" value="01" />
								<span class="vert">
									<bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.nodosTodos"/>
								</span>
								<html:radio property="tipoBusqueda" onclick="habilitarCombos();" styleClass="boton_radio marginadito" styleId="todosNodos" name="form" value="02" />
								<span class="vert">
									<server:serverProperties property="ccaa"/>
								</span>
								<logic:equal name="form" property="nodosSQI" value="true">
									<html:radio property="tipoBusqueda" onclick="deshabilitarCombos();" styleClass="boton_radio marginadito" styleId="todosNodos" name="form" value="04" />
			  							 <span class="vert">
	        		 						<bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.otros"/>
	   									 </span>
	   							</logic:equal>
								<logic:equal name="form" property="tipoBusqueda" value="03">
									&nbsp;&nbsp;&nbsp;
									<html:radio property="tipoBusqueda" onclick="habilitarCombos();" styleClass="boton_radio" styleId="Personalizada" name="form" value="03" />
		              				<span class="vert">
										<bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.personalizada"/>&nbsp;&nbsp;
									 </span>
								</logic:equal>
							</div>
							<div class="linea_separadora"></div>
							<br class="oculto" />
						</logic:equal>
					</div>
				<!-- -->
				</div>
				<!--  FIN CAJA DE FORMULARIO   -->
			</div>
		</div>

	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!-- Fin caja buscador -->
<!-- Inicio Error  -->
<div class="parrafo_comun" id="separacion">

		<logic:notEmpty name="form"	property="quisoDecir">
			<bean:message key="listar.odecu.mostrar.resultados.quiso.quisoDecir"/>:  
				<logic:iterate name="form" property="quisoDecir" id="sugerencia">
					<a href="<html:rewrite action="/BuscarAvanzadoCU/MostrarQuisoDecirAvanzadoBuscarQuisoDecir.do"/>?buscContenido=${sugerencia}&idiomBusc=${form.idioma}&amp;formato=${form.formato}&amp;recurso=${form.recurso}&amp;procesoCognitivo=${form.procesoCognitivo}&amp;contexto=${form.contexto}&amp;edad=${form.edad}&amp;autor=${form.autor}&amp;diaPublic=${form.diaPublic}&amp;mesPublic=${form.mesPublic}&amp;anyoPublic=${form.anyoPublic}&amp;c_s_secuencia=${form.c_s_secuencia}&amp;valoracion=${form.valoracion}&amp;enlaceTaxSelec=${form.enlaceTaxSelec}&amp;enlaceComuSelec=${form.enlaceComuSelec}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;idTesauro=${form.idTesauro}&amp;nomTesauros=${form.nomTesauros}&amp;nivelAgreg=${form.nivelAgreg}&amp;tipoBusqueda=${form.tipoBusqueda}&amp;destinatarios=${form.destinatarios}">${sugerencia}</a> 
				</logic:iterate>
			.<br />
		</logic:notEmpty>
		
		<logic:empty name="form" property="quisoDecir">
			<bean:message key="listar.odecu.mostrar.resultados.quiso.sinSugerencias"/><br />
		</logic:empty>
		
		<logic:notEmpty name="form" property="tesauros">
			<bean:message key="listar.odecu.mostrar.resultados.quiso.tesauros"/>
				<logic:iterate name="form" property="tesauros" id="taxon"> 
					<a href="<html:rewrite action="/BuscarAvanzadoCU/MostrarQuisoDecirAvanzadoQuisoDecir.do"/>?idTesauroSug=${taxon.identificador}&idiomBusc=${form.idioma}&amp;formato=${form.formato}&amp;recurso=${form.recurso}&amp;procesoCognitivo=${form.procesoCognitivo}&amp;contexto=${form.contexto}&amp;edad=${form.edad}&amp;autor=${form.autor}&amp;diaPublic=${form.diaPublic}&amp;mesPublic=${form.mesPublic}&amp;anyoPublic=${form.anyoPublic}&amp;c_s_secuencia=${form.c_s_secuencia}&amp;valoracion=${form.valoracion}&amp;enlaceTaxSelec=${form.enlaceTaxSelec}&amp;enlaceComuSelec=${form.enlaceComuSelec}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;idTesauro=${form.idTesauro}&amp;nomTesauros=${form.nomTesauros}&amp;nivelAgreg=${form.nivelAgreg}&amp;tipoBusqueda=${form.tipoBusqueda}&amp;destinatarios=${form.destinatarios}">${taxon.nombre}</a> 
				</logic:iterate>
			<br />
		</logic:notEmpty>
		
		<logic:empty name="form" property="tesauros">
			<bean:message key="listar.odecu.mostrar.resultados.quiso.sinTesauros"/><br />
		</logic:empty>
	
<bean:message key="listar.odecu.mostrar.resultados.quiso.sinResultados"/>:
<ul id="error">
<li><bean:message key="listar.odecu.mostrar.resultados.quiso.comprobarSintaxis"/></li>
<li><bean:message key="listar.odecu.mostrar.resultados.quiso.otraPalabra"/></li>
<li><bean:message key="listar.odecu.mostrar.resultados.quiso.sinonimo"/></li>
</ul>
</div>
<!-- Fin Error  -->


</form>
</div>
<!-- Fin plantilla contenido  -->
</div>
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