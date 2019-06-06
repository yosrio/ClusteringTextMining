/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesClustering;

import test.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import model.Document;
import model.DocumentToClusterSimilarity;
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
        File dir = new File("dokumenCoba");
        InvertedIndex index = new InvertedIndex();
        index.readDirectory(dir);
        index.clustering();
        for (int i = 0; i < index.getListOfCluster().size(); i++) {
            System.out.println("cluster = " + i + ", center = " + index.getListOfCluster().get(i).getCenter().getId());
            for (int j = 0; j < index.getListOfCluster().get(i).getMember().size(); j++) {
                System.out.println("id dok : " + index.getListOfCluster().get(i).getMember().get(j).getId());
            }
        }
    }

}
