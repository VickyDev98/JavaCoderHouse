
insert into clientes (Nombre,Apellido,Dni) values
("Martin","Gonzalez",12312312),
("Agustin","Blanco",42566449),
("Brisa","Brussa",45789456),
("Juan","Cavallaro",46123456);



insert into productos (Nombre,Precio,Stock) values
("Mesa",55000,4),
("Silla",9999,20),
("Lampara",4800,40),
("Cuadro",6000,50);


insert into ventas (Fecha,IdCliente,Total) values
("2022-10-20",1,39996),
("2022-10-21",2,12000),
("2022-10-22",3,16800),
("2022-10-23",4,9600);




insert into detalle_ventas (IdVenta,IdProducto,Cantidad_Producto)values
(1,1,1),
(1,2,4),
(2,4,2),
(3,4,2),
(3,3,1),
(4,3,2);