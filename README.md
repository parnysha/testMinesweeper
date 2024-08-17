1. Собираем:
  mvn package clean
2. Собираем образ:
  docker build -t minesweeper:0.0.1 .
3. Запускаем на 8080 порту в фоне и изоляции:
  docker run -d -p 8080:8080 -t minesweeper:0.0.1
4. Проверяем работоспособность:
  curl http://localhost:8080
