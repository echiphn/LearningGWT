Manning GWT In Practice Ch8 Notes
-------------------------------------------------------------

"Testing and Continuous Integration"

This project again uses Maven.

This project is setup to run tests, and a benchmark example with 
code coverage using the gwtcoverage code coverage patch JAR (explained in the text).
Note, you must pass "-Dgwt-coverage-enable=true" to the JVM for coverage data
to be collected properly (see the text) - for Maven you use settings.xml (M2) or 
build.properties (M1) to pass this in the google.webtoolkit.extrajvmargs property.

To run the project and get the reports use the following:

Maven 2:

mvn clean install (install target will invoke needed goals and create a WAR)
mvn clean gwt:gwt (runs the GWTShell)
mvn clean test site (runs tests and builds the "site" to generate reports - see text)


Maven 1:

maven clean gwt (runs the GWTShell)
maven clean gwt:war (creates a WAR)
maven clean test (runs tests and generates reports - uses maven.xml postGoal for report step)


To run the Benchmark example you can use the compile-javac and ArrayListAndVectorBenchmarkTest-hosted 
scripts in the "benchmark-example" folder as an example (these may need to be modified for your platform).
After the benchmark runs, you can view the report data with "$GWT_HOME/benchmarkViewer."  
Alternatively, you can just run the viewer from within the benchmark-example directory to see an existing report.

(See ../README.txt.)