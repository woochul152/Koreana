# HOW TO RUN THIS PROJECT

## 0. Setup ini file in mysql
[mysqld]
basedir=C:/mysql-9.5.0-winx64
datadir=C:/mysql-9.5.0-winx64/data
port=3306
bind-address=127.0.0.1

## 1. open CMD
// locate your sql/bin. Mine is in "C:\mysql-9.5.0-winx64\bin".
mysqld --console // this runs your sql server.

## 2. open another CMD
// locate your sql/bin. Mine is in "C:\mysql-9.5.0-winx64\bin".
mysql -u root

ALTER USER 'root'@'localhost' IDENTIFIED BY 'root';
FLUSH PRIVILEGES;
EXIT;

mysql -u root -proot

## 3. Initialize Tables and sample data
this is in Local MySQL.session.sql.
