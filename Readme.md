# NexGenRentProject

## Proje Tanımı

Bu proje, Tobeto kursu kapsamında geliştirilen bir uygulamadır. NexGenRentProject, katmanlı mimari kullanılarak geliştirilmiş bir kiralama sistemini içermektedir. Proje, tüm veritabanı işlemleri, servis katmanı ve repository katmanını içermektedir.
## Güncellemeler

**13.12.2023 Tarihli Güncellemeler:**

- Sisteme Swagger desteği eklendi.

- ModelId ile ilişkilendirilen modelin veritabanında varlığını doğrulamak için Service katmanında gerekli kodlamaları gerçekleştirildi. Bu, Car entity'si için GetAll ve Add işlemlerini daha sağlıklı hale getirildi.

- Car entity'si için CRUD işlemlerini gerçekleştirmek üzere Service katmanında "Car" entity'si kodlamalarını tamamlandı. Bu kodlamalar aracılığıyla controller katmanında ilgili endpoint'leri bağlandı.

- Ekleme ve güncelleme işlemlerinde yapılan kontrol ve validasyonlar:
  - "Kilometer" alanı 0'dan küçük olamaz.
  - "Plate" alanı için Türkiye plakasına uygunluğu Regex ile kontrol edilir.
  - Yıl bilgisi 2005-2024 arasında olmalıdır.
  - DailyPrice 0'dan küçük olamaz.
  - ModelId ve ColorId 0'dan küçük olamaz.
  - Araç eklenirken "Plate" kısmındaki boşluklar kaldırılarak kaydedilir.
  - Verilen ModelId ile bir model veritabanında bulunmalıdır (ModelService içerisinde kodlama yapıldı).
  - Verilen ColorId ile bir renk veritabanında bulunmalıdır.
  - Aynı plakaya sahip başka bir araç bulunmamalıdır.

Yapılan bu güncellemelerle sistem daha güvenilir ve sağlıklı bir şekilde çalışmaktadır.

## Özellikler

- **Katmanlı Mimarisi:** Projede, katmanlı mimari kullanılmıştır. Bu sayede kodun modüler olması sağlanmış ve bakım kolaylığı elde edilmiştir.

- **Veritabanı Nesneleri (Entities):** Tüm veritabanı nesneleri oluşturulmuştur. Bu nesneler, veritabanındaki tabloları temsil eder ve iş mantığıyla ilişkilidir.

- **Repository Katmanı:** Tüm veritabanı nesneleri için repository katmanı oluşturulmuştur. Bu katman, veritabanı işlemlerinin (ekleme, güncelleme, silme, sorgulama vb.) yönetilmesini sağlar.

- **Service Katmanı:** Şu anlık içi boş olmakla birlikte, tüm servis katmanı dosyaları oluşturulmuştur. Bu katman, iş mantığının uygulandığı ve servis fonksiyonlarının bulunduğu bölümdür. İlerleyen aşamalarda bu fonksiyonlar doldurulacaktır.
