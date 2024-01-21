# Project 1

### Insertion Sort

```c
                            [22,27,16,2,18,6]
```

#### a) Write the steps of Insertion Sort according to the given array.
```c
Step 1:  [22,27,16,2,18,6] // 22,27 sorted but 16 not
Step 2:  [22,16,27,2,18,6] // 16 still not sorted
Step 3:  [16,22,27,2,18,6] // 2 not sorted
Step 4:  [16,22,2,27,18,6] // 2 not sorted yet
Step 5:  [16,2,22,27,18,6] // one more time
Step 6:  [2,16,22,27,18,6] // 18 not sorted
Step 7:  [2,16,22,18,27,6] // 18 not sorted yet
Step 8:  [2,16,18,22,27,6] // lastly, sort 6
Step 9:  [2,16,18,22,6,27]
Step 10: [2,16,18,6,22,27]
Step 11: [2,16,6,18,22,27]
Step 12: [2,6,16,18,22,27] // all items sorted
```

#### b) Specify the Big-O Notation.
```
The Time Complexity of the Insertion Sort algorithm is O(n^2).
```

#### c) Time Complexity: After the array is sorted, the number 18 falls under which of the following cases?
1. Average case: The number we are looking for is in the middle
2. Worst case: The number we are looking for is at the end
3. Best case: The number we are looking for is at the beginning of the array
```
Since the number 18 is in the middle of the array, the answer will be Average Case.
```
---
### Selection Sort
```c
                            [7,3,5,8,2,9,4,15,6]
```
write the first four steps of the Selection Sort according to the given array.
```c
Step 1: [2,3,5,8,7,9,4,15,6] // swapped 2 and 7
Step 2: [2,3,5,8,7,9,4,15,6] // 3 has been compared, no smaller value found
Step 3: [2,3,4,8,7,9,5,15,6] // swapped 5 and 4
Step 4: [2,3,4,5,7,9,8,15,6] // swapped 8 and 5
```