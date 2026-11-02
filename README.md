Saya Aghni Lutvia Sari dengan NIM 2508921 mengerjakan TP6 dalam mata kuliah DPBO untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin

aku malas hias readme am so sori, dan aku ga nyontek sama temen, aku liat lewat github orang random ga kenal di google sumoah.
oke


## Desain Program
   - App.java, sama aja kayak main berfungsi untuk ngatur frame window, gabungan antara komponen tampilan dengan logic
   - Logic.java, mengatur logika permainan. Mulai dari pergerakan player, randomizer pipa, deteksi tabrakan, game over, game start, fitur restart, posisi pipa, posisi player
   - Player.java, ini file kelas buat atribut manuknya, seperti ukuran, gambar, sama velocity. Isi nya getter dan setter
   - Pipe.java, sama kayak yang file manuk, tapi ini untuk pipa. Isinya ada ukuran, posisi, gambar, dan boolean untuk penanda burungnya pass apa ngga dari pipa
   - Bertanggung jawab atas *rendering* visual, menggambar ulang posisi pemain, pipa, latar belakang, dan skor di layar menggunakan teknik *double-buffering*.
   - Assets, Menyimpan aset visual seperti sprite gambar karakter, pipa, latar belakang, dan bekgron kren 


## Penjelasan Alur Program

Alur kerja aplikasi dari awal dijalankan hingga permainan berlangsung dapat dijabarkan melalui tahapan berikut:

1. **Inisialisasi Aplikasi (`App.java`):**
   * Saat program dijalankan, `App` membuat objek `JFrame` utama.
   * `App` memanggil dan merakit komponen inti game (`Logic` dan `View`) ke dalam kontainer jendela.

2. **Memulai Sesi & Game Loop (`Logic.java`):**
   * Begitu jendela terbuka, `Logic` menjalankan *timer* atau *game loop* yang berjalan secara konstan (biasanya pada interval milidetik tertentu per *frame*).
   * Pada setiap siklus perbaruan (*tick*), `Logic` memperbarui posisi objek-objek yang ada di dalam game.

3. **Logika Fisika & Pemain (`Player.java` & `Pipe.java`):**
   * **Pemain:** Secara konstan ditarik oleh gravitasi ke bawah. Ketika tombol aksi (seperti *Spacebar* atau klik *mouse*) ditekan, kecepatan vertikal pemain dibalik ke atas (efek lompat).
   * **Pipa:** Secara berkala, objek pipa baru dihasilkan di sisi kanan layar dan bergerak konstan ke arah kiri untuk menjadi rintangan bagi pemain.

4. **Deteksi Tabrakan & Skor:**
   * Di setiap *frame*, `Logic` memeriksa apakah kotak pembatas (*bounding box*) pemain bersinggungan dengan pipa atau keluar dari batas layar (lantai/atap). Jika terjadi tabrakan, status game berubah menjadi *Game Over*.
   * Jika pemain berhasil melewati celah pipa tanpa menabrak, skor pemain akan bertambah secara otomatis.

5. **Rendering Visual (`View.java`):**
   * Setiap kali data posisi objek diperbarui oleh `Logic`, `View` akan memanggil fungsi *repaint()* untuk menggambar ulang (*render*) seluruh elemen grafis (latar belakang, pipa, pemain, dan teks skor) secara mulus di layar agar tidak terjadi kedipan (*flickering*).

itu bekgronnya oren karena karhutla jir
tambahan fitur :
- background main menu
- game pause kalau space belum ditekan untuk pertama kali
- pilihan antara R(restart) atau M (main menu) ketika kalah

## Dokumentasi

!! DISCLAIMER
gamenya gaada backsound, ini aku screenrecord pas dengerin lagu

https://github.com/user-attachments/assets/f1fbd1c1-6977-4a8b-81da-cf1e596a3666


<img width="1920" height="1080" alt="Screenshot (676)" src="https://github.com/user-attachments/assets/6740e539-273f-45f3-adf0-2ff7bf4a837e" />

<img width="1920" height="1080" alt="Screenshot (677)" src="https://github.com/user-attachments/assets/416a9414-0fa8-4dd4-b884-fe2c8d24e5e1" />

<img width="1920" height="1080" alt="Screenshot (678)" src="https://github.com/user-attachments/assets/80bd9eb4-3ef7-4457-808b-eb1756f61bdf" />

<img width="1920" height="1080" alt="Screenshot (679)" src="https://github.com/user-attachments/assets/e54c431e-ef57-4b9e-9906-fcfd6afca236" />
