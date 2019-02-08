package com.mckinsey.lime.apiTestBeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Deprecated
public class SauceLabsFullJobDetailsResponse extends BaseResponse {

    private String browser_short_version;
    private String video_url;
    private String creation_time;
    @JsonProperty("custom-data")
    private String customData;
    private String browser_version;
    private String owner;
    private String id;
    private String record_screenshots;
    private String record_video;
    private String build;
    private String passed;
    @JsonProperty("public")
    private String jobVisibility;
    private String end_time;
    private String status;
    private String log_url;
    private String start_time;
    private String proxied;
    private String modification_time;
    //    private String tags;
    private String name;
    private String commands_not_successful;
    private String consolidated_status;
    private String assigned_tunnel_id;
    private String error;
    private String os;
    private String breakpointed;
    private String browser;

}
