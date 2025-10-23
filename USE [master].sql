USE [master]
GO

CREATE DATABASE [Fitness]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Fitness', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.DBMS_ZUZU\MSSQL\DATA\Fitness.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Fitness_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.DBMS_ZUZU\MSSQL\DATA\Fitness_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO

ALTER DATABASE [Fitness] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Fitness].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Fitness] SET ANSI_NULL_DEFAULT OFF 
GO
-- ... (Các lệnh ALTER DATABASE khác) ...
ALTER DATABASE [Fitness] SET QUERY_STORE = ON
GO
ALTER DATABASE [Fitness] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO

-- Sử dụng database Fitness để tạo bảng
USE [Fitness]
GO

-- Tạo các bảng
CREATE TABLE [dbo].[activity](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[met_value] [float] NULL,
	[name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED ([id] ASC)
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[activity_log](
	[logid] [int] IDENTITY(1,1) NOT NULL,
	[date_log] [datetime2](6) NULL,
	[duration] [float] NULL,
	[activity_id] [int] NULL,
	[user_id] [int] NULL,
PRIMARY KEY CLUSTERED ([logid] ASC)
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[blog](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[approved] [bit] NULL,
	[content] [varchar](255) NULL,
	[date_created] [datetime2](6) NULL,
	[title] [varchar](255) NULL,
	[user_id] [int] NULL,
PRIMARY KEY CLUSTERED ([id] ASC)
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[diet](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[carb_percent] [int] NULL,
	[fat_percent] [int] NULL,
	[name] [varchar](255) NULL,
	[protein_percent] [int] NULL,
PRIMARY KEY CLUSTERED ([id] ASC)
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[dish](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[calories] [float] NULL,
	[carb] [float] NULL,
	[des] [varchar](255) NULL,
	[fat] [float] NULL,
	[fiber] [float] NULL,
	[food_picture] [varchar](255) NULL,
	[name] [varchar](255) NULL,
	[protein] [float] NULL,
	[unit] [smallint] NULL,
PRIMARY KEY CLUSTERED ([id] ASC)
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[food_log](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[log_time] [datetime2](6) NULL,
	[meal] [smallint] NULL,
	[user_id] [int] NULL,
PRIMARY KEY CLUSTERED ([id] ASC)
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[meal_detail](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[food_intake] [float] NULL,
	[dish_id] [int] NULL,
	[food_log_id] [int] NULL,
PRIMARY KEY CLUSTERED ([id] ASC)
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[role](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED ([id] ASC)
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[training_mode](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[des] [varchar](255) NULL,
	[duration] [int] NULL,
	[name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED ([id] ASC)
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[user_diet](
	[carb_percent] [int] NULL,
	[fat_percent] [int] NULL,
	[protein_percent] [int] NULL,
	[diet_id] [int] NOT NULL,
	[user_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED ([diet_id] ASC, [user_id] ASC)
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[user_workout](
	[user_id] [int] NOT NULL,
	[work_id] [int] NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[users](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[age] [float] NULL,
	[bmi] [float] NULL,
	[bmr] [float] NULL,
	[email] [varchar](255) NULL,
	[gender] [varchar](255) NULL,
	[goal] [float] NULL,
	[height] [float] NULL,
	[level_activity] [float] NULL,
	[name] [varchar](255) NULL,
	[password] [varchar](255) NULL,
	[phone_number] [bigint] NULL,
	[tdee] [float] NULL,
	[weight] [float] NULL,
	[role_id] [int] NULL,
PRIMARY KEY CLUSTERED ([id] ASC)
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[workout](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[calories_burned] [float] NULL,
	[duration] [float] NULL,
	[link_video] [varchar](255) NULL,
	[name] [varchar](255) NULL,
	[training_mode_id] [int] NULL,
PRIMARY KEY CLUSTERED ([id] ASC)
) ON [PRIMARY]
GO

-- Tạo các ràng buộc khóa ngoại (Foreign Keys)
ALTER TABLE [dbo].[activity_log]  WITH CHECK ADD  CONSTRAINT [FK_activity_log_activity] FOREIGN KEY([activity_id]) REFERENCES [dbo].[activity] ([id])
GO
ALTER TABLE [dbo].[activity_log]  WITH CHECK ADD  CONSTRAINT [FK_activity_log_users] FOREIGN KEY([user_id]) REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[blog]  WITH CHECK ADD  CONSTRAINT [FK_blog_users] FOREIGN KEY([user_id]) REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[food_log]  WITH CHECK ADD  CONSTRAINT [FK_food_log_users] FOREIGN KEY([user_id]) REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[meal_detail]  WITH CHECK ADD  CONSTRAINT [FK_meal_detail_dish] FOREIGN KEY([dish_id]) REFERENCES [dbo].[dish] ([id])
GO
ALTER TABLE [dbo].[meal_detail]  WITH CHECK ADD  CONSTRAINT [FK_meal_detail_food_log] FOREIGN KEY([food_log_id]) REFERENCES [dbo].[food_log] ([id])
GO
ALTER TABLE [dbo].[user_diet]  WITH CHECK ADD  CONSTRAINT [FK_user_diet_diet] FOREIGN KEY([diet_id]) REFERENCES [dbo].[diet] ([id])
GO
ALTER TABLE [dbo].[user_diet]  WITH CHECK ADD  CONSTRAINT [FK_user_diet_users] FOREIGN KEY([user_id]) REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[user_workout]  WITH CHECK ADD  CONSTRAINT [FK_user_workout_users] FOREIGN KEY([user_id]) REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[user_workout]  WITH CHECK ADD  CONSTRAINT [FK_user_workout_workout] FOREIGN KEY([work_id]) REFERENCES [dbo].[workout] ([id])
GO
ALTER TABLE [dbo].[users]  WITH CHECK ADD  CONSTRAINT [FK_users_role] FOREIGN KEY([role_id]) REFERENCES [dbo].[role] ([id])
GO
ALTER TABLE [dbo].[workout]  WITH CHECK ADD  CONSTRAINT [FK_workout_training_mode] FOREIGN KEY([training_mode_id]) REFERENCES [dbo].[training_mode] ([id])
GO

-- Sử dụng database Fitness
GO

--------------------------------------------------------------------------------
-- Bảng độc lập (Không có khóa ngoại)
--------------------------------------------------------------------------------

-- 1. Chèn thêm 50 bản ghi cho [dbo].[role] (Bắt đầu từ ID hiện có)
INSERT INTO [dbo].[role] ([name]) VALUES
('TRAINER'), ('NUTRITIONIST'), ('MODERATOR'), ('PREMIUM_USER'),
('TRIAL_USER'), ('GUEST_WRITER'), ('PHYSIOTHERAPIST'), ('HEALTH_COACH'),
('GYM_OWNER'), ('CONTENT_MANAGER'), ('SUPPORT_STAFF'), ('ANALYST'),
('RESEARCHER'), ('EDITOR'), ('MARKETING_LEAD'), ('COMMUNITY_MANAGER'),
('EVENT_COORDINATOR'), ('PARTNER'), ('INFLUENCER'), ('BETA_TESTER'),
('Role 21'), ('Role 22'), ('Role 23'), ('Role 24'), ('Role 25'),
('Role 26'), ('Role 27'), ('Role 28'), ('Role 29'), ('Role 30'),
('Role 31'), ('Role 32'), ('Role 33'), ('Role 34'), ('Role 35'),
('Role 36'), ('Role 37'), ('Role 38'), ('Role 39'), ('Role 40'),
('Role 41'), ('Role 42'), ('Role 43'), ('Role 44'), ('Role 45'),
('Role 46'), ('Role 47'), ('Role 48'), ('Role 49'), ('Role 50');
GO

-- 2. Chèn thêm 50 bản ghi cho [dbo].[activity]
INSERT INTO [dbo].[activity] ([met_value], [name]) VALUES
(3.8, N'Tập Thái Cực Quyền'), (5.0, N'Cưỡi ngựa'), (9.0, N'Nhảy dây nhanh'), (8.0, N'Nhảy dây vừa phải'),
(12.0, N'Quyền Anh (đấu tập)'), (7.5, N'Judo, Karate'), (7.0, N'Leo núi trong nhà'), (8.0, N'Leo núi ngoài trời'),
(6.0, N'Đi bộ đường dài (Hiking)'), (3.5, N'Chèo thuyền Kayak'), (5.0, N'Lướt sóng'), (3.5, N'Trượt patin'),
(7.0, N'Trượt băng'), (5.0, N'Chơi Golf (đi bộ)'), (4.0, N'Bowling'), (3.0, N'Dọn dẹp nhà cửa'),
(4.5, N'Làm vườn'), (2.5, N'Nấu ăn'), (5.5, N'Kéo co'), (8.0, N'Bóng nước'),
(11.0, N'Lặn biển'), (6.0, N'Trượt tuyết'), (4.5, N'Chuyển nhà'), (5.0, N'Sơn tường'),
(2.5, N'Yoga phục hồi'), (4.0, N'Yoga Ashtanga'), (3.0, N'Pilates nâng cao'), (7.8, N'Zumba'),
(6.8, N'Aerobics dưới nước'), (8.5, N'Tập Crossfit'), (5.0, N'Đánh bóng quần (Squash)'), (6.5, N'Chèo thuyền'),
(4.0, N'Khiêu vũ (Salsa)'), (5.5, N'Khiêu vũ (Hip Hop)'), (3.0, N'Stretching'), (7.0, N'Tập với tạ chuông (Kettlebell)'),
(5.0, N'Tập với bóng (Medicine Ball)'), (4.0, N'Tập với dây kháng lực'), (6.0, N'Chạy lên cầu thang'), (3.5, N'Đi dạo với chó'),
(2.5, N'Chơi Bi-a'), (4.0, N'Đánh trống'), (8.0, N'Bóng bầu dục'), (6.5, N'Bóng ném'),
(5.0, N'Ném đĩa Frisbee'), (7.0, N'Chèo thuyền ván đứng (SUP)'), (3.0, N'Câu cá'), (4.0, N'Lái xe mô tô địa hình'),
(5.5, N'Đấu vật'), (6.0, N'Chặt củi');
GO

-- 3. Chèn thêm 50 bản ghi cho [dbo].[diet]
INSERT INTO [dbo].[diet] ([carb_percent], [fat_percent], [name], [protein_percent]) VALUES
(55, 25, N'Mediterranean Diet', 20), (25, 60, N'Atkins Diet', 15), (60, 20, N'Vegan Diet', 20),
(50, 30, N'Vegetarian Diet', 20), (45, 35, N'Zone Diet', 20), (40, 40, N'Paleo Diet', 20),
(30, 40, N'South Beach Diet', 30), (50, 25, N'DASH Diet', 25), (60, 15, N'Ornish Diet', 25),
(40, 30, N'Flexitarian Diet', 30), (35, 35, N'TLC Diet', 30), (20, 55, N'Carnivore Diet', 25),
(50, 20, N'Nutritarian Diet', 30), (45, 30, N'Macrobiotic Diet', 25), (30, 50, N'Whole30 Diet', 20),
(55, 20, N'High-Carb Diet', 25), (25, 45, N'High-Fat Diet', 30), (40, 20, N'High-Protein Diet', 40),
(33, 33, N'Even Split Diet 1', 34), (34, 33, N'Even Split Diet 2', 33), (50, 30, N'Diet Plan 21', 20),
(40, 40, N'Diet Plan 22', 20), (30, 30, N'Diet Plan 23', 40), (55, 25, N'Diet Plan 24', 20),
(45, 35, N'Diet Plan 25', 20), (25, 50, N'Diet Plan 26', 25), (60, 15, N'Diet Plan 27', 25),
(50, 20, N'Diet Plan 28', 30), (40, 35, N'Diet Plan 29', 25), (35, 45, N'Diet Plan 30', 20),
(52, 28, N'Diet Plan 31', 20), (42, 38, N'Diet Plan 32', 20), (32, 28, N'Diet Plan 33', 40),
(58, 22, N'Diet Plan 34', 20), (48, 32, N'Diet Plan 35', 20), (28, 52, N'Diet Plan 36', 20),
(60, 20, N'Diet Plan 37', 20), (53, 17, N'Diet Plan 38', 30), (43, 37, N'Diet Plan 39', 20),
(37, 43, N'Diet Plan 40', 20), (50, 30, N'Diet Plan 41', 20), (40, 40, N'Diet Plan 42', 20),
(30, 30, N'Diet Plan 43', 40), (55, 25, N'Diet Plan 44', 20), (45, 35, N'Diet Plan 45', 20),
(25, 50, N'Diet Plan 46', 25), (60, 15, N'Diet Plan 47', 25), (50, 20, N'Diet Plan 48', 30),
(40, 35, N'Diet Plan 49', 25), (35, 45, N'Diet Plan 50', 20);
GO

-- 4. Chèn thêm 50 bản ghi cho [dbo].[dish]
INSERT INTO [dbo].[dish] ([calories], [carb], [des], [fat], [fiber], [food_picture], [name], [protein], [unit]) VALUES
(450, 50, N'Phở bò tái', 15, 5, 'pho_bo.jpg', N'Phở Bò', 30, 0),
(400, 60, N'Bún chả Hà Nội', 10, 5, 'bun_cha.jpg', N'Bún Chả', 20, 0),
(550, 70, N'Bánh mì thịt nướng', 20, 6, 'banh_mi.jpg', N'Bánh Mì Thịt', 25, 0),
(300, 40, N'Gỏi cuốn tôm thịt', 8, 4, 'goi_cuon.jpg', N'Gỏi Cuốn', 15, 0),
(600, 80, N'Cơm tấm sườn bì chả', 25, 5, 'com_tam.jpg', N'Cơm Tấm', 30, 0),
(250, 30, N'Canh chua cá lóc', 10, 5, 'canh_chua.jpg', N'Canh Chua', 20, 0),
(380, 45, N'Cá kho tộ', 15, 2, 'ca_kho.jpg', N'Cá Kho Tộ', 25, 0),
(180, 5, N'Rau muống xào tỏi', 15, 5, 'rau_muong.jpg', N'Rau Muống Xào', 3, 0),
(220, 20, N'Đậu hũ chiên sả', 12, 3, 'dau_hu.jpg', N'Đậu Hũ Chiên', 10, 0),
(420, 55, N'Bún bò Huế', 15, 6, 'bun_bo_hue.jpg', N'Bún Bò Huế', 28, 0),
(310, 35, N'Cháo yến mạch', 8, 7, 'chao_yen_mach.jpg', N'Cháo yến mạch hoa quả', 10, 0),
(280, 40, N'Khoai lang nướng', 1, 9, 'khoai_lang.jpg', N'Khoai lang nướng', 5, 0),
(400, 2, N'Cá hồi áp chảo', 25, 1, 'ca_hoi.jpg', N'Cá hồi áp chảo măng tây', 35, 0),
(90, 20, N'Táo', 0, 5, 'tao.jpg', N'Táo', 1, 0),
(100, 25, N'Chuối', 0, 3, 'chuoi.jpg', N'Chuối', 1, 0),
(150, 5, N'Trứng ốp la (2 quả)', 10, 0, 'trung_op.jpg', N'Trứng ốp la', 12, 0),
(350, 40, N'Sandwich gà', 12, 6, 'sandwich_ga.jpg', N'Sandwich gà', 20, 0),
(180, 15, N'Sữa hạt không đường', 10, 3, 'sua_hat.jpg', N'Sữa hạt hạnh nhân', 5, 1),
(50, 10, N'Dưa chuột', 0, 2, 'dua_chuot.jpg', N'Dưa chuột', 1, 0),
(70, 15, N'Cà chua', 0, 3, 'ca_chua.jpg', N'Cà chua', 2, 0),
(430, 60, N'Mì Ý sốt bò bằm', 15, 7, 'miy.jpg', N'Mì Ý', 22, 0),
(320, 50, N'Pizza chay', 8, 5, 'pizza_chay.jpg', N'Pizza Chay', 10, 0),
(200, 25, N'Súp bí đỏ', 8, 6, 'sup_bi_do.jpg', N'Súp bí đỏ', 4, 0),
(250, 30, N'Cơm chiên dương châu', 10, 4, 'com_chien.jpg', N'Cơm chiên', 8, 0),
(150, 20, N'Kim chi', 2, 5, 'kimchi.jpg', N'Kim chi', 3, 0),
(480, 65, N'Hủ tiếu nam vang', 18, 6, 'hu_tieu.jpg', N'Hủ tiếu', 25, 0),
(390, 50, N'Bánh canh cua', 14, 5, 'banh_canh.jpg', N'Bánh canh cua', 20, 0),
(210, 30, N'Chè hạt sen', 5, 8, 'che_hat_sen.jpg', N'Chè hạt sen', 10, 0),
(160, 25, N'Thạch rau câu', 0, 2, 'thach.jpg', N'Thạch rau câu', 1, 0),
(80, 10, N'Nước ép cần tây', 1, 3, 'nuoc_ep.jpg', N'Nước ép cần tây', 2, 1),
(300, 40, N'Món 31', 10, 5, 'dish31.jpg', N'Món 31', 15, 0), (310, 41, N'Món 32', 11, 5, 'dish32.jpg', N'Món 32', 16, 0),
(320, 42, N'Món 33', 12, 5, 'dish33.jpg', N'Món 33', 17, 0), (330, 43, N'Món 34', 13, 5, 'dish34.jpg', N'Món 34', 18, 0),
(340, 44, N'Món 35', 14, 5, 'dish35.jpg', N'Món 35', 19, 0), (350, 45, N'Món 36', 15, 5, 'dish36.jpg', N'Món 36', 20, 0),
(360, 46, N'Món 37', 16, 5, 'dish37.jpg', N'Món 37', 21, 0), (370, 47, N'Món 38', 17, 5, 'dish38.jpg', N'Món 38', 22, 0),
(380, 48, N'Món 39', 18, 5, 'dish39.jpg', N'Món 39', 23, 0), (390, 49, N'Món 40', 19, 5, 'dish40.jpg', N'Món 40', 24, 0),
(400, 50, N'Món 41', 20, 5, 'dish41.jpg', N'Món 41', 25, 0), (410, 51, N'Món 42', 21, 5, 'dish42.jpg', N'Món 42', 26, 0),
(420, 52, N'Món 43', 22, 5, 'dish43.jpg', N'Món 43', 27, 0), (430, 53, N'Món 44', 23, 5, 'dish44.jpg', N'Món 44', 28, 0),
(440, 54, N'Món 45', 24, 5, 'dish45.jpg', N'Món 45', 29, 0), (450, 55, N'Món 46', 25, 5, 'dish46.jpg', N'Món 46', 30, 0),
(460, 56, N'Món 47', 26, 5, 'dish47.jpg', N'Món 47', 31, 0), (470, 57, N'Món 48', 27, 5, 'dish48.jpg', N'Món 48', 32, 0),
(480, 58, N'Món 49', 28, 5, 'dish49.jpg', N'Món 49', 33, 0), (490, 59, N'Món 50', 29, 5, 'dish50.jpg', N'Món 50', 34, 0);
GO

-- 5. Chèn thêm 50 bản ghi cho [dbo].[training_mode]
INSERT INTO [dbo].[training_mode] ([des], [duration], [name]) VALUES
(N'Yoga for beginners', 60, N'Beginner Yoga'), (N'Advanced high-intensity interval training', 30, N'Advanced HIIT'),
(N'Marathon preparation running plan', 90, N'Marathon Training'), (N'Low-impact cardio for joint safety', 45, N'Low-Impact Cardio'),
(N'Full body workout using only bodyweight', 40, N'Bodyweight Strength'), (N'Flexibility and mobility exercises', 30, N'Mobility Flow'),
(N'Core strengthening exercises', 20, N'Core Focus'), (N'Lower body workout for legs and glutes', 50, N'Lower Body Blast'),
(N'Upper body workout for arms, chest, back', 50, N'Upper Body Pump'), (N'Active recovery day session', 30, N'Active Recovery'),
(N'Steady-state cardio for endurance', 60, N'Endurance Cardio'), (N'Explosive power training', 45, N'Plyometrics'),
(N'Functional fitness for daily movements', 50, N'Functional Training'), (N'Meditation and breathing exercises', 15, N'Mindfulness'),
(N'Post-workout stretching routine', 15, N'Cool Down Stretch'), (N'Workout 16', 45, N'TM 16'), (N'Workout 17', 45, N'TM 17'),
(N'Workout 18', 45, N'TM 18'), (N'Workout 19', 45, N'TM 19'), (N'Workout 20', 45, N'TM 20'),
(N'Workout 21', 45, N'TM 21'), (N'Workout 22', 45, N'TM 22'), (N'Workout 23', 45, N'TM 23'),
(N'Workout 24', 45, N'TM 24'), (N'Workout 25', 45, N'TM 25'), (N'Workout 26', 45, N'TM 26'),
(N'Workout 27', 45, N'TM 27'), (N'Workout 28', 45, N'TM 28'), (N'Workout 29', 45, N'TM 29'),
(N'Workout 30', 45, N'TM 30'), (N'Workout 31', 45, N'TM 31'), (N'Workout 32', 45, N'TM 32'),
(N'Workout 33', 45, N'TM 33'), (N'Workout 34', 45, N'TM 34'), (N'Workout 35', 45, N'TM 35'),
(N'Workout 36', 45, N'TM 36'), (N'Workout 37', 45, N'TM 37'), (N'Workout 38', 45, N'TM 38'),
(N'Workout 39', 45, N'TM 39'), (N'Workout 40', 45, N'TM 40'), (N'Workout 41', 45, N'TM 41'),
(N'Workout 42', 45, N'TM 42'), (N'Workout 43', 45, N'TM 43'), (N'Workout 44', 45, N'TM 44'),
(N'Workout 45', 45, N'TM 45'), (N'Workout 46', 45, N'TM 46'), (N'Workout 47', 45, N'TM 47'),
(N'Workout 48', 45, N'TM 48'), (N'Workout 49', 45, N'TM 49'), (N'Workout 50', 45, N'TM 50');
GO

--------------------------------------------------------------------------------
-- Bảng phụ thuộc (Có khóa ngoại)
--------------------------------------------------------------------------------

-- 6. Chèn thêm 50 bản ghi cho [dbo].[users] (Bắt đầu từ ID 4)
-- Ghi chú: BMI, BMR, TDEE là các giá trị ước tính cho dữ liệu mẫu.
INSERT INTO [dbo].[users] ([age], [bmi], [bmr], [email], [gender], [goal], [height], [level_activity], [name], [password], [phone_number], [tdee], [weight], [role_id]) VALUES
(22, 21.5, 1650, 'user4@example.com', 'Male', 68, 172, 1.375, N'Lê Minh Quân', 'pass4', 987111222, 2268.75, 65, 2),
(35, 24.1, 1500, 'user5@example.com', 'Female', 65, 168, 1.55, N'Phạm Thị Bích', 'pass5', 987222333, 2325, 72, 2),
(29, 22.0, 1800, 'user6@example.com', 'Male', 78, 180, 1.725, N'Vũ Đức Thắng', 'pass6', 987333444, 3105, 75, 2),
(41, 25.7, 1350, 'user7@example.com', 'Female', 60, 160, 1.2, N'Đặng Thu Hà', 'pass7', 987444555, 1620, 66, 3),
(26, 23.1, 1700, 'user8@example.com', 'Male', 72, 175, 1.55, N'Ngô Gia Huy', 'pass8', 987555666, 2635, 71, 4),
(33, 22.5, 1420, 'user9@example.com', 'Female', 58, 163, 1.375, N'Hồ Thanh Mai', 'pass9', 987666777, 1952.5, 60, 2),
(20, 20.8, 1780, 'user10@example.com', 'Male', 70, 178, 1.55, N'Đỗ Anh Khoa', 'pass10', 987777888, 2759, 68, 2),
(45, 26.2, 1550, 'user11@example.com', 'Male', 85, 182, 1.2, N'Lý Hùng', 'pass11', 987888999, 1860, 88, 2),
(28, 21.3, 1380, 'user12@example.com', 'Female', 55, 166, 1.55, N'Trịnh Ngọc Ánh', 'pass12', 987999000, 2139, 59, 2),
(38, 24.9, 1850, 'user13@example.com', 'Male', 82, 179, 1.375, N'Bùi Văn Cường', 'pass13', 987000111, 2543.75, 80, 2),
(23, 21.0, 1600, 'user14@example.com', 'Male', 70, 175, 1.55, N'User 14', 'pass14', 981414141, 2480, 65, 2),
(31, 22.1, 1450, 'user15@example.com', 'Female', 60, 165, 1.375, N'User 15', 'pass15', 981515151, 1993, 60, 2),
(27, 23.2, 1750, 'user16@example.com', 'Male', 75, 178, 1.55, N'User 16', 'pass16', 981616161, 2712, 73, 2),
(36, 24.3, 1500, 'user17@example.com', 'Female', 65, 162, 1.375, N'User 17', 'pass17', 981717171, 2062, 64, 2),
(22, 20.5, 1680, 'user18@example.com', 'Male', 68, 180, 1.725, N'User 18', 'pass18', 981818181, 2900, 67, 2),
(29, 21.6, 1400, 'user19@example.com', 'Female', 58, 169, 1.55, N'User 19', 'pass19', 981919191, 2170, 61, 2),
(40, 25.4, 1800, 'user20@example.com', 'Male', 80, 177, 1.2, N'User 20', 'pass20', 982020202, 2160, 80, 2),
(24, 22.7, 1380, 'user21@example.com', 'Female', 57, 161, 1.375, N'User 21', 'pass21', 982121212, 1897, 59, 2),
(33, 23.8, 1720, 'user22@example.com', 'Male', 77, 176, 1.55, N'User 22', 'pass22', 982222222, 2666, 74, 2),
(26, 20.9, 1420, 'user23@example.com', 'Female', 55, 168, 1.725, N'User 23', 'pass23', 982323232, 2450, 59, 2),
(35, 24.0, 1780, 'user24@example.com', 'Male', 80, 180, 1.375, N'User 24', 'pass24', 982424242, 2447, 78, 2),
(28, 21.2, 1480, 'user25@example.com', 'Female', 62, 170, 1.55, N'User 25', 'pass25', 982525252, 2294, 61, 2),
(21, 22.3, 1650, 'user26@example.com', 'Male', 72, 174, 1.375, N'User 26', 'pass26', 982626262, 2268, 68, 2),
(39, 25.5, 1520, 'user27@example.com', 'Female', 68, 163, 1.2, N'User 27', 'pass27', 982727272, 1824, 68, 2),
(32, 23.5, 1820, 'user28@example.com', 'Male', 82, 182, 1.55, N'User 28', 'pass28', 982828282, 2821, 78, 2),
(25, 20.7, 1460, 'user29@example.com', 'Female', 56, 167, 1.375, N'User 29', 'pass29', 982929292, 2007, 58, 2),
(42, 26.0, 1700, 'user30@example.com', 'Male', 85, 175, 1.2, N'User 30', 'pass30', 983030303, 2040, 80, 2),
(27, 21.8, 1410, 'user31@example.com', 'Female', 59, 164, 1.55, N'User 31', 'pass31', 983131313, 2185, 59, 2),
(30, 22.9, 1760, 'user32@example.com', 'Male', 78, 179, 1.725, N'User 32', 'pass32', 983232323, 3036, 75, 2),
(34, 24.1, 1490, 'user33@example.com', 'Female', 63, 166, 1.375, N'User 33', 'pass33', 983333333, 2048, 64, 2),
(23, 21, 1600, 'user34@example.com', 'Male', 70, 175, 1.55, N'User 34', 'pass34', 983434343, 2480, 65, 2),
(31, 22, 1450, 'user35@example.com', 'Female', 60, 165, 1.375, N'User 35', 'pass35', 983535353, 1993, 60, 2),
(27, 23, 1750, 'user36@example.com', 'Male', 75, 178, 1.55, N'User 36', 'pass36', 983636363, 2712, 73, 2),
(36, 24, 1500, 'user37@example.com', 'Female', 65, 162, 1.375, N'User 37', 'pass37', 983737373, 2062, 64, 2),
(22, 20, 1680, 'user38@example.com', 'Male', 68, 180, 1.725, N'User 38', 'pass38', 983838383, 2900, 67, 2),
(29, 21, 1400, 'user39@example.com', 'Female', 58, 169, 1.55, N'User 39', 'pass39', 983939393, 2170, 61, 2),
(40, 25, 1800, 'user40@example.com', 'Male', 80, 177, 1.2, N'User 40', 'pass40', 984040404, 2160, 80, 2),
(24, 22, 1380, 'user41@example.com', 'Female', 57, 161, 1.375, N'User 41', 'pass41', 984141414, 1897, 59, 2),
(33, 23, 1720, 'user42@example.com', 'Male', 77, 176, 1.55, N'User 42', 'pass42', 984242424, 2666, 74, 2),
(26, 20, 1420, 'user43@example.com', 'Female', 55, 168, 1.725, N'User 43', 'pass43', 984343434, 2450, 59, 2),
(35, 24, 1780, 'user44@example.com', 'Male', 80, 180, 1.375, N'User 44', 'pass44', 984444444, 2447, 78, 2),
(28, 21, 1480, 'user45@example.com', 'Female', 62, 170, 1.55, N'User 45', 'pass45', 984545454, 2294, 61, 2),
(21, 22, 1650, 'user46@example.com', 'Male', 72, 174, 1.375, N'User 46', 'pass46', 984646464, 2268, 68, 2),
(39, 25, 1520, 'user47@example.com', 'Female', 68, 163, 1.2, N'User 47', 'pass47', 984747474, 1824, 68, 2),
(32, 23, 1820, 'user48@example.com', 'Male', 82, 182, 1.55, N'User 48', 'pass48', 984848484, 2821, 78, 2),
(25, 20, 1460, 'user49@example.com', 'Female', 56, 167, 1.375, N'User 49', 'pass49', 984949494, 2007, 58, 2),
(42, 26, 1700, 'user50@example.com', 'Male', 85, 175, 1.2, N'User 50', 'pass50', 985050505, 2040, 80, 2),
(27, 21, 1410, 'user51@example.com', 'Female', 59, 164, 1.55, N'User 51', 'pass51', 985151515, 2185, 59, 2),
(30, 22, 1760, 'user52@example.com', 'Male', 78, 179, 1.725, N'User 52', 'pass52', 985252525, 3036, 75, 2),
(34, 24, 1490, 'user53@example.com', 'Female', 63, 166, 1.375, N'User 53', 'pass53', 985353535, 2048, 64, 2);
GO

-- 7. Chèn thêm 50 bản ghi cho [dbo].[workout]
INSERT INTO [dbo].[workout] ([calories_burned], [duration], [link_video], [name], [training_mode_id]) VALUES
(250, 60, 'youtube.com/yoga1', N'Yoga Chào buổi sáng', 4), (350, 30, 'youtube.com/hiit2', N'HIIT Toàn thân Nâng cao', 5),
(500, 90, 'youtube.com/run1', N'Chạy bền 10km', 6), (200, 45, 'youtube.com/cardio1', N'Cardio cho người mới', 7),
(300, 40, 'youtube.com/body1', N'Tập toàn thân không dụng cụ', 8), (150, 30, 'youtube.com/flex1', N'Giãn cơ toàn thân', 9),
(180, 20, 'youtube.com/core1', N'Tập cơ bụng 20 phút', 10), (400, 50, 'youtube.com/leg1', N'Tập chân và mông', 11),
(380, 50, 'youtube.com/arm1', N'Tập tay vai ngực', 12), (120, 30, 'youtube.com/rec1', N'Phục hồi sau tập', 13),
(320, 45, 'youtube.com/w16', N'Workout 16', 16), (330, 45, 'youtube.com/w17', N'Workout 17', 17),
(340, 45, 'youtube.com/w18', N'Workout 18', 18), (350, 45, 'youtube.com/w19', N'Workout 19', 19),
(360, 45, 'youtube.com/w20', N'Workout 20', 20), (370, 45, 'youtube.com/w21', N'Workout 21', 21),
(380, 45, 'youtube.com/w22', N'Workout 22', 22), (390, 45, 'youtube.com/w23', N'Workout 23', 23),
(400, 45, 'youtube.com/w24', N'Workout 24', 24), (410, 45, 'youtube.com/w25', N'Workout 25', 25),
(250, 30, 'youtube.com/w26', N'Workout 26', 26), (260, 30, 'youtube.com/w27', N'Workout 27', 27),
(270, 30, 'youtube.com/w28', N'Workout 28', 28), (280, 30, 'youtube.com/w29', N'Workout 29', 29),
(290, 30, 'youtube.com/w30', N'Workout 30', 30), (300, 30, 'youtube.com/w31', N'Workout 31', 31),
(310, 30, 'youtube.com/w32', N'Workout 32', 32), (320, 30, 'youtube.com/w33', N'Workout 33', 33),
(330, 30, 'youtube.com/w34', N'Workout 34', 34), (340, 30, 'youtube.com/w35', N'Workout 35', 35),
(350, 60, 'youtube.com/w36', N'Workout 36', 36), (360, 60, 'youtube.com/w37', N'Workout 37', 37),
(370, 60, 'youtube.com/w38', N'Workout 38', 38), (380, 60, 'youtube.com/w39', N'Workout 39', 39),
(390, 60, 'youtube.com/w40', N'Workout 40', 40), (400, 60, 'youtube.com/w41', N'Workout 41', 41),
(410, 60, 'youtube.com/w42', N'Workout 42', 42), (420, 60, 'youtube.com/w43', N'Workout 43', 43),
(430, 60, 'youtube.com/w44', N'Workout 44', 44), (440, 60, 'youtube.com/w45', N'Workout 45', 45),
(450, 60, 'youtube.com/w46', N'Workout 46', 46), (460, 60, 'youtube.com/w47', N'Workout 47', 47),
(470, 60, 'youtube.com/w48', N'Workout 48', 48), (480, 60, 'youtube.com/w49', N'Workout 49', 49),
(490, 60, 'youtube.com/w50', N'Workout 50', 50), (500, 60, 'youtube.com/w51', N'Workout 51', 51),
(510, 60, 'youtube.com/w52', N'Workout 52', 52), (520, 60, 'youtube.com/w53', N'Workout 53', 53);
GO

-- 8. Chèn thêm 50 bản ghi cho [dbo].[blog]
INSERT INTO [dbo].[blog] ([approved], [content], [date_created], [title], [user_id]) VALUES
(1, N'Nội dung blog về dinh dưỡng...', '2025-10-20 08:00:00', N'Chế độ ăn cho người mới tập', 1),
(0, N'Nội dung blog về giảm mỡ...', '2025-10-21 09:15:00', N'5 Mẹo giảm mỡ bụng hiệu quả', 4),
(1, N'Nội dung blog về tập luyện...', '2025-10-22 11:30:00', N'Lịch tập gym cho nam giới', 5),
(0, N'Nội dung blog về yoga...', '2025-10-23 14:00:00', N'Yoga tại nhà cần chuẩn bị gì?', 6),
(1, N'Nội dung blog về chạy bộ...', '2025-10-24 16:45:00', N'Cải thiện tốc độ chạy bộ', 7),
(1, N'Nội dung blog 6', '2025-11-01 10:00:00', N'Tiêu đề 6', 8), (0, N'Nội dung blog 7', '2025-11-02 11:00:00', N'Tiêu đề 7', 9),
(1, N'Nội dung blog 8', '2025-11-03 12:00:00', N'Tiêu đề 8', 10), (0, N'Nội dung blog 9', '2025-11-04 13:00:00', N'Tiêu đề 9', 11),
(1, N'Nội dung blog 10', '2025-11-05 14:00:00', N'Tiêu đề 10', 12), (0, N'Nội dung blog 11', '2025-11-06 15:00:00', N'Tiêu đề 11', 13),
(1, N'Nội dung blog 12', '2025-11-07 16:00:00', N'Tiêu đề 12', 14), (0, N'Nội dung blog 13', '2025-11-08 17:00:00', N'Tiêu đề 13', 15),
(1, N'Nội dung blog 14', '2025-11-09 18:00:00', N'Tiêu đề 14', 16), (0, N'Nội dung blog 15', '2025-11-10 19:00:00', N'Tiêu đề 15', 17),
(1, N'Nội dung blog 16', '2025-11-11 09:00:00', N'Tiêu đề 16', 18), (0, N'Nội dung blog 17', '2025-11-12 10:00:00', N'Tiêu đề 17', 19),
(1, N'Nội dung blog 18', '2025-11-13 11:00:00', N'Tiêu đề 18', 20), (0, N'Nội dung blog 19', '2025-11-14 12:00:00', N'Tiêu đề 19', 21),
(1, N'Nội dung blog 20', '2025-11-15 13:00:00', N'Tiêu đề 20', 22), (0, N'Nội dung blog 21', '2025-11-16 14:00:00', N'Tiêu đề 21', 23),
(1, N'Nội dung blog 22', '2025-11-17 15:00:00', N'Tiêu đề 22', 24), (0, N'Nội dung blog 23', '2025-11-18 16:00:00', N'Tiêu đề 23', 25),
(1, N'Nội dung blog 24', '2025-11-19 17:00:00', N'Tiêu đề 24', 26), (0, N'Nội dung blog 25', '2025-11-20 18:00:00', N'Tiêu đề 25', 27),
(1, N'Nội dung blog 26', '2025-11-21 09:30:00', N'Tiêu đề 26', 28), (0, N'Nội dung blog 27', '2025-11-22 10:30:00', N'Tiêu đề 27', 29),
(1, N'Nội dung blog 28', '2025-11-23 11:30:00', N'Tiêu đề 28', 30), (0, N'Nội dung blog 29', '2025-11-24 12:30:00', N'Tiêu đề 29', 31),
(1, N'Nội dung blog 30', '2025-11-25 13:30:00', N'Tiêu đề 30', 32), (0, N'Nội dung blog 31', '2025-11-26 14:30:00', N'Tiêu đề 31', 33),
(1, N'Nội dung blog 32', '2025-11-27 15:30:00', N'Tiêu đề 32', 34), (0, N'Nội dung blog 33', '2025-11-28 16:30:00', N'Tiêu đề 33', 35),
(1, N'Nội dung blog 34', '2025-11-29 17:30:00', N'Tiêu đề 34', 36), (0, N'Nội dung blog 35', '2025-11-30 18:30:00', N'Tiêu đề 35', 37),
(1, N'Nội dung blog 36', '2025-12-01 08:45:00', N'Tiêu đề 36', 38), (0, N'Nội dung blog 37', '2025-12-02 09:45:00', N'Tiêu đề 37', 39),
(1, N'Nội dung blog 38', '2025-12-03 10:45:00', N'Tiêu đề 38', 40), (0, N'Nội dung blog 39', '2025-12-04 11:45:00', N'Tiêu đề 39', 41),
(1, N'Nội dung blog 40', '2025-12-05 12:45:00', N'Tiêu đề 40', 42), (0, N'Nội dung blog 41', '2025-12-06 13:45:00', N'Tiêu đề 41', 43),
(1, N'Nội dung blog 42', '2025-12-07 14:45:00', N'Tiêu đề 42', 44), (0, N'Nội dung blog 43', '2025-12-08 15:45:00', N'Tiêu đề 43', 45),
(1, N'Nội dung blog 44', '2025-12-09 16:45:00', N'Tiêu đề 44', 46), (0, N'Nội dung blog 45', '2025-12-10 17:45:00', N'Tiêu đề 45', 47),
(1, N'Nội dung blog 46', '2025-12-11 18:45:00', N'Tiêu đề 46', 48), (0, N'Nội dung blog 47', '2025-12-12 19:45:00', N'Tiêu đề 47', 49),
(1, N'Nội dung blog 48', '2025-12-13 20:45:00', N'Tiêu đề 48', 50), (0, N'Nội dung blog 49', '2025-12-14 21:45:00', N'Tiêu đề 49', 51),
(1, N'Nội dung blog 50', '2025-12-15 22:45:00', N'Tiêu đề 50', 52);
GO

--------------------------------------------------------------------------------
-- Bảng Ghi Log và Bảng Quan hệ (Nhiều-Nhiều)
--------------------------------------------------------------------------------

-- 9. Chèn thêm 50 bản ghi cho [dbo].[activity_log]
-- Dữ liệu được tạo ngẫu nhiên cho các user từ 2 đến 53 và activity từ 1 đến 54
INSERT INTO [dbo].[activity_log] ([date_log], [duration], [activity_id], [user_id]) VALUES
('2025-10-20 07:00:00', 45, 5, 2), ('2025-10-20 18:00:00', 60, 15, 3), ('2025-10-21 06:30:00', 30, 8, 4),
('2025-10-21 19:00:00', 50, 22, 5), ('2025-10-22 08:00:00', 75, 35, 6), ('2025-10-22 17:30:00', 40, 40, 7),
('2025-10-23 07:15:00', 25, 12, 8), ('2025-10-23 20:00:00', 90, 50, 9), ('2025-10-24 09:00:00', 60, 3, 10),
('2025-10-24 18:30:00', 45, 18, 11), ('2025-10-25 06:00:00', 35, 25, 12), ('2025-10-25 19:15:00', 55, 33, 13),
('2025-10-26 07:30:00', 80, 45, 14), ('2025-10-26 17:00:00', 50, 7, 15), ('2025-10-27 08:45:00', 65, 19, 16),
('2025-10-27 20:30:00', 30, 28, 17), ('2025-10-28 06:45:00', 45, 38, 18), ('2025-10-28 18:00:00', 70, 48, 19),
('2025-10-29 07:00:00', 60, 9, 20), ('2025-10-29 19:45:00', 40, 21, 21), ('2025-10-30 08:15:00', 50, 31, 22),
('2025-10-30 17:30:00', 85, 41, 23), ('2025-10-31 06:00:00', 30, 51, 24), ('2025-10-31 18:45:00', 45, 13, 25),
('2025-11-01 07:30:00', 60, 23, 26), ('2025-11-01 20:00:00', 75, 34, 27), ('2025-11-02 09:00:00', 55, 44, 28),
('2025-11-02 18:00:00', 40, 6, 29), ('2025-11-03 06:15:00', 35, 16, 30), ('2025-11-03 19:30:00', 65, 26, 31),
('2025-11-04 07:45:00', 50, 36, 32), ('2025-11-04 17:00:00', 90, 46, 33), ('2025-11-05 08:00:00', 45, 52, 34),
('2025-11-05 20:15:00', 60, 11, 35), ('2025-11-06 06:30:00', 30, 24, 36), ('2025-11-06 18:30:00', 50, 30, 37),
('2025-11-07 07:00:00', 70, 42, 38), ('2025-11-07 19:00:00', 40, 49, 39), ('2025-11-08 08:30:00', 55, 14, 40),
('2025-11-08 17:45:00', 80, 27, 41), ('2025-11-09 06:45:00', 35, 37, 42), ('2025-11-09 18:15:00', 60, 47, 43),
('2025-11-10 07:15:00', 45, 53, 44), ('2025-11-10 20:00:00', 65, 10, 45), ('2025-11-11 08:00:00', 50, 20, 46),
('2025-11-11 19:30:00', 75, 32, 47), ('2025-11-12 06:00:00', 40, 43, 48), ('2025-11-12 18:00:00', 55, 54, 49),
('2025-11-13 07:30:00', 60, 17, 50), ('2025-11-13 17:15:00', 45, 29, 51);
GO

-- 10. Chèn thêm 50 bản ghi cho [dbo].[food_log] (Bắt đầu từ ID 5)
INSERT INTO [dbo].[food_log] ([log_time], [meal], [user_id]) VALUES
('2025-10-20 07:30:00', 0, 4), ('2025-10-20 12:00:00', 1, 4), ('2025-10-20 19:00:00', 2, 4),
('2025-10-21 08:00:00', 0, 5), ('2025-10-21 13:00:00', 1, 5), ('2025-10-22 07:00:00', 0, 6),
('2025-10-22 12:30:00', 1, 6), ('2025-10-22 18:30:00', 2, 6), ('2025-10-23 09:00:00', 0, 7),
('2025-10-23 13:30:00', 1, 7), ('2025-10-24 07:45:00', 0, 8), ('2025-10-24 12:15:00', 1, 8),
('2025-10-25 08:15:00', 0, 9), ('2025-10-25 19:30:00', 2, 9), ('2025-10-26 07:00:00', 0, 10),
('2025-10-26 12:00:00', 1, 11), ('2025-10-26 18:00:00', 2, 12), ('2025-10-27 08:30:00', 0, 13),
('2025-10-27 13:00:00', 1, 14), ('2025-10-27 19:00:00', 2, 15), ('2025-10-28 07:15:00', 0, 16),
('2025-10-28 12:45:00', 1, 17), ('2025-10-28 18:45:00', 2, 18), ('2025-10-29 09:00:00', 0, 19),
('2025-10-29 13:15:00', 1, 20), ('2025-10-29 20:00:00', 2, 21), ('2025-10-30 07:30:00', 0, 22),
('2025-10-30 12:30:00', 1, 23), ('2025-10-30 19:15:00', 2, 24), ('2025-10-31 08:00:00', 0, 25),
('2025-10-31 13:00:00', 1, 26), ('2025-10-31 18:30:00', 2, 27), ('2025-11-01 07:45:00', 0, 28),
('2025-11-01 12:15:00', 1, 29), ('2025-11-01 19:45:00', 2, 30), ('2025-11-02 08:15:00', 0, 31),
('2025-11-02 13:45:00', 1, 32), ('2025-11-02 20:15:00', 2, 33), ('2025-11-03 07:00:00', 0, 34),
('2025-11-03 12:00:00', 1, 35), ('2025-11-03 19:00:00', 2, 36), ('2025-11-04 08:30:00', 0, 37),
('2025-11-04 13:30:00', 1, 38), ('2025-11-04 18:00:00', 2, 39), ('2025-11-05 07:15:00', 0, 40),
('2025-11-05 12:45:00', 1, 41), ('2025-11-05 19:30:00', 2, 42), ('2025-11-06 09:00:00', 0, 43),
('2025-11-06 14:00:00', 1, 44), ('2025-11-06 20:00:00', 2, 45);
GO

-- 11. Chèn thêm 50 bản ghi cho [dbo].[meal_detail]
-- Giả sử food_log ID bắt đầu từ 5 cho các bản ghi mới
INSERT INTO [dbo].[meal_detail] ([food_intake], [dish_id], [food_log_id]) VALUES
(250, 5, 5), (1, 6, 6), (1, 7, 7), (150, 8, 8), (1, 9, 9),
(300, 10, 10), (2, 11, 11), (200, 12, 12), (1, 13, 13), (1, 14, 14),
(100, 15, 15), (2, 16, 16), (1, 17, 17), (200, 18, 18), (1, 19, 19),
(150, 20, 20), (250, 21, 21), (300, 22, 22), (1, 23, 23), (1, 24, 24),
(1, 25, 25), (200, 26, 26), (1, 27, 27), (250, 28, 28), (1, 29, 29),
(150, 30, 30), (1, 31, 31), (200, 32, 32), (1, 33, 33), (1, 34, 34),
(250, 35, 35), (300, 36, 36), (1, 37, 37), (150, 38, 38), (1, 39, 39),
(200, 40, 40), (1, 41, 41), (250, 42, 42), (1, 43, 43), (1, 44, 44),
(150, 45, 45), (1, 46, 46), (200, 47, 47), (250, 48, 48), (1, 49, 49),
(150, 50, 50), (1, 51, 51), (200, 52, 52), (1, 53, 53), (250, 54, 54);
GO


-- 12. Chèn thêm 50 bản ghi cho [dbo].[user_diet]
-- Tạo các cặp user_id và diet_id duy nhất
INSERT INTO [dbo].[user_diet] ([diet_id], [user_id]) VALUES
(1, 4), (2, 5), (3, 6), (4, 7), (5, 8), (6, 9), (7, 10), (8, 11), (9, 12), (10, 13),
(11, 14), (12, 15), (13, 16), (14, 17), (15, 18), (16, 19), (17, 20), (18, 21), (19, 22), (20, 23),
(21, 24), (22, 25), (23, 26), (24, 27), (25, 28), (26, 29), (27, 30), (28, 31), (29, 32), (30, 33),
(31, 34), (32, 35), (33, 36), (34, 37), (35, 38), (36, 39), (37, 40), (38, 41), (39, 42), (40, 43),
(41, 44), (42, 45), (43, 46), (44, 47), (45, 48), (46, 49), (47, 50), (48, 51), (49, 52), (50, 53);
GO

-- 13. Chèn thêm 50 bản ghi cho [dbo].[user_workout]
-- Tạo các cặp user_id và work_id duy nhất
INSERT INTO [dbo].[user_workout] ([user_id], [work_id]) VALUES
(4, 4), (5, 5), (6, 6), (7, 7), (8, 8), (9, 9), (10, 10), (11, 11), (12, 12), (13, 13),
(14, 14), (15, 15), (16, 16), (17, 17), (18, 18), (19, 19), (20, 20), (21, 21), (22, 22), (23, 23),
(24, 24), (25, 25), (26, 26), (27, 27), (28, 28), (29, 29), (30, 30), (31, 31), (32, 32), (33, 33),
(34, 34), (35, 35), (36, 36), (37, 37), (38, 38), (39, 39), (40, 40), (41, 41), (42, 42), (43, 43),
(44, 44), (45, 45), (46, 46), (47, 47), (48, 48), (49, 49), (50, 50), (51, 51), (52, 52), (53, 53);
GO

PRINT 'Đã chèn thành công 50 bản ghi mới vào mỗi bảng.';
GO

PRINT 'Đã tạo và chèn toàn bộ dữ liệu cho database Fitness thành công!';
GO