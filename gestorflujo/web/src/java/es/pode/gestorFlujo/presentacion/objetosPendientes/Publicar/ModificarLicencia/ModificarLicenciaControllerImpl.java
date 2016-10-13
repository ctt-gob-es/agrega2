/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.gestorFlujo.presentacion.objetosPendientes.Publicar.ModificarLicencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.buscar.negocio.administrar.servicio.NodoVO;
import es.pode.buscar.negocio.administrar.servicio.SrvNodoService;
import es.pode.fuentestaxonomicas.negocio.servicio.TerminoVO;
import es.pode.gestorFlujo.presentacion.objetosPendientes.Publicar.PublicarSession;
import es.pode.localizador.negocio.servicios.LocalizadorVO;
import es.pode.localizador.negocio.servicios.SrvLocalizadorService;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.menu.MenuController;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.gestorFlujo.presentacion.objetosPendientes.Publicar.ModificarLicencia.ModificarLicenciaController
 */
public class ModificarLicenciaControllerImpl extends ModificarLicenciaController
{

	protected static final String FILE_NAME_PROPERTIES = "/gestorFlujo.properties";

	private static Properties props = null;

	private Logger logger = Logger.getLogger(ModificarLicenciaControllerImpl.class);

	private final String FILE_SEPARATOR="/";
	
	private final String LICENCIA_NAME="licencia.txt";
	private final String ERROR_DESCRIPCION="gestorFlujo.error.descripcion.obligatoria";



    /**
     * @see es.pode.gestorFlujo.presentacion.objetosPendientes.Publicar.ModificarLicencia.ModificarLicenciaController#cargarFormularioLicencias(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPendientes.Publicar.ModificarLicencia.CargarFormularioLicenciasForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void cargarFormularioLicencias(ActionMapping mapping, es.pode.gestorFlujo.presentacion.objetosPendientes.Publicar.ModificarLicencia.CargarFormularioLicenciasForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	boolean disponible = true;
    	java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
    	ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		try {
			if(logger.isDebugEnabled())
				logger.debug("Cargando formulario licencias del ode "+form.getIdODE()+"en la sesion "+this.getPublicarSession(request).getIdODE() );
			// Los idiomas del buscador
			// el combo lo mostramos en el idioma de navegación, pero en la
			// consolidación habrá que guardarlo en inglés
			String[] menCombos = new String[] { getPropertyValue("combo.tipoLicencias") };
			TerminoVO[] licencias = this.getSrvVocabulariosControladosService().obtenerCombos(menCombos,
					LdapUserDetailsUtils.getIdioma())[0].getTerminos();
			licencias = ordena(licencias, LdapUserDetailsUtils.getIdioma());
			if(logger.isDebugEnabled())
			logger.debug("vocabularios obtenidos, idioma: " + request.getLocale().getLanguage());
			form.setLicenciasAsArray(licencias);
			// la licencia seleccionada
			form.setIdentificadorLicencia(this.getPublicarSession(request).getIdentificadorLicencia());
			
			if ((this.getPublicarSession(request).getUniversal().compareTo(getPropertyValue("licencia.acceso.universal"))) == 0)
				form.setUniversal("1");
			else
				form.setUniversal("0");
			logger.debug("Es universal al iniciar? "+ form.getUniversal());
			if(this.getPublicarSession(request).getUniversalBackup()==null){
				this.getPublicarSession(request).setUniversalBackup(form.getUniversal());
			}
			logger.debug("Vamos a escribir el texto de la licencia"+this.getPublicarSession(request).getIdentificadorLicencia()+" siendo el form "+form.getTextoLicencia()+ " y el de sesion "+ this.getPublicarSession(request).getTextoLicencia());
			String texto2 ="";
			if(this.getPublicarSession(request).getIdentificadorLicencia().equals("6.2.5")&& (this.getPublicarSession(request).getTextoLicencia()==null )){
				if(form.getTextoLicencia()==null){
					logger.debug("Es de licencia libre y vemos si trae texto o no, puesto que es la primera vez que entramos");
					SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
					 LocalizadorVO localizar=localizadorService.consultaLocalizador(this.getPublicarSession(request).getIdODE());
					 String pathLocalizador = localizar.getPath();
					 logger.debug("El localizador del ode es "+ pathLocalizador);
					 String localizador=pathLocalizador+"/licencia.txt";
					 File fileLicencia=new File(localizador);
					 if(fileLicencia.exists()){
						 String texto="";
						try{
							BufferedReader br = new BufferedReader(new FileReader(localizador));
							
							 while ((texto=br.readLine()) != null) {
								 if(texto2!=null && !texto2.equals("")){
									 String lineSep = System.getProperty("line.separator");//Asi obtiene el salto de linea dependiendo la plataforma.
	//								 String lineSep="\n";
									 texto2 = texto2+texto;
									 texto2.replaceAll(lineSep, "");
									 logger.debug("El texto de esta línea es "+texto);
								 }else{
									 texto2=texto;
								 }
							 }
					
					
					 		br.close();
						}catch(IOException ex){
							logger.error("Error  obteniendo el texto de la licencia que traía el ode" +ex);
						}
					 }else{
						 texto2=i18n.getString("gestorFlujo.importarURL.descripcion.tooltip");//Recoger de properties
						 logger.debug("El texto de la licencia libre que traía el ode es "+texto2);
						 this.getPublicarSession(request).setTextoLicencia(null);
					 }
				}else{
					logger.debug("Tenemos que obtener el texto genérico para cuando entremos por primera vez");
					if(this.getPublicarSession(request).getIdentificadorLicencia().equals("6.2.5")){
						texto2=i18n.getString("gestorFlujo.importarURL.descripcion.tooltip");//Recoger de properties
					}else{
						texto2=obtenerTextoLicencia(this.getPublicarSession(request).getIdentificadorLicencia());
						logger.debug("El texto de la licencia que recogemos del uploads"+texto2);
					}
					 
					 this.getPublicarSession(request).setTextoLicencia(null);
				}
			}else if(this.getPublicarSession(request).getIdentificadorLicencia().equals("6.2.5")&& ((this.getPublicarSession(request).getTextoLicencia()!=null &&  this.getPublicarSession(request).getTextoLicencia().length()>0) )){
				logger.debug("Trae el texto en el ode "+this.getPublicarSession(request).getTextoLicencia());
				texto2=this.getPublicarSession(request).getTextoLicencia();
				logger.debug("Recojo la licencia y lo borro de sesion");
				this.getPublicarSession(request).setTextoLicencia(null);
			}
			
			else{
				if(this.getPublicarSession(request).getIdentificadorLicencia().equals("6.2.5")){
					texto2=i18n.getString("gestorFlujo.importarURL.descripcion.tooltip");//Recoger de properties
				}else{
					texto2=obtenerTextoLicencia(this.getPublicarSession(request).getIdentificadorLicencia());
				}
				this.getPublicarSession(request).setTextoLicencia(null);
			}
			
			form.setTextoLicencia(texto2.trim());
			if(this.getPublicarSession(request).getIdentificadorLicenciaBackup()==null){		
				this.getPublicarSession(request).setIdentificadorLicenciaBackup(form.getIdentificadorLicencia());
			}
			
			if(this.getPublicarSession(request).getTextoLicenciaBackup()==null){
				this.getPublicarSession(request).setTextoLicenciaBackup(form.getTextoLicencia());
			}
			
			SrvNodoService nodos = this.getSrvNodoService();
			// Cargamos la lista de NODOS con los nodos dados de alta.
			// Hay que tener en cuenta los nodos que ya tenga el propio ODE,
			// para que aparezcan seleccionados. No va a aparecer el nodo local
			if (nodos != null) {
				// Ahora llamamos al mapero que me dice cuantos de los nodos de
				// la lista estan licenciados
				if(logger.isDebugEnabled())
				logger.debug("Vamos a listar los nodos con las licencias impresas");
				form.setComunidadesAsArray(mapeaArrayNodoVOToNodoVOODE(nodos.listarNodos(), this.getPublicarSession(request).getComunidades()));
				
				logger.debug("Las comunidades que recogemos al mapear son de longitud "+form.getComunidadesAsArray().length);
				for(int i =0;i<form.getComunidadesAsArray().length;i++){
					NodoVOODE[] nodosArray=(NodoVOODE[])form.getComunidadesAsArray();
					logger.debug("El nombre es "+nodosArray[i].getIdNodo()+" y es licenciado "+nodosArray[i].isEstaLicenciado());
				}
				if(this.getPublicarSession(request).getComunidadesBackup()==null){
					for (int i=0;i<form.getComunidades().size();i++){
						logger.debug("Las comunidades que tenemos al empezar son"+((NodoVOODE[])form.getComunidadesAsArray())[i].getIdNodo()+" los metemos en nuestra variable backup");
					}
					Collection coleccionComunidades=new ArrayList();
					coleccionComunidades.add(form.getComunidadesAsArray());
					this.getPublicarSession(request).setComunidadesBackup(coleccionComunidades);
				}
		
			} else {
				if(logger.isDebugEnabled())
				logger.error("No hay servicio de nodos cargando licencias");
				disponible = false;
			}

		} catch (Exception ex) {
			logger.error("Error cargando licencias. ", ex);
			if (disponible)
				throw new ValidatorException("{gestorFlujo.error.inesperado}");
			else
				throw new ValidatorException("{gestorFlujo.agregarLicencia.noDisponible}");
		}
     }







    /**
     * @see es.pode.gestorFlujo.presentacion.objetosPendientes.Publicar.ModificarLicencia.ModificarLicenciaController#consolidaLicencias(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPendientes.Publicar.ModificarLicencia.ConsolidaLicenciasForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void consolidaLicencias(ActionMapping mapping, es.pode.gestorFlujo.presentacion.objetosPendientes.Publicar.ModificarLicencia.ConsolidaLicenciasForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
			PublicarSession publiSes = this.getPublicarSession(request);
			//Nos olvidamos del nodo local, al cargar la pagina de publicar se lo meteremos siempre
//				if (form.getUniversal() == null && !form.getUniversal().equals("")) {
					//si hemos seleccionado todos los nodos entendemos que es universal
//			if(form.getIdentificadorLicencia().equals("6.2.5")&& (form.getTextoLicencia()==null|| form.getTextoLicencia().trim().length()==0)){
//				if ((publiSes.getUniversal().compareTo(getPropertyValue("licencia.acceso.universal"))) == 0)
//					form.setUniversal("1");
//				else
//					form.setUniversal("0");
//				logger.debug("Es universal al dar el error? "+ form.getUniversal());
//				throw new ValidatorException("{gestorFlujo.error.descripcion.obligatoria}");
//				
//			}else{
				try {
					if (form.getComunidadesSeleccionadasAsArray()!=null && form.getComunidadesSeleccionadasAsArray().length == (this.getSrvNodoService().listarNodos().length)+1) {
						publiSes.setUniversal(getPropertyValue("licencia.acceso.universal"));
					} else {
						publiSes.setUniversal(getPropertyValue("licencia.acceso.non-universal"));
						if(form.getComunidadesSeleccionadasAsArray()!=null && form.getComunidadesSeleccionadasAsArray().length>0){//Si hemos chequeado algún nodo lo recogemos
							String[] comunidadesMostrar = new String[form.getComunidadesSeleccionadasAsArray().length];
							StringBuilder comunidadesDesc = new StringBuilder("");
							NodoVO[] nodos = this.getSrvNodoService().listarNodos();

							for (int i = 0; i < form.getComunidadesSeleccionadasAsArray().length ; i++) { 
								comunidadesDesc.append((form.getComunidadesSeleccionadasAsArray()[i]).toString()+ ",");
								// TODO manera transitoria de recuperar el nombre de los
								// nodos								
								for (int j = 0; j < nodos.length; j++) {//Los nombres de los nodos
									if (nodos[j].getIdNodo().equalsIgnoreCase(
											form.getComunidadesSeleccionadasAsArray()[i].toString())) {
										comunidadesMostrar[i] = nodos[j].getUrlWS();
										break;
									}
	
								}
							}
							if(logger.isDebugEnabled())
								logger.debug("ComunidadesMostrar:"+comunidadesDesc);
//							comunidadesDesc = comunidadesDesc.substring(0, comunidadesDesc.length() - 1);
							comunidadesDesc = comunidadesDesc.deleteCharAt(comunidadesDesc.length() - 1);
							publiSes.setComunidades(comunidadesDesc.toString());
							publiSes.setComunidadesMostrar(comunidadesMostrar);
							if(publiSes.getComunidadesMostrar().length== (this.getSrvNodoService().listarNodos().length)){
								publiSes.setUniversal(getPropertyValue("licencia.acceso.universal"));
								logger.debug("Hemos seleccionado todos los nodos, por lo que será universal");
							}
							if(logger.isDebugEnabled())
								logger.debug("Consolidando licencias: tipo= " + form.getIdentificadorLicencia()
												+ " Número de comunidades = " + form.getComunidadesSeleccionadasAsArray().length
												+ " comunidades: " + publiSes.getComunidades() + " Universal= "
												+ publiSes.getUniversal() + ".");
							
						}else{//Si no se ha chequeado ningún nodo, le pasamos un String vacío y comunidades para mostrar vacío
							String[] comunidadesMostrar = new String[0];
							publiSes.setComunidadesMostrar(comunidadesMostrar);
							publiSes.setComunidades("");
						}
					}
//				}else{
//					publiSes.setUniversal(getPropertyValue("licencia.acceso.universal"));
//					logger.debug("licencia del ode universal");
//				}

			// hay que mostrar el tipo de licencia en el idioma de la sesión
			 publiSes.setIdentificadorLicencia(form.getIdentificadorLicencia());
			 publiSes.setTextoLicencia(form.getTextoLicencia());
			 form.setIdentificadorLicencia("");
			 form.setTextoLicencia("");
			 
			 logger.info("Licencia consolidada correctamente en la session ["+publiSes.getIdentificadorLicencia()+"] y borramos el formulario");
			logger.info("Ambito consolidado correctamente en la session");
				} catch (ValidatorException ex) {
					logger.error("Excepcion consolidando licencias:", ex);
					throw new ValidatorException("{gestorFlujo.error.descripcion.obligatoria}");
				} catch (Exception ex) {
					logger.error("Excepcion consolidando licencias:", ex);
					throw new ValidatorException("{gestorFlujo.error.inesperado}");
				}
//			}
		
    	
     }



    private TerminoVO[] ordena(TerminoVO[] termino, String idioma) {
		for (int j = 0; j < termino.length - 1; j++) {

			for (int k = j + 1; k < termino.length; k++) {

				if (termino[j].getNomTermino().compareTo(termino[k].getNomTermino()) > 0) {

					TerminoVO auxTerm = new TerminoVO();

					String aux = termino[k].getNomTermino();
					String auxId = termino[k].getIdTermino();
					auxTerm.setIdiomaTermino(idioma);
					auxTerm.setIdTermino(auxId);
					auxTerm.setNomTermino(aux);

					termino[k] = termino[j];
					termino[j] = auxTerm;
				}
			}
		}
		return termino;
	}

	/** obtener el property* */
	private String getPropertyValue(String sKey) {
		String sReturn = "";
		try {
			if (props == null) {
				InputStream fIsSpringProperties = MenuController.class.getResourceAsStream(FILE_NAME_PROPERTIES);
				props = new java.util.Properties();
				props.load(fIsSpringProperties);
			}
			sReturn = props.getProperty(sKey);
		} catch (IOException e) {
			logger.error(e);
		}
		// devolvemos la propiedad
		return sReturn;
	}


	/*
	 * Este metodo mapea los objetos de nodovo a nodoODE vo dependiendo de si
	 * los nodos que le pasan estan en la lista de nodos licenciados
	 */
	private NodoVOODE[] mapeaArrayNodoVOToNodoVOODE(NodoVO[] listaNodos, String listaLicenciados) {
		NodoVOODE[] nodosReturn = new NodoVOODE[listaNodos.length];
		logger.debug("La lista de todos los nodos es de longitud "+listaNodos.length);
		ArrayList arrayLicenciados = new ArrayList(Arrays.asList(listaLicenciados.split(",")));
		logger.debug("Y la lista de los recogidos es "+arrayLicenciados.size());
		// Nos recorremos la lista de nodos y vemos uno a uno si aparece en la
		// lista de licenciados, si es asi, los marcamos positivamente,
		// si no, negativamente.

		for (int i = 0; i < listaNodos.length; i++) {
			if(logger.isDebugEnabled())
			logger.debug("Mapeando nodo[" + listaNodos[i].getIdNodo() + "] etiqueta[" + listaNodos[i].getNodo()
					+ "]esta presente?[" + arrayLicenciados.contains(listaNodos[i].getIdNodo()) + "]");

			NodoVOODE nodo = new NodoVOODE();
			nodo.setNodo(listaNodos[i].getNodo());
			nodo.setIdNodo(listaNodos[i].getIdNodo());
			nodo.setEstaLicenciado(arrayLicenciados.contains(listaNodos[i].getIdNodo()));
			nodosReturn[i] = nodo;
		}

		return nodosReturn;
	}

//	 Necesitamos un VO que albergue los nodos (la info de id y etiqueta) y
	// cierta informacion para saber si el nodo estaba ya en el ODE o no.
	static public class NodoVOODE {
		private String idNodo;

		private String nodo;

		boolean estaLicenciado;

		public boolean isEstaLicenciado() {
			return estaLicenciado;
		}

		public void setEstaLicenciado(boolean estaLicenciado) {
			this.estaLicenciado = estaLicenciado;
		}

		public String getNodo() {
			return nodo;
		}

		public void setNodo(String nodo) {
			this.nodo = nodo;
		}

		public String getIdNodo() {
			return idNodo;
		}

		public void setIdNodo(String idNodo) {
			this.idNodo = idNodo;
		}

	}

	@Override
	public String selectAction(ActionMapping mapping, SelectActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String result = null;
		String actionCombo = form.getActionCombo();
		String actionSubmit = form.getActionSubmit();
		logger.debug("el actionCombo es "+actionCombo+" y el actionSubmit "+actionSubmit);
		java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);//request.getLocale();
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",
				locale);
		String[] comunidades=null;
		if(form.getComunidadesSeleccionadas()!=null && form.getComunidadesSeleccionadas().size()>0){
			comunidades=(String[])form.getComunidadesSeleccionadas().toArray(new String[0]);
		}else{
			logger.debug("No hay comunidades seleccionadas");
			comunidades=new String[0];
		}
		StringBuilder comunidadesString=new StringBuilder("");
		for(int i=0;i<comunidades.length;i++){
			logger.debug("Guardamos las comunidades "+comunidades[i]);
			comunidadesString.append(comunidades[i]+",");
		}
		
		if(comunidades.length== (this.getSrvNodoService().listarNodos().length)){
			this.getPublicarSession(request).setUniversal(getPropertyValue("licencia.acceso.universal"));
			logger.debug("Hemos seleccionado todos los nodos, por lo que será universal");
		}else{
			this.getPublicarSession(request).setUniversal(getPropertyValue("licencia.acceso.non-universal"));
			logger.debug("No es universal");
		}
		logger.debug("Guardamos la lista de  comunidades "+comunidadesString);
		if(actionSubmit == null) {
			logger.error("Error: actionSubmit null en ModificarLicencia");
		} else {
			logger.debug("Guardamos la licencia"+form.getActionCombo());
			if(actionSubmit.equals(i18n.getString("gestorFlujo.aceptar"))) {
				if(actionCombo.equals("6.2.5")&& (form.getTextoLicencia()==null|| form.getTextoLicencia().trim().length()==0)){
					this.getPublicarSession(request).setIdentificadorLicencia(form.getActionCombo());			
					this.getPublicarSession(request).setComunidades(comunidadesString.toString());
					this.getPublicarSession(request).setComunidadesMostrar(comunidades);
					form.setIdentificadorLicencia(form.getActionCombo());//Para luego cogerlo en consolidar licencias y en publicar
					form.setComunidadesSeleccionadasAsArray(comunidades);
					
					result="actionComboSubmit";
//					this.getPublicarSession(request).setIdentificadorLicencia(form.getActionCombo());			
//					this.getPublicarSession(request).setComunidades(comunidadesString);
//					this.getPublicarSession(request).setComunidadesMostrar(comunidades);
//					form.setIdentificadorLicencia(form.getActionCombo());//Para luego cogerlo en consolidar licencias y en publicar
//					form.setComunidadesSeleccionadasAsArray(comunidades);
//					Object currentForm = request.getSession().getAttribute("form");
//					AgregarLicenciaSelectActionFormImpl specificForm = (AgregarLicenciaSelectActionFormImpl)currentForm;
//					if ((this.getPublicarSession(request).getUniversal().compareTo(getPropertyValue("licencia.acceso.universal"))) == 0)
//						specificForm.setUniversal("1");
//					
//					else
//						specificForm.setUniversal("0");
//					logger.debug("Es universal al dar el error? "+ specificForm.getUniversal());
//					throw new ValidatorException("{gestorFlujo.error.descripcion.obligatoria}");
					saveErrorMessage(request, ERROR_DESCRIPCION);
					
				}else{
					this.getPublicarSession(request).setIdentificadorLicencia(form.getActionCombo());				
					this.getPublicarSession(request).setComunidades(comunidadesString.toString());
					this.getPublicarSession(request).setComunidadesMostrar(comunidades);
					form.setIdentificadorLicencia(form.getActionCombo());//Para luego cogerlo en consolidar licencias y en publicar
					form.setComunidadesSeleccionadasAsArray(comunidades);
					this.getPublicarSession(request).setIdentificadorLicenciaBackup(null);
					this.getPublicarSession(request).setTextoLicenciaBackup(null);
					this.getPublicarSession(request).setComunidadesBackup(null);
					this.getPublicarSession(request).setUniversalBackup(null);
					result="Aceptar";
				}
				
			} else if(actionSubmit.equals(i18n.getString("gestorFlujo.cancelar"))) {
				this.getPublicarSession(request).setIdentificadorLicencia(this.getPublicarSession(request).getIdentificadorLicenciaBackup());
				this.getPublicarSession(request).setTextoLicencia(this.getPublicarSession(request).getTextoLicenciaBackup());
				form.setIdentificadorLicencia(this.getPublicarSession(request).getIdentificadorLicenciaBackup());

				NodoVOODE[] comunidadesBackup=null;
				String comunidadesBackupString="";
				if(this.getPublicarSession(request).getComunidadesBackup()!=null && this.getPublicarSession(request).getComunidadesBackup().size()>0){
					Object[] coleccionComu=this.getPublicarSession(request).getComunidadesBackup().toArray();
					comunidadesBackup=(NodoVOODE[])coleccionComu[0];
					logger.debug("Ahora tenemos que recogerlos de nuestra vairable backup"+comunidadesBackup.length);
					String[] colecta=new String[comunidadesBackup.length];
					for(int i=0;i<comunidadesBackup.length;i++){
						if(comunidadesBackup[i].isEstaLicenciado()){
							colecta[i]=comunidadesBackup[i].getIdNodo();
							comunidadesBackupString=comunidadesBackupString+comunidadesBackup[i].getIdNodo()+",";
						}
					}
					logger.debug("Las comunidades que se deben guardar son las iniciales"+comunidadesBackupString);
					this.getPublicarSession(request).setComunidades(comunidadesBackupString);
					this.getPublicarSession(request).setComunidadesMostrar(colecta);
				}
				
				this.getPublicarSession(request).setUniversal(this.getPublicarSession(request).getUniversalBackup());
				result="Cancelar";
				this.getPublicarSession(request).setIdentificadorLicenciaBackup(null);
				this.getPublicarSession(request).setTextoLicenciaBackup(null);
				this.getPublicarSession(request).setComunidadesBackup(null);
				this.getPublicarSession(request).setUniversalBackup(null);
			} else if(actionSubmit.equals(i18n.getString("gestorFlujo.agregarLicencia.ver")) || actionCombo.equals(i18n.getString("gestorFlujo.agregarLicencia.ver"))) {
				this.getPublicarSession(request).setIdentificadorLicencia(form.getActionCombo());			
				this.getPublicarSession(request).setComunidades(comunidadesString.toString());
				this.getPublicarSession(request).setComunidadesMostrar(comunidades);
				form.setIdentificadorLicencia(form.getActionCombo());//Para luego cogerlo en consolidar licencias y en publicar
				form.setComunidadesSeleccionadasAsArray(comunidades);
				
				result="actionComboSubmit";
			} else {
				logger.debug("No se ha seleccionado ningun action valido. Regresando a PublicarController");
				result="Ninguno";
			}
		}
		if(logger.isDebugEnabled()) logger.debug("SelectAction en ModificarLicencia devuelve : " + result);
		return result;

	}
	
	private String obtenerTextoLicencia(String identificadorLicencia) throws RemoteException, Exception{
		String urlLicencias=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.URL_LICENCIAS);
		
		String urlEntero=urlLicencias+this.FILE_SEPARATOR+identificadorLicencia+this.FILE_SEPARATOR+this.LICENCIA_NAME;
		logger.debug("El url entero es "+urlEntero);
		File localizador=new File(urlEntero);
		String texto="";
		String texto2="";
		try{
			BufferedReader br = new BufferedReader(new FileReader(localizador));

			 while ((texto=br.readLine()) != null) {
				 if(texto2!=null && !texto2.equals("")){
					 String lineSep = System.getProperty("line.separator");//Asi obtiene el salto de linea dependiendo la plataforma.
//					 String lineSep="\n";
					 texto2 = texto2+lineSep+texto;
				 }else{
					 texto2=texto.trim();
				 }
			 }
	
		 	logger.debug("El texto de la licencia es "+texto2);
	
	 		br.close();
		
		}catch(IOException ex){
			logger.error("Error  obteniendo el texto de la licencia " +ex);
		}
		return texto2;
	}
}