cc -I $JAVA_HOME/include -I $JAVA_HOME/include/linux -o libjvmfaketime.so -shared  -fPIC jvmfaketime.c
echo "compiled libjvmfaketime.so..."

sudo cp libjvmfaketime.so $JAVA_HOME/jre/lib/i386
echo "installed libjvmfaketime in $JAVA_HOME/jre/lib/i386"

javac -cp javassist.jar:. ClassModifier.java
java -cp .:javassist.jar -Djava.library.path=. ClassModifier
jar -cf jvmfaketime.jar java/
echo "generated jvmfaketime.jar in current directory"

echo "\nexecuting fake time functions, please verify manually"
javac -Xbootclasspath/p:jvmfaketime.jar Tests.java
java -cp . -Xbootclasspath/p:jvmfaketime.jar Tests
