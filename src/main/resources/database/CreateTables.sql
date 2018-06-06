CREATE TABLE `user` (
  `id`       BIGINT      NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(75) NOT NULL UNIQUE,
  `password` VARCHAR(75) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `timetable` (
  `id`                BIGINT        NOT NULL AUTO_INCREMENT,
  `title`             NVARCHAR(256) NOT NULL,
  `description`       NVARCHAR(1024),
  `private`           TINYINT(1)    NOT NULL DEFAULT 0,
  `active`            TINYINT(1)    NOT NULL DEFAULT 0,
  `subscribers_count` INTEGER       NOT NULL DEFAULT 0,
  `use_period`        TINYINT(1)    NOT NULL DEFAULT 0,
  `period_days`       INTEGER       NOT NULL DEFAULT 0,
  `period_weeks`      INTEGER       NOT NULL DEFAULT 0,
  `activated_before`  TINYINT(1)    NOT NULL DEFAULT 0,
  `deleted`           TINYINT(1)    NOT NULL DEFAULT 0,
  `owner_id`          BIGINT        NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_timetable_user_index` (`owner_id` ASC),
  CONSTRAINT `fk_timetable_user`
    FOREIGN KEY (`owner_id`)
    REFERENCES `user` (`id`)
);

CREATE TABLE `event` (
  `id`           BIGINT        NOT NULL AUTO_INCREMENT,
  `title`        NVARCHAR(256) NOT NULL,
  `description`  NVARCHAR(1024),
  `location`     NVARCHAR(512),
  `start_date`   DATETIME      NOT NULL,
  `end_date`     DATETIME      NOT NULL,
  `use_period`   TINYINT(1)    NOT NULL DEFAULT 0,
  `period_days`  INTEGER       NOT NULL DEFAULT 0,
  `period_weeks` INTEGER       NOT NULL DEFAULT 0,
  `timetable_id` BIGINT        NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_event_timetable_index` (`timetable_id` ASC),
  CONSTRAINT `fk_event_timetable`
    FOREIGN KEY (`timetable_id`)
    REFERENCES `timetable` (`id`)
);

CREATE TABLE `subscription` (
  `id`            BIGINT     NOT NULL AUTO_INCREMENT,
  `subscriber_id` BIGINT     NOT NULL,
  `timetable_id`  BIGINT     NOT NULL,
  `muted`         TINYINT(1) NOT NULL DEFAULT 0,
  `banned`        TINYINT(1) NOT NULL DEFAULT 0,
  `approved`      TINYINT(1),
  PRIMARY KEY (`id`),
  INDEX `fk_subscription_user_index` (`subscriber_id` ASC),
  INDEX `fk_subscription_timetable_index` (`timetable_id` ASC),
  CONSTRAINT `fk_subscription_user`
    FOREIGN KEY (`subscriber_id`)
    REFERENCES `user` (`id`),
  CONSTRAINT `fk_subscription_timetable`
    FOREIGN KEY (`timetable_id`)
    REFERENCES `timetable` (`id`)
);

CREATE TABLE `message_text` (
  `id`   BIGINT NOT NULL AUTO_INCREMENT,
  `text` NVARCHAR(1024),
  PRIMARY KEY (`id`)
);

CREATE TABLE `update_message` (
  `id`           BIGINT     NOT NULL AUTO_INCREMENT,
  `timetable_id` BIGINT     NOT NULL,
  `user_id`      BIGINT     NOT NULl,
  `read`         TINYINT(1) NOT NULL DEFAULT 0,
  `text_id`      BIGINT,
  `date`         DATETIME   NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_update_message_timetable_index` (`timetable_id` ASC),
  INDEX `fk_update_message_user_index` (`user_id` ASC),
  CONSTRAINT `fk_update_message_timetable`
    FOREIGN KEY (`timetable_id`)
    REFERENCES `timetable` (`id`),
  CONSTRAINT `fk_update_message_timetable`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`),
  CONSTRAINT `fk_update_message_message_text`
    FOREIGN KEY (`text_id`)
    REFERENCES `message_text` (`id`)
);