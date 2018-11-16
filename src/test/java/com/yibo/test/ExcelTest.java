package com.yibo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yibo.http.ApiClientUtil;
import com.yibo.http.ExcelUtil;
import com.yibo.http.constant.CommonConstants;
import com.yibo.http.constant.HttpHost;
import com.yibo.http.domain.common.Request;
import com.yibo.http.domain.common.Result;
import com.yibo.http.function.ReadStrategy;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelTest extends WmsApiTest {
    @Test
    public void testExcel() throws IOException {
        List<JSONObject> reqData = ExcelUtil.getReqData("/Users/yibo/Downloads/abc.xlsx", null);
        assert reqData != null;
        for (JSONObject data : reqData) {
            login(data);
        }
    }

    private void login(JSONObject reqData) throws IOException {
        Request<JSONObject> request = new Request<>(reqData);
        Result<JSONObject> result = ApiClientUtil.post(
                HttpHost.WMS_TEST_HOST + "/wms/auth/user/noauth/login",
                request);
        System.out.println(JSON.toJSONString(result, CommonConstants.FEATURES));
    }

    @Test
    public void auto() throws IOException {
        ExcelUtil.autoTest("/Users/yibo/Downloads/abc.xlsx",
                null,
                token,
                HttpHost.WMS_TEST_HOST + "/wms/biz/warehouseswitch/query");
    }

    @Test
    public void testStrategy() throws IOException {
        ReadStrategy<JSONObject> strategy = list -> {
            List<JSONObject> result = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (i % 2 == 0) {
                    result.add(list.get(i));
                }
            }
            return result;
        };
        ExcelUtil.autoTest(
                "/Users/yibo/Downloads/abc.xlsx",
                null,
                token,
                HttpHost.WMS_TEST_HOST + "/wms/biz/warehouseswitch/query",
                strategy);
    }
}