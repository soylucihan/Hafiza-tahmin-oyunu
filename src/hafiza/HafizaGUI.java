


import javax.swing.*;
import java.awt.*; //frame
import java.awt.event.*; //action listener
import java.util.Calendar;

public class HafizaGUI extends Frame implements ActionListener {
    /*
     * Oyun 3 siniftan olusuyor.
     * main yordami burada yani HafizaGUI de gerceklestiriliyor.
     * Oyunun arayuzu,yeniden baslat,oyunu coz ,oyunu kapat,gecen sure,basari durumu ve destek icin action listener gerceklestiriyor.
     * Oyun 3 seviyeden oluşuyor.birinci seviyede 16 kare- 8 resim, ikinci seviyede 24 kare-12 resim, 3.seviyede 36 kare-18 resim
     */

    //HucreButtonHandler hucrebuttonhandler = new HucreButtonHandler(a);
    HucreButtonHandler hucrebuttonhandler;
    Hafiza hafiza = new Hafiza();
    JFrame FrmFrame = new JFrame("<---HAFIZA OYUNU (BÜŞRA DİREK)-->");//Jframe olusturuldu.
    JMenuBar MnUstMenum = new JMenuBar(); //menu bar olusturuldu.1-islemler 2-durum ve 3-destek olan menumuz var
    JMenu MnIslem = new JMenu(" islemler "); //1-islemler menusunde 1.a-yeniden baslat 1.b-oyunu coz ve 1.c-oyunu kapat menu itemler bulunmakta.
    JMenuItem MNIYeniOyun = new JMenuItem("Yeniden Baslat");
    JMenuItem MNIOyunuCoz = new JMenuItem("Oyunu Coz");
    JMenuItem MNIOyunuKapat = new JMenuItem("Oyunu Kapat");
    JMenu MnDurum = new JMenu(" Durum ");//2-durum menumuzde 2.a-gecen sure ve 2.b-basari durumunuz isimli menu itemler bulunuyor.
    JMenuItem MNIGecenSure = new JMenuItem("Gecen Sure");
    JMenuItem MNIBasariDurumu = new JMenuItem("Basari Durumunuz");
    JMenu MnDestek = new JMenu(" Destek ");//3-destek menusunde 3.a-oynama seklini isimli meu item olusturuldu.
    JMenuItem MNIOyunOynanisi = new JMenuItem("Oynama Sekli");
    int BasariDurumunuAktiflestir = 1; //oyunu bilgisayarin cozmesi halinde 1 degerinden farkli bir deger alacaktir.

    HafizaGUI(int sayi) {
int en = 0,boy = 0, a=0;
if (sayi==16){en=460;boy=460;a=4;}else if (sayi==36){en=500;boy=500;a=6;}else if (sayi==24){en=550;boy=550;a=5;}else if (sayi==48){en=600;boy=600;a=7;}
       
hucrebuttonhandler = new HucreButtonHandler(a);
        FrmFrame.setSize(en, boy); //boyutlari
        FrmFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//kapata basinca kapatilmasi icin
        FrmFrame.setResizable(false);//boyutlarla oynanmasini engellemek icin
        FrmFrame.setVisible(true);
        FrmFrame.setLocationRelativeTo(null);//ekrani ortalayabilmesi icin
        FrmFrame.setJMenuBar(MnUstMenum); //olusturulan menu bar jframe e,menu itemlerde de  menu ye ekleniyor.
        MnUstMenum.add(MnIslem);
        MnUstMenum.add(MnDurum);
        MnUstMenum.add(MnDestek);
        MnIslem.add(MNIYeniOyun);
        MnIslem.add(MNIOyunuCoz);
        MnIslem.add(MNIOyunuKapat);
        MnDurum.add(MNIGecenSure);
        MnDurum.add(MNIBasariDurumu);
        MnDestek.add(MNIOyunOynanisi);
        FrmFrame.add(hucrebuttonhandler.PnlHucreler);//Hucreler diger sinifta onlarda jframe ekleniyor.
        MNIOyunuCoz.addActionListener(this);//toplam 6 olan menu itemlerin action listenerleri burada gerceklestirilecek.
        MNIYeniOyun.addActionListener(this);
        MNIOyunuKapat.addActionListener(this);
        MNIGecenSure.addActionListener(this);
        MNIBasariDurumu.addActionListener(this);
        MNIOyunOynanisi.addActionListener(this);
    }
static int sayi2;
    private void YeniOyun(int sayi) { //yeni oyuna tiklenince bu metod cagirilirdi.
        for (int i = 0; i < sayi; i++) { //tum hucrelere ilk resim ataniyor.
            hucrebuttonhandler.BtnHucre[i].setIcon(hucrebuttonhandler.BtnHucreilkResim);
            hucrebuttonhandler.BtnHucre[i].setEnabled(true);
            hucrebuttonhandler.BtnHucre[i].setName("00");
        }
        hucrebuttonhandler.HucreResimleriniRastgeleDagit(); //diger siniftaki metod cagiriyor.resimler baslarken rnd yuklaniyor ve zaman sifirlaniyor.
        hucrebuttonhandler.HucreSayaci = 0;//hucreler tik sayaci sifirlaniyor.
        hucrebuttonhandler.AcikHucrelerinSayisi = 0;//tum degerler ilk haline donduruldu.
        hucrebuttonhandler.SonTiklananButon[0]=21;
        hucrebuttonhandler.SonTiklananButon[1]=22;
        hucrebuttonhandler.CozulmusHucrelerinSayisi = 0;
        BasariDurumunuAktiflestir = 1;
    }

    public void HucreleriCoz(int sayi) {//butun hucreler acildi ve basari durumunu sorgulamayi engelkemek icin 0 degeri verildi.
        for (int i = 0; i < sayi; i++) {
            hucrebuttonhandler.BtnHucre[i].setIcon(hucrebuttonhandler.BtnHucreResimleri[i]);
            hucrebuttonhandler.BtnHucre[i].setName(String.valueOf(hucrebuttonhandler.HucreResmi[i]));
            hucrebuttonhandler.BtnHucre[i].setDisabledIcon(hucrebuttonhandler.BtnHucreResimleri[i]);
            hucrebuttonhandler.BtnHucre[i].setEnabled(false);
        }
        BasariDurumunuAktiflestir = 0;

    }

    public void actionPerformed(ActionEvent e) {
        hucrebuttonhandler.calendar = Calendar.getInstance();//suanki zamani alarakgecen sureyi guncel tutmak icin.
        if (e.getSource() == MNIOyunuKapat) { //oyunu kapat ise direkt oyun sonlandirilir.
            System.exit(0);
        }
        if (e.getSource() == MNIYeniOyun) { //yeni oyun metodu cagrilir.
            YeniOyun(zorluk);
        }
        if (e.getSource() == MNIOyunuCoz) {//hucrelericoz metodu cagiriliyor.
            HucreleriCoz(zorluk);
        }
        if (e.getSource() == MNIGecenSure) {//oyunun basladigi andan itibaren gecen sure hesaplanir.
            JOptionPane.showMessageDialog(FrmFrame, "Gecen Sure:" + (int) (hucrebuttonhandler.calendar.getTimeInMillis() - hucrebuttonhandler.OyununBaslangicZamani) / 1000 + " Saniye", "Oyun Devam Ediyor", 1);
        }
        if (e.getSource() == MNIBasariDurumu) {//hafiza sinifina parametreler gondererek bilgi bekleniyor.donen bilgiye gore message dialog cikiyor.
            String BasariSonucu = hafiza.BasariDurumu(BasariDurumunuAktiflestir, hucrebuttonhandler.CozulmusHucrelerinSayisi, (int) (hucrebuttonhandler.calendar.getTimeInMillis() - hucrebuttonhandler.OyununBaslangicZamani) / 1000);
            if (BasariDurumunuAktiflestir == 1) {
                JOptionPane.showMessageDialog(FrmFrame, "Basari Durumunuz: " + BasariSonucu, "Oyun Devam Ediyor", 1);
            } else {
                JOptionPane.showMessageDialog(FrmFrame, BasariSonucu, "Oyun Zaten Cozulmus", 1);
            }
        }
        if (e.getSource() == MNIOyunOynanisi) {//message diyalog ile oyun anlatiliyor.
            JOptionPane.showMessageDialog(FrmFrame, "En Kisa Surede Hucrelerin Eslerini Bulmaya Calismalisiniz.Actiginiz Hucre Sayisi Gecen Sureye Bolunup Basariniz Olculecek.");
        }

    }
    
static int zorluk;
    public static void main(String[] args) {
        sayi2=Integer.parseInt(JOptionPane.showInputDialog("1-4 arası zorluk gir"));
        if (sayi2==1){zorluk=16;}else if(sayi2==2){zorluk=24;}else if(sayi2==3){zorluk=36;}else if(sayi2==4){zorluk=48;}
            HafizaGUI hafizagui = new HafizaGUI(zorluk);
    }
}
