<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib uri="/WEB-INF/tags/googleAnalytic.tld" prefix="analytic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>


<tiles:insert definition="layout-menu-0">

<tiles:put name="title" type="string">
	<bean:message key="title.Comun" />
</tiles:put>
<tiles:put name="body-principal" type="string">
	
<div class="col_der" id="faqs">

	<section >
		<header>
		<h2><bean:message key="faqs.faqs.title" /></h2>
		</header>
		
    	<article class="bloque_titulares" id="categorias_faq">
	   		<form action="Faqs.do" method="get">
		   		<fieldset class="clearfix con_b">
		   			<label for="idioma"><bean:message key="faqs.faqs.categorias"/> :</label>
					<span class="caja_de_boton">
					<select onchange="this.form.submit()" id="categorias" name="categoriaSeleccionada" class="select" title="Seleccione Categor&iacute;as">
					    		
						<logic:iterate id="categoria" collection="${form.categorias}" indexId="indice">
							
							<c:if test="${indice==0}">
								<c:if test="${categoria.id==form.categoriaSeleccionada}">
									<option selected="selected">${categoria.nombre}</option>
								</c:if>
								<c:if test="${categoria.id!=form.categoriaSeleccionada}">
									<option value="${categoria.id}">${categoria.nombre}</option>
								</c:if>
							 </c:if>
							 
							 <c:if test="${indice!=0}">
					 			<logic:iterate id="categoriaCat" collection="${categoria}" indexId="indiceCat">
					 				<c:if test="${form.categoriaSeleccionada==categoriaCat.id}">
										<option selected="selected" value="${categoriaCat.id}">${categoriaCat.nombre}</option>
									</c:if>
									<c:if test="${form.categoriaSeleccionada!=categoriaCat.id}">
										<option value="${categoriaCat.id}">${categoriaCat.nombre}</option>
									</c:if>					
								</logic:iterate>
							</c:if>
						</logic:iterate>
						
		    		</select>
		    		</span>
				</fieldset>
	   		</form>	
	   	</article>

	   	
		<analytic:googleAnalytic />


		<!--***********************      Listado preguntas de faqs      ************************** -->
		<article class="conmargen">
			<!--  TABLA  -->
			<c:if test="${fn:length(form.faqs) > 0}">
				<div class="tabla0">
					<div>
						<table class="tabla_faqs">
							<tr>
							<th><bean:message key="faqs.faqs.TablaFaqs" /></th>
							</tr>
							<logic:iterate id="elemento" collection="${form.faqs}">
								<tr > 
	     							<td><a href="#anchor${elemento.id }">${elemento.pregunta}</a></td>
								</tr>
							</logic:iterate>
						</table>
					</div>
				</div>
			</c:if>
			<!--  FIN TABLA  -->
		</article>	


		<!--*******************       Listado de faqs(pregunta y respuesta)      ****************** -->		
	   	<article class="bloque_titulares">
	   	<ul >
			<c:if test="${fn:length(form.faqs) > 0}">
				<logic:iterate id="elemento" collection="${form.faqs}">
					<li class="clearfix" id="anchor${elemento.id }">
						<a id="anchor${elemento.id }"></a>
						<strong class="titular">${elemento.pregunta}</strong>
						<p>${elemento.respuesta }</p>
						<span class="subir_top clearfix"><a href="#"><bean:message key="faqs.faqs.subir" /></a></span>
					</li>
				</logic:iterate>
			</c:if>
			<c:if test="${fn:length(form.faqs) == 0}">
				<li><p><bean:message key="faqs.noHay"/>	<br/></p></li>  		
			</c:if>
		</ul>
	 	</article>
	 	

	</section>
</div>

</tiles:put>
</tiles:insert>
