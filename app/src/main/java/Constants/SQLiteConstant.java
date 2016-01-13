package Constants;

/**
 * Created by Hano on 20/12/2015.
 */
public class SQLiteConstant {
    public static final String DB_NAME = "LLDb";
    public static final int DB_VERSION = 1;

    // TABLE LESSON - DETAILS

    public static final String LESSON_TABLE = "Lessons";
    public static final String LESSON_COL_ID = "ID";
    public static final String LESSON_COL_LESID = "LesID";
    public static final String LESSON_COL_NAME = "Name";
    public static final String LESSON_COL_OWNER = "Owner";
    public static final String LESSON_COL_STATE = "State";
    public static final String[] LESSON_COLS = new String[] {
            SQLiteConstant.LESSON_COL_ID,
            SQLiteConstant.LESSON_COL_LESID,
            SQLiteConstant.LESSON_COL_NAME,
            SQLiteConstant.LESSON_COL_OWNER,
            SQLiteConstant.LESSON_COL_STATE };

    public static final String LESSON_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS [" + LESSON_TABLE + "] (\n" +
                    "   [" + LESSON_COL_ID + "] INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "   [" + LESSON_COL_LESID + "] INTEGER  NULL,\n" +
                    "   [" + LESSON_COL_NAME + "] NVARCHAR(20)  NOT NULL,\n" +
                    "   [" + LESSON_COL_OWNER + "] VARCHAR(20)  NULL,\n" +
                    "   [" + LESSON_COL_STATE + "] INTEGER  NULL\n" +
                    ");";
    public static final String LESSON_DROP_TABLE = "DROP TABLE IF EXISTS [" + LESSON_TABLE + "]";
    public static final String LESSON_LIST = "SELECT  * FROM [" + LESSON_TABLE + "]";
}
