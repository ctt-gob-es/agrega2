/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.administracion.presentacion.gestionCargas.listarCarpetasCargas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionMapping;

import es.pode.planificador.negocio.servicios.InformacionCargaVO;
import es.pode.planificador.negocio.servicios.SrvPlanificadorService;



/**
 * @see es.pode.administracion.presentacion.gestionCargas.listarCarpetasCargas.ListarCarpetasCargasController
 */
public class ListarCarpetasCargasControllerImpl extends ListarCarpetasCargasController
{


	private static Logger logger = Logger.getLogger(ListarCarpetasCargasControllerImpl.class);
	final int BUFFER_SIZE = 100000;


    /**
     * @see es.pode.administracion.presentacion.gestionCargas.listarCarpetasCargas.ListarCarpetasCargasController#listarCarpetasCargas(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.gestionCargas.listarCarpetasCargas.ListarCarpetasCargasForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void listarCarpetasCargas(ActionMapping mapping, es.pode.administracion.presentacion.gestionCargas.listarCarpetasCargas.ListarCarpetasCargasForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	 try{
    		 Long id = form.getId();
//    		 Long id = new Long(request.getParameter("id"));
    		 logger.info("Vamos a recoger las carpetas de la tarea "+id);
    		 String nombreTarea =form.getNombreTarea();
//    		 String nombreTarea = request.getParameter("nombreTarea");
    		 String nombreLote=form.getNombreLote();
//    		 String nombreLote = request.getParameter("nombreLote");
    		 String fechaFin=form.getFechaFin();
//    		 String fechaFin= request.getParameter("fechaFin");
//    		 Boolean despublicar = Boolean.parseBoolean(request.getParameter("despublicar"));
    		 
    		 logger.debug("La fecha que recogemos es "+fechaFin);
     		if (id == null){
     			throw new ValidatorException ("{errors.eliminarCargasEjecutadas.idNulo}");
     		}
 	        SrvPlanificadorService servicio=this.getSrvPlanificadorService();
 	        InformacionCargaVO[] carpetas = servicio.obtenerCarpetasDeRegistro(id);
 	        logger.info("Hemos recogido "+carpetas.length+" carpetas");
 	        form.setCarpetasAsArray(carpetas);
 	        form.setFechaFin(fechaFin);
 	        form.setId(id);
 	        form.setNombreLote(nombreLote);
 	        form.setNombreTarea(nombreTarea);
// 	        form.setDespublicar(despublicar);
 	    }catch (Exception e) {
 	        	logger.error("Se ha producido un error al listar las carpetas de la carga carga ejecutada: " + e);
 	        	throw new ValidatorException ("{errors.listarCarpetasCargas} ");
 		}
     }
    
  
	public void recuperarInformes(ActionMapping mapping, RecuperarInformesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		logger.info("Recuperacion del fichero Excel con los id_mec");
		//String idCarga = (String)form.getIdCarga();
		File tempDir = new File(System.getProperty("java.io.tmpdir") + File.separator + "Identificadores.xls");
	 	//Generamos el fichero temporal
		//Comprobamos si el fichero existe
		if(tempDir.exists())
		{
		
			tempDir.delete();
			logger.debug("borramos el fichero");
		}
		Long id = new Long(request.getParameter("id"));
		String pathOde = request.getParameter("pathOde");
		//Obtenemos los id_mecs de esa carga
		logger.debug("pathOde "+pathOde);
		logger.debug("id "+id);
				
		InformacionCargaVO[] carpetas = this.getSrvPlanificadorService().obtenerCarpetasDeRegistro(id);
		Collection<String> identificadores=new ArrayList();
	     boolean encontrado=false;
	       for(int j=0;j<carpetas.length && !encontrado;j++){
    		   String nombrePath=carpetas[j].getPathOde();
    		   if(nombrePath.equals(pathOde)){
    			   encontrado=true;
    			   String[] identificadoresTodos=carpetas[j].getIdentificadores();
    			   for(int i=0;i< identificadoresTodos.length;i++){
    		    	   String identificador=identificadoresTodos[i].toString();
    		    	   identificadores.add(identificador);
    		       }
    		   }
    	   }
		
		logger.debug("Lista de identificadores de la carga "+identificadores.size());
		
		//Generamos el fichero Excel 
		
		try 
		
        { 
			int i=0;
            //Se crea el libro Excel 
            HSSFWorkbook wb = new HSSFWorkbook(); 
            //Se crea una nueva hoja dentro del libro 
            HSSFSheet sheet = wb.createSheet("Identificadores"); 
            Iterator iter = identificadores.iterator();
             while (iter.hasNext())
            {
            	String iden = (String)iter.next();
            	HSSFRow row = sheet.createRow((short)i); 
 	            //Creamos celdas de varios tipos 
 	            row.createCell((short)0).setCellValue(iden);
 	            i++;
            }
            //Escribimos los resultados a un fichero Excel 
            FileOutputStream fileOut = new FileOutputStream(tempDir); 
    	    wb.write(fileOut); 
    	    fileOut.close(); 
    	    OutputStream out = response.getOutputStream();
    	    response.setContentType("application/xls");
    	    response.setHeader("Content-Disposition", "attachment;filename=Identificadores.xls");
    	    byte[] buffer = new byte[BUFFER_SIZE];
			int count;
			FileInputStream in = new FileInputStream(tempDir);
			while((count = in.read(buffer, 0, BUFFER_SIZE))!= -1)
			{
				out.write(buffer, 0, count);
			}
			
			out.flush();
			out.close();
             
        } 

        catch(IOException e) 

        { 
            logger.error("Error al escribir el fichero. ",e); 
        }
        tempDir.deleteOnExit();
		
	}







}