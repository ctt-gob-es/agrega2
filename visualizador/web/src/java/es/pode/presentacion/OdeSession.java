/**
 * 
 */
package es.pode.presentacion;

//import java.util.Arrays;
import java.util.HashMap;

import es.pode.entregar.negocio.servicios.ItemVO;

/**
 * @author dgonzalezd
 *
 */
public class OdeSession {
	  /**
	   * Clave del objeto de session
	   */
	    public final static String SESSION_KEY = "odeSession";

	    private java.util.Collection datosSalida;

	    public java.util.Collection getDatosSalida()
	    {
	        return this.datosSalida;
	    }

	    public void setDatosSalida(java.util.Collection datosSalida)
	    {
	        this.datosSalida = datosSalida;
	    }

	    private java.util.Collection itemsFlow;

	    public java.util.Collection getItemsFlow()
	    {
	        return this.itemsFlow;
	    }

	    public void setItemsFlow(java.util.Collection itemsFlow)
	    {
	        this.itemsFlow = itemsFlow;
	    }

//	    private java.lang.String identificador;
//
//	    public java.lang.String getIdentificador()
//	    {
//	        return this.identificador;
//	    }
//
//	    public void setIdentificador(java.lang.String identificador)
//	    {
//	        this.identificador = identificador;
//	    }

	    private java.lang.String localizadorCont;

	    public java.lang.String getLocalizadorCont()
	    {
	        return this.localizadorCont;
	    }

	    public void setLocalizadorCont(java.lang.String localizadorCont)
	    {
	        this.localizadorCont = localizadorCont;
	    }

	    private java.lang.String UrlContenido;

	    public java.lang.String getUrlContenido()
	    {
	        return this.UrlContenido;
	    }

	    public void setUrlContenido(java.lang.String UrlContenido)
	    {
	        this.UrlContenido = UrlContenido;
	    }

	    private java.lang.Boolean btnIzquierdo;

	    public java.lang.Boolean getBtnIzquierdo()
	    {
	        return this.btnIzquierdo;
	    }

	    public void setBtnIzquierdo(java.lang.Boolean btnIzquierdo)
	    {
	        this.btnIzquierdo = btnIzquierdo;
	    }

	    private java.lang.Boolean btnDerecho;

	    public java.lang.Boolean getBtnDerecho()
	    {
	        return this.btnDerecho;
	    }

	    public void setBtnDerecho(java.lang.Boolean btnDerecho)
	    {
	        this.btnDerecho = btnDerecho;
	    }

	    private int contador;

	    public int getContador()
	    {
	        return this.contador;
	    }

	    public void setContador(int contador)
	    {
	        this.contador = contador;
	    }

//	    private java.lang.String usuario;
//
//	    public java.lang.String getUsuario()
//	    {
//	        return this.usuario;
//	    }
//
//	    public void setUsuario(java.lang.String usuario)
//	    {
//	        this.usuario = usuario;
//	    }

	    private java.lang.String idSeleccionado;

	    public java.lang.String getIdSeleccionado()
	    {
	        return this.idSeleccionado;
	    }

	    public void setIdSeleccionado(java.lang.String idSeleccionado)
	    {
	        this.idSeleccionado = idSeleccionado;
	    }

	    private java.lang.String tituloOde;

	    public java.lang.String getTituloOde()
	    {
	        return this.tituloOde;
	    }

	    public void setTituloOde(java.lang.String tituloOde)
	    {
	        this.tituloOde = tituloOde;
	    }

	    private java.lang.String urlCerrar;

	    public java.lang.String getUrlCerrar()
	    {
	        return this.urlCerrar;
	    }

	    public void setUrlCerrar(java.lang.String urlCerrar)
	    {
	        this.urlCerrar = urlCerrar;
	    }

	    private java.lang.String idiomaCat;

	    public java.lang.String getIdiomaCat()
	    {
	        return this.idiomaCat;
	    }

	    public void setIdiomaCat(java.lang.String idiomaCat)
	    {
	        this.idiomaCat = idiomaCat;
	    }

	    private java.lang.String contenidoEmbebido;

	    public java.lang.String getContenidoEmbebido()
	    {
	        return this.contenidoEmbebido;
	    }

	    public void setContenidoEmbebido(java.lang.String contenidoEmbebido)
	    {
	        this.contenidoEmbebido = contenidoEmbebido;
	    }

	    private boolean abiertos;

	    public boolean isAbiertos()
	    {
	        return this.abiertos;
	    }

	    public void setAbiertos(boolean abiertos)
	    {
	        this.abiertos = abiertos;
	    }

	    private java.util.Collection rutaSeleccionado;

	    public java.util.Collection getRutaSeleccionado()
	    {
	        return this.rutaSeleccionado;
	    }

	    public void setRutaSeleccionado(java.util.Collection rutaSeleccionado)
	    {
	        this.rutaSeleccionado = rutaSeleccionado;
	    }

	    private boolean menuDesplegado;

	    public boolean isMenuDesplegado()
	    {
	        return this.menuDesplegado;
	    }

	    public void setMenuDesplegado(boolean menuDesplegado)
	    {
	        this.menuDesplegado = menuDesplegado;
	    }

	    private boolean usuarioAdministrador;

	    public boolean isUsuarioAdministrador()
	    {
	        return this.usuarioAdministrador;
	    }

	    public void setUsuarioAdministrador(boolean usuarioAdministrador)
	    {
	        this.usuarioAdministrador = usuarioAdministrador;
	    }

	    private java.util.Collection listaComentariosEliminar;

	    public java.util.Collection getListaComentariosEliminar()
	    {
	        return this.listaComentariosEliminar;
	    }

	    public void setListaComentariosEliminar(java.util.Collection listaComentariosEliminar)
	    {
	        this.listaComentariosEliminar = listaComentariosEliminar;
	    }

	    private java.lang.String urlImagen;

	    public java.lang.String getUrlImagen()
	    {
	        return this.urlImagen;
	    }

	    public void setUrlImagen(java.lang.String urlImagen)
	    {
	        this.urlImagen = urlImagen;
	    }

	    private java.util.List organizaciones;

	    public java.util.List getOrganizaciones()
	    {
	        return this.organizaciones;
	    }

	    public void setOrganizaciones(java.util.List organizaciones)
	    {
	        this.organizaciones = organizaciones;
	    }

	    private java.lang.String textoLicencia;

	    public java.lang.String getTextoLicencia()
	    {
	        return this.textoLicencia;
	    }

	    public void setTextoLicencia(java.lang.String textoLicencia)
	    {
	        this.textoLicencia = textoLicencia;
	    }

//	    private java.lang.String[] formatosExportacion;
//
//	    public java.lang.String[] getFormatosExportacion()
//	    {
//	        return this.formatosExportacion;
//	    }
//
//	    public void setFormatosExportacion(java.lang.String[] formatosExportacion)
//	    {
//	        this.formatosExportacion = formatosExportacion;
//	    }

	    private boolean secuencia;

	    public boolean isSecuencia()
	    {
	        return this.secuencia;
	    }

	    public void setSecuencia(boolean secuencia)
	    {
	        this.secuencia = secuencia;
	    }

	    private boolean favorito;

	    public boolean isFavorito()
	    {
	        return this.favorito;
	    }

	    public void setFavorito(boolean favorito)
	    {
	        this.favorito = favorito;
	    }

	    private java.lang.String retorno;

	    public java.lang.String getRetorno()
	    {
	        return this.retorno;
	    }

	    public void setRetorno(java.lang.String retorno)
	    {
	        this.retorno = retorno;
	    }

	    private boolean verFavorito;

	    public boolean isVerFavorito()
	    {
	        return this.verFavorito;
	    }

	    public void setVerFavorito(boolean verFavorito)
	    {
	        this.verFavorito = verFavorito;
	    }

	    private boolean verValorar;

	    public boolean isVerValorar()
	    {
	        return this.verValorar;
	    }

	    public void setVerValorar(boolean verValorar)
	    {
	        this.verValorar = verValorar;
	    }

	    private boolean verShare;

	    public boolean isVerShare()
	    {
	        return this.verShare;
	    }

	    public void setVerShare(boolean verShare)
	    {
	        this.verShare = verShare;
	    }

	    private boolean verComentarios;

	    public boolean isVerComentarios()
	    {
	        return this.verComentarios;
	    }

	    public void setVerComentarios(boolean verComentarios)
	    {
	        this.verComentarios = verComentarios;
	    }

	    private boolean verEstadisticas;

	    public boolean isVerEstadisticas()
	    {
	        return this.verEstadisticas;
	    }

	    public void setVerEstadisticas(boolean verEstadisticas)
	    {
	        this.verEstadisticas = verEstadisticas;
	    }

	    private boolean verExportar;

	    public boolean isVerExportar()
	    {
	        return this.verExportar;
	    }

	    public void setVerExportar(boolean verExportar)
	    {
	        this.verExportar = verExportar;
	    }

//	    private boolean verItinerarios;
//
//	    public boolean isVerItinerarios()
//	    {
//	        return this.verItinerarios;
//	    }
//
//	    public void setVerItinerarios(boolean verItinerarios)
//	    {
//	        this.verItinerarios = verItinerarios;
//	    }

	    private boolean verTagging;

	    public boolean isVerTagging()
	    {
	        return this.verTagging;
	    }

	    public void setVerTagging(boolean verTagging)
	    {
	        this.verTagging = verTagging;
	    }

	    private boolean verEmbebido;

	    public boolean isVerEmbebido()
	    {
	        return this.verEmbebido;
	    }

	    public void setVerEmbebido(boolean verEmbebido)
	    {
	        this.verEmbebido = verEmbebido;
	    }

	    private boolean verRecomendar;

	    public boolean isVerRecomendar()
	    {
	        return this.verRecomendar;
	    }

	    public void setVerRecomendar(boolean verRecomendar)
	    {
	        this.verRecomendar = verRecomendar;
	    }

	    private boolean verContenidoInapropiado;

	    public boolean isVerContenidoInapropiado()
	    {
	        return this.verContenidoInapropiado;
	    }

	    public void setVerContenidoInapropiado(boolean verContenidoInapropiado)
	    {
	        this.verContenidoInapropiado = verContenidoInapropiado;
	    }

	    private boolean verSecuencia;

	    public boolean isVerSecuencia()
	    {
	        return this.verSecuencia;
	    }

	    public void setVerSecuencia(boolean verSecuencia)
	    {
	        this.verSecuencia = verSecuencia;
	    }

	    private boolean verComentariosAlta;

	    public boolean isVerComentariosAlta()
	    {
	        return this.verComentariosAlta;
	    }

	    public void setVerComentariosAlta(boolean verComentariosAlta)
	    {
	        this.verComentariosAlta = verComentariosAlta;
	    }

	    private boolean verComentariosBaja;

	    public boolean isVerComentariosBaja()
	    {
	        return this.verComentariosBaja;
	    }

	    public void setVerComentariosBaja(boolean verComentariosBaja)
	    {
	        this.verComentariosBaja = verComentariosBaja;
	    }

	    private boolean valorado;

	    public boolean isValorado()
	    {
	        return this.valorado;
	    }

	    public void setValorado(boolean valorado)
	    {
	        this.valorado = valorado;
	    }

	    private java.lang.String valoracion;

	    public java.lang.String getValoracion()
	    {
	        return this.valoracion;
	    }

	    public void setValoracion(java.lang.String valoracion)
	    {
	        this.valoracion = valoracion;
	    }
	    
	    private HashMap<String, ItemVO[]> hOrganizaciones;

	    public HashMap<String, ItemVO[]> gethOrganizaciones()
	    {
	        return this.hOrganizaciones;
	    }

	    public void sethOrganizaciones(HashMap<String, ItemVO[]> hOrganizaciones)
	    {
	        this.hOrganizaciones = hOrganizaciones;
	    }

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "OdeSession [UrlContenido=" + UrlContenido + ", abiertos="
					+ abiertos + ", btnDerecho=" + btnDerecho
					+ ", btnIzquierdo=" + btnIzquierdo + ", contador="
					+ contador + ", contenidoEmbebido=" + contenidoEmbebido
					+ ", datosSalida=" + datosSalida + ", favorito=" + favorito
//					+ ", formatosExportacion="
//					+ Arrays.toString(formatosExportacion)
					+ ", hOrganizaciones=" + hOrganizaciones
					+ ", idSeleccionado=" + idSeleccionado + ", idiomaCat="
					+ idiomaCat + ", itemsFlow=" + itemsFlow
					+ ", listaComentariosEliminar=" + listaComentariosEliminar
					+ ", localizadorCont=" + localizadorCont
					+ ", menuDesplegado=" + menuDesplegado
					+ ", organizaciones=" + organizaciones + ", retorno="
					+ retorno + ", rutaSeleccionado=" + rutaSeleccionado
					+ ", secuencia=" + secuencia + ", textoLicencia="
					+ textoLicencia + ", tituloOde=" + tituloOde
					+ ", urlCerrar=" + urlCerrar + ", urlImagen=" + urlImagen
					+ ", usuarioAdministrador=" + usuarioAdministrador
					+ ", valoracion=" + valoracion + ", valorado=" + valorado
					+ ", verComentarios=" + verComentarios
					+ ", verComentariosAlta=" + verComentariosAlta
					+ ", verComentariosBaja=" + verComentariosBaja
					+ ", verContenidoInapropiado=" + verContenidoInapropiado
					+ ", verEmbebido=" + verEmbebido + ", verEstadisticas="
					+ verEstadisticas + ", verExportar=" + verExportar
					+ ", verFavorito=" + verFavorito + ", verRecomendar="
					+ verRecomendar + ", verSecuencia=" + verSecuencia
					+ ", verShare=" + verShare + ", verTagging=" + verTagging
					+ ", verValorar=" + verValorar + "]";
		}
	    
}
