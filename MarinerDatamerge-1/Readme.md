# Getting Started

# How to run the project

## Running on IDE
* Clone the project
* Import on Eclipse/SpringToolSuite as Maven project
* Start service MarineDatamerge-1 on Boot Dashboard

## Prerequisites

You need to install the following tools if you want to run this application:

* [JDK](https://adoptopenjdk.net/) (the application is tested with OpenJDK 14)
* [Maven](http://maven.apache.org/) (the application is tested with Maven 3.6.3)

## Running the Application With Maven

Its possible to run the application by using the following command, on the project's folder:

    mvn clean spring-boot:run

The result will be exported to a file. Path will be displayed on the terminal.


# Tools and libraries

* IDE Eclipse/SpringToolSuite 4
* Spring boot
* Maven: build process, dependency management.
* hsqldb: memory database
* Jaxb: XML parse
* Google Guava: Int parsing
* Google Gson: Json parsing
* commons-csv: CSV output to file


## Data sorting and filtering

Read the 3 input files reports.json, reports.csv, reports.xml and output a combined CSV file with the following characteristics:

- The same column order and formatting as reports.csv
- All report records with packets-serviced equal to zero should be excluded
- records should be sorted by request-time in ascending order

Additionally, the application should print a summary showing the number of records in the output file associated with each service-guid.

Please provide source, documentation on how to run the program and an explanation on why you chose the tools/libraries used.

### Submission

You may fork this repo, commit your work and let us know of your project's location, or you may email us your project files in a zip file.

