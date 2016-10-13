// license-header java merge-point
package es.pode.visualizador.presentacion.noticias;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.visualizador.presentacion.noticia.NoticiaCodex;

 
/**
 * @see es.pode.visualizador.presentacion.noticias.NoticiasController
 */
public class NoticiasControllerImpl extends NoticiasController
{

	private java.util.Properties pSpringProperties = null;
	public final static String Puntos="...";
    /**
     * @see es.pode.visualizador.presentacion.noticias.NoticiasController#obtenerNoticias(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.noticias.ObtenerNoticiasForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerNoticias(ActionMapping mapping, es.pode.visualizador.presentacion.noticias.ObtenerNoticiasForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		Long idCategoria = form.getIdCategoria(); 
	 	String idioma = ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
	    
    	Collection noticias = Arrays.asList(this.getSrvNoticiasService().listarNoticiasActivasPorIdioma(((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage()));
       	NoticiaTraducidaVO[] not=null;
    	if (noticias!= null)
    	{
    		if(idCategoria==null)
    		{
    			not= (NoticiaTraducidaVO[]) noticias.toArray(); 
    		}
    		else
        	{
    			Logger.getLogger(this.getClass()).debug("Buscando por categoria = " + idCategoria);
        		not=this.getSrvNoticiasService().obtenerNoticiasActivasPorIdiomayCategoria(idioma, idCategoria);
        		if (not[0]!=null)
        			form.setCategoria(not[0].getCategoria());
        		Logger.getLogger(this.getClass()).debug("Noticias recuperadas : " + not.length);
        	}
    		
    		 
    		for(int i=0;i < not.length; i++)
    		{
    			//sustituimos los retornos de carro por <br> para que se muestre bien el resumen
    			not[i].setResumen(not[i].getResumen().replaceAll("\n\r", "<br/>"));
    			not[i].setResumen(not[i].getResumen().replaceAll("\r\n", "<br/>"));
    			not[i].setResumen(not[i].getResumen().replaceAll("\r", "<br/>"));
    			
    			//sustituimos los retornos de carro por <br> para que se muestre bien el cuerpo
    			not[i].setCuerpo(not[i].getCuerpo().replaceAll("\n\r", "<br/>"));
    			not[i].setCuerpo(not[i].getCuerpo().replaceAll("\r\n", "<br/>"));
    			not[i].setCuerpo(not[i].getCuerpo().replaceAll("\n", "<br/>"));
    			not[i].setCuerpo(not[i].getCuerpo().replaceAll("\r", "<br/>"));
    			
    			//sustituimos @,",:,%,+,-,' por su codigo correspondiente en ASCII
    			not[i].setTitulo(not[i].getTitulo().replaceAll("@","&#64"));
    			not[i].setTitulo(not[i].getTitulo().replaceAll("\\\"","&#34"));
    			not[i].setTitulo(not[i].getTitulo().replaceAll(":","&#58"));
    			not[i].setTitulo(not[i].getTitulo().replaceAll("%","&#37"));
    			not[i].setTitulo(not[i].getTitulo().replaceAll("\\+","&#43"));
    			not[i].setTitulo(not[i].getTitulo().replaceAll("-","&#45"));
    			not[i].setTitulo(not[i].getTitulo().replaceAll("'","&#39"));
    		}
    	}
    	NoticiaCodex[] noticiaCodex=NoticiaCodex.mapToCodexArray(not);
    	form.setNoticiasAsArray(noticiaCodex);
    	form.setIdCategoria(idCategoria);
    	form.setIdiomaNavegacion(idioma);
    	
    }
 
    public void obtenerCategoria(ActionMapping mapping, ObtenerCategoriaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
    {
		if (form.getIdCategoria()!=null) {
			form.setIdCategoria(form.getIdCategoria());
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

}