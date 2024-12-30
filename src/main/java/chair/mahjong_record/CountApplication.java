package chair.mahjong_record;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class CountApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        // 讀取環境變數
        String dbHost = dotenv.get("DB_HOST");
        String dbPort = dotenv.get("DB_PORT");
        String dbName = dotenv.get("DB_NAME");
        String dbUser = dotenv.get("DB_USER");
        String dbPass = dotenv.get("DB_PASS");

        // 使用讀取的變數來設置資料庫配置
        System.setProperty("DB_HOST", dbHost);
        System.setProperty("DB_PORT", dbPort);
        System.setProperty("DB_NAME", dbName);
        System.setProperty("DB_USER", dbUser);
        System.setProperty("DB_PASS", dbPass);

        SpringApplication.run(CountApplication.class, args);

    }

}
