# 麻將紀錄器 (Mahjong Record)

這是一個用來紀錄麻將輸贏情況的系統，透過簡單的操作介面，幫助玩家快速記錄與查詢遊戲結果。

## 🔗 作品連結

- 🔍 [render租用平台展示](https://mahjong-record.onrender.com/)
    - 測試帳號：`test@mail.com`
    - 密碼：`123`
- 💻 [GitHub 原始碼](https://github.com/dchair/mahjong-record)

---

## 📖 使用說明

1. **登入會員系統**：請先點擊右上角進行登入。
2. **設定玩家資訊**：在「玩家功能」中新增玩家名稱與初始籌碼。
3. **設定遊戲規則**：進入「麻將遊戲設定」，設定每台對應金額。
4. **開始記錄遊戲**：
    - 進入「開始記錄」頁面，選擇先前設定的遊戲規則。
    - 選擇四名玩家（需滿四人才能開始記錄）。
5. **查詢遊戲紀錄**：可至「查看遊戲紀錄」頁面瀏覽已儲存的戰績資料。

---

## 🛠 技術架構

- **開發語言**：Java
- **後端框架**：Spring Boot
- **資料庫**：PostgreSQL
- **依賴管理工具**：Maven
- **部署平台**：Render
- **測試工具**：Postman
- **樣板引擎**：Thymeleaf

---

## 📦 功能列表

- ✅ 玩家、遊戲金額設定與遊戲紀錄的 **建立 / 刪除** 功能（暫無 Update，因本專案以紀錄為主）
- ✅ 各資料列表支援 **排序功能**
- ✅ 後台登入驗證（Session 搭配 AOP 實作）
- 🛠 連莊與輸贏邏輯尚有優化空間，後續有時間將進一步完善
- 🛠 UI 未實作完整 RWD，日後將補強

---

## 🚀 快速啟動專案（本地端）

```bash
# 1. 下載專案
git clone https://github.com/dchair/mahjong-record.git
cd mahjong-record

# 2. 設定資料庫連線 (請於 application.properties 中設定 PostgreSQL 資訊)

# 3. 執行專案
./mvnw spring-boot:run