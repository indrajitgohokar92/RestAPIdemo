package com.idg.demorestapi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class AlienRepository {
	List<Alien> aliens;
	Connection con = null;
	public AlienRepository(){
		String url = "jdbc:mysql://localhost:3306/restDatabase";
		String user="root";
		String pass="root"; 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pass);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public List<Alien> getAliens(){
		List<Alien> aliens = new ArrayList<>();
		String sql = "select * from aliens";
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				Alien a = new Alien();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));
				aliens.add(a);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return aliens;
	}

	public Alien getAlien(int id){
		Alien a=new Alien();
		String sql = "select * from aliens where id=?";
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()){
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return a;
	}

	public void createAlien(Alien a1){
		String sql = "insert into aliens values(?,?,?)";
		try{
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, a1.getId());
			st.setString(2, a1.getName());
			st.setInt(3, a1.getPoints());
			st.executeUpdate(); 
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void updateAlien(Alien a1){
		String sql = "update aliens set name=?,points=? where id=?";
		try{
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, a1.getName());
			st.setInt(2, a1.getPoints());
			st.setInt(3, a1.getId());
			st.executeUpdate(); 
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void deleteAlien(int id) {
		String sql = "delete from aliens where id=?";
		try{
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			st.executeUpdate(); 
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
