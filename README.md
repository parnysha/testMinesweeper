Собираем:
  mvn package clean
Собираем образ:
  docker build -t minesweeper:0.0.1 .
Запускаем на 8080 порту в фоне и изоляции:
  docker run -d -p 8080:8080 -t minesweeper:0.0.1

curl http://localhost:8080
