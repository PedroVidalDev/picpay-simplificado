create table transactions(
    id bigint not null auto_increment,
    payer_id bigint not null,
    payee_id bigint not null,
    value float not null,

    primary key(id),
    constraint fk_schedules_payer_id foreign key (payer_id) references users(id),
    constraint fk_schedules_payee_id foreign key (payee_id) references users(id)
);