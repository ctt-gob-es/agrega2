<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/taglib-imports.jspf" %>
<html:xhtml/>
<tiles:insert definition="layout-gestor-flujo-con-style">

    <tiles:put name="title" type="string">
        <bean:message key="title.Comun" />
    </tiles:put>
    <tiles:put name="body" type="string">
        <!--************************          Inicio plantilla contenido       ****************************-->
        <div class="plantilla_contenido"><!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
        <jsp:include page="/layout/messages.jsp" flush="true" />
        <form id="rellenarDatosSubmitForm" method="post" action= "<html:rewrite action="/ImportarURLCU/RellenarDatosSubmit"/>" enctype="multipart/form-data">
        <!--  INICIO GLOBO GRIS   -->
		<!--  INICIO GLOBO GRIS   -->
		<div class="globo_gris" >
		    <div class="globo_gris_01">
		        <div class="globo_gris_02">
		            <div class="globo_gris_03">
		            <!--  INICIO CAJA DE FORMULARIO   -->
		            <div id="formulario"  >
		             <!-- -->
                    <div class="fila_de_tabla" style="padding-bottom:10px">

                    <div class="contenedor_izquierda">
                        <div class="text"><label for="url"><bean:message key="gestorFlujo.importarURL.URL"/>:</label></div>
                        <input name="url" type="hidden" value="${form.url }" />
                    </div>
                    <div class="contenedor_derecha">
                        <div class="text"><label>${form.url}</label></div>
                    </div>
                    <div class="linea_separadora"></div>
                    <br class="oculto" />

                </div>
                </div>
                <!-- -->    
                <!--  FIN CAJA DE FORMULARIO   -->
            </div>
        </div>
    </div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->
<p class="parrafo_comun" id="separacion3"><bean:message key="gestorFlujo.importarURL.parrafoComun"/>:</p>
    <!--  INICIO GLOBO GRIS   -->
    <!--  INICIO GLOBO GRIS   -->
                <div class="globo_gris" id="catalogador">
                    <div class="globo_gris_01">
                        <div class="globo_gris_02">
                            <div class="globo_gris_03"> <!--  INICIO CAJA DE FORMULARIO   -->
                                <div id="formulario">
                                    <div class="fila_de_tabla">

                                        <div class="contenedor_izquierda">
                                            <div class="text"><label for="Titulo"><bean:message key="gestorFlujo.importarURL.titulo"/>:</label>
                                            </div>
                                        </div>
                                        <div class="contenedor_derecha">
                                            <div class="text">                                              
                                                <input name="titulo" onblur="this.style.backgroundColor='#e1e1e1'" id="titulo" type="text" title="<bean:message key="gestorFlujo.importarURL.titulo.tooltip"/>"  />
                                            </div>
                                        </div>

                                        <div class="linea_separadora">
                                        </div><br class="oculto"/>
                                    </div>  <!-- -->
                                    <div class="fila_de_tabla">
                                        <div class="contenedor_izquierda">
                                            <div class="text"><label for="Idioma"><bean:message key="gestorFlujo.importarURL.idioma"/>:</label>
                                            </div>
                                        </div>

                                        <div class="contenedor_derecha">
                                            <div class="text">
						                       <c:choose>
				                                <c:when test="${!empty form.idiomaBackingList}">	
                                  					<html:select name="form" property="idioma" titleKey="gestorFlujo.SeleccioneIdioma" >
                                  						<c:set var="colorblanco" value="true"/>
                                  					    <c:set var="long" value="${fn:length(form.idiomaLabelList)}"/>
                                  						<fmt:formatNumber var="longIdiomaList" value="${long - 1 }" type="number"/>
                                  						<c:forEach  items="${form.idiomaBackingList}" var="listaIdioma" begin ="0" end="${longIdiomaList}">
                                  							<c:choose>
                                  							<c:when test="${colorblanco}">   
                                  								<html:option value="${listaIdioma.value }">${listaIdioma.label }</html:option>
                                  								<c:set var="colorblanco" value="${!colorblanco }"/>
                                  							</c:when>
                                  							<c:otherwise>
                                  								<html:option value="${listaIdioma.value }" styleClass="oscura">${listaIdioma.label }</html:option>
                                  								<c:set var="colorblanco" value="${!colorblanco }"/>
                                  							</c:otherwise>
                                  							</c:choose>	
                                  						</c:forEach>
                                  					</html:select>
                               					</c:when>
                           						</c:choose>	
                                            </div>
                                        </div>
                                        <div class="linea_separadora">
                                        </div><br class="oculto"/>
                                    </div>  <!-- -->
                                    <div class="fila_de_tabla">
                                        <div class="contenedor_izquierda">

                                            <div class="text"><label for="Descripcion"><bean:message key="gestorFlujo.importarURL.descripcion"/>:</label>
                                            </div>
                                        </div>
                                        <div class="contenedor_derecha">
                                            <div class="text">
                                                <textarea name="comentarios" rows="10" cols="40" class="ta_minimo_ancho" onblur="this.style.backgroundColor='#e1e1e1'" id="Descripcion" title="<bean:message key="gestorFlujo.importarURL.descripcion.tooltip"/>"></textarea>
                                            </div>
                                        </div>

                                        <div class="linea_separadora">
                                        </div><br class="oculto"/>
                                    </div>  <!-- -->
                                    <div class="fila_de_tabla">
                                        <div class="contenedor_izquierda">
                                            <div class="text"><label for="tipoRecurso"><bean:message key="gestorFlujo.importarURL.tipo"/>:</label>
                                            </div>
                                        </div>

                                        <div class="contenedor_derecha">
                                            <div class="text">
                                                 <c:choose>
				                                <c:when test="${!empty form.tipoBackingList}">	
                                  					<html:select name="form" property="tipo" titleKey="gestorFlujo.SeleccioneTipoRecurso">
                                  						<c:set var="colorblanco" value="true"/>
                                  					    <c:set var="long" value="${fn:length(form.tipoLabelList)}"/>
                                  						<fmt:formatNumber var="longTipoList" value="${long - 1 }" type="number"/>
                                  						<c:forEach  items="${form.tipoBackingList}" var="listaTipo" begin ="0" end="${longTipoList}">
                                  							<c:choose>
                                  							<c:when test="${colorblanco}">   
                                  								<html:option value="${listaTipo.value }">${listaTipo.label}</html:option>
                                  								<c:set var="colorblanco" value="${!colorblanco }"/>
                                  							</c:when>
                                  							<c:otherwise>
                                  								<html:option value="${listaTipo.value }" styleClass="oscura">${listaTipo.label}</html:option>
                                  								<c:set var="colorblanco" value="${!colorblanco }"/>
                                  							</c:otherwise>
                                  							</c:choose>	
                                  						</c:forEach>
                                  					</html:select>
                               					</c:when>
                           						</c:choose>		
                                            </div>
                                        </div>
                                        <div class="linea_separadora">
                                        </div><br class="oculto"/>
                                    </div>  
                                    <!-- -->
                                    <div class="fila_de_tabla">
                                        <div class="contenedor_izquierda">

                                            <div class="text"><label for="idiomaDestinatario"><bean:message key="gestorFlujo.importarURL.idiomaDestinatario"/>:</label>
                                            </div>
                                        </div>
                                        <div class="contenedor_derecha">
                                            <div class="text">
                                                <c:choose>
				                                <c:when test="${!empty form.idiomaDestBackingList}">	
                                  					<html:select name="form" property="idiomaDest" titleKey="gestorFlujo.SeleccioneIdiomaDestinatario">
                                  						<c:set var="colorblanco" value="true"/>
                                  					    <c:set var="long" value="${fn:length(form.idiomaDestLabelList)}"/>
                                  						<fmt:formatNumber var="longIdiomaDest" value="${long - 1 }" type="number"/>
                                  						<c:forEach  items="${form.idiomaDestBackingList}" var="listaIdiomaDest" begin ="0" end="${longIdiomaDest}">
                                  							<c:choose>
                                  							<c:when test="${colorblanco}">   
                                  								<html:option value="${listaIdiomaDest.value }">${listaIdiomaDest.label}</html:option>
                                  								<c:set var="colorblanco" value="${!colorblanco }"/>
                                  							</c:when>
                                  							<c:otherwise>
                                  								<html:option value="${listaIdiomaDest.value }" styleClass="oscura">${listaIdiomaDest.label}</html:option>
                                  								<c:set var="colorblanco" value="${!colorblanco }"/>
                                  							</c:otherwise>
                                  							</c:choose>	
                                  						</c:forEach>
                                  					</html:select>
                               					</c:when>
                           						</c:choose>	
                                            </div>
                                        </div>
                                        <div class="linea_separadora">
                                        </div><br class="oculto"/>
                                    </div>  <!-- -->
                                    </div></div></div></div></div>
                <!-- Inicio Botones  -->
				<!-- Inicio Botones  -->
				<fieldset class="tipo ft_centrada">
				<input class="boton_125"  type="submit" name="action"  value="<bean:message key="gestorFlujo.importarURL.guardar"/>" />
				
				</fieldset>
				<!-- Fin Botones  -->
				<!-- Fin Botones  -->
                
            </form>
        </div>

    </tiles:put>

</tiles:insert>
