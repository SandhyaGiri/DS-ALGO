// Problem: Find maximum number whose frequency is same as the number


// you can write to stdout for debugging purposes, e.g.
// console.log('this is a debug message');

function solution(A) {
    // write your code in JavaScript (Node.js 8.9.4)
    if(A.length > 0) {
        // sort the array O(nlgn) - ascending
        A.sort((a,b) => a-b);
        let occurences = 1;
        let maxNumber = A[A.length-1]; // last number to start with
        let numFound = false;
        // traverse from end, find the first X that occurs X times
        for(let i=A.length-2;i>=0;i--) {
            if(A[i] == maxNumber) {
                occurences += 1;
            } else {
                // marks end of occurence of maxNumber, check for count match
                if(occurences == maxNumber){
                    numFound = true;
                    break;
                }
                // reset to the current number
                maxNumber = A[i];
                occurences = 1;
            }
        }
        // for the last chain ending at index 0
        if(!numFound && A[0] == maxNumber && occurences == A[0]) {
            numFound = true;
        }
        return numFound ? maxNumber : 0;
    }
}

function solution(A) {
    if(A.length > 0) {
        let numCounts = {};
        // traverse the array and maintain the counts of each number O(n)
        for(let i=0;i<A.length;i++) {
            let ele = A[i];
            if(numCounts[ele] == undefined) {
                numCounts[ele] = 1;
            } else {
                numCounts[ele] = numCounts[ele] + 1;
            }
        }
        
        // numeric keys in objects are already sorted
        // traverse numcounts from end and return first key with same value (also largest)
        const keys = Object.keys(numCounts);
        let maxNumber = undefined;
        for(let i=keys.length-1;i>=0;i--) {
            if(numCounts[keys[i]] == keys[i]) {
                maxNumber = numCounts[keys[i]];
                break;
            }
        }
        return maxNumber == undefined ? 0 : maxNumber;
    }
}