package word_frequency;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import com.sun.xml.internal.fastinfoset.util.StringArray;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tasnim
 */

public class probabilityTable {
    
    ArrayList<ProbabilityCount> probability_count1 = new ArrayList<ProbabilityCount>();  
    ArrayList<ProbabilityCount> probability_count2 = new ArrayList<ProbabilityCount>(); 
    ArrayList<ProbabilityCount> probability_list = new ArrayList<ProbabilityCount>();
    ArrayList<ProbabilityCount> output = new ArrayList<ProbabilityCount>();
    ArrayList<DistanceCount> word_distance = new ArrayList<DistanceCount>();  
    
    HashMap<String,Integer> 
    possible_word=new HashMap<String, Integer>();
    
    HashMap<String,String> 
    generate_word=new HashMap<String, String>();
    
   // ArrayList<String> generate_word = new ArrayList<String>();
    
    int num=0,siz=0,chkstr=0;
    String input_word1,input_word2,tok1,tok2,genfstword,mvStr;
    float word1_freq1,word1_freq2,word2_freq1,word2_freq2;
    static probabilityTable wordlist=new probabilityTable();
    Edit_Distance distance=new Edit_Distance();
    
    ArrayList doProcessing(String word1, String word2){ //private 
   
        wordlist.loadAnndTokenize1();
        wordlist.loadAnndTokenize2();
        wordlist.loadAnndTokenize3();
        //wordlist.problityCount();
        wordlist.probabilityList();
        return output = probability_list;
		
    }
 
    class ProbabilityCount implements Comparator<ProbabilityCount> {
    
    public int compare(ProbabilityCount o1, ProbabilityCount o2) {
        
        if(o1.probability >o2.probability)
        {
            return -1;
        }
        else if(o1.probability <o2.probability)
        {
            return 1;
        }    
        
        else
        {
             return 0;
        }
    }
        float probability;
        String word;
    }
    
    class DistanceCount implements Comparator<DistanceCount> {
    
    public int compare(DistanceCount o1, DistanceCount o2) {
        
        if(o1.dis >o2.dis)
        {
            return -1;
        }
        else if(o1.dis <o2.dis)
        {
            return 1;
        }   
        else
        {
            return 0;
        }
    }
        int dis;
        String i_word;
        String o_word;
    }
    
     public static void main(String[] args) throws IOException{
        // TODO code application logic here
        wordlist.input();       
    }
    
     // get input 3 word..analyze middle word from input1.txt
    void input() throws IOException
    {
        String fst_word = null;
        String middle_word = null;
        String location ="src//word_frequency//";
        String text;
        //String get_txt[] = {"তবে","আগামী","মাসগুলোতে","আর","এ","প্রবণতা","থাকবে","না","মন্তব্য","করে","তিনি","বলেন",
           // "বিদেশি","সাহায্যের","গতি","বাড়লে","আর","রাজস্ব","আদায়ের","প্রবৃদ্ধি","বাড়লে","ঋণের","পরিমাণ","কমে","আসবে" };
        try 
        {
            FileInputStream fis_kal = new FileInputStream(location+"input1.txt");
            InputStreamReader isr_kal = new InputStreamReader(fis_kal, "UTF-8");
            BufferedReader b_kal = new BufferedReader(isr_kal);

            text = b_kal.readLine();
            String line = null;
            int gw=0;
            while ((text = b_kal.readLine()) != null) 
            {
                StringTokenizer tok = new StringTokenizer(text, "।?");
                
                while(tok.hasMoreTokens())
                {
                    int toksize=0;
                    chkstr=0;
                    //input_word1=null;input_word2=null;fst_word=null;middle_word=null;
                    
                    line=tok.nextToken();
                    StringTokenizer  tok1 = new StringTokenizer(line, " ’\";[]''!;,()'[]{}/=/”'‘˜'“");
                    
                    toksize=tok1.countTokens();
                    
                    if(tok1.hasMoreTokens())
                    {
                        fst_word= tok1.nextToken(); 
                        if(gw==0)
                        {
                            genfstword=fst_word;
                            gw++;
                        }
                        if(tok1.hasMoreTokens())
                        {
                            middle_word= tok1.nextToken();
                        }
                        
                    }
                    
                    if(toksize>2)
                    {
                        while(tok1.hasMoreTokens())
                        {
                            if(chkstr==0)
                            {
                                input_word1 = fst_word;
                            }
                            else
                            {
                                input_word1 = mvStr;
                            }

                            input_word2=tok1.nextToken();
                            fst_word=middle_word;
                            //System.out.println(input_word1+"--------------------------------------"+input_word2);
                            num++;
                            output=wordlist.doProcessing(input_word1, input_word2);
                            wordlist.possibleOutput();

                            editDistanceGenerate(middle_word);
                            middle_word=input_word2;
                        }    
                    }
              }
            }
        }
        
        catch (Exception e)
        {
            e.printStackTrace();
        }
        wordlist.getOutput();
    }
    
    //total count of each input word from output_frequency1.txt
    void loadAnndTokenize1()
    {
         String location ="src//word_frequency//";
         String str,text;
        try 
        {
            FileInputStream fis_kal = new FileInputStream(location+"output_frequency1.txt");
            InputStreamReader isr_kal = new InputStreamReader(fis_kal, "UTF-8");
            BufferedReader b_kal = new BufferedReader(isr_kal);

            text = b_kal.readLine();
                    
            while ((text = b_kal.readLine()) != null) 
            {
                StringTokenizer tok = new StringTokenizer(text, " ");
                
                while(tok.hasMoreTokens())
                {
                    str = tok.nextToken();

                    if(str.equals(input_word1))
                    {
                        tok1=str;
                        word1_freq1=Integer.parseInt(tok.nextToken());
                    }
                    
                    if(str.equals(input_word2))
                    {
                        tok2=str;
                        word2_freq1=Integer.parseInt(tok.nextToken()); 
                    }
                }
            }
        }
        
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
    
    }
    
    //for fst input ,,,probability count for 2 word/total count of  fst input word  from output_frequency2.txt,save 2nd word nd probability
    void loadAnndTokenize2()
    {
         
         String location ="src//word_frequency//";
         String fst_word,snd_word,freq,text,g_str;
       
        try 
        {
            FileInputStream fis_kal = new FileInputStream(location+"output_frequency2.txt");
            InputStreamReader isr_kal = new InputStreamReader(fis_kal, "UTF-8");
            BufferedReader b_kal = new BufferedReader(isr_kal);

            text = b_kal.readLine();
            
            while ((text = b_kal.readLine()) != null) 
            {
                StringTokenizer tok = new StringTokenizer(text, " ");

                while(tok.hasMoreTokens())
                {
                    fst_word = tok.nextToken();
                    snd_word = tok.nextToken();
                    freq = tok.nextToken();
                    if(fst_word.equals(input_word1))
                    {    
                        word1_freq2=Integer.parseInt(freq);
                        float res=(float)(word1_freq2/word1_freq1);
                        ProbabilityCount temp = new ProbabilityCount();
                        temp.word=snd_word;
                        temp.probability=res;
                        probability_count1.add(temp);  
                        
                    }
                }
            }
        }
        
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
    //for 2ndt input ,,,probability count for 2 word/total count of  2nd  input word  from output_frequency2.txt,save fst word nd probability
    void loadAnndTokenize3()
    {
         
         String location ="src//word_frequency//";
         String fst_word,snd_word,freq,text;
         
        try 
        {
            FileInputStream fis_kal = new FileInputStream(location+"output_frequency2.txt");
            InputStreamReader isr_kal = new InputStreamReader(fis_kal, "UTF-8");
            BufferedReader b_kal = new BufferedReader(isr_kal);

            text = b_kal.readLine();
            
            while ((text = b_kal.readLine()) != null) 
            {
                StringTokenizer tok = new StringTokenizer(text, " ");

                while(tok.hasMoreTokens())
                {
                    fst_word = tok.nextToken();
                    snd_word = tok.nextToken();
                    freq = tok.nextToken();
   
                    if(snd_word.equals(input_word2))
                    {
                        word2_freq2=Integer.parseInt(freq);
                        float res=(float)(word2_freq2/word2_freq1);
                        ProbabilityCount temp = new ProbabilityCount();
                        temp.word=fst_word;
                        temp.probability=res;
                        probability_count2.add(temp);
                        System.out.println(snd_word+" "+fst_word+" "+res);
                    }
                    
                    
                }
            }
        }
        
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
    void problityCount()
    {
        for(int i=0;i<probability_count1.size();i++)
        {
            System.out.println(probability_count1.get(i).word+" "+probability_count1.get(i).probability+i);
        }
        
        System.out.println("--------------------------------------");
        
        
        for(int i=0;i<probability_count2.size();i++)
        {
            System.out.println(probability_count2.get(i).word+" "+probability_count2.get(i).probability+i);
        }
        
        System.out.println("---------------------------------------");
    }
    
    // get probability from two list by chack same word in two list,nd sort
    void probabilityList()
    {
        int chk=0;
        for(int i=0;i<probability_count1.size();i++)
        {
            String st=probability_count1.get(i).word;
            for(int j=0;j<probability_count2.size();j++)
            {
                if(st.equals(probability_count2.get(j).word))
                {
                    chk=1;
                    ProbabilityCount temp = new ProbabilityCount();
                    temp.word=st;
                    temp.probability=probability_count1.get(i).probability*probability_count2.get(j).probability;
                    probability_list.add(temp);
                    break;
                }
            }
        }
        
        try
        {
            if(chk==1)
            {
                Collections.sort(probability_list, new ProbabilityCount()); 
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }  
    
    // in each input possible output
    void possibleOutput() throws IOException
    {
        
        //String name = "C:\\Users\\Tasnim\\Documents\\NetBeansProjects\\BanglaLanguageProcessing\\word_frequency\\src\\word_frequency\\output.txt";
        //File fileLoc = new File(name); FileWriter fw = new FileWriter(fileLoc.getAbsoluteFile()); BufferedWriter bw = new BufferedWriter(fw);     

        for(int i=0;i<output.size();i++)
        {
            //System.out.println(i+"-------"+output.get(i).word+" "+output.get(i).probability+i);
            //bw.write(output.get(i).word+" "+output.get(i).probability+i);
            //bw.newLine();
             possible_word.put(output.get(i).word, num);
        }
        //bw.close();
        //fw.close();
        probability_count1.clear();
        probability_count2.clear();
        probability_list.clear();
        output.clear();
    }
    
    //editDistanceGenerate for each output nd sort
    
    void editDistanceGenerate(String mid_word)
    {
        for(String key:possible_word.keySet())
        {
            if(possible_word.get(key).equals(num))
            {
                int d=distance.computeDistance(mid_word, key); 
                //System.out.println("Distance------" + mid_word+"------"+key+"------"+dis);
                DistanceCount temp = new DistanceCount();
                temp.dis=d;
                temp.i_word=mid_word;
                temp.o_word=key;
                word_distance.add(temp);  
                //System.out.println(temp.i_word+ " " + temp.o_word + " " + temp.dis);
            }
        }
        
        try
        {
             Collections.sort(word_distance, new DistanceCount()); 
        }catch (Exception e)
        {
        }
        
        siz=word_distance.size()-1;
        
        try{
            chkstr=1;
            mvStr=word_distance.get(siz).o_word;
            String put=generate_word.put(word_distance.get(siz).i_word,word_distance.get(siz).o_word);
        }catch (Exception e)
        {
            chkstr=0;
        }
        
        possible_word.clear();
        word_distance.clear();
    }
    
    //output
    
    void getOutput() throws IOException
    {       
        String tex;
        String location ="src//word_frequency//";
       
        try 
        {
            FileInputStream fis_kal = new FileInputStream(location+"input1.txt");
            InputStreamReader isr_kal = new InputStreamReader(fis_kal, "UTF-8");
            BufferedReader b_kal = new BufferedReader(isr_kal);

            tex = b_kal.readLine();
            String tok_line;
            int get_data;
            while ((tex = b_kal.readLine()) != null) 
            {
                 StringTokenizer  tok = new StringTokenizer(tex, "।?");
                 while(tok.hasMoreTokens())
                 {
                        tok_line=tok.nextToken();
                        StringTokenizer  tok1 = new StringTokenizer(tok_line, " ’\";[]''!:;,()'[]{}:/=/”'‘˜'“");
                        int tok_lenth=tok1.countTokens();
                        get_data = 0;
                        for(int i=0;i<tok_lenth;i++)
                        {
                            String chck_word=tok1.nextToken();
                            
                            if(i==0||i==tok_lenth-1)
                            {
                                get_data=1;
                                System.out.print(chck_word+ " ");
                            }
                            
                            else
                            {
                                get_data=0;
                                for(String key:generate_word.keySet())
                                {
                                    if(chck_word.equals(key))
                                    {
                                        get_data=1;
                                        System.out.printf(generate_word.get(key)+ " ");
                                        break;
                                    }
                                }
                            }
                            
                            if(get_data==0)
                            {
                                System.out.printf("No_Solution"+" ");
                            }
                        }
                        System.out.print("\n");
                 }
                 
            }
        }
        
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    /*void getOutput() throws IOException
    { 
        System.out.printf(genfstword+ " ");
        for(String key:generate_word.keySet())
        {
            System.out.printf(generate_word.get(key)+ " ");
        }
        System.out.printf(input_word2+" ");
        System.out.print("\n");
    }*/
                
    
}