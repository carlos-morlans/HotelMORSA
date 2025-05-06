CREATE TABLE Habitaciones (
    NumeroHabitacion INT PRIMARY KEY AUTO_INCREMENT,
    TipoHabitacion VARCHAR (50) NOT NULL,
    Capacidad INT NOT NULL DEFAULT 1,
    PrecioNoche DECIMAL(10, 2) NOT NULL,
    Estado VARCHAR(20) DEFAULT 'Disponible' 
);

CREATE TABLE Empleados (
    EmpleadoDni VARCHAR(20) PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(100) NOT NULL,
    Apellido VARCHAR(100) NOT NULL,
    Puesto VARCHAR(100) NOT NULL,
    Email VARCHAR(100) UNIQUE,
    Telefono VARCHAR(20),
    Jornada VARCHAR(50),
    HorasExtra INT DEFAULT 0
);

CREATE TABLE Clientes (
    ClienteDni VARCHAR(20) PRIMARY KEY, 
    Nombre VARCHAR(100) NOT NULL,
    Apellido VARCHAR(100) NOT NULL,
    Email VARCHAR(100) UNIQUE,
    Telefono VARCHAR(20),
    Direccion VARCHAR(255)
);

CREATE TABLE Reservas (
    ReservaID INT PRIMARY KEY AUTO_INCREMENT,
    ClienteDni VARCHAR(20) NOT NULL, 
    NumeroHabitacion INT NOT NULL,
    FechaEntrada DATE NOT NULL,
    FechaSalida DATE NOT NULL,
    NumeroAdultos INT NOT NULL,
    NumeroNinos INT DEFAULT 0,
    FechaReserva TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    EstadoReserva VARCHAR(20) DEFAULT 'Pendiente', 
    PrecioTotal DECIMAL(10, 2) NOT NULL,
    FechaCancelacion DATE,
    MotivoCancelacion VARCHAR(255),
    FOREIGN KEY (ClienteDni) REFERENCES Clientes(ClienteDni) ON DELETE CASCADE,
    FOREIGN KEY (NumeroHabitacion) REFERENCES Habitaciones(NumeroHabitacion) ON DELETE CASCADE
);

CREATE TABLE Garaje (
    NumeroPlaza INT PRIMARY KEY AUTO_INCREMENT,
    Estado VARCHAR(20) DEFAULT 'Disponible' 
);

CREATE TABLE Pagos (
    PagoID INT PRIMARY KEY AUTO_INCREMENT,
    ReservaID INT NOT NULL,
    FechaPago TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    Monto DECIMAL(10, 2) NOT NULL, 
    MetodoPago VARCHAR(50) NOT NULL,
    FechaReembolso DATE NULL,
    MotivoReembolso VARCHAR(255) NULL,
    EmpleadoDni INT NULL, 
    FOREIGN KEY (ReservaID) REFERENCES Reservas(ReservaID) ON DELETE CASCADE,
    FOREIGN KEY (EmpleadoDni) REFERENCES Empleados(EmpleadoDni) ON DELETE SET NULL 
);

CREATE TABLE Eventos (
    EventoID INT PRIMARY KEY AUTO_INCREMENT,
    NombreEvento VARCHAR(255) NOT NULL,
    FechaEvento DATE NOT NULL,
    HoraInicio TIME,
    Precio DECIMAL(10, 2),
    Capacidad INT
);

-- Índices para la tabla Habitaciones
CREATE INDEX idx_TipoHabitacion ON Habitaciones (TipoHabitacion);
CREATE INDEX idx_PrecioNoche ON Habitaciones (PrecioNoche);
CREATE INDEX idx_Estado ON Habitaciones (Estado);
CREATE INDEX idx_Capacidad ON Habitaciones (Capacidad);

-- Índices para la tabla Empleados
CREATE INDEX idx_EmpleadoDni ON Empleados (EmpleadoDni);
CREATE INDEX idx_Apellido ON Empleados (Apellido);
CREATE INDEX idx_Puesto ON Empleados (Puesto);
CREATE INDEX idx_Jornada ON Empleados (Jornada);
CREATE INDEX idx_HorasExtra ON Empleados (HorasExtra);

-- Índices para la tabla Clientes
CREATE INDEX idx_ClienteDni ON Clientes (ClienteDni);
CREATE INDEX idx_Apellido ON Clientes (Apellido);

-- Índices para la tabla Reservas
CREATE INDEX idx_ClienteDni ON Reservas (ClienteDni);
CREATE INDEX idx_NumeroHabitacion ON Reservas (NumeroHabitacion);
CREATE INDEX idx_FechaReserva ON Reservas (FechaReserva);
CREATE INDEX idx_FechaEntrada ON Reservas (FechaEntrada);
CREATE INDEX idx_FechaSalida ON Reservas (FechaSalida);
CREATE INDEX idx_EstadoReserva ON Reservas (EstadoReserva);
CREATE INDEX idx_PrecioTotal ON Reservas (PrecioTotal);

-- Índice para la tabla Garaje
CREATE INDEX idx_Estado ON Garaje (Estado);

-- Índices para la tabla Pagos
CREATE INDEX idx_ReservaID ON Pagos (ReservaID);
CREATE INDEX idx_FechaPago ON Pagos (FechaPago);
CREATE INDEX idx_FechaReembolso ON Pagos (FechaReembolso);
CREATE INDEX idx_MetodoPago ON Pagos (MetodoPago);
CREATE INDEX idx_EmpleadoDni ON Pagos (EmpleadoDni);

-- Índices para la tabla Eventos
CREATE INDEX idx_FechaEvento ON Eventos (FechaEvento);
CREATE INDEX idx_NombreEvento ON Eventos (NombreEvento);