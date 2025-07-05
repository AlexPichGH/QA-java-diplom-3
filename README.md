<h2>UI тестирование приложения Stellar Burgers</h2>

<h3>Используемые технологии:</h3>

Java 11.0.26, Maven 3.9.9, JUnit 4.13.2, Selenium 4.34.0, Allure 2.29.1, Datafaker 1.8.0, Gson 2.13.1.

<h3>Тестирование проходило в браузерах:</h3>

* Google Chrome (версия: 138.0.7204.51)
* Яндекс Браузер (версия: 25.6.0.2370, версия драйвера: 25.6.0.2261)

<h3>Запуск тестов в Google Chrome:</h3>

````
mvn clean test 
````

<h3>Для запуска тестов в Яндекс Браузере:</h3>

1. Установить Яндекс Браузер (если установлен, обновить до последней версии)
2. Скачать драйвер последней стабильной версии для вашей ОС (https://github.com/yandex/YandexDriver/releases)
3. Скопировать драйвер в папку `src/test/resources`
4. В зависимости от вашей ОС закомментировать и раскомментировать строки 40/42 и 45/47 в BaseTest.java:
   `src/test/java/ru/yandex/practicum/tests/BaseTest.java`
5. Прописать путь до исполняемого файла Яндекс Браузера в строке 45/47 в BaseTest.java:
   `src/test/java/ru/yandex/practicum/tests/BaseTest.java`

<h3>Запуск тестов в Яндекс Браузере:</h3>

````
mvn clean test -Dbrowser=yandex
````

<h3>Создание отчёта Allure (после запуска тестов):</h3>

````
mvn allure:report
````