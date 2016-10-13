/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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