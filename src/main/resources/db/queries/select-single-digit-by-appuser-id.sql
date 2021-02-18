SELECT app_user_id, integer, repeat_times, single_digit
FROM SINGLE_DIGIT
WHERE app_user_id = :appUserId;
