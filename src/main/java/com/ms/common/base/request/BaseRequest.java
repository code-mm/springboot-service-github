package com.ms.common.base.request;

import com.ms.common.utils.GsonUtils;

public class BaseRequest {

    public String toJson() {
        return GsonUtils.getGson().toJson(this);
    }
}
