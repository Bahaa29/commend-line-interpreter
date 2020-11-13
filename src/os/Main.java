package os;
import os.Terminal;
public class Main {

    public static void main(String[] args) {
        Terminal terminal = new Terminal();
        //terminal.mkdir("testfolder");
        //terminal.rmdir("testfolder");
        //terminal.rm("test.txt");
        //terminal.mv("/home/mohamed/test.txt","/home/Desktop/test.txt");
        //terminal.pipe();
    }
    public static void clear1(String []args1)
    {
        System.out.println("you are restarted");
        main(args1);
    }
}
