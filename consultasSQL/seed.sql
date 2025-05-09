--Creación de tablas
    CREATE TABLE Habitaciones (
        NumeroHabitacion INT PRIMARY KEY AUTO_INCREMENT,
        TipoHabitacion VARCHAR (50) NOT NULL,
        Capacidad INT NOT NULL DEFAULT 1,
        PrecioNoche DECIMAL(10, 2) NOT NULL,
        Estado VARCHAR(20) DEFAULT 'Disponible' 
    );

    CREATE TABLE Empleados (
        EmpleadoDni VARCHAR(20) PRIMARY KEY,
        Nombre VARCHAR(100) NOT NULL,
        Apellido VARCHAR(100) NOT NULL,
        Puesto VARCHAR(100) NOT NULL,
        Email VARCHAR(100) UNIQUE,
        Telefono VARCHAR(20),
        Jornada VARCHAR(50),
        HorasExtra INT DEFAULT 0,
        Salario DECIMAL(10, 2)
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

    CREATE TABLE HistorialPagos (
        HistorialPagoID INT PRIMARY KEY AUTO_INCREMENT,
        Cuantia DECIMAL(10, 2) NOT NULL,
        Fecha DATE NOT NULL,
        Concepto VARCHAR(50) NOT NULL,
        ReservaID INT,
        EmpleadoDni VARCHAR(20) NULL,  
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

-- Índices
    -- Tabla Habitaciones
    CREATE INDEX idx_NumeroHabitacion ON Habitaciones (NumeroHabitacion);

    -- Tabla Garaje
    CREATE INDEX idx_Estado ON Garaje (Estado);









-- Procedimientos
    DELIMITER //

    CREATE Procedure CalcularReserva(
        IN p_ClienteDni VARCHAR(20),
        IN p_numeroHabitacion INT,
        IN p_fechaEntrada DATE,
        IN p_fechaSalida DATE,
        IN p_numeroAdultos INT,
        IN p_numeroNinos INT,
        IN p_concepto VARCHAR(20) -- 'Ingreso', 'Gasto', 'Reembolso'
    )
    BEGIN
        -- Se declaran las variables para almacenar los dias, el precio por noche y el precio total
        DECLARE numDias DECIMAL(10,2);
        DECLARE precioNoche DECIMAL(10,2);
        DECLARE precioTotal DECIMAL(10,2); 
        DECLARE nuevaReservaID INT;

        -- Se asigna el valor a la variable dia y con DATEDIFF se calcula el número de dias entre las dos fechas
        SET numDias = DATEDIFF(p_fechaEntrada, p_fechaSalida);

        SELECT PrecioNoche INTO precioNoche FROM Habitaciones WHERE NumeroHabitacion = p_numeroHabitacion;

        IF precioNoche IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'precioNoche es null';
        END IF;
        
        IF numDias IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'numDias es null';
        END IF;

        -- Se asigna el valor a la variable precioTotal y se calcula el precio total
        SET precioTotal = precioNoche * numDias;

        -- Se inserta la reserva en la tabla de Reservas
        INSERT INTO Reservas (
            clienteDni, numeroHabitacion, fechaEntrada, fechaSalida,
            numeroAdultos, numeroNinos, fechaReserva, estadoReserva, precioTotal
        )VALUES (
            p_ClienteDni, p_numeroHabitacion, p_fechaEntrada, p_fechaSalida,
            p_numeroAdultos, p_numeroNinos, NOW(), 'Confirmada', precioTotal
        );
        
        -- Obtener el ID de la reserva nueva
        SET nuevaReservaID = LAST_INSERT_ID();

        INSERT INTO HistorialPagos(reservaID, cuantia, fecha, concepto)
        VALUES (nuevaReservaID, precioTotal, NOW(), p_concepto);

        

        -- Se actualiza el estado de la habitación a ocupada
        UPDATE Habitaciones
        SET estado = 'Ocupada'
        WHERE numeroHabitacion = p_numeroHabitacion;

    END //

    DELIMITER ;

-- Triggers
    -- Actualización del estado de la habitación al crear una reserva:
    DELIMITER //

    CREATE TRIGGER actualizar_estado_habitacion_al_reservar
    AFTER INSERT ON Reservas
    FOR EACH ROW
    BEGIN
        UPDATE Habitaciones
        SET Estado = 'Ocupada'
        WHERE NumeroHabitacion = NEW.NumeroHabitacion;
    END//

    DELIMITER ;

    -- Actualización del estado de la habitación al cancelar una reserva:
    DELIMITER //

    CREATE TRIGGER gestion_cancelacion_reserva
    AFTER UPDATE ON Reservas
    FOR EACH ROW
    BEGIN
        IF NEW.EstadoReserva = 'Cancelada' AND OLD.EstadoReserva <> 'Cancelada' THEN
            -- Actualizar el estado de la habitación a 'Disponible'
            UPDATE Habitaciones
            SET Estado = 'Disponible'
            WHERE NumeroHabitacion = NEW.NumeroHabitacion;

            -- Registrar el reembolso en HistorialPagos
            INSERT INTO HistorialPagos (ReservaID, Cuantia, Fecha, Concepto)
            VALUES (NEW.ReservaID, -NEW.PrecioTotal, CURDATE(), 'Reembolso');
        END IF;
    END//

    DELIMITER ;