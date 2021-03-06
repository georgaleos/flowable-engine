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

package org.flowable.engine.impl.cmd;

import java.io.Serializable;

import org.flowable.engine.common.impl.interceptor.Command;
import org.flowable.engine.common.impl.interceptor.CommandContext;
import org.flowable.engine.impl.util.CommandContextUtil;
import org.flowable.engine.impl.util.Flowable5Util;
import org.flowable.engine.repository.ProcessDefinition;

/**
 * @author Tom Baeyens
 */
public class IsFlowable5ProcessDefinitionCmd implements Command<Boolean>, Serializable {

    private static final long serialVersionUID = 1L;
    protected String processDefinitionId;

    public IsFlowable5ProcessDefinitionCmd(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    @Override
    public Boolean execute(CommandContext commandContext) {
        ProcessDefinition processDefinition = CommandContextUtil.getProcessEngineConfiguration(commandContext)
                .getDeploymentManager()
                .findDeployedProcessDefinitionById(processDefinitionId);

        if (Flowable5Util.isFlowable5ProcessDefinition(processDefinition, commandContext)) {
            return true;
        }

        return false;
    }
}
