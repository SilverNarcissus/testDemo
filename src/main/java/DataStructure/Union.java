package DataStructure;

public class Union {

  int[] array;

  public Union(int size) {
    array = new int[size];

    for (int i = 1; i < size; i++) {
      array[i] = i;
    }
  }

  public int find(int loc) {
    if (array[loc] != loc) {
      array[loc] = find(array[loc]);
    }

    return array[loc];
  }

  public void union(int a, int b) {
    array[find(a)] = array[find(b)];
  }
}
