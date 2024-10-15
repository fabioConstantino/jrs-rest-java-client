/*
 * Copyright (C) 2005 - 2014 Jaspersoft Corporation. All rights  reserved.
 * http://www.jaspersoft.com.
 *
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License  as
 * published by the Free Software Foundation, either version 3 of  the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero  General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public  License
 * along with this program.&nbsp; If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.jasperserver.jaxrs.client.apiadapters.resources.compexResourcesSupport.decorator;

import com.jaspersoft.jasperserver.dto.resources.ClientDomainTopic;
import com.jaspersoft.jasperserver.jaxrs.client.apiadapters.resources.compexResourcesSupport.processor.CommonOperationProcessorImpl;
import com.jaspersoft.jasperserver.jaxrs.client.apiadapters.resources.util.ResourceServiceParameter;
import com.jaspersoft.jasperserver.jaxrs.client.core.SessionStorage;
import com.jaspersoft.jasperserver.jaxrs.client.core.UrlUtils;
import com.jaspersoft.jasperserver.jaxrs.client.core.operationresult.OperationResult;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.MultivaluedMap;

/**
 * @author Alexander Krasnyanskiy
 */
public class DomainTopicOperationProcessorDecorator {

    protected CommonOperationProcessorImpl<ClientDomainTopic> processor;
    protected ClientDomainTopic domainTopic;
    protected FormDataMultiPart multipart;
    protected String path;
    private MultivaluedMap<String, String> params = new MultivaluedHashMap<>();

    public DomainTopicOperationProcessorDecorator parameter(ResourceServiceParameter param, String value) {
        params.add(param.getName(), UrlUtils.encode(value));
        return this;
    }

    public DomainTopicOperationProcessorDecorator parameter(ResourceServiceParameter param, Boolean value) {
        params.add(param.getName(), value.toString());
        return this;
    }

    public DomainTopicOperationProcessorDecorator parameter(String param, String value) {
        params.add(param, UrlUtils.encode(value));
        return this;
    }

    public DomainTopicOperationProcessorDecorator(ClientDomainTopic domainTopic, SessionStorage sessionStorage) {
        this.processor = new CommonOperationProcessorImpl(domainTopic, domainTopic.getClass(), sessionStorage);
        this.multipart = new FormDataMultiPart();
        this.domainTopic = domainTopic;
    }

    public DomainTopicOperationProcessorDecorator inFolder(String parentFolder) {
        this.path = parentFolder;
        return this;
    }

    public OperationResult<ClientDomainTopic> create() {
        return processor.create(multipart, new MediaType("application", "repository.domainTopic+json"), path, params);
    }

    public OperationResult<ClientDomainTopic> createOrUpdate() {
        return processor.createOrUpdate(multipart, new MediaType("application", "repository.domainTopic+json"), domainTopic.getUri(), params);
    }

}
