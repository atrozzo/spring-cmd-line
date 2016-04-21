
#README

## Statistic File Analyzer

This is a command line application base on SpringBoot technology and on CommandLIneRunner interface. The applicaiton allow to execute analytics command on a target file and print on cosole 
the execution result. 


### Quick Start

###Build
The application can be built using Java version 1.8 and maven version 3.x : 
In the root folder run command : mvn clean install. This will generate: 
1) Jar file named : statistics-0.0.1-SNAPSHOT.jar
2) file will be generated under this folder : /data-analyser/target/

###Run it 

From command line run the application as normal java application using JAR packaging and invoke help for an overview of teh available commands:

java -jar statistics-0.0.1-SNAPSHOT.jar --help=help

This will show the basic commands available : 

1. --file ,used to set the absolute path of the file to analyze. 
2. --analyze, used to define the algoritm to sue on the file specified 

 E.g :  To use a specific file and rund teh defualt statistic algoritm run the follow command,
 
 java -jar statistics-0.0.1-SNAPSHOT.jar --file=/User/user/data.csv --analyze=integers 
 
 That command will execute a statistic algorithm called integers.

###RExtensibility : Command definition 
 
   Command are defined as java Spring beans in the configuration. Each command it s annotated with a custom Annotation and a specific  java interface 
   that mark the bean as command to execute and as well as possible argument via command line : 
   1. Custom annotation : MLDWireOption
   2. Interface : MLDCommand
   
   The annotation set as well the name and he descrition of teh command that can be executed. 
   
   E.g.: 
   
   @MLDWireOption(value = "help", description = ("show all available commands"),
   		shortValue = ("h")) // the name of the menu is coming from the property as well the bean name.
   @Service(value = "help")
   public class MLDMenuHelp implements MLDCommand<MLDResult> {
   
   
   Define a command "help" witha  description in the value property of the annotation. The same name it sused for define the command as spring Bean. 
   The list of command in this way it s built at runtime and teh command are executed in base of what get passed as argument in command line. 
    
 
### Algorithm definition 
   
   As well teh available algoritm are marked with a custom annotation : MLDWireAlg and a specific interface :MLDAlgorithm. 
   
   E.g.:
   @MLDWireAlg(value = "integers", description = ("collect infomations abou integers"))
   @Service(value = "analyze-integers")
   public class MLDNumbersAnalyzer implements MLDAlgorithm<MLDResult> {
   
   In this way like for the command the definition of the possible available algorithm ot execute its transparent ot the system and 
   and can be implmeneted without changing the application. 
   
   
   
   
   
   
   
   
   
 
 
 
 
