INSERT INTO `user` (`id`, `birth`, `email`, `name`, `nickname`, `password`) VALUES ('1', '2023-09-01', 'csw@csw.com', 'sungwon', 'csw', 'csw!@#$');

INSERT INTO `ingredient` (`alert_quantity`, `buy_date`, `expired_date`, `prime_price`, `relieved_quantity`, `remain_quantity`, `id`, `user_id`, `name`, `units`, `url`) values ('50', '2023-08-31', '2023-09-25', '30', '100', '100', '1', '1', 'potato', 'g', 'www.ref.com');
INSERT INTO `ingredient` (`alert_quantity`, `buy_date`, `expired_date`, `prime_price`, `relieved_quantity`, `remain_quantity`, `id`, `user_id`, `name`, `units`, `url`) values ('200', '2023-08-31', '2023-09-10', '15', '1000', '1000', '2', '1', 'cheese', 'g', 'www.ref.com');
INSERT INTO `ingredient` (`alert_quantity`, `buy_date`, `expired_date`, `prime_price`, `relieved_quantity`, `remain_quantity`, `id`, `user_id`, `name`, `units`, `url`) values ('1000', '2023-08-31', '2023-09-01', '298', '10000', '10000', '3', '1', 'beef', 'g', 'www.ref.com');

INSERT INTO `food` (`id`, `fixed_price`, `name`, `user_id`) VALUES ('1', '15000', 'Potato Pizza', '1');
INSERT INTO `food` (`id`, `fixed_price`, `name`, `user_id`) VALUES ('2', '28000', 'Beef Steak', '1');

INSERT INTO `recipe` (`id`, `food_id`, `ingredient_id`, `quantity`) VALUES ('1', '1', '1', '100');
INSERT INTO `recipe` (`id`, `food_id`, `ingredient_id`, `quantity`) VALUES ('2', '1', '2', '250');
INSERT INTO `recipe` (`id`, `food_id`, `ingredient_id`, `quantity`) VALUES ('3', '1', '3', '100');
INSERT INTO `recipe` (`id`, `food_id`, `ingredient_id`, `quantity`) VALUES ('4', '2', '1', '70');
INSERT INTO `recipe` (`id`, `food_id`, `ingredient_id`, `quantity`) VALUES ('5', '2', '3', '300');

INSERT INTO `sales_history` (`id`, `count`, `fixed_price`, `food_id`, `food_name`, `sales_date`, `user_id`) VALUES ('1', '10', '15000', '1', 'Potato Pizza', '2023-09-01', '1');