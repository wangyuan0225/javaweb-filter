import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

/**
 * @BelongsProject: jdbcDemo
 * @BelongsPackage: PACKAGE_NAME
 * @Author: yuan wang
 * @CreateTime: 2023-01-06  22:38
 * @Description: 负责提供方法对User表进行增查删改等所有操作，其它类需要对User表进行增查删改等操作时直接该使用该类
 * @Version: 1.0
 */
public class UserDao {
	/**
	 * @description: 添加一个用户的信息到User表中，如果用户已经存在则不重复添加
	 * @author: yuan wang
	 * @date: 2023/1/7 15:18
	 * @param: user
	 **/
	public void add(User user) {
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();
			connection.setAutoCommit(false);

			String sql = "insert into userInfo (name, password) values (?, ?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setObject(1, user.getName());
			statement.setObject(2, user.getPassword());
			int i = statement.executeUpdate();

			System.out.println("插入数据成功！\n------------------");
			connection.commit();
		} catch (SQLException e) {
			System.out.println("用户名重复\n------------------");
			try {
				connection.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} finally {
			try {
				DBUtil.freeConnection();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}


	/**
	 * @description: 按用户姓名查找用户
	 * @author: yuan wang
	 * @date: 2023/1/7 15:21
	 * @param: name
	 **/
	public void query(String name) {
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();
			connection.setAutoCommit(false);

			String sql = "select * from userInfo where name = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setObject(1, name);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				System.out.println("id--" + resultSet.getObject(1) + "  name--" + resultSet.getObject(2) + "  password--" + resultSet.getObject(3));
				System.out.println("查询成功！\n------------------");
			} else {
				System.out.println("无此用户信息");
			}
			connection.commit();
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			try {
				System.out.println("查询失败！\n------------------");
				connection.rollback();
			} catch (SQLException ex) {
				e.printStackTrace();
			}
		} finally {
			try {
				DBUtil.freeConnection();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}


	/**
	 * @description: 按ID查找用户
	 * @author: yuan wang
	 * @date: 2023/1/7 15:22
	 * @param: id
	 **/
	public void query(int id) {
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();
			connection.setAutoCommit(false);

			String sql = "select * from userInfo where id = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setObject(1, id);
			int i = statement.executeUpdate();

			System.out.println("查询成功！\n------------------");
			connection.commit();
		} catch (SQLException e) {
			try {
				System.out.println("查询失败！\n------------------");
				connection.rollback();
			} catch (SQLException ex) {
				e.printStackTrace();
			}
		} finally {
			try {
				DBUtil.freeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	/**
	 * @description: 根据用户名删除某个用户
	 * @author: yuan wang
	 * @date: 2023/1/7 15:24
	 * @param: name
	 **/
	public void delete(String name) {
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();
			connection.setAutoCommit(false);

			String sql = "delete from userInfo where name = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setObject(1, name);
			int i = statement.executeUpdate();
			if (i != 0) {
				System.out.println("删除成功！\n------------------");
				connection.commit();
			} else {
				System.out.println("没有此用户\n------------------");
			}
		} catch (SQLException e) {
			System.out.println("删除失败！\n------------------");
			try {
				connection.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} finally {
			try {
				DBUtil.freeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @description: 根据编号删除某个用户
	 * @author: yuan wang
	 * @date: 2023/1/7 15:36
	 * @param: id
	 **/
	public void delete(int id) {
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();
			connection.setAutoCommit(false);

			String sql = "delete from userInfo where id = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setObject(1, id);
			int i = statement.executeUpdate();

			System.out.println("删除成功！\n------------------");
			connection.commit();
		} catch (SQLException e) {
			System.out.println("删除失败！\n------------------");
			try {
				connection.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} finally {
			try {
				DBUtil.freeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @description: 修改密码
	 * @author: yuan wang
	 * @date: 2023/1/7 15:38
	 * @param: name
	 **/
	public void update(String name) {
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();
			connection.setAutoCommit(false);

			Scanner sc = new Scanner(System.in);
			do {
				System.out.print("请输入更改后的密码：");
				String newPassword = sc.next();

				String sql1 = "select password from userInfo where name = ?;";
				PreparedStatement statement1 = connection.prepareStatement(sql1);
				statement1.setObject(1, name);
				ResultSet resultSet = statement1.executeQuery();

				if (resultSet.next()) {
					if (!Objects.equals(newPassword, resultSet.getString(1))) {
						String sql2 = "update userInfo set password = ? where name = ?;";
						PreparedStatement statement2 = connection.prepareStatement(sql2);
						statement2.setObject(1, newPassword);
						statement2.setObject(2, name);
						int i = statement2.executeUpdate();
						System.out.println("更改成功！\n------------------");
						connection.commit();
						break;
					} else {
						System.out.println("前后两次密码输入相同，请输入不同的密码");
					}
				} else {
					System.out.println("无此用户\n------------------");
					break;
				}
			} while (true);


		} catch (SQLException e) {
			System.out.println("更改失败！\n------------------");
			try {
				connection.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} finally {
			try {
				DBUtil.freeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * @description: 遍历并显示所有用户信息
	 * @author: yuan wang
	 * @date: 2023/1/7 15:38
	 **/
	public void queryAll() {
		Connection connection = null;
		boolean empty = true;
		try {
			connection = DBUtil.getConnection();
			connection.setAutoCommit(false);

			String sql = "select id, name, password from userInfo;";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Object id = resultSet.getObject(1);
				Object name = resultSet.getObject(2);
				Object password = resultSet.getObject(3);
				System.out.println(id + "--" + name + "--" + password);
				empty = false;
			}

			connection.commit();
			if (empty) {
				System.out.println("无用户\n------------------");
			} else {
				System.out.println("查询成功！\n------------------");
			}
		} catch (SQLException e) {
			System.out.println("查询失败！\n------------------");
			try {
				connection.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} finally {
			try {
				DBUtil.freeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
