# RMS Api tests
  BDD tests for RMS api

  - This project uses java language for writting test steps as and gradle tool for building the project
  - This project uses rest-assured library for api requests and response validation, cucumber-java for writting tests in BDD style
  - This project has .feature file within test/tesources/features which contains the test scenarios in gherkin format and corresponding step definitions can be found in test/java/cucumber/stepdefs. cucumber task in build.gradle includes these details as well
  - Build the project first `gradle build`
  - To run the tests run `gradle cucumber`
  - To view reports check target/reports folder
  - Please check Scenario Details.md to look into scenarios and notes against it.
  


