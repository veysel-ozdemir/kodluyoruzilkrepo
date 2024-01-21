# Project 3

### Binary-Search Tree

```c
                            [7,5,1,8,3,6,0,9,4,2]
```

#### Write the steps of Binary-Search Tree according to the given array.
```c
                    7
                   /  \
                  5    8
                 / \    \            
                1   6    9
               / \
              0   3
                 / \
                2   4

The main root is 7
Step 1:  5 is smaller than the main root, goes to left leaf of main root

Step 2:  1 is smaller than the main root and 5, goes to left leaf of root 5

Step 3:  8 is bigger than the root, goes to right leaf of main root

Step 4:  3 is smaller than main root and 5, whilst bigger than root 1, goes to right leaf of root 1

Step 5:  6 is smaller than main root but bigger than root 5, gooes to right leaf of root 5

Step 6:  0 is smaller than main root, 5, and 1, goes to left leaf of root 1

Step 7:  9 is bigger than main root and 8, goes to right leaf of root 8

Step 8:  4 is smaller than main root, 5, whilst bigger than 1 and 3, goes to right leaf of root 3

Step 9:  2 is smaller than main root and 5 whilst bigger than 1. Since it is also smaller than 3, it goes to left leaf of root 3
```