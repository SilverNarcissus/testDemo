package DataStructure;

public class FenwickTree {
  int[] array;
  int size;


  public FenwickTree(int size) {
    this.size = size;
    array = new int[size];
  }

  public void add(int loc, int val){
    while(loc < size){
      array[loc] += val;
      loc += loc & (-loc);
    }
  }

  public int sum(int loc){
    int res = 0;
    while(loc > 0){
      res += array[loc];
      loc -= loc & (-loc);
    }

    return res;
  }
}
