for file in *.jar
do
  jarsigner -storepass googlewebtoolkit $file gwtip
done
