package com.mckinsey.lime.pages;

import org.openqa.selenium.WebDriver;

public class DesktopPageObjects {
    public static GetAQuotePage getAQuotePage;
    public static FindMeAPlanPage findMeAPlanPage;
    public static CommonPage commonPage;
    public static ComparePlansPage comparePlansPage;
    public static PlanDetailsPage planDetailsPage;
    public static RetrieveApplicationPage retrieveApplicationPage;
    public static LandingPage landingPage;
    public static OurPlansPage ourPlansPage;
    public static PossibleFiguresPage possibleFiguresPage;
    public static WantARiderPage wantARiderPage;
    public static SelectedRiderPage selectedRiderPage;
    public static ProductTogglerSummaryPage productTogglerSummaryPage;


    //TODO: Need to improve the singleTon login=c to support Parallel execution
    public static GetAQuotePage getAQuotePage(WebDriver driver) {
        /*if (getAQuotePage == null) {
            synchronized (DesktopPageObjects.class) {
                if (getAQuotePage == null) {
                    getAQuotePage = new GetAQuotePage(driver);
                }
            }
        }
        return getAQuotePage;*/
        return new GetAQuotePage(driver);
    }

    public static FindMeAPlanPage findMeAPlanPage(WebDriver driver) {
        /*if (findMeAPlanPage == null) {
            synchronized (DesktopPageObjects.class) {
                if (findMeAPlanPage == null) {
                    findMeAPlanPage = new FindMeAPlanPage(driver);
                }
            }
        }
        return findMeAPlanPage;*/
        return new FindMeAPlanPage(driver);
    }

    public static CommonPage commonPage(WebDriver driver) {
        /*if (commonPage == null) {
            synchronized (DesktopPageObjects.class) {
                if (commonPage == null) {
                    commonPage = new CommonPage(driver);
                }
            }
        }
        return commonPage;*/
        return new CommonPage(driver);
    }

    public static ComparePlansPage comparePlansPage(WebDriver driver) {
        /*if (comparePlansPage == null) {
            synchronized (DesktopPageObjects.class) {
                if (comparePlansPage == null) {
                    comparePlansPage = new ComparePlansPage(driver);
                }
            }
        }
        return comparePlansPage;*/
        return new ComparePlansPage(driver);
    }

    public static PlanDetailsPage planDetailsPage(WebDriver driver) {
        /*if (planDetailsPage == null) {
            synchronized (DesktopPageObjects.class) {
                if (planDetailsPage == null) {
                    planDetailsPage = new PlanDetailsPage(driver);
                }
            }
        }
        return planDetailsPage;*/
        return new PlanDetailsPage(driver);
    }

    public static RetrieveApplicationPage retrieveApplicationPage(WebDriver driver) {
        /*if (retrieveApplicationPage == null) {
            synchronized (DesktopPageObjects.class) {
                if (retrieveApplicationPage == null) {
                    retrieveApplicationPage = new RetrieveApplicationPage(driver);
                }
            }
        }
        return retrieveApplicationPage;*/
        return new RetrieveApplicationPage(driver);
    }

    public static LandingPage landingPage(WebDriver driver) {
        /*if (landingPage == null) {
            synchronized (DesktopPageObjects.class) {
                if (landingPage == null) {
                    landingPage = new LandingPage(driver);
                }
            }
        }
        return landingPage;*/
        return new LandingPage(driver);
    }

    public static OurPlansPage ourPlansPage(WebDriver driver) {
        /*if (ourPlansPage == null) {
            synchronized (DesktopPageObjects.class) {
                if (ourPlansPage == null) {
                    ourPlansPage = new OurPlansPage(driver);
                }
            }
        }
        return ourPlansPage;*/
        return new OurPlansPage(driver);
    }

    public static PossibleFiguresPage possibleFiguresPage(WebDriver driver) {
        /*if (ourPlansPage == null) {
            synchronized (DesktopPageObjects.class) {
                if (ourPlansPage == null) {
                    ourPlansPage = new OurPlansPage(driver);
                }
            }
        }
        return ourPlansPage;*/
        return new PossibleFiguresPage(driver);
    }

    public static WantARiderPage wantARiderPage(WebDriver driver) {
        /*if (ourPlansPage == null) {
            synchronized (DesktopPageObjects.class) {
                if (ourPlansPage == null) {
                    ourPlansPage = new OurPlansPage(driver);
                }
            }
        }
        return ourPlansPage;*/
        return new WantARiderPage(driver);
    }

    public static SelectedRiderPage selectedRiderPage(WebDriver driver) {
        /*if (ourPlansPage == null) {
            synchronized (DesktopPageObjects.class) {
                if (ourPlansPage == null) {
                    ourPlansPage = new OurPlansPage(driver);
                }
            }
        }
        return ourPlansPage;*/
        return new SelectedRiderPage(driver);
    }

    public static ProductTogglerSummaryPage productTogglerSummaryPage(WebDriver driver) {
        /*if (ourPlansPage == null) {
            synchronized (DesktopPageObjects.class) {
                if (ourPlansPage == null) {
                    ourPlansPage = new OurPlansPage(driver);
                }
            }
        }
        return ourPlansPage;*/
        return new ProductTogglerSummaryPage(driver);
    }

    public static void killPageObjects() {
        commonPage = null;
        comparePlansPage = null;
        findMeAPlanPage = null;
        getAQuotePage = null;
        landingPage = null;
        ourPlansPage = null;
        planDetailsPage = null;
        retrieveApplicationPage = null;
        possibleFiguresPage = null;
        wantARiderPage = null;
        selectedRiderPage = null;
        productTogglerSummaryPage = null;
    }

}
