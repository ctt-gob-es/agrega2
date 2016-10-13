<%@ include file="/taglib-imports.jspf"%>

<tiles:importAttribute name="idiomasBuscablesPlataforma" scope="page"/>
<tiles:importAttribute name="localizacionBusqueda" scope="page"/>
<tiles:importAttribute name="existenNodosSQI" scope="page"/>
<%@ taglib uri="/WEB-INF/tags/serverProperties.tld" prefix="server" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>


<html:xhtml/>
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
<!-- FIN BUSCADOR  -->
<br class="oculto" />
<a href="<rewrite:rewrite url="${initParam.url_portada}"/>" title="<bean:message key="buscador.pagina.inicio"/>" id="logo"><span><bean:message key="buscador.pagina.inicio" /></span></a>
<br class="oculto" />

<input type="hidden" id="url" value="<rewrite:rewrite url="/buscador/SugerirBusquedaCU/SugerirBusquedaCU.do"/>"/>
<script type="text/javascript">

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


function aplicarCambios(tipoBusqueda){
		if(tipoBusqueda == '04'){//busqueda en repositorios externos
			document.getElementById('Idioma').disabled=true;//desactivar combo idioma
			
			
		}
		else{
			document.getElementById('Idioma').disabled=false;//activar combo idioma
		}
}

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

function cambiarIdioma(){
	jQuery("#buscadorGeneral")[0].autocompleter.flushCache();
	jQuery("#buscadorGeneral")[0].autocompleter.setExtraParams({idiomaBusc: document.getElementById('Idioma').value})
}

</script>
				