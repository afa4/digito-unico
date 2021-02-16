UPDATE APP_USER
SET name  = :name,
    email = :email
WHERE uid = :uid;