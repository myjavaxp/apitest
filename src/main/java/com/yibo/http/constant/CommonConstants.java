package com.yibo.http.constant;

import com.alibaba.fastjson.serializer.SerializerFeature;

public class CommonConstants {
    public static final SerializerFeature[] FEATURES = new SerializerFeature[]{
            SerializerFeature.PrettyFormat,
            SerializerFeature.WriteNullListAsEmpty,
            SerializerFeature.WriteNullStringAsEmpty,
            SerializerFeature.WriteNonStringKeyAsString,
            SerializerFeature.WriteMapNullValue};
}