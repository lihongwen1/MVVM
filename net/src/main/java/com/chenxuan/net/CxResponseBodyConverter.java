package com.chenxuan.net;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class CxResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    CxResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        BasicResponse basicResponse = gson.fromJson(response, BasicResponse.class);
        // 这里只是为了检测code是否!=0,所以只解析HttpStatus中的字段,因为只要code和message就可以了
        if (basicResponse.getErrorCode() != BasicResponseCode.SUCCESS) {
            value.close();
            throw new ApiException(basicResponse.getErrorCode(), basicResponse.getErrorMsg());
        }
        try {
            return adapter.fromJson(response);
        } finally {
            value.close();
        }
    }
}
