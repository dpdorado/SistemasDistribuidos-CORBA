
::idlj -fall interfaces.idl::NO EJECUTAR

javac -d ../bin sop_corba/*.java

javac -d ../bin -cp ".;./jars/mysql-connector-java-5.1.5-bin.jar" servidor/*.java

javac -d ../bin cliente/*.java