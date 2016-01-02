hibernate {
	cache.use_second_level_cache = true
	cache.use_query_cache = false
	cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
		dataSource {
		  dbCreate = "update"		// create or update
		  logSql = true
		  driverClassName = "com.mysql.jdbc.Driver"
		  dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
		  type = "javax.sql.DataSource"
		  auth = "Container"
		  url = "jdbc:mysql://localhost:3306/restDB"//"jdbc:mysql://localhost:3306/atns", "jdbc:mysql://dbcrpmysqldev02:3322/atns",
		  username = "root"//"root","atns",
		  password = "root"//"root","b1=f2z5t",
		  driverClassName = "com.mysql.jdbc.Driver"
		  dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
		  maxActive = "8"
		  maxIdle = "4"
		}		
	}	
}
