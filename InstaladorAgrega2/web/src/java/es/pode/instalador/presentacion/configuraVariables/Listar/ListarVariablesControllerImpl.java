/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.instalador.presentacion.configuraVariables.Listar;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.apache.struts.action.ActionMapping;

import es.pode.instalador.negocio.ficherosconfiguracion.InstalacionVariables;
import es.pode.instalador.negocio.servicios.PropiedadVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;



/**
 * @see es.pode.instalador.presentacion.configuraVariables.Listar.ListarVariablesController
 */
public class ListarVariablesControllerImpl extends ListarVariablesController
{

	@Override
	public void cargarTiposJboss(ActionMapping mapping,
			CargarTiposJbossForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

    	//Collection<String> tipos = new Collection();
		List<String> tipos = new ArrayList<String>();
		PropiedadVO[] tmp = null;
		
		//Si estamos en modo cluster el prefijo nos vendra como parametro de arranque de JBoss
		String nombreJbossCluster = System.getProperty("nombreNodoCluster");
		if (nombreJbossCluster!=null && nombreJbossCluster.isEmpty()==false) {
			
			if(!nombreJbossCluster.contentEquals(InstalacionVariables.JBOSS_TYPE_NODE1.replace("_", "")) &&
				!nombreJbossCluster.contentEquals(InstalacionVariables.JBOSS_TYPE_NODE2.replace("_", "")) &&
				!nombreJbossCluster.contentEquals(InstalacionVariables.JBOSS_TYPE_NODE3.replace("_", "")) &&
				!nombreJbossCluster.contentEquals(InstalacionVariables.JBOSS_TYPE_NODE4.replace("_", "")) &&
				!nombreJbossCluster.contentEquals(InstalacionVariables.JBOSS_TYPE_NODE5.replace("_", "")) &&
				!nombreJbossCluster.contentEquals(InstalacionVariables.JBOSS_TYPE_NODE6.replace("_", "")))
			System.out.println("ERROR: No se le dio un valor correcto al parametro de inincio nombreNodoCluster de JBoss. Se le debe pasar -DnombreNodoCluster=nodeX donde X es un valor entero de 1 a 6 ambos incluidos.");
		    
			tipos.add(nombreJbossCluster+"_");
			form.setTiposJboss(tipos);	
			return;
		}

	  	try {
	  		tmp = this.getSrvConfiguracionService().getPropiedadByName(InstalacionVariables.DEF_NODO);
		} catch (Exception e) {
			System.out.println("ERROR: hubo un problema al solicitar a la DB la variable "+InstalacionVariables.DEF_NODO);
			e.printStackTrace();
		}
	  	
	  	if(tmp==null || tmp[0]==null) {
			//TODO sustituir esto por un validatorexception
			System.out.println("ERROR: Se recibio NULL; hubo un problema al solicitar a la DB la variable "+InstalacionVariables.DEF_NODO);
			return;
		}
		
		//if(tmp[0].getValor()==null || tmp[0].getValor().trim().equals("")) 
	    	tipos.add(InstalacionVariables.JBOSS_TYPE_DEF);
/*		
	  	try {
	  		tmp = this.getSrvConfiguracionService().getPropiedadByName(InstalacionVariables.ALT_NODO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR: hubo un problema al solicitar a la DB la variable "+InstalacionVariables.ALT_NODO);
			e.printStackTrace();
		}
	  	
	  	if(tmp==null || tmp[0]==null) {
			//TODO sustituir esto por un validatorexception
	  		System.out.println("ERROR: Se recibio NULL; hubo un problema al solicitar a la DB la variable "+InstalacionVariables.DEF_NODO);
			return;
		}
		//if(tmp[0].getValor()==null || tmp[0].getValor().trim().equals("")) 
	    	tipos.add(InstalacionVariables.JBOSS_TYPE_ALT);
*/		
		form.setTiposJboss(tipos);		
	}
	
	
    /**
     * Metodo que cambia el nombre de las variables sustituyendo los caracteres que sean punto
     * por barras bajas. Esto es necesario debido a que el nombre de las variables se usa como 
     * identificador y sirven para extraer las variables y sus valores del form. 
     * El caracter punto daria problemas en la parte de la vista (en el JSP).
     * @param props
     * @return
     */
    private PropiedadVO[] reemplazarPuntosPorBarrasBajas(PropiedadVO[] props) {
    	if (props==null || props.length==0) return props;
    	
    	for(int i=0; i<props.length; i++) {
    		String nombre = props[i].getNombre();
    		if(nombre.contains("."))
    			props[i].setNombre(nombre.replace(".", "_"));
    	}
    	return props;
    }


	@Override
	public void cargaVariablesConfiguracion(ActionMapping mapping,
			CargaVariablesConfiguracionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
  	
		if(!InstalacionVariables.esUnTipoJboss(form.getTipoJboss())) {
			//TODO sustituir esto por un validatorexception
			System.out.println("ERROR: el tipo de JBoss recibido no es valido");
			return;
		}
		
	  	//Rellenamos la pantalla con una lista de todas las variables de configuracion que haya en la BD
		//y que ademas no tengan un valor por defecto, ya que en caso de tenerlo se configuran sin preguntar
		//al usuario.
	  	//Las variables tienen que existir, aunque tengan el campo de valor vacio, ya que al menos tienen 
		//que tener descripcion y ejemplo.
	  	PropiedadVO[] propiedades = null;	  	
	  	try {
	  		propiedades = this.getSrvConfiguracionService().getAllProperties(form.getTipoJboss(), false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR: hubo un error al solicitar a la DB el conjunto de propiedades");
			e.printStackTrace();
		}
		if(propiedades == null) {
			//TODO sustituir esto por un validatorexception
			System.out.println("ERROR: hubo un error al solicitar a la DB el conjunto de propiedades");
			return;
		}
	
		HashMap<String ,String> propsMap = new HashMap<String ,String>();
		for(int i=0; i<propiedades.length; i++) 		
			propsMap.put(propiedades[i].getNombre(), propiedades[i].getValor());
		
		//Solo metemos
		form.setCasurl				(propsMap.get(InstalacionVariables.CASURL));
		form.setCcaa				(propsMap.get(InstalacionVariables.CCAA));
		//form.setGaleriaImgPath		(propsMap.get(InstalacionVariables.GALERIAIMGPATH));
		form.setIdnodo				(propsMap.get(InstalacionVariables.IDNODO));
		//form.setImgserverhost		(propsMap.get(InstalacionVariables.IMGSERVERHOST));
		//form.setImgserverport		(propsMap.get(InstalacionVariables.IMGSERVERPORT));
		form.setJBOSS_HOME			(propsMap.get(InstalacionVariables.JBOSS_HOME));
		form.setLdapadmin			(propsMap.get(InstalacionVariables.LDAPADMIN));
		form.setLdapexternal		(propsMap.get(InstalacionVariables.LDAPEXTERNAL));
		form.setLdapmanagerDN		(propsMap.get(InstalacionVariables.LDAPMANAGERDN));
		form.setLdapmanagerPasswd	(propsMap.get(InstalacionVariables.LDAPMANAGERPASSWD));
		form.setLdappath			(propsMap.get(InstalacionVariables.LDAPPATH));
		form.setLdapurl				(propsMap.get(InstalacionVariables.LDAPURL));
		form.setLdapusuariosbindsubpath(propsMap.get(InstalacionVariables.LDAPUSUARIOSBINDSUBPATH));
		form.setMailContacto		(propsMap.get(InstalacionVariables.MAILCONTACTO));
		form.setNodo				(propsMap.get(InstalacionVariables.NODO));
		form.setNodo_jboss			(propsMap.get(InstalacionVariables.NODO_JBOSS));
		form.setPort				(propsMap.get(InstalacionVariables.PORT));
		form.setPort_jboss			(propsMap.get(InstalacionVariables.PORT_JBOSS));
		form.setPrefijo_nodo		(propsMap.get(InstalacionVariables.PREFIJO_NODO));
		form.setProxy				(propsMap.get(InstalacionVariables.PROXY));
		form.setProxyhost			(propsMap.get(InstalacionVariables.PROXYHOST));
		form.setProxypasswd			(propsMap.get(InstalacionVariables.PROXYPASSWD));
		form.setProxyport			(propsMap.get(InstalacionVariables.PROXYPORT));
		form.setProxyuser			(propsMap.get(InstalacionVariables.PROXYUSER));
		form.setRolebindsubpath		(propsMap.get(InstalacionVariables.ROLEBINDSUBPATH));
		form.setRutalogs			(propsMap.get(InstalacionVariables.RUTALOGS));
		form.setServeron			(propsMap.get(InstalacionVariables.SERVERON));
		form.setSmtpauthentication	(propsMap.get(InstalacionVariables.SMTPAUTHENTICATION));
		form.setSmtphost			(propsMap.get(InstalacionVariables.SMTPHOST));
		form.setSmtppasswd			(propsMap.get(InstalacionVariables.SMTPPASSWD));
		form.setSmtpsender			(propsMap.get(InstalacionVariables.SMTPSENDER));
		form.setSmtpuser			(propsMap.get(InstalacionVariables.SMTPUSER));
		form.setSubdomain			(propsMap.get(InstalacionVariables.SUBDOMAIN));
		form.setSubdomain_jboss		(propsMap.get(InstalacionVariables.SUBDOMAIN_JBOSS));
		form.setTelefonoContacto	(propsMap.get(InstalacionVariables.TELEFONOCONTACTO));
		form.setUPLOADS				(propsMap.get(InstalacionVariables.UPLOADS));
		form.setUrlConsejeria		(propsMap.get(InstalacionVariables.URLCONSEJERIA));
		form.setUsercnprefix		(propsMap.get(InstalacionVariables.USERCNPREFIX));

		propiedades = InstalacionVariables.ordenaAlfabeticamentePropiedades(propiedades);		
		propiedades = reemplazarPuntosPorBarrasBajas(propiedades);
		form.setListaVariablesAsArray(propiedades);
	}

	
	private boolean esValido (String s) {
		if(s==null || s.equals(""))	return false;
		return true;
	}
	
	
	private boolean esUnPuerto (String port) {
		if(!esValido(port))	return false;
		int puerto;
		try {
			puerto = Integer.valueOf(port);
		} catch (Exception e) {
			return false;
		}
		if(puerto>19 && puerto<65537) 
			return true;
		return false;
	}
	
	
	private boolean esUnPath (String path) {
		if(!esValido(path))	return false;
		if(path.contains(File.separator)) return true;
		return false;
	}
	
	
	private boolean esUnPathAbsoluto (String path) {
		if(!esUnPath(path)) return false;
		if(path.startsWith(File.separator)) return true;
		return false;
	}
	
	
	private boolean esUnMail (String mail) {
		if(!esValido(mail))	return false;
		if(mail.contains("@")) {
			String[] email = mail.split("@");
			if(email.length!=2) return false;
			if(!email[1].contains(".")) return false;
			String[] emailDomain = email[1].split("\\.");
			if(emailDomain.length<2) return false;
			return true;
		}
		return false;
	}
	
	
	private boolean esUnBooleano (String bool) {
		if(!esValido(bool)) return false;
		if(bool.equalsIgnoreCase("true") || bool.equalsIgnoreCase("false"))
			return true;
		return false;
	}

	
	private boolean tieneCaracteresRaros (String s) {
		if(!esValido(s)) return true;
		if(
				s.equalsIgnoreCase(" ") || 
				s.equalsIgnoreCase("ñ") ||
				s.equalsIgnoreCase("¡") ||
				s.equalsIgnoreCase("¿") ||
				s.equalsIgnoreCase("¨") ||
				s.equalsIgnoreCase("ç") ||
				s.equalsIgnoreCase("º") ||
				s.equalsIgnoreCase("ª") ||
				s.equalsIgnoreCase("\\") ||
				s.equalsIgnoreCase("!") ||
				s.equalsIgnoreCase("?") ||
				s.equalsIgnoreCase("|") ||
				s.equalsIgnoreCase("\"") ||
				s.equalsIgnoreCase("@") ||
				s.equalsIgnoreCase("·") ||
				s.equalsIgnoreCase("#") ||
				s.equalsIgnoreCase("$") ||
				s.equalsIgnoreCase("~") ||
				s.equalsIgnoreCase("%") ||
				s.equalsIgnoreCase("€") ||
				s.equalsIgnoreCase("&") ||
				s.equalsIgnoreCase("¬") ||
				s.equalsIgnoreCase("/") ||
				s.equalsIgnoreCase("(") ||
				s.equalsIgnoreCase(")") ||
				s.equalsIgnoreCase("=") ||
				s.equalsIgnoreCase("^") ||
				s.equalsIgnoreCase("*") ||
				s.equalsIgnoreCase("[") ||
				s.equalsIgnoreCase("]") ||
				s.equalsIgnoreCase("`") ||
				s.equalsIgnoreCase("+") ||
				s.equalsIgnoreCase("´") ||
				s.equalsIgnoreCase("{") ||
				s.equalsIgnoreCase("}") ||
				s.equalsIgnoreCase(";") ||
				s.equalsIgnoreCase(",") ||
				s.equalsIgnoreCase(":")
			) return true;
		return false;
	}
	
	
	//La variable UPLOADS no debe ser un path con un mínimo de una carpeta 
	//de profundidad. En otras palabras, la ruta especificada no puede 
	//ser el directorio raiz de un disco. Esto es debido a que el path
	//GaleriaImgPath sera la carpeta padre de UPLOADS.
	private boolean uploadsValido (String uploads) {
		if(!esUnPathAbsoluto(uploads)) return false;
		String expr = File.separator;
		if(File.separator.equals("\\"))
			expr = "\\"+File.separator;
		String[] up = uploads.split(expr);
		if(up==null || up.length<1) return false;
		return true;
	}	
	
	
	//La variable GaleriaImgPath es la carpeta que contiene el path UPLOADS
	private String calculateGaleriaImgPath (String uploads) {
		if(!uploadsValido(uploads)) return File.separator;
		String expr = File.separator;
		if(File.separator.equals("\\"))
			expr = "\\"+File.separator;
		String[] path = uploads.split(expr);

		if(path.length<=1) return File.separator;
		String galeriaImgPath = "";
		for(int i=0; i<=path.length-2; i++) {
			if (!path[i].equals(""))
				galeriaImgPath=galeriaImgPath+File.separator+path[i];
		}
		return galeriaImgPath;
	}
	
	
	private boolean isPrefijoValido (String prefijo) {
		if(!esValido(prefijo))
			return false;
		if(prefijo.equals("MEC") ||
				prefijo.equals("AN") ||
				prefijo.equals("AR") ||
				prefijo.equals("AS") ||
				prefijo.equals("IB") ||
				prefijo.equals("IC") ||
				prefijo.equals("CB") ||
				prefijo.equals("CM") ||
				prefijo.equals("CL") ||
				prefijo.equals("CT") ||
				prefijo.equals("EU") ||
				prefijo.equals("EX") ||
				prefijo.equals("GA") ||
				prefijo.equals("LR") ||
				prefijo.equals("MA") ||
				prefijo.equals("MU") ||
				prefijo.equals("NA") ||
				prefijo.equals("CV"))
			return true;
		return false;
	}
		

	@Override
	public void modificarVariablesConfiguracion(ActionMapping mapping,
			ModificarVariablesConfiguracionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tmp;
		form.setOperacionSatisfactoria(true);	
		ArrayList<PropiedadVO> props = new ArrayList<PropiedadVO>();

		tmp=form.getJBOSS_HOME();
		if(!esUnPathAbsoluto(tmp)) {
			form.setOperacionSatisfactoria(false);		
			throw new ValidatorException("{error.jboss_home}");			
		}
		PropiedadVO p7 = new PropiedadVO();
		p7.setValor(tmp);
		p7.setNombre(InstalacionVariables.JBOSS_HOME);
		props.add(p7);
		
		tmp=form.getUPLOADS();
		if(!esUnPathAbsoluto(tmp)) {
			form.setOperacionSatisfactoria(false);		
			throw new ValidatorException("{error.uploads}");			
		}
		PropiedadVO p38 = new PropiedadVO();
		p38.setValor(tmp);
		p38.setNombre(InstalacionVariables.UPLOADS);
		props.add(p38);
		
		//GaleriaImgPath se aujsta en funcion del parametro UPLOADS
		tmp=calculateGaleriaImgPath(form.getUPLOADS());	
		form.setGaleriaImgPath(tmp);
/*		if(!esUnPathAbsoluto(tmp)) {
			form.setOperacionSatisfactoria(false);		
			throw new ValidatorException("{error.galeriaImgPath}");		
		}
*/		PropiedadVO p3 = new PropiedadVO();
		p3.setValor(tmp);
		p3.setNombre(InstalacionVariables.GALERIAIMGPATH);
		props.add(p3);
		
		tmp=form.getCasurl();
		if(!esValido(tmp)) {
			form.setOperacionSatisfactoria(false);	
			throw new ValidatorException("{error.casurl}");	
		}
		PropiedadVO p1 = new PropiedadVO();
		p1.setValor(tmp);
		p1.setNombre(InstalacionVariables.CASURL);
		props.add(p1);
		
		tmp=form.getCcaa();
		if(!esValido(tmp)) {	
			form.setOperacionSatisfactoria(false);
			throw new ValidatorException("{error.ccaa}");	
		}
		PropiedadVO p2 = new PropiedadVO();
		p2.setValor(tmp);
		p2.setNombre(InstalacionVariables.CCAA);
		props.add(p2);
				
		tmp=form.getIdnodo();
		if(!esValido(tmp)) {
			form.setOperacionSatisfactoria(false);		
			throw new ValidatorException("{error.idnodo}");			
		}
		PropiedadVO p4 = new PropiedadVO();
		p4.setValor(tmp);
		p4.setNombre(InstalacionVariables.IDNODO);
		props.add(p4);
/*				
		tmp=form.getImgserverhost();
		if(!esValido(tmp)) {
			form.setOperacionSatisfactoria(false);		
			throw new ValidatorException("{error.imgserverhost}");			
		}
		PropiedadVO p5 = new PropiedadVO();
		p5.setValor(tmp);
		p5.setNombre(InstalacionVariables.IMGSERVERHOST);
		props.add(p5);
				
		tmp=form.getImgserverport();
		if(!esUnPuerto(tmp)) {
			form.setOperacionSatisfactoria(false);		
			throw new ValidatorException("{error.imgserverport}");			
		}
		PropiedadVO p6 = new PropiedadVO();
		p6.setValor(tmp);
		p6.setNombre(InstalacionVariables.IMGSERVERPORT);
		props.add(p6);
*/				
		tmp=form.getLdapadmin();
		if(!esUnMail(tmp)) {
			form.setOperacionSatisfactoria(false);		
			throw new ValidatorException("{error.ldapadmin}");			
		}
		PropiedadVO p8 = new PropiedadVO();
		p8.setValor(tmp);
		p8.setNombre(InstalacionVariables.LDAPADMIN);
		props.add(p8);
				
		tmp=form.getLdapexternal();
		if(!esUnBooleano(tmp)) {
			form.setOperacionSatisfactoria(false);		
			throw new ValidatorException("{error.ldapexternal}");			
		}
		PropiedadVO p9 = new PropiedadVO();
		p9.setValor(tmp);
		p9.setNombre(InstalacionVariables.LDAPEXTERNAL);
		props.add(p9);
				
		tmp=form.getLdapmanagerDN();
		if(!esValido(tmp)) {
			form.setOperacionSatisfactoria(false);		
			throw new ValidatorException("{error.ldapmanagerDN}");			
		}
		PropiedadVO p10 = new PropiedadVO();
		p10.setValor(tmp);
		p10.setNombre(InstalacionVariables.LDAPMANAGERDN);
		props.add(p10);
				
		tmp=form.getLdapmanagerPasswd();
		if(!esValido(tmp)) {
			form.setOperacionSatisfactoria(false);		
			throw new ValidatorException("{error.ldapmanagerPasswd}");			
		}
		PropiedadVO p11 = new PropiedadVO();
		p11.setValor(tmp);
		p11.setNombre(InstalacionVariables.LDAPMANAGERPASSWD);
		props.add(p11);
		
		tmp=form.getLdappath();
		//Esto puede ser vacio
		/*
		if(!esValido(tmp)) {
			form.setOperacionSatisfactoria(false);		
			throw new ValidatorException("{error.ldappath}");			
		}
		*/
		PropiedadVO p12 = new PropiedadVO();
		p12.setValor(tmp);
		p12.setNombre(InstalacionVariables.LDAPPATH);
		props.add(p12);
				
		tmp=form.getLdapurl();
		if(!esValido(tmp)) {
			form.setOperacionSatisfactoria(false);		
			throw new ValidatorException("{error.ldapurl}");			
		}
		PropiedadVO p13 = new PropiedadVO();
		p13.setValor(tmp);
		p13.setNombre(InstalacionVariables.LDAPURL);
		props.add(p13);
				
		tmp=form.getLdapusuariosbindsubpath();
		if(!esValido(tmp)) {
			form.setOperacionSatisfactoria(false);		
			throw new ValidatorException("{error.ldapusuariosbindsubpath}");			
		}
		PropiedadVO p14 = new PropiedadVO();
		p14.setValor(tmp);
		p14.setNombre(InstalacionVariables.LDAPUSUARIOSBINDSUBPATH);
		props.add(p14);
				
		tmp=form.getMailContacto();
		if(!esUnMail(tmp)) {
			form.setOperacionSatisfactoria(false);		
			throw new ValidatorException("{error.mailContacto}");			
		}
		PropiedadVO p15 = new PropiedadVO();
		p15.setValor(tmp);
		p15.setNombre(InstalacionVariables.MAILCONTACTO);
		props.add(p15);
				
		tmp=form.getNodo();	
		if(!esValido(tmp)) {
			form.setOperacionSatisfactoria(false);		
			throw new ValidatorException("{error.nodo}");			
		}
		PropiedadVO p16 = new PropiedadVO();
		p16.setValor(tmp);
		p16.setNombre(InstalacionVariables.NODO);
		props.add(p16);
				
		tmp=form.getNodo_jboss();
		if(!esValido(tmp)) {
			form.setOperacionSatisfactoria(false);		
			throw new ValidatorException("{error.nodo_jboss}");			
		}
		PropiedadVO p17 = new PropiedadVO();
		p17.setValor(tmp);
		p17.setNombre(InstalacionVariables.NODO_JBOSS);
		props.add(p17);
				
		tmp=form.getPort();
		if(!esUnPuerto(tmp)) {
			form.setOperacionSatisfactoria(false);		
			throw new ValidatorException("{error.port}");			
		}
		PropiedadVO p18 = new PropiedadVO();
		p18.setValor(tmp);
		p18.setNombre(InstalacionVariables.PORT);
		props.add(p18);
				
		tmp=form.getPort_jboss();
		if(!esUnPuerto(tmp)) {
			form.setOperacionSatisfactoria(false);		
			throw new ValidatorException("{error.port_jboss}");			
		}
		PropiedadVO p19 = new PropiedadVO();
		p19.setValor(tmp);
		p19.setNombre(InstalacionVariables.PORT_JBOSS);
		props.add(p19);
				
		tmp=form.getPrefijo_nodo();			
		if(!isPrefijoValido(tmp)) {
			form.setOperacionSatisfactoria(false);		
			throw new ValidatorException("{error.prefijo_nodo}");			
		}
		PropiedadVO p20 = new PropiedadVO();
		p20.setValor(tmp);
		p20.setNombre(InstalacionVariables.PREFIJO_NODO);
		props.add(p20);
				
		tmp=form.getProxy();
		if(!esUnBooleano(tmp)) {
			form.setOperacionSatisfactoria(false);		
			throw new ValidatorException("{error.proxy}");			
		}
		PropiedadVO p21 = new PropiedadVO();
		p21.setValor(tmp);
		p21.setNombre(InstalacionVariables.PROXY);
		props.add(p21);
				
		tmp=form.getProxyhost();
		//si el proxy es false -> el host y port pueden ir en blanco	
		if(form.getProxy().equalsIgnoreCase("true") && !esValido(tmp)) {
			form.setOperacionSatisfactoria(false);		
			throw new ValidatorException("{error.proxyhost}");			
		}
		PropiedadVO p22 = new PropiedadVO();
		p22.setValor(tmp);
		p22.setNombre(InstalacionVariables.PROXYHOST);
		props.add(p22);
				
		tmp=form.getProxypasswd();
		//proxy user y pass pueden ir en blanco aunque se use proxy	
		PropiedadVO p23 = new PropiedadVO();
		p23.setValor(tmp);
		p23.setNombre(InstalacionVariables.PROXYPASSWD);
		props.add(p23);
				
		tmp=form.getProxyport();
		//si el proxy es false -> el host y port pueden ir en blanco	
		if(form.getProxy().equalsIgnoreCase("true") && !esUnPuerto(tmp)) {
			form.setOperacionSatisfactoria(false);		
			throw new ValidatorException("{error.proxyport}");			
		}
		PropiedadVO p24 = new PropiedadVO();
		p24.setValor(tmp);
		p24.setNombre(InstalacionVariables.PROXYPORT);
		props.add(p24);
				
		tmp=form.getProxyuser();
		//proxy user y pass pueden ir en blanco aunque se use proxy	
		PropiedadVO p25 = new PropiedadVO();
		p25.setValor(tmp);
		p25.setNombre(InstalacionVariables.PROXYUSER);
		props.add(p25);
				
		tmp=form.getRolebindsubpath();
		if(!esValido(tmp)) {
			form.setOperacionSatisfactoria(false);	
			throw new ValidatorException("{error.rolebindsubpath}");				
		}
		PropiedadVO p26 = new PropiedadVO();
		p26.setValor(tmp);
		p26.setNombre(InstalacionVariables.ROLEBINDSUBPATH);
		props.add(p26);
				
		tmp=form.getRutalogs();
		if(!esUnPath(tmp)) {
			form.setOperacionSatisfactoria(false);		
			throw new ValidatorException("{error.rutalogs}");			
		}
		PropiedadVO p27 = new PropiedadVO();
		p27.setValor(tmp);
		p27.setNombre(InstalacionVariables.RUTALOGS);
		props.add(p27);

		//el p28 no esta, asi que podria usarlo para meter variables nuevas		
		
		tmp=form.getServeron();
		//if(tieneCaracteresRaros(tmp)) { 
		if(!esValido(tmp)) {
			form.setOperacionSatisfactoria(false);		
			throw new ValidatorException("{error.serveron}");			
		}
		PropiedadVO p29 = new PropiedadVO();
		p29.setValor(tmp);
		p29.setNombre(InstalacionVariables.SERVERON);
		props.add(p29);
				
		tmp=form.getSmtpauthentication(); 
		if(!esUnBooleano(tmp)) {
			form.setOperacionSatisfactoria(false);		
			throw new ValidatorException("{error.smtpauthentication}");			
		}
		PropiedadVO p30 = new PropiedadVO();
		p30.setValor(tmp);
		p30.setNombre(InstalacionVariables.SMTPAUTHENTICATION);
		props.add(p30);
				
		tmp=form.getSmtphost();
		if(!esValido(tmp)) {
			form.setOperacionSatisfactoria(false);		
			throw new ValidatorException("{error.smtphost}");			
		}
		PropiedadVO p31 = new PropiedadVO();
		p31.setValor(tmp);
		p31.setNombre(InstalacionVariables.SMTPHOST);
		props.add(p31);
				
		tmp=form.getSmtppasswd();
		//si el stmpautentication es false -> el user y pass pueden ir en blanco	
		if(form.getSmtpauthentication().equalsIgnoreCase("true") && !esValido(tmp)) {
			form.setOperacionSatisfactoria(false);	
			throw new ValidatorException("{error.smtppasswd}");				
		}
		PropiedadVO p32 = new PropiedadVO();
		p32.setValor(tmp);
		p32.setNombre(InstalacionVariables.SMTPPASSWD);
		props.add(p32);
				
		tmp=form.getSmtpsender();
		if(!esUnMail(tmp)) {
			form.setOperacionSatisfactoria(false);		
			throw new ValidatorException("{error.smtpsender}");			
		}
		PropiedadVO p33 = new PropiedadVO();
		p33.setValor(tmp);
		p33.setNombre(InstalacionVariables.SMTPSENDER);
		props.add(p33);
				
		tmp=form.getSmtpuser();
		//si el stmpautentication es false -> el user y pass pueden ir en blanco	
		if(form.getSmtpauthentication().equalsIgnoreCase("true") && !esValido(tmp)) {
			form.setOperacionSatisfactoria(false);	
			throw new ValidatorException("{error.smtpuser}");				
		}
		PropiedadVO p34 = new PropiedadVO();
		p34.setValor(tmp);
		p34.setNombre(InstalacionVariables.SMTPUSER);
		props.add(p34);
				
		tmp=form.getSubdomain();
		PropiedadVO p35 = new PropiedadVO();
		p35.setValor(tmp);
		p35.setNombre(InstalacionVariables.SUBDOMAIN);
		props.add(p35);
				
		tmp=form.getSubdomain_jboss();
		PropiedadVO p36 = new PropiedadVO();
		p36.setValor(tmp);
		p36.setNombre(InstalacionVariables.SUBDOMAIN_JBOSS);
		props.add(p36);
				
		tmp=form.getTelefonoContacto();
		if(!esValido(tmp)) {
			form.setOperacionSatisfactoria(false);	
			throw new ValidatorException("{error.telefonoContacto}");				
		}
		PropiedadVO p37 = new PropiedadVO();
		p37.setValor(tmp);
		p37.setNombre(InstalacionVariables.TELEFONOCONTACTO);
		props.add(p37);

		tmp=form.getUrlConsejeria();
		if(!esValido(tmp)) {
			form.setOperacionSatisfactoria(false);		
			throw new ValidatorException("{error.urlConsejeria}");			
		}
		PropiedadVO p39 = new PropiedadVO();
		p39.setValor(tmp);
		p39.setNombre(InstalacionVariables.URLCONSEJERIA);
		props.add(p39);
				
		tmp=form.getUsercnprefix();
		if(!esValido(tmp)) {
			form.setOperacionSatisfactoria(false);		
			throw new ValidatorException("{error.usercnprefix}");			
		}	
		PropiedadVO p40 = new PropiedadVO();
		p40.setValor(tmp);
		p40.setNombre(InstalacionVariables.USERCNPREFIX);
		props.add(p40);

//TODO comprobar que existe el symlink de JBOSS_HOME/UPLOADS 
		
	  	try {
	  		this.getSrvConfiguracionService().updatePropiedades(props.toArray(new PropiedadVO[0]), form.getTipoJboss());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR: hubo un error al realizar la actualizacion/instalacion de variables y ficheros de configuracion");
			e.printStackTrace();
			form.setOperacionSatisfactoria(false);	
		}
	}

}