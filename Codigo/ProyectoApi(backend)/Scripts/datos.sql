INSERT INTO alergenos (nombre, descripcion) VALUES
('Gluten', 'Contiene gluten, un tipo de proteína presente en el trigo, cebada y centeno.'),
('Lactosa', 'Contiene lactosa, un azúcar natural que se encuentra en la leche y productos lácteos.'),
('Frutos secos', 'Contiene frutos secos como almendras, nueces, avellanas, etc.'),
('Pescado', 'Contiene pescado, un alimento proveniente de diversas especies marinas o de agua dulce.'),
('Mariscos', 'Contiene mariscos como camarones, langostas, mejillones y otros crustáceos.');

INSERT INTO personal (nombre, rol, correo_electronico, telefono, dni) VALUES
('Juan Pérez', 'Cocinero', 'juan.perez@catering.com', '555123456', '12345678A'),
('Ana Gómez', 'Cocinero', 'ana.gomez@catering.com', '555234567', '23456789B'),
('Carlos Rodríguez', 'Cocinero', 'carlos.rodriguez@catering.com', '555345678', '34567890C'),
('Laura Martínez', 'Cocinero', 'laura.martinez@catering.com', '555456789', '45678901D'),
('David López', 'Cocinero', 'david.lopez@catering.com', '555567890', '56789012E'),
('Pedro Sánchez', 'Conductor', 'pedro.sanchez@catering.com', '555678901', '67890123F'),
('Marta Díaz', 'Conductor', 'marta.diaz@catering.com', '555789012', '78901234G'),
('José García', 'Conductor', 'jose.garcia@catering.com', '555890123', '89012345H'),
('Elena Fernández', 'Conductor', 'elena.fernandez@catering.com', '555901234', '90123456I'),
('Sofía Ramírez', 'Conductor', 'sofia.ramirez@catering.com', '555012345', '01234567J');

INSERT INTO clientes (nombre, correo_electronico, telefono, direccion, dni) VALUES
('Luis Torres', 'luis.torres@cliente.com', '555123987', 'Calle Falsa 123', '87654321K'),
('María Sánchez', 'maria.sanchez@cliente.com', '555234876', 'Avenida Siempre Viva 456', '98765432L'),
('Pedro Martínez', 'pedro.martinez@cliente.com', '555345765', 'Plaza Mayor 789', '65432109M'),
('Ana Pérez', 'ana.perez@cliente.com', '555456654', 'Calle Luna 101', '76543210N'),
('Carlos Rodríguez', 'carlos.rodriguez@cliente.com', '555567543', 'Calle Sol 202', '54321098O'),
('Elena García', 'elena.garcia@cliente.com', '555678432', 'Paseo Marítimo 303', '43210987P'),
('José López', 'jose.lopez@cliente.com', '555789321', 'Carretera Real 404', '32109876Q'),
('Isabel Fernández', 'isabel.fernandez@cliente.com', '555890210', 'Calle del Río 505', '21098765R'),
('Sofía González', 'sofia.gonzalez@cliente.com', '555901109', 'Calle del Árbol 606', '10987654S'),
('Raúl García', 'raul.garcia@cliente.com', '555012998', 'Calle del Mar 707', '99876543T');

INSERT INTO pedidos (cliente_id, estado, monto_total, personal_id) VALUES
(1, 'En proceso', 150.00, 1),
(2, 'Entregado', 200.50, 2),
(3, 'En proceso', 120.00, 3),
(4, 'Entregado', 180.75, 4),
(5, 'En proceso', 220.00, 5);

INSERT INTO articulos_menu (nombre, descripcion, precio) VALUES
('Paella Valenciana', 'Arroz con mariscos, pollo y verduras', 12.50),
('Tacos de Pescado', 'Tacos con pescado frito y salsa picante', 9.99),
('Hamburguesa Clásica', 'Carne de res, pan de hamburguesa y condimentos', 8.50),
('Sopa de Mariscos', 'Sopa con camarones, almejas y calamares', 10.00),
('Pizza Margarita', 'Pizza con tomate, mozzarella y albahaca', 7.50),
('Ensalada César', 'Lechuga, pollo a la parrilla, aderezo César', 6.99),
('Sushi Roll', 'Rollos de sushi con atún y aguacate', 14.00),
('Spaghetti Carbonara', 'Pasta con salsa de huevo y panceta', 11.00),
('Risotto de Setas', 'Risotto cremoso con setas frescas', 13.50),
('Costillas a la Barbacoa', 'Costillas de cerdo con salsa barbacoa', 15.00),
('Pollo al Curry', 'Pollo cocinado con salsa de curry y arroz', 12.00),
('Tarta de Queso', 'Tarta dulce de queso con mermelada de frutos rojos', 5.50),
('Brownie de Chocolate', 'Pastel de chocolate con nueces', 4.75),
('Ensalada Caprese', 'Tomate, mozzarella y albahaca con aceite de oliva', 7.25),
('Ceviche de Pescado', 'Pescado marinado con jugo de limón y cebolla morada', 10.50),
('Croquetas de Jamón', 'Croquetas rellenas de jamón y bechamel', 5.00),
('Papas Fritas', 'Papas fritas crujientes con sal', 3.00),
('Gambas a la Plancha', 'Gambas cocinadas a la parrilla con aceite de oliva', 12.00),
('Tarta de Limón', 'Tarta refrescante de limón con base de galleta', 5.00),
('Pudin de Chía', 'Pudin saludable de semillas de chía con leche de almendras', 4.50);
INSERT INTO articulos_menu_alergenos (articulo_menu_id, alergeno_id) VALUES
(1, 2), (1, 3), (2, 4), (2, 5), (3, 1),
(4, 2), (4, 3), (5, 1), (5, 4), (6, 5),
(7, 2), (7, 3), (8, 1), (8, 4), (9, 2),
(10, 1), (10, 5), (11, 4), (11, 2), (12, 3),
(13, 1), (13, 5), (14, 4), (14, 2), (15, 1),
(16, 3), (16, 2), (17, 4), (17, 5), (18, 1),
(19, 2), (19, 5), (20, 3), (20, 4), (1, 2);
INSERT INTO articulos_pedido (pedido_id, articulo_menu_id, cantidad, precio, personal_id) VALUES
(1, 1, 2, 12.50, 1),
(1, 2, 1, 9.99, 2),
(2, 3, 3, 8.50, 3),
(2, 4, 2, 10.00, 4),
(3, 5, 1, 7.50, 5),
(4, 6, 4, 6.99, 1),
(5, 7, 1, 14.00, 2);
