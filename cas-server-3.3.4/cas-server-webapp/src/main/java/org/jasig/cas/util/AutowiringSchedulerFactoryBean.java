/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package org.jasig.cas.util;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Trigger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Extension of {@link SchedulerFactoryBean} that collects trigger bean
 * definitions from the application context and calls
 * {@link #setTriggers(org.quartz.Trigger[])} to autowire triggers at
 * {@link #afterPropertiesSet()} time.
 *
 * @author Marvin S. Addison
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.3.4
 **/
public final class AutowiringSchedulerFactoryBean extends SchedulerFactoryBean implements ApplicationContextAware, InitializingBean {

    private final Log log = LogFactory.getLog(getClass());
//	private Logger log= Logger.getLogger(this.getClass());

    private ApplicationContext applicationContext;

    public void afterPropertiesSet() throws Exception {
        final Map<String,Trigger> triggers = (Map<String,Trigger>) this.applicationContext.getBeansOfType(Trigger.class);
        super.setTriggers(triggers.values().toArray(new Trigger[triggers.size()]));

        if (log.isDebugEnabled()) {
            log.debug("Autowired the following triggers defined in application context: " + triggers.keySet().toString());
        }

        super.afterPropertiesSet();
    }

    public void setApplicationContext(final ApplicationContext applicationContext) {
        super.setApplicationContext(applicationContext);
        this.applicationContext = applicationContext;
    }
}
