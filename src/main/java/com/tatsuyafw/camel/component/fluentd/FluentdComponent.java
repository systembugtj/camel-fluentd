/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tatsuyafw.camel.component.fluentd;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.impl.UriEndpointComponent;
import org.apache.camel.util.ObjectHelper;

import java.net.URI;
import java.util.Map;

public class FluentdComponent extends UriEndpointComponent {

    private FluentdConfiguration configuration;

    public FluentdComponent() {
        super(FluentdEndpoint.class);
        configuration = new FluentdConfiguration();
    }

    public FluentdComponent(CamelContext context) {
        super(context, FluentdEndpoint.class);
        configuration = new FluentdConfiguration();
    }

    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        URI url = new URI(uri);

        ObjectHelper.notNull(configuration, "configuration");
        FluentdConfiguration config = configuration.copy();
        config.configure(url);
        setProperties(config, parameters);

        FluentdEndpoint endpoint = new FluentdEndpoint(uri, this);
        endpoint.setConfiguration(config);
        setProperties(endpoint, parameters);

        return endpoint;
    }
}
