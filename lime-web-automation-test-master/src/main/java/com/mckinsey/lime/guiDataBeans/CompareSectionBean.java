package com.mckinsey.lime.guiDataBeans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CompareSectionBean {
    private String header;
    private String textLeft;
    private String textRight;
    private Boolean isInfoIcon;
    //TODO: Need to add info popup fields

}
