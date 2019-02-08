Page Title: PaymentFlow

#Object Definitions
==================================================================================================================================
close                           xpath           (//XCUIElementTypeOther[@name="Close"])[2]
pageHeader                      xpath           (//XCUIElementTypeOther[@name="Pay Bills"])[2]
creditCardRadioButton           xpath           //XCUIElementTypeOther[@name="Credit Card"]/XCUIElementTypeOther[2]
confirmButton                   xpath           //XCUIElementTypeOther[@name="Confirm"]
creditCardNumbertextBox         xpath           (//XCUIElementTypeOther[@name="Credit card number"])[4]/XCUIElementTypeTextField
cardHolderNameTextBox           xpath           (//XCUIElementTypeOther[@name="Cardholder name"])[3]/XCUIElementTypeTextField
expirydate                      xpath           //XCUIElementTypeStaticText[@name="Expiry date"]/following-sibling::XCUIElementTypeOther//XCUIElementTypeTextField
securityCode                    xpath           //XCUIElementTypeStaticText[@name="Security code"]/following-sibling::XCUIElementTypeOther//XCUIElementTypeSecureTextField
payButton                       xpath           //XCUIElementTypeOther[@name="Balance required"]/../../following-sibling::XCUIElementTypeOther
successIcon                     xpath           //XCUIElementTypeOther[@name="View Details"]/preceding-sibling::XCUIElementTypeOther[2]
