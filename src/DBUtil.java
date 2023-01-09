import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @BelongsProject: jdbcDemo
 * @BelongsPackage: PACKAGE_NAME
 * @Author: yuan wang
 * @CreateTime: 2023-01-06  22:38
 * @Description: 实现连接数据库的功能
 * @Version: 1.0
 */
public class DBUtil {
	private static DataSource dataSource = null;

	private static ThreadLocal<Connection> tl = new ThreadLocal<>();

	static {
		//初始化实例对象
		Properties properties = new Properties();
		InputStream ips = DBUtil.class.getClassLoader().getResourceAsStream("druid.properties");
		try {
			properties.load(ips);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		try {
			dataSource = DruidDataSourceFactory.createDataSource(properties);
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * @description: 创建与数据库的连接
	 * @author: yuan wang
	 * @date: 2023/1/7 15:10
	 * @return: java.sql.Connection
	 **/
	public static Connection getConnection() throws SQLException {

		//查看线程本地变量中是否存在
		Connection connection = tl.get();

		//第一次没有
		if (connection == null) {
			connection = dataSource.getConnection();
			tl.set(connection);
		}
		return connection;
	}

	/**
	 * @description: 断开连接
	 * @author: yuan wang
	 * @date: 2023/1/7 15:10
	 **/
	public static void freeConnection() throws SQLException {

		Connection connection = tl.get();
		if(connection != null){
			tl.remove();
			connection.setAutoCommit(true);//回到默认状态
			connection.close();
		}

		connection.close();
	}
}
