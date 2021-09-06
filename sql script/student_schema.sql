DROP SCHEMA IF EXISTS student_schema;
create schema student_schema;

use student_schema;

set foreign_key_checks=0;

create table guardian(
	guardian_id bigint not null auto_increment,
	`name` varchar(30),
    email varchar(40),
    mobile varchar(15),
    primary key(guardian_id)
);

create table student(
	student_id bigint not null auto_increment,
    first_name varchar(30),
    last_name varchar(30),
    email_id varchar(40),
    guardian_id bigint,
    primary key(student_id),
    FOREIGN KEY (guardian_id) REFERENCES guardian(guardian_id) on delete cascade
);

create table teacher(
	teacher_id bigint primary key,
    first_name varchar(30),
    last_name varchar(30)
);

create table course(
	course_id bigint primary key auto_increment,
    title varchar(30),
    credit int,
    teacher_id bigint,
    FOREIGN KEY (teacher_id) REFERENCES teacher(teacher_id)
);

create table course_material(
	course_material_id bigint primary key auto_increment,
    url varchar(50),
    course_id bigint,
    FOREIGN KEY (course_id) REFERENCES course(course_id)
);

create table book(
	book_id bigint not null auto_increment,
    title varchar(30),
    author varchar(30),
    student_id bigint,
    primary key(book_id),
    FOREIGN KEY (student_id) REFERENCES student(student_id) on delete set null
);

create table book_details(
	book_detail_id bigint not null auto_increment,
    issue_date date,
    book_id bigint,
    student_id bigint,
    primary key(book_detail_id),
    FOREIGN KEY (book_id) REFERENCES book(book_id),
    FOREIGN KEY (student_id) REFERENCES student(student_id) on delete cascade
);

set foreign_key_checks=1;