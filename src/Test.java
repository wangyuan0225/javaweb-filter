import java.sql.SQLException;
import java.util.Scanner;

/**
 * @BelongsProject: jdbcDemo
 * @BelongsPackage: PACKAGE_NAME
 * @Author: yuan wang
 * @CreateTime: 2023-01-06  22:39
 * @Description: 测试类，测试以下功能
 * @Version: 1.0
 */
public class Test {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("输入测试方法序号");
			System.out.println("""
					1.添加一个新用户
					2.按新用户名查询用户
					3.修改用户密码
					4.删除新用户
					5.查询并显示所有用户
					6.退出""");
			switch (sc.next()) {
				case "1":
					addNewUser();
					break;
				case "2":
					queryUser();
					break;
				case "3":
					changePassword();
					break;
				case "4":
					deleteUser();
					break;
				case "5":
					queryAll();
					break;
				case "6":
					sc.close();
					System.exit(0);
				default:
					System.out.println("输入错误");
			}
		} while (true);

	}


	/**
	 * @description: 添加一个新用户
	 * @author: yuan wang
	 * @date: 2023/1/7 16:56
	 **/
	public static void addNewUser() {
		User user = new User();
		UserDao userDao = new UserDao();
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入用户名:");
		user.setName(sc.next());
		System.out.print("请输入密码:");
		user.setPassword(sc.next());
		userDao.add(user);

	}

	/**
	 * @description: 按新用户名查询用户
	 * @author: yuan wang
	 * @date: 2023/1/7 19:35
	 **/
	public static void queryUser() {
		UserDao userDao = new UserDao();
		System.out.println("请输入需要查询的姓名");
		Scanner sc = new Scanner(System.in);
		String name = sc.next();
		userDao.query(name);
	}

	/**
	 * @description: 修改用户密码
	 * @author: yuan wang
	 * @date: 2023/1/7 19:43
	 **/
	public static void changePassword() {
		UserDao userDao = new UserDao();
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入待修改密码的用户名");
		String name = sc.next();
		userDao.update(name);
	}

	/**
	 * @description: 删除指定用户
	 * @author: yuan wang
	 * @date: 2023/1/7 19:51
	 **/
	public static void deleteUser() {
		UserDao userDao = new UserDao();
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入需要删除的姓名");
		String name = sc.next();
		userDao.delete(name);
	}



	/**
	 * @description: 查询所有用户
	 * @author: yuan wang
	 * @date: 2023/1/7 19:52
	 **/
	public static void queryAll() {
		UserDao userDao = new UserDao();
		userDao.queryAll();
	}

}
