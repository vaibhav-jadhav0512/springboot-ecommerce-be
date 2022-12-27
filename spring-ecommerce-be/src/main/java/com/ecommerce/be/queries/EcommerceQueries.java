package com.ecommerce.be.queries;

public class EcommerceQueries {

	public static final String CREATE_OR_UPDATE_USER = "INSERT INTO ecom.`user` (uid, name, email, picture,emailVerified) VALUES(:uid, :name, :email, :picture,:emailVerified) "
			+ "ON DUPLICATE KEY UPDATE name=:name, email=:email, picture=:picture, emailVerified=:emailVerified";
	public static final String GET_USER_BY_ID = "SELECT uid, name, email, `role`, picture, emailVerified "
			+ "FROM ecom.`user` WHERE uid=:uid";
	public static final String GET_ADMIN_BY_ID = "SELECT uid, name, email, `role`, picture, emailVerified "
			+ "FROM ecom.`user` WHERE uid=:uid AND role='admin'";
	public static final String GET_ALL_CATEGORIES = "SELECT slug, name FROM ecom.category";
	public static final String GET_CATEGORY_BY_ID = "SELECT slug, name FROM ecom.category WHERE slug=:slug";
	public static final String SAVE_CATEGORY = "INSERT INTO ecom.category (slug,name) VALUES(:slug,:name)";
	public static final String UPDATE_CATEGORY = "UPDATE ecom.category SET name=:name,slug=:slugUpdate WHERE slug=:slug";
	public static final String DELETE_CATEGORY = "DELETE FROM ecom.category WHERE slug=:slug";

}
