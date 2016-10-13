/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.publicacion.negocio.servicios;

import java.util.Arrays;
import java.util.Calendar;

import org.apache.log4j.Logger;

import es.pode.publicacion.negocio.dominio.AlbumCriteria;
import es.pode.publicacion.negocio.dominio.AlbumImpl;
import es.pode.publicacion.negocio.dominio.AlbumesUsuarioCriteria;
import es.pode.publicacion.negocio.dominio.IdODECriteria;
import es.pode.publicacion.negocio.dominio.TransicionImpl;
import es.pode.publicacion.negocio.soporte.I18nModuloPublicacion;


/**
 * @see es.pode.publicacion.negocio.servicios.SrvAlbumService
 */

public class SrvAlbumServiceImpl
    extends es.pode.publicacion.negocio.servicios.SrvAlbumServiceBase
{
	private static Logger logger = Logger.getLogger(SrvAlbumServiceImpl.class);
	public final static String SIN_ERRORES = "0.0";
	
	public final static String TITULO_YA_EXISTE = "12.1";
	public final static String ALBUM_NO_EXISTE = "12.2";
	public final static String DESCRIPCION_VACIA = "12.3";
	public final static String TITULO_VACIO = "12.4";
	public final static String NO_IDALBUM = "12.5";
	public final static String NO_USUARIO = "12.6";
	
    /**
	 * @param usuario
	 *            Usuario del que se quieren averiguar los albumes.
	 * @return Se retorna un VO con la información de los albumes creados por el usuario.
	 * @throws Exception
     */
    protected es.pode.publicacion.negocio.servicios.AlbumVO[] handleObtenAlbumesPorUsuario(java.lang.String usuario)
        throws java.lang.Exception
    {
    	AlbumVO[] retorno = new AlbumVO[0];
    	if (usuario == null || usuario.equals(""))
    	{
    		logger.error("Imposible averiguar albumes de usuario["+usuario+"]");
    		return retorno;
    	}
    	AlbumesUsuarioCriteria criterio = new AlbumesUsuarioCriteria(usuario);
        retorno = (AlbumVO[])this.getAlbumDao().obtenAlbumesDeUsuario(this.getAlbumDao().TRANSFORM_ALBUMVO,criterio).toArray(new AlbumVO[0]);
        if (logger.isInfoEnabled()) logger.info("Obtenidos ["+retorno.length+"]albumes de usuario["+usuario+"]");
        return retorno;
    }
	
	/**
     * @see es.pode.publicacion.negocio.servicios.SrvAlbumService#obtenOdesSinAlbum(java.lang.String)
	 * @param usuario
	 *            Nombre del usuario del que se quieren obtener los ODEs sin album.
	 * @return Se retorna un array de VO con la información de los ODEs que no tienen album.
	 * @throws Exception
     */
	protected TransicionVO[] handleObtenOdesSinAlbum(String usuario) 
		throws Exception 
	{
		TransicionVO[] retorno = new TransicionVO[0];
		if (usuario== null || usuario.equals("")) {
			logger.error("Error obteniendo los ODEs sin Album para el usuario vacio["+usuario+"]");
        	return retorno;
		}
//      Rescatamos los estados de creacion y rechazado para utilizarlos en la consulta.
        AlbumCriteria criterio = new AlbumCriteria();
//        Metemos el estado creacion y rechazado
        criterio.setEstadoActual(new String[]{SrvPublicacionServiceImpl.CREACION, SrvPublicacionServiceImpl.RECHAZADO});
//        Metemos el estado transitado a nulo (indica el estado actual del ODE)
        criterio.setEstadoTransitado(null);
//        Metemos el album del que quiero saber los ODEs. Son los ODEs sin album
        criterio.setAlbum(null);
        retorno = (TransicionVO[])this.getTransicionDao().buscarOdesPorAlbum(this.getTransicionDao().TRANSFORM_TRANSICIONVO,criterio).toArray(new TransicionVO[0]);
        if (logger.isInfoEnabled()) logger.info("Obtenidos ["+((retorno!=null)?retorno.length:0)+"]odes sin album del usuario["+usuario+"]");
        return retorno;
	}



	/**
     * @see es.pode.publicacion.negocio.servicios.SrvAlbumService#obtenOdesPorAlbum(java.lang.Long)
	 * @param idAlbum
	 *            Identificador del album del que se quieren obtener los ODEs.
	 * @return Se retorna un array de VO con la información de los ODEs que contiene el album.
	 * @throws Exception
     */
    protected es.pode.publicacion.negocio.servicios.TransicionVO[] handleObtenOdesPorAlbum(java.lang.Long idAlbum)
        throws java.lang.Exception
    {
    	TransicionVO[] retorno = new TransicionVO[0];
        if(idAlbum == null)
        {
//        	logger.error("Error obteniendo los ODEs por el idAlbum["+idAlbum+"]");
        	logger.error("Error obteniendo los ODEs por el idAlbum nulo");
        	return retorno;
        }
//        Primero seleccionamos el album según el idAlbum que nos pasan
        AlbumImpl album = (AlbumImpl)this.getAlbumDao().load(idAlbum);
        if (album == null) {
			logger.error("Error obteniendo los ODEs del album con identificador["+idAlbum+"]. No existe el album.");
			return retorno;
		}
//        Rescatamos los estados de creacion y rechazado para utilizarlos en la consulta.
        AlbumCriteria criterio = new AlbumCriteria();
//        Metemos el estado creacion y rechazado
        criterio.setEstadoActual(new String[]{SrvPublicacionServiceImpl.CREACION, SrvPublicacionServiceImpl.RECHAZADO});
//        Metemos el estado transitado a nulo (indica el estado actual del ODE)
        criterio.setEstadoTransitado(null);
//        Metemos el album del que quiero saber los ODEs.
        criterio.setAlbum(album);
        retorno = (TransicionVO[])this.getTransicionDao().buscarOdesPorAlbum(this.getTransicionDao().TRANSFORM_TRANSICIONVO,criterio).toArray(new TransicionVO[0]);
        if (logger.isInfoEnabled()) logger.info("Obtenidos ["+((retorno!=null)?retorno.length:0)+"]odes del album["+album.getTitulo()+"] del usuario["+album.getUsuario()+"]");
        return retorno;
    }

    /**
     * @see es.pode.publicacion.negocio.servicios.SrvAlbumService#desasociarODEAlbum(java.lang.String)
	 * @param idODE
	 *            Identificador del ODE que se quiere desasociar de su album.
	 * @return Se retorna el VO con el ODE tal y como se queda.
	 * @throws Exception
     */
    protected es.pode.publicacion.negocio.servicios.TransicionVO handleDesasociarODEAlbum(java.lang.String idODE)
        throws java.lang.Exception
    {
    	TransicionVO retorno = null;
    	if (idODE == null || idODE.equals("")) {
			logger.error("Error desasociando ODE a Album. Identificador de ode vacio["+idODE+"]");
			return retorno;
		}
//        Cada ODE puede estar en un album y solo uno. Sacamos el album al que esta asociado este ODE.
//    	Los ODEs solo pueden esta en estado CREADO y RECHAZADO para poder desasociarlos.
    	IdODECriteria criterio = new IdODECriteria(idODE, null, null);
    	TransicionImpl[] transicion = (TransicionImpl[])this.getTransicionDao().buscarEstadoPorCriterioIdODE(criterio).toArray(new TransicionImpl[0]);
    	if (transicion == null || transicion.length == 0) {
			logger.error("Error desasociando ODE a Album. El ODE con identificador["+idODE+"] no existe.");
			return retorno;
		}
    	if (!transicion[0].getEstadoActual().getNombre().equals(SrvPublicacionServiceImpl.CREACION) &&
    			!transicion[0].getEstadoActual().getNombre().equals(SrvPublicacionServiceImpl.RECHAZADO) &&
    			!transicion[0].getEstadoActual().getNombre().equals(SrvPublicacionServiceImpl.PUBLICADO_VERSIONANDOSE)
    			) {
			logger.error("Error desasociando ODE a Album. El ODE con identificador["+idODE+"] esta en estado["+transicion[0].getEstadoActual().getNombre()+"] y solo se pueden desasociar" +
					" en estado CREACION o RECHAZADO");
			return retorno;
		}
//    	El ODE que me pasan esta en estado CREADO o RECHAZADO, ahora tenemos que desasociarlo del Album. Machacamos su album (si lo tiene)
    	AlbumImpl album = (AlbumImpl)transicion[0].getAlbum();
    	transicion[0].setAlbum(null);
    	this.getTransicionDao().update(transicion[0]);
    	retorno = this.getTransicionDao().toTransicionVO(transicion[0]);
    	if (logger.isInfoEnabled()) logger.info("Desasociado ODE con identificador["+idODE+"] de album"+((album== null)?".":" con idAlbum["+album.getId()+"] y titulo["+album.getTitulo()+"]"));
        return retorno; 
    }

    /**
     * @see es.pode.publicacion.negocio.servicios.SrvAlbumService#asociarODEAlbum(java.lang.String, java.lang.String, java.lang.Long)
	 * @param idODE
	 *            Identificador del ODE que se quiere desasociar de su album.
	 * @param usuario
	 *            Nombre del usuario que realiza la asociacion. Tienen que coincidir con el dueño del album al que se quiere asociar el ODE.
	 * @param idAlbum,
	 *            Identificador del Album al que se quiere asociar el ODE.
	 * @return Se retorna el VO con el ODE tal y como se queda.
	 * @throws Exception
     */
    protected es.pode.publicacion.negocio.servicios.TransicionVO handleAsociarODEAlbum(java.lang.String idODE, java.lang.String usuario, java.lang.Long idAlbum)
        throws java.lang.Exception
    {
//        Solo podemos asociar ODEs a albumes del mismo usuario
//    	Vamos a por el album y vemos si es del usuario
    	AlbumImpl album = (AlbumImpl)this.getAlbumDao().load(idAlbum);
    	if (album == null || !album.getUsuario().equals(usuario) ) {
			logger.error("Error asociando ODE a Album." + ((album==null)?" El Album con idAlbum["+idAlbum+"]no existe.":" El Album ["+album.getTitulo()+"] no es del usuario["+album.getUsuario()+"]"));
//			TODO¿hay que devolver algun error?
			return null; 
		}
//    	Existe el album (asociado al usuario)
//    	Comprobamos que existe el ODE y esta en los estados permitidos
//    	Los ODEs solo pueden esta en estado CREADO y RECHAZADO para poder desasociarlos.
    	IdODECriteria criterio = new IdODECriteria(idODE, null, null);
    	TransicionImpl[] transicion = (TransicionImpl[])this.getTransicionDao().buscarEstadoPorCriterioIdODE(criterio).toArray(new TransicionImpl[0]);
    	if (transicion == null || transicion.length == 0) {
			logger.error("Error asociando ODE a Album. El ODE con identificador["+idODE+"] no existe.");
			return null;
		}
    	if (!transicion[0].getEstadoActual().getNombre().equals(SrvPublicacionServiceImpl.CREACION) &&
    			!transicion[0].getEstadoActual().getNombre().equals(SrvPublicacionServiceImpl.RECHAZADO)
    			) {
			logger.error("Error asociando ODE a Album con idAlbum["+album.getId()+"] y titulo["+album.getTitulo()+"]. El ODE con identificador["+idODE+"] esta en estado["+transicion[0].getEstadoActual().getNombre()+"] y solo se pueden asociar" +
					" en estado CREACION o RECHAZADO");
			return null;
		}
//    	Aqui tenemos el album que sabemos que es del usuario, y el ODE que sabemos que existe y esta en los etados permitidos.
//    	Hay que ver que no este ya asociado al ningun album (no lo machacamos)
    	if(transicion[0].getAlbum() != null)
    	{
    		logger.error("Error asociando ODE a Album con idAlbum[] y titulo[]. El ODE ya esta asociado al album con idAlbum["+transicion[0].getAlbum().getId()+"] y titulo["+transicion[0].getAlbum().getTitulo()+"]");
//    		¿No hay que devolver aqui algun codigo de error?
    		return null;
    	}
//    	Aqui sabemos que el ODE existe, esta en los estados permitidos y no tiene album asociado.
    	transicion[0].setAlbum(album);
    	this.getTransicionDao().update(transicion[0]);
    	if (logger.isInfoEnabled()) logger.info("Asociado ODE con identificador["+transicion[0].getIdODE()+"] a Album con idAlbum["+album.getId()+"] y titulo["+album.getTitulo()+"]");
        return this.getTransicionDao().toTransicionVO(transicion[0]);
    }

    /**
     * @see es.pode.publicacion.negocio.servicios.SrvAlbumService#altaAlbum(java.lang.String, java.lang.String, java.lang.String)
	 * @param titulo,
	 *            Nombre del album que se quiere crear.
	 * @param descripción,
	 *            Descripción del album que se va a crear.
	 * @param usuario,
	 *            Nombre del usuario que crea el ODE.
	 * @return Se retorna el VO con el album tal y como se crea.
	 * @throws Exception
     */
    protected es.pode.publicacion.negocio.servicios.ResultadoOperacionAlbumVO handleAltaAlbum(java.lang.String titulo, java.lang.String descripcion, java.lang.String usuario)
        throws java.lang.Exception
    {
    	
//        Creamos un Album de la nada
    	if (titulo == null || titulo.equals("")) 
    	{
			logger.error("Error creando un Album nuevo. El título no puede ser vacío["+titulo+"]");
			return new ResultadoOperacionAlbumVO(TITULO_VACIO,I18nModuloPublicacion.getPropertyValueI18n(TITULO_VACIO),null);
		}
    	if (descripcion == null || descripcion.equals("")) {
			logger.error("Error creando un Album nuevo. La descripción no puede ser vacía["+descripcion+"]");
			return new ResultadoOperacionAlbumVO(DESCRIPCION_VACIA,I18nModuloPublicacion.getPropertyValueI18n(DESCRIPCION_VACIA),null);
		}
    	if (usuario == null || usuario.equals("")) {
			logger.error("Error creando un Album nuevo. El usuario no puede ser vacío["+usuario+"]");
			return new ResultadoOperacionAlbumVO(NO_USUARIO,I18nModuloPublicacion.getPropertyValueI18n(NO_USUARIO),null);
		}
    	AlbumVO[] albumesUsuario = obtenAlbumesPorUsuario(usuario);
    	if(albumesUsuario != null && albumesUsuario.length>0){
    		for (int i = 0; i < albumesUsuario.length; i++) {
    			if(logger.isDebugEnabled()) logger.debug("El usuario: ["+usuario +"] tiene: ["+ albumesUsuario.length+"] albumes");
    			if(albumesUsuario[i].getTitulo().equals(titulo)){
    				return new ResultadoOperacionAlbumVO(TITULO_YA_EXISTE,I18nModuloPublicacion.getPropertyValueI18n(TITULO_YA_EXISTE), albumesUsuario[i]);
    			}
    		}
    	}
    	
    	AlbumImpl album = (AlbumImpl)this.getAlbumDao().create(titulo, descripcion, usuario, Calendar.getInstance());
    	if (logger.isInfoEnabled()) logger.info("Añadido Album a usuario["+usuario+"] con titulo["+titulo+"] y descripcion["+descripcion+"]"); 
        return new ResultadoOperacionAlbumVO(SIN_ERRORES,I18nModuloPublicacion.getPropertyValueI18n(SIN_ERRORES),this.getAlbumDao().toAlbumVO(album));
    }

    /**
     * @see es.pode.publicacion.negocio.servicios.SrvAlbumService#bajaAlbum(java.lang.Long)
	 * @param idAlbum,
	 *            Identificador del Album que se quiere dar de baja.
	 * @return No se devuelve nada.
	 * @throws Exception
     */
    protected ResultadoOperacionAlbumVO handleBajaAlbum(java.lang.Long idAlbum)throws java.lang.Exception
    {
//    	Higiene de datos
    	if (idAlbum == null) {
    		logger.error("Error eliminando un Album. Identificador vacio");
    		return new ResultadoOperacionAlbumVO(NO_IDALBUM,I18nModuloPublicacion.getPropertyValueI18n(NO_IDALBUM),null);
		}
//    	Primero vemos si existe el album
    	AlbumImpl album = (AlbumImpl)this.getAlbumDao().load(idAlbum);
    	if (album == null){
    		logger.error("Error eliminando un Album. El idAlbum["+idAlbum+"] no hace referencia a ningun Album.");
    		return new ResultadoOperacionAlbumVO(ALBUM_NO_EXISTE,I18nModuloPublicacion.getPropertyValueI18n(ALBUM_NO_EXISTE),null);
    		//throw new Exception("Error eliminando un Album. El idAlbum["+idAlbum+"] no hace referencia a ningun Album.");
    	}
    	
//    	Cuando se da de baja un album, hay que desasociar todos los ODEs que estan asociados al album y luego borrar el album.
    	AlbumCriteria criterio = new AlbumCriteria(new String[]{SrvPublicacionServiceImpl.RECHAZADO,SrvPublicacionServiceImpl.CREACION}, null, album);
        TransicionImpl[] transiciones = (TransicionImpl[])this.getTransicionDao().buscarOdesPorAlbum(criterio).toArray(new TransicionImpl[0]);
//        Si hay transiciones para ese album, borro las referencias al mismo en los ODEs 
        if (transiciones != null) {
//        	Recorro las transiciones borrando la referencia al Album
        	for (int i = 0; i < transiciones.length; i++)
				transiciones[i].setAlbum(null);
//        	Actualizo las transiciones
			this.getTransicionDao().update(Arrays.asList(transiciones));
		}
//        Borro el Album de la lista de Albumes
        this.getAlbumDao().remove(album);
        if (logger.isInfoEnabled()) logger.info("Eliminado Album con idAlbum["+idAlbum+"] y titulo["+album.getTitulo()+"] de usuario["+album.getUsuario()+"]");
        return new ResultadoOperacionAlbumVO(SIN_ERRORES,I18nModuloPublicacion.getPropertyValueI18n(SIN_ERRORES),null);
    }

    /**
     * @see es.pode.publicacion.negocio.servicios.SrvAlbumService#modificaAlbum(java.lang.Long, java.lang.String, java.lang.String)
	 * @param idAlbum,
	 *            Identificador del Album que se quiere modificar.
	 * @param titulo,
	 *            Titulo del Album que se quiere modificar.
	 * @param descripcion,
	 *            Descripcion del Album que se quiere modificar.
	 * @return Se devuelve el Album modificado.
	 * @throws Exception
     */
    protected es.pode.publicacion.negocio.servicios.ResultadoOperacionAlbumVO handleModificaAlbum(java.lang.Long idAlbum, java.lang.String titulo, java.lang.String descripcion)
        throws java.lang.Exception
    {
//    	Higiene de datos
    	if (idAlbum == null)
    	{
    		logger.error("Error modificando album con idAlbum nulo");
    		return new ResultadoOperacionAlbumVO(NO_IDALBUM,I18nModuloPublicacion.getPropertyValueI18n(NO_IDALBUM),null);
    	}
    	if (titulo == null || titulo.equals(""))
    	{
    		logger.error("Error modificando album con titulo vacio["+titulo+"]");
    		return new ResultadoOperacionAlbumVO(TITULO_VACIO,I18nModuloPublicacion.getPropertyValueI18n(TITULO_VACIO),null);
    	}
    	if (descripcion == null || descripcion.equals(""))
    	{
    		logger.error("Error modificando album con descripcion vacia["+descripcion+"]");
    		return new ResultadoOperacionAlbumVO(DESCRIPCION_VACIA,I18nModuloPublicacion.getPropertyValueI18n(DESCRIPCION_VACIA),null);
    	}
//		Primero vamos a ver si existe el album que se quiere modificar
    	AlbumImpl album = (AlbumImpl)this.getAlbumDao().load(idAlbum);
    	if (album == null) {
			logger.error("Error modificando album con idAlbum["+idAlbum+"]. El Album no existe.");
			return new ResultadoOperacionAlbumVO(ALBUM_NO_EXISTE,I18nModuloPublicacion.getPropertyValueI18n(ALBUM_NO_EXISTE),null);
		}
    	//Vemos si el nuevo titulo, corresponde con alguno de los que ya existen en la BBDD
    	AlbumVO[] albumesUsuario = obtenAlbumesPorUsuario(album.getUsuario());
    	if(albumesUsuario != null && albumesUsuario.length>0){
    		for (int i = 0; i < albumesUsuario.length; i++) {
    			if(logger.isDebugEnabled()) logger.debug("El usuario: ["+album.getUsuario() +"] tiene: ["+ albumesUsuario.length+"] albumes");
    			if(albumesUsuario[i].getTitulo().equals(titulo)){
    				return new ResultadoOperacionAlbumVO(TITULO_YA_EXISTE,I18nModuloPublicacion.getPropertyValueI18n(TITULO_YA_EXISTE),albumesUsuario[i]); 
    			}
    		}
    	}
    	
    	album.setDescripcion(descripcion);
    	album.setTitulo(titulo);
    	this.getAlbumDao().update(album);
    	return new ResultadoOperacionAlbumVO(SIN_ERRORES,I18nModuloPublicacion.getPropertyValueI18n(SIN_ERRORES),this.getAlbumDao().toAlbumVO(album));
    }

    
    /**
     * @see es.pode.publicacion.negocio.servicios.SrvAlbumService#modificaAlbum(java.lang.Long, java.lang.String, java.lang.String)
	 * @param idODE,
	 *            Identificador alfanumérico del ODE que voy a comprobar si existe o no en un album
	 * @param album,
	 *           Album en el que quiero ver si existe o no el ODE.
	 * @return Se devuelve un Boolean.
	 * @throws Exception
     */
    protected Boolean handleExisteOdeEnAlbum(String idODE, AlbumVO album)  throws java.lang.Exception{
    	// Verificamos datos:
    	if(idODE == null){
//    		logger.error("Error comprobando la existencia en el album del ode con identificador["+idODE+"]");
    		logger.error("Error comprobando la existencia en el album del ode con identificador nulo");
    		return null;
    	}
    	if (album == null)
    	{
//    		logger.error("Error comprobando la existencia de un ode en el album ["+ album +"]");
    		//Evidentemente, es null
    		logger.error("Error comprobando la existencia de un ode en el album");
    		return false;
    	}
    	
    	if(logger.isDebugEnabled()) logger.debug("Voy a ver si el ode: [" +idODE+ "] existe en el album ["+ album.getTitulo() +"]");
    	Boolean existe = false;
    	try {
			TransicionVO[] transicion = this.obtenOdesPorAlbum(album.getId());
			
			for (int i = 0; i < transicion.length; i++) {
				if (idODE.equals(transicion[i].getIdODE())) 
					existe = true;
			}
		} catch (Exception e) {
			logger.error("Error obteniendo los odes del album ["+ album.getTitulo()+"]");
			throw new Exception("Error comprobando si existe el ODE "+idODE+" en el album ["+album.getTitulo()+"].");
		}
		return existe;
    }



    /**
     * Elimina los Albumes de los usuarios pasados como parámetro
     * @param usuarios,
	 *            Identificadores de los usuarios cuyos ODEs se desean eliminar.
	 * @return Se devuelve un Boolean.
	 * @throws Exception
     */
	protected Boolean handleEliminarAlbumesUsuarios(String[] usuarios) throws Exception {
		boolean resultado= true;
		try{
			for (int i = 0; i < usuarios.length; i++) {
				AlbumVO[] albumesUsuario = this.obtenAlbumesPorUsuario(usuarios[i]);
				if(logger.isDebugEnabled()) logger.debug("El usuario ["+ usuarios[i]+"] tenia [" +albumesUsuario.length+"] albumes.");
				logger.info("Vamos a eliminar los albumes del usuario ["+ usuarios[i] +"]");
				for(int j=0; j<albumesUsuario.length; j++){
					logger.debug("Vamos a eliminar el album  ["+ albumesUsuario[j].getId()+ "]");
					bajaAlbum(albumesUsuario[j].getId());
				}
			}
		}catch(Exception e){
			logger.error("Error eliminando los albumes de ["+ usuarios.length+"] usuarios");
			resultado=false;
		}
		return resultado;
	}

    /**
     * Devuelve el albume por el identificador pasado como parámetro
     * @param idAlbum,
	 *            Identificadores de un album.
	 * @return Se devuelve un AlbumVO.
	 * @throws Exception
     */
	protected AlbumVO handleObtenerAlbumPorId(Long idAlbum) throws Exception {
		if (idAlbum ==  null)
		{
			logger.error("Error consultando datos de album con identificador vacío.");
			return null;
		}
		try {
			AlbumVO respuesta = (AlbumVO)this.getAlbumDao().load(this.getAlbumDao().TRANSFORM_ALBUMVO,idAlbum);
			if(logger.isDebugEnabled())logger.debug("Consultado album con id["+idAlbum+"] y titulo["+(respuesta!=null?respuesta.getTitulo():"")+"]");			
			return respuesta;

		} catch (Exception e) {
			logger.error("Error consultando datos de album con identificador["+idAlbum+"]."+e);
		}
		return null;
	}
    
    
}