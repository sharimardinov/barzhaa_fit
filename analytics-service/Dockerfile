# Используем JDK 21
FROM openjdk:21-jdk-slim

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем собранный JAR-файл в контейнер
COPY target/*.jar app.jar

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "app.jar"]
