/**
 * SrvEmpaquetadorBasicoService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.empaquetador.negocio.servicio;

public interface SrvEmpaquetadorBasicoService extends java.rmi.Remote {

    /**
     * Agrega un paquete, sea un Ode, un RCP (Resource Content Package)
     * o un conjunto de ficheros, comprimidos o no.
     */
    public es.pode.empaquetador.negocio.servicio.AgregarVO agregarLocal(java.lang.String idOde, es.pode.empaquetador.negocio.servicio.FicheroVO fichero, java.lang.String idGrupo, java.lang.String usuario, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Importa un ODE del repositorio indicado al ODE actual, bajo
     * el
     *                 identificador de grupo dado.
     */
    public void agregarRepositorio(java.lang.String idOde, java.lang.String idLocalizador, java.lang.String idGrupo) throws java.rmi.RemoteException;

    /**
     * Analiza el archivo dado y determina si es un RCP, un ODE
     *                 (devuelve "CA"), un archivo ZIP o un fichero individual.
     */
    public es.pode.empaquetador.negocio.servicio.AnalizaArchivoVO analizaArchivo(java.lang.String rutaArchivo, java.lang.String rutaDescomprimido) throws java.rmi.RemoteException;

    /**
     * Asocia los ficheros dados al Recurso referenciado por el Item
     * dado. Si no existe, se creará uno nuevo.
     *                 Emplea las operaciones crearRecurso y modificarRecurso
     * del
     *                 servicio GestorManifestService.
     */
    public void asociarContenidos(java.lang.String idOde, java.lang.String idGrupo, es.pode.empaquetador.negocio.servicio.FileVO[] ficheros, java.lang.String href) throws java.rmi.RemoteException;

    /**
     * Copia los ficheros / carpetas especificados a la carpeta de
     * destino deseada. A continuación elimina los ficheros / carpetas
     *                 de origen con una llamada a eliminar.
     *                 Usa la operación cortar del servicio GestorArchivosService.
     */
    public void cortar(java.lang.String identificador, java.lang.String carpetaDestino, es.pode.empaquetador.negocio.servicio.ArchivoVO[] ficheros) throws java.rmi.RemoteException;

    /**
     * Crea un objeto HTML con el código embebido suministrado.
     *                 Inserta el código dado en una plantilla HTML simple,
     * en el lugar
     *                 del Body.
     *                 No se chequea la validez o corrección del HTML resultante.
     */
    public es.pode.empaquetador.negocio.servicio.ArchivoVO crearObjetoEmbebido(java.lang.String idOde, java.lang.String contenido) throws java.rmi.RemoteException;

    /**
     * Desagregar items a Ode autogenerado.
     *                 El ode se almacena en objetos personales del usuario.
     */
    public java.lang.String desagregarItem(java.lang.String idOde, es.pode.empaquetador.negocio.servicio.GrupoVO[] items) throws java.rmi.RemoteException;

    /**
     * Desagregar items a Ode autogenerado.
     *                 El paquete SCORM2004 con el ODE generado al exportar
     * las
     *                 carpetas se devuelve como un DataHandler
     */
    public javax.activation.DataHandler desagregarItemLocal(java.lang.String idOde, es.pode.empaquetador.negocio.servicio.GrupoVO[] items) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.String editarObjetoEmbebido(java.lang.String idOde, java.lang.String file) throws java.rmi.RemoteException;

    /**
     * Editar ODE actual. Llama internamente a la operación editarOde
     * del servicio GestorManifest y a continuación realiza aplanado de
     *                 organizaciones y submanifiestos que pudiera haber.
     */
    public es.pode.empaquetador.negocio.servicio.OdeVO editarOde(java.lang.String idOde) throws java.rmi.RemoteException;

    /**
     * Elimina del Ode el fichero indicado.
     *                 Si es referenciado por algún item o recurso, no se
     * elimina a no
     *                 ser que checked=true.
     *                 Devuelve true si se eliminó los ficheros, false si
     * alguno es
     *                 referenciado por algún item o recurso.
     */
    public boolean eliminarFicheros(java.lang.String idOde, es.pode.empaquetador.negocio.servicio.ArchivoVO[] ficheros, boolean checked) throws java.rmi.RemoteException;

    /**
     * Elimina el grupo indicado.
     */
    public void eliminarGrupo(java.lang.String idOde, java.lang.String idGrupo, java.lang.Boolean eliminarFicheros) throws java.rmi.RemoteException;

    /**
     * Devuelve la lista de items que referencian a este fichero dentro
     * de este Ode
     */
    public es.pode.empaquetador.negocio.servicio.GrupoVO[] referenciadoPor(java.lang.String idOde, es.pode.empaquetador.negocio.servicio.ArchivoVO fichero) throws java.rmi.RemoteException;

    /**
     * Modifica el nombre del fichero o carpeta deseado.
     *                 Llama al SrvGestorArchivosService.renombrar
     */
    public void renombrar(java.lang.String identificador, java.lang.String carpetaPadre, java.lang.String nombre, java.lang.String nuevoNombre) throws java.rmi.RemoteException;
}
