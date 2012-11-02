jvmfaketime lets you fake System.currentTimeMillis() calls to return anything you want. Since currentTimeMillis() is the source of all time in java, the jvm time is as good as faked. This is useful for performing time dependent tests in your application.

#Usage

jvmfaketime adds additional methods to java.lang.System to let you manipulate time.

System.startFakingTime() gets you into fake time mode
System.moveTimeBy(long offset) sets an offset from the current time (real or fake) by which to move time by
System.resetTime() resets any offsets you've applied so you'd get the real time

For example,

    System.out.println("current time: " + new Date());            

    System.startFakingTime();
	System.moveTimeBy(30 * 24 * 60 * 60 * 1000l);
    System.out.println("faked by 30 days: " + new Date());            

outputs,

	current time: Fri Nov 02 13:44:25 IST 2012
	faked by 30 days: Sun Dec 02 13:44:25 IST 2012   

#Install

There are a couple of steps needed for installation.

1. Compile jvmfaketime.c as a jni library and place it in sun.boot.library.path for your jre.
2. Build a jar out of ClassModifer.java and place it in a bootclasspath for your jre (use -Xbootclasspath/p to specify custom bootclasspaths).

There are a couple of very rudimentary install scripts for linux and osx which probably will work. Or you can use them to write your own for your environment.
 
