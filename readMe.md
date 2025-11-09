# 运行dockers 
docker compose up -d

# 查看容器状态
docker compose ps

# 在容器内查看数据库列表
docker exec -it mysql_dev mysql -u myuser -pmypass -e "SHOW DATABASES;"

# 在容器内查看指定数据库的表
docker exec -it mysql_dev mysql -u myuser -pmypass -e "USE mydb; SHOW TABLES;"

# 在容器内查看指定表的结构
docker exec -it mysql_dev mysql -u myuser -pmypass -e "USE mydb; DESCRIBE carousel;"

# 在容器内查看 Flyway 迁移历史记录
docker exec -it mysql_dev mysql -u myuser -pmypass -e "USE mydb; SELECT installed_rank, version, description, success, installed_on FROM flyway_schema_history ORDER BY installed_rank DESC;"

# 用 Maven 编译并确认编译产物
mvn clean package -DskipTests

# 运行项目
mvn spring-boot:run
# （实时打印日志）
mvn spring-boot:run -Dspring-boot.run.profiles=docker

# 手动执行 SQL 脚本（例如创建轮播图表）
docker exec -i mysql_dev mysql -u myuser -pmypass mydb