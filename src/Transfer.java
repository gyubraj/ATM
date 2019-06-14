import java.sql.*;
import java.util.Scanner;

public class Transfer {
	void transferMoney() throws Exception {
		int total,newtotal;
		Scanner sc=new Scanner(System.in);
		Connection cn=DatabaseConnection.database();
		Statement stat=cn.createStatement();
		int i=1;
		do {
		System.out.println("\n\n\t--------------------------------------------------------------------------");
		System.out.print("\n\tYour Account Number:");
		int number=sc.nextInt();
		String query="Select * from accountinfo";
		ResultSet rs=stat.executeQuery(query);
		Boolean hasNext=rs.next();
		while(hasNext) {
			int anumber=rs.getInt("account_no");
			if(number==anumber) {
				do
				{
				System.out.print("\n\tEnter pin:");
				int pin=sc.nextInt();
				query="select * from accountinfo where account_no="+number+"";
			    rs=stat.executeQuery(query);
				rs.next();
				int apin=rs.getInt("bankpin");
				total=rs.getInt("total");
				if(apin==pin) {
				i=1;
					do {
					System.out.print("\n\tReciver Account Number:");
					int raccount=sc.nextInt();
					query="select * from accountinfo";
					rs=stat.executeQuery(query);
					hasNext=rs.next();
					while(hasNext) {
						int aaccount=rs.getInt("account_no");
						int rtotal=rs.getInt("total");
						if(aaccount==raccount) {
						    i=1;
							do {
							System.out.print("\n\tTransfer Amount:");
							int tamount=sc.nextInt();
							if(tamount<=total) {
								newtotal=total-tamount;
								String sql="update accountinfo set transfer="+tamount+",total="+newtotal+" where account_no="+number+"";
								stat.executeUpdate(sql);
								int newAmount=rtotal+tamount;
								sql="update accountinfo set total="+newAmount+" where account_no="+raccount+"";
								stat.executeUpdate(sql);
								sql="insert into ministatement (account_no,transfer,transfer_to,total) values ("+number+","+tamount+","+raccount+","+newtotal+")";
								stat.executeUpdate(sql);
								System.out.print("\n\n\tTransfer Success.");
								System.out.print("\n\n\tLast balance: "+total);
								System.out.print("\n\tCurrent balance:"+newtotal);
								i=4;
								break;	
								
							}else {
								System.out.print("\n\tNot Enough Balance.");
								System.out.println("\n\tEnter again.");
								i++;
								if(i==4) {
									System.out.println("\n\tYou entered wrong amount 3 times.Back to Menu.");
									break;
							}	
							}
	
							
						}while(i<4);
							break;
						}
						else {
							hasNext=rs.next();
							if(!hasNext) {
								System.out.print("\n\tNo user with this Account number");
								i++;
								if(i==4) {
									System.out.println("\n\tYou entered wrong Account number 3 Times.Back to Menu.");
									break;
								}
							}
						}
							
						}
					}while(i<4);
					
				}
				
				else {
					System.out.print("\n\tWrong Pin.\n\tEnter Again.");
					i++;
					if(i==4) {
						System.out.println("\n\tYou Entered wrong pin 3 times so Application halt.\n\tRestart Application and Try again.");
						System.exit(0);
					}
				}
				}while(i<4);
				break;
				
			}else {
				hasNext=rs.next();
				if(!hasNext) {
		       i++;
				System.out.println("\n\tNo user found with this account number:Enter again.");
				if(i==4) {
					System.out.println("\n\n\t--------------------------------------------------------------------------");
					System.out.println("\n\tForget account?Click F for Recover Process");
					char choice=sc.next().charAt(0);
					sc.nextLine();
					if(choice=='F'||choice=='f') {
						ForgetAccount fa=new ForgetAccount();
						fa.forgetAccount();
				}
				}
			}
			
		}
		
	}
	}while(i<4);
	}

}
