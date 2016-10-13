package es.pode.empaquetador.presentacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.pode.empaquetador.negocio.servicio.ArchivoVO;
import es.pode.empaquetador.negocio.servicio.GrupoVO;
import es.pode.empaquetador.negocio.servicio.OdeVO;
import es.pode.empaquetador.negocio.servicio.OrganizacionVO;
import es.pode.empaquetador.negocio.servicio.RecursoVO;
import es.pode.empaquetador.negocio.servicio.ValidaVO;
import es.pode.empaquetador.presentacion.archivos.GestorArchivosSession;
import es.pode.empaquetador.presentacion.archivos.gestor.GestorArchivosController;
import es.pode.empaquetador.presentacion.basico.gestor.GestorBasicoController;

public class AsistenteEmpaquetador implements Serializable {

	private static final String NORMAL = "Normal";
	
	private boolean hayArchivo(ArchivoVO ultArch){
		boolean archivo=false;
		if ((ultArch.getContenidos()!=null) && (ultArch.getContenidos().length>0)){
			int iA=0;
			while (!archivo && (iA<ultArch.getContenidos().length)){
				if ((!ultArch.getContenidos()[iA].getEsProtegido()) && (ultArch.getContenidos()[iA].getEsFichero())){
					archivo=true;
				}
				iA++;
			}
		}
		
		return archivo;
	}
	private boolean hayCarpeta(ArchivoVO ultCarp){
		boolean carpeta=false;
		if ((ultCarp.getContenidos()!=null) && (ultCarp.getContenidos().length>0)){
			int iA=0;
			while (!carpeta && (iA<ultCarp.getContenidos().length)){
				if ((!ultCarp.getContenidos()[iA].getEsProtegido()) && (!ultCarp.getContenidos()[iA].getEsFichero())){
					carpeta=true;
				}
				iA++;
			}
		} else if ((ultCarp.getContenidos()!=null) && (ultCarp.getContenidos().length==0)){
			if (!ultCarp.getEsFichero() && !ultCarp.getEsProtegido()) { //es una Carpeta
				carpeta=true;
			}
		}
		
		return carpeta;
	}
	
	private boolean hayArchivosEscondidos(ArchivoVO[] arch){
		boolean archivo=false;
		
	
	  if ((arch!=null) && (arch.length>0)){
			int iA=0;
			while (!archivo && (iA<arch.length)){
				//buscamos en cada posicion				
				if ((!arch[iA].getEsProtegido()) && (arch[iA].getEsFichero())){
					archivo=true; //aqui seria true && true
				} else if (arch[iA].getContenidos()!=null && arch[iA].getContenidos().length>0){
					//llamada recursiva
					archivo=hayArchivosEscondidos(arch[iA].getContenidos());
				}
				iA++;
			}			
	   	}
		
		return archivo;
	}
	
	private boolean buscarGrupoSubm(GrupoVO[] grupos, String idSubmanifest){
		boolean submanifest=false;
		
		if (grupos!=null && grupos.length>0){
			int iG=0;
			while (!submanifest && iG<grupos.length){			
				if (!submanifest && grupos[iG].getEsSubmanifiesto() && grupos[iG].getElementoReferenciado().equals(idSubmanifest)){
					submanifest=true;
				}else if (grupos[iG].getGrupos()!=null && grupos[iG].getGrupos().length>0){
					submanifest=buscarGrupoSubm(grupos[iG].getGrupos(), idSubmanifest);
				}
				iG++;	
			}			
		}
		return submanifest;
	}
	
	public String asistenteBasico (EmpaquetadorSession empSes, GestorArchivosSession sesArch) throws Exception{
		
		//Para comprobar que no tiene archivos, recogemos sesArch.getPath y dentro del path están todos los contenidos
		//si el ultimo tiene protegido = false-> es q tiene algun archivo
		//Para curarnos en salud,... vamos a recorrer todos los contenidos
		OrganizacionVO org = null;		
		boolean elementoCarpeta=false;
		boolean elementoRecurso=false;
		boolean urlRecurso=false;
		ValidaVO validacion = null;
		boolean catalogado = false;
		String mensajeAsistente = null;
		
		if (empSes!=null && empSes.getIdCollection()!=null && empSes.getIdCollection().size()>0){
			org = (OrganizacionVO)empSes.getIdCollection().get(0);
			if  (org.getGrupos()!=null && org.getGrupos().length>0) {
				elementoCarpeta=org.getGrupos()[0].getEsCarpeta()!=null?org.getGrupos()[0].getEsCarpeta():false;
				elementoRecurso=org.getGrupos()[0].getEsRecurso()!=null?org.getGrupos()[0].getEsRecurso():false;
				if (elementoRecurso && org.getGrupos()[0].getRecursoURL()!=null && !org.getGrupos()[0].getRecursoURL().equals("")){
					urlRecurso=true;
				}
			}			
			
		}
		if (empSes!=null && (empSes.getEsOffline() || empSes.getEspacioOde()!=null )  ){
			//Cuando entramos en la opción de modificar, la parte de Archivos de la sesión, no se actualiza hasta que no vamos al Gestor de Archivos
			if (empSes.getEsOffline() || !empSes.getEspacioOde().equals("0")){
				//tenemos q intentar comprobar si realmente tiene archivos, e inicializamos sus datos de session...
				ArchivoVO recuperado = this.getSrvGestorArchivosService().recuperarArbol(empSes.getIdLocalizador());
				if (sesArch==null || sesArch.getPath() == null)
		    	{
		      		List portapapeles=new ArrayList();
		    		List pathList=new ArrayList();
		    		pathList.add(recuperado);

		    		sesArch.setAccion(NORMAL);
		    		sesArch.setModoPegar(false);
		    		sesArch.setPath(pathList);
		    		sesArch.setPortapapeles(portapapeles);
		    	}
				
			}
		}
		boolean archivo=false;
		boolean carpeta=false;
		// inicializamos los valores de archivo y carpeta 
		if (sesArch!=null && sesArch.getPath()!=null) {
			ArchivoVO ultArch =(ArchivoVO)sesArch.getPath().get(sesArch.getPath().size()-1);
			archivo=hayArchivo(ultArch);
			carpeta=hayCarpeta(ultArch);
			//ultima comprobacion por si el archivo esta dentro de la carpeta
			if (carpeta && !archivo){
				if (ultArch.getContenidos()!=null && ultArch.getContenidos().length>0){
					archivo=hayArchivosEscondidos(ultArch.getContenidos());
				}
			}
		}
		//Comprobamos los valores
		if (!archivo && !carpeta){ // No tiene archivos ni carpetas
			//throw new ValidatorException("{portalempaquetado.basico.asistente.noCarpetasNoArchivos}");
			mensajeAsistente="portalempaquetado.basico.asistente.noCarpetasNoArchivos";
		} else if (!archivo && carpeta){
			//throw new ValidatorException("{portalempaquetado.basico.asistente.siCarpetasNoArchivos}");
			mensajeAsistente="portalempaquetado.basico.asistente.siCarpetasNoArchivos";
		} else if (archivo && ((org==null) || (org!=null && (org.getGrupos()==null || org.getGrupos().length==0)))){
			//throw new ValidatorException("{portalempaquetado.basico.asistente.siArchivos}");
			mensajeAsistente="portalempaquetado.basico.asistente.siArchivos";
		} 
		////elemento sin asociar
		else if ((elementoCarpeta && !elementoRecurso) || (!elementoCarpeta && elementoRecurso && !urlRecurso)){
			//throw new ValidatorException("{portalempaquetado.basico.asistente.noAsociacion}");
			mensajeAsistente="portalempaquetado.basico.asistente.noAsociacion";
		}
		//si tenemos un recurso (elemento asociado) y no ha sido guardado--> mandamos guardar
		else if (!elementoCarpeta && elementoRecurso && empSes!=null && !empSes.isGuardadoPrimeraVez()){
			//throw new ValidatorException("{portalempaquetado.basico.asistente.siComponentesNoGuardado}");
			mensajeAsistente="portalempaquetado.basico.asistente.siComponentesNoGuardado";
		}
		else if (!elementoCarpeta && elementoRecurso && empSes!=null && empSes.isGuardadoPrimeraVez() && empSes.isModificado()){
			//throw new ValidatorException("{portalempaquetado.basico.asistente.siComponentesOtroNoGuardado}");
			mensajeAsistente="portalempaquetado.basico.asistente.siComponentesOtroNoGuardado";
		}
		//si cumple lo anterior, es hora de catalogar --> mandamos catalogar
		else {
			 
			if (validacion==null || (validacion!=null && !validacion.getEsValidoManifest())){
				//Creamos un backup del manifiesto y guardamos cambios
				this.getSrvGestorManifestService().guardarManifiesto(empSes.getIdLocalizador(), true);
				validacion = this.getSrvGestorManifestService().validar(empSes.getIdLocalizador(), "COMPLETA");
				String resultado = validacion.getResultadoValidacion();
				//Sobreescribimos el manifiesto con su backup
				this.getSrvGestorManifestService().restaurarManifiesto(empSes.getIdLocalizador());				
				if ((resultado.startsWith("Error LOM-ES") || resultado.startsWith("LOM-ES Error")) ||
					(resultado.startsWith("Errorea LOM-ES") || resultado.endsWith("Erro LOM-ES"))) {
					 
					catalogado=false;
				}else {
					 catalogado=true;					
				}
			}
			if (!elementoCarpeta && elementoRecurso && empSes!=null && empSes.isGuardadoPrimeraVez() && !catalogado){
				//throw new ValidatorException("{portalempaquetado.basico.asistente.siComponentesSiGuardado}");
				mensajeAsistente="portalempaquetado.basico.asistente.siComponentesSiGuardado";
			}else {
				if (validacion==null){
					//Creamos un backup del manifiesto y guardamos cambios
					this.getSrvGestorManifestService().guardarManifiesto(empSes.getIdLocalizador(), true);
					validacion = this.getSrvGestorManifestService().validar(empSes.getIdLocalizador(), "COMPLETA");
					//Sobreescribimos el manifiesto con su backup
					this.getSrvGestorManifestService().restaurarManifiesto(empSes.getIdLocalizador());					
				}
				/// si no se cumplen ninguna de las anteriores... validamos el ode
				if (validacion!=null && empSes!=null && empSes.isGuardadoPrimeraVez()){
					if (validacion.getEsValidoManifest().booleanValue()){ //es valido y todo ok
						//throw new ValidatorException("{portalempaquetado.basico.asistente.siComponentesSiCatalogado}");
						mensajeAsistente="portalempaquetado.basico.asistente.siComponentesSiCatalogado";
					} else if (!validacion.getEsValidoManifest().booleanValue()){
						String resultado = validacion.getResultadoValidacion();
						if ((resultado.startsWith("Error") && resultado.endsWith("LOM-ES")) ||
								 (resultado.startsWith("LOM-ES") && resultado.endsWith("Error"))) {
							//throw new ValidatorException("{portalempaquetado.basico.asistente.siComponentesSiGuardado}");
							mensajeAsistente="portalempaquetado.basico.asistente.siComponentesSiGuardado";
						}else {
							//throw new ValidatorException("{portalempaquetado.avanzado.asistente.verError}");
							mensajeAsistente="portalempaquetado.avanzado.asistente.verError";
						}						
					}
				}
			}
		}			
		return mensajeAsistente;
	}
	
public String asistenteAvanzado (EmpaquetadorSession empSes, GestorArchivosSession sesArch) throws Exception{
		
		String mensajeAsistente=null;
		//Para comprobar que no tiene archivos, recogemos sesArch.getPath y dentro del path están todos los contenidos
		//si el ultimo tiene protegido = false-> es q tiene algun archivo
		//Para curarnos en salud,... vamos a recorrer todos los contenidos
		boolean submanifest=false;
		boolean submAsociado=false;
		OrganizacionVO org = null;		
		boolean elementoRecurso=false;
		boolean urlRecurso=false;
		boolean archivo=false;
		boolean carpeta=false;
		boolean orgconItem=false;
		ValidaVO validacion = null;
		boolean catalogado = false;
		
		boolean submanifesNoItem=false;
		
	
		List submanifestPath=empSes.getSubmanifestPath();
    	
    	OdeVO ultimoElemSubmPath = (OdeVO) submanifestPath.get(submanifestPath.size()-1);
    	List recursos=new ArrayList();
    	RecursoVO[] recur = ultimoElemSubmPath.getRecursos();
    	List recursosLista = Arrays.asList(recur);
    	recursos.addAll(recursosLista);
		
    	
		//Comprobamos si tiene Submanifest pero sin asociar, damos indicaciones para que lo asocie
		if (empSes!=null && empSes.getOde()!=null && empSes.getOde().getSubmanifiestos()!=null && empSes.getOde().getSubmanifiestos().length>0){
			//Si entra por aqui, es porque al menos tiene un submanifiesto activamos su variable
			submanifest=true;
			//controlamos que cada submanifiesto tenga un item!!
			int iS=0;
			while (!submanifesNoItem && iS<empSes.getOde().getSubmanifiestos().length){
				String ideSubm=empSes.getOde().getSubmanifiestos()[iS].getIdentifier();
				//inicializamos el valor de submasociado
				submAsociado=false;
				//ahora comprobamos si está asociado o no a la org...
				if (empSes.getOde().getOrganizaciones()!=null && empSes.getOde().getOrganizaciones().length>0) {
					for (int iO=0; iO<empSes.getOde().getOrganizaciones().length && (!submAsociado); iO++ ){
						if (empSes.getOde().getOrganizaciones()[iO].getGrupos()!=null && empSes.getOde().getOrganizaciones()[iO].getGrupos().length>0){
							//buscamos recursivamente en los grupos si tiene sub
							//submAsociado=buscarGrupoSubm(empSes.getOde().getOrganizaciones()[iO].getGrupos(),ideSubm);
							if (buscarGrupoSubm(empSes.getOde().getOrganizaciones()[iO].getGrupos(),ideSubm)){
								submAsociado=true;
							}
						}						
					}
				}
				if (!submAsociado){
					//ese submanifest no ha sido encontrado en ninguna organización ni en ningún grupo
					submanifesNoItem=true;
				}
				iS++;
			}
			
		}
		
		// Comprobamos si tiene algún item la Organización u organizaciones
		if (empSes!= null && empSes.getOde()!=null && empSes.getOde().getOrganizaciones()!=null && empSes.getOde().getOrganizaciones().length>0){
			int iG=0;
			while (!orgconItem && iG<empSes.getOde().getOrganizaciones().length){
				if (empSes.getOde().getOrganizaciones()[iG].getGrupos()!=null && empSes.getOde().getOrganizaciones()[iG].getGrupos().length>0){
					int iI=0;
					while (!orgconItem && iI<empSes.getOde().getOrganizaciones()[iG].getGrupos().length){
						
						if ((empSes.getOde().getOrganizaciones()[iG].getGrupos()[iI].getEsCarpeta()!=null && empSes.getOde().getOrganizaciones()[iG].getGrupos()[iI].getEsCarpeta() && (empSes.getOde().getOrganizaciones()[iG].getGrupos()[iI].getElementoReferenciado()==null || 
							empSes.getOde().getOrganizaciones()[iG].getGrupos()[iI].getElementoReferenciado().equals(""))) ||
							(((empSes.getOde().getOrganizaciones()[iG].getGrupos()[iI].getEsRecurso()!= null && empSes.getOde().getOrganizaciones()[iG].getGrupos()[iI].getEsRecurso()) || empSes.getOde().getOrganizaciones()[iG].getGrupos()[iI].getEsSubmanifiesto()) &&  
							 empSes.getOde().getOrganizaciones()[iG].getGrupos()[iI].getElementoReferenciado()!=null && !empSes.getOde().getOrganizaciones()[iG].getGrupos()[iI].getElementoReferenciado().equals(""))) {
								
								orgconItem=true;
						}
						iI++;						
					}
				}
				iG++;
			}
		}
		
		//miramos los recursos
		if (empSes!=null && empSes.getOde()!=null){
			if (empSes.getOde().getRecursos()!=null && empSes.getOde().getRecursos().length>0 && empSes.getOde().getRecursos()[0].getRecursoURL()!=null){
				elementoRecurso=true;
				if (!empSes.getOde().getRecursos()[0].getRecursoURL().equals("")){
					urlRecurso=true;
				}
			}else {
				if (recursos!= null && recursos.size()>0){
					//en este caso tenemos recursos pero no sabems si estan asociados o no
					int ir=0;
					urlRecurso=false;
					elementoRecurso=true;
					while (!urlRecurso && ir<recursos.size()){
						if (((RecursoVO)recursos.get(ir)).getHref()!=null && !((RecursoVO)recursos.get(ir)).getHref().equals("") &&
							((RecursoVO)recursos.get(ir)).getRecursoURL()!=null && !((RecursoVO)recursos.get(ir)).getRecursoURL().equals("")){
							urlRecurso=true;
						}
						ir++;
					}
					
				}
			}
		}
		if (empSes!=null && (empSes.getEsOffline() || empSes.getEspacioOde()!=null )  ){
			//Cuando entramos en la opción de modificar, la parte de Archivos de la sesión, no se actualiza hasta que no vamos al Gestor de Archivos
			if (empSes.getEsOffline() || !empSes.getEspacioOde().equals("0")){
				//tenemos q intentar comprobar si realmente tiene archivos, e inicializamos sus datos de session...
				ArchivoVO recuperado = this.getSrvGestorArchivosService().recuperarArbol(empSes.getIdLocalizador());
				if (sesArch==null || sesArch.getPath() == null)
		    	{
		      		List portapapeles=new ArrayList();
		    		List pathList=new ArrayList();
		    		pathList.add(recuperado);

		    		sesArch.setAccion(NORMAL);
		    		sesArch.setModoPegar(false);
		    		sesArch.setPath(pathList);
		    		sesArch.setPortapapeles(portapapeles);
		    	}
				
			}
		}
		// inicializamos los valores de archivo y carpeta 
		if (sesArch!=null && sesArch.getPath()!=null) {
			ArchivoVO ultArch =(ArchivoVO)sesArch.getPath().get(sesArch.getPath().size()-1);
			archivo=hayArchivo(ultArch);
			carpeta=hayCarpeta(ultArch);
			//ultima comprobacion por si el archivo esta dentro de la carpeta
			if (carpeta && !archivo){
				if (ultArch.getContenidos()!=null && ultArch.getContenidos().length>0){
					archivo=hayArchivosEscondidos(ultArch.getContenidos());
				}
			}
		}
		//Comprobamos los valores
		if (submanifest && submanifesNoItem){//Primero miramos si tiene submanifest y no los tiene asociados
			//throw new ValidatorException("{portalempaquetado.avanzado.asistente.noSubmAsociado}");
			mensajeAsistente="portalempaquetado.avanzado.asistente.noSubmAsociado";			
		}else if (!archivo && !carpeta){ // No tiene archivos ni carpetas
			//throw new ValidatorException("{portalempaquetado.basico.asistente.noCarpetasNoArchivos}");
			mensajeAsistente="portalempaquetado.basico.asistente.noCarpetasNoArchivos";
		} else if (!archivo && carpeta){
			//throw new ValidatorException("{portalempaquetado.basico.asistente.siCarpetasNoArchivos}");
			mensajeAsistente="portalempaquetado.basico.asistente.siCarpetasNoArchivos";
		} 
		else if ((archivo && !elementoRecurso)){
			//throw new ValidatorException("{portalempaquetado.avanzado.asistente.noRecurso}");
			mensajeAsistente="portalempaquetado.avanzado.asistente.noRecurso";
		}
		else if ((archivo && elementoRecurso && !urlRecurso)){
			//throw new ValidatorException("{portalempaquetado.avanzado.asistente.noRecursoAsociado}");
			mensajeAsistente="portalempaquetado.avanzado.asistente.noRecursoAsociado";
		}
		//la organización necesita un item
		else if (archivo && elementoRecurso && urlRecurso && !orgconItem) {
			//throw new ValidatorException("{portalempaquetado.avanzado.asistente.noOrganizacionItem}");
			mensajeAsistente="portalempaquetado.avanzado.asistente.noOrganizacionItem";
		}
		//si tenemos un recurso (elemento asociado) y no ha sido guardado--> mandamos guardar
		else if (elementoRecurso && empSes!=null && !empSes.isGuardadoPrimeraVez()){
			//throw new ValidatorException("{portalempaquetado.basico.asistente.siComponentesNoGuardado}");
			mensajeAsistente="portalempaquetado.basico.asistente.siComponentesNoGuardado";
		} else if (elementoRecurso && empSes!=null && empSes.isGuardadoPrimeraVez() && empSes.isModificado()){ //caso de Edicion del ode
			//throw new ValidatorException("{portalempaquetado.basico.asistente.siComponentesOtroNoGuardado}");
			mensajeAsistente="portalempaquetado.basico.asistente.siComponentesOtroNoGuardado";
		}	
		//si cumple lo anterior, es hora de catalogar --> mandamos catalogar
		else{ 
			if (validacion==null || (validacion!=null && !validacion.getEsValidoManifest())){
				//Creamos un backup del manifiesto y guardamos cambios
				this.getSrvGestorManifestService().guardarManifiesto(empSes.getIdLocalizador(), true);
				validacion = this.getSrvGestorManifestService().validar(empSes.getIdLocalizador(), "COMPLETA");
				//Sobreescribimos el manifiesto con su backup
				this.getSrvGestorManifestService().restaurarManifiesto(empSes.getIdLocalizador());
				String resultado = validacion.getResultadoValidacion();
				if ((resultado.startsWith("Error LOM-ES") || resultado.startsWith("LOM-ES Error")) ||
					 (resultado.startsWith("Errorea LOM-ES") || resultado.endsWith("Erro LOM-ES"))) {
					 catalogado=false;
				}else {
					 catalogado=true;					
				}
			}			
			if (archivo && elementoRecurso && empSes!=null && empSes.isGuardadoPrimeraVez() && !catalogado){
				//throw new ValidatorException("{portalempaquetado.basico.asistente.siComponentesSiGuardado}");
				mensajeAsistente="portalempaquetado.basico.asistente.siComponentesSiGuardado";
			}else {
				if (validacion==null){
					//Creamos un backup del manifiesto y guardamos cambios
					this.getSrvGestorManifestService().guardarManifiesto(empSes.getIdLocalizador(), true);
					validacion = this.getSrvGestorManifestService().validar(empSes.getIdLocalizador(), "COMPLETA");
					//Sobreescribimos el manifiesto con su backup
					this.getSrvGestorManifestService().restaurarManifiesto(empSes.getIdLocalizador());					
				}
				/// si no se cumplen ninguna de las anteriores... validamos el ode
				if (validacion!=null && empSes!=null && empSes.isGuardadoPrimeraVez()){
					if (validacion.getEsValidoManifest().booleanValue() && !empSes.isModificado()){ //es valido y no modificaco
						//throw new ValidatorException("{portalempaquetado.basico.asistente.siComponentesSiCatalogado}");
						mensajeAsistente="portalempaquetado.basico.asistente.siComponentesSiCatalogado";
					}
					/*else if (validacion.getEsValidoManifest().booleanValue() && empSes.isModificado()){ //es valido y modificado entra aqi
						mensajeAsistente="portalempaquetador.avanzado.asistente.volverAGuardar";
					}*/
					else /*if (!validacion.getEsValidoManifest().booleanValue()) */
					{
						String resultado = validacion.getResultadoValidacion();
						if ((resultado.startsWith("Error") && resultado.endsWith("LOM-ES")) ||
							 (resultado.startsWith("LOM-ES") && resultado.endsWith("Error"))) {
							//throw new ValidatorException("{portalempaquetado.basico.asistente.siComponentesSiGuardado}");
							mensajeAsistente="portalempaquetado.basico.asistente.siComponentesSiGuardado"; //mandamos catalogar
						}else if (!validacion.getEsValidoManifest().booleanValue()){
							//throw new ValidatorException("{portalempaquetado.avanzado.asistente.verError}");
							mensajeAsistente="portalempaquetado.avanzado.asistente.verError";
						}/*else {
					
							if (elementoRecurso && empSes!=null && empSes.isGuardadoPrimeraVez() && empSes.isModificado()){ //caso de Edicion del ode
								//throw new ValidatorException("{portalempaquetado.basico.asistente.siComponentesOtroNoGuardado}");
								mensajeAsistente="portalempaquetado.basico.asistente.siComponentesOtroNoGuardado";
							}
						}*/
					}
				}
			}
		}
		return mensajeAsistente;
	}
	
	  protected final es.pode.empaquetador.negocio.servicio.SrvGestorManifestService getSrvGestorManifestService()
      throws java.lang.Exception
  {
      String srvGestorManifestServiceFile="importedServices.properties";	    
      java.io.InputStream srvGestorManifestServiceInputStream=GestorBasicoController.class.getClassLoader().getResourceAsStream(srvGestorManifestServiceFile);
      java.util.Properties srvGestorManifestServiceProperties = new java.util.Properties();
      srvGestorManifestServiceProperties.load(srvGestorManifestServiceInputStream);
      String srvGestorManifestServiceEndPointAddress="";
      srvGestorManifestServiceEndPointAddress=(String) srvGestorManifestServiceProperties.get("srvGestorManifestServicePort");
      System.out.println("srvGestorManifestServiceEndPointAddress del fichero --> " + srvGestorManifestServiceEndPointAddress);
		es.pode.empaquetador.negocio.servicio.SrvGestorManifestServiceService srvGestorManifestService = new es.pode.empaquetador.negocio.servicio.SrvGestorManifestServiceServiceLocator();                                                                                                                                                                                                                                                    
      if (srvGestorManifestServiceEndPointAddress.length()>0) 
			((es.pode.empaquetador.negocio.servicio.SrvGestorManifestServiceServiceLocator)srvGestorManifestService).setSrvGestorManifestServiceEndpointAddress(srvGestorManifestServiceEndPointAddress);				
  	es.pode.empaquetador.negocio.servicio.SrvGestorManifestService port = srvGestorManifestService.getSrvGestorManifestService();	    
      return port;
  }
	  
	    protected final es.pode.empaquetador.negocio.servicio.SrvGestorArchivosService getSrvGestorArchivosService()
        throws java.lang.Exception
    {
        String srvGestorArchivosServiceFile="importedServices.properties";	    
        java.io.InputStream srvGestorArchivosServiceInputStream=GestorArchivosController.class.getClassLoader().getResourceAsStream(srvGestorArchivosServiceFile);
        java.util.Properties srvGestorArchivosServiceProperties = new java.util.Properties();
        srvGestorArchivosServiceProperties.load(srvGestorArchivosServiceInputStream);
        String srvGestorArchivosServiceEndPointAddress="";
        srvGestorArchivosServiceEndPointAddress=(String) srvGestorArchivosServiceProperties.get("srvGestorArchivosServicePort");
        System.out.println("srvGestorArchivosServiceEndPointAddress del fichero --> " + srvGestorArchivosServiceEndPointAddress);
			es.pode.empaquetador.negocio.servicio.SrvGestorArchivosServiceService srvGestorArchivosService = new es.pode.empaquetador.negocio.servicio.SrvGestorArchivosServiceServiceLocator();                                                                                                                                                                                                                                                    
        if (srvGestorArchivosServiceEndPointAddress.length()>0) 
			((es.pode.empaquetador.negocio.servicio.SrvGestorArchivosServiceServiceLocator)srvGestorArchivosService).setSrvGestorArchivosServiceEndpointAddress(srvGestorArchivosServiceEndPointAddress);				
    	es.pode.empaquetador.negocio.servicio.SrvGestorArchivosService port = srvGestorArchivosService.getSrvGestorArchivosService();	    
        return port;
    }

}