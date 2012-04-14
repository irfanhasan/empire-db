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
package org.apache.empire.jsf2.components;

import java.io.IOException;
import java.util.Map;

import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.apache.empire.commons.StringUtils;
import org.apache.empire.jsf2.controls.FieldRenderer;
import org.apache.empire.jsf2.utils.TagRenderHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValueTag extends UIOutput // implements NamingContainer
{

    // Logger
    private static final Logger log = LoggerFactory.getLogger(ValueTag.class);
    
    private TagRenderHelper helper = new TagRenderHelper(this, "eVal");

    public ValueTag()
    {
        log.trace("component value created");
    }

    @Override
    public String getFamily()
    {
        return "javax.faces.NamingContainer";
    }

    @Override
    public void encodeBegin(FacesContext context)
        throws IOException
    {

        // add label and input components when the view is loaded for the first time
        super.encodeBegin(context);
        
        helper.encodeBegin();
        FieldRenderer renderer = helper.getFieldRenderer();
        FieldRenderer.ValueInfo vi = helper.getValueInfo(context);

        // render components
        ResponseWriter writer = context.getResponseWriter();
        String tag = writeStartElement(vi, writer);
        renderer.renderValue(vi, writer);
        if (tag != null)
            writer.endElement(tag);
    }

    protected String writeStartElement(FieldRenderer.ValueInfo vi, ResponseWriter writer)
        throws IOException
    {
        Map<String, Object> map = getAttributes();
        String tag   = StringUtils.toString(map.get("tag"));
        String title = StringUtils.toString(map.get("title"));
        // Check
        if (tag == null && title == null && !map.containsKey("styleClass"))
            return null;
        // Write tag
        if (StringUtils.isEmpty(tag))
            tag="span";
        writer.startElement(tag, this);
        helper.writeAttribute(writer, "class", helper.getTagStyleClass());
        helper.writeAttribute(writer, "style", map.get("style"));
        helper.writeAttribute(writer, "title", helper.getValueTooltip(title));
        return tag;
    }

}
