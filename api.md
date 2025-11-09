# 创建轮播图
POST /api/carousels 
# 请求体 (JSON):
title (string) — 必填，最大长度 255
imageUrl (string) — 必填，最大长度 1024（可以是相对路径 "/uploads/xxx.jpg" 或完整 URL）
linkUrl (string) — 可选，最大长度 1024
sortOrder (integer) — 可选，默认 0
active (boolean) — 可选，默认 true

# 查询轮播图（支持分页：?page=0&size=20&sort=sortOrder,asc）
GET /api/carousels
# Query 参数:
page (int) — 默认 0
size (int) — 默认 20
sort (string) — 默认 "sortOrder,asc"；格式 "property,direction"（direction 为 asc 或 desc）

# 查询单条（可选）
GET /api/carousels/{id}
# Path 参数:
id (long) — 资源 id

# 修改轮播图
PUT /api/carousels/{id}
# Path 参数:
id (long)

# 删除轮播图
DELETE /api/carousels/{id}
# Path 参数:
id (long)