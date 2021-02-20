# ASB-Braille-Keyboard
Virtual keyboard based on Braille writing system for people who has visual problems.

# Açıklama [TR]

## ÖNSÖZ

Dünya üzerinde 1 milyardan fazla engelli birey yaşamaktadır (ey-der)[1], ve hayatın belirli noktalarında zorluk çekmektedirler.
ASB Braille Klavyesi, braille alfabesi ilkelerini kullanarak görme engelli insanların mobil cihazlarda yazılı mesaj girdisi yapma ihtiyaçlarını bir nebze olsun gidermeyi amaçlayan,
görme yetisini tamamen veya kısmen yitirmiş bireylerin rahatca kullanabilceği bir sanal klavye uygulamasıdır.
Bu raporda görme engelli bireylerin sorunları ve hayatta karşılaşabildikleri zorluklar tanımlanmış, braille alfabesi ve kuralları tanıtılmış, 
sanal klavyenin ne olduğu ve bahsi geçen ASB Braille klavyesinin nasıl çalıştığı teknik detaylarla açıklanmıştır.
Bu projeye teşvik ve desteklerinden dolayı sayın Prof. Dr. Ali Boyacı öğretmenime teşekkürlerimi sunarım.

## İÇİNDEKİLER

<ul>
  <li>ÖZET</li>
  <li>1.GİRİŞ</li>
  <li>
    <ul>
      <li>1.1 Mobil Cihazlarda Sanal Klavye Hakkında</li>
      <li>1.2 Görme Engelli İnsanlar Hakkında</li>
      <li>
        <ul>
          <li>1.2.1 Görme Engelli İnsanların Hayatta Karşılaştıkları Zorluklar</li>
        </ul>
      </li>
      
    </ul>
  </li>
  
  <li></li>
</ul>


## ÖZET

ASB Braille klavyesi, görme engelli bireylerin dokunmatik ekranlı mobil cihazlarda yazı girişi yapılabilen herhangi bir yazı alanında (Text Area) yazılı mesaj girdisi yapma ihtiyaçlarını kolaylaştırmayı amaçlamaktadır. 
Mobil cihazın varsayılan klavyesinin kullanıldığı her yazı alanında kullanılabilir olan bu klavye görme engeli bulunmayan insanlar için tasarlanmış geleneksel sanal klavye modeli, görme engelli bireyler için kullanılması oldukça güç bir modeldir. 
ASB Braille Klavyesi oluşturulurken görme engelli bir bireyin ihtiyaçları doğrultusunda rahatça kullanabileceği şekilde tasarlanmıştır. 
İstenilen karakterin ekrana aktarılması için Braille alfabesi kurallarından yararlanılır. 
Braille alfabesi, Fransız eğitimci ve kaşif olan Louis Braille tarafından 19.yy'da geliştirilmiştir (Wikipedia,2020)[2]. 
Braille alfabesi ile her bir yazım karakteri (harf, sayı, noktalama işaretleri...), 3 satır ve 2 sütundan oluşan bir düzende sıralanmış 6 adet noktanın işaretlenmesi (kabartılması) ya da işaretlenmemesi (kabartılmaması) durumları ile temsil edilir.
Kullanıcı mobil cihazda bir yazma alanı ile etkileşime girdiğinde sanal klavye olarak açılacak ASB Braille klavyesi ile, yazmak istediği karakterin braille kodunu ekrana dokunarak belirtir.
Telefon ekranı, seçilebilir 6 noktadan oluşan boş bir braille kodu olarak düşünülürse, yazılmak istenilen karakterin braille kodunda kullanılan noktaların konumlarına basılır ve ekrana sanal braille kodu betimlemesi yapılır. 
Her karakter için en az 1 dokunuş gereklidir ve en fazla 6 dokunuş yapılabilir. 
Karakterin sanal betimlemesi yapıldıktan sonra, ekrana dokunulmadan geçen ve kişiye göre ayarlanabilen belirli bir zaman aralığı geçtikten sonra, betimlenen karakter klavye algoritması tarafından belirlenir ve yazı girdisi yapılmak istenilen alana istenilen karakterin girdisi yapılmış olur. 
Klavye aynı zamanda yazım sırasında titreşimli, girdi yapıldıktan sonra titreşimli ve sesli olmak üzere geri bildirimler uygular. 

## GİRİŞ

### 1.1 Mobil Cihazlarda Sanal Klavye Hakkında

Mobil cihazlarda yazı girdisi yapabileceğimiz alanlar (text area) ile etkileşime girdiğimizde, işletim sistemine tanımlı olan ve seçili olan klavye ekrana gelir ve girdi işlemi bu sanal klavye aracılığı ile yapılır. 
Sanal klavyenin desteği ile kullanıcının açık ve anlaşılır bir şekilde ekrana girdi yapmak istediği yazı karakterinin seçilmesi sağlanır. 
Bu sayede cihaz, seçilen karakteri yazı alanına bastırabilir.
Geleneksel sanal klavyeler genel kullanıcıya hitap etmek amacıyla, fiziksel yazı klavyesi baz alınarak dokunmatik ekranda kullanılması amaçlanarak sanal ortama uyarlanmıştır ve mecburen herhangi bir engeli bulunan kullanıcılara fazla kolaylık sağlayamaz.
ASB Braille klavyesi, görme engelli bireylere uygun bir sanal klavye ihtiyacına yönelik geliştirilmiştir.

### 1.2 Görme Engelli İnsanlar Hakkında

Her insanın bir engelli adayı olduğu dünyamızda doğuştan ya da sonradan vücudundaki bir fonksiyonu yitiren bireyler mevcut. 
Bu bireyler arasında, görüşünde kısmen ya da tamamen bozukluk olan, görme yetisini kısmen ya da tamamen yitirmiş kişilere görme engelli denmektedir. 
Bir bireyin görüş bozukluğu Dünya Sağlık Örgütü (WHO) tarafından, ışık şiddetinin gözde algılanma oranına göre farklı derecelerde şu şekilde kategorize edilmiştir (Maberley DA ve diğerleri ,2006)[3] ;
20/30-20/60 oranda ışık algısı : Hafif derecede görüş kaybı.
20/70-20/160 oranda ışık algısı : Orta derecede görüş kaybı.
20/200-20/400 oranda ışık algısı : Şiddetli derecede görüş kaybı.
20/500-20/1000 oranda ışık algısı : Çok şiddetli derecede görüş kaybı.
20/1000 üzeri oranda ışık algısı : Neredeyse tamamen görüş kaybı.
0 ışık algısı : Tamamen görüş kaybı.

Görme sorunları sonradan çevresel etkenlerle oluşabileceği gibi, kalıtsal yollarla da aktarılabilir. 
Görme sorunlarının en büyük iki sebebi; miyop ve hipermetrop olarak adlandırılan göz merceğindeki kırılma sorunları (%42 ihtimalle) ve katarakt (%33 ihtimalle) olarak kaydedilmiştir (WHO,2012)[4].  
Bu sorunlar kısmi görüş kaybına neden olabildiği gibi, durumun şiddetlenmesi ile tamamen görüş kaybına da neden olabilir. 
Tamamen görüş kaybına uğrayan insanların %51’i katarakt sebebiyle görüşlerini kaybetmişlerdir (WHO,2012)[5]. 
2012 yılında dünya üzerinde yaşayan insanların %0,58’inin tamamen görme engelli olduğu kaydedilmiştir (WHO,2012)[6]. 
Şiddetli görme engelli bireylerin çevrelerindeki engellerden önceden haberdar olmak için temel olarak engelli bastonlarından ya da kılavuz köpeklerden yararlanabilmektedirler.

### 1.2.1 Görme Engelli İnsanların Hayatta Karşılaştıkları Zorluklar

Görme engelli bireyler hayatın her noktasında, genel insan yaşamına uygun tasarlanmış tasarımlar sebebiyle zorluk çekebilirler ve bazı durumlarda başka bir yardımcıya ihtiyaç duyabilir. 
Görme engelli bir birey dışarı çıktığında karşılaşacağı en temel sorunlardan biri, çevresindeki engelleri ve tehlikeleri görememesidir. 
Görme engelli bireylerin çoğu görme engelli bastonu veya kılavuz köpeklerden yardım almaktadırlar. 
Böylece çevredeki engel ve tehlikelerden bir nebze de olsa kaçınabilirler. 
Bu engeller; yol üzerinde bulunan kaldırım veya yol bozukluklarından kaynaklı çıkıntılar, duvar veya bunun gibi yolu kesen büyük objeler, diğer insanlar, yolda seyir eden araçlar olabilir. 
Tehlike teşkil eden durumlar dışında, görme engelli kişiler ihtiyaç durumunda hayatın imkanlarını kullanmakta zorluk çekebilirler. 
Toplum tarafından sunulan imkanlar engelli bireyler de gözetilerek tasarlanmadıysa bu durum yaşanan sorunu daha da büyük hale getirir. 
Bu sorunlar var olduğundan beri, bu sorunlara çözüm bulmak isteyenler insanlar da mevcuttur. 
Kamu alanlarında engelli bireylerin konfor ve güvenliği gözetilerek tasarlanan görme engelli şeritleri, braille kabartması içeren açıklamalar, sesli geri bildirimlere rastlamak mümkündür. 
Bu tasarımlar engelli bireylerin hayatta karşılaştıkları zorlukları bir nebze de olsa azaltmak amacıyla yapılmıştır.

### 1.3 Braille Alfabesi Hakkında

### 1.3.1 Louis Braille 

Louis Braille 4 Ocak 1809 tarihinde Fransa’da Coupvray kasabasında doğmuştur. 
Babası saraç olarak çalışan Louis, küçükken babasının atölyesinde oyun oynayarak vakit geçirirken, bir deri parçasına iğne batırmaya çalışırken iğnenin kayması ve gözüne batmasıyla bir gözünü kaybetmiştir. 
Yaralanan gözün enfeksiyon kapmasıyla ve diğer göze enfeksiyonun sıçramasıyla Louis Braille iki gözünü de 5 yaşında iken kaybeder. 
Louis, daha sonra Fransız ordusunda görev alan Captain Charles Barbier’ın icat ettiği Barbier alfabesini öğrenir. 
Bu iletişim yöntemi askeriyede gece yazışı olarak adlandırılırdı ve askerlerin ışığa ihtiyaç duymadan iletişim kurmasını sağlamayı amaçlıyordu. 
Barbier’ın geliştirdiği sistem daha sonra Louis’in geliştireceği Braille alfabesine göre oldukça karışıktı. 
Bu karışıklığı basite indirgemek için Louis, her bir karakterin 12 nokta ile temsil edildiği Barbier alfabesini geliştirerek her karakterin 6 nokta ile temsil edilebildiği Braille alfabesi sistemini geliştirdi. 
Bugün bile olduğu gibi değişmeden kullanılan Braille alfabesini icat ettiğinde Louis 15 yaşındaydı. 
Daha sonra 1829 tarihinde kendi alfabe sistemini, sonradan eklediği matematik ve müzik sembolleri ile birlikte yayınladı. 
40 yaşında solunum rahatsızlığı sebebiyle geri döndüğü Coupvray kasabasında 1852 yılında vefat etti. 
Ölümünden sonra, geliştirmiş olduğu alfabe sistemi dünya çapında kabul gördü ve bir standart haline geldi. (Royalblind)[7].

### 1.3.2 Braille Alfabesi Kuralları

Braille alfabesi kullanılacağı yüzeylerde kolay anlaşılması ve verimli olması için, belirtilmek istenen karakter 6 farklı noktanın her birinin kabartılması ya da kabartılmaması durumlarının çeşitliliğinden yararlanılarak belirtilir. 
6 noktadan her birinin kabartılması ya da kabartılmaması durumu bize 2^6 = 64 farklı karakteri ayrı ayrı belirtebilmemize olanak sağlar (PharmaBraille)[8].

IMAGE

Tüm braille karakterlerini sayısal bir braille kodu ile belirtmek için, bir braille karakterinde kullanılan noktalar sol üstten sol alta ve sağ üstten sağ alta sıralı şekilde 1’den 6 ‘ya kadar numaralandırılır.

IMAGE

Yalnızca kabartılmış (işaretlenmiş) noktalara ait numaraların küçükten büyüğe sıralanması ile oluşan rakamsal kod, braille karakterini görsel olmadan belirtmekte kullanılır. 
İlerleyen bölümlerde bu yöntem kullanılacaktır.

IMAGE

Braille alfabesinde dünya çapında genel olarak kullanılan karakterlerin dışında Türkçe karakterler, noktalama işaretleri ve bazı özel karakterler de mevcuttur. 
Braille alfabesinde bazı durumlarda sıradaki kullanılacak olan karakter ya da karakterlerin nasıl yorumlanacağını belirten belirteçler (indicators) kullanılır. 
Mesela bir sonraki tekil karakterin büyük harf olmasını belirtmek için tekil büyük harf belirteci kullanılır (Braille kodu=6). 
Sonraki boşluğa ya da yeni satıra kadar olan tüm karakterlerin büyük harf olacağını belirtmek için çift büyük harf belirteci kullanılır. 
Yani bir büyük harf belirteci kullanıldıktan sonra bir kere daha büyük harf belirteci kullanılırsa bu, bir sonraki boşluk veya satır başına kadar tüm harflerin büyük harf olacağı anlamına gelir. 
Harf karakterlerini rakamlardan ayırmak için rakam belirteci kullanılır (Braille kodu=3456). 
Rakam belirtecinden sonra bir boşluk ya da satır başına kadar kullanılan tüm kodlar harf olarak yorumlanır. 
Boşluk veya satır başından önce harf kullanımına geçmek için harf belirteci kullanılır (Braille kodu=56).

IMAGE

Bazı semboller ve özel karakterler de kullanılmadan önce belirtece ihtiyaç duyabilir. 
Mesela “(” işaretini kullanmak için Braille kodu 5 olan bir belirteç kullanılır ve ardından Braille kodu 126 olan bir kod kullanılır. 
“)” işareti için ise aynı belirteçten sonra 345 kodu kullanılır.

### 1.3.3 Braille Alfabesi Kullanım Alanları

Braille alfabesi insanların yazı ile etkileşime girmek istedikleri her yerde görme engelli bireylere kolaylık sağlamak amacıyla kullanılabilir. 
Braille alfabesi kullanılarak kabartılan yüzeylere sahip kaynaklar sayesinde görme engelli bireyler kitap, dergi ve makale gibi yazılı kaynaklara erişip okuyabilir. 
Kabartma yöntemini bir kağıda uygulamayı mümkün kılarak braille alfabesi ile yazı yazma olanağı sağlayan araçlar da vardır. 
Braille alfabesinin kullanımı için geliştirilen özel donanımlar, elektronik cihazlar da mevcuttur. 
Yüzeyindeki kabarıklıkları kontrol ederek yazı okumayı sağlayan ve mobil cihazlarla uyumlu çalışan elektronik yardımcı cihazlar da vardır.
Günlük sosyal hayatta en genel kullanım şekli, yazılı bilgi bulunduran bir yüzeye görme engelliler için braille alfabesine uygun şekilde kabartmasıdır. 
Bu şekilde aynı bilgilendirmeden görme engelli bireyler de faydalanabilir. 
Günlük hayatta bu yöntem en çok asansör düğmelerinde, trafik ışığı düğmelerinde ve bazı tabela ve işaretlerde karşımıza çıkar.

### 1.4 Neden Sesli Komut Yerine Sanal Klavye Tercih Edilebilir?

Görme engelli bireyler için kolay yazı girdisi ve komut bildirimi sağlayabilen bir yöntem olarak sesli komut kullanmak, bir çok mobil cihazda mümkün. Ancak kullanıcı özel bazı durumlar dahilinde sesli komut yerine kullanımı daha zahmetli olan ve braille alfabesini bilmeyi gerektiren bir braille sanal klavyeyi sesli komut yerine kullanmayı tercih edebilir.
Kullanıcının sesli komut yerine sanal klavyeyi tercih edebileceği bazı durumlar şunlardır;
Görme engelli bir kullanıcının aynı zamanda konuşma engeline de sahip olması.
Kullanıcının, sesli komutun anlaşılamayacağı gürültülü bir ortamda bulunması.
Çevredeki diğer bireyler tarafından duyulmasını istemeyeceği özel bir mesajın yazılmak istenmesi.
Kullanıcının sessiz veya ses çıkarmak istemeyeceği bir ortamda bulunması.
Yazılı girdinin, bir şifre gibi başka insanların duyduğunda güvenlik sorunları oluşturabilecek bir bilgi olması.
Yazılmak istenen bilginin, sesli komut tarafından doğru algılanamayacak bir yazı olması.
Geçici olarak farklı bir dilde yazım yapılacak ise, cihazın diline göre ayarlanmış sesli komut sisteminin bunu doğru çevirmede sorun yaşayabilecek olması.

### 1.5 ASB Braille Klavyesi Hakkında

ASB Braille klavyesi, görme engelli bireyler için Braille alfabesinin olanaklarından faydalanarak mobil cihazlarda yazı girdisi yapmaya olanak sağlıyor. 
Bu klavyeyi kullanabilmek için Braille alfabesini bilmek yeterli olacaktır.

### 1.5.1 Braille Alfabesinin Sanal Klavyeye Uyarlanması

6 noktalı Braille alfabesinin mobil cihazlarda sanal klavye ile kullanımı için, klavye ile etkileşime girildiğinde cihaz ekranını tamamen kaplayan sanal klavye açılır ve cihazın dokunmatik yüzeyinin boş bir braille karakteri olduğu varsayılır. 
Kullanıcı bir yazı karakterini klavyeye betimlemek için braille alfabesinde o karakterin noktalarının bulunduğu konumlara dokunmatik ekranın sınırları içerisinde birer kere basması yeterli olacaktır. 
ASB Braille klavyesi temel olarak bu yöntemi baz alır.

### 1.5.2 ASB Braille Klavyesinin Cihaza Kurulumu

Klavyeyi Android işletim sistemli bir mobil cihaza kurmak için ilk olarak uygulamanın APK kurulum dosyası çalıştırılmalıdır. 
Sanal klavye cihaza kurulduktan sonra cihazın ayarlar menüsünden sanal klavye yönetimi bölümünde bu klavyenin etkinleştirilmesi gereklidir. 
Daha sonra ayarlar menüsünden varsayılan klavye olarak veya cihazla bir yazı alanı ile etkileşime girildiğinde klavye ile birlikte sağ alt kısımda çıkan klavye seçim menüsünden kullanılacak klavye olarak ASB Braille Klavyesi seçilerek kullanılabilir.

### 1.5.3 ASB Braille Klavyesinin Kullanımı

### 1.5.3.1 Yazılacak Karakterin Dokunmatik Ekranda “Betimlenmesi”

Yazılmak istenen karakterin dokunmatik ekran ve sanal klavye aracılığı ile cihaza aktarılmasına betimleme işlemi dersek, istenilen karakter betimlenirken, o karakterin braille kodunda bulunan noktaların konumları cihazın dokunmatik ekranına iz düşürülerek bu noktalara bir kere tıklanır. 
Sanal klavye, konumlama işleminde mükemmel bir tutarlılık gerektirmeyecek şekilde tasarlanmıştır. 
Belirli bir düzeye kadar hata tolere edilebilir. Her bir dokunuştan sonra klavye, kısa bir titreşim ile ekrana dokunulduğunu belirtecektir. 
Dokunuşlar arasında belirli bir gecikme süresi vardır ve her dokunuşta bu süre sıfırlanır. 
Bu gecikme süresi aşılırsa, bu süre aşılana kadar dokunulan her bir nokta kaydedilir ve bu noktaların benzediği Braille karakteri girdi olarak kabul edilir, bir uzun titreşim ve girilen karakterin seslendirilmesi ile geri bildirim yapılır.
Örneğin klavye açıldığında ekranın sol üst kısmına bir kere basılıp bırakılırsa, gecikme süresi dolduktan sonra ekrana “a” karakteri girilecektir. 
Büyük harf ve rakamlar için kullanılan belirteçler de braille alfabesinde nasıl kullanılıyorsa bu klavyede de aynı şekilde kullanılacaklardır. 
Boşluk işlemi için klavye açıkken ekran sağa, silme işlemi için sola, giriş (enter) işlemi için yukarı, klavyeyi kapatmak (gizlemek) için aşağı kaydırılmalıdır. 

### 1.5.3.2 Belirteç Kullanımı

Klavyede büyük harf, numara ve özel karakter belirteçlerini kullanmak için, yazılacak karakterlerden önce kullanılmak istenen belirtecin braille kodu betimlenir. 
Belirteçlerin çalışma prensibi ve ömrü normal braille alfabesindeki ile aynıdır. 
Yani tekli büyük harf belirteci, bir harf büyük yazıldıktan sonra etkisiz hale gelir. 
Bütün kelimeyi büyük harf yazmaya yarayan çift büyük harf belirtecinin kullanılması, bir sonraki boşluğa veya satır başına kadar etkili olur. 
Numara belirteçi de aynı şekilde boşluk veya satır başı olana kadar etkisini devam ettirir. 
Bunlardan önce dilendiğinde harf belirteci ile harf yazmaya devam edilebilir. 
Özel karakterler için de durum aynıdır. İstenilen özel karakter betimlenmeden önce belirteçi betimlenir.

### 1.5.3.3 Ayarlar

Sanal klavyenin yazma gecikme süresini, ses ve titreşim ayarlarını yapılandırmak için ayarlar menüsüne girilebilir. 
Ayarlar menüsüne geçmek için, braille kodu 123456 olan ve tüm noktaların kullanıldığı braille karakteri yazılır. 
Ayarlar menüsüne geçildiği sesli bir geri bildirim ile bildirilir. Burada her bir nokta bir ayar seçeneğini temsil eder. 
1 numaralı nokta sesli geri bildirimleri kapatır.
4 numaralı nokta sesli geri bildirimleri açar.
2 numaralı nokta yazma gecikmesini azaltır (betimlenen karakter daha hızlı girilir).
5 numaralı nokta yazma gecikmesini arttırır (betimlenen karakter daha yavaş girilir).
3 numaralı nokta titreşimleri kapatır.
6 numaralı nokta titreşimleri açar.
Ayarlar menüsünden çıkılmak istendiğinde, giriş için kullanılan 123456 braille kodunun yazılması yeterlidir. 

### 2. METOT VE MATERYAL

### 2.1 Android İşletim Sistemi Hakkında

Android, mobil cihazlar için geliştirilen ve linux çekirdeğinin ve diğer açık kaynak yazılımların değiştirilmesiyle elde edilen, dokunmatik ekran desteği sunan bir mobil işletim sistemidir. 
“Open Handset Allience” adı ile bilinen bir geliştiriciler topluluğu tarafından, başlıca Google destekleriyle geliştirilmiştir ve ilk android mobil telefon 2008 yılında piyasaya sürülmüştür (Wikipedia,2020)[10]. 
Ücretsiz ve açık kaynak olan bu sistem (içinde yüklü gelen harici servis ve uygulamalar var ise bunlar hariç) Apache lisansına sahiptir. 
Bu lisans sayesinde her kullanıcı, bu lisansa sahip her ürünü kullanabilir, değiştirebilir ve istediği gibi dağıtabilir (New Media Rights,2015)[11]. 

### 2.2 Yazılım Ortamı ve Kullanılan Araçlar Hakkında

### 2.2.1 Java Dili Hakkında

Uygulamanın yazılım geliştirme kısmında kullanılan dil, tasarım için kullanılan XML dışında Java dili olmuştur.
Java dili, Sun Microsystems tarafından ilk olarak 1995 yılında geliştirilen, obje ve sınıf tabanlı genel amaçlı bir programlama dilidir. 
Java dili ile yazılan yazılım, Java yazılım platformunun kurulu olduğu her cihazda  çalıştırılabilir. 
Android işletin sisteminin geliştirilmesinde büyük ölçüde kullanılmıştır (Wikipedia,2020)[12]. 

### 2.2.2 Android Studio Hakkında

Uygulamanın geliştirildiği tek ortam olan Android Studio, Android işletim sistemi için uygulama ve servis geliştirmeye yarayan bir uygulama geliştirme ortamıdır.

### 2.2.3 Input Method Editor

Android cihazlarda yazı girişini kontrol etmeye yarayan bir çatıdır (framework). Klavye servisi geliştirilirken yazılımın temelini oluşturur. 
Bu yapı sayesinde kullanıcının girdi yapmak istediği karakter anlaşılır ve cihazda o karakterin girdisi yapılır.

### 2.3 Klavye Algoritması ve Çalışma Prensibi

### 2.3.1 Karakter Betimlemesinin Algılanması

Uygulama ön yüzünde betimlenen karakterin doğru şekilde algılanması ve hataların kapatılabileceği bir zemin hazırlanması amacıyla özel olarak konumlandırılmış 28 farklı düğmeden oluşur.

IMAGE

Bu düğmeler yeşil renk ile gösterilen “Kesin doğruluk” ve sarı renk ile gösterilen “Olası doğruluk” olarak iki kategoriye ayrılmıştır. 
Braille karakterinde noktaların olması gereken konumlarına asıl yakın ve büyük olan düğmeler “Kesin doğruluk” düğmelerdir. 
Bu düğmelere basıldığında kullanıcının bastığı noktada hata yapmadığı varsayılarak kesinlikle dokunulan konumda bir nokta olması gerektiği anlamı çıkartılır. 
Kesin doğruluklu düğmelerin etrafını saran ve farklı Kesin doğruluklu düğmeleri arasında ayraç sayılabilen “Olası doğruluk” düğmelerine basıldığında, kullanıcının bastığı noktada olası bir biçimde hata olabileceği düşünülür. 
Yani bir kullanıcı 1 numaralı düğmeye basmak yerine 4 numaralı düğmeye yakın basmış olabilir.
Her düğme, basıldığında kullanıcının kaç numaralı braille noktasına basmak istemiş olabileceğinin bilgisini tutan bir değer taşır. 
“Kesin doğruluk” kategorisinde olan düğmelere basıldığında, hata yapılmadığı varsayılacağı için bu düğmeler yalnızca ait oldukları noktanın numarasını taşır. 
Örneğin sol üstteki düğme 1 numaralı braille noktasına yakın olan kesin bir düğme olduğu için yalnızca “1” değerini taşır. 
Bu düğmenin bir sağında bulunan düğme “Olası doğruluk” bir düğmedir. 
Ve bu düğmeye basıldığında öncelikli olarak 1 numaralı noktaya, ikincik olarak 4 numaralı noktaya basılmak istendiği anlamı çıkartılır. 
Bu nedenle bu düğme “14” değerini taşır. “Olası doğruluk” kategorisinde olan düğmelerin taşıdıkları değerlerin soldaki rakamı her zaman öncelikli olan değerdir ve büyük ihtimalle kullanıcının dokunmak istediği braille noktasının numarasıdır. 
Sağdaki diğer rakam ise bir hata durumunda kullanıcının dokunmak istemiş olabileceği bir diğer braille noktasının numarasıdır.

IMAGE

Kullanıcı girmek istediği karakterin braille kodundaki noktaların konumlarına tıklarken, birden fazla nokta gerektiren braille karakterleri için ekrana birden fazla tıklamalıdır. 
O an tıklanan konumdaki noktaların tek bir karaktere mi yoksa birden farklı karaktere mi ait olduğunun anlaşılabilmesi için, yazılan karakterin betimlenmesi (yani braille karakterinin noktalarının konumlarına dokunulması) ile onaylanması arasında belirli bir yazma gecikmesi vardır. 
Eğer kullanıcı betimleme yaparken belirli bir süre ekrana hiç dokunmaz ve yeni bir noktayı betimlemez ise, gecikme süresi dolduğunda betimlenen tüm noktalar tek bir karaktere ait olarak kaydedilir. 
Her bir betimlemede ekrana kaç kere dokunulduğu sayılır ve bu sayede betimleme bittiğinde, betimlenen karakterde kaç nokta kullanıldığı bulunmuş olur. 
Her bir karakter betimlenmeden önce içeriği “000000” olan bir current_code string değer oluşturulur. Bu değer her dokunuşla değiştirilecektir. 
Bu string değerinin her bir karakteri ayrı bir braille noktasını temsil eder. 
Mesela sonucu “000000” olan string, boş braille karakterini temsil eder ve “123456” içeriğine sahip bir string tüm noktaları kullanılan bir braille karakterini temsil etmektedir.
Her braille noktası aktif olma durumuna bağlı olarak, string içerisindeki sıra numarası soldan sağa olarak braille kodundaki nokta numarasına göre bir değer alır. 
Kullanıcı bir düğmeye dokunduğunda o düğmeye ait değer, o değerin sıra numarasına göre current_code string’e yazılır. 
Yani yalnızca 1 numaralı braille noktası aktif (kabartılmış/kullanılıyor) ise bu karakterin current_code string değeri “100000” olur. 
Bu yöntemle C harfinin kesin doğruluklu düğmeler ile yazılmış current_code string değeri “100400” olacaktır.
Eğer kullanıcı olası doğruluklu düğmelere basarsa, bu düğmenin taşıdığı değerin en olası rakamı (soldaki) ile belirtilen sıra numarasına ait current_code string değerindeki karakter 0 ise 7 (Yüksek öncelikli olası seçenek) olarak değiştirilir. 
Düğmenin taşıdığı değerin ikincil olası rakamı (sağdaki) ile belirtilen sıra numarasına ait current_code string değerindeki karakter 0 ise 8 (Yüksek öncelikli olası seçenek) olarak değiştirilir.
Kesin doğruluklu bir düğmeye basıldığında, bu düğmenin değeri daha önce olası doğruluklu bir düğme tarafından 7 veya 8 değerleri ile dolduruldu ise, kesin doğruluklu değer öncelikli olacağı için bu 7 veya 8 değeri yerine kesin doğruluklu düğmenin değeri atanır. 
Mesela current_code=“780000” iken 1 numaralı düğmeye dokunulursa current_code=”180000” olur.

Örnek olarak, bahsi geçen düğmelere dokunulmasıyla current_code değerinin değişimi:
current_code=”000000”
dokunulan düğmenin değeri = “2”, dokunma sayısı = 1
current_code=”020000”
dokunulan düğmenin değeri = “3”, dokunma sayısı = 2
current_code=”023000”
dokunulan düğmenin değeri = “65”, dokunma sayısı = 3
current_code=”023087”
dokunulan düğmenin değeri = “6”, dokunma sayısı = 4
current_code=”023086”

Betimleme işlemi bittiğinde dokunulan düğmelerin bilgisini tutan current_code string’i ve ekrana kaç kere dokunulduğunu, yani betimlenen karakterde kaç tane braille noktası kullanıldığını belirten bir dokunma sayısı değeri elde edilir. 
Elde edilen tüm bu bilgiler ile karakter betimlemesinin işlenmesi aşamasına geçilir.

### 2.3.2 Karakter Betimlemesinin İşlenmesi

Karakter betimlemesi aşamasında belirlenen son current_code string’i gereksiz rakamlardan kurtarıp, betimlenen karakterin braille koduna çevirmek için işlem yapılır. 
Betimleme işlemi sırasında her dokunuş ile birlikte arttırılan dokunma sayacı bu aşamada kullanılır. 
Öncelikle current_code string’de bulunan, kesin olasılıklı düğmeler ile elde edilmiş değerlerin adet sayısı sayılır (1 ile 6 arasındaki tüm değerler) ve dokunma sayacının değerinden çıkartılır. 
Eğer kalan sayaç değeri 0 dan büyük ise ve current_code string’i içerisinde olası ihtimalli düğmelerin temsil ettiği muhtemel braille noktalarını temsil eden 7 ve 8 değerleri mevcut ise , öncelikli olan 7 değerleri soldan sağa ele alınır. 
Current_code içindeki her bir 7 değeri, dokunma sayacının 1 azaltılması ile birlikte braille kodundaki sırasına göre bulunduğu konumun değerine çevrilir (Örneğin “007000” -> “003000”). 
Sayaç değeri 0 olana kadar bu işlem devam eder ve current_code içerisindeki tüm 7 değerleri çevrildi ise ve ikincil öncelikli olan 8 değerleri mevcut ise, dokunma sayacı 0 olana kadar yine aynı işlem yapılır. 
Dokunma sayacı 0 olduğunda, 7 ve 8 değeri ile temsil edilen olası noktalar hala daha kaldı ise, bu değerler 0’a çevrilir. 
(Örneğin “020078” -> “020000”)
Tüm bu işlemlerden sonra, betimlenen harfin braille kodunu sade şekilde elde etmek için current_code string’den tüm 0 lar kaldırılır (Örneğin “100406”->”146”). 
Elde edilen değer sadece betimlenen braille karakterinin kullanıcı hatalarından arındırılmış kodu olacaktır.

### 2.3.3 Yapılacak Girdinin Kararlaştırılması ve Girdinin Tamamlanması

Karakter betimlemesi işlendikten sonra elimizde yalın şekilde kullanıcı tarafından betimlenmek istenen braille karakterinin kodunu temsil eden değer kalacaktır. 
Bu değer, daha önce belirtilen bir veri tabanında (karakterlerin braille kodları ve ascii kodlarını içeren arrayler) eşleştirilir ve eşleştiği ascii kodu belirlenir. 
Eğer bu karakter bir belirteç ise (Büyük harf, rakam, ya da uygulamada kullanılan “ayarlar” belirteci) ilgili belirteç aktifleşir ve bir sonraki betimlenen karakter veya karakterleri ve bunların yapacağı girdileri etkiler ve değiştirebilir. 
Mesela bir önceki karakter numara belirteci ise bir sonraki ascii karakterinin numaralar arasındaki karşılığı kullanılır ve girdi işlemi bu ascii koduna göre yapılır.

Klavye algoritmasının çalışma aşamaları temel olarak şöyledir:
1) Bekleme durumundayken; kullanıcı ekranı kaydırarak tek aşamalı silme, boşluk veya giriş işlemlerinden birini yapabilir. Ya da ekrana dokunarak bir braille karakterini betimlemeye başlayabilir.
2) Kullanıcı ekrana dokunduğu an bir geri sayaç başlatılır. Geri sayım bitmeden dokunuşlar ile yapılan her betimleme aynı karakter için sayılır.
3) Geri sayaç bitene kadar ekrana dokunulmazsa karakter betimlemesi bitmiş demektir. Betimlenerek oluşturulan karakterin, betimlenmek istenen braille karakterine ait kodun bulunmasında kullanılacak değerleri kaydedilir.
4) Betimleme sırasında kullanıcı tarafından oluşturulan bu değer işlenir ve betimlenen karakterin yalın braille kodu ortaya çıkarılır.
5) Yazım sırasında aktif olan belirteçler de göz önünde bulundurularak braille kodu kullanılır. Eğer bir girdi yapılacak ise braille kodunun temsil ettiği karakterin karşılığı olan ascii kodu veri tabanından bulunur.
6) Bir braille karakteri için tüm işlemler tamamlanınca, klavye tekrar bekleme durumuna döner.

### 3. SONUÇLAR VE DEĞERLENDİRME

Sonuç olarak, geliştirilen ASB Braille klavyesi görme engelli bireyler için özel olarak geliştirilmiş, cihaza kurulumu yapıldıktan sonra, herhangi bir yazı alanı ile etkileşime girildiğinde girdi işlemi için klavye olarak kullanılabilir. 
Ekrana dokunularak verilen tuş komutlarının yanı sıra aşağı, yukarı, sağa ve sola kaydırma işlemleri ile farklı komutlar verilebiliyor.
Kullanıcı yazma esnasında ve yazdıktan sonra titreşim ve sesli geri bildirimler alınabiliyor. 
Yazma aşamasında dokunulan yerler braille kodundaki kadar tutarlı olmasa bile ve yanlış tuşlara basılsa dahi belirli bir düzeyde hata tolere edilebiliyor ve kullanıcıya daha rahat bir kullanım sunuyor.  
Tüm Türkçe karakterleri destekleniyor ve İngilizce seslendirmenin yanında Türkçe seslendirme de mevcut. 
Ayarlar menüsüne kolayca girip çıkılabiliyor ve yazma gecikmesi, titreşim ve seslendirme ile ilgili ayarlamalar yapılabiliyor.

IMAGE

### LİSANS

"ASB Braille Keyboard" uygulaması "GNU General Public License 2.0" ile korunmaktadır.
Kar amacı gütmeyen bu projede ortaya çıkarılan "ASB Braille Keyboard" sanal klavye uygulaması, tamamen açık kaynak bir uygulamadır ve bu şekilde kalacaktır.

### KAYNAKLAR

[1]EyDer. 23 Mayıs 2020 tarihinde https://ey-der.com/ana-sayfa/turkiye-ve-dunyada-engelliler/ adresinden erişildi.
[2]Wikipedia (2020). 23 Mayıs 2020 tarihinde https://en.wikipedia.org/wiki/Braille adresinden erişildi.
[3] Maberley DA, Hollands H, Chuo J, Tam G, Konkal J, Roesch M, et al. (2006). "The prevalence of low vision and blindness in Canada". Eye. 20.
[4]WHO (2012). “Global Data On Visual Impairment”. sf.6. Fig 2A. 
[5]WHO (2012). “Global Data On Visual Impairment”. sf.6. Fig 2B. 
[6]WHO (2012). “Global Data On Visual Impairment”. sf.5. Table 3. 
[7]Royalblind. 24 Mayıs 2020 tarihinde https://www.royalblind.org/national-braille-week/about-braille/who-was-louis-braille/ adresinden erişildi.
[8]PharmaBraille. 25 Mayıs 2020 tarihinde https://www.pharmabraille.com/pharmaceutical-braille/the-braille-alphabet/ adresinden erişildi.
[9]PharmaBraille (2017). 25 Mayıs 2020 tarihinde https://www.pharmabraille.com/braille-codes/turkey-braille-code/ adresinden erişildi.
[10]Wikipedia (2020). 26 Mayıs 2020 tarihinde https://en.wikipedia.org/wiki/Android_%28operating_system%29#cite_note-12 adresinden erişildi
[11]New Media Rights (2015). 26 Mayıs 2020 tarihinde https://www.newmediarights.org/open_source/new_media_rights_open_source_licensing_guide adresinden erişildi.
[12]Wikipedia (2020). 26 Mayıs 2020 tarihinde https://en.wikipedia.org/wiki/Java_%28programming_language%29 adresinden erişilmiştir.









