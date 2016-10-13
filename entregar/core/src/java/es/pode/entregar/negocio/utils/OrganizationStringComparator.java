package es.pode.entregar.negocio.utils;

import java.io.Serializable;
import java.util.Comparator;

import es.pode.parseadorXML.castor.Organization;


public class OrganizationStringComparator implements Comparator, Serializable {

	public int compare(Object o1, Object o2) {
		
		return ((Organization) o1).getIdentifier().compareTo((String) o2);
	}

}
