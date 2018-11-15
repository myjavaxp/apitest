package com.yibo.http.domain.response;

import java.time.LocalDate;
import java.util.StringJoiner;

public class SimpleContract {
    private Long id;
    private Long supplierId;
    private String supplierName;
    private String contractNo;
    private LocalDate startTime;
    private LocalDate endTime;
    private String startToEnd;
    private int flag = 0;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getStartToEnd() {
        return startToEnd;
    }

    public void setStartToEnd(String startToEnd) {
        this.startToEnd = startToEnd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SimpleContract.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("supplierId=" + supplierId)
                .add("supplierName='" + supplierName + "'")
                .add("contractNo='" + contractNo + "'")
                .add("startTime=" + startTime)
                .add("endTime=" + endTime)
                .add("startToEnd='" + startToEnd + "'")
                .add("flag=" + flag)
                .toString();
    }
}
