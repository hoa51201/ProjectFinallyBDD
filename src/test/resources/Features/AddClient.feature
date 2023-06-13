Feature: Add client functionality

 Background: User is logged to the HRM system
    Given User logged in with email "admin@demo.com" and password "riseDemo"
    
   @success
    Scenario: Add client with vaild field
    Given User navigates to Add client page
    When User fill the form add client with valid inputs    
    |company_name  |owner        |address   |city     |state        |zip     |country   |phone     |website                     |vat  |  client_group |
    | hanh         |Mark Thomas  |Hung Yen  |Hung Yen |Phuong Chieu |870000  |Vietnam   |0939206009|https://rise.fairsketch.com/|10   |VIP            |
    And User clicks on Save in button
    And User searches for the newly added and move to tab Client info
    Then Display client info and check values detail
    
    @failure
    Scenario Outline: Add client with invalid field
    Given User navigates to Add client page
    When User fill the form add client from given sheetname "<SheetName>" and rowNumber <rowNumber>
    And User clicks on Save in button
    Then Display an error message right below the companyname field
    
    Examples:
    | SheetName | rowNumber  |
    | AddClient | 0          |
    | AddClient | 1          |
    