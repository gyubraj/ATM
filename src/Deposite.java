import java.sql.*;
import java.util.*;
public class Deposite {
	void depositeAmount() throws Exception {
		Scanner sc=new Scanner(System.in);
		System.out.println("\n\n\t--------------------------------------------------------------------------");
		Connection cn=DatabaseConnection.database();
		Statement stat=cn.createStatement();
		System.out.print("\n\tDo you have Bank Account?(Y/N)");
		char check=sc.next().charAt(0);
		if(check=='N'||check=='n') {
			CreateAccount ca=new CreateAccount();
			ca.createAccount();
		}
		int i=1,lasttotal,total;
		do
		{
	    System.out.println("\n\n\t--------------------------------------------------------------------------");	
		System.out.print("\n\tEnter your Account number:");
		int number=sc.nextInt();
		String query="select account_no from accountinfo";
		ResultSet rs=stat.executeQuery(query);
		Boolean hasnext=rs.next();
		while(hasnext) {
			int anumber=rs.getInt("account_no");
			if(anumber==number) {
				System.out.print("\n\tDeposite Amount:");
				int amount=sc.nextInt();
				query="select * from accountinfo where account_no="+number+"";
				rs=stat.executeQuery(query);
				rs.next();
				lasttotal=rs.getInt("total");
				total=lasttotal+amount;
				String sql="update accountinfo set total="+total+",deposite="+amount+" where account_no="+number+"";
				stat.executeUpdate(sql);
				sql="insert into ministatement (account_no,deposite,total) values ("+number+","+amount+","+total+")";
				stat.executeUpdate(sql);
				System.out.print("\n\n\tYour Last Balance:"+lasttotal+"\n\n\tYour Current Balance:"+total+"");
				System.out.println();
				i=4;
				break;	
			}
			else {
				hasnext=rs.next();
				if(!hasnext) {
				System.out.println("\n\tAccount number not matched.");
				System.out.println("\n\tEnter Again:");
				i++;
				}
				
			}
			if(i==4) {
				System.out.println("\n\n\t--------------------------------------------------------------------------");
				System.out.println("\n\tForget account?Click F for Recover Process");
				char choice=sc.next().charAt(0);
				if(choice=='F'||choice=='f') {
					ForgetAccount fa=new ForgetAccount();
					fa.forgetAccount();
			}
				
		}
		}
		
		
			
	}while(i<4);

}
}
