# Imagen base para el contenedor de compilación de Angular
FROM node:18 as angular
WORKDIR /app
COPY frontend/webapp16/package*.json ./
RUN npm install
COPY frontend/webapp16 .
RUN npm run build

# Imagen base para el contenedor de compilación de Spring Boot
FROM maven:3.6.3-openjdk-17 as builder
WORKDIR /futProLeague

# Copia las dependencias del proyecto
COPY /backend/pom.xml ./pom.xml

# Copia el código del proyecto
COPY /backend/src ./src

# Copia la aplicación Angular construida a la carpeta de recursos estáticos de Spring Boot
COPY --from=angular /app/dist/webapp16 src/main/resources/public/new

# Compila el proyecto
RUN mvn clean package -DskipTests=true

# Imagen base para el contenedor de la aplicación
FROM openjdk:17.0.2 AS runtime
WORKDIR /futProLeague

# Copia el JAR del contenedor de compilación
COPY --from=builder /futProLeague/target/*.jar .

# Indica el puerto que expone el contenedor
EXPOSE 8443

# Comando que se ejecuta al hacer docker run
CMD [ "java", "-jar", "backend-0.0.1-SNAPSHOT.jar"]