package com.mckinsey.lime.dataPrividers;

import com.mckinsey.lime.testDataBeans.*;
import com.mckinsey.lime.testDataEnums.*;
import com.mckinsey.lime.utils.CommonEnums;
import com.mckinsey.lime.utils.CustomExcelStrings;
import com.mckinsey.lime.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductTogglerAllScenarios {

    @DataProvider(name = "ProductTogglerAllScenarios", parallel = true)
    public Object[][] productTogglerDataSets() {
        Workbook workBook = ExcelUtils.getWorkBook(FilePaths.TESTDATA_PRE_TOGGLER);
        Sheet productToggler = ExcelUtils.getSheet(workBook, CustomExcelStrings.All_SCENARIOS_SHEET_NAME);

        List<String> headerRow = ExcelUtils.getRow(productToggler, 1);
        List<List<String>> sheetData = ExcelUtils.getSheetData(productToggler, 2);

        int numberOfPlans = ExcelUtils.getNoRowsInColumn(sheetData, headerRow, "Plan Type");
        int editableColumnIndex = headerRow.indexOf("Editable");

        List<ProductTogglerScenario.Plan> listOfPlans = sheetData.stream()
                .filter(item -> !item.get(0).isEmpty())
                .map(item -> new ProductTogglerScenario.Plan(PlanType.valueOf(item.get(0)), item.get(1), item.get(2)))
                .collect(Collectors.toList());

        List<DOB> dob = ExcelUtils.getColumn(sheetData, headerRow, "DOB")
                .stream()
                .map(item -> DOB.valueOf(item))
                .collect(Collectors.toList());

        List<Gender> gender = ExcelUtils.getColumn(sheetData, headerRow, "Gender")
                .stream()
                .map(item -> Gender.valueOf(item))
                .collect(Collectors.toList());

        List<Smoke> smoke = ExcelUtils.getColumn(sheetData, headerRow, "Smoke")
                .stream()
                .map(item -> Smoke.valueOf(item))
                .collect(Collectors.toList());

        List<ProductTogglerScenario.EditScenario> editScenarios = sheetData.stream()
                .filter(item -> !item.get(editableColumnIndex).isEmpty())
                .map(item -> new ProductTogglerScenario.EditScenario(PossibleFiguresEditableFields.valueOf(item.get(editableColumnIndex).toUpperCase()),
                        PossibleFiguresEditableScenarios.valueOf(item.get(editableColumnIndex + 1).toUpperCase())))
                .collect(Collectors.toList());

//        printAllPossibleScenarios(listOfPlans, dob, gender, smoke, editScenarios);

        PlansConfigDataBean plansConfigData = TestData.getPlansConfigData();

        List<ProductTogglerScenario> productTogglerScenarios = printAllvalidScenarios(listOfPlans, dob, gender, smoke, editScenarios, plansConfigData);


        ProductTogglerScenario[][] dataArray = new ProductTogglerScenario[productTogglerScenarios.size()][1];
        for (int i = 0; i < productTogglerScenarios.size(); i++) {
            ProductTogglerScenario[] test = {productTogglerScenarios.get(i)};
            dataArray[i] = test;
        }
        return dataArray;

    }

    private void printAllPossibleScenarios(List<ProductTogglerScenario.Plan> listOfPlans, List<DOB> dob, List<Gender> gender, List<Smoke> smoke, List<ProductTogglerScenario.EditScenario> editScenarios) {
        int scenarioNum = 1;
        for (ProductTogglerScenario.Plan currentPlan : listOfPlans) {
            for (DOB currentDOB : dob) {
                for (Gender currentGender : gender) {
                    for (Smoke currentSmoke : smoke) {
                        for (ProductTogglerScenario.EditScenario currentEditScenario : editScenarios) {
                            String delimiter = " -- ";
                            System.out.println(scenarioNum + delimiter +
                                    currentPlan + delimiter +
                                    currentDOB + delimiter +
                                    currentGender + delimiter +
                                    currentSmoke + delimiter +
                                    currentEditScenario);
                            scenarioNum++;
                        }
                    }
                }
            }

        }
    }

    private List<ProductTogglerScenario> printAllvalidScenarios(List<ProductTogglerScenario.Plan> listOfPlans, List<DOB> dob, List<Gender> gender, List<Smoke> smoke, List<ProductTogglerScenario.EditScenario> editScenarios, PlansConfigDataBean plansConfigData) {
        List<ProductTogglerScenario> productTogglerScenarios = new ArrayList<>();

        int scenarioNum = 1;
        for (ProductTogglerScenario.Plan currentPlan : listOfPlans) {
            for (DOB currentDOB : dob) {
                for (Gender currentGender : gender) {
                    for (Smoke currentSmoke : smoke) {
                        for (ProductTogglerScenario.EditScenario currentEditScenario : editScenarios) {
                            boolean isValidScenario = false;
                            String delimiter = " -- ";
                            boolean editable = plansConfigData.isEditable(currentPlan.getPlanTitle(), currentPlan.getPlanSubTitle(), currentEditScenario.getEditable());

                            boolean isMaxSumAssuredAvailable = plansConfigData.isMaxSumAssuredAvailable(currentPlan.getPlanTitle(), currentPlan.getPlanSubTitle());
                            if (editable) {
                                if (currentEditScenario.getEditableScenario() == PossibleFiguresEditableScenarios.PREMIUM_GRATER_THAN_MAX_SA
                                        || currentEditScenario.getEditableScenario() == PossibleFiguresEditableScenarios.SA_GRATER_THAN_MAX) {
                                    if (isMaxSumAssuredAvailable) {
                                        isValidScenario = true;
                                    }

                                } else {
                                    isValidScenario = true;
                                }

                                if (isValidScenario) {
                                    productTogglerScenarios.add(new ProductTogglerScenario(currentPlan, currentDOB, currentGender, currentSmoke, currentEditScenario));

                                    /*System.out.println(scenarioNum + delimiter +
                                            currentPlan + delimiter +
                                            currentDOB + delimiter +
                                            currentGender + delimiter +
                                            currentSmoke + delimiter +
                                            currentEditScenario);*/

                                    scenarioNum++;
                                }
                            }
                        }
                    }
                }
            }

        }
        return productTogglerScenarios;
    }


}
