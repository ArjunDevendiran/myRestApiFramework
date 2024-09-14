package api.endpoint;

public class UserRoute {
	
	public static String base_uri = "https://petstore.swagger.io/v2";
	
	//User Module
	public static String create_user_url = base_uri + "/user";
	public static String get_user_url = base_uri + "/user/{username}";
	public static String update_user_url = base_uri + "/user/{username}";
	public static String delete_user_url = base_uri + "/user/{username}";

}
