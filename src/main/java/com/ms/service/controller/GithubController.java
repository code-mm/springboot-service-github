package com.ms.service.controller;


import com.ms.common.base.ApiResult;
import com.ms.common.base.ApiResultCodeMessage;
import com.ms.common.base.exception.AppException;
import com.ms.common.utils.GsonUtils;
import com.ms.common.utils.MD5Utils;
import com.ms.service.component.GitHubProvider;
import com.ms.service.dto.GithubAccessTokenDTO;
import com.ms.service.dto.GithubUserInfo;

import com.ms.service.response.LoginResponse;
import com.ms.service.utils.ConstantGithubUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
@RequestMapping("github/oauth")
@Slf4j
public class GithubController {

    @Autowired
    private GitHubProvider gitHubProvider;


    /**
     * github 授权渲染
     *
     * @param response
     * @throws Exception
     */
    @GetMapping("/render")
    public void render(HttpServletResponse response, HttpSession session) throws Exception {

        String baseAccessTokenUrl = "https://github.com/login/oauth/authorize?" +
                "client_id=%s" +
                "&redirect_uri=%s" +
                "&scope=%s" +
                "&state=%s";

        String state = MD5Utils.stringToMD5(UUID.randomUUID().toString());
        session.setAttribute("github_state", state);

        //拼接三个参数 ：id  秘钥 和 code值
        String accessTokenUrl = String.format(
                baseAccessTokenUrl,
                ConstantGithubUtils.GITHUB_CLIENT_ID,
                ConstantGithubUtils.GITHUB_REDIRECT_URL,
                "user",
                state
        );
        response.sendRedirect(accessTokenUrl);
    }

    /**
     * github 授权回调
     *
     * @param response
     * @param code
     * @param state
     * @return
     */
    @GetMapping("/callback")
    public ApiResult<LoginResponse> callback(HttpServletResponse response, HttpSession session, @RequestParam(name = "code") String code,
                                             @RequestParam(name = "state") String state) {

        String github_state = (String) session.getAttribute("github_state");

        if (!state.equals(github_state)) {
            throw new AppException(ApiResultCodeMessage.GITHUB_ILLEGAL_CALLBACK);
        }

        ApiResult<LoginResponse> result = new ApiResult<>();
        GithubAccessTokenDTO accessTokenDTO = new GithubAccessTokenDTO();
        accessTokenDTO.setClient_id(ConstantGithubUtils.GITHUB_CLIENT_ID);
        accessTokenDTO.setClient_secret(ConstantGithubUtils.GITHUB_CLIENT_SECRET);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(ConstantGithubUtils.GITHUB_REDIRECT_URL);
        accessTokenDTO.setState(state);

        //进行doPost请求，获取access_token
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);

        log.info("accessToken : " + accessToken);

        if (accessToken != null) {

            //拆分获得需要的access_token值
            String[] split = accessToken.split("&");
            String token = split[0].split("=")[1];

            if (accessToken.startsWith("access_token")) {
                result.setResultCodeMessage(ApiResultCodeMessage.SUCCESS);
                result.setData(LoginResponse.builder().token(token).build());
                GithubUserInfo info = gitHubProvider.getInfo(token);

                // github userinfo

                log.info(GsonUtils.getGson().toJson(info));

                // 获取github open ID
                long githubOpenId = info.getId();

                log.info("github openid: " + githubOpenId);

                // 此处返回的应该是自己系统的TOKEN
                result.setData(LoginResponse.builder().token(token).build());

            } else {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                result.setResultCodeMessage(ApiResultCodeMessage.GITHUB_BAD_VERIFICATION_CODE);
            }
        }
        return result;
    }
}