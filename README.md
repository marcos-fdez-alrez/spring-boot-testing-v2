# spring-boot-testing-v2 

![Java CI with Maven](https://github.com/marcos-fdez-alrez/spring-boot-testing-v2/workflows/Java%20CI%20with%20Maven/badge.svg)

```gherkin
Feature: EUR-USD Conversor using the best Rate 
Background: The provider has an agreement with few REST API
Scenario: Consume the APIs in a Happy path scenario
    Given an amount of Euros as input
    When call endpoint GET /convert/eur/usd/{amount}
    Then ask rate for: https://api.frankfurter.app/latest 
    And  ask rate for: https://api.ratesapi.io/api/latest 
    And  ask rate for: https://api.exchangeratesapi.io/latest 
    And  use the best rate for USD  
    Then return {amount}*rate
```

## References:

https://cucumber.io/docs/gherkin/reference/

### Tests

mvn clean test

### Checkstyle

mvn checkstyle:checkstyle

### PMD

mvn pmd:check

mvn pmd:pmd

### Findbugs

mvn findbugs:gui

mvn findbugs:check

mvn findbugs:findbugs

mvn findbugs:help -Ddetail=true -Dgoal=check

mvn findbugs:help -Ddetail=true -Dgoal=findbugs

### Spotbugs - security issues

mvn spotbugs:check

mvn spotbugs:spotbugs

mvn spotbugs:gui

### Site Generation
mvn site
