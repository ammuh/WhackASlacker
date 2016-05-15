/**
 * Created by ammu on 5/7/16.
 */
public interface Character {
    public String getSoundPath();
    public String getSpritePath();
    public int getPointValue();
    public int getTimeUp();
    public int getRanking();
    public void pop(Thread t);
}
