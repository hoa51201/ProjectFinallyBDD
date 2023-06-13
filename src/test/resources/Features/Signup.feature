Feature: Signup functionality

    @success
    Scenario: Sign up with vaild fields
    Given User navigates to Sign up page
    And User selects Type Individual
    When User enter vaild into fields in the signin form
    | firstname    | lastname     | email               | password   | retypepassword   |
    | hanh         | bui          | hanh74873@gmail.com | 27111992   | 27111992         |
    #And User clicks on Sign up button
    Then User account should get created successfully

    @failure
    Scenario: Sign up without filling any details
    Given User navigates to Sign up page
    When User dont enter any details into fields in the signin form
    And User clicks on Sign up button
    Then User should get proper warning messages for every mandatory field

    Scenario Outline: Sign up with invalid mandatory fields
    Given User navigates to Sign up page
    When User fill the form sign up from given sheetname "<SheetName>" and rowNumber <rowNumber>
    And User selects Type Organization
    And User clicks on Sign up button
    Then Display an error message right below the invalid field
    
    Examples:
    | SheetName | rowNumber |
    | SignUp    | 0         |
    | SignUp    | 1         |


    