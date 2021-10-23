package javaproblems;

import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;

// https://leetcode.com/problems/find-duplicate-file-in-system/
public class DuplicateFileContents {
    /**
     * Given a list paths of directory info, including the directory path, and all the files with contents in this directory, return all the duplicate files in the file system in terms of their paths. You may return the answer in any order.

        A group of duplicate files consists of at least two files that have the same content.

        A single directory info string in the input list has the following format:

        "root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"
        It means there are n files (f1.txt, f2.txt ... fn.txt) with content (f1_content, f2_content ... fn_content) respectively in the directory "root/d1/d2/.../dm". Note that n >= 1 and m >= 0. If m = 0, it means the directory is just the root directory.

        The output is a list of groups of duplicate file paths. For each group, it contains all the file paths of the files that have the same content. A file path is a string that has the following format:

        "directory_path/file_name.txt"
        
        Example 1:

        Input: paths = ["root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)","root 4.txt(efgh)"]
        Output: [["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]
        Example 2:

        Input: paths = ["root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)"]
        Output: [["root/a/2.txt","root/c/d/4.txt"],["root/a/1.txt","root/c/3.txt"]]
        

        Constraints:

        1 <= paths.length <= 2 * 104
        1 <= paths[i].length <= 3000
        1 <= sum(paths[i].length) <= 5 * 105
        paths[i] consist of English letters, digits, '/', '.', '(', ')', and ' '.
        You may assume no files or directories share the same name in the same directory.
        You may assume each given directory info represents a unique directory. A single blank space separates the directory path and file info.
        

        Follow up:

        Imagine you are given a real file system, how will you search files? DFS or BFS?
        If the file content is very large (GB level), how will you modify your solution?
        If you can only read the file by 1kb each time, how will you modify your solution?
        What is the time complexity of your modified solution? What is the most time-consuming part and memory-consuming part of it? How to optimize?
        How to make sure the duplicated files you find are not false positive?

        Idea: Store the contents as key in a map and maintain file names (w. path) as the values. Then just drop unqiue keys
        and retain only duplicate values.

        Time: O(paths.length * n+1) - n files in a dir/file_path
        Space: O(max_distinct_content + all_file_names)

     * @param paths
     * @return
     */
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> fileContentsToFilesMap = new HashMap<>();
        Pattern fileContentPattern = Pattern.compile("([A-Za-z0-9\\.]*)\\((.*)\\)");
        for(String path: paths){
            String[] parts = path.split(" ");
            String filePath = parts[0];
            for(int i=1;i<parts.length;i++){ // filenames with content
                Matcher fileContentMatcher = fileContentPattern.matcher(parts[i]);
                if(fileContentMatcher.find()){
                    String content = fileContentMatcher.group(2); // file contents
                    String fileName = fileContentMatcher.group(1);
                    if(fileContentsToFilesMap.containsKey(content)){
                        fileContentsToFilesMap.get(content).add(filePath + "/" + fileName);
                    } else {
                        List<String> fileNames = new ArrayList<>();
                        fileNames.add(filePath + "/" + fileName);
                        fileContentsToFilesMap.put(content, fileNames);
                    }
                }
            }
        }
        // retain only duplicates (keyset iterator)
        Iterator<String> contentIterator = fileContentsToFilesMap.keySet().iterator();
        while(contentIterator.hasNext()){
            String content = contentIterator.next();
            if(fileContentsToFilesMap.get(content).size() < 2){
                // removing the key from keyset, also modifies the map
                contentIterator.remove();
            }
        }
        return fileContentsToFilesMap.values().stream().collect(Collectors.toList());
    }
}
