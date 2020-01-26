DROP TABLE IF EXISTS task;
DROP TABLE IF EXISTS attachment;
DROP TABLE IF EXISTS tag;
DROP TABLE IF EXISTS tag_task;
DROP TABLE IF EXISTS project;

CREATE TABLE project (
id IDENTITY,
name VARCHAR(100)
);

CREATE TABLE task (
    id IDENTITY,
    title VARCHAR(100),
    description VARCHAR(1024),
    author VARCHAR(100),
    created_at TIMESTAMP,
    project NUMERIC NULL,
    FOREIGN KEY (project) REFERENCES project(id)
);

CREATE TABLE attachment (
    filename VARCHAR(100) UNIQUE,
    comment VARCHAR(1024),
    task NUMERIC,
    FOREIGN KEY (task) REFERENCES task (id)
);

CREATE TABLE tag (
id IDENTITY,
name VARCHAR(100)
);

CREATE TABLE tag_task (
tag NUMERIC NOT NULL,
task NUMERIC NOT NULL,
FOREIGN KEY (tag) REFERENCES tag(id),
FOREIGN KEY (task) REFERENCES task(id)
);