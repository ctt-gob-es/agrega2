/**
 * 
 */
package es.pode.parseadorXML.rest;

/**
 * Clase raiz de la respuesta REST.
 * 
 * @author fjespino
 */
public class RSP {

	public final static String STAT_OK = "OK";
	public final static String STAT_ERROR = "ERROR";

	public RSP() {}
	public RSP(Short errorCode, String errorMessage) {
		stat = STAT_ERROR;
		this.errorCode = errorCode;
		this.errorMsg = errorMessage;
	}
	
	/**
	 * Resultado de la respuesta REST. Los valores validos son RSP.STAT_OK y
	 * RSP.STAT_ERROR
	 */
	private String stat = null;

	/**
	 * Codigo de error en caso de stat=RSP.STAT_ERROR
	 */
	private Short errorCode = null;

	/**
	 * Mensaje de error en caso de stat=RSP.STAT_ERROR
	 */
	private String errorMsg = null;

	/**
	 * Objeto con los resultados de la consulta en caso de stat=RSP.STAT_OK
	 */
	private Odes odes = null;

	/**
	 * @return
	 */
	public String getStat() {
		return stat;
	}

	/**
	 * @param stat
	 */
	public void setStat(String stat) {
		this.stat = stat;
	}

	/**
	 * @return
	 */
	public Short getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 */
	public void setErrorCode(Short errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * @return
	 */
	public Odes getOdes() {
		return odes;
	}

	/**
	 * @param odes
	 */
	public void setOdes(Odes odes) {
		this.odes = odes;
	}

}
