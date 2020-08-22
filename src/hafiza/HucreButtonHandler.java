


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Calendar; //gecen zamani alabilmek icin

public class HucreButtonHandler extends Frame implements ActionListener {
    /*
     * Hucreler icin actionlistenerler gerceklestiriliyor.
     */
int zorluk=HafizaGUI.zorluk;
    Hafiza hafiza2 = new Hafiza();
    JPanel PnlHucreler = new JPanel();//hucreleri yerlestirilecek panel olusturuluyor.
    JButton OyunuBitisiKontrlEt = new JButton();//jframe eklenmeden her hucre eslesmesinde tiklanarak hucrelerin bitisini kontrol edicek.
    JButton OyunuBaslat = new JButton();//zamani almak icin kullanilacak fakat jframe eklenmeden yapilacak
    JButton[] BtnHucre = new JButton[zorluk];//hucreler olusturuluyor.
    int[] HucreResmi = new int[zorluk]; //random degerler alip bunlari r
    int HucreSayaci; //hucrelerin tiklanma sayisini tutuyor.
    ImageIcon[] BtnHucreResimleri = new ImageIcon[zorluk];
    ImageIcon BtnHucreilkResim = new ImageIcon(getClass().getResource("ilkResim.png"));//ilk resmi atamasi icin olusturuldu.
    boolean FonksiyonuCagirma = true;
    int AcikHucrelerinSayisi = 0, CozulmusHucrelerinSayisi = 0;
    long OyununBaslangicZamani;
    int[] SonTiklananButon = new int[2];
    Calendar calendar;

    public HucreButtonHandler(int a) {
        OyunuBaslat.addActionListener(this);//jrame eklenmeden kullanilan buton action listenera ekleniyor.
        OyunuBitisiKontrlEt.addActionListener(this);//jrame eklenmeden kullanilan buton action listenera ekleniyor.
        PnlHucreler.setLayout(new GridLayout(a, a));//a yatay,a dusey sekilde yerlestiriliyor.
        PnlHucreler.setBackground(Color.BLACK);
        for (int i = 0; i < zorluk; i++) {
            BtnHucre[i] = new JButton();
            BtnHucre[i].setBackground(Color.BLACK);
            BtnHucre[i].setEnabled(true);
            BtnHucre[i].setName("00");
            BtnHucre[i].setIcon(BtnHucreilkResim);
            BtnHucre[i].addActionListener(this);
            PnlHucreler.add(BtnHucre[i]);
        }
        HucreResimleriniRastgeleDagit();
    }

    public void HucreResimleriniRastgeleDagit() { //16 elemanli diziye birbiriniz ayni olmayan degerler atanmasi yapiliyor.
        for (int i = 0; i < zorluk; i++) {
            HucreResmi[i] = (int) (Math.random() * zorluk) + 1;
            for (int j = 0; j < i; j++) {
                if (HucreResmi[i] == HucreResmi[j] && i != j && i != 0) {
                    --i;
                }
            }
        }
        //degerler 1- 16(dahil)arasinda verildi.
        for (int i = 0; i < zorluk; i++) {//burada 16 elemanli dizinin ayni 2 degere sahip 8 eleman olusturuldu.ve degerlerin karsilik gelen resim atandi.
            HucreResmi[i] = (HucreResmi[i] % (zorluk/2));
            BtnHucreResimleri[i] = new ImageIcon(getClass().getResource(HucreResmi[i] + 1 + ".png"));
        }
        OyunuBaslat.doClick();//jframe ekli olmayan butonumu tiklanmasini saglayarak action listenerde zamani aldim.
    }

    void ikiSaniyeBeklet() {
        Runnable runnable = new Runnable() {

            public void run() {
                try {
                    Thread.sleep(2000);
                    for (int i = 0; i < zorluk; i++) {
                        if (BtnHucre[i].isEnabled() == true) {
                            BtnHucre[i].setIcon(BtnHucreilkResim);
                            BtnHucre[i].setName("00");
                            HucreSayaci = 0;
                            SonTiklananButon[0] = 21;
                            SonTiklananButon[1] = 22;
                        }
                    }
                } catch (InterruptedException ex) {
                    System.out.println("Thread uyurken sorun olustu..Hata:" + ex);
                }

            }
        };
        SwingUtilities.invokeLater(runnable);
    }

    public void AcikHucrelerinSayisi() { //ayni hucrelerin isenabledleri false olark degistiriliyor geriye acik olanlar kaliyor bu metod acik olnalri buluyor.
        for (int i = 0; i < zorluk; i++) {
            if (BtnHucre[i].isEnabled() == true) {
                AcikHucrelerinSayisi++;
            }
        }
        CozulmusHucrelerinSayisi = (zorluk - AcikHucrelerinSayisi) / 2;
        if (!(AcikHucrelerinSayisi == 0)) {
            AcikHucrelerinSayisi = 0;
        } else {
            OyunuBitisiKontrlEt.doClick();//eger Hic acik hucre kalmamissa oyun bitmis demektir bitir butonuna tiklanir.
        }
    }

    public void actionPerformed(ActionEvent e) {
        calendar = Calendar.getInstance();//suanki zamani alarak gecen sureyi guncelemek icin.
        if (e.getSource() == OyunuBitisiKontrlEt) { //eger buna tiklanmissa oyun bitmis demektir hafiza sinifina deger gonderip sonucu ekrana yansittiktan sonra oyun kapatilir.

            String BasariSonucu = hafiza2.BasariDurumu(2, CozulmusHucrelerinSayisi, (int) (calendar.getTimeInMillis() - OyununBaslangicZamani) / 1000);
            JOptionPane.showMessageDialog(PnlHucreler, "Oyun Tamamlandi.Basari Durumunuz:" + BasariSonucu, "Oyun Tamamlandi", 1);

            
System.exit(0);
            

        }
        if (e.getSource() == OyunuBaslat) {//oyunun baslangicinda yada yeniden baslatilince rka planda tiklatarak su anki zamani almamizi sagliyor.
            OyununBaslangicZamani = calendar.getTimeInMillis();//long tipinde ve milisaniye olar tutuyor.2 zaman dilimni cikartip 1000 bolunce saniye kaliyor.
        } else {
            FonksiyonuCagirma = true;
            if ((HucreSayaci == 0) || (HucreSayaci == 1)) { //uyurken ki durumdn faydalanilmasin diye onlem alindi.
                for (int i = 0; i < zorluk; i++) {
                    {
                        if (e.getSource() == BtnHucre[i]) {
                            SonTiklananButon[HucreSayaci] = i; //en son tiklanan butonu tutuyor.
                            if ((HucreSayaci == 0) || (SonTiklananButon[0] != SonTiklananButon[1])) {//son iki tiklanan butonu kiyaslayarak ard arda ayni butona basilmasina engel oluyor.
                                BtnHucre[i].setIcon(BtnHucreResimleri[i]);
                                BtnHucre[i].setName(String.valueOf(HucreResmi[i]));
                                HucreSayaci++;
                                for (int j = 0; j < zorluk; j++) {

                                    if (e.getSource() == BtnHucre[i] && i != j && BtnHucre[i].getName().equals(BtnHucre[j].getName())) {
                                        BtnHucre[i].setDisabledIcon(BtnHucreResimleri[i]);
                                        BtnHucre[j].setDisabledIcon(BtnHucreResimleri[j]);
                                        BtnHucre[j].setEnabled(false);
                                        BtnHucre[i].setEnabled(false);
                                        FonksiyonuCagirma = false;//ayni resim varsa 2 saniye bekletmeye gerek yok

                                    }
                                }
                            }
                        }
                    }

                }
            }
            if (HucreSayaci == 2 && FonksiyonuCagirma) {
                ikiSaniyeBeklet();
            }
            if (!FonksiyonuCagirma) {//ayni hucre varsa sayaci sifirlamak gerekir.
                HucreSayaci = 0;
                SonTiklananButon[0] = 21;
                SonTiklananButon[1] = 22;
            }
            AcikHucrelerinSayisi();//acik hucre varmi diye balk varsa bitis butnonu tetikle
        }

    }
}
