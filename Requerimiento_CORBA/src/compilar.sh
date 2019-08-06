echo '\nCompilando soportes....\n'
javac -d ../bin/ sop_corba/*.java
echo '\nCompilando servidor\n'
javac -d ../bin/ servidor/*.java
echo '\nCompilando cliente\n'
javac -d ../bin/ cliente/*.java
