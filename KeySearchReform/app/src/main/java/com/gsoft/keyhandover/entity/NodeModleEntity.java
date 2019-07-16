package com.gsoft.keyhandover.entity;

import java.util.List;

/**
 * Created by Administrator on 2018/3/3.
 */

public class NodeModleEntity {


    private List<List1Bean> list1;
    private List<List2Bean> list2;
    private List<List3Bean> list3;

    public List<List1Bean> getList1() {
        return list1;
    }

    public void setList1(List<List1Bean> list1) {
        this.list1 = list1;
    }

    public List<List2Bean> getList2() {
        return list2;
    }

    public void setList2(List<List2Bean> list2) {
        this.list2 = list2;
    }

    public List<List3Bean> getList3() {
        return list3;
    }

    public void setList3(List<List3Bean> list3) {
        this.list3 = list3;
    }

    public static class List1Bean {
        /**
         * nodeId : 28
         * nodeName : 电务作业开始
         */

        private String nodeId;
        private String nodeName;

        public String getNodeId() {
            return nodeId;
        }

        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        public String getNodeName() {
            return nodeName;
        }

        public void setNodeName(String nodeName) {
            this.nodeName = nodeName;
        }
    }

    public static class List2Bean {
        /**
         * flowId : 1
         * flowName : 普通编组刷卡流程
         * templateId : 7
         * templateName : 电务通信作业
         * nodeId : 28
         * nodeName : 电务作业开始
         * roleCode : L
         * roleName : ATP
         * cNodeId : 18
         */

        private String flowId;
        private String flowName;
        private String templateId;
        private String templateName;
        private String nodeId;
        private String nodeName;
        private String roleCode;
        private String roleName;
        private String cNodeId;

        public String getFlowId() {
            return flowId;
        }

        public void setFlowId(String flowId) {
            this.flowId = flowId;
        }

        public String getFlowName() {
            return flowName;
        }

        public void setFlowName(String flowName) {
            this.flowName = flowName;
        }

        public String getTemplateId() {
            return templateId;
        }

        public void setTemplateId(String templateId) {
            this.templateId = templateId;
        }

        public String getTemplateName() {
            return templateName;
        }

        public void setTemplateName(String templateName) {
            this.templateName = templateName;
        }

        public String getNodeId() {
            return nodeId;
        }

        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        public String getNodeName() {
            return nodeName;
        }

        public void setNodeName(String nodeName) {
            this.nodeName = nodeName;
        }

        public String getRoleCode() {
            return roleCode;
        }

        public void setRoleCode(String roleCode) {
            this.roleCode = roleCode;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        public String getCNodeId() {
            return cNodeId;
        }

        public void setCNodeId(String cNodeId) {
            this.cNodeId = cNodeId;
        }
    }

    public static class List3Bean {
        /**
         * beginTemplate : 2
         * beginNode : 11
         * endTemplate : 2
         * endNode : 12
         */

        private String beginTemplate;
        private String beginNode;
        private String endTemplate;
        private String endNode;

        public String getBeginTemplate() {
            return beginTemplate;
        }

        public void setBeginTemplate(String beginTemplate) {
            this.beginTemplate = beginTemplate;
        }

        public String getBeginNode() {
            return beginNode;
        }

        public void setBeginNode(String beginNode) {
            this.beginNode = beginNode;
        }

        public String getEndTemplate() {
            return endTemplate;
        }

        public void setEndTemplate(String endTemplate) {
            this.endTemplate = endTemplate;
        }

        public String getEndNode() {
            return endNode;
        }

        public void setEndNode(String endNode) {
            this.endNode = endNode;
        }
    }
}
