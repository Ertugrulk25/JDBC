import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //n01-ADIM : veri tabanı  sürücü sınıfını kaydetmekle başlayabiliriz.
        Class.forName("org.postgresql.Driver");
        // n02 ADIM bağlantıyı oluşturma  : DB URL
        Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_dt","techpro","password");

        //n03-ADIM : Statement olusturma
        Statement statement=con.createStatement();

        System.out.println("Success");

        //n04-ADIM : sorguyu olusturma/çalıştırma

        //ÖRNEK 1:"workers" adında bir tablo oluşturup "worker_id,worker_name,salary" sütunlarını ekleyiniz.
        // CREATE TABLE workers(worker_id INT,worker_name VARCHAR(50),salary REAL);
        boolean sql1=statement.execute("CREATE TABLE if not exists workers(worker_id INT,worker_name VARCHAR(50),salary REAL)");
        System.out.println("sql1 = " + sql1);

        //ÖRNEK 2:"workers" tablosuna VARCHAR(20) tipinde "city" sütununu ekleyiniz.
        // ALTER TABLE workers ADD COLUMN city VARCHAR(20)

        //  boolean sql2=statement.execute("ALTER TABLE workers ADD COLUMN city VARCHAR(20)");
        //  System.out.println("sql2 = " + sql2);

        //ÖRNEK 3:"workers" tablosunu SCHEMAdan siliniz.
        // DROP TABLE workers;
        statement.execute("DROP TABLE workers");

        //n05-ADIM : Kaynakları kapatma
        statement.close();
        con.close();


    }
}