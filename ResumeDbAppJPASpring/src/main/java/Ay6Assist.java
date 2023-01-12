public class Ay6Assist {

    /*----------------------------------------------------------------------------------------------
    //DERS76 ---------------------------------------------------------------------------------------
    //Topic= JPA-III

    NoteTopics:

    //JPQL;
    Bu JPA'nin query dilidir. (Java Persistence Query Language)
    Bu query dili butun query dillerine (sql, mysql, oraclesql etc) cevrilebilir. (persistence.xml'de qeyd etdiymz dile cevrilecek)
    Belelikle sadece bu dili bilib jpa'de istf ederek butun dilleri bilirmis kimi olursunuz
    select u from User u where u.email=:e and u.password=:p        =sual yerine ikiNoqte qoylub birde ad verlir. (:e)
                                                                   =table hissesi yerine entityClass'in adi yazlir.
                                                                   =ulduz yerine entity classin adi veya teyin edilmis qisaltmasi yazlir

    //JpaQosWEB;
    JPA app'i Webbapp'e qosduq. Sadece pom.xml'den getdik jpa'in melumatlarini yazdiqki bunu deploy ele.
    ve sadece bir nece seyi deyismekle eyni webapp'de jdbc yox jpa ile duzelmis dbapp'i istf ede bilrik
    "Tebiiki bu qeder rahat olmasi ucun men obyektleri ic ice yazmisdim dbapp'de ki jpa'de rahat olsun" (Yeni User klassin icinde UserSkill type'nda skill var String type'da deil meselen)

    //CriteriaBuilder;
    JPQL ile eyni seydir. Tek ferqi burda vapse query yazmirsan. JPQL'de ise right join vs kimi qarisiq query'leri yazmasaqda sade hisseleri yazirdiq

    //&amp;createDatabaseIfNotExist=true;
    yuxardaki command'i elave etdik persistence'da (1.43.00). Bu sekilde artiq o database movcud deilse bele onu duzeldecek

    //JPA.AutoGenarateAllTables;
    persistence'da TableGenerationStrategy'de None yerine Create etdik.
    Bu sekilde JPA entity class'lari eger database'deki yoxdursa AUTO olaraq ozu create edecek.
    Hetta icindeki "foreign key"'leri bele ayarliyacaq(Cunki entity class'larimizda object icinde object'ler istf etmisdik. Onu gedib database'e uyguluycaq)

    //Hibernateqosmaq;
    JPA'da indiye qeder EclipsLink lib'i ile isledik. Lakin Hibernate'de qosub isliye bilerik. Buda basqa meshur lib'di.
    muellim isledi istf eledi ama jpa cooox onemli gormurem deye etdiklerini etmiyecem. denedim ama gordum axtarib hibernate jar tapmalisan yuklemelisen. hec ugrasmiyacam.
    cetin sey deil ne vaxtsa JPA isf edecek olsan ve Hibernate libini tercih etsen gelib izliye bilersen (1.44-1.59)

    //HibernateOverEclipseLink;
    mueellimin dediyne gore caching'dir ancaq. Axxtardi basqa cox inanlmaz bir seyler tapmadiki EclipseLink'den ferqlensin









    //3ciGunIlkSaat(04-05) =1)users.jsp'deki nationality erroru yene vermeye basladi (anladigim qederiynen jpa'li dbapp'i qosduqda verir. fix ele)
                            2)users.jsp search eledikde tapmir. (cunki action ve method hissesinde seflik var)







    //DERS77 ---------------------------------------------------------------------------------------
    //Topic= SpringBootData (I)

    NoteTopics: SpringSingleton; IsBasvurusuEdebilersiz; start.spring.io; jar-war; SpringBenefits; PersistenceContext/Unit;
                SpringContainer; @Autowired; CommandLineRunner; @Bean; @Configuration; @Scope("prototype"); butunWebApplerSpringYazlir;
                ejb.VS.spring; microServices; UdemyCourses(tavsiye); XaricdeISMeselesi;


    *muellim sadece UserDaoImpl'i JPA'a cevirmisdi indide sadece onu Spring'e cevrecek. Dedi qalan class'lari siz birinci JPA'a sonra
    Spring'e cevreceksiniz. Spring'e cevirmek cetin olmuyacaq dedi. (JPA'dan Spring'e asandi anladigim qederynen)

    //SpringSingleton;
    default olaraq Spring singleton'dan ist edir. Butun obyektleri singleton edir. Lakin bu funksiyasini deyise bilerik istesek. Ki her defe yeni obyekt versin

    //IsBasvurusuEdebilersiz;
    "Men jpa'dan, Hibernate'den, Jsp'den istf ederek web proyekt yaza bilirem deyib apply ede bilersiniz vakansiyalara"
    Artiq indiye qeder oyrendikleriniz + PL/Sql ile 90% is tapa bilersiniz. Geride qalan faiz ise sadece vakansiya acilmagidi.
    (PL/Sql'in onemini bir daha esitdik. Mutleq oyrn is apr-may'da. Veya istesen lap mart'da da oyrene bilersen. Banklar ucun yox basqa erlerde teleb eliyir cunki)

    //start.spring.io;
    burada deirsenki bu ozelliklerde project generate et mene (texnolgiyalar fln hamisini hazir sekilde verir sene)

    //jar-war;
    jar= console app (spring'de ele funsksiya varki sen jari web app kimi istf ede bilirsen)
    war= web app (hec cure console app kimi istf ede bilmirsen)

    //SpringBenefits;
    Singleton qaytarir obyektleri (eger sene lazim deyilse bu ozelliyi deyisdire bilersen)
    bir cox seyi avtomatiklesdirir. (JPA'daki getTransaction, close etmek vs meselelerini misal yazmaga ehtyac qalmr)

    //PersistenceContext/Unit;
    Bu iki annotation Spring'in deyil. Lakin Context Spring terefinden idare olnur. Unit ise manual idare edirik (anladgm qederynen icinde EntityManagerFactory ve EntityManager var)
    Unit bize Spring'in benefitlerinden yararlanmaga imkan vermir anladigim qederiynen.
    gelecekte ise @PersistenceContext'de istf etmiyeciyik. @Autowire istf edeciyik. Lap sonrasi ise onuda istf etmiyeciyik "basqa bir sey" istf edeciyik

    //SpringContainer;
    arxada create edilmis obyektleri list halinda ozunde saxlayan bir obyektdir. singleton'dur, prototype'dir bunlari property'lere baxib teyin deir ve hamisini ozunde saxlayir
    Reflection API'si vasitesiyle bu obyektleri ozunde saxlayir. (refelction API'sini kecmisik)

    *Not= intellij Spring'i inanilmaz destekliyir. Sanki Spring ucun duzeldilmis IDE'dir.

    //@Autowired;
    @Autowired =bu annotation ile nisanlanmis obyektden implement etmis butun obyektler hemen nisanlanmis obyektin icinde gorunecek gbi gbi
    qisaca Context klasi duzeldirdik e main folder'de JDBC app'mizde. Hemen classin yerni tutur.
    @Qualifier =bunula conflict'i onlemis olruq. cunki eger hemen nisanlanmis klasdan birden cox klass implement edibse conflict bas verir.
    Bununla hem implement eden klasslari nisanliyriq hemde autowire zamani qeyd edirikki qualifier'i bu olan klassi isteyrik. Belelikle conflict/xeta onlenmis olur

    //CommandLineRunner;
    Command Line Runner is an interface. It is used to execute the code after the Spring Boot application started

    //@Bean;
    bunun ile mark etdiymiz seyin obyekti create olnacaq.
    Ama @Component ile (veya @Repository/@Service) qeyd etdiymz class'in obyekti(yeni Bean'i) LAZIM OLARSA create olnacaq.
    Misal eger @Autowired desek Component oldugu ucun rahatliqla gedib obyekti(bean'i) create olnacaq
    @Bean ile ozmuz bunu create et dedikde ise autowired olsun olmasin obyekt yaranib qalacaq. (bu eslinde birazda dezavantaj sayila biler)

    //@Configuration;
    bununla markladigmiz klasslar app ise dusmeyden once ise dusur, icindeki isler herseyden once edilir
    Qisaca app ise dusmeden once mutleq edilmesi lazim olan seyler bu annotation ile nisanlanir ki basda edilsin.
    (30deq testler etdikden sonra YENI seyler oyrendik)
    1.@Configuration ile marklanmis klasin scope'nu "prototype" etsen + icindeki @Bean'lerin scope'u "singleton" olaraq qalsa =>icindeki @Bean'lerin obyektine ehtiyacin olduqda 1 defe create olnacaq hem klassin ozu hem icindeki sene lazim olan/cagirdigin @Bean. Ne qeder ehtiyacin olursa olsun.
    2.@Configuration ile marklanmis klasin scope'nu "prototype" etsen + icindeki @Bean'lerin scope'nu "prototype"a cevirsen =>her sene o @bean lazim olduqda o bean tkr tkr ise dusecek. Lakin Configuration klasi yene bir defe create olnacaq.
    3.@Configuration klass @Autowired edile biler. (Lakin buda menasizdir. Cunki autowired o demekdir ki senden implement eden klass'larla seni baglasin. Lakin Configuration klass'dan hansisa klasin implement etmesei mentiqi olaraq menasizdir. Lakin texniki olaraq @Autowired edile biler)
    NOTE: yuxardaki 2 maddenide etmek eslinde menasizdir tebiki. Cunki Config olan bir klasin prorotype olmasi menasizdir. Config klasinin meğzi/menasi odurki onun icindeki seyler app'den once create olunsun ve istf olunsun. Yeni singleton olmasi yeterlidir, prototype lazimsizdir. Lakin texniki olaraq neler mumkundur deye gormus olaq deye arasdirdiq

    //@Scope("prototype");
    default olaraq dediymiz kimi spring class'lar singleton'dur. Ve biz appi run etdikde butun bu class'larin obyektleri onlari istf etsekde etmesekde create olnur
    (yeqin ki bir pool'u var ve limitlenib. Cunki limitsiz olsa ve menimde misal 5000 classim olsa hamisinin obyekti create olunsa app cokecek)
    Lakin @Scope("prototype") ile classimiz prototype edib "singleton"dan cixarda bilerik. Bele etdikde ise obyekti create olunmur eger istf etmirikse/cagirmamisiqsa o classi(@Configuration olan klasslar ucunde kecerlidir)
    Lakin singleton olduqda spring cagirsaqda cagirmasaqda create edir (qeribedir. ama yeqinki bir limit var. yoxsa coker app)

    //butunWebApplerSpringYazlir;
    butun WebApp'ler Spring ile Yazlir? =Java ile yazlan web app'lerde beli, Spring tercih olunur.

    //ejb.VS.spring;
    spring allow us to use DI and IoC at Console Apps either (yeni biz Console app'de @Autowired, @PersistenceContext istifade ede bilrik Sprng sayesinde) (bu kimi ustunlukler sebebiyle Spring'e ustunluk verlir. Amma ele bele yox'da yeni inanilmaz ustunluk verlir. ejb is like Swing. dead technology)
    but ejb demand web application to use DI and Ioc. And it is must that you use "ejb supported" server (not Tomcat, yes TomEE - GlassFish)
    Note: Spring JavaEE deyilmi? =anladigim qederiynen deyil. eger ejb istf etsek o Javanin cixardigi ve Java'nin butun qaydalarini menimseyen bir texnologiyadir. lakin Spring ferqlidi.
    ejb'nin RMI kimi ustunluk kimi gorunen bir texnologiyasi var. Lakin "microservice"ler vs cixdigi ucun o texnologiyaya gore hec kes ejb'ye getmir.

    //microServices;
    Bir proyektde esitmisem azi 300 class olur? =monolithic application'larda beli cox klass ola bilir. Men misal Latviyada islediyim project'de 130-140 class var idi. Lakin bu elede yaxsi deyil
                                                =birde microservices anlayisi cixib ki. Bu cur yazilan projectleri kicik kicik app'lerden teskil olunur. O qeder kicik ki bir dev 2 heftede o bir app'i yaza bilir. (30-40 bele kicik app'den olusa bilir bir proyekt)
                                                 Bunun ustunluyu odurki her bir appin ozu DB'e qosulmus olur vs. Yeni heckesden asli olmur. Ve o app cokende sadece o app cokur proyektin diger hisseleri isleyir (Misal google'da belke 500 xidmet var. deyekki mail gondermek hissesinde problem cixdi. Sadece o hisse cokur) (buda o demekdirki butun musterilere hiss etdirilmeyen xeta bas verir. Sadece kicik bir hisse kimki mail'den istf edir onlar prolem yasiyir)
                                                 Lakin monolithic app'lerde bir kicik yer islemedise fsyo butun app dayanir. (texminimce kohne isleyen version'u qosurlar bele olanda)
                                                =Diger ustunlukde odurki, deyekki 500 microservice'in var. Bunlarin herbiri ferqli dillerde yazila biler..python,java vs vs. Cunki musteqil isliyirler.

    //UdemyCourses(tavsiye);
    2.05'den basliyr
    Docker, Kubernetes =bunlari oyrenin.
    ReactJS, NodeJS =butun dunya bu deyqe bunu axtarir(2019). Niye? Cunki bir bunlari bilen adam basqa Java vs ehtiyac olmadan hem back'i hem frotend'i yazir. Eslinde frontend'in tool'u/dil'idir
    Siralamani bele dedi son olraq: Docker, Kubernetes, Microservices dedi. (3unude udemy'de wishlist'e add etmisem)

    //XaricdeISMeselesi;
    bezi sirketler olurki deyir Java'nin J'sini bilirsense gel isle. Retki retki cixir bele sirketler
    bezi sirketlerde ama day senin suyun sixir cixarirda. Tekce texniki yox menevi olaraqda. "Niye bura gelmek isteyirsenki? Maas ucun gelirsen? vs". Herterefli sual cvb elyir
    MuelliminMesleheti: "Menim size meslehetim ilk olaraq Aze'de is tapin isliyin 1 il. Hemde ozunuzu inkisaf etdirin. Eger o 1 il sonunda ejdaha elesesz ozunuzu hetta yalandan bele deye bilersizki 5 il is tecrubem var. Tebiki yasiniz uygun dusurse"
    Mene deyirlerki bezen interview'larda "men cas bas qalmisamki sen neyi bilirsen neyi bilmirsen". Coxlu skiller varda Cv'imde. Mende qeyd ediremki o demek deilde menim burda herseyden tecrubem varda. Xususen Java'da tecrubem var ama meselen React'de bilirem, Python'da bilirem.. Ve bu musbet haldi. Sabahlarin meseln is cixirki python istiyirlerde sen naxadi o isi tutub isliye bilersen.
    Ona gorede ne edirsiz? Durmadan skill'lerinizi artirirsiniz. Udemy'den el cekmirsiz. parta part e durmadan ders alirsiniz, baxirsiniz. Imkan daxilinde Aze'de ise girib isliyirsiniz. Ve bol bol praktika edirsiniz. Isdi is tapa bilmirsinizse bu resumeApp'in axirina cixn. oznuzede lezzet edecekki men bacardim.











    //DERS78 ---------------------------------------------------------------------------------------
    //Topic= SpringBootData (II)

    NoteTopics:







































































































































   */

}
