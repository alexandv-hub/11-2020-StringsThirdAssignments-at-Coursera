import edu.duke.*;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    
    public int findStopCodon (String dna, int startIndex, String stopCodon) {
        int firstOccurrenceOfStopCodon = dna.indexOf(stopCodon);
        if ((firstOccurrenceOfStopCodon-startIndex)%3 == 0)    {        
            return firstOccurrenceOfStopCodon;
        }
        else
            {          
             return dna.length();
        }
    } 
    
    
    
    void testFindStopCodon () {
                //    0123456789012345678901234567
                //    V  V        V  V        V
        String dna = "ATGTTTCCCAAATAAATGAAATGTAAGG";
        int startIndex = dna.indexOf("ATG");
        String stopCodon = "TAA";
        findStopCodon (dna, startIndex, stopCodon);
        
         //    012345678901234567890123456789012
         //       V         V   V  V        V
        dna = "GGGATGTTTCCCCTAAATAAATGAAATTATAAGG";
        findStopCodon (dna, startIndex, stopCodon);
    } 
    
    
    String findGene (String dna) {
        System.out.println();
        System.out.println("Запуск метода findGene для " + dna);
        System.out.println("длина - " + dna.length());
        String geneFound = "";
        int startIndex = 0;
        if (dna.contains("ATG")) {
            startIndex = dna.indexOf("ATG");
        }
        else {
            System.out.println("Не содержит ATG");
            return "";
        }    
        
        if ( findStopCodon(dna, startIndex, "TAA") != dna.length()  
             && startIndex<findStopCodon(dna, startIndex, "TAG")
             && (findStopCodon(dna, startIndex, "TAA")-startIndex)%3 == 0)   {
            geneFound = dna.substring(startIndex, findStopCodon(dna, startIndex, "TAA")+3);    
            System.out.println("Найден ген по stopCodon TAA: " + geneFound);
        }    
        String geneFound1 = geneFound;
        System.out.println("geneFound1=" + geneFound1);
        
        if ( findStopCodon(dna, startIndex, "TAG") != dna.length() 
             && startIndex<findStopCodon(dna, startIndex, "TAG")
             && (findStopCodon(dna, startIndex, "TAG")-startIndex)%3 == 0)   {
                 geneFound = dna.substring(startIndex,findStopCodon(dna, startIndex,"TAG")+3); 
                 System.out.println("Найден ген по stopCodon TAG:"+ geneFound);
        }
        String geneFound2 = geneFound;
        System.out.println("geneFound2=" + geneFound2);
        
        if (geneFound1.length()>geneFound2.length()) {
            geneFound = geneFound2;
        }
        else
            geneFound = geneFound1;            
        System.out.println(("Shortest genefound=" + geneFound));
        return geneFound;
    } 
    
    
    void testFindGene () {
        findGene("ATGTTTTAAAAATAGATGAAATGTAGG");
        findGene("ATGTTTAAAAATAGATGAAATGTAGG");
        findGene("TTTAAAAATAGAATAGG");
        findGene("TTTAAAAATAGATGAATAGG");
    }
    
    
    void printAllGenes  (String dna) {
        boolean ifATGExists = true;
        int startIndex = 0;
        System.out.println();
        System.out.println("Запуск метода printAllGenes для " + dna);
        System.out.println("длина - " + dna.length());
        while (ifATGExists) {
            
            String geneFound = "";
            
            if (dna.contains("ATG")) {
                startIndex = dna.indexOf("ATG");
            }
            else {
                // System.out.println("Не содержит ATG"); 
                ifATGExists = false;
                break;
            }    
            
            if ( findStopCodon(dna, startIndex, "TAA") != dna.length()  
                 && startIndex<findStopCodon(dna, startIndex, "TAA")
                 && (findStopCodon(dna, startIndex, "TAA")-startIndex)%3 == 0)   {
                geneFound = dna.substring(startIndex, findStopCodon(dna, startIndex, "TAA")+3); 
                System.out.println("geneFound (TAA)=" + geneFound);                
            }        
                        
            if ( findStopCodon(dna, startIndex, "TAG") != dna.length() 
                 && startIndex<findStopCodon(dna, startIndex, "TAG")
                 && (findStopCodon(dna, startIndex, "TAG")-startIndex)%3 == 0)   {
                     geneFound = dna.substring(startIndex,findStopCodon(dna, startIndex,"TAG")+3); 
                     System.out.println("geneFound (TAG)=" + geneFound);                      
            }
            
            dna=dna.substring(startIndex+3, dna.length());         
        
        
        }
    
    }
    
     void testPrintAllGenes () {
        printAllGenes("ATGTTTTAAAAATAGATGAAATGTAGG");
        printAllGenes("ATGTTTAAAAATAGATGAAATGTAGG");
        printAllGenes("TTTAAAAATAGAATAGG");
        printAllGenes("TTTAAAAATAGATGAATAGG");
    }
    
    
    
    public StorageResource getAllGenes  (String dna) {
            StorageResource sr = new StorageResource();    
            boolean ifATGExists = true;
            int startIndex = 0;
            String startCodon = "ATG";
            String stopCodon1 = "TAA";
            String stopCodon2 = "TGA";
            String stopCodon3 = "TAG";
            System.out.println();
            System.out.println("Запуск метода getAllGenes для " + dna);
            System.out.println("длина - " + dna.length());
            while (ifATGExists) {
                
                String geneFound = "";
                
                if (dna.contains(startCodon)) {
                    startIndex = dna.indexOf(startCodon);
                }
                else {
                    // System.out.println("Не содержит ATG"); 
                    ifATGExists = false;
                    break;
                }    
                
                if ( findStopCodon(dna, startIndex, stopCodon1) != dna.length()  
                     && startIndex<findStopCodon(dna, startIndex, stopCodon1)
                     && (findStopCodon(dna, startIndex, stopCodon1)-startIndex)%3 == 0)   {
                    geneFound = dna.substring(startIndex, findStopCodon(dna, startIndex, stopCodon1)+3); 
                    sr.add(geneFound);
                    //System.out.println("geneFound (TAA)=" + geneFound);                
                }        
                
                if ( findStopCodon(dna, startIndex, stopCodon2) != dna.length() 
                     && startIndex<findStopCodon(dna, startIndex, stopCodon2)
                     && (findStopCodon(dna, startIndex, stopCodon2)-startIndex)%3 == 0)   {
                         geneFound = dna.substring(startIndex,findStopCodon(dna, startIndex,stopCodon2)+3); 
                         sr.add(geneFound);
                         //System.out.println("geneFound (TGA)=" + geneFound);                      
                }
                
                if ( findStopCodon(dna, startIndex, stopCodon3) != dna.length() 
                     && startIndex<findStopCodon(dna, startIndex, stopCodon3)
                     && (findStopCodon(dna, startIndex, stopCodon3)-startIndex)%3 == 0)   {
                         geneFound = dna.substring(startIndex,findStopCodon(dna, startIndex,stopCodon3)+3); 
                         sr.add(geneFound);
                         //System.out.println("geneFound (TAG)=" + geneFound);                      
                }
                
                dna=dna.substring(startIndex+3, dna.length());         
            
            
            }
            System.out.println();
            return sr;
    }
    
    void testGetAllGenes () {
        StorageResource testSr = new StorageResource();
        testSr = getAllGenes("ATGTTTAAATAGATGAAATGTAGGATGAAATAA");
        for (String s : testSr.data()) {
            System.out.println(s);
        }
    }
    
    
    double cgRatio (String dna) {
        String input = dna;
        float cgRatio;
        int DNA_LENGTH = dna.length();
        String c = "c";
        String g = "g";
        int cgCounter = 0;
        
        boolean ifDnaHasC = false;
        boolean ifDnaHasG = false;
        
        
        if ( dna.contains(c) ) {
            ifDnaHasC = true;
            
        }
        //System.out.println(dna);
        int startIndex = 0;
        while (ifDnaHasC) {
            //System.out.println("Начало цикла while для c");
            startIndex = dna.indexOf(c)+1;
            dna = dna.substring(startIndex, + dna.length() );
            //System.out.println("dna="+dna);
            //System.out.println("dnaLength  = " + dna.length());
            cgCounter++;
            //System.out.println("cgCounter="+cgCounter);
            startIndex +=1;
            //System.out.println("startIndex после итерации = " + startIndex);
            //System.out.println("dnaLength после итерации = " + dna.length());
            
            if (dna.indexOf(c)  == -1)
                ifDnaHasC = false;        
        }
        //System.out.println("Конец поиска C, counter = " + cgCounter);
        //System.out.println();
        
        
        dna = input;
         if ( dna.contains(g) ) {
            ifDnaHasG = true;
        }
        
        startIndex = 0;
        
        //System.out.println("startIndex = " + startIndex);
        //System.out.println("dnaLength="+dna.length());
        while (ifDnaHasG) {
            //System.out.println("Начало цикла while для g");
            startIndex = dna.indexOf(g)+1;
            dna = dna.substring(startIndex, + dna.length() );
            //System.out.println("dna="+dna);
            //System.out.println("dnaLength  = " + dna.length());
            cgCounter++;
            //System.out.println("cgCounter="+cgCounter);
            startIndex +=1;
            //System.out.println("startIndex после итерации = " + startIndex);
            //System.out.println("dnaLength после итерации = " + dna.length());
            if (dna.indexOf(g)  == -1)
                ifDnaHasG = false;        
        }
        //System.out.println("DNA_LENGTH="+ DNA_LENGTH);
        //System.out.println("cgCounter="+cgCounter);
        cgRatio = cgCounter/(float)DNA_LENGTH; 
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
            System.out.println("startIndex=" + startIndex);
            startIndex = dna.indexOf(ctg);
       
            if (startIndex > dna.length()-2 )
                break;
            else 
                {                
                System.out.println("dna=" + dna + " (" + dna.length() + ")");
                counter++;
                System.out.println("counter=" + counter);
                startIndex += 3;
                
                System.out.println("startIndex after iteration = " + startIndex);
                
                dna = dna.substring(startIndex, dna.length() );
                
                if (dna.indexOf(ctg)  == -1)
                    ifDnaHasCTG = false; 
            }
                
            
        }
            return counter;
        }
        
        
   
    
    void testCountCTG () { 
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
    }
   
    
    
    
    
    void processGenes (StorageResource sr) {
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
        System.out.println(theLongestDNA);
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
        /*
        FileResource fr = new FileResource("TestDNAFileResource.txt");
        StorageResource store = new StorageResource();
        for (String s : fr.words()) {
             store.add(s);
        }
        */
        FileResource fr = new FileResource("brca1line.fa");
        StorageResource store = new StorageResource();
        String dna = fr.asString();       
        
        store = getAllGenes(dna);
        /*
        StorageResource store = new StorageResource();
        for (String s : fr.words()) {
             store.add(s);
        }
        */
       
        processGenes(store);
    
    }
    
}
