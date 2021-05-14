import edu.duke.*;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1new {
    
    public int findStopCodon (String dnaStr, int startIndex, String stopCodon) {
        int currIndex = dnaStr.indexOf(stopCodon, startIndex+3);
        while (currIndex != -1) {
            int diff = currIndex - startIndex;
                if(diff %3 == 0) {
                return currIndex;
            }
            else {
            currIndex = dnaStr.indexOf(stopCodon, currIndex+1);
            }
        }
        return -1;
    } 
    
    
    
    void testFindStopCodon () {
                //    0123456789012345678901234567
                //    V           V           V
        String dna = "ATGxxxyyyzzzgTAAxxxyyyzzzTAAxx";
        int startIndex = dna.indexOf("atg");
        String stopCodon = "taa";
        System.out.println(findStopCodon (dna, startIndex, stopCodon));
        /*
         //    012345678901234567890123456789012
         //       V         V   V  V        V
        dna = "GGGATGTTTCCCCTAAATAAATGAAATTATAAGG";
        findStopCodon (dna, startIndex, stopCodon);
        */
    } 
    
    
    String findGene (String dna, int where) {
        
        //System.out.println("Запуск метода findGene для " + dna); 
        int startIndex = dna.indexOf("ATG", where);
        //System.out.println("startIndex="+startIndex);
        if (startIndex == -1) {
            return "";
        }
        
        int taaIndex = findStopCodon (dna, startIndex, "TAA");
        int tagIndex = findStopCodon (dna, startIndex, "TAG");
        int tgaIndex = findStopCodon (dna, startIndex, "TGA");
        
        int minIndex = 0;
        if (taaIndex == -1 || 
            (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        }
        else {
            minIndex = taaIndex;
        }
        
        
        if (minIndex == -1 || 
            (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }
        
        
        if (minIndex == -1) {
            return "";
        }
        //System.out.println(dna.substring(startIndex, minIndex + 3));
        return dna.substring(startIndex, minIndex + 3);
    } 
    
    
    void testFindGene () {
        System.out.println(findGene("atgxxxyyyzzzxxxtagyyyzzztgaxx", 0));
        //findGene("ATGtttaaaTAAaaaggggTAGaa");
        // findGene("TTTAAAAATAGAATAGG");
        // findGene("TTTAAAAATAGATGAATAGG");
    }
    
    
    void printAllGenes  (String dna) {
        int startIndex = 0;
        System.out.println("printAllGenes...");
        
        while (true) {
            String currentGene = findGene (dna, startIndex);
            if (currentGene.isEmpty() ) {
            break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
            
        }
    
    }
    
     void testPrintAllGenes () {
        printAllGenes("atgxxxyyyzzzxxxtagyyyzzztgaxxatgttttga");
        //printAllGenes("ATGTTTAAAAATAGATGAAATGTAGG");
        //printAllGenes("TTTAAAAATAGAATAGG");
        //printAllGenes("TTTAAAAATAGATGAATAGG");
    }
    
    
    
    public StorageResource getAllGenes  (String dna) {
        System.out.println("getAllGenes...");
        StorageResource sr = new StorageResource();    
        int startIndex = 0;
        
        while (true) {
            String currentGene = findGene (dna, startIndex);
            if (currentGene.isEmpty() ) {
            break;
            }
            sr.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
            
        }
            
        return sr;
    }
    
    void testGetAllGenes () {
        
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString(); 
        //StorageResource store = new StorageResource();
        // store = getAllGenes(dna);
        
        StorageResource testSr = new StorageResource();
        testSr = getAllGenes(dna);
        
        for (String s : fr.words()) {
            System.out.println(s);
        }
    }
    
    
    
    double cgRatio (String dna) {
             String input = dna;
            double cgRatio;
            int DNA_LENGTH = dna.length();
            String c = "C";
            String g = "G";
            int cgCounter = 0;
            
            boolean ifDnaHasC = false;
            boolean ifDnaHasG = false;
        
        
            if ( dna.contains(c) ) {
            ifDnaHasC = true;
            
        }
        
        int startIndex = 0;
        while (ifDnaHasC) {
            
            startIndex = dna.indexOf(c)+1;
            dna = dna.substring(startIndex, + dna.length() );
            cgCounter++;
            startIndex +=1;            
            if (dna.indexOf(c)  == -1)
                ifDnaHasC = false;        
        }
        
        dna = input;
        if ( dna.contains(g) ) {
           ifDnaHasG = true;
        }
                                                                                        
        startIndex = 0;
        while (ifDnaHasG) {
                
            startIndex = dna.indexOf(g)+1;
            dna = dna.substring(startIndex, + dna.length() );
            cgCounter++;
            startIndex +=1;
            if (dna.indexOf(g)  == -1)
                ifDnaHasG = false;        
        }
        
        cgRatio = cgCounter/(double)DNA_LENGTH; 
        return cgRatio;             
    }
    
    void testCgRatio () {        
         // System.out.println(cgRatio("ATGTTTTAAAAATAGATGAAATGTAGG"));
         System.out.println(cgRatio("ATGCCATAG"));
    }
    
    
    int countCTG (String dna) {
        int counter = 0;
        String ctg = "CTG";
        boolean ifDnaHasCTG = false;

        if ( dna.contains(ctg) ) {
            ifDnaHasCTG = true;
        }
        int startIndex = 0;
        while (ifDnaHasCTG) {
            //System.out.println("startIndex=" + startIndex);
            startIndex = dna.indexOf(ctg);
       
            if (startIndex > dna.length()-2 )
                break;
            else 
                {                
                //System.out.println("dna=" + dna + " (" + dna.length() + ")");
                counter++;
                //System.out.println("counter=" + counter);
                startIndex += 3;
                
                //System.out.println("startIndex after iteration = " + startIndex);
                
                dna = dna.substring(startIndex, dna.length() );
                
                if (dna.indexOf(ctg)  == -1)
                    ifDnaHasCTG = false; 
            }
                
            
        }
            return counter;
        }
   
    void testCountCTG () { 
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString(); 
        System.out.println(countCTG(dna));
        /*
        System.out.println(countCTG("CTGAAABBBCTG"));
        System.out.println();
        System.out.println(countCTG("CTGACTG"));
        System.out.println();
        System.out.println(countCTG("CTGCTG"));
        System.out.println();
        //System.out.println(countCTG("ATCTGGTTTTAAAAATCTGAGATGAAATCTGCTGGTAGGCTGCTG"));
        System.out.println(countCTG("CTGCTGCTGCTG"));
        System.out.println();
        System.out.println(countCTG("ATCTGGTTTCTGCTGTAAAAATCTGAGATGAAATCTGCTGGTAGGCTGCTG"));
        */
    }
   
    
    
    
    
    void processGenes (StorageResource sr) {
        System.out.println("print all DNAs");
        for (String s : sr.data() ) {
            System.out.println(s);
        }
        System.out.println();
        
        
        
        System.out.println("print all the Strings in sr that are longer than 9 characters");
        for (String s : sr.data() ) {
        if (s.length()>9) 
            System.out.println(s);
        }
        System.out.println();
        
        
        System.out.println("print the number of Strings in sr that are longer than 9 characters");
        int counter = 0;
        for (String s : sr.data() ) {
        if (s.length()>9) 
                counter++;
        }
        System.out.println(counter);
        System.out.println();
        
        
        System.out.println("print the Strings in sr whose C-G-ratio is higher than 0.35");
        for (String s : sr.data() ) {
        if (cgRatio(s)>0.35) {
            
            System.out.println(s);
        }
        }
        System.out.println();
        
        
        System.out.println("print the number of strings in sr whose C-G-ratio is higher than 0.35");
        counter = 0;
        for (String s : sr.data() ) {
        if (cgRatio(s)>0.35)
            counter++;
        }
        System.out.println(counter);
        System.out.println();
        
        
        System.out.println("print the length of the longest gene in sr");
        String theLongestDNA = "";
        for (String s : sr.data() ) {
        if (s.length() > theLongestDNA.length() )
            theLongestDNA = s;
        }
        System.out.println(theLongestDNA.length() );
        System.out.println();
        
        
        System.out.println("print all the Strings that are longer than 60 characters");        
        for (String s : sr.data() ) {
        if (s.length() > 60 )
            System.out.println(s);
        }
        System.out.println();
        
        
        System.out.println("print the number of Strings that are longer than 60 characters ");
        counter = 0;
        for (String s : sr.data() ) {
        if (s.length() > 60 )
            counter++;
        }
        System.out.println(counter);
        System.out.println();
        
        
        System.out.println("print the number of genes:");
        counter = 0;
        for (String s : sr.data() ) {        
            counter++;
        }
        System.out.println(counter);
        System.out.println();
        
    
    }    
    
    void testProcessGenes () {
        
        //FileResource fr = new FileResource("TestDNAFileResource.txt");
        
        
        /*
        for (String s : fr.words()) {
             store.add(s);
        }
        */
        
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString(); 
        
        StorageResource store = new StorageResource();
        store = getAllGenes(dna);
        //printAllGenes("atgcctattggatccaaagagaggccaacattttttgaaatttttaagacacgctgcaacaaagcagatttaggaccaataagtcttaattggtttgaagaa");
        /*
        for (String s : store.data()) {
            System.out.println(s);
        }
        */
        
        
        /*
        StorageResource store = new StorageResource();
        for (String s : fr.words()) {
             store.add(s);
        }
        */
       
        processGenes(store);
    
    }
    
}
