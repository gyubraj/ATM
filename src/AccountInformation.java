import java.sql.*;
public class AccountInformation{
	void accountInformation() throws Exception {
		AccessAtm aa=new AccessAtm();
		int anumber=aa.number;
		int apin=aa.pin;
		Connection cn=DatabaseConnection.database();
		Statement stat=cn.createStatement();
		String query="select * from accountinfo where account_no="+anumber+"";
		ResultSet rs=stat.executeQuery(query);
		rs.next();
		System.out.println("\n\n\t--------------------------------------------------------------------------");
		System.out.print("\n\tName:"+(rs.getString("firstname")+" "+rs.getString("lastname")));
		System.out.print("\n\tGender:"+rs.getString("gender"));
		System.out.print("\n\tAddress:"+rs.getString("address"));
		System.out.print("\n\tContact:"+rs.getString("contact"));
		System.out.print("\n\tLast Deposite:"+rs.getInt("deposite"));
		System.out.print("\n\tLast WithDraw:"+rs.getInt("withdraw"));
		System.out.print("\n\tLast Transfer:"+rs.getInt("transfer"));
		System.out.print("\n\tCurrent Balance:"+rs.getInt("total"));
		System.out.println();
	}

}