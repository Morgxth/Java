-- Table: users
CREATE TABLE users(
    id          INT             NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username    VARCHAR(255)    NOT NULL,
    password    VARCHAR(255)    NOT NULL
)
ENGINE = InnoDB;

CREATE TABLE roles(
    id      INT             NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(100)    NOT NULL
)

Engine = InnoDB;

-- Table for mapping users and roles: user_roles
CREATE TABLE user_roles (
    user_id INT NOT NULL,
    role_id INT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES  users (id),
    FOREIGN KEY (role_id) REFERENCES  roles (id),

    UNIQUE  (user_id, role_id)
)
    ENGINE = InnoDB;

-- Insert data

INSERT INTO  users VALUES (1, 'goygov', '$2y$12$iFCld7Z7eK31a4WEzw075uLAADfoSVLteq0BbOvLSb/XAbRPvSz76');

INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_roles VALUES (1, 2)