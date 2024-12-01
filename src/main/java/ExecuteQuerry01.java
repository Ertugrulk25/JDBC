import java.sql.*;

public class ExecuteQuerry01 {
    public static void main(String[] args) throws SQLException {

        //n02-ADIM : bağlantıyı olusturma : DB URL
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_dt","techpro","password");

        //n03-ADIM : Statement olusturma
        Statement statement=con.createStatement();

        //n04-ADIM
        System.out.println("--------------------------ÖRNEK 1 ------------------------");
        //ÖRNEK 1:id'si 5 ile 10 arasında olan ülkelerin "country_name" bilgisini listeleyiniz.
        String sql1="Select country_name from countries where id between 5 and 10";
        boolean query1=statement.execute(sql1);
        System.out.println("query1 = " + query1);//true
    /*
    verileri alabilmek için:
    JDBC kullanarak veri çekme işlemi sonrasında
    veri listelemek için ResultSet sınıfı kullanılır.

    SQL sorgusu çalıştırıldıktan sonra veritabanından alınan
    verileri saklar. Verilerin arasında gitmemizi sağlar.
    Adv NOT: Veriler üzerinde dolaşmak için next, first, last, previous,
    absolute gibi metotlara sahiptir. Bunun için ayarlama gereklidir.
 */
        ResultSet rs=statement.executeQuery(sql1);
        while (rs.next()){
            System.out.println("Ülke Adı : "+rs.getString("country_name"));
        }
        System.out.println("--------------------ÖRNEK 2------------------------");
    //ÖRNEK 2: phone_code'u 200 den büyük olan ülkelerin "phone_code" ve "country_name" bilgisini listeleyiniz
        String sql2="SELECT phone_code,country_name from countries where phone_code>200 order by phone_code";
        ResultSet rs2=statement.executeQuery(sql2);
        while (rs2.next()){
            System.out.println("Ülke Adı : "+rs2.getString("country_name")+
                    "Telefon Kodu : "+rs2.getInt("phone_code"));


        }
        System.out.println("--------------------ÖRNEK 3------------------------");
        //ÖRNEK 3:developers tablosunda "salary" değeri minimum olan
        // developerların tüm bilgilerini gösteriniz

        ResultSet rs3  =statement.executeQuery("select * from developers where salary=(select min(salary) from developers)");

        while (rs3.next()){
            System.out.println("id : "+rs3.getInt("id")+
                    " --- isim : "+rs3.getString("name")+
                    " --- maas : "+rs3.getDouble("salary")+
                    " --- prog-dili : "+rs3.getString("prog_lang"));

        }
        System.out.println("--------------------ÖDEV------------------------");
        //ÖDEV:Puanı taban puanlarının ortalamasından yüksek olan
        // öğrencilerin isim ve puanlarını listeleyiniz.

    }

}



