1. Собираем образ:
  docker build -t minesweeper:0.0.1 .
2. Запускаем на 8080 порту в фоне и изоляции:
  docker run -d -p 8080:8080 -t minesweeper:0.0.1
3. Проверяем работоспособность:
  curl http://localhost:8080
