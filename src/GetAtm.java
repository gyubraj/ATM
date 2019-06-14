import java.sql.*;
import java.util.*;
public class GetAtm {
	void makeAtm() throws Exception {
		Scanner sc=new Scanner(System.in);
		Connection cn=DatabaseConnection.database();
		Statement stat=cn.createStatement();
		System.out.println("\n\n\t--------------------------------------------------------------------------");
		System.out.print("\n\n\tYour Account number:");
		int number=sc.nextInt();
		String query="select account_no from accountinfo";
		ResultSet rs=stat.executeQuery(query);
		Boolean hasNext=rs.next();
		while(hasNext) {
			int anumber=rs.getInt("account_no");
			if(anumber==number) {
				query="select * from accountinfo where account_no="+number+"";
				rs=stat.executeQuery(query);
				rs.next();
				int pin=rs.getInt("bankpin");
				int atmpin=rs.getInt("pin");
				if(atmpin!=0) {
					System.out.println("You already have Atm card.\n");
					break;
				}
				else {
				String sql="update accountinfo set pin="+pin+" where account_no="+number+"";
				stat.executeUpdate(sql);
				System.out.print("\n\n\tYour ATM created with same Bank Account number and pin.\n\n\tYou can change your pin from ATM.");
				System.out.print("\n\tATM card number:"+number);
				System.out.print("\n\tATM pin:"+pin);
				System.out.println();
			    break;
				}
			}
			else
			{
				hasNext=rs.next();
				if(!hasNext) {
					System.out.print("\n\tNo account with "+number+" account Number\n");
					System.out.println("\n\tCreate Bank account to use ATM.\n");
				}
			}
		}
	}

}
