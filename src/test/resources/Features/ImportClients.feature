Feature: Import clients functionality
 Background: User is logged to the HRM system
    Given User logged in with email "admin@demo.com" and password "riseDemo"

  @failure
  Scenario Outline: User uploaded the wrong file format
    Given User navigates to Import clients page
    When User upload files from given sheetname "<SheetName>" and rowNumber <rowNumber>
    Then User receives an error message right under the files field
    
    Examples:
    | SheetName     | rowNumber |
    | ImportClients | 0         |
    | ImportClients | 1         |
    | ImportClients | 2         |
    | ImportClients | 3         |
    
    
  @failure
  Scenario Outline: User uploaded the correct file format but wrong template
    Given User navigates to Import clients page
    When User upload files "<file_upload>" with wrong template
    And User clicks on Next button
    Then  User receives an error message "<message>" is displays
    
    Examples:
    | file_upload                                             | message                                                                   |
    | C:\Users\Admin\Downloads\TestCase.xlsx                  | There has an invalid header. The indicated field should be Company name.  |
    | ‪C:\Users\Admin\Downloads\NICHO3_Test plan_v1.1.xlsx     | There has an invalid header. The indicated field should be Company name.  |
  
    
    
    
    