package com.mckinsey.lime.dataPrividers;

import com.mckinsey.lime.guiDataBeans.PossibleFiguresBean;
import com.mckinsey.lime.testDataBeans.*;
import com.mckinsey.lime.testDataEnums.*;
import com.mckinsey.lime.utils.CustomExcelStrings;
import com.mckinsey.lime.utils.ExcelUtils;
import com.mckinsey.lime.utils.JavaUtils;
import com.mckinsey.lime.utils.ProductTogglerDataSheetColumns;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductToggler {

    @Test
    public void test() {
        Object[][] objects = ProductToggler.productTogglerDataSets();
        System.out.println(objects);
    }

    @DataProvider(name = "ProductToggler", parallel = true)
    public static Object[][] productTogglerDataSets() {

        Workbook workBook = ExcelUtils.getProductTogglerWorkBook();
        Sheet productToggler = ExcelUtils.getSheet(workBook, CustomExcelStrings.PRODUCT_TOGGLER_SHEET_NAME);

        List<String> headerRow = ExcelUtils.getRow(productToggler, 1);
        List<List<String>> sheetData = ExcelUtils.getSheetData(productToggler, 2);
        List<List<String>> collect = sheetData.stream().collect(Collectors.toList());
        List<List<List<String>>> listOfTestCases = new ArrayList<>();

        List<List<String>> testCase = new ArrayList<>();

        //TODO: Need to refactor
        for (int i = 0; i < collect.size(); i++) {
            List<String> currentRow = collect.get(i);
            String tcNumber = ExcelUtils.getCellValue(currentRow, headerRow, ProductTogglerDataSheetColumns.TC_NUMBER);
            if (!tcNumber.isEmpty()) {
                if (!testCase.isEmpty()) {
                    listOfTestCases.add(testCase);
                }

                testCase = new ArrayList<>();
                testCase.add(currentRow);
            } else {
                testCase.add(currentRow);
            }
        }
        listOfTestCases.add(testCase);

        List<ProductTogglerTestScenario> dataAsList = new ArrayList<>();

        listOfTestCases.stream().forEach(currentTC -> {
            List<String> firstRowOfTC = currentTC.get(0);

            List<PossibleFiguresEditScenario> editScenarios = currentTC.stream()
                    .filter(item -> !ExcelUtils.getCellValue(item, headerRow, ProductTogglerDataSheetColumns.EDIT_FIELD).isEmpty())
                    .map(item -> {
                        PossibleFiguresEditableFields editField = PossibleFiguresEditableFields.valueOf(ExcelUtils.getCellValue(item, headerRow, ProductTogglerDataSheetColumns.EDIT_FIELD));
                        String valueToUpdate = ExcelUtils.getCellValue(item, headerRow, ProductTogglerDataSheetColumns.VALUE_TO_UPDATE);
                        String errorMessage = ExcelUtils.getCellValue(item, headerRow, ProductTogglerDataSheetColumns.ERROR_DETAILS);

                        return new PossibleFiguresEditScenario(editField, valueToUpdate, errorMessage);
                    })
                    .collect(Collectors.toList());

            PossibleFiguresBean defaultPossibleFiguresBean = currentTC.stream()
                    .map(item -> {
                        String defaultPremiumAmount = ExcelUtils.getCellValue(firstRowOfTC, headerRow, ProductTogglerDataSheetColumns.DEFAULT_PREMIUM_AMOUNT);
                        PremiumTerm defaultPremiumTerm = PremiumTerm.valueOf(ExcelUtils.getCellValue(firstRowOfTC, headerRow, ProductTogglerDataSheetColumns.DEFAULT_PREMIUM_TERM).toUpperCase());
                        PremiumFrequency defaultPremiumFrequency = PremiumFrequency.valueOf(ExcelUtils.getCellValue(firstRowOfTC, headerRow, ProductTogglerDataSheetColumns.DEFAULT_PREMIUM_FREQUENCY).toUpperCase());
                        String defaultSumAssured = ExcelUtils.getCellValue(firstRowOfTC, headerRow, ProductTogglerDataSheetColumns.DEFAULT_SUM_ASSURED);
                        String defaultPolicyTerm = ExcelUtils.getCellValue(firstRowOfTC, headerRow, ProductTogglerDataSheetColumns.DEFAULT_POLICY_TERM);
                        String defaultStartAgeForCashBenefit = ExcelUtils.getCellValue(firstRowOfTC, headerRow, ProductTogglerDataSheetColumns.DEFAULT_START_AGE_FOR_CASH_BENEFIT);
                        String defaultPayoutPeriod = ExcelUtils.getCellValue(firstRowOfTC, headerRow, ProductTogglerDataSheetColumns.DEFAULT_PAYOUT_PERIOD);
                        String defaultGuaranteedMonthlyCashBenefit = ExcelUtils.getCellValue(firstRowOfTC, headerRow, ProductTogglerDataSheetColumns.DEFAULT_GUARANTEED_MONTHLY_CASH_BENEFIT);

                        String defaultScenarioA = ExcelUtils.getCellValue(firstRowOfTC, headerRow, ProductTogglerDataSheetColumns.DEFAULT_SCENARIO_A);
                        String defaultScenarioB = ExcelUtils.getCellValue(firstRowOfTC, headerRow, ProductTogglerDataSheetColumns.DEFAULT_SCENARIO_B);
                        String defaultGuaranteedMaturity = ExcelUtils.getCellValue(firstRowOfTC, headerRow, ProductTogglerDataSheetColumns.DEFAULT_GUARANTEED_MATURITY);
                        String defaultYieldA = ExcelUtils.getCellValue(firstRowOfTC, headerRow, ProductTogglerDataSheetColumns.DEFAULT_YIELD_A);
                        String defaultYieldB = ExcelUtils.getCellValue(firstRowOfTC, headerRow, ProductTogglerDataSheetColumns.DEFAULT_YIELD_B);
                        String defaultYearlyCashBenefit = ExcelUtils.getCellValue(firstRowOfTC, headerRow, ProductTogglerDataSheetColumns.DEFAULT_YEARLY_CASH_BENEFIT);

                        return new PossibleFiguresBean.Builder()
                                .premiumAmount(defaultPremiumAmount)
                                .premiumTerm(defaultPremiumTerm)
                                .premiumFrequency(defaultPremiumFrequency)
                                .sumAssured(defaultSumAssured)
                                .policyTerm(defaultPolicyTerm)
                                .startAgeForCashBenefit(defaultStartAgeForCashBenefit)
                                .payoutPeriod(defaultPayoutPeriod)
                                .monthlyCashBenefit(defaultGuaranteedMonthlyCashBenefit)
                                .viewDetails(defaultScenarioA, defaultScenarioB, defaultGuaranteedMaturity, defaultYieldA, defaultYieldB, defaultYearlyCashBenefit)
                                .Build();
                    })
                    .findFirst()
                    .orElse(null);

            List<PossibleFiguresBean> updatedPossibleFiguresBeans = currentTC.stream()
                    .map(item -> {
                        String premiumAmount = ExcelUtils.getCellValue(item, headerRow, ProductTogglerDataSheetColumns.UPDATED_PREMIUM_AMOUNT);

                        PremiumTerm premiumTerm;
                        String premiumTermString = ExcelUtils.getCellValue(item, headerRow, ProductTogglerDataSheetColumns.UPDATED_PREMIUM_TERM).toUpperCase();
                        if (premiumTermString.isEmpty())
                            premiumTerm = PremiumTerm.EMPTY;
                        else
                            premiumTerm = PremiumTerm.valueOf(premiumTermString);

                        PremiumFrequency premiumFrequency;
                        String premiumFrequenctAsString = ExcelUtils.getCellValue(item, headerRow, ProductTogglerDataSheetColumns.UPDATED_PREMIUM_FREQUENCY).toUpperCase();
                        if (premiumFrequenctAsString.isEmpty())
                            premiumFrequency = PremiumFrequency.EMPTY;
                        else
                            premiumFrequency = PremiumFrequency.valueOf(premiumFrequenctAsString);

                        String sumAssured = ExcelUtils.getCellValue(item, headerRow, ProductTogglerDataSheetColumns.UPDATED_SUM_ASSURED);
                        String policyTerm = ExcelUtils.getCellValue(item, headerRow, ProductTogglerDataSheetColumns.UPDATED_POLICY_TERM);
                        String startAgeForCashBenefit = ExcelUtils.getCellValue(item, headerRow, ProductTogglerDataSheetColumns.UPDATED_START_AGE_FOR_CASH_BENEFIT);
                        String payoutPeriod = ExcelUtils.getCellValue(item, headerRow, ProductTogglerDataSheetColumns.UPDATED_PAYOUT_PERIOD);
                        String guaranteedMonthlyCashBenefit = ExcelUtils.getCellValue(item, headerRow, ProductTogglerDataSheetColumns.UPDATED_GUARANTEED_MONTHLY_CASH_BENEFIT);

                        String scenarioA = ExcelUtils.getCellValue(item, headerRow, ProductTogglerDataSheetColumns.UPDATED_SCENARIO_A);
                        String scenarioB = ExcelUtils.getCellValue(item, headerRow, ProductTogglerDataSheetColumns.UPDATED_SCENARIO_B);
                        String guaranteedMaturity = ExcelUtils.getCellValue(item, headerRow, ProductTogglerDataSheetColumns.UPDATED_GUARANTEED_MATURITY);
                        String yieldA = ExcelUtils.getCellValue(item, headerRow, ProductTogglerDataSheetColumns.UPDATED_YIELD_A);
                        String yieldB = ExcelUtils.getCellValue(item, headerRow, ProductTogglerDataSheetColumns.UPDATED_YIELD_B);
                        String yearlyCashBenefit = ExcelUtils.getCellValue(item, headerRow, ProductTogglerDataSheetColumns.UPDATED_YEARLY_CASH_BENEFIT);

                        return new PossibleFiguresBean.Builder()
                                .premiumAmount(premiumAmount)
                                .premiumTerm(premiumTerm)
                                .premiumFrequency(premiumFrequency)
                                .sumAssured(sumAssured)
                                .policyTerm(policyTerm)
                                .startAgeForCashBenefit(startAgeForCashBenefit)
                                .payoutPeriod(payoutPeriod)
                                .monthlyCashBenefit(guaranteedMonthlyCashBenefit)
                                .viewDetails(scenarioA, scenarioB, guaranteedMaturity, yieldA, yieldB, yearlyCashBenefit)
                                .Build();
                    })
                    .collect(Collectors.toList());


            List<RiderScenario> riderScenarios = currentTC.stream()
                    .filter(item -> !ExcelUtils.getCellValue(item, headerRow, ProductTogglerDataSheetColumns.RIDER).isEmpty())
                    .map(item -> {
                        Riders rider = Riders.valueOf(ExcelUtils.getCellValue(item, headerRow, ProductTogglerDataSheetColumns.RIDER));
                        String riderMessage = ExcelUtils.getCellValue(item, headerRow, ProductTogglerDataSheetColumns.RIDER_MESSAGE);
                        String acceptRider = ExcelUtils.getCellValue(item, headerRow, ProductTogglerDataSheetColumns.ACCEPT_RIDER);

                        return new RiderScenario(rider, riderMessage, acceptRider);
                    })
                    .collect(Collectors.toList());

            List<RiderEditScenario> riderEditScenarios = currentTC.stream()
                    .filter(item -> !ExcelUtils.getCellValue(item, headerRow, ProductTogglerDataSheetColumns.RIDER_EDIT_FIELD).isEmpty())
                    .map(item -> {
                        String riderEditField = ExcelUtils.getCellValue(item, headerRow, ProductTogglerDataSheetColumns.RIDER_EDIT_FIELD);
                        String riderValueToUpdate = ExcelUtils.getCellValue(item, headerRow, ProductTogglerDataSheetColumns.RIDER_VALUE_TO_UPDATE);
                        String riderErrorMessage = ExcelUtils.getCellValue(item, headerRow, ProductTogglerDataSheetColumns.RIDER_ERROR_MESSAGE);
                        return new RiderEditScenario(riderEditField, riderValueToUpdate, riderErrorMessage);
                    })
                    .collect(Collectors.toList());

            List<RiderInfo> updatedRiderInfos = currentTC.stream()
                    .filter(item -> !ExcelUtils.getCellValue(item, headerRow, ProductTogglerDataSheetColumns.RIDER_PREMIUM_AMOUNT).isEmpty())
                    .map(item -> {
                        String riderAmount = ExcelUtils.getCellValue(item, headerRow, ProductTogglerDataSheetColumns.RIDER_PREMIUM_AMOUNT);
                        return new RiderInfo(riderAmount);
                    })
                    .collect(Collectors.toList());


            LocalDate dob = DOB.valueOf(ExcelUtils.getCellValue(firstRowOfTC, headerRow, ProductTogglerDataSheetColumns.DOB)).getAge();
            String formattedDOB = JavaUtils.getFormattedDOB(dob);

            ProductTogglerTestScenario currentScenario = new ProductTogglerTestScenario
                    .Builder(ExcelUtils.getCellValue(firstRowOfTC, headerRow, ProductTogglerDataSheetColumns.TC_NUMBER))
                    .plan(new Plan(PlanType.valueOf(ExcelUtils.getCellValue(firstRowOfTC, headerRow, ProductTogglerDataSheetColumns.PLAN_TYPE).toUpperCase()),
                            ExcelUtils.getCellValue(firstRowOfTC, headerRow, ProductTogglerDataSheetColumns.PLAN_TITLE),
                            ExcelUtils.getCellValue(firstRowOfTC, headerRow, ProductTogglerDataSheetColumns.PLAN_SUBTITLE)))
                    .dob_day(formattedDOB.split("/")[0])
                    .dob_month(formattedDOB.split("/")[1])
                    .dob_year(formattedDOB.split("/")[2])
                    .gender(Gender.valueOf(ExcelUtils.getCellValue(firstRowOfTC, headerRow, ProductTogglerDataSheetColumns.GENDER).toUpperCase()))
                    .smoke(Smoke.valueOf(ExcelUtils.getCellValue(firstRowOfTC, headerRow, ProductTogglerDataSheetColumns.SMOKE).toUpperCase()))
                    .defaultPossibleFiguresBean(defaultPossibleFiguresBean)
                    .editScenarios(editScenarios)
                    .updatedPossibleFiguresBeans(updatedPossibleFiguresBeans)
                    .riderScenarios(riderScenarios)
                    .riderEditScenarios(riderEditScenarios)
                    .updatedRiderInfos(updatedRiderInfos)
                    .Build();
            if (ExcelUtils.getCellValue(firstRowOfTC, headerRow, ProductTogglerDataSheetColumns.TC_STATUS).equalsIgnoreCase("EXECUTE"))
                dataAsList.add(currentScenario);
        });

        Object[][] dataArray = new Object[dataAsList.size()][1];
        for (int i = 0; i < dataAsList.size(); i++) {
            Object[] test = {dataAsList.get(i)};
            dataArray[i] = test;
        }
        return dataArray;

    }
}
