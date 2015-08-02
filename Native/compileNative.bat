rem Compile class
javac src\NativeClass.java -d nativeCode
rem Create header 
cd nativeCode
javah NativeClass
rem Compile .c file
gcc -Wall -D_JNI_IMPLEMENTATION_ -Wl,--kill-at -I"C:\Program Files\Java\jdk1.7.0_13\include" -I"C:\Program Files\Java\jdk1.7.0_13\include\win32" -shared NativeClass.c -o Native.dll
rem Delete .class file
del NativeClass.class

PAUSE 