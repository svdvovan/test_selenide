package test777;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;

import java.io.*;
import java.util.Iterator;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.source;

// import org.openqa.selenium;
//import selenium.webdriver.support.ui as ui
/**
 * Created by SretenskyVD on 25.12.2019.
 */


public class test777 {
    public static void main(String[] args) throws IOException {

        System.setProperty("javax.net.ssl.trustStore", "S:/ProjectJava/Kwork/src/itadakima/itadakima.su.cer.jks");
        String Tovar = "Completed Models_18";
        String Manual_category = Tovar;
//        String Manual_Proizvoditel = "Цветомания";

        String Path = "https://itadakima.su/index.php?cat=762&page=";
        //       String Path = "http://www.funkofunatic.ru/";
//keytool -import -v -file S:/ProjectJava/Kwork/src/itadakima/itadakima.su.cer -keystore S:/ProjectJava/Kwork/src/itadakima/itadakima.su.cer.jks -storepass drowssap

        String CatalogName = Tovar;
        int LastPage = 50; //234  Completed Models
        Workbook wb = new HSSFWorkbook();
//    XSSFWorkbook wb = new XSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet1 = wb.createSheet(CatalogName);
        FileOutputStream fileOut = new FileOutputStream("book_" + CatalogName + ".xls");



        try {
            wb.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();


        }
        Sheet sheet = wb.getSheetAt(0);

        //  int Page = 59;
        int Page = 100;
        for (int count = 1; count <= LastPage; count++) {
            String Path2 = Path + Page;
//        String  Path2 = Path;


            Document doc1 = Jsoup.connect(Path2).get();

            Elements links3 = doc1.getElementsByClass("ProductsListName");
            int yyy = 0;
            for (Element link3 : links3) {


                String NamePrduct = doc1.getElementsByClass("ProductsListName").get(yyy).select("a").text();
                System.out.println(NamePrduct);

                String MainPrice = doc1.getElementsByClass("ProductsListPrice").get(yyy).text();
                System.out.println(MainPrice);

                System.out.println();
                String addressUrl3 = (links3.get(yyy).select("a[href]").attr("abs:href"));
                System.out.println(addressUrl3);

                try {
                    Document doc44 = Jsoup.connect(addressUrl3)
//                        Document doc4 = Jsoup.connect(addressUrl3)
//                            .proxy("34.95.167.51", 8080)
//                            .timeout(20000)
//                            .ignoreHttpErrors(true)
//                            .ignoreContentType(true)
//                            .followRedirects(true)
//                            .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.38 Safari/537.36")
                            .get();
////////////рабочий момент///////////
                    String Zag2 = "Ограничение доступа";
                    String Zagolovok = doc44.getElementsByTag("h1").text();
                    if (Zagolovok.equals(Zag2)) {
                        open(addressUrl3);
                        $(By.id("agree")).click();
                        $(byText("Войти")).click();
//                        Document doc44 = Jsoup.connect(addressUrl3).get();
//                       Elements out = doc44.getAllElements();
//                        System.out.println(out);


//                        WebDriver driver = new HtmlUnitDriver();
//                        driver.getPageSource();
//                        System.out.println(driver.getPageSource());
                        System.out.println(source());

                        FileWriter fileOut2 = new FileWriter("addressUrl3"+".html");
                        fileOut2.write(source());
//                        wb.write(fileOut2);
                        fileOut2.close();
                        ///end////////
                        File input = new File("addressUrl3.html");
                        Document doc4 = Jsoup.parse(input, "UTF-8","addressUrl3.html");

                        ////////////////////////////////           }

                        String Category = Manual_category;
                        System.out.println(Category);

                        String SKU = doc4.getElementsByClass("sku_wrapper").text();
                        System.out.println(SKU);

                        //.select("[name=id]").attr("value");

//                     String Description  = doc4.getElementsByTag("div").select("[itemprop=description]").select("p").text();
                        String Description = doc4.getElementsByClass("ds-tab").select("[id=mode-dsc]").html();
//                    String Description2 = Description.toString();   <div id="mode-dsc"
                        System.out.println(Description);

//                    String Description_text  = doc4.getElementsByClass("tab-content").text();
//                    System.out.println(Description_text);
//
                        String Izmerenie = doc4.getElementsByClass("tagged_as").text();
                        System.out.println(Izmerenie);
//
                        String breadcrumbs_last = doc4.getElementsByClass("posted_in").text();
                        System.out.println(breadcrumbs_last);

                        String MainFoto = doc4.getElementsByClass("ProductImages").select("a").attr("abs:href");
                        System.out.println(MainFoto);

                        int rowCount = sheet.getLastRowNum();
                        Row row = sheet.createRow(++rowCount);

//
                        Cell cell227p = row.createCell(0);
                        cell227p.setCellValue(SKU);

                        Cell cell227 = row.createCell(1);
                        cell227.setCellValue(NamePrduct);


                        Cell cell1 = row.createCell(2);
                        cell1.setCellValue(Tovar);

//
                        Cell cell224 = row.createCell(3);
                        cell224.setCellValue(MainPrice);


                        Cell cell224x = row.createCell(4);
                        cell224x.setCellValue(Izmerenie);

                        Cell cell22411 = row.createCell(5);
                        cell22411.setCellValue(breadcrumbs_last);
//

                        Cell cell224221 = row.createCell(7);
                        cell224221.setCellValue(MainFoto);

                        Cell cell2242 = row.createCell(30);
                        cell2242.setCellValue(Description);


//////////////////////////////////////////
                        try {
                            Elements h2 = doc4.getElementsByClass("ProductInfoRight");
                            Iterator<Element> ite = h2.select("dt").iterator();

                            Elements row2 = h2.select("dd");
                            int Spro2 = 0;
                            int y2 = 32;
                            int y53 = 63;

//                        int CountPro = 0;
                            String Spro = "Производитель:";
//                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + Spro2 + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                            for (Element rows : row2) {
                                String Har = ite.next().text();
                                System.out.print(Har);

                                ///////////////////////////////

                                if (Har.equals(Spro)) {
//                                 CountPro = Spro2;
                                    Elements h22 = doc4.getElementsByClass("ProductInfoRight");
                                    Iterator<Element> ite2 = h22.select("dd").iterator();

                                    Elements row22 = h22.select("dd");


//                                int y22 = 33;

                                    for (Element rows3 : row22) {
                                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + Spro2 + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//                                    String Har2 = ite2.next().text();
//                                    Element   Har3 = h22.select("dd").get(3);
                                        String Har2 = row22.get(Spro2).text();
//                                    String Har2 = Har3.toString();
                                        System.out.print(Har2);

                                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + Har2 + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                                        Cell cell100011 = row.createCell(y53);
                                        cell100011.setCellValue(Har2);

//
//                                    CountPro=CountPro+1;
//                                    y22=y22+2;

                                    }
                                }
                                Spro2 = Spro2 + 1;

                                ////////////////////////////////////


                                Cell cell1000 = row.createCell(y2);
                                cell1000.setCellValue(Har);

//
//
                                y2 = y2 + 2;

                            }
                        } catch (java.util.NoSuchElementException e) {
                            e.printStackTrace();
                        }
///////////////////////////////////////////
                        try {
                            Elements h22 = doc4.getElementsByClass("ProductInfoRight");
                            Iterator<Element> ite2 = h22.select("dd").iterator();

                            Elements row22 = h22.select("dd");

                            int y22 = 33;

                            for (Element rows3 : row22) {

                                String Har2 = ite2.next().text();

                                System.out.print(Har2);


                                Cell cell1000 = row.createCell(y22);
                                cell1000.setCellValue(Har2);


                                y22 = y22 + 2;

                            }

                        } catch (java.lang.NullPointerException e) {
                            e.printStackTrace();
                        }
///////////////////////////////////////////


/////////////////////////////////////////////////////////
                        try {
                            Elements pictures = doc4.getElementsByClass("productMoImages").select("a");

                            int z = 0;
                            //                      int y3 = 6;
                            int y3 = 8;
                            for (Element picture : pictures) {
                                System.out.println(pictures.get(z).select("a[href]").attr("abs:href"));
//                        String Foto =  pictures.get(z).select("a").attr("href");
                                String Foto = pictures.get(z).select("a[href]").attr("abs:href");
//                        File f = new File(Foto);
//                        String FILENAME = "test/unikma/"  + CatalogName + "/" + f.getName();
//                        String SvDPDFURL = Foto;
//                        File file = new File(FILENAME);
//                        URL url = new URL(SvDPDFURL);
//                        FileUtils.copyURLToFile(url, file);

                                Cell cell5555 = row.createCell(y3);
                                cell5555.setCellValue(Foto);
                                y3++;


                                z++;
                            }
                        } catch (java.lang.NullPointerException e) {
                            e.printStackTrace();
                        }
///////////////////////////////////////////////////////////


                    }
                    }catch(java.lang.IllegalArgumentException e){
                        e.printStackTrace();
                    }

                    catch(java.net.SocketTimeoutException e){
                        e.printStackTrace();
                    }
                    catch(java.lang.IndexOutOfBoundsException e){
                        e.printStackTrace();
                    }
                    catch(java.lang.NullPointerException e){
                        e.printStackTrace();
                    }


                    System.out.println();
                    yyy++;


                    try {
                        FileOutputStream fileOut1 = new FileOutputStream("book_" + CatalogName + ".xls");
                        wb.write(fileOut1);
                        fileOut1.close();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
                System.out.println(Page);
                Page++;
            }

        }


}

