/**
 * @BelongsProject: jdbcDemo
 * @BelongsPackage: PACKAGE_NAME
 * @Author: yuan wang
 * @CreateTime: 2023-01-06  22:38
 * @Description: 对应于数据库的userInfo表，其成员变量和数据库字段相对应，提供对成员变量操作的方法
 * @Version: 1.0
 */
public class User {

	private int id;
	private String name;
	private String password;

	public User() {
	}

	public User(int id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
