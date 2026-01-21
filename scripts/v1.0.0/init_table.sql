CREATE TYPE category_enum AS ENUM (
    'ACCOUNT',
    'PAYMENT',
    'PROMOTION',
    'SECURITY',
    'SYSTEM'
);

CREATE TYPE channel_type_enum AS ENUM (
    'PUSH',
    'SMS',
    'EMAIL',
    'IN_APP'
);

CREATE TYPE status_enum AS ENUM (
    'PENDING',
    'SENT',
    'FAILED',
    'RETRY',
    'DROPPED'
);

CREATE TYPE event_type_enum AS ENUM (
    'SYSTEM',
    'INSIDER',
    'MANUAL'
);

CREATE TYPE insider_event_enum AS ENUM (
    'LOGIN',
    'TRANSFER',
    'PAYMENT',
    'PROMOTION'
);

CREATE TABLE in_app_notification (
                                     id BIGSERIAL PRIMARY KEY,
                                     recipient_id BIGINT,
                                     event_id VARCHAR(100),
                                     title_vi TEXT,
                                     message_vi TEXT,
                                     title_en TEXT,
                                     message_en TEXT,
                                     category category_enum,
                                     event_type VARCHAR(100),
                                     read BOOLEAN DEFAULT FALSE,
                                     additional_data JSONB NOT NULL DEFAULT '{}'::jsonb,
                                     created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
                                     last_updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE INDEX idx_in_app_recipient ON in_app_notification (recipient_id);
CREATE INDEX idx_in_app_event_id ON in_app_notification (event_id);


CREATE TABLE insider_event_log (
                                   id BIGSERIAL PRIMARY KEY,
                                   partner_id VARCHAR(100),
                                   product_type VARCHAR(100),
                                   hook_id VARCHAR(100),
                                   insider_id VARCHAR(100),
                                   partner VARCHAR(100),
                                   timestamp BIGINT,
                                   triggers TEXT,
                                   phone_number VARCHAR(50),
                                   unique_user_id VARCHAR(100),
                                   event_name insider_event_enum,
                                   parameter JSONB NOT NULL DEFAULT '{}'::jsonb,
                                   created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE INDEX idx_insider_event_name ON insider_event_log (event_name);
CREATE INDEX idx_insider_created_at ON insider_event_log (created_at);

CREATE TABLE notification_metadata (
                                       id BIGSERIAL PRIMARY KEY,
                                       event_id VARCHAR(50) NOT NULL,
                                       event_issuer VARCHAR(100),
                                       event_type event_type_enum,
                                       status status_enum NOT NULL,
                                       channel_type channel_type_enum,
                                       created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
                                       error_description TEXT,
                                       recipient BIGINT,
                                       data JSONB NOT NULL DEFAULT '{}'::jsonb,
                                       provider_response JSONB,
                                       additional_data JSONB NOT NULL DEFAULT '{}'::jsonb
);

CREATE INDEX idx_notification_event_id ON notification_metadata (event_id);
CREATE INDEX idx_notification_status ON notification_metadata (status);
CREATE INDEX idx_notification_channel ON notification_metadata (channel_type);


CREATE TABLE push_notification_setting (
                                           id BIGSERIAL PRIMARY KEY,
                                           recipient_id BIGINT NOT NULL,
                                           category category_enum,
                                           enabled BOOLEAN DEFAULT TRUE,
                                           created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
                                           last_updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE UNIQUE INDEX ux_push_setting_user_category
    ON push_notification_setting (recipient_id, category);


CREATE TABLE push_notification_token (
                                         id BIGSERIAL PRIMARY KEY,
                                         device_id VARCHAR(100),
                                         firebase_token TEXT,
                                         platform_endpoint_arn TEXT,
                                         created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
                                         last_updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE INDEX idx_push_token_device ON push_notification_token (device_id);


CREATE TABLE sms_fees_settings (
                                   id BIGSERIAL PRIMARY KEY,
                                   cif_number VARCHAR(50) NOT NULL,
                                   phone_number VARCHAR(50) NOT NULL,
                                   account_number VARCHAR(50) NOT NULL,
                                   account_type VARCHAR(100) NOT NULL,
                                   is_deleted BOOLEAN DEFAULT FALSE,
                                   is_active BOOLEAN DEFAULT TRUE,
                                   is_billable BOOLEAN DEFAULT TRUE,
                                   created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
                                   updated_at TIMESTAMPTZ
);

CREATE INDEX idx_sms_fee_cif ON sms_fees_settings (cif_number);
CREATE INDEX idx_sms_fee_phone ON sms_fees_settings (phone_number);


CREATE TABLE sms_phone_changes_history (
                                           id BIGSERIAL PRIMARY KEY,
                                           fee_setting_id BIGINT NOT NULL,
                                           previous_phone_number VARCHAR(50) NOT NULL,
                                           cif_number VARCHAR(50) NOT NULL,
                                           created_at TIMESTAMP NOT NULL DEFAULT now(),
                                           created_by VARCHAR(100)
);

CREATE INDEX idx_sms_change_fee_id ON sms_phone_changes_history (fee_setting_id);



CREATE TABLE template (
                          id BIGSERIAL PRIMARY KEY,
                          event_type VARCHAR(100) NOT NULL,
                          channel_type channel_type_enum NOT NULL,
                          title_template TEXT,
                          message_template TEXT NOT NULL,
                          language_code CHAR(2) NOT NULL,
                          category category_enum,
                          service_required VARCHAR(50) DEFAULT '',
                          is_push_only BOOLEAN,
                          customer_tenant VARCHAR(100),
                          created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
                          last_updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE INDEX idx_template_event_channel_lang
    ON template (event_type, channel_type, language_code);
