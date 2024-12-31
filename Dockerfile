# 使用輕量化 JRE 作為基礎映像
FROM eclipse-temurin:17-jre

# 設定工作目錄
WORKDIR /app

# 複製已打包好的 JAR 檔案
COPY mahjong_record-0.0.1.jar app.jar

# 設定容器運行的端口
EXPOSE 8080

# 設定容器啟動命令
ENTRYPOINT ["java", "-jar", "app.jar"]