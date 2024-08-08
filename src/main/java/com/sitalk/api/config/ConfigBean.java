package com.sitalk.api.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "app")
public class ConfigBean {
    private String serviceUrl;
    private String platformLogin;
    //private String servicePort;
    private String jwtSecret;

    private String ciPipelineId = "profile";
    private String ciCommitShortSha = "profile";
    private String ciCommitBranch = "profile";
}
