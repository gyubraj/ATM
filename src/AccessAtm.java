import java.util.*;
import java.sql.*;
public class AccessAtm {
	public static int number,pin;
	int accessAtm() throws Exception{
		Connection cn=DatabaseConnection.database();
		Statement stat=cn.createStatement();
		Scanner sc=new Scanner(System.in);
		int i=1;
		do {
		System.out.println("\n\n\t--------------------------------------------------------------------------");
		System.out.print("\n\tEnter ATM account Number:");
		number=sc.nextInt();
		String query="select * from accountinfo";
		ResultSet rs=stat.executeQuery(query);
		Boolean hasNext=rs.next();
		while(hasNext) {
			int anumber=rs.getInt("account_no");
			if(number==anumber) {
		query="select pin from accountinfo where account_no="+number+"";
		rs=stat.executeQuery(query);
		rs.next();
		pin=rs.getInt("pin");
		if(pin==0) {
			System.out.println("\n\tContact your Bank to Create Atm.\n\tAccess Denied.");
			return 0;
		}
		else
		{
			do {
			System.out.print("\n\tEnter Your Pin:");
			int  apin=sc.nextInt();
			if(pin==apin) {
				System.out.println("\n\tAccess Done.");
				return 1;			
			}
			else {
				i++;
				System.out.println("\n\tWrong Pin.Enter Again.");
				if(i==4) {
					System.out.println("\n\tYou entered Wrong pin 3 times.Access Denied.");
					return 0;
			}
			}
			}while(i<4);
			
		}
		}
			else {
				hasNext=rs.next();
				if(!hasNext) {
					System.out.println("\n\tWrong Account number .Try again.");
					i++;
					if(i==4)
						System.out.println("\n\tYou reached Maximun try.");
				}
			}
		}
		
	}while(i<4);
		return 0;
	}

}
