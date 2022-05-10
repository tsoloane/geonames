create table country (
    iso_alpha2 varchar(2) primary key,
    iso_alpha3 varchar(3) not null,
    iso_numeric integer not null,
    fips varchar(5),
    name varchar(200),
    capital varchar(200),
    area decimal,
    population integer,
    continent varchar(2),
    tld varchar(15),
    currency_code varchar(3),
    currency_name varchar(50),
    dialing_code varchar(30),
    postal_code_format varchar(100),
    postal_code_regex varchar(200),
    languages text,
    geo_name_id integer,
    neighbours text,
    equivalent_fips_code text
);

copy country from '/db/countryInfo.txt' CSV DELIMITER E'\t';

create table geo_name
(
    id              integer      not null primary key,
    name            varchar(200) not null,
    ascii_name      varchar(200),
    alternate_names text,
    latitude        decimal,
    longitude       decimal,
    feature_class   char(1),
    feature_code    varchar(10),
    country_code    varchar(2),
    cc2             varchar(200),
    admin1_code     varchar(20),
    admin2_code     varchar(80),
    admin3_code     varchar(20),
    admin4_code     varchar(20),
    population      bigint,
    elevation       integer,
    dem             integer,
    timezone        varchar(40),
    modify_date     date,
    constraint fk_geo_name_country foreign key (country_code) references country(iso_alpha2)
);

create table alternate_name
(
    id             integer primary key,
    geo_name_id    integer      not null,
    iso_language   varchar(7),
    alternate_name varchar(400) not null,
    preferred_name boolean default false,
    short_name     boolean default false,
    colloquial     boolean default false,
    historic       boolean default false,
    from_date      varchar(100),
    to_date        varchar(100),
    constraint fk_altername_geoname foreign key (geo_name_id) references geo_name (id)
);

create table postal_code (
    country_code varchar(2) not null,
    postal_code varchar(20) not null,
    place_name varchar (200) not null,
    state varchar (100),
    state_code varchar (20),
    county varchar (100),
    county_code varchar (20),
    community varchar (100),
    community_code varchar (20),
    latitude decimal,
    longitude decimal,
    accuracy integer,
    primary key (country_code, postal_code, place_name),
    constraint fk_postal_code_country foreign key (country_code) references country(iso_alpha2)
);

create table feature (
    feature_class char(1) not null,
    feature_code varchar(10),
    description varchar(500),
    comments text
);

alter table geo_name add constraint fk_geoname_feature foreign key (feature_class, feature_code)
    references feature(feature_class, feature_code);