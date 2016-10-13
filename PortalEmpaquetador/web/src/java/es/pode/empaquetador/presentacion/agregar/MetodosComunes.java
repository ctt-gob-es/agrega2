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
