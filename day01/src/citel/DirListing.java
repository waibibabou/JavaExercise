package citel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DirListing {
    static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) {
        boolean R=false;
        boolean verb=false;
        String directory;
        directory=System.getProperty("user.dir");
        int len=args.length;
        if(len==0){
        }
        else if(len==1){
            if(args[0].equals("-R"))R=true;
            else if(args[0].equals("-verbose"))verb=true;
            else directory=args[0];
        }
        else if(len==2){
            if(args[0].equals("-R"))R=true;
            if(args[0].equals("-verbose")||args[1].equals("-verbose"))verb=true;
            if(!R||!verb)directory=args[1];
        }
        else if(len==3){
            R=true;
            verb=true;
            directory=args[2];
        }

        //递归输出
        if(R){
            List<File> list=new ArrayList<>();
            //调用函数得到所有文件
            recursive(directory,list);
            if(verb){
                for(int i=0;i<list.size();i++){
                    System.out.println(list.get(i).length()+" bytes "+formatter.format(list.get(i).lastModified())+" "+list.get(i).getName());
                }
            }
            else{
                for(int i=0;i<list.size();i++){
                    System.out.println(list.get(i).getName());
                }
            }

        }
        //非递归输出
        else{
            if(verb){
                File file=new File(directory);
                File[] files=file.listFiles();
                for(int i = 0; i< Objects.requireNonNull(files).length; i++){
                    System.out.println(files[i].length()+" bytes "+formatter.format(files[i].lastModified())+" "+files[i].getName());
                }
            }
            else{
                File file=new File(directory);
                File[] files=file.listFiles();
                for(int i = 0; i< Objects.requireNonNull(files).length; i++){
                    System.out.println(files[i].getName());
                }
            }
        }

    }

    public static void recursive(String path,List<File>list){
        File file=new File(path);
        File[] files=file.listFiles();
        for(int i = 0; i< Objects.requireNonNull(files).length; i++){
            if(files[i].isFile())list.add(files[i]);
            else{
                recursive(files[i].getPath(),list);
            }
        }

    }

}
