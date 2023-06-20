CREATE TABLE IF NOT EXISTS categories(
   id INTEGER PRIMARY KEY AUTO_INCREMENT,
   description VARCHAR(100) NOT NULL UNIQUE
);

INSERT INTO categories (description) VALUES 
('Suggestion'),
('Critic'),
('Compliment'),
('Comment');

CREATE TABLE suggestions (
   id INTEGER PRIMARY KEY AUTO_INCREMENT,
   date datetime default CURRENT_TIMESTAMP,
   description VARCHAR(500) NOT NULL,
   category_id INTEGER,
   FOREIGN KEY (category_id) REFERENCES categories(id)
);


INSERT INTO suggestions (description, category_id) VALUES
('This is a suggestion', 1);

INSERT INTO suggestions (description, category_id) VALUES
('This is a critic', 2);

INSERT INTO suggestions (description, category_id) VALUES
('This is a complimment', 3);

INSERT INTO suggestions (description, category_id) VALUES
('This is a comment', 4);
