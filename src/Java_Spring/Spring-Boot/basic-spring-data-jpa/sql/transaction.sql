SHOW GRANTS FOR 'root'@'localhost';

DROP TABLE IF EXISTS vds_transaction;

-- num_wrong_otp SMALLINT default 0,
CREATE TABLE transaction(
	transaction_id VARCHAR(20) NOT NULL,
	card_number VARCHAR(20) NOT NULL,
	withdraw_money DECIMAL(10, 2) NOT NULL,
	fee DECIMAL(7, 2) NOT NULL,
	status SMALLINT,
	transaction_type SMALLINT DEFAULT 0,	-- Loai 1: Rut tien mat tu the noi dia, Loai 2:
	created_date DATETIME DEFAULT CURRENT_DATE,
	last_update DATETIME DEFAULT CURRENT_DATE,
	# is_lock BOOLEAN DEFAULT FALSE,
	# channel SMALLINT NOT NULL, -- 0: viettel pay pro, 1: web bank plus
	# sent_otp_date DATETIME,
	# secret_number_timeout VARCHAR(10),
	# napas_mb_transaction_id VARCHAR(10),

	PRIMARY KEY (transaction_id),
	FOREIGN KEY (card_number) REFERENCES user_card(card_number)
);

SELECT * FROM transaction WHERE transaction_id = "1";

INSERT INTO vds_transaction(transaction_id, card_number, withdraw_money, fee, status)
VALUES
('1', '9742 4525 8123', 500000.00, 12000.00, 3),
('2', '9742 4525 8123', 540000.00, 12000.00, 3),
('3', '9742 4525 8123', 1000000.00, 12000.00, 3)
;

SELECT * FROM vds_transaction;

SELECT userCard.full_name, vdsTrans.withdraw_money, vdsTrans.fee, vdsTrans.`status`, vdsTrans.created_date FROM vds_transaction vdsTrans
INNER JOIN user_card userCard
ON userCard.card_number = vdsTrans.card_number;

SELECT vdsTrans.transaction_id, userCard.full_name FROM vds_transaction vdsTrans, user_card userCard
WHERE vdsTrans.card_number = userCard.card_number
AND userCard.identification = ""
;