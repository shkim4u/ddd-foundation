* Windows

```
docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -v c:\data\mysql:/var/lib/mysql mysql:8.0.27
```

* MacOS, Linux
```
docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -v ~/data/mysql:/var/lib/mysql mysql:8.0.27
```


docker start mysql8

docker stop mysql8
