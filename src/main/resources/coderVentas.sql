
DROP DATABASE coderventas;
create DATABASE coderventas;
use coderventas;


create TABLE clientes(
IdCliente int not null auto_increment,
Nombre varchar(30),
Apellido varchar(30),
Dni int,
constraint Id_Cliente primary key(IdCliente)
);
create TABLE productos(
IdProducto int not null auto_increment,
Nombre varchar(50),
Precio double,
Stock int,
constraint Id_Producto primary key(IdProducto)
);
create TABLE ventas(
IdVenta int not null auto_increment,
Fecha date,
IdCliente int,
Total int,
constraint Id_Venta primary key(IdVenta),
constraint FK_Cliente foreign key(IdCliente) references clientes (IdCliente)
);
create TABLE productos_ventas(
IdProducto_Venta int not null auto_increment,
IdVenta int,
IdProducto int,
Cantidad_Producto int,
constraint Id_Producto_Venta primary key(IdProducto_Venta),
constraint FK_Producto foreign key(IdProducto) references productos (IdProducto),
constraint FK_Venta foreign key(IdVenta) references ventas (IdVenta)
);