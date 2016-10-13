// license-header java merge-point
package es.pode.visualizador.presentacion.portada;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;

import es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO;
import es.pode.configuracionPlataforma.negocio.servicios.SrvPropiedadService;
import es.pode.contenidos.negocio.descargas.servicio.DescDescargaVO;
import es.pode.contenidos.negocio.descargas.servicio.DescargaVO;
import es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO;
import es.pode.soporte.configuracionPortal.ConfiguracionPortal;
import es.pode.soporte.configuracionPortal.ConfiguracionPortalImpl;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.url.PerfilPublico;
import es.pode.visualizador.presentacion.descargas.DescargasControllerImpl.DescargaInfo;
import es.pode.visualizador.presentacion.noticia.NoticiaCodex;


/**
 * @see es.pode.visualizador.presentacion.portada.PortadaController
 */
public class PortadaControllerImpl extends PortadaController {
	
	private SrvPropiedadService prop = null;
	private java.util.Properties pSpringProperties = null;
	private static Logger logger = Logger.getLogger(PortadaControllerImpl.class);
	/**
     * The bean factory reference instance.
     */
    private org.springframework.beans.factory.access.BeanFactoryReference beanFactoryPortada;
    IntercepcionPortada interceptorPortada = null;
    
	/**
	 * @see es.pode.visualizador.presentacion.portada.PortadaController#obtenerNoticias(org.apache.struts.action.ActionMapping,
	 *      es.pode.visualizador.presentacion.portada.ObtenerNoticiasForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public final void obtenerNoticias(ActionMapping mapping,
			es.pode.visualizador.presentacion.portada.ObtenerNoticiasForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	
//		Accedemos al bean de acceso a la portada para que se intercepte la llamada.
		if (interceptorPortada == null)
			interceptorPortada = ((IntercepcionPortada)this.getContext().getBean("accesoPortada"));
		interceptorPortada.accesoPortada(((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage());
		
		/* En la parte pública no se debe acceder a configuracion portal
		 
		ConfiguracionPortal configuracionPortal = ConfiguracionPortalImpl.getInstance();
		
		Integer itinerarios = configuracionPortal.getItinerariosAprendizaje();
		if(itinerarios==1) {
			//Vamos a obtener los últimos itinerarios creados y modificados con su url pública
			try{				
				form.setItinerariosActivo(1);
				
				int itinerariosTotal = new Integer(this.getPropertyValue("portada.itinerarios.total")).intValue();
				
				GrupoPublicoVO[] creados = this.getSrvPerfilPublico().listarGruposCreadosUltimos();
				GrupoPublicoVO[] modificados = this.getSrvPerfilPublico().listarGruposModificadosUltimos();
				GrupoPublicoVO[] gruposCreadosLimpios=null;
				GrupoPublicoVO[] gruposModificadosLimpios=null;
				
				if(creados!=null && creados.length>0){
					if(creados.length>itinerariosTotal){
						gruposCreadosLimpios=new GrupoPublicoVO[new Integer(getPropertyValue("portada.itinerarios.total"))];
						for(int i=0;i<gruposCreadosLimpios.length;i++){
							   gruposCreadosLimpios[i]=creados[i];
						}
					}else{
						gruposCreadosLimpios=new GrupoPublicoVO[creados.length];
						gruposCreadosLimpios=creados;
					}	
				}else{
					gruposCreadosLimpios=new GrupoPublicoVO[0];
				}
				GrupoPublicoConURLVO[] gruposCreados = insertarUrlPaginaPublica(gruposCreadosLimpios);
				
				form.setGruposCreadosAsArray(gruposCreados);
				
				if(modificados!=null && modificados.length>0){
					if(modificados.length>itinerariosTotal){
						gruposModificadosLimpios=new GrupoPublicoVO[new Integer(getPropertyValue("portada.itinerarios.total"))];
				    	   for(int i=0;i<gruposModificadosLimpios.length;i++){
				    		   gruposModificadosLimpios[i]=modificados[i];
				    	   }  
					}else{
						gruposModificadosLimpios=new GrupoPublicoVO[creados.length];
						gruposModificadosLimpios=modificados;
					}
				}else{
					gruposModificadosLimpios=new GrupoPublicoVO[0];
				}
				
				GrupoPublicoConURLVO[] gruposModificados = insertarUrlPaginaPublica(gruposModificadosLimpios);
				form.setGruposModificadosAsArray(gruposModificados);
				
			}catch (Exception e) {
				logger.error("Excepcion inesperada obteniendo itinerarios - ", e);
				throw e;
			}
		} else {
			form.setItinerariosActivo(0);
		}
		
		form.setVerNoticias(configuracionPortal.getNoticias());
		form.setVerEstadisticas(configuracionPortal.getEstadisticas());
		form.setIdRssGaleria(configuracionPortal.getIdRssGaleriaPortada());
		form.setNumOdesGaleria(configuracionPortal.getNumOdesGaleria());
		
		*/
		
		

        //Obtenemos la lista de noticias
		try{		
			NoticiaTraducidaVO[]	arrayNoticias = this.getSrvNoticiasService().listarNoticiasActivasPorIdioma(((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage());
			//new NoticiaTraducidaVO[0];
			if (arrayNoticias!=null && arrayNoticias.length>0) {
				
				int numNoticiasMostradas = Integer.parseInt(ObtieneSrvPropiedad().getValorPropiedad(AgregaProperties.NUM_NOTICIAS_MOSTRADAS_EN_RESUMEN));
				ArrayList<NoticiaTraducidaVO> lista_noticias = new ArrayList<NoticiaTraducidaVO>();
//				NoticiaTraducidaVO[] array_noticias = new NoticiaTraducidaVO[numNoticiasMostradas];
				
				for(int h=0; h<numNoticiasMostradas && h < arrayNoticias.length; h++)
				{
					//sustituimos los retorno de carro por <br> en el resumen
					arrayNoticias[h].setResumen(arrayNoticias[h].getResumen().replaceAll("\n\r", "<br/>"));
					arrayNoticias[h].setResumen(arrayNoticias[h].getResumen().replaceAll("\r\n", "<br/>"));
					arrayNoticias[h].setResumen(arrayNoticias[h].getResumen().replaceAll("\n", "<br/>"));
					arrayNoticias[h].setResumen(arrayNoticias[h].getResumen().replaceAll("\r", "<br/>"));
					
					//sustituimos @,",:,%,+,-,' por su codigo correspondiente en ASCII
					arrayNoticias[h].setTitulo(arrayNoticias[h].getTitulo().replaceAll("@","&#64"));
					arrayNoticias[h].setTitulo(arrayNoticias[h].getTitulo().replaceAll("\\\"","&#34"));
					arrayNoticias[h].setTitulo(arrayNoticias[h].getTitulo().replaceAll(":","&#58"));
					arrayNoticias[h].setTitulo(arrayNoticias[h].getTitulo().replaceAll("%","&#37"));
					arrayNoticias[h].setTitulo(arrayNoticias[h].getTitulo().replaceAll("\\+","&#43"));
					arrayNoticias[h].setTitulo(arrayNoticias[h].getTitulo().replaceAll("-","&#45"));
					arrayNoticias[h].setTitulo(arrayNoticias[h].getTitulo().replaceAll("'","&#39"));
					
					lista_noticias.add(arrayNoticias[h]);
				}
				//Cojo solo las que debo
				//int iTotal = Integer.parseInt(this.getPropertyValue("portada.noticias.total"));
				//form.setNumNoticias(iTotal);
				
				NoticiaCodex[] noticiaCodex=NoticiaCodex.mapToCodexArray(lista_noticias.toArray(new NoticiaTraducidaVO[lista_noticias.size()]));
				form.setNoticiasAsArray(noticiaCodex);
				
				//logger.debug("Noticias de portada obtenidas correctamente("+iTotal+").");
				logger.debug("Noticias de portada obtenidas correctamente. Se mostraran <"+numNoticiasMostradas+"> noticias.");
			} else {	
				NoticiaCodex[] noticiaCodex=NoticiaCodex.mapToCodexArray(arrayNoticias);
				form.setNoticiasAsArray(noticiaCodex);
				logger.debug("No hay ninguna noticia de portada. Proceso concluido correctamente.");
			}
			// Ficheros OPML
	        String idioma = ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
			form.setIdiomaNavegacion(idioma);
			
		} catch (Exception e) {
			logger.error("Excepcion inesperada al obtener noticias - ", e);
			throw e;
		}
		
		
		
		//Obtenemos la lista de descargas
		try {
    		Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
			String idioma = locale.getLanguage();
//			logger.debug("Recuperado idioma :"+idioma);
			DescargaVO descargas[] = getSrvDescargas().obtenerDescargasActivas();
			logger.debug("Recuperadas <"+descargas.length!=null?descargas.length:0+"> descargas.");
			ArrayList<DescargaInfo> listaDescargas = new ArrayList<DescargaInfo>();
			
			if (descargas!=null && descargas.length>0) {	
				DescDescargaVO[] descs = getSrvDescargas().obtenerDescDescargasIdioma(descargas, idioma);
				logger.debug("Recuperadas <" + descs.length + "> descripciones de descargas.");
				int numDescargasMostradas = Integer.parseInt(ObtieneSrvPropiedad().getValorPropiedad(AgregaProperties.NUM_DESCARGAS_MOSTRADAS_EN_RESUMEN));
				
				for (int i = 0; i<numDescargasMostradas && i<descargas.length && i<descs.length; i++) {
					DescargaInfo info = new DescargaInfo();
					info.setTitulo(descs[i]!=null&&descs[i].getTitulo()!=null?descs[i].getTitulo():"");
					info.setDescripcion(descs[i]!=null&&descs[i].getDescripcion()!=null?descs[i].getDescripcion():"");
					info.setIdentificador(descargas[i]!=null&&descargas[i].getIdentificador()!=null?descargas[i].getIdentificador().toString():"");
					info.setPeso(descargas[i]!=null&&descargas[i].getPeso()!=null?descargas[i].getPeso():0L,locale);
					info.setRuta(descargas[i]!=null&&descargas[i].getPath()!=null?request.getServerName()+"/"+descargas[i].getPath():"");
					listaDescargas.add(info);
				}
				form.setDescargas(listaDescargas);
				logger.debug("Lista de descargas de portada obtenidas correctamente. Se mostraran <"+numDescargasMostradas+"> descargas.");
			}
		} catch (Exception e) {
			logger.debug("Error al recuperar descargas - ",e);
			throw new ValidatorException("{listaDescargas.error}");
		}
	}

	
	private String getPropertyValue(String sKey) throws IOException
	{
		InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/spring_visualizadorcontenidos2.properties");
		if (this.pSpringProperties == null)
		{
			pSpringProperties = new java.util.Properties();
			pSpringProperties.load(fIsSpringProperties);
		}
		
		// devolvemos la propiedad
		return pSpringProperties.getProperty(sKey);
	}

	
	public void obtenerCategoria(ActionMapping mapping, ObtenerCategoriaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {

			if (form.getIdCategoria()!=null) {
				form.setIdCategoria(form.getIdCategoria());
				logger.debug("Categoria obtenida correctamente");
			}
		} catch (Exception e) {
			logger.error("Excepcion inesperada obteniendo categoria - ", e);
			throw e;
		}
	}
	
	
	private GrupoPublicoConURLVO[] insertarUrlPaginaPublica(GrupoPublicoVO[] grupos){
		GrupoPublicoConURLVO[] gruposURL=null;
    	try{
    		Collection<GrupoPublicoConURLVO> collections=new ArrayList();
    		for(int i=0;i<grupos.length;i++){
    			GrupoPublicoConURLVO grupoCon=new GrupoPublicoConURLVO();
    			grupoCon.setAdministrador(grupos[i].getAdministrador());
    			grupoCon.setDescripcion(grupos[i].getDescripcion());
    			grupoCon.setFechaCreacion(grupos[i].getFechaCreacion());
    			grupoCon.setFechaModificacion(grupos[i].getFechaModificacion());
    			grupoCon.setId(grupos[i].getId());
    			grupoCon.setImagenGrupo(grupos[i].getImagenGrupo());
    			grupoCon.setNombre(grupos[i].getNombre());
    			grupoCon.setOdeGrupo(grupos[i].getOdeGrupo());
    			grupoCon.setUrlPublico(PerfilPublico.urlGrupoPublico(grupos[i].getNombre()));
    			collections.add(grupoCon);
    		}
    		gruposURL=collections.toArray(new GrupoPublicoConURLVO[0]);
    		
    	}catch(Exception e){
    		logger.error("Error al insertar la url del grupo - ",e);
    	}
    	return gruposURL;
	}

	
	public class GrupoPublicoConURLVO {
	    private java.lang.String nombre;

	    private java.lang.String descripcion;

	    private java.lang.String imagenGrupo;

	    private java.lang.String administrador;

	    private java.util.Calendar fechaModificacion;

	    private java.util.Calendar fechaCreacion;

	    private java.lang.Long id;

	    private es.pode.adminusuarios.negocio.servicios.OdeGrupoVO[] odeGrupo;
	    
	    private java.lang.String urlPublico;

	    public GrupoPublicoConURLVO() {
	    }

		public java.lang.String getNombre() {
			return nombre;
		}

		public void setNombre(java.lang.String nombre) {
			this.nombre = nombre;
		}

		public java.lang.String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(java.lang.String descripcion) {
			this.descripcion = descripcion;
		}

		public java.lang.String getImagenGrupo() {
			return imagenGrupo;
		}

		public void setImagenGrupo(java.lang.String imagenGrupo) {
			this.imagenGrupo = imagenGrupo;
		}

		public java.lang.String getAdministrador() {
			return administrador;
		}

		public void setAdministrador(java.lang.String administrador) {
			this.administrador = administrador;
		}

		public java.util.Calendar getFechaModificacion() {
			return fechaModificacion;
		}

		public void setFechaModificacion(java.util.Calendar fechaModificacion) {
			this.fechaModificacion = fechaModificacion;
		}

		public java.util.Calendar getFechaCreacion() {
			return fechaCreacion;
		}

		public void setFechaCreacion(java.util.Calendar fechaCreacion) {
			this.fechaCreacion = fechaCreacion;
		}

		public java.lang.Long getId() {
			return id;
		}

		public void setId(java.lang.Long id) {
			this.id = id;
		}

		public es.pode.adminusuarios.negocio.servicios.OdeGrupoVO[] getOdeGrupo() {
			return odeGrupo;
		}

		public void setOdeGrupo(
				es.pode.adminusuarios.negocio.servicios.OdeGrupoVO[] odeGrupo) {
			this.odeGrupo = odeGrupo;
		}

		public java.lang.String getUrlPublico() {
			return urlPublico;
		}

		public void setUrlPublico(java.lang.String urlPublico) {
			this.urlPublico = urlPublico;
		}
	}
	
	/**
	 * Este metodo levanta el contexto de spring.
	 * @return
	 */
	 private synchronized org.springframework.context.ApplicationContext getContext()
	    {
	        if (this.beanFactoryPortada == null)
	        {
	            org.springframework.beans.factory.access.BeanFactoryLocator beanFactoryLocator =
	                org.springframework.context.access.ContextSingletonBeanFactoryLocator.getInstance("beanRefFactory_visualizadorcontenidos2.xml");
	            this.beanFactoryPortada = beanFactoryLocator.useBeanFactory("beanRefFactory");
	        }
	        return (org.springframework.context.ApplicationContext)this.beanFactoryPortada.getFactory();
	    }

	 private SrvPropiedadService ObtieneSrvPropiedad() throws Exception {
	 	if (this.prop==null)
	 	{
	 		prop = this.getSrvPropiedadService();
	 	}
	 	return prop;
	 }

}