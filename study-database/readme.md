mongo：
    UserDaoImpl 使用 MongoTemplate 进行单个的增删改查
    UserMutiDaoImpl
        使用 MongoTemplate 进行批量操作  xq:不过这个操作不确认是不是一次性的
        使用 MongoClient 进行批量更新   xq:这个是一次性的，是json操作
mongo_repository：
    使用 MongoRepository 对mongo进行操作

mybatis1：   纯xml方式使用, 连接使用了数据库属性配置文件; mapper使用了xml和dao两种方式
mybatis4:    1对1(post-user)，1对多的查询(user-post)
mybatis5:    多对多关系User-Group-UserGroup
mybatis-xml         xml方式+注解方式
mybatis-annotation:    使用spring方式进行 操作
mybatis-mulidatasource  又数据源方式
mybatis-flyway:         flyway的 + mybatis anotation方式

sql_jdbc_demo
    使用 JdbcTemplate 对sql进行操作
sql_jpa1_demo
    使用 Repository，JpaRepository 进行sql操作
sql_jpa2_rest_demo
    使用 RepositoryRestResource,JpaRepository实现数据库的数据通过 rest接口进行操作，不需要自己实际接口
    参考：https://www.jianshu.com/p/3423fa97d185
transaction_demo
    JpaRepository+Transactional 事物的实现