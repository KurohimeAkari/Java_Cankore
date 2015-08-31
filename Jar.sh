#!/bin/bash
#Open Class name
name="Plate"
enname= "Kankore_Hayami"
#java varsion ( java -version )
version="1.8.0_25"
jar cvf "${name}.jar" *.class
jar xvf "${name}.jar"
#METAINF writing.
echo "Manifest-Version: 1.0
Created-By: ${version} (Oracle Corporation)
Main-Class: ${name}" > META-INF/MANIFEST.MF
jar cvfm "${name}.jar" 'META-INF/MANIFEST.MF' *.class
#open
#java -jar ${name}.jar ${name}

mv ${name}.jar Kankore_Hayami.jar
java -jar Kankore_Hayami.jar Kankore_Hayami