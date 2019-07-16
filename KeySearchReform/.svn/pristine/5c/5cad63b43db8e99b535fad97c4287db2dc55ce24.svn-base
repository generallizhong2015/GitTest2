package com.gsoft.keyhandover.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/26.
 */

public class NodeEntity {
    /**
     * flowId : 1
     * flowName : 普通编组刷卡流程
     * templateId : 1
     * templateName : 无电作业
     * nodeId : 29
     * nodeName : 电务作业结束
     * roleCode : L
     * roleName : ATP
     * cCode : 6
     * types : 0
     */

    private String flowId;
    private String flowName;
    private String templateId;
    private String templateName;
    private String nodeId;
    private String nodeName;
    private String roleCode;
    private String roleName;
    private List<String> cCode = new ArrayList<>();
    private boolean types;

    private int state = 1;  //  节点状态：1、他人未完成（不可做）  2、本人未完成（不可做）  3、他人完成 （不可做） 4、本人完成（不可做）  5、他人未完成（可做）    6、本人未完成（可做）    7、本人完成（可做）
    private List<String> userNames = new ArrayList<>();
    //    private String userName;
    private String time;
    private int rule;

    private String beginTemplate;
    private String beginNode;
    private String endTemplate;
    private String endNode;


    public int getRule() {
        return rule;
    }

    public void setRule(int rule) {
        this.rule = rule;
    }

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

    public List<String> getCCode() {
        return cCode;
    }

    public void setCCode(String cCode) {
        this.cCode.add(cCode);
    }

    public boolean isTypes() {
        return types;
    }

    public void setTypes(boolean types) {
        this.types = types;
    }

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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<String> getUserName() {
        return userNames;
    }

    public void setUserName(String userName) {
        this.userNames.add(userName);
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
