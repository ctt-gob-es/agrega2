package es.pode.buscar.negocio.monitorizar.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;

import es.pode.buscar.negocio.administrar.servicio.NodoVO;
import es.pode.buscar.negocio.monitorizar.dominio.EstadoNodoVO;

public class MultiSearcher {
	private static Logger logger = Logger.getLogger(MultiSearcher.class);
	private static ThreadPool pool = new ThreadPool(new ThreadObjectFactory());
	
	/**
	 * Este m�todo hace la llamada al m�todo obtenerEstado en todos los hilos del pool. 
	 * @param listaNodos nodos sobre la federaci�n donde se va a hacer la llamada
	 * @param maxNumObjPool 
	 * @param maxWaitThread n�mero m�ximo de tiempo de espera del hilo.
	 * @param maxWaitPool n�mero m�ximo de tiempo de espera del pool.
	 * @return un array de VOs con el resultado del m�todo en todos los nodos de la federaci�n
	 * @throws Exception
	 */
	public static EstadoNodoVO[] search(NodoVO[] listaNodos, int maxNumObjPool, int maxWaitThread, int maxWaitPool) throws Exception{
		List peticiones = new ArrayList();
		for(int i = 0; i < listaNodos.length; i++){
			for (int j = 0; j < listaNodos[i].getServicio().length; j++){
				EstadoNodoVO estadoNodo = new EstadoNodoVO();
				estadoNodo.setNombreServicio(listaNodos[i].getServicio()[j].getNombre());
				estadoNodo.setDescripcionServicio(listaNodos[i].getServicio()[j].getDescripcion());
				estadoNodo.setNodo(listaNodos[i].getNodo());
				estadoNodo.setUrlWS(getAdress(listaNodos[i].getUrlWS(), listaNodos[i].getPuerto(), listaNodos[i].getServicio()[j].getUrl()));
				estadoNodo.setEstado("estadoServicio.INACTIVO");
				peticiones.add(estadoNodo);
			}
		}
		
		long start = System.currentTimeMillis();
		//DocumentosVO[] documentos = new DocumentosVO[comunidades.size()];
		List resultadoNodos = new ArrayList();
		int numDocumentos = 0;
		int numObjExecute = 0;
		int numObjToExecute = peticiones.size();
		try{
			while(numObjExecute < peticiones.size()){
				MultiSearcherThread[] rtArr = pool.borrowObjects(numObjToExecute, maxNumObjPool); //N�mero mayor que el m�ximo admitido por el pool
				EstadoNodoVO[] nodos = new EstadoNodoVO[rtArr.length];
				for(int i = numObjExecute, j = 0; i < numObjExecute+rtArr.length;i++, j++){
					nodos[j] = (EstadoNodoVO) peticiones.get(i);
				}
				EstadoNodoVO[] resultado = executor(rtArr, nodos, maxWaitThread);
				resultadoNodos.add(resultado);
				numDocumentos += resultado.length;
				numObjExecute += rtArr.length;
				numObjToExecute -= rtArr.length;
				if((System.currentTimeMillis() - start) >= maxWaitPool) {
                    throw new NoSuchElementException("Timeout waiting pool: "+maxWaitPool+" max wait pool miliseg.");
                }
				continue; // keep looping
			}
		} catch(Exception e) {
	    	logger.error("MultiSearcher - search ERROR: Pool fallido",e);
	    }
		long end = System.currentTimeMillis();
		logger.debug("MultiSearcher - search : Hilos terminados federada en =" + (end - start)+ " milisegundos, comienza obtenci�n de datos del pool");
		
		
		
		EstadoNodoVO[] retorno = new EstadoNodoVO[peticiones.size()];
		int ind=0;
		for (int i = 0; i<resultadoNodos.size(); i++){
			if (resultadoNodos.get(i)!=null && ((EstadoNodoVO[])resultadoNodos.get(i)).length > 0){
				for (int j=0; j<((EstadoNodoVO[])resultadoNodos.get(i)).length; j++){
					retorno[ind] = ((EstadoNodoVO[])resultadoNodos.get(i))[j];
					ind++;
				}
			}
		}
		
		
		
		return retorno;
	}
	
	private static EstadoNodoVO[] executor(MultiSearcherThread[] rtArr, EstadoNodoVO[] nodos, int maxWaitThread){
		for(int j = 0; j < rtArr.length ; j++ ){
			try{					//Deber�a ser url 
				logger.debug("MultiSearcher1 - executor : Direccion="+nodos[j].getUrlWS());
				rtArr[j].execute(nodos[j].getUrlWS());
			}catch(Exception e){
				logger.error("MultiSearcher - executor ERROR: Hilo fallido="+ nodos[j].getUrlWS()+" ERROR:",e);
				try{
					pool.returnObject(rtArr[j]);
				}catch(Exception ex){
					logger.error("MultiSearcher - executor ERROR: No se pudo retornar el hilo al pool="+ nodos[j].getUrlWS(),e);
				}	
			}
		}
		for(int i=0;i<rtArr.length;i++) {
			long startObject = System.currentTimeMillis();
			try {
	            while(!(rtArr[i]).isDone()) {
	            	Thread.sleep(500L);
	            	if((System.currentTimeMillis() - startObject) >= maxWaitThread) {
                        throw new NoSuchElementException("Timeout waiting thread: "+maxWaitThread+" max wait thread miliseg.");
                    }
					continue; // keep looping
	            }
	            nodos[i].setEstado(rtArr[i].getResult());
	            //Nombre nodo...
	            pool.returnObject(rtArr[i]);
			} catch(Exception e) {
            	logger.error("MultiSearcher - executor ERROR: Hilo fallido",e);
            	try{
					pool.returnObject(rtArr[i]);
					nodos[i].setEstado("estadoServicio.INACTIVO");
	        		logger.info("SERVICIO INACTIVO: " + nodos[i].getUrlWS() + " --> " +e);
				}catch(Exception ex){
					nodos[i].setEstado("estadoServicio.INACTIVO");
	        		logger.info("SERVICIO INACTIVO: " + nodos[i].getUrlWS() + " --> " +e);
					logger.error("MultiSearcher - executor ERROR: No se pudo retornar el hilo al pool="+ nodos[i].getUrlWS(),e);
				}	
            }
        }
		return nodos;
	}
	
	private static String getAdress(String nodo, String puerto, String urlServicio) {
		logger.debug("getAdress: nodo=[" + nodo + "]puerto=[" +puerto+"] urlservicio="+urlServicio);
		if(puerto!=null && !puerto.trim().equals(""))
			return "http://"+nodo+":"+puerto+urlServicio;
		return "http://"+nodo+urlServicio;
	}
}