// license-header java merge-point
package es.pode.administracion.presentacion.estructuraseducativas.taxonomias.listar;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.fuentestaxonomicas.negocio.servicio.ParamEliminarVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TipoVdex;
import es.pode.fuentestaxonomicas.negocio.servicio.VdexListaVO;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.i18n.LocalizacionIdiomaVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.administracion.presentacion.estructuraseducativas.taxonomias.listar.ListarTaxonomiasController
 */
public class ListarTaxonomiasControllerImpl extends ListarTaxonomiasController
{
	private static Logger logger= Logger.getLogger(ListarTaxonomiasControllerImpl.class);


	public final void obtenerTaxonomias(
    		ActionMapping mapping, 
    		ObtenerTaxonomiasForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    throws Exception
    {
		form.setIdiomaBuscador("02");
		try{
			String idiomaSelected = LdapUserDetailsUtils.getIdioma();
			I18n i18n = I18n.getInstance();
			LocalizacionIdiomaVO[] localizadorIdioma =i18n.obtenerIdiomasPlataformaLocalizados(idiomaSelected);
			form.setIdiomaBuscadorBackingList(Arrays.asList(localizadorIdioma), "idLocalizacion", "nombre");
		}catch (Exception e) {
			logger.error("error en ListarTaxonomiasController.obtenerTaxonomias: obteniendo idiomas ");
		}
		
		try{
			Collection<VdexListaVO> vdex=Arrays.asList(this.getSrvEstructurasEducativasService().listarFicherosTaxonomias());
			form.setTaxonomias(vdex);
		}catch (Exception e) {
			logger.error("error en ListarTaxonomiasController.obtenerTaxonomias: obteniendo vdex  ");
		}

     }



	private String[] filtrarFicherosAEliminar(
			String[] listaOriginal, 
			String[] listaFiltro)
	{
		List<String> resultado= new ArrayList<String>();
		if(listaOriginal!=null && listaOriginal.length>0 )
		for (int i = 0; i < listaOriginal.length; i++) {
			boolean encontrado= false;
			for (int j = 0; j < listaFiltro.length && !encontrado; j++) {
				if(listaOriginal[i].startsWith(listaFiltro[j]))
				{
					encontrado=true;
					resultado.add(listaOriginal[i]);
				}
			}
		}
		return resultado.toArray(new String[0]);
	}


	public void exportar(
			ActionMapping mapping, 
			ExportarForm form,
			HttpServletRequest request, 
			HttpServletResponse response)
	throws Exception 
	{
		final int BUFFER_SIZE = 2048;
		if(form.getNombre()!=null && !form.getNombre().equals("")
		   && form.getTipo()!=null && !form.getTipo().equals(""))
		{
			DataHandler dh =null;
			
			try{
				if(TipoVdex.TAXONOMIA.toString().equals(form.getTipo()))
					dh = this.getSrvEstructurasEducativasService().exportarVdex(form.getNombre(), TipoVdex.TAXONOMIA);
				else
					dh = this.getSrvEstructurasEducativasService().exportarVdex(form.getNombre(), TipoVdex.ARBOL);
				if(dh!=null)
				{
			    	response.setContentType("text/xml;charset=utf-8");
			    	response.setHeader("Content-Disposition", "attachment;filename="+ form.getNombre());
			    	OutputStream out = response.getOutputStream();
			    	InputStream in = dh.getInputStream();
			    	logger.debug("Descargando metadata.xml");
					byte[] buffer = new byte[BUFFER_SIZE];
					int count;
					while((count = in.read(buffer, 0, BUFFER_SIZE))!= -1)
					{
						out.write(buffer, 0, count);
					}
					out.flush();
				}

			}catch (Exception e) {
				logger.debug("error en la llamada al servicio estructuras educativas " + e.getMessage());
			}
				
		}else{
			logger.debug("error al intentar exportar el tesauros o taxonomias ");
		}

		
	}

	



	@Override
	public void guardarEnSesion(
			ActionMapping mapping,
			GuardarEnSesionForm form, 
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception 
	{
		if(form.getSeleccionadoRowSelection()==null || form.getSeleccionadoRowSelection().size()==0)
		{
			throw new ValidatorException("{estructuras.taxonomias.error.seleccionados.vacios}");
		}

		VdexListaVO vdex =null;
		boolean encontrado=false;
		for (Enumeration names = request.getParameterNames(); names.hasMoreElements() && !encontrado;)
		{
			String nameParam = String.valueOf(names.nextElement());
			if(nameParam.startsWith("action"))
			{
        	   logger.debug("action :" + nameParam);
        	   encontrado=true;
        	   String[] nombres = nameParam.split("--");
        	   form.setIdentificadorVdex(nombres[1]);
        		   vdex= this.getSrvEstructurasEducativasService().listarFicherosTaxonomia(nombres[1]);
            }
       	}

    	String[] listaEliminar=filtrarFicherosAEliminar( form.getSeleccionadoRowSelectionAsArray(), vdex.getListaFicheros()) ;
		
		if(listaEliminar==null || listaEliminar.length==0)
		{
			throw new ValidatorException("{estructuras.taxonomias.error.seleccionados.vacios}");
		}
    	
    	List<ParamEliminarVO> params= new ArrayList<ParamEliminarVO>();
    	
    	for (int i = 0; i < listaEliminar.length; i++) 
    	{
			
			ParamEliminarVO param= new ParamEliminarVO();
			if(listaEliminar[i].endsWith(TipoVdex.ARBOL.toString()))
			{
				param.setNombre( listaEliminar[i].substring(0 , listaEliminar[i].length() - TipoVdex.ARBOL.toString().length()) );
				param.setTipo(TipoVdex.ARBOL);
			}else if(listaEliminar[i].endsWith(TipoVdex.TAXONOMIA.toString()))
			{
				param.setNombre( listaEliminar[i].substring(0 , listaEliminar[i].length() - TipoVdex.TAXONOMIA.toString().length()) );
				param.setTipo(TipoVdex.TAXONOMIA);
			}
			
			params.add(param);
		}
		this.getEstructurasSession(request).setVdexEliminar(params);

		
	}


}