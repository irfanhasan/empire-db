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
package org.apache.empire.jsf2.websample.web;

import javax.faces.context.FacesContext;

import org.apache.empire.commons.StringUtils;

import com.sun.faces.application.NavigationHandlerImpl;

public class SampleNavigationHandler extends NavigationHandlerImpl {

    @Override
    public void handleNavigation(FacesContext context, String fromAction,
                                 String outcome) {
        if (!StringUtils.isEmpty(outcome)) {
            String logicalOutcome = outcome;
            // FIXME this is ugly
            if (logicalOutcome.endsWith("WithPreview")) {
                logicalOutcome = logicalOutcome.replace("WithPreview", "");
            }
            FacesUtils.setPage(logicalOutcome);
        }
        super.handleNavigation(context, fromAction, outcome);
    }

}