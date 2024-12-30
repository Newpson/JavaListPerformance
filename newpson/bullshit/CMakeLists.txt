file(GLOB Performance_JAVA *.java)

add_jar(Performance
	SOURCES ${Performance_JAVA}
	MANIFEST MANIFEST.MF)
