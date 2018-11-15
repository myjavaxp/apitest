package com.yibo.http.domain.request;


public class ContractQuery {
    private String supplierName;
    private int pageNo;
    private int pageSize;

    public ContractQuery(String supplierName, int pageNo, int pageSize) {
        this.supplierName = supplierName;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public ContractQuery() {
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
