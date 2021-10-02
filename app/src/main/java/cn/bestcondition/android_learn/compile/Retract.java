package cn.bestcondition.android_learn.compile;

public class Retract {
    private final String retract;


    public Retract(String retract) {
        this.retract = retract;
    }

    public boolean hasRetract(String origin) {
        return origin.startsWith(this.retract);
    }

    public String offRetract(String origin) {
        assert hasRetract(origin);
        return origin.substring(retract.length());
    }

}
