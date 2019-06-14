import java.sql.*;
public class MiniStatement {
 void miniStatement() throws Exception {
		System.out.println("\n\n\t--------------------------------------------------------------------------");
	 Connection cn=DatabaseConnection.database();
	 int numberOfDatas=0,i=0,b=0;
	 int[] array=new int[6];
	 Statement stat=cn.createStatement();
	 AccessAtm aa=new AccessAtm();
	 int anumber=aa.number;
	 String query="select * from ministatement ";
	 ResultSet rs=stat.executeQuery(query);
	 while(rs.last()) {
		 numberOfDatas=rs.getInt("Sn");
		 b=numberOfDatas;
		 break;
	 }
	 while(numberOfDatas!=0) {
		 query="Select * from ministatement where Sn="+numberOfDatas+"";
		 rs=stat.executeQuery(query);
		 rs.next();
		 int accountNumber=rs.getInt("account_no");
		 if(accountNumber==anumber) {
			 i++;
			 array[i]=numberOfDatas;
		 }
		 if(i==4)
			 break;
		 numberOfDatas--;	 
	 }
	 System.out.println("\n\tDate\t\tTime\t\tMedium\t\tTotal");
	 while(i!=0) {
		 System.out.println("\n\n\t--------------------------------------------------------------------------");
		 query="select * from ministatement where Sn="+array[i]+"";
		 rs=stat.executeQuery(query);
		 rs.next();
		 int total=rs.getInt("total");
		 int deposite=rs.getInt("deposite");
		 int transfer=rs.getInt("transfer");
		 int withdraw=rs.getInt("withdraw");
		 Date adate=rs.getDate("ts");
		 Time dtime=rs.getTime("ts");
		 if(deposite!=0) {
			 System.out.println("\t"+adate+"\t"+dtime+"\tDeposite");
			 System.out.print("\t\t\t\t\t"+deposite);
		 }
		 else if(transfer!=0)
		 {
			 System.out.println("\t"+adate+"\t"+dtime+"\tTransfer");
			 System.out.print("\t\t\t\t\t"+transfer);
		 }
		 else
		 {
			 System.out.println("\t"+adate+"\t"+dtime+"\tWith Draw");
			 System.out.print("\t\t\t\t\t"+withdraw);
		 }
		 System.out.print("\t\t"+total);
		 i--; 
	 } 
	 System.out.println(); 
	 }
}
