# Usa una imagen base de Maven para compilar el proyecto
FROM maven:3.8.1-openjdk-17 AS build

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el archivo de configuración de Maven y las dependencias
COPY pom.xml ./
COPY src ./src

# Compila el proyecto
RUN mvn clean package -DskipTests

# Usa una imagen base de OpenJDK para ejecutar la aplicación
FROM openjdk:17-jdk

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el archivo JAR generado en la fase de construcción
COPY --from=build /app/target/ecom-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto en el que se ejecutará la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
