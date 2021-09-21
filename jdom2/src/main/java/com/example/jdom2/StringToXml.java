package com.example.jdom2;

import java.io.File;

import java.io.StringReader;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;


public class StringToXml {

    public static void main(String[] args) throws Exception {

        // 1. XML 로드
        // 1-1. 문자열 파싱 시
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <catalog> <book id=\"bk101\"> <author>Gambardella, Matthew</author> <title>XML Developer's Guide</title> <genre>Computer</genre> <price>44.95</price> <publish_date>2000-10-01</publish_date> <description>An in-depth look at creating applications with XML.</description> </book> <book id=\"bk102\"> <author>Ralls, Kim</author> <title>Midnight Rain</title> <genre>Fantasy</genre> <price>5.95</price> <publish_date>2000-12-16</publish_date> <description>A former architect battles corporate zombies, an evil sorceress, and her own childhood to become queen of the world.</description> </book> </catalog>";

        Document document = new SAXBuilder().build(new StringReader(xml));

        // 1-2. 파일 파싱 시
        //Document document = new SAXBuilder().build(new File("C:/project/blog/file/books.xml"));

        // 2. Root Element (catalog)
        Element rootElement = document.getRootElement();

        // 3. Root Element (book)
        List<Element> bookElements = rootElement.getChildren();
        for(Element bookElement : bookElements){

            String bookAttributeId = bookElement.getAttributeValue("id");
            System.out.println("==== ==== ==== ==== ====");
            System.out.println("book attribute id : " + bookAttributeId);

            // 4. Book Children Element (author, title, genre, price, publish_date, description)
            List<Element> bookChildrenElements =  bookElement.getChildren();
            for(Element bookChildrenElement : bookChildrenElements){
                String name = bookChildrenElement.getName();
                String value = bookChildrenElement.getValue();
                System.out.println(name + " : " + value);
            }

            System.out.println("==== ==== ==== ==== ====");
        }
    }
}