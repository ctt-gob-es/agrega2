<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<tiles:insert definition="layout-menu-1-enriquecido">

    <tiles:put name="title" type="string">
        <bean:message key="title.Comun"/>
    </tiles:put>

    <tiles:put name="body-principal" type="string">
	<%@ include file="/taglib-imports.jspf" %>
	
<!-- PRINCIPAL   -->
<section id="principal">
 <!--  -->
<jsp:include page="/layout/messages.jsp" flush="true" />
<article id="buscador_tipo">
     <header>
       <h2 class="titulo"><bean:message key="encuentra.los.mejores.contenidos.educativos"/></h2>
     </header>
    <section id="buscador_avanzado">
  <form method="post" id="taxonomiaAsociarForm" action="<html:rewrite action="/AreaCurricularCU/TaxonomiaVolcarTaxonomia"/>">
	<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
	<input type="hidden" name="taxonomias" value="${form.taxonomias }">
    	
 <!--  -->	   	

<div class="tipofieldset clearfix" id="primer_fs"  >
<h3><bean:message key="taxonomias.nombre"/>${form.taxNombre}</h3>
</div>
<p><bean:message key="taxonomias.explicacion.taxonomia"/></p>
<!-- TABLA DE TAXONES   -->
<div class="tipofieldset clearfix"  >
<div class="breadcrumb_curricular">
		<html:link styleClass="br_carpeta_abierta" action="/AreaCurricularCU/AreaCurricularCU.do?tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;taxonomias=${form.taxonomias }">
        				<bean:message key="taxonomias.inicio.taxonomia"/><br/>
      	</html:link>
     </div>
    <!--  TABLA  -->
<div class="tabla clearfix" id="sinmargen">
<div>  

	<c:if test="${!empty form.rutaArbol}">
		 <c:set var="longitud" value="${ fn:length(form.rutaArbol)}"/>
		 <fmt:formatNumber var="nombre" value="${longitud -1}" type="number"/>
		 <fmt:formatNumber var="nombre2" value="${longitud -2}" type="number"/>
		 <c:if test="${longitud>1 }">
		   <c:forEach items="${form.rutaArbol}" var="ruta" begin="0" end="${nombre2}">
		    		<em class="oculto">-</em>
		    		<html:link styleClass="br_carpeta_abierta" action="/AreaCurricularCU/TaxonomiaSeleccionaPadre.do?id=${ruta.id}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;taxonomias=${form.taxonomias }">
        				<c:out value="${ruta.valorTax}"/><br/>
        			</html:link>
		    </c:forEach>
		 </c:if>  
		  
      	 <c:forEach items="${form.rutaArbol}" var="ruta3" begin="${nombre}" end="${nombre}">
			 <em class="oculto">-</em>
				 <span class="br_carpeta_abierta">
			    	 <c:out value="${ruta3.valorTax}"/><br/>
			     </span>
		 </c:forEach>           	
        	 			
    	 </c:if>
    	 
		<bean:define id="summary"><bean:message key="taxonomias.tabla.taxones.resumen"/></bean:define>
		<display:table name="${form.nodos}" id="taxonesvoact" 
				class="generica tabla_ruta_curricular tabla_ruta_curricular_especial" cellpadding="0" 
				cellspacing="0" style="width: 100%;"
				summary="${summary}" requestURI="${pageContext.request.requestURL}">
				
				<display:caption><strong> <bean:message key="taxonomias.tabla.taxones.vo"/></strong></display:caption>
        		<display:setProperty name="css.tr.odd" value="tr_gris"/>
        		<display:setProperty name="css.tr.even" value="tr_blanco"/>
        		<display:setProperty name="basic.show.header" value="false"/>
        		<!-- definimos las columnas -->
        		<display:column  class="sin_b">        	
					<label for="${taxonesvoact.id}" class="etiq_invisible" >
						<bean:message key="taxonomias.seleccione.taxon"/> 
					</label>
					<input type="radio" name="taxonSelec" id="${taxonesvoact.id}" value="${taxonesvoact.id}"/>
		        </display:column>
		        <c:choose>
        			<c:when test="${taxonesvoact.esHoja}">
        				<display:column  class="new" autolink="true">
            				<c:out value="${taxonesvoact.valorTax}"/>
            			</display:column>	
	       			</c:when>
        			<c:otherwise>
        				<display:column  class="new" autolink="true">
        					<html:link action="/AreaCurricularCU/TaxonomiaSeleccionaPadre?id=${taxonesvoact.id}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;taxonomias=${form.taxonomias }">
        						${taxonesvoact.valorTax}
        					</html:link>        		 
        				</display:column>	
        			</c:otherwise>
    			</c:choose>		        
		</display:table>  	
		</div>
		</div>
	</div>
	<input class="boton flotante_derecha marginado"  type="submit"  value="<bean:message key="taxonomias.boton.asociar"/>" />
	</form>
	<!--  -->    
<form method="post" action="<html:rewrite action="/BuscarAvanzadoCU/BuscarAvanzadoCU.do"/>">
	<fieldset style="background:#fff !Important" class="botonera_de_dos botonera_de_dos_especial clearfix">
		<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
	    <input class="boton boton_flot"  type="submit"  value="<bean:message key="taxonomias.cancelar.seleccion"/>"/>
	</fieldset>
</form>
    </section>
	
</tiles:put>
</tiles:insert>
