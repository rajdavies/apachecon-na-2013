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
package org.apache.cmueller.camel.apachecon.na2013;

import org.apache.camel.builder.RouteBuilder;
import org.apacheextras.camel.component.vtdxml.VtdXmlXPathBuilder;

public class VtdxmlSplitterRouteTest extends SplitBaseTest {

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() throws Exception {
                from("file://src/test/data?fileName=100M_ordersList.xml&noop=true&initialDelay=0").routeId("splitter").autoStartup(false)
                    .split(new VtdXmlXPathBuilder("/ordersList/orders"))
                        .to("mock:end")
                    .end();
            }
        };
    }
}