import java.util.*;
import java.sql.*;
public class WithDraw {
	
	void withDrawMoney() throws Exception{
		Scanner sc=new Scanner(System.in);
		System.out.println("\n\n\t--------------------------------------------------------------------------");
		Connection cn=DatabaseConnection.database();
		Statement stat=cn.createStatement();
		AccessAtm aa=new AccessAtm();
		int anumber=aa.number;
		int apin=aa.pin;
		String query="select total from accountinfo where account_no="+anumber+"";
		ResultSet rs=stat.executeQuery(query);
		rs.next();
		int total=rs.getInt("total");
		if(total==0)
			System.out.println("\n\tNo balance.");
		else {
			int i=1;
			do {
			System.out.print("\n\tWithdraw Amount:");
			int amount=sc.nextInt();
			if(total<amount) {
				System.out.println("\n\tYou don't have sufficient Balance.\n\tEnter Valid Amount.\n");
				i++;
				if(i==4)
					System.out.println("\n\tYou Entered wrong amount 3 times.Back to Menu.");
			}
			else {
				int newtotal=total-amount;
				String sql="Update accountinfo set total="+newtotal+",withdraw="+amount+" where account_no="+anumber+"";
				stat.executeUpdate(sql);
		        sql="insert into ministatement (account_no,withdraw,total) values ("+anumber+","+amount+","+newtotal+")";
		        stat.executeUpdate(sql);
				System.out.println("\n\tWithDraw Success.");
				System.out.println("\n\tLast Balance:"+total);
				System.out.println("\n\tCurrent Balance:"+newtotal);
				break;
			}
			}while(i<4);
			
		}
		
	}

}
