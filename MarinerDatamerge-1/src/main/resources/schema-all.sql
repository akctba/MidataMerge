DROP TABLE report IF EXISTS;
CREATE TABLE report (
	report_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
	clientAddress VARCHAR(20),
	clientGuid VARCHAR(50),
	requestTime DATETIME,
	serviceGuid VARCHAR(50),
	retriesRequest INT,
	packetsRequested INT,
	packetsServiced INT,
	maxHoleSize INT
);