package com.software.crm.query;

import com.software.crm.base.BaseQuery;

public class CustomerServeQuery extends BaseQuery {
    private String customerName; // 客户姓名
    private Integer serveType;  //服务类型
    private String state; // 服务状态  001 002 003 004
    private Integer assigner;

    public Integer getAssigner() {
        return assigner;
    }

    public void setAssigner(Integer assigner) {
        this.assigner = assigner;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getServeType() {
        return serveType;
    }

    public void setServeType(Integer serveType) {
        this.serveType = serveType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
