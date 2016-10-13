package es.pode.parseadorXML.dozer;

import es.pode.parseadorXML.castor.types.ExitConditionRuleActionType;
import net.sf.dozer.util.mapping.BeanFactoryIF;

public class ExitConditionRuleActionTypeFactory implements BeanFactoryIF {

	public Object createBean(Object arg0, Class arg1, String arg2) {
		
		return ExitConditionRuleActionType.EXIT;
	}

}
