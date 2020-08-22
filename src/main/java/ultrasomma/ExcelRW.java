package ultrasomma;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class ExcelRW {

     private static XSSFWorkbook wb;
     private static Sheet sh;
     private static FileInputStream fis;
     private static FileOutputStream fos;
     private static Row row;
     private static Cell cell;
     private static String medValeria;
     private static String medGeruza;
     private static String medLaurise;
     private static String afrafep;
     private static String caixa;
     private static String cassi;
     private static String embrapa;
     private static String unimed;
     private static String driver;
     private static String url;
     private static String user;
     private static String password;
     private static Connection con;
     private static Statement stm;
     private static String sql;
     private static ResultSet query;
     private static int cont;
     private static int rowEnd;
     private static Date data;
     private static String strData;
     private static SimpleDateFormat format;
     private static String nome;
     private static String procedimento;
     private static Double valor;
     private static String strValor;
     private static String medica;
     private static boolean Vtrue;
     private static boolean Ltrue;
     private static boolean Gtrue;


     //Afrafep
     private static List<String> listAfrafep;
     private static String quantG1;
     private static String totalG1;
     private static String quantL1;
     private static String totalL1;
     private static String quantV1;
     private static String totalV1;
     private static String  erros1;

     //Caixa
     private static List<String> listCaixa;
     private static String quantG2;
     private static String totalG2;
     private static String quantL2;
     private static String totalL2;
     private static String quantV2;
     private static String totalV2;
     private static String erros2;

     //Cassi
     private static List<String> listCassi;
     private static String quantG3;
     private static String totalG3;
     private static String quantL3;
     private static String totalL3;
     private static String quantV3;
     private static String totalV3;
     private static String erros3;

     //Embrapa
     private static List<String> listEmbrapa;
     private static String quantG4;
     private static String totalG4;
     private static String quantL4;
     private static String totalL4;
     private static String quantV4;
     private static String totalV4;
     private static String erros4;

     //Unimed
     private static List<String> listUnimed;
     private static String quantG5;
     private static String filmeG5;
     private static String totalG5;
     private static String quantL5;
     private static String filmeL5;
     private static String totalL5;
     private static String quantV5;
     private static String filmeV5;
     private static String totalV5;
     private static String erros5;

     //Ultrasomma
     private static List<String> listUltrasomma;
     private static String quantG6;
     private static String totalG6;
     private static String quantL6;
     private static String totalL6;
     private static String quantV6;
     private static String totalV6;
     private static String erros6;


     public static void lerExcels(ObservableList<String> arquivos) throws SQLException, ClassNotFoundException, IOException {

          for (String arquivo : arquivos) {
               if (arquivo.contains("valeria")) {
                    medValeria = arquivo;
               } else if (arquivo.contains("geruza")) {
                    medGeruza = arquivo;
               } else if (arquivo.contains("laurise")) {
                    medLaurise = arquivo;
               } else if (arquivo.contains("afrafep")) {
                    afrafep = arquivo;
               } else if (arquivo.contains("caixa")) {
                    caixa = arquivo;
               } else if (arquivo.contains("cassi")) {
                    cassi = arquivo;
               } else if (arquivo.contains("embrapa")) {
                    embrapa = arquivo;
               } else if (arquivo.contains("unimed")) {
                    unimed = arquivo;
               }
          }

          //Prints Files
//          printPath(medValeria);
//          printPath(medGeruza);
//          printPath(medLaurise);
//          printPath(afrafep);
//          printPath(caixa);
//          printPath(cassi);
//          printPath(embrapa);
//          printPath(unimed);

          //Setting Database
          driver = "org.h2.Driver";
          url = "jdbc:h2:mem:";
          user = "Thorinr";
          password = "vaporubi";
          Class.forName(driver);
          System.out.println("Conectando Banco de Dados...");
          con = DriverManager.getConnection(url,user, password);
          System.out.println("Banco de Dados Conectado!");
          stm = con.createStatement();

          //Criando Tabelas das Médicas
          tabelasMedicas(medValeria, "MedValeria");
          tabelasMedicas(medGeruza, "MedGeruza");
          tabelasMedicas(medLaurise, "MedLaurise");

          sql =   "CREATE TABLE Exames ( " +
                  "   data DATE NOT NULL, " +
                  "   nome VARCHAR(255) NOT NULL, " +
                  "   procedimento VARCHAR(255) NOT NULL, " +
                  "   valor DOUBLE NOT NULL, " +
                  "   convenio VARCHAR(255) NOT NULL," +
                  "   medica VARCHAR(255) " +
                  ");";

          stm.executeUpdate(sql);
          System.out.println("\nTabela Exames Criada!");

          //Alimentando Tabelas de Exames
          tabelaExames(afrafep, "Afrafep");
          tabelaExames(caixa, "Caixa");
          tabelaExames(cassi, "Cassi");
          tabelaExames(embrapa, "Embrapa");
          tabelaExames(unimed, "Unimed");


          //Print das Tabelas
//          printTabela("MedValeria");
//          printTabela("MedGeruza");
//          printTabela("MedLaurise");
//          printTabela("Exames");

     }

     private static void tabelaExames(String file, String name) throws IOException, SQLException {

          fis = new FileInputStream(file);
          wb = new XSSFWorkbook(fis);
          sh = wb.getSheetAt(0);
          rowEnd = sh.getLastRowNum();
          format =  new SimpleDateFormat("dd/MM/yyyy");


          for (int i = 1; i <= rowEnd; i++) {
               row = sh.getRow(i);
               Iterator<Cell> cellIterator = row.cellIterator();
               cont = 0;
               Vtrue = false;
               Ltrue = false;
               Gtrue = false;
               medica = "";
               while (cellIterator.hasNext()) {
                    cell = cellIterator.next();
                    if(cont == 0){
                         data = cell.getDateCellValue();
                         strData = format.format(data);

                    }else if(cont == 1){
                         nome = cell.getStringCellValue();
                    }else if(cont == 2){
                         procedimento = cell.getStringCellValue();
                    }else if(cont == 3){
                         valor = cell.getNumericCellValue();
                         strValor = String.format(Locale.US, "%.2f", valor);

                    }
                    cont++;

               }

               //Finding which Doctor made
               //Valeria
               sql = String.format("select * from MedValeria where nome = '%s' ;", nome);
               query = stm.executeQuery(sql);
               if(query.next()){
                    Vtrue = true;
               }
               //Laurise
               sql = String.format("select * from MedLaurise where nome = '%s' ;", nome);
               query = stm.executeQuery(sql);
               if(query.next()){
                    Ltrue = true;
               }
               //Geruza
               sql = String.format("select * from MedGeruza where nome = '%s' ;", nome);
               query = stm.executeQuery(sql);
               if(query.next()){
                    Gtrue = true;
               }

               if(Vtrue && !Ltrue && !Gtrue){
                    medica = "Valéria";
               }else if(Ltrue && !Vtrue && !Gtrue){
                    medica = "Laurise";
               }else if(Gtrue && !Ltrue && !Vtrue){
                    medica = "Geruza";
               }else{
                    medica = "error";
               }

               sql = String.format("INSERT INTO exames VALUES ( " +
                       "TO_DATE('%s', 'DD/MM/YYYY'), " +
                       "'%s', " +
                       "'%s', " +
                       "%s, " +
                       "'%s', " +
                       "'%s'" +
                       ");", strData, nome, procedimento, strValor, name, medica);
               stm.executeUpdate(sql);
          }
          System.out.println("\nTabela Exames Alimentada Com Convênio " + name +" !");

     }

     private static void printTabela(String name) throws SQLException {

          sql = String.format("select * from %s;", name);
          query = stm.executeQuery(sql);
          System.out.println("\nTabela " + name);
          while(query.next()){
               System.out.println(query.getDate(1) + "\t\t" + query.getString(2) + "\t\t" + query.getString(3) + "\t\t" + query.getDouble(4) + "\t\t" + query.getString(5) + "\t\t" + query.getString(6));
          }
     }

     private static void printPath(String file){
          System.out.println(file);
     }

     private static void tabelasMedicas(String file, String name) throws IOException, SQLException {

          fis = new FileInputStream(file);
          wb = new XSSFWorkbook(fis);
          sh = wb.getSheetAt(0);
          rowEnd = sh.getLastRowNum();
          format =  new SimpleDateFormat("dd/MM/yyyy");

          //Create Tabelas
          sql = String.format("CREATE TABLE %s ( " +
                  "   data DATE NOT NULL, " +
                  "   nome VARCHAR(255) NOT NULL, " +
                  "   procedimento VARCHAR(255) NOT NULL, " +
                  "   valor DOUBLE NOT NULL" +
                  ");",name);

          stm.executeUpdate(sql);
          System.out.println("\nTabela " + name + " Criada!");

          for (int i = 1; i <= rowEnd; i++) {
               row = sh.getRow(i);
               Iterator<Cell> cellIterator = row.cellIterator();
               cont = 0;
               while (cellIterator.hasNext()) {
                    cell = cellIterator.next();
                    if(cont == 0){
                         data = cell.getDateCellValue();
                         strData = format.format(data);
                    }else if(cont == 1){
                         nome = cell.getStringCellValue();
                    }else if(cont == 2){
                         procedimento = cell.getStringCellValue();
                    }else if(cont == 3){
                         valor = cell.getNumericCellValue();
                         strValor = String.format(Locale.US, "%.2f", valor);

                    }
                    cont++;

               }

               sql = String.format("INSERT INTO %s VALUES ( " +
                       "TO_DATE('%s', 'DD/MM/YYYY'), " +
                       "'%s', " +
                       "'%s', " +
                       "%s" +
                       ");", name, strData, nome, procedimento, strValor);
               stm.executeUpdate(sql);
          }
          System.out.println("Tabela " + name + " Alimentada!");
     }

     public static Collection<List<String>> getData() throws SQLException {

          //Afrafep
          quantG1 = getQuant("Afrafep", "Geruza");
          totalG1 = getTotal("Afrafep", "Geruza");
          quantL1 = getQuant("Afrafep", "Laurise");
          totalL1 = getTotal("Afrafep", "Laurise");
          quantV1 = getQuant("Afrafep", "Valéria");
          totalV1 = getTotal("Afrafep", "Valéria");
          listAfrafep = new ArrayList<>();
          listAfrafep.addAll(Arrays.asList(quantG1,totalG1,quantL1,totalL1,quantV1,totalV1));

          //Caixa
          quantG2 = getQuant("Caixa", "Geruza");
          totalG2 = getTotal("Caixa", "Geruza");
          quantL2 = getQuant("Caixa", "Laurise");
          totalL2 = getTotal("Caixa", "Laurise");
          quantV2 = getQuant("Caixa", "Valéria");
          totalV2 = getTotal("Caixa", "Valéria");
          listCaixa = new ArrayList<>();
          listCaixa.addAll(Arrays.asList(quantG2,totalG2,quantL2,totalL2,quantV2,totalV2));


          //Cassi
          quantG3 = getQuant("Cassi", "Geruza");
          totalG3 = getTotal("Cassi", "Geruza");
          quantL3 = getQuant("Cassi", "Laurise");
          totalL3 = getTotal("Cassi", "Laurise");
          quantV3 = getQuant("Cassi", "Valéria");
          totalV3 = getTotal("Cassi", "Valéria");
          listCassi = new ArrayList<>();
          listCassi.addAll(Arrays.asList(quantG3,totalG3,quantL3,totalL3,quantV3,totalV3));

          //Embrapa
          quantG4 = getQuant("Embrapa", "Geruza");
          totalG4 = getTotal("Embrapa", "Geruza");
          quantL4 = getQuant("Embrapa", "Laurise");
          totalL4 = getTotal("Embrapa", "Laurise");
          quantV4 = getQuant("Embrapa", "Valéria");
          totalV4 = getTotal("Embrapa", "Valéria");
          listEmbrapa = new ArrayList<>();
          listEmbrapa.addAll(Arrays.asList(quantG4,totalG4,quantL4,totalL4,quantV4,totalV4));

          //Unimed
          quantG5 = getQuant("Unimed", "Geruza");
          filmeG5 = getFilme("Unimed", "Geruza");
          totalG5 = getTotal("Unimed", "Geruza");
          quantL5 = getQuant("Unimed", "Laurise");
          filmeL5 = getFilme("Unimed", "Laurise");
          totalL5 = getTotal("Unimed", "Laurise");
          quantV5 = getQuant("Unimed", "Valéria");
          filmeV5 = getFilme("Unimed", "Valéria");
          totalV5 = getTotal("Unimed", "Valéria");
          listUnimed = new ArrayList<>();
          listUnimed.addAll(Arrays.asList(quantG5,filmeG5,totalG5,quantL5,filmeL5,totalL5,quantV5,filmeV5,totalV5));
//
//          //Ultrasomma
          quantG6 = addQuant(quantG1, quantG2, quantG3, quantG4, quantG5);
          totalG6 = addTotal(totalG1,totalG2,totalG3,totalG4,totalG5);
          quantL6 = addQuant(quantL1, quantL2, quantL3, quantL4, quantL5);
          totalL6 = addTotal(totalL1,totalL2,totalL3,totalL4,totalL5);
          quantV6 = addQuant(quantV1, quantV2, quantV3, quantV4, quantV5);
          totalV6 = addTotal(totalV1,totalV2,totalV3,totalV4,totalV5);
          listUltrasomma = new ArrayList<>();
          listUltrasomma.addAll(Arrays.asList(quantG6,totalG6,quantL6,totalL6,quantV6,totalV6));


          return Arrays.asList( listAfrafep, listCaixa, listCassi, listEmbrapa, listUnimed, listUltrasomma);


     }

     private static String getQuant(String convenio, String medica) throws SQLException {
          sql = String.format("select * from exames" +
                  " where procedimento != 'Filme' and convenio = '%s' and medica = '%s';", convenio, medica);
          query = stm.executeQuery(sql);
          int quant = 0;
          while(query.next()){
               quant++;
          }
          return String.valueOf(quant);
     }

     private static String  getFilme(String convenio, String medica) throws SQLException {
          sql = String.format("select * from exames" +
                  " where procedimento = 'Filme' and convenio = '%s' and medica = '%s';", convenio, medica);
          query = stm.executeQuery(sql);
          double filme = 0.0;
          while(query.next()){
               filme += query.getDouble(4);
          }
          return String.format("%.2f", filme);
     }

     private static String getTotal(String convenio, String medica) throws SQLException {
          sql = String.format("select * from exames" +
                  " where convenio = '%s' and medica = '%s';", convenio, medica);
          query = stm.executeQuery(sql);
          double total = 0.0;
          while(query.next()){
               total += query.getDouble(4);

          }
          return String.format("%.2f", total);
     }

     private static String addQuant(String num1, String num2, String num3, String num4, String num5){
          int soma = 0;
          soma = Integer.parseInt(num1) + Integer.parseInt(num2) + Integer.parseInt(num3) + Integer.parseInt(num4) + Integer.parseInt(num5);
          return String.format("%d", soma);
     }

     private static String addTotal(String num1, String num2, String num3, String num4, String num5){
          double soma = 0.0;
          String num1a = num1.replace(",",".");
          String num2a = num2.replace(",",".");
          String num3a = num3.replace(",",".");
          String num4a = num4.replace(",",".");
          String num5a = num5.replace(",",".");
          soma = Double.parseDouble(num1a) + Double.parseDouble(num2a) + Double.parseDouble(num3a) + Double.parseDouble(num4a) + Double.parseDouble(num5a);
          return String.format("%.2f", soma);
     }



}


