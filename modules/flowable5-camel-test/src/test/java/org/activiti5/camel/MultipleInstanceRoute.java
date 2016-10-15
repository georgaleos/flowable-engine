/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.activiti5.camel;

import java.util.List;

import org.activiti.engine.test.Deployment;
import org.activiti5.spring.impl.test.SpringActivitiTestCase;
import org.apache.camel.CamelContext;
import org.apache.camel.Route;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration("classpath:generic-camel-activiti-context.xml")
public class MultipleInstanceRoute extends SpringActivitiTestCase {

  @Autowired
  protected CamelContext camelContext;

  public void setUp() throws Exception {
    camelContext.addRoutes(new RouteBuilder() {

      @Override
      public void configure() throws Exception {
        from("activiti:multiInstanceCamelProcess:servicetask1").to("log:logMessage");
      }
    });
  }

  public void tearDown() throws Exception {
    List<Route> routes = camelContext.getRoutes();
    for (Route r : routes) {
      camelContext.stopRoute(r.getId());
      camelContext.removeRoute(r.getId());
    }
  }

  @Deployment(resources = { "process/multiInstanceCamel.bpmn20.xml" })
  public void testCamelBody() throws Exception {
    runtimeService.startProcessInstanceByKey("multiInstanceCamelProcess");
  }
}