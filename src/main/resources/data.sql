INSERT INTO members
(member_id, first_name, last_name, email,
 active_loan_count, member_status, membership_tier, registration_date)
VALUES
    ('550e8400-e29b-41d4-a716-446655440000',
     'John', 'Smith', 'john.smith@email.com',
     0, 'ACTIVE', 'STANDARD', '2026-09-01');

INSERT INTO members
(member_id, first_name, last_name, email,
 active_loan_count, member_status, membership_tier, registration_date)
VALUES
    ('6ba7b810-9dad-11d1-80b4-00c04fd430c8',
     'Sarah', 'Johnson', 'sarah.johnson@email.com',
     2, 'ACTIVE', 'PREMIUM', '2026-09-02');

INSERT INTO members
(member_id, first_name, last_name, email,
 active_loan_count, member_status, membership_tier, registration_date)
VALUES
    ('123e4567-e89b-12d3-a456-426614174000',
     'Michael', 'Brown', 'michael.brown@email.com',
     1, 'SUSPENDED', 'STANDARD', '2026-09-03');

INSERT INTO members
(member_id, first_name, last_name, email,
 active_loan_count, member_status, membership_tier, registration_date)
VALUES
    ('123e4567-e89b-12d3-a456-426614174001',
     'Emily', 'Davis', 'emily.davis@email.com',
     3, 'ACTIVE', 'PREMIUM', '2026-09-04');

INSERT INTO members
(member_id, first_name, last_name, email,
 active_loan_count, member_status, membership_tier, registration_date)
VALUES
    ('123e4567-e89b-12d3-a456-426614174002',
     'David', 'Wilson', 'david.wilson@email.com',
     0, 'SUSPENDED', 'STANDARD', '2026-09-05');

INSERT INTO members
(member_id, first_name, last_name, email,
 active_loan_count, member_status, membership_tier, registration_date)
VALUES
    ('123e4567-e89b-12d3-a456-426614174003',
     'Jessica', 'Miller', 'jessica.miller@email.com',
     2, 'ACTIVE', 'PREMIUM', '2026-09-06');

INSERT INTO members
(member_id, first_name, last_name, email,
 active_loan_count, member_status, membership_tier, registration_date)
VALUES
    ('123e4567-e89b-12d3-a456-426614174004',
     'Robert', 'Taylor', 'robert.taylor@email.com',
     1, 'EXPIRED', 'STANDARD', '2026-09-07');

INSERT INTO members
(member_id, first_name, last_name, email,
 active_loan_count, member_status, membership_tier, registration_date)
VALUES
    ('123e4567-e89b-12d3-a456-426614174005',
     'Amanda', 'Anderson', 'amanda.anderson@email.com',
     4, 'EXPIRED', 'PREMIUM', '2026-09-08');

INSERT INTO members
(member_id, first_name, last_name, email,
 active_loan_count, member_status, membership_tier, registration_date)
VALUES
    ('123e4567-e89b-12d3-a456-426614174006',
     'Christopher', 'Thomas', 'christopher.thomas@email.com',
     0, 'SUSPENDED', 'STANDARD', '2026-09-09');




-- ============================================================
-- COLLECTION ITEMS (single-table inheritance)
-- item_type discriminator: BOOK | AUDIO | VIDEO
-- ============================================================
-- BOOKS

INSERT INTO collection_items
(item_type,  item_id, item_status, publication_year, title, isbn)
VALUES
    ('BOOK',
     '550e8400-e29b-41d4-a716-446655440001',
     'AVAILABLE',
     2008,
     'Clean Code',
     '9780132350884');

INSERT INTO collection_items
(item_type,  item_id, item_status, publication_year, title, isbn)
VALUES
    ('BOOK',
     '550e8400-e29b-41d4-a716-446655440002',
     'ON_LOAN',
     2018,
     'Spring in Action',
     '9781617294945');

INSERT INTO collection_items
(item_type,  item_id, item_status, publication_year, title, isbn)
VALUES
    ('BOOK',
     '550e8400-e29b-41d4-a716-446655440003',
     'RESERVED',
     2018,
     'Effective Java',
     '9780134685991');


-- AUDIO ITEMS

INSERT INTO collection_items
(item_type,  item_id, item_status, publication_year, title, narrator, duration)
VALUES
    ('AUDIO',
     '550e8400-e29b-41d4-a716-446655440004',
     'AVAILABLE',
     2020,
     'Atomic Habits',
     'James Clear',
     320);

INSERT INTO collection_items
(item_type,  item_id, item_status, publication_year, title, narrator, duration)
VALUES
    ('AUDIO',
     '550e8400-e29b-41d4-a716-446655440005',
     'ON_LOAN',
     2019,
     'The Power of Habit',
     'Mike Chamberlain',
     410);

INSERT INTO collection_items
(item_type,  item_id, item_status, publication_year, title, narrator, duration)
VALUES
    ('AUDIO',
     '550e8400-e29b-41d4-a716-446655440006',
     'AVAILABLE',
     2021,
     'Deep Work',
     'Jeff Bottoms',
     285);


-- VIDEOS

INSERT INTO collection_items
(item_type, item_id, item_status, publication_year, title, format, duration, avg_rating)
VALUES
    ('VIDEO',
     '550e8400-e29b-41d4-a716-446655440007',
     'AVAILABLE',
     2023,
     'Java Programming Fundamentals',
     'DIGITAL',
     120,
     '4.7');

INSERT INTO collection_items
(item_type,  item_id, item_status, publication_year, title, format, duration, avg_rating)
VALUES
    ('VIDEO',
     '550e8400-e29b-41d4-a716-446655440008',
     'DAMAGED',
     2022,
     'Spring Boot Masterclass',
     'DVD',
     180,
     '4.8');

INSERT INTO collection_items
(item_type,  item_id, item_status, publication_year, title, format, duration, avg_rating)
VALUES
    ('VIDEO',
     '550e8400-e29b-41d4-a716-446655440009',
     'AVAILABLE',
     2021,
     'Database Design Essentials',
     'BLU_RAY',
     95,
     '4.5');

INSERT INTO collection_items
(item_type,  item_id, item_status, publication_year, title, format, duration, avg_rating)
VALUES
    ('VIDEO',
     '550e8400-e29b-41d4-a716-446655440010',
     'RESERVED',
     2024,
     'Software Architecture Patterns',
     'DIGITAL',
     150,
     '4.9');

















-- ============================================================
-- AUTHORS
-- item_id is a foreign key into collection_items.
-- The original draft used 1, 2, 3 here; it must be the UUID
-- of the referenced item, because that table uses UUID keys.
-- ============================================================

INSERT INTO authors
( author_id, name, street, province, city, postal_code, item_id)
VALUES
    ( 'a1b2c3d4-e5f6-4789-8123-111111111111',
      'J.K. Rowling',
      '12 Maple Street',
      'Ontario',
      'Ottawa',
      'K1A0A1',
      1);

INSERT INTO authors
( author_id, name, street, province, city, postal_code, item_id)
VALUES
    ( 'a1b2c3d4-e5f6-4789-8123-222222222222',
      'George Orwell',
      '34 Oak Avenue',
      'Ontario',
      'Toronto',
      'M5V2T6',
      2);

INSERT INTO authors
( author_id, name, street, province, city, postal_code, item_id)
VALUES
    ( 'a1b2c3d4-e5f6-4789-8123-333333333333',
      'Jane Austen',
      '56 Cedar Road',
      'British Columbia',
      'Vancouver',
      'V6B1A1',
      3);

INSERT INTO authors
( author_id, name, street, province, city, postal_code, item_id)
VALUES
    ('a1b2c3d4-e5f6-4789-8123-444444444444',
     'Mark Twain',
     '78 Birch Lane',
     'Alberta',
     'Calgary',
     'T2P1J9',
     1);

INSERT INTO authors
( author_id, name, street, province, city, postal_code, item_id)
VALUES
    ( 'a1b2c3d4-e5f6-4789-8123-555555555555',
      'Ernest Hemingway',
      '90 Pine Avenue',
      'Quebec',
      'Montreal',
      'H3A1A1',
      2);

INSERT INTO authors
( author_id, name, street, province, city, postal_code, item_id)
VALUES
    ('a1b2c3d4-e5f6-4789-8123-666666666666',
     'Agatha Christie',
     '15 Elm Street',
     'Nova Scotia',
     'Halifax',
     'B3H2Y5',
     3);

INSERT INTO authors
( author_id, name, street, province, city, postal_code, item_id)
VALUES
    ( 'a1b2c3d4-e5f6-4789-8123-777777777777',
      'Stephen King',
      '22 Spruce Drive',
      'Manitoba',
      'Winnipeg',
      'R3C4T3',
      1);

INSERT INTO authors
( author_id, name, street, province, city, postal_code, item_id)
VALUES
    ( 'a1b2c3d4-e5f6-4789-8123-888888888888',
      'Maya Angelou',
      '44 Willow Crescent',
      'Saskatchewan',
      'Saskatoon',
      'S7K3J8',
      2);

INSERT INTO authors
( author_id, name, street, province, city, postal_code, item_id)
VALUES
    ('a1b2c3d4-e5f6-4789-8123-999999999999',
     'Charles Dickens',
     '10 Victoria Road',
     'New Brunswick',
     'Fredericton',
     'E3B1B1',
     3);

INSERT INTO authors
( author_id, name, street, province, city, postal_code, item_id)
VALUES
    ( 'a1b2c3d4-e5f6-4789-8123-101010101010',
      'Virginia Woolf',
      '88 Lighthouse Way',
      'Newfoundland and Labrador',
      'St. John''s',
      'A1C5M2',
      3);

INSERT INTO author_phone_numbers (author_id, phone_number) VALUES
                                                               (1, '+1-613-111-1111'),
                                                               (2, '+1-416-222-2222'),
                                                               (3, '+1-604-333-3333'),
                                                               (4, '+1-403-444-4444'),
                                                               (5, '+1-514-555-5555'),
                                                               (6, '+1-902-666-6666'),
                                                               (7, '+1-204-777-7777'),
                                                               (8, '+1-306-888-8888'),
                                                               (9, '+1-506-999-9999'),
                                                               (10, '+1-709-101-1010');
