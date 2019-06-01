/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import model.Document;
import model.InvertedIndex;

/**
 *
 * @author yosrio
 */
public class tesFileDokumen2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        InvertedIndex index = new InvertedIndex();
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File dir = fc.getSelectedFile();
            index.readDirectory(dir);
        }

        ArrayList<Document> listDoc = index.getListOfDocument();
        for (int i = 0; i < listDoc.size(); i++) {
            Document doc = listDoc.get(i);
            System.out.println("Content : " + doc.getId());
            System.out.println(doc.getContent());
        }
    }

}
