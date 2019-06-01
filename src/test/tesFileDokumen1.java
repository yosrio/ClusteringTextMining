/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import model.Document;
import model.InvertedIndex;

/**
 *
 * @author yosrio
 */
public class tesFileDokumen1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        File dir = new File("F:\\smstr 6\\PemrolehanInformasi\\DocumentRetrieval\\dokumen");
        InvertedIndex index = new InvertedIndex();
        index.readDirectory(dir);
        ArrayList<Document> listDoc = index.getListOfDocument();
        for (int i = 0; i < listDoc.size(); i++) {
            Document doc = listDoc.get(i);
            System.out.println("Content : " + doc.getId());
            System.out.println(doc.getContent());
        }
    }

}
