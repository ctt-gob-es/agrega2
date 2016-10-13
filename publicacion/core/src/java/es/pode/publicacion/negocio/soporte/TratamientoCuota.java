/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.publicacion.negocio.soporte;

import org.apache.log4j.Logger;

import es.pode.localizador.negocio.servicios.SrvLocalizadorService;
import es.pode.publicacion.negocio.dominio.Transicion;
import es.pode.publicacion.negocio.servicios.EstadoVO;
import es.pode.publicacion.negocio.servicios.SrvPublicacionServiceImpl;
import es.pode.publicacion.negocio.servicios.TransicionVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;


public class TratamientoCuota {
	private static Logger logger = Logger.getLogger(TratamientoCuota.class);
	/**
	 * Este metodo recibe la ultima transicion del ODE y calcula si para el ODE al que pertenece la transicion se ha superado la cuota de usuario.
	 * Un usuario excede su cuota si el espacio consumido por sus ODEs en estado CREADO y RECHAZADO supera su asignación de espacio en disco.
	 * */
	public static boolean excedeCuotaUsuario(SrvLocalizadorService localizador, Transicion transicion, TransicionVO[] transiciones, EstadoVO estadoActual) throws Exception
	{
//		Para comprobar si el ODE al que pertenece la transición ha superado el espacio asignado al usuario, tenemos que ver el espacio consumido
//		por el usuario actualmente y el espacio que consume el ODE al que pertenece la transicion.
		
//		Primero considero el estado en el que esta el ODE. Si no esta CREADO o RECHAZADO o PUBLICADO_AUTONOMO, no me preocupa
		if (!estadoActual.getNombre().equals(SrvPublicacionServiceImpl.CREACION) ||
				!estadoActual.getNombre().equals(SrvPublicacionServiceImpl.RECHAZADO)||
				!estadoActual.getNombre().equals(SrvPublicacionServiceImpl.PUBLICADO_AUTONOMO))
				return false; // si no esta en estado CREADO ni RECHAZADO, ni PUBLICADO_AUTONOMO, no "pesa" a la hora de calcular el espacio consumido. No excedemos la cuota
		 
//		Calculamos el consumo de la cuota
		long cuotaConsumida = calculaCuotaConsumidaUsuario(localizador, transicion.getIdUsuarioCreacion(), transiciones);
//		Calculamos el consumo actual del ODE concreto.
		long consumoODE = localizador.calculaEspacioLocalizador(transicion.getIdODE()).longValue();
//		Calculamos el consumo anterior del ODE
		long consumoAnterior =localizador.consultaEspacioLocalizador(transicion.getIdODE()).longValue();
		if (logger.isDebugEnabled())logger.debug("Calculando exceso de cuota para usuario["+transicion.getIdUsuarioCreacion()+"] ode["+transicion.getIdODE()+"]: cuota consumida["+cuotaConsumida+"], consumo ODE["+consumoODE+"] consumo anterior["+consumoAnterior+"]");
//		Si el consumo acumulado, menos lo que ocupaba antes el ode, más lo que ocupa ahora es mayor que lo disponible, no cabe.
		return ((cuotaConsumida-consumoAnterior)+consumoODE) > LdapUserDetailsUtils.getCuota().longValue();
	}
	
	/**
	 * Este metodo calcula la cuota de disco consumida por el usuario. Para ello, tiene en cuenta los ODEs en estado CREADO, RECHAZADO y PUBLICADO_AUTONOMO para
	 * calcular su consumo.
	 * */
	public static long calculaCuotaConsumidaUsuario(SrvLocalizadorService localizador, String idUsuario, TransicionVO[] transiciones) throws Exception
	{
		//TransicionVO[] odesEnDisco =  (TransicionVO[])this.handleObtenODEsCreadosPorUsuario(idUsuario);
		TransicionVO[] odesEnDisco = transiciones;
		if (odesEnDisco == null || odesEnDisco.length == 0) // no hay nada para este usuario, no ha consumido nada.
			return 0;
//		Para cada identificador tengo que saber cuanto ocupan en disco, utilizo el localizador.
		String[] identificadores = new String[odesEnDisco.length];
		for (int i = 0; i < odesEnDisco.length; i++) {
			identificadores[i] = odesEnDisco[i].getIdODE();
		}
//		Se los mando al localizador para que me diga cuanto ocupan las localizaciones en disco.
		Long[] espaciosOcupados = localizador.consultaEspacioLocalizadores(identificadores);
		long consumoTotal = 0;
		for (int i = 0; i < espaciosOcupados.length; i++) {
			consumoTotal+=(espaciosOcupados[i]!=null?espaciosOcupados[i].longValue():0);
		}
		if (logger.isDebugEnabled()) logger.debug("El espacio consumido por los ODEs en estado CREADO, RECHAZADO y PUBLICADO_AUTONOMO del usuario["+idUsuario+"] es de ["+consumoTotal+"] bytes");
		return consumoTotal;
	}
	
}
