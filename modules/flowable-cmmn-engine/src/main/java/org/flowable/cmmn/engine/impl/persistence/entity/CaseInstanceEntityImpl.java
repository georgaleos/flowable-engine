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
package org.flowable.cmmn.engine.impl.persistence.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flowable.cmmn.engine.impl.util.CommandContextUtil;
import org.flowable.engine.common.impl.persistence.entity.AbstractEntity;

/**
 * @author Joram Barrez
 */
public class CaseInstanceEntityImpl extends AbstractEntity implements CaseInstanceEntity {
    
    protected String businessKey;
    protected String name;
    protected String parentId;
    protected String caseDefinitionId;
    protected String state;
    protected Date startTime;
    protected String startUserId;
    protected String callbackId;
    protected String callbackType;
    protected String tenantId;
    
    // non persisted
    protected List<PlanItemInstanceEntity> childPlanItemInstances;
    protected List<SentryOnPartInstanceEntity> satisfiedSentryOnPartInstances;
    
    public Object getPersistentState() {
        Map<String, Object> persistentState = new HashMap<>();
        persistentState.put("businessKey", businessKey);
        persistentState.put("name", name);
        persistentState.put("parentId", parentId);
        persistentState.put("caseDefinitionId", caseDefinitionId);
        persistentState.put("state", state);
        persistentState.put("startTime", startTime);
        persistentState.put("startUserId", startUserId);
        persistentState.put("callbackId", callbackId);
        persistentState.put("callbackType", callbackType);
        persistentState.put("tenantId", tenantId);
        return persistentState;
    }
    
    public String getBusinessKey() {
        return businessKey;
    }
    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getParentId() {
        return parentId;
    }
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    public String getCaseDefinitionId() {
        return caseDefinitionId;
    }
    public void setCaseDefinitionId(String caseDefinitionId) {
        this.caseDefinitionId = caseDefinitionId;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public String getStartUserId() {
        return startUserId;
    }
    public void setStartUserId(String startUserId) {
        this.startUserId = startUserId;
    }
    public String getCallbackId() {
        return callbackId;
    }
    public void setCallbackId(String callbackId) {
        this.callbackId = callbackId;
    }
    public String getCallbackType() {
        return callbackType;
    }
    public void setCallbackType(String callbackType) {
        this.callbackType = callbackType;
    }
    public String getTenantId() {
        return tenantId;
    }
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
    
    @Override
    public List<PlanItemInstanceEntity> getChildPlanItemInstances() {
        if (childPlanItemInstances == null) {
            childPlanItemInstances = CommandContextUtil.getPlanItemInstanceEntityManager().findChildPlanItemInstancesForCaseInstance(id);
        }
        return childPlanItemInstances;
    }
    
    @Override
    public void setChildPlanItemInstances(List<PlanItemInstanceEntity> childPlanItemInstances) {
        this.childPlanItemInstances = childPlanItemInstances;
    }
    
    @Override
    public List<SentryOnPartInstanceEntity> getSatisfiedSentryOnPartInstances() {
        if (satisfiedSentryOnPartInstances == null) {
            satisfiedSentryOnPartInstances = CommandContextUtil.getSentryOnPartInstanceEntityManager()
                    .findSentryOnPartInstancesByCaseInstanceIdAndNullPlanItemInstanceId(id);
        }
        return satisfiedSentryOnPartInstances;
    }
    
    @Override
    public void setSatisfiedSentryOnPartInstances(List<SentryOnPartInstanceEntity> sentryOnPartInstanceEntities) {
        this.satisfiedSentryOnPartInstances = sentryOnPartInstanceEntities;
    }
    
}
