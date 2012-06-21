/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infopharma.rprt;

import com.itextpdf.text.*;
import java.util.Date;

import infopharma.data.UserAccount;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class GeneralReport 
{               
        public String desktoppath = System.getProperty("user.home") + "/Desktop/";
        
	public static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	public static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
	public static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	public static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

	public GeneralReport() 
        {
            
	}
        
        public String convertToDoubleWithoutPrecisionLose(String e)
        {
            java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");  
            if (!e.isEmpty())
            {
                try
                {
                    double d = Double.parseDouble(e);
                    return df.format(d);  
                }
                catch(Exception err){}             
            }
            return "0.00";
        }

        public  void addTitle(Document document, String reportName) throws DocumentException 
        {
            Paragraph preface = new Paragraph();
            addEmptyLine(preface, 1);
            preface.add(new Paragraph(reportName, subFont));
            document.add(preface);
	}
        
        public  void addPeriod(Document document, String startDate, String endDate) throws DocumentException 
        {
            Paragraph preface = new Paragraph();
            addEmptyLine(preface, 1);
            String period = "Report Period: "+startDate +" " + endDate;
            preface.add(new Paragraph(period, smallBold));
            document.add(preface);
	}
             
        public void addDateGenerated(Document document) throws DocumentException 
        {
            Paragraph preface = new Paragraph();
            addEmptyLine(preface, 1);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String dateString = "Generated: "+ date;
            preface.add(new Paragraph(dateString, smallBold));
            document.add(preface);
        }
        
        public void addReportGenerator(Document document) throws DocumentException 
        {
            Paragraph preface = new Paragraph();
            addEmptyLine(preface, 1);
            String generator = "By: "+ UserAccount.getRole();
            
            preface.add(new Paragraph(generator, smallBold));
            document.add(preface);
        }
        
        public void addLogo(Document document) throws DocumentException 
        {
            try
            {
                Image image = Image.getInstance("logo.png");
                Paragraph preface = new Paragraph();
                image.setAbsolutePosition(420, 700f);
                document.add(image);
            }
            catch (Exception e)
            {
                System.out.println("Error: "+e.getMessage());
            }
        }
        

	
       
	public void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
}