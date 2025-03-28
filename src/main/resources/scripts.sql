-- Criando a tabela users
CREATE TABLE users (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(500) NOT NULL,
    enabled BOOLEAN NOT NULL
);

-- Criando a tabela authorities
CREATE TABLE authorities (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authority_users FOREIGN KEY(username) REFERENCES users(username),
    CONSTRAINT unique_authority UNIQUE(username, authority)
);

-- Inserindo dados na tabela users, ignorando duplicações
INSERT INTO users (username, password, enabled)
VALUES
    ('user', '{noop}EazyBytes@12345', TRUE)
ON CONFLICT (username) DO NOTHING;

-- Inserindo dados na tabela authorities, ignorando duplicações
INSERT INTO authorities (username, authority)
VALUES
    ('user', 'read')
ON CONFLICT (username, authority) DO NOTHING;

-- Inserindo dados na tabela users para o admin, ignorando duplicações
INSERT INTO users (username, password, enabled)
VALUES
    ('admin', '{bcrypt}$2a$12$wp4JttoZ346TZYaPXxFjxubQPsQewI91j7y76184WYK3Sykbxu22y', TRUE)
ON CONFLICT (username) DO NOTHING;

-- Inserindo dados na tabela authorities para o admin, ignorando duplicações
INSERT INTO authorities (username, authority)
VALUES
    ('admin', 'admin')
ON CONFLICT (username, authority) DO NOTHING;
