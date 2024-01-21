# Project 2

### Merge Sort

```c
                            [16,21,11,8,12,22]
```

#### a) Write the steps of Merge Sort according to the given array.
```c
Step 1:  // divide the array to sub-arrays
         [16,21,11]   [8,12,22]
Step 2:  // continue dividing until every item is isolated
         [16,21] [11]   [8,12] [22]
Step 3:  // now compare the items and merge them
         [16] [21] [11]   [8] [12] [22]
Step 4:  // continue merging by comparing
         [16,21] [11]   [8,12] [22]
Step 5:  // one more time
         [11,16,21]   [8,12,22]
Step 6:  // the array is sorted
         [8,11,12,16,21,22]
```

#### b) Specify the Big-O Notation.
```
The Time Complexity of the Merge Sort algorithm is O(n*lgn).
```