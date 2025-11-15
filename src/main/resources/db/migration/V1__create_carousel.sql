CREATE TABLE IF NOT EXISTS carousel (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  image_url VARCHAR(1024) NOT NULL,
  link_url VARCHAR(1024),
  sort_order INT DEFAULT 0,
  active BOOLEAN DEFAULT TRUE,
  target_type VARCHAR(100) DEFAULT NULL,
  target_id VARCHAR(100) DEFAULT NULL,
  start_at TIMESTAMP NULL DEFAULT NULL,
  end_at TIMESTAMP NULL DEFAULT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 如果需要索引,使用正确的语法
CREATE INDEX idx_carousel_target ON carousel(target_type, target_id);
CREATE INDEX idx_carousel_active ON carousel(active);