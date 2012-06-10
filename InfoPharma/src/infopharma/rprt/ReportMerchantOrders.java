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

public class ReportMerchantOrders extends GeneralReport
{
        private static String startDate;
        private static String endDate;
        private RprtDBAccess rprtDBAccess;
        public String documentName;

        
	private static String FILE = "_orders.pdf";

	public ReportMerchantOrders(String accountNumber, String startDate, String endDate) 
        {
            rprtDBAccess = new RprtDBAccess();
            
            
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
                    addTitle(document, "Merchants Orders Report");
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
                     
                
                PdfPTable table = new PdfPTable(6);         
                
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell c1 = new PdfPCell(new Phrase("Order ID"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

                PdfPCell c2 = new PdfPCell(new Phrase("Order ID"));
		c2 = new PdfPCell(new Phrase("Ordered"));
		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c2);

                PdfPCell c3 = new PdfPCell(new Phrase("Order ID"));
		c3 = new PdfPCell(new Phrase("Amount (£)"));
		c3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c3);
                
                PdfPCell c4 = new PdfPCell(new Phrase("Order ID"));
                c4 = new PdfPCell(new Phrase("Dispatched"));
		c4.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c4);
                
                 PdfPCell c5 = new PdfPCell(new Phrase("Order ID"));
                c5 = new PdfPCell(new Phrase("Delivered"));
		c5.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c5);
                
                PdfPCell c6 = new PdfPCell(new Phrase("Order ID"));
                c6 = new PdfPCell(new Phrase("Paid"));
		c6.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c6);
                
                
                table.setHeaderRows(1);

                
                int ordered = 0;
                double amount = 0;
                int dispatched = 0;
                int delivered = 0;
                int paid = 0;
                
		ArrayList<ArrayList<String>> orders = rprtDBAccess.getMerchantOrders(accountNumber, startDate, endDate);
                
                for(int i=0; i < orders.size(); i++)
                {
                    ArrayList<String> singleOrder = new ArrayList<String>();
                    singleOrder = orders.get(i);
                    
                                        
                    table.addCell(singleOrder.get(0)); // order id
                    
                    table.addCell(singleOrder.get(1)); // ordered
                    ordered += 1;
                    
                    table.addCell(convertToDoubleWithoutPrecisionLose(singleOrder.get(2))); // amount
                    amount += Double.parseDouble(singleOrder.get(2));
                    
                    table.addCell(singleOrder.get(3)); // dispatched
                    if(!singleOrder.get(3).equals("No"))
                    {
                        dispatched += 1;
                    }
                    
                    table.addCell(singleOrder.get(4)); // delivered
                    if(!singleOrder.get(4).equals("Pending"))
                    {
                        delivered += 1;
                    }
                    
                    table.addCell(singleOrder.get(5)); // paid
                    if(!singleOrder.get(5).equals("No"))
                    {
                        paid += 1;
                    }                 
                    table.completeRow();
                }
                
                table.addCell("Totals");
                table.addCell(""+ordered); // ordered
                table.addCell(convertToDoubleWithoutPrecisionLose(""+amount)); //amount
                table.addCell(""+dispatched); //dispatched
                table.addCell(""+delivered); //delivered
                table.addCell(""+paid); //paid
		document.add(table);		
	}

}