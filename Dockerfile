
#Etapa 1; build
#usa a imagem Maven com Java 25 para compilar o projeto
FROM maven:3.9.16-eclipse-temurin-25 AS build
#define o diretorio de trabalho dentro do container
WORKDIR /opt/app
#copia todo codigo-fonte para detro do container
COPY . .
#executa o build completo do projeto, gerando o Jar em /opt/app/target/
RUN mvn clean package -DskipTests

#Etapa 2: runtime
#usa uma imagem leve do Java 25 para rodar o app
FROM eclipse-temurin:25-jre
WORKDIR /opt/app
#copia o JAR gerado na etapa de build para a imagem final
COPY --from=build /opt/app/target/*.jar /opt/app/app.jar
#expõe a porta 8080 (padrao do Spring Boot)
EXPOSE 8080
#define o comando para iniciar a aplicação
ENTRYPOINT ["java","-jar","app.jar"]