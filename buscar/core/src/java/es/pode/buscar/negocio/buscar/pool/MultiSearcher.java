/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.buscar.negocio.buscar.pool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;

import es.pode.buscar.negocio.administrar.servicio.NodoVO;
import es.pode.buscar.negocio.buscar.servicios.ParametrosBusquedaAvanzadaVO30;
import es.pode.buscar.negocio.buscar.servicios.ParametrosDocsCountVO30;
import es.pode.buscar.negocio.buscar.servicios.ResultadosNodoCountVO;
import es.pode.buscar.negocio.federada.servicios.SrvBuscarFederadaServiceImpl;
import es.pode.indexador.negocio.servicios.busqueda.DocumentosVO30;

public class MultiSearcher {
	
	private static Logger logger = Logger.getLogger(MultiSearcher.class);
	private static ThreadPool pool = new ThreadPool(new ThreadObjectFactory());
	private static HashMap<String, String> hashMap=null;
	
	
	
	
	/**
	 * Este método hace la llamada al método busquedaFederada en todos los hilos del pool. 
	 * @param parametros parámetros necesarios para hacer la llamada al método 
	 * @param comunidades nodos sobre la federación donde se va a hacer la llamada
	 * @param urlDestinoESB Ubicacion del servidor esb
	 * @param maxWaitThread número máximo de tiempo de espera del hilo.
	 * @param maxWaitPool número máximo de tiempo de espera del pool.
	 * @return un array de VOs con el resultado del método en todos los nodos de la federación
	 * @throws Exception
	 */
	public static DocumentosVO30[] searchDocuments(ParametrosBusquedaAvanzadaVO30 parametros,List<NodoVO> comunidades, String urlDestinoESB, int maxWaitThread, int maxWaitPool,String srvFederada) throws Exception{ 
        if (logger.isDebugEnabled()) 
    		logger.debug("MultiSearcher - searchDocuments : Solicitud de hilos="+comunidades.size()+" hilos");
		List<Object> searched = search(parametros, comunidades, urlDestinoESB, maxWaitThread, maxWaitPool, srvFederada);
		boolean nodoRecogeVersion=false;
			List<Integer> idComunidades = new ArrayList<Integer>();
			for(int i = 0; i < ((List<?>)searched.get(1)).size() ; i++ ){
				Object[] documentos = (Object[]) ((List<?>)searched.get(1)).get(i);
				if(documentos!=null && documentos.length > 1){
					for(int j = 1; j < documentos.length ; j++ ){
						if(((DocumentosVO30)documentos[j])!=null && ((DocumentosVO30)documentos[j]).getVersionComunidadSolicitada()!=null && ((DocumentosVO30)documentos[j]).getVersionComunidadSolicitada().equals("0")){
							idComunidades.add(j);
						}
					}
				}
			}
			if (logger.isDebugEnabled()) 
	    		logger.debug("MultiSearcher - searchDocuments : Existen "+idComunidades.size()+" comunidades a las que se les va a preguntar si son version 3");
			List<Object> searched2= new ArrayList<Object>();
			List<NodoVO> comunidades2= new ArrayList<NodoVO>();
			if(idComunidades.size()>0){
				for(int i=0; i <idComunidades.size();i++){
					if(hashMap==null || !hashMap.containsKey((comunidades.get(idComunidades.get(i))).getUrl()))
						if (logger.isDebugEnabled()) 
				    		logger.debug("MultiSearcher - searchDocuments : Posible comunidad version 3 a solicitar:"+(comunidades.get(idComunidades.get(i))).getUrl());
						comunidades2.add(comunidades.get(idComunidades.get(i)));
					
				}
			}
			
			if(comunidades2.size()>0){
				searched2 = search(null, comunidades2, urlDestinoESB, 5000, 8000, srvFederada);
				for(int i = 0; i < ((List<?>)searched2.get(1)).size() ; i++ ){
					Object[] versiones = (Object[]) ((List<?>)searched2.get(1)).get(i);
					if(versiones!=null && versiones.length > 0){
						for(int j = 0; j < versiones.length ; j++ ){
							if(versiones[j]!=null && !((String)versiones[j]).startsWith("2.") && !((String)versiones[j]).startsWith("1.")){
								if (logger.isDebugEnabled()) 
						    		logger.debug("MultiSearcher - searchDocuments : Comunidad version 3 obtenida:"+(comunidades2.get(j)).getUrl());
								procesarHasmap((comunidades2.get(j)).getUrl(),"3");
								nodoRecogeVersion=true;
							}
						}
					}
				}
		}
		DocumentosVO30[] resultado = new DocumentosVO30[Integer.parseInt(searched.get(0).toString())];
		int ind = 0;
		int indComunidades = 0;
		
		for(int i = 0; i < ((List<?>)searched.get(1)).size() ; i++ ){
			Object[] documentos = (Object[]) ((List<?>)searched.get(1)).get(i);
			if(documentos!=null && documentos.length > 0){
				for(int j = 0; j < documentos.length ; j++ ){
					if(!(comunidades.get(indComunidades)).getUrlWS().equals((comunidades.get(0)).getUrlWS())&& documentos[j]!=null && ((DocumentosVO30)documentos[j]).getResultados()!=null && ((DocumentosVO30)documentos[j]).getResultados().length>0){
                        if (logger.isDebugEnabled()) 
			    		    Logger.getLogger(SrvBuscarFederadaServiceImpl.class).debug("SrvBuscarFederadaServiceImpl - handleBusquedaFederada: Resultados devueltos="+((DocumentosVO30)documentos[j]).getResultados().length+". Se añade IP server="+(comunidades.get(indComunidades)).getUrl());
			    		for(int k=0; k < ((DocumentosVO30)documentos[j]).getResultados().length;k++){
			    			((DocumentosVO30)documentos[j]).getResultados()[k].setNodo((comunidades.get(indComunidades)).getUrl());
						}
					}
					if(logger.isDebugEnabled() && (comunidades.get(indComunidades)).getUrlWS().equals((comunidades.get(0)).getUrlWS()) && documentos[j]!=null && ((DocumentosVO30)documentos[j]).getResultados()!=null)
							Logger.getLogger(SrvBuscarFederadaServiceImpl.class).debug("SrvBuscarFederadaServiceImpl - handleBusquedaFederada: Resultados devueltos="+((DocumentosVO30)documentos[j]).getResultados().length+". Se añade IP server nodo local="+(comunidades.get(indComunidades)).getUrlWS()+":"+(comunidades.get(indComunidades)).getPuerto());
					if(documentos[j]!=null) {
						if(((DocumentosVO30)documentos[j]).getVersionComunidadSolicitada()!=null){
							procesarHasmap((comunidades.get(indComunidades)).getUrl(),((DocumentosVO30)documentos[j]).getVersionComunidadSolicitada());
							if(!((DocumentosVO30)documentos[j]).getVersionComunidadSolicitada().equals("0")) nodoRecogeVersion=true;
						}
                        if (logger.isDebugEnabled()) 
                            logger.debug("MultiSearcher - searchDocuments : Comunidad="+(comunidades.get(indComunidades)).getNodo()+" con IP WS="+(comunidades.get(indComunidades)).getUrlWS()+ ((((DocumentosVO30)documentos[j]).getVersionComunidadSolicitada()!=null)?" VersionComunidadSolicitada="+((DocumentosVO30)documentos[j]).getVersionComunidadSolicitada():" Número resultados="+((DocumentosVO30)documentos[j]).getNumeroResultados()));
                    }
					else {
						if (logger.isDebugEnabled()) 
                            logger.debug("MultiSearcher - searchDocuments : Comunidad="+(comunidades.get(indComunidades)).getNodo()+" con IP WS="+(comunidades.get(indComunidades)).getUrlWS()+" No devuelve.");
                    }
					resultado[ind]=((DocumentosVO30)documentos[j]);
					ind++;
					indComunidades++;
				}
			}else indComunidades++;
		}
		if(nodoRecogeVersion) resultado=new DocumentosVO30[0];
		return resultado;
	}
	
	/**
	 * Este método hace la llamada al método buscarDocsNodoArbolCurricular en todos los hilos del pool. 
	 * @param parametros parámetros necesarios para hacer la llamada al método 
	 * @param nodos nodos sobre la federación donde se va a hacer la llamada
	 * @param urlDestinoESB Ubicacion del servidor ESB
	 * @param maxWaitThread número máximo de tiempo de espera del hilo.
	 * @param maxWaitPool número máximo de tiempo de espera del pool.
	 * @return un array de VOs con el resultado del método en todos los nodos de la federación
	 * @throws Exception
	 */
	public static ResultadosNodoCountVO[] searchDocsCountArbolCurricularFederada(ParametrosDocsCountVO30 parametros,NodoVO[] nodos, String urlDestinoESB, int maxWaitThread, int maxWaitPool,String srvFederada) throws Exception{ 
        if (logger.isDebugEnabled()) 
		    logger.debug("MultiSearcher - searchDocsCountArbolCurricularFederada : Solicitud de hilos="+nodos.length+" hilos");
		List<Object> searched = search(parametros, Arrays.asList(nodos), urlDestinoESB, maxWaitThread, maxWaitPool,srvFederada);
		ResultadosNodoCountVO[] resultado = new ResultadosNodoCountVO[Integer.parseInt(searched.get(0).toString())];
		int ind = 0;
		int indComunidades = 0;
		for(int i = 0; i < ((List<?>)searched.get(1)).size() ; i++ ){
			Object[] documentos = (Object[]) ((List<?>)searched.get(1)).get(i);
			if(documentos!=null && documentos.length > 0){
				for(int j = 0; j < documentos.length ; j++ ){
					if(documentos[j]!=null) {
						if (logger.isDebugEnabled()) 
                            logger.debug("MultiSearcher - searchDocsCountArbolCurricularFederada : Comunidad="+nodos[indComunidades].getNodo()+" con IP WS="+nodos[indComunidades].getUrlWS()+" Número resultados="+((ResultadosNodoCountVO)documentos[j]).getNumeroResultados());
                    }
					else { 
                        if (logger.isDebugEnabled()) 
                            logger.debug("MultiSearcher - searchDocsCountArbolCurricularFederada : Comunidad="+nodos[indComunidades].getNodo()+" con IP WS="+nodos[indComunidades].getUrlWS()+" No devuelve.");
                    }
					resultado[ind]=((ResultadosNodoCountVO)documentos[j]);
					ind++;
					indComunidades++;
				}
			}else indComunidades ++;
		}
		return resultado;
	}
	
	//TODO En vez de tirar de pool, usaremos los indexSearcher de Lucene
	private static List<Object> search(Object parametros,List<NodoVO> comunidades, String urlDestinoESB, int maxWaitThread, int maxWaitPool,String srvFederada) throws Exception{
		long start = System.currentTimeMillis();
		if(logger.isDebugEnabled())logger.debug("Start time millis ="+start);
		List<Object> retorno = new ArrayList<Object>();
		List<Object[]> peticionesDocumentos = new ArrayList<Object[]>();
		int numDocumentos = 0;
//		int numObjExecute = 0;
//		int numObjToExecute = comunidades.size();
		Object[] rtArr = null;
		try{
				rtArr = pool.borrowObjects(comunidades.size());
				if(rtArr!=null && rtArr.length > 0){
					NodoVO[] nodos = new NodoVO[rtArr.length];
					for(int i = 0; i < rtArr.length;i++){
						nodos[i] = comunidades.get(i);
					}
					Object[] documentos = executor(rtArr, parametros, nodos, urlDestinoESB, maxWaitThread, maxWaitPool,srvFederada);
					peticionesDocumentos.add(documentos);
					numDocumentos += documentos.length;
//					numObjExecute += rtArr.length;
//					numObjToExecute -= rtArr.length;
				}
				//if(logger.isDebugEnabled())logger.debug("Número de hilos ejecutados="+numObjExecute+" y número de hilos por ejecutar="+numObjToExecute+" .Time millis ="+System.currentTimeMillis());
				
		} catch(Exception e) {
		    if(logger.isDebugEnabled())
			    logger.debug("Fallo en peticion executor, se retornan ="+rtArr.length+" hilos"); 
			for(int i = 0; i < rtArr.length; i++){
				try{
					((MultiSearcherThread)rtArr[i]).resetAll();
					pool.returnObject(rtArr[i]);
				}catch(Exception ex){
					logger.error("MultiSearcher - executor ERROR: No se pudo retornar el hilo al pool despues de timeout de pool.",ex);
				}	
			}
	    	logger.error("MultiSearcher - search ERROR: Pool fallido",e);
	    }
		long end = System.currentTimeMillis();
		retorno.add(String.valueOf(numDocumentos));
		retorno.add(peticionesDocumentos);
		if(logger.isDebugEnabled())
		    logger.debug("MultiSearcher - search : Hilos terminados federada en =" + (end - start)+ " milisegundos, comienza obtención de datos del pool");
		return retorno;
	}
	
	
	//TODO O paso de este, o lo reescribo casi entero
	private static Object[] executor(Object[] rtArr, Object parametros, NodoVO[] comunidades, String urlDestinoESB, int maxWaitThread, int maxWaitPool,String srvFederada){
		Object[] result = new Object[rtArr.length];
		Object synObj = new Object();		
		long startObjectPool = System.currentTimeMillis();
		if(logger.isDebugEnabled()) logger.debug("Numero de hilos a ejecuar: " + rtArr.length);
		
		/* Ejecución de los hilos */
		for(int j = 0; j < rtArr.length ; j++ ){
			try{					//Debería ser url 
		        if(logger.isDebugEnabled()) {
				    logger.debug("MultiSearcher - executor : Direccion="+comunidades[j].getUrlWS()+" puerto="+comunidades[j].getPuerto()+" servicio="+urlDestinoESB);
                    logger.debug("Hilo " + j + " : " + rtArr[j]);
                    logger.debug("Miramos si el hashMap esta vacio.Si no es asi observamos que nodo es el causante del error y lo comparamos con el que estamos");
                    if(hashMap!=null)logger.debug("MultiSearcher - executor: Sabemos la versión:"+hashMap.get(comunidades[j].getUrlWS()));
		        }
		        String urlDestinoHilo="";
		      
		        if(j==0 || parametros==null || (hashMap!=null && hashMap.containsKey(comunidades[j].getUrlWS()) && hashMap.get(comunidades[j].getUrlWS()).toString().equals("3"))) urlDestinoHilo = getAdress(comunidades[j], srvFederada);
		        else urlDestinoHilo=urlDestinoESB;
		        
		        ((MultiSearcherThread)rtArr[j]).execute(comunidades[j].getUrlWS(),urlDestinoHilo, setDestinoVersion(comunidades[j],parametros), synObj);        	
			}
			catch(Exception e) {
				logger.error("MultiSearcher - executor ERROR: Hilo fallido="+ comunidades[j].getUrl(),e);	
			}
		}
		/* Recuperación de las ejecuciónes y devolución de los hilos al pool */
		for(int i=0;i<rtArr.length;i++) {
			long startObject = System.currentTimeMillis();
			try {
				long tiempoHilos = System.currentTimeMillis() - startObjectPool;
				if(tiempoHilos <= maxWaitPool) { // No hemos sobrepasado el tiempo total de la ejecución del conjunto de hilos
				
					while(!((MultiSearcherThread)rtArr[i]).isDone()) {
						Thread.sleep(500L);
						long tiempoHilo = System.currentTimeMillis() - startObject;
						if(tiempoHilo >= maxWaitThread) { // Hemos sobrepasado el tiempo de un hilo 							
							logger.info("Hilo fuera de tiempo. Hilo: " + i + " " + rtArr[i].toString() + " Tiempo: " + tiempoHilo + " Máximo de tiempo:" + maxWaitThread);
							throw new NoSuchElementException("Hilo fuera de tiempo. Timeout waiting thread: " + maxWaitThread+" max wait thread miliseg.");
						} 
					}
					if(((MultiSearcherThread)rtArr[i]).getException()==null) result[i] = ((MultiSearcherThread)rtArr[i]).getResult();
				}
				else { // Hemos sobrepasado el tiempo total del conjunto de hilos
					logger.info("Conjunto de hilos fuera de tiempo. Hilo: " + i + " " + rtArr[i].toString() + " Tiempo hilos: " + tiempoHilos + " Máximo de tiempo para el conjunto de hilos: " + maxWaitPool);
					if(((MultiSearcherThread)rtArr[i]).getResult()!=null) result[i] = ((MultiSearcherThread)rtArr[i]).getResult();
				}
				((MultiSearcherThread)rtArr[i]).resetAll();
	            pool.returnObject(rtArr[i]);
			} 
			catch(Exception e) {
            	logger.error("MultiSearcher - executor ERROR: Hilo fallido " + i + " de "+comunidades[i].getUrl()+" " + rtArr[i].toString() + " " + e.getMessage() + " - " + e.getCause());
            	try{
            		if(((MultiSearcherThread)rtArr[i]).getResult()!=null) result[i] = ((MultiSearcherThread)rtArr[i]).getResult();
            		((MultiSearcherThread)rtArr[i]).resetAll();
            		pool.returnObject(rtArr[i]);
				}catch(Exception ex){
					logger.error("MultiSearcher - executor ERROR: No se pudo retornar el hilo " + rtArr[i].toString() + " al pool="+ comunidades[i].getUrl(),e);
				}	
            }
		    if(logger.isDebugEnabled())
			    logger.debug("MultiSearcher - executor : "+(System.currentTimeMillis() - startObject)+" Milisegundos en comunidad="+comunidades[i].getNodo()+", direccion="+comunidades[i].getUrlWS()+", con puerto="+comunidades[i].getPuerto()+" y servicio="+urlDestinoESB);
        }
		logger.debug("Se comprueba si la version es incorrecta y si los resultados son distinto de nulos para establecer todo resultado posible a nulo");
		return result;
		}
	
	public static void resetHashFederacion(){
		hashMap=null;
	}
	
//	private static void procesarHasmap(String[] nodo, String[] version){
//		logger.debug("Rellenamos el objeto Hash nodo="+nodo+" version="+version);
//		//Guardamos la excepcion en un HashMap
//		for(int i=0;i< nodo.length;i++) procesarHasmap(nodo, version);
//	}
	
	private static void procesarHasmap(String nodo, String version){
		logger.debug("Rellenamos el objeto Hash nodo="+nodo+" version="+version);
		//Guardamos la excepcion en un HashMap
		if(hashMap==null) hashMap=new HashMap<String,String>();
		//if(version.equals("0") && hashMap.containsKey(nodo)) hashMap.remove(nodo);
		if(!version.equals("0"))hashMap.put(nodo,version);
	}
	
	private static Object setDestinoVersion(NodoVO comunidad, Object params){
		String version=null;
		String adress=getAdress(comunidad,"");
		if(params!=null){
	        if(hashMap!=null && hashMap.containsKey(comunidad.getUrlWS()))
	        	version = hashMap.get(comunidad.getUrlWS()).toString();
	        if(logger.isDebugEnabled())
			    logger.debug("MultiSearcher - setDestinoVersion ComunidadDestino --> " + adress + " Version --> " + version);
	        if(ParametrosBusquedaAvanzadaVO30.class.equals(params.getClass())){
	        	ParametrosBusquedaAvanzadaVO30 parametros = new ParametrosBusquedaAvanzadaVO30((ParametrosBusquedaAvanzadaVO30)params);
	        	parametros.setComunidadDestino(adress);
	        	parametros.setVersionComunidadDestino(version);
	        	return parametros;
		    }else if(ParametrosDocsCountVO30.class.equals(params.getClass())){
		    	ParametrosDocsCountVO30 parametros = new ParametrosDocsCountVO30 ((ParametrosDocsCountVO30)params);
		    	parametros.setComunidadDestino(adress);
	        	parametros.setVersionComunidadDestino(version);
	        	return parametros;
		    }
		}
        return params;
	}
	
	private static String getAdress(NodoVO comunidad,String srvFederada){
		String adress="http://"+comunidad.getUrlWS();
        if(comunidad.getPuerto()!=null && !comunidad.getPuerto().trim().equals(""))
        	adress+=":"+comunidad.getPuerto();
        adress+=srvFederada;
        return adress;
	}
}
