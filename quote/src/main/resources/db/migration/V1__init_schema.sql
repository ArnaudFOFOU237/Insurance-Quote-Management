CREATE TABLE IF NOT EXISTS quote_entity (
    id BINARY(16) NOT NULL,
    client_id INT,
    status ENUM('PROVISOIRE', 'VALIDE', 'ACCEPTE', 'REFUSE') NOT NULL,
    product_type ENUM('AUTO', 'SANTE', 'HABITATION', 'VIE') NOT NULL,
    percentage_insure FLOAT NOT NULL,
    capital_insure FLOAT NOT NULL,
    life_time INT NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;