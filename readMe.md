# 运行dockers 
docker compose up -d

# 查看容器状态
docker compose ps

# 用 Maven 编译并确认编译产物
mvn clean package -DskipTests

# 运行项目
mvn spring-boot:run