import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_dt", "techpro", "password");

        Statement statement = con.createStatement();

        //  ÖRNEK1:developers tablosunda maaşı ortalama maaştan az
        //  olanların maaşını ortalama maaş ile güncelleyiniz.

        System.out.println("----------------------Update'den ÖNCE---------------------");
        ResultSet rs = statement.executeQuery("select * from developers");
        while (rs.next()) {
            System.out.println("isim : " + rs.getString("name") + " maas : " + rs.getString("salary"));
        }

        String sql1 = "UPDATE developers set salary=(select avg(salary) from developers )" +
                " where salary<(select avg(salary) from developers)";
        int updated = statement.executeUpdate(sql1);//kaç tane yapının değiştigini bildirir


        System.out.println("güncellenen kayıt sayisi : " + updated);
        System.out.println("-------------------UPDATE'den SONRA-----------------------");
        ResultSet rs2 = statement.executeQuery("select * from developers");
        while (rs2.next()) {
            System.out.println("isim : " + rs2.getString("name") + " maas : " + rs2.getString("salary"));

        }
    }
}
