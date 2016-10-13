/**
 * SrvCatalogacionBasicaService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.catalogacion.negocio.servicios;

public interface SrvCatalogacionBasicaService extends java.rmi.Remote {

    /**
     * Mediante el servicio de fuentes taxonomicas se recupera toda
     * el
     *                 path hasta el taxon seleccionado y se introduce este
     * en el
     *                 objeto LOM completo introducido en la hash map. La
     * asociacion de
     *                 la taxonomia se hace directamente sobre el objeto
     * LOM completo,
     *                 no sobre un LomBasicoVO.
     */
    public void asociarTaxonomia(java.lang.String identificador, java.lang.String nomTaxonomia, es.pode.catalogacion.negocio.servicios.CBTaxonVO[] taxonList, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Metodo para asociar una nueva ruta taxonomica (de un arbol
     *                 curricular) a un LomBasicoVO.
     *                 Devuelve el objeto LomBasicoVO pasado como parametro
     * modificado
     *                 con la nueva ruta asociada.
     */
    public es.pode.catalogacion.negocio.servicios.LomBasicoVO asociarTaxonomiaEnLomBasico(java.lang.String identificador, es.pode.catalogacion.negocio.servicios.LomBasicoVO lomBasico, es.pode.catalogacion.negocio.servicios.CBTaxonVO[] taxonList) throws java.rmi.RemoteException;

    /**
     * Metodo que recoge un objeto LOM y lo introduce en una hash
     * map
     *                 global al servicio.
     *                 Devuelve el identificador del objeto LOM que se ha
     * introducido
     *                 en la tabla hash.
     */
    public java.lang.String cargarObjLom(java.lang.String identificador, java.lang.String usuario, javax.activation.DataHandler lom) throws java.rmi.RemoteException;

    /**
     * Metodo para eliminar objetos LOM de la tabla hash del servicio
     * de catalogacion.
     */
    public void eliminarObjLoms(java.lang.String[] ids) throws java.rmi.RemoteException;

    /**
     * Elimina directamente una taxonomia dada de un objeto LOM
     *                 almacenado en la tabla hash.
     */
    public void eliminarTaxonomia(java.lang.String identificador, java.lang.String nomTaxonomia, int idArbol) throws java.rmi.RemoteException;

    /**
     * Metodo para eliminar una ruta taxonomica (arbol curricular)
     * de
     *                 un LomBasicoVO.
     *                 Devuelve el objeto LomBasicoVO pasado como parametro
     * sin la ruta
     *                 curricular indicada.
     */
    public es.pode.catalogacion.negocio.servicios.LomBasicoVO eliminarTaxonomiasEnLomBasico(es.pode.catalogacion.negocio.servicios.LomBasicoVO lomBasico, java.lang.String[] idArbol) throws java.rmi.RemoteException;

    /**

     */
    public javax.activation.DataHandler exportarLomes(java.lang.String identificador, java.lang.String usuario, es.pode.catalogacion.negocio.servicios.LomBasicoVO lomBasico, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Recupera los valores introducidos por el usuario mediante el
     * catalogador en un LomBasicoVO. Traspasa los valores recogidos a
     *                 un LOM completo almacenado en la tabla hash de objetos
     * LOM.
     */
    public es.pode.catalogacion.negocio.servicios.LomBasicoVO generarMetadatos(java.lang.String identificador, java.lang.String usuario, es.pode.catalogacion.negocio.servicios.LomBasicoVO lomBasico, java.lang.String idioma) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.catalogacion.negocio.servicios.LomBasicoVO importarLomes(javax.activation.DataHandler ficheroLomes, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Metodo que recupera un objeto LOM completo y recoge los valores
     * necesarios para mostrar en el catalogador basico.
     *                 Devuelve un objeto LomBasicoVO con los valores requeridos
     * desde
     *                 el catalogador (Titulo, descripcion, edad y los identificadores
     * de los valores elegidos de los vocabularios controlados). Para
     *                 recoger los identificadores de los terminos, se recogera
     * el
     *                 valor en ingles desde el objeto LOM completo, y mediante
     * el
     *                 servicio de vocabularios controlados, se obtendra
     * el
     *                 identificador asociado. Con los identificadores, el
     * catalogador
     *                 en la interfaz de usuario cargara el combo con el
     * valor
     *                 seleccionado.
     */
    public es.pode.catalogacion.negocio.servicios.LomBasicoVO obtenerLomBasico(java.lang.String identificador, java.lang.String usuario, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Metodo para obtener uno o varios objetos LOM almacenados en
     * la
     *                 tabla hash del servicio.
     *                 Devuelve una lista de IdLomVO, donde cada uno contiene
     * un
     *                 identificador del objeto y una representacion mediante
     * un
     *                 datahandler del metadato LOM-ES.
     */
    public es.pode.catalogacion.negocio.servicios.IdLomVO[] obtenerObjLoms(java.lang.String[] ids) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.catalogacion.negocio.servicios.LomBasicoVO traducirLomes(java.lang.String identificador, java.lang.String usuario, java.lang.String idiomaTrad, es.pode.catalogacion.negocio.servicios.LomBasicoVO lom, java.lang.String idiomaOri, boolean soloCoincidentes) throws java.rmi.RemoteException;
}
