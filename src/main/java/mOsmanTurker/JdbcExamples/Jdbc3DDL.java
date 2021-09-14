package mOsmanTurker.JdbcExamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc3DDL {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		// burada daha once yaptigimiz gibi orada sql den veri almayacagiz burda tablo create edecegiz ve tablo ilaveleri vs gibi seyler yapacagiz.
		
		/* her sorguda öncekileri yoruma al hata vermesin
		   A) CREATE TABLE, DROP TABLE, ALTER TABLE gibi DDL ifadeleri icin sonuc kümesi (ResultSet) 
		      dondurmeyen metotlar kullanilmalidir.(executeQuery kullanamayız) Bunun icin JDBC'de 2 alternatif bulunmaktadir.  
		       1) execute() metodu 
		       2) excuteUpdate() metodu.  
		       
		   B) - execute() metodu hertur SQL ifadesiyle kullanilabilen genel bir komuttur.
		      - execute(), Boolean bir deger dondurur. DDL islemlerin false dondururken, 
		        DML islemlerinde true deger dondurur. 
		      - Ozellikle, hangi tip SQL ifadesinin kullanilmasinin gerektiginin belli olmadigi 
		        durumlarda tercih edilmektedir.  
		        
		   C) - executeUpdate() metodu ise INSERT, Update gibi DML islemlerinde yaygin kullanilir.
		      - bu islemlerde islemden etkilenen satir sayisini dondurur.
		      - Ayrıca, DDL islemlerinde de kullanilabilir ve bu islemlerde 0 dondurur.
		*/
		
		Class.forName("oracle.jdbc.driver.OracleDriver");		

		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "hr", "hr");
		
		Statement st = con.createStatement();

		// ORNEK1:isciler adinda bir tablo olusturunuz 
		// id NUMBER(3), birim VARCHAR2(10), maas NUMBER(5)
		
//		st.execute("CREATE TABLE isciler(" +
//		         " id NUMBER(3)," +
//		         " birim VARCHAR(10)," +
//		         " maas NUMBER(5))");
		
		// 1 kere basmalısın sonra hata veriyor cunku olusturduktan sonra basarsak tekrar create eerken hata aliriz.
		
		
		System.out.println("=========================");
		
		
		// ORNEK 2: isciler tablosunu siliniz
		
//		st.executeUpdate("DROP TABLE isciler PURGE"); // purge komple sildi.
		
		System.out.println("isciler tablosu silindi"); // bu sadece islemlerin yapılıp yapılmadigini kontrol amacli yazildi.
		
		
		System.out.println("=========================");
		
		// ORNEK 3: isciler tablosuna yeni bir sutun [isim VARCHAR2(20)] ekleyiniz.
		
//		st.executeUpdate("ALTER TABLE isciler ADD isim VARCHAR2(20)");
		
		// bu st.executeUpdate ve st.execute() herhangi bir sey dondurmez.
		
		System.out.println("isim sutunu eklendi...");
		System.out.println("=========================");
		// ORNEK 4:isciler tablosundaki maas sutununu siliniz.
		
//		st.execute("ALTER TABLE isciler DROP COLUMN maas");
		System.out.println("maas sutunu silindi...");
		
		System.out.println("=========================");
		
		// ORNEK6:isciler tablosunun adini calisanlar olarak degistiriniz. 

		st.executeUpdate("ALTER TABLE isciler RENAME TO calisanlar");
		System.out.println("tablo ismi degistirildi....");
		
	}

}
