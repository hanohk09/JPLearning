package Utilities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.SQLException;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import Models.LessonModel;

/**
 * Created by Hano on 20/12/2015.
 */
public class MSSQLUtils {
    Context context;
    Connection connection;

    private final String config_URL = "jdbc:jtds:sqlserver://192.168.1.2:1433;databaseName=LLDb;user=lldb;password=lldb;";
    private final String config_driver = "net.sourceforge.jtds.jdbc.Driver";
    private final String system = "system";

    public MSSQLUtils(Context context){
        this.context = context;
    }

    @SuppressLint("NewApi")
    public void connectDb() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        connection = null;
        try {
            Class.forName(config_driver);
            connection = DriverManager.getConnection(config_URL);
        } catch (SQLException e) {
            Log.e("ERROR", e.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERROR", e.getMessage());
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }
    }

    public boolean isConnected() {
        if (connection == null) {
            connectDb();
        }
        return connection != null;
    }

    String lesson_init =
            "IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='Lessons' AND xtype='U')\n" +
                    "   CREATE TABLE [dbo].[Lessons](\n" +
                    "       [ID] [int] PRIMARY KEY IDENTITY(1,1) NOT NULL,\n" +
                    "       [Name] [nvarchar](50) NOT NULL,\n" +
                    "       [Owner] [varchar](20) NOT NULL,\n" +
                    "       [IsPublic] [bit] NOT NULL DEFAULT(1),\n" +
                    "       [Version] [int] NOT NULL DEFAULT (0),\n" +
                    "       [InsDate] [datetime] NOT NULL DEFAULT(GETDATE()),\n" +
                    "       [InsUser] [nvarchar](20) NOT NULL,\n" +
                    "       [UpdDate] [datetime] NOT NULL,\n" +
                    "       [UpdUser] [nvarchar](20) NOT NULL,\n" +
                    "   )";

    public boolean initialDb(){
        if (isConnected()){
            try {
                Statement stmt = connection.createStatement();
                stmt.execute(lesson_init);
                return true;
            }
            catch (Exception ex) {
                return false;
            }
        }
        return false;
    }

    public final String lesson_insert =
            "INSERT INTO [dbo].[Lessons]([Name], [Owner], [IsPublic], [InsUser], [UpdDate], [UpdUser])\n" +
                    "VALUES (?, ?, ?, ?, GETDATE(), ?)";

    public boolean insertLesson(LessonModel les, Context context){
        if (isConnected()){
            try {
                PreparedStatement stmt = connection.prepareStatement(lesson_insert);
                stmt.setString(1, les.getName());
                stmt.setString(2, system);
                stmt.setBoolean(3, les.isPublic());
                stmt.setString(4, system);
                stmt.setString(5, system);
                return stmt.executeUpdate() == 1;
            }
            catch (Exception ex) {

            }
        }
        return false;
    }
}
