/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.migracion;

import java.io.File;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Abstract base class for test cases.
 *
 * @author <a href="jason@zenplex.com">Jason van Zyl</a>
 */
public abstract class AbstractTestCase
    extends TestCase 
{
    /** 
     * Basedir for all file I/O. Important when running tests from
     * the reactor.
     */
    public String basedir = System.getProperty("basedir");
    
    /**
     * Constructor.
     */
    public AbstractTestCase(String testName)
    {
        super(testName);
    }
    
    /**
     * Get test input file.
     *
     * @param path Path to test input file.
     */
    public String getTestFile(String path)
    {
        return new File(basedir,path).getAbsolutePath();
    }
}

