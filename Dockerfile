# Usamos uma imagem base com o Java 21, que é o que você precisa
FROM openjdk:21-slim as build

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o código do projeto para o diretório de trabalho
COPY . .

# Concede permissão de execução ao Gradle Wrapper
RUN chmod +x ./gradlew

# Compila a aplicação e gera o arquivo JAR
RUN ./gradlew bootJar

# ----- Segunda etapa (Runtime) -----
# Usamos uma imagem base leve para a execução (não inclui o Gradle)
FROM openjdk:21-slim

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo JAR compilado da primeira etapa para a imagem final
COPY --from=build /app/build/libs/*.jar /app/app.jar

# Expõe a porta que a aplicação usará
EXPOSE 8080

# Define o comando de inicialização da aplicação
CMD ["java", "-jar", "app.jar"]