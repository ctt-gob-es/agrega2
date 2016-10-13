/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * 
 */
package es.pode.empaquetador.presentacion.agregar;

import java.util.ArrayList;
import java.util.List;

import es.pode.empaquetador.negocio.servicio.OdeVO;
import es.pode.empaquetador.negocio.servicio.OrganizacionVO;
import es.pode.empaquetador.presentacion.EmpaquetadorSession;

/**
 * @author dgonzalezd
 *
 */
public class MetodosComunes {
	public static void refrescaSesion(EmpaquetadorSession sesEmpaq, OdeVO odeEditado) {
		sesEmpaq.setOde(odeEditado);
		sesEmpaq.setVistaCarpeta(true);
		sesEmpaq.setAccion("Normal");
		sesEmpaq.setModoPegar(false);
		
		//le introduzco la organizacion principal
		List idCollection2=new ArrayList();
		String idOrgPrincipal = odeEditado.getOrganizacionPrincipal();
		
		if(idOrgPrincipal!=null)
		{
			OrganizacionVO[] orgs= odeEditado.getOrganizaciones();
			if(orgs!=null && orgs.length>0)
			{
				for(int i=0;i<orgs.length ; i++)
				{
					if(orgs[i].getIdentifier().equals(idOrgPrincipal))
					{
						idCollection2.add(orgs[i]);
					}
				}
			}
		}
		sesEmpaq.setIdCollection(idCollection2);
	}
}
