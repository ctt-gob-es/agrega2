/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.agrega.soporte.tag.el;

import java.rmi.RemoteException;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.xml.rpc.ServiceException;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.soporte.configuracionPortal.ConfiguracionPortal;
import es.pode.soporte.configuracionPortal.ConfiguracionPortalImpl;

public class ELTag extends BodyTagSupport{
	
	private static final long serialVersionUID = 1L;

	public static boolean esTaller(){
		
		String esTaller = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NODO_TALLER);
//		if(esTaller.equals("true"))
//			return true;
//		return false;
		return (esTaller.equals("true"));

	}
	public static boolean esRegistro() throws ServiceException, RemoteException{

		ConfiguracionPortal configurar = ConfiguracionPortalImpl.getInstance();
		Integer registrar = configurar.getRegistrese();	
//		if(registrar.equals(1))
//			return true;
//		return false;
		return(registrar.equals(1));
	}
	public static boolean esContrasena() throws ServiceException, RemoteException{
		ConfiguracionPortal configurar = ConfiguracionPortalImpl.getInstance();
		Integer password = configurar.getEnlacePassword();
//		if(password.equals(1))
//			return true;
//		return false;
		return (password.equals(1));
	}
	public static boolean esOpenId() throws ServiceException, RemoteException{
		ConfiguracionPortal configurar = ConfiguracionPortalImpl.getInstance();
		Integer openId = configurar.getOpenId(); 
//		if(openId.equals(1))
//			return true;
//		return false;
		return (openId.equals(1));
	}
	
	public static boolean esLdapExternal(){
			
			String esLdapExternal = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.LDAPEXTERNO);
//			if(esLdapExternal.equals("true"))
//				return true;
//			return false;
			return(esLdapExternal.equals("true"));
		}
	
	public static boolean habilitadoPublicarAutonomo(){

		String habilitadoPubAutonomo = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PUBLICADOS_AUTONOMOS_HABILITADO);
		return habilitadoPubAutonomo.equals("true");
	}
	
	public static boolean habilitadoPerfilPublico(){

		String habilitadoPerfilPublico = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PERFIL_PUBLICO);
		return habilitadoPerfilPublico.equals("true");
	}
	
	public static boolean esRememberMe(){
		
		String esRememberMe = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.REMEMBERME);
//		if(esRememberMe.equals("true"))
//			return true;
//		return false;
		return(esRememberMe.equals("true"));
	}
}