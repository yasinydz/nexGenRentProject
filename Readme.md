# NexGenRentProject 🚗

## Proje Tanımı 📚 

Bu proje, Tobeto kursu kapsamında geliştirilen bir uygulamadır. NexGenRentProject, katmanlı mimari kullanılarak geliştirilmiş bir kiralama sistemini içermektedir. Proje, tüm veritabanı işlemleri, servis katmanı ve repository katmanını içermektedir.

---

## Katkıda Bulunanlar 🛠️

Bu projeye katkıda bulunanlar aşağıda listelenmiştir. Projeye katkı sağlayan herkese teşekkür ederiz!

- [Hüseyin Demirel](https://github.com/HsynDmrl) 🤝
- [Yasin Yıldız](https://github.com/PickerWork) 🤝
- [Ege Kavcıoğlu](https://github.com/EgeKavcioglu) 🤝
- [Mustafa Tayyar](https://github.com/urmustafa) 🤝

--- 

## Veritabanı ER Diagramı 📊 

Aşağıda, PostgreSQL üzerinde oluşturulan ER (Entity-Relationship) diagramı bulunmaktadır:

![ER Diagram](https://github.com/PickerWork/nexGenRentProject/raw/master/Diagram.png)

Bu diagram, projenin veritabanı yapısını göstermektedir. Detayları incelemek için lütfen [buraya](https://github.com/PickerWork/nexGenRentProject/blob/master/Diagram.png) tıklayın.

---

## Güncellemeler 📢 

<details>
<summary>15.12.2023 Tarihli Güncellemeler 📅</summary> 

- Auto Mapping işlemi yapan "Model Mapper" implementasyonu projeye eklenmiştir. Artık araç verileri listelenirken, Sırasıyla plaka, kilometre, ücret, yıl, model adı ve renk adı bilgileri görüntülenmektedir.

- Tüm işlemler için Request-Response pattern'a uyulmuştur.

- Araç eklenirken "Plate" kısmındaki boşluklar kaldırılarak kaydedilmektedir.

- ModelService içerisinde ilgili kodlama yapıldı. Bir service, diğer bir entity'nin reposunu değil, ilgili service'ini çağırıyor.
  
- ER Diagramı projeye eklenmiştir.
  
</details>

<details>
<summary>13.12.2023 Tarihli Güncellemeler 📅</summary>

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

</details>


## Özellikler 💡

- **Katmanlı Mimarisi:** Projede, katmanlı mimari kullanılmıştır. Bu sayede kodun modüler olması sağlanmış ve bakım kolaylığı elde edilmiştir.

- **Veritabanı Nesneleri (Entities):** Tüm veritabanı nesneleri bulunmaktadır. Bu nesneler, veritabanındaki tabloları temsil eder ve iş mantığıyla ilişkilidir.

- **Repository Katmanı:** Tüm veritabanı nesneleri için repository katmanı bulunmaktadır. Bu katman, veritabanı işlemlerinin (ekleme, güncelleme, silme, sorgulama vb.) yönetilmesini sağlar.

- **Service Katmanı:** Service katmanı, iş mantığının uygulandığı ve servis fonksiyonlarının bulunduğu bölümdür. Abstracts içinde service'ler, concretes içinde manager'lar bulunmaktadır. DTOs içinde ise request ve response objeleri yer almaktadır. Bu katman, genel projenin fonksiyonelliğini yönetir.

- **Mapperlar:** Core.Utilities içindeki mapper'lar, veritabanı nesneleri ile DTO'lar arasında veri dönüşümlerini gerçekleştirmek için kullanılmaktadır.

