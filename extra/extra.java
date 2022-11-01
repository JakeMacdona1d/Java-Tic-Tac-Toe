// checks if theres no win conditions
// default public boolean checkForDraw() {
//     char test[][];
//     test = new char[getNumColumns()][getNumRows()];

//     char symbols[] = new char[10];
//     int symnum = 0;

//     int spaces[][] = new int[getNumColumns() * getNumRows()][2];
//     int spacenum = 0;

//     for (int i = 0; getNumColumns() > i; i++)
//         for (int j = 0; getNumRows() > j; j++) {
//             BoardPosition pos = new BoardPosition(j,i);
//              char val = whatsAtPos(pos);
//              if (val != ' ') {
//                 boolean newChar = true;
//                 for (int k = 0; symnum > k; k++)
//                     if (symbols[k] == val) {
//                         newChar = false;
//                         break;
//                     }
//                 if (newChar) {
//                     symbols[symnum++] = val;
//                 }
//              } else {
//                 spaces[spacenum][0] = j;
//                 spaces[spacenum++][1] = i; 
//              }
//         }

//     for (int i = 0; symnum > i; i++) {
//         BoardPosition pos = new BoardPosition(0,0);
//         boolean draw = true;
//         for (int j = 0; spacenum > j; j++) {
//             pos = new BoardPosition(spaces[j][0],spaces[j][1]);
//             placeMarker(pos, symbols[i]);
//             if (checkForWinner(pos)) {
//                 draw = false;
//                 break;
//             }

//         }
//         if (draw) return true;

//         for (int j = 0; spacenum > j; j++) {
//             pos = new BoardPosition(spaces[j][0],spaces[j][1]);
//             placeMarker(pos, ' ');
//         }

//     }

//     return false;
// }
