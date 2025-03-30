# Calorie Calculator

Сервис для отслеживания дневной нормы калорий пользователя и учета съеденных блюд.

## 1. Описание проекта

Сервис позволяет:
- Искать пользователя по email и сохранять нового пользователя.
- Добавлять блюда.
- Добавлять и получать приемы пищи.
- Получать отчет с суммой калорий за день.
- Проверять, уложился ли пользователь в свою дневную норму калорий.
- Просматривать историю питания по дням.

## 2. Технологический стек

- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Postman
- Tomcat

## 3. Установка и запуск проекта

### 3.1. Клонирование репозитория

```bash
git clone https://github.com/RoninWay1618/calorie_calculator.git
cd calorie_calculator 
```

### 3.2. Настройка базы данных
1. Убедитесь, что PostgreSQL установлен и запущен.
2. Создайте базу данных (в IDEA File -> New -> DataSource -> PostgreSQL)
3. Проверьте настройки подключения к базе данных в классе
(com.example.calorieCalculator.configuration.MyConfig)
### 3.3. Добавьте в проект TomCat
1. File -> Run -> Edit Configuration -> "плюсик" -> TomCat Server Local -> war exploded
2. Запустите TomCat
###   3.4. Тестирование через Postman
   Используйте Postman для тестирования API-эндпоинтов. 
## 4. Описание API (эндпоинты)

### 1.Пользователи
 POST /users — создать пользователя

GET /users/{email} — получить информацию о пользователе

### 2. Блюда
POST /dishes — добавить блюдо

### 3. Прием пищи
POST /meals/{userEmail}— добавить прием пищи

GET /meals/{userEmail}/{localDate}— получить приемы пищи пользователя за день

### 4. Отчёты
GET /dailyReport/{userEmail}/{localDate} — отчет за день

GET /checkDailyNorm/{userEmail}/{localDate} — проверка, уложился ли пользователь в норму калорий

GET /history/{userEmail}/{localDate} — история питания