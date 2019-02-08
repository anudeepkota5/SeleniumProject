# testAutomation
This is a test project for React Native app iTrue Service for End to End testing


### How to execute test cases ?
Below is the basic command to execute test cases by using project `iservice-app-automation`
    
`mvn clean test`
    
We need to apend maven command line options to change the behaviour of test cases execution.
    

#### Different command line options for test case execution:


##### Local or Sauce Labs execution:
To define the test environment append the below command line option at the end of above mentioned mvn command:
    
    -DtestEnvironment=LOCAL
    -DtestEnvironment=SAUCE
    
**Note: ** Local execution must be done in single threaded mode.

##### Single Threaded Execution:
 To execute the Test cases in single thread follow the below steps:
 1. Open the testNG xml file that user is about to execute.
 2. remove the following capabilities from xml:
    3. parallel="tests"
    4. parallel="instances"
  
##### Micro Service environment:
To define the app environment append the below command line option at the end of above mentioned mvn command:
    
    -DappEnvironment=DEV
    -DappEnvironment=ALPHA

##### IOS or Android execution:
To define the device type append the below command line option at the end of above mentioned mvn command:
    
    -DdeviceType=IOS
    -DdeviceType=ANDROID

##### Change the sauceLabs account details:
To change the sauce labs account deatils append the below command line option at the end of above mentioned mvn command:
    
    -DsauceLabURL=http://sharma2908abhishek:dcd3f9e8-8130-4e8c-8dbe-898cb85aa4a8@ondemand.saucelabs.com/wd/hub

##### Change the CHANNEL for API test case execution:
To change the channel value for api test cases append the below command line option at the end of above mentioned mvn command:
    
    -Dapi_channel=APP
    -Dapi_channel=WEB

##### Change the PLATFORM info for API test case execution:
To change the platform value for api test cases append the below command line option at the end of above mentioned mvn command:
    
    -Dapi_platform=ios
    -Dapi_platform=android

##### Enable/Disable sending test case report to slack group:
To change whether the slack report to send post execution of test cases, append the below command line option at the end of above mentioned mvn command:
    
    -DslackReport=true
    -DslackReport=false

##### To change the Authorization values for Micro services:
To change the Authorization values for micro service environments, user need to update the values in configaration file "environment.json" which is available with the repo.


##### To change the capabilities of the emulators/simulators:
To change the capabilities of the emulators/simulators that are used to execute test cases, user need to update the values in configaration file "capabilities.yaml" which is available with the repo.


##### To add/update test user details:
To add/update the test users that are used to execute test cases, user need to update the values in below configaration files based on the MicroServices environment which is available with the repo:
        
        DEV: api-users.json
        ALPHA: api-users-alpha.json




_**Notes to remember:**_

1. Payment related test cases will execute only on DEV environment of Micro Services.

2. Issue related to mobile Safari with Appium: https://github.com/appium/appium/issues/9002