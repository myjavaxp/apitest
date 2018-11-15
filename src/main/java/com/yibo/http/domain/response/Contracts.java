package com.yibo.http.domain.response;

import java.util.List;
import java.util.StringJoiner;

public class Contracts {
    private List<SimpleContract> contractList;
    private int total;

    public Contracts() {
    }

    public Contracts(List<SimpleContract> contractList, int total) {
        this.contractList = contractList;
        this.total = total;
    }

    public List<SimpleContract> getContractList() {
        return contractList;
    }

    public void setContractList(List<SimpleContract> contractList) {
        this.contractList = contractList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Contracts.class.getSimpleName() + "[", "]")
                .add("contractList=" + contractList)
                .add("total=" + total)
                .toString();
    }
}
