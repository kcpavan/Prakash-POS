set sql_safe_updates=0;

drop database  if exists storedb;
create database storedb;

drop table if exists storedb.user_group;
create table storedb.user_group
(id_pk integer primary key not null auto_increment,
group_name varchar(20) not null);

INSERT INTO storedb.user_group
(id_pk,group_name)
values(1,'admin');

drop table if exists storedb.users;
create table storedb.users
(id_pk integer primary key not null auto_increment,
 first_name varchar(50) not null,
 last_name varchar(50) not null,
username varchar(20) not null,
password varchar(20) not null,
user_state int not null,
group_id_fk int not null,
CONSTRAINT fk_group_id
FOREIGN KEY(`group_id_fk`) 
REFERENCES `user_group`(`id_pk`));

drop table if exists storedb.item_category;
create table storedb.item_category
(id_pk integer primary key not null auto_increment,
category_name varchar(250) not null);

drop table if exists storedb.uom ;
create table storedb.uom
(id_pk integer not null auto_increment primary key,
uom_desc varchar(50) not null);

insert into storedb.uom(id_pk,uom_desc) values(1,'kg'),(2,'gm'),(3,'pkt'),(4,'box'),(5,'case'),
(6,'doz'),(7,'sheet'),(8,'bag');


drop table if exists storedb.items;
create table storedb.items 
(id_pk integer primary key not null auto_increment,
item_name varchar(250) unique not null,
barcode varchar(250) not null,
category_id_fk int null,
/*uom_id_fk integer not null,
mrp double not null,
weight double not null,*/

/*weight_unit varchar(50)  null,
actual_price double not null,
selling_price double not null,*/
/*hasfree boolean  null,*/
modified_by int not null,
modified_date timestamp,
CONSTRAINT fk_category_id
	FOREIGN KEY(`category_id_fk`) 
	REFERENCES `item_category`(`id_pk`),
CONSTRAINT fk_modified_by
	FOREIGN KEY(`modified_by`) 
	REFERENCES `users`(`id_pk`)
);

drop table if exists storedb.billing_type;
create table storedb.billing_type
(id_pk integer primary key not null auto_increment,
type_desc varchar(250) not null);


drop table if exists storedb.item_details;
create table storedb.item_details
(id_pk integer primary key not null auto_increment,
item_id_fk integer not null,
uom_id_fk integer not null,
weight double not null,
mrp double not null,
actual_price double not null,
start_range integer  null,
end_range integer  null,
/*retail_billing_price double not null,
wholesale_billing_price double not null,*/
billing_type_id_fk int not null,
hasfree boolean  null,
tax double null,
margin double not null,
enabled boolean not null,
modified_by int not null,
modified_date timestamp,
CONSTRAINT fk_billing_modified_user
	FOREIGN KEY(`modified_by`) 
	REFERENCES `users`(`id_pk`),
CONSTRAINT fk_billing_item_id
	FOREIGN KEY(`item_id_fk`) 
	REFERENCES `items`(`id_pk`),
CONSTRAINT fk_billing_type
	FOREIGN KEY(`billing_type_id_fk`) 
	REFERENCES `billing_type`(`id_pk`),
CONSTRAINT un_start_billing_range UNIQUE (item_id_fk,start_range),
CONSTRAINT un_end_billing_range UNIQUE (item_id_fk,end_range));

drop table if exists storedb.stocks;
create table storedb.stocks
(id_pk integer primary key not null auto_increment,
item_id_fk integer not null,
itemdetails_id_fk integer not null,
quantity bigint not null,
case_quantity bigint not null,
quantity_per_case integer null,
modified_by integer not null,
modified_date timestamp,
CONSTRAINT fk_stocks_item_id
	FOREIGN KEY(`item_id_fk`) 
	REFERENCES `items`(`id_pk`),
CONSTRAINT fk_modified_user
	FOREIGN KEY(`modified_by`) 
	REFERENCES `users`(`id_pk`)
);

drop table if exists storedb.distributor;
create table storedb.distributor
(id_pk integer primary key not null auto_increment,
name varchar(60) not null,
address varchar(100) not null,
phone_number integer(12) not null,
modified_by int not null,
modified_date timestamp,
CONSTRAINT fk_distributor_modified_user
	FOREIGN KEY(`modified_by`) 
	REFERENCES `users`(`id_pk`)
);


drop table if exists storedb.purchase;
create table storedb.purchase
(id_pk integer primary key not null auto_increment,
purchase_number integer not null,
distibutor_id_fk integer not null,
received_date timestamp not null,
modified_by int not null,
modified_date timestamp,
net_amount double not null,
cd double not null,
cd_amount double not null,
total_amount double not null, 
CONSTRAINT fk_purchase_modified_user
	FOREIGN KEY(`modified_by`) 
	REFERENCES `users`(`id_pk`),
CONSTRAINT fk_purchase_distributor_id
	FOREIGN KEY(`distibutor_id_fk`) 
	REFERENCES `distributor`(`id_pk`)
);




drop table if exists storedb.purchase_details;
create table storedb.purchase_details
(id_pk integer primary key not null auto_increment,
purchase_id_fk integer not null,
item_details_id_fk integer not null,
mrp double not null,
units_quantity integer null,
case_quantity integer null,
quantity_per_case integer null,
free_quantity integer null,
basic_rate double not null,
gross_amount double not null,
scheme int not null,
cd double not null,
tax_percentage double not null,
tax double not null,
net_amount double not null,

CONSTRAINT fk_purchasedet_item_id
	FOREIGN KEY(`item_details_id_fk`) 
	REFERENCES `item_details`(`id_pk`),
CONSTRAINT fk_purchase_id
	FOREIGN KEY(`purchase_id_fk`) 
	REFERENCES `purchase`(`id_pk`)
);


drop table if exists storedb.invoice;
create table storedb.invoice
(id_pk integer primary key not null auto_increment,
total_items integer not null,
total_amount double not null,
modified_by int not null,
modified_date timestamp,
CONSTRAINT fk_invoice_modified_user
	FOREIGN KEY(`modified_by`) 
	REFERENCES `users`(`id_pk`)

);




/*drop table if exists storedb.item_mrp;
create table storedb.item_mrp
(id_pk integer primary key not null auto_increment,
item_id_fk integer not null,
mrp double not null,
actual_price double not null,
modified_by int not null,
modified_date timestamp,
CONSTRAINT fk_itemmrp_modified_user
	FOREIGN KEY(`modified_by`) 
	REFERENCES `users`(`id_pk`),
CONSTRAINT fk_itemmrp_item_id
	FOREIGN KEY(`item_id_fk`) 
	REFERENCES `items`(`id_pk`));


*/

/*drop table if exists storedb.billing_price;
create table storedb.billing_price
(id_pk integer primary key not null auto_increment,
item_id_fk integer not null,
mrp double not null,
start_range integer not null,
end_range integer not null,
billing_price double not null,
billing_type_id_fk int not null,
modified_by int not null,
modified_date timestamp,
CONSTRAINT fk_billing_modified_user
	FOREIGN KEY(`modified_by`) 
	REFERENCES `users`(`id_pk`),
CONSTRAINT fk_billing_item_id
	FOREIGN KEY(`item_id_fk`) 
	REFERENCES `items`(`id_pk`),
CONSTRAINT fk_billing_type
	FOREIGN KEY(`billing_type_id_fk`) 
	REFERENCES `billing_type`(`id_pk`),
CONSTRAINT un_start_billing_range UNIQUE (item_id_fk,start_range),
CONSTRAINT un_end_billing_range UNIQUE (item_id_fk,end_range));
*/



drop table if exists storedb.invoice_details;
create table storedb.invoice_details
(id_pk integer primary key not null auto_increment,
invoice_id_fk integer not null,
/*item_id_fk integer not null,*/

itemdetails_id_fk integer not null,
quantity double not null,
billingprice_id_fk integer not null,
margin double not null,
total double not null,
CONSTRAINT fk_invoicedet_invoice_id
	FOREIGN KEY(`invoice_id_fk`) 
	REFERENCES `invoice`(`id_pk`),
/*CONSTRAINT fk_invoice_item_id
	FOREIGN KEY(`item_id_fk`) 
	REFERENCES `items`(`id_pk`),*/
CONSTRAINT fk_invoicedet_itemdetails_id
	FOREIGN KEY(`itemdetails_id_fk`) 
	REFERENCES `item_details`(`id_pk`)


/*,
CONSTRAINT fk_billing_price_id
	FOREIGN KEY(`billingprice_id_fk`) 
	REFERENCES `billing_price`(`id_pk`)
*/
);





INSERT INTO storedb.users
(first_name,last_name,username,password,group_id_fk,user_state)

values('prakash','b','kt','kt',1,1);

/*drop table if exists storedb.billing_price;
create table storedb.billing_price
(id_pk integer primary key not null auto_increment,
item_id_fk integer not null,
start_range1 integer not null,
end_range1 integer not null,
range1_billingprice double not null,
start_range1 integer not null,
end_range1 integer not null,
range2_billingprice double not null,
start_range1 integer not null,
end_range1 integer not null,
range3_billingprice double not null,
billing_price integer not null,
modified_by int not null,
modified_date timestamp,
CONSTRAINT fk_invoice_modified_user
	FOREIGN KEY(`modified_by`) 
	REFERENCES `users`(`id_pk`),

CONSTRAINT fk_item_id
	FOREIGN KEY(`item_id_fk`) 
	REFERENCES `items`(`id_pk`)
);*/




/*insert into storedb.billing_price
(item_id_fk,start_range ,end_range ,billingprice,modified_by,modified_date )
values(1,1,10,15.50,1,'2012-09-20 21:21:04'),
(1,11,20,15.50,1,'2012-09-20 21:21:04'),
(2,1,10,13.50,1,'2012-09-20 21:21:04');
*/



insert into storedb.item_category
(id_pk,category_name) values(1,'Rice'),(2,'Spices');


insert into storedb.billing_type
(type_desc) values('retail'),('wholesale');

insert into storedb.distributor
(name ,
address,
phone_number,
modified_by,
modified_date) values('Jaysree','abc xyz','12343','1','2012-09-20 21:21:04');

drop table if exists storedb.billing_price;
create table storedb.billing_price
(id_pk integer primary key not null auto_increment,
item_details_id_fk integer not null,
start_range integer  null,
end_range integer  null,
billing_price double not null,
modified_by int not null,
modified_date timestamp,
CONSTRAINT fk_billingprice_modified_user
	FOREIGN KEY(`modified_by`) 
	REFERENCES `users`(`id_pk`),
CONSTRAINT fk_billingprice_itemdetails_id
	FOREIGN KEY(`item_details_id_fk`) 
	REFERENCES `item_details`(`id_pk`),
CONSTRAINT un_billing_range UNIQUE (item_details_id_fk,start_range,end_range));



drop table if exists storedb.bag_weight ;
create table storedb.bag_weight
(id_pk integer not null auto_increment primary key,
bag_weight double not null);

insert into storedb.bag_weight(bag_weight) values(25),(30),(50),(100);



/*drop table if exists storedb.weighing_type;
create table storedb.weighing_type
(id_pk integer primary key not null auto_increment,
type_desc varchar(250) not null) ;

insert into storedb.weighing_type
(type_desc) values('Bag'),('Loose'),('Box');

select * from weighing_type;


drop table if exists storedb.category_weighingtype_mapping;
create table storedb.category_weighingtype_mapping
(id_pk integer primary key not null auto_increment,
category_id_fk int not null,
weighing_id_fk int not null,
CONSTRAINT fk_category_id_mapping
	FOREIGN KEY(`category_id_fk`) 
	REFERENCES `item_category`(`id_pk`),
CONSTRAINT fk_weighing_id_mapping
	FOREIGN KEY(`weighing_id_fk`) 
	REFERENCES `weighing_type`(`id_pk`)) ;

insert into storedb.category_weighingtype_mapping
(category_id_fk ,
weighing_id_fk) values(1,1),(1,1),(2,1),(2,1);*/

drop table if exists storedb.category_uom_mapping;
create table storedb.category_uom_mapping
(id_pk integer primary key not null auto_increment,
category_id_fk int not null,
uom_id_fk int not null,
CONSTRAINT fk_category_id_mapping
	FOREIGN KEY(`category_id_fk`) 
	REFERENCES `item_category`(`id_pk`),
CONSTRAINT fk_uom_id_mapping
	FOREIGN KEY(`uom_id_fk`) 
	REFERENCES `uom`(`id_pk`)) ;

insert into storedb.category_uom_mapping
(category_id_fk ,
uom_id_fk) values(1,1),(1,8),(2,1),(2,8);