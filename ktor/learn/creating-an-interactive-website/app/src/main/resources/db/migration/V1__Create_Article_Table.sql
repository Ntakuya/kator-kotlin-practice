CREATE TABLE IF NOT EXISTS article (
   article_id uuid,
   title VARCHAR(255) NOT NULL,
   body text NOT NULL,
   created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(), 
  PRIMARY KEY(article_id)
)
