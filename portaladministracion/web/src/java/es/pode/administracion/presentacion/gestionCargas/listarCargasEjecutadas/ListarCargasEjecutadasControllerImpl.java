/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.administracion.presentacion.gestionCargas.listarCargasEjecutadas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionMapping;

import es.pode.planificador.negocio.servicios.OdeCargaVO;
import es.pode.planificador.negocio.servicios.SrvPlanificadorService;
import es.pode.planificador.negocio.servicios.TareaEjecutadaExplotacionVO;



/**
 * @see es.pode.administracion.presentacion.gestionCargas.listarCargasEjecutadas.ListarCargasEjecutadasController
 */
public class ListarCargasEjecutadasControllerImpl extends ListarCargasEjecutadasController
{

	private static Logger logger = Logger.getLogger(ListarCargasEjecutadasControllerImpl.class);
	final int BUFFER_SIZE = 100000;

    /**
     * @see es.pode.administracion.presentacion.gestionCargas.listarCargasEjecutadas.ListarCargasEjecutadasController#listarCargasEjecutadas(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.gestionCargas.listarCargasEjecutadas.ListarCargasEjecutadasForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void listarCargasEjecutadas(ActionMapping mapping, es.pode.administracion.presentacion.gestionCargas.listarCargasEjecutadas.ListarCargasEjecutadasForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {

	    try{
	        SrvPlanificadorService servicio=this.getSrvPlanificadorService();
	        TareaEjecutadaExplotacionVO[] cargas = servicio.obtenerTareasCargaEjecutadas();
	        TareaEjecutadaExplotacionDate[] cargasDate=this.cambiarFormatoTarea(cargas);
	        form.setCargasEjecutadasAsArray(cargasDate);
	    }catch (Exception e) {
	        	logger.error("Se ha producido un error al listar las tareas de carga ejecutadas: " + e);
	        	throw new ValidatorException ("{errors.listarCargasEjecutadas} ");
		}
	 }

    /**
     * @see es.pode.administracion.presentacion.gestionCargas.listarCargasEjecutadas.ListarCargasEjecutadasController#getIds(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.gestionCargas.listarCargasEjecutadas.GetIdsForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public void getIds(ActionMapping mapping, es.pode.administracion.presentacion.gestionCargas.listarCargasEjecutadas.GetIdsForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
	    	List lista = ((ListarCargasEliminarFormImpl) form).getIdCargaRowSelection();
	    	if(logger.isDebugEnabled())
	    		logger.debug("Las tareas que se van a eliminar son ["+lista);
	       if (lista == null){
	    	   throw new ValidatorException ("{errors.eliminarCargasEjecutadas.idNulo}");
	       }
	       form.setIds(lista);
	       logger.info("Se van a eliminar las siguientes tareas: " + lista);
	       
       } catch (ValidatorException e){
    	   throw e;
	    	   
       } catch (Exception e){
    	   
    	   logger.error("Se ha producido un error al intentar recuperar los ids para borrar: " +e);
    	   throw new ValidatorException ("{errors.eliminarCargasEjecutadas.idError}");
    	   
       }
    }

    private TareaEjecutadaExplotacionDate[] cambiarFormatoTarea(TareaEjecutadaExplotacionVO[] tarea) 
    {
    	TareaEjecutadaExplotacionDate[] tareaDate=new TareaEjecutadaExplotacionDate[tarea.length];
    	for(int i=0;i<tarea.length;i++){
    		TareaEjecutadaExplotacionDate explo=new TareaEjecutadaExplotacionDate();
    		explo.setCarpeta(tarea[i].getCarpeta());
    		explo.setDescripcion(tarea[i].getDescripcion());
    		explo.setDescripcionTarea(tarea[i].getDescripcionTarea());
    		explo.setId(tarea[i].getId());
    		explo.setNombreLote(tarea[i].getNombreLote());
    		explo.setPathCarga(tarea[i].getPathCarga());
    		explo.setDespublicado(tarea[i].getDespublicado());
	    	if (tarea[i].getFechaFin() != null){
	    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
	    		String fech=sdf.format(tarea[i].getFechaFin().getTime());
	    		if(logger.isDebugEnabled())logger.debug("Tenemos la siguiente fecha "+fech);
	    		explo.setFechaFin(fech);
	    		
	    	}
	    	tareaDate[i]=explo;
    	}
    	return tareaDate;
    }
 
 public static class TareaEjecutadaExplotacionDate  {
    	
        public java.lang.String getDescripcionTarea() {
		return descripcionTarea;
	}

        private java.lang.String descripcionTarea;
        
        private java.lang.String fechaFin;
        
        private java.lang.Long id;

        private java.lang.String nombreLote;

        private java.lang.String pathCarga;

        private java.lang.String descripcion;     

        private java.lang.Boolean carpeta;
        
        private java.lang.Boolean despublicado;




        public TareaEjecutadaExplotacionDate() {
        }


	public void setDescripcionTarea(java.lang.String descripcionTarea) {
		this.descripcionTarea = descripcionTarea;
	}




	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public java.lang.Long getId() {
		return id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public java.lang.String getNombreLote() {
		return nombreLote;
	}

	public void setNombreLote(java.lang.String nombreLote) {
		this.nombreLote = nombreLote;
	}

	public java.lang.String getPathCarga() {
		return pathCarga;
	}

	public void setPathCarga(java.lang.String pathCarga) {
		this.pathCarga = pathCarga;
	}

	public java.lang.String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(java.lang.String descripcion) {
		this.descripcion = descripcion;
	}

	public java.lang.Boolean getDespublicado() {
		return despublicado;
	}


	public void setDespublicado(java.lang.Boolean despublicado) {
		this.despublicado = despublicado;
	}


	public java.lang.Boolean getCarpeta() {
		return carpeta;
	}

	public void setCarpeta(java.lang.Boolean carpeta) {
		this.carpeta = carpeta;
	}
	
 }

	public String getTextoBusqueda(ActionMapping mapping,
			GetTextoBusquedaForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String texto="";
		
			if(logger.isDebugEnabled()){
				logger.debug("Método para verificar que han insertado un texto de busqueda "+form.getTextoBusqueda());
				logger.debug("Y el tipo de busqueda es "+form.getTipoBusqueda());
			}
			if(form.getTextoBusqueda()==null ||form.getTextoBusqueda().trim().equals("")){
				throw new ValidatorException ("{errors.textoBusqueda.nulo}");
			}
			
			texto=form.getTextoBusqueda().trim();
			if(form.getTipoBusqueda()==null ||form.getTipoBusqueda().trim().equals("")){
				throw new ValidatorException ("{errors.tipoBusqueda.nulo}");
			}
		return texto;
	}

	
	public void recuperarInforme(ActionMapping mapping, RecuperarInformeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		 	logger.info("Recuperacion del fichero Excel con los id_mec");
			File tempDir = new File(System.getProperty("java.io.tmpdir") + File.separator + "Identificadores.xls");
		 	//Generamos el fichero temporal
			//Comprobamos si el fichero existe
			if(tempDir.exists())
			{
				
				tempDir.delete();
				logger.debug("borramos el fichero");
			}
			String idCarga = request.getParameter("idCarga");
			//Obtenemos los id_mecs de esa carga
			logger.debug("idCarga "+idCarga);
					
			OdeCargaVO[] odes = this.getSrvPlanificadorService().obtenerODEsPublicadosEnCarga(new Long(idCarga));
			logger.debug("odes "+Arrays.toString(odes));
			Collection<String> identificadores=new ArrayList();
			for(int i=0;i< odes.length;i++){
		    	   String identificador=odes[i].getId();
		    	    identificadores.add(identificador);
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