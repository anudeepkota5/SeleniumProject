package com.mckinsey.lime.guiDataBeans;

import com.mckinsey.lime.apiTestBeans.ProductDetailsResponse;
import com.mckinsey.lime.testDataEnums.Gender;
import com.mckinsey.lime.testDataEnums.Smoke;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetAQuoteBean {
    private ProductDetailsResponse.IndProductDetails indProductDetails;
    private String day;
    private String month;
    private String year;
    private Gender gender;
    private Smoke smoke;

}
