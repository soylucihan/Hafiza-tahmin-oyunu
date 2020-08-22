public class Hafiza {
    /*
     * Burada oyunun durumu saklaniyor.gecen sure ile cozulen hucre sayisi bakilip oranina gore basari olcumu yapiliyor.
     */

    public String BasariDurumu(int Cagri, int CozulenHucreSayisi, int GecenSure) {
        if (Cagri == 2) { //bunu ancak oyun bitirme butonu cagirir.
            int oran = GecenSure / CozulenHucreSayisi;
            if (oran <= 8) {
                return " Super " + GecenSure + " Saniyede Tamamladiniz.";
            } else if (8.5 < oran && oran <= 11.5) {
                return " Normal " + GecenSure + " Saniyede Tamamladiniz.";
            } else {
                return " Kotu" + GecenSure + " Saniyede Tamamladiniz.";
            }
        } else if (Cagri == 1) { //devam eden sure icerisinde bilgi alinmak istenilirse cagiriliyor.
            if (!(CozulenHucreSayisi == 0)) { //ama cozulen hucre varsa olsum yapilir.
                double oran = GecenSure / CozulenHucreSayisi;
                if (oran <= 8.5) {
                    return " Super " + GecenSure + " Saniyede " + 2 * CozulenHucreSayisi + " Resim Eslestirdiniz";
                } else if (8.5 < oran && oran <= 11.5) {
                    return " Normal " + GecenSure + " Saniyede " + 2 * CozulenHucreSayisi + " Resim Eslestirdiniz";
                } else {
                    return " Kotu " + GecenSure + " Saniyede " + 2 * CozulenHucreSayisi + " Resim Eslestirdiniz";
                }
            } else { //yoksa uyarilir.
                return "Hesaplanmadi Cunku Acilan Hucre Yok";
            }
        } else { //cagri parametreleri 2 veya 1 degilse ki 0 dır o zamanda oyunu bilgisayarin cozdugu anlasilir.
            return "Hesaplanmadi Cunku Oyunu Bilgisayar Cozdu";
        }
    }
}
