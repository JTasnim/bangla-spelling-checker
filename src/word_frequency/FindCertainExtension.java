package word_frequency;
import java.io.*;
import java.util.Vector;
 
public class FindCertainExtension {
 
        private String FILE_DIR = "C:\\Users\\Tasnim\\Documents\\NetBeansProjects\\BanglaLanguageProcessing\\Bangla Corpus version12.05.08";
        private String FILE_TEXT_EXT = ".txt";
        public String[] file_name;
        public Vector listFile = new Vector();
        public Vector listName = new Vector();
        //public Vector listName = new Vector();
        
        public FindCertainExtension()
        {
            
	}
        
        public void listFilesAndFilesSubDirectories(String directoryName){
 
        File directory = new File(directoryName);
 
        //get all the files from a directory
        File[] fList = directory.listFiles();
        
            for (File file : fList){
                if (file.isFile()){
                    listFile.add(file.getAbsolutePath());
                    listName.add(file.getName());
                    //System.out.println(file.getAbsolutePath() + file.getName() + file.getPath());
                } else if (file.isDirectory()){
                    listFilesAndFilesSubDirectories(file.getAbsolutePath());
                }
            }
        }
        
        public String[] listFile(String folder, String ext) 
        {
 
            GenericExtFilter filter = new GenericExtFilter(ext);

            File dir = new File(folder);
            String[] list = dir.list(filter);
            file_name = dir.list(filter);
            
            if(dir.isDirectory()==false)
            {
                //System.out.println("Directory does not exists : " + folder);                
            }

            // list out all the file name and filter by the extension
            

            if (list.length == 0) 
            {
                //System.out.println("no files end with : " + ext);                
            }

            int i=0;
            for (String file : list) 
            {                   
                String temp = new StringBuffer(folder).append(File.separator).append(file).toString();
                list[i]=temp;
                i++;
            }
            
            return list;
	}
 
	// inner class, generic extension filter
	public class GenericExtFilter implements FilenameFilter 
        {
            private String ext;

            public GenericExtFilter(String ext) 
            {
                this.ext = ext;
            }

            public boolean accept(File dir, String name) 
            {
                return (name.endsWith(ext));
            }
	}
}