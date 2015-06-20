package Appn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AutoSet
{
	public void check(String name,String type,String server)
	{
		boolean exist=false;
		try
		{
			DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
			Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@"+server,"system","redhat" );
			PreparedStatement st = conn.prepareStatement("Select * from LIST WHERE name= '"+name+"'");
			ResultSet rs = st.executeQuery();
			while(rs.next())
			{
				exist=true;
			}
			if(!exist)
			{
				String query="insert into LIST values('"+name+"','"+type+"')";
				st.executeUpdate(query);

			}
			st.close();
		conn.close();

	}
	catch(Exception e)
	{
		e.printStackTrace();

	}


	}


}