INSERT INTO public.oficina (id, company_number, nome_oficina, vat_number)
VALUES
    (1, 123456789, 'Oficina São João', 987654321),
    (2, 987654321, 'Oficina Auto Fácil', 123456789),
    (3, 555666777, 'Oficina Rápida', 112233445);

INSERT INTO public.dono_oficina (id, data_nascimento, email, nome, sobrenome, senha, usuario)
VALUES
    (1, '1985-05-20', 'joao.silva@email.com', 'João', 'Silva', 'senha123', 'joao_silva'),
    (2, '1990-08-15', 'ana.pereira@email.com', 'Ana', 'Pereira', 'senha456', 'ana_pereira'),
    (3, '1982-02-10', 'mario.oliveira@email.com', 'Mário', 'Oliveira', 'senha789', 'mario_oliveira'),
    (4, '1995-11-30', 'lucas.souza@email.com', 'Lucas', 'Souza', 'senha101', 'lucas_souza'),
    (5, '1987-04-22', 'carla.gomes@email.com', 'Carla', 'Gomes', 'senha202', 'carla_gomes');

INSERT INTO public.oficina_dono (oficina_id, dono_oficina_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (1, 4),
    (2, 5);

INSERT INTO public.cliente (id, data_nascimento, email, nome, sobrenome, numero_drive, numero_passaporte, numero_rg, oficina_id)
VALUES
    (1, '1990-06-15', 'pedro.santos@email.com', 'Pedro Henrique Santos', 'Santos', 123456789, 987654321, 112233445, 1),
    (2, '1985-03-25', 'mariana.martins@email.com', 'Mariana Alves Martins', 'Martins', 223344556, 876543210, 223344556, 1),
    (3, '1992-07-10', 'lucas.oliveira@email.com', 'Lucas Gabriel Oliveira', 'Oliveira', 334455667, 765432109, 334455667, 1),
    (4, '1988-09-20', 'ana.souza@email.com', 'Ana Carolina Souza', 'Souza', 445566778, 654321098, 445566778, 1),
    (5, '1995-01-05', 'luana.costa@email.com', 'Luana Beatriz Costa', 'Costa', 556677889, 543210987, 556677889, 1),
    (6, '1991-11-17', 'andre.silva@email.com', 'André Luiz Silva', 'Silva', 667788990, 432109876, 667788990, 1),
    (7, '1987-12-30', 'marcio.lima@email.com', 'Márcio José Lima', 'Lima', 778899001, 321098765, 778899001, 1),
    (8, '1993-05-22', 'carla.almeida@email.com', 'Carla Fernanda Almeida', 'Almeida', 889900112, 210987654, 889900112, 1),
    (9, '1994-02-14', 'fernando.santos@email.com', 'Fernando Miguel Santos', 'Santos', 990011223, 109876543, 990011223, 1),
    (10, '1996-07-28', 'bruna.rodrigues@email.com', 'Bruna Gabriela Rodrigues', 'Rodrigues', 101112233, 998765432, 101112233, 1),
    (11, '1989-08-06', 'paulo.martins@email.com', 'Paulo Roberto Martins', 'Martins', 111223344, 887654321, 111223344, 1),
    (12, '1990-10-13', 'eliane.costa@email.com', 'Eliane Cristina Costa', 'Costa', 122334455, 776543210, 122334455, 1),
    (13, '1992-04-09', 'rafael.souza@email.com', 'Rafael Lucas Souza', 'Souza', 133445566, 665432109, 133445566, 1),
    (14, '1985-12-25', 'maria.pereira@email.com', 'Maria Luiza Pereira', 'Pereira', 144556677, 554321098, 144556677, 1),
    (15, '1994-11-19', 'gustavo.silva@email.com', 'Gustavo Henrique Silva', 'Silva', 155667788, 443210987, 155667788, 1),
    (16, '1991-06-12', 'luciana.lima@email.com', 'Luciana Maria Lima', 'Lima', 166778899, 332109876, 166778899, 1),
    (17, '1988-10-05', 'alexandre.almeida@email.com', 'Alexandre Eduardo Almeida', 'Almeida', 177889900, 221098765, 177889900, 1),
    (18, '1993-09-14', 'renata.rodrigues@email.com', 'Renata Helena Rodrigues', 'Rodrigues', 188990011, 110987654, 188990011, 1),
    (19, '1995-02-20', 'daniela.martins@email.com', 'Daniela Fernanda Martins', 'Martins', 199001122, 998765431, 199001122, 1),
    (20, '1986-07-18', 'fabio.souza@email.com', 'Fábio Augusto Souza', 'Souza', 200112233, 887654320, 200112233, 1),
    (21, '1991-03-09', 'priscila.oliveira@email.com', 'Priscila Gabriela Oliveira', 'Oliveira', 211223344, 776543209, 211223344, 1),
    (22, '1984-01-25', 'eduardo.santos@email.com', 'Eduardo João Santos', 'Santos', 222334455, 665432108, 222334455, 1),
    (23, '1992-12-15', 'isabela.lima@email.com', 'Isabela Mariana Lima', 'Lima', 233445566, 554321097, 233445566, 1),
    (24, '1987-06-30', 'marcio.rodrigues@email.com', 'Márcio Renato Rodrigues', 'Rodrigues', 244556677, 443210986, 244556677, 1),
    (25, '1994-08-17', 'camila.souza@email.com', 'Camila Priscila Souza', 'Souza', 255667788, 332109875, 255667788, 1),
    (26, '1989-10-23', 'jose.silva@email.com', 'José Antônio Silva', 'Silva', 266778899, 221098764, 266778899, 1),
    (27, '1993-11-12', 'marta.martins@email.com', 'Marta Isabel Martins', 'Martins', 277889900, 110987653, 277889900, 1),
    (28, '1990-09-14', 'lucas.souza@email.com', 'Lucas Felipe Souza', 'Souza', 288990011, 998765430, 288990011, 1),
    (29, '1991-02-04', 'roberto.silva@email.com', 'Roberto Carlos Silva', 'Silva', 299001122, 887654319, 299001122, 1),
    (30, '1992-06-25', 'taina.oliveira@email.com', 'Tainá Alice Oliveira', 'Oliveira', 300112233, 776543198, 300112233, 1),
    (31, '1985-09-02', 'carlos.rodrigues@email.com', 'Carlos Eduardo Rodrigues', 'Rodrigues', 311223344, 665432187, 311223344, 1),
    (32, '1994-03-17', 'alessandra.martins@email.com', 'Alessandra Beatriz Martins', 'Martins', 322334455, 554321086, 322334455, 1),
    (33, '1990-12-19', 'roberta.souza@email.com', 'Roberta Fernanda Souza', 'Souza', 333445566, 443210975, 333445566, 1),
    (34, '1986-05-18', 'alvaro.lima@email.com', 'Álvaro Henrique Lima', 'Lima', 344556677, 332109864, 344556677, 1),
    (35, '1995-08-01', 'andreia.martins@email.com', 'Andreia Cristina Martins', 'Martins', 355667788, 221098753, 355667788, 1),
    (36, '1992-03-12', 'claudio.santos@email.com', 'Cláudio César Santos', 'Santos', 366778899, 110987642, 366778899, 1),
    (37, '1994-12-30', 'aline.martins@email.com', 'Aline Rafaela Martins', 'Martins', 377889900, 998765421, 377889900, 1),
    (38, '1991-08-04', 'david.souza@email.com', 'David Felipe Souza', 'Souza', 388990011, 887654308, 388990011, 1),
    (39, '1988-11-17', 'veronica.lima@email.com', 'Verônica Carolina Lima', 'Lima', 399001122, 776543197, 399001122, 1),
    (40, '1993-01-06', 'fernanda.souza@email.com', 'Fernanda Regina Souza', 'Souza', 400112233, 665432176, 400112233, 1),
    (41, '1985-07-19', 'marcelo.rodrigues@email.com', 'Marcelo Vinícius Rodrigues', 'Rodrigues', 411223344, 554321065, 411223344, 1),
    (42, '1992-11-02', 'patricia.martins@email.com', 'Patrícia Simone Martins', 'Martins', 422334455, 443210964, 422334455, 1),
    (43, '1994-04-22', 'rafaela.santos@email.com', 'Rafaela Cristina Santos', 'Santos', 433445566, 332109853, 433445566, 1),
    (44, '1989-12-18', 'lucas.oliveira@email.com', 'Lucas Gabriel Oliveira', 'Oliveira', 444556677, 221098742, 444556677, 1),
    (45, '1990-01-29', 'carol.rodrigues@email.com', 'Carol Bianca Rodrigues', 'Rodrigues', 455667788, 110987631, 455667788, 1),
    (46, '1991-05-03', 'josefina.martins@email.com', 'Josefina Clara Martins', 'Martins', 466778899, 998765410, 466778899, 1),
    (47, '1986-06-22', 'joana.souza@email.com', 'Joana Beatriz Souza', 'Souza', 477889900, 887654299, 477889900, 1),
    (48, '1995-09-07', 'renato.oliveira@email.com', 'Renato Luiz Oliveira', 'Oliveira', 488990011, 776543188, 488990011, 1),
    (49, '1984-02-16', 'guilherme.santos@email.com', 'Guilherme Augusto Santos', 'Santos', 499001122, 665432177, 499001122, 1),
    (50, '1993-10-12', 'sandra.lima@email.com', 'Sandra Helena Lima', 'Lima', 500112233, 554321054, 500112233, 1);


INSERT INTO public.endereco (cidade, numero, postcode, rua, pessoa_id)
VALUES
    ('São Paulo', 123, '01000-000', 'Rua A', 1),
    ('São Paulo', 456, '02000-000', 'Rua B', 2),
    ('São Paulo', 789, '03000-000', 'Rua C', 3),
    ('Rio de Janeiro', 101, '20000-000', 'Avenida D', 4),
    ('Rio de Janeiro', 202, '21000-000', 'Avenida E', 5),
    ('Belo Horizonte', 303, '30000-000', 'Rua F', 6),
    ('Belo Horizonte', 404, '31000-000', 'Rua G', 7),
    ('Porto Alegre', 505, '40000-000', 'Rua H', 8),
    ('Porto Alegre', 606, '41000-000', 'Rua I', 9),
    ('Curitiba', 707, '50000-000', 'Rua J', 10),
    ('Curitiba', 808, '51000-000', 'Rua K', 11),
    ('Salvador', 909, '60000-000', 'Avenida L', 12),
    ('Salvador', 1001, '61000-000', 'Avenida M', 13),
    ('Fortaleza', 1112, '70000-000', 'Rua N', 14),
    ('Fortaleza', 1213, '71000-000', 'Rua O', 15),
    ('Recife', 1314, '80000-000', 'Rua P', 16),
    ('Recife', 1415, '81000-000', 'Rua Q', 17),
    ('Manaus', 1516, '90000-000', 'Avenida R', 18),
    ('Manaus', 1617, '91000-000', 'Avenida S', 19),
    ('Natal', 1718, '10000-000', 'Rua T', 20),
    ('Natal', 1819, '11000-000', 'Rua U', 21),
    ('Goiânia', 1920, '12000-000', 'Avenida V', 22),
    ('Goiânia', 2021, '13000-000', 'Avenida W', 23),
    ('Vitória', 2122, '14000-000', 'Rua X', 24),
    ('Vitória', 2223, '15000-000', 'Rua Y', 25),
    ('Maceió', 2324, '16000-000', 'Rua Z', 26),
    ('Maceió', 2425, '17000-000', 'Rua AA', 27),
    ('Campo Grande', 2526, '18000-000', 'Avenida BB', 28),
    ('Campo Grande', 2627, '19000-000', 'Avenida CC', 29),
    ('Aracaju', 2728, '20000-000', 'Rua DD', 30),
    ('Aracaju', 2829, '21000-000', 'Rua EE', 31),
    ('Florianópolis', 2930, '22000-000', 'Avenida FF', 32),
    ('Florianópolis', 3031, '23000-000', 'Avenida GG', 33),
    ('João Pessoa', 3132, '24000-000', 'Rua HH', 34),
    ('João Pessoa', 3233, '25000-000', 'Rua II', 35),
    ('São Luís', 3334, '26000-000', 'Rua JJ', 36),
    ('São Luís', 3435, '27000-000', 'Rua KK', 37),
    ('Belém', 3536, '28000-000', 'Avenida LL', 38),
    ('Belém', 3637, '29000-000', 'Avenida MM', 39),
    ('Rio Branco', 3738, '30000-000', 'Rua NN', 40),
    ('Rio Branco', 3839, '31000-000', 'Rua OO', 41),
    ('Macapá', 3940, '32000-000', 'Rua PP', 42),
    ('Macapá', 4041, '33000-000', 'Rua QQ', 43),
    ('Boa Vista', 4142, '34000-000', 'Avenida RR', 44),
    ('Boa Vista', 4243, '35000-000', 'Avenida SS', 45),
    ('Porto Velho', 4344, '36000-000', 'Rua TT', 46),
    ('Porto Velho', 4445, '37000-000', 'Rua UU', 47),
    ('Rio de Janeiro', 4546, '38000-000', 'Avenida VV', 48),
    ('Rio de Janeiro', 4647, '39000-000', 'Avenida WW', 49),
    ('São Paulo', 4748, '40000-000', 'Rua XX', 50);

INSERT INTO public.telefone (country, ddd, numero, tipo, pessoa_id)
VALUES
    ('Brasil', 11, 912345678, 1, 1),
    ('Brasil', 21, 987654321, 1, 2),
    ('Brasil', 31, 998877665, 1, 3),
    ('Brasil', 41, 919283746, 1, 4),
    ('Brasil', 51, 987654322, 1, 5),
    ('Brasil', 61, 998877666, 1, 6),
    ('Brasil', 71, 912345679, 1, 7),
    ('Brasil', 81, 987654323, 1, 8),
    ('Brasil', 91, 998877667, 1, 9),
    ('Brasil', 92, 912345680, 1, 10),
    ('Brasil', 93, 987654324, 1, 11),
    ('Brasil', 94, 998877668, 1, 12),
    ('Brasil', 95, 912345681, 1, 13),
    ('Brasil', 96, 987654325, 1, 14),
    ('Brasil', 97, 998877669, 1, 15),
    ('Brasil', 98, 912345682, 1, 16),
    ('Brasil', 99, 987654326, 1, 17),
    ('Brasil', 22, 998877670, 1, 18),
    ('Brasil', 24, 912345683, 1, 19),
    ('Brasil', 25, 987654327, 1, 20),
    ('Brasil', 26, 998877671, 1, 21),
    ('Brasil', 27, 912345684, 1, 22),
    ('Brasil', 28, 987654328, 1, 23),
    ('Brasil', 29, 998877672, 1, 24),
    ('Brasil', 30, 912345685, 1, 25),
    ('Brasil', 32, 987654329, 1, 26),
    ('Brasil', 33, 998877673, 1, 27),
    ('Brasil', 34, 912345686, 1, 28),
    ('Brasil', 35, 987654330, 1, 29),
    ('Brasil', 36, 998877674, 1, 30),
    ('Brasil', 37, 912345687, 1, 31),
    ('Brasil', 38, 987654331, 1, 32),
    ('Brasil', 39, 998877675, 1, 33),
    ('Brasil', 40, 912345688, 1, 34),
    ('Brasil', 42, 987654332, 1, 35),
    ('Brasil', 43, 998877676, 1, 36),
    ('Brasil', 44, 912345689, 1, 37),
    ('Brasil', 45, 987654333, 1, 38),
    ('Brasil', 46, 998877677, 1, 39),
    ('Brasil', 47, 912345690, 1, 40),
    ('Brasil', 48, 987654334, 1, 41),
    ('Brasil', 49, 998877678, 1, 42),
    ('Brasil', 50, 912345691, 1, 43),
    ('Brasil', 53, 987654335, 1, 44),
    ('Brasil', 54, 998877679, 1, 45),
    ('Brasil', 55, 912345692, 1, 46),
    ('Brasil', 56, 987654336, 1, 47),
    ('Brasil', 57, 998877680, 1, 48),
    ('Brasil', 58, 912345693, 1, 49),
    ('Brasil', 59, 987654337, 1, 50);


-- CRINDO ORDEM DE SERVICO --

 INSERT INTO public.ordem_servico (
    id, data_inicio, invoice_number, observacao, quantidade_parcelas, tipo_pagamento,
    valor_total, vat, cliente_id, oficina_id
) VALUES
-- Ordem de Serviço 1
(1, '2024-12-01 09:00:00', 1001, 'Serviço realizado conforme solicitado.', 3, 1, 1500.00, 12, 1, 1),
-- Ordem de Serviço 2
(2, '2024-12-02 10:15:00', 1002, 'Ajuste de manutenção em equipamentos.', 2, 2, 800.00, 12, 2, 1),
-- Ordem de Serviço 3
(3, '2024-12-03 11:30:00', 1003, 'Reparo completo de motor.', 4, 1, 2200.00, 12, 3, 1),
-- Ordem de Serviço 4
(4, '2024-12-04 14:00:00', 1004, 'Troca de óleo e filtros.', 1, 2, 450.00, 12, 4, 1),
-- Ordem de Serviço 5
(5, '2024-12-05 16:45:00', 1005, 'Revisão de suspensão e alinhamento.', 3, 1, 1200.00, 12, 5, 1),
-- Ordem de Serviço 6
(6, '2024-12-06 09:30:00', 1006, 'Inspeção de sistema de freios.', 2, 1, 600.00, 12, 6, 1),
-- Ordem de Serviço 7
(7, '2024-12-07 08:00:00', 1007, 'Troca de pneus e balanceamento.', 2, 2, 800.00, 12, 7, 1),
-- Ordem de Serviço 8
(8, '2024-12-08 10:15:00', 1008, 'Ajuste de sistema de injeção eletrônica.', 1, 1, 500.00, 12, 8, 1),
-- Ordem de Serviço 9
(9, '2024-12-09 11:30:00', 1009, 'Troca de velas e cabos.', 1, 2, 350.00, 12, 9, 1),
-- Ordem de Serviço 10
(10, '2024-12-10 13:00:00', 1010, 'Reparo de sistema de ar condicionado.', 2, 1, 900.00, 12, 10, 1),
-- Ordem de Serviço 11
(11, '2024-12-11 14:45:00', 1011, 'Troca de bateria e revisão geral.', 1, 2, 500.00, 12, 11, 1),
-- Ordem de Serviço 12
(12, '2024-12-12 15:00:00', 1012, 'Substituição de pastilhas de freio.', 1, 1, 300.00, 12, 12, 1),
-- Ordem de Serviço 13
(13, '2024-12-13 16:00:00', 1013, 'Reparo em sistema elétrico.', 3, 1, 1300.00, 12, 13, 1),
-- Ordem de Serviço 14
(14, '2024-12-14 17:30:00', 1014, 'Manutenção de sistema de direção.', 2, 2, 700.00, 12, 14, 1),
-- Ordem de Serviço 15
(15, '2024-12-15 08:30:00', 1015, 'Troca de filtro de ar e inspeção geral.', 2, 1, 600.00, 12, 15, 1),
-- Ordem de Serviço 16
(16, '2024-12-16 10:15:00', 1016, 'Reparo em sistema de ignição.', 3, 2, 1000.00, 12, 16, 1),
-- Ordem de Serviço 17
(17, '2024-12-17 11:00:00', 1017, 'Substituição de embreagem.', 4, 1, 1500.00, 12, 17, 1),
-- Ordem de Serviço 18
(18, '2024-12-18 14:00:00', 1018, 'Ajuste em sistema de escape.', 1, 2, 400.00, 12, 18, 1),
-- Ordem de Serviço 19
(19, '2024-12-19 15:15:00', 1019, 'Revisão de sistema de suspensão.', 3, 1, 1100.00, 12, 19, 1),
-- Ordem de Serviço 20
(20, '2024-12-20 16:00:00', 1020, 'Troca de correia dentada e óleo.', 2, 2, 700.00, 12, 20, 1),
-- Ordem de Serviço 21
(21, '2024-12-21 09:00:00', 1021, 'Reparo em sistema de transmissão.', 3, 1, 1500.00, 12, 21, 1),
-- Ordem de Serviço 22
(22, '2024-12-22 10:45:00', 1022, 'Verificação e troca de bomba d água.', 2, 1, 800.00, 12, 22, 1),
-- Ordem de Serviço 23
(23, '2024-12-23 11:30:00', 1023, 'Manutenção em sistema de direção hidráulica.', 1, 2, 600.00, 12, 23, 1),
-- Ordem de Serviço 24
(24, '2024-12-24 13:00:00', 1024, 'Troca de óleo e filtros de ar.', 1, 1, 350.00, 12, 24, 1),
-- Ordem de Serviço 25
(25, '2024-12-25 14:15:00', 1025, 'Reparo de suspensão e alinhamento de direção.', 2, 1, 750.00, 12, 25, 1),
-- Ordem de Serviço 26
(26, '2024-12-26 15:00:00', 1026, 'Substituição de motor de arranque.', 3, 2, 950.00, 12, 26, 1),
-- Ordem de Serviço 27
(27, '2024-12-27 16:00:00', 1027, 'Manutenção de câmbio e embreagem.', 2, 1, 1200.00, 12, 27, 1),
-- Ordem de Serviço 28
(28, '2024-12-28 09:30:00', 1028, 'Reparo no sistema de direção e troca de fluido.', 1, 2, 500.00, 12, 28, 1),
-- Ordem de Serviço 29
(29, '2024-12-29 11:00:00', 1029, 'Verificação de sistema de ar condicionado e troca de gás.', 3, 1, 800.00, 12, 29, 1),
-- Ordem de Serviço 30
(30, '2024-12-30 14:30:00', 1030, 'Troca de bomba de combustível.', 1, 1, 550.00, 12, 30, 1),
-- Ordem de Serviço 31
(31, '2025-01-01 10:00:00', 1031, 'Reparo de sistema de ignição.', 2, 2, 600.00, 12, 31, 1),
-- Ordem de Serviço 32
(32, '2025-01-02 11:15:00', 1032, 'Troca de filtros de combustível e ar.', 3, 1, 750.00, 12, 32, 1),
-- Ordem de Serviço 33
(33, '2025-01-03 13:30:00', 1033, 'Revisão de sistema de suspensão e direção.', 2, 1, 950.00, 12, 33, 1),
-- Ordem de Serviço 34
(34, '2025-01-04 14:00:00', 1034, 'Troca de pastilhas de freio e revisão geral.', 1, 1, 600.00, 12, 34, 1),
-- Ordem de Serviço 35
(35, '2025-01-05 15:30:00', 1035, 'Reparo no sistema elétrico do veículo.', 3, 2, 1100.00, 12, 35, 1),
-- Ordem de Serviço 36
(36, '2025-01-06 16:15:00', 1036, 'Inspeção e troca de velas de ignição.', 1, 1, 350.00, 12, 36, 1),
-- Ordem de Serviço 37
(37, '2025-01-07 09:00:00', 1037, 'Reparo no sistema de exaustão.', 2, 1, 650.00, 12, 37, 1),
-- Ordem de Serviço 38
(38, '2025-01-08 11:00:00', 1038, 'Ajuste no sistema de injeção eletrônica.', 2, 2, 800.00, 12, 38, 1),
-- Ordem de Serviço 39
(39, '2025-01-09 13:00:00', 1039, 'Revisão e troca de fluido de freio.', 3, 1, 700.00, 12, 39, 1),
-- Ordem de Serviço 40
(40, '2025-01-10 14:00:00', 1040, 'Verificação e troca de correia dentada.', 1, 2, 500.00, 12, 40, 1),
-- Ordem de Serviço 41
(41, '2025-01-11 15:00:00', 1041, 'Manutenção do sistema de ar condicionado.', 2, 1, 800.00, 12, 41, 1),
-- Ordem de Serviço 42
(42, '2025-01-12 16:15:00', 1042, 'Reparo em sistema de freios ABS.', 3, 2, 1200.00, 12, 42, 1),
-- Ordem de Serviço 43
(43, '2025-01-13 09:00:00', 1043, 'Troca de bateria e sistema elétrico.', 2, 1, 600.00, 12, 43, 1),
-- Ordem de Serviço 44
(44, '2025-01-14 11:30:00', 1044, 'Inspeção de suspensão e alinhamento de direção.', 1, 2, 450.00, 12, 44, 1),
-- Ordem de Serviço 45
(45, '2025-01-15 13:00:00', 1045, 'Reparo em caixa de direção hidráulica.', 3, 1, 950.00, 12, 45, 1),
-- Ordem de Serviço 46
(46, '2025-01-16 14:30:00', 1046, 'Troca de óleo de motor e revisão.', 2, 1, 550.00, 12, 46, 1),
-- Ordem de Serviço 47
(47, '2025-01-17 16:00:00', 1047, 'Reparo em embreagem e troca de fluido.', 2, 1, 850.00, 12, 47, 1),
-- Ordem de Serviço 48
(48, '2025-01-18 09:30:00', 1048, 'Manutenção em sistema de ignição.', 1, 1, 400.00, 12, 48, 1),
-- Ordem de Serviço 49
(49, '2025-01-19 10:00:00', 1049, 'Reparo em bomba de combustível.', 3, 1, 700.00, 12, 49, 1),
-- Ordem de Serviço 50
(50, '2025-01-20 11:00:00', 1050, 'Substituição de pastilhas de freio e óleo de motor.', 2, 2, 650.00, 12, 50, 1),
-- Ordem de Serviço 51
(51, '2025-01-21 13:00:00', 1051, 'Troca de correias e velas.', 3, 2, 1000.00, 12, 51, 1),
-- Ordem de Serviço 52
(52, '2025-01-22 14:30:00', 1052, 'Manutenção do sistema de câmbio.', 1, 1, 800.00, 12, 52, 1),
-- Ordem de Serviço 53
(53, '2025-01-23 15:15:00', 1053, 'Reparo de sistema de suspensão e direção.', 2, 1, 750.00, 12, 53, 1),
-- Ordem de Serviço 54
(54, '2025-01-24 16:00:00', 1054, 'Verificação e troca de pastilhas de freio.', 1, 2, 600.00, 12, 54, 1),
-- Ordem de Serviço 55
(55, '2025-01-25 09:30:00', 1055, 'Troca de óleo e filtros de combustível.', 3, 1, 700.00, 12, 55, 1),
-- Ordem de Serviço 56
(56, '2025-01-26 10:15:00', 1056, 'Reparo em sistema de direção hidráulica.', 2, 2, 850.00, 12, 56, 1),
-- Ordem de Serviço 57
(57, '2025-01-27 11:00:00', 1057, 'Inspeção e troca de amortecedores.', 3, 1, 1200.00, 12, 57, 1),
-- Ordem de Serviço 58
(58, '2025-01-28 12:30:00', 1058, 'Manutenção de sistema de injeção eletrônica.', 1, 2, 500.00, 12, 58, 1),
-- Ordem de Serviço 59
(59, '2025-01-29 14:00:00', 1059, 'Reparo em sistema de ignição e troca de cabos.', 3, 1, 950.00, 12, 59, 1),
-- Ordem de Serviço 60
(60, '2025-01-30 15:30:00', 1060, 'Reparo de sistema de ar condicionado.', 2, 1, 800.00, 12, 60, 1),
-- Ordem de Serviço 61
(61, '2025-02-01 09:00:00', 1061, 'Troca de velas e troca de óleo de motor.', 1, 2, 450.00, 12, 61, 1),
-- Ordem de Serviço 62
(62, '2025-02-02 10:30:00', 1062, 'Revisão de sistema de suspensão e direção.', 3, 1, 750.00, 12, 62, 1),
-- Ordem de Serviço 63
(63, '2025-02-03 11:00:00', 1063, 'Manutenção de sistema de transmissão.', 2, 1, 1200.00, 12, 63, 1),
-- Ordem de Serviço 64
(64, '2025-02-04 13:00:00', 1064, 'Reparo em bomba d água e sistema de refrigeração.', 3, 2, 1100.00, 12, 64, 1),
-- Ordem de Serviço 65
(65, '2025-02-05 14:15:00', 1065, 'Troca de filtro de ar e correias.', 2, 1, 700.00, 12, 65, 1),
-- Ordem de Serviço 66
(66, '2025-02-06 15:00:00', 1066, 'Substituição de pastilhas de freio e óleo de motor.', 1, 2, 600.00, 12, 66, 1),
-- Ordem de Serviço 67
(67, '2025-02-07 16:00:00', 1067, 'Reparo em câmbio e trocas de fluido de freio.', 3, 1, 950.00, 12, 67, 1),
-- Ordem de Serviço 68
(68, '2025-02-08 09:30:00', 1068, 'Inspeção e troca de válvula termostática.', 2, 1, 600.00, 12, 68, 1),
-- Ordem de Serviço 69
(69, '2025-02-09 11:15:00', 1069, 'Manutenção de embreagem e revisão de motor.', 3, 2, 1500.00, 12, 69, 1),
-- Ordem de Serviço 70
(70, '2025-02-10 12:00:00', 1070, 'Troca de fluido de freio e pastilhas.', 2, 1, 500.00, 12, 70, 1),
-- Ordem de Serviço 71
(71, '2025-02-11 13:30:00', 1071, 'Substituição de velas e correia dentada.', 2, 2, 850.00, 12, 71, 1),
-- Ordem de Serviço 72
(72, '2025-02-12 14:00:00', 1072, 'Ajuste de suspensão e alinhamento.', 1, 1, 600.00, 12, 72, 1),
-- Ordem de Serviço 73
(73, '2025-02-13 16:30:00', 1073, 'Reparo em sistema elétrico e troca de bateria.', 2, 2, 750.00, 12, 73, 1),
-- Ordem de Serviço 74
(74, '2025-02-14 09:00:00', 1074, 'Manutenção de câmbio e troca de óleo.', 3, 1, 1200.00, 12, 74, 1),
-- Ordem de Serviço 75
(75, '2025-02-15 11:15:00', 1075, 'Verificação de motor e sistema de exaustão.', 2, 2, 850.00, 12, 75, 1),
-- Ordem de Serviço 76
(76, '2025-02-16 12:45:00', 1076, 'Reparo em direção e troca de fluido.', 3, 1, 900.00, 12, 76, 1),
-- Ordem de Serviço 77
(77, '2025-02-17 14:00:00', 1077, 'Manutenção no sistema de ar condicionado e trocas de gás.', 2, 1, 700.00, 12, 77, 1),
-- Ordem de Serviço 78
(78, '2025-02-18 15:00:00', 1078, 'Substituição de filtro de combustível e revisão.', 1, 2, 650.00, 12, 78, 1),
-- Ordem de Serviço 79
(79, '2025-02-19 09:30:00', 1079, 'Reparo em bomba de combustível.', 2, 1, 750.00, 12, 79, 1),
-- Ordem de Serviço 80
(80, '2025-02-20 10:30:00', 1080, 'Troca de óleo de motor e revisão completa.', 3, 2, 800.00, 12, 80, 1);


--- DETALHES DE SERVIÇOS ---

-- Detalhes de Serviço para Ordem de Serviço 1
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(1, '2024-12-01 09:00:00', 'Reparo completo do motor.', 50000, 'ABC1234', 1, 1500.00, 1);

-- Detalhes de Serviço para Ordem de Serviço 2
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(2, '2024-12-02 10:15:00', 'Manutenção em equipamentos de suspensão.', 60000, 'XYZ5678', 1, 800.00, 2);

-- Detalhes de Serviço para Ordem de Serviço 3
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(3, '2024-12-03 11:30:00', 'Reparo em motor, substituição de pistões.', 70000, 'DEF3456', 1, 2200.00, 3);

-- Detalhes de Serviço para Ordem de Serviço 4
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(4, '2024-12-04 14:00:00', 'Troca de óleo e filtros.', 80000, 'GHI8901', 1, 450.00, 4);

-- Detalhes de Serviço para Ordem de Serviço 5 (2 detalhes)
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(5, '2024-12-05 16:45:00', 'Revisão da suspensão.', 90000, 'JKL2345', 1, 600.00, 5),
(6, '2024-12-05 16:45:00', 'Alinhamento de direção.', 90000, 'JKL2345', 1, 600.00, 5);

-- Detalhes de Serviço para Ordem de Serviço 6
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(7, '2024-12-06 09:30:00', 'Inspeção e troca de freios.', 100000, 'MNO6789', 1, 600.00, 6);

-- Detalhes de Serviço para Ordem de Serviço 7 (2 detalhes)
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(8, '2024-12-07 08:00:00', 'Troca de pneus.', 110000, 'PQR1234', 1, 400.00, 7),
(9, '2024-12-07 08:00:00', 'Balanceamento de rodas.', 110000, 'PQR1234', 1, 400.00, 7);

-- Detalhes de Serviço para Ordem de Serviço 8
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(10, '2024-12-08 10:15:00', 'Ajuste de injeção eletrônica.', 120000, 'STU5678', 1, 500.00, 8);

-- Detalhes de Serviço para Ordem de Serviço 9
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(11, '2024-12-09 11:30:00', 'Troca de velas e cabos.', 130000, 'VWX6789', 1, 350.00, 9);

-- Detalhes de Serviço para Ordem de Serviço 10 (2 detalhes)
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(12, '2024-12-10 13:00:00', 'Reparo no ar condicionado.', 140000, 'YZA1234', 1, 450.00, 10),
(13, '2024-12-10 13:00:00', 'Troca de filtro de ar.', 140000, 'YZA1234', 1, 450.00, 10);

-- Detalhes de Serviço para Ordem de Serviço 11
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(14, '2024-12-11 14:45:00', 'Troca de bateria.', 150000, 'BCD9876', 1, 500.00, 11);

-- Detalhes de Serviço para Ordem de Serviço 12
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(15, '2024-12-12 15:00:00', 'Troca de pastilhas de freio.', 160000, 'EFG2345', 1, 300.00, 12);

-- Detalhes de Serviço para Ordem de Serviço 13 (2 detalhes)
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(16, '2024-12-13 16:00:00', 'Reparo de sistema elétrico.', 170000, 'HIJ4567', 1, 700.00, 13),
(17, '2024-12-13 16:00:00', 'Substituição de fusíveis e relés.', 170000, 'HIJ4567', 1, 600.00, 13);

-- Detalhes de Serviço para Ordem de Serviço 14
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(18, '2024-12-14 17:30:00', 'Ajuste na direção hidráulica.', 180000, 'KLM6789', 1, 700.00, 14);

-- Detalhes de Serviço para Ordem de Serviço 15 (2 detalhes)
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(19, '2024-12-15 08:30:00', 'Troca de filtro de ar.', 190000, 'NOP9876', 1, 300.00, 15),
(20, '2024-12-15 08:30:00', 'Inspeção de suspensão e alinhamento.', 190000, 'NOP9876', 1, 300.00, 15);

-- Detalhes de Serviço para Ordem de Serviço 16
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(21, '2024-12-16 10:15:00', 'Reparo no sistema de ignição.', 200000, 'QRS2345', 1, 1000.00, 16);

-- Detalhes de Serviço para Ordem de Serviço 17 (2 detalhes)
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(22, '2024-12-17 11:00:00', 'Substituição de embreagem.', 210000, 'TUV5678', 1, 800.00, 17),
(23, '2024-12-17 11:00:00', 'Troca de fluido de transmissão.', 210000, 'TUV5678', 1, 700.00, 17);

-- Detalhes de Serviço para Ordem de Serviço 18
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(24, '2024-12-18 14:00:00', 'Ajuste no sistema de escape.', 220000, 'WXY1234', 1, 600.00, 18);

-- Detalhes de Serviço para Ordem de Serviço 19
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(25, '2024-12-19 15:30:00', 'Verificação de sistema elétrico.', 230000, 'ZAB2345', 1, 500.00, 19);

-- Detalhes de Serviço para Ordem de Serviço 20
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(26, '2024-12-20 16:00:00', 'Substituição de pastilhas de freio.', 240000, 'CDE6789', 1, 400.00, 20);

-- Detalhes de Serviço para Ordem de Serviço 21
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(27, '2024-12-21 10:30:00', 'Reparo em sistema de injeção eletrônica.', 250000, 'EFG5678', 1, 1500.00, 21);

-- Detalhes de Serviço para Ordem de Serviço 22
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(28, '2024-12-22 11:00:00', 'Revisão geral e troca de óleo.', 260000, 'HIJ2345', 1, 450.00, 22);

-- Detalhes de Serviço para Ordem de Serviço 23 (2 detalhes)
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(29, '2024-12-23 13:15:00', 'Troca de fluido de freio.', 270000, 'KLM7890', 1, 300.00, 23),
(30, '2024-12-23 13:15:00', 'Substituição de pastilhas de freio.', 270000, 'KLM7890', 1, 300.00, 23);

-- Detalhes de Serviço para Ordem de Serviço 24
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(31, '2024-12-24 14:00:00', 'Troca de óleo e filtro de ar.', 280000, 'NOP1234', 1, 350.00, 24);

-- Detalhes de Serviço para Ordem de Serviço 25 (2 detalhes)
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(32, '2024-12-25 15:30:00', 'Ajuste na suspensão.', 290000, 'QRS2345', 1, 400.00, 25),
(33, '2024-12-25 15:30:00', 'Alinhamento e balanceamento de rodas.', 290000, 'QRS2345', 1, 400.00, 25);

-- Detalhes de Serviço para Ordem de Serviço 26
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(34, '2024-12-26 09:00:00', 'Verificação do sistema de direção.', 300000, 'STU6789', 1, 700.00, 26);

-- Detalhes de Serviço para Ordem de Serviço 27
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(35, '2024-12-27 10:30:00', 'Substituição do sistema de exaustão.', 310000, 'VWX1234', 1, 900.00, 27);

-- Detalhes de Serviço para Ordem de Serviço 28 (2 detalhes)
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(36, '2024-12-28 11:00:00', 'Reparo na suspensão dianteira.', 320000, 'YZA2345', 1, 450.00, 28),
(37, '2024-12-28 11:00:00', 'Reparo no sistema elétrico.', 320000, 'YZA2345', 1, 450.00, 28);

-- Detalhes de Serviço para Ordem de Serviço 29
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(38, '2024-12-29 12:30:00', 'Revisão do sistema de transmissão.', 330000, 'BCD5678', 1, 1200.00, 29);

-- Detalhes de Serviço para Ordem de Serviço 30
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(39, '2024-12-30 13:00:00', 'Reparo no sistema de ar condicionado.', 340000, 'EFG1234', 1, 600.00, 30);

-- Detalhes de Serviço para Ordem de Serviço 31 (2 detalhes)
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(40, '2025-01-02 14:30:00', 'Reparo na parte elétrica.', 350000, 'HIJ7890', 1, 500.00, 31),
(41, '2025-01-02 14:30:00', 'Troca de bateria e alternador.', 350000, 'HIJ7890', 1, 500.00, 31);

-- Detalhes de Serviço para Ordem de Serviço 32
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(42, '2025-01-03 09:00:00', 'Troca de óleo e revisão de suspensão.', 360000, 'JKL2345', 1, 700.00, 32);

-- Detalhes de Serviço para Ordem de Serviço 33 (2 detalhes)
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(43, '2025-01-04 10:00:00', 'Troca de velas e cabos.', 370000, 'MNO1234', 1, 350.00, 33),
(44, '2025-01-04 10:00:00', 'Substituição de filtro de combustível.', 370000, 'MNO1234', 1, 350.00, 33);

-- Detalhes de Serviço para Ordem de Serviço 34
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(45, '2025-01-05 11:15:00', 'Reparo no sistema de direção hidráulica.', 380000, 'PQR6789', 1, 850.00, 34);

-- Detalhes de Serviço para Ordem de Serviço 35 (2 detalhes)
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(46, '2025-01-06 12:00:00', 'Ajuste na embreagem.', 390000, 'STU2345', 1, 600.00, 35),
(47, '2025-01-06 12:00:00', 'Troca de fluido de transmissão.', 390000, 'STU2345', 1, 600.00, 35);

-- Detalhes de Serviço para Ordem de Serviço 36
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(48, '2025-01-07 13:00:00', 'Revisão de sistema de exaustão.', 400000, 'VWX5678', 1, 1200.00, 36);

-- Detalhes de Serviço para Ordem de Serviço 37
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(49, '2025-01-08 14:00:00', 'Ajuste do sistema de combustível.', 410000, 'YZA1234', 1, 550.00, 37);

-- Detalhes de Serviço para Ordem de Serviço 38 (2 detalhes)
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(50, '2025-01-09 15:00:00', 'Troca de óleo de motor.', 420000, 'BCD1234', 1, 600.00, 38),
(51, '2025-01-09 15:00:00', 'Reparo no sistema de ar condicionado.', 420000, 'BCD1234', 1, 600.00, 38);

-- Detalhes de Serviço para Ordem de Serviço 39
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(52, '2025-01-10 16:30:00', 'Revisão geral de motor.', 430000, 'EFG2345', 1, 1800.00, 39);

-- Detalhes de Serviço para Ordem de Serviço 40 (2 detalhes)
INSERT INTO public.detalhe_servico (id, data, descricao, milhagem, placa, quantidade, valor, ordem_servico_id)
VALUES
(53, '2025-01-11 08:00:00', 'Substituição de filtros de ar.', 440000, 'HIJ3456', 1, 350.00, 40),
(54, '2025-01-11 08:00:00', 'Troca de pastilhas de freio.', 440000, 'HIJ3456', 1, 350.00, 40);


-- PAGAMENTOS --

-- Pagamento para Ordem de Serviço 1
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(1, '2024-12-01 09:30:00', 1500.00, 1);

-- Pagamento para Ordem de Serviço 2
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(2, '2024-12-02 10:45:00', 800.00, 2);

-- Pagamento para Ordem de Serviço 3
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(3, '2024-12-03 11:45:00', 2200.00, 3);

-- Pagamento para Ordem de Serviço 4
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(4, '2024-12-04 14:30:00', 450.00, 4);

-- Pagamento para Ordem de Serviço 5
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(5, '2024-12-05 17:00:00', 600.00, 5);

-- Pagamento para Ordem de Serviço 6
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(6, '2024-12-06 10:00:00', 600.00, 6);

-- Pagamento para Ordem de Serviço 7
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(7, '2024-12-07 09:30:00', 800.00, 7);

-- Pagamento para Ordem de Serviço 8
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(8, '2024-12-08 11:00:00', 500.00, 8);

-- Pagamento para Ordem de Serviço 9
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(9, '2024-12-09 12:00:00', 350.00, 9);

-- Pagamento para Ordem de Serviço 10
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(10, '2024-12-10 13:15:00', 900.00, 10);

-- Pagamento para Ordem de Serviço 11
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(11, '2024-12-11 15:00:00', 500.00, 11);

-- Pagamento para Ordem de Serviço 12
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(12, '2024-12-12 16:00:00', 300.00, 12);

-- Pagamento para Ordem de Serviço 13
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(13, '2024-12-13 17:00:00', 1300.00, 13);

-- Pagamento para Ordem de Serviço 14
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(14, '2024-12-14 09:30:00', 700.00, 14);

-- Pagamento para Ordem de Serviço 15
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(15, '2024-12-15 10:00:00', 600.00, 15);

-- Pagamento para Ordem de Serviço 16
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(16, '2024-12-16 12:00:00', 700.00, 16);

-- Pagamento para Ordem de Serviço 17
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(17, '2024-12-17 13:15:00', 1500.00, 17);

-- Pagamento para Ordem de Serviço 18
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(18, '2024-12-18 14:30:00', 600.00, 18);

-- Pagamento para Ordem de Serviço 19
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(19, '2024-12-19 15:45:00', 500.00, 19);

-- Pagamento para Ordem de Serviço 20
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(20, '2024-12-20 16:30:00', 400.00, 20);

-- Pagamento para Ordem de Serviço 21
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(21, '2024-12-21 10:00:00', 1500.00, 21);

-- Pagamento para Ordem de Serviço 22
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(22, '2024-12-22 11:30:00', 450.00, 22);

-- Pagamento para Ordem de Serviço 23
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(23, '2024-12-23 13:00:00', 600.00, 23);

-- Pagamento para Ordem de Serviço 24
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(24, '2024-12-24 14:00:00', 350.00, 24);

-- Pagamento para Ordem de Serviço 25
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(25, '2024-12-25 15:15:00', 800.00, 25);

-- Pagamento para Ordem de Serviço 26
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(26, '2024-12-26 16:30:00', 700.00, 26);

-- Pagamento para Ordem de Serviço 27
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(27, '2024-12-27 10:00:00', 900.00, 27);

-- Pagamento para Ordem de Serviço 28
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(28, '2024-12-28 12:00:00', 600.00, 28);

-- Pagamento para Ordem de Serviço 29
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(29, '2024-12-29 13:30:00', 1200.00, 29);

-- Pagamento para Ordem de Serviço 30
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(30, '2024-12-30 15:00:00', 600.00, 30);

-- Pagamento para Ordem de Serviço 31
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(31, '2025-01-02 16:00:00', 1000.00, 31);

-- Pagamento para Ordem de Serviço 32
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(32, '2025-01-03 10:30:00', 700.00, 32);

-- Pagamento para Ordem de Serviço 33
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(33, '2025-01-04 12:00:00', 700.00, 33);

-- Pagamento para Ordem de Serviço 34
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(34, '2025-01-05 13:00:00', 850.00, 34);

-- Pagamento para Ordem de Serviço 35
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(35, '2025-01-06 14:30:00', 1000.00, 35);

-- Pagamento para Ordem de Serviço 36
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(36, '2025-01-07 15:30:00', 1200.00, 36);

-- Pagamento para Ordem de Serviço 37
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(37, '2025-01-08 16:30:00', 500.00, 37);

-- Pagamento para Ordem de Serviço 38
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(38, '2025-01-09 17:30:00', 600.00, 38);

-- Pagamento para Ordem de Serviço 39
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(39, '2025-01-10 18:00:00', 800.00, 39);

-- Pagamento para Ordem de Serviço 40
INSERT INTO public.pagamento (id, data_pagamento, valor_pago, ordem_servico_id)
VALUES
(40, '2025-01-11 09:00:00', 700.00, 40);


-- STATUS ORDEM SERVICO --

-- Status da Ordem de Serviço 1
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(1, '2024-12-01', 0.00, 1, '2024-12-01 09:30:00', 0.00, 1);  -- PAGO

-- Status da Ordem de Serviço 2
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(2, '2024-12-02', 0.00, 1, '2024-12-02 10:45:00', 0.00, 2);  -- PAGO

-- Status da Ordem de Serviço 3
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(3, '2024-12-03', 0.00, 1, '2024-12-03 11:45:00', 0.00, 3);  -- PAGO

-- Status da Ordem de Serviço 4
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(4, '2024-12-04', 450.00, 2, '2024-12-04 14:30:00', 450.00, 4);  -- AGENDADO

-- Status da Ordem de Serviço 5
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(5, '2024-12-05', 600.00, 2, '2024-12-05 17:00:00', 600.00, 5);  -- AGENDADO

-- Status da Ordem de Serviço 6
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(6, '2024-12-06', 600.00, 2, '2024-12-06 10:00:00', 600.00, 6);  -- AGENDADO

-- Status da Ordem de Serviço 7
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(7, '2024-12-07', 800.00, 2, '2024-12-07 09:30:00', 800.00, 7);  -- AGENDADO

-- Status da Ordem de Serviço 8
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(8, '2024-12-08', 500.00, 2, '2024-12-08 11:00:00', 500.00, 8);  -- AGENDADO

-- Status da Ordem de Serviço 9
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(9, '2024-12-09', 350.00, 2, '2024-12-09 12:00:00', 350.00, 9);  -- AGENDADO

-- Status da Ordem de Serviço 10
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(10, '2024-12-10', 0.00, 1, '2024-12-10 13:15:00', 0.00, 10);  -- PAGO

-- Status da Ordem de Serviço 11
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(11, '2024-12-11', 0.00, 1, '2024-12-11 15:00:00', 0.00, 11);  -- PAGO

-- Status da Ordem de Serviço 12
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(12, '2024-12-12', 300.00, 2, '2024-12-12 16:00:00', 300.00, 12);  -- AGENDADO

-- Status da Ordem de Serviço 13
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(13, '2024-12-13', 0.00, 1, '2024-12-13 17:00:00', 0.00, 13);  -- PAGO

-- Status da Ordem de Serviço 14
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(14, '2024-12-14', 700.00, 2, '2024-12-14 09:30:00', 700.00, 14);  -- AGENDADO

-- Status da Ordem de Serviço 15
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(15, '2024-12-15', 600.00, 2, '2024-12-15 10:00:00', 600.00, 15);  -- AGENDADO

-- Status da Ordem de Serviço 16
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(16, '2024-12-16', 700.00, 2, '2024-12-16 12:00:00', 700.00, 16);  -- AGENDADO

-- Status da Ordem de Serviço 17
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(17, '2024-12-17', 0.00, 1, '2024-12-17 13:15:00', 0.00, 17);  -- PAGO

-- Status da Ordem de Serviço 18
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(18, '2024-12-18', 600.00, 2, '2024-12-18 14:30:00', 600.00, 18);  -- AGENDADO

-- Status da Ordem de Serviço 19
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(19, '2024-12-19', 500.00, 2, '2024-12-19 15:45:00', 500.00, 19);  -- AGENDADO

-- Status da Ordem de Serviço 20
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(20, '2024-12-20', 400.00, 2, '2024-12-20 16:30:00', 400.00, 20);  -- AGENDADO


-- Status da Ordem de Serviço 21
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(21, '2024-12-21', 0.00, 1, '2024-12-21 10:00:00', 0.00, 21);  -- PAGO

-- Status da Ordem de Serviço 22
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(22, '2024-12-22', 450.00, 2, '2024-12-22 11:30:00', 450.00, 22);  -- AGENDADO

-- Status da Ordem de Serviço 23
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(23, '2024-12-23', 600.00, 2, '2024-12-23 13:00:00', 600.00, 23);  -- AGENDADO

-- Status da Ordem de Serviço 24
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(24, '2024-12-24', 350.00, 2, '2024-12-24 14:00:00', 350.00, 24);  -- AGENDADO

-- Status da Ordem de Serviço 25
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(25, '2024-12-25', 0.00, 1, '2024-12-25 15:15:00', 0.00, 25);  -- PAGO

-- Status da Ordem de Serviço 26
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(26, '2024-12-26', 700.00, 2, '2024-12-26 16:30:00', 700.00, 26);  -- AGENDADO

-- Status da Ordem de Serviço 27
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(27, '2024-12-27', 900.00, 2, '2024-12-27 10:00:00', 900.00, 27);  -- AGENDADO

-- Status da Ordem de Serviço 28
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(28, '2024-12-28', 600.00, 2, '2024-12-28 12:00:00', 600.00, 28);  -- AGENDADO

-- Status da Ordem de Serviço 29
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(29, '2024-12-29', 1200.00, 2, '2024-12-29 13:30:00', 1200.00, 29);  -- AGENDADO

-- Status da Ordem de Serviço 30
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(30, '2024-12-30', 600.00, 2, '2024-12-30 15:00:00', 600.00, 30);  -- AGENDADO

-- Status da Ordem de Serviço 31
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(31, '2025-01-02', 1000.00, 2, '2025-01-02 16:00:00', 1000.00, 31);  -- AGENDADO

-- Status da Ordem de Serviço 32
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(32, '2025-01-03', 700.00, 2, '2025-01-03 10:30:00', 700.00, 32);  -- AGENDADO

-- Status da Ordem de Serviço 33
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(33, '2025-01-04', 700.00, 2, '2025-01-04 12:00:00', 700.00, 33);  -- AGENDADO

-- Status da Ordem de Serviço 34
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(34, '2025-01-05', 850.00, 2, '2025-01-05 13:00:00', 850.00, 34);  -- AGENDADO

-- Status da Ordem de Serviço 35
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(35, '2025-01-06', 1000.00, 2, '2025-01-06 14:30:00', 1000.00, 35);  -- AGENDADO

-- Status da Ordem de Serviço 36
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(36, '2025-01-07', 1200.00, 2, '2025-01-07 15:30:00', 1200.00, 36);  -- AGENDADO

-- Status da Ordem de Serviço 37
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(37, '2025-01-08', 500.00, 2, '2025-01-08 16:30:00', 500.00, 37);  -- AGENDADO

-- Status da Ordem de Serviço 38
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(38, '2025-01-09', 600.00, 2, '2025-01-09 17:30:00', 600.00, 38);  -- AGENDADO

-- Status da Ordem de Serviço 39
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(39, '2025-01-10', 800.00, 2, '2025-01-10 18:00:00', 800.00, 39);  -- AGENDADO

-- Status da Ordem de Serviço 40
INSERT INTO public.status_ordem_servico (id, proximo_vencimento, saldo_devedor, tipo_status, ultimo_pagamento, valor_proxima_parcela, ordem_servico_id)
VALUES
(40, '2025-01-11', 700.00, 2, '2025-01-11 09:00:00', 700.00, 40);  -- AGENDADO


-- PARCELAMENTO --

-- Parcelamento para Ordem de Serviço 4 (Saldo Devedor 450.00)
INSERT INTO public.parcela (id, data_vencimento, status_parcela, valor_parcela, ordem_servico_id)
VALUES
(1, '2025-02-01', 1, 150.00, 4),  -- Parcela 1
(2, '2025-03-01', 1, 150.00, 4),  -- Parcela 2
(3, '2025-04-01', 1, 150.00, 4);  -- Parcela 3

-- Parcelamento para Ordem de Serviço 5 (Saldo Devedor 600.00)
INSERT INTO public.parcela (id, data_vencimento, status_parcela, valor_parcela, ordem_servico_id)
VALUES
(4, '2025-02-01', 1, 200.00, 5),  -- Parcela 1
(5, '2025-03-01', 1, 200.00, 5),  -- Parcela 2
(6, '2025-04-01', 1, 200.00, 5);  -- Parcela 3

-- Parcelamento para Ordem de Serviço 6 (Saldo Devedor 600.00)
INSERT INTO public.parcela (id, data_vencimento, status_parcela, valor_parcela, ordem_servico_id)
VALUES
(7, '2025-02-01', 1, 200.00, 6),  -- Parcela 1
(8, '2025-03-01', 1, 200.00, 6),  -- Parcela 2
(9, '2025-04-01', 1, 200.00, 6);  -- Parcela 3

-- Parcelamento para Ordem de Serviço 7 (Saldo Devedor 800.00)
INSERT INTO public.parcela (id, data_vencimento, status_parcela, valor_parcela, ordem_servico_id)
VALUES
(10, '2025-02-01', 1, 266.67, 7),  -- Parcela 1
(11, '2025-03-01', 1, 266.67, 7),  -- Parcela 2
(12, '2025-04-01', 1, 266.67, 7);  -- Parcela 3

-- Parcelamento para Ordem de Serviço 8 (Saldo Devedor 500.00)
INSERT INTO public.parcela (id, data_vencimento, status_parcela, valor_parcela, ordem_servico_id)
VALUES
(13, '2025-02-01', 1, 166.67, 8),  -- Parcela 1
(14, '2025-03-01', 1, 166.67, 8),  -- Parcela 2
(15, '2025-04-01', 1, 166.67, 8);  -- Parcela 3

-- Parcelamento para Ordem de Serviço 9 (Saldo Devedor 350.00)
INSERT INTO public.parcela (id, data_vencimento, status_parcela, valor_parcela, ordem_servico_id)
VALUES
(16, '2025-02-01', 1, 116.67, 9),  -- Parcela 1
(17, '2025-03-01', 1, 116.67, 9),  -- Parcela 2
(18, '2025-04-01', 1, 116.66, 9);  -- Parcela 3

-- Parcelamento para Ordem de Serviço 14 (Saldo Devedor 700.00)
INSERT INTO public.parcela (id, data_vencimento, status_parcela, valor_parcela, ordem_servico_id)
VALUES
(19, '2025-02-01', 1, 233.33, 14),  -- Parcela 1
(20, '2025-03-01', 1, 233.33, 14),  -- Parcela 2
(21, '2025-04-01', 1, 233.34, 14);  -- Parcela 3

-- Parcelamento para Ordem de Serviço 15 (Saldo Devedor 600.00)
INSERT INTO public.parcela (id, data_vencimento, status_parcela, valor_parcela, ordem_servico_id)
VALUES
(22, '2025-02-01', 1, 200.00, 15),  -- Parcela 1
(23, '2025-03-01', 1, 200.00, 15),  -- Parcela 2
(24, '2025-04-01', 1, 200.00, 15);  -- Parcela 3

-- Parcelamento para Ordem de Serviço 16 (Saldo Devedor 700.00)
INSERT INTO public.parcela (id, data_vencimento, status_parcela, valor_parcela, ordem_servico_id)
VALUES
(25, '2025-02-01', 1, 233.33, 16),  -- Parcela 1
(26, '2025-03-01', 1, 233.33, 16),  -- Parcela 2
(27, '2025-04-01', 1, 233.34, 16);  -- Parcela 3

-- Parcelamento para Ordem de Serviço 18 (Saldo Devedor 600.00)
INSERT INTO public.parcela (id, data_vencimento, status_parcela, valor_parcela, ordem_servico_id)
VALUES
(28, '2025-02-01', 1, 200.00, 18),  -- Parcela 1
(29, '2025-03-01', 1, 200.00, 18),  -- Parcela 2
(30, '2025-04-01', 1, 200.00, 18);  -- Parcela 3

