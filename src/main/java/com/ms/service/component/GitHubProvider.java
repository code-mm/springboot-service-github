package com.ms.service.component;

import com.ms.service.dto.GithubAccessTokenDTO;
import com.ms.service.dto.GithubUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Slf4j
public class GitHubProvider {
    public String getAccessToken(GithubAccessTokenDTO accessTokenDTO) {

        return WebClient.create()
                .post().uri("https://github.com/login/oauth/access_token")
                .syncBody(accessTokenDTO)
                .retrieve().bodyToMono(String.class).block();
    }

    public GithubUserInfo getInfo(String token) {
        return WebClient.create().get().uri("https://api.github.com/user")
                .header("Authorization", "token " + token)
                .retrieve()
                .bodyToMono
                        (GithubUserInfo.class)
                .log()
                .block();
    }
}

