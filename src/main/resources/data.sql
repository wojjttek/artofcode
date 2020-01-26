
INSERT INTO project (name) VALUES ( 'Mój pierwszy projekt' );

INSERT INTO task(title, description, author, created_at, project)
VALUES ('Dokonczyc zadanie z modulu 1', 'A', 'Rejestracja na FB', NOW(), 1),
       ('Obejrzeć moduł 2', 'B', 'Wprowadzenie do SPirnga', NOW(), 1),
       ('Stworzyć własny projekt na githubie', 'ABC', 'https://github.com', NOW(), 1);

INSERT INTO tag(name)
VALUES ('Pilne'),
       ('W domu'),
       ('W mieście')