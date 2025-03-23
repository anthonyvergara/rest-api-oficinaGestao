# Fase de build
FROM ubuntu:latest AS build

# Atualiza o repositório e instala o OpenJDK 17
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

# Define um diretório de trabalho para garantir o caminho correto
WORKDIR /app

# Copia todos os arquivos do diretório atual para o diretório /app no container
COPY . .

# Instala o Maven
RUN apt-get install maven -y

# Compila o projeto usando Maven
RUN mvn clean install

# Fase final
FROM openjdk:17-jdk-slim

# Expondo a porta onde a aplicação vai rodar
EXPOSE 8080

# Copia o arquivo JAR gerado na fase de build para o container final
COPY --from=build /app/target/api-oficina-0.0.1-SNAPSHOT.jar app.jar

# Configura o ponto de entrada do contêiner
ENTRYPOINT ["java", "-jar", "app.jar"]
