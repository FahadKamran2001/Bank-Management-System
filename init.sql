drop table BankLoanApplication;
drop table BankTransaction;
drop table BankLoan;
drop table BankAccount;
drop table BankAdmin;
drop table BankCustomer;

create table BankCustomer(
custID varchar2(10) not null primary key,
name varchar2(30) not null,
phone# varchar2(13),
email varchar2(30),
username varchar2(20) not null unique,
password varchar2(20) not null);

insert into BankCustomer values('1','Fahad Kamran','+923333333333','fradez@gmail.com','fahadcust','fahad');
insert into BankCustomer values('2','Mahad Kamran','+923333333334','mradez@gmail.com','mahadcust','mahad');
insert into BankCustomer values('3','Inaya Kamran','+923333333335','inaya@gmail.com','inayacust','inaya');

create table BankAdmin(
adminID varchar2(10) not null primary key,
name varchar2(30) not null,
phone# varchar2(13),
email varchar2(30),
username varchar2(20) not null unique,
password varchar2(20) not null);

insert into BankAdmin values('1','Fahad Kamran Kundi','+923333333336','fradez2@gmail.com','fahadadm','fahad');

create table BankAccount(
acc# varchar2(12) not null primary key,
balance number not null,
creationDate date not null,
custID varchar2(10) not null,
foreign key(custID) references BankCustomer(custID),
status varchar2(10) not null);

insert into BankAccount values('1',300000,TO_DATE(sysdate, 'yyyy/mm/dd hh24:mi:ss'),'1','Active');
insert into BankAccount values('2',300000,TO_DATE(sysdate, 'yyyy/mm/dd hh24:mi:ss'),'2','Active');
insert into BankAccount values('3',300000,TO_DATE(sysdate, 'yyyy/mm/dd hh24:mi:ss'),'3','Active');

create table BankTransaction(
transactionID varchar2(15) not null primary key,
acc# varchar2(12) not null,
foreign key(acc#) references BankAccount(acc#),
transDate date not null,
amount number not null,
dueDate date,
reason varchar2(50) not null);

create table BankLoan(
loanID varchar2(15) not null primary key,
acc# varchar2(12) not null,
foreign key(acc#) references BankAccount(acc#),
amount number not null,
interestRate number not null,
acceptDate date not null,
endDate date not null,
status varchar2(10) not null);

create table BankLoanApplication(
loanID varchar2(15) not null primary key,
acc# varchar2(12) not null,
foreign key(acc#) references BankAccount(acc#),
amount number not null,
applyDate date not null,
status varchar2(10) not null);
