package com.yibo.test;

import com.yibo.http.ApiClientUtil;
import com.yibo.http.HttpHost;
import com.yibo.http.domain.common.Result;
import com.yibo.http.domain.request.ContractQuery;
import com.yibo.http.domain.response.Contracts;
import com.yibo.http.domain.response.Driver;
import org.junit.Test;

import java.io.IOException;

public class ApiTest {
    @Test
    public void test01() throws IOException {
        Result<Driver> result = ApiClientUtil.get(HttpHost.VELP_HOST + "/driver/findByUserId/11", Driver.class);
        System.out.println(result);
    }

    @Test
    public void test02() throws IOException {
        Result<Contracts> result = ApiClientUtil.post(
                HttpHost.VELP_HOST + "/contract/query",
                Contracts.class,
                new ContractQuery(null, 1, 100));
        System.out.println(result);
    }
}
