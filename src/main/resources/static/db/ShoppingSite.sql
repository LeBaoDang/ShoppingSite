use master 
go
Create database ShoppingSite;
go
use ShoppingSite;
go
Create table Categories(
	[Id] [char](4) Primary key NOT NULL,
	[Name] [nvarchar](50) NOT NULL
)
go
Create table Products(
	[Id] [int] IDENTITY(1,1) Primary key NOT NULL,
	[Name] [nvarchar](255) NOT NULL,
	[Image] [nvarchar](50) NOT NULL,
	[Price] [float] NOT NULL,
	[CreateDate] [date] NOT NULL,
	[Available] [bit] NOT NULL,
	[CategoryId] [char](4) NOT NULL,
	[Description] [nvarchar](500),
	CONSTRAINT fk_products_categoryid_categories
	FOREIGN KEY (CategoryId) 
	REFERENCES Categories(Id)
)
Create table roles(
	[Id] [nvarchar](10) Primary key NOT NULL,
	[Name] [nvarchar](50) NOT NULL
)
go
Create table Accounts(
	[Username] [nvarchar](50) Primary key NOT NULL,
	[Password] [nvarchar](255) NOT NULL,
	[Fullname] [nvarchar](50) NOT NULL,
	[Email] [nvarchar](50) NOT NULL,
	[Photo] [nvarchar](50)
)
go
Create table Authorities(
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Username] [nvarchar](50) NOT NULL,
	[RoleId] [nvarchar](10) NOT NULL,
	CONSTRAINT fk_authorities_username_accounts
	FOREIGN KEY (Username) 
	REFERENCES Accounts(Username),
	CONSTRAINT fk_authorities_roleid_roles
	FOREIGN KEY (RoleId) 
	REFERENCES roles(Id),
)
go
Create table Orders (
	[Id] [bigint] IDENTITY(1,1) Primary key NOT NULL,
	[Username] [nvarchar](50) NOT NULL,
	[CreateDate] [datetime] NOT NULL,
	[Address] [nvarchar](100) NOT NULL,
	CONSTRAINT fk_orders_username_accounts
	FOREIGN KEY (Username) 
	REFERENCES Accounts(Username)
)
go
Create table Orderdetails(
	[Id] [bigint] IDENTITY(1,1) Primary key NOT NULL,
	[ProductId] [int] NOT NULL,
	[OrderId] [bigint] NOT NULL,
	[Price] [float] NOT NULL,
	[Quantity] [int] NOT NULL,
	CONSTRAINT fk_orderdetails_productid_products
	FOREIGN KEY (ProductId) 
	REFERENCES Products(Id),
	CONSTRAINT fk_orderdetails_orderid_orders
	FOREIGN KEY (OrderId) 
	REFERENCES Orders(Id)
)

INSERT INTO Accounts VALUES 
	('Dang','$2a$10$bC3fIu4WyB/FGwlbOPlZt.3IRzkM34vZNt1Kbe5ZDcq7r/XZFWNrO','LeBaoDang','dangletv@gmail.com','1.jpg'),
	('Khanh','$2a$10$bC3fIu4WyB/FGwlbOPlZt.3IRzkM34vZNt1Kbe5ZDcq7r/XZFWNrO','HuynhNhatKhanh','khanh@gmail.com','2.jpg'),
	('Son','$2a$10$bC3fIu4WyB/FGwlbOPlZt.3IRzkM34vZNt1Kbe5ZDcq7r/XZFWNrO','Son','son@gmail.com','2.jpg')

INSERT INTO roles VALUES 
	('CUST',N'Khách hàng'),
	('DIRE',N'Giám đốc'),
	('STAF',N'Nhân viên')

INSERT INTO Authorities VALUES 
	('Dang','DIRE'),
	('Khanh','STAF'),
	('Son','CUST')

INSERT INTO Categories VALUES
	(N'1000', N'Đồng hồ đeo tay'),
	(N'1001', N'Máy tính xách tay'),
	(N'1003', N'Điện thoại'),
	(N'1004', N'Tai nghe')

INSERT INTO Products VALUES 

	/* dong ho deo tay */

	( N'Đồng hồ TITAN 37.4 mm Nam 1595WL03' , '1.jpg' , 1740000 , '2023-05-02' , 0 , '1000' , N'Đồng hồ Titan chủ yếu là phong cách cổ điển nhưng vẫn mang âm hưởng của phong cách hiện đại. Thương hiệu này chú trọng chủ yếu đến chất lượng và mẫu mã đồng hồ khiến người dùng thật sự hài lòng về các model đồng hồ có mặt trên thị trường hiện nay' ),
	( N'Đồng hồ MVW 44.5 mm Nam MS008-01' , '2.jpg' , 888000 , '2023-05-02' , 0 , '1000' , N'Sản phẩm đồng hồ mang thương hiệu MVW với nhiều mẫu mã năng động, trẻ trung nhưng không kém phần tinh tế và sang trọng, phù hợp với tất cả mọi người hoạt động ở nhiều lĩnh vực từ dân văn phòng đến doanh nhân' ),
	( N'Đồng hồ ADRIATICA 24 mm Nữ A3469.9193Q' , '3.jpg' , 1780000 , '2023-05-02' , 0 , '1000' , N'Adriatica thể hiện một khái niệm về chất lượng, độ chính xác, độ tin cậy cao kết hợp với sự thanh lịch và độc đáo trong thiết kế. Một trong những mục tiêu chính của họ là tạo ra các thiết kế và mô hình mới hoàn toàn, phù hợp với sự thay đổi của thị hiếu khách hàng, đồng thời giữ giá cả cạnh tranh. Adriatica hứa hẹn sẽ đem đến cho khách hàng những sản phẩm hoàn hảo, chuẩn mực cùng với thiết kế độc đáo, sang trọng' ),
	( N'Đồng hồ ORIENT 42.4 mm Nam RA-AC0J07S10B' , '4.jpg' , 7154000 , '2023-05-02' , 0 , '1000' , N'Đồng hồ Orient đem đến những sản phẩm ấn tượng chinh phục người nhìn một cách nhanh chóng. Đồng hồ Orient với những chất liệu cao cấp bóng bẩy nâng tầm đẳng cấp cho người sở hữu, phù hợp với doanh nhân thành đạt, dân văn phòng hay các giám đốc công ty. Phong cách thời thượng, sang trọng đầy sức thu hút đến từ đồng hồ Orient chắc chắn sẽ khiến bạn luôn hãnh diện với những người xung quanh' ),
	( N'Đồng hồ CASIO 39.7 mm Nam AE-1200WHD-1AVDF' , '5.jpg' , 1046000 , '2023-05-02' , 0 , '1000' , N'Thương hiệu đồng hồ nổi tiếng đến từ Nhật Bản không ngừng cải tiến và cho ra mắt những dòng sản phẩm chất lượng phù hợp với nhiều đối tượng khách hàng. Những dòng sản phẩm nổi tiếng của Casio là: G-Shock với thiết kế mạnh mẽ cùng độ bền cao, Edifice thiết kế hiện đại cùng nhiều tính năng vượt trội, Sheen với thiết kế cổ điển và sang trọng' ),
	( N'Đồng hồ CITIZEN 40 mm Nam NH8391-51L' , '6.jpg' , 5900000 , '2023-05-02' , 0 , '1000' , N'Xu hướng thiết kế chính của đồng hồ Citizen là đơn giản và thanh lịch. Citizen luôn chú trọng đến việc đổi mới và tạo sự phong phú cho các mẫu thiết kế. Các chi tiết cũng được Citizen đầu tư kỹ lưỡng trong khâu chế tác' ),
	( N'Đồng hồ ORIENT Star 38.7 mm Nam RE-AW0002L00B ' , '7.jpg' , 20859000 , '2023-05-02' , 0 , '1000' , N'Đồng hồ Orient đem đến những sản phẩm ấn tượng chinh phục người nhìn một cách nhanh chóng. Đồng hồ Orient với những chất liệu cao cấp bóng bẩy nâng tầm đẳng cấp cho người sở hữu, phù hợp với doanh nhân thành đạt, dân văn phòng hay các giám đốc công ty. Phong cách thời thượng, sang trọng đầy sức thu hút đến từ đồng hồ Orient chắc chắn sẽ khiến bạn luôn hãnh diện với những người xung quanh' ),
	( N'Đồng hồ ViWat Tinh Hoa 40 mm Nam V9.W1079B ' , '8.jpg' , 1911000 , '2023-05-02' , 0 , '1000' , N'Các sản phẩm của ViWat là sự kết hợp hài hòa giữa bộ máy chất lượng cao, cùng dòng kính và dây đeo cao cấp, thổi vào đó là những thiết kế đầy tính nghệ thuật sáng tạo, đậm đà bản sắc Việt, đem đến cảm giác vừa thân quen vừa thú vị' ),
	( N'Đồng hồ Q&Q 40 mm Nam C08A-013PY' , '9.jpg' , 632000 , '2023-05-02' , 0 , '1000' , N'Q&Q là thương hiệu đồng hồ nổi tiếng Nhật Bản, họ kết hợp 2 đặc điểm trên thông qua việc tạo ra những chiếc đồng hồ chất lượng, đáng tin cậy và sản xuất ra số lượng đồng hồ để phục vụ mọi người trên thế giới. Sứ mệnh của đồng hồ Q&Q là làm phong phú cuộc sống hàng ngày của tất cả mọi người bằng cách cung cấp những bộ sưu tập giá cả phải chăng đến mọi người ở mọi nơi trên thế giới' ),
	( N'Đồng hồ CITIZEN 42 mm Nam NJ0084-59A' , '10.jpg' , 9580000 , '2023-05-02' , 0 , '1000' , N'Xu hướng thiết kế chính của đồng hồ Citizen là đơn giản và thanh lịch. Citizen luôn chú trọng đến việc đổi mới và tạo sự phong phú cho các mẫu thiết kế. Các chi tiết cũng được Citizen đầu tư kỹ lưỡng trong khâu chế tác' ),

	/* May tinh xach tay*/

	( N'Laptop Acer TravelMate B3 TMB311 31 C2HB N4020/4GB/128GB/Win11' , '11.jpg' , 4900000 , '2023-05-02' , 0 , '1001' , N'Acer TravelMate B3 có khối lượng máy chỉ 1.4 kg, độ dày 20.99 mm, thiết kế thanh lịch, đẹp mắt với lớp vỏ nhựa màu đen đơn giản kèm logo hãng tinh tế ở góc trái trên mặt lưng, dễ dàng cùng bạn xuất hiện ở mọi nơi' ),
	( N'Laptop MacBook Pro 14 M1 Pro 2021 8-core CPU/16GB/512GB/14-core GPU' , '12.jpg' , 42990000 , '2023-05-02' , 0 , '1001' , N'MacBook Pro 14 inch mang trong mình bộ vi xử lý M1 Pro được sản xuất bằng tiến trình 5 nm hiện đại, tích hợp 8 lõi CPU với 6 lõi hiệu suất cao và 2 lõi tiết kiệm điện năng, hứa hẹn khả năng xử lý nhanh hơn tới 70% so với CPU của M1, đồng thời giảm điện năng tiêu thụ đáng kể để kéo dài thời lượng pin' ),
	( N'Laptop Apple MacBook Pro 16 M1 Pro 2021 10 core-CPU/16GB/512GB/16 core-GPU' , '13.jpg' , 51000000 , '2023-05-02' , 0 , '1001' , N'MacBook Pro 16 inch sở hữu sức mạnh từ bộ vi xử lý M1 Pro tiên tiến được trang bị 10 nhân CPU (8 nhân hiệu suất cao + 2 nhân tiết kiệm điện) mang đến hiệu suất nhanh hơn tới 70% so với M1, cho khả năng xử lý mượt mà mọi tác vụ từ cơ bản đến khắt khe nhất như chỉnh sửa hình ảnh, dựng video độ phân giải cao,... Không những có thời gian phản hồi nhanh mà còn tối ưu tiết kiệm được đáng kể điện năng' ),
	( N'Laptop HP 15s fq2662TU i3 1115G4/4GB/256GB/Win11' , '14.jpg' , 10000000 , '2023-05-02' , 0 , '1001' , N' Laptop HP cơ bản được bao bọc bởi lớp vỏ nhựa màu bạc, mang đến vẻ đẹp tối giản nhưng cũng không kém phần thanh lịch, tao nhã. Khối lượng 1.7 kg hỗ trợ bạn bỏ vào balo dễ dàng và mang đến khắp mọi nơi để học tập, làm việc' ),
	( N'Laptop Lenovo Ideapad 3 15IAU7 i3 1215U/8GB/256GB/Win11' , '15.jpg' , 10990000 , '2023-05-02' , 0 , '1001' , N'Laptop Lenovo Ideapad giải quyết tốt mọi tác vụ của một chiếc laptop học tập - văn phòng từ cơ bản đến nâng cao nhờ sự kết hợp của bộ vi xử lý Intel Core i3 1215U cùng card đồ họa Intel UHD Graphics' ),
	( N'Laptop Acer Aspire 7 Gaming A715 42G R4XX R5 5500U/8GB/256GB/4GB GTX1650/Win11' , '16.jpg' , 16000000 , '2023-05-02' , 0 , '1001' , N'Laptop AMD Ryzen 5 5500U cùng card rời NVIDIA GeForce GTX 1650 4 GB, mang đến khả năng thiết kế 2D, render video ngắn,... ổn định trên các phần mềm Adobe hay giải trí với các tựa game kịch tính trên thị trường hiện nay một cách mượt mà' ),
	( N'Laptop Asus TUF Gaming A15 FA507RC R7 6800H/8GB/512GB/4GB RTX3050/144Hz/Win11' , '17.jpg' , 20200000 , '2023-05-02' , 0 , '1001' , N'Nói về phân khúc laptop gaming thì Asus TUF luôn có một chỗ đứng nhất định trên thị trường nhờ sự mạnh mẽ đến từ cả ngoại hình lẫn hiệu năng, và chiếc laptop Asus năm nay cũng không ngoại lệ khi được tân trang cho mình bộ vi xử lý AMD Ryzen 7 6800H hiệu năng cao cùng card rời NVIDIA GeForce RTX 3050 4 GB, đáp ứng hoàn hảo cho nhu cầu vừa học tập vừa chiến game mà một sinh viên như mình đang tìm kiếm' ),
	( N'Laptop HP Elitebook 630 G9 i5 1235U/8GB/512GB/Win11' , '18.jpg' , 20000000 , '2023-05-02' , 0 , '1001' , N'Laptop HP Elitebook được trang bị bộ vi xử lý Intel Core i5 1235U sở hữu cấu trúc 10 nhân, 12 luồng tối ưu hiệu năng, hỗ trợ người dùng xử lý nhanh gọn mọi tác vụ văn phòng trên Word, Excel, PowerPoint' ),
	( N'Laptop LG gram 2022 i7 1260P/16GB/512GB/Win11' , '19.jpg' , 42280000 , '2023-05-02' , 0 , '1001' , N'Laptop LG gram 16 thách thức mọi định luật về trọng lực nhờ vào khối lượng nhẹ đến không thể tin được, đánh lừa thị giác ngay từ lần đầu tiên mình lấy máy ra khỏi hộp đựng với cảm giác cầm nắm thực sự nhẹ, như chỉ có một chiếc khung không chứa linh kiện bên trong' ),
	( N'Laptop Dell XPS 13 Plus 9320 i7 1260P/16GB/512GB/Touch/Cáp/OfficeHS/Win11' , '20.jpg' , 53000000 , '2023-05-02' , 0 , '1001' , N'Laptop Dell được chế tạo tinh xảo từ hai chất liệu nhôm và kính trong khi vẫn giữ được sự chắc chắn vốn có của một thiết bị cao cấp. Ở trạng thái đóng, bạn sẽ không thể nhận ra được chiếc XPS mới so với những thế hệ cũ. Nhưng khi mở nắp máy lên, ngôn ngữ thiết kế độc lạ, hiện đại đến từ khu vực bàn phím và bàn di chuột sẽ khiến bạn thực sự mê mẩn' ),

	/* tai nghe */

	( N'Tai nghe Bluetooth AirPods Pro (2nd Gen) MagSafe Charge Apple MQD83' , '21.jpg' , 6500000 , '2023-05-02' , 0 , '1004' , N'Về phần thiết kế, nhà Apple vẫn giữ nguyên kiểu dáng quen thuộc của những phiên bản tiền nhiệm trước đó như: Thiết kế gọn nhẹ, đường bo góc tinh tế, gam màu trắng trang nhã bao bọc trọn vẹn tai nghe và hộp sạc' ),
	( N'Tai nghe Bluetooth AirPods 2 Lightning Charge Apple MV7N2' , '22.jpg' , 3100000 , '2023-05-02' , 0 , '1004' , N'Đầu tiên, nhắc đến điểm cải tiến đáng "ăn tiền" nhất trên chiếc tai nghe này chính là AirPods 2 được tích hợp chip xử lý H1 thế hệ mới cho thời gian chuyển đổi kết nối giữa các thiết bị nhanh hơn gấp 2 lần so với phiên bản cũ' ),
	( N'Tai nghe Bluetooth AirPods Pro MagSafe Charge Apple MLWK3' , '23.jpg' , 4500000 , '2023-05-02' , 0 , '1004' , N'Tai nghe Bluetooth AirPods Pro MagSafe Charge Apple MLWK3 trắng được chế tác với vẻ ngoài tinh giản, gam màu trắng trẻ trung, sáng đẹp, phối hợp tuyệt vời với mọi trang phục từ đời thường đến công sở, dự tiệc của bạn' ),
	( N'Tai nghe Bluetooth AirPods 3 Lightning Charge Apple MPNY3' , '24.jpg' , 4100000 , '2023-05-02' , 0 , '1004' , N'Tích hợp các công nghệ âm thanh tiên tiến độc quyền nhà Apple như: Adaptive EQ, Chip Apple H1, Spatial Audio giúp tai nghe tự động hiệu chỉnh và tối ưu âm thanh phát ra đem đến trải nghiệm tuyệt vời cho người dùng' ),
	( N'Tai nghe Bluetooth True Wireless OPPO ENCO Air 2 Pro ETE21' , '25.jpg' , 1900000 , '2023-05-02' , 0 , '1004' , N'Thay vì thiết kế dạng nắp hộp trong suốt như trước kia, chiếc tai nghe Bluetooth TWS OPPO ENCO Air 2 Pro ETE21 này mang đến thiết kế hộp đựng được bao kín hoàn toàn tạo cảm giác chắc chắn cho hộp đựng. Các chi tiết thiết kế như bản lề, đường bo góc mình nhận thấy chiếc ENCO Air 2 Pro này vẫn được chăm chút tỉ mỉ' ),
	( N'Tai nghe Bluetooth True Wireless Realme Buds T100' , '26.jpg' , 890000 , '2023-05-02' , 0 , '1004' , N'Thiết kế hộp sạc hiện đại, đẹp mắt với gam màu thời thượng, kiểu dáng tối giản. Phần bản lề được chế tác chỉn chu, chắc chắn cho thao tác đóng mở hộp đựng dễ dàng' ),
	( N'Tai nghe Bluetooth True Wireless Rezo F153' , '27.jpg' , 390000 , '2023-05-02' , 0 , '1004' , N'Thiết kế housing tinh xảo, đồng màu với hộp sạc trong 2 phiên bản là màu hồng ngọt ngào và màu trắng trang nhã, phù hợp với cả phái mạnh và phái đẹp. Housing được chế tác với tay cầm dài cho bạn dễ cầm và đeo vào tai, hạn chế trượt tay, rơi rớt khi thao tác' ),
	( N'Tai nghe Bluetooth True Wireless AVA+ DS201A-WB' , '28.jpg' , 345000 , '2023-05-02' , 0 , '1004' , N'Housing (phần tai nghe) được chế tác với kiểu dáng nhỏ nhắn, gọn đẹp, cầm nắm và đeo tháo linh hoạt chỉ bằng 2 đầu ngón tay, nút tai mềm nhẹ, đeo vừa vặn và thoải mái, không tạo cảm giác cấn, đau trong suốt quá trình sử dụng' ),
	( N'Tai nghe Bluetooth True Wireless Mozard Air 3' , '29.jpg' , 445000 , '2023-05-02' , 0 , '1004' , N'Bề mặt của hộp đựng tai nghe Bluetooth True Wireless Mozard Air 3 đen được làm hơi nhám giúp dễ dàng cầm nắm khi tay dính nước hay đổ mồ hôi. Hộp đựng có kích thước vừa phải có thể dễ dàng mang theo khi tập thể dục, đi cà phê, du lịch' ),
	( N'Tai nghe Bluetooth True Wireless Mozard Q8' , '30.jpg' , 359000 , '2023-05-02' , 0 , '1004' , N'Thiết kế hộp sạc nhỏ gọn với dáng hộp bo tròn mềm mại, chất liệu nhựa bóng trong suốt ở mặt chính giúp nổi bật sản phẩm ở bên trong, rất tiện lợi để bạn bảo quản và mang theo' ),

	/* dien thoai */

	( N'Điện thoại Samsung Galaxy Z Flip4 128GB' , '31.jpg' , 18490000 , '2023-05-02' , 0 , '1003' , N'Có lẽ điện thoại gập đã không còn là cái tên quá xa lạ bởi nhiều ông lớn trong ngành sản xuất thiết bị di động đã cho ra mắt khá nhiều sản phẩm có thiết kế tương tự, gần đây nhất thì có sự góp mặt của chiếc flagship đến từ nhà Samsung mang tên Galaxy Z Flip4' ),
	( N'Điện thoại iPhone 11 64GB' , '32.jpg' , 11490000 , '2023-05-02' , 0 , '1003' , N'Apple đã chính thức trình làng bộ 3 siêu phẩm iPhone 11, trong đó phiên bản iPhone 11 64GB có mức giá rẻ nhất nhưng vẫn được nâng cấp mạnh mẽ như iPhone Xr ra mắt trước đó' ),
	( N'Điện thoại Xiaomi Redmi Note 11 (4GB/64GB)B' , '33.jpg' , 4900000 , '2023-05-02' , 0 , '1003' , N'Điện thoại Redmi được mệnh danh là dòng sản phẩm quốc dân ngon - bổ  - rẻ của Xiaomi và Redmi Note 11 (4GB/64GB) cũng không phải ngoại lệ, máy sở hữu một hiệu năng ổn định, màn hình 90 Hz mượt mà, cụm camera AI đến 50 MP cùng một mức giá vô cùng tốt' ),
	( N'Điện thoại Samsung Galaxy S22 Ultra 5G 128GB' , '34.jpg' , 23000000 , '2023-05-02' , 0 , '1003' , N'Galaxy S22 Ultra 5G chiếc smartphone cao cấp nhất trong bộ 3 Galaxy S22 series mà Samsung đã cho ra mắt. Tích hợp bút S Pen hoàn hảo trong thân máy, trang bị vi xử lý mạnh mẽ cho các tác vụ sử dụng vô cùng mượt mà và nổi bật hơn với cụm camera không viền độc đáo mang đậm dấu ấn riêng' ),
	( N'Điện thoại Xiaomi Redmi Note 11S 5G' , '35.jpg' , 5600000 , '2023-05-02' , 0 , '1003' , N'Điện thoại Redmi Note 11S 5G có ngoại hình bắt mắt với 3 màu sắc thời thượng gồm: Trắng, xám, xanh dương. Viền màn hình hai bên của máy khá mỏng cùng với đó là cạnh viền bo cong theo lòng bàn tay, tạo cảm giác cầm nắm thoải mái hơn' ),
	( N'Điện thoại iPhone 13 Pro Max 1TB' , '36.jpg' , 34500000 , '2023-05-02' , 0 , '1003' , N'iPhone 13 Pro Max 1 TB thuộc phân khúc điện thoại cao cấp mà không một iFan nào có thể bỏ qua, với màn hình lớn sắc nét, cấu hình vượt trội, dung lượng lưu trữ "khủng", thời gian sử dụng dài, mỗi lần trải nghiệm đều cho bạn cảm giác thỏa mãn đáng ngạc nhiên' ),
	( N'Điện thoại Samsung Galaxy A23 4GB ' , '37.jpg' , 5200000 , '2023-05-02' , 0 , '1003' , N'Samsung Galaxy A23 4GB sở hữu bộ thông số kỹ thuật khá ấn tượng trong phân khúc, máy có một hiệu năng ổn định, cụm 4 camera thông minh cùng một diện mạo trẻ trung phù hợp cho mọi đối tượng người dùng' ),
	( N'Điện thoại Vivo Y15s' , '38.jpg' , 2700000 , '2023-05-02' , 0 , '1003' , N'Vivo vừa mang một chiến binh mới đến phân khúc tầm trung giá rẻ có tên Vivo Y15s, một sản phẩm sở hữu khá nhiều ưu điểm như thiết kế đẹp, màn hình lớn, camera kép, pin cực trâu và còn rất nhiều điều thú vị khác đang chờ đón bạn' ),
	( N'Điện thoại OPPO Reno8 Pro 5G' , '39.jpg' , 1700000 , '2023-05-02' , 0 , '1003' , N'OPPO Reno8 Pro 5G là chiếc điện thoại cao cấp được nhà OPPO ra mắt vào thời điểm 09/2022, máy hướng đến sự hoàn thiện cao cấp ở phần thiết kế cùng khả năng quay chụp chuyên nghiệp nhờ trang bị vi xử lý hình ảnh MariSilicon X chuyên dụng' ),
	( N'Điện thoại OPPO A55' , '40.jpg' , 3950000 , '2023-05-02' , 0 , '1003' , N'OPPO A55 4G có 3 phiên bản màu độc đáo là xanh lá, xanh dương và màu đen. Thiết kế cong 3D cùng kích thước mỏng nhẹ, OPPO A55 4G vừa vặn trong tay người cầm, cho từng thao tác trải nghiệm thoải mái và chắc chắn' )
