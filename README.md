# Simple CRUD operations (Spring 5 + HIbernate 5 + VUE.js)

**Note:** all of codes in this repository were only for my personal practice purposes, some design patterns maybe not so correct, DO NOT use it as your tutorial

**注意:** 這裡的所有代碼只是我拿來自我練習，裡面許多設計模式也許不那麼正確，請勿拿來當你的編程教材

## Requirements
Develop couples Restful APIs for basic CRUD operation , and search data by keyword  
related HTTP verbs and API endpoints are as follows :

設計一組 Restful API 讓使用者完成簡易的CRUD操作及關鍵字模糊搜尋    
使用的HTTP verbs 與 API 端點如下:
- GET     /employee/{id}
- GET     /employees
- POST    /employees/search/{name}
- POST    /employee
- PUT     /employee
- DELETE  /employee

and design a user interface for comsuming these Restful API  more convenient  
Html + css + VUE.js is preferred

然後設計一個用戶介面來方便操作這些Restful API  
最好能使用HTML + css + VUE.js這些技術來完成它

## API detailed specifications
![API detailed specifications](images/api_manual.pdf?raw=true "Title")  


## How to build this project ?

Via Maven command:

``` bash
$ mvn clean
$ mvn package
```
then you will find apiez02.war in target sub-directory  


## Database Structure  
don't worry about the database structure , Hibernate will generate a table for you,  
it's a simple table called 'TB_EMPLOYEE' , as its name, it stores some mock employees data  
database table structure like this :  

不要擔心底層資料表的結構 , HIbernate會為你產生它,  
是一個簡單的資料表叫做 'TBL_EMPLOYEE' , 如它的表名 , 它會用來儲存一些假員工的資料  

``` 
MariaDB [db_spring]> desc TBL_EMPLOYEE;
+-------------+--------------+------+-----+---------+----------------+
| Field       | Type         | Null | Key | Default | Extra          |
+-------------+--------------+------+-----+---------+----------------+
| id          | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| address     | varchar(200) | NO   |     | NULL    |                |
| birthday    | date         | NO   |     | NULL    |                |
| luckyNumber | int(11)      | NO   |     | NULL    |                |
| name        | varchar(100) | NO   |     | NULL    |                |
| online      | tinyint(4)   | NO   |     | NULL    |                |
+-------------+--------------+------+-----+---------+----------------+
6 rows in set (0.001 sec)


```

## Try it on dq5rocks.com  && ScreenShot

click [here](https://www.dq5rocks.com/apiez02/ "Title") try it out  
this link probobaly may become unavailable in someday future,   
if i could not pay for domain name or vps payment

在 [這裡](https://www.dq5rocks.com/apiez02/ "Title") 直接玩  
連結可能在未來有天會失效，如果我繳不出域名或VPS主機的錢  


取一個 ( get one )    
![get one](https://www.dq5rocks.com/images/202008181644/1_getOne.jpg?raw=true "Title")  

  
取全部 ( get all )  
![get all](https://www.dq5rocks.com/images/202008181644/2_getAll.jpg?raw=true "Title")  
  
  
用姓名搜尋 ( search by name )    
![search by name](https://www.dq5rocks.com/images/202008181644/3_searchByName.jpg?raw=true "Title")  
  

新增員工 ( create )    
![create](https://www.dq5rocks.com/images/202008181644/4_createEmployee.jpg?raw=true "Title")  


修改員工 ( update )    
![update](https://www.dq5rocks.com/images/202008181644/5_updateEmployee.jpg?raw=true "Title")  


刪除員工 ( delete )    
![delete](https://www.dq5rocks.com/images/202008181644/6_deleteEmployee.jpg?raw=true "Title")    
  
  
this above link probobaly may become unavailable in someday future,   
if i could not pay for domain name or vps payment  

上面的連結可能在未來有天會失效，如果我繳不出域名或VPS主機的錢  


## Contributing  
可愛的Me ，不是做UI的所以很簡陋只有功能，咿

## Donate me

可以捐錢給我喔，i need your support  

- [國泰世華銀行收款帳號](#CathayBank) **銀行代碼 013 帳號 001-50-235346-9 戶名 KUN AN HSU 館前分行**  

- [Alipay(支付寶)](#alipay) **annbigbig@gmail.com**  

- [BitCoin](#Bitcoin)  ![BitcoinIcon](images/Bitcoin.png?raw=true "Thank you")

  **1FGEtWkZpo3WHzQqDw6aJvsaDyxNmX4H9**

- [Ethereum](#Ethereum)  ![EthereeumIcon](images/Ethereum.png?raw=true "Thank you")  
  **0x36077A217819cf747F938EbFad26Ec50e44cDC48**

## Contact me

- **annbigbig@gmail.com**   
2020年7月，又開始寫扣，祝我好運

## License

None. 無


