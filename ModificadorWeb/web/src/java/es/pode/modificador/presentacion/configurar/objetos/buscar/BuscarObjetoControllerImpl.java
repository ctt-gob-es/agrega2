// license-header java merge-point
package es.pode.modificador.presentacion.configurar.objetos.buscar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.modificador.negocio.servicio.FormularioTaxonomias;
import es.pode.modificador.negocio.servicio.LabelValueVO;
import es.pode.modificador.negocio.servicio.TaxonVO;
import es.pode.modificador.negocio.servicio.TaxonomiaVO;
import es.pode.modificador.presentacion.idiomasBuscador.IdiomasBuscadorSingleton;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;




/**
 * @see es.pode.modificador.presentacion.configurar.objetos.buscar.BuscarObjetoController
 */
public class BuscarObjetoControllerImpl extends BuscarObjetoController
{
	private static String VACIA="";
	private static Logger logger = Logger.getLogger(BuscarObjetoControllerImpl.class);
	public static final String TAX_ASTERISCO = "\\*";
	public final static String MENSAJE_ERROR_NO_SELEC_TAX="configurar.avanzado.controller.exception.noSelecTaxonomia";
	public final static String ASTERISK = "*";
	public static final String TAX_DOLAR = "\\$";
	public static final String BARRA="/";
    /**
     * @see es.pode.modificador.presentacion.configurar.objetos.buscar.BuscarObjetoController#recuperarDatos(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.objetos.buscar.RecuperarDatosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void recuperarDatos(ActionMapping mapping, es.pode.modificador.presentacion.configurar.objetos.buscar.RecuperarDatosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	//Recuperamos valores previos de la sesión
    	BusquedaSession sesion=getBusquedaSession(request);
    	if(sesion==null) sesion=new BusquedaSession();
    	
        form.setDiaDesde(sesion.getDiaDesde()!=null?sesion.getDiaDesde():VACIA);
        form.setDiaHasta(sesion.getDiaHasta()!=null?sesion.getDiaHasta():VACIA);
        form.setMesDesde(sesion.getMesDesde()!=null?sesion.getMesDesde():VACIA);
        form.setMesHasta(sesion.getMesHasta()!=null?sesion.getMesHasta():VACIA);
        form.setAnyoDesde(sesion.getAnyoDesde()!=null?sesion.getAnyoDesde():VACIA);
        form.setAnyoHasta(sesion.getAnyoHasta()!=null?sesion.getAnyoHasta():VACIA);
        form.setTitulo(sesion.getTitulo()!=null?sesion.getTitulo():VACIA);
        form.setAutor(sesion.getAutor()!=null?sesion.getAutor():VACIA);
        form.setIdentificador(sesion.getIdentificador()!=null?sesion.getIdentificador():VACIA);
        form.setIdioma(sesion.getIdioma());
        
        form.setIdiomaBuscadorBackingList(IdiomasBuscadorSingleton.getInstance().obtenerIdiomas(LdapUserDetailsUtils.getIdioma()), "idLocalizacion", "nombre");
        
        form.setIdiomaBackingList(Arrays.asList(I18n.getInstance().obtenerIdiomasBuscablesLocalizados(((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage())), "idLocalizacion", "nombre");
        setBusquedaSession(request, sesion);

        //Cargamos el combos
        try{
	        FormularioTaxonomias combos = this.getSrvHerramientaModificacion().obtenerCombosTaxonomias(LdapUserDetailsUtils.getIdioma());
	        
	        LabelValueVO[] taxonomias=combos.getTaxonomias();
	
			Collection idTax2 = Arrays.asList(taxonomias);
			Collection idTax= new ArrayList();
			Iterator zTax = idTax2.iterator();
			while (zTax.hasNext()) {
				idTax.add(zTax.next());
			}
			form.setTaxonomiasBackingList(idTax, "value", "label");
			if(logger.isDebugEnabled())logger.debug("Cargados las taxonomias en el combo, longitud= "+form.getTaxonomiasBackingList().length);
			
//			se cargan las rutas taxonómicas seleccionadas hasta el momento
			if(sesion.getTaxonesSeleccionados()!=null && sesion.getTaxonesSeleccionados().size()>0){
				form.setTaxonesSeleccionados(sesion.getTaxonesSeleccionados());
				Collection coleccionTaxones = sesion.getTaxonesSeleccionados();
				if(logger.isDebugEnabled())logger.debug("Hemos recogido "+sesion.getTaxonesSeleccionados().size()+" rutas taxonomicas");
				String[] textoTaxones=new String[coleccionTaxones.size()];
				String[] fuentesTaxones=new String[coleccionTaxones.size()];
				int indice=0;
				for (Iterator iter = coleccionTaxones.iterator(); iter.hasNext();) {
					TaxonomiaVO cadaTaxonomia = (TaxonomiaVO) iter.next();
					TaxonVO[] taxones=cadaTaxonomia.getTaxonPath();
					String nombreTax=cadaTaxonomia.getNombreTaxonomia();
					StringBuffer taxon=new StringBuffer("");
					logger.debug("El tamaño de los taxones es "+taxones.length);
					for(int i=0;i<taxones.length;i++){
						String valorTax=taxones[i].getValorTax();
						if(i==taxones.length-1){
							taxon.append(valorTax);
						}
						else
							taxon.append(valorTax).append(BARRA);
					}
					logger.debug("el texto de la ruta "+indice+ "es "+taxon);
					textoTaxones[indice]=taxon.toString();
					fuentesTaxones[indice]=nombreTax;
					indice++;
					
				}
				form.setTaxSelec(textoTaxones);
				form.setFuentes(fuentesTaxones);
				

			}
			
//     		if(form.getEnlaceTaxSelec()!=null && !form.getEnlaceTaxSelec().equals("")){
//	     		try{
//	     			logger.debug("El enlaceTaxSelec que nos llega es "+form.getEnlaceTaxSelec());
//	     			String[] rutasTax = form.getEnlaceTaxSelec().split(this.TAX_ASTERISCO);
//	     			List <String>rutasTaxSinRepetir = new ArrayList<String>();
//	     			for(int i=0 ; i<rutasTax.length ;i++){
//	     				if(!rutasTaxSinRepetir.contains(rutasTax[i])){
//	     					rutasTaxSinRepetir.add(rutasTax[i]);
//	     				}
//	     			}
//	     			String enlaceTaxSelec = rutasTaxSinRepetir.get(0);
//	     			for(int i=1 ; i<rutasTaxSinRepetir.size() ;i++){
//	     				enlaceTaxSelec+= this.ASTERISK + rutasTaxSinRepetir.get(i);
//	     			}
//	     			form.setEnlaceTaxSelec(enlaceTaxSelec);
//	     			logger.debug("El texto de los taxones que le pasamos es "+enlaceTaxSelec);
//	     		}
//	     		catch(Exception e){
//	     			logger.error("BuscarAvanzadoControllerImpl - cargaFormularioBusquedaAvanzada: Error al obtener las rutas taxonómicas seleccionadas",e);
//	     			form.setEnlaceTaxSelec("");
//	     		}
//	     	}
        }catch(Exception e){
        	logger.error("Error al cargar los combos "+e);
        }
        
     }

    /**
     * @see es.pode.modificador.presentacion.configurar.objetos.buscar.BuscarObjetoController#selectAction(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.objetos.buscar.SelectActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String selectAction(ActionMapping mapping, es.pode.modificador.presentacion.configurar.objetos.buscar.SelectActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",
				locale);
		
		String volver = "Volver";
		String buscar = i18n.getString("buscarObjeto.Buscar");
		String escogerTaxonomia=i18n.getString("buscarObjeto.taxonomia.aniadir");
		String eliminarTaxonomia=i18n.getString("buscarObjeto.taxonomia.eliminar");
		String action = form.getAction();
		logger.debug("Hemos entrado en selectAction con action "+action);
		if (action == null) {
			return volver;
		}if(action.equals(escogerTaxonomia)){
			if(form.getTaxonomias()!=null && !form.getTaxonomias().equals("")){
				logger.debug("Hemos escogido añadir taxonomia");
				return "EscogerTaxonomia";
			}else{
				throw new ValidatorException(
				"{buscarObjetos.msgErrorComboTaxonomias}");
			}
		}else if(action.equals(eliminarTaxonomia)){
			if(form.getTaxSelec()!=null && form.getTaxSelec().length>0){
				logger.debug("Hemos escogido eliminar taxonomia");
				return "EliminarTaxonomia";
			}else{
				throw new ValidatorException(
				"{buscarObjetos.msgErrorEliminarTaxonomias}");
			}
		}
		if (action.equals(buscar)) {

			// Guarda datos búsqueda en sesión
			BusquedaSession sesion = getBusquedaSession(request);
			boolean alMenosUnParametro = false;
			boolean parteFecha = false;
			
			if (vacia(form.getIdioma())) {
				throw new ValidatorException("{buscarObjeto.msgErrorIdioma}");
			}
			if (!vacia(form.getAutor())) {

				alMenosUnParametro = true;
			}
			if (!vacia(form.getIdentificador())) {

				alMenosUnParametro = true;
			}
			if (!vacia(form.getTitulo())) {

				alMenosUnParametro = true;
			}
			if (fechaValida(form.getAnyoDesde())/*&&fechaValida(form.getDiaDesde())&&fechaValida(form.getMesDesde())*/) {

				alMenosUnParametro = true;
				parteFecha = true;
			}
			if (fechaValida(form.getAnyoHasta())/*&&fechaValida(form.getDiaHasta())&&fechaValida(form.getMesHasta())*/) {

				alMenosUnParametro = true;
				parteFecha = true;
			}
			if (fechaValida(form.getDiaDesde())) {

				alMenosUnParametro = true;
				parteFecha = true;
			}
			if (fechaValida(form.getDiaHasta())) {

				alMenosUnParametro = true;
				parteFecha = true;
			}
			if (fechaValida(form.getMesDesde())) {

				alMenosUnParametro = true;
				parteFecha = true;
			}
			if (fechaValida(form.getMesHasta())) {

				alMenosUnParametro = true;
				parteFecha = true;
			}
			if(sesion.getTaxonesSeleccionados()!= null && sesion.getTaxonesSeleccionados().size()>0){
				alMenosUnParametro = true;
			}

			//Si al menos uno de los campos de una fecha se ha introducido, se da error si falta otro
			
			if(parteFecha) {
				if (!fechaValida(form.getDiaDesde())) {
					throw new ValidatorException("{buscarObjeto.msgErrorDiaDesde}");
				} else if (!fechaValida(form.getMesDesde())) {
					throw new ValidatorException("{buscarObjeto.msgErrorMesDesde}");
				} else if (!fechaValida(form.getAnyoDesde())) {
					throw new ValidatorException("{buscarObjeto.msgErrorAnyoDesde}");
				} else if (!fechaValida(form.getDiaHasta())) {
					throw new ValidatorException("{buscarObjeto.msgErrorDiaHasta}");
				} else if (!fechaValida(form.getMesHasta())) {
					throw new ValidatorException("{buscarObjeto.msgErrorMesHasta}");
				} else if (!fechaValida(form.getAnyoHasta())) {
					throw new ValidatorException("{buscarObjeto.msgErrorAnyoHasta}");
				}
			}
			
			if (!alMenosUnParametro) {
				throw new ValidatorException("{buscarObjeto.exception}");
			}

			//Vuelco el form a la sesion
			sesion.setAutor(form.getAutor());
			sesion.setIdentificador(form.getIdentificador());
			sesion.setTitulo(form.getTitulo());
			sesion.setIdioma(form.getIdioma());
			
			if (fechaValida(form.getDiaDesde())&&fechaValida(form.getMesDesde())&&fechaValida(form.getAnyoDesde())
					&&fechaValida(form.getDiaHasta())&&fechaValida(form.getMesHasta())&&fechaValida(form.getAnyoHasta())) {
				//Comprobaciones de la fecha
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				format.setLenient(false);
				
				String guion = "-";
				int dayFrom, dayTo, yearFrom, yearTo, monthFrom, monthTo;
				try {
					dayFrom = Integer.valueOf(form.getDiaDesde()).intValue();
					dayTo = Integer.valueOf(form.getDiaHasta()).intValue();
					yearFrom = Integer.valueOf(form.getAnyoDesde()).intValue();
					yearTo = Integer.valueOf(form.getAnyoHasta()).intValue();
					monthFrom = Integer.valueOf(form.getMesDesde()).intValue();
					monthTo = Integer.valueOf(form.getMesHasta()).intValue();
				} catch (Exception e) {
					throw new ValidatorException(
							"{buscarObjetos.msgErrorFormato}");
				}
				StringBuffer fechaFromStr = new StringBuffer();
				fechaFromStr.append(dayFrom).append(guion).append(monthFrom)
						.append(guion).append(yearFrom);
				
				Date fechaFrom;
				Date fechaTo;
				
				try {
					fechaFrom = format.parse(fechaFromStr.toString());
					StringBuffer fechaToStr = new StringBuffer();
					fechaToStr.append(dayTo).append(guion).append(monthTo).append(
							guion).append(yearTo);
					
					fechaTo = format.parse(fechaToStr.toString());
					if (fechaTo.before(fechaFrom)) {
						throw new ValidatorException(
								"{buscarObjetos.msgErrorFormato}");
					}
				} catch (Exception e) {
					throw new ValidatorException("{buscarObjetos.msgErrorFormato}");
				}
				//Saco ahora campos de fecha a sesion
				String[] partes = format.format(fechaFrom).split("-");
				sesion.setDiaDesde(partes[0]);
				sesion.setMesDesde(partes[1]);
				sesion.setAnyoDesde(partes[2]);
				partes = format.format(fechaTo).split("-");
				sesion.setDiaHasta(partes[0]);
				sesion.setMesHasta(partes[1]);
				sesion.setAnyoHasta(partes[2]);
			} else {
				//Actualizamos los campos de fecha en sesion aunque no sean buenos
				sesion.setDiaDesde(form.getDiaDesde());
				sesion.setMesDesde(form.getMesDesde());
				sesion.setAnyoDesde(form.getAnyoDesde());
				sesion.setDiaHasta(form.getDiaHasta());
				sesion.setMesHasta(form.getMesHasta());
				sesion.setAnyoHasta(form.getAnyoHasta());
			}
			sesion.setResultados(null);
			return "Buscar";
		}

		return volver;
	}

	public void borrarSesion(ActionMapping mapping, BorrarSesionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	removeBusquedaSession(request);
	}
	
	/**
	 * Comprueba si la cadena dada tiene algo útil.
	 * @param cadena
	 * @return
	 */
	private boolean vacia(String cadena) {
		return cadena==null||cadena.trim().equals(VACIA);
	}
	
	private boolean esNumero(String cadena) {
		try {
			Integer.parseInt(cadena);
		} catch (Exception e) {
			return false;
		}		
		return true;
	}
	
	private boolean fechaValida(String cadena) {
		return !vacia(cadena)&&esNumero(cadena);
	}

	@Override
	public void guardarBusquedaEnSesion(ActionMapping mapping, GuardarBusquedaEnSesionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			logger.debug("BuscarObjetoControllerImpl - guardarBusquedaEnSesion: Realizando insercion de datos de busqueda en sesion para retorno a este diagrama.");
			BusquedaSession sesion = new BusquedaSession();
			
			if (sesion == null) this.setBusquedaSession(request, new BusquedaSession());
//			Propiedades del contenido
		
			sesion.setIdioma(form.getIdioma());		
			sesion.setAutor(form.getAutor());
			sesion.setAnyoDesde(form.getAnyoDesde());
			sesion.setAnyoHasta(form.getAnyoHasta());
			sesion.setDiaDesde(form.getDiaDesde());
			sesion.setDiaHasta(form.getDiaHasta());
			sesion.setIdentificador(form.getIdentificador());
			sesion.setMesDesde(form.getMesDesde());
			sesion.setMesHasta(form.getMesHasta());
			sesion.setTitulo(form.getTitulo());
			sesion.setEnlaceTaxSelec(form.getEnlaceTaxSelec());
			sesion.setResultados(null);
			if(form.getTaxonesSeleccionados()!=null)
				sesion.setTaxonesSeleccionados(form.getTaxonesSeleccionados());
			else{
				sesion.setTaxonesSeleccionados(new ArrayList<TaxonomiaVO>());
			}
			logger.debug("BuscarObjetoControllerImpl - guardarBusquedaEnSesion FIN");
		}catch (Exception ex){
    		logger.error("BuscarObjetoControllerImpl - guardarBusquedaEnSesion ERROR: Error al guardar la búsqueda en sesion",ex);
		}
		
	}

	@Override
	public String selecTipo(ActionMapping mapping, SelecTipoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//Tipo de taxonomia o tesauro
		String tipoAniadir = form.getTaxonomias();
    	if(logger.isDebugEnabled())logger.debug("el tipo para añadir es "+tipoAniadir);
    	BusquedaSession sesion=getBusquedaSession(request);
		String result="";
		if(tipoAniadir!=null && !tipoAniadir.equals("")){

				if (tipoAniadir.startsWith("TES_") )
				{	
					String tipoA=tipoAniadir.substring(4, tipoAniadir.length());
					if(logger.isDebugEnabled())logger.debug("El nombre del tesauro es "+tipoA);
					sesion.setNombreTaxonomia(tipoA);
					result="Tesauro";
				}else{
					if(logger.isDebugEnabled())logger.debug("El nombre de la taxonomia es "+tipoAniadir);
					sesion.setNombreTaxonomia(tipoAniadir);
					result="Taxonomia";
				}
 

				
		}else{
			throw new ValidatorException(
			"{buscarObjetos.msgErrorComboTaxonomias}");
		}
        return result;
	}

	@Override
	public void eliminarTaxonomia(ActionMapping mapping, EliminarTaxonomiaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("BuscarAvanzadoControllerImpl - eliminarTaxonomia con el numero de taxonomias a borrar es "+ form.getTaxSelec().length);
		if(form.getTaxSelec()!=null && form.getTaxSelec().length>0){
			String[] paraEliminar=form.getTaxSelec();
			BusquedaSession sesion=getBusquedaSession(request);
			ArrayList<TaxonomiaVO> rutas= (ArrayList<TaxonomiaVO>)sesion.getTaxonesSeleccionados();
//			ArrayList arrayRutas=new ArrayList<TaxonomiaVO>(rutas);
			List<TaxonomiaVO> array=new ArrayList<TaxonomiaVO>();
			
			if(rutas.size()> paraEliminar.length){
//				Collection<TaxonomiaVO> vuelta=new ArrayList(rutas.size()-paraEliminar.length);
				for(int i=0;i< paraEliminar.length;i++){
					String eliminar=paraEliminar[i];
					if(eliminar.equals("")){//en la posicion 0 no le pone el 0, lo deja vacio
						eliminar="0";
					}
					int posicion=Integer.parseInt(eliminar);
					logger.debug("Las posiciones que tenemos que eliminar son "+posicion);
					TaxonomiaVO taxonomias=rutas.get(posicion);
					array.add(taxonomias);
//					TaxonomiaVO ok = rutas.remove(posicion);
//					for(int j=i;j<paraEliminar.length;j++){
//						String menos=paraEliminar[j];
//						int posi=Integer.parseInt(menos);
//						paraEliminar[j]=""+(posi-1);
//					}
				}
				boolean ok=rutas.removeAll(array);
				
				logger.debug("El collection que le metemos tiene "+rutas.size()+" elementos y le hemos borrado "+array.size()+ "seguro? "+ok);
//				vuelta=rutas;
				sesion.setTaxonesSeleccionados(rutas);
				logger.debug("El collection que le metemos tiene "+rutas.size()+" elementos");
			}else{
				sesion.setTaxonesSeleccionados(null);
			}
		}else{
			throw new ValidatorException(
			"{buscarObjetos.msgErrorEliminarTaxonomias}");
		}
    	
    	logger.debug("BuscarAvanzadoControllerImpl - eliminarTaxonomia FIN");
		
	}
	
	
	
}
