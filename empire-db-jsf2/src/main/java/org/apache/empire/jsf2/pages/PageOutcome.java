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
package org.apache.empire.jsf2.pages;

import org.apache.empire.commons.StringUtils;

public class PageOutcome
{
    private String outcome;
    public PageOutcome(String outcome)
    {
        if (StringUtils.isEmpty(outcome))
            throw new RuntimeException("Illegal Outcome Value");
        this.outcome = outcome;
    }
    
    public PageOutcome addParamWithValue(String paramWithValue)
    {
        if (paramWithValue==null || paramWithValue.indexOf('=')<0)
            throw new RuntimeException("Illegal Parameter Value");
        // assemble
        if (outcome.indexOf('?')>0)
            outcome = outcome+"&"+paramWithValue;
        else
            outcome = outcome+"?"+paramWithValue;
        return this;
    }
    
    public PageOutcome addParam(String param, String value)
    {
        if (StringUtils.isEmpty(value))
            return this; // Ignore Empty values
        if (StringUtils.isEmpty(param))
            throw new RuntimeException("Illegal Parameter Name");
        String paramWithValue = param + "=" + value;
        return addParamWithValue(paramWithValue);
    }
    
    @Override
    public String toString()
    {
        return outcome;
    }
}
