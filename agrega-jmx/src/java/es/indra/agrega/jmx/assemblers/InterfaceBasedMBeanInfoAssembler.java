/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * 
 */
package es.indra.agrega.jmx.assemblers;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.management.Descriptor;
import javax.management.modelmbean.ModelMBeanOperationInfo;

import org.springframework.beans.BeanUtils;

/**
 * @author jlhuertas
 *
 */
public class InterfaceBasedMBeanInfoAssembler extends
		org.springframework.jmx.export.assembler.InterfaceBasedMBeanInfoAssembler {

	/**
	 * Iterate through all methods on the MBean class and gives subclasses the chance
	 * to vote on their inclusion. If a particular method corresponds to the accessor
	 * or mutator of an attribute that is inclued in the managment interface, then
	 * the corresponding operation is exposed with the &quot;role&quot; descriptor
	 * field set to the appropriate value.
	 * @param managedBean the bean instance (might be an AOP proxy)
	 * @param beanKey the key associated with the MBean in the beans map
	 * of the <code>MBeanExporter</code>
	 * @return the operation metadata
	 * @see #populateOperationDescriptor
	 */
	protected ModelMBeanOperationInfo[] getOperationInfo(Object managedBean, String beanKey) {
		Method[] methods = getClassToExpose(managedBean).getMethods();

		List infos = new ArrayList();

		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			if (method.getDeclaringClass().equals(Object.class)) {
				continue;
			}

			ModelMBeanOperationInfo info = null;
			PropertyDescriptor pd = BeanUtils.findPropertyForMethod(method);
			if (pd != null) {
				if ((method.equals(pd.getReadMethod()) && includeReadAttribute(method, beanKey)) ||
						(method.equals(pd.getWriteMethod()) && includeWriteAttribute(method, beanKey))) {
					// Attributes need to have their methods exposed as
					// operations to the JMX server as well.
/*					info = createModelMBeanOperationInfo(method, pd.getName(), beanKey);
					Descriptor desc = info.getDescriptor();
					if (method.equals(pd.getReadMethod())) {
						desc.setField(FIELD_ROLE, ROLE_GETTER);
					}
					else {
						desc.setField(FIELD_ROLE, ROLE_SETTER);
					}
					desc.setField(FIELD_VISIBILITY, ATTRIBUTE_OPERATION_VISIBILITY);
					if (isExposeClassDescriptor()) {
						desc.setField(FIELD_CLASS, getClassForDescriptor(managedBean).getName());
					}
					info.setDescriptor(desc);*/
				}
			}
			else if (includeOperation(method, beanKey)) {
				info = createModelMBeanOperationInfo(method, method.getName(), beanKey);
				Descriptor desc = info.getDescriptor();
				desc.setField(FIELD_ROLE, ROLE_OPERATION);
				if (isExposeClassDescriptor()) {
					desc.setField(FIELD_CLASS, getClassForDescriptor(managedBean).getName());
				}
				populateOperationDescriptor(desc, method, beanKey);
				info.setDescriptor(desc);
			}

			if (info != null) {
				infos.add(info);
			}
		}

		return (ModelMBeanOperationInfo[]) infos.toArray(new ModelMBeanOperationInfo[infos.size()]);
	}

	
}
