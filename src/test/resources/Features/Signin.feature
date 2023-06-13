Feature: Signin functionality

    @success
    Scenario Outline: Signin with valid credentials
    Given User navigate to url
    When User enters vaild email "<email>" into email field
    And User enters valid password "<password>" into password field
    And User clicks on Sign in button
    Then User is redirected to the Dashboard page
    
    Examples: 
    | email            | password   |
    | admin@demo.com   | riseDemo   |
    | client@demo.com  | riseDemo   |
    
    @failure
    Scenario: Signin without filling any details
    Given User navigate to url
    When User dont enter any details into fields in the signin form
    And User clicks on Sign in button
    Then User should get error messages for every field in the signin form
    
    @failure
    Scenario Outline: Signin with invalid email and valid password
    Given User navigate to url
    When User fill the form sign in from given sheetname "<SheetName>" and rowNumber <rowNumber>
    And User clicks on Sign in button
    Then User receives a formatting error message right under the email field
    
    Examples:
    | SheetName | rowNumber |
    | SignIn    | 0         |
    | SignIn    | 1         |
    | SignIn    | 2         |
    
    @failure
    Scenario Outline: Signin with invalid credentials
    Given User navigate to url
    When User fill the form sign in from given sheetname "<SheetName>" and rowNumber <rowNumber>
    And User clicks on Sign in button
    Then User should get an alert message about incorrect credentials
    
     Examples:
    | SheetName | rowNumber |
    | SignIn    | 4         |
    | SignIn    | 5         |
    

    

 
    