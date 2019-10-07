# google_test

### Prerequisites:
1) Docker installed (min version  1.6.0)
2) JDK 11
3) Maven 3

### To run tests

Tests can be executed in one of the browsers: Cheome or Firefox.

To launch tests:
1) execute maven command `mvn -Dbrowser.name=FIREFOX clean test` (use `-Dbrowser.name=CHROME`) to run test in Chrome

or

2) run tests from your favourite IDE (remember to specify -Dbrowser.name property, otherwise browser will be defaulted to Chrome)


