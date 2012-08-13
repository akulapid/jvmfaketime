import java.util.*;
import java.lang.reflect.Method;
import static java.lang.System.*;

public class Tests {
    public static void main(String[] args) {            
        try {
            Method m = ClassLoader.class.getDeclaredMethod("loadLibrary", Class.class, String.class, Boolean.TYPE);
            m.setAccessible(true);
            m.invoke(null, java.lang.System.class, "jvmfaketime", false);
        } catch (Exception e) {
            System.out.println("couldn't load native library.");
        }
		
        out.println("current time: " + new Date());            

        startFakingTime();
		moveTimeBy(30 * 24 * 60 * 60 * 1000l);
        out.println("faked by 30 days: " + new Date());            
        out.println("faked still, true time: " + new Date());            
        resetTime();
        out.println("defaked: " + new Date());            
        startFakingTime();
        moveTimeBy(1 * 24 * 60 * 60 * 1000l);
        out.println("faked by 1 day again: " + new Date());            
    }
}
