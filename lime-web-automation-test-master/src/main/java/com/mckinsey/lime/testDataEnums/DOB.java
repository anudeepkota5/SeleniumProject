package com.mckinsey.lime.testDataEnums;

import com.mckinsey.lime.utils.JavaUtils;

import java.time.LocalDate;
import java.time.Period;

public enum DOB implements Age {
    AGE_18 {
        @Override
        public LocalDate getAge() {
            return JavaUtils.getDOBAge18();
        }
    },
    AGE_18MINUS1 {
        @Override
        public LocalDate getAge() {
            return JavaUtils.getDOBAge18_1DayLess();
        }
    },
    AGE_18PLUS1 {
        @Override
        public LocalDate getAge() {
            return JavaUtils.getDOBAge18_1Day();
        }
    },
    AGE_62 {
        @Override
        public LocalDate getAge() {
            return JavaUtils.getDOBAge62();
        }
    },
    AGE_62MINUS1 {
        @Override
        public LocalDate getAge() {
            return JavaUtils.getDOBAge62_1DayLess();
        }
    },
    AGE_62PLUS1 {
        @Override
        public LocalDate getAge() {
            return JavaUtils.getDOBAge62_1Day();
        }
    },
    AGE_20 {
        @Override
        public LocalDate getAge() {
            return JavaUtils.getDOBAge20();
        }
    },
    AGE_20MINUS1 {
        @Override
        public LocalDate getAge() {
            return JavaUtils.getDOBAge20_1DayLess();
        }
    },
    AGE_20PLUS1 {
        @Override
        public LocalDate getAge() {
            return JavaUtils.getDOBAge20_1Day();
        }
    },
    AGE_59 {
        @Override
        public LocalDate getAge() {
            return JavaUtils.getDOBAge59();
        }
    },
    AGE_59MINUS1 {
        @Override
        public LocalDate getAge() {
            return JavaUtils.getDOBAge59_1DayLess();
        }
    },
    AGE_59PLUS1 {
        @Override
        public LocalDate getAge() {
            return JavaUtils.getDOBAge59_1Day();
        }
    },
    AGE_60 {
        @Override
        public LocalDate getAge() {
            return JavaUtils.getDOBAge60();
        }
    },
    AGE_60MINUS1 {
        @Override
        public LocalDate getAge() {
            return JavaUtils.getDOBAge60_1DayLess();
        }
    },
    AGE_60PLUS1 {
        @Override
        public LocalDate getAge() {
            return JavaUtils.getDOBAge60_1Day();
        }
    },
    AGE_19 {
        @Override
        public LocalDate getAge() {
            return LocalDate.now().minus(Period.of(19, 0, 0));
        }
    },
    AGE_41 {
        @Override
        public LocalDate getAge() {
            return LocalDate.now().minus(Period.of(41, 0, 0));
        }
    },
    AGE_52 {
        @Override
        public LocalDate getAge() {
            return LocalDate.now().minus(Period.of(52, 0, 0));
        }
    },
    AGE_25 {
        @Override
        public LocalDate getAge() {
            return LocalDate.now().minus(Period.of(25, 0, 0));
        }
    },
    AGE_31 {
        @Override
        public LocalDate getAge() {
            return LocalDate.now().minus(Period.of(31, 0, 0));
        }
    },
    AGE_28 {
        @Override
        public LocalDate getAge() {
            return LocalDate.now().minus(Period.of(28, 0, 0));
        }
    },
    AGE_50 {
        @Override
        public LocalDate getAge() {
            return LocalDate.now().minus(Period.of(50, 0, 0));
        }
    },
    AGE_30 {
        @Override
        public LocalDate getAge() {
            return LocalDate.now().minus(Period.of(30, 0, 0));
        }
    },
    AGE_21 {
        @Override
        public LocalDate getAge() {
            return LocalDate.now().minus(Period.of(21, 0, 0));
        }
    },
    AGE_56 {
        @Override
        public LocalDate getAge() {
            return LocalDate.now().minus(Period.of(56, 0, 0));
        }
    },
    AGE_35 {
        @Override
        public LocalDate getAge() {
            return LocalDate.now().minus(Period.of(35, 0, 0));
        }
    },
    AGE_26 {
        @Override
        public LocalDate getAge() {
            return LocalDate.now().minus(Period.of(26, 0, 0));
        }
    },
    AGE_40 {
        @Override
        public LocalDate getAge() {
            return LocalDate.now().minus(Period.of(40, 0, 0));
        }
    },
    AGE_39 {
        @Override
        public LocalDate getAge() {
            return LocalDate.now().minus(Period.of(39, 0, 0));
        }
    },
    AGE_48 {
        @Override
        public LocalDate getAge() {
            return LocalDate.now().minus(Period.of(48, 0, 0));
        }
    },
    AGE_32 {
        @Override
        public LocalDate getAge() {
            return LocalDate.now().minus(Period.of(32, 0, 0));
        }
    },
    AGE_45 {
        @Override
        public LocalDate getAge() {
            return LocalDate.now().minus(Period.of(45, 0, 0));
        }
    },
    AGE_55 {
        @Override
        public LocalDate getAge() {
            return LocalDate.now().minus(Period.of(55, 0, 0));
        }
    },
    AGE_34 {
        @Override
        public LocalDate getAge() {
            return LocalDate.now().minus(Period.of(34, 0, 0));
        }
    },
    AGE_29 {
        @Override
        public LocalDate getAge() {
            return LocalDate.now().minus(Period.of(29, 0, 0));
        }
    },
    AGE_57 {
        @Override
        public LocalDate getAge() {
            return LocalDate.now().minus(Period.of(57, 0, 0));
        }
    },
    AGE_46 {
        @Override
        public LocalDate getAge() {
            return LocalDate.now().minus(Period.of(46, 0, 0));
        }
    },
    AGE_36 {
        @Override
        public LocalDate getAge() {
            return LocalDate.now().minus(Period.of(36, 0, 0));
        }
    },
    AGE_23 {
        @Override
        public LocalDate getAge() {
            return LocalDate.now().minus(Period.of(23, 0, 0));
        }
    },
    AGE_38 {
        @Override
        public LocalDate getAge() {
            return LocalDate.now().minus(Period.of(38, 0, 0));
        }
    },
    AGE_42 {
        @Override
        public LocalDate getAge() {
            return LocalDate.now().minus(Period.of(42, 0, 0));
        }
    },
    AGE_61 {
        @Override
        public LocalDate getAge() {
            return LocalDate.now().minus(Period.of(61, 0, 0));
        }
    },
    AGE_37 {
        @Override
        public LocalDate getAge() {
            return LocalDate.now().minus(Period.of(37, 0, 0));
        }
    },
    AGE_51 {
        @Override
        public LocalDate getAge() {
            return LocalDate.now().minus(Period.of(51, 0, 0));
        }
    },
    AGE_43 {
        @Override
        public LocalDate getAge() {
            return LocalDate.now().minus(Period.of(43, 0, 0));
        }
    },
    AGE_27 {
        @Override
        public LocalDate getAge() {
            return LocalDate.now().minus(Period.of(27, 0, 0));
        }
    };

}

interface Age {
    LocalDate getAge();
}