create table users (
    userId serial primary key,
    userName varchar(255) unique
);

create table lists (
    listId serial primary key,
    userId int,
    listName varchar(255) not null unique,
    titles text[],
    bodies text[],
    checkedIndex int not null,
    constraint fk_userId foreign key (userId) references users(userId)
);