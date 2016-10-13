package es.pode.publicacion.negocio.soporte;

import net.sf.dozer.util.mapping.converters.CustomConverter;

import org.apache.log4j.Logger;

import es.pode.publicacion.negocio.dominio.Album;
import es.pode.publicacion.negocio.servicios.AlbumVO;

public class AlbumVODeTransicionVO implements CustomConverter {

	private static Logger logger = Logger.getLogger(AlbumVODeTransicionVO.class);
	
	/*
	 * Cuando se mapea un album, de las transiciones asociadas al album, no me interesan los otros albumes a los que esta asociado (porque soy yo mismo).
	 * Tengo que ponerlas a nulo, porque si no da un bucle infinito cuando se llama al WS del servicio y se quieren mapear los resultados.
	 */
	public Object convert(Object destination, Object source, Class destClass,
			Class srcClass) {
//		Si no hay nada que hacer, no hago nada
		if (source == null) {
			return null;
		}
    	if(source instanceof Album)
    	{
    		Album albumActual = (Album)source;
    		if (albumActual.getId()==null || albumActual.getId().equals(new Long(0)))
    		{
    			logger.debug("mapeando albumVO nulo, volvemos");
    			return new AlbumVO();
    		}
    		AlbumVO albumDestino = (AlbumVO)destination;
    		if (destination == null)
    			albumDestino = new AlbumVO();
//  		Compiamos los datos del album, pero nulamos la lista de transiciones que pueda tener asociada.
    		albumDestino.setDescripcion(albumActual.getDescripcion());
    		albumDestino.setFechaCreacion(albumActual.getFechaCreacion().toString());
    		albumDestino.setId(albumActual.getId());
    		albumDestino.setOdes(null);
    		albumDestino.setTitulo(albumActual.getTitulo());
    		albumDestino.setUsuario(albumActual.getUsuario());
    		logger.info("Mapeamos un album["+albumActual.getTitulo()+"] de una trasicion sin transiciones asociadas");
    		return albumDestino;
    	}
    	else
    	{
    		logger.error("Estamos intentando mapear un AlbumVO a un AlbumImpl!!!!!!, me enfado.");
    		return null;
    	}
	}
}
