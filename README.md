## Testleri Çalıştırma

*config/config.properties* dosyası içerisinde browser ve driver path'i güncelleyiniz. Test class'ları *src/test/java/testclasses/* altındadır. Junit @Nested annotation'ı kullanılarak organize edilmiştir.

## Dosya Lokasyonları
Proje bir adet log dosyası üretmektedir. Ayrıca ürün adının yazdırıldığı bir adet genel amaçlı txt dosyası üretmektedir. Bunun yanında test datası dataların json şeklined tutulduğu dosyadan okunmaktadır.
Lokasyonlar: 
src/main/resources/productinfo.txt
src/main/resources/logfile.log
testdata/testdata.json

# Önemli Not
**n11.com'a Selenium ile login gerçekleştirilemiyor!**
