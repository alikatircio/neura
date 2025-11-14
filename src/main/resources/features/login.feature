Feature: Login Scenarios

  Scenario: Verify login page
    Given User navigates to "http://192.168.2.13:8080"
    When Wait "username" to element until visible
    Given Get page language with "username" placeholder
    Then Verify "user" page "username" element "username" value
    When Click to element "username"
    When User enter "superadmin" value to "username"
    When Click to element "password"
    When User enter "superadmin" value to "password"
    When Wait "loginBtn" to element until visible
    Then Pause
    When Click to element "loginBtn"
    When Wait "menuSettingBtn" to element until visible
    When Click to element "loginBtn"

  Scenario: Verify UI in second approach
    Given User navigates to "http://192.168.2.13:8080"
    Then Verify "login.username" placeholder
    And I click the text "login.username"
    When User types "superadmin" into "login.username"