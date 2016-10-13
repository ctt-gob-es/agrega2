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

import java.util.Calendar;
import java.util.Date;


public final class CalendarUtils {
    
    public static final String[] WEEKDAYS = new String[] {"UNDEFINED", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    
    private CalendarUtils() {
        // nothing to do
    }
    
    public static int getCurrentDayOfWeek() {
        return getCurrentDayOfWeekFor(new Date());
    }
    
    public static int getCurrentDayOfWeekFor(final Date date) {
        return  getCalendarFor(date).get(Calendar.DAY_OF_WEEK);
    }
    
    public static Calendar getCalendarFor(final Date date) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
}
