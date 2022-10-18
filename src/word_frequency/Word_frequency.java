/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package word_frequency;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
/**
 *
 * @author Tasnim
 */
public class Word_frequency {

    HashMap<String,Integer> 
    listword=new HashMap<String, Integer>();
    
    HashMap<String,Integer> 
    list=new HashMap<String, Integer>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Word_frequency list=new Word_frequency();
       // list.loadAnndTokenize1();
        //list.loadAnndTokenize2();
        
    }
    
    void loadAnndTokenize1() throws IOException
    {
        String name = "C:\\Users\\Tasnim\\Documents\\NetBeansProjects\\BanglaLanguageProcessing\\word_frequency\\src\\word_frequency\\output_frequency1.txt"; 
        File fileLoc = new File(name); FileWriter fw = new FileWriter(fileLoc.getAbsoluteFile()); BufferedWriter bw = new BufferedWriter(fw);

        FindCertainExtension fileRead = new FindCertainExtension();

        fileRead.listFilesAndFilesSubDirectories("C:\\Users\\Tasnim\\Documents\\NetBeansProjects\\BanglaLanguageProcessing\\Bangla Corpus version12.05.08");
        
        //String location ="src/";
        String str,text;
        
        try 
        {
            /*FileInputStream fis_kal = new FileInputStream(location+"input.txt");
            InputStreamReader isr_kal = new InputStreamReader(fis_kal, "UTF-8");
            BufferedReader b_kal = new BufferedReader(isr_kal);*/
            
            for (int i=0; i<fileRead.listFile.size();i++) 
            {
                //System.out.println(fileRead.listName.elementAt(i));
                FileInputStream fis_kal = new FileInputStream(fileRead.listFile.elementAt(i).toString());
                InputStreamReader isr_kal = new InputStreamReader(fis_kal, "UTF-8");
                BufferedReader b_kal = new BufferedReader(isr_kal);

                text = b_kal.readLine();

                while ((text = b_kal.readLine()) != null) 
                {
                    StringTokenizer tok = new StringTokenizer(text, " ˈ.—“।’‘\";?[]''!:;,()[]{}:/=/”'˜'");

                    while(tok.hasMoreTokens())
                    {
                        str = tok.nextToken();

                        if(listword.containsKey(str))
                        {
                            int p=listword.get(str);
                            p++;
                            listword.put(str, p);
                        }

                        else
                        {
                            listword.put(str, 1);
                        }

                    }
                }
            }
        }
        
        catch (Exception e)
        {
            e.printStackTrace();
        } 
        
        for(String key:listword.keySet())
        {
            bw.write(key+" "+listword.get(key));
            bw.newLine();
        }
        
        bw.close();
        fw.close();
        
    }
    
    
    void loadAnndTokenize2() throws IOException
    {
        String name = "C:\\Users\\Tasnim\\Documents\\NetBeansProjects\\BanglaLanguageProcessing\\word_frequency\\src\\word_frequency\\output_frequency2.txt"; 
        File fileLoc = new File(name); FileWriter fw = new FileWriter(fileLoc.getAbsoluteFile()); BufferedWriter bw = new BufferedWriter(fw);
       // String name = "C:\\Users\\Tasnim\\Documents\\NetBeansProjects\\BanglaLanguageProcessing\\listWord\\src\\output_frequency.txt"; File fileLoc = new File(name); FileWriter fw = new FileWriter(fileLoc.getAbsoluteFile()); BufferedWriter bw = new BufferedWriter(fw); 
        FindCertainExtension fileRead = new FindCertainExtension();

        fileRead.listFilesAndFilesSubDirectories("C:\\Users\\Tasnim\\Documents\\NetBeansProjects\\BanglaLanguageProcessing\\Bangla Corpus version12.05.08");
        
        String location ="src/";
        String str,text,str1 = null,str2,str3,str4 = null;
        
        try 
        {
            for (int i=0; i<fileRead.listFile.size();i++) 
            {
                //System.out.println(fileRead.listName.elementAt(i));
                FileInputStream fis_kal = new FileInputStream(fileRead.listFile.elementAt(i).toString());
                InputStreamReader isr_kal = new InputStreamReader(fis_kal, "UTF-8");
                BufferedReader b_kal = new BufferedReader(isr_kal);

                text = b_kal.readLine();
                String line = null;
                while ((text = b_kal.readLine()) != null) 
                {
                    StringTokenizer tok = new StringTokenizer(text, "।?");

                    while(tok.hasMoreTokens())
                    {
                        str = tok.nextToken();
                        
                        StringTokenizer tok1 = new StringTokenizer(str, " ’\";[]''!:;,()[]{}:-/=/”'‘˜'“");

                        if(tok1.hasMoreTokens())
                        {
                            str1 =  tok1.nextToken();
                        }

                        while(tok1.hasMoreTokens())
                        {                   
                            str2 =  str1;

                            if(tok1.hasMoreTokens())
                            {                               
                                str3 =  tok1.nextToken();
                                str4 =  str2+' '+str3;
                                str1=   str3;
                            }

                            if(list.containsKey(str4))
                            {
                               int v=list.get(str4);
                               v++;
                               list.put(str4, v);
                            }

                            else
                            {
                               list.put(str4, 1);
                            }
                        }
                    }
                }
            }
        }
        
        catch (Exception e)
        {
            e.printStackTrace();
        } 
        for(String key:list.keySet())
        {
            bw.write(key+" "+list.get(key));
            bw.newLine();
        }
        bw.close();
        fw.close();
        
    }
}

