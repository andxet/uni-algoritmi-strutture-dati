package peretti.unionFind;

public class FabbricaUnionFindInt {
	public static UnionFindInt crea(int n) {
	    return new UnionFindIntArray(n);
	}
}
