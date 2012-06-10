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

public class ReportLowStock extends GeneralReport
{
        private RprtDBAccess rprtDBAccess;
        private OrderDBAccess orderDBAccess;
        public String documentName;

        
	private static String FILE = "_lowStock.pdf";

	public ReportLowStock() 
        {
            rprtDBAccess = new RprtDBAccess();
            orderDBAccess = new OrderDBAccess();
            
		try 
                {  
                    Document document = new Document();

                    Date date= new java.util.Date();
                    long timeStamp = date.getTime();


                    documentName = desktoppath + timeStamp + "_" + FILE;
                    PdfWriter.getInstance(document, new FileOutputStream(documentName));
                    document.open();
                    addTitle(document, "Low Stock Report");
                    addLogo(document);                    
                    addProductDetails(document);
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
       

	private void addProductDetails(Document document) throws BadElementException, DocumentException {               
                Paragraph preface = new Paragraph();
                addEmptyLine(preface, 5);
                document.add(preface);
                     
                
                PdfPTable table = new PdfPTable(3);         
                
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell c1 = new PdfPCell(new Phrase("Product ID"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

                PdfPCell c2 = new PdfPCell(new Phrase("Current Stock"));
		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c2);

                PdfPCell c3 = new PdfPCell(new Phrase("Minimum Stock"));
		c3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c3);
                
                
                
                table.setHeaderRows(1);


                
		ArrayList<ArrayList<String>> products = rprtDBAccess.getLowStockedProducts();
                
                for(int i = 0; i < products.size(); i++)
                {
                    ArrayList<String> product = products.get(i);
                    table.addCell(product.get(0)); // product id                 
                    table.addCell(product.get(1)); // currentstock
                    table.addCell(product.get(2)); // minimumstock
                    
                    table.completeRow();
                }
                document.add(table);		
	}

}