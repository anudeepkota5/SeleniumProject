package com.mckinsey.lime.guiDataBeans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductCardBean {
    private String productHeader;
    private String productDescription;
    private Boolean isFooterLink;
    private String productTitle;
    private String productSubTitle;
}
