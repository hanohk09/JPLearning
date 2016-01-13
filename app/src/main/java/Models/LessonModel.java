package Models;

/**
 * Created by Hano on 20/12/2015.
 */
public class LessonModel extends BaseEntityModel {
    private boolean IsCheck = false;
    private long ID;
    private long LesID;
    private String Name;
    private String Owner;
    private boolean IsPublic;
    private int State;
    private int Words;

    public boolean isCheck() {
        return IsCheck;
    }

    public void setIsCheck(boolean isCheck) {
        IsCheck = isCheck;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getLesID() {
        return LesID;
    }

    public void setLesID(long lesID) {
        LesID = lesID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public boolean isPublic() {
        return IsPublic;
    }

    public void setIsPublic(boolean isPublic) {
        IsPublic = isPublic;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    public int getWords() {
        return Words;
    }

    public void setWords(int words) {
        Words = words;
    }
}
