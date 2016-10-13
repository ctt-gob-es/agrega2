// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.publicacion.negocio.dominio;

/**
 * @see es.pode.publicacion.negocio.dominio.Album
 */
public class AlbumDaoImpl
    extends es.pode.publicacion.negocio.dominio.AlbumDaoBase
{
    /**
     * @see es.pode.publicacion.negocio.dominio.AlbumDao#toAlbumVO(es.pode.publicacion.negocio.dominio.Album)
     */
    public es.pode.publicacion.negocio.servicios.AlbumVO toAlbumVO(final es.pode.publicacion.negocio.dominio.Album entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.publicacion.negocio.servicios.AlbumVO) 
                  this.getBeanMapper().map(entity, es.pode.publicacion.negocio.servicios.AlbumVO.class, DEF_MAPPING_ALBUM_ALBUMVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.publicacion.negocio.dominio.AlbumDao#fromAlbumVO(es.pode.publicacion.negocio.servicios.AlbumVO)
	 */
    public es.pode.publicacion.negocio.dominio.Album fromAlbumVO(final es.pode.publicacion.negocio.servicios.AlbumVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromAlbumVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.publicacion.negocio.dominio.AlbumDao#fromAlbumVO(es.pode.publicacion.negocio.servicios.AlbumVO ,es.pode.publicacion.negocio.dominio.Album)	 
	 */
    public void fromAlbumVO(es.pode.publicacion.negocio.servicios.AlbumVO vo, es.pode.publicacion.negocio.dominio.Album entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromAlbumVO(vo, entity);
    }
}