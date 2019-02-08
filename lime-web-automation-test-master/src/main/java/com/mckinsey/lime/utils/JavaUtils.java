package com.mckinsey.lime.utils;

import com.mckinsey.lime.testDataEnums.DOB;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class JavaUtils {

    public static void hardWait(double seconds) {
        try {
            Thread.sleep((long) (seconds * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //TODO:
    public static String getEffectiveConfig(String keyValue, String configFileValue) {
        String property = System.getProperty(keyValue);
        String env = System.getenv(keyValue);

        if (env != null) {
            if (!env.isEmpty()) {
                return env;
            }
        } else if (property != null) {
            if (!property.isEmpty()) {
                return property;
            }
        }
        return configFileValue;
    }

    public static Double setDoublePrecision(Double d) {
        return BigDecimal.valueOf(d)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public static String getCallerClassName() {
        List<String> allTestPackages = Arrays.asList(new String[]{"com.mckinsey.iService.mobileWebTests", "com.mckinsey.iService.apiTests", "com.mckinsey.iService.paymentAPIs", "com.mckinsey.iService.tests"});
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        Optional<String> collect = Arrays.asList(stElements).stream()
//                .map(item -> item.getClassName().substring(0, item.getClassName().lastIndexOf(".")))
                .filter(item -> allTestPackages.contains(item.getClassName().substring(0, item.getClassName().lastIndexOf("."))))
                .map(item -> item.getClassName().substring(item.getClassName().lastIndexOf(".") + 1))
                .reduce((first, second) -> second);
//        .findFirst();

        if (collect.isPresent()) {
            return collect.get();
        } else {
            return null;
        }
//        CustomLogging.witeInfo(collect);

//        for (int i=1; i<stElements.length; i++) {
//            StackTraceElement ste = stElements[i];
//
//            if (ste.getClass().getPackage().getPlanTitle().equalsIgnoreCase("com.mckinsey.iService.mobilePages")) {
//                return ste.getClassName();
//            }
//            if (!ste.getClassName().equals(KDebug.class.getPlanTitle()) && ste.getClassName().indexOf("java.lang.Thread")!=0) {
//                return ste.getClassName();
//            }
//        }
//        return null;
    }

    public static void deleteDirectoryStream(Path path) {
        try {
            if (path.toFile().exists()) {
                Files.walk(path)
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int generateRandomIntIntRange() {
        int max = 999999;
        int min = 9999;
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static LocalDate getDOB(int years, int months, int days) {
        return LocalDate.now().minus(Period.of(years, months, days));
    }

    public static LocalDate getDOBAge30() {
        return LocalDate.now().minus(Period.of(30, 0, 0));
    }

    public static LocalDate getDOBAge18() {
        return LocalDate.now().minus(Period.of(18, 0, 0));
    }

    public static LocalDate getDOBAge18_1Day() {
        return LocalDate.now().minus(Period.of(18, 1, 0));
    }

    public static LocalDate getDOBAge18_1DayLess() {
        return LocalDate.now().minus(Period.of(18, -1, 0));
    }

    public static LocalDate getDOBAge62() {
        return LocalDate.now().minus(Period.of(62, 0, 0));
    }

    public static LocalDate getDOBAge62_1Day() {
        return LocalDate.now().minus(Period.of(62, 1, 0));
    }

    public static LocalDate getDOBAge62_1DayLess() {
        return LocalDate.now().minus(Period.of(62, -1, 0));
    }

    public static LocalDate getDOBAge55() {
        return LocalDate.now().minus(Period.of(55, 0, 0));
    }

    public static LocalDate getDOBAge55_1Day() {
        return LocalDate.now().minus(Period.of(55, 1, 0));
    }

    public static LocalDate getDOBAge55_1DayLess() {
        return LocalDate.now().minus(Period.of(55, -1, 0));
    }

    public static LocalDate getDOBAge20() {
        return LocalDate.now().minus(Period.of(20, 0, 0));
    }

    public static LocalDate getDOBAge20_1Day() {
        return LocalDate.now().minus(Period.of(20, 1, 0));
    }

    public static LocalDate getDOBAge20_1DayLess() {
        return LocalDate.now().minus(Period.of(20, -1, 0));
    }

    public static LocalDate getDOBAge59() {
        return LocalDate.now().minus(Period.of(59, 0, 0));
    }

    public static LocalDate getDOBAge59_1Day() {
        return LocalDate.now().minus(Period.of(59, 1, 0));
    }

    public static LocalDate getDOBAge59_1DayLess() {
        return LocalDate.now().minus(Period.of(59, -1, 0));
    }

    public static LocalDate getDOBAge60() {
        return LocalDate.now().minus(Period.of(60, 0, 0));
    }

    public static LocalDate getDOBAge60_1Day() {
        return LocalDate.now().minus(Period.of(60, 1, 0));
    }

    public static LocalDate getDOBAge60_1DayLess() {
        return LocalDate.now().minus(Period.of(60, -1, 0));
    }

    public static String trimStringOfMultipleLines(String str) {
        return Arrays.stream(str.split("\n"))
                .map(item -> item.trim())
                .collect(Collectors.joining("\n"));
    }

    public static String getFormatedWith2Digits(int value) {
        return new DecimalFormat("00").format(value);

    }

    public static String getAgeInYears(String day, String month, String year) {
        LocalDate dob = LocalDate.of(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day));
        return String.valueOf(Period.between(dob, LocalDate.now()).getYears());
    }

    public static String getFormattedDOB(LocalDate age) {
        String formattedDay = JavaUtils.getFormatedWith2Digits(age.getDayOfMonth());
        String formattedMonth = JavaUtils.getFormatedWith2Digits(age.getMonthValue());
        String year = String.valueOf(age.getYear());

        return formattedDay + "/" + formattedMonth + "/" + year;
    }
}
