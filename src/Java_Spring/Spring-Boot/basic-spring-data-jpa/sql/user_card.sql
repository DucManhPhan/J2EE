SHOW DATABASES;

USE rtm_sample;

SHOW TABLES;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS user_card;

CREATE TABLE user_card (
	card_number VARCHAR(20) NOT NULL,
	identification VARCHAR(20) NOT NULL,
	phone_number VARCHAR(20) NOT NULL,
	full_name VARCHAR(255) NOT NULL,
	validity_date VARCHAR(10) NOT NULL,
	created_date DATETIME DEFAULT CURRENT_DATE,
	last_update DATETIME DEFAULT CURRENT_DATE,

	PRIMARY KEY(card_number)
);

INSERT INTO user_card(card_number, identification, phone_number, full_name, validity_date)
VALUES
('9742 4525 8639', '052 632 859', '0165658563', 'HA TIEN QUAN', '09/12'),
('9742 4525 8123', '052 612 859', '0165658563', 'CAO NGOC THANG', '09/12'),
('9742 4525 8690', '052 678 852', '0165658663', 'DAO NGOC THANG', '09/12'),
('9742 4525 8987', '052 645 859', '0165658563', 'HA TIEN DAO', '09/12')
;

SELECT * FROM user_card;
