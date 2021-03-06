/**
 * SrvAuditoriaServicio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.auditoria.negocio.servicios;

public interface SrvAuditoriaServicio extends java.rmi.Remote {

    /**

     */
    public void crearGuardarInforme(es.pode.auditoria.negocio.servicios.ParametroCrearInformeVO parametroCrearInformeVO) throws java.rmi.RemoteException;

    /**

     */
    public javax.activation.DataHandler crearInforme(es.pode.auditoria.negocio.servicios.ParametroCrearInformeVO parametrosCrearInforme) throws java.rmi.RemoteException;

    /**
     * Genera un informe con toda la información del repositorio.
     * Será
     *                 invocado a través de una tarea programada
     */
    public void crearInformeRepositorio() throws java.rmi.RemoteException;

    /**
     * Obtiene los informes de tipo 'Mas' que aparecerán en la portada.
     * Será el método que se invocará desde la tarea planificada
     */
    public void crearInformesPortada() throws java.rmi.RemoteException;

    /**
     * Método que obtiene un VO con toda la información necesaria
     * para
     *                 obtener el informe de odes por cobertura curricular
     */
    public es.pode.auditoria.negocio.servicios.InformeCoberturaCurricularVO[] informeCoberturaCurricular(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametrosInformeVO) throws java.rmi.RemoteException;

    /**
     * Método que devuelve un array de objetos InformeEstadoVO con
     * el
     *                 número de odes que se encuentran en cada uno de los
     * estados
     *                 posibles.
     */
    public es.pode.auditoria.negocio.servicios.InformeEstadoOdesVO[] informeEstadoOdes(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametrosInformeVO) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.auditoria.negocio.servicios.InformeEstadoOdesVO[] informeEstadoOdesTransicciones(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametrosInformeVO) throws java.rmi.RemoteException;

    /**
     * Método que devuelve un array de InformeLicenciaOdesVO con el
     * número de odes que hay por licencia para un rango de fechas en
     *                 concreto
     */
    public es.pode.auditoria.negocio.servicios.InformeLicenciasOdesVO[] informeLicenciasOdes(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametrosInformeVO) throws java.rmi.RemoteException;

    /**
     * Devuelve un VO con el número de odes que se le pasa por
     *                 parámetro más descargados
     */
    public es.pode.auditoria.negocio.servicios.InformeMasVO[] informeMasDescargados(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametrosInformeVO) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.auditoria.negocio.servicios.InformeMasVO[] informeMasEnviado(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametrosInformeVO) throws java.rmi.RemoteException;

    /**
     * Devuelve un VO con el número de odes que se le pasa por
     *                 parámetro más mostrados
     */
    public es.pode.auditoria.negocio.servicios.InformeMasVO[] informeMasMostrado(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametrosInformeVO) throws java.rmi.RemoteException;

    /**
     * Devuelve un array de InformeMasPesadosVO con los odes más
     *                 pesados.
     */
    public es.pode.auditoria.negocio.servicios.InformeMasPesadosVO[] informeMasPesados(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametrosInformeVO) throws java.rmi.RemoteException;

    /**
     * Devuelve un VO con el número de odes que se le pasa por
     *                 parámetro más previsualizados
     */
    public es.pode.auditoria.negocio.servicios.InformeMasVO[] informeMasPrevisualizados(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametrosInformeVO) throws java.rmi.RemoteException;

    /**
     * Método que devuelve un array de InformeMasValoradoVO con los
     * odes más valorados. El número de odes que devolverá este métodos
     *                 dependerá de lo que se le pase por parámetro
     */
    public es.pode.auditoria.negocio.servicios.InformeMasValoradoVO[] informeMasValorado(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametrosInformeVO) throws java.rmi.RemoteException;

    /**
     * Devuelve un VO con el número de odes que se le pasa por
     *                 parámetro más visualizados
     */
    public es.pode.auditoria.negocio.servicios.InformeMasVO[] informeMasVisualizados(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametrosInformeVO) throws java.rmi.RemoteException;

    /**
     * Obtiene un VO con la información del informe de odes por nivel
     * de agregación entre dos fechas concretas, estas fechas serán las
     *                 fechas de publicación de los odes.
     */
    public es.pode.auditoria.negocio.servicios.InformeNivelAgregacionVO[] informeNivelAgregacion(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametrosInformeVO) throws java.rmi.RemoteException;

    /**
     * Método que devuelve las operaciones que ha realizado un usuario
     * entre dos fechas concretas
     */
    public es.pode.auditoria.negocio.servicios.InformeOdeUsuarioVO[] informeOdeUsuario(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametroInformeVO) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.auditoria.negocio.servicios.InformeOdeUsuarioVO[] informeOdeUsuarioOperacion(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametrosInformeVO) throws java.rmi.RemoteException;

    /**
     * Obtiene un VO con las operaciones realizadas (previsualizar,
     * visualizar, descargar,....) entre una fecha de inicio y una
     *                 fecha de fin
     */
    public es.pode.auditoria.negocio.servicios.InformeOperacionVO[] informeOperacionesRealizadas(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametroInformeVO) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.auditoria.negocio.servicios.InformeOperacionUsuarioVO[] informeOperacionUsuario(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametrosInformeVO) throws java.rmi.RemoteException;

    /**
     * Método que devuelve un array de InformeProcesoPlanificado con
     * los procesos planificados entre un rango de fechas concretos
     */
    public es.pode.auditoria.negocio.servicios.InformeProcesoPlanificadoVO[] informeProcesosPlanificados(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametrosInformeVO) throws java.rmi.RemoteException;

    /**
     * Método que devuelve un VO con toda la información necesaria
     * para
     *                 obtener el informe de términos buscados
     */
    public es.pode.auditoria.negocio.servicios.InformeTerminoBusquedaVO[] informeTerminosBusqueda(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametrosInformeVO) throws java.rmi.RemoteException;

    /**
     * Método que devuelve un array de InformeUsuariosVO con todos
     * los
     *                 datos de los usuarios activos entre un rango de fechas
     * concreto
     */
    public es.pode.auditoria.negocio.servicios.InformeUsuariosVO[] informeUsuarios(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametroInformeVO) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.String[] obtenerInformes() throws java.rmi.RemoteException;

    /**
     * Nos devuelve un array de numeroOperacionesVO
     */
    public es.pode.auditoria.negocio.servicios.NumeroOperacionesVO[] obtenNumeroOperaciones(java.lang.String identificador) throws java.rmi.RemoteException;

    /**
     * Método que almacena en BD los términos que han sido buscados
     * así
     *                 como el ámbito y el usuario que realiza la búsqueda
     */
    public void registrarBusqueda(es.pode.auditoria.negocio.servicios.BusquedaVO busqueda) throws java.rmi.RemoteException;

    /**
     * Almacena en BD las distintas operaciones que se van realizando
     * (previsualizar, descargar, visualizar....) en la plataforma
     */
    public void registrarOperacion(es.pode.auditoria.negocio.servicios.OperacionVO operacion) throws java.rmi.RemoteException;

    /**
     * Registro de la fecha de finalización de un trabajo ejecutado
     */
    public java.lang.Long registrarTrabajoFechaFinPlan(es.pode.auditoria.negocio.servicios.TareaEjecutadaPlanVO trabajo) throws java.rmi.RemoteException;

    /**
     * Regostro de la subtarea de una tarea
     */
    public java.lang.Long registrarTrabajoHijoPlan(es.pode.auditoria.negocio.servicios.RegistroTareaEjecPlanVO trabajoHijo) throws java.rmi.RemoteException;

    /**
     * Se registra el trabajo que se ha interrumpido
     */
    public java.lang.Long registrarTrabajoInterrPlan() throws java.rmi.RemoteException;

    /**
     * Registro de un trabajo planificado
     */
    public java.lang.Long registrarTrabajoPlan(es.pode.auditoria.negocio.servicios.TareaEjecutadaPlanVO trabajo) throws java.rmi.RemoteException;

    /**
     * Obtiene todo el contenido del repositorio
     */
    public es.pode.auditoria.negocio.servicios.RepositorioVO[] repositorio() throws java.rmi.RemoteException;
}
