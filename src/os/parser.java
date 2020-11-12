package os;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class parser
{
    String[] args;
    String cmd=null;

    /**
     * Parse Function
     * this function take a command line from the user and split it into two parts command saved in cmd
     * and arguments saved into args
     * @param input this is the command line (command + arguments)
     * @return True if it success and false if the command or arguments isn't valid
     * */
    public boolean parse(String input)
    {
        String command=null;
        String[] split_args = null;

        //Remain | && > && >> && more
        if(input.contains("|"))
        {
            //Pipes Case
            boolean pipe_exist = false;
            if(input.contains("|") == true){
                pipe_exist = true;
            }
            //Pipe Command
            if(pipe_exist){
                command = "|";
                split_args =input.split("|");

            }
        }
        else if(input.contains(">")||input.contains(">>"))
        {
            if(input.contains(">"))
            {
                split_args = input.split(">");
                command=">";
            }

            else
            {
                split_args=input.split(">>");
                command=">>";
            }

        }
        else{
            split_args =input.split(" ");
            command=split_args[0];
            split_args = Arrays.copyOfRange(split_args,1,split_args.length -1);
            String[] commands = {"cd" , "ls" , "cp" , "cat" ,"more" ,"mkdir" ,"rmdir" ,"mv","rm","args","date"
                    ,"help","pwd","clear"};

            //Validate The Name of command if it's valid
            boolean isFound = false;
            for (String s:commands){
                if(Arrays.asList(commands).contains(command.toLowerCase())){
                    isFound = true;
                }
            }
            if (isFound == false){
                System.out.println("Invalid Command!");
                return false;
            }
            //Validation of the count of args
            switch (command){
                case "cd":
                    if(split_args.length != 1 || split_args.length != 0){
                        System.out.println("Invalid Command Arguments!");
                        return false;
                    }
                    break;
                case "more":
                case "ls":
                    if(split_args.length > 1){
                        System.out.println("Too much args!");
                        return false;
                    }
                    break;
                case "pwd":
                case "help":
                case "clear":
                case "date":
                    if(split_args.length > 0){
                        System.out.println("Commands Dosn't Take Any Arguments!");
                        return false;
                    }
                    break;
                case "cp":
                case "mv":
                    if(split_args.length != 2){
                        if(split_args.length < 2)
                            System.out.println("Too Few Arguments!");
                        else
                            System.out.println("Too Much Argument!");
                        return false;
                    }
                    break;
                case "mkdir":
                case "rmdir":
                case "rm":
                case "args":
                    if(split_args.length != 1){
                        System.out.println("Invalid Command Arguments!");
                        return false;
                    }
                    break;
            }
        }
        //Save Command And Arguments
        this.args = split_args;
        this.cmd = command;
        return true;
    }
    public String[] getArguments()
    {
        return args;
    }

    public String getCmd()
    {
        return cmd;
    }


}