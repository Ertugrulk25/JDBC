import java.sql.*;
/*
PreparedStatement; önceden derlenmiş tekrar tekrar kullanılabilen
parametreli sorgular oluşturmamızı ve çalıştırmamızı sağlar.

PreparedStatement Statement ı extend eder(statementın gelişmiş halidir.)
 */

public class PreparedStatement01 {
    public static void main(String[] args) throws SQLException {

        //n02-ADIM : bağlantıyı olusturma : DB URL
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_nt","techpro","password");

        //n03-ADIM : Statement olusturma
        Statement statement=con.createStatement();


        //ÖRNEK1: bolumler tablosunda Matematik bölümünün taban_puanı'nı 475 olarak güncelleyiniz.
        //   String sql1="Update bolumler set taban_puani=475 where bolum ilike 'Matematik'";
        //   int updated=statement.executeUpdate(sql1);
        //   System.out.println("updated : "+updated);

        //Prepared Statement kullanarak bolumler tablosunda
        // Matematik bölümünün taban_puanı'nı 499 olarak güncelleyiniz

        //parametreli sorgu hazırlayalım
        String sql2="Update bolumler set taban_puani= ? where bolum ilike ? ";
        PreparedStatement prst=con.prepareStatement(sql2);
        //  prst.setInt(1,499);
        //  prst.setString(2,"Matematik");
        //  prst.executeUpdate();


        //Prepared Statement kullanarak bolumler tablosunda
        // Edebiyat bölümünün taban_puanı'nı 450 olarak güncelleyiniz.
        prst.setString(2,"Edebiyat");
        prst.setInt(1,450);
        //Update bolumler set taban_puani= 450 where bolum ilike 'edebiyat';
        prst.executeUpdate();

        //Örnek 3:Prepared Statement kullanarak bolumler tablosuna
        // Yazılım Mühendisliği(id=5006,taban_puanı=475, kampus='Merkez') bölümünü ekleyiniz.
        String sql3="Insert Into bolumler values(?,?,?,?)";
        PreparedStatement prst2=con.prepareStatement(sql3);
        prst2.setInt(1,5006);
        prst2.setString(2,"Yazılım Müh.");
        prst2.setInt(3,475);
        prst2.setString(4,"Merkez");
        //"Insert Into bolumler values(5006,'Yazılım Müh.',475,'Merkez')"
        prst2.executeUpdate();


        statement.close();
        prst.close();
        prst2.close();
        con.close();




    }}