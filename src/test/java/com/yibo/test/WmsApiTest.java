package com.yibo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yibo.http.ApiClientUtil;
import com.yibo.http.constant.HttpHost;
import com.yibo.http.domain.common.Request;
import com.yibo.http.domain.common.Result;
import com.yibo.test.common.BaseTest;
import org.junit.Test;

import java.io.IOException;

import static com.yibo.http.constant.CommonConstants.FEATURES;

public class WmsApiTest extends BaseTest {

    @Test
    public void test02() throws IOException {
        JSONObject reqData = new JSONObject();
        reqData.put("whseId", "1044");
        reqData.put("pageNum", "1");
        reqData.put("pageRows", "1000");
        Request<JSONObject> request = new Request<>(token, reqData);
        System.out.println();
        Result<JSONObject> result = ApiClientUtil.post(
                HttpHost.WMS_TEST_HOST + "/wms/biz/warehouseswitch/query",
                request);
        System.out.println(JSON.toJSONString(result, FEATURES));
    }
}
