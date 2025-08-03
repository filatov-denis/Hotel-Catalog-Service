create table hotel (
    id bigint primary key,
    name varchar(250) not null,
    description text,
    brand varchar(250) not null,
    phone varchar(50) not null,
    email varchar(250) not null,
    check_in time not null,
    check_out time
);

create index hotel_brand_idx on hotel(brand);

create table address (
    id bigint primary key,
    house_number smallint,
    street varchar(250) not null,
    city varchar(250) not null,
    country varchar(250) not null,
    post_code varchar(250) not null,
    hotel_id bigint references hotel(id)
);

create index address_city_idx on address(city);
create index address_country_idx on address(country);

create table amenity (
    id bigint primary key,
    name varchar(250) not null unique
);

create table hotel_amenity (
    id bigserial primary key,
    hotel_id bigint not null references hotel(id),
    amenity_id bigint not null references amenity(id)
);
