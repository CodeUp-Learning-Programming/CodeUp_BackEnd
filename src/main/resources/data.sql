insert into usuario
    (nome, dt_nascimento, email, senha, xp, nivel, plano)
values
    ('Desenvolvedor', '2002-02-13','dev@sptech.school', '$2a$10$J2hkSo44vnvbd04d9oK3zu1DEUF0.yeb2KZnEmY2JhgL4Vs1QNWXa',100000,100, 'pro');

insert into materia (nome) values ('Algoritmos');

insert into fase
    (num_fase,titulo,fk_materia)
values
    (1, 'Primeiros Passos', 1),
    (2, 'Primeiros Passos', 1),
    (3, 'Primeiros Passos', 1),
    (4, 'Primeiros Passos', 1),
    (5, 'Primeiros Passos', 1),
    (6, 'Primeiros Passos', 1),
    (7, 'Primeiros Passos', 1),
    (8, 'Primeiros Passos', 1);

insert into exercicio
    (num_exercicio, conteudo_teorico, desafio, instrucao, layout_funcao, resposta, fk_fase)
values
    (1, 'Experimente problemas de lógica de portas, como o clássico "Você está em uma sala com duas portas - uma leva à liberdade e a outra à prisão. Existem duas pessoas, uma sempre diz a verdade e a outra sempre mente. Você pode fazer uma pergunta para determinar qual porta leva à liberdade?','Desafio do Exercício 1', 'Passo 1: Faça isso\n     Passo 2: Faça aquilo', 'function exercicio1() { /* Implemente aqui */ }', 'function respostaExercicio1() { /* Implemente a resposta aqui */ }', 1),
    (2, 'Experimente problemas de lógica de portas, como o clássico "Você está em uma sala com duas portas - uma leva à liberdade e a outra à prisão. Existem duas pessoas, uma sempre diz a verdade e a outra sempre mente. Você pode fazer uma pergunta para determinar qual porta leva à liberdade?','Desafio do Exercício 1', 'Passo 1: Faça isso\n     Passo 2: Faça aquilo', 'function exercicio1() { /* Implemente aqui */ }', 'function respostaExercicio1() { /* Implemente a resposta aqui */ }', 1),
    (3, 'Experimente problemas de lógica de portas, como o clássico "Você está em uma sala com duas portas - uma leva à liberdade e a outra à prisão. Existem duas pessoas, uma sempre diz a verdade e a outra sempre mente. Você pode fazer uma pergunta para determinar qual porta leva à liberdade?','Desafio do Exercício 1', 'Passo 1: Faça isso\n     Passo 2: Faça aquilo', 'function exercicio1() { /* Implemente aqui */ }', 'function respostaExercicio1() { /* Implemente a resposta aqui */ }', 1),
    (4, 'Experimente problemas de lógica de portas, como o clássico "Você está em uma sala com duas portas - uma leva à liberdade e a outra à prisão. Existem duas pessoas, uma sempre diz a verdade e a outra sempre mente. Você pode fazer uma pergunta para determinar qual porta leva à liberdade?','Desafio do Exercício 1', 'Passo 1: Faça isso\n     Passo 2: Faça aquilo', 'function exercicio1() { /* Implemente aqui */ }', 'function respostaExercicio1() { /* Implemente a resposta aqui */ }', 1),
    (1, 'Experimente problemas de lógica de portas, como o clássico "Você está em uma sala com duas portas - uma leva à liberdade e a outra à prisão. Existem duas pessoas, uma sempre diz a verdade e a outra sempre mente. Você pode fazer uma pergunta para determinar qual porta leva à liberdade?','Desafio do Exercício 1', 'Passo 1: Faça isso\n     Passo 2: Faça aquilo', 'function exercicio1() { /* Implemente aqui */ }', 'function respostaExercicio1() { /* Implemente a resposta aqui */ }', 2);


insert into usuario
    (nome, dt_nascimento, email, senha, xp, nivel)
values
    ('João da Silva', '1990-05-15', 'joao.silva@hotmail.com', '$2a$10$J2hkSo44vnvbd04d9oK3zu1DEUF0.yeb2KZnEmY2JhgL4Vs1QNWXa', 50000, 50),
    ('Maria Santos', '1985-08-22', 'maria.santos@gmail.com', '$2a$10$J2hkSo44vnvbd04d9oK3zu1DEUF0.yeb2KZnEmY2JhgL4Vs1QNWXa', 45000, 45),
    ('Antônio Pereira', '1992-03-10', 'antonio.pereira@outlook.com', '$2a$10$J2hkSo44vnvbd04d9oK3zu1DEUF0.yeb2KZnEmY2JhgL4Vs1QNWXa', 52000, 52),
    ('Mariana Almeida', '1988-12-05', 'mariana.almeida@hotmail.com', '$2a$10$J2hkSo44vnvbd04d9oK3zu1DEUF0.yeb2KZnEmY2JhgL4Vs1QNWXa', 48000, 48),
    ('Carlos Rodrigues', '1995-07-19', 'carlos.rodrigues@gmail.com', '$2a$10$J2hkSo44vnvbd04d9oK3zu1DEUF0.yeb2KZnEmY2JhgL4Vs1QNWXa', 55000, 55),
    ('Amanda Lima', '1993-10-08', 'amanda.lima@outlook.com', '$2a$10$J2hkSo44vnvbd04d9oK3zu1DEUF0.yeb2KZnEmY2JhgL4Vs1QNWXa', 49000, 49),
    ('Felipe Souza', '1991-02-25', 'felipe.souza@hotmail.com', '$2a$10$J2hkSo44vnvbd04d9oK3zu1DEUF0.yeb2KZnEmY2JhgL4Vs1QNWXa', 51000, 51),
    ('Luana Pereira', '1987-04-30', 'luana.pereira@gmail.com', '$2a$10$J2hkSo44vnvbd04d9oK3zu1DEUF0.yeb2KZnEmY2JhgL4Vs1QNWXa', 47000, 47),
    ('Ricardo Martins', '1997-06-12', 'ricardo.martins@outlook.com', '$2a$10$J2hkSo44vnvbd04d9oK3zu1DEUF0.yeb2KZnEmY2JhgL4Vs1QNWXa', 57000, 57),
    ('Isabella Oliveira', '1989-11-02', 'isabella.oliveira@hotmail.com', '$2a$10$J2hkSo44vnvbd04d9oK3zu1DEUF0.yeb2KZnEmY2JhgL4Vs1QNWXa', 46000, 46),
    ('Fernando Santos', '1996-09-14', 'fernando.santos@gmail.com', '$2a$10$J2hkSo44vnvbd04d9oK3zu1DEUF0.yeb2KZnEmY2JhgL4Vs1QNWXa', 58000, 58),
    ('Giovanna Castro', '1986-07-18', 'giovanna.castro@outlook.com', '$2a$10$J2hkSo44vnvbd04d9oK3zu1DEUF0.yeb2KZnEmY2JhgL4Vs1QNWXa', 52000, 52),
    ('Eduardo Alves', '1994-04-27', 'eduardo.alves@hotmail.com', '$2a$10$J2hkSo44vnvbd04d9oK3zu1DEUF0.yeb2KZnEmY2JhgL4Vs1QNWXa', 59000, 59),
    ('Vitória Fernandes', '1998-01-10', 'vitoria.fernandes@gmail.com', '$2a$10$J2hkSo44vnvbd04d9oK3zu1DEUF0.yeb2KZnEmY2JhgL4Vs1QNWXa', 48000, 48),
    ('Roberto Ribeiro', '1984-12-07', 'roberto.ribeiro@outlook.com', '$2a$10$J2hkSo44vnvbd04d9oK3zu1DEUF0.yeb2KZnEmY2JhgL4Vs1QNWXa', 60000, 60);
