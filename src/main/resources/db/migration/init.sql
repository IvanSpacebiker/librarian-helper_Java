create table readers (
    id uuid not null,
    name varchar(100) not null,
    surname varchar(100) not null
    );
create table books (
    id uuid not null,
    title varchar(100) not null,
    author varchar(100) not null,
    quantity int not null
    );
create table events (
    id uuid not null,
    type varchar(100) not null,
    time timestamp not null,
    bookid uuid not null,
    readerid uuid not null
    );
-- insert into readers (name, surname) values ('John', 'Snow'), ('Bilbo', 'Baggins');
-- insert into books (title, author) values ('Witcher', 'Sapkovsky'), ('Hobbit', 'Tolkien');