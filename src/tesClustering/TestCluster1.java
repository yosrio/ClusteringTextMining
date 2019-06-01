/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesClustering;

import java.util.ArrayList;
import model.Document;
import model.InvertedIndex;
import model.Posting;

/**
 *
 * @author yosrio
 */
public class TestCluster1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Document doc1 = new Document(1, "Fahri Hamzah Usul Ibu Kota Pindah ke Kepulauan Seribu");
        Document doc2 = new Document(2, "Gaya Nyentrik Menteri Susi Saat Pimpin Penenggelaman 13 Kapal Vietnam di Kalbar");
        Document doc3 = new Document(3, "TNI AL: KRI Tjiptadi-381 Diprovokasi Kapal Pengawas Ikan Vietnam ");

        // buat object invertedIndex
        InvertedIndex index = new InvertedIndex();
        // lakukan stemming untuk semua dokumen
        doc1.IndonesianStemming();
        doc2.IndonesianStemming();
        doc3.IndonesianStemming();
        // tmbahkan document ke index
        index.addNewDocument(doc1);
        index.addNewDocument(doc2);
        index.addNewDocument(doc3);
        // bikin dictionary
        index.makeDictionaryWithTermNumber();
//        index.preClustering();
        index.clustering();
        for (int i = 0; i < index.getListOfCluster().size(); i++) {
            System.out.println(index.getListOfCluster().get(i).getCenter());
        }
//        for (int i = 0; i < index.getListOfDocument().size(); i++) {
//            ArrayList<Posting> listPosting = index.getListOfDocument().get(i).getListOfClusteringPosting();
//            System.out.println("IdDoc = " + index.getListOfDocument().get(i).getId());
//            for (int j = 0; j < listPosting.size(); j++) {
//                System.out.println(listPosting.get(j).getDocument());
//            }
//        }
    }
}
