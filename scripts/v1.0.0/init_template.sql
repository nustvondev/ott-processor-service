INSERT INTO template (
    event_type, channel_type, title_template, message_template,
    language_code, category, is_push_only
) VALUES (
             'TRANSFER_SUCCESS',
             'PUSH',
             'Chuyển tiền thành công',
             'Giao dịch {{amount}} VND đến {{receiver_name}} đã thành công lúc {{time}}.',
             'vi',
             'PAYMENT',
             FALSE
         );
INSERT INTO template (
    event_type, channel_type, title_template, message_template,
    language_code, category, is_push_only
) VALUES (
             'TRANSFER_SUCCESS',
             'PUSH',
             'Transfer Successful',
             'Your transfer of {{amount}} VND to {{receiver_name}} was successful at {{time}}.',
             'en',
             'PAYMENT',
             FALSE
         );
INSERT INTO template (
    event_type, channel_type, title_template, message_template,
    language_code, category
) VALUES (
             'TRANSFER_FAILED',
             'IN_APP',
             'Chuyển tiền không thành công',
             'Giao dịch {{amount}} VND đến {{receiver_name}} thất bại. Lý do: {{reason}}.',
             'vi',
             'PAYMENT'
         );
INSERT INTO template (
    event_type, channel_type, message_template,
    language_code, category
) VALUES (
             'TRANSFER_FAILED',
             'SMS',
             'GD {{amount}} VND den {{receiver_name}} that bai. Ly do: {{reason}}. LH ngan hang de ho tro.',
             'vi',
             'PAYMENT'
         );
INSERT INTO template (
    event_type, channel_type, title_template, message_template,
    language_code, category, is_push_only
) VALUES (
             'LOGIN_ALERT',
             'PUSH',
             'Cảnh báo đăng nhập',
             'Tài khoản của bạn vừa đăng nhập từ thiết bị {{device}} tại {{location}}.',
             'vi',
             'SECURITY',
             TRUE
         );
INSERT INTO template (
    event_type, channel_type, title_template, message_template,
    language_code, category
) VALUES (
             'LOGIN_ALERT',
             'IN_APP',
             'Login Alert',
             'Your account was accessed from {{device}} at {{location}}.',
             'en',
             'SECURITY'
         );
INSERT INTO template (
    event_type, channel_type, message_template,
    language_code, category
) VALUES (
             'BALANCE_CHANGED',
             'SMS',
             'So du tai khoan cua Quy khach vua thay doi {{amount}} VND. So du hien tai: {{balance}} VND.',
             'vi',
             'ACCOUNT'
         );
INSERT INTO template (
    event_type, channel_type, title_template, message_template,
    language_code, category
) VALUES (
             'BALANCE_CHANGED',
             'IN_APP',
             'Biến động số dư',
             'Số dư tài khoản thay đổi {{amount}} VND. Số dư hiện tại: {{balance}} VND.',
             'vi',
             'ACCOUNT'
         );
INSERT INTO template (
    event_type, channel_type, message_template,
    language_code, category, service_required
) VALUES (
             'OTP_VERIFICATION',
             'SMS',
             'Ma OTP cua Quy khach la {{otp}}. Hieu luc {{ttl}} giay. Vui long khong chia se ma nay.',
             'vi',
             'SECURITY',
             'OTP'
         );

