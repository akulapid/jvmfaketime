#include <jni.h>
#include <sys/time.h>
#include <stdlib.h>

long long system_time() {
	struct timeval now;
	gettimeofday(&now, NULL);
	return now.tv_sec * 1000LL + now.tv_usec / 1000LL;
}

JNIEXPORT jlong JNICALL Fake_CurrentTimeMillis(JNIEnv* env, jclass jc) {
	jfieldID field = (*env)->GetStaticFieldID(env, jc, "fakeTimeOffset", "J");
	if (field == NULL || (*env)->ExceptionCheck(env)) {
 		(*env)->ExceptionClear(env);		
   		return 0;
	}
	jlong offset = (*env)->GetStaticLongField(env, jc, field);
	return system_time() + offset;
}

JNIEXPORT jlong JNICALL Fake_TrueCurrentTimeMillis(JNIEnv* env, jclass jc) {
	return system_time();
}

static JNINativeMethod fake_method[] = {
    { "currentTimeMillis", "()J", (void*) &Fake_CurrentTimeMillis },
    { "trueCurrentTimeMillis", "()J", (void*) &Fake_TrueCurrentTimeMillis }
};

JNIEXPORT void JNICALL Java_java_lang_System_startFakingTime(JNIEnv *env, jclass cls) {
    (*env)->RegisterNatives(env, cls, fake_method, sizeof(fake_method) / sizeof(fake_method[0]));
}
