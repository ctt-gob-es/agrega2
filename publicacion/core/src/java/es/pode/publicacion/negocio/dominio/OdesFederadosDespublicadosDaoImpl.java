// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.publicacion.negocio.dominio;

import es.pode.publicacion.negocio.servicios.OdesFederadosDespublicadosVO;
import es.pode.publicacion.negocio.servicios.TransicionVO;

/**
 * @see es.pode.publicacion.negocio.dominio.OdesFederadosDespublicados
 */
public class OdesFederadosDespublicadosDaoImpl
    extends es.pode.publicacion.negocio.dominio.OdesFederadosDespublicadosDaoBase
{

	@Override
	public OdesFederadosDespublicadosVO toOdesFederadosDespublicadosVO(
			OdesFederadosDespublicados entity) {
		
        return (es.pode.publicacion.negocio.servicios.OdesFederadosDespublicadosVO) 
                this.getBeanMapper().map(entity, es.pode.publicacion.negocio.servicios.OdesFederadosDespublicadosVO.class, DEF_MAPPING_ODESFEDERADOSDESPUBLICADOS_ODESFEDERADOSDESPUBLICADOSVO);
	}

}