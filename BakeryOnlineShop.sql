USE [master]
GO

IF EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name = 'BakeryShop')
BEGIN
	ALTER DATABASE [BakeryShop] SET OFFLINE WITH ROLLBACK IMMEDIATE;
	ALTER DATABASE [BakeryShop] SET ONLINE;
	DROP DATABASE [BakeryShop];
END

GO

CREATE DATABASE [BakeryShop]
GO

USE [BakeryShop]
GO

CREATE TABLE [Customers](
	[userID] int NOT NULL IDENTITY(1,1) primary key ,
	[username] varchar(30) NOT NULL Unique,
	[password] varchar(32) ,
	[fullname] varchar(50) ,
	[email] nvarchar(100) ,
	[googleID] nvarchar(100) ,
	[accessToken] nvarchar(100) ,
	[userAvatar] nvarchar(100) ,
	[address] nvarchar(50),
	[phoneNumber] nvarchar(20),
);
CREATE TABLE [Staffs](
	[staffID] int NOT NULL IDENTITY(1,1) primary key,
	[staffName] varchar(30) NOT NULL Unique ,
	[password] varchar(32) ,
	[fullname] varchar(50) ,
	[email] nvarchar(100) ,
	[address] nvarchar(50),
	[phoneNumber] nvarchar(20),
	[staffAvatar] nvarchar(100) ,
	[managerID] int foreign key references Staffs(staffID) Not null,
);
CREATE TABLE [Orders](
	[orderID] int NOT NULL IDENTITY(1,1) primary key,
	[userID] int NOT NULL foreign key references Customers(userID),
	[staffID] int foreign key references Staffs(staffID),
	[orderDescription] nvarchar(255) ,
	[totalPrice] Decimal,
	[orderDate] Date NOT NULL,
	[receivedDate] Date,
	[wasPaid] Bit ,
	[status] nvarchar(20),
);

CREATE TABLE [Cakes](
	[cakeID] int Not null IDENTITY(1,1) primary key ,
	[cakeName] Nvarchar(30) NOT NULL,
	[cakeDescription] Nvarchar(200),
	[cakePrice] Decimal not null ,
	[cakeImg] nvarchar(100),
	[cakeQuantity] int Not null,
	[cakeType] nvarchar(30),
);
CREATE TABLE [Ratings](
	[userID] int Not null foreign key references Customers(userID) ,
	[cakeID] int NOT NULL foreign key references Cakes(cakeID),
	[ratingDate] Date not null,
	[ratingValue] int Not null,
	[comment] varchar(200),
	primary key (userID,cakeID),
);
CREATE TABLE [CakeInOrder](
	[cioID] int Not null IDENTITY(1,1) primary key ,
	[cioQuantity] int Not null,
	[orderID] int foreign key references Orders(orderID),
	[cakeID] int NOT NULL foreign key references Cakes(cakeID),
);
CREATE TABLE [Toppings](
	[toppingID] int Not null IDENTITY(1,1) primary key ,
	[toppingName] Nvarchar(20) Not null,
	[toppingQuantity] int not null,
	[toppingPrice] Decimal not null,
	[toppingImg] nvarchar(100),
	[toppingDescription] Nvarchar(100),
);
CREATE TABLE [GoWiths](
	[cakeID] int NOT NULL foreign key references Cakes(cakeID),
	[toppingID] int NOT NULL foreign key references Toppings(toppingID),
	primary key (cakeID,toppingID),
);
CREATE TABLE [ToppingInCake](
	[cioID] int NOT NULL foreign key references [CakeInOrder](cioID),
	[toppingID] int NOT NULL foreign key references Toppings(toppingID),
	[ticQuantity] int not null,
	primary key (cioID,toppingID),
);
CREATE TABLE [ProductHistory](
	[phID] int Not null IDENTITY(1,1) primary key ,
	[phQuantity] int not null,
	[updateDate] Date,
	[createDate] Date,
	[updateBy] int foreign key references Staffs(staffID),
	[createBy] int foreign key references Staffs(staffID),
	[toppingID] int foreign key references Toppings(toppingID),
	[cakeID] int foreign key references Cakes(cakeID),
);


INSERT INTO Customers ([username], [password], [fullname], [email], [googleID], [accessToken], [userAvatar], [address], [phoneNumber])
VALUES 
    ('luffy',CONVERT(VARCHAR(32), HASHBYTES('MD5', 'onepiece'), 2), 'Monkey D. Luffy', 'luffy@strawhatpirates.com', NULL, NULL, 'Image/Avatar/luffy.jpg', 'Thousand Sunny, East Blue', '0123456789'),
    ('voldemort',CONVERT(VARCHAR(32), HASHBYTES('MD5',  'darklord'), 2),'Lord Voldemort', 'voldemort@deathEaters.com', NULL, NULL, 'Image/Avatar/voldemort.jpg', 'Malfoy Manor, Wiltshire', '0987654321'),
    ('mickeymouse',CONVERT(VARCHAR(32), HASHBYTES('MD5', 'disney'), 2), 'Mickey Mouse', 'mickey@disney.com', NULL, NULL, 'Image/Avatar/mickeymouse.jpg', 'Disneyland, Anaheim, California', '0123456789'),
    ('darthvader', CONVERT(VARCHAR(32), HASHBYTES('MD5', 'sithlord'), 2),'Darth Vader', 'vader@empire.com', NULL, NULL, 'Image/Avatar/darthvader.jpg', 'Death Star, Outer Rim Territories', '0987654321'),
    ('sherlockholmes', CONVERT(VARCHAR(32), HASHBYTES('MD5', 'elementary'), 2),  'Sherlock Holmes', 'sherlock@bakerstreet.com', NULL, NULL, 'Image/Avatar/sherlockholmes.jpg', '221B Baker Street, London', '0123456789'),
    ('winniethepooh',CONVERT(VARCHAR(32), HASHBYTES('MD5', 'honey'), 2),  'Winnie the Pooh', 'pooh@hundredacrewood.com', NULL, NULL, 'Image/Avatar/winniethepooh.jpg', 'Hundred Acre Wood', '0987654321'),
    ('hanniballecter',CONVERT(VARCHAR(32), HASHBYTES('MD5', 'favaBeans'), 2),  'Hannibal Lecter', 'hannibal@psychiatrist.com', NULL, NULL, 'Image/Avatar/hanniballecter.jpg', 'Baltimore State Hospital for the Criminally Insane', '0123456789'),
    ('elizabethbennet', CONVERT(VARCHAR(32), HASHBYTES('MD5', 'prideprejudice'), 2), 'Elizabeth Bennet', 'elizabeth@longbourn.com', NULL, NULL, 'Image/Avatar/elizabethbennet.jpg', 'Longbourn Estate, Hertfordshire', '0987654321'),
    ('michaeljackson', CONVERT(VARCHAR(32), HASHBYTES('MD5', 'moonwalk'), 2), 'Michael Jackson', 'michael@neverlandranch.com', NULL, NULL, 'Image/Avatar/michaeljackson.jpg', 'Neverland Ranch, California', '0123456789'),
    ('pikachu',CONVERT(VARCHAR(32), HASHBYTES('MD5', 'thunderbolt'), 2),  'Pikachu', 'pikachu@pokemon.com', NULL, NULL, 'Image/Avatar/pikachu.jpg', 'Pallet Town, Kanto', '0987654321'),
    ('harrypotter',CONVERT(VARCHAR(32), HASHBYTES('MD5', 'hogwarts'), 2),  'Harry Potter', 'harry@hogwarts.com', NULL, NULL, 'Image/Avatar/harrypotter.jpg', '4 Privet Drive, Little Whinging, Surrey', '0123456789'),
    ('ironman', CONVERT(VARCHAR(32), HASHBYTES('MD5', 'tonystark'), 2), 'Tony Stark', 'tony@starkindustries.com', NULL, NULL, 'Image/Avatar/ironman.jpg', 'Stark Tower, New York', '0987654321'),
    ('batman', CONVERT(VARCHAR(32), HASHBYTES('MD5', 'brucewayne'), 2),  'Bruce Wayne', 'bruce@wayneenterprises.com', NULL, NULL, 'Image/Avatar/batman.jpg', 'Wayne Manor, Gotham City', '0123456789'),
    ('wonderwoman', CONVERT(VARCHAR(32), HASHBYTES('MD5', 'amazon'), 2),  'Diana Prince', 'diana@themyscira.com', NULL, NULL, 'Image/Avatar/wonderwoman.jpg', 'Themyscira, Paradise Island', '0987654321'),
    ('spiderman', CONVERT(VARCHAR(32), HASHBYTES('MD5', 'peterparker'), 2),  'Peter Parker', 'peter@dailybugle.com', NULL, NULL, 'Image/Avatar/spiderman.jpg', 'Queens, New York City', '0123456789'),
    ('jamesbond',CONVERT(VARCHAR(32), HASHBYTES('MD5', '007'), 2), 'James Bond', 'james@mi6.com', NULL, NULL, 'Image/Avatar/jamesbond.jpg', 'London, England', '0987654321'),
    ('gandalf', CONVERT(VARCHAR(32), HASHBYTES('MD5', 'youshallnotpass'), 2),  'Gandalf the Grey', 'gandalf@greyhavens.com', NULL, NULL, 'Image/Avatar/gandalf.jpg', 'The Shire, Middle-earth', '0123456789'),
    ('deadpool',CONVERT(VARCHAR(32), HASHBYTES('MD5', 'chimichangas'), 2),  'Deadpool', 'deadpool@mercwithamouth.com', NULL, NULL, 'Image/Avatar/deadpool.jpg', 'New York City', '0987654321'),
    ('joker', CONVERT(VARCHAR(32), HASHBYTES('MD5', 'whysoserious'), 2), 'The Joker', 'joker@arkhamasylum.com', NULL, NULL, 'Image/Avatar/joker.jpg', 'Gotham City', '0123456789'),
    ('lukeSkywalker', CONVERT(VARCHAR(32), HASHBYTES('MD5', 'usetheforce'), 2), 'Luke Skywalker', 'luke@rebels.com', NULL, NULL, 'Image/Avatar/lukeskywalker.jpg', 'Tatooine', '0987654321');


INSERT INTO Staffs ([staffName], [password], [fullname], [email], [address], [phoneNumber], [staffAvatar], [managerID])
VALUES 
    ('johnsmith', CONVERT(VARCHAR(32), HASHBYTES('MD5', 'johnSmith@123'),2 ), 'John Smith', 'john@company.com', '123 Main Street, City', '0927481623', 'Image/Avatar/johnsmith.jpg', 1),
    ('janedoe', CONVERT(VARCHAR(32), HASHBYTES('MD5', 'janeDoe@123'),2 ), 'Jane Doe', 'jane@company.com', '456 Oak Avenue, Town', '0926371623', 'Image/Avatar/janedoe.jpg', 1),
    ('michaelbrown', CONVERT(VARCHAR(32), HASHBYTES('MD5', 'michaelBrown@123'), 2), 'Michael Brown', 'michael@company.com', '789 Pine Road, Village', '0926374134', 'Image/Avatar/michaelbrown.jpg', 1),
    ('emilyjohnson', CONVERT(VARCHAR(32), HASHBYTES('MD5', 'emilyJohnson@123'), 2), 'Emily Johnson', 'emily@company.com', '101 Maple Lane, County', '0927374826', 'Image/Avatar/emilyjohnson.jpg', 1),
    ('williamtaylor',CONVERT(VARCHAR(32), HASHBYTES('MD5', 'williamTaylor@123'), 2),  'William Taylor', 'william@company.com', '111 Cedar Drive, District', '0925236805', 'Image/Avatar/williamtaylor.jpg', 1),
    ('sarahjones', CONVERT(VARCHAR(32), HASHBYTES('MD5', 'sarahJones@123'), 2), 'Sarah Jones', 'sarah@company.com', '1212 Elm Street, Territory', '0956351823', 'Image/Avatar/sarahjones.jpg', 1),
    ('robertrodriguez', CONVERT(VARCHAR(32), HASHBYTES('MD5', 'roberTrodriguez@123'), 2), 'Robert Rodriguez', 'robert@company.com', '1313 Walnut Avenue, Province', '0972637251', 'Image/Avatar/robertrodriguez.jpg', 1),
    ('jessicawilliams',CONVERT(VARCHAR(32), HASHBYTES('MD5', 'jessicaWilliams'), 2),  'Jessica Williams', 'jessica@company.com', '1414 Pinecrest Road, State', '0926481623', 'Image/Avatar/jessicawilliams.jpg', 1),
    ('davidmartinez', CONVERT(VARCHAR(32), HASHBYTES('MD5', 'davidMartinez@123'), 2),'David Martinez', 'david@company.com', '1515 Oakwood Boulevard, Country', '0926381623', 'Image/Avatar/davidmartinez.jpg', 1),
    ('elizabethtaylor', CONVERT(VARCHAR(32), HASHBYTES('MD5', 'elizabethTaylor@123'), 2),  'Elizabeth Taylor', 'elizabeth@company.com', '1616 Maplewood Drive, Empire', '0916381627', 'Image/Avatar/elizabethtaylor.jpg', 1);

-- Chèn dữ liệu vào bảng Orders
INSERT INTO Orders ([userID], [staffID], [orderDescription], [totalPrice], [orderDate], [receivedDate], [wasPaid], [status])
VALUES
(8, 4, N'Bánh chia tay cho bữa tiệc nghỉ hưu của đồng nghiệp.', 520000, '2024-01-05', '2024-01-09', 1, 'Done'),
(1, 2, N'Bánh chia tay cho bữa tiệc nghỉ hưu của đồng nghiệp.', 233000, '2024-01-05', '2024-01-09', 1, 'Done'),
(9, 2, N'Bánh cho sự kiện gây quỹ ủng hộ từ thiện địa phương.', 990000, '2024-01-05', '2024-01-10', 1, 'Done'),
(1, 10, N'Bánh cho sự kiện gây quỹ ủng hộ từ thiện địa phương.', 690000, '2024-01-05', '2024-01-10', 1, 'Done'),
(10, 8, N'Bánh bất ngờ giao cho buổi tiệc chúc mừng việc thăng chức của Jane.', 380000, '2024-01-05', '2024-01-11', 1, 'Done'),
(1, 2, N'Bánh bất ngờ giao cho buổi tiệc chúc mừng việc thăng chức của Jane.', 478000, '2024-01-05', '2024-01-11', 1, 'Done'),
(7, 3, N'Bánh tiệc cho sự kiện tổ chức tiệc mừng sinh em bé của gia đình Johnson.', 849000, '2024-01-06', '2024-01-08', 1, 'Done'),
(1, 2, N'Bánh tiệc cho sự kiện tổ chức tiệc mừng sinh em bé của gia đình Johnson.', 676000, '2024-01-06', '2024-01-08', 1, 'Done'),
(6, 2, N'Bánh theo chủ đề ngày lễ cho bữa tiệc Giáng sinh.', 281000, '2024-01-08', '2024-01-07', 1, 'Done'),
(12, 5, N'Bánh trà cho buổi gặp mặt cựu đồng nghiệp.', 3879000, '2024-01-12', '2024-01-14', 1, 'Done'),
(1, 2, N'Bánh trà cho buổi gặp mặt cựu đồng nghiệp.', 284000, '2024-01-12', '2024-01-14', 1, 'Done'),
(13, 6, N'Bánh sinh nhật cho bữa tiệc sinh nhật lần thứ 50 của Jane.', 450000, '2024-01-13', '2024-01-15', 1, 'Done'),
(1, 2, N'Bánh sinh nhật cho bữa tiệc sinh nhật lần thứ 50 của Jane.', 370000, '2024-01-13', '2024-01-15', 1, 'Done'),
(14, 2, N'Bánh chia tay cho đồng nghiệp sắp chuyển công ty.', 340000, '2024-01-14', '2024-01-16', 1, 'Done'),
(1, 9, N'Bánh chia tay cho đồng nghiệp sắp chuyển công ty.', 283000, '2024-01-14', '2024-01-16', 1, 'Done'),
(19, 2, N'Bánh tặng cho buổi tiệc mừng khai trương của cửa hàng mới.', 291000, '2024-01-19', '2024-01-21', 1, 'Done'),
(1, 4, N'Bánh tặng cho buổi tiệc mừng khai trương của cửa hàng mới.', 253000, '2024-01-19', '2024-01-21', 1, 'Done'), 
(20, 3, N'Bánh chia tay cho đồng nghiệp chuyển công tác.', 310000, '2024-01-20', '2024-01-22', 1, 'Done'),
(1, 2, N'Bánh chia tay cho đồng nghiệp chuyển công tác.', 291000, '2024-01-20', '2024-01-22', 1, 'Done'),
(1, 4, N'Bánh cưới với hoa trang trí cho đám cưới của Smith-Jones', 268000, '2024-02-01', NULL, 0, 'Delivering'),
(2, 4, N'Bánh cưới với hoa trang trí cho đám cưới của Smith-Jones', 309000, '2024-02-07', NULL, 0, 'Delivering'),
(15, 2, N'Bánh đặc biệt cho buổi tiệc cuối năm của công ty.', 324000, '2024-02-08', NULL, 0, 'Delivering'),
(1, 6, N'Bánh tùy chỉnh cho sự kiện kỷ niệm hàng năm của công ty', 257000, '2024-02-08', NULL, 1, 'Delivering'),
(1, 2, N'Bánh đặc biệt cho buổi tiệc cuối năm của công ty.', 310000, '2024-02-09', NULL, 0, 'Delivering'),
(4, 6, N'Bánh tùy chỉnh cho sự kiện kỷ niệm hàng năm của công ty', 331000, '2024-02-09', NULL, 1, 'Delivering'),
(5, NULL, N'Bánh đặc biệt cho lễ tốt nghiệp của Mary Smith.', 334000, '2024-02-16', NULL, 1, 'Waiting'),
(11, NULL, N'Bánh kỷ niệm cho sự kiện kỷ niệm 5 năm kết hôn.', 320000, '2024-02-18', NULL, 1, 'Waiting'),
(18, NULL, N'Bánh kỷ niệm cho ngày kỷ niệm 10 năm làm việc.', 277000, '2024-02-18', NULL, 0, 'Waiting'),
(1, NULL, N'Bánh kỷ niệm cho sự kiện kỷ niệm 5 năm kết hôn.', 430000, '2024-02-19', NULL, 1, 'Waiting'),
(5, NULL, N'Bánh đặc biệt cho lễ tốt nghiệp của Mary Smith.', 1191000, '2024-02-20', NULL, 1, 'Waiting'),
(13, NULL, N'Bánh kỷ niệm cho sự kiện kỷ niệm 15 năm kết hôn.', 300000, '2024-02-20', NULL, 1, 'Waiting'),
(15, NULL, N'Bánh kỷ niệm cho ngày kỷ niệm 1 năm làm việc.', 331000, '2024-02-21', NULL, 0, 'Waiting'),
(19, NULL, N'Bánh kỷ niệm cho sự kiện kỷ niệm tân gia.', 260000, '2024-02-21', NULL, 1, 'Waiting'),
(20, NULL, N'Bánh chúc mừng tân gia.', 334000, '2024-02-22', NULL, 1, 'Waiting');

-- Chèn dữ liệu vào bảng Cakes
INSERT INTO Cakes ([cakeName], [cakeDescription], [cakePrice], [cakeImg], [cakeQuantity], [cakeType])
VALUES 
 ('Chocolate Cake', N'A delicious and moist chocolate cake with rich cocoa flavor, perfect for chocolate lovers.', 250000, 'Image/Cake/chocolate_cake.jpg', 20, 'Sponge Cake'),
    ('Vanilla Cake', N'A classic vanilla cake with a hint of sweetness, light and fluffy texture, and a delicious vanilla aroma.', 300000, 'Image/Cake/vanilla_cake.jpg', 15, 'Sponge Cake'),
    ('Strawberry Cake', N'A delightful strawberry cake bursting with fresh strawberries, moist and flavorful, a treat for strawberry enthusiasts.', 225000, 'Image/Cake/strawberry_cake.jpg', 18, 'Layer Cake'),
    ('Red Velvet Cake', N'A visually stunning red velvet cake with a velvety texture and a rich cocoa flavor, topped with smooth cream cheese frosting.', 270000, 'Image/Cake/red_velvet_cake.jpg', 25, 'Layer Cake'),
    ('Carrot Cake', N'A moist and flavorful carrot cake packed with grated carrots, warm spices, and a creamy frosting, a delightful dessert option.', 320000, 'Image/Cake/carrot_cake.jpg', 22, 'Bundt Cake'),
    ('Cheesecake', N'A luscious and creamy cheesecake with a buttery graham cracker crust, smooth cream cheese filling, and a hint of vanilla, a decadent treat.', 400000, 'Image/Cake/cheesecake.jpg', 28, 'Cheesecake'),
    ('Lemon Cake', N'A refreshing lemon cake with a tangy lemon flavor, moist crumb, and a zesty lemon glaze, perfect for citrus lovers.', 275000, 'Image/Cake/lemon_cake.jpg', 17, 'Sponge Cake'),
    ('Coconut Cake', N'A tropical delight, this coconut cake is infused with coconut flavor, moist and tender, and topped with shredded coconut for added texture.', 310000, 'Image/Cake/coconut_cake.jpg', 20, 'Layer Cake'),
    ('Banana Cake', N'A moist and flavorful banana cake made with ripe bananas, warm spices, and a hint of cinnamon, a comforting and satisfying dessert.', 225000, 'Image/Cake/banana_cake.jpg', 19, 'Bundt Cake'),
    ('Pineapple Cake', N'A delightful pineapple cake with juicy pineapple pieces, moist and fluffy texture, and a sweet pineapple glaze, a taste of the tropics.', 315000, 'Image/Cake/pineapple_cake.jpg', 16, 'Layer Cake');


-- Chèn dữ liệu vào bảng Ratings
INSERT INTO Ratings ([userID], [cakeID], [ratingDate], [ratingValue], [comment])
VALUES 
    (1, 1, '2024-01-01', 5, 'Delicious!'),
    (2, 2, '2024-01-02', 4, 'Great taste!'),
    (3, 3, '2024-01-03', 3, 'Average.'),
    (4, 4, '2024-01-04', 5, 'Fantastic!'),
    (5, 5, '2024-01-05', 2, 'Could be better.'),
    (6, 6, '2024-01-06', 4, 'Really good.'),
    (7, 7, '2024-01-07', 5, 'Excellent!'),
    (8, 8, '2024-01-08', 3, 'Not bad.'),
    (9, 9, '2024-01-09', 4, 'Impressive.'),
    (10, 10, '2024-01-10', 5, 'Highly recommended!'),
    (1, 7, '2024-01-11', 5, 'Delicious cake with amazing flavors and perfect texture!'),
    (6, 3, '2024-01-12', 4, 'The taste is wonderful, it melts in your mouth.'),
    (5, 10, '2024-01-13', 3, 'Not bad, but could be improved.'),
    (5, 7, '2024-01-14', 5, 'Absolutely fantastic, exceeded my expectations!'),
    (4, 2, '2024-01-15', 2, 'It''s okay, but I expected more.'),
    (4, 6, '2024-01-16', 4, 'Really good cake, I enjoyed every bite.'),
    (9, 2, '2024-01-17', 5, 'Excellent cake, definitely worth trying!'),
    (6, 9, '2024-01-18', 3, 'Decent cake, but nothing special.'),
    (1, 8, '2024-01-19', 4, 'Impressive taste and presentation, highly recommend!'),
    (1, 5, '2024-01-20', 5, 'Highly recommended, one of the best cakes I''ve ever had!'),
    (4, 9, '2024-01-21', 5, 'Delicious and moist, loved every bite!'),
    (2, 7, '2024-01-22', 4, 'Great taste, perfect for any occasion.'),
    (1, 10, '2024-01-23', 3, 'Average cake, nothing too special.'),
    (2, 4, '2024-01-24', 5, 'Fantastic cake, exceeded my expectations!'),
    (4, 7, '2024-01-25', 2, 'Could be better, but still enjoyable.'),
    (2, 8, '2024-01-26', 4, 'Really good cake, would definitely order again.'),
    (6, 2, '2024-01-27', 5, 'Excellent taste and presentation, highly recommend!'),
    (4, 5, '2024-01-28', 3, 'Not bad, but I''ve had better.'),
    (7, 3, '2024-01-29', 4, 'Impressive cake, worth every penny!'),
    (10, 3, '2024-01-30', 5, 'Highly recommended, you won''t be disappointed!'),
    (2, 3, '2024-01-31', 5, 'Absolutely delicious, a must-try!'),
    (8, 2, '2024-02-01', 4, 'Great taste and texture, loved it!'),
    (4, 1, '2024-02-02', 3, 'Average cake, nothing too special.'),
    (2, 1, '2024-02-03', 5, 'Fantastic cake, couldn''t ask for more!'),
    (7, 2, '2024-02-04', 2, 'Disappointing, expected better quality.'),
    (8, 1, '2024-02-05', 4, 'Really enjoyed this cake, perfect for any occasion.'),
    (6, 4, '2024-02-06', 5, 'Excellent taste and presentation, highly recommend!'),
    (5, 6, '2024-02-07', 3, 'Decent cake, but I''ve had better.'),
    (1, 2, '2024-02-08', 4, 'Impressive cake, would definitely order again!'),
    (8, 4, '2024-02-09', 5, 'Highly recommended, one of the best cakes I''ve ever had!'),
    (7, 4, '2024-02-10', 5, 'Delicious cake with amazing flavors and perfect texture!'),
    (4, 10, '2024-02-11', 4, 'Great taste, perfect for any occasion.'),
    (5, 2, '2024-02-12', 3, 'Average cake, nothing too special.'),
    (2, 5, '2024-02-13', 5, 'Absolutely fantastic, exceeded my expectations!'),
    (11, 2, '2024-02-14', 1, 'Extremely disappointed, not worth the price.'),
    (12, 2, '2024-02-15', 1, 'Terrible taste, would not recommend.'),
    (13, 2, '2024-02-16', 1, 'Very poor quality, not what I expected.'),
    (14, 2, '2024-02-17', 1, 'Awful cake, tasted stale and dry.'),
    (15, 2, '2024-02-18', 1, 'Disgusting, couldn''t finish it.'),
    (16, 2, '2024-02-19', 1, 'Worst cake I''ve ever had, wouldn''t recommend to anyone.'),
    (17, 2, '2024-02-20', 1, 'Absolutely dreadful, waste of money.'),
    (18, 2, '2024-02-21', 1, 'Horrible taste, regret ordering it.'),
    (19, 2, '2024-02-22', 1, 'Complete disaster, never ordering from here again.'),
    (20, 2, '2024-02-23', 1, 'Disappointing, wouldn''t recommend to anyone.');

-- Chèn dữ liệu vào bảng CakeInOrder
INSERT INTO CakeInOrder ([cioQuantity], [orderID], [cakeID])
VALUES 
    (2, 1, 1),
    (1, 2, 3),
    (3, 3, 5),
    (2, 4, 7),
    (1, 5, 8),
    (2, 6, 9),
    (3, 7, 7),
    (2, 8, 8),
    (1, 9, 9),
    (3, 10, 10),
    (1, 11, 4),
    (1, 12, 6),
    (1, 13, 5),
    (1, 14, 2),
    (1, 15, 7),
    (1, 16, 4),
    (1, 17, 3),
    (1, 18, 2),
    (1, 19, 7),
    (1, 20, 1),
    (1, 21, 9),
    (1, 22, 8),
    (1, 23, 3),
    (1, 24, 2),
    (1, 25, 10),
    (1, 26, 5),
    (1, 27, 2),
    (1, 28, 4),
    (1, 29, 6),
    (1, 30, 7),
    (1, 30, 1),
    (1, 30, 9),
    (1, 30, 8),
    (1, 31, 7),
    (1, 32, 10),
    (1, 33, 1),
    (1, 34, 5);

-- Chèn dữ liệu vào bảng Toppings
INSERT INTO Toppings ([toppingName], [toppingQuantity], [toppingPrice], [toppingImg], [toppingDescription])
VALUES 
    ('Chocolate Chips', 50, 10000, 'Image/Topping/chocolate_chips.jpg', 'Crunchy chocolate chips'),
    ('Sprinkles', 30, 10000, 'Image/Topping/sprinkles.jpg', 'Colorful sprinkles'),
    ('Nuts', 40, 8000, 'Image/Topping/nuts.jpg', 'Crushed nuts'),
    ('Whipped Cream', 20, 20000, 'Image/Topping/whipped_cream.jpg', 'Fluffy whipped cream'),
    ('Caramel Sauce', 25, 14000, 'Image/Topping/caramel_sauce.jpg', 'Smooth caramel sauce'),
    ('Fruit Slices', 35, 7000, 'Image/Topping/fruit_slices.jpg', 'Fresh fruit slices'),
    ('Marshmallows', 30, 25000, 'Image/Topping/marshmallows.jpg', 'Soft marshmallows'),
    ('Coconut Flakes', 28, 28000, 'Image/Topping/coconut_flakes.jpg', 'Crunchy coconut flakes'),
    ('Jelly Beans', 18, 18000, 'Image/Topping/jelly_beans.jpg', 'Colorful jelly beans'),
    ('Gummy Bears', 8, 8000, 'Image/Topping/gummy_bears.jpg', 'Chewy gummy bears');

-- Chèn dữ liệu vào bảng GoWiths
INSERT INTO GoWiths ([cakeID], [toppingID])
VALUES 
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 6),
    (7, 7),
    (8, 8),
    (9, 9),
    (10, 10),
    (1, 6),  
    (2, 4),  
    (3, 7),   
    (4, 3), 
    (5, 2), 
    (6, 1), 
    (7, 10), 
    (8, 5),   
    (9, 8), 
    (10, 9),
    (1, 8),   
    (2, 1),   
    (3, 5),    
    (4, 10),   
    (5, 6),  
    (6, 9), 
    (7, 2),    
    (8, 3),  
    (9, 4), 
    (10, 7),
    (1, 9),   
    (2, 10),   
    (3, 1),    
    (4, 6),  
    (5, 7),   
    (6, 8), 
    (7, 3),  
    (8, 4),
    (9, 5),
    (10, 2);

-- Chèn dữ liệu vào bảng ToppingInCake
INSERT INTO ToppingInCake ([cioID], [toppingID], [ticQuantity])
VALUES 
    (1, 1, 2),
    (2, 3, 1),
    (3, 2, 3),
    (4, 4, 7),
    (5, 5, 5),
    (6, 6, 4),
    (7, 10, 3),
    (8, 8, 2),
    (9, 5, 4),
    (10, 2, 3),
    (10, 7, 1),
    (10, 9, 2),
    (10, 10, 1),
    (11, 6, 2),
    (12, 1, 5),
    (13, 7, 2),
    (14, 4, 2),
    (15, 3, 1),
    (16, 6, 3),
    (17, 5, 2),
    (18, 2, 1),
    (19, 3, 2),
    (20, 9, 1),
    (21, 8, 3),
    (22, 5, 1),
    (23, 3, 4),
    (24, 2, 1),
    (25, 10, 2),
    (26, 6, 2),
    (27, 4, 1),
    (28, 6, 1),
    (29, 1, 3),
    (30, 3, 4),
    (31, 8, 1),
    (32, 5, 2),
    (33, 3, 1),
    (34, 7, 1),
    (35, 10, 2),
    (36, 1, 1),
    (37, 5, 1);

-- Chèn dữ liệu vào bảng ProductHistory
INSERT INTO ProductHistory ([phQuantity], [updateDate], [createDate], [updateBy], [createBy], [toppingID], [cakeID])
VALUES 
    (50, '2024-01-01', NULL, 1, NULL, 1, NULL),
    (40, NULL, '2024-01-02', NULL, 2, NULL, 1),
    (30, NULL, '2024-01-03', NULL, 3, NULL, 3),
    (20, NULL, '2024-01-04', NULL, 4, 4, NULL),
    (15, '2024-01-05', NULL, 5, NULL, NULL, 5),
    (25, NULL, '2024-01-06', NULL, 6, 6, NULL),
    (35, NULL, '2024-01-07', NULL, 7, NULL, 7),
    (45, '2024-01-08', NULL, 8, NULL, 8, NULL),
    (55, NULL, '2024-01-09', NULL, 9, 9, NULL),
    (60, '2024-01-10', NULL, 10, NULL, NULL, 10);



