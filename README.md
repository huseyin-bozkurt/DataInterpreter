## Proje Gelistime Ortami Hakkinda
Proje, Eclipse IDE for Enterprise Java and Web Developers (Version: 2022-06 Build id: 20220609-1112) ve Java 17 (jdk-17.0.4.1) kullanilarak Windows 10 isletim sistemi uzerinde bir maven projesi (maven-3.8.7) olarak gelistirilmistir.

## Calistirma ortami Hakkinda
Uygulama saglikli bir sekilde calisabilmek icin, MongoDB ve Apache Kafka kurulumuna ihtiyac duymaktadir. Harici veri tabani olarak ise H2 veritabani kullanmaktadir, gomulu olarak uygulama icerisindedir ve kurulum gerektirmemektedir. Ilgili kurulumlar tamamlandiktan sonra uygulama icerisinde bulunan application.properties dosyasinda ilgili kurulum bilgileri (Ornegin: kafkaServer=127.0.0.1:9092, mongoConnectionString=mongodb://localhost:27017/homework) girilmelidir.
Uygulama hem birim hem entegrasyon testi icermektedir. Eger, MongoDB kurulumu yapmadan testlerin calisitirilmasi isteniyorsa, entegrasyon testleri pas gecilmelidir. Bunun icin uygulamanin bulundugu klasor icerisinde
```
mvn install -Dtest=!*IntegrationTests*
```
komutu kullanilmalidir.

Eger, MongoDB kurulumu yapildiysa ve uygulama icerisinde bulunan application.properties dosyasinda ilgili kurulum bilgileri (kafkaServer=127.0.0.1:9092, mongoConnectionString=mongodb://localhost:27017/homework) girildiyse entegrasyon testeri de calistirilabilir. Bunun icin uygulamanin bulundugu klasor icerisinde
```
mvn install
```
komutu kullanilmalidir.

## Proje Hakkinda
Proje, Apache Kafka’dan gelecek olan ve “kafkaTopic” adi verilmis olan basligi dinleyecek sekilde konfigure edilmistir. Baslikta bulunan her mesaj otomatik olarak sisteme dahil edilir ve ilgili islemlerden gecirilir.
Istatistiksel islemler icin gelen veri oncelikle MongoDB icerisindeki sensorData dokumanina kaydedilir ve bir Rest ucu tarafindan StatusChange bilgisini disari aktarilabilir.
Operasyonel islemler, gelen verinin islenmesi icin oncelikle H2 veri tabanindan bir sensore ait Batarya Threshold bilgisi okur ve gelen veri icerisindeki sensor voltaj degerinin bu Threshold seviyesinin altinda olup olmadigini kontrol eder. Bu seviyenin altinda olmasi durumunda sensorun bataryasinin degistirilmesi gerektigini bildirmek icin MongoDB icerisinde bulunan batteryChange dokumanini sensor bilgilerini de icerecek sekilde doldurur.
