# spring_project

DB
create database shop;
use shop;

create table member(m_num int primary key auto_increment,
					m_type int not null default 1,
                    m_id varchar(20) not null unique,
                    m_pw varchar(20) not null,
                    m_phone varchar(20) not null unique,
                    m_email  varchar(30) not null unique,
                    m_address varchar(50) not null unique,
                    m_date datetime default now()
                    );
                    
insert into member(m_type,m_id,m_pw,m_phone,m_email,m_address)
			values(1,"abc","abc11","11111111111","abc@abc.com","종로 인사동");
            
select * from member;

create table board(b_num int primary key auto_increment,
					b_type int not null default 1,
                    b_title varchar(50) not null,
                    b_content varchar(500),
                    b_writer varchar(20) not null,
                    b_writedate datetime default now(),
                    b_readcount int default 0
                    );
                    
insert into board(b_type,b_title,b_content,b_writer)
			values(1,"제목","내용","작성자");
            
select * from board;

create table item(i_num int primary key auto_increment,
					i_type int not null,
					i_name varchar(30) not null,
                    i_price int not null,
                    i_stock int not null default 1,
                    i_image varchar(50),
                    i_explanation varchar(100) 
                    );

insert into item(i_type,i_name,i_price,i_stock,i_image,i_explanation)
			values(1,"반바지",20000,10,"없음","여름에 시원한 반바지");
            
select * from item;
