INSERT INTO task(title, description, author, created_at)
VALUES ('Dokonczyc zadanie z modulu 1', 'A', 'Rejestracja na FB', NOW()),
       ('Obejrzeć moduł 2', 'B', 'Wprowadzenie do SPirnga', NOW()),
       ('Stworzyć własny projekt na githubie', 'ABC', 'https://github.com', NOW());

INSERT INTO tag(name)
VALUES ('Pilne'),
       ('W domu'),
       ('W mieście')