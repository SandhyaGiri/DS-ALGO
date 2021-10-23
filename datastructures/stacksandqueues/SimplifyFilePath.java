package datastructures.stacksandqueues;

import java.util.*;

// https://leetcode.com/problems/simplify-path/
public class SimplifyFilePath {
    /**
     * Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file system, convert it to the simplified canonical path.

        In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the directory up a level, and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'. For this problem, any other format of periods such as '...' are treated as file/directory names.

        The canonical path should have the following format:

        The path starts with a single slash '/'.
        Any two directories are separated by a single slash '/'.
        The path does not end with a trailing '/'.
        The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..')
        Return the simplified canonical path.

        

        Example 1:

        Input: path = "/home/"
        Output: "/home"
        Explanation: Note that there is no trailing slash after the last directory name.
        Example 2:

        Input: path = "/../"
        Output: "/"
        Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
        Example 3:

        Input: path = "/home//foo/"
        Output: "/home/foo"
        Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
        Example 4:

        Input: path = "/a/./b/../../c/"
        Output: "/c"

        Idea: Store '/<folder_name>' inside a stack, on encountering a /. ignore, /.. pop the stack, otherwise push
        the new dir into the stack. Finally combine all elements in the stack.

     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        Stack<String> directories = new Stack<>();
        int currIndex = 0;
        int endIndex = path.indexOf("/", currIndex+1);
        boolean setToEndIndex = false; // should be done once, to avoid Time limit exceeded
        while(!setToEndIndex && endIndex <= path.length()){
            if(endIndex == -1){
                setToEndIndex = true;
                endIndex = path.length();
            }
            //System.out.println("curr: "+ currIndex +", end: " + endIndex);
            String dirName = path.substring(currIndex, endIndex); // including slash /a
            if(dirName.equals("/.") || dirName.equals("/")){
                currIndex = endIndex;
            } else if(dirName.equals("/..")){
                if(!directories.empty()){
                    directories.pop(); // going up one level
                }
                currIndex = endIndex;
            } else {
                // insert into stack
                directories.push(dirName);
                // System.out.println(dirName);
                currIndex = endIndex;
            }
            endIndex = path.indexOf("/", currIndex+1);
        }
        Optional<String> canonicalPath = directories.stream().reduce((a,b) -> a+b);
        return canonicalPath.isPresent() ? canonicalPath.get(): "/";
    }
}
