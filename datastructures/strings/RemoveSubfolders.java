package datastructures.strings;

import java.util.*;

public class RemoveSubfolders {
    /**
     * https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/
     * 
     * Given a list of folders, remove all sub-folders in those folders and return in any order the folders after removing.

        If a folder[i] is located within another folder[j], it is called a sub-folder of it.

        The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters. For example, /leetcode and /leetcode/problems are valid paths while an empty string and / are not.

        

        Example 1:

        Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
        Output: ["/a","/c/d","/c/f"]
        Explanation: Folders "/a/b/" is a subfolder of "/a" and "/c/d/e" is inside of folder "/c/d" in our filesystem.
        Example 2:

        Input: folder = ["/a","/a/b/c","/a/b/d"]
        Output: ["/a"]
        Explanation: Folders "/a/b/c" and "/a/b/d/" will be removed because they are subfolders of "/a".
        Example 3:

        Input: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
        Output: ["/a/b/c","/a/b/ca","/a/b/d"]
     * @param folder
     * @return
     */
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder); // sorted such that subfolders come later in the array
        List<String> folders = new ArrayList<String>();
        for(String f:folder){
            if(folders.isEmpty()){
                folders.add(f);
            } else {
                // delete subfolder and check if folder exists
                int endIndex = f.lastIndexOf("/", f.length());
                boolean parentFolderFound = false;
                while(endIndex >0){ // don't delete the first slash, at index 0
                    String tentative = f.substring(0, endIndex);   
                    if(folders.contains(tentative)){
                        parentFolderFound = true;   
                        break;
                    }
                    endIndex = f.lastIndexOf("/", endIndex-1); // to skip current slash char
                }
                if(!parentFolderFound){
                    // new parent folder add it final list
                    folders.add(f);
                } // else its a sub folder which can be deleted
            }
        }
        return folders;
    }
}