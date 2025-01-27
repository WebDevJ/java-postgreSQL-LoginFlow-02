

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(255) DEFAULT 'USER',
                       account_type VARCHAR(255) NOT NULL,
                       linkedin VARCHAR(255),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
-- PostgreSQL does not automatically update a timestamp column on row changes using an inline definition

-- function will set updated_at to the current time (NOW()) before each update
CREATE OR REPLACE FUNCTION update_timestamp()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


-- attach this trigger function to your table so that it fires before any update

CREATE TRIGGER set_updated_at
BEFORE UPDATE ON users
FOR EACH ROW
EXECUTE PROCEDURE update_timestamp();



INSERT INTO users (email, password, role, account_type, linkedin) VALUES
                                                                      ('test1@example.com', 'hashedpassword1', 'USER', 'Client', 'https://www.linkedin.com/in/jamesrrobertsii'),
                                                                      ('test2@example.com', 'hashedpassword2', 'ADMIN', 'Recruiter', 'https://www.linkedin.com/in/jamesrrobertsii');
