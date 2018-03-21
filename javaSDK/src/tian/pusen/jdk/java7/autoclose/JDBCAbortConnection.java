package tian.pusen.java7.autoclose;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class JDBCAbortConnection {
	public void abortConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost/java7book");
        ThreadPoolExecutor executor = new DebugExecutorService(2, 10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        connection.abort(executor);
        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.MINUTES);
            System.out.println(executor.getCompletedTaskCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static class DebugExecutorService extends ThreadPoolExecutor {
        public DebugExecutorService(int corePoolSize, int maximumPoolSize,
                long keepAliveTime, TimeUnit unit,
                BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }
        public void beforeExecute(Thread t, Runnable r) {
            System.out.println("清理任务：" + r.getClass());
            super.beforeExecute(t, r);
        }
    }
    public static void main(String[] args) {
    	JDBCAbortConnection ca = new JDBCAbortConnection();
        try {
            ca.abortConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
//
//在JDK7之后，Connection、Statement、CallableStatement、PreparedStatement、ResultSet、RowSet、JdbcRowSet、
// JoinRowSet、CachedRowSet、FilteredRowSet、WebRowSet 等都实现了AutoCloseable 接口，
// 所以可以使用Try-with-Resources方式进行管理

//由于资源是按照先声明后关闭的顺序，所以在关闭时，以上例子将按照ResultSet-->Statement-->Connection的顺序关闭。
//
//然而，如果使用以下匿名新建的方式
//则将导致只有ResultSet关闭了，而建立的Connection和Statement对象都不会被自动关闭而导致资源泄露。
//
