import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import java.util.Stack;

public class Finder {

    final static boolean visited = false;

    public static void main(String[] args) {

        String userHomePath = System.getProperty("user.home");

        Path sPath = Paths.get(userHomePath);
        File dfsEx = dfsLarge(sPath);
        File bfsEx = bfsLarge(sPath);

        //Prints for DFS
        if(dfsEx != null) {
            String found = "Starting at :" + sPath.toAbsolutePath().toString() + "\nthe largest file was found here: " + dfsEx.getAbsolutePath()
                    + "\nand its size is: " + dfsEx.length();
            System.out.println(found);
        }
        else {
            System.out.println("no largest file found: " + sPath.toAbsoultePath().toString());
        }
        //Prints for BFS
        if(dfsEx != null) {
            String bfs = "Starting at : " + sPath.toAbsolutePath().toString() + "\nthe largest file was found here: " + bfsEx.getAbsolutePath()
                    + "\nand its size is: " + bfsEx.length();
            System.out.println(bfs);
        }
        {
            else{
                System.out.println("no largest file found: " + sPath.toAbsoultePath().toString());

        }
        }
        }
    }




    private static File large(final File file1, final File file2) {

        if (file1 == null || !file1.exists() || !file1.isFile()) {
            if (file2 != null && file2.exists() && file2.isFile()) {
                return file2;
            }
        }

        if (file1 == null || !file2.exists() || !file2.isFile()) {
            if (file1 != null && file1.exists() && file1.isFile()) {
                return file2;
            }
        }

        assert file1 != null;
        return file1.length() > file2.length() ? file1 : file2;
    }

    //BFS Implementation
    private static File bfsLarge(final Path p) {
        File large = null;
        final Queue<File>queue = new LinkedList<>();
        //mark the current folder as visited and add to queue
        queue.add(p.toFile());
        while(!queue.isEmpty()) {
            File main = queue.remove();

            final File[] fa = main.listFiles();
            if (fa == null) {
                continue;  // if null then directory is probably not accessible
            }
            for(File f : fa){
                if(f.isDirectory())
                {
                    queue.add(f);
                    continue;
                }
                if (large != null) {
                    large = large(large, f);
                } else {
                    large = f;
                }
            }

        }

        return large;
    }


    //DFS Implementation
    private static File dfsLarge(final Path p) {

        File large = null;

        //LinkedList for holding the directories/folders
        final Stack<File> stack = new Stack<>();
        stack.push(p.toFile());

        while (!stack.isEmpty()) {
            File dir = stack.pop();
            final File[] fa = dir.listFiles();
            if (fa == null) {
                continue;
            }

            for (File f : fa) {
                if (f.isDirectory()) {
                    stack.push(f);
                    continue;
                }
                if (large != null) {
                    large = large(large, f);
                } else {
                    large = f;
                }
            }
        }

        return large;
    }
}