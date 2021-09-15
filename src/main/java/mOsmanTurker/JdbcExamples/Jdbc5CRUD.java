package mOsmanTurker.JdbcExamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Jdbc5CRUD {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//	    TERCIH NEDENLERI

//	    1: hızlıdır, çoklu sorguları bir seferde yollar

//   	2: hazırlanan deyimlerin en önemli avantajı SQL enjeksiyon saldırılarını önlemeye yardımcı olmasıdır.
//		SQL enjeksiyonu, SQL ifadelerinde istemci tarafından sağlanan verileri kullanan uygulamalardan kötü niyetli olarak yararlanma tekniğidir. 
//		Saldırganlar, özel hazırlanmış dize girişi sağlayarak SQL motorunu istenmeyen komutları yürütmesi için kandırır,
//	    böylece kısıtlı verileri görüntülemek veya işlemek için bir veritabanına yetkisiz erişim elde eder. 
//		SQL enjeksiyon tekniklerinin tümü, uygulamadaki tek bir güvenlik açığından yararlanır
//	    Yanlış doğrulanmış veya doğrulanmamış dize değişmezleri, dinamik olarak oluşturulmuş bir SQL ifadesinde birleştirilir ve 
//		SQL motoru tarafından kod olarak yorumlanır. Hazırlanan ifadeler, müşteri tarafından sağlanan verileri 
//		her zaman bir parametrenin içeriği olarak ele alır ve asla bir SQL ifadesinin parçası olarak kabul etmez.
	
		
		Class.forName("oracle.jdbc.driver.OracleDriver");		

		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "hr", "hr");
		
		Statement st = con.createStatement();
		
		//ORNEK1: urunler adinda bir tablo olusturalim id NUMBER(3), isim VARCHAR2(10) fiyat NUMBER(7,2) 

//		st.execute("CREATE TABLE urunler ("
//                + " id NUMBER(3),"
//                + " isim VARCHAR2(10),"                       
//                + " fiyat NUMBER(7,2))"); 
		
		// Cok miktarda kayit eklemek icin PreparedStatement metodu kullanilabilir. 
		// PreparedStatement hem hizli hem de daha guvenli (SQL injection saldirilari icin) bir yontemdir. 
		// Bunun icin; 
		//    1) Veri girisine uygun bir POJO(Plain Old Java Object) sinifi olusturulur.
		//    2) POJO Class nesnelerini saklayacak bir collection olusturulur
		//    3) bir dongu ile kayitlar eklenir. 
		 
		List<Urun> kayitlar = new ArrayList<>();
		kayitlar.add(new Urun(101,"laptop", 6500));
		kayitlar.add(new Urun(102,"PC", 4500));
		kayitlar.add(new Urun(103,"Telefon", 4500));
		kayitlar.add(new Urun(104,"Anakart", 1500));
		kayitlar.add(new Urun(105,"Klavye", 200));
		kayitlar.add(new Urun(106,"Fare", 100));
		
		PreparedStatement pst = con.prepareStatement("INSERT INTO urunler VALUES(?,?,?)"); // burada verileri bol bol yazmak yerine ? koyduk.
		
		for (Urun w : kayitlar) {
			pst.setInt(1, w.getId());
			pst.setString(2, w.getIsim());
			pst.setDouble(3, w.getFiyat());
			
			pst.addBatch();
		}
		pst.executeBatch();
		
		con.close();
		pst.close();
		st.close();
	}

}
