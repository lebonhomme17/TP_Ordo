package probleme;

public class Job {
    private String name;
    private int p;
    private int d;
    private int w;

    public Job(String name, int p, int d, int w) {
        this.name = name;
        this.p = p;
        this.d = d;
        this.w = w;
    }

    public Job(String name, int p, int d) {
        this(name, p, d, 1);
    }

    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
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
