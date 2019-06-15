import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class MiniStatement {
 void miniStatement() throws Exception {
	 Document doc=new Document();
	 LocalDate localDate= LocalDate.now();
	 System.out.println("\n\n\t--------------------------------------------------------------------------");
	 Connection cn=DatabaseConnection.database();
	 int numberOfDatas=0,i=0,b=0;
	 int[] array=new int[6];
	 Statement stat=cn.createStatement();
	 AccessAtm aa=new AccessAtm();
	 int anumber=aa.number;
	 File file = new File("C:/Users/Public/BankPDF/"+anumber+".pdf");
	 file.createNewFile();
	 PdfWriter.getInstance(doc, new FileOutputStream(file));
	 doc.open();
	 doc.add(new Paragraph("                                                                    Hamro Aafnai Bank"));
	 doc.add(new Paragraph("                                                                                                                      "+DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate)));
	 String query="select firstname,lastname from accountinfo where account_no="+anumber+"";
	 ResultSet rs=stat.executeQuery(query);
	 rs.next();
	 doc.add(new Paragraph(" "));
	 doc.add(new Paragraph(" "));
	 String name=rs.getString("firstname")+" "+rs.getString("lastname");
	 doc.add(new Paragraph("Account number  :"+anumber));
	 doc.add(new Paragraph("Name            :"+name));
	 doc.add(new Paragraph(" "));
	 doc.add(new Paragraph(" "));
	 doc.add(new Paragraph("Your Last four Transation is:"));
	 doc.add(new Paragraph(" "));
	 doc.add(new Paragraph(" "));
	 query="select * from ministatement ";
	  rs=stat.executeQuery(query);
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
	 float[] columnwidth= {100f,100f,100f,100f};
	 PdfPTable table =new PdfPTable(columnwidth);
	 table.addCell("Date");
	 table.addCell("Time");
	 table.addCell("Medium");
	 table.addCell("Total");
	 int total=0;
	 while(i!=0) {
		 System.out.println("\n\n\t--------------------------------------------------------------------------");
		 query="select * from ministatement where Sn="+array[i]+"";
		 rs=stat.executeQuery(query);
		 rs.next();
		 total=rs.getInt("total");
		 int deposite=rs.getInt("deposite");
		 int transfer=rs.getInt("transfer");
		 int withdraw=rs.getInt("withdraw");
		 Date adate=rs.getDate("ts");
		 Time dtime=rs.getTime("ts");
		 table.addCell(""+adate+"");
		 table.addCell(""+dtime+"");
		 
		 if(deposite!=0) {
			 System.out.println("\t"+adate+"\t"+dtime+"\tDeposite");
			 System.out.print("\t\t\t\t\t"+deposite);
			 table.addCell("Deposite:\n"     +deposite+"");
		 }
		 else if(transfer!=0)
		 {
			 System.out.println("\t"+adate+"\t"+dtime+"\tTransfer");
			 System.out.print("\t\t\t\t\t"+transfer);
			 table.addCell("Transfer:\n"     +transfer+"");
		 }
		 else if(withdraw!=0)
		 {
			 System.out.println("\t"+adate+"\t"+dtime+"\tWith Draw");
			 System.out.print("\t\t\t\t\t"+withdraw);
			 table.addCell("WithDraw:\n"     +withdraw+"");
		 }
		 else {
			 System.out.println("\n\n\n\n\n\tYou have done No Transation");
			 doc.add(new Paragraph("No Transation. "));
			 break;
		 }
		 System.out.print("\t\t"+total);
		 table.addCell(""+total+"");
		 i--; 
	 } doc.add(table);
	 doc.add(new Paragraph(" "));
	 doc.add(new Paragraph(" "));
	 doc.add(new Paragraph(" "));
	 doc.add(new Paragraph("Your Current balance is: Rs."+total));
	 doc.add(new Paragraph("                                                          THANK YOU FOR USING OUR SERVICE!!!"));
	 System.out.println(); 
	 
	 doc.close();
	 
	 }
}
