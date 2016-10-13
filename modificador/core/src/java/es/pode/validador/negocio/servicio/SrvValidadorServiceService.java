/**
 * SrvValidadorServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.validador.negocio.servicio;

public interface SrvValidadorServiceService extends javax.xml.rpc.Service {

/**
 * El servicio de validacion consta de cuatro metodos para
 *             diferentes tipos de validacion:
 *             a) obtenerValidacionBin: parametro de entrada rutaManifest
 * de
 *             tipo String, es la ruta en la      que    se   encuentra
 * el
 *             fichero imsmanifest.xml; parametro de salida, el tipo
 * ValidaVO
 *             Este tipo de validacion realiza las siguientes tareas:
 * 1.- chequea que el fichero se encuentre en la ruta
 *             correspondiente
 *             2.- realiza el parseo del fichero y comprueba que los
 * nodos y
 *             atributos sean correctos
 *             3.- chequeo de los contenidos, en los que se testea que
 * los
 *             ficheros a los que referencia           el imsmanifest.xml
 * sean
 *             correctos y esten en su ruta correspondiente
 *             El resultado de esta validacion sera del tipo ValidaVO,
 * con sus
 *             atributos rellenos seguno
 *             se ha comentado en el mismo
 *             b) obtenerValidacionLigera: parametro de entrada rutaManifest
 * de
 *             tipo String, es la ruta en
 *             la que se encuentra el fichero imsmanifest.xml; parametro
 * de
 *             salida, el tipo ValidaVO
 *             Este tipo de validacion realiza las siguientes tareas:
 * 1.- chequea que el fichero se encuentre en la ruta
 *             correspondiente
 *             2.- realiza el parseo del fichero y comprueba que los
 * nodos y
 *             atributos sean correctos;
 *             esta comprobacion tendra mayor o menor restriccion dependiendo
 * del tipo de ODE que
 *             puede ser: CA (contentAggregation--> si es obligatorio
 * que tenga
 *             al menos una
 *             organizacion) o RCP (ResourceContentPackage --> la etiqueta
 * organizations tiene que ir
 *             vacia)
 *             El resultado de esta validacion sera del tipo ValidaVO,
 * con sus
 *             atributos rellenos seguno
 *             se ha comentado en el mismo
 *             c) validarCargaOde: parametro de entrada rutaManifest
 * de tipo
 *             String, es la ruta en
 *             la que se encuentra el fichero imsmanifest.xml; parametro
 * de
 *             salida, el tipo ValidaVO
 *             Este tipo de validacion realiza las siguientes tareas:
 * 1.- valida que el fichero se encuentre en la ruta
 *             correspondiente
 *             2.- chequea que todos los metadatos de tipo LOM-ES sean
 * correctos
 *             3.- Realiza la validacion Binaria (explicado en apartado
 * a)
 *             4.- chequea que esten rellenos los campos de metadatos
 * basicos
 *             obligatorios
 *             d) validarMDBasicosObl: parametro de entrada MDBasicosOblVO,
 * es
 *             la ruta en
 *             la que se encuentra el fichero imsmanifest.xml; parametro
 * de
 *             salida, el tipo ValidaVO
 *             Con este metodo se obliga a que se rellenen los metadatos
 * basicos obligatorios, si no estan
 *             devolvera un error
 */
    public java.lang.String getSrvValidadorServiceAddress();

    public es.pode.validador.negocio.servicio.SrvValidadorService getSrvValidadorService() throws javax.xml.rpc.ServiceException;

    public es.pode.validador.negocio.servicio.SrvValidadorService getSrvValidadorService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
