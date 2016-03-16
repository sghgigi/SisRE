package model;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class StudentDAO 
{
	private DataSource dataSource;
	String driver = "org.apache.derby.jdbc.ClientDriver";
	String url = "jdbc:derby://localhost:64413/CSE;user=student;password=secret";
	
	public StudentDAO() throws Exception
	{
		Class.forName(driver);
		this.dataSource = (DataSource) (new InitialContext()).lookup("java://comp/env/jdbc/EECS");
	}
	
	public List<StudentBean> retrieve2(String namePrefix, double gpa) throws Exception
	{
		Connection con = this.dataSource.getConnection();
		Statement s = con.createStatement();
		s.executeUpdate("set schema roumani");
		String query = "Select surname, givenname, major, courses, gpa, YEARADMITTED from SIS where SURNAME like '" +
		namePrefix +
		"' and GPA >= " + gpa;
		ResultSet r = s.executeQuery(query);
		List<StudentBean> list = new ArrayList<StudentBean>();
		while(r.next())
		{
			StudentBean sb = new StudentBean
				(
					r.getString("SURNAME") + ", " + r.getString("GIVENNAME"),
					r.getString("MAJOR"),
					r.getInt("COURSES"),
					r.getDouble("GPA"),
					r.getString("YEARADMITTED")
				);
				
			list.add(sb);
		}
		r.close();
		return list;
	}
	
	public List<StudentBean> retrieve2(String namePrefix, double gpa, String year) throws Exception
	{
		Connection con = this.dataSource.getConnection();
		Statement s = con.createStatement();
		s.executeUpdate("set schema roumani");
		String query = "Select surname, givenname, major, courses, gpa, YEARADMITTED from SIS where SURNAME like '" +
		namePrefix +
		"' and GPA >= " + gpa + " and YEARADMITTED = '" + year+"'";
		ResultSet r = s.executeQuery(query);
		List<StudentBean> list = new ArrayList<StudentBean>();
		while(r.next())
		{
			StudentBean sb = new StudentBean
				(
					r.getString("SURNAME") + ", " + r.getString("GIVENNAME"),
					r.getString("MAJOR"),
					r.getInt("COURSES"),
					r.getDouble("GPA"),
					r.getString("YEARADMITTED")
				);
				
			list.add(sb);
		}
		r.close();
		return list;
	}
	
	public List<StudentBean> retrieve(String namePrefix, double gpa, String sortBy) throws Exception
	{
		Connection con = this.dataSource.getConnection();
		Statement s = con.createStatement();
		s.executeUpdate("set schema roumani");
		String query = "Select surname, givenname, major, courses, gpa, YEARADMITTED from SIS where SURNAME like '" + 
				namePrefix +
				"' and GPA >= " + gpa + " order by " + sortBy;
		ResultSet r = s.executeQuery(query);
		List<StudentBean> list = new ArrayList<StudentBean>();
		while(r.next())
		{
			StudentBean sb = new StudentBean
					(r.getString("SURNAME") + ", " + r.getString("GIVENNAME"),
						r.getString("MAJOR"),
						r.getInt("COURSES"),
						r.getDouble("GPA"),
						r.getString("YEARADMITTED")
					);
			list.add(sb);
		}
		con.close();
		return list;
		
	}
	
	public List<StudentBean> retrieve(String namePrefix, double gpa, String year, String sortBy) throws Exception
	{
		Connection con = this.dataSource.getConnection();
		Statement s = con.createStatement();
		s.executeUpdate("set schema roumani");
		String query = "Select surname, givenname, major, courses, gpa, YEARADMITTED from SIS where SURNAME like '" + 
				namePrefix +
				"' and GPA >= " + gpa + " and YEARADMITTED = '" + year + "' order by " + sortBy;
		System.out.println(query);
		ResultSet r = s.executeQuery(query);
		List<StudentBean> list = new ArrayList<StudentBean>();
		while(r.next())
		{
			StudentBean sb = new StudentBean
					(r.getString("SURNAME") + ", " + r.getString("GIVENNAME"),
						r.getString("MAJOR"),
						r.getInt("COURSES"),
						r.getDouble("GPA"),
						r.getString("YEARADMITTED")
					);
			list.add(sb);
		}
		con.close();
		return list;
		
	}
	//List<Integer> list = new ArrayList<>();
	public List<StudentBean> retrieveyear() throws Exception
	{
		Connection con = this.dataSource.getConnection();
		Statement s = con.createStatement();
		s.executeUpdate("set schema roumani");
		String query = "Select distinct YearAdmitted from SIS order by YearAdmitted";
		ResultSet r = s.executeQuery(query);
		List<StudentBean> list = new ArrayList<StudentBean>();
		while(r.next())
		{	
			StudentBean sb = new StudentBean
					(r.getString("YEARADMITTED"));			
			list.add(sb);
		}
		con.close();
		return list;
	}
	
	/*if (scope.equals("NONE"))
	{
		if(year.equals("NONE"))
		{
			return this.studentDao.retrieve(namePrefix, gpa);
		}
		else
		{
			return this.studentDao.retrieve(namePrefix, gpa , year);
		}
	}else
	{
		if(year.equals("NONE"))
		{
			return this.studentDao.retrieve(namePrefix, gpa, scope);
		}
		else
		{
			return this.studentDao.retrieve(namePrefix, gpa, year, scope);
		}
		
	}*/
	

}
