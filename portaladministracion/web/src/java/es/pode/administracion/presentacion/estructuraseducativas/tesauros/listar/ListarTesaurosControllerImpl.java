/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.administracion.presentacion.estructuraseducativas.tesauros.listar;

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
 * @see es.pode.administracion.presentacion.estructuraseducativas.tesauros.listar.ListarTesaurosController
 */
public class ListarTesaurosControllerImpl extends ListarTesaurosController
{
	private static Logger logger=Logger.getLogger(ListarTesaurosControllerImpl.class);
	
	public void obtenerTesauros(
			ActionMapping mapping, 
			ObtenerTesaurosForm form, 
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
			logger.error("error en ListarTesaurosController.obtenerTesauros: obteniendo idiomas ");
		}
	    
		try{
		Collection<VdexListaVO> tesauros=Arrays.asList(this.getSrvEstructurasEducativasService().listarFicherosTesauros());
		form.setTesauros(tesauros);
		}catch (Exception e) {
			logger.error("error en ListarTesaurosController.obtenerTesauros: obteniendo vdex  ");
		}
	}


	
	
	public void exportarTesauro(
			ActionMapping mapping,
			ExportarTesauroForm form, 
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception 
	{
		final int BUFFER_SIZE = 2048;
		if(form.getNombre()!=null && !form.getNombre().equals(""))
		{
			DataHandler dh =null;
			try{
				dh = this.getSrvEstructurasEducativasService().exportarVdex(form.getNombre(), TipoVdex.fromString(form.getTipo()));
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
					
				}else{
//					logger.error("Fichero  vacio. Abortamos descarga.");
//		    		throw new ValidatorException("{catalogadorAvanzado.exportar.error.fichero}");
				}

			}catch (Exception e) {
				logger.debug("error en la llamada al servicio estructuras educativas " + e.getMessage());
			}
				
		}else{
			logger.debug("error al intentar exportar el tesauro seleccionado");
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
			throw new ValidatorException("{estructuras.tesauros.error.seleccionados.vacios}");
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
       		   vdex= this.getSrvEstructurasEducativasService().listarFicherosTesauro(nombres[1]);
            }
       	}

    	String[] listaEliminar ;
		if (vdex!=null) {
			listaEliminar = filtrarFicherosAEliminar(form
					.getSeleccionadoRowSelectionAsArray(), vdex
					.getListaFicheros());
		} else {
			listaEliminar = new String[0];
		}
		
		if(listaEliminar==null || listaEliminar.length==0)
		{
			throw new ValidatorException("{estructuras.tesauros.error.seleccionados.vacios}");
		}
    	
    	List<ParamEliminarVO> params= new ArrayList<ParamEliminarVO>();
    	
    	for (int i = 0; i < listaEliminar.length; i++) 
    	{
			
			ParamEliminarVO param= new ParamEliminarVO();
			if(listaEliminar[i].endsWith(TipoVdex.TESAURO.toString()))
			{
				param.setNombre( listaEliminar[i].substring(0 , listaEliminar[i].length() - TipoVdex.TESAURO.toString().length()) );
				param.setTipo(TipoVdex.TESAURO);
			}else if(listaEliminar[i].endsWith(TipoVdex.TESAUROS_BACK.toString()))
			{
				param.setNombre( listaEliminar[i].substring(0 , listaEliminar[i].length() - TipoVdex.TESAUROS_BACK.toString().length()) );
				param.setTipo(TipoVdex.TESAUROS_BACK);
			} 
			params.add(param);
		}

		this.getEstructurasSession(request).setVdexEliminar(params);
		
	}


}