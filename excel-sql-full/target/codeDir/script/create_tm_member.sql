create table tm_member (
    member_no char(20) NOT NULL PRIMARY KEY,
    login_name varchar(64) NOT NULL,
    phone_no char(11) NOT NULL,
    member_name varchar(64),
    status char(1),
    create_time datetime,
    modified_time datetime
);
