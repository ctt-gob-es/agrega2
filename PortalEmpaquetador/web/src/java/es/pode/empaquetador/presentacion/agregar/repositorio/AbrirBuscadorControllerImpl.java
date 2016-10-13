/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.empaquetador.presentacion.agregar.repositorio;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionMapping;

import es.pode.empaquetador.negocio.servicio.GrupoVO;
import es.pode.empaquetador.negocio.servicio.OdeVO;
import es.pode.empaquetador.negocio.servicio.OrganizacionVO;
import es.pode.empaquetador.presentacion.GestorSesion;



/**
 * @see es.pode.empaquetador.presentacion.agregar.repositorio.AbrirBuscadorController
 */
public class AbrirBuscadorControllerImpl extends AbrirBuscadorController
{

//	private final static Logger logger = Logger.getLogger(AbrirBuscadorControllerImpl.class);

	private GestorSesion gs = new GestorSesion();


    /**
     * @see es.pode.empaquetador.presentacion.agregar.repositorio.AbrirBuscadorController#tipoEmpaquetador(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.agregar.repositorio.TipoEmpaquetadorForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String tipoEmpaquetador(ActionMapping mapping, es.pode.empaquetador.presentacion.agregar.repositorio.TipoEmpaquetadorForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        return gs.tipoEmpaquetador(this.getEmpaquetadorSession(request));
    }


    /**
     * @see es.pode.empaquetador.presentacion.agregar.repositorio.AbrirBuscadorController#prepararParametros(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.agregar.repositorio.PrepararParametrosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void prepararParametros(ActionMapping mapping, es.pode.empaquetador.presentacion.agregar.repositorio.PrepararParametrosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        form.setIdOde(this.getEmpaquetadorSession(request).getIdLocalizador());
        form.setTipoEmpaquetador(this.getEmpaquetadorSession(request).getTipoEmpaquetador());
        String idDestino = null;
        if("avanzado".equalsIgnoreCase(this.getEmpaquetadorSession(request).getTipoEmpaquetador())) {
        	if(this.getEmpaquetadorSession(request).getSubmanifestPath().size()>1) {
        		List subManPath = this.getEmpaquetadorSession(request).getSubmanifestPath();
        		idDestino = ((OdeVO)subManPath.get(subManPath.size()-1)).getIdentifier();
        	}
        } else {
        	Object obj = this.getEmpaquetadorSession(request).getIdCollection().get(this.getEmpaquetadorSession(request).getIdCollection().size()-1);
        	if (obj instanceof OrganizacionVO) {
				OrganizacionVO org = (OrganizacionVO) obj;
				idDestino = org.getIdentifier();
			} else {
				GrupoVO grp = (GrupoVO) obj;
				idDestino = grp.getIdentifier();
			}
        }
        form.setIdDestino(idDestino);
        //modificaci�n del ode
        this.getEmpaquetadorSession(request).setModificado(true);
        if ((request.getParameter("asistente")==null) || !(request.getParameter("asistente").equals("si"))){
   			//inicializamos session
   			this.getEmpaquetadorSession(request).setMensajeAsistente("");
   	   }
        
     }









}