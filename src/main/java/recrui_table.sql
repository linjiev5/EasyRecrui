--ユーザログイン表を作成--
create table user_login(
    id serial primary key,
    user_name varchar not null,
    password varchar not null,
    rocked boolean,--flase--
    user_role varchar,
    create_time timestamp,
    update_time timestamp
);
--ユーザ情報表作成--
create table user_info(
    id serial primary key,
    user_name varchar not null,
    icon varchar,--アイコン--
    mail varchar,
    tel varchar,
    sex int,
    adress varchar,
    nearest_station varchar,
    birthday date
);
--会社情報--
create table company_info(
    name varchar primary key,
    demand varchar,--要求--
    mail varchar,
    tel varchar,
    info varchar,--募集詳細--
    founded_time date,--創業時間--
    icon varchar,--アイコン--
    picture varchar,
    adress varchar,
    nearest_station varchar
);
--履歴書--
create table resume(
    name varchar,
    nationality varchar, --国籍--
    nama_katakana varchar, --カタカナ--
    educational_background varchar, --学歴--
    nearest_station varchar,--最寄駅--
    work_experience varchar, --職歴--
    motivation text, --希望動機--
    self_public_relations text, --自己ＰＲ--
    hobbies_and_skills varchar, --趣味・特技--
    licence_or_qualification varchar--免許・資格--
);

--募集詳細--
create table recrui_info(
    name varchar,
    category varchar,
    oosition varchar,
    title varchar,
    salary varchar,--給与--
    welfare varchar,--福利厚生--
    job_description text--募集詳細--
);

