FROM openjdk:11
COPY out/artifacts/MiniDesafioBiblioteca_jar/MiniDesafioBiblioteca.jar .
CMD ["java","-jar","MiniDesafioBiblioteca.jar"]
