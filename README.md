# RMS Api tests
  BDD tests for RMS api

  - This project uses `java` for writting test cases and `gradle` for building the project
  - This project uses `rest-assured` library for api requests and response validation, `cucumber-java` for writting tests in BDD style
  - This project has .feature file in test/tesources/features folder which contains the test feature and scenarios in gherkin format and corresponding step definitions can be found from test/java/cucumber/stepdefs.
  - build.gradle defines a `cucumber` task which includes path to feature file and step defs and path to cucumber reports
  - TestRunner.java have additional cucumber properties  
  - Build the project first with `gradle build`
  - To run the tests run command `gradle cucumber`
  - To view reports check target/reports folder
  - Please check `ScenarioDetails.md` to look into scenarios and notes against it.
  


