ALTER TABLE `card`.`rent`
ADD COLUMN `cost` decimal(10, 2) NOT NULL AFTER `rentable_after`;