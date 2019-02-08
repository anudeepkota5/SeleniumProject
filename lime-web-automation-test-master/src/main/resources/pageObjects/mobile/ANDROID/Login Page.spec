Page Title: Login Page

#Object Definitions
==================================================================================================================================
textBox_username            xpath               //android.widget.EditText
//textBox_username            xpath               //android.view.View[@resource-id="submitform"]/android.view.View[2]/android.view.View[3]/android.widget.EditText
textBox_password			xpath               //android.widget.EditText[@resource-id='password']
button_signIn				xpath                //android.widget.Button[@resource-id='submit']
text_successfulMessage		xpath				//XCUIElementTypeStaticText[@name="Login Successful!"]
text_accountMissingError	xpath				//XCUIElementTypeStaticText[@name="account field is required."]
text_passwordMissingError	xpath				//XCUIElementTypeStaticText[@name="Password field is required."]
text_wrongPassword			xpath				//XCUIElementTypeStaticText[@name="Invalid account or password."]
button_cancel				name				Cancel
