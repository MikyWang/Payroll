package com.lejiyu.payroll.Entity;

public class RequireRaise {
    private Integer formId;

    private Long requirerId;

    private Long managerId;

    private String status;

    private Long requireMoney;

    private String content;

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public Long getRequirerId() {
        return requirerId;
    }

    public void setRequirerId(Long requirerId) {
        this.requirerId = requirerId;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getRequireMoney() {
        return requireMoney;
    }

    public void setRequireMoney(Long requireMoney) {
        this.requireMoney = requireMoney;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}