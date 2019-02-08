package com.mckinsey.lime.apiTestBeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
//ToDo: Need to update
@Deprecated
public class BillDetailsResponse extends BaseResponse {

}
