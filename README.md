#SWRLAPI SWRLGEO - An implementation of geospatial functions for SWRL

This project provides a collection of functions for geospatial operations using SWRL for a SWRLAPI implementation.

This example library defines the Egenhofer Relation functions available in the GeoSPARQL standard for the usage in SWRL. In addition it implements utility functions to compare features of geometries such as its length, its type and its validity.

The ```./src/main/resources/owl``` directory contains an OWL file called ```swrlgeo.owl```
that [defines this built-in](https://github.com/protegeproject/swrlapi/wiki/SWRLBuiltInBridge#defining-built-ins-in-owl) so that it can be used by importing ontologies. 

This built-in library can be used in two primary ways:

* Interactively, in the [Protégé 5 ontology editor](http://protege.stanford.edu/)
* In [SWRLAPI](https://github.com/protegeproject/swrlapi/wiki)-based applications

### Using this Built-in Library in Protégé 5

The JAR containing the built-in library must first be placed in Protégé's class path so
that the built-ins it defines can be resolved.
A Protégé installation contains a plugin directory that can be used for this purpose.
The name of the directory is operating system dependent.
On OS X this subdirectory is called ```./Contents/Java/plugins/```.
So a complete plugin directory path might be something like ```/Applications/Protege-5.0.0/Protégé.app/Contents/Java/plugins/```.

Copy the JAR to the plugins directory and then open Protégé 5.

To run the SQWRL query, open the importing ontology in Protégé.
If it is not already enabled, Go to the ```Windows->Tabs``` menu item and select the ```SQWRLTab``` item.
Navigate to the [SQWRLTab](https://github.com/protegeproject/swrlapi/wiki/SQWRLQueryTab) and select
and execute the SQWRL query that used the ```stringsEqual``` built-in.

### Using the Built-in Library in a SWRLAPI-based Application

First use OWLAPI to create an ontology and load the ontology defining the built-in.
The SWRLAPI's [SWRL Rule Engine API](https://github.com/protegeproject/swrlapi/wiki#SWRL_Rule_Engine_API) 
or [SQWRL Query API](https://github.com/protegeproject/swrlapi/wiki#SQWRL_Query_API)
can then be used to run rules or queries that make use of this built-in.

### Building and Installing

To build this library you must have the following items installed:

+ [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
+ A tool for checking out a [Git](http://git-scm.com/) repository
+ Apache's [Maven](http://maven.apache.org/index.html)

Get a copy of the latest code:

    git clone https://github.com/protegeproject/swrlapi-builtin-library-example.git 

Change into the ```swrlapi-builtin-library-example``` directory:

    cd swrlapi-builtin-library-example

Build it with Maven:

    mvn clean install

On build completion, your local Maven repository will contain generated ```swrlapi-builtin-library-example-${version}.jar```.
The ```./target``` directory will also contain this JAR.

As per the [built-in library installation instructions](https://github.com/protegeproject/swrlapi/wiki/SWRLBuiltInBridge#Loading_a_Builtin_Implementation_Class_at_Runtime), 
this JAR must be available in an application's class path for dynamic runtime resolution.

#### Questions

If you have questions about this library, please go to the main
Protégé website and subscribe to the [Protégé Developer Support
mailing list](http://protege.stanford.edu/support.php#mailingListSupport).
After subscribing, send messages to protege-dev at lists.stanford.edu.
