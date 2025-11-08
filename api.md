# 创建轮播图
POST /api/carousels 
# 查询轮播图（支持分页：?page=0&size=20&sort=sortOrder,asc）
GET /api/carousels 
# 查询单条（可选）
GET /api/carousels/{id}
# 修改轮播图
PUT /api/carousels/{id}
# 删除轮播图
DELETE /api/carousels/{id} 

创建： curl -X POST http://localhost:8080/api/carousels -H "Content-Type: application/json" -d '{"title":"Demo","imageUrl":"/uploads/demo.jpg","linkUrl":"https://example.com","sortOrder":1,"active":true}'
列表（分页）： curl "http://localhost:8080/api/carousels?page=0&size=20&sort=sortOrder,asc"
查询单条： curl http://localhost:8080/api/carousels/1
修改： curl -X PUT http://localhost:8080/api/carousels/1 -H "Content-Type: application/json" -d '{"title":"New","imageUrl":"/uploads/new.jpg","linkUrl":"","sortOrder":2,"active":true}'
删除： curl -X DELETE http://localhost:8080/api/carousels/1