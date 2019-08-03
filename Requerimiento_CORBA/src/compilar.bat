
::idlj -fall interfaces.idl::NO EJECUTAR

javac -d ../bin sop_corba/*.java

javac -d ../bin servidor/*.java

javac -d ../bin cliente/*.java