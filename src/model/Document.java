/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.id.IndonesianAnalyzer;
import org.apache.lucene.analysis.id.IndonesianStemFilter;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

/**
 *
 * @author Yos Rio
 */
public class Document implements Comparable<Document> {

    private int id;
    private String content;
    private String realContent;
    private ArrayList<Posting> listOfClusteringPosting  = new ArrayList<Posting>();
    private String title;
    private String author;
    private int idCluster = -1;
    
    public Document() {
    }

    public Document(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public int getIdCluster() {
        return idCluster;
    }

    /**
     * @param idCluster the idCluster to set
     */
    public void setIdCluster(int idCluster) {
        this.idCluster = idCluster;
}
    
    
    public Document(String content) {
        this.content = content;
        this.realContent = content;
    }

    public Document(int id, String content) {
        this.id = id;
        this.content = content;
        this.realContent = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRealContent() {
        return realContent;
    }

    public void setRealContent(String realContent) {
        this.realContent = realContent;
    }

    public ArrayList<Posting> getListOfPosting() {
        return listOfClusteringPosting;
    }

    public void setListOfPosting(ArrayList<Posting> listOfPosting) {
        this.listOfClusteringPosting = listOfPosting;
    }

    public String[] getListofTerm() {
        String value = this.getContent();
        value = value.replaceAll("[.,?!]", "");
        value = value.replaceAll("\n", " ");
        return value.split(" ");
    }

    public ArrayList<Posting> getListofPosting() {
        String[] tempString = getListofTerm();
        ArrayList<Posting> list = new ArrayList<>();
        for (int i = 0; i < tempString.length; i++) {
            if (i == 0) {
                Posting tempPosting = new Posting(tempString[0], this);
                list.add(tempPosting);
            } else {
                Collections.sort(list);
                Posting tempPosting = new Posting(tempString[i], this);
                int indexCari = Collections.binarySearch(list, tempPosting);
                if (indexCari < 0) {
                    list.add(tempPosting);
                } else {
                    int tempNumber = list.get(indexCari).getNumberOfTerm() + 1;
                    list.get(indexCari).setNumberOfTerm(tempNumber);
                }
            }
        }
        Collections.sort(list);
        return list;
    }

    @Override
    public int compareTo(Document o) {
        return Integer.compare(this.id, o.id);
    }

    public void readFile(int idDoc, File file) throws FileNotFoundException, IOException {
        int len;
        char[] chr = new char[4096];
        final StringBuffer buffer = new StringBuffer();
        final FileReader reader = new FileReader(file);
            String name = file.getName().replace(".txt", "");
            String[] splitter;
        try {
            splitter = name.split("-");
            setAuthor(splitter[0]);
            setTitle(splitter[1]);
        } catch (Exception e) {
            
        }

        try {
            while ((len = reader.read(chr)) > 0) {
                buffer.append(chr, 0, len);
            }
        } finally {
            reader.close();
        }
        this.id = idDoc;
        this.content = buffer.toString();
//        IndonesianStemming();
        this.realContent = buffer.toString();
    }

    @Override
    public String toString() {
        return "Document{" + "id=" + id + ", content=" + content + '}';
    }

    public void removeStopWords() {
        String text = content;
        Version matchVersion = Version.LUCENE_7_7_0;
        Analyzer analyzer = new StandardAnalyzer();
        analyzer.setVersion(matchVersion);

        CharArraySet stopWords = EnglishAnalyzer.getDefaultStopSet();
        TokenStream tokenStream = analyzer.tokenStream("myField", new StringReader(text));

        tokenStream = new StopFilter(tokenStream, stopWords);
        StringBuilder sb = new StringBuilder();
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        try {
            tokenStream.reset();
            while (tokenStream.incrementToken()) {
                String term = charTermAttribute.toString();
                sb.append(term + " ");
            }
        } catch (IOException ex) {
            System.out.println("Exception : " + ex);
        }

        content = sb.toString();
    }

    public void stemming() {
        String text = content;
        Version matchVersion = Version.LUCENE_7_7_0;
        Analyzer analyzer = new StandardAnalyzer();
        analyzer.setVersion(matchVersion);

        TokenStream tokenStream = analyzer.tokenStream("myField", new StringReader(text));

        tokenStream = new PorterStemFilter(tokenStream);
        StringBuilder sb = new StringBuilder();
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        try {
            tokenStream.reset();
            while (tokenStream.incrementToken()) {
                String term = charTermAttribute.toString();
                sb.append(term + " ");
            }
        } catch (IOException ex) {
            System.out.println("Exception : " + ex);
        }
        content = sb.toString();
    }

    public void IndonesianStemming() {
        String text = content;
        Version matchVersion = Version.LUCENE_7_7_0;
        Analyzer analyzer = new IndonesianAnalyzer();
        analyzer.setVersion(matchVersion);

        TokenStream tokenStream = analyzer.tokenStream("myField", new StringReader(text));

        tokenStream = new IndonesianStemFilter(tokenStream);
        StringBuilder sb = new StringBuilder();
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        try {
            tokenStream.reset();
            while (tokenStream.incrementToken()) {
                String term = charTermAttribute.toString();
                sb.append(term + " ");
            }
        } catch (IOException ex) {
            System.out.println("Exception : " + ex);
        }
        content = sb.toString();
    }

    public ArrayList<Posting> getListOfClusteringPosting() {
        return listOfClusteringPosting;
    }

    public void setListOfClusteringPosting(ArrayList<Posting> listOfClusteringPosting) {
        this.listOfClusteringPosting = listOfClusteringPosting;
    }

    
}
