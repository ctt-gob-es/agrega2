/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.indexador.negocio.servicios.auditoria;

import java.util.Date;

import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.indexador.negocio.servicios.busqueda.DocVO30;
import es.pode.indexador.negocio.servicios.busqueda.ResultadosCountVO;
import es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorService;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.utiles.date.DateManager;


/**
 * @see es.pode.indexador.negocio.servicios.auditoria.SrvAuditoriaIndexadorService
 */

public class SrvAuditoriaIndexadorServiceImpl
    extends es.pode.indexador.negocio.servicios.auditoria.SrvAuditoriaIndexadorServiceBase
{

	private Logger logger = Logger.getLogger(this.getClass());
    
    public static final int NIVELES_AGREGACION = 4; // hay 4 niveles de agregacion. 
    /**
     * Este metodo recibe un recorrido de arbol curricular y devuelve el recubrimiento 
     * del mismo que hacen los elementos que estan indexados en el repositorio.
     * @param parametroAuditoria Parametros del metodo.	 	    	 
     * @return Se devuelve un VO con el numero de documentos indexados totales para cada nodo del recorrido de arbol
     * curricular que se pasa.
     * @throws Exception
     * 
     */
    protected es.pode.indexador.negocio.servicios.auditoria.CoberturaVO[] handleCoberturaArbolCurricular(ParametroAuditIndexadorVO parametroAuditoria)
    throws java.lang.Exception
    {
    	if (parametroAuditoria == null)
    	{
    		logger.error("Error calculando cobertura de arbol curricular. No hay parametros.");
    		throw new Exception("Error calculando cobertura de arbol curricular. No hay parametros.");
    	}
    	Date fechaDesde = parametroAuditoria.getFechaDesde().getTime();
    	Date fechaHasta = parametroAuditoria.getFechaHasta().getTime();
    	String[] idNodos = parametroAuditoria.getIdNodo();
    	
    	if (idNodos == null || idNodos.length == 0)
    	{
    		logger.error("Error calculando cobertura de arbol curricular. No hay nodos en el arbol.");
    		throw new Exception("Error calculando cobertura de arbol curricular. No hay nodos en el arbol.");
    	}
    	if (fechaDesde == null || fechaHasta == null)
    	{
    		logger.error("Error calculando cobertura de arbol curricular. Fechas desde["+fechaDesde==null?null:fechaDesde.toString()+"] y hasta["+fechaHasta==null?null:fechaHasta.toString()+"] vacias.");
    		throw new Exception("Error calculando cobertura de arbol curricular. Fechas desde["+fechaDesde==null?null:fechaDesde.toString()+"] y hasta["+fechaHasta==null?null:fechaHasta.toString()+"] vacias.");
    	}
    	if (fechaDesde.after(fechaHasta))
    	{
    		logger.error("Error calculando cobertura de arbol curricular. Fechas desde["+fechaDesde.toString()+"] y hasta["+fechaHasta.toString()+"] incoherentes.");
    		throw new Exception("Error calculando cobertura de arbol curricular. Fechas desde["+fechaDesde.toString()+"] y hasta["+fechaHasta.toString()+"] incoherentes.");    		
    	}
    	SrvBuscadorService servicioBusqueda = this.getSrvBuscadorService(); 
    	CoberturaVO[] resultadoCober = new CoberturaVO[idNodos.length];

    	String pathIndices = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVER_ID);
    	for (int j = 0; j < idNodos.length; j++) {

    		ResultadosCountVO resultado = getSrvBuscadorService().solicitudDocsTaxonomiaNodo(pathIndices,"acloe2006/" + idNodos[j], "es");
				
    		CoberturaVO cobertura = new CoberturaVO();
    		cobertura.setId(idNodos[j]);
    		Integer numDoc = resultado != null && resultado.getDocumentosCount()!= null?resultado.getDocumentosCount():0;
    		cobertura.setNumDoc(numDoc);
    		resultadoCober[j] = cobertura;

    	}

//  	Devolvemos el array con los resultados
    	return resultadoCober;
    }

    /**
     * Este metodo devuelve el nivel de cobertura de los elementos indexados sobre todas las licencias.
     * @param parametroAuditoria Parametros del metodo.	 	    	 
     * @return Se devuelve un VO con el numero de documentos indexados totales para cada licencia que se pasa.
     * @throws Exception
     * 
     */
	protected CoberturaVO[] handleCoberturaLicencias(ParametroAuditIndexadorVO parametroAuditoria) throws Exception {
	   	
		if (parametroAuditoria == null)
    	{
    		logger.error("Error calculando cobertura de licencias. No hay parametros.");
    		throw new Exception("Error calculando cobertura de licencias. No hay parametros.");
    	}
    	Date fechaDesde = parametroAuditoria.getFechaDesde().getTime();
    	Date fechaHasta = parametroAuditoria.getFechaHasta().getTime();
    	String[] licencias = parametroAuditoria.getIdLicencias();
    	
    	if (licencias == null || licencias.length == 0)
    	{
    		logger.error("Error calculando cobertura de licencias. No hay licencias.");
    		throw new Exception("Error calculando cobertura de licencias. No hay licencias.");
    	}
    	if (fechaDesde == null || fechaHasta == null)
    	{
    		logger.error("Error calculando cobertura de licencias. Fechas desde["+fechaDesde==null?null:fechaDesde.toString()+"] y hasta["+fechaHasta==null?null:fechaHasta.toString()+"] vacias.");
    		throw new Exception("Error calculando cobertura de licencias. Fechas desde["+fechaDesde==null?null:fechaDesde.toString()+"] y hasta["+fechaHasta==null?null:fechaHasta.toString()+"] vacias.");
    	}
    	if (fechaDesde.after(fechaHasta))
    	{
    		logger.error("Error calculando cobertura de licencias. Fechas desde["+fechaDesde.toString()+"] y hasta["+fechaHasta.toString()+"] incoherentes.");
    		throw new Exception("Error calculando cobertura de licencias. Fechas desde["+fechaDesde.toString()+"] y hasta["+fechaHasta.toString()+"] incoherentes.");    		
    	}

//  	Voy recorriendo todas las licencias y voy calculando todos los odes que hay para esa licencia
    	SrvBuscadorService servicioBusqueda = this.getSrvBuscadorService();
    	CoberturaVO[] resultadoCober = new CoberturaVO[licencias.length];

    	for (int j = 0; j < licencias.length; j++) {
    		ResultadosCountVO resultado = servicioBusqueda.busquedaDocsLicenciaFecha(licencias[j], DateManager.dateToCalendar(fechaDesde), DateManager.dateToCalendar(fechaHasta));
				
    		CoberturaVO cobertura = new CoberturaVO();
    		cobertura.setId(licencias[j]);
    		Integer numDoc = resultado != null && resultado.getDocumentosCount()!= null?resultado.getDocumentosCount():0;
    		cobertura.setNumDoc(numDoc);
    		resultadoCober[j] = cobertura;
    	}

//  	Devolvemos el array de todas las licencias
    	return resultadoCober;
	}

    /**
     * Este metodo devuelve el nivel de cobertura que tienen los elementos del indice sobre el nivel de agregacion.
     * @param parametroAuditoria Parametros del metodo.	 	    	 
     * @return Se devuelve un VO con el numero de documentos indexados totales para cada nivel de agregacion.
     * @throws Exception
     * 
     */
	protected CoberturaVO[] handleCoberturaNivelAgregacion(ParametroAuditIndexadorVO parametroAuditoria) throws Exception {
		if (parametroAuditoria == null)
    	{
    		logger.error("Error calculando cobertura de nivel de agregacion. No hay parametros.");
    		throw new Exception("Error calculando cobertura de nivel de agregacion. No hay parametros.");
    	}
    	Date fechaDesde = parametroAuditoria.getFechaDesde().getTime();
    	Date fechaHasta = parametroAuditoria.getFechaHasta().getTime();
    	int nivelesAgregacion = NIVELES_AGREGACION; // el nivel de agregacion va de 1 a 4
    	String[] nivelesAgrega= new String[nivelesAgregacion];
    	for (int i = 0; i< nivelesAgregacion; i++)
    	{
    		nivelesAgrega[i]=""+(i+1);
    	}
    	
    	if (fechaDesde == null || fechaHasta == null)
    	{
    		logger.error("Error calculando cobertura de nivel de agregacion. Fechas desde["+fechaDesde==null?null:fechaDesde.toString()+"] y hasta["+fechaHasta==null?null:fechaHasta.toString()+"] vacias.");
    		throw new Exception("Error calculando cobertura de nivel de agregacion. Fechas desde["+fechaDesde==null?null:fechaDesde.toString()+"] y hasta["+fechaHasta==null?null:fechaHasta.toString()+"] vacias.");
    	}
    	if (fechaDesde.after(fechaHasta))
    	{
    		logger.error("Error calculando cobertura de nivel de agregacion. Fechas desde["+fechaDesde.toString()+"] y hasta["+fechaHasta.toString()+"] incoherentes.");
    		throw new Exception("Error calculando cobertura de nivel de agregacion. Fechas desde["+fechaDesde.toString()+"] y hasta["+fechaHasta.toString()+"] incoherentes.");    		
    	}

//  	Voy recorriendo todas los niveles de agregacion y voy calculando todos los odes que hay para ese nivel
    	SrvBuscadorService servicioBusqueda = this.getSrvBuscadorService(); 

    	CoberturaVO[] resultadoCober = new CoberturaVO[nivelesAgrega.length];

    	for (int j = 0; j < nivelesAgrega.length; j++) {
    		ResultadosCountVO resultado = servicioBusqueda.busquedaDocsRangoFechaNivelAgregacion(nivelesAgrega[j], DateManager.dateToCalendar(fechaDesde), DateManager.dateToCalendar(fechaHasta));

    		CoberturaVO cobertura = new CoberturaVO();
    		cobertura.setId(nivelesAgrega[j]);
    		Integer numDoc = resultado != null && resultado.getDocumentosCount()!= null?resultado.getDocumentosCount():0;
    		cobertura.setNumDoc(numDoc);
    		resultadoCober[j] = cobertura;
    		}
//  	Devolvemos el array de todos los niveles de agregación
    	return resultadoCober;
	}

    /**
     * Este metodo devuelve las urls de la imagenes de los identificadores de los odes.
     * @param idioma
     * 			El idioma de los odes.
     * @param identificadores
     * 			Los identificadores de los odes	 	    	 
     * @return Se devuelve un array de String con la url pequeña de la imagen.
     * @throws Exception
     * 
     */
    /*
     * Este metodo devuelve las urls de la imagenes de los identificadores de los odes
     * */
	protected String[] handleObtenerURLImagenPequena(String idioma, String[] identificadores) throws Exception {
		if(logger.isDebugEnabled())
		logger.debug("Estamos en obtener URLImagenPequeña con: idioma:["+idioma+" ] y identificadores con logitud:["+identificadores.length);
		String[] urls=new String[identificadores.length];
		SrvBuscadorService servicioBusqueda = this.getSrvBuscadorService(); 
		
		String[] idiomas = I18n.getInstance().obtenerIdiomasBuscables();
//  	Para cada indice, tenemos que hacer un recorrido de todas las licencias y sumar el acumulado.
    	if (idiomas == null || idiomas.length == 0)
    	{
    		logger.error("Error calculando url imagen pequena. No existen indices en el indexador. Imposible continuar.");
    		throw new Exception("Error calculando url imagen pequena. No existen indices en el indexador. Imposible continuar.");
    	}
    	
    	if(idioma!=null && !idioma.equals("")){//Cuando el idioma no es nulo
    		boolean encontrado=false;
        	for(int i=0;i<idiomas.length;i++){
        		if(idiomas[i].equals(idioma)){
        			encontrado=true;
        		}
        	}
    		if(encontrado){
				if(logger.isDebugEnabled())
				logger.debug("Tenemos ["+identificadores.length+"] identificadores para encontrar en el indexador con el idioma ["+idioma);
				for(int i=0;i<identificadores.length;i++){
					String url="";
					try{
						DocVO30 busqueda = servicioBusqueda.busquedaMEC(identificadores[i], idioma);
		
						if(busqueda!=null){//Está indexado
							String localizador=busqueda.getLocalizadorODE();
							if(logger.isDebugEnabled())
							logger.debug("El localizador que nos devuelve es:["+localizador);
							String urlImagen=busqueda.getImagen();
							if(urlImagen !=null || !urlImagen.equals("")){
								url=urlImagen;
								logger.debug("La url del identificador:["+identificadores[i]+"[es :["+url);
							}else{//No tiene imagen,aunque ODE este indexado
								if(logger.isDebugEnabled())
								logger.debug("Esta indexado, pero no tiene imagen");
								url =  "/"+ AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.URL_IMAGEN_DEFECTO);
								if(logger.isDebugEnabled())
								logger.debug("La url que devolvemos es:["+url);
							}
						}else{//No está indexado
							if(logger.isDebugEnabled())
							logger.debug("No está indexado");
							url =  "/"+ AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.URL_IMAGEN_DEFECTO);
							if(logger.isDebugEnabled())
							logger.debug("La url que devolvemos es:["+url);
							
						}
						urls[i]=url;
						
					}catch(Exception e){
						logger.error("Ha saltado una excepcion", e);
						url =  "/"+ AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.URL_IMAGEN_DEFECTO);
						if(logger.isDebugEnabled())
						logger.debug("La url que devolvemos es:["+url);
						urls[i]=url;
					}
				}
			}else{//Viene un idioma que no es un idioma de indexacion
				logger.error("Ese idioma no es un idioma de indexación");
				for(int i=0;i<identificadores.length;i++){
					String url =  "/"+ AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.URL_IMAGEN_DEFECTO);
					urls[i]=url;
				}
				
				
				logger.error("Error calculando url imagen pequena. No existen indices con ese idioma en el indexador. Imposible continuar.");
	    		throw new Exception("Error calculando url imagen pequena. No existen indices con ese idioma en el indexador. Imposible continuar.");
				
			}
    	}else{//Si el idioma viene vacío
    		if(logger.isDebugEnabled())
    		logger.debug("El array de identificadores es de:"+identificadores.length+" posiciones");
    		for (int j = 0; j < identificadores.length; j++) {
    			String url="";
    			boolean encontrado=false;
    			for (int i = 0; i < idiomas.length && !encontrado; i++) {
        			DocVO30 resultado = servicioBusqueda.busquedaMEC(identificadores[j], idiomas[i]);
        			if(logger.isDebugEnabled())
						logger.debug("El identificador"+j+" es:["+identificadores[j]+"y el idioma"+i+":"+idiomas[i]+" Y el resultado que devuelve es:["+resultado);
        			if (resultado != null )
        			{
        				encontrado=true;
        				logger.debug("El resultado que devuelve:["+resultado.getIdentificadorODE());
        				String localizador=resultado.getLocalizadorODE();
						if(logger.isDebugEnabled())
						logger.debug("El localizador que nos devuelve es:["+localizador);
						String urlImagen=resultado.getImagen();
						if(urlImagen !=null && !urlImagen.equals("")){
							url=urlImagen;
							logger.debug("La url del identificador:["+identificadores[j]+"[es :["+url);
						}else{//No tiene imagen,aunque ODE este indexado
							if(logger.isDebugEnabled())
							logger.debug("Esta indexado, pero no tiene imagen");
							url =  "/"+ AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.URL_IMAGEN_DEFECTO);
							if(logger.isDebugEnabled())
							logger.debug("La url que devolvemos es:["+url);
						}
						urls[j]=url;
        			}
        			
        		}if(!encontrado){//Si no se encuentra en ningún indice, imagen por defecto
        			if(logger.isDebugEnabled())
						logger.debug("No lo hemos encontrado en ningun indice");
						url =  "/"+ AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.URL_IMAGEN_DEFECTO);
						if(logger.isDebugEnabled())
						logger.debug("La url que devolvemos es:["+url);
						urls[j]=url;
        		}
        		
        	}
    	}
		return urls;
	}
}