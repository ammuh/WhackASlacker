/**
 * Created by ammu on 5/7/16.
 */
public abstract class Character {
    public abstract String getSoundPath();
    public abstract String getSpritePath();
    public abstract int getPointValue();
    public abstract int getTimeUp();
    public abstract int getRanking();
    public abstract void pop(Thread t);
}
