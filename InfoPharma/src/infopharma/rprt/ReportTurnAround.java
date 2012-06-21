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
import infopharma.data.OrderDBAccess;
import infopharma.data.RprtDBAccess;
import java.util.ArrayList;

public class ReportTurnAround extends GeneralReport
{
        private RprtDBAccess rprtDBAccess;
        private OrderDBAccess orderDBAccess;
        public String documentName;

        
	private static String FILE = "_turnaround.pdf";

	public ReportTurnAround() 
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
                    addTitle(document, "Turnaround Report");
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

                PdfPCell c2 = new PdfPCell(new Phrase("Product Name"));
		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c2);

                PdfPCell c3 = new PdfPCell(new Phrase("Quantity Sold"));
		c3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c3);
                
                
                
                table.setHeaderRows(1);


                
		ArrayList<ArrayList<String>> products = rprtDBAccess.getTurnAroundReport();
                
                for(int i=0; i < products.size(); i++)
                {
                    ArrayList<String> product = new ArrayList<String>();
                    product = products.get(i);
                    
                                        
                    table.addCell(product.get(0)); // product id
                    
                    table.addCell(product.get(1)); // product name

                    
                    table.addCell(product.get(2)); // quantity
       
                    table.completeRow();
                }
                document.add(table);		
	}

}