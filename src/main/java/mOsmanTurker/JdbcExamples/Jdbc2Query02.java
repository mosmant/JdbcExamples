package mOsmanTurker.JdbcExamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc2Query02 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");		

		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "hr", "hr");
		
		Statement st = con.createStatement();

		// ORNEK 1 BOLUMLER TABLOSUNDAKI TUM KAYITLARI LISTELEYEN BIR SORGU YAZINIZ.
		
		ResultSet tablo1 = st.executeQuery("SELECT * FROM bolumler");
		
		while (tablo1.next()) {
			System.out.printf("%-7d%-15s%-10s%n", tablo1.getInt(1),tablo1.getString(2),tablo1.getString(3));
												// getInt("bolum_id")	// "bolum_isim"		//  "konum"  yazilabilirdi.  
		}
		
		System.out.println("=========================");
		
		// ORNEK 2 SATIS VE MUHASEBE BOLUMLERINDE CALISAN PERSONELIN ISIMLERINI VE MAASLARINI MAAS TERS SIRALI OLARAK LISTELE
		
		String a = "SELECT personel_isim, maas FROM personel "
				+ "WHERE bolum_id IN(10,30) "
				+ "ORDER BY personel_isim, maas DESC";
		
		ResultSet tablo2 = st.executeQuery(a);
		
		while (tablo2.next()) {
			System.out.println(tablo2.getString(1)+"    \t"+tablo2.getInt(2));
		}
		System.out.println("=========================");
		// ORNEK3: Tüm bolumlerde calisan personelin isimlerini, bolum isimlerini ve maaslarini, bolum ve maas sirali listeleyiniz. 
		// NOT: calisani olmasa bile bolum ismi gosterilmelidir.
		
		String b = "SELECT bolum_isim, personel_isim, maas FROM bolumler b  LEFT JOIN personel p ON b.bolum_id =p.bolum_id ORDER BY bolum_isim,maas";
		
		ResultSet sonuc1 = st.executeQuery(b);
		
		while (sonuc1.next()) {
			System.out.println(sonuc1.getString(1)+"     \t"+sonuc1.getString(2)+"     \t"+sonuc1.getInt(3));
		}
		
		System.out.println("=========================");
		
		// ORNEK4: Maasi en yuksek 10 kisinin bolumunu,adini ve maasini listeyiniz
		/*
		String c = "SELECT bolum_isim, personel_isim, maas FROM personel p "
				+ "FULL JOIN bolumler b ON p.bolum_id = b.bolum_id "
				+ "ORDER BY maas DESC "
				+ "FECTH NEXT 10 ROWS ONLY";
		
		ResultSet sonuc2= st.executeQuery(c);
		while (sonuc2.next()) {
			System.out.println(sonuc2.getString(1)+"     \t"+sonuc2.getString(2)+"     \t"+sonuc2.getInt(3));
		}
		*/
		
		// burada oracle database de kronik olan sikinti yuzunden yazamiyoruz. 
		// bunun sebebi sql de yazdigimiz koddan fetch komutunun calismamasindan kaynaklaniyor.
		
		con.close();
		st.close();
		tablo1.close();
		tablo2.close();
		sonuc1.close();
		// sonuc2.close();
		
	}

}
