package probleme;

public class Job {
    private int p;
    private int d;
    private int w;

    public Job(int p, int d, int w) {
        this.p = p;
        this.d = d;
        this.w = w;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }
}
