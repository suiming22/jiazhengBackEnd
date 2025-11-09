CREATE TABLE IF NOT EXISTS carousel (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  image_url VARCHAR(1024) NOT NULL,         -- 对应 DTO 的 pic
  link_url VARCHAR(1024),
  sort_order INT DEFAULT 0,
  active BOOLEAN DEFAULT TRUE,
  target_type VARCHAR(100) DEFAULT NULL,    -- 新：target.type
  target_id VARCHAR(100) DEFAULT NULL,      -- 新：target.id
  start_at TIMESTAMP NULL DEFAULT NULL,     -- 新：startAt (UTC 推荐使用 ISO 格式由应用解析)
  end_at TIMESTAMP NULL DEFAULT NULL,       -- 新：endAt
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 索引：如需按 target 查询可保留或删除
CREATE INDEX IF NOT EXISTS idx_carousel_target ON carousel(target_type, target_id);