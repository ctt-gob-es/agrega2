/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.uportal.org/license.html
 */
package org.jasig.cas.util;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.quartz.Scheduler;

/**
 * Test case for {@link org.jasig.cas.util.AutowiringSchedulerFactoryBean} class.
 *
 * @author Marvin S. Addison
 * @version $Revision: $ $Date: $
 * @since 3.3.3
 *
 */
public class AutowiringSchedulerFactoryBeanTests extends TestCase {
    private ApplicationContext context;

    private Scheduler scheduler;


    protected void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext(new String[] {
            "applicationContext.xml"});

        this.scheduler = (Scheduler) context.getBean("autowiringSchedulerFactoryBean");
        this.scheduler.start();

    }

    public void testAfterPropertiesSet() throws Exception {
        assertEquals(1, this.scheduler.getTriggerNames(Scheduler.DEFAULT_GROUP).length);
    }
}
