<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/tags/banderas.tld" prefix="flags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<tiles:insert definition="layout-administrador">

<tiles:put name="title" type="string">
	<bean:message key="title.Comun"/>
</tiles:put>
<tiles:put name="body" type="string">
<%@ include file="/taglib-imports.jspf" %>

<!--************************          Inicio plantilla contenido       ****************************-->
<div class="plantilla_contenido">

<jsp:include page="/layout/messages.jsp" flush="true" />



	<!--		Inicio  PESTANIAS de FICHA		 -->

	<div id="ficha_pestanias">
		<div> <h2> <bean:message key="estructuras.tesauros.titulo"/> </h2></div>
		<ul>
<!-- 			<li><a href="<html:rewrite action="/ListarArboles/ListarArboles"/>"><bean:message key="estructuras.estructuras.arboles"/></a></li> -->
			<li id="pest_activa"><a href="<html:rewrite action="/ListarTesauros/ListarTesauros"/>" id="seleccionada"><bean:message key="estructuras.estructuras.tesauros"/></a></li>
			<li><a href="<html:rewrite action="/ListarTaxonomias/ListarTaxonomias"/>"><bean:message key="estructuras.estructuras.taxonomias"/></a></li>
		</ul>
	</div>

	<!--		Fin  PESTANIAS de FICHA		-->


	<!--		Inicio CONTENIDO PESTANIAS		-->

	<div class="interno_ficha">
	<div class="plantilla_contenido_pestanias">
		

<form id="listarTesaurosFormListarTesaurosBaja" 
	action="<html:rewrite action="/ListarTesauros/FormListarTesaurosSubmit"/>" method="post">


		
<logic:iterate id="vdex" name="form" property="tesauros" indexId="i">


<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris uno_b" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03" style="padding-bottom:16px;">
				<div class="caja_dinamica">
					<a class="desplegado" id="p${vdex.identificador}" href="#" onclick="expandirCaja('${vdex.identificador}', '<bean:message key="estructuras.vermas"/>', '<bean:message key="estructuras.ocultar"/>');return false;" onkeypress="expandirCaja('${vdex.identificador}', '<bean:message key="estructuras.vermas"/>', '<bean:message key="estructuras.ocultar"/>');return false;">
					<br class="falso" /><strong id="d${vdex.identificador}" ><bean:message key="estructuras.ocultar"/></strong></a>
					<h3>${vdex.nombre}</h3>
				</div>
				<div id="${vdex.identificador}" class="caja_abierta" >
				<br />
				<!--  INICIO CAJA DE FORMULARIO   -->
				<div class="formu"  style="margin:0 13px !important" >
				<!--  INICIO CAPA TABLA   -->
				<!--  INICIO CAPA TABLA   -->					
				<div class="caja_tabla bordeada" > 
					<bean:define id="tituloNombre"><b><bean:message key='estructuras.nombre'/></b></bean:define>	
					<bean:define id="tituloIdioma"><b><bean:message key='estructuras.idioma'/></b></bean:define>
					<bean:define id="summary"><b><bean:message key='estructuras.tesauros.titulo.summary'/></b></bean:define>
					
					  	<display:table name="${vdex.listaFicheros}" 
  						requestURI="${pageContext.request.requestURL}"
       					export="false" id="row" class="resultados_listados"
       				    style="border:1;width:100%;" sort="list" cellpadding="0" cellspacing="0" 
        				defaultsort="1" summary="${summary}">
         
				        <display:caption><strong><bean:message key='estructuras.tesauros.titulo.summary'/></strong></display:caption>
				        <display:setProperty name="css.tr.odd" value="tr_gris"/>
				        <display:setProperty name="css.tr.even" value="tr_blanco"/>
				        <display:setProperty name="basic.show.header" value="true"/>

<c:if test="${i>0}">
					        <display:column style="valign:top;" class="numeracion">        	
								<label for="${row}" class="etiq_invisible" ><bean:message key="estructuras.tesauros.seleccione"/> </label>	
					            <input type="checkbox"  id="${row}" value="${row}${vdex.tipo}" name="seleccionadoRowSelectionAsArray"/>
					        </display:column>         
</c:if>
					        <display:column  
					        	media="html"
					        	title="${tituloNombre}"
								sortable="false"
					        	autolink="true" 
					        	style="valign:top;" 
					        	class="alinea_a_izq2">	
					        	 	${row}
					        </display:column>
						<c:set var="inicio" value="${fn:length(row)-6}"/>
						<c:set var="fin" value="${fn:length(row)-4}"/>
						<c:set var="idioma" value="${fn:substring(row, inicio , fin )}"/>
				           <display:column  
					             title="${tituloIdioma}"	
					             style="valign:top;"
					             class="td_idiomas">
					        	   <flags:banderas banderas="${idioma}" action="actionEstructurasEducativas"/> 
				           </display:column>
					        <display:column media="html" autolink="true" paramId="titulo">
					            <span class="oculto">-</span>
					            <c:set var="link"><html:rewrite action="/ListarTesauros/FormListarTesaurosExportar"/>?nombre=${row}&amp;tipo=${vdex.tipo}</c:set>
					        	<html:link href="${link}">
									<bean:message key="estructuras.arboles.exportar"/>
					        	</html:link>
					        </display:column>
						</display:table>
				</div>
				<!--  FIN CAPA TABLA   -->		
				<!--  FIN CAPA TABLA   -->		

				</div>
				
				<!--  FIN CAJA DE FORMULARIO   -->

					<!-- Inicio Botones  -->
					<!-- Inicio Botones  -->
					<fieldset class="tipo_interior_04">
						<a href="<html:rewrite action="/ListarTesauros/FormListarTesaurosModificar"/>?identificadorVdex=${vdex.identificador}&amp;tipo=${vdex.tipo}" class="boton_125_de_2_izq" title="<bean:message key="estructuras.modificar"/>"><span><bean:message key="estructuras.modificar"/></span></a>
<c:if test="${i>0}">
						<input class="boton_125_de_2" type="submit" name="action--${vdex.identificador}"  value="<bean:message key="estructuras.eliminar"/>" />
</c:if>
					</fieldset>
					<!-- Fin Botones  -->
					<!-- Fin Botones  -->


				</div>
			</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->

</logic:iterate>

<script type="text/javascript">
<!--//
<logic:iterate id="vdex" name="form" property="tesauros" indexId="i">
	expandirCaja('${vdex.identificador}', '<bean:message key="estructuras.vermas"/>', '<bean:message key="estructuras.ocultar"/>');
</logic:iterate>
//-->
</script>

</form>

<form id="listarTesaurosAltaTesauro"
	  action="<html:rewrite action="/ListarTesauros/FormListarTesaurosAlta"/>"
	  method="post">
	<!-- Inicio Botones  -->
	<!-- Inicio Botones  -->
	<fieldset class="tipo ft_centrada">
		<input class="boton_125" type="submit" name="action-${vdex.identificador}" value="<bean:message key="estructuras.taxonomias.crear"/>" />
	</fieldset>
	<!-- Fin Botones  -->
	<!-- Fin Botones  -->

</form>


<br/>
<br/>



	</div>
	</div>
	
	<!--		Fin CONTENIDO PESTANIAS		-->	
	





</div><!-- plantilla contenido -->
</tiles:put>
</tiles:insert>