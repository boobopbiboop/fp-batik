import { PrismaClient } from "@prisma/client";

const prisma = new PrismaClient();

async function main() {
  await prisma.kain.createMany({
    data: [
      {
        name: "Batik Buketan Pekalongan",
        class: "batik-buketan-pekalongan",
        description:
          "Motif bunga khas Pekalongan dengan warna cerah dan detail halus, menggambarkan alam tropis. Kombinasi bunga, daun, dan unsur burung/kupu-kupu menciptakan harmoni visual dipengaruhi estetika Eropa dan lokal.",
        origin: "Pekalongan, Jawa Tengah",
        century: 19,
        type: "Batik Pesisir / Klasik",
        meaning: "Keindahan, kesuburan, keharmonisan, kemakmuran, kegembiraan",
        pilosophy:
          "Batik Buketan Pekalongan merupakan perpaduan sempurna antara estetika lokal dan pengaruh Eropa yang datang melalui kolonialisme. Motif ini menggambarkan filosofi hidup masyarakat pesisir yang terbuka terhadap perubahan namun tetap mempertahankan nilai-nilai tradisional. Setiap elemen bunga melambangkan siklus kehidupan, sementara warna-warna cerah mencerminkan semangat pantang menyerah. Batik ini juga menjadi simbol akulturasi budaya, di mana keindahan alam Nusantara diinterpretasikan melalui teknik dan gaya yang dipengaruhi seni Eropa, menciptakan harmoni visual yang unik.",
        dirUrl: "batik-buketan-pekalongan",
        history:
          "Batik Buketan mulai berkembang pesat pada abad ke-19 di Pekalongan, terutama setelah dibukanya jalur pelayaran internasional yang membawa berbagai pengaruh budaya. Awalnya motif ini hanya dibuat untuk kalangan bangsawan dan saudagar kaya, kemudian menyebar ke masyarakat umum. Pada masa Hindia Belanda, batik buketan menjadi komoditas ekspor yang diminati di Eropa. Perkembangannya tidak lepas dari peran para perempuan Pekalongan yang dengan sabar mengembangkan teknik dan variasi motif. Hingga kini, batik buketan tetap menjadi ikon Pekalongan dengan sentuhan kontemporer namun tidak meninggalkan pakem tradisionalnya.",
      },
      {
        name: "Batik Celup",
        class: "batik-celup",
        description:
          "Teknik ikat-celup menghasilkan pola abstrak dengan warna mencolok. Setiap karya unik karena proses mengikat dan mencelup yang tidak bisa diduplikasi persis, memberikan kesan alami dan organik.",
        origin: "Beberapa daerah pesisir Indonesia",
        century: 0,
        type: "Teknik Batik (Ikat Celup)",
        meaning: "Kebebasan berekspresi, kreativitas, keunikan, spontanitas",
        pilosophy:
          "Batik celup atau jumputan melambangkan kebebasan berekspresi dalam berkesenian. Berbeda dengan batik motif yang memiliki pola baku, teknik ini mengajarkan filosofi menerima ketidakteraturan sebagai keindahan. Proses pembuatannya yang sederhana namun penuh kejutan mengajarkan kita untuk menghargai proses daripada hasil. Dalam konteks budaya Nusantara, batik celup juga merepresentasikan keragaman karena setiap daerah memiliki ciri khas teknik dan warna tersendiri. Teknik ini mengajarkan fleksibilitas dan adaptasi, di mana seniman harus bekerja sama dengan alam (pewarna alami) untuk menciptakan karya.",
        dirUrl: "batik-celup",
        history:
          "Teknik ikat celup sebenarnya telah dikenal sejak zaman prasejarah di berbagai belahan dunia, termasuk di Nusantara. Di Indonesia, teknik ini berkembang secara independen di berbagai daerah pesisir sebagai cara sederhana menghias kain. Catatan sejarah menunjukkan teknik serupa telah digunakan sejak abad ke-8 di Jawa, kemudian menyebar ke Sumatra, Kalimantan, dan Sulawesi. Perkembangannya mencapai puncak pada abad ke-17 ketika teknik ini digunakan untuk membuat kain upacara di berbagai kerajaan. Pada masa modern, batik celup mengalami revitalisasi sebagai bagian dari gerakan kembali ke alam dan kerajinan tangan tradisional.",
      },
      {
        name: "Batik Jlamprang Pekalongan",
        class: "batik-jlamprang",
        description:
          "Motif geometris terinspirasi kain patola India dengan pola kotak dan lung-lungan simetris. Warna cerah dengan kontras kuat menciptakan kesan megah dan berwibawa.",
        origin: "Pekalongan, Jawa Tengah",
        century: 17,
        type: "Batik Pesisir",
        meaning:
          "Keseimbangan, keteraturan, harmoni kosmik, ketelitian, kesempurnaan",
        pilosophy:
          "Motif Jlamprang yang geometris dan simetris merepresentasikan konsep kosmologi Jawa tentang keteraturan alam semesta. Setiap garis dan sudut yang presisi melambangkan jalan hidup yang terarah dan disiplin. Filosofinya berasal dari konsep 'memayu hayuning bawana' atau menjaga keharmonisan dunia. Motif ini juga mencerminkan pengaruh Islam yang kuat di Pekalongan, di mana bentuk geometris digunakan sebagai ekspresi seni yang sesuai dengan nilai-nilai agama. Pada tingkat yang lebih dalam, Jlamprang mengajarkan tentang kesabaran dan ketekunan, karena membuat motif ini membutuhkan presisi tinggi dalam setiap titik dan garisnya.",
        dirUrl: "batik-jlamprang",
        history:
          "Batik Jlamprang mulai dikenal di Pekalongan sekitar abad ke-17, diadaptasi dari motif patola Gujarat yang dibawa oleh pedagang India. Awalnya motif ini eksklusif untuk kalangan tertentu karena kerumitannya. Pada abad ke-19, ketika Pekalongan menjadi pusat perdagangan batik, Jlamprang mulai diproduksi secara lebih luas. Motif ini mengalami adaptasi lokal dimana pengrajin Jawa menambahkan unsur-unsur flora di antara bentuk geometrisnya. Pada masa kolonial, Jlamprang menjadi salah satu motif favorit untuk diekspor ke Eropa. Perkembangannya terus berlanjut hingga kini dengan berbagai variasi modern namun tetap mempertahankan karakter geometrisnya yang khas.",
      },
      {
        name: "Batik Kawung",
        class: "batik-kawung",
        description:
          "Susunan bulatan/bujur sangkar rapi menyerupai irisan buah kawung atau bunga teratai. Motif geometris seimbang dengan kesederhanaan elegan.",
        origin: "Yogyakarta (Kesultanan Mataram)",
        century: 13,
        type: "Batik Klasik (Keraton)",
        meaning:
          "Kesucian, kemurnian, pengendalian diri, kesempurnaan, ketertiban",
        pilosophy:
          "Batik Kawung mengandung filosofi mendalam tentang kehidupan manusia yang harus seimbang antara dunia dan akhirat. Bentuk bulatannya yang sempurna melambangkan kesatuan dan keutuhan jiwa. Dalam tradisi Jawa, motif ini merepresentasikan 'manunggaling kawula gusti' atau penyatuan hamba dengan Tuhannya. Setiap lingkaran saling terhubung menggambarkan siklus kehidupan yang terus berputar. Kawung juga mengajarkan tentang pentingnya pengendalian diri, di mana setiap individu harus bisa mengatur nafsu dan emosi seperti susunan motif yang teratur rapi. Motif ini sering dikaitkan dengan konsep kepemimpinan Jawa yang harus adil dan bijaksana.",
        dirUrl: "batik-kawung",
        history:
          "Batik Kawung memiliki sejarah panjang yang bermula dari abad ke-13 di lingkungan Keraton Mataram. Awalnya motif ini hanya boleh dikenakan oleh raja dan keluarga kerajaan sebagai simbol kekuasaan. Pada masa Kerajaan Majapahit, motif serupa Kawung sudah ditemukan pada relief candi. Perkembangannya mencapai bentuk sempurna pada masa Kesultanan Yogyakarta dan Surakarta di abad ke-18. Setiap periode memberikan sentuhan berbeda pada motif Kawung, mulai dari ukuran, kerapatan, hingga variasi ornamen pendukungnya. Pada abad ke-19, motif ini mulai boleh dipakai oleh masyarakat umum dengan beberapa aturan tertentu. Kini Kawung tetap menjadi salah satu motif batik paling dihormati dan terus dilestarikan sebagai warisan budaya adiluhung.",
      },
      {
        name: "Batik Lasem",
        class: "batik-lasem",
        description:
          "Perpaduan motif Tionghoa-Jawa dengan warna merah khas (abang getih pitik). Detail halus dengan kombinasi unsur budaya harmonis menciptakan gaya unik.",
        origin: "Lasem, Rembang, Jawa Tengah",
        century: 15,
        type: "Batik Pesisir",
        meaning: "Akulturasi, keberagaman, kemakmuran, harapan, toleransi",
        pilosophy:
          "Batik Lasem merupakan perwujudan sempurna dari filosofi Bhinneka Tunggal Ika. Motif ini mengajarkan tentang harmoni dalam perbedaan, di mana unsur Tionghoa dan Jawa menyatu tanpa menghilangkan identitas masing-masing. Warna merah yang dominan melambangkan keberanian dan semangat hidup masyarakat pesisir. Filosofi Lasem juga mencerminkan konsep 'memayu hayuning bawono' atau memperindah keindahan dunia melalui keragaman budaya. Setiap motif memiliki makna khusus, seperti burung hong melambangkan keberuntungan, atau bunga peoni yang berarti kemakmuran. Batik Lasem mengajarkan kita untuk menghargai warisan leluhur sambil tetap terbuka terhadap pengaruh positif dari luar.",
        dirUrl: "batik-lasem",
        history:
          "Batik Lasem mulai berkembang pada awal abad ke-15 seiring dengan kedatangan Laksamana Cheng Ho dan rombongannya yang singgah di Lasem. Para perempuan Tionghoa yang menetap kemudian memadukan teknik membatik dengan motif khas negeri mereka. Pada abad ke-17, Lasem menjadi pusat produksi batik penting di pesisir utara Jawa. Masa keemasan batik Lasem terjadi pada abad ke-19 ketika banyak saudagar Tionghoa berinvestasi dalam usaha batik. Warna merah khas Lasem (abang getih pitik) diciptakan karena keterbatasan pewarna alam saat itu. Pada masa pendudukan Jepang, produksi sempat terhenti sebelum bangkit kembali setelah kemerdekaan. Kini batik Lasem tetap menjadi bukti hidup akulturasi budaya yang berlangsung selama berabad-abad.",
      },
      {
        name: "Batik Megamendung",
        class: "batik-megamendung-cirebon",
        description:
          "Motif awan bergelombang khas Cirebon dengan gradasi warna biru-merah mencolok. Pola dinamis menciptakan kesan megah dan penuh wibawa.",
        origin: "Cirebon, Jawa Barat",
        century: 16,
        type: "Batik Klasik (Pesisir Cirebon)",
        meaning: "Kebebasan, ketenangan, kesuburan, keluasan, kedamaian",
        pilosophy:
          "Megamendung melambangkan konsep kosmologi dalam budaya Cirebon yang memandang langit sebagai simbol kebesaran Tuhan. Bentuk awan yang bergelombang menggambarkan dinamika kehidupan manusia yang tidak statis. Filosofi motif ini juga terkait erat dengan konsep 'kepapan' atau keseimbangan dalam tradisi Jawa-Sunda. Warna biru melambangkan kedalaman ilmu dan ketenangan jiwa, sementara merah menyimbolkan keberanian dan semangat hidup. Megamendung juga merefleksikan ajaran tasawuf tentang perjalanan manusia mendekatkan diri kepada Sang Pencipta. Setiap lengkungan pada motif ini mengandung makna tentang fleksibilitas dalam menghadapi tantangan hidup.",
        dirUrl: "batik-megamendung",
        history:
          "Batik Megamendung mulai dikenal pada abad ke-16 di Kesultanan Cirebon, dipengaruhi oleh seni awan dalam budaya Tionghoa yang dibawa oleh para pedagang. Awalnya motif ini hanya digunakan untuk kain panjang para sultan dan bangsawan keraton. Pada abad ke-17, Sunan Gunung Jati mengembangkan motif ini sebagai media dakwah, dengan bentuk awan yang melambangkan kebesaran Ilahi. Masa kejayaan motif Megamendung terjadi pada abad ke-18 ketika Cirebon menjadi pusat perdagangan penting. Setiap periode memberikan perkembangan pada motif ini, mulai dari yang sangat sederhana hingga sangat detail. Pada abad ke-20, Megamendung mulai dikenal luas sebagai ikon batik Cirebon dan terus berkembang dengan berbagai inovasi warna dan teknik.",
      },
      {
        name: "Batik Sekar Jagad",
        class: "batik-sekar-jagad",
        description:
          "Kompilasi aneka motif batik klasik dalam satu kain menyerupai peta dunia atau taman bunga. Setiap bagian berbeda namun menyatu dalam harmoni.",
        origin: "Yogyakarta/Solo, Jawa Tengah",
        century: 18,
        type: "Batik Klasik",
        meaning:
          "Keragaman, persatuan, kebijaksanaan, kelengkapan, kebhinekaan",
        pilosophy:
          "Sekar Jagad merupakan perwujudan filosofi Jawa tentang keanekaragaman dunia yang harus dipahami secara utuh. Motif ini mengajarkan bahwa perbedaan bukanlah penghalang untuk bersatu, justru memperkaya kehidupan. Dalam konteks yang lebih luas, Sekar Jagad merepresentasikan konsep 'Manunggaling Kawula Gusti' dimana manusia harus memahami keseluruhan ciptaan Tuhan. Setiap elemen motif yang berbeda namun saling melengkapi mengajarkan tentang toleransi dan kerja sama. Filosofi motif ini juga terkait dengan konsep 'memayu hayuning bawana' atau ikut serta dalam memperindah dunia dengan menerima keragaman sebagai anugerah.",
        dirUrl: "batik-sekar-jagad",
        history:
          "Batik Sekar Jagad mulai dikenal pada abad ke-18 di lingkungan keraton Yogyakarta dan Surakarta sebagai ekspresi seni yang menggabungkan berbagai motif tradisional. Awalnya motif ini dibuat sebagai hadiah untuk tamu-tamu penting keraton yang diharapkan bisa memahami kekayaan budaya Jawa. Pada abad ke-19, motif ini mulai diproduksi secara lebih luas namun tetap mempertahankan kompleksitasnya. Masa kolonial membawa pengaruh baru dengan masuknya motif-motif Eropa ke dalam komposisi Sekar Jagad. Perkembangannya mencapai puncak pada awal abad ke-20 ketika para seniman batik mulai bereksperimen dengan lebih banyak variasi. Kini Sekar Jagad tetap menjadi salah satu motif batik paling prestisius yang membutuhkan keterampilan tinggi dalam pembuatannya.",
      },
      {
        name: "Batik Sidomukti",
        class: "batik-sidomukti",
        description:
          "Batik klasik keraton dengan warna sogan (cokelat) dominan, dihiasi motif kupu-kupu, garuda, dan bambu. Detail halus dengan ketelitian tinggi.",
        origin: "Surakarta (Solo), Jawa Tengah",
        century: 17,
        type: "Batik Keraton (Solo)",
        meaning:
          "Kemakmuran, kebahagiaan, keteguhan, keselarasan, harapan baik",
        pilosophy:
          "Sidomukti berasal dari kata 'sido' (terus-menerus) dan 'mukti' (kebahagiaan), melambangkan harapan untuk kehidupan yang sejahtera lahir batin. Motif kupu-kupu melambangkan transformasi menuju kehidupan lebih baik, sementara bambu mengajarkan keteguhan dan fleksibilitas. Filosofi batik ini sangat terkait dengan konsep 'sangkan paraning dumadi' atau asal-usul dan tujuan penciptaan manusia dalam kepercayaan Jawa. Warna sogan yang dominan merepresentasikan kedekatan dengan tanah sebagai sumber kehidupan. Sidomukti juga mengajarkan tentang pentingnya menjaga harmoni dalam keluarga dan masyarakat sebagai pondasi kebahagiaan.",
        dirUrl: "batik-sidomukti",
        history:
          "Batik Sidomukti mulai dikembangkan di Keraton Surakarta pada abad ke-17 sebagai kain upacara khusus untuk pernikahan bangsawan. Awalnya proses pembuatannya hanya diketahui oleh para pembatik keraton dengan bahan-bahan pilihan. Pada abad ke-18, motif ini mulai diproduksi di luar keraton namun dengan aturan ketat tentang penggunaannya. Masa keemasan Sidomukti terjadi pada pemerintahan Pakubuwono X di akhir abad ke-19 ketika motif ini menjadi simbol status sosial. Pada masa kemerdekaan, aturan penggunaan mulai melonggar dan Sidomukti bisa dipakai oleh masyarakat umum. Kini batik Sidomukti tetap menjadi pilihan utama untuk acara-acara penting seperti pernikahan dengan berbagai inovasi teknik namun tetap mempertahankan makna filosofisnya.",
      },
      {
        name: "Songket Lombok",
        class: "songket-lombok",
        description:
          "Tenun songket khas Sasak dengan benang emas/perak berkilau. Motif geometris dan flora simbolis menciptakan kesan mewah namun elegan.",
        origin: "Lombok, NTB (Sasak)",
        century: 19,
        type: "Tenun Tradisional (Songket)",
        meaning: "Kematangan, kemandirian, kemakmuran, cinta abadi, harga diri",
        pilosophy:
          "Songket Lombok mengandung filosofi mendalam tentang perjalanan hidup perempuan Sasak. Proses menenun yang panjang dan penuh kesabaran melambangkan persiapan seorang gadis menuju kedewasaan. Benang emas yang berkilauan menyimbolkan harapan untuk kehidupan gemilang. Motif-motif geometris yang teratur mengajarkan tentang disiplin dan ketelitian dalam menjalani hidup. Dalam budaya Sasak, kemampuan menenun songket merupakan prasyarat pernikahan, menunjukkan bahwa seorang perempuan sudah siap secara mental dan spiritual membangun rumah tangga. Songket juga merepresentasikan hubungan harmonis antara manusia dengan alam.",
        dirUrl: "songket-lombok",
        history:
          "Songket Lombok mulai dikenal secara luas pada tahun 1832 di Desa Sukarara, Lombok Tengah, meskipun teknik tenun serupa sebenarnya sudah ada sebelumnya. Awalnya songket hanya ditenun untuk keperluan upacara adat dan keluarga kerajaan. Pada akhir abad ke-19, songket mulai diproduksi lebih massal sebagai mas kawin yang wajib disiapkan calon pengantin perempuan. Masa penjajahan Belanda membawa pengaruh pada motif-motif songket yang mulai memasukkan unsur flora yang lebih beragam. Setelah kemerdekaan, songket Lombok berkembang pesat sebagai komoditas unggulan daerah. Pada tahun 1980-an, pemerintah mulai mempromosikan songket sebagai warisan budaya yang harus dilestarikan. Kini songket Lombok tidak hanya menjadi bagian penting dalam adat Sasak tetapi juga komoditas ekonomi kreatif yang diminati kolektor dalam dan luar negeri.",
      },
      {
        name: "Songket Palembang",
        class: "songket-palembang",
        description:
          "Tenun mewah warisan Sriwijaya dengan dominasi benang emas berkilau. Motif kompleks penuh makna mencerminkan keagungan budaya Melayu.",
        origin: "Palembang, Sumatera Selatan",
        century: 7,
        type: "Tenun Tradisional (Songket)",
        meaning: "Keagungan, kekuasaan, keberanian, kemakmuran, status sosial",
        pilosophy:
          "Songket Palembang merepresentasikan filosofi hidup masyarakat Melayu yang menjunjung tinggi nilai-nilai keagungan dan kemuliaan. Benang emas yang mendominasi melambangkan cahaya kebijaksanaan yang harus menyinari kehidupan. Motif-motif yang rumit dan penuh makna mengajarkan tentang kompleksitas kehidupan yang harus dihadapi dengan kecerdasan dan ketelitian. Dalam konteks budaya Melayu, songket juga melambangkan 'adat bersendi syarak, syarak bersendi Kitabullah' dimana tradisi tidak boleh bertentangan dengan ajaran agama. Setiap motif memiliki makna khusus, seperti motif bunga melur yang berarti kesucian, atau motif pucuk rebung yang melambangkan pertumbuhan dan perkembangan.",
        dirUrl: "songket-palembang",
        history:
          "Songket Palembang telah dikenal sejak masa Kerajaan Sriwijaya pada abad ke-7 sebagai kain kebesaran raja dan bangsawan. Catatan sejarah Cina dari dinasti Tang menyebutkan tentang kemewahan kain tenun emas dari Sriwijaya. Pada abad ke-15, ketika Palembang menjadi kesultanan, songket mencapai masa kejayaannya dengan teknik dan motif yang semakin kompleks. Abad ke-17 sampai 19 merupakan masa ketika songket Palembang menjadi komoditas perdagangan penting di Nusantara. Masa penjajahan Belanda sempat membuat produksi songket menurun sebelum bangkit kembali pada awal abad ke-20. Pada tahun 1980-an, songket Palembang ditetapkan sebagai warisan budaya nasional. Kini songket Palembang tetap menjadi simbol kebanggaan masyarakat Sumatera Selatan dengan berbagai inovasi motif namun tetap mempertahankan ciri khasnya yang mewah.",
      },
      {
        name: "Tenun Troso Jepara",
        class: "tenun-troso",
        description:
          "Tenun ikat tradisional Troso dengan motif geometris khas, dibuat menggunakan alat tenun bukan mesin. Warna alam dan pola simetris yang memukau.",
        origin: "Troso, Jepara, Jawa Tengah",
        century: 20,
        type: "Tenun Tradisional (Ikat)",
        meaning:
          "Kesabaran, ketekunan, keseimbangan, kesederhanaan, kebersamaan",
        pilosophy:
          "Tenun Troso mengandung filosofi hidup masyarakat pesisir Jawa tentang keselarasan dengan alam dan kebersamaan. Proses menenun yang panjang dan rumit mengajarkan nilai kesabaran dan ketekunan. Motif-motif geometris yang teratur melambangkan keseimbangan dalam kehidupan. Warna-warna alam yang digunakan merepresentasikan kedekatan dengan lingkungan. Dalam konteks yang lebih luas, tenun Troso juga melambangkan gotong royong karena pembuatannya sering melibatkan seluruh anggota keluarga. Setiap helai benang yang disusun dengan teliti mengajarkan tentang pentingnya proses dalam mencapai hasil yang berkualitas.",
        dirUrl: "tenun-troso",
        history:
          "Tenun Troso mulai dikenal pada tahun 1935 di Desa Troso, Jepara, sebagai alternatif mata pencaharian selain kayu. Awalnya teknik tenun ini dipelajari dari pengrajin dari Tuban dan Madura yang datang ke Jepara. Pada masa pendudukan Jepang, produksi tenun Troso meningkat karena permintaan kain lokal yang tinggi. Tahun 1950-an menjadi masa keemasan ketika tenun Troso mulai dikenal di berbagai daerah di Jawa. Pada tahun 1970-an, para pengrajin mulai mengembangkan motif-motif baru dengan tetap mempertahankan teknik tradisional. Tahun 1990, pemerintah menetapkan Troso sebagai sentra tenun ikat yang perlu dilestarikan. Kini tenun Troso terus berkembang dengan berbagai inovasi motif dan pemasaran, namun tetap mempertahankan karakter aslinya yang dibuat dengan alat tenun bukan mesin.",
      },
      {
        name: "Batik Simbut Banten",
        class: "batik-simbut-banten",
        description:
          "Motif daun talas tersusun rapi, batik tertua suku Badui. Warna alam lembut dengan inspirasi langsung dari alam sekitar.",
        origin: "Suku Badui, Banten",
        century: 20,
        type: "Batik Tradisional (Badui)",
        meaning:
          "Kesederhanaan, kerendahan hati, kelembutan, kedekatan dengan alam, ketulusan",
        pilosophy:
          "Batik Simbut merepresentasikan filosofi hidup suku Badui yang sederhana namun penuh makna. Motif daun talas yang menjadi ciri khasnya melambangkan kedekatan dengan alam sebagai sumber kehidupan. Dalam kepercayaan Badui, batik ini mengajarkan tentang pentingnya hidup selaras dengan lingkungan tanpa merusaknya. Warna-warna alam yang digunakan menunjukkan penghormatan terhadap apa yang disediakan oleh bumi. Proses pembuatan yang sederhana tanpa banyak variasi mengajarkan tentang nilai-nilai kesederhanaan dan ketulusan. Batik Simbut juga menjadi simbol identitas budaya Badui yang tetap bertahan di tengah modernisasi.",
        dirUrl: "batik-simbut-banten",
        history:
          "Batik Simbut berasal dari tradisi suku Badui Dalam yang telah ada sejak lama, namun baru dikenal luas oleh masyarakat luar pada abad ke-20. Awalnya motif ini hanya dibuat untuk keperluan adat suku Badui dengan teknik yang sangat sederhana. Pada tahun 1970-an, ketika pemerintah mulai memperhatikan budaya Badui, batik Simbut mulai diperkenalkan ke publik. Tahun 1980-an menjadi titik penting ketika motif ini diakui sebagai batik khas Banten. Pada tahun 2003, batik Simbut mulai diproduksi secara lebih luas oleh masyarakat Badui Luar dengan tetap mempertahankan filosofi dasarnya. Kini batik Simbut menjadi salah satu ikon budaya Banten yang terus dilestarikan dengan berbagai program pelatihan dan pengembangan.",
      },
      {
        name: "Batik Tujuh Rupa Pekalongan",
        class: "batik-tujuh-rupa-pekalongan",
        description:
          "Tujuh motif berbeda dalam satu kain menampilkan flora-fauna khas Pekalongan. Warna cerah menciptakan karya dinamis penuh makna.",
        origin: "Pekalongan, Jawa Tengah",
        century: 20,
        type: "Batik Pesisir",
        meaning:
          "Pluralisme, kreativitas, adaptasi, kebhinekaan, kelenturan budaya",
        pilosophy:
          "Batik Tujuh Rupa merupakan perwujudan filosofi masyarakat Pekalongan yang terbuka terhadap berbagai pengaruh budaya. Tujuh motif yang berbeda dalam satu kain mengajarkan tentang pentingnya menghargai perbedaan dan menemukan harmoni dalam keragaman. Setiap elemen flora dan fauna yang digambarkan melambangkan kekayaan alam Nusantara yang harus dijaga. Warna-warna cerah yang digunakan merepresentasikan semangat hidup dan kegembiraan. Batik ini juga mengajarkan tentang fleksibilitas budaya, di mana berbagai unsur bisa disatukan tanpa menghilangkan identitas aslinya. Dalam konteks yang lebih luas, Tujuh Rupa menjadi simbol Indonesia mini yang majemuk namun bersatu.",
        dirUrl: "batik-tujuh-rupa-pekalongan",
        history:
          "Batik Tujuh Rupa mulai berkembang di Pekalongan pada awal abad ke-20 sebagai ekspresi kreativitas para pembatik yang ingin menampilkan berbagai motif dalam satu kain. Awalnya motif ini dibuat untuk memenuhi permintaan pasar yang menginginkan variasi dalam satu produk. Pada tahun 1930-an, Tujuh Rupa mulai dikenal sebagai batik khas Pekalongan dengan ciri warna-warna cerahnya. Masa pendudukan Jepang sempat menghentikan produksi sebelum bangkit kembali setelah kemerdekaan. Tahun 1970-an menjadi masa keemasan ketika batik ini mulai diekspor ke berbagai negara. Pada abad ke-21, Tujuh Rupa terus berkembang dengan berbagai inovasi motif namun tetap mempertahankan konsep dasarnya tentang keragaman dalam kesatuan.",
      },
      {
        name: "Kain Ulos Batak",
        class: "kain-ulos-batak",
        description:
          "Tenun tradisional Batak kaya simbol, digunakan dalam upacara adat sebagai wujud kasih sayang. Motif geometris penuh arti dengan warna simbolis.",
        origin: "Sumatera Utara (Tanah Batak)",
        century: 13,
        type: "Tenun Tradisional (Ulos)",
        meaning:
          "Persatuan, perlindungan, berkat, penghormatan, kelangsungan adat",
        pilosophy:
          "Ulos Batak mengandung filosofi mendalam tentang hubungan antar manusia dan dengan Sang Pencipta dalam budaya Batak. Proses menenun yang panjang melambangkan perjalanan hidup yang penuh liku namun harus dihadapi dengan sabar. Motif-motif yang kaya simbol mengajarkan tentang nilai-nilai leluhur yang harus dijaga. Dalam adat Batak, pemberian ulos merupakan bentuk kasih sayang dan doa berkat dari yang tua kepada yang muda. Filosofi ulos juga terkait dengan konsep 'habonaron do bona' atau kebenaran sebagai dasar kehidupan. Setiap jenis ulos memiliki makna khusus dalam siklus kehidupan manusia Batak dari lahir sampai meninggal.",
        dirUrl: "kain-ulos-batak",
        history:
          "Kain Ulos telah menjadi bagian integral budaya Batak sejak abad ke-13, seperti yang tercatat dalam berbagai prasasti dan folklore Batak. Awalnya ulos hanya ditenun untuk keperluan ritual dan upacara adat oleh perempuan-perempuan Batak. Pada abad ke-16, ketika kerajaan Batak mulai berkembang, ulos menjadi simbol status sosial dengan motif-motif tertentu yang hanya boleh dipakai bangsawan. Masa kolonial Belanda membawa pengaruh pada bahan dan warna yang digunakan. Tahun 1950-an, ulos mulai dikenal secara nasional sebagai warisan budaya Batak. Pada tahun 2014, UNESCO menetapkan tenun Batak sebagai warisan budaya takbenda yang perlu dilindungi. Kini ulos tidak hanya berfungsi dalam adat tetapi juga menjadi identitas budaya Batak modern dengan berbagai adaptasi penggunaan.",
      },
    ],
    skipDuplicates: true,
  });
}

main()
  .catch((e) => {
    console.error(e);
    process.exit(1);
  })
  .finally(async () => {
    await prisma.$disconnect();
  });
