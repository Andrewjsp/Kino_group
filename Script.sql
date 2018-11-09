CREATE DATABASE `group_kino` DEFAULT CHARACTER SET utf8 ;
use `group_kino`;
CREATE TABLE `goods` (
	`good_id` int NOT NULL AUTO_INCREMENT,
	`good_name` varchar(50) NOT NULL,
	`category_id` int,
	`description` mediumtext,
	`parent_id` int,
	`price` int NOT NULL,
	`color_id` int,
	`size_id` int,
	PRIMARY KEY (`good_id`)
);

CREATE TABLE `basket` (
	`order_id` int NOT NULL AUTO_INCREMENT,
	`good_id` int NOT NULL,
	`user_id` int NOT NULL,
	`good_name` varchar(50) NOT NULL,
	`good_price` int NOT NULL,
	PRIMARY KEY (`order_id`)
);


CREATE TABLE `category` (
	`category_id` int NOT NULL,
	`category_name` varchar(30) NOT NULL,
	`language_id` int NOT NULL,
	PRIMARY KEY (`category_id`,`category_name`)
);

CREATE TABLE `user` (
	`user_id` int NOT NULL AUTO_INCREMENT,
	`user_name` varchar(40) NOT NULL,
	`user_login` varchar(40) NOT NULL,
	`user_password` varchar(150) NOT NULL,
	`user_role` int NOT NULL,
	`balance` int NOT NULL,
	PRIMARY KEY (`user_id`)
);

CREATE TABLE `size` (
	`size_id` int NOT NULL AUTO_INCREMENT,
	`size_name` varchar(40) NOT NULL,
	PRIMARY KEY (`size_id`)
);

CREATE TABLE `color` (
	`color_id` int NOT NULL AUTO_INCREMENT,
	`color_name` varchar(40) NOT NULL,
	PRIMARY KEY (`color_id`)
);

CREATE TABLE `language` (
	`Language_id` int NOT NULL AUTO_INCREMENT,
	`Language_name` varchar(20) NOT NULL,
	`Language_locale` varchar(5) NOT NULL,
	PRIMARY KEY (`Language_id`)
);

ALTER TABLE `goods` ADD CONSTRAINT `goods_fk0` FOREIGN KEY (`category_id`) REFERENCES `category`(`category_id`);

ALTER TABLE `goods` ADD CONSTRAINT `goods_fk1` FOREIGN KEY (`color_id`) REFERENCES `color`(`color_id`);

ALTER TABLE `goods` ADD CONSTRAINT `goods_fk2` FOREIGN KEY (`size_id`) REFERENCES `size`(`size_id`);

ALTER TABLE `basket` ADD CONSTRAINT `basket_fk0` FOREIGN KEY (`good_id`) REFERENCES `goods`(`good_id`);

ALTER TABLE `basket` ADD CONSTRAINT `basket_fk1` FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`);

ALTER TABLE `category` ADD CONSTRAINT `category_fk0` FOREIGN KEY (`language_id`) REFERENCES `language`(`Language_id`);





INSERT INTO `group_kino`.`language` (`Language_name`, `Language_locale`) VALUES ('Русский', 'ru');
INSERT INTO `group_kino`.`language` (`Language_name`, `Language_locale`) VALUES ('English', 'en');

INSERT INTO `group_kino`.`size` (`size_name`) VALUES ('M');
INSERT INTO `group_kino`.`size` (`size_name`) VALUES ('XL');
INSERT INTO `group_kino`.`size` (`size_name`) VALUES ('XXL');
INSERT INTO `group_kino`.`size` (`size_name`) VALUES ('XXXL');
INSERT INTO `group_kino`.`size` (`size_name`) VALUES ('S');
INSERT INTO `group_kino`.`size` (`size_name`) VALUES ('L');

INSERT INTO `group_kino`.`category` (`category_id`, `category_name`, `language_id`) VALUES ('1', 'Альбомы', '1');
INSERT INTO `group_kino`.`category` (`category_id`, `category_name`, `language_id`) VALUES ('2', 'Футболки', '1');
INSERT INTO `group_kino`.`category` (`category_id`, `category_name`, `language_id`) VALUES ('3', 'Свитшоты', '1');
INSERT INTO `group_kino`.`category` (`category_id`, `category_name`, `language_id`) VALUES ('1', 'Albums', '2');
INSERT INTO `group_kino`.`category` (`category_id`, `category_name`, `language_id`) VALUES ('2', 'Shirt', '2');
INSERT INTO `group_kino`.`category` (`category_id`, `category_name`, `language_id`) VALUES ('3', 'Sweatshirt', '2');


INSERT INTO `group_kino`.`color` (`color_name`) VALUES ('Красный');
INSERT INTO `group_kino`.`color` (`color_name`) VALUES ('Черный');
INSERT INTO `group_kino`.`color` (`color_name`) VALUES ('Желтый');
INSERT INTO `group_kino`.`color` (`color_name`) VALUES ('Ораньжевый');
INSERT INTO `group_kino`.`color` (`color_name`) VALUES ('Белый');
INSERT INTO `group_kino`.`color` (`color_name`) VALUES ('Зеленый');
INSERT INTO `group_kino`.`color` (`color_name`) VALUES ('Фиолетовый');
INSERT INTO `group_kino`.`color` (`color_name`) VALUES ('Серый');

INSERT INTO `group_kino`.`goods` (`good_name`, `category_id`, `description`, `price`) VALUES ('Черный альбом', '1', 'Кино — восьмой и последний студийный альбом одноимённой рок-группы, записанный в 1990 году. Черновой демо-вариант альбома был записан Виктором Цоем и Юрием Каспаряном в латвийском посёлке Плиеньциемс незадолго до гибели Цоя.', '11000');
INSERT INTO `group_kino`.`goods` (`good_name`, `category_id`, `description`, `price`) VALUES ('Группа крови', '1', 'Гру́ппа кро́ви — шестой студийный альбом советской рок-группы «Кино». Выпущенный в 1988 году, альбом получил широкую известность как в Советском Союзе, так и за рубежом. Выход альбома принёс группе быстрый рост популярности,', '13000');
INSERT INTO `group_kino`.`goods` (`good_name`, `category_id`, `description`, `price`) VALUES ('Звезда по имени солнце', '1', '«Звезда́ по и́мени Со́лнце» — седьмой студийный альбом советской рок-группы «Кино», вышедший в конце лета 1989 года. Последний прижизненный альбом Виктора Цоя. Запись чернового варианта альбома происходила у Георгия Гурьянова, где располагалась домашняя студия группы.', '12500');
INSERT INTO `group_kino`.`goods` (`good_name`,  `parent_id`, `price`) VALUES ('Песня без слов',  '3', '750');
INSERT INTO `group_kino`.`goods` (`good_name`, `parent_id`, `price`) VALUES ('Звезда по имени солнце',  '3', '800');
INSERT INTO `group_kino`.`goods` (`good_name`, `parent_id`, `price`) VALUES ('Невеселая песня',  '3', '850');
INSERT INTO `group_kino`.`goods` (`good_name`, `parent_id`, `price`) VALUES ('Группа крови',  '2', '1000');
INSERT INTO `group_kino`.`goods` (`good_name`,  `parent_id`, `price`) VALUES ('Закрой за мной дверь',  '2', '790');
INSERT INTO `group_kino`.`goods` (`good_name`, `parent_id`, `price`) VALUES ('Кончится лето',  '1', '760');
INSERT INTO `group_kino`.`goods` (`good_name`,  `parent_id`, `price`) VALUES ('Красно-желтые дни',  '1', '800');
INSERT INTO `group_kino`.`goods` (`good_name`,  `parent_id`, `price`) VALUES ('Нам с тобой',  '1', '800');
INSERT INTO `group_kino`.`goods` (`good_name`,  `parent_id`, `price`) VALUES ('Муравейник',  '1', '1000');

INSERT INTO `group_kino`.`goods` ( `good_name`, `category_id`, `description`, `price`, `color_id`, `size_id`) VALUES ( 'Футболка', '2', 'Походная,практичная', '7800', '1', '2');
INSERT INTO `group_kino`.`goods` (`good_name`, `category_id`, `description`, `price`, `color_id`, `size_id`) VALUES ( 'Футболка', '2', 'Повседневная', '9500', '2', '3');
INSERT INTO `group_kino`.`goods` (`good_name`, `category_id`, `description`, `price`, `color_id`, `size_id`) VALUES ( 'Свитшот', '3', 'Туристическая', '17000', '2', '1');
INSERT INTO `group_kino`.`goods` ( `good_name`, `category_id`, `description`, `price`, `color_id`, `size_id`) VALUES ( 'Свитшот', '3', 'Походная,практичная', '12050', '3', '4');
INSERT INTO `group_kino`.`goods` (`good_name`, `category_id`, `description`, `price`, `color_id`, `size_id`) VALUES ( 'Свитшот', '3', 'Туристическая', '15000', '3', '1');

INSERT INTO `group_kino`.`user` (user_name,user_login,user_password,user_role,balance) VALUES ('Джавист','qwerty','f21978e1f04877a4e9853e3b0b4046c623bf6e304474e820af7669d258d28a32',1,5000);
