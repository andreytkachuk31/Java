#include <stdio.h>
#include "NativeClass.h"

JNIEXPORT jint JNICALL Java_NativeClass_printOne (JNIEnv *env, jclass myclass) {
	printf("One");
	return 10;
}