package com.ms.common.base.response;

import com.ms.common.utils.GsonUtils;

public class BaseResponst {
    public String toJson() {
        return GsonUtils.getGson().toJson(this);
    }
}