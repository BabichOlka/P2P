# P2P
# Chat

## Preparation

### In Terminal
1) git clone *https://github.com/BabichOlka/P2P.git*

### In MySql
1) go to project folder
2) go to DB
3) run script from ***server.sql*** and ***client.sql*** 

### Open project in IDEA
1) setup sdk
2) open ***src/main/resources/mybatisconfig.xml***
	change **root** to your username in musql on line 12
	change **root** to your password in musql on line 13
3) open ***src/main/resources/mybatisconfigServer.xml***
	change **root** to your username in musql on line 12
	change **root** to your password in musql on line 13


#### COMMENTS
* for correct work MySQL set in the command line next configuration:
 SET GLOBAL max_connections = 1500;
 SET GLOBAL max_user_connections=110000;
