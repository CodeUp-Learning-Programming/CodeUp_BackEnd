insert into usuario
    (nome, dt_nascimento, email, senha)
values
    ('Desenvolvedor', '2002-02-13','dev@sptech.school', '$2a$10$J2hkSo44vnvbd04d9oK3zu1DEUF0.yeb2KZnEmY2JhgL4Vs1QNWXa');

insert into materia (nome) values ('Algoritmos');

insert into fase
    (num_fase,titulo,fk_materia)
values
    (1, 'Primeiros Passos', 1);

insert into exercicio
    (num_exercicio, conteudo_teorico, desafio, instrucao, layout_funcao, resposta, fk_fase)
values
    (1, 'Experimente problemas de lógica de portas, como o clássico "Você está em uma sala com duas portas - uma leva à liberdade e a outra à prisão. Existem duas pessoas, uma sempre diz a verdade e a outra sempre mente. Você pode fazer uma pergunta para determinar qual porta leva à liberdade?','Desafio do Exercício 1', 'Passo 1: Faça isso\n     Passo 2: Faça aquilo', 'function exercicio1() { /* Implemente aqui */ }', 'function respostaExercicio1() { /* Implemente a resposta aqui */ }', 1);
