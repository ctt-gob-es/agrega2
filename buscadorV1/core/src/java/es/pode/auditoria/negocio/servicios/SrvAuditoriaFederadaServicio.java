/*
Agrega2 es una federaci髇 de repositorios de objetos digitales educativos formada por todas las Comunidades Aut髇omas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvAuditoriaFederadaServicio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.auditoria.negocio.servicios;

public interface SrvAuditoriaFederadaServicio extends java.rmi.Remote {

    /**
     * m茅todo de obtenci贸n de los odes por cobertura curricular
     */
    public es.pode.auditoria.negocio.servicios.InformeCoberturaFederadoVO coberturaFederadoLocal(es.pode.auditoria.negocio.servicios.ParametrosInformeFederadoVO parametros) throws java.rmi.RemoteException;

    /**
     * Obtiene el informe federados de los odes por 谩rea curricular
     * en
     *                 cada uno de los nodos de la federaci贸n
     */
    public es.pode.auditoria.negocio.servicios.InformeCoberturaFederadoVO[] informeCoberturaFederado(es.pode.auditoria.negocio.servicios.ParametrosInformeFederadoVO parametros) throws java.rmi.RemoteException;

    /**
     * M茅todo que generar谩 un array de VO con la informaci贸n de los
     * odes que pertenecen a cada nivel de agregaci贸n dentro de la
     *                 federaci贸n
     */
    public es.pode.auditoria.negocio.servicios.NivelAgregacionVO[] informeNivelAgregacionFederado(es.pode.auditoria.negocio.servicios.ParametrosInformeFederadoVO parametros) throws java.rmi.RemoteException;

    /**
     * m茅todo que devuelve los odes publicados en cada idioma para
     * cada
     *                 nodo
     */
    public es.pode.auditoria.negocio.servicios.InformeOdesIdiomaFederadoVO[] informeOdesIdiomaFederado(es.pode.auditoria.negocio.servicios.ParametrosInformeFederadoVO parametros) throws java.rmi.RemoteException;

    /**
     * m茅todo que devuelve los odes publicados en cada idioma
     */
    public es.pode.auditoria.negocio.servicios.InformeOdesIdiomaFederadoVO informeOdesIdiomaLocal(es.pode.auditoria.negocio.servicios.ParametrosInformeFederadoVO parametros) throws java.rmi.RemoteException;

    /**
     * m茅todo que obtiene el n煤mero de odes publicados en cada nodo
     */
    public es.pode.auditoria.negocio.servicios.InformeOdesPublicadosFederadaVO[] informeOdesPublicadosFederado(es.pode.auditoria.negocio.servicios.ParametrosInformeFederadoVO parametros) throws java.rmi.RemoteException;

    /**
     * M茅todo de generaci贸n del informe de nivel de agregaci贸n local
     */
    public es.pode.auditoria.negocio.servicios.NivelAgregacionVO nivelAgregacionFederadoLocal(es.pode.auditoria.negocio.servicios.ParametrosInformeFederadoVO parametros) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.auditoria.negocio.servicios.InformeOdesPublicadosFederadaVO odesPublicadosFederadoLocal(es.pode.auditoria.negocio.servicios.ParametrosInformeFederadoVO parametros) throws java.rmi.RemoteException;
}
