package es.pode.soporte.constantes;

/** Fichero de constantes de desarrollo de la plataforma AGREGA */ 
public interface ConstantesAgrega {
	public final static String TRABAJO_ERRONEO = "ERROR";
	public final static String TRABAJO_CORRECTO = "OK";
	public final static String TRABAJO_INTERRUMPIDO = "INTERRUMPIDO";
	public final static String NO_PERIODICA = "N";
	public final static String PERIODICA = "P";
    public final static String HORARIA = "H";
	public final static String DIARIA = "D";
	public final static String SEMANAL = "S";
	public final static String MENSUAL = "M";
	public final static String ANUAL = "A";
	public final static String GRUPO_MODIFICADOR = "GrupoModificador";
	public final static String TRIGGER_MODIFICADOR = "TriggerModificador_";
	public final static String NOMBRE_MODIFICADOR = "Modificacion_";
	public final static String LISTA_IDIOMAS_BUSCABLES = "idiomas.buscables";
	public final static String LISTA_IDIOMAS_PLATAFORMA = "idiomas.plataforma";
	public final static String LISTA_IDIOMAS_ESTRUCTURAS_EDUCATIVAS = "idiomas.estructuras.educativas";
	public final static String IDIOMA_DEFECTO_PLATAFORMA = "idioma.por.defecto";
	public final static String IDIOMA_SECUNDARIO_PLATAFORMA = "idioma.secundario";
	public final static String LISTA_IDIOMAS_TRADUCIBLES = "idiomas.traducibles";
	public final static String AMBITO_GENERAL = "MEC"; // identificador del nodo general
	public final static String AMBITO_NODO = "nodo";  // constante para acceder al identificador del nodo al que pertenezco
	public final static String DEFAULT_LOCALE = "org.apache.struts.action.LOCALE";
	public final static String APPLICATION_PROPERTIES = "application-resources";
	public final static String FICHERO = "FICHERO";
	public final static String ARCHIVO = "ARCHIVO";
	public final static String RCP = "RCP";
	public final static String CA = "CA";
	public final static String ODE_NO_VALIDO = "ODE_NO_VALIDO";
	public final static String GRUPO_TRABAJO_MODIFICADOR = "GrupoTrabajoModificador";
	public final static String GRUPO_TRIGGER_MODIFICADOR = "GrupoTriggerModificador";
	
	//Constantes para conocer el tipo de Ode (utilizadas en validador, entregar)
	public static final String SCORM_04 = "SCORM_2004";
    public static final String SCORM_12 = "SCORM_12";
    public static final String IMS_CP = "IMS_CP";
	
	//Nueva constante para el planificador, este mensaje es el utilizado para aquellas tareas que no se pueden lanzar por estar ejecutándose otras
	//tareas incompatibles con ella
	public final static String TRABAJO_INCOMPATIBLE = "No se ha podido lanzar la tarea por haber otra tarea incompatible ejecutándose";
}
