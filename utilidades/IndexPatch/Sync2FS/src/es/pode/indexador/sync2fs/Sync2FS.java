/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * 
 */
package es.pode.indexador.sync2fs;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import es.pode.indexador.negocio.servicios.indexado.SrvIndexadorService;
import es.pode.indexador.negocio.servicios.indexado.SrvIndexadorServiceServiceLocator;

/**
 * @author dgonzalezd
 *
 */
public class Sync2FS {

	static String nodo="http://localhost:8080";
	static String url="/indexador-0.1/services/SrvIndexadorService";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SrvIndexadorServiceServiceLocator locator = new SrvIndexadorServiceServiceLocator();
		try {
			
			String esteNodo=nodo;
			if(args.length>0) {
				esteNodo=args[0];
			}
			System.out.println("Nodo es "+esteNodo);

			SrvIndexadorService servicio = locator.getSrvIndexadorService(new URL(esteNodo+url));
			boolean resultado=servicio.sincronizarIndiceCompass();
			
			System.out.println("La operación ha salido "+(resultado?"bien":"mal"));
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
