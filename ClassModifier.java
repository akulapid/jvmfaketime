import javassist.*; 

public class ClassModifier {
    public static void main(String[] args) {        
        try {            
            System.loadLibrary("jvmfaketime");

            ClassPool pool = ClassPool.getDefault();
            CtClass systemClass = pool.get("java.lang.System");

			systemClass.addField(
				CtField.make("public static long fakeTimeOffset;", systemClass)
			);

            systemClass.addMethod(
				CtNewMethod.make(
					"public static void resetTime() { System.fakeTimeOffset = 0L; }",
				systemClass)
			);
			
            systemClass.addMethod(
				CtNewMethod.make(
					"public static void moveTimeBy(long offset) { System.fakeTimeOffset += offset; }",
				systemClass)
			);

            systemClass.addMethod(
				CtNewMethod.make(
					Modifier.PUBLIC | Modifier.STATIC | Modifier.NATIVE,
					CtClass.longType,
					"trueCurrentTimeMillis",
					new CtClass[0],
					new CtClass[0],
					null,
				systemClass)
			);

            systemClass.addMethod(
				CtNewMethod.make(
					Modifier.PUBLIC | Modifier.STATIC | Modifier.NATIVE,
					CtClass.voidType,
					"startFakingTime",
					new CtClass[0],
					new CtClass[0],
					null,
				systemClass)
			);

            systemClass.writeFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
