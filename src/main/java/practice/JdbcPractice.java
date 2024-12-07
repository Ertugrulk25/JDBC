package practice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcPractice {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc","techpro","password");

        Statement statement=con.createStatement();

      //  countries tablosunda, ülke kodu 'TR' olan ülkenin telefon kodunu bulun.

        String sql1 =  "SELECT phone_code FROM countries WHERE country_code = 'TR'";
        ResultSet rs1 = statement.executeQuery(sql1);
        if (rs1.next()){
            System.out.println("Telefon kodu: "+rs1.getInt("phone_code"));
        }

        //n02: ogrenciler tablosunda, puanı 500’den büyük olan öğrencilerin isimlerini
        // ve şehirlerini getirin.

String sql2 = "SELECT isim,sehir FROM ogrenciler WHERE puan >?";
        PreparedStatement prst = con.prepareStatement(sql2);
        prst.setInt(1,500);

       ResultSet rs2 = prst.executeQuery();

       while (rs2.next()){
           System.out.println("isim: "+ rs2.getString(1)
           + "sehir: "+ rs2.getString(2));

       }
        //n03: bolumler tablosuna yeni bir bölüm ekleyin.
        // Bölüm adı 'Mimarlık', taban puanı 510, kampüsü 'Hisar' olsun.

//        PreparedStatement ps3 =con.prepareStatement( "INSERT INTO bolumler (bolum_adi,taban_puani,kampus) VALUES (?,?,?)");
//
//       ps3.setString(1,"Mimarlık");
//       ps3.setInt(2,510);
//       ps3.setString(3,"Hisar");
//
//        int inserted = ps3.executeUpdate();
//
//        if (inserted>0){
//            System.out.println("Kayıt ekleme başarılı: "+ inserted);
//        }
        //n04. developers tablosunda maaşı 5000’den fazla olan geliştiricilerin isimlerini
        // bir listeye ekleyiniz.

        System.out.println("---------------------------------------------------------4");

        String sql4="SELECT name FROM developers WHERE salary>5000";
        ResultSet rs4=statement.executeQuery(sql4);

        List<String> name = new ArrayList<>();

        while (rs4.next()){
            name.add(rs4.getString("name"));

        }
        System.out.println(name);

        //n05. ogrenciler tablosundaki her öğrenciye 50 puan bonus ekleyin.

        PreparedStatement pr4 = con.prepareStatement("UPDATE ogrenciler SET puan= puan+? ");

        pr4.setInt(1,50);

        int update = pr4.executeUpdate();
        System.out.println(" güncellenen  "+ update);

        //n06. developers tablosunda 'Java' programlama diliyle çalışan
        // en yüksek maaşlı geliştiriciyi bulun.


        System.out.println("---------------------------------------------------------6-alternatif");

        //alternatif çözüm
        ResultSet rset6=statement.executeQuery("SELECT name,salary FROM developers " +
                "WHERE prog_lang = 'Java' " +
                "AND salary = (SELECT MAX(salary) FROM developers WHERE prog_lang = 'Java')");
        while (rset6.next()){
            System.out.println("isim: "+rset6.getString("name")+
                    " - max. maaş: "+rset6.getDouble("salary"));
        }
        //n07. countries tablosundaki toplam ülke sayısını bulun.

        //n07. countries tablosundaki toplam ülke sayısını bulun.
        System.out.println("---------------------------------------------------------7");

        ResultSet rs7=statement.executeQuery("SELECT COUNT(*) AS ulke_sayisi FROM countries");

        if (rs7.next()){
            System.out.println("Toplam ülke sayısı : "+rs7.getInt("ulke_sayisi"));
        }

       // n08. developers tablosunda maaşı en düşük olan geliştiricinin bilgilerini silin.

        //n08. developers tablosunda maaşı en düşük olan geliştiricinin bilgilerini silin.
        int deleted=statement.executeUpdate("DELETE FROM developers " +
                "WHERE salary=(SELECT MIN(salary) FROM developers)");

        System.out.println(deleted+" tane satır silindi.");


        //n09. ogrenciler ve bolumler tablolarını birleştirerek,
        // öğrencilerin isimlerini ve okudukları bölümün kampüslerini getirin.
        ResultSet rs9=statement.executeQuery("SELECT o.isim, b.kampus " +
                "FROM ogrenciler o INNER JOIN bolumler b ON o.bolum=b.bolum");

        while (rs9.next()){
            System.out.println("isim : "+rs9.getString("isim")+
                    " kampüs: "+rs9.getString("kampus"));
        }
        //10. ogrenciler tablosunda her şehirdeki öğrenci sayısını bulun
        // ve azalan sırayla listeleyin.
        //10. ogrenciler tablosunda her şehirdeki öğrenci sayısını bulun
        // ve azalan sırayla listeleyin.
        System.out.println("---------------------------------------------------------10");

        String sql10="SELECT sehir,COUNT(*) ogrenci_sayisi FROM ogrenciler " +
                "GROUP BY sehir ORDER BY ogrenci_sayisi DESC";
        ResultSet rs10=statement.executeQuery(sql10);
        while (rs10.next()){
            System.out.println("şehir : "+rs10.getString("sehir")
                    +" - öğrenci sayısı : "+rs10.getInt("ogrenci_sayisi"));
        }

        //ödev 1: developers tablosunda her programlama dili için ortalama maaşı bulun
        // ve sonuçları büyükten küçüğe sıralayın.

        //ödev 2: developers tablosunda programlama dillerine göre geliştirici sayısını
        // ve toplam maaşı hesaplayın. Sonuçları geliştirici sayısına göre azalan sırada sıralayın.
    }
    }






