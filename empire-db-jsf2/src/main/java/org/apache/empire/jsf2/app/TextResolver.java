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
package org.apache.empire.jsf2.app;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.empire.exceptions.EmpireException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TextResolver
{
    private static final Logger log = LoggerFactory.getLogger(TextResolver.class);
    
    public static String MSG_KEY_INDICATOR = "!";

    private final ResourceBundle resBundle;

    public TextResolver(ResourceBundle resBundle)
    {
        this.resBundle = resBundle;
    }
    
    public final ResourceBundle getResourceBundle()
    {
        return resBundle;
    }
    
    public final Locale getLocale()
    {
        return resBundle.getLocale();
    }

    public String resolveKey(String key)
    {
        try
        {
            String res = resBundle.getString(key);
            if (res==null)
                throw new MissingResourceException("Message Key not found.", String.class.getSimpleName(), key);
            return res;
        }
        catch (MissingResourceException e)
        {
            log.error("Message key missing '{}'.", key);
            return "["+key+"]";
        }
        catch (Exception e)
        {
            log.error("Error resolving text: {}", e);
            return "["+key+"]";
        }
    }
    
    public String resolveText(String text)
    {
        // Translate
        if (text != null && text.startsWith(MSG_KEY_INDICATOR))
        {
            String key = text.substring(1);
            return resolveKey(key);
        }
        return text;
    }
    
    public String getExceptionMessage(Exception e)
    {
        if (e instanceof EmpireException)
        {
            EmpireException ee = (EmpireException)e;
            String key = ee.getErrorType().getKey();
            // get Pattern 
            String pattern;
            if (resBundle.containsKey(key))
            {   // Get Pattern
                pattern = resBundle.getString(key); 
            }
            else
            {   // No error message pattern provided. Using default
                pattern = ee.getErrorType().getMessagePattern();
                log.error("Error resolving error messsage pattern: {}", key);
            }
            // get Params
            String[] params = ee.getErrorParams();
            Object[] values = null;
            if (params!=null)
            {   values = new Object[params.length];
                for (int i=0; i<params.length; i++)
                    values[i] = resolveText(params[i]);
            }
            // Format message
            return MessageFormat.format(pattern, values);            
        }
        else
        {   // Other exception try to resolve by class name
            String key = "exception."+e.getClass().getName();
            if (resBundle.containsKey(key))
                return resBundle.getString(key);
            // not provided
            return e.getLocalizedMessage();
        }
    }
}
