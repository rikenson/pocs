create
database pocs_db
use pocs_db

create table sample
(
    uuid            varchar(255) not null,
    name            varchar(255) not null,
    description     text         not null,
    start_date_time timestamp    not null,
    end_date_time   timestamp    not null,
    status          text         not null,
    version         integer      not null,
    created_at      timestamp    not null,
    modified_at     timestamp    not null,
    created_by   varchar(255) not null,
    modified_by  varchar(255),
    primary key (uuid)
)