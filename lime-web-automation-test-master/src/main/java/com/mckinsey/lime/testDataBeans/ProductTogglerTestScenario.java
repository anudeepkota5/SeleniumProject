package com.mckinsey.lime.testDataBeans;

import com.mckinsey.lime.guiDataBeans.PossibleFiguresBean;
import com.mckinsey.lime.testDataEnums.*;
import lombok.Getter;

import java.util.List;

@Getter
//@Setter
public class ProductTogglerTestScenario {
    private final String tcNo;
    private final Plan plan;
    private final String dob_day;
    private final String dob_month;
    private final String dob_year;
    private final Gender gender;
    private final Smoke smoke;

    private final PossibleFiguresBean defaultPossibleFiguresBean;

    private final List<PossibleFiguresEditScenario> editScenarios;

    private List<PossibleFiguresBean> updatedPossibleFiguresBeans;

    private final List<RiderScenario> riderScenarios;
    private final List<RiderEditScenario> riderEditScenarios;
    private final List<RiderInfo> updatedRiderInfos;

    public static class Builder {
        // Add default values, if required
        private String tcNo;

        private Plan plan;
        private String dob_day;
        private String dob_month;
        private String dob_year;
        private Gender gender;
        private Smoke smoke;
        private PossibleFiguresBean defaultPossibleFiguresBean;

        private List<PossibleFiguresEditScenario> editScenarios;

        private List<PossibleFiguresBean> updatedPossibleFiguresBeans;

        private List<RiderScenario> riderScenarios;
        private List<RiderEditScenario> riderEditScenarios;
        private List<RiderInfo> updatedRiderInfos;


        public Builder(String tcNo) {
            this.tcNo = tcNo;
        }

        public Builder plan(Plan plan) {
            this.plan = plan;
            return this;
        }

        public Builder dob_day(String dob_day) {
            this.dob_day = dob_day;
            return this;
        }

        public Builder dob_month(String dob_month) {
            this.dob_month = dob_month;
            return this;
        }

        public Builder dob_year(String dob_year) {
            this.dob_year = dob_year;
            return this;
        }

        public Builder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder smoke(Smoke smoke) {
            this.smoke = smoke;
            return this;
        }

        public Builder defaultPossibleFiguresBean(PossibleFiguresBean defaultPossibleFiguresBean) {
            this.defaultPossibleFiguresBean = defaultPossibleFiguresBean;
            return this;
        }

        public Builder editScenarios(List<PossibleFiguresEditScenario> editScenarios) {
            this.editScenarios = editScenarios;
            return this;
        }

        public Builder updatedPossibleFiguresBeans(List<PossibleFiguresBean> updatedPossibleFiguresBean) {
            this.updatedPossibleFiguresBeans = updatedPossibleFiguresBean;
            return this;
        }

        public Builder riderScenarios(List<RiderScenario> riderScenarios) {
            this.riderScenarios = riderScenarios;
            return this;
        }

        public Builder riderEditScenarios(List<RiderEditScenario> riderEditScenarios) {
            this.riderEditScenarios = riderEditScenarios;
            return this;
        }

        public Builder updatedRiderInfos(List<RiderInfo> updatedRiderInfos) {
            this.updatedRiderInfos = updatedRiderInfos;
            return this;
        }

        public ProductTogglerTestScenario Build() {

            return new ProductTogglerTestScenario(this);
        }

    }

    private ProductTogglerTestScenario(Builder builder) {
        this.tcNo = builder.tcNo;
        this.plan = builder.plan;
        this.dob_day = builder.dob_day;
        this.dob_month = builder.dob_month;
        this.dob_year = builder.dob_year;
        this.gender = builder.gender;
        this.smoke = builder.smoke;
        this.defaultPossibleFiguresBean = builder.defaultPossibleFiguresBean;

        this.editScenarios = builder.editScenarios;
        this.updatedPossibleFiguresBeans = builder.updatedPossibleFiguresBeans;

        this.riderScenarios = builder.riderScenarios;
        this.riderEditScenarios = builder.riderEditScenarios;
        this.updatedRiderInfos = builder.updatedRiderInfos;
    }

    @Override
    public String toString() {
        return tcNo + " | " +
                plan + " | " +
                dob_day + " | " +
                dob_month + " | " +
                dob_year + " | " +
                gender + " | " +
                smoke + " | " +
                defaultPossibleFiguresBean + " | " +
                editScenarios + " | ";
    }
}
