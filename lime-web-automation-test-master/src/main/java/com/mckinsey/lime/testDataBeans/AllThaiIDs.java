package com.mckinsey.lime.testDataBeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Deprecated
public class AllThaiIDs {
    private List<String> dev;
    private List<String> alpha;
    private List<String> devSet2;

}
