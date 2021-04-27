Feature: Excel data reader

  Scenario: User login Escenario
    Given User in principal Page
    When User Login username and passwor with excel at "/excel_resources/login.xlsx"
