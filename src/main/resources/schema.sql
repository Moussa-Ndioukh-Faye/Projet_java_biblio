
CREATE TABLE membres (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL
);

CREATE TABLE documents (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titre VARCHAR(200) NOT NULL,
    anneePublication INT,
    disponible BOOLEAN DEFAULT TRUE,
    type VARCHAR(20) NOT NULL
);

CREATE TABLE livres (
    id INT PRIMARY KEY,
    auteur VARCHAR(150),
    isbn VARCHAR(50),
    genre VARCHAR(100),
    FOREIGN KEY (id) REFERENCES documents(id)
);

CREATE TABLE revues (
    id INT PRIMARY KEY,
    periodicite VARCHAR(100),
    numeroCahier INT,
    FOREIGN KEY (id) REFERENCES documents(id)
);

CREATE TABLE emprunts (
    id INT PRIMARY KEY AUTO_INCREMENT,
    membreId INT NOT NULL,
    documentId INT NOT NULL,
    dateEmprunt DATE NOT NULL,
    dateRetourPrevu DATE NOT NULL,
    dateRetourEffectif DATE,
    amendeParJour DOUBLE DEFAULT 100,
    FOREIGN KEY (membreId) REFERENCES membres(id),
    FOREIGN KEY (documentId) REFERENCES documents(id)
);

CREATE TABLE caisse_historique (
    id INT PRIMARY KEY AUTO_INCREMENT,
    membre VARCHAR(200),
    montant DOUBLE NOT NULL,
    dateTransaction DATE NOT NULL
);

CREATE TABLE vehicules (
    id INT PRIMARY KEY AUTO_INCREMENT,
    immatriculation VARCHAR(50) NOT NULL,
    kilometrage DOUBLE DEFAULT 0,
    enService BOOLEAN DEFAULT TRUE
);
