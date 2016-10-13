/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvGestorManifestService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.empaquetador.negocio.servicio;

public interface SrvGestorManifestService extends java.rmi.Remote {

    /**
     * Agrega un ODE del repositorio como submanifiesto del ODE actual.
     */
    public java.lang.String agregarManifiestoRepositorio(java.lang.String identificador, java.lang.String localizador, java.lang.String submanifestId) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.String agregarManifiestoZIP(java.lang.String identificador, javax.activation.DataHandler paqueteSCORM, java.lang.String submanifestId) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.String agregarManifiestoZIPByte(java.lang.String identificador, byte[] paqueteSCORM, java.lang.String submanifestId) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.String agregarManifiestoZipUrl(java.lang.String identificador, java.lang.String fichero, java.lang.String submanifestId) throws java.rmi.RemoteException;

    /**

     */
    public void asociarDatosItem(java.lang.String identificador, java.lang.String idElemento, es.pode.empaquetador.negocio.servicio.TimeLimitActionVO timeLimitAction, es.pode.empaquetador.negocio.servicio.DataFromLMSVO dataFromLMS, es.pode.empaquetador.negocio.servicio.CompletionThresholdVO completionThreshold, es.pode.empaquetador.negocio.servicio.PresentationVO presentation) throws java.rmi.RemoteException;

    /**
     * Llama al servicio de catalogación para recuperar (y eliminar
     * de
     *                 su cache) el objeto Lom asociado al identificador
     * del objeto
     *                 deseado. Si el objeto Lom esta vacío, no se asocia
     * al objeto.
     *                 Los cambios se almacenan en la cacheEmpaquetacion.
     */
    public void asociarLom(java.lang.String identificador, java.lang.String idElemento, java.lang.String href) throws java.rmi.RemoteException;

    /**
     * Comprueba si el elemento idElemento tiene secuencia asociada
     * y
     *                 mapea los cambios a su ControlMode (si la secuencia
     * existía) o
     *                 crea una nueva con los datos proporcionados (en caso
     * de que no
     *                 existiera).
     */
    public java.lang.String asociarSecuencia(java.lang.String identificador, java.lang.String idElemento, es.pode.empaquetador.negocio.servicio.ControlModeVO controlMode) throws java.rmi.RemoteException;

    /**
     * Mueve a una posicion superior en el array el Item solicitado.
     */
    public java.lang.String bajarItem(java.lang.String identificador, java.lang.String idItem) throws java.rmi.RemoteException;

    /**
     * Baja la organización indicada
     */
    public java.lang.String bajarOrganization(java.lang.String identificador, java.lang.String idOrganization) throws java.rmi.RemoteException;

    /**
     * Carga un objeto manifests existente en la cache para su edición
     * desde el Empaquetador.
     */
    public es.pode.empaquetador.negocio.servicio.OdeVO cargarManifest(java.lang.String identificador) throws java.rmi.RemoteException;

    /**
     * Copia los elementos indicados bajo el elemento destino
     */
    public void copiarElementos(java.lang.String identificador, java.lang.String[] elementos, java.lang.String destino) throws java.rmi.RemoteException;

    /**
     * Crea un objeto Item bajo la Organization / Item deseada a partir
     * del Grupo
     */
    public java.lang.String crearGrupo(java.lang.String identificador, es.pode.empaquetador.negocio.servicio.GrupoVO grupoVO, java.lang.String identificadorPadre) throws java.rmi.RemoteException;

    /**
     * Crea una nueva organizacion para el ODE en edición.
     */
    public java.lang.String crearOrganizacion(java.lang.String identificador, es.pode.empaquetador.negocio.servicio.OrganizacionVO organizacion) throws java.rmi.RemoteException;

    /**
     * Crear un recurso en el objeto manifest.
     */
    public java.lang.String crearRecurso(java.lang.String identificador, es.pode.empaquetador.negocio.servicio.RecursoVO recurso) throws java.rmi.RemoteException;

    /**
     * Extrae el elemento solicitado del Manifesto y lo introduce
     * en la
     *                 cache como referencia para futuras modificaciones
     * (edicion de
     *                 submanifiestos).
     */
    public java.lang.Boolean crearReferenciaEnCache(java.lang.String identificador, java.lang.String idElemento) throws java.rmi.RemoteException;

    /**
     * Desagrega un submanifiesto del ODE en que se esta trabajando
     * para que el usuario lo tenga disponible en local. El retorno es
     *                 un paquete SCORM 2004 serializado en un datahandler.
     */
    public javax.activation.DataHandler desagregarSubmanifiestoLocal(java.lang.String identificador, java.lang.String submanifiesto, java.lang.String submanifestId) throws java.rmi.RemoteException;

    /**
     * Desagrega un submanifiesto a la carpeta taller del usuario.
     */
    public void desagregarSubmanifiestoRepositorio(java.lang.String identificador, java.lang.String submanifiestoId, java.lang.String submanifestId) throws java.rmi.RemoteException;

    /**
     * Eliminar de la cache el objeto Manifest.
     */
    public java.lang.Boolean descargarManifest(java.lang.String identificador) throws java.rmi.RemoteException;

    /**
     * Desvincula el Item de su elemento referenciado.
     */
    public java.lang.String desvincularGrupo(java.lang.String identificador, java.lang.String identificadorGrupo) throws java.rmi.RemoteException;

    /**
     * Comienza la edicion de un ODE existente en la carpeta taller
     * del
     *                 usuario. Esta operacion incluye cargar el Manifest
     * en la cache,
     *                 generar un backup de los contenidos previos y registrar
     * los
     *                 objetos Lom asociados al Ode en el servicio de catalogación.
     * El
     *                 resultado es un objeto OdeVO con los datos del ODE
     * a editar.
     */
    public es.pode.empaquetador.negocio.servicio.OdeVO editarODE(java.lang.String identificador) throws java.rmi.RemoteException;

    /**
     * Elimina el objeto Item deseado del ODE en edición.
     */
    public java.lang.String eliminarGrupo(java.lang.String identificador, java.lang.String identificadorGrupo) throws java.rmi.RemoteException;

    /**
     * Elimina fisicamente el ODE (se usara para eliminar aquellos
     * ODEs
     *                 que se cierran sin haber sido guardados por primera
     * vez).
     */
    public void eliminarODE(java.lang.String identificador) throws java.rmi.RemoteException;

    /**
     * Elimina la organizacion del ODE en edición.
     */
    public java.lang.String eliminarOrganizacion(java.lang.String identificador, java.lang.String idOrganizacion) throws java.rmi.RemoteException;

    /**
     * Elimina los recursos solicitados y desvincula los grupos y
     *                 recursos que dependieran de ellos.
     */
    public void eliminarRecursos(java.lang.String identificador, java.lang.String[] recursos) throws java.rmi.RemoteException;

    /**
     * Elimina un submanifiesto del ODE en que se esta trabajando.
     * No
     *                 elimina los archivos, ya que esta funcionalidad se
     * puede
     *                 realizar desde el gestor de archivos.
     */
    public java.lang.String eliminarSubmanifiesto(java.lang.String identificador, java.lang.String submanifiestoId, java.lang.String idSubmanifestPadre) throws java.rmi.RemoteException;

    /**
     * Retorna un paquete con los recursos exportados en formato RCP.
     */
    public javax.activation.DataHandler exportarRecursos(java.lang.String identificador, java.lang.String[] recursos, java.lang.String submanifestId, java.lang.String nombre) throws java.rmi.RemoteException;

    /**
     * Cambia la organizacion por defecto.
     */
    public java.lang.String fijarOrganizacionPorDefecto(java.lang.String identificador, java.lang.String idOrganizacion) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.empaquetador.negocio.servicio.OdeVO generarMetadatoInicial(java.lang.String titulo, java.lang.String idioma, java.lang.String identificador) throws java.rmi.RemoteException;

    /**
     * Genera un imsmanifest.xml a partir del objeto Manifest
     *                 almacenado en la cache.
     */
    public void guardarManifiesto(java.lang.String identificador, boolean cambioManifest) throws java.rmi.RemoteException;

    /**
     * Retorna el OdeVO del manifiesto / submanifiesto principal
     *                 editado.
     */
    public es.pode.empaquetador.negocio.servicio.AgregarVO importarRecursos(java.lang.String identificador, javax.activation.DataHandler paqueteRCP, java.lang.String submanifestId, java.lang.String usuario, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Inicia la edición de un nuevo ODE. Para ello, crea un
     *                 localizador no publicador, registra un nuevo Manifest
     * en la
     *                 cacheEmpaquetacion, crea la estructura de carpetas
     * inicial y
     *                 inicia la catalogacion con un Lom vacío. El resultado
     * es un
     *                 OdeVO describiendo el nuevo ODE.
     */
    public es.pode.empaquetador.negocio.servicio.OdeVO iniciarNuevoODE(java.lang.String user, java.lang.String titulo, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Modifica un Item del ODE en edicion.
     */
    public java.lang.String modificarGrupo(java.lang.String identificador, es.pode.empaquetador.negocio.servicio.GrupoVO grupoVO) throws java.rmi.RemoteException;

    /**
     * Modifica la organización deseada.
     */
    public java.lang.String modificarOrganizacion(java.lang.String identificador, es.pode.empaquetador.negocio.servicio.OrganizacionVO organizacion) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.String modificarRecurso(java.lang.String identificador, es.pode.empaquetador.negocio.servicio.RecursoVO recurso) throws java.rmi.RemoteException;

    /**
     * Mueve los elementos indicados bajo el elemento destino
     */
    public void moverElementos(java.lang.String identificador, java.lang.String[] elementos, java.lang.String destino) throws java.rmi.RemoteException;

    /**
     * Obtiene un listado de los objetos compartidos por otros usuarios
     */
    public es.pode.empaquetador.negocio.servicio.TransicionVO[] obtenerObjetosCompartidos() throws java.rmi.RemoteException;

    /**
     * Obtiene un listado de objetos personales del usuario.
     */
    public es.pode.empaquetador.negocio.servicio.TransicionVO[] obtenerObjetosPersonales(java.lang.String user) throws java.rmi.RemoteException;

    /**
     * Llama al servicio de catalogacion para cargar el objeto Lom
     * asociado al objeto que se desea catalogar. Si no existe un
     *                 objeto Lom previo, se carga uno vacio. El valor de
     * retorno es el
     *                 identificador con que se ha almacenado el objeto Lom
     * en el
     *                 servicio de catalogación.
     */
    public java.lang.String prepararCatalogacion(java.lang.String identificador, java.lang.String idElemento, java.lang.String href) throws java.rmi.RemoteException;

    /**
     * Carga en el servicio de Entregar el Objeto Digital Educativo
     * que
     *                 se quiere previsualizar. El identificador devuelto
     * por el
     *                 servicio de entregar se usara para llamar al modulo
     * Visualizador.
     */
    public java.lang.String previsualizarOde(java.lang.String identificador) throws java.rmi.RemoteException;

    /**
     * En el caso que exista el backup del manifiesto de un ODE lo
     * restaura. Esto lo hace sobreescribiendo el manifiesto con el
     *                 contenido del backup.
     *                 Normalmente el backup del manifiesto lo crea el metodo
     * guardarManifiesto.
     */
    public void restaurarManifiesto(java.lang.String identificador) throws java.rmi.RemoteException;

    /**
     * Reordena un Item moviendolo a una posición anterior en el array.
     */
    public java.lang.String subirItem(java.lang.String identificador, java.lang.String idItem) throws java.rmi.RemoteException;

    /**
     * Sube la organización indicada
     */
    public java.lang.String subirOrganization(java.lang.String identificador, java.lang.String idOrganization) throws java.rmi.RemoteException;

    /**
     * Finaliza la edición del ODE. Esta operación implica eliminar
     * de
     *                 la cache el Manifest en edición, eliminar del servicio
     * de
     *                 Catalogación todos los objetos Lom asociados a este
     * Ode. Y
     *                 eliminar los backups que se generan al comenzar la
     * edición o al
     *                 guardar.
     */
    public void terminarEdicion(java.lang.String identificador) throws java.rmi.RemoteException;

    /**
     * Llama al servicio de validacion
     */
    public es.pode.empaquetador.negocio.servicio.ValidaVO validar(java.lang.String identificador, java.lang.String tipo) throws java.rmi.RemoteException;

    /**
     * Añade un vinculo entre un Item y otro elemento (Resource o
     *                 Manifest).
     */
    public java.lang.String vincularGrupo(java.lang.String identificador, java.lang.String identificadorGrupo, java.lang.String identificadorRecurso) throws java.rmi.RemoteException;
}
