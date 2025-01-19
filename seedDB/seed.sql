

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(255) DEFAULT 'USER',
                       account_type VARCHAR(255) NOT NULL,
                       linkedin VARCHAR(255),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


INSERT INTO users (email, password, role, account_type, linkedin) VALUES
                                                                      ('test1@example.com', 'hashedpassword1', 'USER', 'Client', 'https://www.linkedin.com/in/jamesrrobertsii'),
                                                                      ('test2@example.com', 'hashedpassword2', 'ADMIN', 'Recruiter', 'https://www.linkedin.com/in/jamesrrobertsii');
