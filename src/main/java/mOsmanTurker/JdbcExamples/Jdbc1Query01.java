package mOsmanTurker.JdbcExamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// bu importlar yerine  "import java.sql.* " yazarak tum kutuphaneleri getirebiliriz.

public class Jdbc1Query01 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		// 1- ilgili driveri yuklemeliyiz. yukleme ici gerekli kod asagidaki gibi
		// olacak. (fisi taktik)

		Class.forName("oracle.jdbc.driver.OracleDriver");

		// 2- baglanti olustur.(netflix'e baglanma gibi)

		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "hr", "hr");
		// java.sql.Connection import ettik.
		// yukaridaki baglanti objesi olustrdu ve bunu con a bagladik.

		// 3- SQL komutlari icin bir statement (alan) nesnesi olusturalim. (alan olustrduk kumanda da kanal ayarlama)...

		Statement st = con.createStatement();
		// java.sql.Statement import ettik.
		
		// 4- SQL komutlarini yazabilir ve calistirabiliriz.(kumanda da istedigimiz komuta basma ses acma kanal degistirma gibi...)
		
		// (personel tablosundaki personel_id'si 7369 olan personelin adini ve maasini sorgulayalim...)
		
		ResultSet isim = st.executeQuery("SELECT personel_isim,maas FROM personel WHERE personel_id = 7369");
		
		// 5- Sonuclari aldik ve isledik
		
		while (isim.next()) {
			System.out.print("Personel isim = "+isim.getString("personel_isim") +" "+"\nMaas = "+ isim.getInt("maas"));
																// 1 yazilinca da calisir.						//2 yazilinca da calisir.
		}
		
		// bu islemleri yaparken bircok nesne olusturduk. bu nesneler bellekte cok yer kaplar ve gereksiz yorar.
		// 6- olusturulan nesleri kapatma islemi
		con.close();
		st.close();
		isim.close();
		
		

	}

}
