package com.ms.service.response;

import com.ms.common.base.response.BaseResponst;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse extends BaseResponst {


    private String token;

}
