INSERT INTO products
(name, description, brand, price, category, release_date, product_available, stock_quantity, image_name, image_type, image_data)
VALUES
('iPhone 15', 'Apple iPhone 15 with 128GB storage', 'Apple', 999.99, 'Smartphone', '2023-09-22', true, 25, 'iphone15.jpg', 'image/jpeg', NULL),
('Galaxy S24', 'Samsung Galaxy S24 256GB', 'Samsung', 899.99, 'Smartphone', '2024-01-17', true, 30, 'galaxys24.jpg', 'image/jpeg', NULL),
('MacBook Air M3', '13-inch Apple MacBook Air with M3 chip', 'Apple', 1499.99, 'Laptop', '2024-03-08', true, 15, 'macbookairm3.jpg', 'image/jpeg', NULL),
('Sony WH-1000XM5', 'Wireless Noise Cancelling Headphones', 'Sony', 349.99, 'Headphones', '2022-05-12', true, 40, 'sonyxm5.jpg', 'image/jpeg', NULL),
('Dell XPS 15', '15-inch high performance laptop', 'Dell', 1899.99, 'Laptop', '2023-06-20', false, 0, 'dellxps15.jpg', 'image/jpeg', NULL);

INSERT INTO reviews (reviewer_name, comment, rating, product_id)
VALUES
('علی محمدی', 'کیفیت ساخت و دوربین آیفون ۱۵ فوق‌العاده است.', 5, 1),
('سارا امیری', 'باتری آیفون نسبت به نسل قبلی خیلی بهتر شده.', 4, 1),
('رضا احمدی', 'صفحه نمایش ۲۴۰ هرتزی گلکسی S24 بی نظیره.', 5, 2),
('مریم کاظمی', 'طراحی مک بوک ایر M3 بسیار باریک و سبک است ولی قیمتش بالاست.', 4, 3),
('حسین نوری', 'نویز کنسلینگ هدفون سونی واقعاً حیرت‌انگیزه.', 5, 4);

-- ==========================================
-- درج تگ‌های اولیه سیستم
-- ==========================================
INSERT INTO tags (name)
VALUES
('ELECTRONICS'),
('MOBILE'),
('LAPTOP'),
('AUDIO'),
('APPLE'),
('SAMSUNG'),
('SONY');

-- ==========================================
-- اتصال تگ‌ها به محصولات در جدول واسط (product_tags)
-- ==========================================
INSERT INTO product_tags (product_id, tag_id)
VALUES
-- iPhone 15 (Product 1) -> ELECTRONICS(1), MOBILE(2), APPLE(5)
(1, 1),
(1, 2),
(1, 5),

-- Galaxy S24 (Product 2) -> ELECTRONICS(1), MOBILE(2), SAMSUNG(6)
(2, 1),
(2, 2),
(2, 6),

-- MacBook Air M3 (Product 3) -> ELECTRONICS(1), LAPTOP(3), APPLE(5)
(3, 1),
(3, 3),
(3, 5),

-- Sony WH-1000XM5 (Product 4) -> ELECTRONICS(1), AUDIO(4), SONY(7)
(4, 1),
(4, 4),
(4, 7),

-- Dell XPS 15 (Product 5) -> ELECTRONICS(1), LAPTOP(3)
(5, 1),
(5, 3);