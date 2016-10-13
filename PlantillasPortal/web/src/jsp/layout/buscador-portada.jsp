<%@ include file="/taglib-imports.jspf"%>

<tiles:importAttribute name="idiomasBuscablesPlataforma" scope="page"/>
<tiles:importAttribute name="localizacionBusqueda" scope="page"/>
<tiles:importAttribute name="existenNodosSQI" scope="page"/>
<%@ taglib uri="/WEB-INF/tags/serverProperties.tld" prefix="server" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<html:xhtml/>


<!-- INICIO BUSCADOR AGREGA -->
<!--
<div id="buscador">
<hr />
<div id="nuevo_buscador">
<strong class="oculto"><bean:message key="buscador.buscador" /></strong>
<br class="oculto" />
<br class="oculto" />
<form method="get" action="<rewrite:rewrite url="${initParam.url_buscador}"/>">
<fieldset>
<a href="<rewrite:rewrite url="${initParam.url_formularioAvanzado}"/>" class="avanzado"><bean:message key="buscador.avanzado" /></a>
<label class="oculto" for="buscadorGeneral"><bean:message	key="buscador.buscador" />:&nbsp;</label> 
<input type="text" autocomplete="off" id="buscadorGeneral" class="caja_buscador ac_input" value="" title="Buscador" name="buscadorGeneral"  /><br  class="oculto" />
<bean:define id="i18nValue"><bean:message key="buscador.idioma.por.defecto"/></bean:define>
<label class="oculto" for="Idioma"><bean:message key="buscador.idioma.del.contenido"/>:&nbsp;</label>
<c:choose>
<c:when test="${!empty idiomasBuscablesPlataforma}">
   <select name="idiomBusc" onchange="cambiarIdioma();" class="caja_buscador_selector" title="<bean:message key="buscador.idioma.del.contenido"/>" id="Idioma" >
		<option value="#" selected="selected" ><bean:message key="buscador.idioma.del.contenido"/></option>
		<logic:iterate name="idiomasBuscablesPlataforma" id="idiomaItem">
		     <option value="<bean:write name="idiomaItem" property="idLocalizacion"/>" ><bean:write name="idiomaItem" property="nombre"/></option>
		</logic:iterate>
   </select>
</c:when>
<c:otherwise>
	<select name="idiomBusc" class="caja_buscador_selector" title="${i18nValue }"/>" id="Idioma" >
		<option value="#" selected="selected" ><bean:message key="buscador.idioma.del.contenido"/></option>
   </select>
</c:otherwise>
</c:choose>

<input type="submit" alt="<bean:message key="buscador.boton.buscar.alt"/>" class="buscar" value="<bean:message key="buscador.buscador.boton"/>" name="buscar" /><br />
<div class="ren">	
	<label class="lb_primera"><bean:message key="buscador.radio.buscar.en" /></label>
	<html:radio property="tipoBusqueda" onclick="aplicarCambios('01');" styleClass="botonradio" styleId="todo_agrega" name="localizacionBusqueda" value="01" />
	<label for="todo_agrega"><bean:message	key="buscador.radio.buscar.en.red" /></label>
	<html:radio property="tipoBusqueda" onclick="aplicarCambios('02');" styleClass="botonradio" styleId="comunidad" name="localizacionBusqueda" value="02" />
	<label for="comunidad"><server:serverProperties property="ccaa"/></label>
	<c:choose>
		<c:when test="${!empty existenNodosSQI}">
			<c:choose>
				<c:when test="${existenNodosSQI}">
					<html:radio property="tipoBusqueda" onclick="aplicarCambios('04');" styleClass="botonradio" styleId="otros" name="localizacionBusqueda" value="04" />
					<label for="otros"><bean:message key="buscador.radio.buscar.en.otros" /></label>
				</c:when>
			</c:choose>
		</c:when>
	</c:choose>
</div>

</fieldset>
</form>
</div>
<br class="oculto" />
</div>
<hr />
-->
<!-- FIN BUSCADOR AGREGA -->






<!-- INICIO BUSCADOR AGREGA 2 -->
<!--<article id="buscador">-->
    <section>
    <form method="get" action="<rewrite:rewrite url="${initParam.url_buscador}"/>">
	<span class="enlace_f"><a href="<rewrite:rewrite url="${initParam.url_formularioAvanzado}"/>"><bean:message key="buscador.avanzado" /></a></span>
	<fieldset id="caja_buscador">
    	<div>
			<input type="submit" alt="<bean:message key="buscador.boton.buscar.alt"/>" class="boton_submit" value="<bean:message key="buscador.buscador.boton"/>" name="buscar" />		
    		<label for="busc_contenidos"><bean:message	key="buscador.textinput" /></label>
			
			<!--<input onfocus="clearText(this)" type="text" autocomplete="off" id="buscadorGeneral" class="buscador" value="Buscar Contenidos Agrega" title="Buscador" name="buscContenido"  />-->
			<!--
			<input onkeypress="searchKeyPress(event);" onfocus="clearText(this)" type="text" autocomplete="off" id="buscadorGeneral" class="buscador" value="<bean:message key='buscador.textinput'/>" title="Buscador" name="buscadorGeneral"/>
			-->
			<input onkeypress="searchKeyPress(event);" type="text" autocomplete="off" id="buscadorGeneral" class="buscador" value="" title="Buscador" name="buscadorGeneral"/>
			
			<bean:define id="i18nValue"><bean:message key="buscador.idioma.por.defecto"/></bean:define>
			<label for="idioma"><bean:message key="buscador.idioma.del.contenido"/>:&nbsp;</label>
			<span class="caja_de_boton">		
			<c:choose>
			<c:when test="${!empty idiomasBuscablesPlataforma}">
				<!--<select name="idioma" onchange="cambiarIdioma();" class="select" title="<bean:message key="buscador.idioma.del.contenido"/>" id="idioma" >-->
				<select name="idiomBusc" class="select" title="<bean:message key="buscador.idioma.del.contenido"/>" id="Idioma" >
					<option value="#" selected="selected" ><bean:message key="buscador.idioma.del.contenido"/></option>
					<logic:iterate name="idiomasBuscablesPlataforma" id="idiomaItem">
						 <option value="<bean:write name="idiomaItem" property="idLocalizacion"/>" ><bean:write name="idiomaItem" property="nombre"/></option>
					</logic:iterate>
				</select>
			</c:when>
			<c:otherwise>
				<!--<select name="idioma" class="select" title="${i18nValue}"/>" id="idioma" >-->
				<select name="idiomBusc" class="select" title="${i18nValue }"/>" id="Idioma" >
					<option value="#" selected="selected" ><bean:message key="buscador.idioma.del.contenido"/></option>
				</select>
			</c:otherwise>
			</c:choose>
    		</span>
		</div>
	</fieldset>
	<fieldset class="radios">
		<html:radio property="tipoBusqueda" onclick="aplicarCambios('01');" styleClass="botonradio" styleId="todo_agrega" name="localizacionBusqueda" value="01" />
		<label for="todo_agrega"><bean:message	key="buscador.radio.buscar.en.red" /></label>
		<html:radio property="tipoBusqueda" onclick="aplicarCambios('02');" styleClass="botonradio" styleId="nodo_cca_aa" name="localizacionBusqueda" value="02" />
		<label for="nodo_cca_aa"><server:serverProperties property="ccaa"/></label>
	</fieldset>				
	</form>
    </section>
<!--</article>-->
<!-- FIN BUSCADOR AGREGA 2 -->



<br>
<div class="tipofieldset clearfix" 
     style="background: none repeat scroll 0 0 #EFF7FF;
    border-radius: 5px 5px 5px 5px;
    margin: 0 auto 20px;
    padding: 10px 10px 1px;
    text-align: center;
    width: 682px;">
	
	<h2 style="color: #858585;
		font-family: Georgia,Arial,'Times New Roman',Verdana,serif;
		font-size: 16px;
		padding: 0 0 10px;">
		
		<!--
		Prueba el prototipo del nuevo Agrega Semántico.<br>
		Disfruta ya de su nueva interfaz y de su buscador mejorado sobre el nodo INTEF.
		-->
		
		Visita el nuevo Espacio Procomún Educativo que integra un buscador mejorado de recursos educativos en Agrega y en el que podrás participar en la nueva Red Social Docente
		
		<div id="nuevo_buscador">
		<div class="botonera_especial">
			<!--
			<form method="get" target="_blank" action="http://agregasemantico.educalab.es">
			-->
			<form method="get" target="_blank" action="http://procomun.educalab.es">
			<input style="background: -moz-linear-gradient(center top , #CD2423, #9D0303) repeat scroll 0 0 rgba(0, 0, 0, 0) !important;
				border: 1px solid #890000;
				color: #FFFFFF !important;
				display: block !important;
				float: none;
				font-size: 12px;
				font-weight: bold;
				height: 25px;
				line-height: 21px;
				margin: 5px auto;
				text-align: center;
				width: 140px;"
				class="boton" type="submit" value="Acceder">
			</form>
		</div>
		</div>
	</h2>
</div>



<!--
<br class="oculto" />
<a href="<rewrite:rewrite url="${initParam.url_portada}"/>" title="<bean:message key="buscador.pagina.inicio"/>" id="logo"><span><bean:message key="buscador.pagina.inicio" /></span></a>
<br class="oculto" />
-->
<input type="hidden" id="url" value="<rewrite:rewrite url="/buscador2/SugerirBusquedaCU/SugerirBusquedaCU.do"/>"/>

<script type="text/javascript">


/* Estan comentados porque el autocompleter estropea los estilos del cuadro de busqueda

function findValue(li) {
	if( li == null ) return alert("&iexcl;No hay t&eacute;rminos con esta Inicial");
	if( !!li.extra ) var sValue = li.extra[0];
	else var sValue = li.selectValue;
}

function selectItem(li) {
	findValue(li);
}

function formatItem(row) {
	return row[0] + " (id: " + row[1] + ")";
}

function lookupAjax(){
	var oSuggest = jQuery("#buscadorGeneral")[0].autocompleter;

	oSuggest.findValue();

	return false;
}

function lookupLocal(){
	var oSuggest = jQuery("#buscadorGeneral")[0].autocompleter;

	oSuggest.findValue();

	return false;
}


jQuery(document).ready(function() {
	configAutocomplete(document.getElementById('url').value,document.getElementById('Idioma').value)
});


function configAutocomplete(url,idioma){
	jQuery("#buscadorGeneral").autocomplete(url, {
		delay:100,
		minChars:1,
		matchSubset:1,
		onItemSelect:selectItem,
		onFindValue:findValue,
		autoFill:true,
		maxItemsToShow:10,
		extraParams: {idiomaBusc: idioma}
	});
}		
*/


function aplicarCambios(tipoBusqueda){
/*		Comentado por que ya no se usa en el buscador de Agrega2

		if(tipoBusqueda == '04'){//busqueda en repositorios externos
			document.getElementById('Idioma').disabled=true;//desactivar combo idioma
		}
		else{
*/			document.getElementById('Idioma').disabled=false;//activar combo idioma
//		}
}


function cambiarIdioma(){
	jQuery("#buscadorGeneral")[0].autocompleter.flushCache();
	jQuery("#buscadorGeneral")[0].autocompleter.setExtraParams({idiomaBusc: document.getElementById('Idioma').value})
}


//Code to allow launch the form pressing enter 
//document.onkeypress = searchKeyPress;
function searchKeyPress(e) {
/*	if (null == e)
		e = window.event ;
	if (e.keyCode == 13) {
		submitForm() ;
	}
*/	
}

	//The same code with JQuery
	/*$("#id_of_textbox").keyup(function(event){
		if(event.keyCode == 13){
			$("#id_of_button").click();
		}
	});*/

</script>