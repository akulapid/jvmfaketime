cc -I '$JAVA_HOME/include' -I '$JAVA_HOME/include/darwin' -o libjvmfaketime.dylib -dynamiclib jvmfaketime.c
echo "compiled libjvmfaketime.so..."

sudo cp libjvmfaketime.dylib $JAVA_HOME/jre/lib
echo "installed libjvmfaketime in $JAVA_HOME/jre/lib"

javac -cp javassist.jar:. ClassModifier.java
java -cp .:javassist.jar -Djava.library.path=. ClassModifier
jar -cf jvmfaketime.jar java/
echo "generated jvmfaketime.jar in current directory"

echo "\nexecuting fake time functions, please verify manually"
javac -Xbootclasspath/p:jvmfaketime.jar Tests.java
java -cp . -Xbootclasspath/p:jvmfaketime.jar Tests
