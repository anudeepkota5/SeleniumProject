package com.mckinsey.lime.mobileTestFactories;

import com.mckinsey.lime.testDataBeans.FilePaths;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import com.mckinsey.lime.utils.CustomExcelStrings;
import com.mckinsey.lime.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.testng.annotations.DataProvider;

import java.util.List;

public class DataProviders {
    private static final UsersDataBean apiUsersData = TestData.getApiUsersData();

    @DataProvider(name = "dp", parallel = true)
    public static Object[][] dataProvider() {
        List<UsersDataBean.User> users = apiUsersData.getUsers();
        Object[][] dataArray = new Object[users.size()][1];
        for (int i = 0; i < users.size(); i++) {
            Object[] test = {users.get(i)};
            dataArray[i] = test;
        }
        return dataArray;
    }

    @DataProvider(name = "productToggler", parallel = true)
    public static Object[][] productTogglerDataSets() {

        Workbook workBook = ExcelUtils.getWorkBook(FilePaths.TESTDATA_PRE_TOGGLER);
        Sheet productToggler = ExcelUtils.getSheet(workBook, CustomExcelStrings.PRODUCT_TOGGLER_SHEET_NAME);
        List<List<String>> sheetData = ExcelUtils.getSheetData(productToggler, 2);
        String[][] data = new String[sheetData.size()][sheetData.get(0).size()];
        int i = 0;
        for (List<String> sheetRowArray : sheetData) {
            int j = 0;
            for (String cell : sheetRowArray) {
                data[i][j] = cell;
                j++;
            }
            i++;
        }
        return data;
    }
}
