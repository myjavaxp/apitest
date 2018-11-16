package com.yibo.test;

import com.alibaba.fastjson.JSON;
import com.yibo.http.ApiClientUtil;
import com.yibo.http.constant.HttpHost;
import com.yibo.http.domain.common.Result;
import com.yibo.http.domain.request.ContractQuery;
import com.yibo.http.domain.response.Contracts;
import com.yibo.http.domain.response.Driver;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import static com.yibo.http.constant.CommonConstants.FEATURES;

public class ApiTest {
    private static final int CLIENT_TOTAL = 100;
    private static final int THREAD_TOTAL = 10;

    @Test
    public void test01() throws IOException {
        Result<Driver> result = ApiClientUtil.get(HttpHost.VELP_HOST + "/driver/findByUserId/11", Driver.class);
        System.out.println(JSON.toJSONString(result, FEATURES));
    }

    @Test
    public void test02() {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(THREAD_TOTAL);
        final CountDownLatch countDownLatch = new CountDownLatch(CLIENT_TOTAL);
        for (int i = 0; i < CLIENT_TOTAL; i++) {
            threadPool.execute(() -> {
                try {
                    semaphore.acquire();
                    testPost();
                    semaphore.release();
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPool.shutdown();
    }

    @Test
    public void testPost() throws IOException {
        Result<Contracts> result = ApiClientUtil.post(
                HttpHost.VELP_HOST + "/contract/query",
                Contracts.class,
                new ContractQuery(null, 1, 100));
        System.out.println(JSON.toJSONString(result, FEATURES));
    }
}
