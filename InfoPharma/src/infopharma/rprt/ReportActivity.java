/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infopharma.rprt;

import com.itextpdf.text.*;
import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.crypto.provider.DESKeyFactory;
import com.sun.tools.javac.jvm.Gen;
import infopharma.data.OrderDBAccess;
import infopharma.data.RprtDBAccess;
import infopharma.data.UserAccount;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ReportActivity extends GeneralReport
{
        private static String startDate;
        private static String endDate;
        private RprtDBAccess rprtDBAccess;
        private OrderDBAccess orderDBAccess;
        public String documentName;

        
	private static String FILE = "_activity.pdf";

	public ReportActivity(String accountNumber, String startDate, String endDate) 
        {
            rprtDBAccess = new RprtDBAccess();
            orderDBAccess = new OrderDBAccess();
            
            
            this.startDate = startDate;
            this.endDate = endDate;
		try 
                {  
                    Document document = new Document();

                    Date date= new java.util.Date();
                    long timeStamp = date.getTime();


                    documentName = desktoppath + timeStamp + "_" +accountNumber + FILE;
                    PdfWriter.getInstance(document, new FileOutputStream(documentName));
                    document.open();
                    addTitle(document, "Merchants Activity Report");
                    addLogo(document);
                    addPeriod(document, startDate, endDate);
                    addMerchantDetails(document, ""+accountNumber);
                    
                    addOrderDetails(document, ""+accountNumber);
                    addDateGenerated(document);
                    addReportGenerator(document);
                    document.close();
                    System.out.println("wrote file");
		} 
                catch (Exception e) 
                {
			e.printStackTrace();
		}
	}

      
        private void addMerchantDetails(Document document, String accountNumber) throws DocumentException 
        {
            Paragraph preface = new Paragraph();
            addEmptyLine(preface, 1);
            String details = "";
            ArrayList<String> detailsArray = new ArrayList<String>();
            detailsArray = rprtDBAccess.getMerchantDetails(accountNumber);
            
            
            details += "To: \n";
            details += ""+detailsArray.get(0)+"\n"; // company
            details += ""+detailsArray.get(1)+"\n"; // address
            details += ""+detailsArray.get(2)+"\n"; // postcode
            details += ""+detailsArray.get(3)+"\n"; // phone
            details += "\n";
            details += "IPOS ACCOUNT: "+detailsArray.get(4)+"\n"; // accountNumber
            
            preface.add(new Paragraph(details, smallBold));
            document.add(preface);
	}
       

	private void addOrderDetails(Document document, String accountNumber) throws BadElementException, DocumentException {               
                Paragraph preface = new Paragraph();
                addEmptyLine(preface, 1);
                document.add(preface);
                     
                
                PdfPTable table = new PdfPTable(3);         
                
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell c1 = new PdfPCell(new Phrase("Order ID"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

                PdfPCell c2 = new PdfPCell(new Phrase("Product ID"));
		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c2);

                PdfPCell c3 = new PdfPCell(new Phrase("Total"));
		c3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c3);
                
                
                
                table.setHeaderRows(1);

                
                double total = 0;
                
		ArrayList<ArrayList<String>> orders = rprtDBAccess.getActivityOrder(accountNumber, startDate, endDate);
                
                for(int i=0; i < orders.size(); i++)
                {
                    ArrayList<String> singleOrder = new ArrayList<String>();
                    singleOrder = orders.get(i);
                    
                    
                                        
                    table.addCell(singleOrder.get(0)); // order id
                    
                    table.addCell(singleOrder.get(1)); // product id

                    
                    table.addCell(singleOrder.get(2)); // total/amoutn/price
                    total += Double.parseDouble(singleOrder.get(2));
       
                    table.completeRow();
                }
                
                table.addCell("Totals");
                table.addCell(""); // ordered
                table.addCell(""+total); //amount
		document.add(table);		
	}

}