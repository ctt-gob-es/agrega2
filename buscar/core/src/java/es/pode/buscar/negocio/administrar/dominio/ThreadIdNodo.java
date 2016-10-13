/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.buscar.negocio.administrar.dominio;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import es.pode.buscar.negocio.federada.servicios.SrvBuscarFederadaImportService;
import es.pode.buscar.negocio.federada.servicios.SrvBuscarFederadaServiceServiceLocator;

public class ThreadIdNodo implements Runnable {
	
	private static Logger log = Logger.getLogger(ThreadIdNodo.class);
	
	private es.pode.buscar.negocio.administrar.servicio.NodoVO nodo = null;
    private String result = null;
    private String address = null;
    
    public es.pode.buscar.negocio.administrar.servicio.NodoVO getNodo() {
		return nodo;
	}
	public void setNodo(es.pode.buscar.negocio.administrar.servicio.NodoVO nodo) {
		this.nodo = nodo;
	}		    	
	public String getResult() {
		return result;
	}
	public void setResult (String result) {
		this.result = result;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress (String address) {
		this.address = address;
	}
        // This method is called when the thread runs
        public void run()
        {	    	
        	SrvBuscarFederadaImportService port = null;
	         log.debug("La url del nodo del que queremos obtener el idNodo es ["+address+"]");
	         es.pode.buscar.negocio.federada.servicios.SrvBuscarFederadaServiceService srvBuscarFederadaService = new SrvBuscarFederadaServiceServiceLocator();
	         ((SrvBuscarFederadaServiceServiceLocator)srvBuscarFederadaService).setSrvBuscarFederadaServiceAddress(address);
	         try {
				port = srvBuscarFederadaService.getSrvBuscarFederadaService();
				this.result = port.obtenerIdentificadorNodo();
			} catch (ServiceException e) {				
				log.error("<"+address+"> #### Ejcucion servicio KO - ",e);
				return;
			} catch (RemoteException e) {	        	 
				log.error("<"+address+"> #### Ejcucion remota KO - ",e);
	        	 return;
	        }
	         log.debug(address+" #### srvBuscarFederadaEndPointAddress del fichero --> " + srvBuscarFederadaService.getSrvBuscarFederadaServiceAddress());
         
	         log.debug(address+" #### Execution Result OK= " + result);
	         return;
//	         try {
//				Thread.sleep(30000);
//			} catch (InterruptedException e) {
//				logger.error(e);
//			}
        }
}


