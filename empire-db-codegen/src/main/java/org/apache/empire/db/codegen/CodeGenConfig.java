/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.empire.db.codegen;

import org.apache.empire.xml.XMLConfiguration;

public class CodeGenConfig extends XMLConfiguration
{
    private String jdbcClass = "org.hsqldb.jdbcDriver";

    private String jdbcURL = "jdbc:hsqldb:file:hsqldb/sample;shutdown=true";

    private String jdbcUser = "sa";

    private String jdbcPwd = "";
    
    // generation options
    /**
     * name of the target package
     */
    private String packageName = "org.foo.db";
    
    /**
     * Target name of the generated database class.
     * This class extends DBDatabase.
     */
    private String dbClassName    = "MyDB";
    /**
     * Target name of the generated table class.
     * This class extends DBTable and is the base class for all generated individual table classes.
     */
    private String tableBaseName  = "MyTable";
    /**
     * Target name of the generated view class.
     * This class extends DBView and is the base class for all generated individual view classes.
     */
    private String viewBaseName   = "MyView";
    /**
     * Target name of the generated record class.
     * This is a template class that extends DBRecord as follows:<br/>
     * <pre>XXRecord<T extends XXTable> extends DBRecord</pre><br/>
     */
    private String recordBaseName = "MyRecord";
    /**
     * Prefix used for generating table class names.<br/> 
     * The Table name is appended after the prefix starting with captial letter followed by lower case letters.<br/>
     * Occurrence an of underscore indicates a new word which will again start with a capital letter.<br/>
     * e.g.<br/>
     * <ul>
     *  <li>Table "names" -> Class "XXNames"</li>
     *  <li>Table "flight_bookings" -> Class "XXFlightBookings"</li>
     * </ul>
     * Where XX is the prefix.
     */
    private String tableClassPrefix = "T";
    /**
     * Prefix used for generating view class names.<br/> 
     * The Table name is appended after the prefix starting with captial letter followed by lower case letters.<br/>
     * Occurrence an of underscore indicates a new word which will again start with a capital letter.<br/>
     * See naming of table classes above.
     */
    private String viewClassPrefix  = "V";
    
    /**
     * if TRUE table classes should be declared as inner classes of DBDatabase.<br/>
     * if FALSE table classes should be declared as top level classes.
     */
    private boolean nestTables;
    /**
     * if TRUE view classes should be declared as inner classes of DBDatabase.<br/>
     * if FALSE view classes should be declared as top level classes.
     */
    private boolean nestViews;
    /**
     * if TRUE record classes should have a getter and setter for each field.<br/>
     * Otherwiese getters / setters are omitted.
     */
    private boolean createRecordProperties;
    

    /**
     * Initialize the configuration.
     * 
     * @param filename the file to read
     * 
     * @return true on succes
     */
    public boolean init(String filename)
    {
        // Read the properties file
        if (super.init(filename, false, true) == false)
            return false;
        // Done
        if (readProperties(this, "properties")==false)
            return false;
        // Reader Provider Properties
        return true;
    }
    
    public String getJdbcClass()
    {
        return jdbcClass;
    }

    public void setJdbcClass(String jdbcClass)
    {
        this.jdbcClass = jdbcClass;
    }

    public String getJdbcURL()
    {
        return jdbcURL;
    }

    public void setJdbcURL(String jdbcURL)
    {
        this.jdbcURL = jdbcURL;
    }

    public String getJdbcUser()
    {
        return jdbcUser;
    }

    public void setJdbcUser(String jdbcUser)
    {
        this.jdbcUser = jdbcUser;
    }

    public String getJdbcPwd()
    {
        return jdbcPwd;
    }

    public void setJdbcPwd(String jdbcPwd)
    {
        this.jdbcPwd = jdbcPwd;
    }
    
    // ------- generation options -------
    
    public String getPackageName()
    {
        return packageName;
    }

    public void setPackageName(String packageName)
    {
        this.packageName = packageName;
    }

    public String getDbClassName()
    {
        return dbClassName;
    }

    public void setDbClassName(String dbClassName)
    {
        this.dbClassName = dbClassName;
    }

    public String getTableBaseName()
    {
        return tableBaseName;
    }

    public void setTableBaseName(String tableBaseName)
    {
        this.tableBaseName = tableBaseName;
    }

    public String getViewBaseName()
    {
        return viewBaseName;
    }

    public void setViewBaseName(String viewBaseName)
    {
        this.viewBaseName = viewBaseName;
    }

    public String getRecordBaseName()
    {
        return recordBaseName;
    }

    public void setRecordBaseName(String recordBaseName)
    {
        this.recordBaseName = recordBaseName;
    }

    public String getTableClassPrefix()
    {
        return tableClassPrefix;
    }

    public void setTableClassPrefix(String tableClassPrefix)
    {
        this.tableClassPrefix = tableClassPrefix;
    }

    public String getViewClassPrefix()
    {
        return viewClassPrefix;
    }

    public void setViewClassPrefix(String viewClassPrefix)
    {
        this.viewClassPrefix = viewClassPrefix;
    }

    public boolean isNestTables()
    {
        return nestTables;
    }

    public void setNestTables(boolean nestTables)
    {
        this.nestTables = nestTables;
    }

    public boolean isNestViews()
    {
        return nestViews;
    }

    public void setNestViews(boolean nestViews)
    {
        this.nestViews = nestViews;
    }

    public boolean isCreateRecordProperties()
    {
        return createRecordProperties;
    }

    public void setCreateRecordProperties(boolean createRecordProperties)
    {
        this.createRecordProperties = createRecordProperties;
    }
    
    
}