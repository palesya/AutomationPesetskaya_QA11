mvn versions:display-dependency-updates
[INFO] No dependencies in Dependencies have newer versions.
[INFO] The following dependencies in Plugin Dependencies have newer versions:
[INFO]   org.aspectj:aspectjweaver ........................... 1.9.6 -> 1.9.9.1


mvn versions:use-latest-versions
[INFO] --- versions-maven-plugin:2.10.0:use-latest-versions (default-cli) @ AutomationPesetskaya_QA11 ---
[INFO] Major version changes allowed

mvn clean test -Dtest=Homework_17
[INFO] Running Homework_17.Homework_17
[ERROR] Tests run: 15, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 11.257 s <<< FAILURE! - in Homework_17.Homework_17
[ERROR] deleteUser_test(Homework_17.Homework_17)  Time elapsed: 0.403 s  <<< FAILURE!
java.lang.AssertionError:
1 expectation failed.
Expected status code <204> but was <404>.

mvn clean test -Dconfig=moodpanda "-Dtest=Homework_18.MoodPandaTest"
[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 10.377 s - in Homework_18.MoodPandaTest

