package com.dspractice;

import java.io.*;
import java.util.Scanner;
import com.itextpdf.text.Document;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class Main {
    //path name WITH the cover letter template file (i.e: /Users/JohnDoe/Desktop/cover-letters/cover-letter-template.docx)
    private static final String COVER_LETTER_TEMPLATE__PATH_NAME ="D:\\Program Files (x86)\\school\\personal stuff\\cover-letters\\";
    private static Scanner input = new Scanner(System.in);
    private static FileInputStream docInputStream;
    public static void main(String[] args) {
        Goback:
        System.out.println("Is this an IT or SWE job?");
        String career = input.nextLine();
    try{
        //asks user if its an IT or SWE role; loads doc file depending on career
        if(career.equals("SWE")){
            docInputStream = new FileInputStream(COVER_LETTER_TEMPLATE__PATH_NAME+"cover-letter-template__SWE.docx");
        }else if(career.equals("IT")){
            docInputStream = new FileInputStream(COVER_LETTER_TEMPLATE__PATH_NAME+"cover-letter-template__IT.docx");
        }else{
            System.out.println("Error");
            return;
        }

        XWPFDocument doc = new XWPFDocument(docInputStream);


        //retrieve texts from doc file
        XWPFWordExtractor extract = new XWPFWordExtractor(doc);
        String docContents = extract.getText();


        System.out.print("Company name: ");
        String company_name = input.nextLine();

        System.out.print("Position name:");
        String position_name = input.nextLine();

        System.out.print("Company objective:");
        String company_objective = input.nextLine();

        //replace keywords
        String newKeywords = docContents.
                replace("company_name", company_name).
                replace("position_name", position_name).
                replace("company_objective", "");

        //create a pdf file
        FileOutputStream pdfOutputStream = new FileOutputStream("D:\\Program Files (x86)\\school\\personal stuff\\cover-letters\\"+company_name+"__cover-letter.pdf");
        Document pdfDoc = new Document();
        PdfWriter writer = PdfWriter.getInstance(pdfDoc, pdfOutputStream);
        pdfDoc.open();

        //add new texts to pdf doc
        Paragraph paragraph = new Paragraph(newKeywords);
        pdfDoc.add(paragraph);

        //closes pdf doc
        pdfDoc.close();
        writer.close();

        //close input stream
        docInputStream.close();
        System.out.println("Task successfully completed!");
    }catch(Exception e){
        e.printStackTrace();
    }
    }
}
