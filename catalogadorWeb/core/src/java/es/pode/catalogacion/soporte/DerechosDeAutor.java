package es.pode.catalogacion.soporte;

import java.io.Serializable;


public class DerechosDeAutor implements Serializable{
	
	//Todos estos valores se podr�an recoger de un properties en vez
	//de tenerlos como constantes.
	public static final String  LIC_PROPIETARIA	= "6.2.1";//licencia propietaria
	public static final String  LIC_LIBRE_EUPL	= "6.2.2";//licencia libre EUPL
	public static final String  LIC_LIBRE_GPL	= "6.2.3";//licencia libre GPL
	public static final String  LIC_LIBRE_GPL_EUPL	= "6.2.4";//licencia libre dual GPL y EUPL
	public static final String  OTRAS_LIC_LIBRES = "6.2.5";//otras licencias libres
	public static final String  DOMINIO_PUBLICO	= "6.2.6";//dominio p�blico
	public static final String  NO_CORRESPONDE	= "6.2.7";//no corresponde
	public static final String  LIC_PROPIETARIA_INTELEC	= "6.2.8";//licencia propietaria intelectual
	public static final String  RECONOCIMIENTO	= "6.2.9";//creative commons: reconocimiento
	public static final String  RECONO_SIN_OBRA_DERIVADA  = "6.2.10";//creative commons: reconocimiento - sin obra derivada
	public static final String  RECONO_SIN_OBRA_DER_NO_COMERCIAL  = "6.2.11";//creative commons: reconocimiento - sin obra derivada - no comercial
	public static final String  RECONO_NO_COMERCIAL  = "6.2.12";//creative commons: reconocimiento - no comercial
	public static final String  RECONO_NO_COM_COMPARTIR_IGUAL  = "6.2.13";//creative commons: reconocimiento - no comercial - compartir igual
	public static final String  RECONO_COMPARTIR_IGUAL  = "6.2.14";//creative commons: reconocimiento - compartir igual
	public static final String  LIC_GFDL	= "6.2.15";//licencia GFDL
	
	public String[] dameDerechosAplicacionInformatica(){
		String[] derechos ={LIC_PROPIETARIA, LIC_LIBRE_EUPL, LIC_LIBRE_GPL, LIC_LIBRE_GPL_EUPL, 
				 			OTRAS_LIC_LIBRES, DOMINIO_PUBLICO}; 
		return derechos;
	}
	public String[] dameDerechosServicio(){
		String[] derechos ={NO_CORRESPONDE}; 
		return derechos;
	}
	
	public String[] dameDerechosNoServicioNoAplicacionInformatica(){
		String[] derechos ={LIC_PROPIETARIA_INTELEC, RECONOCIMIENTO, RECONO_SIN_OBRA_DERIVADA,
		RECONO_SIN_OBRA_DER_NO_COMERCIAL, RECONO_NO_COMERCIAL, RECONO_NO_COM_COMPARTIR_IGUAL,
		RECONO_COMPARTIR_IGUAL,	LIC_GFDL, DOMINIO_PUBLICO}; 
		return derechos;
	}
	public String[] dameDerechosTodos(){
		String[] derechos ={LIC_PROPIETARIA, LIC_LIBRE_EUPL, LIC_LIBRE_GPL, LIC_LIBRE_GPL_EUPL, 
	 			OTRAS_LIC_LIBRES, DOMINIO_PUBLICO,LIC_PROPIETARIA_INTELEC, RECONOCIMIENTO, 
	 			RECONO_SIN_OBRA_DERIVADA, RECONO_SIN_OBRA_DER_NO_COMERCIAL, RECONO_NO_COMERCIAL, 
	 			RECONO_NO_COM_COMPARTIR_IGUAL, RECONO_COMPARTIR_IGUAL, LIC_GFDL, DOMINIO_PUBLICO}; 
		return derechos;
	}
}
